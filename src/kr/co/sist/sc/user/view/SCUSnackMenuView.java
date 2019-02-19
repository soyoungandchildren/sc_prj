package kr.co.sist.sc.user.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.user.controller.SCUSnackMenuController;

@SuppressWarnings("serial")
public class SCUSnackMenuView extends JDialog{
	
	private SCUMainView smv;
	private DefaultTableModel dtmOrderList, dtmOrderTotalPrice;
	private JTable jtOrderList, jtOrderTotalPrice;
	private JButton jbtnCheckOut, jbtnDeleteOrder, jbtnExit;
	private JButton[] jbtnMenu;
	
	public SCUSnackMenuView(SCUMainView smv) {
		super(smv, "���� �ֹ�", true);
		this.smv = smv;
		
		//������Ʈ ����
		jbtnCheckOut = new JButton("����");
		jbtnDeleteOrder = new JButton("�ֹ� ����");
		jbtnExit = new JButton("�ݱ�");
		
		jbtnMenu = new JButton[8];
		for(int i =0; i<jbtnMenu.length; i++) {
			jbtnMenu[i] = new JButton();
		}

		String[] columnNames = {"������", "����", "����", "�� ����"};
		dtmOrderList = new DefaultTableModel(columnNames, 0) {
		};
		
		jtOrderList = new JTable(dtmOrderList) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
			//JTable�� ���� ��������
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};
		JScrollPane jspOrderList = new JScrollPane(jtOrderList);
		
		//�հ� ���̺�
		String[] temp = {"�հ�", "�� ����"};
		dtmOrderTotalPrice = new DefaultTableModel(temp, 0); {
		};
		
		jtOrderTotalPrice = new JTable(dtmOrderTotalPrice) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		
		Object[] rowData = new Object[2];
		rowData[0] = "�� ��";
		rowData[1] = "0";
		
		dtmOrderTotalPrice.addRow(rowData);
		jtOrderTotalPrice.setTableHeader(null);
		
		//���̺� �÷� ����
		jtOrderList.getColumnModel().getColumn(0).setPreferredWidth(250);
		jtOrderList.getColumnModel().getColumn(1).setPreferredWidth(175);
		jtOrderList.getColumnModel().getColumn(2).setPreferredWidth(70);
		jtOrderList.getColumnModel().getColumn(3).setPreferredWidth(175);
		
		jtOrderTotalPrice.getColumnModel().getColumn(0).setPreferredWidth(425);
		jtOrderTotalPrice.getColumnModel().getColumn(1).setPreferredWidth(245);
		
		//���̺� �� ����
		jtOrderList.setRowHeight(30);
		jtOrderTotalPrice.setRowHeight(35);
		
		//�����ϸ� �� �ٲ�°� ����
		jtOrderTotalPrice.setCellSelectionEnabled(false);
		
		//�÷� ���� ���� �Ұ�
		jtOrderList.getTableHeader().setReorderingAllowed(false);
		jtOrderList.getTableHeader().setResizingAllowed(false);
		
		//������Ʈ ��ġ
		add(jbtnCheckOut);
		add(jbtnDeleteOrder);
		add(jbtnExit);
		for(int i =0; i<jbtnMenu.length; i++) {
			add(jbtnMenu[i]);
		}
		
		add(jspOrderList);
		add(jtOrderTotalPrice);
		
		setLayout(null);
		
		jspOrderList.setBounds(60,330,670,200);
		jbtnCheckOut.setBounds(210,590,100,40);
		jbtnDeleteOrder.setBounds(340,590,100,40);
		jbtnExit.setBounds(470,590,100,40);
		
		int x = 60;
		for(int i = 0; i<4; i++) {
			jbtnMenu[i].setBounds(x, 30, 160, 140);
			x+=170;
		}//end for
		
		int y = 60;
		for(int i=4; i<8; i++) {
			jbtnMenu[i].setBounds(y, 180, 160, 140);
			y+=170;
		}//end for
		
		jtOrderTotalPrice.setBounds(60,540,670,35);
		
		//�̺�Ʈ
		SCUSnackMenuController ssmc = new SCUSnackMenuController(this);
		addWindowListener(ssmc);
		jbtnCheckOut.addActionListener(ssmc);
		jbtnDeleteOrder.addActionListener(ssmc);
		jbtnExit.addActionListener(ssmc);
		
		for(int i =0; i<jbtnMenu.length; i++) {
			jbtnMenu[i].addActionListener(ssmc);
		}//end for
		
		//â ����
		setBounds(smv.getX()+100, smv.getY()+80, 800, 680);
		setResizable(false);
		setVisible(true);
		
	}//SCUSnackMenuView
	
	public SCUMainView getSmv() {
		return smv;
	}

	public DefaultTableModel getDtmOrderList() {
		return dtmOrderList;
	}

	public DefaultTableModel getDtmOrderTotalPrice() {
		return dtmOrderTotalPrice;
	}

	public JTable getJtOrderList() {
		return jtOrderList;
	}

	public JTable getJtOrderTotalPrice() {
		return jtOrderTotalPrice;
	}

	public JButton getJbtnCheckOut() {
		return jbtnCheckOut;
	}

	public JButton getJbtnDeleteOrder() {
		return jbtnDeleteOrder;
	}

	public JButton getJbtnExit() {
		return jbtnExit;
	}

	public JButton[] getJbtnMenu() {
		return jbtnMenu;
	}

	public void setJbtnMenu(JButton[] jbtnMenu) {
		this.jbtnMenu = jbtnMenu;
	}

}//class
