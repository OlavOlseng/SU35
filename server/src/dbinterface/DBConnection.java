package dbinterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import networking.MessageListener;

public class DBConnection implements MessageListener{
	
	private Properties p;
	private Connection connection;
	
	public DBConnection(String user, String password) {
		p = new Properties();
		
		p.setProperty("user", user);
		p.setProperty("password", password);
		p.setProperty("jdbcDriver", "com.mysql.jdbc.Driver");
		p.setProperty("url", "jdbc:mysql://localhost/mydb");
	}
	
	public void init() throws SQLException, ClassNotFoundException {
		System.out.println("Opening connection to database...");
		
		Class.forName(p.getProperty("jdbcDriver"));
		
		Properties info = new Properties();
		info.setProperty("user", p.getProperty("user"));
		info.setProperty("password", p.getProperty("password"));
		connection = DriverManager.getConnection(p.getProperty("url"), info);
		System.out.println("Connection opened....");
	}
	
	public void close() throws SQLException {
		System.out.println("Closing connection to database...");
		connection.close();
		System.out.println("Connection closed...");
	}
	
	public ResultSet makeSingleQuery(String sql) throws SQLException {
		Statement st = connection.createStatement();
		return st.executeQuery(sql);
	}
	
	public void makeSingleUpdate(String sql) throws SQLException {
		Statement st = connection.createStatement();
		st.executeUpdate(sql);
	}

	public PreparedStatement preparedStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}

	@Override
	public void messageReceived(String msg) {
		// TODO Auto-generated method stub
		System.out.println(msg);
	}
	
}