package tests;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketTimeoutException;

import no.ntnu.fp.net.admin.Log;
import no.ntnu.fp.net.co.Connection;
import no.ntnu.fp.net.co.ConnectionImpl;

public class ConnTestReceive implements Runnable{

	Connection server;

	public ConnTestReceive() {
		server = new ConnectionImpl(40000);
	}

	public void run() {
		Log log = new Log();
		log.setLogName("Server");

		// server connection instance, listen on port 5555
		// each new connection lives in its own instance
		Connection conn;
		try {
			conn = server.accept();

			try {
				while (true) {
					String msg = conn.receive();
					Log.writeToLog("Message got through to server: " + msg,
							"TestServer");
				}
			} catch (EOFException e){
				Log.writeToLog("Got close request (EOFException), closing.",
						"TestServer");
				conn.close();
			}

			System.out.println("SERVER TEST FINISHED");
			Log.writeToLog("TEST SERVER FINISHED","TestServer");
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}
