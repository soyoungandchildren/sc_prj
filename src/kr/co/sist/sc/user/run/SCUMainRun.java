package kr.co.sist.sc.user.run;

import java.io.IOException;

import kr.co.sist.sc.user.nio.SCUFileClient;
import kr.co.sist.sc.user.view.SCUMainView;

public class SCUMainRun{
	
	
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
		new SCUMainView();
	}//Main	
}//SCUMainRun