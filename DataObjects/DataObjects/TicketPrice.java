package DataObjects;

import Constant.SeatType;

public class TicketPrice {
	private SeatType seatType;
	private String price;

	public TicketPrice(SeatType seatType, String price) {
		this.seatType = seatType;
		this.price = price;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
