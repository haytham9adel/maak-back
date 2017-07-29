package com.trackingbus.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author designer-5
 *
 */
@Entity
@Table(name="notification")
public class NotificationModel {
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="noti_id")
	private Integer noti_id;
	
	@Column(name="parent_id")
	private Integer parent_id;
	
	@Column(name="student_id")
	private Integer student_id;
	
	@Column(name="noti_type")
	private String noti_type;
	
	@Column(name="date")
	private String date;
	
	@Column(name="noti_desc")
	private String noti_desc;
	
	@Column(name="route_id")
	private Integer route_id;

	
	public String getNoti_desc() {
		return noti_desc;
	}

	public void setNoti_desc(String noti_desc) {
		this.noti_desc = noti_desc;
	}

	public Integer getNoti_id() {
		return noti_id;
	}

	public void setNoti_id(Integer noti_id) {
		this.noti_id = noti_id;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public Integer getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}

	public String getNoti_type() {
		return noti_type;
	}

	public void setNoti_type(String noti_type) {
		this.noti_type = noti_type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	

	public Integer getRoute_id() {
		return route_id;
	}

	public void setRoute_id(Integer route_id) {
		this.route_id = route_id;
	}
}
