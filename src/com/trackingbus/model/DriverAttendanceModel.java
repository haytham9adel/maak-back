package com.trackingbus.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tbl_driver_attendance")
public class DriverAttendanceModel {
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="a_id")
	private Integer a_id;
	
	@Column(name="driver_id")
	private Integer driver_id;
	
	@Column(name="login_time")
	private String login_time;
	 
	@Column(name="login_date")
	private String login_date;
	
	@Column(name="logout_time")
	private String logout_time;
	
	@Column(name="login_evening")
	private String login_evening;
	
	@Column(name="logout_evening")
	private String logout_evening;
	
	
	public String getLogin_evening() {
		return login_evening;
	}

	public void setLogin_evening(String login_evening) {
		this.login_evening = login_evening;
	}

	public String getLogout_evening() {
		return logout_evening;
	}

	public void setLogout_evening(String logout_evening) {
		this.logout_evening = logout_evening;
	}

	@Column(name="status")
	private Integer status;

	public Integer getA_id() {
		return a_id;
	}

	public void setA_id(Integer a_id) {
		this.a_id = a_id;
	}

	public Integer getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(Integer driver_id) {
		this.driver_id = driver_id;
	}

	 
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLogin_time() {
		return login_time;
	}

	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}

	public String getLogin_date() {
		return login_date;
	}

	public void setLogin_date(String login_date) {
		this.login_date = login_date;
	}

	public String getLogout_time() {
		return logout_time;
	}

	public void setLogout_time(String logout_time) {
		this.logout_time = logout_time;
	}
	

}
