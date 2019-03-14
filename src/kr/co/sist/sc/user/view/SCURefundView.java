package kr.co.sist.sc.user.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.co.sist.sc.user.controller.SCURefundController;
import kr.co.sist.sc.user.util.CustomFontList;
import kr.co.sist.sc.user.util.CustomTableRenderer;

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

		// 컴포넌트 생성
		String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/";

		jtpRefund = new JTabbedPane(); // 탭

		// 영화 예매 환불

		String[] refundColumns = { "예매번호", "영화명", "예매수", "상영일시", "총 가격", "환불 가능여부" };

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

//		jtBookingList.getColumnModel().getColumn(0).setPreferredWidth(10);// 번호
		jtBookingList.getColumnModel().getColumn(0).setPreferredWidth(70);// 예매번호
		jtBookingList.getColumnModel().getColumn(1).setPreferredWidth(60);// 영화명
		jtBookingList.getColumnModel().getColumn(2).setPreferredWidth(30);// 예매수
		jtBookingList.getColumnModel().getColumn(3).setPreferredWidth(100);// 결제일시
		jtBookingList.getColumnModel().getColumn(4).setPreferredWidth(40);// 총가격
		jtBookingList.getColumnModel().getColumn(5).setPreferredWidth(50);// 환불가능여부

		jtBookingList.setRowHeight(50);

		// 스낵 환불
		String[] snackColumns = { "스낵주문번호", "스낵명", "수량", "결제일시", "총 가격", "환불가능여부" };

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

		// 테이블 가운데 정렬
		DefaultTableCellRenderer dtcrSort = new DefaultTableCellRenderer();
		dtcrSort.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmTab1 = jtBookingList.getColumnModel();
		TableColumnModel tcmTab2 = jtSnackList.getColumnModel();

		// 주문 목록 가운데 정렬
		for (int i = 0; i < tcmTab1.getColumnCount(); i++) {
			tcmTab1.getColumn(i).setCellRenderer(dtcrSort);
		} // end for

		// 합계 가운데 정렬
		for (int i = 0; i < tcmTab2.getColumnCount(); i++) {
			tcmTab2.getColumn(i).setCellRenderer(dtcrSort);
		} // end for

//		jtSnackList.getColumnModel().getColumn(0).setPreferredWidth(60);
		jtSnackList.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtSnackList.getColumnModel().getColumn(1).setPreferredWidth(40);
		jtSnackList.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtSnackList.getColumnModel().getColumn(3).setPreferredWidth(100);
		jtSnackList.getColumnModel().getColumn(4).setPreferredWidth(40);

		jtSnackList.setRowHeight(50);

		/////////////////////////////////////////

		// 영화예약 취소
		// 컴포넌트 설정
		for (int i = 0; i < jtBookingList.getColumnCount(); i++) {

			jtBookingList.getColumnModel().getColumn(i).setCellRenderer(new CustomTableRenderer());
		} // end for

		JScrollPane jspBooking = new JScrollPane(jtBookingList);
		jspBooking.setBorder(new TitledBorder("영화예매목록"));
		jspBooking.setBorder(BorderFactory.createLineBorder(Color.white));
		jspBooking.setBackground(new Color(20, 39, 75));
		jspBooking.setBorder(BorderFactory.createLineBorder(Color.white));

		// 레이아웃이 들어간 패널
		JPanel jpBooking = new JPanel();
		jpBooking.setLayout(new BorderLayout());
		jpBooking.add("Center", jspBooking);

		jtBookingList.setBorder(new LineBorder(Color.white));
		jtBookingList.setBackground(new Color(20, 39, 75));
		jtBookingList.setBackground(new Color(20, 39, 75));
		jtBookingList.setOpaque(false);
		jtBookingList.setFont(CustomFontList.getInstance().getFontLabel());

		jtpRefund.add("영화 예매 목록", jpBooking);
		jspBooking.getViewport().setBackground(new Color(20, 39, 75));


		/////////// 스낵///////////////
		// 컴포넌트 설정
		for (int i = 0; i < jtSnackList.getColumnCount(); i++) {
			jtSnackList.getColumnModel().getColumn(i).setCellRenderer(new CustomTableRenderer());
		} // end for

		JScrollPane jspSnack = new JScrollPane(jtSnackList);
		jspSnack.setBorder(new TitledBorder("스낵예약목록"));
		jspSnack.setBorder(BorderFactory.createLineBorder(Color.white));
		jspSnack.setBackground(new Color(20, 39, 75));

		JPanel jpSnack = new JPanel();
		jpSnack.setLayout(new BorderLayout());
		jpSnack.add(jspSnack);

		jtpRefund.add("스낵 구매 목록", jpSnack);
		jtSnackList.setBorder(new LineBorder(Color.white));
		jtSnackList.setBackground(new Color(20, 39, 75));
//		jtSnackList.setOpaque(false);
		jspSnack.getViewport().setBackground(new Color(20, 39, 75));

		
		////////////////////////// 공통 디자인////////////////////////
		
		// 테이블랜더러 추가 (테이블 폰트)
		jtBookingList.getColumnModel().getColumn(0).setCellRenderer(CustomTableRenderer.applyRenderer());

		// 탭 배경색 바꾸기
		jtpRefund.setBackground(new Color(83, 83, 83));
		jtpRefund.setForeground(new Color(255, 255, 255));

		// 버튼
		jbtnRefund = new JButton(new ImageIcon(imgPath + "jbt_delete_order(125x40).png"));
		jbtnExit = new JButton(new ImageIcon(imgPath + "jbt_cancel(125x40).png"));

		// 버튼 테두리 없애기
		jbtnRefund.setContentAreaFilled(false);
		jbtnRefund.setBorderPainted(false);
		jbtnExit.setContentAreaFilled(false);
		jbtnExit.setBorderPainted(false);

		// 컬럼 길이 수정 불가
		jtSnackList.getTableHeader().setReorderingAllowed(false);
		jtSnackList.getTableHeader().setResizingAllowed(false);
		jtBookingList.getTableHeader().setReorderingAllowed(false);
		jtBookingList.getTableHeader().setResizingAllowed(false);

		// 버튼이 들어간 패널
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

		// 탭을 프레임에 배치
		add("Center", jtpRefund);
		add("South", jpBookingSouth);

		// 이벤트!
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
