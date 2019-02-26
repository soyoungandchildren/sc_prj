package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.sc.user.model.SCUMyPageDAO;
import kr.co.sist.sc.user.view.SCUModifyMemberInfoView;
import kr.co.sist.sc.user.vo.SCUUpdateMemberInfoVO;

public class SCUModifyMemberInfoController extends WindowAdapter implements ActionListener{
	
	private SCUModifyMemberInfoView smmiv;
	
	public SCUModifyMemberInfoController(SCUModifyMemberInfoView smmiv) {
		this.smmiv = smmiv;
		smmiv.getJtfName().setText(smmiv.getSmpv().getJtfName().getText());
		smmiv.getJtfPhone().setText(smmiv.getSmpv().getJtfPhone().getText());
	}
	
	public void modifyInfo() {
		SCUUpdateMemberInfoVO sumiVO = 
				new SCUUpdateMemberInfoVO(smmiv.getJtfName().getText(), smmiv.getJtfPhone().getText(), 
						smmiv.getSmpv().getSmv().getIdConnecting());
		
		try {
			int sqlResult = SCUMyPageDAO.getInstance().updateMemberInfo(sumiVO);
			
			if(sqlResult==1) {
				msgCenter("정보가 성공적으로 변경되었습니다.");
				new SCUMyPageController(smmiv.getSmpv());
				smmiv.dispose();
			}else {
				msgCenter("시스템 상의 오류로 변경이 취소되었습니다. 잠시 후 다시 시도해주세요.");
			}
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		
	}
	
	private void msgCenter(String msg) {
		JOptionPane.showMessageDialog(smmiv, msg, "알림", JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(smmiv.getJbtnConfirm())) {
			String iName = smmiv.getJtfName().getText();
			String iPhone= smmiv.getJtfPhone().getText();
			
			if(iName.trim().equals("")) {
				msgCenter("이름은 공백일 수 없습니다.");
				return;
			}else if(iPhone.trim().equals("")){
				msgCenter("전화번호는 공백일 수 없습니다.");
				return;
			}
			
			
			modifyInfo();
		}//end if
		
		if(ae.getSource().equals(smmiv.getJbtnExit())) {
			smmiv.dispose();
		}
	}
	
}
