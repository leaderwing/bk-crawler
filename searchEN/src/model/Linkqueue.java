package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

public class Linkqueue {
	private static java.sql.Statement stmt = null;
	private static int row_count;
	private static java.sql.ResultSet rs;
	 private static Connection connect;
	public static void saveDocument(String link,double weight) {
		 connect = ConnectDatabase.connectDb();
		try {
			stmt = connect.createStatement();
			link=link.replace("'", "");
			link=link.replace('"', '\"');
			String sql="INSERT INTO link_queue(link,weight) VALUES('"+ link+"',"+weight+")";
			//System.out.println("sql link="+sql+"\n");
			row_count = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("loi save link queue");
			e.printStackTrace();
		} 

	}
	
	public static java.sql.ResultSet getLink() {
		
		
		 connect = ConnectDatabase.connectDb();
		try {
			stmt = connect.createStatement();
			String sql="SELECT * FROM link_queue ORDER BY weight desc LIMIT 1 ";
			rs = stmt.executeQuery(sql);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("loi get link");
			e.printStackTrace();
		} 
		return rs;
	}
	public static java.sql.ResultSet getLink(double avg_weight) {
		
		
		 connect = ConnectDatabase.connectDb();
		try {
			stmt = connect.createStatement();
			String sql="SELECT * FROM link_queue WHERE weight >= "+avg_weight+" LIMIT 1";
			System.out.println("sql 01="+sql+"\n");
			rs = stmt.executeQuery(sql);
			int count=0;
			while(rs.next()){
				 count++;
				 System.out.println("count ="+count+"\n");
				 System.out.println("link crawl abc ="+ rs.getString("link")+"\n");
			}
			if(count>0) {return rs;}
			else{
			    sql="SELECT * FROM link_queue ORDER BY id asc LIMIT 1 ";
				rs = stmt.executeQuery(sql);
				System.out.println("sql 02="+sql);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("loi get link");
			e.printStackTrace();
		} 
		return rs;
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
	public static void delLink(int id) {
		 connect = ConnectDatabase.connectDb();
		try {
			stmt = connect.createStatement();
			String sql="DELETE FROM link_queue WHERE id="+id;
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("loi delete link");
			e.printStackTrace();
		} 

	}
	public static boolean checkLink(String link) {
		boolean is_queue=false;
		int id=0;
		 connect = ConnectDatabase.connectDb();
		try {
			stmt = connect.createStatement();
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
		} 
		return is_queue;
	}
	
}
