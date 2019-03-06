package kr.co.sist.sc.user.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kr.co.sist.sc.user.controller.SCUMyPageController;

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
		jbtnUpdateInfo = new JButton("정보 수정");
		jbtnUpdatePW = new JButton("비밀번호 수정");
		jbtnPointUpdate = new JButton("포인트 충전");
		jbtnResign = new JButton("회원 탈퇴");
		jbtnExit = new JButton("나가기");
        
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
		//////////////////////////////////////////
		setLayout(null);
		
		int y = 20;
		for(int i = 0; i<jlblTitleArr.length; i++) {
			
			jlblTitleArr[i].setHorizontalAlignment(SwingConstants.RIGHT);
			jlblTitleArr[i].setBounds(20, y, 100, 30);
			
			add(jlblTitleArr[i]);
			y+=40;
		}
		
		//////////////////////////////////////
		SCUMyPageController smpc = new SCUMyPageController(this);
		jbtnUpdatePW.addActionListener(smpc);
		jbtnPointUpdate.addActionListener(smpc);
		jbtnExit.addActionListener(smpc);
		jbtnResign.addActionListener(smpc);
		jbtnUpdateInfo.addActionListener(smpc);
		/////////////////////////////////////
		JLabel background = new JLabel(new ImageIcon("C:\\dev\\workspace\\sc_prj\\src\\kr\\co\\sist\\sc\\user\\images\\user_member_info_bg1(400x550).png"));
		background.setBounds(0, 0, 400, 550);
		add(background);
		
		setBounds(smv.getX()+50, smv.getY()+200, 400, 550);
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
