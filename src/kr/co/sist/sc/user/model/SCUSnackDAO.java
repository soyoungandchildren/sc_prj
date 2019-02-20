package kr.co.sist.sc.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.sc.user.vo.SCUAddOrderSnackVO;
import kr.co.sist.sc.user.vo.SCUSearchMenuVO;
import kr.co.sist.sc.user.vo.SCUSnackOrderDataVO;

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
			//드라이버 로딩, connection
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
		
		//연결 끊기
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
	
	public SCUSnackOrderDataVO selectSnackOrderData(String selectName) throws SQLException {
		SCUSnackOrderDataVO ssodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = SCUConnect.getInstance().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select snack_img, snack_price, snack_info from snack ")
				.append("where snack_name=?");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, selectName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ssodVO = new SCUSnackOrderDataVO(rs.getString("snack_img"), rs.getString("snack_info"), rs.getInt("snack_price"));
			}//end if
			
		} finally {
			if(rs!=null) {
				rs.close();
			}//end if
			if(pstmt!=null) {
				pstmt.close();
			}//end if
			if(con!=null) {
				con.close();
			}//end if
		}//end finally
		
		return ssodVO;
	}//selectSnackOrderData
	
	public boolean insertOrderSnack(List<SCUAddOrderSnackVO> list, String totalOrderPrice) throws SQLException {
		boolean flag = false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = SCUConnect.getInstance().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert into snack_sale(snack_order_num, snack_name, member_id, quan) ")
				.append("values(snack_order_num,?,?,?) ");
			
			pstmt = con.prepareStatement(sql.toString());
			
			for(int i=0; i<list.size(); i++) {
				pstmt.setString(1, list.get(i).getSnack_name());
				pstmt.setString(2, list.get(i).getMember_id());
				pstmt.setInt(3, list.get(i).getQuan());
				
				pstmt.executeQuery();
			}//end for
			
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}//end if
			if(con != null) {
				con.close();
			}//end if
		}//end finally
		
		return flag;
	}//insertOrderSnack
	
}//class
