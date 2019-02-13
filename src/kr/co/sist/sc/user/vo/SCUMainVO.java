package kr.co.sist.sc.user.vo;

public class SCUMainVO {

	private String movie_title, movie_img;
	private int personnel;
	
	public SCUMainVO(String movie_title, String movie_img, int personnel) {
		super();
		this.movie_title = movie_title;
		this.movie_img = movie_img;
		this.personnel = personnel;
	}//Constructor
	
	//getters
	public String getMovie_title() {
		return movie_title;
	}
	public String getMovie_img() {
		return movie_img;
	}
	public int getPersonnel() {
		return personnel;
	}
	//end getters
	
}//Class
