package kr.co.sist.sc.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.sc.user.vo.SCUAddOrderSnackVO;
import kr.co.sist.sc.user.vo.SCUSearchMenuVO;
import kr.co.sist.sc.user.vo.SCUSnackOrderDataVO;

public class SCUSnackDAO {
	private static SCUSnackDAO ssDAO;
	private Connection con;
	private PreparedStatement pstmt;
	private PreparedStatement pstmt2;
	private PreparedStatement pstmt3;
	private ResultSet rs;
	
	public SCUSnackDAO() {
	}//SCUSnackDAO
	
	public static SCUSnackDAO getInstance() {
		if(ssDAO == null) {
			ssDAO = new SCUSnackDAO();
		}//end if
		
		return ssDAO;
	}//getInstance
	
	public List<SCUSearchMenuVO> selectSnackMenu() throws SQLException {
		List<SCUSearchMenuVO> list = new ArrayList<>();
		
		con = null;
		pstmt = null;
		rs = null;
		
		try {
			//����̹� �ε�, connection
			con = SCUConnect.getInstance().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select snack_img, snack_name from snack where check_delete='N'");
			
			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			SCUSearchMenuVO ssmv = null;
			while(rs.next()) {
				ssmv = new SCUSearchMenuVO(rs.getString("snack_img"), rs.getString("snack_name"));
				list.add(ssmv);
			}//end while
		
		//���� ����
		} finally {
			if(rs != null) {
				rs.close();
			}//end if
			if(pstmt != null) {
				pstmt.close();
			}//end if
			if(con != null) {
				con.close();
			}//end if
		}//end finally
		
		return list;
	}//selectSnackMenu
	
	public SCUSnackOrderDataVO selectSnackOrderData(String selectName) throws SQLException {
		SCUSnackOrderDataVO ssodVO = null;
		con = null;
		pstmt = null;
		rs = null;
		
		try {
			con = SCUConnect.getInstance().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select snack_img, snack_price, snack_info from snack ")
				.append("where snack_name=?");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, selectName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ssodVO = new SCUSnackOrderDataVO(rs.getString("snack_img"), rs.getString("snack_info"), rs.getInt("snack_price"));
			}//end if
			
		} finally {
			if(rs!=null) {
				rs.close();
			}//end if
			if(pstmt!=null) {
				pstmt.close();
			}//end if
			if(con!=null) {
				con.close();
			}//end if
		}//end finally
		
		return ssodVO;
	}//selectSnackOrderData
	
	public boolean insertOrderSnack(List<SCUAddOrderSnackVO> list, String totalOrderPrice, String member) throws SQLException {
		boolean result = false;
		
		con = null;
		pstmt = null;
		pstmt2 = null;
		pstmt3 = null;
		rs = null;
		
		int point = 0;
		int toPrice = Integer.parseInt(totalOrderPrice);
		
		try {
			con = SCUConnect.getInstance().getConnection();
			//����Ŀ�� ����
			con.setAutoCommit(false);
			
			//�����Ϸ��� ȸ���� ��������Ʈ ��ȸ
			String sql = "select hold_point from member where member_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member);
			
			rs = pstmt.executeQuery();
			
			//��ȸ�� ����Ʈ�� ������ ����
			if(rs.next()) {
				point = rs.getInt(1);
			}//end if
			
			//���� �������� ������ �����Ǹ����� insert
			StringBuilder sql2 = new StringBuilder();
			sql2.append("insert into snack_sale(snack_order_num, snack_name, member_id, quan) ")
				.append("values(snack_order_num,?,?,?) ");
				
			pstmt2 = con.prepareStatement(sql2.toString());
			
			int cnt = 0;
			for(int i=0; i<list.size(); i++) {
				pstmt2.setString(1, list.get(i).getSnack_name());
				pstmt2.setString(2, list.get(i).getMember_id());
				pstmt2.setInt(3, list.get(i).getQuan());
					
				cnt += pstmt2.executeUpdate();
			}//end for
			
			//insert �Ŀ� ���� ����Ʈ�� (��������Ʈ - ������ ���� �ֹ� �� ����)�� update
			int chgPoint = point-toPrice;
			String sql3 = "update member set hold_point=? where member_id=?";
			
			pstmt3 = con.prepareStatement(sql3);
			pstmt3.setInt(1, chgPoint);
			pstmt3.setString(2, member);
			
			boolean chg = pstmt3.execute();
//			System.out.println(chg);
			result = insertOrderSnackTransaction(point, toPrice, cnt, list.size(), chg);
			
		} catch(SQLException se) {
			se.printStackTrace();
		}//end catch
		
		return result;
	}//insertOrderSnack
	
	public boolean insertOrderSnackTransaction(int point, int toPrice, int cnt, int listSize, boolean chg) throws SQLException {
		boolean result = false;
		
		try {
			if(point >= toPrice && cnt == listSize && chg == false) {
				con.commit();
				result = true;
			} else {
				con.rollback();
				result = false;
			}//end else
			
		} finally {
			if(rs != null) {
				rs.close();
			}//end if
			if(pstmt2 != null) {
				pstmt2.close();
			}//end if
			if(pstmt != null) {
				pstmt.close();
			}//end if
			if(con != null) {
				con.close();
			}//end if
		}//end finally
		
		return result;
	}//insertOrderSnackTransaction
	
}//class
