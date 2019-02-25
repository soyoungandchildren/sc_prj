package kr.co.sist.sc.user.vo;

public class SCUMainVO {

	private String movie_title, movie_img;
	private int audience, cnt;
	
	public SCUMainVO(String movie_title, String movie_img, int audience, int cnt) {
		this.movie_title = movie_title;
		this.movie_img = movie_img;
		this.audience = audience;
		this.cnt = cnt;
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

	public int getCnt() {
		return cnt;
	}

	@Override
	public String toString() {
		return "SCUMainVO [movie_title=" + movie_title + ", movie_img=" + movie_img + ", audience=" + audience
				+ ", cnt=" + cnt + "]";
	}
	
}//Class
