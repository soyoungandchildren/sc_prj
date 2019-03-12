package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import kr.co.sist.sc.user.model.SCUMovieDAO;
import kr.co.sist.sc.user.view.SCUBookingView;
import kr.co.sist.sc.user.view.SCUSeatView;
import kr.co.sist.sc.user.vo.SCUSearchScreenVO;

public class SCUBookingController extends WindowAdapter implements ActionListener, MouseListener{
	
	private SCUBookingView sbv;
	private List<SCUSearchScreenVO> list;
	private List<Object[]> listFilter;
	
	
	
	public SCUBookingController(SCUBookingView sbv) {
		this.sbv = sbv;
		searchOnScreen();
		sbv.getJrbAll().setSelected(true);
	}//Constructor
	
	public void searchOnScreen(){
		
		try {
			list = SCUMovieDAO.getInstance().selectScreen(sbv.getMovieCode());
			
			
			Object[] objArr = null;
			Set<String> setDate = new HashSet<>();
			listFilter = new ArrayList<>();
			
			sbv.getDtmOnScreen().setRowCount(0);
			for(int i = 0; i<list.size(); i++) {
				if("0".equals(list.get(i).getRemain_seat())) {//만약 잔여좌석이 0이라면 보여주지 않는다.
					continue;
				}//end if
				
				objArr = new Object[6];
				setDate.add(list.get(i).getScreen_date());
				
				objArr[0] = list.get(i).getScreen_date();
				objArr[1] = list.get(i).getStart_time();
				objArr[2] = list.get(i).getEnd_time();
				switch (list.get(i).getScreen_name()) {
				case "P":
					objArr[3] = "프리미엄";
					break;
				case "N":
					objArr[3] = "일반";
					break;
				}//end switch
				objArr[4] = list.get(i).getRemain_seat();
				objArr[5] = list.get(i).getScreen_num();
				
				
				sbv.getDtmOnScreen().addRow(objArr);
				listFilter.add(objArr);
			}//end for
			
			
			sbv.getDcbmDate().removeAllElements();
			List<String> listDate = new ArrayList<>(setDate);
			Collections.sort(listDate);
			sbv.getDcbmDate().addElement("전체");
			for(int i = 0; i<listDate.size(); i++) {
				sbv.getDcbmDate().addElement(listDate.get(i));
			}
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}//end try~catch
		
	}//searchOnScreen
	
	public void filterDate() {
		
		if(sbv.getJcbDate().getSelectedItem().equals("전체")) {
			searchOnScreen();
		}else {
			
			sbv.getDtmOnScreen().setRowCount(0);
			String selectedDate = String.valueOf(sbv.getJcbDate().getSelectedItem()); 
			listFilter = new ArrayList<>();
			Object[] objArr = null;
			
			for(int i=0; i<list.size(); i++) {
				
				if(list.get(i).getScreen_date().equals(selectedDate)) {
					
					objArr = new Object[6];
					
					objArr[0] = list.get(i).getScreen_date();
					objArr[1] = list.get(i).getStart_time();
					objArr[2] = list.get(i).getEnd_time();
					switch (list.get(i).getScreen_name()) {
					case "P":
						objArr[3] = "프리미엄";
						break;
					case "N":
						objArr[3] = "일반";
						break;
					}//end switch
					objArr[4] = list.get(i).getRemain_seat();
					objArr[5] = list.get(i).getScreen_num();
					
					sbv.getDtmOnScreen().addRow(objArr);
					listFilter.add(objArr);
				}//end if
					
				
			}//end for
			
		}//end if~else
		
	}//filterOnScreen
	
	
	public void filterScreen(String screenType) {
	
		sbv.getDcbmPersonnel().removeAllElements();
		sbv.setSelectedPersonnel(0);
		sbv.getDtmOnScreen().setRowCount(0);
		
			for(int i = 0 ; i< listFilter.size(); i++) {
				
				if(screenType.equals("전체")) {
					sbv.getDtmOnScreen().addRow(listFilter.get(i));
				}else {
					if(listFilter.get(i)[3].equals(screenType)) {
						sbv.getDtmOnScreen().addRow(listFilter.get(i));
					}//end if
				}//end if
				
			}//end for
	}//fileterScreen
	
