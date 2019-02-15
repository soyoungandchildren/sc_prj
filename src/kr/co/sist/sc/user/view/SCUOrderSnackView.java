package kr.co.sist.sc.user.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SCUOrderSnackView extends JDialog {
	private SCUSnackMenuView ssmv;
	private JTextField jtfSnackName, jtfPrice, jtfTotalPrice;
	private JComboBox<Integer> jcbQuan;
	private JTextArea jtaSnackInfo;
	private JButton jbtnAddOrder, jbtnClose;
//	private String snackName;
	
	public SCUOrderSnackView(SCUSnackMenuView ssmv, String snackName) {
		super(ssmv, "스낵 정보", true);
		this.ssmv = ssmv;
		
		//컴포넌트 생성
		JLabel jlbSnackImg = new JLabel(new ImageIcon("C:/dev/workspace/lunch_prj/src/kr/co/sist/lunch/user/img/no_img.jpg"));
		JLabel jlbSnackName = new JLabel("스낵명");
		JLabel jlbPrice = new JLabel("가격");
		JLabel jlbQuan = new JLabel("수량");
		JLabel jlbTotalPrice = new JLabel("총 가격");
		JLabel jlbSnackInfo = new JLabel("스낵 정보");
		
		jtfSnackName = new JTextField(snackName);
		jtfPrice = new JTextField();
		jcbQuan = new JComboBox<Integer>();
		jtfTotalPrice = new JTextField();
		jtaSnackInfo = new JTextArea();
		
		jbtnAddOrder = new JButton("주문 추가");
		jbtnClose = new JButton("취소");
		
		add(jlbSnackImg);
		add(jlbSnackName);
		add(jtfSnackName);
		add(jlbPrice);
		add(jtfPrice);
		add(jlbQuan);
		add(jcbQuan);
		add(jlbTotalPrice);
		add(jtfTotalPrice);
		add(jlbSnackInfo);
		add(jtaSnackInfo);
		add(jbtnAddOrder);
		add(jbtnClose);
		
		jtaSnackInfo.setLineWrap(true);
		
		//배치
		setLayout(null);
		
		jlbSnackImg.setBounds(10, 20, 250, 230);
		jlbSnackName.setBounds(270, 20, 60, 30);
		jtfSnackName.setBounds(325, 23, 130, 25);
		jlbPrice.setBounds(270, 50, 60,30);
		jtfPrice.setBounds(325, 53, 130, 25);
		jlbQuan.setBounds(270, 80, 60,30);
		jcbQuan.setBounds(325, 83, 130, 25);
		jlbTotalPrice.setBounds(270, 110, 60,30);
		jtfTotalPrice.setBounds(325, 113, 130, 25);
		jlbSnackInfo.setBounds(270, 140, 60,30);
		jtaSnackInfo.setBounds(270, 165, 182, 85);
		
		jbtnAddOrder.setBounds(150, 265, 90, 30);
		jbtnClose.setBounds(260, 265, 90, 30);
		
		setBounds(ssmv.getX()+880, ssmv.getY()+100, 500, 350);
		setResizable(false);
		setVisible(true);
		
	}//SCUOrderSnackView
	
}//class
