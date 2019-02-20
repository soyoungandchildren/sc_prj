package kr.co.sist.sc.user.vo;

public class SCUInsertRatingVO {

	private String book_number, review, member_id;
	private int movie_rate;

	public SCUInsertRatingVO(String book_number, int movie_rate, String review, String member_id) {
		super();
		this.book_number = book_number;
		this.movie_rate = movie_rate;
		this.review = review;
		this.member_id = member_id;
	}

	public String getBook_number() {
		return book_number;
	}

	public int getMovie_rate() {
		return movie_rate;
	}

	public String getReview() {
		return review;
	}

	public String getMember_id() {
		return member_id;
	}

	@Override
	public String toString() {
		return "SCUInsertRatingVO [book_number=" + book_number + ", review=" + review + ", member_id=" + member_id
				+ ", movie_rate=" + movie_rate + "]";
	}
	
	
}
