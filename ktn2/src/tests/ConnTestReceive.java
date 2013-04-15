package tests;

import java.io.IOException;
import java.net.SocketTimeoutException;

import no.ntnu.fp.net.co.Connection;
import no.ntnu.fp.net.co.ConnectionImpl;

public class ConnTestReceive {
	
	Connection passiveConn;
	
	public ConnTestReceive() {
		passiveConn = new ConnectionImpl(40000);
	}
	
	private void run() {
		try {
			passiveConn.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ConnTestReceive ct = new ConnTestReceive();
		ct.run();
	}
}
