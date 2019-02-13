package kr.co.sist.sc.user.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class SCUSnackMenuView extends JDialog{
	private SCUMainView smv;
	private DefaultTableModel dtmOrderList;
	private JTable jtOrderList;
	private JButton jbtnCheckOut, jbtnDeleteOrder, jbtnExit,
				jbtnMenu1, jbtnMenu2, jbtnMenu3, jbtnMenu4, jbtnMenu5, jbtnMenu6, jbtnMenu7, jbtnMenu8; 
	
	public SCUSnackMenuView(SCUMainView smv) {
		super(smv, "스낵 주문", true);
		this.smv = smv;
		
		//컴포넌트 생성
		jbtnCheckOut = new JButton("결제");
		jbtnDeleteOrder = new JButton("주문 삭제");
		jbtnExit = new JButton("닫기");
		jbtnMenu1 = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/normal_popcorn.png"));
		jbtnMenu2 = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/normal_papcorn.png"));
		jbtnMenu3 = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/normal_papcorn.png"));
		jbtnMenu4 = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/normal_papcorn.png"));
		jbtnMenu5 = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/normal_papcorn.png"));
		jbtnMenu6 = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/normal_papcorn.png"));
		jbtnMenu7 = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/normal_papcorn.png"));
		jbtnMenu8 = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/normal_papcorn.png"));

		String[] columnNames = {"미리보기", "스낵명", "가격", "수량", "총 가격"};
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
		
		//테이블 컬럼 길이
		jtOrderList.getColumnModel().getColumn(0).setPreferredWidth(250);
		jtOrderList.getColumnModel().getColumn(1).setPreferredWidth(130);
		jtOrderList.getColumnModel().getColumn(2).setPreferredWidth(110);
		jtOrderList.getColumnModel().getColumn(3).setPreferredWidth(70);
		jtOrderList.getColumnModel().getColumn(4).setPreferredWidth(110);
		
		//테이블 행 높이
		jtOrderList.setRowHeight(70);
		
		//컴포넌트 배치
		setLayout(null);
		
		jbtnCheckOut.setBounds(210,580,100,40);
		jbtnDeleteOrder.setBounds(340,580,100,40);
		jbtnExit.setBounds(470,580,100,40);
		
		jbtnMenu1.setBounds(60,30,160,140);
		jbtnMenu2.setBounds(230,30,160,140);
		jbtnMenu3.setBounds(400,30,160,140);
		jbtnMenu4.setBounds(570,30,160,140);
		jbtnMenu5.setBounds(60,180,160,140);
		jbtnMenu6.setBounds(230,180,160,140);
		jbtnMenu7.setBounds(400,180,160,140);
		jbtnMenu8.setBounds(570,180,160,140);
		
		jspOrderList.setBounds(60,350,670,200);
		
		add(jbtnCheckOut);
		add(jbtnDeleteOrder);
		add(jbtnExit);
		add(jbtnMenu1);
		add(jbtnMenu2);
		add(jbtnMenu3);
		add(jbtnMenu4);
		add(jbtnMenu5);
		add(jbtnMenu6);
		add(jbtnMenu7);
		add(jbtnMenu8);
		
		add(jspOrderList);
		
		
		setBounds(smv.getX()+100, smv.getY()+80, 800, 700);
		setVisible(true);
		
		//이벤트
		
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

	public JButton getJbtnMenu1() {
		return jbtnMenu1;
	}

	public JButton getJbtnMenu2() {
		return jbtnMenu2;
	}

	public JButton getJbtnMenu3() {
		return jbtnMenu3;
	}

	public JButton getJbtnMenu4() {
		return jbtnMenu4;
	}

	public JButton getJbtnMenu5() {
		return jbtnMenu5;
	}

	public JButton getJbtnMenu6() {
		return jbtnMenu6;
	}

	public JButton getJbtnMenu7() {
		return jbtnMenu7;
	}

	public JButton getJbtnMenu8() {
		return jbtnMenu8;
	}
	
}//class
