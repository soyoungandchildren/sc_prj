package kr.co.sist.sc.user.controller;

import javax.swing.JLabel;

public class SCUClientThreadHelper implements Runnable{
	
	private String text;
	private JLabel jlblBookingRank;

	public SCUClientThreadHelper(String text, JLabel jlblBookingRank){
		this.text = text;
		this.jlblBookingRank = jlblBookingRank;
		
		Thread t = new Thread(this);
		t.start();
		
	}
	
	@Override
	public void run() {
		
		int x = 0;
		int y = 150;
		while(true) {
			
			jlblBookingRank.setText((text).substring(x, y));
			
			try {
				
				Thread.sleep(60);
				
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

}
