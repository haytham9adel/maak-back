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
@Table(name="tbl_pages")
public class PageModel {

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="page_id")
	private Integer page_id;
	
	@Column(name="page_name")
	private String page_name;
	
	@Column(name="page_title")
	private String page_title;
	
	@Column(name="page_desc")
	private String page_desc;
	
	@Column(name="page_name_ar")
	private String page_name_ar;
	
	@Column(name="page_title_ar")
	private String page_title_ar;
	
	@Column(name="page_desc_ar")
	private String page_desc_ar;
	
	
	@Column(name="p_type")
	private Integer p_type;

	public Integer getPage_id() {
		return page_id;
	}

	public void setPage_id(Integer page_id) {
		this.page_id = page_id;
	}

	public String getPage_name() {
		return page_name;
	}

	public void setPage_name(String page_name) {
		this.page_name = page_name;
	}

	public String getPage_title() {
		return page_title;
	}

	public void setPage_title(String page_title) {
		this.page_title = page_title;
	}

	public String getPage_desc() {
		return page_desc;
	}

	public void setPage_desc(String page_desc) {
		this.page_desc = page_desc;
	}

	public Integer getP_type() {
		return p_type;
	}

	public void setP_type(Integer p_type) {
		this.p_type = p_type;
	}

	public String getPage_name_ar() {
		return page_name_ar;
	}

	public void setPage_name_ar(String page_name_ar) {
		this.page_name_ar = page_name_ar;
	}

	public String getPage_title_ar() {
		return page_title_ar;
	}

	public void setPage_title_ar(String page_title_ar) {
		this.page_title_ar = page_title_ar;
	}

	public String getPage_desc_ar() {
		return page_desc_ar;
	}

	public void setPage_desc_ar(String page_desc_ar) {
		this.page_desc_ar = page_desc_ar;
	}
	
	
}
