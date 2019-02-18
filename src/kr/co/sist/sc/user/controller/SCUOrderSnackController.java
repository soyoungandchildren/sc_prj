package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;

import kr.co.sist.sc.user.model.SCUSnackDAO;
import kr.co.sist.sc.user.view.SCUOrderSnackView;
import kr.co.sist.sc.user.view.SCUSnackMenuView;
import kr.co.sist.sc.user.vo.SCUSnackOrderDataVO;

public class SCUOrderSnackController extends WindowAdapter implements ActionListener{
	private SCUSnackMenuView ssmv;
	private SCUOrderSnackView sosv;
	private SCUSnackDAO ssDAO;
	private String selectName;
	
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
			sosv.getJlbSnackImg().setIcon(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/"+ssodVO.getSnack_img()));
			sosv.getJtaSnackInfo().setText(ssodVO.getSnack_info());
			
		} catch (SQLException se) {
			se.printStackTrace();
		}//end catch
	}//searchInfo
	
	public void insertSnackOnList() {
//		ssmv.getDtmOrderList().setRowCount(0);	//예외처리
		//주문 목록에 추가하기
		Object[] rowData = new Object[4];
		rowData[0] = sosv.getJtfSnackName().getText();
		rowData[1] = sosv.getJtfPrice().getText();
		rowData[2] = sosv.getJcbQuan().getSelectedItem();
		rowData[3] = sosv.getJtfTotalPrice().getText();
		
		ssmv.getDtmOrderList().addRow(rowData);
		
		//합계 추가하기
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
			insertSnackOnList();
		}//end if
	}//actionPerformed
	
	@Override
	public void windowClosing(WindowEvent e) {
		sosv.dispose();
	}//windowClosing
	
}//class
