package Util;

import java.sql.*;

public class JDBCTool {
	
	public static Connection getConnection(String url, String dbname, String username, String password) {
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://"+url+"/"+dbname, username, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Connection getConnection() {
		return JDBCTool.getConnection("localhost", "jsplogindemo", "root", "");
	}

	public static void closeAllConnections(Connection conn, Statement st, ResultSet rs) throws SQLException{
		if (rs != null){
			rs.close();
		}
		if (st != null){
			st.close();
		}
		if (conn != null){
			conn.close();
		}
	}

}
