package kr.co.sist.sc.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.sist.sc.user.vo.SCUFindPWVO;
import kr.co.sist.sc.user.vo.SCULoginVO;
import kr.co.sist.sc.user.vo.SCUModifyPWVO;
import kr.co.sist.sc.user.vo.SCUSignUpVO;

public class SCULoginDAO {
	private static SCULoginDAO slDAO;

	public SCULoginDAO() {
	}// Constructor

	public static SCULoginDAO getInstance() {

		if (slDAO == null) {
			slDAO = new SCULoginDAO();
		} // end if

		return slDAO;
	}// getInstance Method

	/**
	 * 
	 * @param slvo
	 * @return
	 * @throws SQLException
	 */
	public String selectLogin(SCULoginVO slvo) throws SQLException {
		String userID = "";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.
			// 2.
			con = SCUConnect.getInstance().getConnection();

			// 3.
			String selectID = "select member_id from member where member_id = ? and password=?";
			pstmt = con.prepareStatement(selectID);
			// 4.
			pstmt.setString(1, slvo.getMember_id());
			pstmt.setString(2, slvo.getPassword());
			// 5.
			rs = pstmt.executeQuery();
			if (rs.next()) {
				userID = rs.getString("member_id");
			}

		} finally {
			// 6.
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally

		return userID;
	}// selectLogin

	/**
	 * 아이디 중복 확인
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean selectCheckDup(String id) throws SQLException {
		boolean flag = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = SCUConnect.getInstance().getConnection();

			String checkID = "select member_id from member where member_id = ?";
			pstmt = con.prepareStatement(checkID);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
			} // end if
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

		return flag;
	}// selectCheckDup

	/**
	 * 회원가입을 DB에 입력하기
	 * 
	 * @param ssuvo
	 */
	public int insertSignUp(SCUSignUpVO ssuvo) throws SQLException {
		
		int cnt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 1.
			// 2.
			con = SCUConnect.getInstance().getConnection();

			// 3.
			StringBuilder insertMember = new StringBuilder();
			insertMember.append(" insert into member (member_id, password, name, birthdate, phone, membership, hold_point, acc_point) ")
						.append(" select ?,?,?,?,?,?,?,?" )
						.append(" from dual ")
						.append(" where not exists (select * from member where phone = ?) " );
//			insertMember.append("INSERT INTO MEMBER")
//					.append("(MEMBER_ID, PASSWORD, NAME, BIRTHDATE, PHONE, MEMBERSHIP, hold_point, acc_point)").append("VALUES(?,?,?,?,?,?,?,?)");
			
			pstmt = con.prepareStatement(insertMember.toString());

			// 4. 바인드 변수에 값넣기
			pstmt.setString(1, ssuvo.getMember_id());
			pstmt.setString(2, ssuvo.getPassword());
			pstmt.setString(3, ssuvo.getName());
			pstmt.setString(4, ssuvo.getBirthdate());
			pstmt.setString(5, ssuvo.getPhone());
			pstmt.setString(6, "s");
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setString(9, ssuvo.getPhone());

			// 5.
			cnt = pstmt.executeUpdate(); // insert되거나 예외이거나 둘 중 하나

		} finally {
			// 6.
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
		return cnt;
	}

	/**
	 * 전화번호 넣어서 아이디찾기
	 * 
	 * @param phone
	 * @return
	 * @throws SQLException
	 */
	public String selectID(String phone) throws SQLException {
		String findId = "";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = SCUConnect.getInstance().getConnection();

			// 3.
			String find = "SELECT MEMBER_ID FROM MEMBER WHERE PHONE = ?";
			pstmt = con.prepareStatement(find);

			// 4. 바인드 변수에 값넣기
			pstmt.setString(1, phone);

			rs = pstmt.executeQuery();

//			System.out.println(phone);
//			pstmt.executeUpdate(); // insert되거나 예외이거나 둘 중 하나
			if (rs.next()) {
				findId = rs.getString("MEMBER_ID");
			} // end if

			// 5.

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
		return findId;
	}// selectID

	/**
	 * 비밀번호 찾기
	 * @param sfpvo
	 * @return
	 * @throws SQLException
	 */
	public boolean selectPW(SCUFindPWVO sfpvo) throws SQLException {
		boolean flag = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = SCUConnect.getInstance().getConnection();

			String selectPW = "select password from member where member_id=? and name =? and phone=?";
			pstmt = con.prepareStatement(selectPW);
			
			pstmt.setString(1, sfpvo.getMember_id());
			pstmt.setString(2, sfpvo.getName());
			pstmt.setString(3, sfpvo.getPhone());
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				flag = true;
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

		return flag;
	}// selectPW
	
	public boolean updatePW(SCUModifyPWVO smpvo) throws SQLException {
		boolean flag = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println(smpvo.getPassword());
		System.out.println(smpvo.getMember_id());

		try {
			con = SCUConnect.getInstance().getConnection();

			String updatePW = "update member set password=? where member_id=?";
			
			pstmt = con.prepareStatement(updatePW);
			
			pstmt.setString(1, smpvo.getPassword());
			pstmt.setString(2, smpvo.getMember_id());
			
			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				flag = true;
			}
		} finally {

			if (pstmt != null) {
				pstmt.close();
			}

			if (con != null) {
				con.close();
			}//end if
		}//finally

		return flag;	
	}// selectPW
	
}// class
