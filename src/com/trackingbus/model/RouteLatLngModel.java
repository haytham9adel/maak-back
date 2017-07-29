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
@Table(name="route_lat_lng")
public class RouteLatLngModel {
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="route_id")
	private Integer route_id;
	
	
	@Column(name="lat")
	private String lat;
	
	@Column(name="lng")
	private String lng;
	
	@Column(name="school_id")
	private Integer school_id;

	@Column(name="student_id")
	private Integer student_id;

	@Column(name="address")
	private String address;

	@Column(name="student_name")
	private String student_name;
	
	@Column(name="parent_id")
	private Integer parent_id;
	
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public Integer getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	public Integer getSchool_id() {
		return school_id;
	}

	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}

}
