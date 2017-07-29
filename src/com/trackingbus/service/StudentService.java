package com.trackingbus.service;
import com.trackingbus.model.DriverTrackModel;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.NationlityModel;
import com.trackingbus.model.RouteLatLngModel;
import com.trackingbus.model.SchoolModel;
import com.trackingbus.model.StudentAbsentModel;
import com.trackingbus.model.StudentModel;
import com.trackingbus.model.StudentTrackingModel;
import com.trackingbus.model.SubscriberModel;

import java.util.List;
public interface StudentService {
	public void addStudent(StudentModel s);
	public long addNewStudent(StudentModel s);
	public StudentModel checkSchoolName(String email);
	public List<LoginModel> listParent(int school_id);
	public List<LoginModel> getAllParents();
	public List<StudentModel> getStudentByParent(int parent_id);
	public StudentModel getStudentById(int student_id);
	public NationlityModel getNationalityById(int national_id);
	public RouteLatLngModel getLatLngBySId(int student_id);
	
	/*this is new functionality  added later for get student by all parent id*/
	public List<StudentModel> getStudentByParentStatus(int p_status_id);
	/*This is for track student*/
	public List<StudentTrackingModel> getStudentTracking(int student_id);
	public List<DriverTrackModel> getDriverTrack(int route_id);
	public List<DriverTrackModel> getAllDriverTrack(String[] drivers);
	
	public void updateNationalityById(NationlityModel nation,int national_id);
	public List<DriverTrackModel> getDriverTrackLimit(int route_id);
	public List<DriverTrackModel> getDriverTrackLimitOne(int route_id);
	public void addStudentAbsent(StudentAbsentModel s);
	
	public SubscriberModel getSubEmailById(String email);
	public void addSubscribers(SubscriberModel s);	
}
