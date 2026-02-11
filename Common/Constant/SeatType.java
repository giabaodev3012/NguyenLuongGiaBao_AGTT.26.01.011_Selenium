package Constant;

public enum SeatType {
    HS("HS", "Hard seat"),
    SS("SS", "Soft seat"),
    SSC("SSC", "Soft seat with air conditioner"),
    HB("HB", "Hard bed"),
    SB("SB", "Soft bed"),
    SBC("SBC", "Soft bed with air conditioner");

	private final String code;
	private final String description;

	SeatType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
}