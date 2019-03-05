package kr.co.sist.sc.user.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.co.sist.sc.user.controller.SCUFindAccountController;

@SuppressWarnings("serial")
public class SCUFindAccountView extends JDialog {

	private JTextField jtfPhoneForID, jtfNameForPW, jtfIDForPW, jtfPhoneForPW;
	private JButton jbtnFindID, jbtnFindPW;

	public SCUFindAccountView(SCULoginView slv) {

		super(slv, "���̵�/��й�ȣ ã��", true);
		// ������Ʈ ����
		String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/";

		// ��ȭ��ȣ�� ���̵�ã��
		JLabel jlPhoneForID = new JLabel("��ȭ��ȣ");
		jtfPhoneForID = new JTextField();
		jbtnFindID = new JButton("���̵� ã��");

		// �Ʒ� ������ ���ã��
		JLabel jlIDForPW = new JLabel("���̵�");
		JLabel jlNameForPW = new JLabel("�̸�");
		JLabel jlPhoneForPW = new JLabel("��ȭ��ȣ");
		jtfIDForPW = new JTextField();
		jtfNameForPW = new JTextField();
		jtfPhoneForPW = new JTextField();
		jbtnFindPW = new JButton("��й�ȣ ã��");

		setLayout(null);

		jlPhoneForID.setBounds(20, 30, 100, 30);
		jtfPhoneForID.setBounds(80, 30, 180, 30);
		jbtnFindID.setBounds(70, 80, 140, 30);

		jlIDForPW.setBounds(20, 180, 100, 30);
		jlNameForPW.setBounds(20, 220, 100, 30);
		jlPhoneForPW.setBounds(20, 260, 100, 30);

		jtfIDForPW.setBounds(80, 180, 180, 30);
		jtfNameForPW.setBounds(80, 220, 180, 30);
		jtfPhoneForPW.setBounds(80, 260, 180, 30);

		jbtnFindPW.setBounds(70, 310, 140, 30);

		add(jlPhoneForID);
		add(jlIDForPW);
		add(jlNameForPW);
		add(jlPhoneForPW);

		add(jtfPhoneForID);
		add(jtfIDForPW);
		add(jtfNameForPW);
		add(jtfPhoneForPW);

		add(jbtnFindID);
		add(jbtnFindPW);

		// �̺�Ʈ ���
		SCUFindAccountController sfac = new SCUFindAccountController(this, slv);
		addWindowListener(sfac);
		jbtnFindID.addActionListener(sfac);
		jbtnFindPW.addActionListener(sfac);

		// â ����
		JLabel background = new JLabel(new ImageIcon(imgPath + "user_snackcorner_bg1(900x800).png"));
		background.setBounds(0, 0, 900, 800);
		add(background);
		
		setResizable(false);
		setBounds(105, 140, 290, 450); // �ϼ� �Ŀ� â �ߴ� ��ġ �ణ �����ϱ�
		setVisible(true);
	}

	public JTextField getJtfPhoneForID() {
		return jtfPhoneForID;
	}

	public JTextField getJtfNameForPW() {
		return jtfNameForPW;
	}

	public JTextField getJtfIDForPW() {
		return jtfIDForPW;
	}

	public JTextField getJtfPhoneForPW() {
		return jtfPhoneForPW;
	}

	public JButton getJbtnFindID() {
		return jbtnFindID;
	}

	public JButton getJbtnFindPW() {
		return jbtnFindPW;
	}

}// class
