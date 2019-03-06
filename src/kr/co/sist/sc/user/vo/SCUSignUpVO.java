package kr.co.sist.sc.user.vo;

public class SCUSignUpVO {
	String member_id, password, name, birthdate, phone;

	public SCUSignUpVO(String member_id, String password, String name, String birthdate, String phone) {
		super();
		this.member_id = member_id;
		this.password = password;
		this.name = name;
		this.birthdate = birthdate;
		this.phone = phone;
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

	@Override
	public String toString() {
		return "SCUSignUpVO [member_id=" + member_id + ", password=" + password + ", name=" + name + ", birthdate="
				+ birthdate + ", phone=" + phone + "]";
	}
	
	

}//class
