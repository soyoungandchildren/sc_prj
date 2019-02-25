package kr.co.sist.sc.user.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.user.controller.SCURefundController;

@SuppressWarnings("serial")
public class SCURefundView extends JDialog {

	private JTabbedPane jtpRefund;
	private JButton jbtnRefund, jbtnExit;
	private DefaultTableModel dtmBookingList, dtmSnackList;
	private JTable jtBookingList, jtSnackList;

	public SCURefundView(SCUMainView smv) {

		jtpRefund = new JTabbedPane(); // ��

		// ��ȭ ���� ȯ��

		String[] refundColumns = { "��ȣ", "���Ź�ȣ", "���ż�", "�����Ͻ�", "�� ����	", "ȯ�� ���ɿ���" };

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

		jtBookingList.getColumnModel().getColumn(0).setPreferredWidth(60);
		jtBookingList.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtBookingList.getColumnModel().getColumn(2).setPreferredWidth(40);
		jtBookingList.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtBookingList.getColumnModel().getColumn(4).setPreferredWidth(100);
		jtBookingList.getColumnModel().getColumn(5).setPreferredWidth(40);

		jtBookingList.setRowHeight(110);

		// ���� ȯ��
		String[] snackColumns = { "��ȣ", "�����ֹ���ȣ", "����", "�����Ͻ�", "�� ����", "ȯ�Ұ��ɿ���" };

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

		jtSnackList.setRowHeight(23);

		/////////////////////////////////////////
		JScrollPane jspBooking = new JScrollPane(jtBookingList);
		jspBooking.setBorder(new TitledBorder("��ȭ���Ÿ��"));
		JScrollPane jspSnack = new JScrollPane(jtSnackList);
		jspSnack.setBorder(new TitledBorder("����������"));

		// ��ȭ���� ���
		// ���̾ƿ��� �� �г�
		JPanel jpBooking = new JPanel();
		jpBooking.setLayout(new BorderLayout());

		jpBooking.add("Center", jspBooking);

		jtpRefund.add("��ȭ ���� ���", jpBooking);

		// ����
		JPanel jpSnack = new JPanel();
		jpSnack.setLayout(new BorderLayout());
		jpSnack.add(jspSnack);

		jtpRefund.add("���� ���� ���", jpSnack);

		// ��ư
		jbtnRefund = new JButton("ȯ��");
		jbtnExit = new JButton("�ݱ�");

		// ��ư�� �� �г�
		JPanel jpBookingSouth = new JPanel();
		jpBookingSouth.add(jbtnRefund);
		jpBookingSouth.add(jbtnExit);

		// ���� �����ӿ� ��ġ
		add("Center", jtpRefund);
		add("South", jpBookingSouth);

		// �̺�Ʈ!
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
