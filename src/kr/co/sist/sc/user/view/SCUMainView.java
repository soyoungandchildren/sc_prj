package kr.co.sist.sc.user.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import kr.co.sist.sc.user.controller.SCUMainController;

@SuppressWarnings("serial")
public class SCUMainView extends JFrame {

	private JLabel jlblImageBoard1, jlblImageBoard2, jlblImageBoard3, jlblBookingRank, 
		jlblMovieName1, jlblAudience1, jlblAudienceCnt1, jlblReserve1, jlblReserveRate1,
		jlblMovieName2, jlblAudience2, jlblAudienceCnt2, jlblReserve2, jlblReserveRate2,
		jlblMovieName3, jlblAudience3, jlblAudienceCnt3, jlblReserve3, jlblReserveRate3;
	private JButton jbtnLogin, jbtnBooking, jbtnSnack, jbtnRefund, jbtnMyPage;
	private boolean isLogin;
	private String idConnecting;	
	

	public SCUMainView() {
		super("메인");
		
		//컴포넌트 생성
		jlblBookingRank = new JLabel();
		jlblImageBoard1 = new JLabel();
		jlblImageBoard2 = new JLabel();
		jlblImageBoard3 = new JLabel();
		
		jlblMovieName1 = new JLabel();
		jlblAudience1 = new JLabel("누적 관객 수 : ");
		jlblAudienceCnt1 = new JLabel();
		jlblReserve1 = new JLabel("예매율 ");
		jlblReserveRate1 = new JLabel();
		
		jlblMovieName2 = new JLabel();
		jlblAudience2 = new JLabel("누적 관객 수 : ");
		jlblAudienceCnt2 = new JLabel();
		jlblReserve2 = new JLabel("예매율 ");
		jlblReserveRate2 = new JLabel();
		
		jlblMovieName3 = new JLabel();
		jlblAudience3 = new JLabel("누적 관객 수 : ");
		jlblAudienceCnt3 = new JLabel();
		jlblReserve3 = new JLabel("예매율 ");
		jlblReserveRate3 = new JLabel();
		
		jbtnMyPage= new JButton("마이페이지");
		jbtnLogin= new JButton("로그인/회원가입");
		jbtnBooking= new JButton("예매");
		jbtnSnack= new JButton("스낵");
		jbtnRefund= new JButton("환불");
		
		
		//컴포넌트 설정
		jlblImageBoard1.setBorder(new LineBorder(Color.RED));
		jlblImageBoard2.setBorder(new LineBorder(Color.RED));
		jlblImageBoard3.setBorder(new LineBorder(Color.RED));
		
		jlblBookingRank.setBorder(new LineBorder(Color.BLUE));
		
		//컴포넌트 배치
		setLayout(null);
		
		jlblBookingRank.setBounds(20, 22, 660, 30);
		jlblImageBoard1.setBounds(100, 120, 200, 300);
		jlblImageBoard2.setBounds(350, 120, 200, 300);
		jlblImageBoard3.setBounds(600, 120, 200, 300);
		
		//1위
		jlblMovieName1.setBounds(150, 430, 100, 30);
		jlblAudience1.setBounds(120, 450, 80, 30);
		jlblAudienceCnt1.setBounds(230, 450, 100, 30);
		jlblReserve1.setBounds(130, 470, 50, 30);
		jlblReserveRate1.setBounds(180, 470, 50, 30);
		
		//2위
		jlblMovieName2.setBounds(400, 430, 100, 30);
		jlblAudience2.setBounds(370, 450, 80, 30);
		jlblAudienceCnt2.setBounds(480, 450, 100, 30);
		jlblReserve2.setBounds(380, 470, 50, 30);
		jlblReserveRate2.setBounds(430, 470, 50, 30);
		
		//3위
		jlblMovieName3.setBounds(650, 430, 100, 30);
		jlblAudience3.setBounds(620, 450, 80, 30);
		jlblAudienceCnt3.setBounds(730, 450, 100, 30);
		jlblReserve3.setBounds(630, 470, 50, 30);
		jlblReserveRate3.setBounds(680, 470, 50, 30);
		
		jbtnLogin.setBounds(700, 20, 130, 40);
		jbtnBooking.setBounds(280, 550, 140, 60);
		jbtnSnack.setBounds(480, 550, 140, 60);
		jbtnRefund.setBounds(680, 550, 140, 60);
		jbtnMyPage.setBounds(80, 550, 140, 60);
		
		add(jlblBookingRank);
		add(jlblImageBoard1);
		add(jlblImageBoard2);
		add(jlblImageBoard3);
		
		//1위
		add(jlblMovieName1);
		add(jlblAudience1);
		add(jlblAudienceCnt1);
		add(jlblReserve1);
		add(jlblReserveRate1);
		
		//2위
		add(jlblMovieName2);
		add(jlblAudience2);
		add(jlblAudienceCnt2);
		add(jlblReserve2);
		add(jlblReserveRate2);
		
		//3위
		add(jlblMovieName3);
		add(jlblAudience3);
		add(jlblAudienceCnt3);
		add(jlblReserve3);
		add(jlblReserveRate3);
		
		add(jbtnMyPage);
		add(jbtnRefund);
		add(jbtnSnack);
		add(jbtnBooking);
		add(jbtnLogin);
		
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
	public JLabel getJlblMovieName1() {
		return jlblMovieName1;
	}
	public JLabel getJlblAudience1() {
		return jlblAudience1;
	}
	public JLabel getJlblAudienceCnt1() {
		return jlblAudienceCnt1;
	}
	public JLabel getJlblReserve1() {
		return jlblReserve1;
	}
	public JLabel getJlblReserveRate1() {
		return jlblReserveRate1;
	}
	public JLabel getJlblMovieName2() {
		return jlblMovieName2;
	}
	public JLabel getJlblAudience2() {
		return jlblAudience2;
	}
	public JLabel getJlblAudienceCnt2() {
		return jlblAudienceCnt2;
	}
	public JLabel getJlblReserve2() {
		return jlblReserve2;
	}
	public JLabel getJlblReserveRate2() {
		return jlblReserveRate2;
	}
	public JLabel getJlblMovieName3() {
		return jlblMovieName3;
	}
	public JLabel getJlblAudience3() {
		return jlblAudience3;
	}
	public JLabel getJlblAudienceCnt3() {
		return jlblAudienceCnt3;
	}
	public JLabel getJlblReserve3() {
		return jlblReserve3;
	}
	public JLabel getJlblReserveRate3() {
		return jlblReserveRate3;
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
