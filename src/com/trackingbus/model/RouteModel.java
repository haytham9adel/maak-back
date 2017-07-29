package com.trackingbus.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Rudiment Webtech Solutions
 *
 */
@Entity
@Table(name="tbl_route")
public class RouteModel {
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="route_id")
	private Integer route_id;
	
	@Column(name="school_id")
	private Integer school_id;
	
	@Column(name="route_name")
	private String route_name;
	
	@Column(name="source")
	private String source;
	
	@Column(name="destination")
	private String destination;
	
	@Column(name="source_lat")
	private String source_lat;
	
	@Column(name="source_lng")
	private String source_lng;
	
	@Column(name="destination_lat")
	private String destination_lat;
	
	@Column(name="destination_lng")
	private String destination_lng;
	
	@Column(name="radius")
	private String radius;
	
	@Column(name="source_note")
	private String source_note;
	
	@Column(name="destination_note")
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

	@Column(name="note")
	private String note;
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public Integer getRoute_id() {
		return route_id;
	}

	public void setRoute_id(Integer route_id) {
		this.route_id = route_id;
	}

	public Integer getSchool_id() {
		return school_id;
	}

	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}

	public String getRoute_name() {
		return route_name;
	}

	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}

	
	
	

}
