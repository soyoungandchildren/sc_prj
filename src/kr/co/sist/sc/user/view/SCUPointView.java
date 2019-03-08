package kr.co.sist.sc.user.view;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.co.sist.sc.user.controller.SCUPointController;
import kr.co.sist.sc.user.util.CustomFontList;

@SuppressWarnings("serial")
public class SCUPointView extends JDialog{
	private SCUMyPageView smpv;
	private JTextField jtfNowPoint;
	private JButton jbtnCharge, jbtnClose;
	private JComboBox<String> jcbPoint;
	
	public SCUPointView(SCUMyPageView smpv) {
		super(smpv,"포인트 충전",true);
		this.smpv = smpv;
		
		//컴포넌트 생성
		String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/";
		
		JLabel background = new JLabel(new ImageIcon(imgPath+"user_book_bg4(500x400).png"));
		JLabel jlbTitle = new JLabel("포인트 충전");
		JLabel jlbNowPoint = new JLabel("현재 포인트");
		JLabel jlbChoice = new JLabel("충전 금액");
		JLabel jlbNotice1 = new JLabel("※ 만 원 단위로 충전이 가능합니다.");
		JLabel jlbNotice2 = new JLabel("※ 한 번에 최대 10만 원 충전이 가능합니다.");
		
		jtfNowPoint = new JTextField();
		
		DefaultComboBoxModel<String> point = new DefaultComboBoxModel<String>();
		point.addElement(" ---금액 선택--- ");
		
		for(int i=1; i<11; i++) {
			point.addElement(String.valueOf(i+"0,000"));
		}//end for
		
		jcbPoint = new JComboBox<String>(point);
		
		jbtnCharge = new JButton(new ImageIcon(imgPath+"jbt_charging(90x40).png"));
		jbtnClose = new JButton(new ImageIcon(imgPath+"jbt_cancel(90x40).png"));
		
		//컴포넌트 설정
		jlbTitle.setForeground(Color.WHITE);
		jlbTitle.setFont(CustomFontList.getInstance().getFontTitle());
		jlbNowPoint.setForeground(Color.WHITE);
		jlbNowPoint.setFont(CustomFontList.getInstance().getFontLabel());
		jlbChoice.setForeground(Color.WHITE);
		jlbChoice.setFont(CustomFontList.getInstance().getFontLabel());
		jlbNotice1.setForeground(Color.WHITE);
		jlbNotice1.setFont(CustomFontList.getInstance().getFontNotice());
		jlbNotice2.setForeground(Color.WHITE);
		jlbNotice2.setFont(CustomFontList.getInstance().getFontNotice());
		jtfNowPoint.setEditable(false);
		
		jtfNowPoint.setOpaque(false);
		jtfNowPoint.setFont(CustomFontList.getInstance().getFontLabel());
		jtfNowPoint.setForeground(Color.WHITE);
		
		
		jcbPoint.setFont(CustomFontList.getInstance().getFontLabel());
		jcbPoint.setBackground(new Color(20,34,65));
		jcbPoint.setForeground(Color.WHITE);
		
		
		
		//버튼 테두리 없애기
		jbtnCharge.setContentAreaFilled(false);
		jbtnCharge.setBorderPainted(false);
		jbtnClose.setContentAreaFilled(false);
		jbtnClose.setBorderPainted(false);
		
		String holdPoint = smpv.getJtfHoldPoint().getText();
		String memberId = smpv.getJtfMemberID().getText();
		
		add(jlbTitle);
		add(jlbNowPoint);
		add(jtfNowPoint);
		add(jlbChoice);
		add(jcbPoint);
		add(jlbNotice1);
		add(jlbNotice2);
		add(jbtnCharge);
		add(jbtnClose);
		add(background);
		
		//배치
		setLayout(null);
		
		background.setBounds(0, 0, 300, 400);
		jlbTitle.setBounds(25,20,250,40);
		jlbNowPoint.setBounds(25,60,80,50);
		jtfNowPoint.setBounds(117,71,130,25);
		jlbChoice.setBounds(25,100,100,50);
		jcbPoint.setBounds(117,111,130,25);
		jlbNotice1.setBounds(25,145,200,25);
		jlbNotice2.setBounds(25,165,230,25);
		jbtnCharge.setBounds(48,210,90,40);
		jbtnClose.setBounds(148,210,90,40);
		
		//이벤트
		SCUPointController spc = new SCUPointController(this, smpv, holdPoint, memberId);
		addWindowListener(spc);
		jbtnCharge.addActionListener(spc);
		jbtnClose.addActionListener(spc);
		
		//가시화
		setBounds(smpv.getX()+450, smpv.getY()+60, 290, 310);
		setResizable(false);
		setVisible(true);
	}//SCUPointView

	public SCUMyPageView getSmpv() {
		return smpv;
	}
	public JTextField getJtfNowPoint() {
		return jtfNowPoint;
	}
	public JButton getJbtnCharge() {
		return jbtnCharge;
	}
	public JButton getJbtnClose() {
		return jbtnClose;
	}
	public JComboBox<String> getJcbPoint() {
		return jcbPoint;
	}
}//class
