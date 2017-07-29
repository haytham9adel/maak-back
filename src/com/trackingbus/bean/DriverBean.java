package com.trackingbus.bean;

import java.util.List;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

public class DriverBean {
	private Integer driver_id;
	private String driver_fname;
	private String driver_lname;
	private String d_email;
	private String contact_number;
	private String address;
	private String blood_group;
	private Integer driver_school_id;
	private Integer status;
	private String image_path;
	private String school_name;
	private Integer route_id;
	private String password;
	private String username;
	private String middle_name;
	private String dob;
	private String driver_picl;
	private String nationality;
	private String licence_expiry;
	private Integer lang;
	private String route_name;
	
	public Integer getLang() {
		return lang;
	}
	public void setLang(Integer lang) {
		this.lang = lang;
	}
	public String getDevice_token() {
		return device_token;
	}
	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}
	private String device_token;
 
	private List<MultipartFile> insurance_card_copy;
	private List<String> insurance_end_date;
	
	
	public List<String> getInsurance_end_date() {
		return insurance_end_date;
	}
	public void setInsurance_end_date(List<String> insurance_end_date) {
		this.insurance_end_date = insurance_end_date;
	}
	private List<String> insurance_document_name; 
	private List<String> insurance_document_expiry; 
	private List<String> remind_day; 
	private List<String> v_doc_id; 
	
	public List<MultipartFile> getInsurance_card_copy() {
		return insurance_card_copy;
	}
	public void setInsurance_card_copy(List<MultipartFile> insurance_card_copy) {
		this.insurance_card_copy = insurance_card_copy;
	}
	public List<String> getInsurance_document_name() {
		return insurance_document_name;
	}
	public void setInsurance_document_name(List<String> insurance_document_name) {
		this.insurance_document_name = insurance_document_name;
	}
	public List<String> getInsurance_document_expiry() {
		return insurance_document_expiry;
	}
	public void setInsurance_document_expiry(List<String> insurance_document_expiry) {
		this.insurance_document_expiry = insurance_document_expiry;
	}
	public List<String> getRemind_day() {
		return remind_day;
	}
	public void setRemind_day(List<String> remind_day) {
		this.remind_day = remind_day;
	}
	public List<String> getV_doc_id() {
		return v_doc_id;
	}
	public void setV_doc_id(List<String> v_doc_id) {
		this.v_doc_id = v_doc_id;
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
	public String getDriver_picl() {
		return driver_picl;
	}
	public void setDriver_picl(String driver_picl) {
		this.driver_picl = driver_picl;
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
	public Integer getRoute_id() {
		return route_id;
	}
	public void setRoute_id(Integer route_id) {
		this.route_id = route_id;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
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
	public String getRoute_name() {
		return route_name;
	}
	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}
	
}
