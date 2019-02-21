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

		jbtnRefund = new JButton("ȯ��");
		jbtnExit = new JButton("�ݱ�");

		dtmSnackList = new DefaultTableModel();

		// ��ȭ ���� ȯ��
		jtpRefund = new JTabbedPane(); //��

		String[] refundColumns = {"��ȣ", "���Ź�ȣ", "���ż�", "�����Ͻ�", "�� ����	", "ȯ�� ���ɿ���" };
		
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
		
		//���� ȯ��
		String[] snackColumns = {"��ȣ","�����ֹ���ȣ","����","�����Ͻ�","�� ����","ȯ�Ұ��ɿ���"};
		
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
		jspBooking.setBorder(new TitledBorder("��ȭ���Ÿ��"));
		JScrollPane jspSnack = new JScrollPane(jtSnackList);
		jspSnack.setBorder(new TitledBorder("����������"));

		JPanel jpBooking = new JPanel();
		jpBooking.setLayout(null);
		
		
		JPanel jpBookingNorth = new JPanel();
		jpBookingNorth.add(jbtnRefund);
		
		jtpRefund.addTab("��ȭ ���� ���", jspBooking);
		
		add("Center", jtpRefund);
		add("South", jbtnRefund);
		
		////////////
		JPanel jpSnack = new JPanel();
		jpSnack.setLayout(new BorderLayout());
		jpSnack.add(jspSnack);
		
		jtpRefund.addTab("���� ���� ���", jspSnack);
			
		setBounds(100, 100, 800, 680);
		setResizable(false);
		setVisible(true);
	}// SCURefundView

	public static void main(String[] args) {
		new SCURefundView();
	}// main

}// class
