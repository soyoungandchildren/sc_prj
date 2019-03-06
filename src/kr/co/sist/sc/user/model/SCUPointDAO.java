package kr.co.sist.sc.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SCUPointDAO {
	private static SCUPointDAO spDAO;
	
	public SCUPointDAO() {
	}
	
	public static SCUPointDAO getInstance() {
		if(spDAO == null) {
			spDAO = new SCUPointDAO();
		}//end if
		
		return spDAO;
	}//getInstance
	
	public boolean PointCharge(int sumPoint, String user) throws SQLException {
		boolean result = false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		con = SCUConnect.getInstance().getConnection();
		
		String sql = "update member set hold_point=? where member_id=?";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, sumPoint);
		pstmt.setString(2, user);
		
		result = pstmt.execute();
		
		return result;
	}//
	
}//class
