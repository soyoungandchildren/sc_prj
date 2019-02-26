package kr.co.sist.sc.user.vo;

public class SCURemoveAccountVO {
	
	private String member_id, password, name, birthdate, phone, membership;
	private int hold_point, acc_point;
	
	public SCURemoveAccountVO(String member_id, String password, String name, String birthdate, String phone,
			String membership, int hold_point, int acc_point) {
		super();
		this.member_id = member_id;
		this.password = password;
		this.name = name;
		this.birthdate = birthdate;
		this.phone = phone;
		this.membership = membership;
		this.hold_point = hold_point;
		this.acc_point = acc_point;
	}
	public String getMember_id() {
		return member_id;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public String getPhone() {
		return phone;
	}
	public String getMembership() {
		return membership;
	}
	public int getHold_point() {
		return hold_point;
	}
	public int getAcc_point() {
		return acc_point;
	}
	
	
	
}
