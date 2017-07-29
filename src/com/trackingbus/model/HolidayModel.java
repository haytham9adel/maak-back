package com.trackingbus.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tbl_holiday")
public class HolidayModel {

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="h_id")
	private Integer h_id;
	
	@Column(name="holiday_date")
	private String holiday_date;
	
	@Column(name="holiday_name")
	private String holiday_name;
	
	@Column(name="school_id")
	private Integer school_id;
	
	@Column(name="holiday_enddate")
	private String holiday_enddate;
	
	@Column(name="holiday_start_date")
	private String holiday_start_date;
	
	

	public String getHoliday_start_date() {
		return holiday_start_date;
	}

	public void setHoliday_start_date(String holiday_start_date) {
		this.holiday_start_date = holiday_start_date;
	}

	public String getHoliday_enddate() {
		return holiday_enddate;
	}

	public void setHoliday_enddate(String holiday_enddate) {
		this.holiday_enddate = holiday_enddate;
	}

	public Integer getSchool_id() {
		return school_id;
	}

	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}

	public String getHoliday_name() {
		return holiday_name;
	}

	public void setHoliday_name(String holiday_name) {
		this.holiday_name = holiday_name;
	}

	public Integer getH_id() {
		return h_id;
	}

	public void setH_id(Integer h_id) {
		this.h_id = h_id;
	}

	public String getHoliday_date() {
		return holiday_date;
	}

	public void setHoliday_date(String holiday_date) {
		this.holiday_date = holiday_date;
	}
	
}

