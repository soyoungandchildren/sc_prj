package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.SliderUI;

import kr.co.sist.sc.user.model.SCURefundDAO;
import kr.co.sist.sc.user.view.SCURefundView;
import kr.co.sist.sc.user.vo.SCUGetBookingHistoryVO;
import kr.co.sist.sc.user.vo.SCUGetSnackHistoryVO;

public class SCURefundController extends WindowAdapter implements ActionListener, MouseListener, Runnable {

	private SCURefundView srv;
	private SCURefundDAO srDAO;

	public SCURefundController(SCURefundView srv) {
		super();
		this.srv = srv;
		srDAO = SCURefundDAO.getInstace();
		searchBookingHistory(srv.getSmv().getIdConnecting());
		searchSnackHistory(srv.getSmv().getIdConnecting());
	}// SCURefundController

	/**
	 * ���� ���� Ȯ��
	 */
	public void searchBookingHistory(String id) {

		try {
			List<SCUGetBookingHistoryVO> list = new ArrayList<>();
			list = srDAO.searchBookingHistory(srv.getSmv().getIdConnecting());

			Object[] objArr = null;
//			SCUGetBookingHistoryVO sgbhvo = null;

			for (int i = 0; i < list.size(); i++) {
//				sgbhvo = list.get(i);
				objArr = new Object[6];
				objArr[0] = (i + 1);
				objArr[1] = list.get(i).getBook_number();
				objArr[2] = list.get(i).getPersonnel();
				objArr[3] = list.get(i).getPayment_date();
				objArr[4] = list.get(i).getScreen_price() *list.get(i).getPersonnel();
				objArr[5] = list.get(i).getMovie_start();
				srv.getDtmBookingList().addRow(objArr);
			} // end for

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}//searchBookingHistory

	/**
	 * ���� ���ų��� Ȯ��
	 */
	public void searchSnackHistory(String id) {
		try {
			List<SCUGetSnackHistoryVO> list = new ArrayList<>();
			list = srDAO.searchSnackHistory(srv.getSmv().getIdConnecting());
			
			Object[] objArr = null;
			
			for(int i =0; i<list.size(); i++) {
				objArr = new Object[6];
				objArr[0] = (i + 1);//��ȣ
				objArr[1] = list.get(i).getSnack_order_num();//���Ź�ȣ
				objArr[2] = list.get(i).getQuan();//����
				objArr[3] = list.get(i).getSnack_sale_date();//������
				objArr[4] = list.get(i).getQuan()*list.get(i).getSnack_price();//
				objArr[5] = list.get(i).getCheck_exchange();
				srv.getDtmSnackList().addRow(objArr);			
				}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}//end catch

	}//searchSnackHistory

	/**
	 * ��ȭ ���� ���
	 */
	public void deleteBooking() {
		int selectRow = srv.getJtBookingList().getSelectedRow();
		srv.getDtmBookingList().removeRow(selectRow);
	}//deleteBooking

	/**
	 * ���� ���� ���
	 */
	public void deleteSnack() {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		srv.dispose(); 
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		/*
		 * //�� if(ae.getSource() == srv.getJtpRefund()) {
		 * 
		 * }
		 */
		// ȯ�� ��ư
		int selectIdx = srv.getJtBookingList().getSelectedRow();
		if (ae.getSource() == srv.getJbtnRefund()) {
			deleteBooking();
		}

		// �ݱ� ��ư
		if (ae.getSource() == srv.getJbtnExit()) {
			srv.dispose();
		}
	}// actionPerformed

	@Override
	public void run() {
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}// class
