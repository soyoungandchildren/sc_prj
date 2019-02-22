package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;

import kr.co.sist.sc.user.model.SCUMainDAO;
import kr.co.sist.sc.user.view.SCULoginView;
import kr.co.sist.sc.user.view.SCUMainView;
import kr.co.sist.sc.user.view.SCUMovieListView;
import kr.co.sist.sc.user.view.SCUSnackMenuView;
import kr.co.sist.sc.user.vo.SCUMainVO;

public class SCUMainController extends WindowAdapter implements ActionListener{
	private SCUMainView smv;
	private SCUMainDAO smDAO;
	private Integer[] cnt;
	
	public SCUMainController(SCUMainView smv) {
		this.smv = smv;
		smDAO = SCUMainDAO.getInstance();
		setImgBoard();
	}//Constructor
	
	public void setImgBoard() {
		try {
			List<SCUMainVO> list = smDAO.searchSetImgBoard();
			String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/";
			
			cnt = new Integer[list.size()];
			for(int i=0; i<list.size(); i++) {
				cnt[i] = list.get(i).getCnt();
			}//end for
			
			StringBuilder ranking = new StringBuilder();
			ranking.append("1위 - [ "+list.get(0).getMovie_title()+" ] 누적 관람객 "+list.get(0).getAudience()+"명         ")
					.append("2위 - [ "+list.get(1).getMovie_title()+" ] 누적 관람객 "+list.get(1).getAudience()+"명         ")
					.append("3위 - [ "+list.get(2).getMovie_title()+" ] 누적 관람객 "+list.get(2).getAudience()+"명");
			
			smv.getJlblBookingRank().setText(ranking.toString());
			
			smv.getJlblImageBoard1().setIcon(new ImageIcon(imgPath+list.get(0).getMovie_img()));
			smv.getJlblImageBoard2().setIcon(new ImageIcon(imgPath+list.get(1).getMovie_img()));
			smv.getJlblImageBoard3().setIcon(new ImageIcon(imgPath+list.get(2).getMovie_img()));
			
			rankMovie();
		} catch (SQLException se) {
			se.printStackTrace();
		}//end catch
		
	}//setImgBoard
	
	public void rankMovie() {
		try {
			String rowCnt = smDAO.SearchRankMovie();
			int bookingCnt = Integer.parseInt(rowCnt);
			
			
			System.out.println(String.format("%.2f",(double)cnt[1]/(double)bookingCnt*100.0));
			System.out.println("%");
			
		} catch (SQLException se) {
			se.printStackTrace();
		}//end catch
	}//rankMovie
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource().equals(smv.getJbtnLogin())) {//로그인버튼
			if(!smv.getIsLogin()) {
				new SCULoginView(smv);
			}else {
				smv.setIsLogin(false);
				smv.setIdConnecting("");
				smv.getJbtnLogin().setText("로그인/회원가입");
			}
		}
		
		if(ae.getSource().equals(smv.getJbtnBooking())) {//예매 버튼
			new SCUMovieListView(smv);
		}//end if
		
		if(ae.getSource().equals(smv.getJbtnSnack())) {//스낵 버튼
			new SCUSnackMenuView(smv);
		}//end if
		
	}//actionPerformed
	
	@Override
	public void windowClosing(WindowEvent we) {
		smv.dispose();
	}//windowClosing
	
}//Class
