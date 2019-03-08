package kr.co.sist.sc.user.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.co.sist.sc.user.controller.SCUFindAccountController;
import kr.co.sist.sc.user.util.CustomFontList;

@SuppressWarnings("serial")
public class SCUFindAccountView extends JDialog {

	private JTextField jtfPhoneForID, jtfNameForPW, jtfIDForPW, jtfPhoneForPW;
	private JButton jbtnFindID, jbtnFindPW;

	public SCUFindAccountView(SCULoginView slv) {

		super(slv, "아이디/비밀번호 찾기", true);
		// 컴포넌트 생성
		String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/";

		// 전화번호로 아이디찾기
		JLabel jlPhoneForID = new JLabel("전화번호");
		jtfPhoneForID = new JTextField();
		jbtnFindID = new JButton(new ImageIcon(imgPath+"jbt_find_id(220x27).png"));

		// 아래 정보로 비번찾기
		JLabel jlIDForPW = new JLabel("아이디");
		JLabel jlNameForPW = new JLabel("이름");
		JLabel jlPhoneForPW = new JLabel("전화번호");
		jtfIDForPW = new JTextField();
		jtfNameForPW = new JTextField();
		jtfPhoneForPW = new JTextField();
		jbtnFindPW = new JButton(new ImageIcon(imgPath+"jbt_find_passwd(220x27).png"));

		setLayout(null);

		jlPhoneForID.setBounds(25, 50, 100, 30);
		jlPhoneForID.setForeground(Color.white);
		jlPhoneForID.setFont(CustomFontList.getInstance().getFontLabel());
		jtfPhoneForID.setBounds(85, 50, 180, 30);
		
		jbtnFindID.setBounds(35, 100, 220, 27);
		jbtnFindID.setContentAreaFilled(false);
		jbtnFindID.setBorderPainted(false);


		jlIDForPW.setBounds(25, 200, 100, 30);
		jlIDForPW.setForeground(Color.white);
		jlIDForPW.setFont(CustomFontList.getInstance().getFontLabel());
		jtfIDForPW.setBounds(85, 200, 180, 30);
	
		jlNameForPW.setBounds(25, 240, 100, 30);
		jlNameForPW.setForeground(Color.white);
		jlNameForPW.setFont(CustomFontList.getInstance().getFontLabel());
		jtfNameForPW.setBounds(85, 240, 180, 30);
		
		jlPhoneForPW.setBounds(25, 280, 100, 30);
		jlPhoneForPW.setForeground(Color.white);
		jlPhoneForPW.setFont(CustomFontList.getInstance().getFontLabel());
		jtfPhoneForPW.setBounds(85, 280, 180, 30);
		
		jbtnFindPW.setBounds(35, 330, 220, 27);
		//버튼 테두리 없애기
		jbtnFindPW.setContentAreaFilled(false);
		jbtnFindPW.setBorderPainted(false);
				
		add(jlPhoneForID);
		add(jlIDForPW);
		add(jlNameForPW);
		add(jlPhoneForPW);

		add(jtfPhoneForID);
		add(jtfIDForPW);
		add(jtfNameForPW);
		add(jtfPhoneForPW);

		add(jbtnFindID);
		add(jbtnFindPW);

		// 이벤트 등록
		SCUFindAccountController sfac = new SCUFindAccountController(this, slv);
		addWindowListener(sfac);
		jbtnFindID.addActionListener(sfac);
		jbtnFindPW.addActionListener(sfac);

		// 창 설정
		JLabel background = new JLabel(new ImageIcon(imgPath + "user_login_bg2(300x500).png"));
		background.setBounds(0, 0, 300, 500);
		add(background);
		
		setResizable(false);
		setBounds(slv.getX(), slv.getY(), 300, 500); // 완성 후에 창 뜨는 위치 약간 조정하기
		setVisible(true);
	}

	public JTextField getJtfPhoneForID() {
		return jtfPhoneForID;
	}

	public JTextField getJtfNameForPW() {
		return jtfNameForPW;
	}

	public JTextField getJtfIDForPW() {
		return jtfIDForPW;
	}

	public JTextField getJtfPhoneForPW() {
		return jtfPhoneForPW;
	}

	public JButton getJbtnFindID() {
		return jbtnFindID;
	}

	public JButton getJbtnFindPW() {
		return jbtnFindPW;
	}

}// class
