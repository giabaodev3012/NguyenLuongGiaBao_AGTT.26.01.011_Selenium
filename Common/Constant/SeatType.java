package Constant;

public enum SeatType {

    HS("HS"),
    SS("SS"),
    SSC("SSC"),
    HB("HB"),
    SB("SB"),
    SBC("SBC");

	private final String code;

	SeatType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
