package kr.co.sist.sc.user.vo;

public class SCUInsertSeatVO {
	
	private String book_number;
	private int seat_num;
	
	public SCUInsertSeatVO(String book_number, int seat_num) {
		super();
		this.book_number = book_number;
		this.seat_num = seat_num;
	}
	
	public String getBook_number() {
		return book_number;
	}
	public int getSeat_num() {
		return seat_num;
	}
	
}//Class
