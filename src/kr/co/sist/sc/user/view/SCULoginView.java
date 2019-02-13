package kr.co.sist.sc.user.view;

import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.sc.user.controller.SCULoginController;

@SuppressWarnings("serial")
public class SCULoginView extends JFrame {

	private SCUMainView smv;
	private JTextField jtfID;
	private JPasswordField jpfPW;
	private JButton jbtnLogin, jbtnSignUp;

	public SCULoginView(SCUMainView smv) {

		this.smv = smv;

		jtfID = new JTextField();
		jpfPW = new JPasswordField();
		jbtnLogin = new JButton("�α���");
		jbtnSignUp = new JButton("ȸ������");

		JLabel jlId = new JLabel("�α���");
		JLabel jlPass = new JLabel("��й�ȣ");

		setLayout(null);

		jlId.setBounds(20, 50, 100, 30);
		jtfID.setBounds(80, 50, 180, 30);
		jlPass.setBounds(20, 90, 100, 30);
		jpfPW.setBounds(80, 90, 180, 30);
		jbtnLogin.setBounds(30, 130, 110, 40);
		jbtnSignUp.setBounds(150, 130, 110, 40);

		add(jlId);
		add(jtfID);
		add(jlPass);
		add(jpfPW);
		add(jbtnLogin);
		add(jbtnSignUp);
		
		//�̺�Ʈ ���
		//2019-02-11 (������): �α��� �� ��, �α��� ���� ȭ��â �ݱ� ���ؼ� smv(mainView) �Ű������� ����.
		SCULoginController slc = new SCULoginController(this, smv);
		addWindowListener(slc);
		jbtnLogin.addActionListener(slc);
		jbtnSignUp.addActionListener(slc);

		setVisible(true);
		setBounds(100, 100, 300, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public JTextField getJtfID() {
		return jtfID;
	}

	public JPasswordField getJpfPW() {
		return jpfPW;
	}

	public JButton getJbtnLogin() {
		return jbtnLogin;
	}

	public JButton getJbtnSignUp() {
		return jbtnSignUp;
	}

}