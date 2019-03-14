package kr.co.sist.sc.user.controller;

import javax.swing.JTextArea;

public class SCUClientThreadHelper implements Runnable{
	
	private String text;
	private JTextArea jtaBookingRank;

	public SCUClientThreadHelper(String text, JTextArea jtaBookingRank){
		this.text = text;
		this.jtaBookingRank = jtaBookingRank;
		
		Thread t = new Thread(this);
		t.start();
		
	}
	
	@Override
	public void run() {
		int x = 0;
		int y = 150;
		while(true) {
			jtaBookingRank.setText((text).substring(x, y));
			try {
				Thread.sleep(85);
	            x+=1;
	            y+=1;
	            if(y==text.length()) {
	            	x=0;
	            	y=150;
	            }
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//end while
	}

	public void setText(String text) {
		this.text = text;
	}
	

}
