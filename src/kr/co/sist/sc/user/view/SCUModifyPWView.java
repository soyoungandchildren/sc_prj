package kr.co.sist.sc.user.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import kr.co.sist.sc.user.model.SCULoginDAO;
import kr.co.sist.sc.user.util.CustomFontList;
import kr.co.sist.sc.user.vo.SCUModifyPWVO;

@SuppressWarnings("serial")
public class SCUModifyPWView extends JDialog implements ActionListener,KeyListener {

	private JPasswordField jpfPW, jpfConfirmPW;
	private JButton jbtnConfirm, jbtnExit;
	private JLabel jlInstruction1;
	private String stringIDForPW;

	/**
	 * 비밀번호 변경
	 * 
	 * @param slv
	 */
	public SCUModifyPWView(SCULoginView slv, SCUFindAccountView sfav) {

		super(slv, "비밀번호 변경", true);

		stringIDForPW = sfav.getJtfIDForPW().getText();
		makeView();

	}// SCUModifyPWView

	public SCUModifyPWView(SCUMyPageView sppv) {
		super(sppv, "비밀번호 변경", true);

		stringIDForPW = sppv.getSmv().getIdConnecting();
		makeView();
	}

	public void makeView() {

		// 컴포넌트 생성
		JLabel jlPW = new JLabel("변경할 비밀번호 :");
		jpfPW = new JPasswordField();
		JLabel jlConfirmPW = new JLabel("비밀번호 확인 :");
		jpfConfirmPW = new JPasswordField();
		jlInstruction1 = new JLabel("변경할 비밀번호를 입력해주세요");
		JLabel jlInstruction2 = new JLabel("※비밀번호는 30자까지 가능합니다.");

		jbtnConfirm = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_charging(90x40).png"));
		jbtnExit = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_cancel(90x40).png"));

		jpfConfirmPW.setForeground(Color.WHITE);
		jpfPW.setForeground(Color.WHITE);
		
		jbtnConfirm.setBorderPainted(false);
		jbtnConfirm.setContentAreaFilled(false);
		jbtnExit.setBorderPainted(false);
		jbtnExit.setContentAreaFilled(false);
		
		jpfPW.setOpaque(false);
		jpfConfirmPW.setOpaque(false);
		
		jlInstruction1.setForeground(Color.WHITE);
		jlInstruction2.setForeground(Color.WHITE);
		jlInstruction1.setFont(CustomFontList.getInstance().getFontNotice());
		jlInstruction2.setFont(CustomFontList.getInstance().getFontNotice());
		
		jlPW.setForeground(Color.WHITE);
		jlConfirmPW.setForeground(Color.WHITE);
		jlPW.setFont(CustomFontList.getInstance().getFontLabel());
		jlConfirmPW.setFont(CustomFontList.getInstance().getFontLabel());
		jlPW.setHorizontalAlignment(SwingConstants.RIGHT);
		jlConfirmPW.setHorizontalAlignment(SwingConstants.RIGHT);
		
		setLayout(null);
		
		jlInstruction1.setBounds(90, 80, 200, 30);
		jlInstruction2.setBounds(30, 102, 200, 30);
		
		jlPW.setBounds(10, 10, 120, 30);
		jpfPW.setBounds(133, 12, 130, 25);
		jlConfirmPW.setBounds(10, 50, 120, 30);
		jpfConfirmPW.setBounds(133, 52, 130, 25);

		jbtnConfirm.setBounds(300/2-95, 150, 90, 40);
		jbtnExit.setBounds(300/2+5, 150, 90, 40);

		add(jlInstruction1);
		add(jlInstruction2);
		add(jlPW);
		add(jpfPW);
		add(jlConfirmPW);
		add(jpfConfirmPW);
		add(jbtnConfirm);
		add(jbtnExit);

		// 이벤트처리
		jbtnConfirm.addActionListener(this);
		jbtnExit.addActionListener(this);
		jpfPW.addKeyListener(this);
		jpfConfirmPW.addKeyListener(this);

		// 창 설정
		JLabel background = new JLabel(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/user_login_bg3(420x500).png"));
		background.setBounds(0, 0, 300, 230);
		add(background);

		setResizable(false);
		setBounds(90, 200, 300, 230);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * 비밀번호 수정
	 * 
	 * @param password
	 */
	public void modifyPW(String password) throws SQLException {

		String stringPW = new String(jpfPW.getPassword());
		String stringConfirmPW = new String(jpfConfirmPW.getPassword());

		if (!stringPW.equals(stringConfirmPW)) {
			JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
			return;
		} // end if

		SCUModifyPWVO sfpvo = new SCUModifyPWVO(stringPW.trim(), stringIDForPW.trim());
		try {
			if (SCULoginDAO.getInstance().updatePW(sfpvo)) {
				JOptionPane.showMessageDialog(this, "변경되었습니다.");
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
			}
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(this, "비밀번호는 필수 입력사항입니다!");
			se.printStackTrace();
		} // end catch

	}// modifyPW

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {
		String inputPw = new String(jpfPW.getPassword());
		String inputCpw = new String(jpfConfirmPW.getPassword());
		
		if(inputPw.length()<30 && inputCpw.length()<30) {
			
			if("".equals(inputCpw)&&"".equals(inputPw)) {
				jlInstruction1.setForeground(Color.WHITE);
				jlInstruction1.setText("비밀번호를 입력해주세요.");
				return;
			}
			
			if(!inputPw.equals(inputCpw)) {
				jlInstruction1.setForeground(Color.RED);
				jlInstruction1.setText("입력한 비밀번호를 확인해주세요.");
				return;
			}else {
				jlInstruction1.setForeground(Color.WHITE);
				jlInstruction1.setText("사용할 수 있는 비밀번호입니다.");
				
			}
		}else {
			jlInstruction1.setForeground(Color.RED);
			jlInstruction1.setText("사용할 수 없는 비밀번호입니다.");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {

		String stringPW = new String(jpfPW.getPassword());

		// 비밀번호 변경창 '변경'버튼
		if (ae.getSource() == getJbtnConfirm()) {
			try {
				modifyPW(stringPW.trim());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // JbtnConfirm

//			 비밀번호 변경창 '취소'버튼
		if (ae.getSource() == getJbtnExit()) {
			dispose();
		} // JbtnExit

	}// actionPerformed

	public JPasswordField getJpfPW() {
		return jpfPW;
	}

	public JPasswordField getJpfConfirmPW() {
		return jpfConfirmPW;
	}

	public JButton getJbtnConfirm() {
		return jbtnConfirm;
	}

	public JButton getJbtnExit() {
		return jbtnExit;
	}


}// class