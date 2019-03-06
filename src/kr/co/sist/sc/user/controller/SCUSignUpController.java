package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.sc.user.model.SCULoginDAO;
import kr.co.sist.sc.user.view.SCUSignUpView;
import kr.co.sist.sc.user.vo.SCUSignUpVO;

public class SCUSignUpController extends WindowAdapter implements ActionListener, KeyListener {

	private SCUSignUpView ssuv;
	private SCULoginDAO slDao;
	private int count = 0;
	boolean booleanCheckDub, booleanKey;

	public SCUSignUpController(SCUSignUpView ssuv) {
		this.ssuv = ssuv;
		slDao = SCULoginDAO.getInstance();
	}// end SCUSignUpController

	/**
	 * id �ߺ�Ȯ�� (���̾�α� â ���� UIó�� �����ϱ�)
	 * 
	 * @param id
	 */
	public void checkDuplication(String id) {

		JTextField jtf = ssuv.getJtfID();
		String inputId = jtf.getText().trim();

		try {
			if (inputId.equals("")) {
				JOptionPane.showMessageDialog(ssuv, ".");
			} else if (inputId.length() < 3 || inputId.length() > 30) {
				JOptionPane.showMessageDialog(ssuv, "���̵�� 3~30�� �̾��̵� �Է��ϼ��䳻�� �����մϴ�.");
			} else if (slDao.selectCheckDup(id) && !inputId.equals("")) { // ���� ���̵� ������ ��
				JOptionPane.showMessageDialog(ssuv, "���� ���̵� �����մϴ�.");
				jtf.setText("");
			} else {// ���� ���̵� �������� ���� ��
				booleanCheckDub = true;
				JOptionPane.showMessageDialog(ssuv, "��� ������ ���̵��Դϴ�.");// end else
			} // end else

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ssuv, "��� �� �ٽ� �õ����ּ���.");
			e.printStackTrace();
		} // end catch

	}// checkDuplication

	/**
	 * ���ۼ� (�ʱ�ȭ)
	 */
	public void reset() {
		ssuv.getJtfID().setText("");
		ssuv.getJpfPW().setText("");
		ssuv.getJpfConfirmPW().setText("");
		ssuv.getJtfName().setText("");
		ssuv.getJtfBirth().setText("");
		ssuv.getJtfPhone().setText("");
	}// reset

