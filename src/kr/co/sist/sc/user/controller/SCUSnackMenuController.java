package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;

import kr.co.sist.sc.user.model.SCUSnackDAO;
import kr.co.sist.sc.user.view.SCUOrderSnackView;
import kr.co.sist.sc.user.view.SCUSnackMenuView;
import kr.co.sist.sc.user.vo.SCUSearchMenuVO;

public class SCUSnackMenuController extends WindowAdapter implements ActionListener {
	private SCUSnackMenuView ssmv;
	private SCUSnackDAO ssDAO;
	private String[] snackName;
	
	public SCUSnackMenuController(SCUSnackMenuView ssmv) {
		this.ssmv=ssmv;
		ssDAO = SCUSnackDAO.getInstance();
		searchMenu();
	}//SCUSnackMenuController
	
	//메뉴 불러오기
	public void searchMenu() {
		try {
			List<SCUSearchMenuVO> list = ssDAO.selectSnackMenu();
			SCUSearchMenuVO ssmVO = null;
			Object[] rowData = null;
			String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/";
			snackName = new String[list.size()];
			
			for(int i=0; i<list.size(); i++) {
				ssmVO = list.get(i);
				
				rowData = new Object[2];
				rowData[0] = new ImageIcon(imgPath+ssmVO.getSnack_img());
				rowData[1] = ssmVO.getSnack_name();
				ssmv.getJbtnMenu()[i].setIcon(new ImageIcon(rowData[0].toString()));	
				
				//스낵 이름을 담을 배열
				snackName[i] = rowData[1].toString();
			}//end for
			
			//메뉴가 8개 미만이 되었을때 NoImage.pnc로 채우기
			int ex = 8-list.size();
			if(list.size() != 8) {
				for(int i=0; i<ex; i++) {
					ssmv.getJbtnMenu()[7-i].setIcon(new ImageIcon(imgPath+"NoImage.png"));
					ssmv.getJbtnMenu()[7-i].setEnabled(false);
				}//end for
			}//end if
			
		} catch (SQLException se) {
			se.printStackTrace();
		}//end catch
		
	}//searchMenu
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		//스낵 정보로 이동 1~8
		for(int i =0; i<ssmv.getJbtnMenu().length; i++) {
			if(ae.getSource().equals(ssmv.getJbtnMenu()[i])) {
				new SCUOrderSnackView(ssmv, snackName[i]);
			}
		}//end for
		
		if(ae.getSource() == ssmv.getJbtnExit()) {	//닫기
			ssmv.dispose();
		}//end if
			
	}//actionPerformed
	
	@Override
	public void windowClosing(WindowEvent we) {
		ssmv.dispose();
	}//windowClosing

}//class
