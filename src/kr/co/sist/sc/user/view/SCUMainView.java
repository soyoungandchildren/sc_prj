package kr.co.sist.sc.user.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import kr.co.sist.sc.user.controller.SCUMainController;
import kr.co.sist.sc.user.images.CustomFontList;

@SuppressWarnings("serial")
public class SCUMainView extends JFrame {
	private JLabel jlblImageBoard1, jlblImageBoard2, jlblImageBoard3, jlbRank1, jlbRank2, jlbRank3;
	private JButton jbtnLogin, jbtnBooking, jbtnSnack, jbtnRefund, jbtnMyPage;
	private boolean isLogin;
	private String idConnecting;	
	private JTextArea jtaBookingRank;

	public SCUMainView() {
		super("메인");
		
		//컴포넌트 생성
		String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/";
		
		jtaBookingRank = new JTextArea();
		jlblImageBoard1 = new JLabel();
		jlblImageBoard2 = new JLabel();
		jlblImageBoard3 = new JLabel();
		
		jlbRank1 = new JLabel();
		jlbRank2 = new JLabel();
		jlbRank3 = new JLabel();
		
		jbtnMyPage= new JButton(new ImageIcon(imgPath+"jbt_mypage(175x175).png"));
		jbtnLogin= new JButton(new ImageIcon(imgPath+"jbt_login_join(215x40).png"));
		jbtnBooking= new JButton(new ImageIcon(imgPath+"jbt_ticketing(175x175).png"));
		jbtnSnack= new JButton(new ImageIcon(imgPath+"jbt_buy_the_snack(175x175).png"));
		jbtnRefund= new JButton(new ImageIcon(imgPath+"jbt_cancel_payment(175x175).png"));
		
		//버튼 테두리 없애기
		jbtnLogin.setContentAreaFilled(false);
		jbtnLogin.setBorderPainted(false);
		jbtnMyPage.setContentAreaFilled(false);
		jbtnMyPage.setBorderPainted(false);
		jbtnBooking.setContentAreaFilled(false);
		jbtnBooking.setBorderPainted(false);
		jbtnSnack.setContentAreaFilled(false);
		jbtnSnack.setBorderPainted(false);
		jbtnRefund.setContentAreaFilled(false);
		jbtnRefund.setBorderPainted(false);
		
		//랭킹 레일
		jtaBookingRank.setOpaque(false);
		jtaBookingRank.setEditable(false);
		
		//컴포넌트 설정
		jlblImageBoard1.setBorder(new LineBorder(Color.WHITE));
		jlblImageBoard2.setBorder(new LineBorder(Color.WHITE));
		jlblImageBoard3.setBorder(new LineBorder(Color.WHITE));
		
		jtaBookingRank.setBorder(new LineBorder(Color.WHITE));
		jtaBookingRank.setForeground(Color.WHITE);
		jtaBookingRank.setFont(CustomFontList.getInstance().getFixedLengthFont());
		jtaBookingRank.setFocusable(false);
		
		jlbRank1.setBorder(new LineBorder(Color.WHITE));
		jlbRank1.setFont(CustomFontList.getInstance().getFontLabel());
		jlbRank1.setForeground(Color.WHITE);
		jlbRank1.setHorizontalAlignment(SwingConstants.CENTER);
		
		jlbRank2.setBorder(new LineBorder(Color.WHITE));
		jlbRank2.setFont(CustomFontList.getInstance().getFontLabel());
		jlbRank2.setForeground(Color.WHITE);
		jlbRank2.setHorizontalAlignment(SwingConstants.CENTER);
		
		jlbRank3.setBorder(new LineBorder(Color.WHITE));
		jlbRank3.setFont(CustomFontList.getInstance().getFontLabel());
		jlbRank3.setForeground(Color.WHITE);
		jlbRank3.setHorizontalAlignment(SwingConstants.CENTER);
		
		//컴포넌트 배치
		setLayout(null);
		
		jtaBookingRank.setBounds(120, 42, 605, 23);
		jlblImageBoard1.setBounds(116, 100, 237, 315);
		jlblImageBoard2.setBounds(376, 100, 237, 315);
		jlblImageBoard3.setBounds(636, 100, 237, 315);
		
		//순위
		jlbRank1.setBounds(116, 440, 237, 90);
		jlbRank2.setBounds(376, 440, 237, 90);
		jlbRank3.setBounds(636, 440, 237, 90);
		
		jbtnLogin.setBounds(752, 34, 215, 40);
		jbtnBooking.setBounds(310, 550, 175, 175);
		jbtnSnack.setBounds(510, 550, 175, 175);
		jbtnRefund.setBounds(710, 550, 175, 175);
		jbtnMyPage.setBounds(110, 550, 175, 175);
		
		add(jtaBookingRank);
		add(jlblImageBoard1);
		add(jlblImageBoard2);
		add(jlblImageBoard3);
		
		add(jlbRank1);
		add(jlbRank2);
		add(jlbRank3);
		
		add(jbtnMyPage);
		add(jbtnRefund);
		add(jbtnSnack);
		add(jbtnBooking);
		add(jbtnLogin);
		
		//창 설정
		JLabel background = new JLabel(new ImageIcon(imgPath+"user_main_bg(1000x800).png"));
		background.setBounds(0, 0, 1000, 800);
		add(background);
		
		//이벤트 추가 
		SCUMainController smc = new SCUMainController(this);
		addWindowListener(smc);
		jbtnLogin.addActionListener(smc);
		jbtnBooking.addActionListener(smc);
		jbtnSnack.addActionListener(smc);
		jbtnRefund.addActionListener(smc);
		jbtnMyPage.addActionListener(smc);
		
		//창 설정
		setResizable(false);
		setBounds(100, 100, 1000, 800);
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
	public JTextArea getJtaBookingRank() {
		return jtaBookingRank;
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
	public JLabel getJlbRank1() {
		return jlbRank1;
	}
	public JLabel getJlbRank2() {
		return jlbRank2;
	}
	public JLabel getJlbRank3() {
		return jlbRank3;
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
