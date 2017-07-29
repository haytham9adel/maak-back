package com.trackingbus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.trackingbus.dao.FrontDashboardDao;
import com.trackingbus.dao.WebserviceDao;
import com.trackingbus.model.AttendanceModel;
import com.trackingbus.model.DriverAttendanceModel;
import com.trackingbus.model.DriverMessage;
import com.trackingbus.model.DriverModel;
import com.trackingbus.model.DriverTrackModel;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.NotificationModel;
import com.trackingbus.model.ParentMessage;
import com.trackingbus.model.RouteLatLngModel;
import com.trackingbus.model.RouteModel;
import com.trackingbus.model.StudentAbsentModel;

@Service("webservicesser")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)

public class WebserviceImp implements WebservicesSer {

	@Autowired
	private WebserviceDao webservicedao;

	@Override
	public DriverModel driverLogin(String driver_email, String driver_pass) {
		// TODO Auto-generated method stub
		DriverModel drivermodel=webservicedao.driverLogin(driver_email, driver_pass);
		return drivermodel;
	}

	@Override
	public DriverModel driverLoginByNFC(int nfc_code) {
		// TODO Auto-generated method stub
		DriverModel drivermodel=webservicedao.driverLoginByNFC(nfc_code);
		return drivermodel;
	}
	@Override
	public DriverModel getDriverByRouteId(int route_id) {
		// TODO Auto-generated method stub
		DriverModel drivermodel=webservicedao.getDriverByRouteId(route_id);
		return drivermodel;
	}
	
	
	

	@Override
	public void driverTrack(DriverTrackModel dtm) {
		// TODO Auto-generated method stub
		System.out.println(dtm.getLat());
		webservicedao.driverTrack(dtm);
	}

	@Override
	public List<RouteLatLngModel> getRouteLatLngDetails(int route_id) {
		// TODO Auto-generated method stub
		
		return webservicedao.getRouteLatLngDetails(route_id);
	}

	@Override
	public RouteModel getRouteDetailsByRouteId(int route_id) {
		// TODO Auto-generated method stub
		return webservicedao.getRouteDetailsByRouteId(route_id);
	}

	@Override
	public AttendanceModel getAttendanceByStudentId(int student_id) {
		// TODO Auto-generated method stub
		return webservicedao.getAttendanceByStudentId(student_id);
	}
	@Override
	public StudentAbsentModel getAbsentByStudentId(int student_id) {
		// TODO Auto-generated method stub
		return webservicedao.getAbsentByStudentId(student_id);
	}
	
	
	@Override
	public RouteLatLngModel getStudentLatLng(int student_id) {
		// TODO Auto-generated method stub
		return webservicedao.getStudentLatLng(student_id);
	}

	@Override
	public DriverTrackModel getDriverCurrentLocation(int route_id) {
		// TODO Auto-generated method stub
		return webservicedao.getDriverCurrentLocation(route_id);
	}

	@Override
	public List<RouteLatLngModel> getLatLngByParentId(int parent_id) {
		// TODO Auto-generated method stub
		return webservicedao.getLatLngByParentId(parent_id);
	}

	@Override
	public List<RouteModel> getRouteDetailsBySchoolId(int school_id) {
		// TODO Auto-generated method stub
		return webservicedao.getRouteDetailsBySchoolId(school_id);
	}

	@Override
	public List<RouteLatLngModel> getRouteDetailsBySchoolId(int school_id,
			int route_id) {
		// TODO Auto-generated method stub
		return webservicedao.getRouteDetailsBySchoolId(school_id,route_id);
	}

	@Override
	public void sendMessage(ParentMessage pm) {
		// TODO Auto-generated method stub
		webservicedao.sendMessage(pm);
	}
	
	@Override
	public void sendMessageDriver(DriverMessage pm) {
		// TODO Auto-generated method stub
		webservicedao.sendMessageDriver(pm);
	}

	@Override
	public List<ParentMessage> getMessageByParentId(int reciever_id) {
		// TODO Auto-generated method stub
		return webservicedao.getMessageByParentId(reciever_id);
	}
	
	@Override
	public List<DriverMessage> getMessageByDriverId(int reciever_id) {
		// TODO Auto-generated method stub
		return webservicedao.getMessageByDriverId(reciever_id);
	}
	
	
	@Override
	public List<NotificationModel> getNotificationList(int parent_id,int route_id) {
		// TODO Auto-generated method stub
		return webservicedao.getNotificationList(parent_id,route_id);
	}
	
	@Override
	public void updateStudentAttandance(int student_id, String date,String field, String field_value) {
		// TODO Auto-generated method stub
		webservicedao.updateStudentAttandance(student_id,date,field,field_value);
	}
	@Override
	public void updateStudentAttandanceSecond(int student_id, String date,String field, String field_value) {
		// TODO Auto-generated method stub
		webservicedao.updateStudentAttandanceSecond(student_id,date,field,field_value);
	}
	
	@Override
	public void updateDriverAttandance(int driver_id, String date,String field, String field_value) {
		// TODO Auto-generated method stub
		webservicedao.updateDriverAttandance(driver_id,date,field,field_value);
	}

	@Override
	public int insertNotification(NotificationModel nm) {
		// TODO Auto-generated method stub
		return webservicedao.insertNotification(nm);
	}

	@Override
	public void insertStudentAttandance(AttendanceModel am) {
		// TODO Auto-generated method stub
			webservicedao.insertStudentAttandance(am);
	}

	@Override
	public DriverAttendanceModel driverLoggedCheck(int driver_id) {
		// TODO Auto-generated method stub
		DriverAttendanceModel dab=webservicedao.driverLoggedCheck(driver_id);
		return dab;
	}

	@Override
	public void insertDriverAttendace(DriverAttendanceModel dab) {
		// TODO Auto-generated method stub
		webservicedao.insertDriverAttendace(dab);
	}

	@Override
	public void editParentById(int parent_id, LoginModel login) {
		// TODO Auto-generated method stub
		webservicedao.editParentById(parent_id,login);
	}

	@Override
	public void updateStudentBlinkStatus(int student_id, int status) {
		// TODO Auto-generated method stub
		webservicedao.updateStudentBlinkStatus(student_id,status);
	}

	
}
