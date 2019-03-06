package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.sc.user.model.SCULoginDAO;
import kr.co.sist.sc.user.view.SCUSignUpView;
import kr.co.sist.sc.user.vo.SCUSignUpVO;

public class SCUSignUpController extends WindowAdapter implements ActionListener, KeyListener {

	private SCUSignUpView ssuv;
	private SCULoginDAO slDao;
	private int count = 0;
	boolean booleanCheckDub, booleanKey;

	public SCUSignUpController(SCUSignUpView ssuv) {
		this.ssuv = ssuv;
		slDao = SCULoginDAO.getInstance();
	}// end SCUSignUpController

	/**
	 * id 중복확인 (다이얼로그 창 내용 UI처럼 수정하기)
	 * 
	 * @param id
	 */
	public void checkDuplication(String id) {

		JTextField jtf = ssuv.getJtfID();
		String inputId = jtf.getText().trim();

		try {
			if (inputId.equals("")) {
				JOptionPane.showMessageDialog(ssuv, ".");
			} else if (inputId.length() < 3 || inputId.length() > 30) {
				JOptionPane.showMessageDialog(ssuv, "아이디는 3~30자 이아이디를 입력하세요내만 가능합니다.");
			} else if (slDao.selectCheckDup(id) && !inputId.equals("")) { // 같은 아이디가 존재할 때
				JOptionPane.showMessageDialog(ssuv, "같은 아이디가 존재합니다.");
				jtf.setText("");
			} else {// 같은 아이디가 존재하지 않을 때
				booleanCheckDub = true;
				JOptionPane.showMessageDialog(ssuv, "사용 가능한 아이디입니다.");// end else
			} // end else

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ssuv, "잠시 후 다시 시도해주세요.");
			e.printStackTrace();
		} // end catch

	}// checkDuplication

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
	}// reset

