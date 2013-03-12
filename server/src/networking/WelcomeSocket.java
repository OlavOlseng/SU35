package networking;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class WelcomeSocket {

	private ServerSocket socket;
	private boolean running;
	public WelcomeSocket() {
		try {
			this.socket = new ServerSocket(4000);
			running = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init();
	}

	public void init() {
		try {
			ServerSocket.setSocketFactory(new SocketFactory());
			this.socket.setReuseAddress(true);
			run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void run() {
		while (running) {
			try {
				Socket connectionSocket;
				connectionSocket = socket.accept();
				//Create input stream attached to socket  
				BufferedReader inFromClient=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));  
				//Create output stream attached to socket  
				DataOutputStream outToClient=new DataOutputStream(connectionSocket.getOutputStream());  
				//Read in line from socket  
				System.out.println(inFromClient.readLine());  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  

		}
	}

	public static void main(String[] argv) {
		WelcomeSocket s = new WelcomeSocket();
	}

}
