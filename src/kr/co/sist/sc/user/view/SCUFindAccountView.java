package kr.co.sist.sc.user.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.co.sist.sc.user.controller.SCUFindAccountController;
import kr.co.sist.sc.user.images.CustomFontList;

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
		jbtnFindID = new JButton(new ImageIcon(imgPath+"jbt_find_id(220x27).png"));

		// �Ʒ� ������ ���ã��
		JLabel jlIDForPW = new JLabel("���̵�");
		JLabel jlNameForPW = new JLabel("�̸�");
		JLabel jlPhoneForPW = new JLabel("��ȭ��ȣ");
		jtfIDForPW = new JTextField();
		jtfNameForPW = new JTextField();
		jtfPhoneForPW = new JTextField();
		jbtnFindPW = new JButton(new ImageIcon(imgPath+"jbt_find_passwd(220x27).png"));

		setLayout(null);

		jlPhoneForID.setBounds(25, 50, 100, 30);
		jlPhoneForID.setForeground(Color.white);
		jlPhoneForID.setFont(CustomFontList.getInstance().getFontLabel());
		jtfPhoneForID.setBounds(85, 50, 180, 30);
		
		jbtnFindID.setBounds(35, 100, 220, 27);
		jbtnFindID.setContentAreaFilled(false);
		jbtnFindID.setBorderPainted(false);


		jlIDForPW.setBounds(25, 200, 100, 30);
		jlIDForPW.setForeground(Color.white);
		jlIDForPW.setFont(CustomFontList.getInstance().getFontLabel());
		jtfIDForPW.setBounds(85, 200, 180, 30);
	
		jlNameForPW.setBounds(25, 240, 100, 30);
		jlNameForPW.setForeground(Color.white);
		jlNameForPW.setFont(CustomFontList.getInstance().getFontLabel());
		jtfNameForPW.setBounds(85, 240, 180, 30);
		
		jlPhoneForPW.setBounds(25, 280, 100, 30);
		jlPhoneForPW.setForeground(Color.white);
		jlPhoneForPW.setFont(CustomFontList.getInstance().getFontLabel());
		jtfPhoneForPW.setBounds(85, 280, 180, 30);
		
		jbtnFindPW.setBounds(35, 330, 220, 27);
		//��ư �׵θ� ���ֱ�
		jbtnFindPW.setContentAreaFilled(false);
		jbtnFindPW.setBorderPainted(false);
				
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
		JLabel background = new JLabel(new ImageIcon(imgPath + "user_login_bg2(300x500).png"));
		background.setBounds(0, 0, 300, 500);
		add(background);
		
		setResizable(false);
		setBounds(slv.getX(), slv.getY(), 300, 500); // �ϼ� �Ŀ� â �ߴ� ��ġ �ణ �����ϱ�
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
