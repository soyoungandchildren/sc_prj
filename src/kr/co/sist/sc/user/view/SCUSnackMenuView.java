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
	private DefaultTableModel dtmOrderList;
	private JTable jtOrderList;
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
		String[][] rowData = {
						{"����", "5000", "1", "5000"},
						{"�ݶ�", "3000", "2", "6000"},
						{"�ݶ�", "3000", "2", "6000"},
						{"�ݶ�", "3000", "2", "6000"},
						{"�ݶ�", "3000", "2", "6000"},
						{"�ݶ�", "3000", "2", "6000"},
						{"�ݶ�", "3000", "2", "6000"},
						{"�ݶ�", "3000", "2", "6000"}
						};
		
		dtmOrderList = new DefaultTableModel(rowData, columnNames) {
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
		
		//���̺� �÷� ����
		jtOrderList.getColumnModel().getColumn(0).setPreferredWidth(250);
		jtOrderList.getColumnModel().getColumn(1).setPreferredWidth(175);
		jtOrderList.getColumnModel().getColumn(2).setPreferredWidth(70);
		jtOrderList.getColumnModel().getColumn(3).setPreferredWidth(175);
		
		//���̺� �� ����
		jtOrderList.setRowHeight(30);
		
		//������Ʈ ��ġ
		setLayout(null);
		
		jbtnCheckOut.setBounds(210,580,100,40);
		jbtnDeleteOrder.setBounds(340,580,100,40);
		jbtnExit.setBounds(470,580,100,40);
		
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
		
		jspOrderList.setBounds(60,350,670,200);
		
		add(jbtnCheckOut);
		add(jbtnDeleteOrder);
		add(jbtnExit);
		for(int i =0; i<jbtnMenu.length; i++) {
			add(jbtnMenu[i]);
		}
		
		add(jspOrderList);
		
		//�̺�Ʈ
		SCUSnackMenuController ssmc = new SCUSnackMenuController(this);
		addWindowListener(ssmc);
		jbtnCheckOut.addActionListener(ssmc);
		jbtnDeleteOrder.addActionListener(ssmc);
		jbtnExit.addActionListener(ssmc);
		
		for(int i =0; i<jbtnMenu.length; i++) {
			jbtnMenu[i].addActionListener(ssmc);
		}
		
		//â ����
		setBounds(smv.getX()+100, smv.getY()+80, 800, 700);
		setVisible(true);
		setResizable(false);
		
	}//SCUSnackMenuView
	
	public SCUMainView getSmv() {
		return smv;
	}

	public DefaultTableModel getDtmOrderList() {
		return dtmOrderList;
	}

	public JTable getJtOrderList() {
		return jtOrderList;
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
