package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Bestword {
	private static java.sql.Statement stmt = null;
	private static int rs;
	public static void saveDocument(ArrayList<String> bestword) {

		Connection conn = ConnectDatabase.connectDb();
       
		try {
			String arr_bestword="";
			for(int i=0;i< bestword.size();i++) {
				arr_bestword+=bestword.get(i)+",";
			}
			stmt = conn.createStatement();
			String sql="INSERT INTO bestword (bestword) VALUES('"+ arr_bestword +"')";
			
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
