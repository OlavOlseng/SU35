package networking;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientSocket {
	private Socket cs;
	private PrintWriter writer;
	private final static int CONNECTPORT = 49153;
	private final static String host = "192.168.137.1";
	
	public ClientSocket(){}
	
	public ClientSocket(String host, int socketnum)throws UnknownHostException, IOException{
		// connect to host
		
		cs = new Socket(host, socketnum);
		
		// add writer (and reader) to client to enable writing 
		//(and reading) to/from host.
		
		writer = null;
		try {
			writer = new PrintWriter(cs.getOutputStream(),true);
		} catch (IOException e) {
			System.err.println("An IOException occured during making writer");
		}
	}
	
	public static void main(String[] args){
		boolean connected = false;
		ClientSocket user = null;
		// establish connection
		try {
			user = new ClientSocket(ClientSocket.host, ClientSocket.CONNECTPORT);
			connected = true;
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// sending stuff
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		while(connected){
			try {
				String message = r.readLine();
				if(message.equals("end")){
					break;
				}
				user.send(message);
			} catch (IOException e) {
				System.err.println("An IOException occured during sending or reading input");
			}
			
		}
		
		// closing connection
		try {
			user.close();
		} catch (IOException e) {
			System.err.println("An IOException occured during closing socket");
		}
		
	}
	
	// not needed, but can be used if standard constructor is used
	public void connect(String host, int port) throws UnknownHostException, IOException{
		cs = new Socket(host, port);
	}
	
	// sends info over the socket
	public void send(String s){
		writer.println(s);
	}
	
	// close the connection
	public void close() throws IOException{
		if( cs!= null) cs.close();
	}

}
