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
	
	private JTextField jtfName, jtfPhone;
	private JButton jbtnConfirm, jbtnExit;
	private SCUMyPageView smpv;
	
	public SCUModifyMemberInfoView(SCUMyPageView smpv) {
		super(smpv, "개인정보 수정", true);
		this.smpv = smpv;
		
		jtfName = new JTextField(15);
		jtfPhone = new JTextField(15);
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
		jtfPhone.setBorder(new LineBorder(Color.WHITE));
		jtfName.setForeground(Color.WHITE);
		jtfPhone.setForeground(Color.WHITE);
		jtfName.setOpaque(false);
		jtfPhone.setOpaque(false);
		
		jbtnConfirm.setContentAreaFilled(false);
		jbtnConfirm.setBorderPainted(false);
		jbtnExit.setContentAreaFilled(false);
		jbtnExit.setBorderPainted(false);
		
		setLayout(null);
		
		jlblTitle.setBounds(30, 7, 200, 50);
		jlblName.setBounds(10, 60, 80, 40);
		jlblPhone.setBounds(10, 110, 80, 40);
		jtfName.setBounds(95, 68, 150, 25);
		jtfPhone.setBounds(95, 118, 150, 25);
		jbtnConfirm.setBounds(290/2-90-5, 220, 90, 40);
		jbtnExit.setBounds(290/2+5, 220, 90, 40);
		
		
		add(jlblTitle);
		add(jlblName);
		add(jlblPhone);
		add(jtfName);
		add(jtfPhone);
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

	public JTextField getJtfPhone() {
		return jtfPhone;
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
