package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import kr.co.sist.sc.user.model.SCUMovieDAO;
import kr.co.sist.sc.user.view.SCUMovieDetailView;
import kr.co.sist.sc.user.vo.SCUMovieDetailVO;

public class SCUMovieDetailController extends WindowAdapter implements ActionListener{
	
	private SCUMovieDetailView smdv;
	
	public SCUMovieDetailController(SCUMovieDetailView smdv) {
		this.smdv = smdv;
		searchMovieData();
	}//Constructor
	
	public void searchMovieData() {
		try {
			SCUMovieDetailVO smdVO = SCUMovieDAO.getInstance().selectMovieDetails(smdv.getMovieCode());
			smdv.getJtfMovieCode().setText(smdv.getMovieCode()); 
			smdv.getJtfMovieTitle().setText(smdVO.getMovie_title());
			smdv.getJtfGenre().setText(smdVO.getGenre());
			smdv.getJtfCountry().setText(smdVO.getCountry());
			smdv.getJtfDirector().setText(smdVO.getDirector());
			smdv.getJtfMovieGrade().setText(smdVO.getMovie_grade());
			smdv.getJtfRunningTime().setText(String.valueOf(smdVO.getRunningtime()+"Ка"));
			smdv.getJtfPlayDate().setText(smdVO.getPlaydate());
			smdv.getJtfActor().setText(smdVO.getActor());
			smdv.getJtaSynopsis().setText(smdVO.getSynopsis());
			
			int xJlblMovieImg = smdv.getJlblMovieImg().getX();
			int yJlblMovieImg = smdv.getJlblMovieImg().getY();
			int wJlblMovieImg = smdv.getJlblMovieImg().getWidth();
			int hJlblMovieImg = smdv.getJlblMovieImg().getHeight();
			smdv.setJlblMovieImg(new JLabel(new ImageIcon(
					"C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/movie/l_movie_"+smdVO.getMovie_img())));
			smdv.getJlblMovieImg().setBounds(xJlblMovieImg, yJlblMovieImg, wJlblMovieImg, hJlblMovieImg);
			smdv.add(smdv.getJlblMovieImg());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end try~catch
	}//searchMovieData Method
	
	@Override
	public void windowClosing(WindowEvent we) {
		smdv.dispose();
	}//windowClosing Override
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource().equals(smdv.getJbtnClose())) {
			smdv.dispose();
		}//end if
		
	}//actionPerformed Override
	
	

}//Class
