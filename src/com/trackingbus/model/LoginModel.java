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
@Table(name="tbl_users")
public class LoginModel {
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private Integer user_id;
	
	@Column(name="user_email")
	private String user_email;
	
	@Column(name="contact_number")
	private String contact_number;
	
	
	@Column(name="first_name")
	private String first_name;
	
	
	
	@Column(name="last_name")
	private String last_name;
	
	
	
	@Column(name="p_status")
	private Integer p_status;
	
	@Column(name="device_token")
	private String device_token;
	
	@Column(name="middle_name")
	private String middle_name;
	
	@Column(name="family_name")
	private String family_name;
	
	@Column(name="mobile_number")
	private String mobile_number;
	
	@Column(name="lang")
	private Integer lang;
	
	@Column(name="noti_on")
	private Integer noti_on;
	
	@Column(name="chat_on")
	private Integer chat_on;
	
	@Column(name="checked_in_on")
	private Integer checked_in_on;
	
	@Column(name="checked_out_on")
	private Integer checked_out_on;
	
	@Column(name="speed_on")
	private Integer speed_on;
	
	@Column(name="max_speed")
	private Integer max_speed;
	
	@Column(name="wrong_route_on")
	private Integer wrong_route_on;
	
	@Column(name="sms_checked_in_on")
	private Integer sms_checked_in_on;
	
	@Column(name="sms_checked_out_on")
	private Integer sms_checked_out_on;
	
	@Column(name="sms_speed_on")
	private Integer sms_speed_on;
	
	@Column(name="sms_max_speed")
	private Integer sms_max_speed;
	
	@Column(name="sms_wrong_route_on")
	private Integer sms_wrong_route_on;
	
	@Column(name="instant_message")
	private Integer instant_message;
	
	
	@Column(name="morning_before")
	private Integer morning_before;
	
	@Column(name="sms_evening_before")
	private Integer sms_evening_before;
	
	
	@Column(name="evening_before")
	private Integer evening_before;
	
	@Column(name="sms_instant_message")
	private Integer sms_instant_message;
	
	@Column(name="sms_morning_before")
	private Integer sms_morning_before;
	
	@Column(name="device_id")
	private String device_id;

	@Column(name="sound_setting")
	private Integer sound_setting;
	
	@Column(name="chat_sound")
	private Integer chat_sound;
	
	public Integer getChat_sound() {
		return chat_sound;
	}

	public void setChat_sound(Integer chat_sound) {
		this.chat_sound = chat_sound;
	}

	public Integer getSound_setting() {
		return sound_setting;
	}

	public void setSound_setting(Integer sound_setting) {
		this.sound_setting = sound_setting;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public Integer getInstant_message() {
		return instant_message;
	}

	public void setInstant_message(Integer instant_message) {
		this.instant_message = instant_message;
	}

	public Integer getMorning_before() {
		return morning_before;
	}

	public void setMorning_before(Integer morning_before) {
		this.morning_before = morning_before;
	}

	public Integer getSms_evening_before() {
		return sms_evening_before;
	}

	public void setSms_evening_before(Integer sms_evening_before) {
		this.sms_evening_before = sms_evening_before;
	}

	public Integer getEvening_before() {
		return evening_before;
	}

	public void setEvening_before(Integer evening_before) {
		this.evening_before = evening_before;
	}

	public Integer getSms_instant_message() {
		return sms_instant_message;
	}

	public void setSms_instant_message(Integer sms_instant_message) {
		this.sms_instant_message = sms_instant_message;
	}

	public Integer getSms_morning_before() {
		return sms_morning_before;
	}

	public void setSms_morning_before(Integer sms_morning_before) {
		this.sms_morning_before = sms_morning_before;
	}

	public Integer getSms_checked_in_on() {
		return sms_checked_in_on;
	}

	public void setSms_checked_in_on(Integer sms_checked_in_on) {
		this.sms_checked_in_on = sms_checked_in_on;
	}

	public Integer getSms_checked_out_on() {
		return sms_checked_out_on;
	}

	public void setSms_checked_out_on(Integer sms_checked_out_on) {
		this.sms_checked_out_on = sms_checked_out_on;
	}

	public Integer getSms_speed_on() {
		return sms_speed_on;
	}

	public void setSms_speed_on(Integer sms_speed_on) {
		this.sms_speed_on = sms_speed_on;
	}

	public Integer getSms_max_speed() {
		return sms_max_speed;
	}

	public void setSms_max_speed(Integer sms_max_speed) {
		this.sms_max_speed = sms_max_speed;
	}

	public Integer getSms_wrong_route_on() {
		return sms_wrong_route_on;
	}

	public void setSms_wrong_route_on(Integer sms_wrong_route_on) {
		this.sms_wrong_route_on = sms_wrong_route_on;
	}

	public Integer getLang() {
		return lang;
	}

	public void setLang(Integer lang) {
		this.lang = lang;
	}

	public Integer getNoti_on() {
		return noti_on;
	}

	public void setNoti_on(Integer noti_on) {
		this.noti_on = noti_on;
	}

	public Integer getChat_on() {
		return chat_on;
	}

	public void setChat_on(Integer chat_on) {
		this.chat_on = chat_on;
	}

	public Integer getChecked_in_on() {
		return checked_in_on;
	}

	public void setChecked_in_on(Integer checked_in_on) {
		this.checked_in_on = checked_in_on;
	}

	public Integer getChecked_out_on() {
		return checked_out_on;
	}

	public void setChecked_out_on(Integer checked_out_on) {
		this.checked_out_on = checked_out_on;
	}

	public Integer getSpeed_on() {
		return speed_on;
	}

	public void setSpeed_on(Integer speed_on) {
		this.speed_on = speed_on;
	}

	public Integer getMax_speed() {
		return max_speed;
	}

	public void setMax_speed(Integer max_speed) {
		this.max_speed = max_speed;
	}

	public Integer getWrong_route_on() {
		return wrong_route_on;
	}

	public void setWrong_route_on(Integer wrong_route_on) {
		this.wrong_route_on = wrong_route_on;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public String getDevice_token() {
		return device_token;
	}

	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}

	public Integer getP_status() {
		return p_status;
	}

	public void setP_status(Integer p_status) {
		this.p_status = p_status;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	@Column(name="user_pass")
	private String user_pass;
	
	@Column(name="user_name")
	private String user_name;
	
	@Column(name="user_role")
	private int user_role;
	
	@Column(name="permission")
	private int permission;
	
	@Column(name="main_school_admin")
	private int main_school_admin;
	
	
	public int getMain_school_admin() {
		return main_school_admin;
	}

	public void setMain_school_admin(int main_school_admin) {
		this.main_school_admin = main_school_admin;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public int getSchool_id() {
		return school_id;
	}

	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}

	@Column(name="school_id")
	private int school_id;

	public int getUser_role() {
		return user_role;
	}

	public void setUser_role(int user_role) {
		this.user_role = user_role;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_pass() {
		return user_pass;
	}

	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	
}
