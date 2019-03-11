package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JOptionPane;

import kr.co.sist.sc.user.model.SCUMyPageDAO;
import kr.co.sist.sc.user.view.SCUModifyMemberInfoView;
import kr.co.sist.sc.user.vo.SCUUpdateMemberInfoVO;

public class SCUModifyMemberInfoController extends WindowAdapter implements ActionListener{
	
	private SCUModifyMemberInfoView smmiv;
	
	
	
	public SCUModifyMemberInfoController(SCUModifyMemberInfoView smmiv) {
		this.smmiv = smmiv;
		smmiv.getJtfName().setText(smmiv.getSmpv().getJtfName().getText());
		
		String userPhone = smmiv.getSmpv().getJtfPhone().getText();
		
		smmiv.getJtfPhone1().setText(userPhone.trim().substring(0, userPhone.indexOf("-")-1));
		smmiv.getJtfPhone2().setText(userPhone.trim().substring(userPhone.indexOf("-"), userPhone.lastIndexOf("-")-1));
		smmiv.getJtfPhone3().setText(userPhone.trim().substring(userPhone.lastIndexOf("-")));
	}
	
	public void modifyInfo(String iName, String iPhone) {
		SCUUpdateMemberInfoVO sumiVO = 
				new SCUUpdateMemberInfoVO(iName, iPhone, 
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
			String iName = smmiv.getJtfName().getText().trim();
			
			String frontPhone = smmiv.getJtfPhone1().getText().trim(); 
			String middlePhone = smmiv.getJtfPhone2().getText().trim(); 
			String lastPhone = smmiv.getJtfPhone3().getText().trim();
			
			StringBuilder iPhoneCombine = new StringBuilder();
			iPhoneCombine
			.append(frontPhone)
			.append("-")
			.append(middlePhone)
			.append("-")
			.append(lastPhone);
			
			
			if("".equals(iName.replaceAll(" ", ""))) {
				msgCenter("�̸��� ������ �� �����ϴ�.");
				smmiv.getJtfName().requestFocus();
				smmiv.getJtfName().setText("");
				return;
			}else if("".equals((iPhoneCombine.toString().replaceAll("-", "")).replaceAll(" ", ""))){
				msgCenter("��ȭ��ȣ�� ������ �� �����ϴ�.");
				initializeNumberField();
				return;
			}else if(2>iName.length()||10<iName.length()) {
				msgCenter("�̸��� 2�ڿ��� 10�� �����Դϴ�.");
				smmiv.getJtfName().setText("");
				smmiv.getJtfName().requestFocus();
				return;
			}else if(frontPhone.length()!=3||middlePhone.length()>4||middlePhone.length()<3||
					lastPhone.length()<3||lastPhone.length()>4) {
				msgCenter("�޴���ȭ ����ó ������ Ȯ�����ּ���.");
				initializeNumberField();
				return;
			}
			
			try {
				new Integer(frontPhone);
				new Integer(middlePhone);
				new Integer(lastPhone);
			}catch(NumberFormatException nfe) {
				msgCenter("�޴���ȭ ����ó ������ Ȯ�����ּ���.");
				initializeNumberField();
				return;
			}
			
			
			modifyInfo(iName, iPhoneCombine.toString());
		}//end if
		
		if(ae.getSource().equals(smmiv.getJbtnExit())) {
			smmiv.dispose();
		}
		
	}
	
	private void initializeNumberField() {
		smmiv.getJtfPhone1().setText("");
		smmiv.getJtfPhone2().setText("");
		smmiv.getJtfPhone3().setText("");
		smmiv.getJtfPhone1().requestFocus();
	}

	
}
