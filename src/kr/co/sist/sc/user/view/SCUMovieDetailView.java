package kr.co.sist.sc.user.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.co.sist.sc.user.controller.SCUMovieDetailController;
import kr.co.sist.sc.user.util.CustomFontList;

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
		
		
		jlblDetailTitle.setForeground(Color.WHITE);
		jlblMovieTitle.setForeground(Color.WHITE);
		jlblMovieCode.setForeground(Color.WHITE);
		jlblGenre.setForeground(Color.WHITE);
		jlblCountry.setForeground(Color.WHITE);
		jlblDirector.setForeground(Color.WHITE);
		jlblMovieGrade.setForeground(Color.WHITE);
		jlblRunningTime.setForeground(Color.WHITE);
		jlblPlayDate.setForeground(Color.WHITE);
		jlblActor.setForeground(Color.WHITE);
		jlblSynopsis.setForeground(Color.WHITE);
		
		jlblDetailTitle.setFont(CustomFontList.getInstance().getFontTitle());
		jlblMovieTitle.setFont(CustomFontList.getInstance().getFontLabel());
		jlblMovieCode.setFont(CustomFontList.getInstance().getFontLabel());
		jlblGenre.setFont(CustomFontList.getInstance().getFontLabel());
		jlblCountry.setFont(CustomFontList.getInstance().getFontLabel());
		jlblDirector.setFont(CustomFontList.getInstance().getFontLabel());
		jlblMovieGrade.setFont(CustomFontList.getInstance().getFontLabel());
		jlblRunningTime.setFont(CustomFontList.getInstance().getFontLabel());
		jlblPlayDate.setFont(CustomFontList.getInstance().getFontLabel());
		jlblActor.setFont(CustomFontList.getInstance().getFontLabel());
		jlblSynopsis.setFont(CustomFontList.getInstance().getFontLabel());
		
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
		
		jtfMovieCode.setOpaque(false); 
		jtfMovieTitle.setOpaque(false);
		jtfGenre.setOpaque(false);
		jtfCountry.setOpaque(false);
		jtfDirector.setOpaque(false);
		jtfMovieGrade.setOpaque(false);
		jtfRunningTime.setOpaque(false);
		jtfPlayDate.setOpaque(false);
		jtfActor.setOpaque(false);
		jspJtaSynopsis.setOpaque(false);
		jspJtaSynopsis.getViewport().setOpaque(false);
		jtaSynopsis.setOpaque(false);
		
		jtfMovieCode.setForeground(Color.WHITE);
		jtfMovieTitle.setForeground(Color.WHITE);
		jtfGenre.setForeground(Color.WHITE);
		jtfCountry.setForeground(Color.WHITE);
		jtfDirector.setForeground(Color.WHITE);
		jtfMovieGrade.setForeground(Color.WHITE);
		jtfRunningTime.setForeground(Color.WHITE);
		jtfPlayDate.setForeground(Color.WHITE);
		jtfActor.setForeground(Color.WHITE);
		jtaSynopsis.setForeground(Color.WHITE);
		jtfMovieCode.setFont(CustomFontList.getInstance().getFontLabel());
		jtfMovieTitle.setFont(CustomFontList.getInstance().getFontLabel());
		jtfGenre.setFont(CustomFontList.getInstance().getFontLabel());
		jtfCountry.setFont(CustomFontList.getInstance().getFontLabel());
		jtfDirector.setFont(CustomFontList.getInstance().getFontLabel());
		jtfMovieGrade.setFont(CustomFontList.getInstance().getFontLabel());
		jtfRunningTime.setFont(CustomFontList.getInstance().getFontLabel());
		jtfPlayDate.setFont(CustomFontList.getInstance().getFontLabel());
		jtfActor.setFont(CustomFontList.getInstance().getFontLabel());
		jtaSynopsis.setFont(CustomFontList.getInstance().getFontLabel());
		
		
		//컴포넌트 배치
		setLayout(null);
		
		jtfMovieCode.setBounds(445, 57+7, 315, 25); 
		jtfMovieTitle.setBounds(445, 104+7, 315, 25);
		jtfGenre.setBounds(445, 151+7, 315, 25);
		jtfCountry.setBounds(445, 198+7, 315, 25);
		jtfDirector.setBounds(445, 245+7, 315, 25);
		jtfMovieGrade.setBounds(445, 292+7, 315, 25);
		jtfRunningTime.setBounds(445, 339+7, 315, 25);
		jtfPlayDate.setBounds(445, 386+7, 315, 25);
		jtfActor.setBounds(445, 433+7, 315, 25);
		jspJtaSynopsis.setBounds(55, 510, 710, 210);
		
		jlblDetailTitle.setBounds(30,10,200, 40);
		jlblMovieCode.setBounds(370, 57, 70, 40); 
		jlblMovieTitle.setBounds(370, 104, 70, 40);
		jlblGenre.setBounds(370, 151, 70, 40);
		jlblCountry.setBounds(370, 198, 70, 40);
		jlblDirector.setBounds(370, 245, 70, 40);
		jlblMovieGrade.setBounds(370, 292, 70, 40);
		jlblRunningTime.setBounds(370, 339, 70, 40);
		jlblPlayDate.setBounds(370, 386, 70, 40);
		jlblActor.setBounds(370, 433, 70, 40);
		jlblSynopsis.setBounds(55, 475, 70, 40);
		
		jlblMovieImg.setBounds(55, 60, 288, 413);
		
		jbtnClose.setBounds(830/2-125/2, 730, 125, 40);
		
		
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
