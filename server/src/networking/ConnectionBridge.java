package networking;

import dbinterface.*;
import models.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbinterface.DBConnection;

public class ConnectionBridge implements MessageListener {

	private Connection inetConnection;
	private DBConnection dbConnection;
	private ReceiveWorker rw;
	private ServerMessageHandler msgHandler;

	public ConnectionBridge(Connection c, DBConnection dbc) {
		this.inetConnection = c;
		this.dbConnection = dbc;
		this.msgHandler = new ServerMessageHandler(this); 
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
		System.out.println(msg);
	}

	public boolean isConnectionClosed() {
		return inetConnection.isClosed();
	}
	
	public DBConnection getDBConnection() {
		return this.dbConnection;
	}

	@Override
	public void messageReceived(String msg) {
		//This block is needed for closing the connection properly
		if(msg == null)
			try {
				close();
				return;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		System.out.println(msg);
		msgHandler.handleMessage(msg);
	}
}