package tests;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import no.ntnu.fp.net.co.ConnectionImpl;
import no.ntnu.fp.net.co.Connection;

public class ConnTestSend {
	
	
	Connection initConn;
	
	public ConnTestSend() {
		initConn = new ConnectionImpl(30000);
	}
	
	public void run() {
		InetAddress a = null;
		try {
			a = InetAddress.getByName("127.0.0.1");
		} catch (UnknownHostException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			initConn.connect(a, 40000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ConnTestSend ct = new ConnTestSend();
		ct.run();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
