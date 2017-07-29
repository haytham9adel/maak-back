package com.trackingbus.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_contact_category")
public class CategoryContactModel {

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="c_cat_id")
	private Integer c_cat_id;
	
	 
	@Column(name="category_en")
	private String category_en;
	
	@Column(name="category_ar")
	private String category_ar;

	public Integer getC_cat_id() {
		return c_cat_id;
	}

	public void setC_cat_id(Integer c_cat_id) {
		this.c_cat_id = c_cat_id;
	}

	public String getCategory_en() {
		return category_en;
	}

	public void setCategory_en(String category_en) {
		this.category_en = category_en;
	}

	public String getCategory_ar() {
		return category_ar;
	}

	public void setCategory_ar(String category_ar) {
		this.category_ar = category_ar;
	}
	
	
	
	
}
