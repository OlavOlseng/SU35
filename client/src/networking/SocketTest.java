package networking;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketTest implements MessageListener{
	public static int CLIENTPORT = 49153;
	public static String host = "78.91.14.138";
	
	private Connection conn;
	private Socket client;
	private ReceiveWorker rw;
	private SBPFactory fac = new SBPFactory();
	
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
				if (msg.equals("test1")) {
					System.out.println("Querying");
					msg = test.fac.makeGetMessage("employee", "Leif");
				}
				if (msg.equals("test2")) {
					System.out.println("Querying");
					msg = test.fac.makeGetMessage("employe", "Leif");
				}
				if (msg.equals("test3")) {
					System.out.println("Querying");
					msg = test.fac.makeGetMessage("employee", "eif");
				}
				if (msg.equals("testA1")) {
					System.out.println("Querying");
					msg = test.fac.makeGetMessage(SBPFactory.OPTION_APPOINTMENT, "1");
				}
				if (msg.equals("testA2")) {
					System.out.println("Querying");
					msg = test.fac.makeGetMessage(SBPFactory.OPTION_APPOINTMENT, "2");
				}
				
				if (msg.equals("login1")) {
					System.out.println("Logging in");
					msg = test.fac.makeLoginMessage("Leif", "1");
				}
				if (msg.equals("login2")) {
					System.out.println("Logging in");
					msg = test.fac.makeLoginMessage("Leif", "nei");
				}
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
