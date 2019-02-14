package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;

import kr.co.sist.sc.user.view.SCUFindAccountView;
import kr.co.sist.sc.user.view.SCULoginView;
import kr.co.sist.sc.user.view.SCUModifyPWView;
import kr.co.sist.sc.user.vo.SCUFindPWVO;
import kr.co.sist.sc.user.vo.SCULoginVO;

public class SCUFindAccountController extends WindowAdapter implements ActionListener{

	private SCUFindAccountView sfav;
	private SCUModifyPWView smpv;
	private SCULoginView slv;
	
	public SCUFindAccountController(SCUFindAccountView sfav, SCULoginView slv) {
		this.sfav = sfav;
		this.slv = slv;
	}//SCUFindAccountController
	

	@Override
	public void windowClosing(WindowEvent we) {
		sfav.dispose();
	}//end windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		JTextField jtfPhoneForID= sfav.getJtfPhoneForID();
		String phoneForID = jtfPhoneForID.getText().trim();
				
				
		//���̵� ã���ư
		if(ae.getSource() == sfav.getJbtnFindID()) {
			
		}//end if
		
		//��й�ȣ ã�� ��ư
		if(ae.getSource().equals(sfav.getJbtnFindPW())) {
			new SCUModifyPWView(slv);
			sfav.dispose();
		}//end if
		
		//��й�ȣ ����â '����'��ư
		if(ae.getSource() == smpv.getJbtnConfirm()) {
			
		}//end if
		
		//��й�ȣ ����â '���'��ư
		if(ae.getSource() == smpv.getJbtnExit()) {
			new SCUModifyPWView(slv);
			smpv.dispose();
		}//end if
	}
	
	
}
