package model;

import java.sql.Connection;
import java.sql.SQLException;

public class Newkeyword {
	private static java.sql.Statement stmt = null;
	private static int rs;
	public static void saveDocument(String new_key, String link,double best_fitness) {

		Connection conn = ConnectDatabase.connectDb();
       
		try {
			stmt = conn.createStatement();
			String sql="INSERT INTO new_keyword (new_key,link,best_fitness) VALUES('"+ new_key + "','" + link+ "',"+best_fitness+")";
			
			rs = stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("loi save db");
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				}

				stmt = null;
			}
		}

	}
}
