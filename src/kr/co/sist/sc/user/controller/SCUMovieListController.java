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
import javax.swing.JTable;

import kr.co.sist.sc.user.model.SCUMovieDAO;
import kr.co.sist.sc.user.view.SCUBookingView;
import kr.co.sist.sc.user.view.SCUMainView;
import kr.co.sist.sc.user.view.SCUMovieDetailView;
import kr.co.sist.sc.user.view.SCUMovieListView;
import kr.co.sist.sc.user.view.SCURatingView;
import kr.co.sist.sc.user.vo.SCUMovieListVO;

public class SCUMovieListController extends WindowAdapter implements ActionListener{

	private SCUMainView smv;
	private SCUMovieListView smlv;
	
	public SCUMovieListController(SCUMovieListView smlv) {
		this.smlv = smlv;
		smv = smlv.getSmv();
		
		setMovieList();
	}//Constructor
	
	public void isLogin() {
		if(smv.getIsLogin()==true) {
			
		} else {
			JOptionPane.showMessageDialog(smlv, "로그인이 선행되어야합니다.");
		}//end else
	}//isLogin Method
	
	
	public void setMovieList() {
		List<SCUMovieListVO> list = new ArrayList<>();
		
		try {
			list = SCUMovieDAO.getInstance().searchMovieList();
			
			Object[] objArr = null;
			SCUMovieListVO smlVO = null;
			String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/movie/s_movie_";
			for(int i = 0; i<list.size(); i++) {
				smlVO = list.get(i);
				objArr= new Object[11];
				objArr[0] = "\n\n\n"+(i+1)+"위";
				objArr[1] = new ImageIcon(imgPath+smlVO.getMovie_img());
				objArr[2] = "\n\n\n"+smlVO.getMovie_title();
				objArr[3] = "\n\n\n"+smlVO.getMovie_grade();
				objArr[4] = "\n\n\n"+smlVO.getAudience();
				objArr[5] = "\n\n\n"+smlVO.getRating_avg();
				objArr[6] = "\n\n\n"+smlVO.getGenre();
				objArr[7] = "\n\n\n"+smlVO.getRunningtime();
				objArr[8] = "\n\n\n"+smlVO.getPlaydate();
				objArr[9] = "\n\n\n"+smlVO.getActor();
				objArr[10] = smlVO.getMovie_code();
				smlv.getDtmMovieTable().addRow(objArr);
			}//end for
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}//end try~catch
		
	}//setMovieList Method
	
	
	public void dialogDetail(String selectedMovieCode) {
		new SCUMovieDetailView(smlv, selectedMovieCode);
	}//dialogDetail Method
	
	
	public void dialogBooking(String selectedMovieCode) {
		new SCUBookingView(smlv, selectedMovieCode);
	}//dialogBooking Method
	
	
	public void dialogRating() {
		new SCURatingView(smlv);
	}//dialogRating Method
	
	
	@Override
	public void windowClosing(WindowEvent we) {
		smlv.dispose();
	}//windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		JTable jt = smlv.getJtMovieTable();
		
		try {
			smlv.setSelectedMovieCode(String.valueOf(jt.getValueAt(jt.getSelectedRow(), 10)));
			smlv.setSelectedMovieTitle(String.valueOf(jt.getValueAt(jt.getSelectedRow(), 2)));
		}catch(ArrayIndexOutOfBoundsException aio) {
			
		}//end try~catch
		
		
		
		
		if(ae.getSource().equals(smlv.getJbtnExit())) {
			smlv.dispose();
			return;
		}//end if
		
		if(smlv.getSelectedMovieCode()==null) {
			

			JOptionPane.showMessageDialog(smlv, "영화를 선택해 주세요.");
			return;
		}//end if
		
		
		if(ae.getSource().equals(smlv.getJbtnDetail())) {
			dialogDetail(smlv.getSelectedMovieCode());
		}//end if
		
		if(ae.getSource().equals(smlv.getJbtnBooking())) {
			dialogBooking(smlv.getSelectedMovieCode());
		}//end if
		
		if(ae.getSource().equals(smlv.getJbtnRating())) {
			dialogRating();
		}//end if
		
	}//actionPerformed


}//Class
