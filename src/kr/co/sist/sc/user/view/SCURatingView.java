package kr.co.sist.sc.user.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import kr.co.sist.sc.user.controller.SCURatingController;
import kr.co.sist.sc.user.util.CustomFontList;
import kr.co.sist.sc.user.util.CustomTableRenderer;

@SuppressWarnings("serial")
public class SCURatingView extends JDialog {

	private SCUMovieListView smlv;
	private JButton jbtnWriteRating, jbtnClose;
	private DefaultTableModel dtmRatingTable;
	private JTable jtRatingTable;
	private String selectedMovieCode;
	private JLabel jlblStar;
	
	public SCURatingView(SCUMovieListView smlv, String selectedMovieCode) {
		super(smlv, smlv.getSelectedMovieTitle()+" 리뷰", true);
		this.smlv = smlv;
		this.selectedMovieCode = selectedMovieCode;
		
		jbtnWriteRating = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_write_review(125x40).png"));
		jbtnClose = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_close(125x40).png"));
		
		String[] columnNames = {"평 점", "한줄평", "작성자"};
		dtmRatingTable = new DefaultTableModel(columnNames, 0);
		jtRatingTable = new JTable(dtmRatingTable) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane jspRatingTable = new JScrollPane(jtRatingTable);
		
		
		StringBuilder sbTitle = new StringBuilder();
		sbTitle.append("[").append(smlv.getSelectedMovieTitle()).append("]").append(" 한줄 평");
		JLabel jlblTitle = new JLabel(sbTitle.toString());
		
		jtRatingTable.setOpaque(false);
		jspRatingTable.setOpaque(false);
		jspRatingTable.getViewport().setOpaque(false);
		jtRatingTable.setEnabled(false);
		jtRatingTable.getTableHeader().setReorderingAllowed(false);
		jtRatingTable.getTableHeader().setResizingAllowed(false);
		
		jtRatingTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		jtRatingTable.getColumnModel().getColumn(0).setMaxWidth(150);
		jtRatingTable.getColumnModel().getColumn(2).setPreferredWidth(110);
		jtRatingTable.getColumnModel().getColumn(2).setMaxWidth(110);
		
		jtRatingTable.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				JLabel jlbl = new JLabel(new ImageIcon(value.toString()));
				table.setOpaque(false);
				return jlbl;
			}
		});
		jtRatingTable.getColumnModel().getColumn(1).setCellRenderer(new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				JTextArea jta = new JTextArea();
				jta.setOpaque(false);
				jta.setLineWrap(true);
				jta.setFont(CustomFontList.getInstance().getFontLabel());
				jta.setForeground(Color.WHITE);
				if(value != null) {
					jta.setText(value.toString());
				}
				return jta;
			}
		});
		jtRatingTable.getColumnModel().getColumn(2).setCellRenderer(CustomTableRenderer.applyRenderer());
		
		jtRatingTable.setRowHeight(91);
		
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
	public String getSelectedMovieCode() {
		return selectedMovieCode;
	}
	
	
	
	
	
}//Class
