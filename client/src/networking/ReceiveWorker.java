/*This class attaches to a connection and waits for incoming messages,
 * attach listeners to listen to the messages. It will send a String = null, if a connection is closed.
 * 
 * NB!!!!
 * Invoke the start() function, NOT run(), when starting the worker. This will make it run on it's own thread.
 */

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
			String msg = null;
			try {
				msg = connection.receive();
				if(msg == null){ 
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
				break;
			} finally{
				for(MessageListener ml : listeners) {
					ml.messageReceived(msg);
				}
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