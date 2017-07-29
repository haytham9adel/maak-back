package com.trackingbus.model;
import java.io.Serializable;
import java.util.Date;

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
@Table(name="student_track")
public class StudentTrackingModel {
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="track_id")
	private Integer track_id;
	
	@Column(name="student_id")
	private Integer student_id;
	
	@Column(name="lat")
	private String lat;
	
	@Column(name="lng")
	private String lng;
	
	@Column(name="track_date")
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