	public boolean checkHoldPoint(String screenName) {
		boolean checkHoldPoint = false;
		
		String memberID = sbv.getSmlv().getSmv().getIdConnecting();
		int personnel = sbv.getSelectedPersonnel();
		
		try {
			checkHoldPoint = SCUMovieDAO.getInstance().checkHoldPoint(memberID, personnel, screenName);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end try~catch
		
		
		return checkHoldPoint;
	}
	
	
	public void checkSeat() {
		if(sbv.getSmlv().getSmv().getIsLogin()) {
			
			if(sbv.getJtOnScreen().getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(sbv, "원하는 시간대의 상영을 선택해주세요.");
				return;
			}//end if
			
			if(sbv.getSelectedPersonnel()!=0) {
				
				JTable jtOnScreen = sbv.getJtOnScreen();
				String screenName = "";
				switch (String.valueOf(jtOnScreen.getValueAt(jtOnScreen.getSelectedRow(), 3))) {
				case "일반":
					screenName = "N";
					break;
				case "프리미엄":
					screenName = "P";
					break;
				}
				
				
				if(checkHoldPoint(screenName)) {
					
					sbv.setSelectedScreenNum(String.valueOf(jtOnScreen.getValueAt(jtOnScreen.getSelectedRow(), 5)));
					sbv.setSelectedScreenName(String.valueOf(jtOnScreen.getValueAt(jtOnScreen.getSelectedRow(), 3)));
					sbv.setSelectedScreenStartTime(String.valueOf(jtOnScreen.getValueAt(jtOnScreen.getSelectedRow(), 1)));
					sbv.setSelectedScreenStartDate(String.valueOf(jtOnScreen.getValueAt(jtOnScreen.getSelectedRow(), 0)));
					
					new SCUSeatView(sbv);
					
				}else {
					JOptionPane.showMessageDialog(sbv, "잔여 포인트를 확인해주세요.");
				}
				
			}else {
				JOptionPane.showMessageDialog(sbv, "관람할 인원을 알려주세요.");
			}//end if
			
		}else {
			JOptionPane.showMessageDialog(sbv, "로그인을 먼저 해주세요.");
			return;
		}
	}

	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(sbv.getJbtnExit())) {
			sbv.dispose();
		}//end if
		
		if(ae.getSource().equals(sbv.getJcbDate())) {
			sbv.getDcbmPersonnel().removeAllElements();
			sbv.setSelectedPersonnel(0);
			sbv.getJrbAll().setSelected(true);
			filterDate();
		}//end if
		
		
		if(ae.getSource().equals(sbv.getJrbStandard())) {
			filterScreen("일반");
		}else if(ae.getSource().equals(sbv.getJrbPremium())){
			filterScreen("프리미엄");
		}else if(ae.getSource().equals(sbv.getJrbAll())) {
			filterScreen("전체");
		}//end if
		
		
		try {
			if(ae.getSource().equals(sbv.getJcbPersonnel())) {
				sbv.setSelectedPersonnel(Integer.parseInt(String.valueOf(sbv.getJcbPersonnel().getSelectedItem())));
			}//end if
		}catch(NumberFormatException nfe) {
		}//end try~catch
		
		if(ae.getSource().equals(sbv.getJbtnCheckSeat())) {
			checkSeat();
		}//end if
		
	}//actionPerformed Override
	

	@Override
	public void mousePressed(MouseEvent me) {}
	public void mouseClicked(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) {
		if(me.getSource().equals(sbv.getJtOnScreen())) {
			
			JTable jt = sbv.getJtOnScreen();
			
			
			sbv.getDcbmPersonnel().removeAllElements();
			int maxSeat = Integer.parseInt(jt.getValueAt(jt.getSelectedRow(), 4).toString());
			for(int i=0; i<maxSeat+1; i++) {
				sbv.getDcbmPersonnel().addElement(String.valueOf(i));
			}//end for
			
		}//end if
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	//mouseListener Override

	public List<SCUSearchScreenVO> getList() {
		return list;
	}
	
	
}//Class
