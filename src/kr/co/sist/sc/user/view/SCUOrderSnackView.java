package kr.co.sist.sc.user.view;

import javax.swing.JDialog;

@SuppressWarnings("serial")
public class SCUOrderSnackView extends JDialog {
	private SCUSnackMenuView ssmv;
	
	public SCUOrderSnackView(SCUSnackMenuView ssmv, String snackName) {
		super(ssmv, "½º³¼ Á¤º¸", true);
		this.ssmv = ssmv;
		System.out.println(snackName);
		setVisible(true);
	}
	
	
}//class
