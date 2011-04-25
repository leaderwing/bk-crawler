package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

public class Linkqueue {
	private static java.sql.Statement stmt = null;
	private static int row_count;
	private static java.sql.ResultSet rs;
	public static void saveDocument(String link,double weight) {
		Connection conn = ConnectDatabase.connectDb();
		try {
			stmt = conn.createStatement();
			link=link.replace("'", "");
			link=link.replace('"', '\"');
			String sql="INSERT INTO link_queue(link,weight) VALUES('"+ link+"',"+weight+")";
			//System.out.println("sql link="+sql+"\n");
			row_count = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("loi save link queue");
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
	
	public static java.sql.ResultSet getLink() {
		
		int id=0;
		Connection conn = ConnectDatabase.connectDb();
		try {
			stmt = conn.createStatement();
			String sql="SELECT * FROM link_queue ORDER BY weight desc LIMIT 1";
			rs = stmt.executeQuery(sql);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("loi get link");
			e.printStackTrace();
		} 
		return rs;
	}
	public static void closeStatement() {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException sqlEx) {
			}
			stmt = null;
		}
	}
	public static void delLink(int id) {
		Connection conn = ConnectDatabase.connectDb();
		try {
			stmt = conn.createStatement();
			String sql="DELETE FROM link_queue WHERE id="+id;
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("loi delete link");
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
		boolean is_queue=false;
		int id=0;
		Connection conn = ConnectDatabase.connectDb();
		try {
			stmt = conn.createStatement();
			link=link.replace("'", "");
			link=link.replace('"', '\"');
			String sql="SELECT id FROM link_queue WHERE link='"+link+"' LIMIT 1";
			System.out.println("sql check link="+ sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				 id=rs.getInt("id");
			}
			System.out.println("id queue ="+id+"\n");
			if(id>0) is_queue=true;
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
		return is_queue;
	}
	
}
