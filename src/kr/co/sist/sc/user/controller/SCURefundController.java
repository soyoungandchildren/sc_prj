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
	 * ���� ���� Ȯ��
	 */
	public void searchBookingHistory(String id) {

		try {
			List<SCUGetBookingHistoryVO> list = new ArrayList<>();
			list = srDAO.searchBookingHistory(srv.getSmv().getIdConnecting());
			Object[] objArr = null;

			// ���ó�¥�� ���⼭ ���÷� ����
			// ��ȭ ���� �ð��� ��������ϱ�
			// ���⼭ �񱳽��Ѽ� 'Y', 'N'�� ���� ����
			// ���� ��¥ ���ϱ�
			Calendar cal = Calendar.getInstance();
			String stringToday = String.format("%04d%02d%02d%02d%02d%02d", cal.get(Calendar.YEAR),
					(cal.get(Calendar.MONTH) + 1), cal.get(Calendar.DAY_OF_MONTH),

					cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
			long today = Long.parseLong(stringToday);

			for (int i = 0; i < list.size(); i++) {
				objArr = new Object[6];
//				objArr[0] = (i + 1);
				objArr[0] = list.get(i).getBook_number();
				objArr[1] = list.get(i).getMovie_title();
				objArr[2] = list.get(i).getPersonnel();
				objArr[3] = list.get(i).getPayment_date();
				objArr[4] = list.get(i).getScreen_price() * list.get(i).getPersonnel();
				if (today - Long.parseLong(list.get(i).getMovie_start()) < 0) {
					objArr[5] = "Y";
				} else {
					objArr[5] = "N";
				}
				srv.getDtmBookingList().addRow(objArr);

			} // end for

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}// searchBookingHistory

	/**
	 * ���� ���ų��� Ȯ��
	 */
	public void searchSnackHistory(String id) {
		try {
			List<SCUGetSnackHistoryVO> list = new ArrayList<>();
			list = srDAO.searchSnackHistory(srv.getSmv().getIdConnecting());

			Object[] objArr = null;

			for (int i = 0; i < list.size(); i++) {
				objArr = new Object[6];
//				objArr[0] = (i + 1);// ��ȣ
				objArr[0] = list.get(i).getSnack_order_num();// ���Ź�ȣ
				objArr[1] = list.get(i).getSnack_name();// ������
				objArr[2] = list.get(i).getQuan();// ����
				objArr[3] = list.get(i).getSnack_sale_date();// ������
				objArr[4] = list.get(i).getQuan() * list.get(i).getSnack_price();//
				objArr[5] = list.get(i).getCheck_exchange();
				srv.getDtmSnackList().addRow(objArr);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} // end catch

	}// searchSnackHistory

	/**
	 * ��ȭ ���� ȯ��
	 */
	public void deleteBooking() {
		// ������ ���� ��ȣ ����
		int selectRow = srv.getJtBookingList().getSelectedRow();

		// ������ (��, ��)�� ���� ����
		String id = new String(srv.getSmv().getIdConnecting()); // ���̵� �ҷ���
		String code = new String(srv.getJtBookingList().getValueAt(selectRow, 0).toString());// �ڵ尪
		int intCode = Integer.parseInt(code);
		System.out.println(intCode);
		String movieTitle = new String(srv.getJtBookingList().getValueAt(selectRow, 1).toString());
		String refundPrice = new String(srv.getJtBookingList().getValueAt(selectRow, 4).toString());// ��ȭ���� ���
		String removable = new String(srv.getJtBookingList().getValueAt(selectRow, 5).toString());

		// ��ҿ�û�ð� (����)
		Calendar cal = Calendar.getInstance();
		String stringToday = String.format("%04d%02d%02d%02d%02d%02d", cal.get(Calendar.YEAR),
				(cal.get(Calendar.MONTH) + 1), cal.get(Calendar.DAY_OF_MONTH),

				cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
		long today = Long.parseLong(stringToday);

		try {
			switch (JOptionPane.showConfirmDialog(srv, "[ " + movieTitle + " ] ���Ÿ� ����Ͻðڽ��ϱ�?", "��� Ȯ��",
					JOptionPane.OK_CANCEL_OPTION)) {
			case JOptionPane.OK_OPTION:

				boolean flag = srDAO.deleteBooking(code, id, refundPrice, removable); // ���õ� �ڵ��� ���� ���
				if ("Y".equals(removable)) {
					srv.getDtmBookingList().removeRow(selectRow);// ����Ʈ �� ����
					break;
				}

			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(srv, "����");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// deleteBooking

	/**
	 * ���� ���� ���
	 */
	public void deleteSnack() {
		// ���� ���� ���
		// ������ ���� ��ȣ ����
		int selectRow = srv.getJtSnackList().getSelectedRow();

		// ������ (��, ��)�� ���� ����
		String id = new String(srv.getSmv().getIdConnecting()); // ���̵� �ҷ���
		String snackOrderNum = new String(srv.getJtSnackList().getValueAt(selectRow, 0).toString());// �����ֹ���ȣ
		String snackName = new String(srv.getJtSnackList().getValueAt(selectRow, 1).toString());
		String refundPrice = new String(srv.getJtSnackList().getValueAt(selectRow, 4).toString());// �������� ���
		String removable = new String(srv.getJtSnackList().getValueAt(selectRow, 5).toString());//��ҿ���
		
		try {
			switch (JOptionPane.showConfirmDialog(srv, "[ "+snackName+" ]���Ÿ� ����Ͻðڽ��ϱ�?", "��� Ȯ��", JOptionPane.OK_CANCEL_OPTION)) {
			case JOptionPane.OK_OPTION:

				boolean flag = srDAO.deleteSnack(snackOrderNum, id, refundPrice, removable); // ���õ� �ڵ��� ���� ���
				if ("Y".equals(removable)) {
					srv.getDtmSnackList().removeRow(selectRow);// ����Ʈ �� ����
					break;
				}

			}
		} catch (NumberFormatException nfe) {

		} catch (SQLException e) {
			e.printStackTrace();
		} // ����Ʈ ��ȯ

	}// deleteSnack

	@Override
	public void windowClosing(WindowEvent e) {
		srv.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// ���õ� ���� ��ȣ ȣ��
		String tabNum = Integer.toString(srv.getJtpRefund().getSelectedIndex());
		// ȯ�� ��ư
		if (ae.getSource() == srv.getJbtnRefund()) {
			if ("0".equals(tabNum)) {// ��ȭ ���� ��
				deleteBooking();
			} else if ("1".equals(tabNum)) {// ���� ���� ��
				deleteSnack();
			} // end else

		} // end if
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