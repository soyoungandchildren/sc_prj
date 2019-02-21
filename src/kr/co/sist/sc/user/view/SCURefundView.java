package kr.co.sist.sc.user.view;

import java.awt.BorderLayout;
import java.awt.Dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import oracle.net.aso.p;

@SuppressWarnings("serial")
public class SCURefundView extends JDialog {

	private SCUMainView smv;
	private JTabbedPane jtpRefund;
	private JButton jbtnRefund, jbtnExit;
	private DefaultTableModel dtmBookingList, dtmSnackList;
	private JTable jtBookingList, jtSnackList;

	public SCURefundView() {

		jbtnRefund = new JButton("환불");
		jbtnExit = new JButton("닫기");

		dtmSnackList = new DefaultTableModel();

		// 영화 예매 환불
		jtpRefund = new JTabbedPane(); //탭

		String[] refundColumns = {"번호", "예매번호", "예매수", "결제일시", "총 가격	", "환불 가능여부" };
		
		dtmBookingList = new DefaultTableModel(refundColumns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}// isCellEditable
		};// DefaultTableModel

		jtBookingList = new JTable(dtmBookingList) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0,column).getClass();
			}
		};//jtBookingList
		
		
		jtBookingList.getColumnModel().getColumn(0).setPreferredWidth(60);
		jtBookingList.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtBookingList.getColumnModel().getColumn(2).setPreferredWidth(40);
		jtBookingList.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtBookingList.getColumnModel().getColumn(4).setPreferredWidth(100);
		jtBookingList.getColumnModel().getColumn(5).setPreferredWidth(40);
		
		jtBookingList.setRowHeight(23);
		
		//스낵 환불
		String[] snackColumns = {"번호","스낵주문번호","수량","결제일시","총 가격","환불가능여부"};
		
		dtmSnackList = new DefaultTableModel(snackColumns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}// isCellEditable
		};// DefaultTableModel

		jtSnackList = new JTable(dtmSnackList) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0,column).getClass();
			}
		};//jtBookingList
		
		
		jtSnackList.getColumnModel().getColumn(0).setPreferredWidth(60);
		jtSnackList.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtSnackList.getColumnModel().getColumn(2).setPreferredWidth(40);
		jtSnackList.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtSnackList.getColumnModel().getColumn(4).setPreferredWidth(100);
		jtSnackList.getColumnModel().getColumn(5).setPreferredWidth(40);
		
		jtSnackList.setRowHeight(23);
		
		/////////////////////////////////////////
		JScrollPane jspBooking = new JScrollPane(jtBookingList);
		jspBooking.setBorder(new TitledBorder("영화예매목록"));
		JScrollPane jspSnack = new JScrollPane(jtSnackList);
		jspSnack.setBorder(new TitledBorder("스낵예약목록"));

		JPanel jpBooking = new JPanel();
		jpBooking.setLayout(null);
		
		
		JPanel jpBookingNorth = new JPanel();
		jpBookingNorth.add(jbtnRefund);
		
		jtpRefund.addTab("영화 예매 목록", jspBooking);
		
		add("Center", jtpRefund);
		add("South", jbtnRefund);
		
		////////////
		JPanel jpSnack = new JPanel();
		jpSnack.setLayout(new BorderLayout());
		jpSnack.add(jspSnack);
		
		jtpRefund.addTab("스낵 구매 목록", jspSnack);
			
		setBounds(100, 100, 800, 680);
		setResizable(false);
		setVisible(true);
	}// SCURefundView

	public static void main(String[] args) {
		new SCURefundView();
	}// main

}// class
