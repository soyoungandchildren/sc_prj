package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.sc.user.model.SCULoginDAO;
import kr.co.sist.sc.user.view.SCUFindAccountView;
import kr.co.sist.sc.user.view.SCULoginView;
import kr.co.sist.sc.user.view.SCUMainView;
import kr.co.sist.sc.user.view.SCUSignUpView;
import kr.co.sist.sc.user.vo.SCULoginVO;
import kr.co.sist.sc.user.vo.SCUSignUpVO;

public class SCULoginController extends WindowAdapter implements ActionListener, KeyListener{
	private SCULoginView slv;
	private SCUMainView smv;
	

	public SCULoginController(SCULoginView slv, SCUMainView smv) {
		this.slv = slv;
		this.smv = smv;
	}

	@Override
	public void windowClosing(WindowEvent we) {
		slv.dispose();
	}

	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {

		//로그인 버튼
		if (ae.getSource() == slv.getJbtnLogin()) {

			if (!checkIdEmpty() && !checkPassEmpty()) {
				JTextField jtf = slv.getJtfID();
				JPasswordField jpf = slv.getJpfPW();

				String id = jtf.getText().trim();
				String pass = new String(jpf.getPassword());

				SCULoginVO slvo = new SCULoginVO(id, pass);
				String idConnect = login(slvo); 
				
				if (idConnect.equals("")) { // 로그인이 안 되었을 때
					JOptionPane.showMessageDialog(slv, "아이디 또는 비밀번호가 일치하지 않습니다. ");
					jtf.setText("");
					jpf.setText("");
					jtf.requestFocus();
				} else { // 로그인이 되었을 때
					smv.setIsLogin(true);
					JOptionPane.showMessageDialog(slv, "로그인 성공!");
					smv.setIdConnecting(idConnect);
					smv.getJbtnLogin().setIcon(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_logout(215x40).png"));
					slv.dispose();
				} // end if

			} // end if

		} // jbtnLogin
		
		
		//아이디에서 엔터가 눌리면 커서가 비밀번호로 옮겨감
		if(ae.getSource() == slv.getJtfID()) {
			slv.getJpfPW().requestFocus();
		}//getJtfID
		
		// 가입
		if (ae.getSource() == slv.getJbtnSignUp()) {
			new SCUSignUpView(slv);
		} // jbtnSignup

		//아이디/비밀번호 찾기
		if (ae.getSource() == slv.getJbtnFindAccount()) {
			new SCUFindAccountView(slv);
		}
	}
	
	//아이디가 빈 칸인지 확인
	private boolean checkIdEmpty() {
		boolean flag = false;
		JTextField jtfID = slv.getJtfID();
		if (jtfID.getText().trim().equals("")) {
			jtfID.setText("");
			jtfID.requestFocus();
			flag = true;
		} // end if
		return flag;
	}// checkIdEmpty

	//비밀번호가 빈 칸인지 확인
	private boolean checkPassEmpty() {
		boolean flag = false;
		JPasswordField jpfPW = slv.getJpfPW();
		String PW = new String(jpfPW.getPassword());
		if (PW.isEmpty()) {
			jpfPW.setText("");
			jpfPW.requestFocus();
			flag = true;
		} // end if
		return flag;
	}// checkPassEmpty
	
	private String login(SCULoginVO slvo) {
		String name = "";
		SCULoginDAO slDao = SCULoginDAO.getInstance();
		try {
			name = slDao.selectLogin(slvo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(slv, "데이터 에러발생");
			e.printStackTrace();	
		}
		return name;
	}// end login

	@Override
	public void keyPressed(KeyEvent ke) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent ke) {
		int key=ke.getKeyCode();
		
		if(ke.getSource() == slv.getJpfPW()) { //jpfPW 에서 신호가 왔을 때
			if(key == KeyEvent.VK_ENTER) {// 그 눌린 값의 키가 enter라면
				slv.getJbtnLogin().doClick();
			}//end if
		}//end if
	}//keyReleased



}
