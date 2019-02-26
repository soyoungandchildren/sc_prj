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
	// scuDAO -> srDAO
	static SCURefundDAO srDAO;
	private Connection con;
	private PreparedStatement pstmt;
	private PreparedStatement pstmt2;
	private PreparedStatement pstmt3;
	private PreparedStatement pstmt4;
	private ResultSet rs;

	public SCURefundDAO() {
	}

	public static SCURefundDAO getInstace() {
		if (srDAO == null) {
			srDAO = new SCURefundDAO();
		} // end if

		return srDAO;
	}

	public List<SCUGetBookingHistoryVO> searchBookingHistory(String id) throws SQLException {
		List<SCUGetBookingHistoryVO> list = new ArrayList<>();

		con = null;
		pstmt = null;
		rs = null;

		try {
			con = SCUConnect.getInstance().getConnection();

			SCUGetBookingHistoryVO sgbhVO = null;

			StringBuilder sb = new StringBuilder();
			sb.append(
					"select b.book_number, b.payment_date,  b.personnel, t.screen_price, to_char(b.movie_start,'yyyy-mm-dd hh24:mm') movie_start ")
					.append("from  book b, theater t, on_screen os ")
					.append("where b.screen_num = os.screen_num and os.screen_name = t.screen_name and b.member_id =?");

			pstmt = con.prepareStatement(sb.toString());

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				sgbhVO = new SCUGetBookingHistoryVO(rs.getString("book_number"), rs.getString("payment_date"),
						rs.getInt("personnel"), rs.getInt("screen_price"), rs.getString("movie_start"));
				list.add(sgbhVO);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return list;
	}// searchBookingHistory

	public List<SCUGetSnackHistoryVO> searchSnackHistory(String id) throws SQLException {
		List<SCUGetSnackHistoryVO> list = new ArrayList<>();

		con = null;
		pstmt = null;
		rs = null;

		try {
			con = SCUConnect.getInstance().getConnection();

			SCUGetSnackHistoryVO sgshvo = null;

			StringBuilder sb = new StringBuilder();
			sb.append("select ss.snack_order_num, ss.quan, ss.snack_sale_date, ss.check_exchange, s.snack_price ")
					.append("from snack_sale ss, snack s ")
					.append("where ss.snack_name = s.snack_name and ss.member_id =? ");

			pstmt = con.prepareStatement(sb.toString());

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				sgshvo = new SCUGetSnackHistoryVO(rs.getString("snack_order_num"), rs.getInt("quan"),
						rs.getString("snack_sale_date"), rs.getString("check_exchange"), rs.getInt("snack_price"));
				list.add(sgshvo);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return list;

	}// searchSnackHistory

	public boolean deleteBooking(String code) throws SQLException {
		boolean flag = false;

		con = null;
		pstmt = null;// 삭제
		pstmt2 = null;
		rs = null;
		String deleteSeat = "";
		try {
			con = SCUConnect.getInstance().getConnection();
			// con.setAutoCommit(false);

			// 예매의 좌석 삭제
			if (code.substring(0, 1).equals("N")) {
				deleteSeat = "delete from standard_seat where book_number=?";
			} else if (code.substring(0, 1).equals("P")) {
				deleteSeat = "delete from premium_seat where book_number=?";
			}
			pstmt = con.prepareStatement(deleteSeat);
			pstmt.setString(1, code);
			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				flag = true;
			}
			
			// 예매 삭제 (시간이랑 비교해서 영화 상영 전에만 취소될 수 있게 해야함.)
			deleteSeat = "delete from book where book_number=?";
			
			pstmt2 = con.prepareStatement(deleteSeat);
			pstmt2.setString(1, code);
			int cnt2 = pstmt2.executeUpdate();
			if(cnt2==1) {
				flag = true;
			}
			
			//포인트 환불
			
			
			
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
			if (rs != null) {
				rs.close();
			}
		} // end finally

		return flag;
	}//
}// class
