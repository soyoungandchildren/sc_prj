package kr.co.sist.sc.user.vo;

public class SCUGetBookingHistoryVO {
	private String book_number, payment_date, movie_start;
	private int personnel, screen_price;
	
	public SCUGetBookingHistoryVO(String book_number, String payment_date, int personnel, int screen_price, String movie_start
			) {
		this.book_number = book_number;
		this.payment_date = payment_date;
		this.movie_start = movie_start;
		this.screen_price = screen_price;
		this.personnel = personnel;
	}

	public String getBook_number() {
		return book_number;
	}

	public String getPayment_date() {
		return payment_date;
	}

	public String getMovie_start() {
		return movie_start;
	}

	public int getPersonnel() {
		return personnel;
	}

	public int getScreen_price() {
		return screen_price;
	}
	
	

}// class
