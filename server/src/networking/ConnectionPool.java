package networking;

import java.io.IOException;
import java.util.ArrayList;

public class ConnectionPool implements MessageListener{
	
	public ArrayList<Connection> pool;
	
	public ConnectionPool() {
		pool = new ArrayList<Connection>();
	}
	
	public void add(Connection c) {
		pool.add(c);
	}
	
	public void close(Connection c) throws IOException {
		if(!c.isClosed())
			c.close();
		pool.remove(c);
	}
	
	public void flush() throws IOException {
		for(Connection c : pool) {
			if(!c.isClosed())
				c.close();
		}
		pool.clear();
	}
	
	public void broadcast(String msg) throws IOException {
		for (Connection c : pool) {
			if (c.isClosed()) {
				close(c);
				continue;
			}
			c.send(msg);
		}
	}
	
	@Override
	public void messageReceived(String msg) {
		System.out.println(msg);
	}
}
