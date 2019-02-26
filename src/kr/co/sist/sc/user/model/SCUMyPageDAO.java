package kr.co.sist.sc.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.sist.sc.user.vo.SCURemoveAccountVO;
import kr.co.sist.sc.user.vo.SCUSearchMyPageVO;
import kr.co.sist.sc.user.vo.SCUUpdateMemberInfoVO;

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
	
	public int updateMemberInfo(SCUUpdateMemberInfoVO sumiVO) throws SQLException{
		int sqlResult = 0;
		
		con = null;
		pstmt = null;
		
		try {
			con = SCUConnect.getInstance().getConnection();
			StringBuilder sqlUpdateMemberInfo = new StringBuilder();
			
			sqlUpdateMemberInfo
			.append("update member ")
			.append("set name =?, phone=? ")
			.append("where member_id = ? ");
			
			pstmt = con.prepareStatement(sqlUpdateMemberInfo.toString());
			pstmt.setString(1, sumiVO.getName());
			pstmt.setString(2, sumiVO.getPhone());
			pstmt.setString(3, sumiVO.getMember_ID());
			
			sqlResult = pstmt.executeUpdate();
		}finally {
			disconnect();
		}
		return sqlResult;
	}
	
	public int deleteAccount(SCURemoveAccountVO sraVO) throws SQLException{
		con = null;
		pstmt = null;
		int sqlResult = 0;
		
		try {
			con = SCUConnect.getInstance().getConnection();
			StringBuilder sqlUpdateAccount = new StringBuilder();
			sqlUpdateAccount
			.append("update member ")
			.append("set password = ?, name = ?, birthdate = ?, phone = ?, membership =?, hold_point = ?, ")
			.append("acc_point = ? ")
			.append("where member_id = ? ");
			
			pstmt = con.prepareStatement(sqlUpdateAccount.toString());
			
			pstmt.setString(1, sraVO.getPassword());
			pstmt.setString(2, sraVO.getName());
			pstmt.setString(3, sraVO.getBirthdate());
			pstmt.setString(4, sraVO.getPhone());
			pstmt.setString(5, sraVO.getMembership());
			pstmt.setInt(6, sraVO.getHold_point());
			pstmt.setInt(7, sraVO.getAcc_point());
			pstmt.setString(8, sraVO.getMember_id());
			
			sqlResult = pstmt.executeUpdate();
		}finally {
			disconnect();
		}
		
		return sqlResult;
	}
	
	private void disconnect() throws SQLException{
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(con!=null) {con.close();}
	}//disconnect

}//Class
