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
@Table(name="tbl_features")
public class FeaturesModel {

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="features_id")
	private Integer features_id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name="image_path")
	private String image_path;
	
	@Column(name="category_type")
	private String category_type;
	
	@Column(name="title_ar")
	private String title_ar;
	
	@Column(name="content_ar")
	private String content_ar;
	
	@Column(name="image_path_ar")
	private String image_path_ar;
	
	public String getCategory_type() {
		return category_type;
	}

	public void setCategory_type(String category_type) {
		this.category_type = category_type;
	}

	public Integer getFeatures_id() {
		return features_id;
	}

	public void setFeatures_id(Integer features_id) {
		this.features_id = features_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getTitle_ar() {
		return title_ar;
	}

	public void setTitle_ar(String title_ar) {
		this.title_ar = title_ar;
	}

	public String getContent_ar() {
		return content_ar;
	}

	public void setContent_ar(String content_ar) {
		this.content_ar = content_ar;
	}

	public String getImage_path_ar() {
		return image_path_ar;
	}

	public void setImage_path_ar(String image_path_ar) {
		this.image_path_ar = image_path_ar;
	}
	
	
	
	
}
