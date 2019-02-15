package kr.co.sist.sc.user.vo;

public class SCUSnackOrderDataVO {
	private String snack_img, snack_info;
	private int snack_price;
	
	public SCUSnackOrderDataVO(String snack_img, String snack_info, int snack_price) {
		this.snack_img = snack_img;
		this.snack_info = snack_info;
		this.snack_price = snack_price;
	}

	public String getSnack_img() {
		return snack_img;
	}

	public String getSnack_info() {
		return snack_info;
	}

	public int getSnack_price() {
		return snack_price;
	}
	
}//class
