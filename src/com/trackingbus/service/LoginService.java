package com.trackingbus.service;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.SchoolModel;

import java.util.List;
/**
 * @author Rudiment Webtech Solutions
 * */
public interface LoginService {
	
	public LoginModel login(String user_email,String user_pass);
	public LoginModel checkUsername(String user_name);
	public LoginModel checkSchoolName(String email);
	public LoginModel checkMobile(String mobile);

	public void editParentById(int parent_id, String device_token);
	public void updateParentDeviceId(int parent_id,String device_id);
	
	public void updateDriverDeviceToken(int driver_id,String device_token);
	public void updateDriverDeviceId(int driver_id,String device_id);
	public void updateDriverLoggedIn(int driver_id,int logged_status);
	

}