//	private void setDay() {
//		int selYear = ((Integer) ssuv.getJcbYear().getSelectedItem()).intValue();
//		int selMonth = ((Integer) ssuv.getJcbMonth().getSelectedItem()).intValue();
//
//		// ������ �� ���
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, selYear);
//		cal.set(Calendar.MONTH, selMonth - 1);
//
//		int lastDay = cal.getActualMaximum(Calendar.DATE); // ���� ������ ���� ���ϴ� �޼ҵ�
//		int nowDay = cal.get(Calendar.DAY_OF_MONTH);
//		ssuv.getCbmDay().removeAllElements(); // ���� �ʱ�ȭ�ϰ�
//
//		for (int day = 1; day < lastDay + 1; day++) {
//			ssuv.getCbmDay().addElement(day);// ������ ���� �����Ѵ�.
//		} // end for
//
////		lmv.getCbmDay().setSelectedItem(new Integer(nowDay)); // ������ �����Ѵ�.
//
//	}// setDay

	/**
	 * ȸ������
	 */
	public void addSignUp() {
		// ���̵�
		JTextField jtfId = ssuv.getJtfID();

		// ��й�ȣ
		JPasswordField jpfPw = ssuv.getJpfPW();
		String stringPW = new String(jpfPw.getPassword());

		// ��й�ȣ Ȯ��
		JPasswordField jpfConfirmPW = ssuv.getJpfConfirmPW();
		String stringConfirmPW = new String(ssuv.getJpfConfirmPW().getPassword());

		// �Է��� ���� �� ���ϱ�
		int selYear = ((Integer) ssuv.getJcbYear().getSelectedItem()).intValue();
		int selMonth = ((Integer) ssuv.getJcbMonth().getSelectedItem()).intValue();
		int selDay = ((Integer) ssuv.getJcbDay().getSelectedItem()).intValue();

		// ������� ���� ���߱�
		String month = "0";
		if (selMonth < 10) {
			month = "0" + Integer.toString(selMonth);
		}

		String day = "0";
		if (selDay < 10) {
			day = "0" + Integer.toString(selDay);
		}

		StringBuilder searchDate = new StringBuilder();
		searchDate.append(selYear).append(month).append(day);

		// �̸�, ��ȭ��ȣ
		JTextField jtfName = ssuv.getJtfName();
		JTextField jtfPhone = ssuv.getJtfPhone();

		SCUSignUpVO ssuvo = new SCUSignUpVO(jtfId.getText().trim(), stringPW.trim(), jtfName.getText().trim(),
				searchDate.toString().trim(), jtfPhone.getText().trim());
		
		
		///////////////////��ȿ�� ����//////////////////
		// ���̵� Ȯ��
		if (jtfId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(ssuv, "���̵�� �ʼ��Է»����Դϴ�.");
			jtfId.requestFocus();
			return;
		} else if (jtfId.getText().length() < 4 || jtfId.getText().length() > 30) {
			JOptionPane.showMessageDialog(ssuv, "���̵�� 3~30�� �̳��Դϴ�.");
			jtfId.setText("");
			jtfId.requestFocus();
			return;
		} else if (count < 1) {
			JOptionPane.showMessageDialog(ssuv, "���̵� �ߺ�Ȯ���� ���ּ���.");
			return;
		} // end else

		// ��й�ȣ
		if (stringPW.isEmpty()) {
			JOptionPane.showMessageDialog(ssuv, "��й�ȣ�� �ʼ��Է»����Դϴ�.");
			jpfPw.requestFocus();
			return;
		} else if (!stringPW.equals(stringConfirmPW)) {
			JOptionPane.showMessageDialog(ssuv, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			jpfPw.setText("");
			jpfConfirmPW.setText("");
			jpfPw.requestFocus();
			return;
		} else if (stringPW.length() < 3 || stringPW.length() > 31) {
			JOptionPane.showMessageDialog(ssuv, "��й�ȣ�� 3~30�� �̳��Դϴ�.");
			jpfPw.setText("");
			jpfPw.requestFocus();
			return;
		} // end else

		// �̸�
		if (jtfName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(ssuv, "�̸��� �Է��ϼ���");
			jtfName.requestFocus();
			return;
		} else if (jtfName.getText().length() < 2 || jtfName.getText().length() > 31) {
			JOptionPane.showMessageDialog(ssuv, "�̸��� 2~10�� ���̸� �����մϴ�.");
			jtfName.requestFocus();
			return;
		}

		// ������� (���ڸ� �Էµǰ�)
//		if (jtfBirthdate.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(ssuv, "��������� �Է��ϼ���");
//			jtfBirthdate.requestFocus();
//			return;
//		}

		// ��ȭ��ȣ
		if (jtfPhone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(ssuv, "��ȭ��ȣ�� �Է��ϼ���");
			jtfPhone.requestFocus();
			return;
		} // end else

		try {
			// ������ ���� catch�� ������ DB�� �߰��� �ȵ�
			SCULoginDAO.getInstance().insertSignUp(ssuvo);// ������ ���� �ʴ� ��� DB�� �߰�
			JOptionPane.showMessageDialog(ssuv, "[ " + jtfId.getText() + " ] ���� ȸ�������� ȯ���մϴ�.");
			ssuv.dispose();
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(ssuv, "DB���� ������ �߻��Ͽ����ϴ�.");
			se.printStackTrace();
		} // end catch
	}// addSignUp

	@Override
	public void windowClosing(WindowEvent we) {
		ssuv.dispose();
	}// windowClosing

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource().equals(ssuv.getJbtnCheckDuplication())) {
			count++;
			checkDuplication(ssuv.getJtfID().getText());
		} // �ߺ�Ȯ��

		if (ae.getSource().equals(ssuv.getJbtnReset())) {
			reset();
		} // �ʱ�ȭ

		if (ae.getSource().equals(ssuv.getJbtnSignUp())) {
			if (booleanCheckDub) {
				addSignUp();
			} else {
				JOptionPane.showMessageDialog(ssuv, "���̵� �ߺ�Ȯ���� ���ּ���.");
			}
		} // ����

		if (ae.getSource().equals(ssuv.getJbtnExit())) {
			ssuv.dispose();
		} // getJbtnExit
	}// actionPerformed

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == ssuv.getJtfID()) {
			booleanCheckDub = false;
		} // end if
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}// class