//	private void setDay() {
//		int selYear = ((Integer) ssuv.getJcbYear().getSelectedItem()).intValue();
//		int selMonth = ((Integer) ssuv.getJcbMonth().getSelectedItem()).intValue();
//
//		// 마지막 날 얻기
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, selYear);
//		cal.set(Calendar.MONTH, selMonth - 1);
//
//		int lastDay = cal.getActualMaximum(Calendar.DATE); // 달의 마지막 날을 구하는 메소드
//		int nowDay = cal.get(Calendar.DAY_OF_MONTH);
//		ssuv.getCbmDay().removeAllElements(); // 모델을 초기화하고
//
//		for (int day = 1; day < lastDay + 1; day++) {
//			ssuv.getCbmDay().addElement(day);// 마지막 날을 설정한다.
//		} // end for
//
////		lmv.getCbmDay().setSelectedItem(new Integer(nowDay)); // 오늘을 선택한다.
//
//	}// setDay

	/**
	 * 회원가입
	 */
	public void addSignUp() {
		// 아이디
		JTextField jtfId = ssuv.getJtfID();

		// 비밀번호
		JPasswordField jpfPw = ssuv.getJpfPW();
		String stringPW = new String(jpfPw.getPassword());

		// 비밀번호 확인
		JPasswordField jpfConfirmPW = ssuv.getJpfConfirmPW();
		String stringConfirmPW = new String(ssuv.getJpfConfirmPW().getPassword());

		// 입력한 생일 값 구하기
		int selYear = ((Integer) ssuv.getJcbYear().getSelectedItem()).intValue();
		int selMonth = ((Integer) ssuv.getJcbMonth().getSelectedItem()).intValue();
		int selDay = ((Integer) ssuv.getJcbDay().getSelectedItem()).intValue();

		// 생년월일 형식 맞추기
		String month = "0";
		if (selMonth < 10) {
			month = "0" + Integer.toString(selMonth);
		}

		String day = "0";
		if (selDay < 10) {
			day = "0" + Integer.toString(selDay);
		}

		StringBuilder searchDate = new StringBuilder();
		searchDate.append(selYear).append(month).append(day);

		// 이름, 전화번호
		JTextField jtfName = ssuv.getJtfName();
		JTextField jtfPhone = ssuv.getJtfPhone();

		SCUSignUpVO ssuvo = new SCUSignUpVO(jtfId.getText().trim(), stringPW.trim(), jtfName.getText().trim(),
				searchDate.toString().trim(), jtfPhone.getText().trim());
		
		
		///////////////////유효성 검증//////////////////
		// 아이디 확인
		if (jtfId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(ssuv, "아이디는 필수입력사항입니다.");
			jtfId.requestFocus();
			return;
		} else if (jtfId.getText().length() < 4 || jtfId.getText().length() > 30) {
			JOptionPane.showMessageDialog(ssuv, "아이디는 3~30자 이내입니다.");
			jtfId.setText("");
			jtfId.requestFocus();
			return;
		} else if (count < 1) {
			JOptionPane.showMessageDialog(ssuv, "아이디 중복확인을 해주세요.");
			return;
		} // end else

		// 비밀번호
		if (stringPW.isEmpty()) {
			JOptionPane.showMessageDialog(ssuv, "비밀번호는 필수입력사항입니다.");
			jpfPw.requestFocus();
			return;
		} else if (!stringPW.equals(stringConfirmPW)) {
			JOptionPane.showMessageDialog(ssuv, "비밀번호가 일치하지 않습니다.");
			jpfPw.setText("");
			jpfConfirmPW.setText("");
			jpfPw.requestFocus();
			return;
		} else if (stringPW.length() < 3 || stringPW.length() > 31) {
			JOptionPane.showMessageDialog(ssuv, "비밀번호는 3~30자 이내입니다.");
			jpfPw.setText("");
			jpfPw.requestFocus();
			return;
		} // end else

		// 이름
		if (jtfName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(ssuv, "이름을 입력하세요");
			jtfName.requestFocus();
			return;
		} else if (jtfName.getText().length() < 2 || jtfName.getText().length() > 31) {
			JOptionPane.showMessageDialog(ssuv, "이름은 2~10자 사이만 가능합니다.");
			jtfName.requestFocus();
			return;
		}

		// 생년월일 (숫자만 입력되게)
//		if (jtfBirthdate.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(ssuv, "생년월일을 입력하세요");
//			jtfBirthdate.requestFocus();
//			return;
//		}

		// 전화번호
		if (jtfPhone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(ssuv, "전화번호를 입력하세요");
			jtfPhone.requestFocus();
			return;
		} // end else

		try {
			// 에러가 나면 catch로 빠져서 DB에 추가가 안됨
			SCULoginDAO.getInstance().insertSignUp(ssuvo);// 에러가 나지 않는 경우 DB에 추가
			JOptionPane.showMessageDialog(ssuv, "[ " + jtfId.getText() + " ] 님의 회원가입을 환영합니다.");
			ssuv.dispose();
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(ssuv, "DB에서 문제가 발생하였습니다.");
			se.printStackTrace();
		} // end catch
	}// addSignUp

	@Override
	public void windowClosing(WindowEvent we) {
		ssuv.dispose();
	}// windowClosing

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource().equals(ssuv.getJbtnCheckDuplication())) {
			count++;
			checkDuplication(ssuv.getJtfID().getText());
		} // 중복확인

		if (ae.getSource().equals(ssuv.getJbtnReset())) {
			reset();
		} // 초기화

		if (ae.getSource().equals(ssuv.getJbtnSignUp())) {
			if (booleanCheckDub) {
				addSignUp();
			} else {
				JOptionPane.showMessageDialog(ssuv, "아이디 중복확인을 해주세요.");
			}
		} // 가입

		if (ae.getSource().equals(ssuv.getJbtnExit())) {
			ssuv.dispose();
		} // getJbtnExit
	}// actionPerformed

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == ssuv.getJtfID()) {
			booleanCheckDub = false;
		} // end if
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}// class
