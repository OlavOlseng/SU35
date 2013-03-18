package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Connection {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public Connection(Socket s) throws IOException {
		this.socket = s;
		if(socket.isBound()) {
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.out = new PrintWriter(socket.getOutputStream(),true);
		}
	}

	public InetAddress getRemoteAddress() {
		return socket.getInetAddress();
	}

	public void connect(String host, int port) throws IOException{
		socket = new Socket(host, port);
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.out = new PrintWriter(socket.getOutputStream(),true);
	}

	public void send(String msg) throws IOException {
		if(socket.isBound())
			out.print(msg + "�");
	}

	public void close() throws IOException {
		socket.close();
		System.out.println(String.format("Connection closed on, %s ...", socket.getInetAddress().getHostAddress()));
	}

	public String receive() throws IOException {
		String	msg = ""; 
		char c = 0;
		do{
			if (in.ready()){
				c = (char)in.read();
				msg += c;
			}
		}while(c != '�');
		return msg;
	}

	public boolean isClosed() {
		return socket.isClosed();
	}
}
