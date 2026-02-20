package DataObjects;

public class RouteInfo {

	private String departFrom;
	private String arriveAt;
	private String departTime;
	private String arriveTime;

	public RouteInfo(String departFrom, String arriveAt, String departTime, String arriveTime) {
		this.departFrom = departFrom;
		this.arriveAt = arriveAt;
		this.departTime = departTime;
		this.arriveTime = arriveTime;
	}

	public RouteInfo(String departFrom, String arriveAt) {
		this.departFrom = departFrom;
		this.arriveAt = arriveAt;
	}

	public String getDepartFrom() {
		return departFrom;
	}

	public void setDepartFrom(String departFrom) {
		this.departFrom = departFrom;
	}

	public String getArriveAt() {
		return arriveAt;
	}

	public void setArriveAt(String arriveAt) {
		this.arriveAt = arriveAt;
	}

	public String getDepartTime() {
		return departTime;
	}

	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
}
