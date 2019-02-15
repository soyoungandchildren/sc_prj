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

public class SCUFindAccountController extends WindowAdapter implements ActionListener {

	private SCUFindAccountView sfav;
	private SCUModifyPWView smpv;
	private SCULoginView slv;
	private SCULoginDAO slDao;

	public SCUFindAccountController(SCUFindAccountView sfav, SCULoginView slv) {
		this.sfav = sfav;
		this.slv = slv;
	}// SCUFindAccountController

	public void findID() {
		JTextField jtfPhoneForID = sfav.getJtfPhoneForID();
		String phoneForID = jtfPhoneForID.getText().trim();
		
		try {
			String getID = SCULoginDAO.getInstance().selectID(phoneForID);
			
			if(!getID.isEmpty()) { //��ȸ�� ���̵� ���� ��
				JOptionPane.showMessageDialog(sfav, "ã�� ID: [ "+getID+" ]");
				sfav.dispose();
			}else if(phoneForID.isEmpty()) { //���̵� �Է����� �ʾ��� ��
				JOptionPane.showMessageDialog(sfav, "ID�� �Է��ϼ���.");
			}else{ //��ȸ�� ���̵� ���� �� 
				JOptionPane.showMessageDialog(sfav, "��ϵ��� ���� ��ȭ��ȣ�Դϴ�.");
			}
		}catch(SQLException se) {
			JOptionPane.showMessageDialog(sfav, "DB����!!!");
			se.printStackTrace();
		}
	}//class

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
			new SCUModifyPWView(slv);
			sfav.dispose();
		} // end if

		// ��й�ȣ ����â '����'��ư
//		if (ae.getSource() == smpv.getJbtnConfirm()) {
//			
//		} // end if

		// ��й�ȣ ����â '���'��ư
//		if (ae.getSource() == smpv.getJbtnExit()) {
//			new SCUModifyPWView(slv);
//			smpv.dispose();
//		} // end if
	}//actionPerformed

}
