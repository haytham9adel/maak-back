package com.trackingbus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author Rudiment Webtech Solutions
 *
 */
@Entity
@Table(name="tbl_student")
public class StudentModel {
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="student_id")
	private Integer student_id;
	
	@Column(name="s_fname")
	private String s_fname;
	
	@Column(name="s_lname")
	private String s_lname;
	
	@Column(name="s_email")
	private String s_email;
	
	@Column(name="s_pass")
	private String s_pass;
	
	@Column(name="s_address")
	private String s_address;
	
	@Column(name="s_city")
	private Integer s_city;
	
	@Column(name="p_status_id")
	private Integer p_status_id;
	
	@Column(name="s_driver")
	private Integer s_driver;
	
	@Column(name="s_route_id")
	private Integer s_route_id;
	
	@Column(name="father_name")
	private String father_name;
	
	@Column(name="grand_name")
	private String grand_name;

	@Column(name="family_name")
	private String family_name;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="student_class")
	private String student_class;

	@Column(name="dob")
	private String dob;

	@Column(name="nationality")
	private String nationality;
	
	@Column(name="blood_type")
	private String blood_type;
	
	@Column(name="blink_status")
	private Integer blink_status;

	@Column(name="p_1")
	private Integer p_1;
	
	@Column(name="p_2")
	private Integer p_2;
	
	@Column(name="p_3")
	private Integer p_3;
	
	@Column(name="r_1")
	private String r_1;
	
	@Column(name="r_2")
	private String  r_2;
	
	@Column(name="r_3")
	private String r_3;
	
	
	
	

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

	public Integer getBlink_status() {
		return blink_status;
	}

	public void setBlink_status(Integer blink_status) {
		this.blink_status = blink_status;
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

	@Column(name="s_country")
	private Integer s_country;
	
	@Column(name="s_contact")
	private String s_contact;
	
	@Column(name="s_zip")
	private String s_zip;
	
	@Column(name="s_image_path")
	private String s_image_path;
	
	@Column(name="s_parent_id")
	private Integer s_parent_id;
	
	@Column(name="s_school_id")
	private Integer s_school_id;

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

	public Integer getS_city() {
		return s_city;
	}

	public void setS_city(Integer s_city) {
		this.s_city = s_city;
	}

	/*public Integer getS_state() {
		return s_state;
	}

	public void setS_state(Integer s_state) {
		this.s_state = s_state;
	}*/

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

	public String getS_image_path() {
		return s_image_path;
	}

	public void setS_image_path(String s_image_path) {
		this.s_image_path = s_image_path;
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
}
