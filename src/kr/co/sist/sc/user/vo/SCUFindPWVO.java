package kr.co.sist.sc.user.vo;

public class SCUFindPWVO {
	String member_id, name, phone;

	public SCUFindPWVO(String member_id, String name, String phone) {
		super();
		this.member_id = member_id;
		this.name = name;
		this.phone = phone;
	}

	public String getMember_id() {
		return member_id;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

}//class
