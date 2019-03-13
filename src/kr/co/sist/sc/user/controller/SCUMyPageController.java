package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
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
				membership = "일반 회원";
				break;
			case "g":
				membership = "골드 회원";
				break;
			case "v":
				membership = "VIP 회원";
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
		String msg = "정말 회원탈퇴하시겠습니까?\n탈퇴하시면 같은 아이디로 재가입이 불가능합니다.";
		int confirm = JOptionPane.showConfirmDialog(smpv, msg, "리얼루?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		switch (confirm) {
		case JOptionPane.OK_OPTION:
			SCURemoveAccountVO sraVO = new SCURemoveAccountVO(smpv.getSmv().getIdConnecting(), " ", " ", " ", " ", " ", 0, 0);
			try {
				int sqlResult = SCUMyPageDAO.getInstance().deleteAccount(sraVO);
				
				if(sqlResult == 1) {
					JOptionPane.showMessageDialog(smpv, "이용해주셔서 감사합니다.");
					smpv.getSmv().getJbtnLogin().setIcon(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_login_join(215x40).png"));
					smpv.getSmv().setIsLogin(false);
					smpv.getSmv().setIdConnecting("");
					smpv.dispose();
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
	
	public void dialogPointView() {
		new SCUPointView(smpv);
	}
	
	public void dialogModifyMemberInfo() {
		new SCUModifyMemberInfoView(smpv);
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
			dialogModifyMemberInfo();
		}
		if(ae.getSource().equals(smpv.getJbtnPointUpdate())) {
			dialogPointView();
		}
	}
	
	@Override
	public void windowClosing(WindowEvent we) {
		smpv.dispose();
	}
	
}//Class
