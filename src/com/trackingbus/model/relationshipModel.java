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
@Table(name="tbl_relationship")
public class relationshipModel {

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="r_id")
	private Integer r_id;
	
	@Column(name="r_title")
	private String r_title;

	public Integer getR_id() {
		return r_id;
	}

	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}

	public String getR_title() {
		return r_title;
	}

	public void setR_title(String r_title) {
		this.r_title = r_title;
	}
	
	
}
