package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import kr.co.sist.sc.user.model.SCUMainDAO;
import kr.co.sist.sc.user.view.SCULoginView;
import kr.co.sist.sc.user.view.SCUMainView;
import kr.co.sist.sc.user.view.SCUMovieListView;
import kr.co.sist.sc.user.view.SCUMyPageView;
import kr.co.sist.sc.user.view.SCUSnackMenuView;
import kr.co.sist.sc.user.vo.SCULoginVO;

public class SCUMainController extends WindowAdapter implements ActionListener{
	
	private SCUMainView smv;
	private int cntCheckPassword;
	
	public SCUMainController(SCUMainView smv) {
		this.smv = smv;
	}//Constructor
	
	public void setMain() {
//		List<SCUMainVO> list = new ArrayList<>();
//		try {
//			list = SCUMainDAO.getInstance().setMain();
//		} catch (SQLException sqle) {
//			sqle.printStackTrace();
//		}//end catch
		
	}//setMain Method
	
	
	public void checkPassword() {
		if(cntCheckPassword==5) {
			JOptionPane.showMessageDialog(smv, "나중에 다시 시도해주세요");
			cntCheckPassword=0;
			return;
		}//end if
		
		JPasswordField jpf = new JPasswordField();
		int commit = JOptionPane.showConfirmDialog(smv, jpf, "패스워드 입력", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(commit==0) {
			String password = new String(jpf.getPassword());
			
			boolean checkPassword;
			try {
				SCULoginVO slVO = new SCULoginVO(smv.getIdConnecting(), password);
				checkPassword = SCUMainDAO.getInstance().checkPassword(slVO);
				
				if(checkPassword) {
					cntCheckPassword = 0;
					new SCUMyPageView(smv);
				}else {
					JOptionPane.showMessageDialog(smv, "비밀번호를 확인해주세요.");
					cntCheckPassword+=1;
					checkPassword();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//try~catch
			
		}//end if
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource().equals(smv.getJbtnLogin())) {//로그인버튼
			if(!smv.getIsLogin()) {
				new SCULoginView(smv);
			}else {
				smv.setIsLogin(false);
				smv.setIdConnecting("");
				smv.getJbtnLogin().setText("로그인/회원가입");
			}
		}
		
		if(ae.getSource().equals(smv.getJbtnBooking())) {//예매 버튼
			new SCUMovieListView(smv);
		}//end if
		
		if(ae.getSource().equals(smv.getJbtnSnack())) {//스낵 버튼
			new SCUSnackMenuView(smv);
		}//end if
		
		if(ae.getSource().equals(smv.getJbtnMyPage())) {
			if(smv.getIsLogin()) {
				checkPassword();
			}else {
				JOptionPane.showMessageDialog(smv, "로그인을 해주세요.");
			}
		}//end if
		
	}//actionPerformed
	
	@Override
	public void windowClosing(WindowEvent we) {
		smv.dispose();
	}//windowClosing
	
}//Class
