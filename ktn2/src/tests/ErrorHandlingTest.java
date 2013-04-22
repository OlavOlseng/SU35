package tests;

public class ErrorHandlingTest {

	public static void main(String[] args) {
		Thread server = new Thread(new ConnTestReceive());
		server.start();
		Thread client = new Thread(new ConnTestSend(10));
		client.start();
	}
}
