package networking;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketTest implements MessageListener{
	public static int CLIENTPORT = 49153;
	public static String host = "78.91.15.184";
	
	private Connection conn;
	private Socket client;
	private ReceiveWorker rw;
	public SocketTest(String host,int port) throws UnknownHostException, IOException{
		client = new Socket();
		conn = new Connection(client);
		conn.connect(host, port);
		rw = new ReceiveWorker(conn, this);
		rw.start();
		
	}
	
	public static void main(String[] args) {
		try {
			SocketTest test = new SocketTest(SocketTest.host, SocketTest.CLIENTPORT);
			Scanner sc = new Scanner(System.in);
			String msg = null;
			do{
				msg = sc.nextLine();
				test.conn.send(msg);
			}while(!msg.trim().equals("q"));
			System.out.println("Closing connection to server");
			test.conn.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void messageReceived(String message) {
		System.out.println(message);
		
	}

}
