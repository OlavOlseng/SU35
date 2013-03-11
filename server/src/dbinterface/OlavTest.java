package dbinterface;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import models.Employee;

public class OlavTest {
	
	public static void main(String[] argv) {
		DBConnection dbc = new DBConnection();
		
		try {
			dbc.init();
			
			String sql="select * from employee";
			ResultSet rs=dbc.makeSingleQuery(sql);
			ResultSetMetaData meta=rs.getMetaData();
			
			
			int cols = meta.getColumnCount();
			
			for (int i = 1; i <= cols; i++) {
				System.out.print("Col:" +i + " " +meta.getColumnLabel(i) + "\t");
			}
			System.out.println();
			System.out.println();
			rs.beforeFirst();
			
			while(rs.next())
			{
				Employee e = new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				System.out.println(e);
				System.out.println();
			}
			
			rs.close();
			
			System.out.println();
			
			dbc.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}

