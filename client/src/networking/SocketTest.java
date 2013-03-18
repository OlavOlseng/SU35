package networking;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketTest implements MessageListener{
	public static int CLIENTPORT = 49153;
	public static String host = "localhost";
	
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
				if (msg.equals("emp1")) {
					System.out.println("Should work");
					msg = test.fac.makeGetMessage("employee", "sindre@epost.no");
				}
				if (msg.equals("emp2")) {
					System.out.println("Should fail");
					msg = test.fac.makeGetMessage("emplyee", "sindre@epost.no");
				}
				if (msg.equals("emp3")) {
					System.out.println("Should fail");
					msg = test.fac.makeGetMessage("employee", "sindr@epost.no");
				}
				
				if (msg.equals("app1")) {
					System.out.println("Should work");
					msg = test.fac.makeGetMessage(SBPFactory.OPTION_APPOINTMENT, "2");
				}
				if (msg.equals("app2")) {
					System.out.println("Should fail");
					msg = test.fac.makeGetMessage(SBPFactory.OPTION_APPOINTMENT, "0");
				}
				
				if (msg.equals("inv1")) {
					System.out.println("Should work");
					msg = test.fac.makeGetMessage(SBPFactory.OPTION_INVITATIONS_BY_APPOINTMENT, "2");
				}
				if (msg.equals("inv2")) {
					System.out.println("Should work");
					msg = test.fac.makeGetMessage(SBPFactory.OPTION_INVITATIONS_BY_EMPLOYEE, "kenneth@epost.no");
				}
				if (msg.equals("inv3")) {
					System.out.println("Should fail");
					msg = test.fac.makeGetMessage(SBPFactory.OPTION_INVITATIONS_BY_APPOINTMENT, "0");
				}
				if (msg.equals("inv4")) {
					System.out.println("Should fail");
					msg = test.fac.makeGetMessage(SBPFactory.OPTION_INVITATIONS_BY_EMPLOYEE, "olav@epost.no");
				}
				if (msg.equals("inv5")) {
					System.out.println("Should work");
					msg = test.fac.makeGetMessage(SBPFactory.OPTION_INVITATION, "kenneth@epost.no¤2");
				}
				if (msg.equals("room1")) {
					System.out.println("Should work");
					msg = test.fac.makeGetMessage(SBPFactory.OPTION_ROOM, null);
				}
				if (msg.equals("group1")) {
					System.out.println("Should work");
					msg = test.fac.makeGetMessage(SBPFactory.OPTION_GROUP, "nett@epost.no");
				}
				if (msg.equals("alarm1")) {
					System.out.println("Should work");
					msg = test.fac.makeGetMessage(SBPFactory.OPTION_ALARM, "kenneth@epost.no¤2");
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
