package kr.co.sist.sc.user.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SCUConnect {
	
	private static SCUConnect sc;
	
	private SCUConnect() {
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}//end try
		
	}//Constructor
	
	public static SCUConnect getInstance() {
		
		if(sc==null) {
			sc = new SCUConnect();
		}//end if
		
		return sc;
	}//getInstance
	
	public Connection getConnection() throws SQLException{
		
		Connection con = null;
		String url = "jdbc:oracle:thin:@211.63.89.142:1521:orcl";
		String user = "scadmin";
		String password = "sc1234";
		
		con = DriverManager.getConnection(url, user, password);
		
		con.setAutoCommit(true);
		return con;
	}//getConnection Method

}//Class
