package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import kr.co.sist.sc.user.model.SCUSnackDAO;
import kr.co.sist.sc.user.util.CustomTableRenderer;
import kr.co.sist.sc.user.view.SCUOrderSnackView;
import kr.co.sist.sc.user.view.SCUSnackMenuView;
import kr.co.sist.sc.user.vo.SCUSnackOrderDataVO;

public class SCUOrderSnackController extends WindowAdapter implements ActionListener{
	private SCUSnackMenuView ssmv;
	private SCUOrderSnackView sosv;
	private SCUSnackDAO ssDAO;
	private String selectName;
	private String[] chkname;
	
	public SCUOrderSnackController(SCUOrderSnackView sosv, SCUSnackMenuView ssmv, String snackName) {
		this.sosv = sosv;
		this.ssmv = ssmv;
		ssDAO = SCUSnackDAO.getInstance();
		selectName = snackName;
		searchInfo();
	}//SCUOrderSnackController
	
	public void searchInfo() {
		try {
			SCUSnackOrderDataVO ssodVO = ssDAO.selectSnackOrderData(selectName);
			
			sosv.getJtfPrice().setText(String.valueOf(ssodVO.getSnack_price()));
			sosv.getJlbSnackImg().setIcon(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/snack/l_snack_"+ssodVO.getSnack_img()));
			sosv.getJtaSnackInfo().setText(ssodVO.getSnack_info());
			
		} catch (SQLException se) {
			se.printStackTrace();
		}//end catch
	}//searchInfo
	
	public void insertSnackOnList() {
		//주문 목록에 추가하기
		if(ssmv.getDtmOrderList().getRowCount() != 0) {
			
			chkname = new String[ssmv.getDtmOrderList().getRowCount()];
			for(int i =0; i<ssmv.getDtmOrderList().getRowCount(); i++) {
				chkname[i] = ssmv.getDtmOrderList().getValueAt(i, 0).toString();
			}//end for
			
			boolean flag = false;
			int idx = 0;
			for(idx=0; idx<chkname.length; idx++) {
				if(chkname[idx].equals(sosv.getJtfSnackName().getText())) {
					flag = true;
					break;
				}//end if
			}//end for
			
			Object[] rowData = new Object[4];
			if(!flag) {
				rowData[0] = sosv.getJtfSnackName().getText();
				rowData[1] = sosv.getJtfPrice().getText();
				rowData[2] = sosv.getJcbQuan().getSelectedItem();
				rowData[3] = sosv.getJtfTotalPrice().getText();
				
				ssmv.getDtmOrderList().addRow(rowData);
			} else{
				int tempQuan = Integer.parseInt(ssmv.getDtmOrderList().getValueAt(idx, 2).toString())+
						Integer.parseInt(sosv.getJcbQuan().getSelectedItem().toString());
				Object rQuan = tempQuan;
				
				int tempPrice = Integer.parseInt(ssmv.getDtmOrderList().getValueAt(idx, 3).toString())+
								Integer.parseInt(sosv.getJtfTotalPrice().getText());
				Object rPrice = tempPrice;
				
				ssmv.getDtmOrderList().setValueAt(rQuan, idx, 2);
				ssmv.getDtmOrderList().setValueAt(rPrice, idx, 3);
			}//end else
		} else {
			Object[] rowData = new Object[4];
			rowData[0] = sosv.getJtfSnackName().getText();
			rowData[1] = sosv.getJtfPrice().getText();
			rowData[2] = sosv.getJcbQuan().getSelectedItem();
			rowData[3] = sosv.getJtfTotalPrice().getText();
				
			ssmv.getDtmOrderList().addRow(rowData);
		}//end else
		
		//테이블랜더러 추가
		ssmv.getJtOrderList().getColumnModel().getColumn(0).setCellRenderer(CustomTableRenderer.applyRenderer());
		ssmv.getJtOrderList().getColumnModel().getColumn(1).setCellRenderer(CustomTableRenderer.applyRenderer());
		ssmv.getJtOrderList().getColumnModel().getColumn(2).setCellRenderer(CustomTableRenderer.applyRenderer());
		ssmv.getJtOrderList().getColumnModel().getColumn(3).setCellRenderer(CustomTableRenderer.applyRenderer());
		
		//합계
		Object price = new Object();
		
		int temp = 0;
		if(ssmv.getDtmOrderList().getRowCount() > 0) {
			for(int i=0; i<ssmv.getDtmOrderList().getRowCount(); i++) {
				temp += Integer.parseInt(ssmv.getJtOrderList().getValueAt(i, 3).toString());
			}//end for
		}
		price = temp;
		ssmv.getJtOrderTotalPrice().setValueAt(price, 0, 1);
		
		sosv.dispose();
	}//insertSnackOnList

	@Override
	public void actionPerformed(ActionEvent ae) {
		//취소버튼
		if(ae.getSource() == sosv.getJbtnClose()) {
			sosv.dispose();
		}//end if
		
		//수량에 따른 총 가격 얻기
		if(ae.getSource() == sosv.getJcbQuan()) {
			int price = Integer.parseInt(sosv.getJtfPrice().getText());
			int quan = sosv.getJcbQuan().getSelectedIndex();
			sosv.getJtfTotalPrice().setText(String.valueOf(price*quan));
		}//end if
		
		//주문 목록에 추가하기
		if(ae.getSource() == sosv.getJbtnAddOrder()) {
			//수량 선택하지 않았을 때
			if(sosv.getJcbQuan().getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(sosv, "수량을 선택하세요", "주문 오류", JOptionPane.ERROR_MESSAGE);
				return;
			}//end if
			
			switch(JOptionPane.showConfirmDialog(sosv, "["+sosv.getJtfSnackName().getText()+"] [수량 : "+
					sosv.getJcbQuan().getSelectedItem()+"] [총 가격 : "+sosv.getJtfTotalPrice().getText()+
					"]을\n주문 목록에 추가하시겠습니까?", "주문 추가", JOptionPane.OK_CANCEL_OPTION)) {
			case JOptionPane.OK_OPTION :
				JOptionPane.showMessageDialog(sosv, "["+sosv.getJtfSnackName().getText()+"] [수량 : "+
						sosv.getJcbQuan().getSelectedItem()+"] [총 가격 : "+sosv.getJtfTotalPrice().getText()+
						"]이\n주문 목록에 추가 되었습니다!", "주문 완료", JOptionPane.PLAIN_MESSAGE);
				insertSnackOnList();
			case JOptionPane.CANCEL_OPTION :
				return;
			}//end switch
		}//end if
	}//actionPerformed
	
	@Override
	public void windowClosing(WindowEvent e) {
		sosv.dispose();
	}//windowClosing
	
}//class
