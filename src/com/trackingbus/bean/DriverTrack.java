package com.trackingbus.bean;

/**
 * @author designer-5
 *
 */
public class DriverTrack {
	private Integer track_id;
	private Integer driver_id;
	private Integer route_id;
	private String lat;
	private String lng;
	private String track_date;
	private String speed;
	private String route_name;
	private String driver_name;
	
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public Integer getTrack_id() {
		return track_id;
	}
	public void setTrack_id(Integer track_id) {
		this.track_id = track_id;
	}
	public Integer getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(Integer driver_id) {
		this.driver_id = driver_id;
	}
	public Integer getRoute_id() {
		return route_id;
	}
	public void setRoute_id(Integer route_id) {
		this.route_id = route_id;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getTrack_date() {
		return track_date;
	}
	public void setTrack_date(String track_date) {
		this.track_date = track_date;
	}
	public String getRoute_name() {
		return route_name;
	}
	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	
	
	
}
