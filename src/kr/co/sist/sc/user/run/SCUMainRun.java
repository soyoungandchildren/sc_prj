package kr.co.sist.sc.user.run;

import java.io.IOException;

import kr.co.sist.sc.user.nio.SCUFileClient;
import kr.co.sist.sc.user.view.SCUMainView;

public class SCUMainRun implements Runnable{
	
	@Override
	public void run() {
//		while(true) {
//	         try {
//	            SCUFileClient.getInstance().connectToServer(0);
//	            Thread.sleep(1000*10);
//			 }catch(IOException ioe) {
//				 ioe.printStackTrace();
//	         }catch(InterruptedException ie) {
//	        	 ie.printStackTrace();
//	         }//end catch
//		}
	}
	
   public void initFile() {
	   try {
		   SCUFileClient.getInstance().connectToServer(1);
		   SCUFileClient.getInstance().connectToServer(2);
	   }catch(IOException ioe) {
		   ioe.printStackTrace();
	   }
   }
	
	
	public static void main(String[] args) {
				SCUMainRun smr = new SCUMainRun();
		
		smr.initFile();
//		Thread t = new Thread(smr);
//		t.start();
		
		new SCUMainView();
	}//Main	
}//SCUMainRun