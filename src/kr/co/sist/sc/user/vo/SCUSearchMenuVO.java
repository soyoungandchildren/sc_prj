package kr.co.sist.sc.user.vo;

public class SCUSearchMenuVO {
	//판매중인 스낵 불러오기
	private String snack_img, snack_name;
	
	public SCUSearchMenuVO(String snack_img, String snack_name) {
		this.snack_img = snack_img;
		this.snack_name = snack_name;
	}

	public String getSnack_img() {
		return snack_img;
	}

	public String getSnack_name() {
		return snack_name;
	}

	@Override
	public String toString() {
		return "SCUSearchMenuVO [snack_img=" + snack_img + ", snack_name=" + snack_name + "]";
	}
}
		