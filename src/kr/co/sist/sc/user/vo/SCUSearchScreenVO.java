package kr.co.sist.sc.user.vo;

public class SCUSearchScreenVO {

	private String screen_date, start_time, end_time, screen_name, remain_seat, screen_num;

	public SCUSearchScreenVO(String screen_date, String start_time, String end_time, String screen_name, String remain_seat, String screen_num) {
		super();
		this.screen_date = screen_date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.screen_name = screen_name;
		this.remain_seat = remain_seat;
		this.screen_num = screen_num;
	}

	public String getScreen_date() {
		return screen_date;
	}
	
	public String getRemain_seat() {
		return remain_seat;
	}

	public String getStart_time() {
		return start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public String getScreen_name() {
		return screen_name;
	}

	public String getScreen_num() {
		return screen_num;
	}

	@Override
	public String toString() {
		return "SCUSearchScreenVO [screen_date=" + screen_date + ", start_time=" + start_time + ", end_time=" + end_time
				+ ", screen_name=" + screen_name + "]";
	}
	
}//Class
