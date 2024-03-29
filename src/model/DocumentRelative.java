package model;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;

public class DocumentRelative extends ConnectDatabase {
	// assume that conn is an already created JDBC connection
	private static java.sql.Statement stmt = null;
	private static int count_row;
	private static ResultSet rs;
	private static Connection connect;
	public static void saveDocument(String link, String content, double weight) {

		 connect = ConnectDatabase.connectDb();
        content= content.replace("'", " ");
        content= content.replace("\"", " ");
		try {
			stmt = connect.createStatement();
			String sql="INSERT INTO document_relative (link,content,weight) VALUES('"+ link + "','" + content + "'," + weight + ")";
			//System.out.println("sql="+sql);//System.exit(0);
			count_row = stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("loi save document relative");
			e.printStackTrace();
		} 

	}
	public static ResultSet getContent() {
		
		
		 connect = ConnectDatabase.connectDb();
		try {
			stmt = connect.createStatement();
			String sql="SELECT * FROM document_relative ORDER BY id asc LIMIT 3000";
			rs = stmt.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error get db");
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

}
