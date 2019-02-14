package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.sc.user.model.SCULoginDAO;
import kr.co.sist.sc.user.view.SCUSignUpView;
import kr.co.sist.sc.user.vo.SCULoginVO;
import kr.co.sist.sc.user.vo.SCUSignUpVO;

public class SCUSignUpController extends WindowAdapter implements ActionListener {

	private SCUSignUpView ssuv;
	private SCULoginDAO slDao;
	private boolean dupResult;

	public SCUSignUpController(SCUSignUpView ssuv) {
		this.ssuv = ssuv;
		slDao = SCULoginDAO.getInstance();
	}

	/**
	 * id 중복확인 (다이얼로그 창 내용 UI처럼 수정하기)
	 * @param id
	 */
	public void checkDuplication(String id) {

		JTextField jtf = ssuv.getJtfID();
		String inputId = jtf.getText().trim();

		try {
			if (!slDao.selectCheckDup(id) && !inputId.equals("")) {// 같은 아이디가 존재하지 않을 때
				JOptionPane.showMessageDialog(ssuv, "사용 가능한 아이디입니다.");
			} else if (inputId.equals("")) {
				JOptionPane.showMessageDialog(ssuv, "아이디를 입력하세요.");
			} else { // 같은 아이디가 존재할 때
				JOptionPane.showMessageDialog(ssuv, "같은 아이디가 존재합니다.");
				jtf.setText("");
			} // end else
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ssuv, "잠시 후 다시 시도해주세요.");
			e.printStackTrace();
		} // end catch

	}
	
	/**
	 * 재작성 (초기화)
	 */
	public void reset() {
		ssuv.getJtfID().setText("");
		ssuv.getJpfPW().setText("");
		ssuv.getJpfConfirmPW().setText("");
		ssuv.getJtfName().setText("");
		ssuv.getJtfBirth().setText("");
		ssuv.getJtfPhone().setText("");
	}

	/**
	 * 회원가입
	 * 1. 각 창에 값이 있는지 확인
	 * 2. 비밀번호, 비밀번호 확인 일치하는지 확인하기 (비번 길이도 조건걸기)
	 * 3. ...다이얼로그 창이 흠.. :( (생각 중)
	 */
	public void addSignUp() {
		
		int cnt = 0;
		
		JTextField jtfId = ssuv.getJtfID();
		
		JPasswordField jpfPw = ssuv.getJpfPW();
		String stringPW = new String(jpfPw.getPassword());
		
		JPasswordField jpfConfirmPW = ssuv.getJpfConfirmPW();
		String stringconfirmPW = new String(ssuv.getJpfConfirmPW().getPassword());
		
		JTextField jtfName = ssuv.getJtfName();
		JTextField jtfBirthdate = ssuv.getJtfBirth();
		JTextField jtfPhone = ssuv.getJtfPhone();
		
		if (jtfId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(ssuv, "아이디는 필수입력사항입니다.");
			return;
		}
		
		if(stringPW.isEmpty()) {
			JOptionPane.showMessageDialog(ssuv, "비밀번호는 필수입력사항입니다.");
			return;
		}

		SCUSignUpVO ssuvo = new SCUSignUpVO(jtfId.getText().trim(), stringPW.trim(), jtfName.getText().trim(), jtfBirthdate.getText().trim(), jtfPhone.getText().trim());
		try {
			// 에러가 나면 catch로 빠져서 DB에 추가가 안됨
			SCULoginDAO.getInstance().insertSignUp(ssuvo);// 에러가 나지 않는 경우 DB에 추가	
			
			// 다음 도시락의 입력을 편하게 하기 위해서 입력 폼을 초기화
			jtfId.setText("");
			jpfPw.setText("");
			jtfName.setText("");
			jtfBirthdate.setText("");
			jtfPhone.setText("");
			
			JOptionPane.showMessageDialog(ssuv, "회원가입이 완료되었습니다.");
			jtfName.requestFocus();
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(ssuv, "DB에서 문제가 발생하였습니다.");
			se.printStackTrace();
		} // end catch
	}//addSignUp
	
	@Override
	public void windowClosing(WindowEvent we) {
		ssuv.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource().equals(ssuv.getJbtnCheckDuplication())) {
			checkDuplication(ssuv.getJtfID().getText());
		} // end JbtnCheckDuplication

		if(ae.getSource().equals(ssuv.getJbtnReset())) {
			reset();
		}
		
		if (ae.getSource().equals(ssuv.getJbtnSignUp())) {
			addSignUp();
		}
		
		if(ae.getSource().equals(ssuv.getJbtnExit())){
			ssuv.dispose();
		}
	}

}// class
