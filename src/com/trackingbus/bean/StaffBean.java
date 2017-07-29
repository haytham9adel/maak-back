package com.trackingbus.bean;

public class StaffBean {

	private Integer staff_id;
	private String staff_name;
	private String staff_email;
	private String staff_contact;
	private String address;
	private Integer dept_id;
	private String image_path;
	private String education;
	private Integer school_id;
	private String staff_lname;
	private String school_name;
	
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getStaff_lname() {
		return staff_lname;
	}
	public void setStaff_lname(String staff_lname) {
		this.staff_lname = staff_lname;
	}
	public Integer getSchool_id() {
		return school_id;
	}
	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}
	public Integer getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(Integer staff_id) {
		this.staff_id = staff_id;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getStaff_email() {
		return staff_email;
	}
	public void setStaff_email(String staff_email) {
		this.staff_email = staff_email;
	}
	public String getStaff_contact() {
		return staff_contact;
	}
	public void setStaff_contact(String staff_contact) {
		this.staff_contact = staff_contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getDept_id() {
		return dept_id;
	}
	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
}
