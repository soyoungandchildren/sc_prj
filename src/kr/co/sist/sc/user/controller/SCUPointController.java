package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.sc.user.model.SCUPointDAO;
import kr.co.sist.sc.user.view.SCUMyPageView;
import kr.co.sist.sc.user.view.SCUPointView;

public class SCUPointController extends WindowAdapter implements ActionListener {
	private SCUPointView spv;
	private SCUMyPageView smpv;
	private String user;
	private SCUPointDAO spDAO;
	private int sumPoint, nowPoint;
	
	public SCUPointController(SCUPointView spv, SCUMyPageView smpv, String holdPoint, String memberId) {
		this.spv = spv;
		this.smpv = smpv;
		spDAO = SCUPointDAO.getInstance();
		user = memberId.trim();
		nowPoint = Integer.parseInt(holdPoint.trim());
		spv.getJtfNowPoint().setText(holdPoint);
	}//SCUPointController
	
	public void pointUpdate() {
		try {
			String temp = String.valueOf(spv.getJcbPoint().getSelectedItem());
			int charge = Integer.parseInt(temp.replaceAll(",", ""));
			sumPoint = nowPoint+charge;
			
			String acc = smpv.getJtfAccPoint().getText().trim();
			int accP = charge+Integer.parseInt(acc);
			
			boolean result = spDAO.pointCharge(sumPoint, user, charge);
			if(result) {
				JOptionPane.showMessageDialog(spv, "충전이 완료되었습니다!", "충전 완료", JOptionPane.PLAIN_MESSAGE);
				spv.dispose();
				smpv.getJtfHoldPoint().setText(" "+String.valueOf(sumPoint));
				smpv.getJtfAccPoint().setText(" "+String.valueOf(accP));
				
				//회원의 누적포인트에 따른 회원 등급 변경
				if(0 <= accP && accP < 100000) {
					smpv.getJtfMembership().setText(" 일반 회원");
				} else if(accP < 200000) {
					smpv.getJtfMembership().setText(" 골드 회원");
				} else {
					smpv.getJtfMembership().setText(" VIP 회원");
				}//end else
				
			} else {
				JOptionPane.showMessageDialog(spv, "내부 사정으로 포인트 충전이 중단되었습니다.\n다시 시도해주세요!", "충전 오류", JOptionPane.WARNING_MESSAGE);
			}//end else
			
		} catch (SQLException se) {
			se.printStackTrace();
		}//end catch
		
	}//PointUpdate

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == spv.getJbtnCharge()) {
			String temp = String.valueOf(spv.getJcbPoint().getSelectedItem());
			int replace = Integer.parseInt(temp.replaceAll(",", ""));
			int temp2 = Integer.parseInt(spv.getJtfNowPoint().getText().trim());
			int temp3 = temp2+replace;
			
			switch(JOptionPane.showConfirmDialog(spv, "현재 금액 : "+temp2+"\n충전 후 금액 : "+temp3+"\n정말 포인트를 충전하시겠습니까?", "포인트 충전", JOptionPane.OK_CANCEL_OPTION)) {
			case JOptionPane.OK_OPTION :
				pointUpdate();
			case JOptionPane.CANCEL_OPTION :
				return;
			}//end switch
		}//end if
		
		if(ae.getSource() == spv.getJbtnClose()) {
			spv.dispose();
		}//end if
	}//actionPerformed
	
	@Override
	public void windowClosing(WindowEvent e) {
		spv.dispose();
	}//windowClosing

}//class
