package kr.co.sist.sc.user.vo;

public class SCUGetSnackHistoryVO {
	private String snack_order_num, snack_sale_date, check_exchange, snack_name;
	private int quan, snack_price;

	public SCUGetSnackHistoryVO(String snack_order_num, String snack_sale_date, String check_exchange,
			String snack_name, int quan, int snack_price) {
		super();
		this.snack_order_num = snack_order_num;
		this.snack_sale_date = snack_sale_date;
		this.check_exchange = check_exchange;
		this.snack_name = snack_name;
		this.quan = quan;
		this.snack_price = snack_price;
	}

	public String getSnack_order_num() {
		return snack_order_num;
	}

	public String getSnack_sale_date() {
		return snack_sale_date;
	}

	public String getCheck_exchange() {
		return check_exchange;
	}

	public String getSnack_name() {
		return snack_name;
	}

	public int getQuan() {
		return quan;
	}

	public int getSnack_price() {
		return snack_price;
	}

}// class
