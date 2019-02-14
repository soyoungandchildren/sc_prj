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
import kr.co.sist.sc.user.vo.SCULoginVO;
import kr.co.sist.sc.user.vo.SCUSignUpVO;

public class SCUSignUpController extends WindowAdapter implements ActionListener {

	private SCUSignUpView ssuv;
	private SCULoginDAO slDao;
	private boolean dupResult;

	public SCUSignUpController(SCUSignUpView ssuv) {
		this.ssuv = ssuv;
		slDao = SCULoginDAO.getInstance();
	}

	/**
	 * id �ߺ�Ȯ�� (���̾�α� â ���� UIó�� �����ϱ�)
	 * @param id
	 */
	public void checkDuplication(String id) {

		JTextField jtf = ssuv.getJtfID();
		String inputId = jtf.getText().trim();

		try {
			if (!slDao.selectCheckDup(id) && !inputId.equals("")) {// ���� ���̵� �������� ���� ��
				JOptionPane.showMessageDialog(ssuv, "��� ������ ���̵��Դϴ�.");
			} else if (inputId.equals("")) {
				JOptionPane.showMessageDialog(ssuv, "���̵� �Է��ϼ���.");
			} else { // ���� ���̵� ������ ��
				JOptionPane.showMessageDialog(ssuv, "���� ���̵� �����մϴ�.");
				jtf.setText("");
			} // end else
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ssuv, "��� �� �ٽ� �õ����ּ���.");
			e.printStackTrace();
		} // end catch

	}
	
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
	}

	/**
	 * ȸ������
	 * 1. �� â�� ���� �ִ��� Ȯ��
	 * 2. ��й�ȣ, ��й�ȣ Ȯ�� ��ġ�ϴ��� Ȯ���ϱ� (��� ���̵� ���ǰɱ�)
	 * 3. ...���̾�α� â�� ��.. :( (���� ��)
	 */
	public void addSignUp() {
		
		int cnt = 0;
		
		JTextField jtfId = ssuv.getJtfID();
		
		JPasswordField jpfPw = ssuv.getJpfPW();
		String stringPW = new String(jpfPw.getPassword());
		
		JPasswordField jpfConfirmPW = ssuv.getJpfConfirmPW();
		String stringconfirmPW = new String(ssuv.getJpfConfirmPW().getPassword());
		
		JTextField jtfName = ssuv.getJtfName();
		JTextField jtfBirthdate = ssuv.getJtfBirth();
		JTextField jtfPhone = ssuv.getJtfPhone();
		
		if (jtfId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(ssuv, "���̵�� �ʼ��Է»����Դϴ�.");
			return;
		}
		
		if(stringPW.isEmpty()) {
			JOptionPane.showMessageDialog(ssuv, "��й�ȣ�� �ʼ��Է»����Դϴ�.");
			return;
		}

		SCUSignUpVO ssuvo = new SCUSignUpVO(jtfId.getText().trim(), stringPW.trim(), jtfName.getText().trim(), jtfBirthdate.getText().trim(), jtfPhone.getText().trim());
		try {
			// ������ ���� catch�� ������ DB�� �߰��� �ȵ�
			SCULoginDAO.getInstance().insertSignUp(ssuvo);// ������ ���� �ʴ� ��� DB�� �߰�	
			
			// ���� ���ö��� �Է��� ���ϰ� �ϱ� ���ؼ� �Է� ���� �ʱ�ȭ
			jtfId.setText("");
			jpfPw.setText("");
			jtfName.setText("");
			jtfBirthdate.setText("");
			jtfPhone.setText("");
			
			JOptionPane.showMessageDialog(ssuv, "ȸ�������� �Ϸ�Ǿ����ϴ�.");
			jtfName.requestFocus();
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(ssuv, "DB���� ������ �߻��Ͽ����ϴ�.");
			se.printStackTrace();
		} // end catch
	}//addSignUp
	
	@Override
	public void windowClosing(WindowEvent we) {
		ssuv.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource().equals(ssuv.getJbtnCheckDuplication())) {
			checkDuplication(ssuv.getJtfID().getText());
		} // end JbtnCheckDuplication

		if(ae.getSource().equals(ssuv.getJbtnReset())) {
			reset();
		}
		
		if (ae.getSource().equals(ssuv.getJbtnSignUp())) {
			addSignUp();
		}
		
		if(ae.getSource().equals(ssuv.getJbtnExit())){
			ssuv.dispose();
		}
	}

}// class
