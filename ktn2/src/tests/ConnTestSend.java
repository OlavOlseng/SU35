package tests;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import no.ntnu.fp.net.admin.Log;
import no.ntnu.fp.net.co.ConnectionImpl;
import no.ntnu.fp.net.co.Connection;

public class ConnTestSend implements Runnable{


	Connection conn;
	int packets;
	public ConnTestSend(int packagesToSend) {
		conn = new ConnectionImpl(30000);
		this.packets = packagesToSend;
	}

	public void run() {
		Log log = new Log();
		log.setLogName("Client");

		// Connection object listening on 4001
		InetAddress addr;  // will hold address of host to connect to
		try {
			// get address of local host and connect
			addr = InetAddress.getLocalHost();
			conn.connect(addr, 40000);
			// send two messages to server
			for (int x = 0; x < packets; x++) {
			conn.send(String.format("This is packet num %d of %d! ", x + 1, packets));
			}
			Log.writeToLog("Client is now closing the connection!",
				"TestApplication");
			conn.close();
		}
		catch (ConnectException e){
			Log.writeToLog(e.getMessage(),"TestApplication");
			e.printStackTrace();
		}
		catch (UnknownHostException e){
			Log.writeToLog(e.getMessage(),"TestApplication");
			e.printStackTrace();
		}
		catch (IOException e){
			Log.writeToLog(e.getMessage(),"TestApplication");
			e.printStackTrace();
		}

		System.out.println("CLIENT TEST FINISHED");
		Log.writeToLog("CLIENT TEST FINISHED","TestApplication");
	}
}
