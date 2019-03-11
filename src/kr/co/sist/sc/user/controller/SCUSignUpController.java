package kr.co.sist.sc.user.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

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
				JOptionPane.showMessageDialog(ssuv, "���̵�� �ʼ� �Է»����Դϴ�.");
				jtf.setText("");
				ssuv.getJpfPW().requestFocus();
			} else if (inputId.length() < 4 || inputId.length() > 30) {
				JOptionPane.showMessageDialog(ssuv, "���̵�� 3~30�� �̳��� �����մϴ�.");
			} else if (slDao.selectCheckDup(id) && !inputId.equals("")) { // ���� ���̵� ������ ��
				JOptionPane.showMessageDialog(ssuv, "���� ���̵� �����մϴ�.");
				jtf.setText("");
				ssuv.getJpfPW().requestFocus();
			} else {// ���� ���̵� �������� ���� ��
				booleanCheckDub = true;
				JOptionPane.showMessageDialog(ssuv, "��� ������ ���̵��Դϴ�.");// end else
				ssuv.getJlNoteID().setText("");
				ssuv.getJpfPW().requestFocus();
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

		// ��ȭ��ȣ
		JTextField jtfPhone = ssuv.getJtfPhone();
		String num = ((String) ssuv.getJcbNum().getSelectedItem()); // ���ڸ�
		String num1 = jtfPhone.getText();
		String number = "";
		number = num + num1;

		// �̸�
		JTextField jtfName = ssuv.getJtfName();

		/////////////////// ��ȿ�� ����//////////////////
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
		
		for (int i = 0; i < jtfName.getText().length(); i++) {
			if(jtfName.getText().charAt(i) >= '0' && jtfName.getText().charAt(i) <= '9') {
				ssuv.getJlNoteName().setForeground(Color.RED);
				ssuv.getJlNoteName().setText("�̸��� ���ڸ� �Է� �����մϴ�.");
				return;
			}//end if
		}//end for

		// ��ȭ��ȣ ��ȿ��
		if (jtfPhone.getText().isEmpty()) { // ��ĭ �̶��
			JOptionPane.showMessageDialog(ssuv, "��ȭ��ȣ�� �Է��ϼ���");
			jtfPhone.requestFocus();
			return;
		} else if (!jtfPhone.getText().isEmpty()) {// ��ĭ�� �ƴ� ���
			if (number.length() < 10 || number.length() > 11) {
				JOptionPane.showMessageDialog(ssuv, "��ȭ��ȣ�� ���̴� 11~12�� �̳��Դϴ�.");
				jtfPhone.setText("");
				return;
			}

			for (int i = 0; i < number.length(); i++) {
				if (number.charAt(i) < '0' || number.charAt(i) > '9') {
					JOptionPane.showMessageDialog(ssuv, "��ȭ��ȣ�� ���ڸ� �����մϴ�.");
					jtfPhone.setText("");
					return;
				}
			} // end for

			if (number.length() == 10) {
				number = num + "-" + num1.substring(0, 3) + "-" + num1.substring(3, 7);
			} else if (number.length() == 11) {
				number = num + "-" + num1.substring(0, 4) + "-" + num1.substring(4, 8);
			} // else if
		} // end else

		SCUSignUpVO ssuvo = new SCUSignUpVO(jtfId.getText().trim(), stringPW.trim(), jtfName.getText().trim(),
				searchDate.toString().trim(), number.trim());

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
		
		String num = ((String) ssuv.getJcbNum().getSelectedItem());
		String num1 = ssuv.getJtfPhone().getText();
		String number = num + num1;

		if (ae.getSource().equals(ssuv.getJbtnCheckDuplication())) {
			count++;
			checkDuplication(ssuv.getJtfID().getText());
			if (count > 0) {

			}
		} // �ߺ�Ȯ��

		if (ae.getSource().equals(ssuv.getJbtnReset())) {
			reset();
		} // �ʱ�ȭ

		if (ae.getSource().equals(ssuv.getJbtnSignUp())) {
			if (booleanCheckDub) {
				addSignUp();
			} else {
				JOptionPane.showMessageDialog(ssuv, "���̵� �ߺ�Ȯ���� ���ּ���.");
				ssuv.getJtfID().requestFocus();
			}
		} // ����

		if (ae.getSource().equals(ssuv.getJbtnExit())) {
			ssuv.dispose();
		} // getJbtnExit

		if (ae.getSource().equals(ssuv.getJtfPhone())) {
			ssuv.getJbtnSignUp().requestFocus();
		} // getJtfID
		
		//��ȭ��ȣ �� ��ȣ �����ߴ��� Ȯ��
		if(ae.getSource().equals(ssuv.getJcbNum())) {
			if(number.length() == 10  || number.length() == 11) {
			ssuv.getJlNoteNum().setText("");
			return;
			}
		}
	}// actionPerformed

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == ssuv.getJtfID()) {
			booleanCheckDub = false;
		} // end if
	}

	@Override
	public void keyReleased(KeyEvent e) {
		String inputId = new String(ssuv.getJtfID().getText().trim());
		String inputPw = new String(ssuv.getJpfPW().getPassword());
		String inputCpw = new String(ssuv.getJpfConfirmPW().getPassword());
		String inputName = new String(ssuv.getJtfName().getText().trim());
		String inputNum = new String(ssuv.getJtfPhone().getText().trim());
		String num = ((String) ssuv.getJcbNum().getSelectedItem());
		String num1 = ssuv.getJtfPhone().getText();
		String number = num + num1;
		int key = e.getKeyCode();

		//// ���̵�
		if (e.getSource().equals(ssuv.getJtfID())) {

			if ("".equals(inputId)) {
				ssuv.getJlNoteID().setForeground(Color.red);
				ssuv.getJlNoteID().setText("���̵�� �ʼ� �Է»����Դϴ�.");
				return;
			}

			if (inputId.length() < 4 || inputId.length() > 30) { // ���̵� ���ڼ� Ʋ����
				ssuv.getJlNoteID().setForeground(Color.red);
				ssuv.getJlNoteID().setText("���̵�� 3~30�ڱ��� �����մϴ�.");
				return;
			} else {
				ssuv.getJlNoteID().setForeground(Color.red);
				ssuv.getJlNoteID().setText("�ߺ�Ȯ���� ���ּ���.");
				return;
			}
		} // end Id

		//// ��й�ȣ
		if (e.getSource().equals(ssuv.getJpfConfirmPW()) || e.getSource().equals(ssuv.getJpfPW())) {
			if ("".equals(inputPw) && "".equals(inputCpw)) {

				ssuv.getJlNotePass().setForeground(Color.red);
				ssuv.getJlNotePass().setText("��й�ȣ�� �ʼ� �Է»����Դϴ�!");
				return;

			} else if (inputPw.length() < 3 && inputPw.length() > 30) {
				ssuv.getJlNotePass().setForeground(Color.RED);
				ssuv.getJlNotePass().setText("��й�ȣ�� 3~30�� �̳��� �����մϴ�.");
				return;

			} else {
				if (!inputPw.equals(inputCpw)) {
					ssuv.getJlNotePass().setForeground(Color.RED);
					ssuv.getJlNotePass().setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					return;
				} else {
					ssuv.getJlNotePass().setText("");
					return;
				}
			}
		}

		// �̸�
		if (e.getSource().equals(ssuv.getJtfName())) {
			if ("".equals(inputName)) {
				ssuv.getJlNoteName().setForeground(Color.red);
				ssuv.getJlNoteName().setText("�̸��� �ʼ� �Է»����Դϴ�");
				return;
			}
			if (inputName.length() < 2 || inputName.length() > 10) {
				ssuv.getJlNoteName().setForeground(Color.RED);
				ssuv.getJlNoteName().setText("�̸��� 2~10�� �̳��� �����մϴ�.");
				return;
			}
			
			for (int i = 0; i < inputName.length(); i++) {
				if(inputName.charAt(i) >= '0' && inputName.charAt(i) <= '9') {
					ssuv.getJlNoteName().setForeground(Color.RED);
					ssuv.getJlNoteName().setText("�̸��� ���ڸ� �Է� �����մϴ�.");
					return;
				}//end if
			}//end for
			
			ssuv.getJlNoteName().setText("");
			return;
		}

		//// ��ȭ��ȣ
		if (e.getSource().equals(ssuv.getJtfPhone()) || e.getSource().equals(ssuv.getJcbNum())) {
			if (num.length() > 2 && (number.length() > 9 && number.length() < 12)) {
				ssuv.getJlNoteNum().setText("");
				
				//��ȭ��ȣ���� �Է��ϰ� enter ������ ���Թ�ư ����
				if (key == KeyEvent.VK_ENTER) {// �� ���� ���� Ű�� enter���
					ssuv.getJbtnSignUp().doClick();
				} // end if

			} else if ("".equals(inputNum)) {
				ssuv.getJlNoteNum().setForeground(Color.red);
				ssuv.getJlNoteNum().setText("��ȭ��ȣ�� �ʼ��Է»����Դϴ�.");
				return;
			} else if (inputNum.length() < 7 || inputNum.length() > 8) {
				ssuv.getJlNoteNum().setForeground(Color.red);
				ssuv.getJlNoteNum().setText("��ȭ��ȣ ��Ŀ� ���� �ʽ��ϴ�.");
				return;
			}
				if (ssuv.getJcbNum().getSelectedIndex() == 0) {
					ssuv.getJlNoteNum().setForeground(Color.red);
					ssuv.getJlNoteNum().setText("��ȭ��ȣ �� ��ȣ�� �����ϼ���.");
					return;
				} else {
					ssuv.getJlNoteNum().setText("");
				}
			} // end



	}// keyReleased

	@Override
	public void keyPressed(KeyEvent e) {
	}
}// class
