package test;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

public class OlavTest {
	
	public static void main(String[] argv) {
		DBConnection dbc = new DBConnection();
		
		try {
			dbc.init();
			
			String sql="select * from employee";
			ResultSet rs=dbc.makeSingleQuery(sql);
			ResultSetMetaData meta=rs.getMetaData();
			
			rs.beforeFirst();
			
			System.out.print("Col1:"+meta.getColumnLabel(1));
			System.out.println(" Col2:"+meta.getColumnLabel(2));
			
			while(rs.next())
			{
				String name=rs.getString(1);
				String birthYear=rs.getString(2);
				System.out.println(String.format("%s %s\n",name,birthYear));
			}
			
			rs.close();
			
			
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

