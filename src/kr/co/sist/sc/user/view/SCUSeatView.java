package kr.co.sist.sc.user.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import kr.co.sist.sc.user.controller.SCUSeatController;

@SuppressWarnings("serial")
public class SCUSeatView extends JDialog{
	
	private SCUBookingView sbv;
	private SCUMovieListView smlv;
	private JButton jbtnConfirm, jbtnExit;
	private JCheckBox[] jckbSeat;
	
	public SCUSeatView(SCUBookingView sbv) {
		super(sbv, "좌석 선택", true);
		
		this.sbv = sbv;
		this.smlv = sbv.getSmlv();
		
		JPanel jpnlSeat = new JPanel();
		jpnlSeat.setLayout(null);
		
		jbtnConfirm = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_book(125x40).png"));
		jbtnExit = new JButton(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_close(125x40).png"));
		
		JPanel panelScreen = new JPanel();
		panelScreen.setOpaque(false);
		panelScreen.setLayout(null);
		panelScreen.setBorder(new LineBorder(Color.WHITE));
		
		JLabel imageScreen = null;
		
		setLayout(null);
		if(sbv.getSelectedScreenName().equals("일반")) {
			jckbSeat = new JCheckBox[20];
			
			int x = 0;
			int y = 0;
			int cnt = 0;
			
			for(int i = 0; i<jckbSeat.length; i++) {
				jckbSeat[i] = new JCheckBox(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_s_seat_selectable(67x61).png"));
				
				jckbSeat[i].setBounds(x,y,75,61);
				
				x+=90;
				cnt++;
				if(cnt==5) {
					x=0;
					y+=70;
					cnt=0;
				}//end if
				
				jckbSeat[i].setContentAreaFilled(false);
				jpnlSeat.add(jckbSeat[i]);
				
			}//end for
			jpnlSeat.setBounds(60, 280, (jckbSeat[0].getWidth()+15)*5-15, (jckbSeat[0].getHeight()+9)*4-9);
			imageScreen = new JLabel(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/imageicon_standard_screen(440x65).png"));
			jpnlSeat.setOpaque(false);
			
			
		}else if(sbv.getSelectedScreenName().equals("프리미엄")){
			
			jckbSeat = new JCheckBox[10];
			
			int x = 0;
			int y = 0;
			int y2 = 0;
			int cnt = 0;
			
			for(int i = 0; i<jckbSeat.length; i++) {
				jckbSeat[i] = new JCheckBox(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/jbt_p_seat_selectable(77x49).png"));
				
				jckbSeat[i].setBounds(x,y,90,50);
				
				x+=95;
				cnt++;
				if(cnt<3) {
					y+=15;
					y2=y;
				}else{
					y2-=15;
					y=y2;
				}//end if~else
				
				if(cnt==5) {
					x=0;
					y+=80;
					cnt=0;
				}//end if
				
				jckbSeat[i].setContentAreaFilled(false);
				jpnlSeat.add(jckbSeat[i]);
				
			}//end for
			jpnlSeat.setBounds(40, 350, (jckbSeat[0].getWidth()+5)*5-5, jckbSeat[0].getHeight()*2+55);
			imageScreen = new JLabel(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/imageicon_premium_screen(440x85).png"));
			jpnlSeat.setOpaque(false);
			
		}//end if~else
		
		jbtnConfirm.setBounds((650/2)-125-5, 780-90, 125, 40);
		jbtnExit.setBounds((650/2)+5, 780-90, 125, 40);
		jbtnConfirm.setContentAreaFilled(false);
		jbtnConfirm.setBorderPainted(false);
		jbtnExit.setContentAreaFilled(false);
		jbtnExit.setBorderPainted(false);
		
		imageScreen.setBounds(55, 100, 440, 85);
		
		panelScreen.add(imageScreen);
		panelScreen.add(jpnlSeat);
		panelScreen.setBounds(50, 50, 550, 620);
		
		add(panelScreen);
		add(jbtnConfirm);
		add(jbtnExit);
		
		
		SCUSeatController ssc = new SCUSeatController(this);
		for(int i = 0; i<jckbSeat.length; i++) {
			jckbSeat[i].addItemListener(ssc);
		}//end for
		jbtnConfirm.addActionListener(ssc);
		jbtnExit.addActionListener(ssc);
		
		
		JLabel background = new JLabel(new ImageIcon("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/user_book_bg5(650x780).png"));
		background.setBounds(0,0,650,780);
		add(background);
		
		setResizable(false);
		setBounds(sbv.getX()+200, sbv.getY()+20, 650, 780);
		setVisible(true);
		
	}//Constructor


	public SCUBookingView getSbv() {
		return sbv;
	}
	public JButton getJbtnConfirm() {
		return jbtnConfirm;
	}
	public JButton getJbtnExit() {
		return jbtnExit;
	}
	public JCheckBox[] getJckbSeat() {
		return jckbSeat;
	}
	public void setJckbSeat(JCheckBox[] jckbSeat) {
		this.jckbSeat = jckbSeat;
	}
	public SCUMovieListView getSmlv() {
		return smlv;
	}
	
	
	
}//Class
