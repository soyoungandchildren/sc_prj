package kr.co.sist.sc.user.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.sc.user.controller.SCUSignUpController;

@SuppressWarnings("serial")
public class SCUSignUpView extends JDialog {
	
	private JButton jbtnCheckDuplication, jbtnReset, jbtnSignUp, jbtnExit;
	private JTextField jtfID, jtfName, jtfBirth, jtfPhone;
	private JPasswordField jpfPW, jpfConfirmPW;
	
	public SCUSignUpView(SCULoginView slv) {
		
		super(slv,"ȸ������",true);
		
		jbtnCheckDuplication = new JButton("�ߺ�Ȯ��");
		jbtnReset = new JButton("���ۼ�");
		jbtnSignUp = new JButton("����");
		jbtnExit = new JButton("���");

		jtfID = new JTextField();
		jtfName = new JTextField();
		jtfBirth = new JTextField();
		jtfPhone = new JTextField();

		jpfPW = new JPasswordField();
		jpfConfirmPW = new JPasswordField();

		JLabel jlID = new JLabel("���̵�");
		JLabel jlPW = new JLabel("��й�ȣ");
		JLabel jlConfirmPW = new JLabel("��й�ȣ Ȯ��");
		JLabel jlName = new JLabel("�̸�");
		JLabel jlBirth = new JLabel("�������");
		JLabel jlPhone = new JLabel("�޴��� ��ȣ");

		setLayout(null);

		jlID.setBounds(20, 80, 100, 30);
		jlPW.setBounds(20, 120, 100, 30);
		jlConfirmPW.setBounds(20, 160, 100, 30);
		jlName.setBounds(20, 200, 100, 30);
		jlBirth.setBounds(20, 240, 100, 30);
		jlPhone.setBounds(20, 280, 100, 30);

		jpfConfirmPW.setBounds(110, 160, 180, 30);
		jpfPW.setBounds(110, 120, 180, 30);

		jtfID.setBounds(110, 80, 180, 30);
		jtfName.setBounds(110, 200, 180, 30);
		jtfBirth.setBounds(110, 240, 180, 30);
		jtfPhone.setBounds(110, 280, 180, 30);

		jbtnCheckDuplication.setBounds(300, 80, 90, 30);
		jbtnReset.setBounds(120, 350, 80, 30);
		jbtnSignUp.setBounds(210, 350, 80, 30);
		jbtnExit.setBounds(300, 350, 80, 30);

		add(jlID);
		add(jlPW);
		add(jlConfirmPW);
		add(jlName);
		add(jlBirth);
		add(jlPhone);

		add(jtfID);
		add(jtfName);
		add(jtfBirth);
		add(jtfPhone);

		add(jpfPW);
		add(jpfConfirmPW);

		add(jbtnCheckDuplication);
		add(jbtnReset);
		add(jbtnSignUp);
		add(jbtnExit);

		// �̺�Ʈ ���
		SCUSignUpController ssuc = new SCUSignUpController(this);
		addWindowListener(ssuc);
		
		jbtnCheckDuplication.addActionListener(ssuc);
		jbtnReset.addActionListener(ssuc);
		jbtnSignUp.addActionListener(ssuc);
		jbtnExit.addActionListener(ssuc);
		jtfID.addKeyListener(ssuc);

		setBounds(100, 100, 420, 500);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public JButton getJbtnCheckDuplication() {
		return jbtnCheckDuplication;
	}

	public JButton getJbtnReset() {
		return jbtnReset;
	}

	public JButton getJbtnSignUp() {
		return jbtnSignUp;
	}

	public JButton getJbtnExit() {
		return jbtnExit;
	}

	public JTextField getJtfID() {
		return jtfID;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfBirth() {
		return jtfBirth;
	}

	public JTextField getJtfPhone() {
		return jtfPhone;
	}

	public JPasswordField getJpfPW() {
		return jpfPW;
	}

	public JPasswordField getJpfConfirmPW() {
		return jpfConfirmPW;
	}

}// class
