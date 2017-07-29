package com.trackingbus.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.trackingbus.dao.LoginDao;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.SchoolModel;

/**
 * @author Rudiment Webtech Solutions
 * */
@Service("loginservice")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao logindao;

	@Override
	public LoginModel login(String userName, String password) {
		
		LoginModel loginmodel = logindao.login(userName, password);
		return loginmodel;
	
	
	}

	@Override
	public LoginModel checkSchoolName(String email) {
		/*LoginModel loginmodel = logindao.checkSchoolName(email);*/
		return null;
	}

	@Override
	public LoginModel checkUsername(String user_name) {
		LoginModel loginmodel = logindao.checkUsername(user_name);
		return loginmodel;
	}

	@Override
	public void editParentById(int parent_id, String device_token) {
		// TODO Auto-generated method stub
			logindao.editParentById(parent_id, device_token);
		
	}
	@Override
	public void updateParentDeviceId(int parent_id,String device_id) {
		// TODO Auto-generated method stub
			logindao.updateParentDeviceId(parent_id, device_id);
		
	}
	
	 
	@Override
	public void updateDriverDeviceToken(int driver_id, String device_token) {
		// TODO Auto-generated method stub
			logindao.updateDriverDeviceToken(driver_id, device_token);
		
	}
	@Override
	public void  updateDriverDeviceId(int driver_id,String device_id) {
		// TODO Auto-generated method stub
			logindao.updateDriverDeviceId(driver_id, device_id);
		
	}
	 
	@Override
	public void updateDriverLoggedIn(int driver_id,int logged_status) {
		// TODO Auto-generated method stub
			logindao.updateDriverLoggedIn(driver_id,logged_status);
		
	}
	
	
	@Override
	public LoginModel checkMobile(String mobile) {
		// TODO Auto-generated method stub
		LoginModel loginmodel = logindao.checkMobile(mobile);
		return loginmodel;
	}
	
	
	
	
	

}
