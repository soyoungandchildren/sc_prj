package kr.co.sist.sc.user.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kr.co.sist.sc.user.controller.SCUMyPageController;
import kr.co.sist.sc.user.util.CustomFontList;

@SuppressWarnings("serial")
public class SCUMyPageView extends JDialog{

	private SCUMainView smv;
	private JTextField jtfMemberID, jtfName, jtfBirthdate, jtfPhone,
						jtfMembership, jtfHoldPoint, jtfAccPoint, jtfInputDate;
	private JButton jbtnUpdateInfo, jbtnUpdatePW, jbtnPointUpdate, jbtnResign, jbtnExit;
	
	public SCUMyPageView(SCUMainView smv) {
		super(smv,"마이 페이지",true);
		this.smv = smv;
		
		JLabel jlblTitle = new JLabel("회원 정보");
		jtfMemberID = new JTextField(10);
		jtfName = new JTextField(10);
		jtfBirthdate = new JTextField(10);
		jtfPhone = new JTextField(10);
		jtfMembership = new JTextField(10);
		jtfHoldPoint = new JTextField(10);
		jtfAccPoint = new JTextField(10);
		jtfInputDate = new JTextField(10);
		jbtnUpdateInfo = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_chg_inform(100x30).png"));
		jbtnUpdatePW = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_chg_passwd(100x30).png"));
		jbtnPointUpdate = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_chargin_point(252x30).png"));
		jbtnResign = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_leave_member(100x30).png"));
		jbtnExit = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_close(100x30).png"));
        
		String[] lblTitleArr = {"아이디", "이름", "생년월일", "휴대폰번호",
							"회원등급", "보유포인트", "누적 포인트",
							"회원 가입일"};
		JLabel[] jlblTitleArr = new JLabel[8];
		JPanel[] jpnelArr = new JPanel[8];
		
		for(int  i = 0; i<jlblTitleArr.length; i++) {
			jlblTitleArr[i] = new JLabel(lblTitleArr[i]);
			jpnelArr[i] = new JPanel();
			
			jpnelArr[i].add(jlblTitleArr[i]);
		}//end for
		////////////////////////////////////////////////////
		jtfAccPoint.setEditable(false);
		jtfBirthdate.setEditable(false);
		jtfHoldPoint.setEditable(false);
		jtfInputDate.setEditable(false);
		jtfMemberID.setEditable(false);
		jtfMembership.setEditable(false);
		jtfName.setEditable(false);
		jtfPhone.setEditable(false);
		
		jtfAccPoint.setOpaque(false);
		jtfBirthdate.setOpaque(false);
		jtfHoldPoint.setOpaque(false);
		jtfInputDate.setOpaque(false);
		jtfMemberID.setOpaque(false);
		jtfMembership.setOpaque(false);
		jtfName.setOpaque(false);
		jtfPhone.setOpaque(false);
		
		jtfAccPoint.setForeground(Color.WHITE);
		jtfBirthdate.setForeground(Color.WHITE);
		jtfHoldPoint.setForeground(Color.WHITE);
		jtfInputDate.setForeground(Color.WHITE);
		jtfMemberID.setForeground(Color.WHITE);
		jtfMembership.setForeground(Color.WHITE);
		jtfName.setForeground(Color.WHITE);
		jtfPhone.setForeground(Color.WHITE);
		
		jtfAccPoint.setFont(CustomFontList.getInstance().getFontLabel());
		jtfBirthdate.setFont(CustomFontList.getInstance().getFontLabel());
		jtfHoldPoint.setFont(CustomFontList.getInstance().getFontLabel());
		jtfInputDate.setFont(CustomFontList.getInstance().getFontLabel());
		jtfMemberID.setFont(CustomFontList.getInstance().getFontLabel());
		jtfMembership.setFont(CustomFontList.getInstance().getFontLabel());
		jtfName.setFont(CustomFontList.getInstance().getFontLabel());
		jtfPhone.setFont(CustomFontList.getInstance().getFontLabel());
		
		jlblTitle.setFont(CustomFontList.getInstance().getFontTitle());
		jlblTitle.setForeground(Color.WHITE);
		jlblTitle.setOpaque(false);
		
		jbtnPointUpdate.setBorderPainted(false);
		jbtnPointUpdate.setContentAreaFilled(false);
		jbtnExit.setBorderPainted(false);
		jbtnExit.setContentAreaFilled(false);
		jbtnResign.setBorderPainted(false);
		jbtnResign.setContentAreaFilled(false);
		jbtnUpdateInfo.setBorderPainted(false);
		jbtnUpdateInfo.setContentAreaFilled(false);
		jbtnUpdatePW.setBorderPainted(false);
		jbtnUpdatePW.setContentAreaFilled(false);
		//////////////////////////////////////////
		setLayout(null);
		
		int y = 60;
		for(int i = 0; i<jlblTitleArr.length; i++) {
			jlblTitleArr[i].setFont(CustomFontList.getInstance().getFontLabel());
			jlblTitleArr[i].setForeground(Color.WHITE);
			
			jlblTitleArr[i].setHorizontalAlignment(SwingConstants.RIGHT);
			jlblTitleArr[i].setBounds(20, y, 100, 30);
			
			add(jlblTitleArr[i]);
			if(i==5) {
				y+=35;
			}
			y+=40;
		}
		jlblTitle.setBounds(30, 7, 200, 50);
		jbtnPointUpdate.setBounds(125, 295, 252, 30);
		jtfMemberID.setBounds(137, 63, 230, 25);
		jtfName.setBounds(137, 103, 230, 25);
		jtfBirthdate.setBounds(137, 143, 230, 25);
		jtfPhone.setBounds(137, 183, 230, 25);
		jtfMembership.setBounds(137, 223, 230, 25);
		jtfHoldPoint.setBounds(137, 263, 230, 25);
		jtfAccPoint.setBounds(137, 338, 230, 25);
		jtfInputDate.setBounds(137, 378, 230, 25);
		jbtnResign.setBounds(280,17,100,30);
		jbtnUpdateInfo.setBounds(400/2-170,415,100,30);
		jbtnUpdatePW.setBounds(400/2-100/2,415,100,30);
		jbtnExit.setBounds(400/2+70, 415,100,30);
		
		
		add(jlblTitle);
		add(jtfMemberID);
		add(jtfName);
		add(jtfBirthdate);
		add(jtfPhone);
		add(jtfMembership);
		add(jtfHoldPoint);
		add(jtfAccPoint);
		add(jtfInputDate);
		add(jbtnPointUpdate);
		add(jbtnUpdateInfo);
		add(jbtnUpdatePW);
		add(jbtnResign);
		add(jbtnExit);
		//////////////////////////////////////
		SCUMyPageController smpc = new SCUMyPageController(this);
		jbtnUpdatePW.addActionListener(smpc);
		jbtnPointUpdate.addActionListener(smpc);
		jbtnExit.addActionListener(smpc);
		jbtnResign.addActionListener(smpc);
		jbtnUpdateInfo.addActionListener(smpc);
		/////////////////////////////////////
		JLabel background = new JLabel(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/user_member_info_bg1(400x550).png"));
		background.setBounds(0, 0, 400, 550);
		add(background);
		
		setBounds(smv.getX()+50, smv.getY()+200, 400, 500);
		setResizable(false);
		setVisible(true);
		
//jtfMemberID, jtfName, jtfBirthdate, jtfPhone,           
//jtfMembership, jtfHoldPoint, jtfAccPoint, jtfInputDate
//jbtnUpdateInfo, jbtnUpdatePW, jbtnResign, jbtnExit        
		
	}//Constructor


	public SCUMainView getSmv() {
		return smv;
	}
	public JTextField getJtfMemberID() {
		return jtfMemberID;
	}
	public JTextField getJtfName() {
		return jtfName;
	}
	public JTextField getJtfBirthdate() {
		return jtfBirthdate;
	}
	public JTextField getJtfPhone() {
		return jtfPhone;
	}
	public JTextField getJtfMembership() {
		return jtfMembership;
	}
	public JTextField getJtfHoldPoint() {
		return jtfHoldPoint;
	}
	public JTextField getJtfAccPoint() {
		return jtfAccPoint;
	}
	public JTextField getJtfInputDate() {
		return jtfInputDate;
	}
	public JButton getJbtnUpdateInfo() {
		return jbtnUpdateInfo;
	}
	public JButton getJbtnUpdatePW() {
		return jbtnUpdatePW;
	}
	public JButton getJbtnPointUpdate() {
		return jbtnPointUpdate;
	}
	public JButton getJbtnResign() {
		return jbtnResign;
	}
	public JButton getJbtnExit() {
		return jbtnExit;
	}
	
	
}//Class
