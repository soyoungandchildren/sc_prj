package kr.co.sist.sc.user.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.sc.user.controller.SCULoginController;

@SuppressWarnings("serial")
public class SCULoginView extends JDialog {

	private JTextField jtfID;
	private JPasswordField jpfPW;
	private JButton jbtnLogin, jbtnSignUp, jbtnFindAccount;

	public SCULoginView(SCUMainView smv) {

		super(smv, "�α���", true);
		// ������Ʈ ����
		String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/";

		jtfID = new JTextField();
		jpfPW = new JPasswordField();
		jbtnLogin = new JButton("�α���");
		jbtnSignUp = new JButton("ȸ������");
		jbtnFindAccount = new JButton("���̵�/��й�ȣ ã��");

		JLabel jlId = new JLabel("�α���");
		JLabel jlPass = new JLabel("��й�ȣ");

		setLayout(null);

		jlId.setBounds(20, 50, 100, 30);
		jtfID.setBounds(80, 50, 180, 30);

		jlPass.setBounds(20, 90, 100, 30);
		jpfPW.setBounds(80, 90, 180, 30);

		jbtnFindAccount.setBounds(40, 130, 200, 30);
		jbtnLogin.setBounds(20, 260, 110, 40);
		jbtnSignUp.setBounds(150, 260, 110, 40);

		add(jlId);
		add(jtfID);
		add(jbtnFindAccount);

		add(jlPass);
		add(jpfPW);
		add(jbtnLogin);
		add(jbtnSignUp);

		// �̺�Ʈ ���
		SCULoginController slc = new SCULoginController(this, smv);
		addWindowListener(slc);
		jtfID.addActionListener(slc);
		jbtnFindAccount.addActionListener(slc);
		jbtnLogin.addActionListener(slc);
		jbtnSignUp.addActionListener(slc);
		jpfPW.addKeyListener(slc);

		// â ����
		JLabel background = new JLabel(new ImageIcon(imgPath + "user_snackcorner_bg1(900x800).png"));
		background.setBounds(0, 0, 900, 800);
		add(background);

		setBounds(100, 100, 300, 400);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public JButton getJbtnFindAccount() {
		return jbtnFindAccount;
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
