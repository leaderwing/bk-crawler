package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Bestword {
	private static java.sql.Statement stmt = null;
	private static int rs;
	private static Connection connect;
	public static void saveDocument(ArrayList<String> bestword) {

		 connect = ConnectDatabase.connectDb();
       
		try {
			String arr_bestword="";
			for(int i=0;i< bestword.size();i++) {
				arr_bestword+=bestword.get(i)+",";
			}
			stmt = connect.createStatement();
			String sql="INSERT INTO bestword (bestword) VALUES('"+ arr_bestword +"')";
			
			rs = stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("loi save db");
			e.printStackTrace();
		} 

	}
	public static void closeConnect() {
		if (stmt != null) {
			try {
				stmt.close();
				//connect.close();
			} catch (SQLException sqlEx) {
			}
			stmt = null;
		}
		if (connect != null) {
			try {
				connect.close();
				//connect.close();
			} catch (SQLException sqlEx) {
			}
			connect = null;
		}
	}
}
