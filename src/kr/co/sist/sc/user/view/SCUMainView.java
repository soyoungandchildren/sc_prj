package kr.co.sist.sc.user.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import kr.co.sist.sc.user.controller.SCUMainController;

@SuppressWarnings("serial")
public class SCUMainView extends JFrame {

	private JLabel jlblImageBoard1, jlblImageBoard2, jlblImageBoard3, jlblBookingRank;
	private JButton jbtnLogin, jbtnBooking, jbtnSnack, jbtnRefund, jbtnMyPage;
	private boolean isLogin;
	private String idConnecting;
	

	public SCUMainView() {
		super("����");
		
		//������Ʈ ����
		jlblBookingRank = new JLabel("��ȭ ���� ��");
		jlblImageBoard1 = new JLabel();
		jlblImageBoard2 = new JLabel();
		jlblImageBoard3 = new JLabel();
		
		jbtnMyPage= new JButton("����������");
		jbtnLogin= new JButton("�α���/ȸ������");
		jbtnBooking= new JButton("����");
		jbtnSnack= new JButton("����");
		jbtnRefund= new JButton("ȯ��");
		
		
		//������Ʈ ����
		jlblImageBoard1.setBorder(new LineBorder(Color.RED));
		jlblImageBoard2.setBorder(new LineBorder(Color.RED));
		jlblImageBoard3.setBorder(new LineBorder(Color.RED));
		
		jlblBookingRank.setBorder(new LineBorder(Color.BLUE));

		
		//������Ʈ ��ġ
		setLayout(null);
		
		jlblBookingRank.setBounds(20, 30, 500, 20);
		jlblImageBoard1.setBounds(100, 130, 200, 300);
		jlblImageBoard2.setBounds(350, 130, 200, 300);
		jlblImageBoard3.setBounds(600, 130, 200, 300);
		
		jbtnLogin.setBounds(700, 20, 130, 40);
		jbtnBooking.setBounds(280, 500, 140, 60);
		jbtnSnack.setBounds(480, 500, 140, 60);
		jbtnRefund.setBounds(680, 500, 140, 60);
		jbtnMyPage.setBounds(80, 500, 140, 60);
		
		add(jlblBookingRank);
		add(jlblImageBoard1);
		add(jlblImageBoard2);
		add(jlblImageBoard3);
		
		add(jbtnMyPage);
		add(jbtnRefund);
		add(jbtnSnack);
		add(jbtnBooking);
		add(jbtnLogin);
		
		
		//�̺�Ʈ �߰� 
		SCUMainController smc = new SCUMainController(this);
		addWindowListener(smc);
		jbtnLogin.addActionListener(smc);
		jbtnBooking.addActionListener(smc);
		jbtnSnack.addActionListener(smc);
		jbtnRefund.addActionListener(smc);
		jbtnMyPage.addActionListener(smc);
		
		
		//â ����
		setResizable(false);
		setBounds(100, 100, 900, 700);
		setVisible(true);
		
	}//Constructor

	//Getters
	public JLabel getJlblImageBoard1() {
		return jlblImageBoard1;
	}
	public JLabel getJlblImageBoard2() {
		return jlblImageBoard2;
	}
	public JLabel getJlblImageBoard3() {
		return jlblImageBoard3;
	}
	public JLabel getJlblBookingRank() {
		return jlblBookingRank;
	}
	public JButton getJbtnLogin() {
		return jbtnLogin;
	}
	public JButton getJbtnBooking() {
		return jbtnBooking;
	}
	public JButton getJbtnSnack() {
		return jbtnSnack;
	}
	public JButton getJbtnRefund() {
		return jbtnRefund;
	}
	public JButton getJbtnMyPage() {
		return jbtnMyPage;
	}
	public boolean getIsLogin() {
		return isLogin;
	}
	public String getIdConnecting() {
		return idConnecting;
	}
	//end Getters
	//Setters
	public void setIsLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	public void setIdConnecting(String idConnecting) {
		this.idConnecting = idConnecting;
	}
	//end Setters
	
}//Class
