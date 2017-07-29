package com.trackingbus.bean;

public class HolidayBean {
	private Integer h_id;
	private String holiday_date;
    private String holiday_name;
    private Integer school_id;
    private Integer month;
    private Integer year;
    private String holiday_enddate;
    private String day;
    private String holiday_start_date;
	public String getHoliday_start_date() {
		return holiday_start_date;
	}
	public void setHoliday_start_date(String holiday_start_date) {
		this.holiday_start_date = holiday_start_date;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getHoliday_enddate() {
		return holiday_enddate;
	}
	public void setHoliday_enddate(String holiday_enddate) {
		this.holiday_enddate = holiday_enddate;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
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
