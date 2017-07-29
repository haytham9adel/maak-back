package com.trackingbus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.trackingbus.model.StudentModel;

import java.util.List;

import com.trackingbus.dao.StudentDao;
import com.trackingbus.model.CountryModel;
import com.trackingbus.model.DriverTrackModel;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.NationlityModel;
import com.trackingbus.model.RouteLatLngModel;
import com.trackingbus.model.StudentAbsentModel;
import com.trackingbus.model.StudentTrackingModel;
import com.trackingbus.model.SubscriberModel;

@Service("studentService")  
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)  
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;

	@Override
	public void addStudent(StudentModel s) {
		studentDao.addSchool(s);
	}

	@Override
	public StudentModel checkSchoolName(String email) {
		StudentModel studentmodel = studentDao.checkSchoolName(email);
		return studentmodel;
	}

	@Override
	public List<LoginModel> listParent(int school_id) {
		return studentDao.listParent(school_id);
		
	}

	@Override
	public List<StudentModel> getStudentByParent(int parent_id) {
		// TODO Auto-generated method stub
		return studentDao.getStudentByParent(parent_id);
	}
	@Override
	public StudentModel getStudentById(int student_id) {
		// TODO Auto-generated method stub
		return (StudentModel) studentDao.getStudentById(student_id);
	}

	@Override
	public List<LoginModel> getAllParents() {
		// TODO Auto-generated method stub
		return studentDao.getAllParents();
	}

	@Override
	public List<StudentModel> getStudentByParentStatus(int p_status_id) {
		// TODO Auto-generated method stub
		return studentDao.getStudentByParentStatus(p_status_id);
	}

	@Override
	public long addNewStudent(StudentModel s) {
		// TODO Auto-generated method stub
		long id=studentDao.addNewStudent(s);
		return id;
	}

	@Override
	public RouteLatLngModel getLatLngBySId(int student_id) {
		// TODO Auto-generated method stub
		return (RouteLatLngModel) studentDao.getLatLngBySId(student_id);
	}

	@Override
	public List<StudentTrackingModel> getStudentTracking(int student_id) {
		// TODO Auto-generated method stub
		return studentDao.getStudentTracking(student_id);
	}

	@Override
	public List<DriverTrackModel> getDriverTrack(int route_id) {
		// TODO Auto-generated method stub
		return studentDao.getDriverTrack(route_id);
	}
	
	public List<DriverTrackModel> getAllDriverTrack(String[] drivers) {
		// TODO Auto-generated method stub
		return studentDao.getAllDriverTrack(drivers);
	}
	
	

	@Override
	public List<DriverTrackModel> getDriverTrackLimit(int route_id) {
		// TODO Auto-generated method stub
		return studentDao.getDriverTrackLimit(route_id);
	}
	
	@Override
	public List<DriverTrackModel> getDriverTrackLimitOne(int route_id) {
		// TODO Auto-generated method stub
		return studentDao.getDriverTrackLimitOne(route_id);
	}
	
	@Override
	public NationlityModel getNationalityById(int national_id) {
		// TODO Auto-generated method stub
		return studentDao.getNationalityById(national_id);
	}

	@Override
	public void updateNationalityById(NationlityModel nation, int national_id) {
		// TODO Auto-generated method stub
		studentDao.updateNationalityById(nation,national_id);
	}

	@Override
	public void addStudentAbsent(StudentAbsentModel s) {
		// TODO Auto-generated method stub
		studentDao.addStudentAbsent(s);
		 
	}

	@Override
	public SubscriberModel getSubEmailById(String email) {
		// TODO Auto-generated method stub
		return studentDao.getSubEmailById(email);
	}

	@Override
	public void addSubscribers(SubscriberModel s) {
		// TODO Auto-generated method stub
		 studentDao.addSubscribers(s);
	}

}
