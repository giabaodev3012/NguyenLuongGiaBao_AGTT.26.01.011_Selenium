package Constant;

public enum MenuTab {
	LOGIN("Login"),
	REGISTER("Register"),
	FAQ("FAQ"),
	LOGOUT("Log out");

	private final String text;

	MenuTab(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
