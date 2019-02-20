package kr.co.sist.sc.user.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.sc.user.model.SCULoginDAO;
import kr.co.sist.sc.user.vo.SCUFindPWVO;
import kr.co.sist.sc.user.vo.SCUModifyPWVO;

@SuppressWarnings("serial")
public class SCUModifyPWView extends JDialog implements ActionListener {

	private JPasswordField jpfPW, jpfConfirmPW;
	private JButton jbtnConfirm, jbtnExit;
	private SCUFindAccountView sfav;
	private String stringPW;
	/**
	 * ��й�ȣ ����
	 * @param slv
	 */
	public SCUModifyPWView(SCULoginView slv, SCUFindAccountView sfav) {
		
		super(slv,"��й�ȣ ����",true);
		
		this.sfav = sfav;

		JLabel jlPW = new JLabel("������ ��й�ȣ");
		jpfPW = new JPasswordField();
		JLabel jlConfirmPW = new JLabel("��й�ȣ Ȯ��");
		jpfConfirmPW = new JPasswordField();

		jbtnConfirm = new JButton("����");
		jbtnExit = new JButton("���");

		setLayout(null);

		jlPW.setBounds(20, 10, 110, 30);
		jpfPW.setBounds(120, 10, 180, 30);
		jlConfirmPW.setBounds(20, 50, 110, 30);
		jpfConfirmPW.setBounds(120, 50, 180, 30);

		jbtnConfirm.setBounds(30, 110, 110, 30);
		jbtnExit.setBounds(170, 110, 110, 30);

		add(jlPW);
		add(jpfPW);
		add(jlConfirmPW);
		add(jpfConfirmPW);
		add(jbtnConfirm);
		add(jbtnExit);

		// �̺�Ʈó��
		jbtnConfirm.addActionListener(this);
		jbtnExit.addActionListener(this);
		
		setBounds(90, 200, 340, 200);
		setVisible(true);
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}//SCUModifyPWView

	
	
	/**
	 * ��й�ȣ ����
	 * @param password
	 */
	public void modifyPW(String password) throws SQLException {
		String stringIDForPW = sfav.getJtfIDForPW().getText();
		
		String stringPW = new String(jpfPW.getPassword());
		String stringConfirmPW = new String(jpfConfirmPW.getPassword());
		
		
		if(!stringPW.equals(stringConfirmPW)) {
			JOptionPane.showMessageDialog(this, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			return;
		}//end if
		
		SCUModifyPWVO sfpvo = new SCUModifyPWVO (stringPW.trim(), stringIDForPW.trim());
		System.out.println("������� �ڵ��");
		try {
			if(SCULoginDAO.getInstance().updatePW(sfpvo)) {
				System.out.println("������� �ڵ�� ��Ȳ1");
				JOptionPane.showMessageDialog(this, "����Ǿ����ϴ�.");
			}else {
				System.out.println("������� �ڵ�� ��Ȳ2");
				JOptionPane.showMessageDialog(this, "dsfd");
			}
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(this, "DB���� ������ �߻��Ͽ����ϴ�.");
			se.printStackTrace();
		} // end catch
		
	}//modifyPW
	
	@Override
	public void actionPerformed(ActionEvent ae) {

		String stringPW = new String(jpfPW.getPassword());
		
		 //��й�ȣ ����â '����'��ư
			if (ae.getSource() == getJbtnConfirm()) {
				try {
					modifyPW(stringPW.trim());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // JbtnConfirm
			

//			 ��й�ȣ ����â '���'��ư
			if (ae.getSource() == getJbtnExit()) {
				dispose();
			} // JbtnExit
			
			
	}//actionPerformed
	

	public JPasswordField getJpfPW() {
		return jpfPW;
	}

	public JPasswordField getJpfConfirmPW() {
		return jpfConfirmPW;
	}

	public JButton getJbtnConfirm() {
		return jbtnConfirm;
	}

	public JButton getJbtnExit() {
		return jbtnExit;
	}

}// class