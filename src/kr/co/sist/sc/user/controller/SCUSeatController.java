package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import kr.co.sist.sc.user.model.SCUMovieDAO;
import kr.co.sist.sc.user.view.SCUBookingView;
import kr.co.sist.sc.user.view.SCUSeatView;
import kr.co.sist.sc.user.vo.SCUInsertBookingVO;
import kr.co.sist.sc.user.vo.SCUInsertSeatVO;

public class SCUSeatController implements ActionListener, ItemListener{

	private SCUSeatView ssv;
	private SCUBookingView sbv;
	private String imgType, imgsize;
	private List<Integer> listCheckedSeat;
	
	public SCUSeatController(SCUSeatView ssv) {
		this.ssv = ssv;
		this.sbv = ssv.getSbv();
		
		searchSeat();
	}//Constructor
	
	public void searchSeat() {
		imgType = "";
		imgsize = "";
		
		if(sbv.getSelectedScreenName().equals("일반")) {
			imgType = "s";
			imgsize = "(67x61)";
			
		}else {//프리미엄 관일때
			imgType = "p";
			imgsize = "(77x49)";
		}//if~else
		
		
		try {
			List<Integer> list = SCUMovieDAO.getInstance().selectReservedSeat(sbv.getSelectedScreenName(), sbv.getSelectedScreenNum());
			
			for(int i = 0; i< list.size(); i++) {
				ssv.getJckbSeat()[list.get(i).intValue()-1].setIcon(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_"+imgType+"_seat_unselectable"+imgsize+".png"));
				ssv.getJckbSeat()[list.get(i).intValue()-1].setEnabled(false);
			}//end for
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}//try~catch
		
		
		
	}//searchSeat Method
	
	public void insertBooking() throws SQLException{
		
		String bookNumberChar = "";
		if(sbv.getSelectedScreenName().equals("일반")) {
			bookNumberChar = "N";
		}else {
			bookNumberChar = "P";
		}//end if~else
		
		StringBuilder sbBookNumber = new StringBuilder();
		sbBookNumber.append(bookNumberChar).append(ssv.getSmlv().getSelectedMovieCode());
		StringBuilder sbSelectedScreenStart = new StringBuilder();
		sbSelectedScreenStart.append(sbv.getSelectedScreenStartDate()).append(" ")
			.append(sbv.getSelectedScreenStartTime()).insert(13, ":");
		
		
		SCUInsertBookingVO sibVO = new SCUInsertBookingVO(sbv.getSelectedPersonnel(), 
				sbBookNumber.toString(), sbSelectedScreenStart.toString(), 
				sbv.getSmlv().getSmv().getIdConnecting(), sbv.getSelectedScreenNum());
		
		List<SCUInsertSeatVO> listSisVO = new ArrayList<>();
		SCUInsertSeatVO sisVO = null;
		for(int i = 0; i<listCheckedSeat.size(); i++) {
			sisVO = new SCUInsertSeatVO(sbBookNumber.toString(), listCheckedSeat.get(i));
			listSisVO.add(sisVO);
		}//end for
		
		//고객 등급 조회
		String membership = SCUMovieDAO.getInstance().selectMembership(sbv.getSmlv().getSmv().getIdConnecting());
		
		boolean result = SCUMovieDAO.getInstance().insertBooking(sibVO, listSisVO, sbv.getSelectedScreenName(), membership);
		if(result) {
			JOptionPane.showMessageDialog(ssv, "감사합니다 고갱뉨");
		}else {
			JOptionPane.showMessageDialog(ssv, "시스템 상의 오류로 예매작업이 중단되었습니다.\n나중에 다시 시도해주세요.");
		}//end if~else
		
		ssv.dispose();
		sbv.dispose();
		sbv.getSmlv().dispose();
		
	}//insertBooking Method
	
	
	public void cofirmBook() {
		listCheckedSeat = new ArrayList<>();
		JCheckBox[] jckb = ssv.getJckbSeat();
		
		for(int i = 0; i<jckb.length; i++) {
			if(jckb[i].isSelected()) {
				listCheckedSeat.add(i+1);
			}//end if
		}//end for
		
		if(sbv.getSelectedPersonnel()!=listCheckedSeat.size()) {
			JOptionPane.showMessageDialog(ssv, "좌석을 모두 선택해주세요!");
			return;
		}//end if
		
		StringBuilder sbSeat = new StringBuilder();
		for(int i = 0; i<listCheckedSeat.size();i++) {
			sbSeat.append(listCheckedSeat.get(i)).append("번 ");
		}//end for
		
		StringBuilder sbTime = new StringBuilder(sbv.getSelectedScreenStartTime());
		StringBuilder sbReceipt = new StringBuilder();
		JTextArea jtaReceipt = new JTextArea();
		jtaReceipt.setEditable(false);
		sbTime.insert(2, "시 ").append("분");
		sbReceipt
		.append(sbv.getSmlv().getSmv().getIdConnecting()).append("님의 예매 정보\n")
		.append("======================================\n")
		.append("영화 제목 : ").append(sbv.getSmlv().getSelectedMovieTitle().replaceAll("\n", "")).append("\n")
		.append("날짜 : ").append(sbv.getSelectedScreenStartDate()).append("\n")
		.append("시작 시간 : ").append(sbTime.toString()).append("\n")
		.append("인원 : ").append(sbv.getSelectedPersonnel()).append("\n")
		.append("좌석 번호 : ").append(sbSeat.toString()).append("\n")
		.append("-------------------------------------------------------------------\n")
		.append("위의 정보로 예약을 진행하시겠습니까?")
		;
		jtaReceipt.append(sbReceipt.toString());
		
		int confirm = JOptionPane.showConfirmDialog(ssv, jtaReceipt, "예매확인", JOptionPane.OK_CANCEL_OPTION);
		
		switch (confirm) {
		case JOptionPane.OK_OPTION:
			try {
				insertBooking();
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}
			break;
		case JOptionPane.CANCEL_OPTION:
			break;
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource().equals(ssv.getJbtnExit())) {
			ssv.dispose();
		}//end if
		
		if(ae.getSource().equals(ssv.getJbtnConfirm())) {
			cofirmBook();
		}//end if
		
	}//actionPerformed Override

	@Override
	public void itemStateChanged(ItemEvent ie) {
			
			int cnt = 0;
			for(int i = 0; i<ssv.getJckbSeat().length; i++) {//클릭하려는 인원수가 선택한 인원수를 넘으면 알린다.
			
				if(ssv.getJckbSeat()[i].isSelected()){
					cnt++;
				}//end if
				
				if(cnt>sbv.getSelectedPersonnel()) {
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
