package dbinterface;

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
		System.out.println("Connection to database opened....");
	}

	public void close() throws SQLException {
		System.out.println("Closing connection to database...");
		connection.close();
	}

	public ResultSet makeSingleQuery(String sql) throws SQLException {
		Statement st = connection.createStatement();
		return st.executeQuery(sql);
	}

	public int makeSingleUpdate(String sql) throws SQLException {
		Statement st = connection.createStatement();
		st.executeUpdate(sql);
		int i = st.getUpdateCount();
		return i;
	}

	public PreparedStatement preparedStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}
}
