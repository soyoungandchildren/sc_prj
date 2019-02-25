package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JTextField;

import kr.co.sist.sc.user.model.SCUMyPageDAO;
import kr.co.sist.sc.user.view.SCUModifyMemberInfoView;
import kr.co.sist.sc.user.view.SCUModifyPWView;
import kr.co.sist.sc.user.view.SCUMyPageView;
import kr.co.sist.sc.user.vo.SCUSearchMyPageVO;

public class SCUMyPageController extends WindowAdapter implements ActionListener{
	
	private SCUMyPageView smpv;
	
	public SCUMyPageController(SCUMyPageView smpv) {
		this.smpv = smpv;
		
		setMyPage();
	}//Constructor
	
	public void setMyPage() {
		try {
			SCUSearchMyPageVO ssmpVO = SCUMyPageDAO.getInstance().selectMyPage(smpv.getSmv().getIdConnecting());
			
			smpv.getJtfAccPoint().setText(String.valueOf(ssmpVO.getAcc_point()));
			smpv.getJtfBirthdate().setText(ssmpVO.getBirthdate());
			smpv.getJtfHoldPoint().setText(String.valueOf(ssmpVO.getHold_point()));
			smpv.getJtfInputDate().setText(ssmpVO.getInput_date());
			smpv.getJtfMemberID().setText(ssmpVO.getMember_id());
			smpv.getJtfMembership().setText(ssmpVO.getMembership());
			smpv.getJtfName().setText(ssmpVO.getName());
			smpv.getJtfPhone().setText(ssmpVO.getPhone());
			
			
			
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
	}//setMyPage
	
	
	public void modifyMemberPW() {
		
		new SCUModifyPWView(smpv);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(smpv.getJbtnExit())) {
			smpv.dispose();
		}
		if(ae.getSource().equals(smpv.getJbtnUpdatePW())) {
			modifyMemberPW();
		}
		if(ae.getSource().equals(smpv.getJbtnUpdateInfo())) {
			new SCUModifyMemberInfoView(smpv);
		}
	}
	
	@Override
	public void windowClosing(WindowEvent we) {
		smpv.dispose();
	}
	
}//Class
