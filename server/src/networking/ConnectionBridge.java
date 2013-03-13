package networking;

import java.io.IOException;
import java.sql.SQLException;

import dbinterface.DBConnection;

public class ConnectionBridge implements MessageListener {

	private Connection inetConnection;
	private DBConnection dbConnection;
	private ReceiveWorker rw;

	public ConnectionBridge(Connection c, DBConnection dbc) {
		this.inetConnection = c;
		this.dbConnection = dbc;
		this.rw = new ReceiveWorker(c);
		rw.addListener(this);
		rw.start();
	}

	public void close() throws IOException, SQLException {
		System.out.println("Closing connectionbridge...");
		inetConnection.close();
		dbConnection.close();
		ConnectionPool.getInstance().remove(this);
	}

	public void send(String msg) throws IOException {
		inetConnection.send(msg);
	}

	public boolean isConnectionClosed() {
		return inetConnection.isClosed();
	}

	@Override
	public void messageReceived(String msg) {
		// TODO Auto-generated method stub
		System.out.println(msg);
		try {
			ConnectionPool.getInstance().broadcast(String.format("Message received from %s: %s", inetConnection.getRemoteAddress().getHostAddress(), msg));
			if(msg == null) 
				close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
