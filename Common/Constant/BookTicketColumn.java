package Constant;

public enum BookTicketColumn {

    DEPART_STATION("Depart Station"),
    ARRIVE_STATION("Arrive Station"),
    SEAT_TYPE("Seat Type"),
    DEPART_DATE("Depart Date"),
    BOOK_DATE("Book Date"),
    EXPIRED_DATE("Expired Date"),
    AMOUNT("Amount"),
    TOTAL_PRICE("Total Price");

	private final String headerName;

	BookTicketColumn(String headerName) {
		this.headerName = headerName;
	}

	public String getHeaderName() {
		return headerName;
	}
}
