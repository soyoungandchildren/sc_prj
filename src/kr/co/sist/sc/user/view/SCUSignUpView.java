package kr.co.sist.sc.user.view;

import java.awt.Color;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.sc.user.controller.SCUSignUpController;
import kr.co.sist.sc.user.util.CustomFontList;

@SuppressWarnings("serial")
public class SCUSignUpView extends JDialog {

	private JButton jbtnCheckDuplication, jbtnReset, jbtnSignUp, jbtnExit;
	private JTextField jtfID, jtfName, jtfBirth, jtfPhone;
	private JPasswordField jpfPW, jpfConfirmPW;
	private JComboBox<Integer> jcbYear, jcbMonth, jcbDay;
	private JComboBox<String> jcbNum;
	private DefaultComboBoxModel<Integer> cbmYear, cbmMonth, cbmDay;

	private Calendar cal;

	private JLabel jlNoteID, jlNotePass, jlNoteName, jlNoteNum;

	public SCUSignUpView(SCULoginView slv) {

		super(slv, "회원가입", true);
		cal = Calendar.getInstance();

		// 컴포넌트 생성
		String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/";

		jbtnCheckDuplication = new JButton(new ImageIcon(imgPath + "jbt_check_double(100x30).png"));
		jbtnReset = new JButton(new ImageIcon(imgPath + "jbt_rewrite(100x40).png"));
		jbtnSignUp = new JButton(new ImageIcon(imgPath + "jbt_join(100x40).png"));
		jbtnExit = new JButton(new ImageIcon(imgPath + "jbt_close(100x40).png"));

		jtfID = new JTextField();
		jlNoteID = new JLabel("");

		jpfPW = new JPasswordField();
		jpfConfirmPW = new JPasswordField();
		jlNotePass= new JLabel("");

		jtfBirth = new JTextField();

		jtfName = new JTextField();
		jlNoteName= new JLabel("");
		jtfPhone = new JTextField();
		jlNoteNum= new JLabel("");


		JLabel jlID = new JLabel("아이디");
		JLabel jlPW = new JLabel("비밀번호");
		JLabel jlConfirmPW = new JLabel("비밀번호 확인");
		JLabel jlName = new JLabel("이름");
		JLabel jlBirth = new JLabel("생년월일");
		JLabel jlYear = new JLabel("년");
		JLabel jlMonth = new JLabel("월");
		JLabel jlDay = new JLabel("일");
		JLabel jlPhone = new JLabel("휴대폰 번호");

		cbmYear = new DefaultComboBoxModel<>();
		jcbYear = new JComboBox<Integer>(cbmYear);

		cbmMonth = new DefaultComboBoxModel<Integer>();
		jcbMonth = new JComboBox<Integer>(cbmMonth);

		cbmDay = new DefaultComboBoxModel<Integer>();
		jcbDay = new JComboBox<Integer>(cbmDay);

		// 번호
		DefaultComboBoxModel<String> num = new DefaultComboBoxModel<>();
		num.addElement("번호");

		String arr[] = { "010", "011", "017", "016", "019" };
		for (int i = 0; i < arr.length; i++) {
			num.addElement(String.valueOf(arr[i]));
		}

		jcbNum = new JComboBox<String>(num);

		setLayout(null);

		jlID.setBounds(20, 80, 100, 30);
		jlID.setForeground(Color.white);
		jlID.setFont(CustomFontList.getInstance().getFontLabel());
		jlNoteID.setBounds(110, 100, 190, 30);
		jlNoteID.setForeground(Color.white);

		jtfID.setBounds(110, 80, 180, 30);
		

		jlPW.setBounds(20, 120, 100, 30);
		jlPW.setForeground(Color.white);
		jlPW.setFont(CustomFontList.getInstance().getFontLabel());
		jpfPW.setBounds(110, 120, 180, 30);
		
		jlConfirmPW.setBounds(20, 160, 100, 30);
		jlConfirmPW.setForeground(Color.white);
		jlConfirmPW.setFont(CustomFontList.getInstance().getFontLabel());
		jpfConfirmPW.setBounds(110, 160, 180, 30);
		jlNotePass.setBounds(110, 180, 180, 30);
		jlNotePass.setForeground(Color.white);

		jlName.setBounds(20, 200, 100, 30);
		jlName.setForeground(Color.white);
		jlName.setFont(CustomFontList.getInstance().getFontLabel());
		jtfName.setBounds(110, 200, 180, 30);
		jlNoteName.setBounds(110, 220, 180, 30);
		jlNoteName.setForeground(Color.white);

		jlBirth.setBounds(20, 240, 100, 30);
		jlBirth.setForeground(Color.white);
		jlBirth.setFont(CustomFontList.getInstance().getFontLabel());

		jlYear.setBounds(105, 240, 80, 30);
		jlYear.setForeground(Color.white);
		jlYear.setFont(CustomFontList.getInstance().getFontLabel());
		jcbYear.setBounds(125, 240, 60, 30);
		
		jlMonth.setBounds(187, 240, 80, 30);
		jlMonth.setForeground(Color.white);
		jlMonth.setFont(CustomFontList.getInstance().getFontLabel());
		jcbMonth.setBounds(205, 240, 40, 30);
		
		jlDay.setBounds(245, 240, 40, 30);
		jlDay.setForeground(Color.white);
		jlDay.setFont(CustomFontList.getInstance().getFontLabel());
		jcbDay.setBounds(260, 240, 40, 30);

		jlPhone.setBounds(20, 280, 100, 30);
		jlPhone.setForeground(Color.white);
		jlPhone.setFont(CustomFontList.getInstance().getFontLabel());
		jcbNum.setBounds(110, 280, 60, 30);
		jtfPhone.setBounds(175, 280, 113, 30);
		jlNoteNum.setBounds(110, 300, 180, 30);
		jlNoteNum.setForeground(Color.white);


		jbtnCheckDuplication.setBounds(300, 80, 100, 30);
		jbtnCheckDuplication.setContentAreaFilled(false);
		jbtnCheckDuplication.setBorderPainted(false);

		jbtnReset.setBounds(40, 350, 100, 40);
		jbtnReset.setContentAreaFilled(false);
		jbtnReset.setBorderPainted(false);

		jbtnSignUp.setBounds(160, 350, 100, 40);
		jbtnSignUp.setContentAreaFilled(false);
		jbtnSignUp.setBorderPainted(false);

		jbtnExit.setBounds(280, 350, 100, 40);
		jbtnExit.setContentAreaFilled(false);
		jbtnExit.setBorderPainted(false);

		add(jlID);
		add(jlPW);
		add(jlConfirmPW);
		add(jlName);
		add(jlBirth);
		add(jlYear);
		add(jlMonth);
		add(jlDay);
		add(jlPhone);

		add(jlNoteID);
		add(jlNotePass);
		add(jlNoteName);
		add(jlNoteNum);
		
		add(jtfID);
		add(jtfName);
		add(jtfPhone);

		add(jcbYear);
		add(jcbMonth);
		add(jcbDay);
		add(jcbNum);

		add(jpfPW);
		add(jpfConfirmPW);

		add(jbtnCheckDuplication);
		add(jbtnReset);
		add(jbtnSignUp);
		add(jbtnExit);

		setYear();// JCB Year
		setMonth();// JCB Month
		setDay();// JCB Day

		// 이벤트 등록
		SCUSignUpController ssuc = new SCUSignUpController(this);
		addWindowListener(ssuc);

		jbtnCheckDuplication.addActionListener(ssuc);
		jbtnReset.addActionListener(ssuc);
		jbtnSignUp.addActionListener(ssuc);
		jbtnExit.addActionListener(ssuc);
		jtfID.addKeyListener(ssuc);
		jpfPW.addKeyListener(ssuc);
		jpfConfirmPW.addKeyListener(ssuc);
		jtfName.addKeyListener(ssuc);
		jtfPhone.addKeyListener(ssuc);

		// 창 설정
		JLabel background = new JLabel(new ImageIcon(imgPath + "user_login_bg3(420x500).png"));
		background.setBounds(0, 0, 420, 500);
		add(background);

		setBounds(slv.getX(), slv.getY(), 420, 500);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}// class

