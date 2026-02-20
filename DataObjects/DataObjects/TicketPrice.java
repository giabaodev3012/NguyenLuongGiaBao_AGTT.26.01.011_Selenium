package DataObjects;

public class TicketPrice {
	private String seatType;
	private String price;

	public TicketPrice(String seatType, String price) {
		this.seatType = seatType;
		this.price = price;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
