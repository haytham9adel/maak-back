package com.trackingbus.service;
import com.trackingbus.model.CountryModel;
import com.trackingbus.model.DriverModel;
import com.trackingbus.model.FrontDashboard;
import com.trackingbus.model.HolidayModel;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.RouteLatLngModel;
import com.trackingbus.model.StateModel;
import com.trackingbus.model.CityModel;
import com.trackingbus.model.StudentModel;

import java.util.List;
/**
 * @author Rudiment Webtech Solutions
 * */
public interface FrontDashboardService {
	
	public FrontDashboard getPage(int page_id);
	public List<StateModel> listStates(int country_id);
	public List<CityModel> listCity(int state_id);
	public List<DriverModel> driverByRoute(int route_id);
	public CityModel getCityById(int city_id);
	public CountryModel getCountryById(int country_id);
	public List<RouteLatLngModel> listRouteStudent(int route_id);
	public RouteLatLngModel getStudentAddress(int student_id);
	public LoginModel getParentById(int parent_id);
	public List<HolidayModel> getHolidayByMonth(int month,int year,int school_id);
	

}
