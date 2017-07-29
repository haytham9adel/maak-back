package com.trackingbus.ws;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.trackingbus.dao.LoginDao;
import com.trackingbus.model.LoginModel;

public class WebDao implements LoginDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public LoginModel login(String user_email, String user_pass) {
		LoginModel a = null;
		System.out.println(user_email+"--"+user_pass);
		LoginModel m = (LoginModel)	sessionFactory.getCurrentSession().createQuery("from LoginModel where user_email='"+user_email+"'").uniqueResult();
		System.out.println("Here dao"+m.getSchool_id());
		return m;
	}

	@Override
	public LoginModel checkSchoolName(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginModel checkUsername(String user_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editParentById(int parent_id, String device_token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LoginModel checkMobile(String mobile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateDriverDeviceToken(int driver_id, String device_token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDriverLoggedIn(int driver_id, int logged_status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateParentDeviceId(int parent_id, String device_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDriverDeviceId(int driver_id, String device_id) {
		// TODO Auto-generated method stub
		
	}

}
