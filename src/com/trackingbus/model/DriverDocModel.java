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
@Table(name="driver_doc")
public class DriverDocModel {
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="v_doc_id")
	private Integer v_doc_id;
	
	@Column(name="insurance_document_name")
	private String insurance_document_name;
	
	@Column(name="insurance_document_expiry")
	private String insurance_document_expiry;
	
	@Column(name="remind_day")
	private String remind_day;
	
	@Column(name="school_id")
	private Integer school_id;
	
	@Column(name="v_id")
	private Integer v_id;
	
	@Column(name="insurance_card_copy")
	private String insurance_card_copy;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="noti_type")
	private Integer noti_type;

	@Column(name="insurance_end_date")
	private String insurance_end_date;
	
	
	public String getInsurance_end_date() {
		return insurance_end_date;
	}

	public void setInsurance_end_date(String insurance_end_date) {
		this.insurance_end_date = insurance_end_date;
	}

	public Integer getNoti_type() {
		return noti_type;
	}

	public void setNoti_type(Integer noti_type) {
		this.noti_type = noti_type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getV_doc_id() {
		return v_doc_id;
	}

	public void setV_doc_id(Integer v_doc_id) {
		this.v_doc_id = v_doc_id;
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

	public Integer getSchool_id() {
		return school_id;
	}

	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}

	public Integer getV_id() {
		return v_id;
	}

	public void setV_id(Integer v_id) {
		this.v_id = v_id;
	}

	public String getInsurance_card_copy() {
		return insurance_card_copy;
	}

	public void setInsurance_card_copy(String insurance_card_copy) {
		this.insurance_card_copy = insurance_card_copy;
	}
	
	
	
}
