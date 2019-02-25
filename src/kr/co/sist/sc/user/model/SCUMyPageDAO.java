package kr.co.sist.sc.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.sist.sc.user.vo.SCUSearchMyPageVO;

public class SCUMyPageDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static SCUMyPageDAO smpDAO;
	
	private SCUMyPageDAO() {
	}
	
	public static SCUMyPageDAO getInstance() {
		
		if(smpDAO==null) {
			smpDAO= new SCUMyPageDAO();
		}//end if
		
		return smpDAO;
	}//getInstance
	
	public SCUSearchMyPageVO selectMyPage(String idConnecting) throws SQLException{
		SCUSearchMyPageVO ssmpVO = null;
		
		con = null;
		pstmt = null;
		rs = null;
		
		try {
			con = SCUConnect.getInstance().getConnection();
			StringBuilder sqlSelectMyPage = new StringBuilder();
			sqlSelectMyPage
			.append("select member_id, name, birthdate, phone, membership, to_char(input_date, 'yyyy-mm-dd') input_date, hold_point, acc_point ")
			.append("from member ")
			.append("where member_id = ? ");
			
			pstmt = con.prepareStatement(sqlSelectMyPage.toString());
			pstmt.setString(1, idConnecting);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ssmpVO = new SCUSearchMyPageVO(rs.getString("member_id"), rs.getString("name"), 
						rs.getString("birthdate"), rs.getString("phone"), rs.getString("membership"), 
						rs.getString("input_date"), rs.getInt("hold_point"), rs.getInt("acc_point"));
			}//end if
			
			
		}finally {
			disconnect();
		}
		
		return ssmpVO;
	}
	
	private void disconnect() throws SQLException{
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(con!=null) {con.close();}
	}//disconnect

}//Class
