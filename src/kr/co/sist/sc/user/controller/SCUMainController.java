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
import kr.co.sist.sc.user.view.SCURefundView;
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
			
			//영화별 누적 관람객 수
			cnt = new Integer[list.size()];
			for(int i=0; i<list.size(); i++) {
				cnt[i] = list.get(i).getCnt();
			}//end for
			
			//상단 영화 순위
			StringBuilder ranking = new StringBuilder();
			ranking.append("1위 - [ "+list.get(0).getMovie_title()+" ]         ")
					.append("2위 - [ "+list.get(1).getMovie_title()+" ]         ")
					.append("3위 - [ "+list.get(2).getMovie_title()+" ]         ")
					.append("4위 - [ "+list.get(3).getMovie_title()+" ]         ")
					.append("5위 - [ "+list.get(4).getMovie_title()+" ]         ")
					.append("6위 - [ "+list.get(5).getMovie_title()+" ]         ");
			
			smv.getJlblBookingRank().setText(ranking.toString());
			
//			ranking.append("1위 - [ "+list.get(0).getMovie_title()+" ] 누적 관람객 "+list.get(0).getAudience()+"명         ")
//			.append("2위 - [ "+list.get(1).getMovie_title()+" ] 누적 관람객 "+list.get(1).getAudience()+"명         ")
//			.append("3위 - [ "+list.get(2).getMovie_title()+" ] 누적 관람객 "+list.get(2).getAudience()+"명");
			
			//센터 영화 1~3위 포스터 배치
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
			//bookingCnt는 총 예매 건수
			String rowCnt = smDAO.SearchRankMovie();
			int bookingCnt = Integer.parseInt(rowCnt);
			
			String rank1 = String.format("%.2f",(double)cnt[0]/(double)bookingCnt*100.0);
			String rank2 = String.format("%.2f",(double)cnt[1]/(double)bookingCnt*100.0);
			String rank3 = String.format("%.2f",(double)cnt[2]/(double)bookingCnt*100.0);
			
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
			}//end else
		}//end if
		
		if(ae.getSource().equals(smv.getJbtnBooking())) {//예매 버튼
			new SCUMovieListView(smv);
		}//end if
		
		if(ae.getSource().equals(smv.getJbtnSnack())) {//스낵 버튼
			new SCUSnackMenuView(smv);
		}//end if

		if(ae.getSource().equals(smv.getJbtnRefund())) {//환불 버튼
			new SCURefundView(smv);
		}//end if
		
	}//actionPerformed
	
	@Override
	public void windowClosing(WindowEvent we) {
		smv.dispose();
	}//windowClosing
	
}//Class
