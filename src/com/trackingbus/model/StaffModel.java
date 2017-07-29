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
@Table(name="tbl_staff")
public class StaffModel {
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="staff_id")
	private Integer staff_id;
	
	@Column(name="staff_name")
	private String staff_name;
	
	@Column(name="staff_lname")
	private String staff_lname;
	
	@Column(name="staff_email")
	private String staff_email;
	
	@Column(name="staff_contact")
	private String staff_contact;
	
	@Column(name="address")
	private String address;
	
	@Column(name="dept_id")
	private Integer dept_id;
	
	@Column(name="image_path")
	private String image_path;
	
	@Column(name="education")
	private String education;

	@Column(name="school_id")
	private Integer school_id;
	
	
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
