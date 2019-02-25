package kr.co.sist.sc.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.sc.user.vo.SCUGetBookingHistoryVO;
import kr.co.sist.sc.user.vo.SCUGetSnackHistoryVO;

public class SCURefundDAO {
	//scuDAO -> srDAO
	static SCURefundDAO srDAO;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
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

		con = null;
		pstmt = null;
		rs = null;
		
		try {
			con=SCUConnect.getInstance().getConnection();

			SCUGetBookingHistoryVO sgbhVO = null;
			
			StringBuilder sb = new StringBuilder();
					sb.append("select b.book_number, b.payment_date,  b.personnel, t.screen_price, to_char(b.movie_start,'yyyy-mm-dd hh24:mm') movie_start ")
					.append("from  book b, theater t, on_screen os ")
					.append("where b.screen_num = os.screen_num and os.screen_name = t.screen_name");
			
			pstmt = con.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			
			
			while(rs.next()) {
				sgbhVO = new SCUGetBookingHistoryVO(rs.getString("book_number"), rs.getString("payment_date"), rs.getInt("personnel"), rs.getInt("screen_price"),rs.getString("movie_start"));
				list.add(sgbhVO);
			}
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
	}//searchBookingHistory
	
	public List<SCUGetSnackHistoryVO> searchSnackHistory() throws SQLException{
		List<SCUGetSnackHistoryVO> list = new ArrayList<>();
		
		con = null;
		pstmt = null;
		rs = null;
		
		try {
			con=SCUConnect.getInstance().getConnection();

			SCUGetSnackHistoryVO sgshvo = null;
			
			StringBuilder sb = new StringBuilder();
			sb.append("select ss.snack_order_num, ss.quan, ss.snack_sale_date, ss.check_exchange, s.snack_price ")
				.append("from snack_sale ss, snack s ")
				.append("where ss.snack_name = s.snack_name ");

			
			
			pstmt = con.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			
			
			while(rs.next()) {
				sgshvo = new SCUGetSnackHistoryVO(rs.getString("snack_order_num"), rs.getInt("quan"), rs.getString("snack_sale_date"), rs.getString("check_exchange"),  rs.getInt("snack_price"));
				list.add(sgshvo);
			}
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
		
	}//searchSnackHistory
	
	public boolean deleteBooking(String code) throws SQLException{
		boolean flag = false;
		con = null;
		pstmt = null;
		
		try {
			con=SCUConnect.getInstance().getConnection();
			
			String 
			
		}finally {
			if(pstmt != null) {pstmt.close();}
			if(con != null) {con.close();}
		}
		
		
		
		return flag;
	}//
}//class
