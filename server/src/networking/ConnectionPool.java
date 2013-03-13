package networking;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool {
	
	public ArrayList<ConnectionBridge> pool;
	private static ConnectionPool instance;
	
	private ConnectionPool() {
		pool = new ArrayList<ConnectionBridge>();
	}
	
	public synchronized void add(ConnectionBridge c) {
		pool.add(c);
	}
	
	public synchronized void remove(ConnectionBridge c) throws IOException, SQLException {
		pool.remove(c);
	}
	
	public synchronized void flush() throws IOException, SQLException {
		for(ConnectionBridge c : pool) {
			if(!c.isConnectionClosed())
				c.close();
		}
		pool.clear();
	}
	
	public synchronized void clearClosedConnections() throws IOException, SQLException {
		for (ConnectionBridge c : pool) {
			if (c.isConnectionClosed()) {
				remove(c);
			}
		}
	}
	
	public synchronized void broadcast(String msg) throws IOException, SQLException {
		for (ConnectionBridge c : pool) {
			c.send(msg);
		}
	}
	
	public synchronized static ConnectionPool getInstance() {
		if(instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}
}
