package kr.co.sist.sc.user.vo;

public class SCUGetBookingHistoryVO {
	private String book_number, payment_date, movie_start;
	private int personnel;

	public SCUGetBookingHistoryVO(String book_number, String payment_date, String movie_start, int personnel) {
		super();
		this.book_number = book_number;
		this.payment_date = payment_date;
		this.movie_start = movie_start;
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

}//class
