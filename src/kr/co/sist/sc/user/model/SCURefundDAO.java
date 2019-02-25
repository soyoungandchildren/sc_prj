package kr.co.sist.sc.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.sc.user.vo.SCUGetBookingHistoryVO;

public class SCURefundDAO {
	//scuDAO -> srDAO
	static SCURefundDAO srDAO;

	public SCURefundDAO() {
	}
	
	public static SCURefundDAO getInstace() {
		if (srDAO == null) {
			srDAO = new SCURefundDAO();
		} // end if
		
		return srDAO;
	}
	
	public List<SCUGetBookingHistoryVO> searchBookingHistory() throws SQLException{
		List<SCUGetBookingHistoryVO> list = new ArrayList<>();

		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=SCUConnect.getInstance().getConnection();

			SCUGetBookingHistoryVO sgbhVO = null;
			
			String bh = "select book_number, personnel, payment_date, to_char(movie_start,'yyyy-mm-dd hh24:mm') from book";
			
			pstmt = con.prepareStatement(bh);
			pstmt.setString(1, sgbhVO.getBook_number());
			pstmt.setInt(2, sgbhVO.getPersonnel());
			pstmt.setString(3, sgbhVO.getPayment_date());
			pstmt.setString(4, sgbhVO.getMovie_start());
			pstmt.setString(5, sgbhVO.getClass());
			
					
		}finally{
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		}
		
		return list;
	}//class
	
	
}
