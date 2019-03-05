package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.sc.user.view.SCUPointView;

public class SCUPointController extends WindowAdapter implements ActionListener {
	private SCUPointView spv;
	private String userPoint;
	
	public SCUPointController(SCUPointView spv, String holdPoint) {
		this.spv = spv;
		userPoint = holdPoint;
		spv.getJtfNowPoint().setText(userPoint);
	}//SCUPointController
	
	public void PointUpdate() {
		System.out.println("±î²á");
	}//PointUpdate

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == spv.getJbtnCharge()) {
			PointUpdate();
		}//end if
		if(ae.getSource() == spv.getJbtnClose()) {
			spv.dispose();
		}//end if
	}//actionPerformed
	
	@Override
	public void windowClosing(WindowEvent e) {
		spv.dispose();
	}//windowClosing

}//class
