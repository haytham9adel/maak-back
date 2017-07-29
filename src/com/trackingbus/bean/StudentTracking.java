package com.trackingbus.bean;

import java.util.Date;

public class StudentTracking {

	private Integer track_id;
	private Integer student_id;
	private String lat;
	private String lng;
	private String track_date;
	public String getTrack_date() {
		return track_date;
	}
	public void setTrack_date(String track_date) {
		this.track_date = track_date;
	}
	public Integer getTrack_id() {
		return track_id;
	}
	public void setTrack_id(Integer track_id) {
		this.track_id = track_id;
	}
	public Integer getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
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
	
	
}
