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

	public static void saveDocument(String link, String content, double weight) {

		Connection conn = ConnectDatabase.connectDb();
        content= content.replace("'", " ");
        content= content.replace("\"", " ");
		try {
			stmt = conn.createStatement();
			String sql="INSERT INTO document_relative (link,content,weight) VALUES('"+ link + "','" + content + "'," + weight + ")";
			//System.out.println("sql="+sql);//System.exit(0);
			count_row = stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("loi save document relative");
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
	public static ResultSet getContent() {
		
		
		Connection conn = ConnectDatabase.connectDb();
		try {
			stmt = conn.createStatement();
			String sql="SELECT * FROM document_relative ORDER BY id asc LIMIT 3000";
			rs = stmt.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error get db");
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

}
