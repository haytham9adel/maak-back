package com.trackingbus.bean;

import java.util.List;

import javax.persistence.Column;

public class StudentBean  {

	private Integer student_id;
	private String s_fname;
	private String s_lname;
	private String s_email;
	private String s_pass;
	private String s_address;
	private String s_image_path;
	private Integer s_city;
	private Integer s_state;
	private Integer s_country;
	private String s_contact;
	private String s_zip;
	private Integer s_parent_id;
	private Integer s_school_id;
	private String school_name;
	private Integer p_status_id;
	private Integer s_driver;
	private Integer s_route_id;
	private String father_name;
	private String grand_name;
	private String family_name;
	private String gender;
	private String student_class;
	private String dob;
	private String nationality;
	private String blood_type;	
	private String student_lat;
	private String student_lng;
	private Integer blink_status;
	private List<String> relationship_details; 
	private List<String> parent_list; 
	
	private Integer p_1;
	
	private Integer p_2;
	
	private Integer p_3;
	
	private String r_1;
	
	private String  r_2;
	
	private String r_3;
	
	private String pp_1;
		
	private String  pp_2;
		
	private String pp_3;

	public List<String> getRelationship_details() {
		return relationship_details;
	}
	public void setRelationship_details(List<String> relationship_details) {
		this.relationship_details = relationship_details;
	}
	public List<String> getParent_list() {
		return parent_list;
	}
	public void setParent_list(List<String> parent_list) {
		this.parent_list = parent_list;
	}
	public Integer getBlink_status() {
		return blink_status;
	}
	public void setBlink_status(Integer blink_status) {
		this.blink_status = blink_status;
	}
	public String getStudent_lat() {
		return student_lat;
	}
	public void setStudent_lat(String student_lat) {
		this.student_lat = student_lat;
	}
	public String getStudent_lng() {
		return student_lng;
	}
	public void setStudent_lng(String student_lng) {
		this.student_lng = student_lng;
	}
	public String getFather_name() {
		return father_name;
	}
	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}
	public String getGrand_name() {
		return grand_name;
	}
	public void setGrand_name(String grand_name) {
		this.grand_name = grand_name;
	}
	public String getFamily_name() {
		return family_name;
	}
	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStudent_class() {
		return student_class;
	}
	public void setStudent_class(String student_class) {
		this.student_class = student_class;
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
	public String getBlood_type() {
		return blood_type;
	}
	public void setBlood_type(String blood_type) {
		this.blood_type = blood_type;
	}
	public Integer getS_route_id() {
		return s_route_id;
	}
	public void setS_route_id(Integer s_route_id) {
		this.s_route_id = s_route_id;
	}
	public Integer getS_driver() {
		return s_driver;
	}
	public void setS_driver(Integer s_driver) {
		this.s_driver = s_driver;
	}
	public Integer getP_status_id() {
		return p_status_id;
	}
	public void setP_status_id(Integer p_status_id) {
		this.p_status_id = p_status_id;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public Integer getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}
	public String getS_fname() {
		return s_fname;
	}
	public void setS_fname(String s_fname) {
		this.s_fname = s_fname;
	}
	public String getS_lname() {
		return s_lname;
	}
	public void setS_lname(String s_lname) {
		this.s_lname = s_lname;
	}
	public String getS_email() {
		return s_email;
	}
	public void setS_email(String s_email) {
		this.s_email = s_email;
	}
	public String getS_pass() {
		return s_pass;
	}
	public void setS_pass(String s_pass) {
		this.s_pass = s_pass;
	}
	public String getS_address() {
		return s_address;
	}
	public void setS_address(String s_address) {
		this.s_address = s_address;
	}
	public String getS_image_path() {
		return s_image_path;
	}
	public void setS_image_path(String s_image_path) {
		this.s_image_path = s_image_path;
	}
	public Integer getS_city() {
		return s_city;
	}
	public void setS_city(Integer s_city) {
		this.s_city = s_city;
	}
	public Integer getS_state() {
		return s_state;
	}
	public void setS_state(Integer s_state) {
		this.s_state = s_state;
	}
	public Integer getS_country() {
		return s_country;
	}
	public void setS_country(Integer s_country) {
		this.s_country = s_country;
	}
	public String getS_contact() {
		return s_contact;
	}
	public void setS_contact(String s_contact) {
		this.s_contact = s_contact;
	}
	public String getS_zip() {
		return s_zip;
	}
	public void setS_zip(String s_zip) {
		this.s_zip = s_zip;
	}
	public Integer getS_parent_id() {
		return s_parent_id;
	}
	public void setS_parent_id(Integer s_parent_id) {
		this.s_parent_id = s_parent_id;
	}
	public Integer getS_school_id() {
		return s_school_id;
	}
	public void setS_school_id(Integer s_school_id) {
		this.s_school_id = s_school_id;
	}
	public Integer getP_1() {
		return p_1;
	}
	public void setP_1(Integer p_1) {
		this.p_1 = p_1;
	}
	public Integer getP_2() {
		return p_2;
	}
	public void setP_2(Integer p_2) {
		this.p_2 = p_2;
	}
	public Integer getP_3() {
		return p_3;
	}
	public void setP_3(Integer p_3) {
		this.p_3 = p_3;
	}
	public String getR_1() {
		return r_1;
	}
	public void setR_1(String r_1) {
		this.r_1 = r_1;
	}
	public String getR_2() {
		return r_2;
	}
	public void setR_2(String r_2) {
		this.r_2 = r_2;
	}
	public String getR_3() {
		return r_3;
	}
	public void setR_3(String r_3) {
		this.r_3 = r_3;
	}
	public String getPp_1() {
		return pp_1;
	}
	public void setPp_1(String pp_1) {
		this.pp_1 = pp_1;
	}
	public String getPp_2() {
		return pp_2;
	}
	public void setPp_2(String pp_2) {
		this.pp_2 = pp_2;
	}
	public String getPp_3() {
		return pp_3;
	}
	public void setPp_3(String pp_3) {
		this.pp_3 = pp_3;
	}
	
}
