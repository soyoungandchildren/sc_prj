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
import kr.co.sist.sc.user.util.CustomFontList;

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

		String[] columnNames = {"스낵명", "가격", "수량", "총 가격"};
		dtmOrderList = new DefaultTableModel(columnNames, 0) {
		};
		
		jtOrderList = new JTable(dtmOrderList) {
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
			
			//JTable에 사진 가져오기
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};
		JScrollPane jspOrderList = new JScrollPane(jtOrderList);
		
		//주문 목록테이블 색상
		jtOrderList.setOpaque(false);
		jspOrderList.setOpaque(false);
		jspOrderList.getViewport().setOpaque(false);
		
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
		
		//합계 테이블 색상
		
		Object[] rowData = new Object[2];
		rowData[0] = "합 계";
		rowData[1] = "0";
		
		dtmOrderTotalPrice.addRow(rowData);
		jtOrderTotalPrice.setTableHeader(null);
		jtOrderTotalPrice.setFont(CustomFontList.getInstance().getFontLabel());
		
		//테이블 가운데 정렬
		DefaultTableCellRenderer dtcrSort = new DefaultTableCellRenderer();
		dtcrSort.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmTab1 = jtOrderList.getColumnModel();
		TableColumnModel tcmTab2 = jtOrderTotalPrice.getColumnModel();
		
		//주문 목록 가운데 정렬
		for(int i=0; i<tcmTab1.getColumnCount(); i++) {
			tcmTab1.getColumn(i).setCellRenderer(dtcrSort);
		}//end for
		
		//합계 가운데 정렬
		for(int i=0; i<tcmTab2.getColumnCount(); i++) {
			tcmTab2.getColumn(i).setCellRenderer(dtcrSort);
		}//end for
		
		//버튼 테두리 없애기
		jbtnCheckOut.setContentAreaFilled(false);
		jbtnCheckOut.setBorderPainted(false);
		jbtnDeleteOrder.setContentAreaFilled(false);
		jbtnDeleteOrder.setBorderPainted(false);
		jbtnExit.setContentAreaFilled(false);
		jbtnExit.setBorderPainted(false);
		
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
