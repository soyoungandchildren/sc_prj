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
			JOptionPane.showMessageDialog(smlv, "�α����� ����Ǿ���մϴ�.");
		}//end else
	}//isLogin Method
	
	
	public void setMovieList() {
		List<SCUMovieListVO> list = new ArrayList<>();
		
		try {
			list = SCUMovieDAO.getInstance().searchMovieList();
			
			Object[] objArr = null;
			SCUMovieListVO smlVO = null;
			String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/s";
			for(int i = 0; i<list.size(); i++) {
				smlVO = list.get(i);
				objArr= new Object[11];
				objArr[0] = (i+1)+"��";
				objArr[1] = new ImageIcon(imgPath+smlVO.getMovie_img());
				objArr[2] = smlVO.getMovie_title();
				objArr[3] = smlVO.getMovie_grade();
				objArr[4] = smlVO.getAudience();
				objArr[5] = smlVO.getRating_avg();
				objArr[6] = smlVO.getGenre();
				objArr[7] = smlVO.getRunningtime();
				objArr[8] = smlVO.getPlaydate();
				objArr[9] = smlVO.getActor();
				objArr[10] = smlVO.getMovie_code();
				smlv.getDtmMovieTable().addRow(objArr);
				
			}//end for
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}//end try~catch
		
	}//setMovieList Method
	
	
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
			JOptionPane.showMessageDialog(smlv, "��ȭ�� ������ �ּ���.");
			return;
		}//end if
		
		
		if(ae.getSource().equals(smlv.getJbtnDetail())) {
			new SCUMovieDetailView(smlv, smlv.getSelectedMovieCode());
		}//end if
		
		if(ae.getSource().equals(smlv.getJbtnBooking())) {
				new SCUBookingView(smlv, smlv.getSelectedMovieCode());
		}//end if
		
	}//actionPerformed


}//Class
