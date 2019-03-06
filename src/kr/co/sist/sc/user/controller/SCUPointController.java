package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.sc.user.model.SCUPointDAO;
import kr.co.sist.sc.user.view.SCUPointView;

public class SCUPointController extends WindowAdapter implements ActionListener {
	private SCUPointView spv;
	private String user;
	private SCUPointDAO spDAO;
	private int sumPoint, nowPoint;
	
	public SCUPointController(SCUPointView spv, String holdPoint, String memberId) {
		this.spv = spv;
		spDAO = SCUPointDAO.getInstance();
		user = memberId;
		nowPoint = Integer.parseInt(holdPoint);
		spv.getJtfNowPoint().setText(holdPoint);
	}//SCUPointController
	
	public void PointUpdate() {
		try {
			String temp = String.valueOf(spv.getJcbPoint().getSelectedItem());
			int charge = Integer.parseInt(temp.replaceAll(",", ""));
			sumPoint = nowPoint+charge;
			
			boolean result = spDAO.PointCharge(sumPoint, user);
			if(!result) {
				JOptionPane.showMessageDialog(spv, "������ �Ϸ�Ǿ����ϴ�!", "���� �Ϸ�", JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(spv, "���� �������� ����Ʈ ������ �ߴܵǾ����ϴ�.\n�ٽ� �õ����ּ���!", "���� ����", JOptionPane.WARNING_MESSAGE);
			}//end else
			
		} catch (SQLException se) {
			se.printStackTrace();
		}//end catch
		
	}//PointUpdate

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == spv.getJbtnCharge()) {
			switch(JOptionPane.showConfirmDialog(spv, "���� �ݾ� : "+nowPoint+"\n���� �� �ݾ� : "+sumPoint+"\n���� ����Ʈ�� �����Ͻðڽ��ϱ�?", "����Ʈ ����", JOptionPane.OK_CANCEL_OPTION)) {
			case JOptionPane.OK_OPTION :
				PointUpdate();
			case JOptionPane.CANCEL_OPTION :
				return;
			}//end switch
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
