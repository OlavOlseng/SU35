package networking;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection {

	private Socket socket;
	private BufferedReader in;
	private DataOutputStream out;
	private ReceiveWorker receiveWorker;

	public Connection(Socket s, MessageListener ml) throws IOException {
		this.socket = s;
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.out = new DataOutputStream(socket.getOutputStream());
		this.receiveWorker = new ReceiveWorker(this, ml);
		this.receiveWorker.start();
	}
	
	
	public void connect(String host, int port) throws IOException{
		socket = new Socket(host, port);
		receiveWorker.start();
	}
	
	public void send(String msg) throws IOException {
		out.writeBytes(msg);
	}

	public void close() throws IOException {
		socket.close();
		System.out.println(String.format("Connection closed on, %s ...", socket.getInetAddress().getHostAddress()));
	}

	public String receive() throws IOException {
		String	msg = in.readLine();
		return msg;
	}
	
	public boolean isClosed() {
		return socket.isClosed();
	}
}
