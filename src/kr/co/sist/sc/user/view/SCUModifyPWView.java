package kr.co.sist.sc.user.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import kr.co.sist.sc.user.model.SCULoginDAO;
import kr.co.sist.sc.user.vo.SCUModifyPWVO;

@SuppressWarnings("serial")
public class SCUModifyPWView extends JDialog implements ActionListener {

	private JPasswordField jpfPW, jpfConfirmPW;
	private JButton jbtnConfirm, jbtnExit;
	private SCUFindAccountView sfav;
	private String stringIDForPW;

	/**
	 * ��й�ȣ ����
	 * 
	 * @param slv
	 */
	public SCUModifyPWView(SCULoginView slv, SCUFindAccountView sfav) {

		super(slv, "��й�ȣ ����", true);
		this.sfav = sfav;

		stringIDForPW = sfav.getJtfIDForPW().getText();
		makeView();

	}// SCUModifyPWView

	public SCUModifyPWView(SCUMyPageView sppv) {
		super(sppv, "��й�ȣ ����", true);

		stringIDForPW = sppv.getSmv().getIdConnecting();
		makeView();
	}

	public void makeView() {

		// ������Ʈ ����
		String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/";

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

		// â ����
		JLabel background = new JLabel(new ImageIcon(imgPath + "user_snackcorner_bg1(900x800).png"));
		background.setBounds(0, 0, 900, 800);
		add(background);

		setBounds(90, 200, 340, 200);
		setVisible(true);
		// setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/**
	 * ��й�ȣ ����
	 * 
	 * @param password
	 */
	public void modifyPW(String password) throws SQLException {

		String stringPW = new String(jpfPW.getPassword());
		String stringConfirmPW = new String(jpfConfirmPW.getPassword());

		if (!stringPW.equals(stringConfirmPW)) {
			JOptionPane.showMessageDialog(this, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			return;
		} // end if

		SCUModifyPWVO sfpvo = new SCUModifyPWVO(stringPW.trim(), stringIDForPW.trim());
		try {
			if (SCULoginDAO.getInstance().updatePW(sfpvo)) {
				JOptionPane.showMessageDialog(this, "����Ǿ����ϴ�.");
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(this, "��й�ȣ�� �ʼ� �Է»����Դϴ�!");
			se.printStackTrace();
		} // end catch

	}// modifyPW

	@Override
	public void actionPerformed(ActionEvent ae) {

		String stringPW = new String(jpfPW.getPassword());

		// ��й�ȣ ����â '����'��ư
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

	}// actionPerformed

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