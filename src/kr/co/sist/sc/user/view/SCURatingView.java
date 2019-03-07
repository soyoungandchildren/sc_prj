package kr.co.sist.sc.user.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import kr.co.sist.sc.user.controller.SCURatingController;
import kr.co.sist.sc.user.images.CustomFontList;
import kr.co.sist.sc.user.util.CustomTableRenderer;

@SuppressWarnings("serial")
public class SCURatingView extends JDialog {

	private SCUMovieListView smlv;
	private JButton jbtnWriteRating, jbtnClose;
	private DefaultTableModel dtmRatingTable;
	private JTable jtRatingTable;
	
	////
	private JLabel jlblStar;
	////
	
	public SCURatingView(SCUMovieListView smlv) {
		super(smlv, smlv.getSelectedMovieTitle()+" ����", true);
		this.smlv = smlv;
		
		jbtnWriteRating = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_write_review(125x40).png"));
		jbtnClose = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_close(125x40).png"));
		
		String[] columnNames = {"�� ��", "������", "�ۼ���"};
		dtmRatingTable = new DefaultTableModel(columnNames, 0);
		jtRatingTable = new JTable(dtmRatingTable){
			
			
			@Override
			public Class<?> getColumnClass(int column) {
				setOpaque(false);
				return getValueAt(0, 0).getClass();
			}
		};
		JScrollPane jspRatingTable = new JScrollPane(jtRatingTable);
		
		
		StringBuilder sbTitle = new StringBuilder();
		sbTitle.append("[").append(smlv.getSelectedMovieTitle()).append("]").append(" ���� ��");
		JLabel jlblTitle = new JLabel(sbTitle.toString());
		
		jtRatingTable.setOpaque(false);
		jspRatingTable.setOpaque(false);
		jspRatingTable.getViewport().setOpaque(false);
		
		jtRatingTable.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				return null;
			}
		});
		jtRatingTable.getColumnModel().getColumn(1).setCellRenderer(CustomTableRenderer.applyRenderer());
		jtRatingTable.getColumnModel().getColumn(2).setCellRenderer(CustomTableRenderer.applyRenderer());
		
		jtRatingTable.setRowHeight(100);
		
		jbtnClose.setContentAreaFilled(false);
		jbtnClose.setBorderPainted(false);
		jbtnWriteRating.setContentAreaFilled(false);
		jbtnWriteRating.setBorderPainted(false);
		
		jlblTitle.setFont(CustomFontList.getInstance().getFontTitle());
		jlblTitle.setForeground(Color.WHITE);
		
		
		
		setLayout(null);
		
		jlblTitle.setBounds(30, 20, 500, 30);
		jspRatingTable.setBounds(30, 70, 605, 320);
		jbtnWriteRating.setBounds(215, 400, 125, 40);
		jbtnClose.setBounds(355, 400, 125, 40);
		
		add(jlblTitle);
		add(jspRatingTable);
		add(jbtnWriteRating);
		add(jbtnClose);
		
		SCURatingController src = new SCURatingController(this);
		jbtnWriteRating.addActionListener(src);
		jbtnClose.addActionListener(src);
		
		JLabel background = new JLabel(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/user_book_bg3(670x485).png"));
		background.setBounds(0, 0, 670, 485);
		add(background);
		
		setResizable(false);
		setBounds(smlv.getX(), smlv.getY(), 670, 485);
		setVisible(true);
		
	}//Constructor

	public SCUMovieListView getSmlv() {
		return smlv;
	}
	public JButton getJbtnWriteRating() {
		return jbtnWriteRating;
	}
	public JButton getJbtnClose() {
		return jbtnClose;
	}
	public DefaultTableModel getDtmRatingTable() {
		return dtmRatingTable;
	}
	public JTable getJtRatingTable() {
		return jtRatingTable;
	}

	public JLabel getJlblStar() {
		return jlblStar;
	}
	public void setJlblStar(JLabel jlblStar) {
		this.jlblStar = jlblStar;
	}
	
	
	
	
	
}//Class
