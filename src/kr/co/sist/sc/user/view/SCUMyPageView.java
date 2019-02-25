package kr.co.sist.sc.user.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SCUMyPageView extends JDialog{

	private SCUMainView smv;
	private JTextField jtfMemberID, jtfName, jtfBirthdate, jtfPhone,
						jtfMembership, jtfHoldPoint, jtfAccPoint, jtfInputDate;
	private JButton jbtnUpdateInfo, jbtnUpdatePW, jbtnResign, jbtnExit;
	
	
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
		jbtnUpdateInfo = new JButton();
		jbtnUpdatePW = new JButton();
		jbtnResign = new JButton();
		jbtnExit = new JButton();
        
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
		
		jpnelArr[0].add(jtfMemberID);
		jpnelArr[1].add(jtfName);
		jpnelArr[2].add(jtfBirthdate);
		jpnelArr[3].add(jtfPhone);
		jpnelArr[4].add(jtfMembership);
		jpnelArr[5].add(jtfHoldPoint);
		jpnelArr[6].add(jtfAccPoint);
		jpnelArr[7].add(jtfInputDate);

		
		setLayout(new BorderLayout());
		
		JPanel jpnlNorth = new JPanel();
		JPanel jpnlCenter = new JPanel();
		JPanel jpnlSouth = new JPanel();
		
		jpnlNorth.add(jlblTitle);
		
		JPanel centerField = new JPanel();
		centerField.setLayout(new GridLayout(8, 1));
		
		for(int i = 0 ; i<jpnelArr.length; i++) {
			centerField.add(jpnelArr[i]);
		}//end for
		jpnlCenter.add(centerField);
		
		add("North", jpnlNorth);
		add("Center", jpnlCenter);
		add("South", jpnlSouth);
		
		setBounds(smv.getX()+50, smv.getY()+200, 300, 600);
		setVisible(true);
		
		
//jtfMemberID, jtfName, jtfBirthdate, jtfPhone,           
//jtfMembership, jtfHoldPoint, jtfAccPoint, jtfInputDate
//jbtnUpdateInfo, jbtnUpdatePW, jbtnResign, jbtnExit        
		
		
		
		
	}//Constructor
	
	
}//Class
