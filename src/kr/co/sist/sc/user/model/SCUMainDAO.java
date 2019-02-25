package kr.co.sist.sc.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.sc.user.vo.SCUMainVO;

public class SCUMainDAO {
	
	private static SCUMainDAO smDAO;
	
	private SCUMainDAO() {
	}//Constructor
	
	public static SCUMainDAO getInstance() {
		if(smDAO==null) {
			smDAO=new SCUMainDAO();
		}//end if
		
		return smDAO;
	}//getInstance Method
	
	public List<SCUMainVO> searchSetImgBoard() throws SQLException{
		List<SCUMainVO> list = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = SCUConnect.getInstance().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select sum(personnel) audience, count(book_number) cnt, movie_img, movie_title ")
				.append("from ")
				.append("(select substr(book_number,2,8) book_number, personnel ")
				.append("from book) ")
				.append("left join ")
				.append("(select movie_code, movie_img, movie_title ")
				.append("from movie) ")
				.append("on movie_code=book_number " )
				.append("group by book_number, movie_img, movie_title ")
				.append("order by audience desc, movie_title asc");
			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			SCUMainVO smVO = null;
			while(rs.next()) {
				smVO = new SCUMainVO(rs.getString("movie_title"), rs.getString("movie_img"), rs.getInt("audience"), rs.getInt("cnt"));
				list.add(smVO);
			}//end while
			
		}finally {
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			if(con!=null){con.close();}
		}//end finally
		
		return list;
	}//setImgBoard Method
	
	public String SearchRankMovie() throws SQLException {
		String rowCnt = "";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		con = SCUConnect.getInstance().getConnection();
		String sql = "select count(*) from book";
		pstmt = con.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			rowCnt = rs.getString(1);
		}//end if
		
		return rowCnt;
	}//SearchRankMovie

}//Class
