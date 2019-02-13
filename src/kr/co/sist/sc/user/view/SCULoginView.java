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
		jbtnLogin = new JButton("로그인");
		jbtnSignUp = new JButton("회원가입");

		JLabel jlId = new JLabel("로그인");
		JLabel jlPass = new JLabel("비밀번호");

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
		
		//이벤트 등록
		//2019-02-11 (이지수): 로그인 한 후, 로그인 이전 화면창 닫기 위해서 smv(mainView) 매개변수로 받음.
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
