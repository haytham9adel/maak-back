package com.trackingbus.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_home_slider_images")
public class SliderModel {

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="slider_id")
	private Integer slider_id;
	
	@Column(name="slider_type")
	private Integer slider_type;
	
	@Column(name="slider_image")
	private String slider_image;
	
	@Column(name="slider_image_ar")
	private String slider_image_ar;
	
	public Integer getSlider_id() {
		return slider_id;
	}

	public void setSlider_id(Integer slider_id) {
		this.slider_id = slider_id;
	}

	public Integer getSlider_type() {
		return slider_type;
	}

	public void setSlider_type(Integer slider_type) {
		this.slider_type = slider_type;
	}

	public String getSlider_image() {
		return slider_image;
	}

	public void setSlider_image(String slider_image) {
		this.slider_image = slider_image;
	}

	public String getSlider_image_ar() {
		return slider_image_ar;
	}

	public void setSlider_image_ar(String slider_image_ar) {
		this.slider_image_ar = slider_image_ar;
	}
	
	
	
}
