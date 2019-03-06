package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.sc.user.model.SCUMyPageDAO;
import kr.co.sist.sc.user.view.SCUModifyMemberInfoView;
import kr.co.sist.sc.user.view.SCUModifyPWView;
import kr.co.sist.sc.user.view.SCUMyPageView;
import kr.co.sist.sc.user.view.SCUPointView;
import kr.co.sist.sc.user.vo.SCURemoveAccountVO;
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
			
			smpv.getJtfAccPoint().setText(" "+String.valueOf(ssmpVO.getAcc_point()));
			smpv.getJtfBirthdate().setText(" "+ssmpVO.getBirthdate());
			smpv.getJtfHoldPoint().setText(" "+String.valueOf(ssmpVO.getHold_point()));
			smpv.getJtfInputDate().setText(" "+ssmpVO.getInput_date());
			smpv.getJtfMemberID().setText(" "+ssmpVO.getMember_id());
			
			String membership = "";
			switch (ssmpVO.getMembership()) {
			case "s":
				membership = "�Ϲ� ȸ��";
				break;
			case "g":
				membership = "��� ȸ��";
				break;
			case "v":
				membership = "VIP ȸ��";
				break;
			}
			smpv.getJtfMembership().setText(" "+membership);
			smpv.getJtfName().setText(" "+ssmpVO.getName());
			smpv.getJtfPhone().setText(" "+ssmpVO.getPhone());
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
	}//setMyPage
	
	
	private void modifyMemberPW() {
		
		new SCUModifyPWView(smpv);
	}
	
	private void resignAccount() {
		String msg = "���� ȸ��Ż���Ͻðڽ��ϱ�?\nŻ���Ͻø� ���� ���̵�� �簡���� �Ұ����մϴ�.";
		int confirm = JOptionPane.showConfirmDialog(smpv, msg, "�����?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		switch (confirm) {
		case JOptionPane.OK_OPTION:
			SCURemoveAccountVO sraVO = new SCURemoveAccountVO(smpv.getSmv().getIdConnecting(), " ", " ", " ", " ", " ", 0, 0);
			try {
				int sqlResult = SCUMyPageDAO.getInstance().deleteAccount(sraVO);
				
				if(sqlResult == 1) {
					JOptionPane.showMessageDialog(smpv, "�̿����ּż� �����մϴ�.");
					smpv.dispose();
					smpv.getSmv().getJbtnLogin().setText("�α���/ȸ������");
					smpv.getSmv().setIsLogin(false);
					smpv.getSmv().setIdConnecting("");
				}else {
					
				}
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}
			break;

		case JOptionPane.CANCEL_OPTION:
			return;
		}
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(smpv.getJbtnExit())) {
			smpv.dispose();
		}
		if(ae.getSource().equals(smpv.getJbtnUpdatePW())) {
			modifyMemberPW();
		}
		if(ae.getSource().equals(smpv.getJbtnResign())) {
			resignAccount();
		}
		if(ae.getSource().equals(smpv.getJbtnUpdateInfo())) {
			new SCUModifyMemberInfoView(smpv);
		}
		if(ae.getSource().equals(smpv.getJbtnPointUpdate())) {
			new SCUPointView(smpv);
		}
	}
	
	@Override
	public void windowClosing(WindowEvent we) {
		smpv.dispose();
	}
	
}//Class
