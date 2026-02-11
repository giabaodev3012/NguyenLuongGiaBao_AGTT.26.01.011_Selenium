package DataObjects;

import Constant.Station;

public class RouteInfo {

	private Station departFrom;
	private Station arriveAt;
	private String departTime;
	private String arriveTime;

	public RouteInfo(Station departFrom, Station arriveAt, String departTime, String arriveTime) {
		this.departFrom = departFrom;
		this.arriveAt = arriveAt;
		this.departTime = departTime;
		this.arriveTime = arriveTime;
	}

	public RouteInfo(Station departFrom, Station arriveAt) {
		this.departFrom = departFrom;
		this.arriveAt = arriveAt;
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
