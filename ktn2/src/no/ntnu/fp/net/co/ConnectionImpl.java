/*
 * Created on Oct 27, 2004
 */
package no.ntnu.fp.net.co;

import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import no.ntnu.fp.net.admin.Log;
import no.ntnu.fp.net.cl.ClException;
import no.ntnu.fp.net.cl.ClSocket;
import no.ntnu.fp.net.cl.KtnDatagram;
import no.ntnu.fp.net.cl.KtnDatagram.Flag;

/**
 * Implementation of the Connection-interface. <br>
 * <br>
 * This class implements the behaviour in the methods specified in the interface
 * {@link Connection} over the unreliable, connectionless network realised in
 * {@link ClSocket}. The base class, {@link AbstractConnection} implements some
 * of the functionality, leaving message passing and error handling to this
 * implementation.
 * 
 * @author Sebjørn Birkeland and Stein Jakob Nordbø
 * @see no.ntnu.fp.net.co.Connection
 * @see no.ntnu.fp.net.cl.ClSocket
 */
public class ConnectionImpl extends AbstractConnection {

    

	/** Keeps track of the used ports for each server port. */
	private static Map<Integer, Boolean> usedPorts = Collections.synchronizedMap(new HashMap<Integer, Boolean>());
	private volatile static int minPort = 40000;
	private volatile static int lastAssignedPort = 40000;
	private volatile static int maxPort = 45000;
	
	private int sendCount;


	/**
	 * Initialise initial sequence number and setup state machine.
	 * 
	 * @param myPort
	 *            - the local port to associate with this connection
	 */
	public ConnectionImpl(int myPort) {
		super();
		this.sendCount = 0;
		this.myPort = myPort;
		this.myAddress = getIPv4Address();
	}

