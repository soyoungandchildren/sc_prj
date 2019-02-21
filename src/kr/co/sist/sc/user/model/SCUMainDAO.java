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
	
	public List<SCUMainVO> setMain() throws SQLException{
		List<SCUMainVO> list = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = SCUConnect.getInstance().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select book_number, sum(personnel) audience, movie_img, movie_title ")
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
				smVO = new SCUMainVO(rs.getString("movie_title"), rs.getString("movie_img"), rs.getInt("audience"));
				list.add(smVO);
			}//end while
			
		}finally {
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			if(con!=null){con.close();}
		}//end finally
		
		return list;
	}//setMain Method

}//Class
