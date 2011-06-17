package model;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;

public class VariableState extends ConnectDatabase {
	// assume that conn is an already created JDBC connection
	private static java.sql.Statement stmt = null;
	private static int count_row;
	private static ResultSet rs;
	private static Connection connect;
	
	public static void saveDocument(String keyword, int num_doc_contain_key, int num_doc_crawled,int sum_length) {

		 connect = ConnectDatabase.connectDb();
       
		try {
			stmt = connect.createStatement();
			String sql="INSERT INTO variable_state (keyword,num_doc_contain_key,num_doc_crawled,sum_length) VALUES('"+ keyword + "'," + num_doc_contain_key + "," + num_doc_crawled + ","+sum_length+")";
			System.out.println("sql="+sql);//System.exit(0);
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
			String sql="SELECT * FROM variable_state ORDER BY id asc ";
			System.out.println("sql link simple ="+sql+"\n");
			rs = stmt.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error get db");
			e.printStackTrace();
		} 
		return rs;
	}
	public static void delete() {
		
		
		 connect = ConnectDatabase.connectDb();
		try {
			stmt = connect.createStatement();
			String sql="DELETE FROM variable_state";
			System.out.println("sql="+sql);//System.exit(0);
			count_row = stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error delete table variable_state");
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
