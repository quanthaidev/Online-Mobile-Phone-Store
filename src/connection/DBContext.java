package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
	// // Method này connect tới CSDL
		public static Connection getConnection() {
			Connection con = null;
			try {

				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingdb", "root", "12345");

			} catch (Exception e) {
				System.out.println(e);

			}
			return con;
		}

}
