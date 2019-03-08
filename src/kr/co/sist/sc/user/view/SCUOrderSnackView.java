package kr.co.sist.sc.user.view;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.co.sist.sc.user.controller.SCUOrderSnackController;
import kr.co.sist.sc.user.util.CustomFontList;

@SuppressWarnings("serial")
public class SCUOrderSnackView extends JDialog {
	private SCUSnackMenuView ssmv;
	private JTextField jtfSnackName, jtfPrice, jtfTotalPrice;
	private JLabel jlbSnackImg;
	private JComboBox<String> jcbQuan;
	private JTextArea jtaSnackInfo;
	private JButton jbtnAddOrder, jbtnClose;
	
	public SCUOrderSnackView(SCUSnackMenuView ssmv, String snackName) {
		super(ssmv, "스낵 정보", true);
		this.ssmv = ssmv;
		
		//컴포넌트 생성
		String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/";
		
		jlbSnackImg = new JLabel();
		JLabel jlbSnackName = new JLabel("스낵명");
		JLabel jlbPrice = new JLabel("가격");
		JLabel jlbQuan = new JLabel("수량");
		JLabel jlbTotalPrice = new JLabel("총 가격");
		JLabel jlbSnackInfo = new JLabel("스낵 정보");
		
		jtfSnackName = new JTextField(snackName);
		jtfPrice = new JTextField();
		
		DefaultComboBoxModel<String> snackQuan = new DefaultComboBoxModel<String>();
		snackQuan.addElement(" ---수량 선택--- ");
		
		for(int i=1; i<11; i++) {
			snackQuan.addElement(String.valueOf(i));
		}//end for
		
		jcbQuan = new JComboBox<String>(snackQuan);
		jtfTotalPrice = new JTextField();
		
		jtaSnackInfo = new JTextArea();
		JScrollPane jspSnackInfo = new JScrollPane(jtaSnackInfo);
		
		jbtnAddOrder = new JButton(new ImageIcon(imgPath+"jbt_add_order(125x40).png"));
		jbtnClose = new JButton(new ImageIcon(imgPath+"jbt_cancel(125x40).png"));
		
		//버튼 테두리 없애기
		jbtnAddOrder.setContentAreaFilled(false);
		jbtnAddOrder.setBorderPainted(false);
		jbtnClose.setContentAreaFilled(false);
		jbtnClose.setBorderPainted(false);
		
		//라벨 폰트
		jlbSnackName.setForeground(Color.WHITE);
		jlbSnackName.setFont(CustomFontList.getInstance().getFontLabel());
		
		jlbPrice.setForeground(Color.WHITE);
		jlbPrice.setFont(CustomFontList.getInstance().getFontLabel());
		
		jlbQuan.setForeground(Color.WHITE);
		jlbQuan.setFont(CustomFontList.getInstance().getFontLabel());
		
		jlbTotalPrice.setForeground(Color.WHITE);
		jlbTotalPrice.setFont(CustomFontList.getInstance().getFontLabel());
		
		jlbSnackInfo.setForeground(Color.WHITE);
		jlbSnackInfo.setFont(CustomFontList.getInstance().getFontLabel());
		
		//박스 꾸미기
		jtfSnackName.setOpaque(false);
		jtfSnackName.setForeground(Color.WHITE);
		jtfSnackName.setFont(CustomFontList.getInstance().getFontLabel());
		
		jtfPrice.setOpaque(false);
		jtfPrice.setForeground(Color.WHITE);
		jtfPrice.setFont(CustomFontList.getInstance().getFontLabel());
		
		jtfTotalPrice.setOpaque(false);
		jtfTotalPrice.setForeground(Color.WHITE);
		jtfTotalPrice.setFont(CustomFontList.getInstance().getFontLabel());
		
		jcbQuan.setForeground(Color.WHITE);
		jcbQuan.setFont(CustomFontList.getInstance().getFontLabel());
		jcbQuan.setBackground(new Color(20,45,85));
		
		jtaSnackInfo.setBackground(new Color(20,45,85));
		jtaSnackInfo.setForeground(Color.WHITE);
		jtaSnackInfo.setFont(CustomFontList.getInstance().getFontLabel());
		
		
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
		add(jspSnackInfo);
		add(jbtnAddOrder);
		add(jbtnClose);
		
		//배치
		setLayout(null);
		
		jlbSnackImg.setBounds(50, 22, 325, 325);
		jlbSnackName.setBounds(390, 18, 60, 30);
		jtfSnackName.setBounds(440, 21, 130, 25);
		jlbPrice.setBounds(390, 54, 60,30);
		jtfPrice.setBounds(440, 57, 130, 25);
		jlbQuan.setBounds(390, 90, 60,30);
		jcbQuan.setBounds(440, 93, 130, 25);
		jlbTotalPrice.setBounds(390, 126, 60,30);
		jtfTotalPrice.setBounds(440, 129, 130, 25);
		jlbSnackInfo.setBounds(390, 162, 60,30);
		jspSnackInfo.setBounds(390, 193, 181, 152);
		jbtnAddOrder.setBounds(175, 363, 125, 40);
		jbtnClose.setBounds(315, 363, 125, 40);
		
		//수정 불가
		jtfSnackName.setEditable(false);
		jtfPrice.setEditable(false);
		jtfTotalPrice.setEditable(false);
		jtfTotalPrice.setEditable(false);
		jtaSnackInfo.setEditable(false);
		jtaSnackInfo.setLineWrap(true);
		
		//배경 설정
		JLabel background = new JLabel(new ImageIcon(imgPath+"user_snackcorner_bg2(620x450).png"));
		background.setBounds(0, 0, 620, 450);
		add(background);
		
		//이벤트
		SCUOrderSnackController sosc = new SCUOrderSnackController(this, ssmv, snackName);
		addWindowListener(sosc);
		jbtnAddOrder.addActionListener(sosc);
		jbtnClose.addActionListener(sosc);
		jcbQuan.addActionListener(sosc);
		
		//가시화
		setBounds(ssmv.getX()+880, ssmv.getY()+100, 620, 450);
		setResizable(false);
		setVisible(true);
	}//SCUOrderSnackView

	public SCUSnackMenuView getSsmv() {
		return ssmv;
	}
	public JTextField getJtfSnackName() {
		return jtfSnackName;
	}
	public JTextField getJtfPrice() {
		return jtfPrice;
	}
	public JLabel getJlbSnackImg() {
		return jlbSnackImg;
	}
	public JTextField getJtfTotalPrice() {
		return jtfTotalPrice;
	}
	public JComboBox<String> getJcbQuan() {
		return jcbQuan;
	}
	public JTextArea getJtaSnackInfo() {
		return jtaSnackInfo;
	}
	public JButton getJbtnAddOrder() {
		return jbtnAddOrder;
	}
	public JButton getJbtnClose() {
		return jbtnClose;
	}
}//class
