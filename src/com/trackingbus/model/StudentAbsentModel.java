package com.trackingbus.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_student_absent")
public class StudentAbsentModel {

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="a_id")
	private Integer a_id;
	
	@Column(name="student_id")
	private Integer student_id;
	
	@Column(name="absent_date")
	private String absent_date;
	
	@Column(name="reason")
	private String reason;
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getA_id() {
		return a_id;
	}

	public void setA_id(Integer a_id) {
		this.a_id = a_id;
	}

	public Integer getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}

	public String getAbsent_date() {
		return absent_date;
	}

	public void setAbsent_date(String absent_date) {
		this.absent_date = absent_date;
	}
 

	 
	
	
	
	
	
}
