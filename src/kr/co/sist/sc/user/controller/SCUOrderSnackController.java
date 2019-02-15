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
	
	public SCUOrderSnackController(SCUOrderSnackView sosv, String snackName) {
		this.sosv = sosv;
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
		System.out.println("랄ㄹㄹ라");
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
		}
		
	}//actionPerformed
	
	@Override
	public void windowClosing(WindowEvent e) {
		sosv.dispose();
	}//windowClosing
	
}//class
