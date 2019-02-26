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
				msgCenter("������ ���������� ����Ǿ����ϴ�.");
				new SCUMyPageController(smmiv.getSmpv());
				smmiv.dispose();
			}else {
				msgCenter("�ý��� ���� ������ ������ ��ҵǾ����ϴ�. ��� �� �ٽ� �õ����ּ���.");
			}
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		
	}
	
	private void msgCenter(String msg) {
		JOptionPane.showMessageDialog(smmiv, msg, "�˸�", JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(smmiv.getJbtnConfirm())) {
			String iName = smmiv.getJtfName().getText();
			String iPhone= smmiv.getJtfPhone().getText();
			
			if(iName.trim().equals("")) {
				msgCenter("�̸��� ������ �� �����ϴ�.");
				return;
			}else if(iPhone.trim().equals("")){
				msgCenter("��ȭ��ȣ�� ������ �� �����ϴ�.");
				return;
			}
			
			
			modifyInfo();
		}//end if
		
		if(ae.getSource().equals(smmiv.getJbtnExit())) {
			smmiv.dispose();
		}
	}
	
}
