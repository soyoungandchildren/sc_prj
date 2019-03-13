package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kr.co.sist.sc.user.model.SCULoginDAO;
import kr.co.sist.sc.user.view.SCUFindAccountView;
import kr.co.sist.sc.user.view.SCULoginView;
import kr.co.sist.sc.user.view.SCUModifyPWView;
import kr.co.sist.sc.user.vo.SCUFindPWVO;

public class SCUFindAccountController extends WindowAdapter implements ActionListener {

	private SCUFindAccountView sfav;
	private SCULoginView slv;

	public SCUFindAccountController(SCUFindAccountView sfav, SCULoginView slv) {
		this.sfav = sfav;
		this.slv = slv;
	}// SCUFindAccountController

	/**
	 * 전번으로 아이디찾기
	 */
	public void findID() {
		JTextField jtfPhoneForID = sfav.getJtfPhoneForID();
		String phoneForID = jtfPhoneForID.getText().trim();

		try {
			String getID = SCULoginDAO.getInstance().selectID(phoneForID);

			if (!getID.isEmpty()) { // 조회된 아이디가 있을 때
				JOptionPane.showMessageDialog(sfav, "찾은 ID: [ " + getID + " ]");
				sfav.dispose();
			} else if (phoneForID.isEmpty()) { // 아이디를 입력하지 않았을 때
				JOptionPane.showMessageDialog(sfav, "ID를 입력하세요.");
			} else { // 조회된 아이디가 없을 때
				JOptionPane.showMessageDialog(sfav, "등록되지 않은 전화번호입니다.");
				jtfPhoneForID.setText("");
			}
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(sfav, "DB오류!!!");
			se.printStackTrace();
		}
	}// findID

	/**
	 * 비밀번호
	 */
	public void findPW() {
		JTextField jtfIDForPW = sfav.getJtfIDForPW();
		JTextField jtfPhoneForPW = sfav.getJtfPhoneForPW();
		JTextField jtfNameForPW = sfav.getJtfNameForPW();

		String IDForPW = jtfIDForPW.getText().trim();
		String NameForPW = jtfNameForPW.getText().trim();
		String PhoneForPW = jtfPhoneForPW.getText().trim();

		SCUFindPWVO sfpvo = new SCUFindPWVO(IDForPW, NameForPW, PhoneForPW);
		try {
			boolean result = SCULoginDAO.getInstance().selectPW(sfpvo);
			if (result) {
				sfav.dispose();
				new SCUModifyPWView(slv, sfav);
			} else {
				JOptionPane.showMessageDialog(sfav, "정보가 잘못 입력되었습니다.");
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		
	}// findPW

	@Override
	public void windowClosing(WindowEvent we) {
		sfav.dispose();
	}// end windowClosing

	@Override
	public void actionPerformed(ActionEvent ae) {

		// 아이디 찾기버튼
		if (ae.getSource().equals(sfav.getJbtnFindID())) {
			findID();
		} // end if

		// 비밀번호 찾기 버튼
		if (ae.getSource().equals(sfav.getJbtnFindPW())) {
			findPW();
		} // end if

	}// actionPerformed

}
