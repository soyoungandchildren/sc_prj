package kr.co.sist.sc.user.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class SCUModifyPWView extends JDialog implements ActionListener {

	private JPasswordField jpfPW, jpfConfirmPW;
	private JButton jbtnConfirm, jbtnExit;

	
	/**
	 * 비밀번호 변경
	 * @param slv
	 */
	public SCUModifyPWView(SCULoginView slv) {

		JLabel jlPW = new JLabel("변경할 비밀번호");
		jpfPW = new JPasswordField();
		JLabel jlConfirmPW = new JLabel("비밀번호 확인");
		jpfConfirmPW = new JPasswordField();

		jbtnConfirm = new JButton("변경");
		jbtnExit = new JButton("취소");

		setLayout(null);

		jlPW.setBounds(20, 10, 110, 30);
		jpfPW.setBounds(120, 10, 180, 30);
		jlConfirmPW.setBounds(20, 50, 110, 30);
		jpfConfirmPW.setBounds(120, 50, 180, 30);

		jbtnConfirm.setBounds(30, 110, 110, 30);
		jbtnExit.setBounds(170, 110, 110, 30);

		add(jlPW);
		add(jpfPW);
		add(jlConfirmPW);
		add(jpfConfirmPW);
		add(jbtnConfirm);
		add(jbtnExit);

		// 이벤트처리
		jbtnExit.addActionListener(this);
		
		setVisible(true);
		setBounds(90, 200, 340, 200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		 //비밀번호 변경창 '변경'버튼
			if (ae.getSource() == getJbtnConfirm()) {
				
			} // end if

//			 비밀번호 변경창 '취소'버튼
			if (ae.getSource() == getJbtnExit()) {
				dispose();
			} // end if
	}

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