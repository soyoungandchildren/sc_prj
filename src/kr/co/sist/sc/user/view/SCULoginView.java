package kr.co.sist.sc.user.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.sc.user.controller.SCULoginController;
import kr.co.sist.sc.user.util.CustomFontList;

@SuppressWarnings("serial")
public class SCULoginView extends JDialog {

	private JTextField jtfID;
	private JPasswordField jpfPW;
	private JButton jbtnLogin, jbtnSignUp, jbtnFindAccount;

	public SCULoginView(SCUMainView smv) {

		super(smv, "로그인", true);
		// 컴포넌트 생성
		String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/";

		jtfID = new JTextField();
		jpfPW = new JPasswordField();
		
		jbtnLogin = new JButton(new ImageIcon(imgPath+"jbt_login(100x40).png"));
		jbtnSignUp = new JButton(new ImageIcon(imgPath+"jbt_join_member(100x40).png"));
		jbtnFindAccount = new JButton(new ImageIcon(imgPath+"jbt_find_id_passwd(220x27).png"));

		JLabel jlId = new JLabel("로그인");
		JLabel jlPass = new JLabel("비밀번호");

		setLayout(null);

		jlId.setBounds(31, 130, 100, 30);
		jlId.setForeground(Color.white);
		jlId.setFont(CustomFontList.getInstance().getFontLabel());
		jtfID.setBounds(80, 130, 180, 30);

		jlPass.setBounds(20, 170, 100, 30);
		jlPass.setForeground(Color.white);
		jlPass.setFont(CustomFontList.getInstance().getFontLabel());
		jpfPW.setBounds(80, 170, 180, 30);

		jbtnFindAccount.setBounds(40, 220, 220, 27);
		jbtnFindAccount.setContentAreaFilled(false);
		jbtnFindAccount.setBorderPainted(false);

		jbtnLogin.setBounds(20, 310, 100, 40);
		//테두리 없애기
		jbtnLogin.setContentAreaFilled(false);
		jbtnLogin.setBorderPainted(false);

		jbtnSignUp.setBounds(150, 310, 100, 40);
		jbtnSignUp.setContentAreaFilled(false);
		jbtnSignUp.setBorderPainted(false);


		add(jlId);
		add(jtfID);
		add(jbtnFindAccount);

		add(jlPass);
		add(jpfPW);
		add(jbtnLogin);
		add(jbtnSignUp);

		// 이벤트 등록
		SCULoginController slc = new SCULoginController(this, smv);
		addWindowListener(slc);
		jtfID.addActionListener(slc);
		jbtnFindAccount.addActionListener(slc);
		jbtnLogin.addActionListener(slc);
		jbtnSignUp.addActionListener(slc);
		jpfPW.addKeyListener(slc);

		// 창 설정
		JLabel background = new JLabel(new ImageIcon(imgPath + "user_login_bg1(300x500).png"));
		background.setBounds(0, 0, 300, 500);
		add(background);

		setBounds(smv.getX() + 301, smv.getY() + 50, 300, 500);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public JButton getJbtnFindAccount() {
		return jbtnFindAccount;
	}

	public JTextField getJtfID() {
		return jtfID;
	}

	public JPasswordField getJpfPW() {
		return jpfPW;
	}

	public JButton getJbtnLogin() {
		return jbtnLogin;
	}

	public JButton getJbtnSignUp() {
		return jbtnSignUp;
	}

}
