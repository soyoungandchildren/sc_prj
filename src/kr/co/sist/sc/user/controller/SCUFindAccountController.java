package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.sc.user.view.SCUFindAccountView;
import kr.co.sist.sc.user.view.SCULoginView;
import kr.co.sist.sc.user.view.SCUModifyPWView;

public class SCUFindAccountController extends WindowAdapter implements ActionListener{

	private SCUFindAccountView sfav;
	private SCUModifyPWView smpv;
	private SCULoginView slv;
	
	public SCUFindAccountController(SCUFindAccountView sfav, SCULoginView slv) {
		this.sfav = sfav;
		this.slv = slv;
	}
	

	@Override
	public void windowClosing(WindowEvent we) {
		sfav.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		//아이디 찾기버튼
		if(ae.getSource() == sfav.getJbtnFindID()) {
			
		}
		
		//비밀번호 찾기 버튼
		if(ae.getSource().equals(sfav.getJbtnFindPW())) {
			new SCUModifyPWView(slv);
		}
		
		if(ae.getSource() == smpv.getJbtnExit()) {
			new SCUModifyPWView(slv);
			smpv.dispose();
		}
	}
	
	
}
