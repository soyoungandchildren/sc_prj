package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import kr.co.sist.sc.user.model.SCUMovieDAO;
import kr.co.sist.sc.user.view.SCURatingView;
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
			list = SCUMovieDAO.getInstance().selectRatingData(srv.getSmlv().getSelectedMovieCode());
			
			if(list.size()==0) {
				System.out.println("작성된 한줄 평이 없습니다.");
			}else {
				
				Object[] objArr = new Object[3];
				
				for(int i =0; i<list.size(); i++) {
					
					objArr[0] = list.get(i).getMovie_rate();
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
	
	private boolean didWatch() {
		boolean watched = false;
		
		String idConnecting = srv.getSmlv().getSmv().getIdConnecting();
		String selectedMovieCode = srv.getSmlv().getSelectedMovieCode();
		
		try {
			String msg = SCUMovieDAO.getInstance().didWatch(idConnecting, selectedMovieCode);
			if(!msg.equals("")) {
				JOptionPane.showMessageDialog(srv, msg);
				return watched;
			}else {
				watched = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return watched;
	}//didWatch Method
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource().equals(srv.getJbtnWriteRating())) {
			
			if(isLogin()) {
				if(didWatch()) {
					System.out.println("쓴다");
				}//end if
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
