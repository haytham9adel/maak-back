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
@Table(name="tbl_school_msg")

public class SchoolMessage {
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="msg_id")
	private Integer msg_id;
	
	@Column(name="school_id")
	private Integer school_id;

	@Column(name="reciever_id")
	private Integer reciever_id;

	@Column(name="sender_id")
	private Integer sender_id;
	
	@Column(name="msg")
	private String msg;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="time")
	private String time;
	
	@Column(name="sender")
	private Integer sender;
	
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(Integer msg_id) {
		this.msg_id = msg_id;
	}

	public Integer getSchool_id() {
		return school_id;
	}

	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}

	public Integer getReciever_id() {
		return reciever_id;
	}

	public void setReciever_id(Integer reciever_id) {
		this.reciever_id = reciever_id;
	}

	public Integer getSender_id() {
		return sender_id;
	}

	public void setSender_id(Integer sender_id) {
		this.sender_id = sender_id;
	}

	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	

	public Integer getSender() {
		return sender;
	}

	public void setSender(Integer sender) {
		this.sender = sender;
	}
	
	
	
	
	
	
}
