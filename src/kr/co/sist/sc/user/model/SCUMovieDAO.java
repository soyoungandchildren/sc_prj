package kr.co.sist.sc.user.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.sc.user.vo.SCUInsertBookingVO;
import kr.co.sist.sc.user.vo.SCUInsertRatingVO;
import kr.co.sist.sc.user.vo.SCUInsertSeatVO;
import kr.co.sist.sc.user.vo.SCUMovieDetailVO;
import kr.co.sist.sc.user.vo.SCUMovieListVO;
import kr.co.sist.sc.user.vo.SCUSearchRatingVO;
import kr.co.sist.sc.user.vo.SCUSearchScreenVO;
import oracle.jdbc.OracleTypes;


public class SCUMovieDAO {

	private static SCUMovieDAO smDAO;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private CallableStatement cstmt1, cstmt2;
	
	private SCUMovieDAO(){
	}//Constructor
	
	public static SCUMovieDAO getInstance(){
		if(smDAO==null) {
			smDAO = new SCUMovieDAO();
		}
		return smDAO;
	}//getInstance Method
	
	
	public List<SCUMovieListVO> searchMovieList()  throws SQLException{
		List<SCUMovieListVO> list = new ArrayList<>();
		con = null;
		pstmt = null;
		rs = null;
		
		try {
			con = SCUConnect.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql
			.append("select movie_img, movie_title, movie_grade, audience, nvl(rating_avg, 0) rating_avg, genre, ")
			.append("		runningtime, playdate, actor, movie_code ")
			.append("from ")
			.append("( ")
			.append("select movie_img, movie_title, movie_grade, genre, runningtime, playdate, actor, movie_code, ")
			.append("		nvl(sum(personnel), 0) audience ")
			.append("from movie m ")
			.append("left join book ")
			.append("on substr(book_number,2,8)= movie_code ")
			.append("group by movie_img, movie_title, movie_grade, genre, runningtime, playdate, actor, movie_code ")
			.append("order by audience desc, movie_title ")
			.append(") ")
			.append("left join ")
			.append("( ")
			.append("select substr(book_number,2,8) r_movie_code, trunc(avg(movie_rate),1) rating_avg ")
			.append("from rating r ")
			.append("group by substr(book_number,2,8) ")
			.append(") ")
			.append("on r_movie_code = movie_code ")
			.append("order by audience desc, movie_title ");
			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			SCUMovieListVO smlVO = null;
			while(rs.next()) {
				smlVO = new SCUMovieListVO(rs.getString("movie_img"), rs.getString("movie_title"), rs.getString("movie_grade"), 
						rs.getString("genre"), rs.getString("runningtime"), rs.getString("playdate"), 
						rs.getString("actor"), rs.getString("movie_code"), rs.getDouble("rating_avg"), rs.getInt("audience"));
				
				list.add(smlVO);
			}//end while
			
		}finally{
			disconnect();
		}//end try~finally
		
		return list;
	}//searchMovieList Method
	
