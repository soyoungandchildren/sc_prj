package kr.co.sist.sc.user.vo;

public class SCUAddOrderSnackVO {
	private String snack_name, member_id, snack_img;
	private int quan;
	
	public SCUAddOrderSnackVO(String snack_name, String member_id, String snack_img, int quan) {
		this.snack_name = snack_name;
		this.member_id = member_id;
		this.snack_img = snack_img;
		this.quan = quan;
	}

	public String getSnack_name() {
		return snack_name;
	}

	public String getMember_id() {
		return member_id;
	}

	public String getSnack_img() {
		return snack_img;
	}

	public int getQuan() {
		return quan;
	}

	@Override
	public String toString() {
		return "SCUAddOrderSnackVO [snack_name=" + snack_name + ", member_id=" + member_id + ", snack_img=" + snack_img
				+ ", quan=" + quan + "]";
	}
	
}
