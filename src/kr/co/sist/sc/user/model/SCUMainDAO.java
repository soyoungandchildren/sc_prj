package kr.co.sist.sc.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.sc.user.vo.SCUMainVO;

public class SCUMainDAO {
	
	private static SCUMainDAO smDAO;
	
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
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = SCUConnect.getInstance().getConnection();
			
			String sql = "";
			pstmt = con.prepareStatement(sql);
			
			
		}finally {
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			if(con!=null){con.close();}
		}//end finally
		
		return list;
	}//setMain Method

}//Class
