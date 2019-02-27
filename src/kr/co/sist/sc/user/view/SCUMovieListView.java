package kr.co.sist.sc.user.view;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import kr.co.sist.sc.user.controller.SCUMovieListController;

@SuppressWarnings("serial")
public class SCUMovieListView extends JDialog {

	private SCUMainView smv;
	private DefaultTableModel dtmMovieTable;
	private JTable jtMovieTable;
	private JButton jbtnDetail, jbtnRating, jbtnBooking, jbtnExit;
	private String selectedMovieTitle, selectedMovieCode;
	
	public SCUMovieListView(SCUMainView smv) {
		super(smv, "예매", true);
		this.smv = smv;
		
		
		//컴포넌트 생성
		jbtnDetail = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_detail_info(125x40).png"));
		jbtnRating = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_read_review(125x40).png"));
		jbtnBooking = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_book(125x40).png"));
		jbtnExit = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_close(125x40).png"));
		
		String[] columnNames = {"순위","포스터","영화 제목", "등급", "누적 관람", "평점", "장르", "러닝타임", "개봉일", "출연진", "영화코드"};
		dtmMovieTable = new DefaultTableModel(columnNames,0) {
		};
		jtMovieTable = new JTable(dtmMovieTable) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
			
		};
		JScrollPane jspMovieTable = new JScrollPane(jtMovieTable);
		//컴포넌트 설정
		jtMovieTable.getTableHeader().setReorderingAllowed(false);
		jtMovieTable.getTableHeader().setResizingAllowed(false);
		jtMovieTable.setRowHeight(120);
		jtMovieTable.getColumn("영화코드").setMaxWidth(0);
		jtMovieTable.getColumn("영화코드").setMinWidth(0);
		jtMovieTable.getColumn("영화코드").setPreferredWidth(0);
		jtMovieTable.getColumnModel().getColumn(9).setCellRenderer(this.new TextTableRenderer());
		jtMovieTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		jbtnDetail.setBorderPainted(false);
		jbtnDetail.setContentAreaFilled(false);
		jbtnBooking.setBorderPainted(false);
		jbtnBooking.setContentAreaFilled(false);
		jbtnExit.setBorderPainted(false);
		jbtnExit.setContentAreaFilled(false);
		jbtnRating.setBorderPainted(false);
		jbtnRating.setContentAreaFilled(false);
		
		
		//컴포넌트 배치
		setLayout(null);
		
		jspMovieTable.setBounds(20, 20, 840, 440);
		jbtnDetail.setBounds(105, 470, 125, 40);
		jbtnBooking.setBounds(285, 470, 125, 40);
		jbtnRating.setBounds(465 ,470, 125, 40);
		jbtnExit.setBounds(645, 470, 125, 40);
		
		add(jspMovieTable);
		add(jbtnDetail);
		add(jbtnBooking);
		add(jbtnRating);
		add(jbtnExit);
		
		//이벤트 등록
		SCUMovieListController smlc = new SCUMovieListController(this);
		addWindowListener(smlc);
		jbtnDetail.addActionListener(smlc);
		jbtnBooking.addActionListener(smlc);
		jbtnRating.addActionListener(smlc);
		jbtnExit.addActionListener(smlc);
		
		//창 설정
		ImageIcon ii = new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/user_book_bg1(1000x800).png");
		JLabel jlbl = new JLabel(ii);
		jlbl.setBounds(0, 0, 1000, 800);
		add(jlbl);
		
		setResizable(false);
		setBounds(smv.getX()+80, smv.getY()+50, 1000, 800);
		setVisible(true);
		
	}//Constructor

	
	//Getters
	public SCUMainView getSmv() {
		return smv;
	}
	public DefaultTableModel getDtmMovieTable() {
		return dtmMovieTable;
	}
	public JTable getJtMovieTable() {
		return jtMovieTable;
	}
	public JButton getJbtnDetail() {
		return jbtnDetail;
	}
	public JButton getJbtnRating() {
		return jbtnRating;
	}
	public JButton getJbtnBooking() {
		return jbtnBooking;
	}
	public JButton getJbtnExit() {
		return jbtnExit;
	}
	public String getSelectedMovieTitle() {
		return selectedMovieTitle;
	}
	public String getSelectedMovieCode() {
		return selectedMovieCode;
	}
	//end Getters
	public void setSelectedMovieTitle(String selectedMovieTitle) {
		this.selectedMovieTitle = selectedMovieTitle;
	}
	public void setSelectedMovieCode(String selectedMovieCode) {
		this.selectedMovieCode = selectedMovieCode;
	}
	
	
	////////////////////////////////////////////////////////////////////////
	class TextTableRenderer extends JTextArea implements TableCellRenderer{
		
		public TextTableRenderer() {
			setOpaque(true);
			setLineWrap(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			
			setText(value.toString());
			return this;
		}
	}
	////////////////////////////////////////////////////////////////////////
	
}//Class
