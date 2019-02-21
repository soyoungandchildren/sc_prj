package kr.co.sist.sc.user.vo;

public class SCUMainVO {

	private String movie_title, movie_img;
	private int audience;
	
	public SCUMainVO(String movie_title, String movie_img, int audience) {
		this.movie_title = movie_title;
		this.movie_img = movie_img;
		this.audience = audience;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public String getMovie_img() {
		return movie_img;
	}

	public int getAudience() {
		return audience;
	}

	@Override
	public String toString() {
		return "SCUMainVO [movie_title=" + movie_title + ", movie_img=" + movie_img + ", audience=" + audience + "]";
	}
	
}//Class
