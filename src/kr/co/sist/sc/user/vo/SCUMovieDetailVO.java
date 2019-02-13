package kr.co.sist.sc.user.vo;

public class SCUMovieDetailVO {
	
	private String movie_title, movie_img, genre, country, director, movie_grade, playdate,
				synopsis, actor;
	private int runningtime;
	public SCUMovieDetailVO(String movie_title, String movie_img, String genre, String country, String director,
			String movie_grade, String playdate, String synopsis, String actor, int runningtime) {
		super();
		this.movie_title = movie_title;
		this.movie_img = movie_img;
		this.genre = genre;
		this.country = country;
		this.director = director;
		this.movie_grade = movie_grade;
		this.playdate = playdate;
		this.synopsis = synopsis;
		this.actor = actor;
		this.runningtime = runningtime;
	}
	public String getMovie_title() {
		return movie_title;
	}
	public String getMovie_img() {
		return movie_img;
	}
	public String getGenre() {
		return genre;
	}
	public String getCountry() {
		return country;
	}
	public String getDirector() {
		return director;
	}
	public String getMovie_grade() {
		return movie_grade;
	}
	public String getPlaydate() {
		return playdate;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public String getActor() {
		return actor;
	}
	public int getRunningtime() {
		return runningtime;
	}
	
	
}//Class
