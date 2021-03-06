package com.trackingbus.model;
import java.io.Serializable;

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
@Table(name="tbl_school")
public class SchoolModel {
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="s_id")
	private Integer s_id;
	
	@Column(name="school_name")
	private String school_name;
	
	@Column(name="principal_name")
	private String principal_name;
	
	@Column(name="principal_contact")
	private String principal_contact;
	
	@Column(name="email")
	private String email;
	
	@Column(name="contact_number")
	private String contact_number;
	
	@Column(name="country")
	private Integer country;
	
	@Column(name="state")
	private Integer state;
	
	@Column(name="city")
	private Integer city;
	
	@Column(name="zip_code")
	private Integer zip_code;
	
	@Column(name="total_bus")
	private Integer total_bus;
	
	@Column(name="total_students")
	private Integer total_students;
	
	@Column(name="school_logo")
	private String school_logo;
	
	@Column(name="school_address")
	private String school_address;
	
	@Column(name="school_lat")
	private String school_lat;
	
	@Column(name="school_lng")
	private String school_lng;

	@Column(name="school_address_field")
	private String school_address_field;
	

	public String getSchool_address_field() {
		return school_address_field;
	}

	public void setSchool_address_field(String school_address_field) {
		this.school_address_field = school_address_field;
	}

	public String getSchool_address() {
		return school_address;
	}

	public void setSchool_address(String school_address) {
		this.school_address = school_address;
	}

	public String getSchool_lat() {
		return school_lat;
	}

	public void setSchool_lat(String school_lat) {
		this.school_lat = school_lat;
	}

	public String getSchool_lng() {
		return school_lng;
	}

	public void setSchool_lng(String school_lng) {
		this.school_lng = school_lng;
	}

	public String getSchool_logo() {
		return school_logo;
	}

	public void setSchool_logo(String school_logo) {
		this.school_logo = school_logo;
	}

	public Integer getS_id() {
		return s_id;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}

	public String getPrincipal_name() {
		return principal_name;
	}

	public void setPrincipal_name(String principal_name) {
		this.principal_name = principal_name;
	}

	public String getPrincipal_contact() {
		return principal_contact;
	}

	public void setPrincipal_contact(String principal_contact) {
		this.principal_contact = principal_contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getZip_code() {
		return zip_code;
	}

	public void setZip_code(Integer zip_code) {
		this.zip_code = zip_code;
	}

	public Integer getTotal_bus() {
		return total_bus;
	}

	public void setTotal_bus(Integer total_bus) {
		this.total_bus = total_bus;
	}

	public Integer getTotal_students() {
		return total_students;
	}

	public void setTotal_students(Integer total_students) {
		this.total_students = total_students;
	}
	
	
	
}
