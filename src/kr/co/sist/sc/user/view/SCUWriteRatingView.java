package kr.co.sist.sc.user.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.co.sist.sc.user.controller.SCUWriteRatingController;
import kr.co.sist.sc.user.util.CustomFontList;

@SuppressWarnings("serial")
public class SCUWriteRatingView extends JDialog{
	
	private SCURatingView srv;
	private JComboBox<Integer> jcbRating;
	private JTextArea jtaReview;
	private JButton jbtnCommit, jbtnExit;
	private String bookNumber;
	
	public SCUWriteRatingView(SCURatingView srv, String bookNumber) {
		super(srv, srv.getSmlv().getSelectedMovieTitle(), true);
		
		this.bookNumber = bookNumber;
		
		this.srv = srv;
		StringBuilder sbTitle = new StringBuilder();
		sbTitle.append("[").append(srv.getSmlv().getSelectedMovieTitle()).append("]").append(" 한줄평 쓰기");
		JLabel jlblTitle = new JLabel(sbTitle.toString());
		JLabel jlblRating = new JLabel("평점 : ");
		JLabel jlblInstruction = new JLabel("※한줄평은 100자까지 작성이 가능합니다.");
		
		
		jcbRating = new JComboBox<>();
		jtaReview = new JTextArea();
		JScrollPane jspReview = new JScrollPane(jtaReview);
		jtaReview.setLineWrap(true);
		jbtnCommit = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_write_complete(125x40).png"));
		jbtnExit = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_close(125x40).png"));
		
		
		jbtnCommit.setContentAreaFilled(false);
		jbtnCommit.setBorderPainted(false);
		jbtnExit.setContentAreaFilled(false);
		jbtnExit.setBorderPainted(false);
		
		jlblTitle.setFont(CustomFontList.getInstance().getFontLabel());
		jlblTitle.setForeground(Color.WHITE);
		jlblRating.setFont(CustomFontList.getInstance().getFontLabel());
		jlblRating.setForeground(Color.WHITE);
		jlblInstruction.setFont(CustomFontList.getInstance().getFontNotice());
		jlblInstruction.setForeground(Color.WHITE);
		
		
		for(int i =10; i>=0; i--) {
			jcbRating.addItem(i);
		}//end for
		
		setLayout(null);
		
		jlblRating.setBounds(20, 40, 100, 30);
		jlblTitle.setBounds(20, 0, 400, 40);
		jcbRating.setBounds(70, 43, 80, 25);
		jspReview.setBounds(20, 80, 450, 190);
		jbtnCommit.setBounds(500/2-140, 310, 125, 40);
		jbtnExit.setBounds(500/2+15, 310, 125, 40);
		jlblInstruction.setBounds(20, 275, 300, 25);
		
		add(jlblInstruction);
		add(jlblRating);
		add(jlblTitle);
		add(jcbRating);
		add(jspReview);
		add(jbtnCommit);
		add(jbtnExit);
		
		SCUWriteRatingController swrc = new SCUWriteRatingController(this);
		jbtnCommit.addActionListener(swrc);
		jbtnExit.addActionListener(swrc);
		
		JLabel background = new JLabel(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/user_book_bg4(500x400).png"));
		background.setBounds(0, 0, 500, 400);
		add(background);
		
		setResizable(false);
		setBounds(srv.getX()+150, srv.getY()+50, 500, 400);
		setVisible(true);
		
	}//Constructor

	public SCURatingView getSrv() {
		return srv;
	}

	public JComboBox<Integer> getJcbRating() {
		return jcbRating;
	}

	public JTextArea getJtaReview() {
		return jtaReview;
	}

	public JButton getJbtnCommit() {
		return jbtnCommit;
	}

	public JButton getJbtnExit() {
		return jbtnExit;
	}

	public String getBookNumber() {
		return bookNumber;
	}
	
	

}//class
