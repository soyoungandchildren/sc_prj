package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import kr.co.sist.sc.user.model.SCUMainDAO;
import kr.co.sist.sc.user.view.SCULoginView;
import kr.co.sist.sc.user.view.SCUMainView;
import kr.co.sist.sc.user.view.SCUMovieListView;
import kr.co.sist.sc.user.view.SCUSnackMenuView;
import kr.co.sist.sc.user.vo.SCUMainVO;

public class SCUMainController extends WindowAdapter implements ActionListener{
	private SCUMainView smv;
	private SCUMainDAO smDAO;
	
	public SCUMainController(SCUMainView smv) {
		this.smv = smv;
		smDAO = SCUMainDAO.getInstance();
		setMain();
	}//Constructor
	
	public void setMain() {
		try {
			List<SCUMainVO> list = smDAO.setMain();
			//����Ʈ �Ѿ���°� Ȯ��
			
		} catch (SQLException se) {
			se.printStackTrace();
		}//end catch
		
	}//setMain
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource().equals(smv.getJbtnLogin())) {//�α��ι�ư
			if(!smv.getIsLogin()) {
				new SCULoginView(smv);
			}else {
				smv.setIsLogin(false);
				smv.setIdConnecting("");
				smv.getJbtnLogin().setText("�α���/ȸ������");
			}
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
