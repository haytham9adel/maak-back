package com.trackingbus.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author designer-5
 *
 */
@Entity
@Table(name="tbl_contact")
public class ContactDetailModel {

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="contact_id")
	private Integer contact_id;
	
	@Column(name="category")
	private String category;
	
	@Column(name="name")
	private String name;
	
	@Column(name="phone_number")
	private String phone_number;
	
	@Column(name="email")
	private String email;
	
	@Column(name="message")
	private String message;

	public Integer getContact_id() {
		return contact_id;
	}

	public void setContact_id(Integer contact_id) {
		this.contact_id = contact_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
	
	
}
