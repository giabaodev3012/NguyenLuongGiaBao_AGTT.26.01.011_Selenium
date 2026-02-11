package DataObjects;

import Constant.SeatType;
import Constant.Station;

public class TicketInfo {

	private String departDate;
	private Station departFrom;
	private Station arriveAt;
	private SeatType seatType;
	private String ticketAmount;

	public TicketInfo(String departDate, Station departFrom, Station arriveAt, SeatType seatType, String ticketAmount) {
		this.departDate = departDate;
		this.departFrom = departFrom;
		this.arriveAt = arriveAt;
		this.seatType = seatType;
		this.ticketAmount = ticketAmount;
	}

	public TicketInfo(Station departFrom, Station arriveAt) {
		this.departFrom = departFrom;
		this.arriveAt = arriveAt;
	}

	public String getDepartDate() {
		return departDate;
	}

	public void setDepartDate(String departDate) {
		this.departDate = departDate;
	}

	public Station getDepartFrom() {
		return departFrom;
	}

	public void setDepartFrom(Station departFrom) {
		this.departFrom = departFrom;
	}

	public Station getArriveAt() {
		return arriveAt;
	}

	public void setArriveAt(Station arriveAt) {
		this.arriveAt = arriveAt;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}

	public String getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(String ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
}
