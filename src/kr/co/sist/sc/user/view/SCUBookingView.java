package kr.co.sist.sc.user.view;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.user.controller.SCUBookingController;

@SuppressWarnings("serial")
public class SCUBookingView extends JDialog{
	
	private SCUMovieListView smlv;
	private String movieCode;
	private DefaultComboBoxModel<String> dcbmDate, dcbmPersonnel;
	private JComboBox<String> jcbDate, jcbPersonnel;
	private JRadioButton jrbAll, jrbStandard, jrbPremium;
	private DefaultTableModel dtmOnScreen;
	private JButton jbtnCheckSeat,jbtnExit;
	private JTable jtOnScreen;
	private String selectedScreenNum, selectedScreenName, selectedScreenStartTime, selectedScreenStartDate;
	private int selectedPersonnel;
	

	public SCUBookingView(SCUMovieListView smlv, String movieCode) {
		super(smlv, "예매하기", true);
		
		this.smlv=smlv;
		this.movieCode = movieCode;
		
		//컴포넌트 생성
		JLabel jlblTitle = new JLabel("["+smlv.getSelectedMovieTitle()+"] 예매하기");
		JLabel jlblPersonnel = new JLabel("인원 수");
		
		dcbmDate = new DefaultComboBoxModel<>();
		jcbDate = new JComboBox<>(dcbmDate);
		
		jrbAll = new JRadioButton("전체");
		jrbStandard = new JRadioButton("일반");
		jrbPremium = new JRadioButton("프리미엄");
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrbAll);
		bg.add(jrbStandard);
		bg.add(jrbPremium);
		
		dcbmPersonnel = new DefaultComboBoxModel<>();
		jcbPersonnel = new JComboBox<>(dcbmPersonnel);
		
		String[] columnNames = {"상영일시", "시작 시간", "끝나는 시간", "상영관 종류", "잔여 좌석", "상영코드"};
		dtmOnScreen = new DefaultTableModel(columnNames, 0);
		jtOnScreen = new JTable(dtmOnScreen) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane jspOnScreen = new JScrollPane(jtOnScreen);
		
		jbtnCheckSeat = new JButton("좌석 확인");
		jbtnExit = new JButton("닫기");
		
		//컴포넌트 설정
		jtOnScreen.setRowHeight(30);
		jtOnScreen.getTableHeader().setReorderingAllowed(false);
		jcbPersonnel.setEnabled(false);
		
		jtOnScreen.getColumn("상영코드").setMaxWidth(0);
		jtOnScreen.getColumn("상영코드").setMinWidth(0);
		jtOnScreen.getColumn("상영코드").setPreferredWidth(0);
		jtOnScreen.getTableHeader().setResizingAllowed(false);
		jtOnScreen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		//배치
		setLayout(null);
		
		jlblTitle.setBounds(15, 15, 200, 40);
		jcbDate.setBounds(30, 60, 200, 25);
		jrbAll.setBounds(30, 90, 70, 25);
		jrbStandard.setBounds(110, 90, 70, 25);
		jrbPremium.setBounds(190, 90, 100, 25);
		jcbPersonnel.setBounds(290, 60, 110, 25);
		jspOnScreen.setBounds(30, 120, 520, 470);
		jbtnCheckSeat.setBounds(420, 50, 130, 45);
		jbtnExit.setBounds(240, 600, 120, 30);
		jlblPersonnel.setBounds(240, 60, 50, 25);
		
		
		
		add(jlblTitle);
		add(jcbDate);
		add(jrbAll);
		add(jrbStandard);
		add(jrbPremium);
		add(jcbPersonnel);
		add(jspOnScreen);
		add(jbtnCheckSeat);
		add(jbtnExit);
		add(jlblPersonnel);
		
		//이벤트 등록
		SCUBookingController sbc = new SCUBookingController(this);
		jcbDate.addActionListener(sbc);
		jrbStandard.addActionListener(sbc);
		jrbPremium.addActionListener(sbc);
		jrbAll.addActionListener(sbc);
		jbtnCheckSeat.addActionListener(sbc);
		jbtnExit.addActionListener(sbc);
		jtOnScreen.addMouseListener(sbc);
		jcbPersonnel.addActionListener(sbc);
		
		
		//창 설정
		setResizable(false);
		setBounds(smlv.getX()+70, smlv.getY()-50, 600, 700);
		setVisible(true);
		
	}//Constructor

	public SCUMovieListView getSmlv() {
		return smlv;
	}
	public String getMovieCode() {
		return movieCode;
	}
	public DefaultComboBoxModel<String> getDcbmDate() {
		return dcbmDate;
	}
	public JRadioButton getJrbStandard() {
		return jrbStandard;
	}
	public JRadioButton getJrbPremium() {
		return jrbPremium;
	}
	public DefaultTableModel getDtmOnScreen() {
		return dtmOnScreen;
	}
	public JButton getJbtnCheckSeat() {
		return jbtnCheckSeat;
	}
	public JButton getJbtnExit() {
		return jbtnExit;
	}
	public JRadioButton getJrbAll() {
		return jrbAll;
	}
	public JComboBox<String> getJcbDate() {
		return jcbDate;
	}
	public DefaultComboBoxModel<String> getDcbmPersonnel() {
		return dcbmPersonnel;
	}
	public JComboBox<String> getJcbPersonnel() {
		return jcbPersonnel;
	}	
	public JTable getJtOnScreen() {
		return jtOnScreen;
	}
	public String getSelectedScreenNum() {
		return selectedScreenNum;
	}
	public void setSelectedScreenNum(String selectedScreenNum) {
		this.selectedScreenNum = selectedScreenNum;
	}
	public String getSelectedScreenName() {
		return selectedScreenName;
	}
	public void setSelectedScreenName(String selectedScreenName) {
		this.selectedScreenName = selectedScreenName;
	}
	public int getSelectedPersonnel() {
		return selectedPersonnel;
	}
	public void setSelectedPersonnel(int selectedPersonnel) {
		this.selectedPersonnel = selectedPersonnel;
	}
	public String getSelectedScreenStartTime() {
		return selectedScreenStartTime;
	}
	public void setSelectedScreenStartTime(String selectedScreenStartTime) {
		this.selectedScreenStartTime = selectedScreenStartTime;
	}
	public String getSelectedScreenStartDate() {
		return selectedScreenStartDate;
	}
	public void setSelectedScreenStartDate(String selectedScreenStartDate) {
		this.selectedScreenStartDate = selectedScreenStartDate;
	}
	
	
	
}//Class
