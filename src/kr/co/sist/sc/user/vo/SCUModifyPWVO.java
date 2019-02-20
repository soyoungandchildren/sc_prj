package kr.co.sist.sc.user.vo;	

public class SCUModifyPWVO {
	String member_id, password;

	public SCUModifyPWVO(String password, String member_id) {
		this.member_id = member_id;
		this.password = password;
	}

	public String getMember_id() {
		return member_id;
	}//getMember_id

	public String getPassword() {
		return password;
	}//getPassword

}//class
