package kr.co.sist.sc.user.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.sc.user.model.SCULoginDAO;
import kr.co.sist.sc.user.vo.SCUFindPWVO;
import kr.co.sist.sc.user.vo.SCUModifyPWVO;

@SuppressWarnings("serial")
public class SCUModifyPWView extends JDialog implements ActionListener {

	private JPasswordField jpfPW, jpfConfirmPW;
	private JButton jbtnConfirm, jbtnExit;
	private SCUFindAccountView sfav;
	/**
	 * 비밀번호 변경
	 * @param slv
	 */
	public SCUModifyPWView(SCULoginView slv, SCUFindAccountView sfav) {
		
		super(slv,"비밀번호 변경",true);
		
		this.sfav = sfav;

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
		jbtnConfirm.addActionListener(this);
		jbtnExit.addActionListener(this);
		
		setBounds(90, 200, 340, 200);
		setVisible(true);
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}//SCUModifyPWView

	
	
	/**
	 * 비밀번호 수정
	 * @param password
	 */
	public void modifyPW(String password) throws SQLException {
		String stringIDForPW = sfav.getJtfIDForPW().getText();
		
		String stringPW = new String(jpfPW.getPassword());
		String stringConfirmPW = new String(jpfConfirmPW.getPassword());
		
		
		if(!stringPW.equals(stringConfirmPW)) {
			JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
			return;
		}//end if	
		
		SCUModifyPWVO sfpvo = new SCUModifyPWVO (stringPW.trim(), stringIDForPW.trim());
		try {
			if(SCULoginDAO.getInstance().updatePW(sfpvo)) {
				JOptionPane.showMessageDialog(this, "변경되었습니다.");
			}else {
				JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
			}
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(this, "비밀번호는 필수 입력사항입니다!");
			se.printStackTrace();
		} // end catch
		
	}//modifyPW
	
	@Override
	public void actionPerformed(ActionEvent ae) {

		String stringPW = new String(jpfPW.getPassword());
		
		 //비밀번호 변경창 '변경'버튼
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
			
			
	}//actionPerformed
	

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