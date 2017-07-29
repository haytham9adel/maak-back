package com.trackingbus.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trackingbus.model.LoginModel;
import com.trackingbus.model.StudentModel;

/**
 * @author Rudiment Webtech Solutions
 *
 */
@Repository("logindao")
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@SuppressWarnings("unchecked")
	public LoginModel login(String user_email, String user_pass) {
		LoginModel a = null;
		LoginModel m = (LoginModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from LoginModel where user_name='" + user_email + "'")
				.uniqueResult();
		return m;
	}

	@Override
	public LoginModel checkSchoolName(String email) {
		// return (StudentModel)
		// sessionFactory.getCurrentSession().createQuery("from LoginModel where user_email='"+email+"'").uniqueResult();;
		return null;
	}

	@Override
	public LoginModel checkUsername(String user_name) {
		// TODO Auto-generated method stub
		LoginModel a = null;
		LoginModel m = (LoginModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from LoginModel where user_name='" + user_name + "'")
				.uniqueResult();
		return m;
	}

	@Override
	public void editParentById(int parent_id, String device_token) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE LoginModel set device_token='" + device_token
								+ "' where user_id='" + parent_id + "'")
				.executeUpdate();
	}
	@Override
	public void updateParentDeviceId(int parent_id, String device_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE LoginModel set device_id='" + device_id
								+ "' where user_id='" + parent_id + "'")
				.executeUpdate();
	}
	
	@Override
	public void updateDriverDeviceToken(int driver_id, String device_token) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE DriverModel set device_token='" + device_token
								+ "' where driver_id='" + driver_id + "'")
				.executeUpdate();
	}
	@Override
	public void updateDriverDeviceId(int driver_id,String device_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE DriverModel set device_id='" + device_id
								+ "' where driver_id='" + driver_id + "'")
				.executeUpdate();
	}
	
	
	@Override
	public void updateDriverLoggedIn(int driver_id, int logged_status) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE DriverModel set logged_status='" + logged_status
								+ "' where driver_id='" + driver_id + "'")
				.executeUpdate();
	}

	
		
	@Override
	public LoginModel checkMobile(String mobile) {
		// TODO Auto-generated method stub
		LoginModel a = null;
		LoginModel m = (LoginModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from LoginModel where mobile_number='" + mobile + "'")
				.uniqueResult();
		return m;
	}

}
