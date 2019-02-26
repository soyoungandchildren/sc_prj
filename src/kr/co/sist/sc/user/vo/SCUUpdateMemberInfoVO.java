package kr.co.sist.sc.user.vo;

public class SCUUpdateMemberInfoVO {
	
	private String name, phone, member_ID;

	public SCUUpdateMemberInfoVO(String name, String phone, String member_ID) {
		super();
		this.name = name;
		this.phone = phone;
		this.member_ID = member_ID;
	}
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	public String getMember_ID() {
		return member_ID;
	}
	
}
