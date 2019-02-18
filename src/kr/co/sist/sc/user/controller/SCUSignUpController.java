package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.sc.user.model.SCULoginDAO;
import kr.co.sist.sc.user.view.SCUSignUpView;
import kr.co.sist.sc.user.vo.SCUSignUpVO;

public class SCUSignUpController extends WindowAdapter implements ActionListener{

	private SCUSignUpView ssuv;
	private SCULoginDAO slDao;
	private int count = 0;

	public SCUSignUpController(SCUSignUpView ssuv) {
		this.ssuv = ssuv;
		slDao = SCULoginDAO.getInstance();
	}//end SCUSignUpController

	/**
	 * id �ߺ�Ȯ�� (���̾�α� â ���� UIó�� �����ϱ�)
	 * @param id
	 */
	public void checkDuplication(String id) {

		JTextField jtf = ssuv.getJtfID();
		String inputId = jtf.getText().trim();

		try {
			if (inputId.equals("")) {
				JOptionPane.showMessageDialog(ssuv, "���̵� �Է��ϼ���.");
			} else if(inputId.length() < 3 || inputId.length() > 30) {
				JOptionPane.showMessageDialog(ssuv, "���̵�� 3~30�� �̳��� �����մϴ�.");
			} else if (slDao.selectCheckDup(id) && !inputId.equals("")) { // ���� ���̵� ������ ��
				JOptionPane.showMessageDialog(ssuv, "���� ���̵� �����մϴ�.");
				jtf.setText("");
			} else {// ���� ���̵� �������� ���� ��
				JOptionPane.showMessageDialog(ssuv, "��� ������ ���̵��Դϴ�.");// end else
			}//end else
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ssuv, "��� �� �ٽ� �õ����ּ���.");
			e.printStackTrace();
		} // end catch

	}//checkDuplication
	
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
	}//reset

	/**
	 * ȸ������
	 * 3. ...���̾�α� â�� ��.. :( (���� ��)
	 */
	public void addSignUp() {
		
		//���̵�
		JTextField jtfId = ssuv.getJtfID();
		
		//��й�ȣ
		JPasswordField jpfPw = ssuv.getJpfPW();
		String stringPW = new String(jpfPw.getPassword());
		
		//��й�ȣ Ȯ��
		String stringconfirmPW = new String(ssuv.getJpfConfirmPW().getPassword());
		
		//�̸�, ����, ��ȭ��ȣ
		JTextField jtfName = ssuv.getJtfName();
		JTextField jtfBirthdate = ssuv.getJtfBirth();
		JTextField jtfPhone = ssuv.getJtfPhone();
		
		SCUSignUpVO ssuvo = new SCUSignUpVO(jtfId.getText().trim(),stringPW.trim(), jtfName.getText().trim(), jtfBirthdate.getText().trim(), jtfPhone.getText().trim());
		
		//���̵� ��ȿ�� Ȯ��
		if (jtfId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(ssuv, "���̵�� �ʼ��Է»����Դϴ�.");
			return;		
		} else if(jtfId.getText().length() < 4 || jtfId.getText().length() > 30) {
			JOptionPane.showMessageDialog(ssuv, "���̵�� 3~30�� �̳��Դϴ�.");
			jtfId.setText("");
			return;		
		} else if(count < 1) {
			JOptionPane.showMessageDialog(ssuv, "���̵� �ߺ�Ȯ���� ���ּ���.");
			return;
		}//end else
		
		//��й�ȣ ��ȿ�� Ȯ��
		if(stringPW.isEmpty()) {
			JOptionPane.showMessageDialog(ssuv, "��й�ȣ�� �ʼ��Է»����Դϴ�.");
			return;
		} else if(!stringPW.equals(stringconfirmPW)) {
			JOptionPane.showMessageDialog(ssuv, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			jpfPw.setText("");
			return;
		}else if(stringPW.length()<3 || stringPW.length()>31) {
			JOptionPane.showMessageDialog(ssuv, "��й�ȣ�� �ʼ��Է»����Դϴ�.");
			jpfPw.setText("");
			return;
		}//end else
		
		//�̸�, �������, ��ȭ��ȣ �Է� Ȯ��
		if(jtfName.getText().isEmpty()) { 
			JOptionPane.showMessageDialog(ssuv, "�̸��� �Է��ϼ���");
			return;
		} else if(jtfBirthdate.getText().isEmpty()) {
			JOptionPane.showMessageDialog(ssuv, "��������� �Է��ϼ���");
			return;
		} else if(jtfPhone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(ssuv, "��ȭ��ȣ�� �Է��ϼ���");
			return;
		}//end else
		
		try {
			// ������ ���� catch�� ������ DB�� �߰��� �ȵ�
			SCULoginDAO.getInstance().insertSignUp(ssuvo);// ������ ���� �ʴ� ��� DB�� �߰�	
			JOptionPane.showMessageDialog(ssuv, "[ "+jtfId.getText()+" ] ���� ȸ�������� ȯ���մϴ�.");
			jtfName.requestFocus();
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(ssuv, "DB���� ������ �߻��Ͽ����ϴ�.");
			se.printStackTrace();
		} // end catch
	}//addSignUp
	
	@Override
	public void windowClosing(WindowEvent we) {
		ssuv.dispose();
	}//windowClosing

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource().equals(ssuv.getJbtnCheckDuplication())) {
			count++;
			checkDuplication(ssuv.getJtfID().getText());
		} // JbtnCheckDuplication

		if(ae.getSource().equals(ssuv.getJbtnReset())) {
			reset();
		}//getJbtnReset
		
		if (ae.getSource().equals(ssuv.getJbtnSignUp())) {
			addSignUp();
		}//getJbtnSignUp
		
		if(ae.getSource().equals(ssuv.getJbtnExit())){
			ssuv.dispose();
		}//getJbtnExit
	}//actionPerformed



}// class
