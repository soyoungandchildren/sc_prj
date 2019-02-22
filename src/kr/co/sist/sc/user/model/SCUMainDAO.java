package kr.co.sist.sc.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.sc.user.vo.SCULoginVO;
import kr.co.sist.sc.user.vo.SCUMainVO;

public class SCUMainDAO {
	
	private static SCUMainDAO smDAO;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private SCUMainDAO() {
	}//Constructor
	
	public static SCUMainDAO getInstance() {
		
		if(smDAO==null) {
			smDAO=new SCUMainDAO();
		}//end if
		
		return smDAO;
	}//getInstance Method
	
	public List<SCUMainVO> setMain() throws SQLException{
		List<SCUMainVO> list = new ArrayList<>();
		
		con = null;
		pstmt = null;
		rs = null;
		
		try {
			con = SCUConnect.getInstance().getConnection();
			
			String sql = "";
			pstmt = con.prepareStatement(sql);
			
			
		}finally {
			disconnect();
		}//end finally
		
		return list;
	}//setMain Method
	
	public boolean checkPassword(SCULoginVO slVO) throws SQLException{
		boolean flag = false;
		
		con = null;
		pstmt =null;
		rs = null;
		
		try {
			String idConnecting = slVO.getMember_id();
			String inputPassword = slVO.getPassword();
			con = SCUConnect.getInstance().getConnection();
			StringBuilder sqlSelectPassword = new StringBuilder();
			sqlSelectPassword
			.append("select password ")
			.append("from member ")
			.append("where member_id = ? ");
			
			pstmt = con.prepareStatement(sqlSelectPassword.toString());
			pstmt.setString(1, idConnecting);
			System.out.println("여기 오긴하냐");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(inputPassword);
				System.out.println(rs.getString("password"));
				
				if(rs.getString("password").equals(inputPassword)) {
					flag = true;
				}//end if
				
			}//end if
			
		}finally {
			disconnect();
		}
		
		return flag;
	}//checkPassword
	
	private void disconnect() throws SQLException{
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(con!=null) {con.close();}
	}
	

}//Class
