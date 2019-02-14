package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.sc.user.model.SCULoginDAO;
import kr.co.sist.sc.user.view.SCUFindAccountView;
import kr.co.sist.sc.user.view.SCULoginView;
import kr.co.sist.sc.user.view.SCUMainView;
import kr.co.sist.sc.user.view.SCUSignUpView;
import kr.co.sist.sc.user.vo.SCULoginVO;

public class SCULoginController extends WindowAdapter implements ActionListener {
	private SCULoginView slv;
	private SCUMainView smv;

	public SCULoginController(SCULoginView slv, SCUMainView smv) {
		this.slv = slv;
		this.smv = smv;
	}

	@Override
	public void windowClosing(WindowEvent we) {
		slv.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == slv.getJbtnLogin()) {

			if (!checkIdEmpty() && !checkPassEmpty()) {
				JTextField jtf = slv.getJtfID();
				JPasswordField jpf = slv.getJpfPW();

				String id = jtf.getText().trim();
				String pass = new String(jpf.getPassword());

				SCULoginVO slvo = new SCULoginVO(id, pass);
				String idConnect = login(slvo);

				if (idConnect.equals("")) { // �α����� �� �Ǿ��� ��
					JOptionPane.showMessageDialog(slv, "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�. ");
					jtf.setText("");
					jpf.setText("");
					jtf.requestFocus();
				} else { // �α����� �Ǿ��� ��
					// new SCUMainView(name);
					// smv.dispose();//���α׷��� �����ϰ� �α����� ����� �ؼ� �α��� ��.
					slv.dispose();
					smv.setIsLogin(true);
					smv.setIdConnecting(idConnect);
				} // end if

			} // end if

		} // jbtnLogin

		// ����
		if (ae.getSource() == slv.getJbtnSignUp()) {
			new SCUSignUpView(slv);
			slv.dispose();
		} // jbtnSignup

		if (ae.getSource() == slv.getJbtnFindAccount()) {
			new SCUFindAccountView(slv);
		}
	}

	private boolean checkIdEmpty() {
		boolean flag = false;
		JTextField jtfID = slv.getJtfID();
		if (jtfID.getText().trim().equals("")) {
			jtfID.setText("");
			jtfID.requestFocus();
			flag = true;
		} // end if
		return flag;
	}// checkIdEmpty

	private boolean checkPassEmpty() {
		boolean flag = false;
		JPasswordField jpfPW = slv.getJpfPW();
		String PW = new String(jpfPW.getPassword());
		if (PW.isEmpty()) {
			jpfPW.setText("");
			jpfPW.requestFocus();
			flag = true;
		} // end if
		return flag;
	}// checkPassEmpty

	private String login(SCULoginVO slvo) {

		String name = "";
		SCULoginDAO slDao = SCULoginDAO.getInstance();
		try {
			name = slDao.selectLogin(slvo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(slv, "������ �����߻�");
			e.printStackTrace();
		}
		return name;
	}// end login

}
