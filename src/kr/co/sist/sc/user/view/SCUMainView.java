package kr.co.sist.sc.user.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;

import kr.co.sist.sc.user.controller.SCUMainController;
import kr.co.sist.sc.user.util.CustomFontList;

@SuppressWarnings("serial")
public class SCUMainView extends JFrame {
	private JLabel jlblImageBoard1, jlblImageBoard2, jlblImageBoard3, jlbRank1, jlbRank2, jlbRank3,
					jlbRankAudience1, jlbRankAudience2, jlbRankAudience3,
					jlbRankRate1, jlbRankRate2, jlbRankRate3;
	private JButton jbtnLogin, jbtnBooking, jbtnSnack, jbtnRefund, jbtnMyPage;
	private boolean isLogin;
	private String idConnecting;	
	private JTextArea jtaBookingRank;

	@SuppressWarnings("static-access")
	public SCUMainView() {
		super("쌍용관");
		
		UIManager UI=new UIManager();
		UI.put("OptionPane.background",new ColorUIResource(20, 49, 94));
		UI.put("Panel.background",new ColorUIResource(20, 49, 94));
		UI.put("OptionPane.messageForeground", Color.WHITE);
		
		//컴포넌트 생성
		String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/";
		
		jtaBookingRank = new JTextArea();
		jlblImageBoard1 = new JLabel();
		jlblImageBoard2 = new JLabel();
		jlblImageBoard3 = new JLabel();
		
		jlbRank1 = new JLabel();
		jlbRank2 = new JLabel();
		jlbRank3 = new JLabel();
		
		jlbRankAudience1 = new JLabel();
		jlbRankAudience2 = new JLabel();
		jlbRankAudience3 = new JLabel();
		
		jlbRankRate1 = new JLabel();
		jlbRankRate2 = new JLabel();
		jlbRankRate3 = new JLabel();
		
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
		jtaBookingRank.setBorder(new LineBorder(Color.WHITE));
		jtaBookingRank.setForeground(Color.WHITE);
		jtaBookingRank.setFont(CustomFontList.getInstance().getFixedLengthFont());
		jtaBookingRank.setFocusable(false);
		
//		jlbRank1.setBorder(new LineBorder(Color.WHITE));
		jlbRank1.setFont(CustomFontList.getInstance().getFontRank());
		jlbRank1.setForeground(Color.WHITE);
		jlbRank1.setHorizontalAlignment(SwingConstants.CENTER);
		
		
//		jlbRank2.setBorder(new LineBorder(Color.WHITE));
		jlbRank2.setFont(CustomFontList.getInstance().getFontRank());
		jlbRank2.setForeground(Color.WHITE);
		jlbRank2.setHorizontalAlignment(SwingConstants.CENTER);
		
//		jlbRank3.setBorder(new LineBorder(Color.WHITE));
		jlbRank3.setFont(CustomFontList.getInstance().getFontRank());
		jlbRank3.setForeground(Color.WHITE);
		jlbRank3.setHorizontalAlignment(SwingConstants.CENTER);
		
//		jlbRankAudience1.setBorder(new LineBorder(Color.WHITE));
		jlbRankAudience1.setFont(CustomFontList.getInstance().getFontLabel());
		jlbRankAudience1.setForeground(Color.WHITE);
		jlbRankAudience1.setHorizontalAlignment(SwingConstants.CENTER);
		
//		jlbRankAudience2.setBorder(new LineBorder(Color.WHITE));
		jlbRankAudience2.setFont(CustomFontList.getInstance().getFontLabel());
		jlbRankAudience2.setForeground(Color.WHITE);
		jlbRankAudience2.setHorizontalAlignment(SwingConstants.CENTER);
		
//		jlbRankAudience3.setBorder(new LineBorder(Color.WHITE));
		jlbRankAudience3.setFont(CustomFontList.getInstance().getFontLabel());
		jlbRankAudience3.setForeground(Color.WHITE);
		jlbRankAudience3.setHorizontalAlignment(SwingConstants.CENTER);
		
//		jlbRankRate1.setBorder(new LineBorder(Color.WHITE));
		jlbRankRate1.setFont(CustomFontList.getInstance().getFontLabel());
		jlbRankRate1.setForeground(Color.WHITE);
		jlbRankRate1.setHorizontalAlignment(SwingConstants.CENTER);
		
//		jlbRankRate2.setBorder(new LineBorder(Color.WHITE));
		jlbRankRate2.setFont(CustomFontList.getInstance().getFontLabel());
		jlbRankRate2.setForeground(Color.WHITE);
		jlbRankRate2.setHorizontalAlignment(SwingConstants.CENTER);
		
//		jlbRankRate3.setBorder(new LineBorder(Color.WHITE));
		jlbRankRate3.setFont(CustomFontList.getInstance().getFontLabel());
		jlbRankRate3.setForeground(Color.WHITE);
		jlbRankRate3.setHorizontalAlignment(SwingConstants.CENTER);
		
		//컴포넌트 배치
		setLayout(null);
		
		jtaBookingRank.setBounds(120, 42, 605, 23);
		jlblImageBoard1.setBounds(116, 110, 237, 315);
		jlblImageBoard2.setBounds(376, 110, 237, 315);
		jlblImageBoard3.setBounds(636, 110, 237, 315);
		
		//순위
		jlbRank1.setBounds(116, 433, 237, 24);
		jlbRank2.setBounds(376, 433, 237, 24);
		jlbRank3.setBounds(636, 433, 237, 24);
		
		jlbRankAudience1.setBounds(116, 457, 237, 24);
		jlbRankAudience2.setBounds(376, 457, 237, 24);
		jlbRankAudience3.setBounds(636, 457, 237, 24);
		
		jlbRankRate1.setBounds(116, 481, 237, 24);
		jlbRankRate2.setBounds(376, 481, 237, 24);
		jlbRankRate3.setBounds(636, 481, 237, 24);
		
		jbtnLogin.setBounds(752, 34, 215, 40);
		jbtnBooking.setBounds(310, 545, 175, 175);
		jbtnSnack.setBounds(510, 545, 175, 175);
		jbtnRefund.setBounds(710, 545, 175, 175);
		jbtnMyPage.setBounds(110, 545, 175, 175);
		
		add(jtaBookingRank);
		add(jlblImageBoard1);
		add(jlblImageBoard2);
		add(jlblImageBoard3);
		
		add(jlbRank1);
		add(jlbRank2);
		add(jlbRank3);
		
		add(jlbRankAudience1);
		add(jlbRankAudience2);
		add(jlbRankAudience3);
		
		add(jlbRankRate1);
		add(jlbRankRate2);
		add(jlbRankRate3);
		
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
	public JLabel getJlbRankAudience1() {
		return jlbRankAudience1;
	}
	
	public JLabel getJlbRankAudience2() {
		return jlbRankAudience2;
	}
	
	public JLabel getJlbRankAudience3() {
		return jlbRankAudience3;
	}
	
	public JLabel getJlbRankRate1() {
		return jlbRankRate1;
	}
	
	public JLabel getJlbRankRate2() {
		return jlbRankRate2;
	}
	
	public JLabel getJlbRankRate3() {
		return jlbRankRate3;
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
