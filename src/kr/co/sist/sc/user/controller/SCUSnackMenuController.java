package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.co.sist.sc.user.model.SCUSnackDAO;
import kr.co.sist.sc.user.view.SCUOrderSnackView;
import kr.co.sist.sc.user.view.SCUSnackMenuView;
import kr.co.sist.sc.user.vo.SCUAddOrderSnackVO;
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
			String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/snack/";
			snackName = new String[list.size()];
			
			for(int i=0; i<list.size(); i++) {
				ssmVO = list.get(i);
				
				rowData = new Object[2];
				rowData[0] = new ImageIcon(imgPath+"s_snack_"+ssmVO.getSnack_img());
				rowData[1] = ssmVO.getSnack_name();
				ssmv.getJbtnMenu()[i].setIcon(new ImageIcon(rowData[0].toString()));	
				
				//스낵 이름을 담을 배열
				snackName[i] = rowData[1].toString();
			}//end for
			
			//메뉴가 8개 미만이 되었을때 NoImage.png로 채우기
			int ex = 8-list.size();
			if(list.size() != 8) {
				for(int i=0; i<ex; i++) {
					ssmv.getJbtnMenu()[7-i].setIcon(new ImageIcon(imgPath+"s_snack_no_image(187x162).png"));
					ssmv.getJbtnMenu()[7-i].setEnabled(false);
				}//end for
			}//end if
		} catch (SQLException se) {
			se.printStackTrace();
		}//end catch
	}//searchMenu
	
	//주문 목록 삭제
	public void deleteSnackOnList() {
		int selectRow = ssmv.getJtOrderList().getSelectedRow();
		ssmv.getDtmOrderList().removeRow(selectRow);
		
		//주문 목록 삭제 -> 합계 값 변경
		Object price = new Object();
			
		int temp = 0;
		if(ssmv.getDtmOrderList().getRowCount() > 0) {
			for(int i=0; i<ssmv.getDtmOrderList().getRowCount(); i++) {
				temp += Integer.parseInt(ssmv.getJtOrderList().getValueAt(i, 3).toString());
			}//end for
		}//end if
		price = temp;
		ssmv.getJtOrderTotalPrice().setValueAt(price, 0, 1);
	}//deleteSnackOnList
	
	//결제
	public void checkOutSnack() {
		String member = ssmv.getSmv().getIdConnecting();
		String totalOrderPrice = ssmv.getJtOrderTotalPrice().getValueAt(0,1).toString();
		JTextArea jtaBill = new JTextArea(13,17);
		jtaBill.setEditable(false);
		
		JScrollPane jspBill = new JScrollPane(jtaBill);
		
		String[] chkname = new String[ssmv.getDtmOrderList().getRowCount()];
		Integer[] chkQuan = new Integer[ssmv.getDtmOrderList().getRowCount()];
		for(int i =0; i<ssmv.getDtmOrderList().getRowCount(); i++) {
			chkname[i] = ssmv.getDtmOrderList().getValueAt(i, 0).toString();
			chkQuan[i] = Integer.parseInt(ssmv.getDtmOrderList().getValueAt(i, 2).toString());
		}//end for
		
		StringBuilder bill = new StringBuilder();
		bill.append(" "+member+"님의 주문 내역\n")
			.append(" ------------------------------------------\n");
		
			for(int i=0; i<chkname.length; i++) {
				bill.append(" [ "+chkname[i]+" ] "+chkQuan[i]+"개\n");
			}//end for
		
		bill.append(" ------------------------------------------\n")
			.append(" 합 계 : "+totalOrderPrice+"\n")
			.append(" ------------------------------------------\n")
			.append(" 정말 결제를 진행하시겠습니까?");
		
		jtaBill.setText(bill.toString());
		
		Object reset = 0;
		
		List<SCUAddOrderSnackVO> list = new ArrayList<>();
		switch(JOptionPane.showConfirmDialog(ssmv, jspBill, "주문 확인", JOptionPane.OK_CANCEL_OPTION)) {
		case JOptionPane.OK_OPTION :
			for(int i =0; i<ssmv.getDtmOrderList().getRowCount(); i++) {
				SCUAddOrderSnackVO saosVO = new SCUAddOrderSnackVO(chkname[i], member, chkQuan[i]);
				list.add(saosVO);
			}//end for
			try {
				boolean result = ssDAO.insertOrderSnack(list, totalOrderPrice, member);
				if(result) {
					JOptionPane.showMessageDialog(ssmv, "결제가 완료되었습니다.", "결제 완료", JOptionPane.PLAIN_MESSAGE);
					ssmv.getDtmOrderList().setRowCount(0);
					ssmv.getJtOrderTotalPrice().setValueAt(reset, 0, 1);
				} else {
					JOptionPane.showMessageDialog(ssmv, "포인트가 부족합니다!\n포인트 충전 후 이용해주세요.\n포인트는 [마이페이지-포인트 충전하기] 에서 가능합니다.",
						"결제 오류", JOptionPane.WARNING_MESSAGE);
				}//end else
			} catch (SQLException se) {
				se.printStackTrace();
				JOptionPane.showMessageDialog(ssmv, "내부상의 문제로 결제가 취소되었습니다.", "결제 오류", JOptionPane.WARNING_MESSAGE);
			}//end catch
		case JOptionPane.CANCEL_OPTION :
			return;
		}//end switch
	}//checkOutSnack
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		//스낵 정보로 이동 1~8
		for(int i =0; i<ssmv.getJbtnMenu().length; i++) {
			if(ae.getSource().equals(ssmv.getJbtnMenu()[i])) {
				new SCUOrderSnackView(ssmv, snackName[i]);
			}
		}//end for
		
		//결제
		int rowCount = ssmv.getJtOrderList().getRowCount();
		if(ae.getSource() == ssmv.getJbtnCheckOut()) {
			//로그인 여부 확인
			if(ssmv.getSmv().getIsLogin()) {
				//스낵 주문 확인
				if(rowCount != 0) {
					checkOutSnack();
				} else {
					JOptionPane.showMessageDialog(ssmv, "스낵 주문 후 결제 해주세요!", "Warning", JOptionPane.WARNING_MESSAGE);
				}//end else
			} else {
				JOptionPane.showMessageDialog(ssmv, "로그인 후에 가능합니다.", "Warning", JOptionPane.WARNING_MESSAGE);
			}//end else
		}//end if
		
		//주문 삭제
		int selectIdx = ssmv.getJtOrderList().getSelectedRow();
		if(ae.getSource() == ssmv.getJbtnDeleteOrder()) {
			if(selectIdx != -1) {
				switch(JOptionPane.showConfirmDialog(ssmv, "["+ssmv.getJtOrderList().getValueAt(selectIdx, 0)+"] [수량 : "+
							ssmv.getJtOrderList().getValueAt(selectIdx, 2)+"] [총 가격 : "+ssmv.getJtOrderList().getValueAt(selectIdx, 3)+
							"]을\n주문 목록에서 삭제하시겠습니까?", "주문 삭제", JOptionPane.OK_CANCEL_OPTION)) {
				case JOptionPane.OK_OPTION :
					JOptionPane.showMessageDialog(ssmv, "["+ssmv.getJtOrderList().getValueAt(selectIdx, 0)+"] [수량 : "+
									ssmv.getJtOrderList().getValueAt(selectIdx, 2)+"] [총 가격 : "+ssmv.getJtOrderList().getValueAt(selectIdx, 3)+
									"]이\n주문 목록에서 삭제되었습니다!", "주문목록 삭제", JOptionPane.PLAIN_MESSAGE);
					deleteSnackOnList();
				case JOptionPane.CANCEL_OPTION :
					return;
				}//end switch
			} else {
				JOptionPane.showMessageDialog(ssmv, "주문 목록에서 삭제할 스낵을 선택해주세요!", "Warning", JOptionPane.WARNING_MESSAGE);
			}//end else
		}//end if
		
		//닫기
		if(ae.getSource() == ssmv.getJbtnExit()) {
			ssmv.dispose();
		}//end if
	}//actionPerformed
	
	@Override
	public void windowClosing(WindowEvent we) {
		ssmv.dispose();
	}//windowClosing
}//class
