package kr.co.sist.sc.user.view;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.sc.user.controller.SCUBookingController;
import kr.co.sist.sc.user.util.CustomFontList;
import kr.co.sist.sc.user.util.CustomTableRenderer;

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
		
		jlblPersonnel.setFont(CustomFontList.getInstance().getFontLabel());
		jlblTitle.setFont(CustomFontList.getInstance().getFontTitle());
		jrbAll.setFont(CustomFontList.getInstance().getFontLabel());
		jrbPremium.setFont(CustomFontList.getInstance().getFontLabel());
		jrbStandard.setFont(CustomFontList.getInstance().getFontLabel());
		jlblPersonnel.setForeground(Color.WHITE);
		jlblTitle.setForeground(Color.WHITE);
		jrbAll.setForeground(Color.WHITE);
		jrbPremium.setForeground(Color.WHITE);
		jrbStandard.setForeground(Color.WHITE);
		
		
		
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
		
		jbtnCheckSeat = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_check_seat(125x40).png"));
		jbtnExit = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_close(125x40).png"));
		
		//컴포넌트 설정
		jtOnScreen.setRowHeight(30);
		jtOnScreen.getTableHeader().setReorderingAllowed(false);
		
		for(int i = 0; i<jtOnScreen.getColumnCount(); i++) {
			jtOnScreen.getColumnModel().getColumn(i).setCellRenderer(CustomTableRenderer.applyRenderer());
		}
		
		jtOnScreen.setOpaque(false);
		jspOnScreen.setOpaque(false);
		jspOnScreen.getViewport().setOpaque(false);
		
		jtOnScreen.getColumn("상영코드").setMaxWidth(0);
		jtOnScreen.getColumn("상영코드").setMinWidth(0);
		jtOnScreen.getColumn("상영코드").setPreferredWidth(0);
		jtOnScreen.getTableHeader().setResizingAllowed(false);
		jtOnScreen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		jrbAll.setBorderPainted(false);
		jrbAll.setContentAreaFilled(false);
		jrbAll.setFocusPainted(false);
		jrbPremium.setBorderPainted(false);
		jrbPremium.setContentAreaFilled(false);
		jrbPremium.setFocusPainted(false);
		jrbStandard.setBorderPainted(false);
		jrbStandard.setContentAreaFilled(false);
		jrbStandard.setFocusPainted(false);
		
		jbtnCheckSeat.setBorderPainted(false);
		jbtnCheckSeat.setContentAreaFilled(false);
		jbtnExit.setBorderPainted(false);
		jbtnExit.setContentAreaFilled(false);
		
		
		jcbDate.setBackground(new Color(20, 34, 65));
		jcbDate.setForeground(Color.WHITE);
		jcbDate.setFont(CustomFontList.getInstance().getFontLabel());
		jcbPersonnel.setBackground(new Color(20, 34, 65));
		jcbPersonnel.setForeground(Color.WHITE);
		jcbPersonnel.setFont(CustomFontList.getInstance().getFontLabel());
		jcbPersonnel.setOpaque(false);
		
		
		jrbAll.setBackground(new Color(20, 34, 65));
		jrbPremium.setBackground(new Color(20, 34, 65));
		jrbStandard.setBackground(new Color(20, 34, 65));
		
		
		//배치
		setLayout(null);
		
		jlblTitle.setBounds(30, 15, 500, 40);
		jcbDate.setBounds(50, 70, 220, 25);
		jrbAll.setBounds(50, 105, 70, 25);
		jrbStandard.setBounds(130, 105, 70, 25);
		jrbPremium.setBounds(210, 105, 100, 25);
		jcbPersonnel.setBounds(345, 70, 110, 25);
		jspOnScreen.setBounds(50, 135, 550, 550);
		jbtnCheckSeat.setBounds(475, 60, 125, 40);
		jbtnExit.setBounds(650/2-125/2, 695, 125, 40);
		jlblPersonnel.setBounds(290, 70, 70, 25);
		
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
		if(sbc.getList().size()==0) {
			JOptionPane.showMessageDialog(smlv, "상영중인 영화가 없습니다.");
			this.dispose();
		}
		
		jcbDate.addActionListener(sbc);
		jrbStandard.addActionListener(sbc);
		jrbPremium.addActionListener(sbc);
		jrbAll.addActionListener(sbc);
		jbtnCheckSeat.addActionListener(sbc);
		jbtnExit.addActionListener(sbc);
		jtOnScreen.addMouseListener(sbc);
		jcbPersonnel.addActionListener(sbc);
		
		//창 설정
		JLabel background = new JLabel(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/user_book_bg5(650x780).png"));
		background.setBounds(0, 0, 650, 780);
		add(background);
		
		setResizable(false);
		setBounds(smlv.getX()+70, smlv.getY()-50, 650, 780);
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
