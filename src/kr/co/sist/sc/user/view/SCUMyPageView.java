package kr.co.sist.sc.user.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kr.co.sist.sc.user.controller.SCUMyPageController;

@SuppressWarnings("serial")
public class SCUMyPageView extends JDialog{

	private SCUMainView smv;
	private JTextField jtfMemberID, jtfName, jtfBirthdate, jtfPhone,
						jtfMembership, jtfHoldPoint, jtfAccPoint, jtfInputDate;
	private JButton jbtnUpdateInfo, jbtnUpdatePW, jbtnResign, jbtnExit;
	
	
	public SCUMyPageView(SCUMainView smv) {
		super(smv,"���� ������",true);
		this.smv = smv;
		
		JLabel jlblTitle = new JLabel("ȸ�� ����");
		jtfMemberID = new JTextField(10);
		jtfName = new JTextField(10);
		jtfBirthdate = new JTextField(10);
		jtfPhone = new JTextField(10);
		jtfMembership = new JTextField(10);
		jtfHoldPoint = new JTextField(10);
		jtfAccPoint = new JTextField(10);
		jtfInputDate = new JTextField(10);
		jbtnUpdateInfo = new JButton("���� ����");
		jbtnUpdatePW = new JButton("��й�ȣ ����");
		jbtnResign = new JButton("ȸ�� Ż��");
		jbtnExit = new JButton("������");
        
		String[] lblTitleArr = {"���̵�", "�̸�", "�������", "�޴�����ȣ",
							"ȸ�����", "��������Ʈ", "���� ����Ʈ",
							"ȸ�� ������"};
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
		//////////////////////////////////////////
		jtfAccPoint.setEnabled(false);
		jtfBirthdate.setEnabled(false);
		jtfHoldPoint.setEnabled(false);
		jtfInputDate.setEnabled(false);
		jtfMemberID.setEnabled(false);
		jtfMembership.setEnabled(false);
		jtfName.setEnabled(false);
		jtfPhone.setEnabled(false);
		//////////////////////////////////////////
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
		
		
		jpnlSouth.add(jbtnUpdateInfo);
		jpnlSouth.add(jbtnUpdatePW);
		jpnlSouth.add(jbtnResign);
		jpnlSouth.add(jbtnExit);
		
		add("North", jpnlNorth);
		add("Center", jpnlCenter);
		add("South", jpnlSouth);
		//////////////////////////////////////
		SCUMyPageController smpc = new SCUMyPageController(this);
		jbtnUpdatePW.addActionListener(smpc);
		jbtnExit.addActionListener(smpc);
		jbtnResign.addActionListener(smpc);
		jbtnUpdateInfo.addActionListener(smpc);
		/////////////////////////////////////
		
		setBounds(smv.getX()+50, smv.getY()+200, 600, 600);
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
	public JButton getJbtnResign() {
		return jbtnResign;
	}
	public JButton getJbtnExit() {
		return jbtnExit;
	}
	
	
}//Class
