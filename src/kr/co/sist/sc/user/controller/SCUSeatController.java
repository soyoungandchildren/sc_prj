package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import kr.co.sist.sc.user.model.SCUMovieDAO;
import kr.co.sist.sc.user.view.SCUSeatView;

public class SCUSeatController implements ActionListener, ItemListener{

	private SCUSeatView ssv;
	private String imgType, imgsize;
	
	public SCUSeatController(SCUSeatView ssv) {
		this.ssv = ssv;
		
		searchSeat();
		
	}//Constructor
	
	public void searchSeat() {
		imgType = "";
		imgsize = "";
		
		if(ssv.getSelectedScreenName().equals("일반")) {
			imgType = "s";
			imgsize = "(67x61)";
			
		}else {//프리미엄 관일때
			imgType = "p";
			imgsize = "(77x49)";
		}//if~else
		
		
		try {
			List<Integer> list = SCUMovieDAO.getInstance().selectReservedSeat(ssv.getSelectedScreenName(), ssv.getSelectedScreenNum());
			
			for(int i = 0; i< list.size(); i++) {
				ssv.getJckbSeat()[list.get(i).intValue()-1].setIcon(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_"+imgType+"_seat_unselectable"+imgsize+".png"));
				ssv.getJckbSeat()[list.get(i).intValue()-1].setEnabled(false);
			}//end for
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}//try~catch
		
		
		
	}//searchSeat Method
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource().equals(ssv.getJbtnExit())) {
			ssv.dispose();
		}//end if
		
		
		
	}//actionPerformed Override

	@Override
	public void itemStateChanged(ItemEvent ie) {
			
			int cnt = 0;
			for(int i = 0; i<ssv.getJckbSeat().length; i++) {//클릭하려는 인원수가 선택한 인원수를 넘으면 알린다.
			
				if(ssv.getJckbSeat()[i].isSelected()){
					cnt++;
				}//end if
				
				if(cnt>ssv.getSelectedPersonnel()) {
					JOptionPane.showMessageDialog(ssv, "선택한 좌석수가 관람인원보다 많습니다.");
					return;
				}//end if
			}//end for
			
			
			for(int i = 0; i<ssv.getJckbSeat().length; i++)	{//선택한 좌석을 빨갛게 한다.
					
				if(ssv.getJckbSeat()[i].isSelected()) {
					ssv.getJckbSeat()[i].setIcon(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_"+imgType+"_seat_selected"+imgsize+".png"));
				}else {
					ssv.getJckbSeat()[i].setIcon(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_"+imgType+"_seat_selectable"+imgsize+".png"));
				}//end if~else
				
			}//end for
			
	}//itemStatechanged
	
	
}//Class
