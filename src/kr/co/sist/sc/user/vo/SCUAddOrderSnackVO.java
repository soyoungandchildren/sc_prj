package kr.co.sist.sc.user.vo;

public class SCUAddOrderSnackVO {
	private String snack_name, member_id;
	private int quan;
	
	public SCUAddOrderSnackVO(String snack_name, String member_id, int quan) {
		this.snack_name = snack_name;
		this.member_id = member_id;
		this.quan = quan;
	}

	public String getSnack_name() {
		return snack_name;
	}

	public String getMember_id() {
		return member_id;
	}

	public int getQuan() {
		return quan;
	}

	@Override
	public String toString() {
		return "SCUAddOrderSnackVO [snack_name=" + snack_name + ", member_id=" + member_id + ", quan=" + quan + "]";
	}

}
