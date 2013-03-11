package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {
	
	private Properties p;
	private Connection connection;
	
	public DBConnection() {
		p = new Properties();
		
		p.setProperty("user", "root");
		p.setProperty("password", "admin");
		p.setProperty("jdbcDriver", "com.mysql.jdbc.Driver");
		p.setProperty("url", "jdbc:mysql://localhost/mydb");
	}
	
	public void init() throws SQLException, ClassNotFoundException {
		System.out.println("Opening connection...");
		
		Class.forName(p.getProperty("jdbcDriver"));
		
		Properties info = new Properties();
		info.setProperty("user", p.getProperty("user"));
		info.setProperty("password", p.getProperty("password"));
		connection = DriverManager.getConnection(p.getProperty("url"), info);
		System.out.println("Connection opened....");
	}
	
	public void close() throws SQLException {
		System.out.println("Closing connection...");
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
	
}
