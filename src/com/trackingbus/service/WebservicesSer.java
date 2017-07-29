package com.trackingbus.service;

import java.util.List;

import com.trackingbus.model.AttendanceModel;
import com.trackingbus.model.CountryModel;
import com.trackingbus.model.DriverAttendanceModel;
import com.trackingbus.model.DriverMessage;
import com.trackingbus.model.DriverModel;
import com.trackingbus.model.DriverTrackModel;
import com.trackingbus.model.FrontDashboard;
import com.trackingbus.model.HolidayModel;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.NotificationModel;
import com.trackingbus.model.ParentMessage;
import com.trackingbus.model.RouteLatLngModel;
import com.trackingbus.model.RouteModel;
import com.trackingbus.model.StateModel;
import com.trackingbus.model.CityModel;
import com.trackingbus.model.StudentAbsentModel;
import com.trackingbus.model.StudentModel;


public interface WebservicesSer {
	
	public DriverModel driverLogin(String driver_email,String driver_pass);
	public DriverAttendanceModel driverLoggedCheck(int driver_id);
	public void insertDriverAttendace(DriverAttendanceModel dab);
	
	public DriverModel driverLoginByNFC(int nfc_code);
	public DriverModel getDriverByRouteId(int route_id);
	public void driverTrack(DriverTrackModel dtm);
	public List<RouteLatLngModel> getRouteLatLngDetails(int route_id);
	public RouteModel getRouteDetailsByRouteId(int route_id);
	public AttendanceModel getAttendanceByStudentId(int student_id);
	public StudentAbsentModel getAbsentByStudentId(int student_id);
	public RouteLatLngModel getStudentLatLng(int student_id);
	public DriverTrackModel getDriverCurrentLocation(int route_id);
	public List<RouteLatLngModel> getLatLngByParentId(int parent_id);
	public List<RouteModel> getRouteDetailsBySchoolId(int school_id);
	public List<RouteLatLngModel> getRouteDetailsBySchoolId(int school_id, int route_id);
	public void sendMessage(ParentMessage pm);
	public void sendMessageDriver(DriverMessage pm);
	public List<ParentMessage> getMessageByParentId(int reciever_id);
	public List<DriverMessage> getMessageByDriverId(int reciever_id);
	public void updateStudentAttandance(int student_id,String date,String field, String field_value);
	public void updateStudentAttandanceSecond(int student_id,String date,String field, String field_value);
	public int insertNotification(NotificationModel nm);
	public void insertStudentAttandance(AttendanceModel am);
	public List<NotificationModel> getNotificationList(int parent_id,int route_id);
	public void updateDriverAttandance(int driver_id,String date,String field, String field_value);
	public void editParentById(int parent_id, LoginModel login);
	public void updateStudentBlinkStatus(int student_id,int status);
}
