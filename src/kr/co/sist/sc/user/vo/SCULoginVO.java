package kr.co.sist.sc.user.vo;

public class SCULoginVO {
	String member_id, password;

	public SCULoginVO(String member_id, String password) {
		this.member_id = member_id;
		this.password = password;
	}

	public String getMember_id() {
		return member_id;
	}

	public String getPassword() {
		return password;
	}

}//class
