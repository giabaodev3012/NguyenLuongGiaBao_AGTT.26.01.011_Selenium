package DataObjects;

public class User {
	private String username;
	private String password;
	private String confirmpassword;
	private String pid;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String confirmpassword, String pid) {
		this.username = username;
		this.password = password;
		this.confirmpassword = confirmpassword;
		this.pid=pid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
