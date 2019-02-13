package kr.co.sist.sc.user.vo;

public class SCUMovieListVO {

	private String movie_img, movie_title, movie_grade, genre, runningtime, playdate, actor, movie_code;
	private double rating_avg;
	private int audience;
	public SCUMovieListVO(String movie_img, String movie_title, String movie_grade, String genre, String runningtime,
			String playdate, String actor, String movie_code, double rating_avg, int audience) {
		super();
		this.movie_img = movie_img;
		this.movie_title = movie_title;
		this.movie_grade = movie_grade;
		this.genre = genre;
		this.runningtime = runningtime;
		this.playdate = playdate;
		this.actor = actor;
		this.movie_code = movie_code;
		this.rating_avg = rating_avg;
		this.audience = audience;
	}//Constructor
	public String getMovie_img() {
		return movie_img;
	}
	public String getMovie_title() {
		return movie_title;
	}
	public String getMovie_grade() {
		return movie_grade;
	}
	public String getGenre() {
		return genre;
	}
	public String getRunningtime() {
		return runningtime;
	}
	public String getPlaydate() {
		return playdate;
	}
	public String getActor() {
		return actor;
	}
	public String getMovie_code() {
		return movie_code;
	}
	public double getRating_avg() {
		return rating_avg;
	}
	public int getAudience() {
		return audience;
	}
	@Override
	public String toString() {
		return "SCUMovieListVO [movie_img=" + movie_img + ", movie_title=" + movie_title + ", movie_grade="
				+ movie_grade + ", genre=" + genre + ", runningtime=" + runningtime + ", playdate=" + playdate
				+ ", actor=" + actor + ", movie_code=" + movie_code + ", rating_avg=" + rating_avg + ", audience="
				+ audience + "]";
	}//toString
	
}//Class
