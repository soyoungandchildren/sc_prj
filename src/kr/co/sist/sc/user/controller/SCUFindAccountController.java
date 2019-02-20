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
import kr.co.sist.sc.user.vo.SCULoginVO;
import kr.co.sist.sc.user.vo.SCUModifyPWVO;

public class SCUFindAccountController extends WindowAdapter implements ActionListener {

	private SCUFindAccountView sfav;
	private SCUModifyPWView smpv;
	private SCULoginView slv;

	public SCUFindAccountController(SCUFindAccountView sfav, SCULoginView slv) {
		this.sfav = sfav;
		this.slv = slv;
	}// SCUFindAccountController

	/**
	 * �������� ���̵�ã��
	 */
	public void findID() {
		JTextField jtfPhoneForID = sfav.getJtfPhoneForID();
		String phoneForID = jtfPhoneForID.getText().trim();

		try {
			String getID = SCULoginDAO.getInstance().selectID(phoneForID);

			if (!getID.isEmpty()) { // ��ȸ�� ���̵� ���� ��
				JOptionPane.showMessageDialog(sfav, "ã�� ID: [ " + getID + " ]");
				sfav.dispose();
			} else if (phoneForID.isEmpty()) { // ���̵� �Է����� �ʾ��� ��
				JOptionPane.showMessageDialog(sfav, "ID�� �Է��ϼ���.");
			} else { // ��ȸ�� ���̵� ���� ��
				JOptionPane.showMessageDialog(sfav, "��ϵ��� ���� ��ȭ��ȣ�Դϴ�.");
				jtfPhoneForID.setText("");
			}
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(sfav, "DB����!!!");
			se.printStackTrace();
		}
	}// findID

	/**
	 * ��й�ȣ
	 */
	public void findPW() { // ���̵�, ����, �̸� ��� �Է°��� ���� �� ��� ������ �����ϰ� ����� ��!
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
				JOptionPane.showMessageDialog(sfav, "������ �߸� �ԷµǾ����ϴ�.");
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

		// ���̵� ã���ư
		if (ae.getSource().equals(sfav.getJbtnFindID())) {
			findID();
		} // end if

		// ��й�ȣ ã�� ��ư
		if (ae.getSource().equals(sfav.getJbtnFindPW())) {
			findPW();
		} // end if

	}// actionPerformed

}
