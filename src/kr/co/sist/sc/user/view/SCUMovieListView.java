package kr.co.sist.sc.user.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import kr.co.sist.sc.user.controller.SCUMovieListController;
import kr.co.sist.sc.user.images.CustomFontList;

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
		
		JLabel jlblTitle = new JLabel("��ȭ ����");
		jlblTitle.setFont(CustomFontList.getInstance().getFontTitle());
		jlblTitle.setForeground(Color.white);
		
		
		//������Ʈ ����
		jbtnDetail = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_detail_info(125x40).png"));
		jbtnRating = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_read_review(125x40).png"));
		jbtnBooking = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_book(125x40).png"));
		jbtnExit = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_close(125x40).png"));
		
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
		//105,153
		JScrollPane jspMovieTable = new JScrollPane(jtMovieTable);
		
		
		//������Ʈ ����
		jtMovieTable.getTableHeader().setReorderingAllowed(false);
		jtMovieTable.getTableHeader().setResizingAllowed(false);
		jtMovieTable.setRowHeight(153);
		jtMovieTable.getColumn("������").setWidth(105);
		jtMovieTable.getColumn("��ȭ�ڵ�").setMaxWidth(0);
		jtMovieTable.getColumn("��ȭ�ڵ�").setMinWidth(0);
		jtMovieTable.getColumn("��ȭ�ڵ�").setPreferredWidth(0);
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
		
		
		//������Ʈ ��ġ
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
		
		//�̺�Ʈ ���
		SCUMovieListController smlc = new SCUMovieListController(this);
		addWindowListener(smlc);
		jbtnDetail.addActionListener(smlc);
		jbtnBooking.addActionListener(smlc);
		jbtnRating.addActionListener(smlc);
		jbtnExit.addActionListener(smlc);
		
		//â ����
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
			setOpaque(false);
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
