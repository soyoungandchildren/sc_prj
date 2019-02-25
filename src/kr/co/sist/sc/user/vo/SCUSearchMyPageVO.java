package kr.co.sist.sc.user.vo;

public class SCUSearchMyPageVO {
	
	private String member_id, name, birthdate, phone, membership, input_date;
	private int hold_point, acc_point;
	public SCUSearchMyPageVO(String member_id, String name, String birthdate, String phone, String membership,
			String input_date, int hold_point, int acc_point) {
		super();
		this.member_id = member_id;
		this.name = name;
		this.birthdate = birthdate;
		this.phone = phone;
		this.membership = membership;
		this.input_date = input_date;
		this.hold_point = hold_point;
		this.acc_point = acc_point;
	}
	public String getMember_id() {
		return member_id;
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
	public String getInput_date() {
		return input_date;
	}
	public int getHold_point() {
		return hold_point;
	}
	public int getAcc_point() {
		return acc_point;
	}
}
