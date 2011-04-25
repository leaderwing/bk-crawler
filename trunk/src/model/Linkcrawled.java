package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

public class Linkcrawled {
	private static java.sql.Statement stmt = null;
	private static int row_count;
	private static java.sql.ResultSet rs;
	public static void saveDocument(String link) {
		Connection conn = ConnectDatabase.connectDb();
		try {
			stmt = conn.createStatement();
			String sql="INSERT INTO link_crawled(link) VALUES('"+ link+"')";
			row_count = stmt.executeUpdate(sql);
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
	
	public static boolean checkLink(String link) {
		boolean is_crawled=false;
		int id=0;
		Connection conn = ConnectDatabase.connectDb();
		try {
			stmt = conn.createStatement();
			link=link.replace("'", "");
			link=link.replace('"', '\"');
			String sql="SELECT id FROM link_crawled WHERE link='"+link+"' LIMIT 1";
			//System.out.println("sql check link="+ sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				 id=rs.getInt("id");
			}
			if(id>0) is_crawled=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("loi check link");
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
		return is_crawled;
	}
}
