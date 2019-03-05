package kr.co.sist.sc.user.images;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CustomFontList {

	private static CustomFontList cfl;
	
	private CustomFontList() {
	}
	
	public static CustomFontList getInstance() {
		if(cfl==null) {
			cfl = new CustomFontList();
		}
		return cfl;
	}
	
	public Font getFontTitle() {
		Font font = null;
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/nanumgodic.TTF")).deriveFont(30f);
			
		}catch(FileNotFoundException fnfe){
			fnfe.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(FontFormatException ffe) {
			ffe.printStackTrace();
		}
		
		return font;
	}
	
	public Font getFontLabel() {
		Font font = null;
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/nanumgodic.TTF")).deriveFont(15f);
			
		}catch(FileNotFoundException fnfe){
			fnfe.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(FontFormatException ffe) {
			ffe.printStackTrace();
		}
		
		return font;
	}
	
	
}