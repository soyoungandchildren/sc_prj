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
		super(smv, "스낵 주문", true);
		this.smv = smv;
		
		//컴포넌트 생성
		jbtnCheckOut = new JButton("결제");
		jbtnDeleteOrder = new JButton("주문 삭제");
		jbtnExit = new JButton("닫기");
		
		jbtnMenu = new JButton[8];
		for(int i =0; i<jbtnMenu.length; i++) {
			jbtnMenu[i] = new JButton();
		}

		String[] columnNames = {"스낵명", "가격", "수량", "총 가격"};
		dtmOrderList = new DefaultTableModel(columnNames, 0) {
		};
		
		jtOrderList = new JTable(dtmOrderList) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
			//JTable에 사진 가져오기
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};
		JScrollPane jspOrderList = new JScrollPane(jtOrderList);
		
		//합계 테이블
		String[] temp = {"합계", "총 가격"};
		dtmOrderTotalPrice = new DefaultTableModel(temp, 0); {
		};
		
		jtOrderTotalPrice = new JTable(dtmOrderTotalPrice) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		
		Object[] rowData = new Object[2];
		rowData[0] = "합 계";
		rowData[1] = "0";
		
		dtmOrderTotalPrice.addRow(rowData);
		jtOrderTotalPrice.setTableHeader(null);
		
		//테이블 컬럼 길이
		jtOrderList.getColumnModel().getColumn(0).setPreferredWidth(250);
		jtOrderList.getColumnModel().getColumn(1).setPreferredWidth(175);
		jtOrderList.getColumnModel().getColumn(2).setPreferredWidth(70);
		jtOrderList.getColumnModel().getColumn(3).setPreferredWidth(175);
		
		jtOrderTotalPrice.getColumnModel().getColumn(0).setPreferredWidth(425);
		jtOrderTotalPrice.getColumnModel().getColumn(1).setPreferredWidth(245);
		
		//테이블 행 높이
		jtOrderList.setRowHeight(30);
		jtOrderTotalPrice.setRowHeight(35);
		
		//선택하면 색 바뀌는거 해제
		jtOrderTotalPrice.setCellSelectionEnabled(false);
		
		//컬럼 길이 수정 불가
		jtOrderList.getTableHeader().setReorderingAllowed(false);
		jtOrderList.getTableHeader().setResizingAllowed(false);
		
		//컴포넌트 배치
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
		
		//이벤트
		SCUSnackMenuController ssmc = new SCUSnackMenuController(this);
		addWindowListener(ssmc);
		jbtnCheckOut.addActionListener(ssmc);
		jbtnDeleteOrder.addActionListener(ssmc);
		jbtnExit.addActionListener(ssmc);
		
		for(int i =0; i<jbtnMenu.length; i++) {
			jbtnMenu[i].addActionListener(ssmc);
		}//end for
		
		//창 설정
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
