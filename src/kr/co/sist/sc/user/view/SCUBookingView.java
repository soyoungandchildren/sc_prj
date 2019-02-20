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
		super(smlv, "�����ϱ�", true);
		
		this.smlv=smlv;
		this.movieCode = movieCode;
		
		//������Ʈ ����
		JLabel jlblTitle = new JLabel("["+smlv.getSelectedMovieTitle()+"] �����ϱ�");
		JLabel jlblPersonnel = new JLabel("�ο� ��");
		
		dcbmDate = new DefaultComboBoxModel<>();
		jcbDate = new JComboBox<>(dcbmDate);
		
		jrbAll = new JRadioButton("��ü");
		jrbStandard = new JRadioButton("�Ϲ�");
		jrbPremium = new JRadioButton("�����̾�");
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrbAll);
		bg.add(jrbStandard);
		bg.add(jrbPremium);
		
		dcbmPersonnel = new DefaultComboBoxModel<>();
		jcbPersonnel = new JComboBox<>(dcbmPersonnel);
		
		String[] columnNames = {"���Ͻ�", "���� �ð�", "������ �ð�", "�󿵰� ����", "�ܿ� �¼�", "���ڵ�"};
		dtmOnScreen = new DefaultTableModel(columnNames, 0);
		jtOnScreen = new JTable(dtmOnScreen) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane jspOnScreen = new JScrollPane(jtOnScreen);
		
		jbtnCheckSeat = new JButton("�¼� Ȯ��");
		jbtnExit = new JButton("�ݱ�");
		
		//������Ʈ ����
		jtOnScreen.setRowHeight(30);
		jtOnScreen.getTableHeader().setReorderingAllowed(false);
		jcbPersonnel.setEnabled(false);
		
		jtOnScreen.getColumn("���ڵ�").setMaxWidth(0);
		jtOnScreen.getColumn("���ڵ�").setMinWidth(0);
		jtOnScreen.getColumn("���ڵ�").setPreferredWidth(0);
		jtOnScreen.getTableHeader().setResizingAllowed(false);
		jtOnScreen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		//��ġ
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
		
		//�̺�Ʈ ���
		SCUBookingController sbc = new SCUBookingController(this);
		jcbDate.addActionListener(sbc);
		jrbStandard.addActionListener(sbc);
		jrbPremium.addActionListener(sbc);
		jrbAll.addActionListener(sbc);
		jbtnCheckSeat.addActionListener(sbc);
		jbtnExit.addActionListener(sbc);
		jtOnScreen.addMouseListener(sbc);
		jcbPersonnel.addActionListener(sbc);
		
		
		//â ����
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
