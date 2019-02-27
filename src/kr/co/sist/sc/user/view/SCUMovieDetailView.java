package kr.co.sist.sc.user.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.co.sist.sc.user.controller.SCUMovieDetailController;

@SuppressWarnings("serial")
public class SCUMovieDetailView extends JDialog{
	
	private SCUMovieListView smlv;
	private JButton jbtnClose;
	private JTextField jtfMovieCode, jtfMovieTitle, jtfGenre, jtfCountry,
					   jtfDirector, jtfMovieGrade, jtfRunningTime,
					   jtfPlayDate, jtfActor;
	private JTextArea jtaSynopsis;
	private JLabel jlblMovieImg;
	private String movieCode;
	
	
	public SCUMovieDetailView(SCUMovieListView smlv, String movieCode) {
		super(smlv,"영화 상세 정보", true);
		this.smlv = smlv;
		this.movieCode = movieCode;
		
		//컴포넌트 생성
		jbtnClose = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_close(125x40).png"));
		
		jtfMovieCode = new JTextField(); 
		jtfMovieTitle = new JTextField();
		jtfGenre = new JTextField();
		jtfCountry = new JTextField();
		jtfDirector = new JTextField();
		jtfMovieGrade = new JTextField();
		jtfRunningTime = new JTextField();
		jtfPlayDate = new JTextField();
		jtfActor = new JTextField();
		
		jtaSynopsis = new JTextArea();
		JScrollPane jspJtaSynopsis = new JScrollPane(jtaSynopsis);
		jtaSynopsis.setLineWrap(true);
		
		JLabel jlblDetailTitle = new JLabel("영화 상세 정보");
		JLabel jlblMovieCode = new JLabel("영화 코드");
		JLabel jlblMovieTitle = new JLabel("영화 제목");
		JLabel jlblGenre = new JLabel("장르");
		JLabel jlblCountry = new JLabel("제작 국가");
		JLabel jlblDirector = new JLabel("감독");
		JLabel jlblMovieGrade = new JLabel("영화 등급");
		JLabel jlblRunningTime = new JLabel("러닝 타임");
		JLabel jlblPlayDate = new JLabel("개봉일");
		JLabel jlblActor = new JLabel("출연진");
		JLabel jlblSynopsis = new JLabel("줄거리");
		
		jlblMovieImg = new JLabel("");

		
		//컴포넌트 설정
		jtfMovieCode.setEditable(false); 
		jtfMovieTitle.setEditable(false);
		jtfGenre.setEditable(false);
		jtfCountry.setEditable(false);
		jtfDirector.setEditable(false);
		jtfMovieGrade.setEditable(false);
		jtfRunningTime.setEditable(false);
		jtfPlayDate.setEditable(false);
		jtfActor.setEditable(false);
		
		jtaSynopsis.setEditable(false);
		
		jbtnClose.setBorderPainted(false);
		jbtnClose.setContentAreaFilled(false);
		
		
		//컴포넌트 배치
		setLayout(null);
		
		jtfMovieCode.setBounds(325, 57, 300, 25); 
		jtfMovieTitle.setBounds(325, 97, 300, 25);
		jtfGenre.setBounds(325, 137, 300, 25);
		jtfCountry.setBounds(325, 177, 300, 25);
		jtfDirector.setBounds(325, 217, 300, 25);
		jtfMovieGrade.setBounds(325, 257, 300, 25);
		jtfRunningTime.setBounds(325, 297, 300, 25);
		jtfPlayDate.setBounds(325, 337, 300, 25);
		jtfActor.setBounds(325, 377, 300, 25);
		jspJtaSynopsis.setBounds(20, 435, 660, 160);
		
		jlblDetailTitle.setBounds(20,10,100, 40);
		jlblMovieCode.setBounds(240, 50, 70, 40); 
		jlblMovieTitle.setBounds(240, 90, 70, 40);
		jlblGenre.setBounds(240, 130, 70, 40);
		jlblCountry.setBounds(240, 170, 70, 40);
		jlblDirector.setBounds(240, 210, 70, 40);
		jlblMovieGrade.setBounds(240, 250, 70, 40);
		jlblRunningTime.setBounds(240, 290, 70, 40);
		jlblPlayDate.setBounds(240, 330, 70, 40);
		jlblActor.setBounds(240, 370, 70, 40);
		jlblSynopsis.setBounds(20, 400, 70, 40);
		
		jlblMovieImg.setBounds(20, 60, 185, 260);
		
		jbtnClose.setBounds(300, 610, 125, 40);
		
		
		add(jtfMovieCode);
		add(jtfMovieTitle);
		add(jtfGenre);
		add(jtfCountry);
		add(jtfDirector);
		add(jtfMovieGrade);
		add(jtfRunningTime);
		add(jtfPlayDate);
		add(jtfActor);
		add(jspJtaSynopsis);
		
		add(jlblDetailTitle);
		add(jlblMovieCode);
		add(jlblMovieTitle);
		add(jlblGenre);
		add(jlblCountry);
		add(jlblDirector);
		add(jlblMovieGrade);
		add(jlblRunningTime);
		add(jlblPlayDate);
		add(jlblActor);
		add(jlblSynopsis);
		add(jlblMovieImg);
		
		add(jbtnClose);
		
		//이벤트 등록
		SCUMovieDetailController smdc = new SCUMovieDetailController(this);
		addWindowListener(smdc);
		jbtnClose.addActionListener(smdc);
		
//		jtfMovieCode 
//		jtfMovieTitle
//		jtfGenre
//		jtfCountry
//		jtfDirector
//		jtfMovieGrade
//		jtfRunningTime
//		jtfPlayDate
//		jtfActor
		
		
		JLabel background = new JLabel(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/user_book_bg2(830x820).png"));
		background.setBounds(0, 0, 830, 820);
		add(background);
		
		
		setResizable(false);
		setBounds(smlv.getX()+150, smlv.getY()+30, 830, 820);
		setVisible(true);
	}//Constructor

	//getters
	public SCUMovieListView getSmlv() {
		return smlv;
	}
	public JButton getJbtnClose() {
		return jbtnClose;
	}
	public JTextField getJtfMovieCode() {
		return jtfMovieCode;
	}
	public JTextField getJtfMovieTitle() {
		return jtfMovieTitle;
	}
	public JTextField getJtfGenre() {
		return jtfGenre;
	}
	public JTextField getJtfCountry() {
		return jtfCountry;
	}
	public JTextField getJtfDirector() {
		return jtfDirector;
	}
	public JTextField getJtfMovieGrade() {
		return jtfMovieGrade;
	}
	public JTextField getJtfRunningTime() {
		return jtfRunningTime;
	}
	public JTextField getJtfPlayDate() {
		return jtfPlayDate;
	}
	public JTextField getJtfActor() {
		return jtfActor;
	}
	public JTextArea getJtaSynopsis() {
		return jtaSynopsis;
	}
	public JLabel getJlblMovieImg() {
		return jlblMovieImg;
	}
	public String getMovieCode() {
		return movieCode;
	}
	public void setJlblMovieImg(JLabel jlblMovieImg) {
		this.jlblMovieImg = jlblMovieImg;
	}
	
	
}//Class
