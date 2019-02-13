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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import kr.co.sist.sc.user.controller.SCUBookingController;

@SuppressWarnings("serial")
public class SCUBookingView extends JDialog{
	
	private SCUMovieListView smlv;
	private String movieCode;
	private DefaultComboBoxModel<String> dcbmDate;
	private JRadioButton jrbStandard, jrbPremium;
	private JTextField jtfPersonnel;
	private DefaultTableModel dtmOnScreen;
	private JButton jbtnCheckSeat,jbtnExit;
	
	public SCUBookingView(SCUMovieListView smlv, String movieCode) {
		super(smlv, "예매하기", true);
		
		this.smlv=smlv;
		this.movieCode = movieCode;
		
		//컴포넌트 생성
		JLabel jlblTitle = new JLabel("["+smlv.getSelectedMovieTitle()+"] 예매하기");
		JLabel jlblPersonnel = new JLabel("인원 수");
		
		dcbmDate = new DefaultComboBoxModel<>();
		JComboBox<String> jcbDate = new JComboBox<>(); 
		
		jrbStandard = new JRadioButton("일반");
		jrbPremium = new JRadioButton("프리미엄");
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrbStandard);
		bg.add(jrbPremium);
		
		jtfPersonnel = new JTextField();
		
		String[] columnNames = {"상영일시", "시작 시간", "끝나는 시간", "상영관 종류"};
		dtmOnScreen = new DefaultTableModel(columnNames, 0);
		JTable jtOnScreen = new JTable(dtmOnScreen) {
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
		
		
		//배치
		setLayout(null);
		
		jlblTitle.setBounds(15, 15, 200, 40);
		jcbDate.setBounds(30, 60, 200, 25);
		jrbStandard.setBounds(30, 90, 70, 25);
		jrbPremium.setBounds(110, 90, 100, 25);
		jtfPersonnel.setBounds(290, 60, 110, 25);
		jspOnScreen.setBounds(30, 120, 520, 470);
		jbtnCheckSeat.setBounds(420, 50, 130, 45);
		jbtnExit.setBounds(240, 600, 120, 30);
		jlblPersonnel.setBounds(240, 60, 50, 25);
		
		
		add(jlblTitle);
		add(jcbDate);
		add(jrbStandard);
		add(jrbPremium);
		add(jtfPersonnel);
		add(jspOnScreen);
		add(jbtnCheckSeat);
		add(jbtnExit);
		add(jlblPersonnel);
		
		//이벤트 등록
		SCUBookingController sbc = new SCUBookingController(this);
		jcbDate.addActionListener(sbc);
		jrbStandard.addActionListener(sbc);
		jrbPremium.addActionListener(sbc);
		jbtnCheckSeat.addActionListener(sbc);
		jbtnExit.addActionListener(sbc);
		
		//창 설정
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
	public JTextField getJtfPersonnel() {
		return jtfPersonnel;
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
}//Class
