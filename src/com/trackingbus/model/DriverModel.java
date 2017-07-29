package com.trackingbus.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *@author Rudiment Webtech Solutions
 **/

@Entity
@Table(name="tbl_driver")
public class DriverModel {
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="driver_id")
	private Integer driver_id;
	
	@Column(name="driver_fname")
	private String driver_fname;
	
	@Column(name="driver_lname")
	private String driver_lname;
	
	@Column(name="d_email")
	private String d_email;
	
	@Column(name="contact_number")
	private String contact_number;
	
	@Column(name="address")
	private String address;
	
	@Column(name="image_path")
	private String image_path;
	
	@Column(name="blood_group")
	private String blood_group;
	
	
	@Column(name="password")
	private String password;
	
	@Column(name="username")
	private String username;

	@Column(name="middle_name")
	private String middle_name;
	
	@Column(name="dob")
	private String dob;

	@Column(name="nationality")
	private String nationality;

	@Column(name="licence_expiry")
	private String licence_expiry;
	
	@Column(name="insurance_card_copy")
	private String insurance_card_copy;
	
	@Column(name="insurance_document_name")
	private String insurance_document_name;

	@Column(name="insurance_document_expiry")
	private String insurance_document_expiry;
	
	@Column(name="remind_day")
	private String remind_day;
	
	@Column(name="logged_status")
	private Integer logged_status;
	
	@Column(name="device_id")
	private String device_id;
	
	@Column(name="lang")
	private Integer lang;
	
	public Integer getLang() {
		return lang;
	}

	public void setLang(Integer lang) {
		this.lang = lang;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public Integer getLogged_status() {
		return logged_status;
	}

	public void setLogged_status(Integer logged_status) {
		this.logged_status = logged_status;
	}

	public String getDevice_token() {
		return device_token;
	}

	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}

	@Column(name="insurance_end_date")
	private String insurance_end_date;
		
	@Column(name="device_token")
	private String device_token;
	
	public String getInsurance_end_date() {
		return insurance_end_date;
	}

	public void setInsurance_end_date(String insurance_end_date) {
		this.insurance_end_date = insurance_end_date;
	}

	public String getInsurance_card_copy() {
		return insurance_card_copy;
	}

	public void setInsurance_card_copy(String insurance_card_copy) {
		this.insurance_card_copy = insurance_card_copy;
	}

	public String getInsurance_document_name() {
		return insurance_document_name;
	}

	public void setInsurance_document_name(String insurance_document_name) {
		this.insurance_document_name = insurance_document_name;
	}

	public String getInsurance_document_expiry() {
		return insurance_document_expiry;
	}

	public void setInsurance_document_expiry(String insurance_document_expiry) {
		this.insurance_document_expiry = insurance_document_expiry;
	}

	public String getRemind_day() {
		return remind_day;
	}

	public void setRemind_day(String remind_day) {
		this.remind_day = remind_day;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getLicence_expiry() {
		return licence_expiry;
	}

	public void setLicence_expiry(String licence_expiry) {
		this.licence_expiry = licence_expiry;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="driver_school_id")
	private Integer driver_school_id;
	
	@Column(name="route_id")
	private Integer route_id;
	
	
	public Integer getRoute_id() {
		return route_id;
	}

	public void setRoute_id(Integer route_id) {
		this.route_id = route_id;
	}

	@Column(name="status")
	private Integer status;

	public Integer getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(Integer driver_id) {
		this.driver_id = driver_id;
	}

	public String getDriver_fname() {
		return driver_fname;
	}

	public void setDriver_fname(String driver_fname) {
		this.driver_fname = driver_fname;
	}

	public String getDriver_lname() {
		return driver_lname;
	}

	public void setDriver_lname(String driver_lname) {
		this.driver_lname = driver_lname;
	}

	public String getD_email() {
		return d_email;
	}

	public void setD_email(String d_email) {
		this.d_email = d_email;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getBlood_group() {
		return blood_group;
	}

	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}

	public Integer getDriver_school_id() {
		return driver_school_id;
	}

	public void setDriver_school_id(Integer driver_school_id) {
		this.driver_school_id = driver_school_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	

}
