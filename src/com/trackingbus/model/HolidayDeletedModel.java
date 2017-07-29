package com.trackingbus.model;
 
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_deleted_holiday")
public class HolidayDeletedModel {
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="del_id")
	private Integer del_id;
	
	@Column(name="school_id")
	private Integer school_id;
	
	@Column(name="holiday_id")
	private Integer holiday_id;

	public Integer getDel_id() {
		return del_id;
	}

	public void setDel_id(Integer del_id) {
		this.del_id = del_id;
	}

	public Integer getSchool_id() {
		return school_id;
	}

	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}

	public Integer getHoliday_id() {
		return holiday_id;
	}

	public void setHoliday_id(Integer holiday_id) {
		this.holiday_id = holiday_id;
	}
}
