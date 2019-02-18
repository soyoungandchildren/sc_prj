package kr.co.sist.sc.user.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.sc.user.vo.SCUInsertSeatVO;
import kr.co.sist.sc.user.vo.SCUMovieDetailVO;
import kr.co.sist.sc.user.vo.SCUMovieListVO;
import kr.co.sist.sc.user.vo.SCUSearchScreenVO;

public class SCUMovieDAO {

	private static SCUMovieDAO smDAO;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private CallableStatement cstmt;
	
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
			.append("select screen_num, count(book_number) reserved ")
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
			if(screenName.equals("일반")) {
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
				
//				select seat_num
//				from
//				(
//				select b.screen_num, book_number
//				from book b
//				left join on_screen o
//				on o.screen_num = b.screen_num
//				) sq
//				right join standard_seat s
//				on s.book_number = sq.book_number
//				where screen_num = 'N_1901291650';
				
			}else if(screenName.equals("프리미엄")){
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
	
	
	public boolean insertBooking(List<SCUInsertSeatVO> listSeat) throws SQLException{
		boolean result = false;
		
		con = null;
		cstmt = null;
		
		try {
			
			con = SCUConnect.getInstance().getConnection();
			
		}finally {
			disconnect();
		}//end try~finally
		
		
		return result;
	}//end insertBooking
	
	
	private void disconnect() {
		try {
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			if(cstmt!=null) {cstmt.close();}
			if(con!=null) {con.close();}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}//end catch
	}//disconnect
	
	
	
}//Class
