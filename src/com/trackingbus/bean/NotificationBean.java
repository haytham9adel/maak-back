package com.trackingbus.bean;

public class NotificationBean {

  private Integer noti_id;
  private Integer route_id; 
  private Integer student_id;
  private String noti_type;
  private String method;
  private Integer parent_id;
  private String date;
  private String noti_desc;
  private String msg;
  private String parent_ids;
  private Integer ins_status;
  
public Integer getIns_status() {
	return ins_status;
}
public void setIns_status(Integer ins_status) {
	this.ins_status = ins_status;
}
public String getParent_ids() {
	return parent_ids;
}
public void setParent_ids(String parent_ids) {
	this.parent_ids = parent_ids;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public Integer getNoti_id() {
	return noti_id;
}
public void setNoti_id(Integer noti_id) {
	this.noti_id = noti_id;
}
public Integer getRoute_id() {
	return route_id;
}
public void setRoute_id(Integer route_id) {
	this.route_id = route_id;
}
public Integer getStudent_id() {
	return student_id;
}
public void setStudent_id(Integer student_id) {
	this.student_id = student_id;
}
public String getNoti_type() {
	return noti_type;
}
public void setNoti_type(String noti_type) {
	this.noti_type = noti_type;
}
public String getMethod() {
	return method;
}
public void setMethod(String method) {
	this.method = method;
}
public Integer getParent_id() {
	return parent_id;
}
public void setParent_id(Integer parent_id) {
	this.parent_id = parent_id;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getNoti_desc() {
	return noti_desc;
}
public void setNoti_desc(String noti_desc) {
	this.noti_desc = noti_desc;
}
}
