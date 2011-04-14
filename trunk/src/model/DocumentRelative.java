package model;

import java.sql.Connection;

import java.sql.SQLException;

public class DocumentRelative extends ConnectDatabase {
	// assume that conn is an already created JDBC connection
	private static java.sql.Statement stmt = null;
	private static int rs;

	public static void saveDocument(String link, String content, double weight) {

		Connection conn = ConnectDatabase.connectDb();
        content= content.replace("'", " ");
        content= content.replace("\"", " ");
		try {
			stmt = conn.createStatement();
			String sql="INSERT INTO document_relative (link,content,weight) VALUES('"+ link + "','" + content + "'," + weight + ")";
			//System.out.println("sql="+sql);
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
