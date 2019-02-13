package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.sc.user.model.SCUMovieDAO;
import kr.co.sist.sc.user.view.SCUBookingView;
import kr.co.sist.sc.user.vo.SCUSearchScreenVO;

public class SCUBookingController extends WindowAdapter implements ActionListener{
	
	private SCUBookingView sbv;
	
	public SCUBookingController(SCUBookingView sbv) {
		this.sbv = sbv;
		searchOnScreen();
	}//Constructor
	
	public void searchOnScreen(){
		try {
			List<SCUSearchScreenVO> list = SCUMovieDAO.getInstance().selectScreen(sbv.getMovieCode());
			
			Object[] objArr = new Object[list.size()];
			
			for(int i = 0; i<objArr.length; i++) {
				objArr[0] = list.get(i).getScreen_date();
				objArr[1] = list.get(i).getStart_time();
				objArr[2] = list.get(i).getEnd_time();
				objArr[3] = list.get(i).getScreen_name();
				
				sbv.getDtmOnScreen().addRow(objArr);
			}//end for
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
	}//searchOnScreen
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
	}//actionPerformed Override
	
	
}//Class
