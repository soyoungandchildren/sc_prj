package kr.co.sist.sc.user.view;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

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

		// ������Ʈ ����
		String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/";

		jtpRefund = new JTabbedPane(); // ��

		// ��ȭ ���� ȯ��

		String[] refundColumns = { "��ȣ", "���Ź�ȣ", "��ȭ��", "���ż�", "�����Ͻ�", "�� ����", "ȯ�� ���ɿ���" };

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

		jtBookingList.getColumnModel().getColumn(0).setPreferredWidth(10);// ��ȣ
		jtBookingList.getColumnModel().getColumn(1).setPreferredWidth(100);// ���Ź�ȣ
		jtBookingList.getColumnModel().getColumn(2).setPreferredWidth(30);// ��ȭ��
		jtBookingList.getColumnModel().getColumn(3).setPreferredWidth(30);// ���ż�
		jtBookingList.getColumnModel().getColumn(4).setPreferredWidth(100);// �����Ͻ�
		jtBookingList.getColumnModel().getColumn(5).setPreferredWidth(40);// �Ѱ���
		jtBookingList.getColumnModel().getColumn(6).setPreferredWidth(50);// ȯ�Ұ��ɿ���

		jtBookingList.setRowHeight(50);

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

		// ���̺� ��� ����
		DefaultTableCellRenderer dtcrSort = new DefaultTableCellRenderer();
		dtcrSort.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmTab1 = jtBookingList.getColumnModel();
		TableColumnModel tcmTab2 = jtSnackList.getColumnModel();

		// �ֹ� ��� ��� ����
		for (int i = 0; i < tcmTab1.getColumnCount(); i++) {
			tcmTab1.getColumn(i).setCellRenderer(dtcrSort);
		} // end for

		// �հ� ��� ����
		for (int i = 0; i < tcmTab2.getColumnCount(); i++) {
			tcmTab2.getColumn(i).setCellRenderer(dtcrSort);
		} // end for

		jtSnackList.getColumnModel().getColumn(0).setPreferredWidth(60);
		jtSnackList.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtSnackList.getColumnModel().getColumn(2).setPreferredWidth(40);
		jtSnackList.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtSnackList.getColumnModel().getColumn(4).setPreferredWidth(100);
		jtSnackList.getColumnModel().getColumn(5).setPreferredWidth(40);

		jtSnackList.setRowHeight(50);

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
		jbtnRefund = new JButton(new ImageIcon(imgPath+"jbt_delete_order(125x40).png"));
		jbtnExit = new JButton(new ImageIcon(imgPath+"jbt_cancel(125x40).png"));

		// ��ư �׵θ� ���ֱ�
		jbtnRefund.setContentAreaFilled(false);
		jbtnRefund.setBorderPainted(false);
		jbtnExit.setContentAreaFilled(false);
		jbtnExit.setBorderPainted(false);

		// �÷� ���� ���� �Ұ�
		jtSnackList.getTableHeader().setReorderingAllowed(false);
		jtSnackList.getTableHeader().setResizingAllowed(false);
		jtBookingList.getTableHeader().setReorderingAllowed(false);
		jtBookingList.getTableHeader().setResizingAllowed(false);

		// ��ư�� �� �г�
		ImageIcon icon = new ImageIcon(imgPath + "user_snackcorner_bg1(900x800).png");

		JPanel jpBookingSouth = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		jpBookingSouth.add(jbtnRefund);
		jpBookingSouth.add(jbtnExit);

		jpBooking.add(jspBooking);

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
