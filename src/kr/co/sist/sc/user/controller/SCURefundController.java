package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

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
	 * 예매 내역 확인
	 */
	public void searchBookingHistory(String id) {

		try {
			List<SCUGetBookingHistoryVO> list = new ArrayList<>();
			list = srDAO.searchBookingHistory(srv.getSmv().getIdConnecting());
			Object[] objArr = null;

			// 오늘날짜를 여기서 로컬로 만들어서
			// 영화 시작 시간이 담겨있으니까
			// 여기서 비교시켜서 'Y', 'N'을 줄지 결정
			// 오늘 날짜 구하기
			Calendar cal = Calendar.getInstance();
			String stringToday = String.format("%04d%02d%02d%02d%02d%02d", cal.get(Calendar.YEAR),
					(cal.get(Calendar.MONTH) + 1), cal.get(Calendar.DAY_OF_MONTH),

					cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
			long today = Long.parseLong(stringToday);

			for (int i = 0; i < list.size(); i++) {
				objArr = new Object[7];
				objArr[0] = (i + 1);
				objArr[1] = list.get(i).getBook_number();
				objArr[2] = list.get(i).getMovie_title();
				objArr[3] = list.get(i).getPersonnel();
				objArr[4] = list.get(i).getPayment_date();
				objArr[5] = list.get(i).getScreen_price() * list.get(i).getPersonnel();
				if (today - Long.parseLong(list.get(i).getMovie_start()) < 0) {
					objArr[6] = "Y";
				} else {
					objArr[6] = "N";
				}
				srv.getDtmBookingList().addRow(objArr);

			} // end for

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}// searchBookingHistory

	/**
	 * 스낵 구매내역 확인
	 */
	public void searchSnackHistory(String id) {
		try {
			List<SCUGetSnackHistoryVO> list = new ArrayList<>();
			list = srDAO.searchSnackHistory(srv.getSmv().getIdConnecting());

			Object[] objArr = null;

			for (int i = 0; i < list.size(); i++) {
				objArr = new Object[6];
				objArr[0] = (i + 1);// 번호
				objArr[1] = list.get(i).getSnack_order_num();// 구매번호
				objArr[2] = list.get(i).getQuan();// 수량
				objArr[3] = list.get(i).getSnack_sale_date();// 결제일
				objArr[4] = list.get(i).getQuan() * list.get(i).getSnack_price();//
				objArr[5] = list.get(i).getCheck_exchange();
				srv.getDtmSnackList().addRow(objArr);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} // end catch

	}// searchSnackHistory

	/**
	 * 영화 예매 환불
	 */
	public void deleteBooking() {
		// 선택한 열의 번호 구함
		int selectRow = srv.getJtBookingList().getSelectedRow();

		// 선택한 (열, 행)의 값을 구함
		String code = new String(srv.getJtBookingList().getValueAt(selectRow, 1).toString());// 코드값
		String id = new String(srv.getSmv().getIdConnecting()); // 아이디 불러옴
		String refundPrice = new String(srv.getJtBookingList().getValueAt(selectRow, 5).toString());// 영화예매 비용
		String removable = new String(srv.getJtBookingList().getValueAt(selectRow, 6).toString());

		// 취소요청시간 (현재)
		Calendar cal = Calendar.getInstance();
		String stringToday = String.format("%04d%02d%02d%02d%02d%02d", cal.get(Calendar.YEAR),
				(cal.get(Calendar.MONTH) + 1), cal.get(Calendar.DAY_OF_MONTH),

				cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
		long today = Long.parseLong(stringToday);

		try {
			switch (JOptionPane.showConfirmDialog(srv, "예매를 취소하시겠습니까?", "취소 확인", JOptionPane.OK_CANCEL_OPTION)) {
			case JOptionPane.OK_OPTION:

				boolean flag = srDAO.deleteBooking(code, id, refundPrice, removable); // 선택된 코드의 예매 취소
				if ("Y".equals(removable)) {
					srv.getDtmBookingList().removeRow(selectRow);// 리스트 열 삭제
					break;
				}

			}
		} catch (NumberFormatException nfe) {

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// deleteBooking

	/**
	 * 스낵 구매 취소
	 */
	public void deleteSnack() {
		// 스낵 구매 취소
		// 선택한 열의 번호 구함

		int selectRow = srv.getJtSnackList().getSelectedRow();

		// 선택한 (열, 행)의 값을 구함
		String snackOrderNum = new String(srv.getJtSnackList().getValueAt(selectRow, 1).toString());// 스낵주문번호
		String id = new String(srv.getSmv().getIdConnecting()); // 아이디 불러옴
		String refundPrice = new String(srv.getJtSnackList().getValueAt(selectRow, 4).toString());// 스낵구매 비용
		String removable = new String(srv.getJtSnackList().getValueAt(selectRow, 5).toString());
		System.out.println(snackOrderNum);
		System.out.println(id);
		System.out.println(refundPrice);
		System.out.println(removable);
		try {
			switch (JOptionPane.showConfirmDialog(srv, "구매를 취소하시겠습니까?", "취소 확인", JOptionPane.OK_CANCEL_OPTION)) {
			case JOptionPane.OK_OPTION:

				boolean flag = srDAO.deleteBooking(snackOrderNum, id, refundPrice, removable); // 선택된 코드의 예매 취소
				if ("Y".equals(removable)) {
					srv.getDtmSnackList().removeRow(selectRow);// 리스트 열 삭제
					break;
				}

			}
		} catch (NumberFormatException nfe) {

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 포인트 반환

	}// deleteSnack

	@Override
	public void windowClosing(WindowEvent e) {
		srv.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// 선택된 탭의 번호 호출
		String tabNum = Integer.toString(srv.getJtpRefund().getSelectedIndex());
		// 환불 버튼
		if (ae.getSource() == srv.getJbtnRefund()) {
			if ("0".equals(tabNum)) {// 영화 예매 탭
				deleteBooking();
			} else if ("1".equals(tabNum)) {// 스낵 구매 탭
				deleteSnack();
			} // end else

		} // end if
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