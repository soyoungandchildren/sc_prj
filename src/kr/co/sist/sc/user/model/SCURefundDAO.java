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
					sb.append("select b.book_number, b.personnel, b.payment_date, to_char(b.movie_start,'yyyy-mm-dd hh24:mm') movie_start, t.screen_price*b.personnel total_price, to_char(sysdate,'yyyy-mm-dd hh24:mm') today")
					.append("from   book b, theater t, on_screen os")
					.append("where b.screen_num = os.screen_num and os.screen_name = t.screen_name");
			
			pstmt = con.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			
			SCUGetBookingHistoryVO sbhvo = null;
			
			while(rs.next()) {
				sbhvo = new SCUGetBookingHistoryVO(rs.getString("book_number"), rs.getString("payment_date"), rs.getString("movie_start"), rs.getInt("personnel"));
				list.add(sbhvo);
			}
			System.out.println(rs.getString("book_number"));
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