	public SCUMovieDetailVO selectMovieDetails(String movieCode) throws SQLException{
		
		SCUMovieDetailVO smdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = SCUConnect.getInstance().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql
			.append("select movie_title, movie_img, genre, country, director, movie_grade, playdate, ")
			.append("synopsis, actor, runningtime ")
			.append("from movie ")
			.append("where movie_code = ?");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, movieCode);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				smdVO = new SCUMovieDetailVO(rs.getString("movie_title"), rs.getString("movie_img"), rs.getString("genre"),
						rs.getString("country"), rs.getString("director"), rs.getString("movie_grade"), 
						rs.getString("playdate"), rs.getString("synopsis"), rs.getString("actor"), rs.getInt("runningtime"));
			}//end if
			
		}finally {
			disconnect();
		}//end try~finally
		
		return smdVO; 
	}//selectMovieDetails Method
	
	
	public List<SCUSearchScreenVO> selectScreen(String movie_code) throws SQLException{
		List<SCUSearchScreenVO> list = new ArrayList<>();
		con = null;
		pstmt = null;
		rs = null;
		
		try {
			con = SCUConnect.getInstance().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql
			.append("select screen_date, start_time, end_time, screen_name, seat_count-nvl(reserved,0) remain_seat, a.screen_num ")
			.append("from ")
			.append("( ")
			.append("select o.screen_num, screen_date, start_time, end_time , seat_count, o.screen_name ")
			.append("from theater t ")
			.append("left join on_screen o ")
			.append("on t.screen_name = o.screen_name ")
			.append("where movie_code = ? ")
			.append(") a ")
			.append("left join ( ")
			.append("select screen_num, sum(personnel) reserved ")
			.append("from book ")
			.append("group by screen_num) b ")
			.append("on a.screen_num = b.screen_num ")
			.append("order by screen_date, start_time ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, movie_code);
			
			rs = pstmt.executeQuery();
			
			SCUSearchScreenVO sssVO = null;
			while(rs.next()) {
				sssVO = new SCUSearchScreenVO(rs.getString("screen_date"), rs.getString("start_time"),
						rs.getString("end_time"), rs.getString("screen_name"), rs.getString("remain_seat"), rs.getString("screen_num"));
				
				list.add(sssVO);
			}//end while
			
		}finally {
			disconnect();
		}
		
		
		return list;
	}//selectScreen Method
	
	
	public List<Integer> selectReservedSeat(String screenName, String screenNum) throws SQLException{
		List<Integer> list = new ArrayList<>();
		con = null;
		pstmt = null;
		rs =null;
		
		try {
			con = SCUConnect.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			if(screenName.equals("老馆")) {
				sql
				.append("select seat_num ")
				.append("from ")
				.append("( ")
				.append("select b.screen_num, book_number ")
				.append("from book b ")
				.append("left join on_screen o ")
				.append("on o.screen_num = b.screen_num ")
				.append(") sq ")
				.append("right join standard_seat s ")
				.append("on s.book_number = sq.book_number ")
				.append("where screen_num = ? ");
				
			}else if(screenName.equals("橇府固决")){
				sql
				.append("select seat_num ")
				.append("from ")
				.append("( ")
				.append("select b.screen_num, book_number ")
				.append("from book b ")
				.append("left join on_screen o ")
				.append("on o.screen_num = b.screen_num ")
				.append(") sq ")
				.append("right join premium_seat p ")
				.append("on p.book_number = sq.book_number ")
				.append("where screen_num = ? ");
				
			}//end if~else
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, screenNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new Integer(rs.getInt("seat_num")));
			}//end while
			
		}finally {
			disconnect();
		}//end finally
		
		return list;
	}//selectReservedSeat
	
	
	public boolean insertBooking(SCUInsertBookingVO sibVO, List<SCUInsertSeatVO> listSisVO, String screenName){
		boolean transactionResult = false;
		
		con = null;
		cstmt1 = null;
		cstmt2 = null; 
		
		try {
			
			con = SCUConnect.getInstance().getConnection();
			con.setAutoCommit(false);
			cstmt1 = con.prepareCall("{ call procedure_insert_booking(?,?,?,?,?,?) }");
			
			cstmt1.setInt(1, sibVO.getPersonnel());
			cstmt1.setString(2, sibVO.getBook_number());
			cstmt1.setString(3, sibVO.getMovie_start());
			cstmt1.setString(4, sibVO.getMember_id());
			cstmt1.setString(5, sibVO.getScreen_num());
			
			cstmt1.registerOutParameter(6, OracleTypes.NUMERIC);
			cstmt1.execute();
			int cntBooking = cstmt1.getInt(6);
			
			
			String screen_name = "";
			if(screenName.equals("老馆")) {
				screen_name = "N";
			}else if(screenName.equals("橇府固决")) {
				screen_name = "P";
			}//end if
			
			int cntSeat = 0;
			cstmt2 = con.prepareCall("{ call procedure_insert_seat(?,?,?,?) }"); 
			for(int i = 0; i<listSisVO.size();i++) {
				cstmt2.setString(1, listSisVO.get(i).getBook_number());
				cstmt2.setInt(2, listSisVO.get(i).getSeat_num());
				cstmt2.setString(3, screen_name);
				
				cstmt2.registerOutParameter(4, OracleTypes.NUMERIC);
				cstmt2.execute();
				cntSeat += cstmt2.getInt(4);
			}//end for
			
			transactionResult = insertBookingTransaction(cntBooking, cntSeat, listSisVO.size());
			
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}//end try~catch
		
		
		return transactionResult;
	}//end insertBooking
	
	
	public boolean insertBookingTransaction(int cntBooking, int cntSeat, int listSize) throws SQLException{
		boolean transactionResult = false;
		
		try {
			if(cntBooking==1&&cntSeat==listSize) {
				con.commit();
				transactionResult = true;
			}else {
				con.rollback();
				transactionResult = false;
			}//end if~else
		}finally {
			disconnect();
		}//end try~finally
		
		return transactionResult;
	}//insertBookingTransaction
	
	
	public List<String> didWatch(String idConnecting, String selectedMovieCode) throws SQLException{
		List<String> listBookNumber = new ArrayList<>();
		
		con = null;
		pstmt = null;
		rs = null;
		
		try {
			
			StringBuilder sql = new StringBuilder();
			sql
			.append("select book_number ")
			.append("from ")
			.append("( ")
			.append("select screen_num, book_number ")
			.append("from book ")
			.append("where member_id=? ")
			.append(") a ")
			.append("inner join ")
			.append("( ")
			.append("select screen_num ")
			.append("from on_screen ")
			.append("where movie_code = ? ")
			.append(") b ")
			.append("on a.screen_num = b.screen_num ");
			
			con = SCUConnect.getInstance().getConnection();
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, idConnecting);
			pstmt.setString(2, selectedMovieCode);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				listBookNumber.add(rs.getString("book_number"));
			}//end while
			
			
		}finally {
			disconnect();
		}//end try finally
		
		return listBookNumber;
	}//didWatch Method
	
	
	
	public String didWrite(List<String> listBookNumber) throws SQLException{
		String bookNumber ="";
		con = null;
		pstmt = null;
		rs = null;
		
		try {
			con = SCUConnect.getInstance().getConnection();
			StringBuilder sbSql = new StringBuilder();
			sbSql
			.append("select book_number ")
			.append("from rating ")
			.append("where book_number = ? ");
			pstmt = con.prepareStatement(sbSql.toString());
			
			int i = 0;
			
			while(i < listBookNumber.size()) {
				pstmt.setString(1, listBookNumber.get(i));
				bookNumber = listBookNumber.get(0);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					return bookNumber ="";
				}
					
				
				i++;
			}//end while
			
		}finally {
			disconnect();
		}
		
		
		return bookNumber;
	}
	
	
	public List<SCUSearchRatingVO> selectRatingData(String selectedMovieCode) throws SQLException{
		List<SCUSearchRatingVO> list = new ArrayList<>();
		
		con = null;
		pstmt = null;
		rs = null;
		
		try {
			con = SCUConnect.getInstance().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql
			.append("select movie_rate, review, member_id ")
			.append("from rating ")
			.append("where substr(book_number, 2, 8) = ? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, selectedMovieCode);
			
			rs = pstmt.executeQuery();
			
			SCUSearchRatingVO ssrVO = null;
			while(rs.next()) {
				ssrVO = new SCUSearchRatingVO(rs.getInt("movie_rate"), rs.getString("review"), rs.getString("member_id"));
				list.add(ssrVO);
			}//end while
			
		}finally {
			disconnect();
		}
		
		return list;
	}//selectRatingData
	
	
	public int insertRating(SCUInsertRatingVO sirVO) throws SQLException{
		int cnt = 0;
		
		con = null;
		pstmt = null;
		try {
			con = SCUConnect.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql
			.append("insert into rating(book_number, movie_rate, review, member_id) ")
			.append("values(?,?,?,?) ");
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, sirVO.getBook_number());
			pstmt.setInt(2, sirVO.getMovie_rate());
			pstmt.setString(3, sirVO.getReview());
			pstmt.setString(4, sirVO.getMember_id());
			
			cnt = pstmt.executeUpdate();
			
		}finally {
			disconnect();
		}
		return cnt;
	}
	
	
	private void disconnect() {
		try {
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			if(cstmt1!=null) {cstmt1.close();}
			if(cstmt2!=null) {cstmt2.close();}
			if(con!=null) {con.close();}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}//end catch
	}//disconnect
	
	
	
}//Class
