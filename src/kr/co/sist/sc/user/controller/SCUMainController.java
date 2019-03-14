package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import kr.co.sist.sc.user.model.SCUMainDAO;
import kr.co.sist.sc.user.view.SCULoginView;
import kr.co.sist.sc.user.view.SCUMainView;
import kr.co.sist.sc.user.view.SCUMovieListView;
import kr.co.sist.sc.user.view.SCUMyPageView;
import kr.co.sist.sc.user.view.SCURefundView;
import kr.co.sist.sc.user.view.SCUSnackMenuView;
import kr.co.sist.sc.user.vo.SCULoginVO;
import kr.co.sist.sc.user.vo.SCUMainVO;

public class SCUMainController extends WindowAdapter implements ActionListener, Runnable{
   private SCUMainView smv;
   private SCUMainDAO smDAO;
   private Integer[] cnt;
   private int cntCheckPassword;
   private Thread t;
   private StringBuilder ranking;
   
   public SCUMainController(SCUMainView smv) {
      this.smv = smv;
      smDAO = SCUMainDAO.getInstance();
      setImgBoard();
      runThread();
   }//Constructor
   
   public void runThread() {
      t = new Thread(this);
      t.start();
   }//runThread
   
   @Override
   public void run() {
	   
	  SCUClientThreadHelper scth = new SCUClientThreadHelper(ranking.toString(), smv.getJtaBookingRank());
      while(true) {
         try {
            setImgBoard();
            scth.setText(ranking.toString());
            Thread.sleep(1000*10);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }//end catch
      }//end while
   }//run
   
   public void setImgBoard() {
      try {
         List<SCUMainVO> list = smDAO.searchSetImgBoard();
         String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/movie/m_movie_";
         
         //영화별 누적 관람객 수
         cnt = new Integer[list.size()];
         for(int i=0; i<list.size(); i++) {
            cnt[i] = list.get(i).getCnt();
         }//end for
         
         //상단 영화 순위
         ranking = new StringBuilder();
         ranking.append("                                                                                 ")
         		.append(" 1위 - [ "+list.get(0).getMovie_title()+" ]         ")
               .append("2위 - [ "+list.get(1).getMovie_title()+" ]         ")
               .append("3위 - [ "+list.get(2).getMovie_title()+" ]         ")
               .append("4위 - [ "+list.get(3).getMovie_title()+" ]         ")
               .append("5위 - [ "+list.get(4).getMovie_title()+" ]         ")
               .append("6위 - [ "+list.get(5).getMovie_title()+" ]         ")
               .append("                                                                                                                                           ");
         
         //가운데 영화 랭킹
         //1위 정보
         smv.getJlbRank1().setText(list.get(0).getMovie_title());
         smv.getJlbRankAudience1().setText("누적 관객 수 : "+String.valueOf(list.get(0).getAudience())+"명");
         
         //2위 정보
         smv.getJlbRank2().setText(list.get(1).getMovie_title());
         smv.getJlbRankAudience2().setText("누적 관객 수 : "+String.valueOf(list.get(1).getAudience())+"명");
         
         //3위 정보
         smv.getJlbRank3().setText(list.get(2).getMovie_title());
         smv.getJlbRankAudience3().setText("누적 관객 수 : "+String.valueOf(list.get(2).getAudience())+"명");
         
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
         String rowCnt = smDAO.searchRankMovie();
         int bookingCnt = Integer.parseInt(rowCnt);
         
         String rank1 = String.format("%.2f",(double)cnt[0]/(double)bookingCnt*100.0);
         String rank2 = String.format("%.2f",(double)cnt[1]/(double)bookingCnt*100.0);
         String rank3 = String.format("%.2f",(double)cnt[2]/(double)bookingCnt*100.0);
         
         smv.getJlbRankRate1().setText("예매율 : "+rank1+"%");
         smv.getJlbRankRate2().setText("예매율 : "+rank2+"%");
         smv.getJlbRankRate3().setText("예매율 : "+rank3+"%");
         
      } catch (SQLException se) {
         se.printStackTrace();
      }//end catch
   }//rankMovie
   
   public void checkPassword() {
      if(cntCheckPassword==5) {
         JOptionPane.showMessageDialog(smv, "나중에 다시 시도해주세요");
         cntCheckPassword=0;
         return;
      }//end if
      
      JPasswordField jpf = new JPasswordField();
      int commit = JOptionPane.showConfirmDialog(smv, jpf, "패스워드 입력", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
      if(commit==0) {
         String password = new String(jpf.getPassword());
         
         boolean checkPassword = false;
         try {
            SCULoginVO slVO = new SCULoginVO(smv.getIdConnecting(), password);
            checkPassword = SCUMainDAO.getInstance().checkPassword(slVO);
            
            if(checkPassword) {
               cntCheckPassword = 0;
               new SCUMyPageView(smv);
            }else {
               JOptionPane.showMessageDialog(smv, "비밀번호를 확인해주세요.");
               cntCheckPassword+=1;
               checkPassword();
            }
            
         } catch (SQLException e) {
            e.printStackTrace();
         }//try~catch
         
      }//end if
   }//checkPassword
   
   @Override
   public void actionPerformed(ActionEvent ae) {
      
      if(ae.getSource().equals(smv.getJbtnLogin())) {//로그인버튼
         if(!smv.getIsLogin()) {
            new SCULoginView(smv);
         }else {
        	int confirmLogout = JOptionPane.showConfirmDialog(smv, "정말 로그아웃 하시겠습니까?", "로그아웃", JOptionPane.OK_CANCEL_OPTION);
        	switch (confirmLogout) {
			case JOptionPane.OK_OPTION:
				smv.setIsLogin(false);
				smv.setIdConnecting("");
				smv.getJbtnLogin().setIcon(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_login_join(215x40).png"));;
				break;
			case JOptionPane.CANCEL_OPTION:
				return;
			}
         }//end else
      }//end if
      
      if(ae.getSource().equals(smv.getJbtnBooking())) {//예매 버튼
         new SCUMovieListView(smv);
      }//end if
      
      if(ae.getSource().equals(smv.getJbtnSnack())) {//스낵 버튼
         new SCUSnackMenuView(smv);
      }//end if
      
      if(ae.getSource().equals(smv.getJbtnMyPage())) {
         if(smv.getIsLogin()) {
            checkPassword();
         }else {
            JOptionPane.showMessageDialog(smv, "로그인을 해주세요.");
         }
      }//end if
      
      if(ae.getSource().equals(smv.getJbtnRefund())) {//환불
         if(smv.getIsLogin()) {
            new SCURefundView(smv);
         }else {
            JOptionPane.showMessageDialog(smv, "로그인을 해주세요.");
            new SCULoginView(smv);
         }//end else
      }//end if
   }//actionPerformed
   
   @Override
   public void windowClosing(WindowEvent we) {
      smv.dispose();
   }//windowClosing
   
   @Override
   public void windowClosed(WindowEvent e) {
      System.exit(0);
   }//windowClosed
}//Class