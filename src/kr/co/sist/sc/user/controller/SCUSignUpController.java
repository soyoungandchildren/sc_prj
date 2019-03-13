package kr.co.sist.sc.user.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

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
				JOptionPane.showMessageDialog(ssuv, "아이디는 필수 입력사항입니다.");
				jtf.setText("");
				ssuv.getJpfPW().requestFocus();
			} else if (inputId.length() < 4 || inputId.length() > 30) {
				JOptionPane.showMessageDialog(ssuv, "아이디는 3~30자 이내만 가능합니다.");
			} else if (slDao.selectCheckDup(id) && !inputId.equals("")) { // 같은 아이디가 존재할 때
				JOptionPane.showMessageDialog(ssuv, "같은 아이디가 존재합니다.");
				jtf.setText("");
				ssuv.getJpfPW().requestFocus();
			} else {// 같은 아이디가 존재하지 않을 때
				booleanCheckDub = true;
				JOptionPane.showMessageDialog(ssuv, "사용 가능한 아이디입니다.");// end else
				ssuv.getJlNoteID().setText("");
				ssuv.getJpfPW().requestFocus();
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
		ssuv.getJcbYear().setSelectedIndex(0);
		ssuv.getJcbMonth().setSelectedIndex(0);
		ssuv.getJcbDay().setSelectedIndex(0);
		ssuv.getJcbNum().setSelectedIndex(0);
		ssuv.getJlNoteID().setText("");
		ssuv.getJlNotePass().setText("");
		ssuv.getJlNoteName().setText("");
		ssuv.getJlNoteNum().setText("");
	}// reset

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

		// 전화번호
		JTextField jtfPhone = ssuv.getJtfPhone();
		String num = ((String) ssuv.getJcbNum().getSelectedItem()); // 앞자리
		String num1 = jtfPhone.getText();
		String number = "";
		number = num + num1;

		// 이름
		JTextField jtfName = ssuv.getJtfName();

		/////////////////// 유효성 검증//////////////////
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
		
		for (int i = 0; i < jtfName.getText().length(); i++) {
			if(jtfName.getText().charAt(i) >= '0' && jtfName.getText().charAt(i) <= '9') {
				JOptionPane.showMessageDialog(ssuv, "이름은 문자만 입력가능합니다.");
				jtfName.setText("");
				jtfName.requestFocus();
				return;
			}//end if
		}//end for

		// 전화번호 유효성
		if (jtfPhone.getText().isEmpty()) { // 빈칸 이라면
			JOptionPane.showMessageDialog(ssuv, "전화번호를 입력하세요");
			jtfPhone.requestFocus();
			return;
		} else if (!jtfPhone.getText().isEmpty()) {// 빈칸이 아닐 경우
			if (number.length() < 10 || number.length() > 11) {
				JOptionPane.showMessageDialog(ssuv, "전화번호의 길이는 11~12자 이내입니다.");
				jtfPhone.setText("");
				return;
			}

			for (int i = 0; i < number.length(); i++) {
				if (number.charAt(i) < '0' || number.charAt(i) > '9') {
					JOptionPane.showMessageDialog(ssuv, "전화번호는 숫자만 가능합니다.");
					jtfPhone.setText("");
					return;
				}
			} // end for

			if (number.length() == 10) {
				number = num + "-" + num1.substring(0, 3) + "-" + num1.substring(3, 7);
			} else if (number.length() == 11) {
				number = num + "-" + num1.substring(0, 4) + "-" + num1.substring(4, 8);
			} // else if
		} // end else

		SCUSignUpVO ssuvo = new SCUSignUpVO(jtfId.getText().trim(), stringPW.trim(), jtfName.getText().trim(),
				searchDate.toString().trim(), number.trim());
		int cnt=0;
		try {
			// 에러가 나면 catch로 빠져서 DB에 추가가 안됨
		
			cnt = SCULoginDAO.getInstance().insertSignUp(ssuvo);// 에러가 나지 않는 경우 DB에 추가
			if(cnt == 1) {
			JOptionPane.showMessageDialog(ssuv, "[ " + jtfId.getText() + " ] 님의 회원가입을 환영합니다.");
			ssuv.dispose();
			}else {
				JOptionPane.showMessageDialog(ssuv, "이미 회원정보가 존재합니다.");
			}
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
		
		String num = ((String) ssuv.getJcbNum().getSelectedItem());
		String num1 = ssuv.getJtfPhone().getText();
		String number = num + num1;

		if (ae.getSource().equals(ssuv.getJbtnCheckDuplication())) {
			count++;
			checkDuplication(ssuv.getJtfID().getText());
			if (count > 0) {

			}
		} // 중복확인

		if (ae.getSource().equals(ssuv.getJbtnReset())) {
			reset();
		} // 초기화

		if (ae.getSource().equals(ssuv.getJbtnSignUp())) {
			if (booleanCheckDub) {
				addSignUp();
			} else {
				JOptionPane.showMessageDialog(ssuv, "아이디 중복확인을 해주세요.");
				ssuv.getJtfID().requestFocus();
			}
		} // 가입

		if (ae.getSource().equals(ssuv.getJbtnExit())) {
			ssuv.dispose();
		} // getJbtnExit

		if (ae.getSource().equals(ssuv.getJtfPhone())) {
			ssuv.getJbtnSignUp().requestFocus();
		} // getJtfID
		
		//전화번호 앞 번호 선택했는지 확인
		if(ae.getSource().equals(ssuv.getJcbNum())) {
			if(number.length() == 10  || number.length() == 11) {
			ssuv.getJlNoteNum().setText("");
			return;
			}
		}
	}// actionPerformed

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == ssuv.getJtfID()) {
			booleanCheckDub = false;
		} // end if
	}

	@Override
	public void keyReleased(KeyEvent e) {
		String inputId = new String(ssuv.getJtfID().getText().trim());
		String inputPw = new String(ssuv.getJpfPW().getPassword());
		String inputCpw = new String(ssuv.getJpfConfirmPW().getPassword());
		String inputName = new String(ssuv.getJtfName().getText().trim());
		String inputNum = new String(ssuv.getJtfPhone().getText().trim());
		String num = ((String) ssuv.getJcbNum().getSelectedItem());
		String num1 = ssuv.getJtfPhone().getText();
		String number = num + num1;
		int key = e.getKeyCode();

		//// 아이디
		if (e.getSource().equals(ssuv.getJtfID())) {

			if ("".equals(inputId)) {
				ssuv.getJlNoteID().setForeground(Color.red);
				ssuv.getJlNoteID().setText("아이디는 필수 입력사항입니다.");
				return;
			}

			if (inputId.length() < 4 || inputId.length() > 30) { // 아이디 글자수 틀리면
				ssuv.getJlNoteID().setForeground(Color.red);
				ssuv.getJlNoteID().setText("아이디는 3~30자까지 가능합니다.");
				return;
			} else {
				ssuv.getJlNoteID().setForeground(Color.red);
				ssuv.getJlNoteID().setText("중복확인을 해주세요.");
				return;
			}
		} // end Id

		//// 비밀번호
		if (e.getSource().equals(ssuv.getJpfConfirmPW()) || e.getSource().equals(ssuv.getJpfPW())) {
			if ("".equals(inputPw) && "".equals(inputCpw)) {

				ssuv.getJlNotePass().setForeground(Color.red);
				ssuv.getJlNotePass().setText("비밀번호는 필수 입력사항입니다!");
				return;

			} else if (inputPw.length() < 3 && inputPw.length() > 30) {
				ssuv.getJlNotePass().setForeground(Color.RED);
				ssuv.getJlNotePass().setText("비밀번호는 3~30자 이내만 가능합니다.");
				return;

			} else {
				if (!inputPw.equals(inputCpw)) {
					ssuv.getJlNotePass().setForeground(Color.RED);
					ssuv.getJlNotePass().setText("비밀번호가 일치하지 않습니다.");
					return;
				} else {
					ssuv.getJlNotePass().setText("");
					return;
				}
			}
		}

		// 이름
		if (e.getSource().equals(ssuv.getJtfName())) {
			if ("".equals(inputName)) {
				ssuv.getJlNoteName().setForeground(Color.red);
				ssuv.getJlNoteName().setText("이름은 필수 입력사항입니다");
				return;
			}
			if (inputName.length() < 2 || inputName.length() > 10) {
				ssuv.getJlNoteName().setForeground(Color.RED);
				ssuv.getJlNoteName().setText("이름은 2~10자 이내만 가능합니다.");
				return;
			}
			
			for (int i = 0; i < inputName.length(); i++) {
				if(inputName.charAt(i) >= '0' && inputName.charAt(i) <= '9') {
					ssuv.getJlNoteName().setForeground(Color.RED);
					ssuv.getJlNoteName().setText("이름은 문자만 입력 가능합니다.");
					return;
				}//end if
			}//end for
			
			ssuv.getJlNoteName().setText("");
			return;
		}

		//// 전화번호
		if (e.getSource().equals(ssuv.getJtfPhone()) || e.getSource().equals(ssuv.getJcbNum())) {
			if (num.length() > 2 && (number.length() > 9 && number.length() < 12)) {
				ssuv.getJlNoteNum().setText("");
				
				//전화번호까지 입력하고 enter 눌리면 가입버튼 눌림
				if (key == KeyEvent.VK_ENTER) {// 그 눌린 값의 키가 enter라면
					ssuv.getJbtnSignUp().doClick();
				} // end if

			} else if ("".equals(inputNum)) {
				ssuv.getJlNoteNum().setForeground(Color.red);
				ssuv.getJlNoteNum().setText("전화번호는 필수입력사항입니다.");
				return;
			} else if (inputNum.length() < 7 || inputNum.length() > 8) {
				ssuv.getJlNoteNum().setForeground(Color.red);
				ssuv.getJlNoteNum().setText("전화번호 양식에 맞지 않습니다.");
				return;
			}
				if (ssuv.getJcbNum().getSelectedIndex() == 0) {
					ssuv.getJlNoteNum().setForeground(Color.red);
					ssuv.getJlNoteNum().setText("전화번호 앞 번호를 선택하세요.");
					return;
				} else {
					ssuv.getJlNoteNum().setText("");
				}
			} // end



	}// keyReleased

	@Override
	public void keyPressed(KeyEvent e) {
	}
}// class
