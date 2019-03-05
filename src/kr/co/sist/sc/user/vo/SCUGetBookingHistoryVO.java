package kr.co.sist.sc.user.vo;

public class SCUGetBookingHistoryVO {
	private String book_number, movie_title, payment_date, movie_start;
	private int personnel, screen_price;

	public SCUGetBookingHistoryVO(String book_number, String movie_title, String payment_date, String movie_start,
			int personnel, int screen_price) {
		this.book_number = book_number;
		this.movie_title = movie_title;
		this.payment_date = payment_date;
		this.movie_start = movie_start;
		this.personnel = personnel;
		this.screen_price = screen_price;
	}

	public String getBook_number() {
		return book_number;
	}

	public String getMovie_title() {
		return movie_title;
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
