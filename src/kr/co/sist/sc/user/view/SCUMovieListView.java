package kr.co.sist.sc.user.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.user.controller.SCUMovieListController;
import kr.co.sist.sc.user.util.CustomFontList;
import kr.co.sist.sc.user.util.CustomTableRenderer;

@SuppressWarnings("serial")
public class SCUMovieListView extends JDialog {

	private SCUMainView smv;
	private DefaultTableModel dtmMovieTable;
	private JTable jtMovieTable;
	private JButton jbtnDetail, jbtnRating, jbtnBooking, jbtnExit;
	private String selectedMovieTitle, selectedMovieCode;
	
	public SCUMovieListView(SCUMainView smv) {
		super(smv, "영화 목록", true);
		this.smv = smv;
		
		JLabel jlblTitle = new JLabel("영화 예매");
		jlblTitle.setFont(CustomFontList.getInstance().getFontTitle());
		jlblTitle.setForeground(Color.white);
		
		
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
		
		//컴포넌트 설정
		for(int i = 0; i<jtMovieTable.getColumnCount(); i++) {
			if(i==1) {
				continue;
			}
			
			jtMovieTable.getColumnModel().getColumn(i).setCellRenderer(CustomTableRenderer.applyRenderer());
		}
		
		JScrollPane jspMovieTable = new JScrollPane(jtMovieTable);
		
		jspMovieTable.getViewport().setOpaque(false);
		jspMovieTable.setOpaque(false);
		jtMovieTable.setOpaque(false);
		
		jtMovieTable.getTableHeader().setReorderingAllowed(false);
		jtMovieTable.getTableHeader().setResizingAllowed(false);	;
		jtMovieTable.getTableHeader().setPreferredSize(new Dimension(10, 35));
		jtMovieTable.getTableHeader().setFont(CustomFontList.getInstance().getFontRank());
		
		jtMovieTable.setRowHeight(153);
		jtMovieTable.getColumn("순위").setPreferredWidth(10);
		jtMovieTable.getColumn("영화 제목").setPreferredWidth(100);
		jtMovieTable.getColumn("영화코드").setMaxWidth(0);
		jtMovieTable.getColumn("영화코드").setMinWidth(0);
		jtMovieTable.getColumn("영화코드").setPreferredWidth(0);
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
		
		jspMovieTable.setBounds(40, 80, 910, 600);
		jbtnDetail.setBounds(500-125-30-125-60, 800-40-55, 125, 40);
		jbtnBooking.setBounds(500-125-30, 705, 125, 40);
		jbtnRating.setBounds(500+30 ,705, 125, 40);
		jbtnExit.setBounds(500+30+60+125, 705, 125, 40);
		
		jlblTitle.setBounds(40, 10, 200, 70);
		
		add(jspMovieTable);
		add(jbtnDetail);
		add(jbtnBooking);
		add(jbtnRating);
		add(jbtnExit);
		
		add(jlblTitle);
		
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
	
	
	
}//Class
