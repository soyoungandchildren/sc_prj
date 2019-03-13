package kr.co.sist.sc.user.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import kr.co.sist.sc.user.controller.SCUModifyMemberInfoController;
import kr.co.sist.sc.user.util.CustomFontList;

@SuppressWarnings("serial")
public class SCUModifyMemberInfoView extends JDialog{
	
	private JTextField jtfName, jtfPhone1, jtfPhone2, jtfPhone3;
	private JButton jbtnConfirm, jbtnExit;
	private SCUMyPageView smpv;
	
	public SCUModifyMemberInfoView(SCUMyPageView smpv) {
		super(smpv, "개인정보 수정", true);
		this.smpv = smpv;
		
		jtfName = new JTextField(15);
		jtfPhone1 = new JTextField(3);
		jtfPhone2 = new JTextField(3);
		jtfPhone3 = new JTextField(3);
		jbtnConfirm = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_edit(90x40).png"));
		jbtnExit = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_cancel(90x40).png"));
		
		JLabel jlblTitle = new JLabel("회원정보 수정");
		JLabel jlblName = new JLabel("이름 : ");
		JLabel jlblPhone = new JLabel("전화번호 : ");
		
		
		jlblTitle.setOpaque(false);
		jlblTitle.setForeground(Color.WHITE);
		jlblTitle.setFont(CustomFontList.getInstance().getFontTitle());
		
		jlblName.setHorizontalAlignment(SwingConstants.RIGHT);
		jlblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		jlblName.setFont(CustomFontList.getInstance().getFontLabel());
		jlblPhone.setFont(CustomFontList.getInstance().getFontLabel());
		jlblName.setForeground(Color.WHITE);
		jlblPhone.setForeground(Color.WHITE);
		jtfName.setBorder(new LineBorder(Color.WHITE));
		jtfPhone1.setBorder(new LineBorder(Color.WHITE));
		jtfPhone2.setBorder(new LineBorder(Color.WHITE));
		jtfPhone3.setBorder(new LineBorder(Color.WHITE));
		jtfName.setForeground(Color.WHITE);
		jtfPhone1.setForeground(Color.WHITE);
		jtfPhone2.setForeground(Color.WHITE);
		jtfPhone3.setForeground(Color.WHITE);
		jtfName.setOpaque(false);
		jtfPhone1.setOpaque(false);
		jtfPhone2.setOpaque(false);
		jtfPhone3.setOpaque(false);
		
		jbtnConfirm.setContentAreaFilled(false);
		jbtnConfirm.setBorderPainted(false);
		jbtnExit.setContentAreaFilled(false);
		jbtnExit.setBorderPainted(false);
		
		setLayout(null);
		
		jlblTitle.setBounds(30, 7, 200, 50);
		jlblName.setBounds(10, 60, 80, 40);
		jlblPhone.setBounds(10, 110, 80, 40);
		jtfName.setBounds(95, 68, 150, 25);
		jtfPhone1.setBounds(95, 118, 25, 25);
		jtfPhone2.setBounds(130, 118, 35, 25);
		jtfPhone3.setBounds(175, 118, 35, 25);
		jbtnConfirm.setBounds(290/2-90-5, 220, 90, 40);
		jbtnExit.setBounds(290/2+5, 220, 90, 40);
		
		
		add(jlblTitle);
		add(jlblName);
		add(jlblPhone);
		add(jtfName);
		add(jtfPhone1);
		add(jtfPhone2);
		add(jtfPhone3);
		add(jbtnConfirm);
		add(jbtnExit);
		
		
		SCUModifyMemberInfoController smmic = new SCUModifyMemberInfoController(this);
		jbtnConfirm.addActionListener(smmic);
		jbtnExit.addActionListener(smmic);
		
		
		
		JLabel background = new JLabel(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/user_member_info_bg2(290x310).png"));
		background.setBounds(0, 0, 290, 310);
		add(background);
		
		setResizable(false);
		setBounds(smpv.getX()+150, smpv.getY()+150, 290, 310);
		setVisible(true);
		
		
		
	}//Constructor

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfPhone1() {
		return jtfPhone1;
	}
	public JTextField getJtfPhone2() {
		return jtfPhone2;
	}
	public JTextField getJtfPhone3() {
		return jtfPhone3;
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
