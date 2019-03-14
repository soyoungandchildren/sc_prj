package kr.co.sist.sc.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SCUPointDAO {
	private static SCUPointDAO spDAO;
	private Connection con;
	private PreparedStatement pstmt1, pstmt2, pstmt3, pstmt4;
	private ResultSet rs;
	
	public SCUPointDAO() {

	}
	
	public static SCUPointDAO getInstance() {
		if(spDAO == null) {
			spDAO = new SCUPointDAO();
		}//end if
		
		return spDAO;
	}//getInstance
	
	public boolean pointCharge(int sumPoint, String user, int charge) throws SQLException {
		boolean result = false;
		int accPoint = 0;
		int updatePoint = 0;
		String grade = "";
		
		try {
			con = SCUConnect.getInstance().getConnection();
			
			//오토커밋 해제
			con.setAutoCommit(false);
			
			//회원의 포인트를 충전
			String sql1 = "update member set hold_point=? where member_id=?";
			
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, sumPoint);
			pstmt1.setString(2, user);
			
			boolean update1 = pstmt1.execute();//변수가 rs가 아니면 성공=false
			
			//포인트를 충전하려는 회원의 누적 포인트를 select
			String sql2 = "select acc_point from member where member_id=?";
			
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setString(1, user);
			rs = pstmt2.executeQuery();
			
			//조회한 누적 포인트를 변수에 저장
			if(rs.next()) {
				accPoint = rs.getInt(1);
			}//end if
			
			//조회한 누적 포인트와 충전한 금액을 합산하여 회원의 누적 포인트를 update
			updatePoint = accPoint+charge;
			String sql3 = "update member set acc_point=? where member_id=?";
			
			pstmt3 = con.prepareStatement(sql3);
			pstmt3.setInt(1, updatePoint);
			pstmt3.setString(2, user);
			
			boolean update2 = pstmt3.execute();
			
			//포인트를 충전한 후의 누적 포인트를 확인하여 회원의 등급 갱신
			if(0 <= updatePoint && updatePoint < 100000) {
				grade = "s";
			} else if(updatePoint < 200000) {
				grade = "g";
			} else {
				grade = "v";
			}//end else
			
			String sql4 = "update member set membership=? where member_id=?";
			
			pstmt4 = con.prepareStatement(sql4);
			pstmt4.setString(1, grade);
			pstmt4.setString(2, user);
			
			boolean update3 = pstmt4.execute();
			
			result = pointChargeTransaction(update1, update2, update3);
		
		} catch(SQLException se) {
			se.printStackTrace();
		}//end catch	
		
		return result;
	}//
	
	public boolean pointChargeTransaction(boolean update1, boolean update2, boolean update3) throws SQLException {
		boolean result = false;
		
		try {
			if(!update1 && !update2 && !update3) {
				con.commit();
				result = true;
			} else {
				con.rollback();
				result = false;
			}//end else
			
		} finally {
			if(rs != null) {
				rs.close();
			}//end if
			if(pstmt1 != null) {
				pstmt1.close();
			}//end if
			if(pstmt2 != null) {
				pstmt2.close();
			}//end if
			if(pstmt3 != null) {
				pstmt3.close();
			}//end if
			if(pstmt4 != null) {
				pstmt4.close();
			}//end if
			if(con != null) {
				con.close();
			}//end if
		}//end finally
		
		return result;
	}//PointChargeTransaction
	
}//class
