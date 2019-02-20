package kr.co.sist.sc.user.vo;

public class SCUSearchRatingVO {
	private int movie_rate;
	private String review, member_id;
	
	public SCUSearchRatingVO(int movie_rate, String review, String member_id) {
		super();
		this.movie_rate = movie_rate;
		this.review = review;
		this.member_id = member_id;
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
	
}
