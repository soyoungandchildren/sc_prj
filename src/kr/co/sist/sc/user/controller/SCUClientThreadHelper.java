package kr.co.sist.sc.user.controller;

import javax.swing.JLabel;

public class SCUClientThreadHelper implements Runnable{
	
	private String text;
	private JLabel jlblBookingRank;

	public SCUClientThreadHelper(String text, JLabel jlblBookingRank){
		this.text = text;
		this.jlblBookingRank = jlblBookingRank;
		
		//smv.getJlblBookingRank().setText((ranking.toString()).substring(x, y));
		Thread t = new Thread(this);
		t.start();
		
		/*
		 * 
		 *  while(true) {
         try {
            setImgBoard();
            x+=1;
            y+=1;
///////////////////////////////////            SCUFileClient.getInstance().connectToServer(0);
            if(y==ranking.toString().length()) {
            	x=0;
            	y=150;
            }
            
            smv.getJlblBookingRank().setText((ranking.toString()).substring(x, y));
            
            Thread.sleep(10);
         } catch (InterruptedException e) {
            e.printStackTrace();
///////////////////////////////////         }catch(IOException ioe) {
///////////////////////////////////        	 ioe.printStackTrace();
         }//end catch
		 * 
		 */
	}
	
	@Override
	public void run() {
		
		int x = 0;
		int y = 150;
		while(true) {
			
			jlblBookingRank.setText((text).substring(x, y));
			
			try {
				
				Thread.sleep(40);
				
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
