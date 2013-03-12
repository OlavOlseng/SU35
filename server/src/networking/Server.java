package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import dbinterface.DBConnection;

public class Server implements Runnable{

	public static int SERVERPORT = 49153;

	private boolean running;
	private ServerSocket welcomeSocket;
	private ConnectionPool connectionPool;
	
	public Server() {
		connectionPool = new ConnectionPool();
		running = false;
	}

	public void init() {
		try {
			welcomeSocket = new ServerSocket(Server.SERVERPORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stop() {
		running = false;
	}

	@Override
	public void run(){
		//this method listens for incoming connections, and adds new connections to the pool
		this.running = true;
		
		while(running) {
			try {
				Socket s = welcomeSocket.accept();
				System.out.println(String.format("Connection received from %s ...", s.getInetAddress().getHostAddress()));
				Connection c = new Connection(s, connectionPool);
				connectionPool.add(c);
				
			}
			catch (IOException e) {
				e.printStackTrace();
				running = false;
				break;
			}
		}
		try {
			welcomeSocket.close();
			connectionPool.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] argv) {
		Server s = new Server();
		s.init();
		s.run();
	}
}
