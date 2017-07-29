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
@Table(name="tbl_page_content")
public class PageContentModel {
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="p_id")
	private Integer p_id;
	
	@Column(name="p_name")
	private String p_name;
	
	@Column(name="heading1")
	private String heading1;
	
	@Column(name="description1")
	private String description1;
	
	@Column(name="heading2")
	private String heading2;
	
	@Column(name="description2")
	private String description2;
	
	@Column(name="heading3")
	private String heading3;
	
	@Column(name="description3")
	private String description3;
	
	
	@Column(name="heading4")
	private String heading4;
	
	@Column(name="heading5")
	private String heading5;
	
	@Column(name="description4")
	private String description4;
	
	@Column(name="description5")
	private String description5;

	public String getHeading4() {
		return heading4;
	}

	public void setHeading4(String heading4) {
		this.heading4 = heading4;
	}

	public String getHeading5() {
		return heading5;
	}

	public void setHeading5(String heading5) {
		this.heading5 = heading5;
	}

	public String getDescription4() {
		return description4;
	}

	public void setDescription4(String description4) {
		this.description4 = description4;
	}

	public String getDescription5() {
		return description5;
	}

	public void setDescription5(String description5) {
		this.description5 = description5;
	}

	public Integer getP_id() {
		return p_id;
	}

	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getHeading1() {
		return heading1;
	}

	public void setHeading1(String heading1) {
		this.heading1 = heading1;
	}

	public String getDescription1() {
		return description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public String getHeading2() {
		return heading2;
	}

	public void setHeading2(String heading2) {
		this.heading2 = heading2;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public String getHeading3() {
		return heading3;
	}

	public void setHeading3(String heading3) {
		this.heading3 = heading3;
	}

	public String getDescription3() {
		return description3;
	}

	public void setDescription3(String description3) {
		this.description3 = description3;
	}
	
	
	
}
