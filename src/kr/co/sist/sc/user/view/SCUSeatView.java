package kr.co.sist.sc.user.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
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
		jpnlSeat.setBorder(new LineBorder(Color.BLACK));
		
		jbtnConfirm = new JButton("예매하기");
		jbtnExit = new JButton("닫기");
		
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
				
				jpnlSeat.add(jckbSeat[i]);
				
			}//end for
			jpnlSeat.setBounds(0, 150, 500, 300);
			
		
			
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
				
				jpnlSeat.add(jckbSeat[i]);
				
			}//end for
			jpnlSeat.setBounds(0, 100, 500, 400);
			
		}//end if~else
		jbtnConfirm.setBounds(143, 500, 100, 30);
		jbtnExit.setBounds(257, 500, 100, 30);
		
		add(jbtnConfirm);
		add(jbtnExit);
		add(jpnlSeat);
		
		
		SCUSeatController ssc = new SCUSeatController(this);
		for(int i = 0; i<jckbSeat.length; i++) {
			jckbSeat[i].addItemListener(ssc);
		}//end for
		jbtnConfirm.addActionListener(ssc);
		jbtnExit.addActionListener(ssc);
		
		setResizable(false);
		setBounds(sbv.getX()+200, sbv.getY()+20, 500, 600);
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
