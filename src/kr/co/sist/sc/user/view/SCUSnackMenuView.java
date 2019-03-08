package kr.co.sist.sc.user.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.co.sist.sc.user.controller.SCUSnackMenuController;
import kr.co.sist.sc.user.images.CustomFontList;
import kr.co.sist.sc.user.util.CustomTableRenderer;

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
		String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/";
		
		jbtnCheckOut = new JButton(new ImageIcon(imgPath+"jbt_payment(125x40).png"));
		jbtnDeleteOrder = new JButton(new ImageIcon(imgPath+"jbt_delete_order(125x40).png"));
		jbtnExit = new JButton(new ImageIcon(imgPath+"jbt_close(125x40).png"));
		
		jbtnMenu = new JButton[8];
		for(int i =0; i<jbtnMenu.length; i++) {
			jbtnMenu[i] = new JButton();
			jbtnMenu[i].setContentAreaFilled(false);
			jbtnMenu[i].setBorderPainted(false);
		}//end for

		String[] columnNames = {"������", "����", "����", "�� ����"};
		dtmOrderList = new DefaultTableModel(columnNames, 0) {
		};
		
		jtOrderList = new JTable(dtmOrderList) {
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
			
			//JTable�� ���� ��������
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};
		JScrollPane jspOrderList = new JScrollPane(jtOrderList);
		
		//�ֹ� ������̺� ����
		jtOrderList.setOpaque(false);
		jspOrderList.setOpaque(false);
		jspOrderList.getViewport().setOpaque(false);
		
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
		
		//�հ� ���̺� ����
		
		Object[] rowData = new Object[2];
		rowData[0] = "�� ��";
		rowData[1] = "0";
		
		dtmOrderTotalPrice.addRow(rowData);
		jtOrderTotalPrice.setTableHeader(null);
		jtOrderTotalPrice.setFont(CustomFontList.getInstance().getFontLabel());
		
		//���̺� ��� ����
		DefaultTableCellRenderer dtcrSort = new DefaultTableCellRenderer();
		dtcrSort.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmTab1 = jtOrderList.getColumnModel();
		TableColumnModel tcmTab2 = jtOrderTotalPrice.getColumnModel();
		
		//�ֹ� ��� ��� ����
		for(int i=0; i<tcmTab1.getColumnCount(); i++) {
			tcmTab1.getColumn(i).setCellRenderer(dtcrSort);
		}//end for
		
		//�հ� ��� ����
		for(int i=0; i<tcmTab2.getColumnCount(); i++) {
			tcmTab2.getColumn(i).setCellRenderer(dtcrSort);
		}//end for
		
		//��ư �׵θ� ���ֱ�
		jbtnCheckOut.setContentAreaFilled(false);
		jbtnCheckOut.setBorderPainted(false);
		jbtnDeleteOrder.setContentAreaFilled(false);
		jbtnDeleteOrder.setBorderPainted(false);
		jbtnExit.setContentAreaFilled(false);
		jbtnExit.setBorderPainted(false);
		
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
		
		jspOrderList.setBounds(55,410,788,200);
		jbtnCheckOut.setBounds(240,695,125,40);
		jbtnDeleteOrder.setBounds(390,695,125,40);
		jbtnExit.setBounds(540,695,125,40);
		
		int x = 55;
		for(int i = 0; i<4; i++) {
			jbtnMenu[i].setBounds(x, 40, 187, 162);
			x+=200;
		}//end for
		
		int y = 55;
		for(int i=4; i<8; i++) {
			jbtnMenu[i].setBounds(y, 215, 187, 162);
			y+=200;
		}//end for
		
		jtOrderTotalPrice.setBounds(55,630,788,35);
		
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
		JLabel background = new JLabel(new ImageIcon(imgPath+"user_snackcorner_bg1(900x800).png"));
		background.setBounds(0, 0, 900, 800);
		add(background);
		
		setBounds(smv.getX()+100, smv.getY()+80, 900, 800);
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
