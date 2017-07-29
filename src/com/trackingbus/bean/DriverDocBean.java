package com.trackingbus.bean;

public class DriverDocBean {
	private Integer v_doc_id;
	private String insurance_document_name;
	private String insurance_document_expiry;
	private String remind_day;
	private Integer school_id;
	private Integer v_id;
	private String insurance_card_copy;
	private Integer status;
	private Integer noti_type;
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
	public String getInsurance_card_copy() {
		return insurance_card_copy;
	}
	public void setInsurance_card_copy(String insurance_card_copy) {
		this.insurance_card_copy = insurance_card_copy;
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
}
