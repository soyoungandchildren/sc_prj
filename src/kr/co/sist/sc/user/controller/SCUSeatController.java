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
		
		if(sbv.getSelectedScreenName().equals("�Ϲ�")) {
			imgType = "s";
			imgsize = "(67x61)";
			
		}else {//�����̾� ���϶�
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
	
	public void insertBooking() {
		
		String bookNumberChar = "";
		if(sbv.getSelectedScreenName().equals("�Ϲ�")) {
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
		
		
		boolean result = SCUMovieDAO.getInstance().insertBooking(sibVO, listSisVO, sbv.getSelectedScreenName());
		if(result) {
			JOptionPane.showMessageDialog(ssv, "�����մϴ� ����");
		}else {
			JOptionPane.showMessageDialog(ssv, "�ý��� ���� ������ �����۾��� �ߴܵǾ����ϴ�.\n���߿� �ٽ� �õ����ּ���.");
		}//end if~else
		
		ssv.dispose();
		sbv.dispose();
		sbv.getSmlv().dispose();
		
	}//insertBooking Method
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource().equals(ssv.getJbtnExit())) {
			ssv.dispose();
		}//end if
		
		if(ae.getSource().equals(ssv.getJbtnConfirm())) {
			listCheckedSeat = new ArrayList<>();
			JCheckBox[] jckb = ssv.getJckbSeat();
			
			for(int i = 0; i<jckb.length; i++) {
				if(jckb[i].isSelected()) {
					listCheckedSeat.add(i+1);
				}//end if
			}//end for
			
			if(sbv.getSelectedPersonnel()!=listCheckedSeat.size()) {
				JOptionPane.showMessageDialog(ssv, "�¼��� ��� �������ּ���!");
				return;
			}//end if
			
			StringBuilder sbSeat = new StringBuilder();
			for(int i = 0; i<listCheckedSeat.size();i++) {
				sbSeat.append(listCheckedSeat.get(i)).append("�� ");
			}//end for
			
			StringBuilder sbTime = new StringBuilder(sbv.getSelectedScreenStartTime());
			StringBuilder sbReceipt = new StringBuilder();
			JTextArea jtaReceipt = new JTextArea();
			jtaReceipt.setEditable(false);
			sbTime.insert(2, "�� ").append("��");
			sbReceipt
			.append(sbv.getSmlv().getSmv().getIdConnecting()).append("���� ���� ����\n")
			.append("======================================\n")
			.append("��ȭ ���� : ").append(sbv.getSmlv().getSelectedMovieTitle()).append("\n")
			.append("��¥ : ").append(sbv.getSelectedScreenStartDate()).append("\n")
			.append("���� �ð� : ").append(sbTime.toString()).append("\n")
			.append("�ο� : ").append(sbv.getSelectedPersonnel()).append("\n")
			.append("�¼� ��ȣ : ").append(sbSeat.toString()).append("\n")
			.append("-------------------------------------------------------------------\n")
			.append("���� ������ ������ �����Ͻðڽ��ϱ�?")
			;
			jtaReceipt.append(sbReceipt.toString());
			
			int confirm = JOptionPane.showConfirmDialog(ssv, jtaReceipt, "����Ȯ��", JOptionPane.OK_CANCEL_OPTION);
			
			switch (confirm) {
			case JOptionPane.OK_OPTION:
				insertBooking();
				break;
			case JOptionPane.CANCEL_OPTION:
				break;
			}
			
		}//end if
		
	}//actionPerformed Override

	@Override
	public void itemStateChanged(ItemEvent ie) {
			
			int cnt = 0;
			for(int i = 0; i<ssv.getJckbSeat().length; i++) {//Ŭ���Ϸ��� �ο����� ������ �ο����� ������ �˸���.
			
				if(ssv.getJckbSeat()[i].isSelected()){
					cnt++;
				}//end if
				
				if(cnt>sbv.getSelectedPersonnel()) {
					JOptionPane.showMessageDialog(ssv, "������ �¼����� �����ο����� �����ϴ�.");
					return;
				}//end if
			}//end for
			
			
			for(int i = 0; i<ssv.getJckbSeat().length; i++)	{//������ �¼��� ������ �Ѵ�.
					
				if(ssv.getJckbSeat()[i].isSelected()) {
					ssv.getJckbSeat()[i].setIcon(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_"+imgType+"_seat_selected"+imgsize+".png"));
				}else {
					ssv.getJckbSeat()[i].setIcon(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_"+imgType+"_seat_selectable"+imgsize+".png"));
				}//end if~else
				
			}//end for
			
	}//itemStatechanged
	
	
}//Class
