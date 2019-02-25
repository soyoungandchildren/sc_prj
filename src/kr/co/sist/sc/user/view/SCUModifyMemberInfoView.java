package kr.co.sist.sc.user.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SCUModifyMemberInfoView extends JDialog{
	
	private JTextField jtfName, jtfPhone;
	private JButton jbtnConfirm, jbtnExit;
	private SCUMyPageView smpv;
	
	public SCUModifyMemberInfoView(SCUMyPageView smpv) {
		super(smpv, "�������� ����", true);
		this.smpv = smpv;
		
		jtfName = new JTextField(15);
		jtfPhone = new JTextField(15);
		jbtnConfirm = new JButton("Ȯ��");
		jbtnExit = new JButton("���");
		
		JLabel jlblName = new JLabel("�̸� : ");
		JLabel jlblPhone = new JLabel("��ȭ��ȣ : ");
		
		setLayout(null);
		
		jlblName.setBounds(10, 50, 100, 40);
		jlblPhone.setBounds(10, 100, 100, 40);
		jtfName.setBounds(150, 50, 100, 40);
		jtfPhone.setBounds(150, 100, 100, 40);
		jbtnConfirm.setBounds(125, 300, 70, 50);
		jbtnExit.setBounds(205, 300, 70, 50);
		
		
		add(jlblName);
		add(jlblPhone);
		add(jtfName);
		add(jtfPhone);
		add(jbtnConfirm);
		add(jbtnExit);
		
		
		
		
		setResizable(false);
		setBounds(smpv.getX()+150, smpv.getY()+150, 400, 400);
		setVisible(true);
		
		
		
	}//Constructor

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfPhone() {
		return jtfPhone;
	}

	public JButton getJbtnConfirm() {
		return jbtnConfirm;
	}

	public JButton getJbtnExit() {
		return jbtnExit;
	}

	public SCUMyPageView getSmpv() {
		return smpv;
	}
	
	
	
}
