package com.trackingbus.bean;

public class SchoolMessageBean {
	private Integer msg_id;
	private Integer school_id;
	private Integer reciever_id;
	private Integer sender_id;
	private String msg;
	private Integer status;
	private String time;
	private Integer sender;
	private String name;
	private String u_check;
	
	public String getU_check() {
		return u_check;
	}
	public void setU_check(String u_check) {
		this.u_check = u_check;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
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
	public void set(Integer school_id) {
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
