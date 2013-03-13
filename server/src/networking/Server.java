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
	
	public Server() {
		running = false;
	}

	public void init() {
		try {
			welcomeSocket = new ServerSocket(Server.SERVERPORT);
			System.out.println("Server initialised...");
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
		System.out.println("Server running...");
		while(running) {
			try {
				Socket s = welcomeSocket.accept();
				System.out.println(String.format("Connection received from %s ...", s.getInetAddress().getHostAddress()));
				DBConnection dbc = new DBConnection("root", "admin");
				dbc.init();
				Connection c = new Connection(s);
				ConnectionBridge cb = new ConnectionBridge(c, dbc);
				ConnectionPool.getInstance().add(cb);
				
			}
			catch (IOException e) {
				e.printStackTrace();
				running = false;
				break;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			welcomeSocket.close();
			ConnectionPool.getInstance().flush();
		} catch (IOException | SQLException e) {
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
