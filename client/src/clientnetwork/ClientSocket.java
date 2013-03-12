package clientnetwork;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientSocket {
	private Socket cs;
	private final static int connectPort = 4000;
	private final static String host = "78.91.14.34";
	
	public static void main(String[] args){
		ClientSocket user1 = new ClientSocket();
		try {
			user1.connect();
			user1.send("hei");
		} catch (UnknownHostException e) {
			System.err.println("Could not connect to the host " +
		host+ " with port number " + connectPort );
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			user1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void connect() throws UnknownHostException, IOException{
		cs = new Socket(host, connectPort);
	}
	
	public void send(String s){
		if(cs != null){
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(cs.getOutputStream(),true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			writer.println(s);
			writer.close();
		}
	}
	
	public void close() throws IOException{
		if( cs!= null) cs.close();
	}

}
