package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.sc.user.model.SCUPointDAO;
import kr.co.sist.sc.user.view.SCUMyPageView;
import kr.co.sist.sc.user.view.SCUPointView;

public class SCUPointController extends WindowAdapter implements ActionListener {
	private SCUPointView spv;
	private SCUMyPageView smpv;
	private String user;
	private SCUPointDAO spDAO;
	private int sumPoint, nowPoint;
	
	public SCUPointController(SCUPointView spv, SCUMyPageView smpv, String holdPoint, String memberId) {
		this.spv = spv;
		this.smpv = smpv;
		spDAO = SCUPointDAO.getInstance();
		user = memberId.trim();
		nowPoint = Integer.parseInt(holdPoint.trim());
		spv.getJtfNowPoint().setText(holdPoint);
	}//SCUPointController
	
	public void PointUpdate() {
		try {
			String temp = String.valueOf(spv.getJcbPoint().getSelectedItem());
			int charge = Integer.parseInt(temp.replaceAll(",", ""));
			sumPoint = nowPoint+charge;
			
			String acc = smpv.getJtfAccPoint().getText().trim();
			int accP = charge+Integer.parseInt(acc);
			
			boolean result = spDAO.PointCharge(sumPoint, user, charge);
			if(result) {
				JOptionPane.showMessageDialog(spv, "������ �Ϸ�Ǿ����ϴ�!", "���� �Ϸ�", JOptionPane.PLAIN_MESSAGE);
				spv.dispose();
				smpv.getJtfHoldPoint().setText(" "+String.valueOf(sumPoint));
				smpv.getJtfAccPoint().setText(" "+String.valueOf(accP));
				
				//ȸ���� ��������Ʈ�� ���� ȸ�� ��� ����
				if(0 <= accP && accP < 100000) {
					smpv.getJtfMembership().setText(" �Ϲ� ȸ��");
				} else if(accP < 200000) {
					smpv.getJtfMembership().setText(" ��� ȸ��");
				} else {
					smpv.getJtfMembership().setText(" VIP ȸ��");
				}//end else
				
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
			String temp = String.valueOf(spv.getJcbPoint().getSelectedItem());
			int replace = Integer.parseInt(temp.replaceAll(",", ""));
			int temp2 = Integer.parseInt(spv.getJtfNowPoint().getText().trim());
			int temp3 = temp2+replace;
			
			switch(JOptionPane.showConfirmDialog(spv, "���� �ݾ� : "+temp2+"\n���� �� �ݾ� : "+temp3+"\n���� ����Ʈ�� �����Ͻðڽ��ϱ�?", "����Ʈ ����", JOptionPane.OK_CANCEL_OPTION)) {
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
