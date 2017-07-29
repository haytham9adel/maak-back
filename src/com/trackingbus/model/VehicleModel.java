package com.trackingbus.model;
import java.io.Serializable;
import java.util.List;

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
@Table(name="tbl_vechile")
public class VehicleModel {
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="vechile_id")
	private Integer vechile_id;
	
	@Column(name="manufacture")
	private String manufacture;
	
	
	@Column(name="vehile_name")
	private String vehile_name;
	
	
	@Column(name="model")
	private String model;
	
	@Column(name="year")
	private String year;
	
	@Column(name="color")
	private String color;
	
	@Column(name="configurtion")
	private String configurtion;
	
	@Column(name="engine")
	private String engine;
	
	@Column(name="school_id")
	private Integer school_id;

	@Column(name="bus_number")
	private String bus_number;
	
	@Column(name="insurance_end_date")
	private String insurance_end_date;

	@Column(name="insurance_card_copy")
	private String insurance_card_copy;
	
	@Column(name="insurance_document_name")
	private String insurance_document_name;

	@Column(name="insurance_document_expiry")
	private String insurance_document_expiry;
	
	@Column(name="remind_day")
	private String remind_day;
	
	@Column(name="driver_id")
	private Integer driver_id;
	
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

	public String getInsurance_document_name() {
		return insurance_document_name;
	}

	public void setInsurance_document_name(String insurance_document_name) {
		this.insurance_document_name = insurance_document_name;
	}

	public String getInsurance_end_date() {
		return insurance_end_date;
	}

	public void setInsurance_end_date(String insurance_end_date) {
		this.insurance_end_date = insurance_end_date;
	}

	public String getInsurance_card_copy() {
		return insurance_card_copy;
	}

	public void setInsurance_card_copy(String insurance_card_copy) {
		this.insurance_card_copy = insurance_card_copy;
	}

	public String getBus_number() {
		return bus_number;
	}

	public void setBus_number(String string) {
		this.bus_number = string;
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

	public Integer getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(Integer driver_id) {
		this.driver_id = driver_id;
	}
	
	
	
}
