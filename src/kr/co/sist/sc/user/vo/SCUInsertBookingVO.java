package kr.co.sist.sc.user.vo;

public class SCUInsertBookingVO {

	private int personnel;
	private String book_number, movie_start, member_id, screen_num;
	
	public SCUInsertBookingVO(int personnel, String book_number, String movie_start,
			String member_id, String screen_num) {
		super();
		this.personnel = personnel;
		this.book_number = book_number;
		this.movie_start = movie_start;
		this.member_id = member_id;
		this.screen_num = screen_num;
	}
	
	public String getBook_number() {
		return book_number;
	}
	public int getPersonnel() {
		return personnel;
	}
	public String getMovie_start() {
		return movie_start;
	}
	public String getMember_id() {
		return member_id;
	}
	public String getScreen_num() {
		return screen_num;
	}


	
	
	
}//Class