	private void setYear() {// 현재 년도 4년 전까지
		int year = cal.get(Calendar.YEAR);
		for (int temp = 0; temp < 130; temp++) {
			cbmYear.addElement(year - temp); // 2019가 제일 위에 오게 하려고 - 했음
		}

//		jcbYear.setSelectedItem(new Integer(year)); // 이거 안 써주면 항상 2019/01/01 로 들어가니까, 시작을 오늘 날짜로 들어가게 하려고
	}// setYear

	private void setMonth() {// 월 1~12월
		int now_month = cal.get(Calendar.DAY_OF_MONTH) + 1;
		for (int month = 1; month < 13; month++) {
			cbmMonth.addElement(month);
		}
//		jcbMonth.setSelectedItem(new Integer(now_month));
	}// setMonth

	private void setDay() {// 그 월의 마지막 날
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		int nowDay = cal.get(Calendar.DAY_OF_MONTH);
		for (int day = 1; day < lastDay + 1; day++) {
			getCbmDay().addElement(day);
		} // end for
//		jcbDay.setSelectedItem(new Integer(nowDay));
	}// setDay

	public JButton getJbtnCheckDuplication() {
		return jbtnCheckDuplication;
	}

	public JButton getJbtnReset() {
		return jbtnReset;
	}

	public JButton getJbtnSignUp() {
		return jbtnSignUp;
	}

	public JButton getJbtnExit() {
		return jbtnExit;
	}

	public JTextField getJtfID() {
		return jtfID;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfBirth() {
		return jtfBirth;
	}

	public JTextField getJtfPhone() {
		return jtfPhone;
	}

	public JPasswordField getJpfPW() {
		return jpfPW;
	}

	public JPasswordField getJpfConfirmPW() {
		return jpfConfirmPW;
	}

	public JComboBox<Integer> getJcbYear() {
		return jcbYear;
	}

	public JComboBox<Integer> getJcbMonth() {
		return jcbMonth;
	}

	public JComboBox<Integer> getJcbDay() {
		return jcbDay;
	}

	public JComboBox<String> getJcbNum() {
		return jcbNum;
	}

	public DefaultComboBoxModel<Integer> getCbmYear() {
		return cbmYear;
	}

	public DefaultComboBoxModel<Integer> getCbmMonth() {
		return cbmMonth;
	}

	public DefaultComboBoxModel<Integer> getCbmDay() {
		return cbmDay;
	}

	public Calendar getCal() {
		return cal;
	}

	public JLabel getJlNoteID() {
		return jlNoteID;
	}

	public JLabel getJlNotePass() {
		return jlNotePass;
	}

	public JLabel getJlNoteName() {
		return jlNoteName;
	}

	public JLabel getJlNoteNum() {
		return jlNoteNum;
	}
	
	

}// class
