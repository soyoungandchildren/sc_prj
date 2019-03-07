package kr.co.sist.sc.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import kr.co.sist.sc.user.view.SCURefundView;
import kr.co.sist.sc.user.vo.SCUGetBookingHistoryVO;
import kr.co.sist.sc.user.vo.SCUGetSnackHistoryVO;

public class SCURefundDAO {
	// scuDAO -> srDAO
	static SCURefundDAO srDAO;
	private SCURefundView srv;
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
					"select b.book_number, m.movie_title, b.payment_date,  b.personnel, t.screen_price, to_char(b.movie_start,'yyyymmddhh24miss') movie_start ")
					.append("from book b, theater t, on_screen os, movie m ")
					.append("where b.screen_num = os.screen_num and os.screen_name = t.screen_name and os.movie_code = m.movie_code ")
					.append("and b.member_id = ? ");

			pstmt = con.prepareStatement(sb.toString());

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				sgbhVO = new SCUGetBookingHistoryVO(rs.getString("book_number"), rs.getString("movie_title"),
						rs.getString("payment_date"), rs.getString("movie_start"), rs.getInt("personnel"),
						rs.getInt("screen_price"));
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
			sb.append("select ss.snack_order_num, ss.quan, ss.snack_sale_date, ss.check_exchange, s.snack_price, ss.snack_name ")
					.append("from snack_sale ss, snack s ")
					.append("where ss.snack_name = s.snack_name and ss.member_id =? ");

			pstmt = con.prepareStatement(sb.toString());

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sgshvo = new SCUGetSnackHistoryVO(rs.getString("snack_order_num"),rs.getString("snack_sale_date")
						, rs.getString("check_exchange"),rs.getString("snack_name"), rs.getInt("quan"), rs.getInt("snack_price"));
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

	public boolean deleteBooking(String code, String id, String refundPrice, String removable) throws SQLException {
		boolean flag = false;

		con = null;
		pstmt = null;// 삭제
		pstmt2 = null;
		pstmt3 = null;
		pstmt4 = null;
		rs = null;

		String deleteSeat = "";
		int holdPoint = 0;
		int refundPoint = Integer.parseInt(refundPrice);

		try {
			con = SCUConnect.getInstance().getConnection();

			// 오토커밋 해제
			con.setAutoCommit(false);

			// 영화 예매 취소
			if ("Y".equals(removable)) {
				deleteSeat = "delete from book where book_number=?";
				pstmt = con.prepareStatement(deleteSeat);
				pstmt.setString(1, code);
				int cnt = pstmt.executeUpdate();
				if (cnt == 1) {
					flag = true;
				}
			} else {
				JOptionPane.showMessageDialog(srv, "예매취소 가능 시간이 아닙니다!");
				flag = false;
			}

			// 보유한 포인트 조회
			String selectPoint = "select hold_point from member where member_id=?";

			pstmt3 = con.prepareStatement(selectPoint);
			pstmt3.setString(1, id);

			rs = pstmt3.executeQuery();

			if (rs.next()) {
				holdPoint = rs.getInt(1);
			}

			// 포인트 환불 (update) (보유 포인트 + 환불 포인트)
			int chgPoint = holdPoint + refundPoint;
			String updatePoint = "update member set hold_point=? where member_id=?";

			pstmt4 = con.prepareStatement(updatePoint);
			pstmt4.setInt(1, chgPoint);
			pstmt4.setString(2, id);

			boolean chg = pstmt4.execute();
			flag = deleteBookingTransaction(flag, chg);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// return한 flag 값이 어떻게 사용되는지 확인!
		return flag;
	}// deleteBooking

	public boolean deleteBookingTransaction(boolean flag, boolean chg) throws SQLException {
		boolean result = false;

		try {
			if (flag == true && chg == false) { // true / false 에러, false/ false 에러, false/true
				con.commit();
				result = true;
			} else {
				con.rollback();
				result = false;
			}
		} finally {
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt4 != null) {
				pstmt4.close();
			} // end if
			if (pstmt3 != null) {
				pstmt3.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally
		return result;
	}// deleteBookingTransaction

	public boolean deleteSnack(String snackOrderNum, String id, String refundPrice, String removable) throws SQLException {
		boolean flag = false;

		// 오토커밋 해제
//		con.setAutoCommit(false);

		con = null;
		pstmt = null;
		pstmt3 = null;
		pstmt4 = null;
		rs = null;

		String deleteSanck = "";
		int holdPoint = 0;
		int refundPoint = Integer.parseInt(refundPrice);
		
		try {
			con = SCUConnect.getInstance().getConnection();
			if ("Y".equals(removable)) {
				deleteSanck = "delete from snack_sale where snack_order_num=?";
				pstmt = con.prepareStatement(deleteSanck);
				pstmt.setString(1, snackOrderNum);
				int cnt = pstmt.executeUpdate();
				if (cnt == 1) {
					flag = true;
				}
			} else {
				JOptionPane.showMessageDialog(srv, "구매취소가 불가능합니다.");
				flag = false;
			}

			// 보유한 포인트 조회
			String selectPoint = "select hold_point from member where member_id=?";

			pstmt3 = con.prepareStatement(selectPoint);
			pstmt3.setString(1, id);

			rs = pstmt3.executeQuery();

			if (rs.next()) {
				holdPoint = rs.getInt(1);
			}

			// 포인트 환불 (update) (보유 포인트 + 환불 포인트)
			int chgPoint = holdPoint + refundPoint;
			String updatePoint = "update member set hold_point=? where member_id=?";

			pstmt4 = con.prepareStatement(updatePoint);
			pstmt4.setInt(1, chgPoint);
			pstmt4.setString(2, id);

			boolean chg = pstmt4.execute();
//			flag = deleteSnackTransaction(flag, chg);

		} catch (Exception e) {
			e.printStackTrace();

		} // catch
		return flag;
	}// deleteSnack

	public boolean deleteSnackTransaction(boolean flag, boolean chg) throws SQLException{
		boolean result = false;
		
		try {
			if(flag == true && chg == false ) { 
				result = true;
			}else {
				con.rollback();
				result = false;
			}
		}finally {
			if(rs != null) {
				rs.close();
			}//end if
			if(pstmt4 != null) {
				pstmt4.close();
			}//end if
			if(pstmt3 != null) {
				pstmt3.close();
			}//end if
			if(pstmt != null) {
				pstmt.close();
			}//end if
			if(con != null) {
				con.close();
			}//end if
		}//end finally
		return result;
	}// deleteSnackTransaction

}// class