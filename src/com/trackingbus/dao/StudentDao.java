package com.trackingbus.dao;
import java.util.List;

import com.trackingbus.model.CityModel;
import com.trackingbus.model.DriverTrackModel;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.NationlityModel;
import com.trackingbus.model.RouteLatLngModel;
import com.trackingbus.model.StudentAbsentModel;
import com.trackingbus.model.StudentModel;
import com.trackingbus.model.StudentTrackingModel;
import com.trackingbus.model.SubscriberModel;
public interface StudentDao {
	public void addSchool(StudentModel s);
	public long addNewStudent(StudentModel s);
	public void addStudentAbsent(StudentAbsentModel s);
	public StudentModel checkSchoolName(String email);
	public List<LoginModel> listParent(int school_id);
	
	public List<LoginModel> getAllParents();
	
	public List<StudentModel> getStudentByParent(int parent_id);
	public StudentModel getStudentById(int student_id);
	/*this is new functionlity added later for get student by all parent id*/
	public List<StudentModel> getStudentByParentStatus(int p_status_id);
	public RouteLatLngModel getLatLngBySId(int student_id);
	public List<StudentTrackingModel> getStudentTracking(int student_id);
	public List<DriverTrackModel> getDriverTrack(int student_id);
	public NationlityModel getNationalityById(int national_id);
	public void updateNationalityById(NationlityModel nation, int national_id);
	public List<DriverTrackModel> getDriverTrackLimit(int student_id);
	public List<DriverTrackModel> getDriverTrackLimitOne(int route_id);
	public List<DriverTrackModel> getAllDriverTrack(Object[] drivers);
	public SubscriberModel getSubEmailById(String email);
	public void addSubscribers(SubscriberModel s);	
}
