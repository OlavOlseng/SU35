package networking;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTest implements MessageListener{
	public static int CLIENTPORT = 49153;
	public static String host = "78.91.15.184";
	
	private Connection conn;
	private Socket client;
	
	public SocketTest(String host,int port) throws UnknownHostException, IOException{
		client = new Socket();
		conn = new Connection(client, this);
		conn.connect(host, port);
	}
	
	public static void main(String[] args) {
		try {
			SocketTest test = new SocketTest(SocketTest.host, SocketTest.CLIENTPORT);
			test.conn.send("select * from employee");
		
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
