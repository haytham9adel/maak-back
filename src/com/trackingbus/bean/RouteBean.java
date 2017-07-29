package com.trackingbus.bean;

public class RouteBean {
	
	private Integer route_id;
	private String route_name;
	private Integer school_id;
	private String source;
	private String destination;
	private String source_lat;
	private String source_lng;
	private String destination_lat;
	private String destination_lng;
	private String note;
	private String radius;
	private String source_note;
	private String destination_note;
	
	
	public String getSource_note() {
		return source_note;
	}
	public void setSource_note(String source_note) {
		this.source_note = source_note;
	}
	public String getDestination_note() {
		return destination_note;
	}
	public void setDestination_note(String destination_note) {
		this.destination_note = destination_note;
	}
	public String getRadius() {
		return radius;
	}
	public void setRadius(String radius) {
		this.radius = radius;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getSource_lat() {
		return source_lat;
	}
	public void setSource_lat(String source_lat) {
		this.source_lat = source_lat;
	}
	public String getSource_lng() {
		return source_lng;
	}
	public void setSource_lng(String source_lng) {
		this.source_lng = source_lng;
	}
	public String getDestination_lat() {
		return destination_lat;
	}
	public void setDestination_lat(String destination_lat) {
		this.destination_lat = destination_lat;
	}
	public String getDestination_lng() {
		return destination_lng;
	}
	public void setDestination_lng(String destination_lng) {
		this.destination_lng = destination_lng;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public Integer getRoute_id() {
		return route_id;
	}
	public void setRoute_id(Integer route_id) {
		this.route_id = route_id;
	}
	public String getRoute_name() {
		return route_name;
	}
	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}
	public Integer getSchool_id() {
		return school_id;
	}
	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}
}
