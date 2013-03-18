package dbinterface;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import models.Employee;

public class OlavTest {
	
	DBConnection dbc;
	
	public static void main(String[] argv) {
		OlavTest test = new OlavTest();
		test.test();
	}
	
	public void test() {
		dbc = new DBConnection("root", "admin");
		try {
			dbc.init();
		
			//TODO run tests
			readTest();
		
			dbc.close();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readTest() {
		/*
		try {
			String sql="select * from employee";
			ResultSet rs = dbc.makeSingleQuery(sql);
			ResultSetMetaData meta = rs.getMetaData();
			
			System.out.println();
		
			rs.beforeFirst();
		
			while(rs.next())
			{
				Employee e = new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				System.out.println(e);
				System.out.println();
			}
			
			rs.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println();
		*/
	}
}

