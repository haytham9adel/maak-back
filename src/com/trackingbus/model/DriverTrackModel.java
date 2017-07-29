package com.trackingbus.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="driver_track")
public class DriverTrackModel {
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="track_id")
	private Integer track_id;
	
	@Column(name="driver_id")
	private Integer driver_id;
	
	@Column(name="route_id")
	private Integer route_id;
	
	@Column(name="lat")
	private String lat;
	
	@Column(name="lng")
	private String lng;
	
	@Column(name="track_date")
	private String track_date;
	
	@Column(name="speed")
	private String speed;
	
	
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
	
	
	
	
}
