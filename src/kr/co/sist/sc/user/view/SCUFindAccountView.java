package kr.co.sist.sc.user.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.co.sist.sc.user.controller.SCUFindAccountController;

@SuppressWarnings("serial")
public class SCUFindAccountView extends JDialog {

	private JTextField jtfPhoneForID, jtfNameForPW, jtfIDForPW, jtfPhoneForPW;
	private JButton jbtnFindID, jbtnFindPW;

	public SCUFindAccountView(SCULoginView slv) {

		// 전화번호로 아이디찾기
		JLabel jlPhoneForID = new JLabel("전화번호");
		jtfPhoneForID = new JTextField();
		jbtnFindID = new JButton("아이디 찾기");

		// 아래 정보로 비번찾기
		JLabel jlIDForPW = new JLabel("아이디");
		JLabel jlNameForPW = new JLabel("이름");
		JLabel jlPhoneForPW = new JLabel("전화번호");
		jtfIDForPW = new JTextField();
		jtfNameForPW = new JTextField();
		jtfPhoneForPW = new JTextField();
		jbtnFindPW = new JButton("비밀번호 찾기");


		setLayout(null);

		jlPhoneForID.setBounds(20, 30, 100, 30);
		jtfPhoneForID.setBounds(80, 30, 180, 30);
		jbtnFindID.setBounds(70, 80, 140, 30);

		jlIDForPW.setBounds(20, 180, 100, 30);
		jlNameForPW.setBounds(20, 220, 100, 30);
		jlPhoneForPW.setBounds(20, 260, 100, 30);

		jtfIDForPW.setBounds(80, 180, 180, 30);
		jtfNameForPW.setBounds(80, 220, 180, 30);
		jtfPhoneForPW.setBounds(80, 260, 180, 30);

		jbtnFindPW.setBounds(70, 310, 140, 30);

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
		
		
		setVisible(true);
		setResizable(false);
		setBounds(105, 140, 290, 450); // 완성 후에 창 뜨는 위치 약간 조정하기
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

}//class
