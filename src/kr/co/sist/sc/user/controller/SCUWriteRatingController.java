package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.sc.user.model.SCUMovieDAO;
import kr.co.sist.sc.user.view.SCUWriteRatingView;
import kr.co.sist.sc.user.vo.SCUInsertRatingVO;

public class SCUWriteRatingController extends WindowAdapter implements ActionListener {
	
	private SCUWriteRatingView swrv;
	
	public SCUWriteRatingController(SCUWriteRatingView swrv) {
		this.swrv = swrv;
		
	}//Constructor

	
	public void insertRating(String review) {
		String book_number = swrv.getBookNumber();
		int movie_rate = Integer.parseInt(swrv.getJcbRating().getSelectedItem().toString());
		String member_id = swrv.getSrv().getSmlv().getSmv().getIdConnecting();
		
		SCUInsertRatingVO sirVO = new SCUInsertRatingVO(book_number, movie_rate, review, member_id);
		
		try {
			int cnt = SCUMovieDAO.getInstance().insertRating(sirVO);
			
			if(cnt==1) {
				JOptionPane.showMessageDialog(swrv, "한줄평이 등록되었습니다.");
				swrv.getSrv().getDtmRatingTable().setRowCount(0);
				new SCURatingController(swrv.getSrv());
				swrv.dispose();
			}else {
				JOptionPane.showMessageDialog(swrv, "죄송합니다. 시스템 오류가 발생했습니다.");
				swrv.dispose();
			}//if~else
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(swrv, "죄송합니다. 시스템 오류가 발생했습니다.");
			sqle.printStackTrace();
		}
		
		
		
	}//insertRating Method
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		
		if(ae.getSource().equals(swrv.getJbtnCommit())) {
			String review = swrv.getJtaReview().getText().replaceAll("\n", " ");
			if(review.length()>100) {
				JOptionPane.showMessageDialog(swrv,"한줄 평이 너무 길어요!");
			}else {
				insertRating(review);
			}
		}//end if
		
		if(ae.getSource().equals(swrv.getJbtnExit())) {
			swrv.dispose();
		}//end if
		
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		swrv.dispose();
	}

}
