package com.trackingbus.bean;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class VechileBean {
	private Integer vechile_id;
	private String vehile_name;
	private String manufacture;
	private String model;
	private String year;
	private String color;
	private String configurtion;
	private String engine;
	private Integer school_id;
	private String bus_number;
	private String school_name;
	private String insurance_end_date;
	private List<MultipartFile> insurance_card_copy;
	private List<String> insurance_document_name; 
	private List<String> insurance_document_expiry; 
	private List<String> remind_day; 
	private List<String> v_doc_id; 
	private List<String> insurance_end_date_e; 
	private Integer driver_id;
	
	
	public List<String> getInsurance_end_date_e() {
		return insurance_end_date_e;
	}
	public void setInsurance_end_date_e(List<String> insurance_end_date_e) {
		this.insurance_end_date_e = insurance_end_date_e;
	}
	public List<String> getV_doc_id() {
		return v_doc_id;
	}
	public void setV_doc_id(List<String> v_doc_id) {
		this.v_doc_id = v_doc_id;
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
	public List<String> getInsurance_document_name() {
		return insurance_document_name;
	}
	public void setInsurance_document_name(List<String> insurance_document_name) {
		this.insurance_document_name = insurance_document_name;
	}
	public String getInsurance_end_date() {
		return insurance_end_date;
	}
	public void setInsurance_end_date(String insurance_end_date) {
		this.insurance_end_date = insurance_end_date;
	}
/*	public MultipartFile getInsurance_card_copy() {
		return insurance_card_copy;
	}
	public void setInsurance_card_copy(MultipartFile insurance_card_copy) {
		this.insurance_card_copy = insurance_card_copy;
	}*/
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getBus_number() {
		return bus_number;
	}
	public void setBus_number(String bus_number) {
		this.bus_number = bus_number;
	}
	public Integer getSchool_id() {
		return school_id;
	}
	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}
	public String getVehile_name() {
		return vehile_name;
	}
	public void setVehile_name(String vehile_name) {
		this.vehile_name = vehile_name;
	}
	public Integer getVechile_id() {
		return vechile_id;
	}
	public void setVechile_id(Integer vechile_id) {
		this.vechile_id = vechile_id;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getConfigurtion() {
		return configurtion;
	}
	public void setConfigurtion(String configurtion) {
		this.configurtion = configurtion;
	}
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	public List<MultipartFile> getInsurance_card_copy() {
		return insurance_card_copy;
	}
	public void setInsurance_card_copy(List<MultipartFile> insurance_card_copy) {
		this.insurance_card_copy = insurance_card_copy;
	}
	public Integer getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(Integer driver_id) {
		this.driver_id = driver_id;
	}
	

}
