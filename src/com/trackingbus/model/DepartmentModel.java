package com.trackingbus.model;
import java.io.Serializable;

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
@Table(name="tbl_staff_department")

public class DepartmentModel {


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="depart_id")
	private Integer depart_id;
	
	@Column(name="depart_name")
	private String depart_name;

	public Integer getDepart_id() {
		return depart_id;
	}

	public void setDepart_id(Integer depart_id) {
		this.depart_id = depart_id;
	}

	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}
	
}
