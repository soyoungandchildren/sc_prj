package kr.co.sist.sc.user.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
		jbtnDetail = new JButton("상세 정보");
		jbtnRating = new JButton("평점 보기");
		jbtnBooking = new JButton("예매하기");
		jbtnExit = new JButton("나가기");
		
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
		jtMovieTable.setRowHeight(120);
		
		//컴포넌트 배치
		setLayout(null);
		
		jspMovieTable.setBounds(20, 20, 840, 440);
		jbtnDetail.setBounds(105, 470, 150, 50);
		jbtnBooking.setBounds(285, 470, 150, 50);
		jbtnRating.setBounds(465 ,470, 150, 50);
		jbtnExit.setBounds(645, 470, 150, 50);
		
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
		setBounds(smv.getX()+80, smv.getY()+50, 900, 600);
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
	
	
	
}//Class
