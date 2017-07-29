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
@Table(name="tbl_is_typing")
public class TypingModel {
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="type_id")
	private Integer type_id;
	
	@Column(name="typing_user")
	private Integer typing_user;
	
	@Column(name="typing_reciever")
	private Integer typing_reciever;
	
	@Column(name="status")
	private Integer status;

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public Integer getTyping_user() {
		return typing_user;
	}

	public void setTyping_user(Integer typing_user) {
		this.typing_user = typing_user;
	}

	public Integer getTyping_reciever() {
		return typing_reciever;
	}

	public void setTyping_reciever(Integer typing_reciever) {
		this.typing_reciever = typing_reciever;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
