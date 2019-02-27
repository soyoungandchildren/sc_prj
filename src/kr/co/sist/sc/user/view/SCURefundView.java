package kr.co.sist.sc.user.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.user.controller.SCURefundController;

@SuppressWarnings("serial")
public class SCURefundView extends JDialog {
	private SCUMainView smv;
	private SCUMyPageView smpv;
	private JTabbedPane jtpRefund;
	private JButton jbtnRefund, jbtnExit;
	private DefaultTableModel dtmBookingList, dtmSnackList;
	private JTable jtBookingList, jtSnackList;

	public SCURefundView(SCUMainView smv) {

		this.smv = smv;

		jtpRefund = new JTabbedPane(); // 탭

		// 영화 예매 환불

		String[] refundColumns = { "번호", "예매번호", "예매수", "결제일시", "총 가격	", "환불 가능여부" };

		dtmBookingList = new DefaultTableModel(refundColumns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}// isCellEditable
		};// DefaultTableModel

		jtBookingList = new JTable(dtmBookingList) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};// jtBookingList

		jtBookingList.getColumnModel().getColumn(0).setPreferredWidth(60);// 번호
		jtBookingList.getColumnModel().getColumn(1).setPreferredWidth(100);// 예매번호
		jtBookingList.getColumnModel().getColumn(2).setPreferredWidth(20);// 예매수
		jtBookingList.getColumnModel().getColumn(3).setPreferredWidth(80);// 결제일시
		jtBookingList.getColumnModel().getColumn(4).setPreferredWidth(50);// 총가격
		jtBookingList.getColumnModel().getColumn(5).setPreferredWidth(50);// 환불가능여부

		jtBookingList.setRowHeight(50);

		// 스낵 환불
		String[] snackColumns = { "번호", "스낵주문번호", "수량", "결제일시", "총 가격", "환불가능여부" };

		dtmSnackList = new DefaultTableModel(snackColumns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}// isCellEditable
		};// DefaultTableModel

		jtSnackList = new JTable(dtmSnackList) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};// jtBookingList

		jtSnackList.getColumnModel().getColumn(0).setPreferredWidth(60);
		jtSnackList.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtSnackList.getColumnModel().getColumn(2).setPreferredWidth(40);
		jtSnackList.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtSnackList.getColumnModel().getColumn(4).setPreferredWidth(100);
		jtSnackList.getColumnModel().getColumn(5).setPreferredWidth(40);

		jtSnackList.setRowHeight(50);

		/////////////////////////////////////////
		JScrollPane jspBooking = new JScrollPane(jtBookingList);
		jspBooking.setBorder(new TitledBorder("영화예매목록"));
		JScrollPane jspSnack = new JScrollPane(jtSnackList);
		jspSnack.setBorder(new TitledBorder("스낵예약목록"));

		// 영화예약 취소
		// 레이아웃이 들어간 패널
		JPanel jpBooking = new JPanel();
		jpBooking.setLayout(new BorderLayout());

		jpBooking.add("Center", jspBooking);

		jtpRefund.add("영화 예매 목록", jpBooking);

		// 스낵
		JPanel jpSnack = new JPanel();
		jpSnack.setLayout(new BorderLayout());
		jpSnack.add(jspSnack);

		jtpRefund.add("스낵 구매 목록", jpSnack);

		// 버튼
		jbtnRefund = new JButton("환불");
		jbtnExit = new JButton("닫기");

		// 버튼이 들어간 패널
		JPanel jpBookingSouth = new JPanel();
		jpBookingSouth.add(jbtnRefund);
		jpBookingSouth.add(jbtnExit);

		// 탭을 프레임에 배치
		add("Center", jtpRefund);
		add("South", jpBookingSouth);

		// 이벤트!
		SCURefundController srfc = new SCURefundController(this);
		addWindowListener(srfc);
		jtpRefund.addMouseListener(srfc);
		jtBookingList.addMouseListener(srfc);
		jtSnackList.addMouseListener(srfc);

		jbtnRefund.addActionListener(srfc);
		jbtnExit.addActionListener(srfc);

		setBounds(smv.getX() + 110, smv.getY() + 30, 800, 680);
		setResizable(false);
		setVisible(true);
	}// SCURefundView

	public SCUMyPageView getSmpv() {
		return smpv;
	}

	public SCUMainView getSmv() {
		return smv;
	}

	public JTabbedPane getJtpRefund() {
		return jtpRefund;
	}

	public JButton getJbtnRefund() {
		return jbtnRefund;
	}

	public JButton getJbtnExit() {
		return jbtnExit;
	}

	public DefaultTableModel getDtmBookingList() {
		return dtmBookingList;
	}

	public DefaultTableModel getDtmSnackList() {
		return dtmSnackList;
	}

	public JTable getJtBookingList() {
		return jtBookingList;
	}

	public JTable getJtSnackList() {
		return jtSnackList;
	}

}// class
