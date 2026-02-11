package Constant;

public enum Station {
	SAIGON("Sài Gòn"),
	PHANTHIET("Phan Thiết"),
	NHATRANG("Nha Trang"),
	DANANG("Đà Nẵng"),
	HUE("Huế"),
	QUANGNGAI("Quảng Ngãi");

	private String name;

	Station(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}