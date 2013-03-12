package dbinterface;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.Employee;

import com.mysql.jdbc.ResultSetMetaData;

public class SindreTest {

	
	public static void main(String[] args) {
		DBConnection db = new DBConnection("root", "u2xt3f227");
		
		try {
			db.init();
			String update = "INSERT INTO employee " + "VALUES ('sindr', 'Sindre', 'Magnussen', " +
					"'41517055', '41517055', 'test1')";
			db.makeSingleUpdate(update);
			String query = "select firstname from employee";
			ResultSet rs = db.makeSingleQuery(query);
			ResultSetMetaData rsmeta = (ResultSetMetaData) rs.getMetaData();
			rs.beforeFirst();
			while(rs.next()){
				Employee e = new Employee("sindrma@gmail.com", rs.getString(1),null, null, null);
				System.out.println(e);
			}
			rs.close();
			db.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
