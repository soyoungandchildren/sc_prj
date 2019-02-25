package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import kr.co.sist.sc.user.model.SCURefundDAO;
import kr.co.sist.sc.user.view.SCURefundView;
import kr.co.sist.sc.user.vo.SCUGetBookingHistoryVO;

public class SCURefundController extends WindowAdapter implements ActionListener, MouseListener, Runnable {

	private SCURefundView srv;
	private SCURefundDAO srDAO;

	public SCURefundController(SCURefundView srv) {
		super();
		this.srv = srv;

		srDAO = SCURefundDAO.getInstace();
		searchBookingHistory();
	}// SCURefundController

	/**
	 * 예매 내역 확인
	 */
	public void searchBookingHistory() {
		List<SCUGetBookingHistoryVO> = srDAO;
	}

	/**
	 * 스낵 구매내역 확인
	 */
	public void searchSnackHistory() {

	}

	/**
	 * 영화 예매 취소
	 */
	public void deleteBooking() {

	}

	/**
	 * 스낵 구매 취소
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
		 * //탭 if(ae.getSource() == srv.getJtpRefund()) {
		 * 
		 * }
		 */
		// 환불 버튼
		if (ae.getSource() == srv.getJbtnRefund()) {

		}

		// 닫기 버튼
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
