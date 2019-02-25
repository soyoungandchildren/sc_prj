package kr.co.sist.sc.user.view;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import kr.co.sist.sc.user.controller.SCURatingController;

@SuppressWarnings("serial")
public class SCURatingView extends JDialog {

	private SCUMovieListView smlv;
	private JButton jbtnWriteRating, jbtnClose;
	private DefaultTableModel dtmRatingTable;
	private JTable jtRatingTable;
	
	public SCURatingView(SCUMovieListView smlv) {
		super(smlv, smlv.getSelectedMovieTitle()+" 리뷰", true);
		this.smlv = smlv;
		
		jbtnWriteRating = new JButton("한줄 평 쓰기");
		jbtnClose = new JButton("닫기");
		
		String[] columnNames = {"평 점", "한줄평", "작성자"};
		dtmRatingTable = new DefaultTableModel(columnNames, 0);
		jtRatingTable = new JTable(dtmRatingTable) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};
		JScrollPane jspRatingTable = new JScrollPane(jtRatingTable);
		
		StringBuilder sbTitle = new StringBuilder();
		sbTitle.append("[").append(smlv.getSelectedMovieTitle()).append("]").append(" 한줄 평");
		JLabel jlblTitle = new JLabel(sbTitle.toString());
		
		
		jtRatingTable.setRowHeight(100);
		
		
		
		setLayout(null);
		
		jlblTitle.setBounds(10, 10, 200, 30);
		jspRatingTable.setBounds(10, 50, 670, 250);
		jbtnWriteRating.setBounds(215, 315, 130, 30);
		jbtnClose.setBounds(355, 315, 130, 30);
		
		add(jlblTitle);
		add(jspRatingTable);
		add(jbtnWriteRating);
		add(jbtnClose);
		
		SCURatingController src = new SCURatingController(this);
		jbtnWriteRating.addActionListener(src);
		jbtnClose.addActionListener(src);
		
		setResizable(false);
		setBounds(smlv.getX(), smlv.getY(), 700, 400);
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
	
}//Class