	private String getIPv4Address() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		}
		catch (UnknownHostException e) {
			return "127.0.0.1";
		}
	}

	private static synchronized int getValidPort() {
		boolean freePort;
		do {
			if(++lastAssignedPort > maxPort){
				lastAssignedPort = minPort;
			}
			if(usedPorts.get(lastAssignedPort) == null) {
				freePort = true;
			}
			else { 
				freePort = usedPorts.get(lastAssignedPort);
			}
		} while(!freePort);
		
		usedPorts.put(lastAssignedPort, false);
		return lastAssignedPort;
	}

	/**
	 * Establish a connection to a remote location.
	 * 
	 * @param remoteAddress
	 *            - the remote IP-address to connect to
	 * @param remotePort
	 *            - the remote portnumber to connect to
	 * @throws IOException
	 *             If there's an I/O error.
	 * @throws java.net.SocketTimeoutException
	 *             If timeout expires before connection is completed.
	 * @see Connection#connect(InetAddress, int)
	 */
	public void connect(InetAddress remoteAddress, int remotePort) throws IOException,
	SocketTimeoutException {

		/*
		 * This chunk tries to initialize connection to server
		 */
		this.remoteAddress = remoteAddress.getHostAddress();
		this.remotePort = remotePort;
		KtnDatagram syn_packet = constructInternalPacket(Flag.SYN);
		try {
			simplySendPacket(syn_packet);
			state = State.SYN_SENT;
		} catch (ClException e) {
			e.printStackTrace();
			throw new IOException("Unable to send SYN packet...");
		}

		/*
		 *This chunk waits for the response and sets new port if desired. 
		 */
		KtnDatagram response = null;
		response = receivePacket(true);
		if (response == null) throw new SocketTimeoutException("Server not responding...");
		
		System.out.println("Received connection request response packet");
		if (response.getFlag() != Flag.SYN_ACK) throw new IOException("Connection refused...");
		
		this.remoteAddress = response.getSrc_addr();
		this.remotePort = response.getSrc_port();
		
		//ACK'ing SYN_ACK
		KtnDatagram ack = constructInternalPacket(Flag.ACK);	
		try {
			simplySendPacket(ack);
			state = State.ESTABLISHED;
		}catch (ClException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (state != State.ESTABLISHED) throw new IOException("Connection failed...");
	}

	/**
	 * Listen for, and accept, incoming connections. Locks thread until a message is reveived;
	 * 
	 * @return A new ConnectionImpl-object representing the new connection, or null if it fails.
	 * @see Connection#accept()
	 */
	public Connection accept() throws IOException, SocketTimeoutException {
		state = State.LISTEN;
		KtnDatagram connectionRequestPackage = null;
		
		//This loop listens for incoming SYN requests.
		while (connectionRequestPackage == null) { 
			connectionRequestPackage = receivePacket(true);
		}
		
		ConnectionImpl delegateSocket = null;
		if(connectionRequestPackage.getFlag() == Flag.SYN) {
			System.out.println("SYN received");
			
			//Setting up a a new connection that requesting end can talk to.
			delegateSocket = new ConnectionImpl(getValidPort());
			delegateSocket.state = State.SYN_RCVD;
			delegateSocket.remoteAddress = connectionRequestPackage.getSrc_addr();
			delegateSocket.remotePort = connectionRequestPackage.getSrc_port();
			
			//sending SYN_ACK response through new delegate socket;
			KtnDatagram syn_ackResponse = delegateSocket.constructInternalPacket(Flag.SYN_ACK);
			try {
				delegateSocket.simplySendPacket(syn_ackResponse);
			} catch (ClException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			KtnDatagram ack = delegateSocket.receivePacket(true);
			if (ack != null && ack.getFlag() == Flag.ACK) {
				delegateSocket.state = State.ESTABLISHED;
				System.out.println("Three-way-handshake successful");
			}
			else {
				System.out.println("Didn't receive ACK from SYN_ACK");
				usedPorts.put(delegateSocket.myPort, false);
				delegateSocket = null;
			}
		}
		state = State.CLOSED;
		return delegateSocket;
	}
	
	/**
     * Send a message from the application.
     * 
     * @param msg
     *            - the String to be sent.
     * @throws ConnectException
     *             If no connection exists.
     * @throws IOException
     *             If no ACK was received.
     * @see AbstractConnection#sendDataPacketWithRetransmit(KtnDatagram)
     * @see no.ntnu.fp.net.co.Connection#send(String)
     */
    public void send(String msg) throws ConnectException, IOException {
    	if(this.state != State.ESTABLISHED){
    		throw new IOException("No connection established");
    	}
    	int count = 0;
    	// Create new datagram to send
    	KtnDatagram datamsg = constructDataPacket(msg);
    	// Create a datagram to receive ACK
    	KtnDatagram ack = null;
    	// Send datagram, retransmit if needed. Receive ack.
    	ack = sendDataPacketWithRetransmit(datamsg);
    	lastDataPacketSent = datamsg;
    	// Check ack
    	if(ack != null && isValid(ack)){ // Check ack
    	// If it is ok, we are done
    		System.out.println("The ack was received");
        	return;
        } else{
        	send(msg);
        }
    	
    }
	
  

    /**
     * Wait for incoming data.
     * 
     * @return The received data's payload as a String.
     * @see Connection#receive()
     * @see AbstractConnection#receivePacket(boolean)
     * @see AbstractConnection#sendAck(KtnDatagram, boolean)
     */
    public String receive() throws ConnectException, IOException {
    	// Check if the connection has valid state
    	if(this.state != State.ESTABLISHED){
    		throw new IllegalStateException("The connection is not in an established state.");
    	}
    	KtnDatagram data = null;
    	// Receive packet.
    	try{
    		 data = receivePacket(false);
    	} catch(EOFException e) {
    		// FIN-packet received
    		state = State.CLOSE_WAIT;
    		throw e;
    	}
    	if(isValid(data)){
    		lastValidPacketReceived = data;
    		sendAck(data, false);
    		return data.getPayload().toString();
    	} else{
    		sendAck(lastValidPacketReceived, false);
    		return receive();
    	}
    }

    /**
     * Close the connection.
     * 
     * @see Connection#close()
     */
    public void close() throws IOException {
        //Sending close request from established state
    	System.out.println("\nCLOSING!!\n");
    	KtnDatagram response = null;
    	
    	if(state == State.ESTABLISHED) {
        	KtnDatagram finPacket = constructInternalPacket(Flag.FIN);
			try {
				int count = 0;
				do{
					System.out.println("SENDING INITIAL FIN");
					simplySendPacket(finPacket);
					response = receiveAck();
					count++;
				}while(response == null && count < 3);
				state = State.FIN_WAIT_1;
				
				//Response is null if the connection was unable to receive any ack
				if (response == null) {
					System.out.println("Did not receive ACK on FIN request, force closing...");
					forceClose();
					return;
				}
			} catch (ClException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	
    	if(state == State.FIN_WAIT_1) {
    		if (response == null) {
    			response = receiveAck();
    		}
    		if (response != null && response.getFlag() == Flag.ACK) {
    			System.out.println("ACK RECEIVED ON FIN");
    			state = State.FIN_WAIT_2;
    		}
    		else{
    			System.out.println("Did not receive ACK on FIN request, force closing...");
				forceClose();
				return;
    		}
    	}
    	
    	if (state == State.FIN_WAIT_2) {
    		response = null;
    		
    		int count = 0;
			do{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("\nWAITING FOR LAST FIN\n");
				response = receivePacket(true);
				count++;
				if (response != null) {
					if (response.getFlag() == Flag.FIN) break;
				}
			}while(count < 3);
    		
    		if (response == null) {
    			System.out.println("DIDN'T RECEIVE FINAL FIN PACKET");
    			forceClose();
    			return;
    		}
    		
    		if (response.getFlag() == Flag.FIN) {
    			KtnDatagram finReceivedAck = constructInternalPacket(Flag.ACK);
    			try {
    				System.out.println("SENDING FINAL FIN RECEIVED ACK");
					simplySendPacket(finReceivedAck);
					state = State.TIME_WAIT;
					Thread.sleep(TIMEOUT);
					state = State.CLOSED;
					usedPorts.put(myPort, false);
					return;
				} catch (ClException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	
    	
    	//This chunk does the close operation when other end initiated.
    	if(state == State.CLOSE_WAIT) {
    		KtnDatagram ackPacket = constructInternalPacket(Flag.ACK);
    		try {
				System.out.println("\nSENDING ACK RESPONSE ON FIN INIT\n");
				simplySendPacket(ackPacket);
				KtnDatagram finPacket = constructInternalPacket(Flag.FIN);
				
				int count = 0;
				do{
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("\nSENDING FIN RESPONSE ON FIN INIT\n");
					simplySendPacket(finPacket);
					response = receiveAck();
					count++;
				}while(response == null && count < 3);
				state = State.LAST_ACK;
			} catch (ClException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if (state == State.LAST_ACK) {
    		if(response == null) {
    			int count = 0;
				do{
					response = receiveAck();
					count++;
				}while(response == null && count < 3);
    		}
    		state = State.CLOSED;
    		usedPorts.put(myPort, false);
    	}
    }
    
    private void forceClose() {
    	state = State.CLOSED;
    	usedPorts.put(myPort, false);
    }
    
    /**
     * Test a packet for transmission errors. This function should only called
     * with data or ACK packets in the ESTABLISHED state.
     * 
     * @param packet
     *            Packet to test.
     * @return true if packet is free of errors, false otherwise.
     */
    protected boolean isValid(KtnDatagram packet) {
    	if (!packet.getDest_addr().equals(myAddress)) return false;
    	if (packet.getDest_port() != myPort) return false;
    	if (packet.getChecksum() != packet.calculateChecksum()) return false;
    	
    	if (packet.getFlag() == Flag.ACK) {
    		if (packet.getAck() != lastDataPacketSent.getSeq_nr()) return false;
    	}
    	else if(packet.getFlag() == Flag.NONE) {
    		if (lastValidPacketReceived == null);
    		else if (packet.getSeq_nr() != lastValidPacketReceived.getSeq_nr() + 1) return false;
    	}
    	else return false;
    	
    	return true;
    }
    
}
