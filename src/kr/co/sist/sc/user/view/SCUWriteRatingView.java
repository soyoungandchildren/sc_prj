package kr.co.sist.sc.user.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.co.sist.sc.user.controller.SCUWriteRatingController;

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
		sbTitle.append("[").append(srv.getSmlv().getSelectedMovieTitle()).append("]").append("한줄평 쓰기");
		JLabel jlblTitle = new JLabel(sbTitle.toString());
		
		jcbRating = new JComboBox<>();
		jtaReview = new JTextArea();
		JScrollPane jspReview = new JScrollPane(jtaReview);
		jtaReview.setLineWrap(true);
		jbtnCommit = new JButton("작성");
		jbtnExit = new JButton("닫기");
		
		for(int i =10; i>=0; i--) {
			jcbRating.addItem(i);
		}//end for
		
		setLayout(null);
		
		jlblTitle.setBounds(10, 20, 460, 40);
		jcbRating.setBounds(10, 70, 460, 30);
		jspReview.setBounds(10, 105, 460, 200);
		jbtnCommit.setBounds(145, 310, 100, 50);
		jbtnExit.setBounds(255, 310, 100, 50);
		
		add(jlblTitle);
		add(jcbRating);
		add(jspReview);
		add(jbtnCommit);
		add(jbtnExit);
		
		SCUWriteRatingController swrc = new SCUWriteRatingController(this);
		jbtnCommit.addActionListener(swrc);
		jbtnExit.addActionListener(swrc);
		
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
