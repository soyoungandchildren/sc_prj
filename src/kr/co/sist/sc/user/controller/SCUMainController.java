package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import kr.co.sist.sc.user.model.SCUMainDAO;
import kr.co.sist.sc.user.view.SCULoginView;
import kr.co.sist.sc.user.view.SCUMainView;
import kr.co.sist.sc.user.view.SCUMovieListView;
import kr.co.sist.sc.user.view.SCUSnackMenuView;
import kr.co.sist.sc.user.vo.SCUMainVO;

public class SCUMainController extends WindowAdapter implements ActionListener{
	
	private SCUMainView smv;
//	private SCULogoutView slov;
	
	public SCUMainController(SCUMainView smv) {
		this.smv = smv;
//		setMain();
	}//Constructor
	
	public void setMain() {
		List<SCUMainVO> list = new ArrayList<>();
		try {
			list = SCUMainDAO.getInstance().setMain();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}//end catch
		
	}//setMain Method
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource().equals(smv.getJbtnLogin())) {//�α��ι�ư
			new SCULoginView(smv);
		}
		
		if(ae.getSource().equals(smv.getJbtnBooking())) {//���� ��ư
			new SCUMovieListView(smv);
		}//end if
		
		if(ae.getSource().equals(smv.getJbtnSnack())) {//���� ��ư
			new SCUSnackMenuView(smv);
		}//end if
		
	}//actionPerformed
	
	@Override
	public void windowClosing(WindowEvent we) {
		smv.dispose();
	}//windowClosing
	
}//Class