package kr.co.sist.sc.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.sc.user.vo.SCUSearchMenuVO;

public class SCUSnackDAO {
	private static SCUSnackDAO ssDAO;
	
	public SCUSnackDAO() {
	}//SCUSnackDAO
	
	public static SCUSnackDAO getInstance() {
		if(ssDAO == null) {
			ssDAO = new SCUSnackDAO();
		}//end if
		
		return ssDAO;
	}//getInstance
	
	public List<SCUSearchMenuVO> selectSnackMenu() throws SQLException {
		List<SCUSearchMenuVO> list = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//����̹� �ε�, connection
			con = SCUConnect.getInstance().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select snack_img, snack_name from snack where check_delete='N'");
			
			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			SCUSearchMenuVO ssmv = null;
			while(rs.next()) {
				ssmv = new SCUSearchMenuVO(rs.getString("snack_img"), rs.getString("snack_name"));
				list.add(ssmv);
			}//end while
		
		//���� ����
		} finally {
			if(rs != null) {
				rs.close();
			}//end if
			if(pstmt != null) {
				pstmt.close();
			}//end if
			if(con != null) {
				con.close();
			}//end if
		}//end finally
		
		return list;
	}//selectSnackMenu
	
}//class
