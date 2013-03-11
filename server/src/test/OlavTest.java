package test;

import java.sql.SQLException;

public class OlavTest {
	
	public static void main(String[] argv) {
		DBConnection dbc = new DBConnection();
		
		try {
			dbc.init();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			dbc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

