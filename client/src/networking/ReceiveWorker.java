package networking;

import java.io.IOException;
import java.util.ArrayList;

public class ReceiveWorker extends Thread{
	
	private ArrayList<MessageListener> listeners;
	private Connection connection;
	
	public ReceiveWorker(Connection c) {
		listeners = new ArrayList<MessageListener>();
		this.connection = c;
	}
	
	public ReceiveWorker(Connection c, MessageListener listener) {
		this(c);
		listeners.add(listener);
	}
	
	public void addListener(MessageListener ml) {
		listeners.add(ml);
	}
	
	public void removeListener(MessageListener ml) {
		listeners.remove(ml);
	}
	
	public void run() {
		while (true) {
			try {
				String msg = connection.receive();
				if(msg == null) break;
				
				for(MessageListener ml : listeners) {
					ml.messageReceived(msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
		
		try {
			connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}