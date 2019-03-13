package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import kr.co.sist.sc.user.model.SCUMovieDAO;
import kr.co.sist.sc.user.view.SCURatingView;
import kr.co.sist.sc.user.view.SCUWriteRatingView;
import kr.co.sist.sc.user.vo.SCUSearchRatingVO;

public class SCURatingController extends WindowAdapter implements ActionListener {
	
	private SCURatingView srv;
	
	public SCURatingController(SCURatingView srv) {
		this.srv = srv;
		searchRating();
	}//Constructor

	
	public void searchRating() {
		List<SCUSearchRatingVO> list = new ArrayList<>();
		
		
		try {
			list = SCUMovieDAO.getInstance().selectRatingData(srv.getSelectedMovieCode());
			
			Object[] objArr = new Object[3];
			if(list.size()==0) {
				
				objArr[0] = "";
				objArr[1] = "등록된 한줄평이 없습니다.";
				objArr[2] = "";
				srv.getDtmRatingTable().addRow(objArr);
			}else {
				
				int star = 0;
				ImageIcon imgStar = null;
				for(int i =0; i<list.size(); i++) {
					
					star = list.get(i).getMovie_rate();
					imgStar = new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/imageicon_star_"+star+"pt(22x22).png");
					objArr[0] = imgStar;
					objArr[1] = list.get(i).getReview();
					objArr[2] = list.get(i).getMember_id();
					
					
					srv.getDtmRatingTable().addRow(objArr);
				}//end for
				
			}//end if
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end try catch
		
	}//searchRating
	
	
	private boolean isLogin() {
		boolean result = false;
		
		result = srv.getSmlv().getSmv().getIsLogin();
		
		return result;
	}//isLogin Method
	
	private void canWrite() {
		List<String> listBookNumber = new ArrayList<>();
		
		String idConnecting = srv.getSmlv().getSmv().getIdConnecting();
		String selectedMovieCode = srv.getSelectedMovieCode();
		
		try {
			listBookNumber = SCUMovieDAO.getInstance().didWatch(idConnecting, selectedMovieCode);
			if(listBookNumber.size() == 0) {
				JOptionPane.showMessageDialog(srv, "영화를 관람하셔야 한줄평 쓰기가 가능합니다.");
			}else {
				String bookNumber = SCUMovieDAO.getInstance().didWrite(listBookNumber);
				if(bookNumber.equals("")) {
					JOptionPane.showMessageDialog(srv, "이미 영화에 대한 한줄평을 작성하셨습니다.");
				}else {
					new SCUWriteRatingView(srv, bookNumber);
				}//end if~else
				
			}//if~else
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//didWatch Method
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource().equals(srv.getJbtnWriteRating())) {
			
			if(isLogin()) {
				canWrite();
			}else {
				JOptionPane.showMessageDialog(srv, "로그인을 먼저 해주세요.");
			}//end if else
			
		}//end if
		
		if(ae.getSource().equals(srv.getJbtnClose())) {
			srv.dispose();
		}//end if

	}//actionPerformed Override
	
	@Override
	public void windowClosing(WindowEvent e) {
		srv.dispose();
	}//windowClosing Override
	
}//Class
