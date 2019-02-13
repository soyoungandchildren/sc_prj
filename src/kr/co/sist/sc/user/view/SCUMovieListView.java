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
		super(smv, "����", true);
		this.smv = smv;
		
		
		//������Ʈ ����
		jbtnDetail = new JButton("�� ����");
		jbtnRating = new JButton("���� ����");
		jbtnBooking = new JButton("�����ϱ�");
		jbtnExit = new JButton("������");
		
		String[] columnNames = {"����","������","��ȭ ����", "���", "���� ����", "����", "�帣", "����Ÿ��", "������", "�⿬��", "��ȭ�ڵ�"};
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
		
		//������Ʈ ����
		jtMovieTable.getTableHeader().setReorderingAllowed(false);
		jtMovieTable.setRowHeight(120);
		
		//������Ʈ ��ġ
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
		
		//�̺�Ʈ ���
		SCUMovieListController smlc = new SCUMovieListController(this);
		addWindowListener(smlc);
		jbtnDetail.addActionListener(smlc);
		jbtnBooking.addActionListener(smlc);
		jbtnRating.addActionListener(smlc);
		jbtnExit.addActionListener(smlc);
		
		//â ����
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