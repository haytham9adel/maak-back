package com.trackingbus.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.trackingbus.dao.FrontDashboardDao;
import com.trackingbus.model.CityModel;
import com.trackingbus.model.CountryModel;
import com.trackingbus.model.DriverModel;
import com.trackingbus.model.FrontDashboard;
import com.trackingbus.model.HolidayModel;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.RouteLatLngModel;
import com.trackingbus.model.StateModel;
/**
 * @author Rudiment Webtech Solutions
 * */
@Service("frontdashboardservice")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FrontDashboardServiceImpl implements FrontDashboardService {

	@Autowired
	private FrontDashboardDao frontdashboardao;
	
	@Override
	public FrontDashboard getPage(int page_id) {
		
		return frontdashboardao.getPage(page_id);
	}
	@Override
	public List<StateModel> listStates(int country_id) {
		return frontdashboardao.listState(country_id);
	}
	@Override
	public List<CityModel> listCity(int country_id) {
		return frontdashboardao.listCity(country_id);
	}
	@Override
	public CityModel getCityById(int city_id) {
		// TODO Auto-generated method stub
		return frontdashboardao.getCityById(city_id);
	}
	@Override
	public CountryModel getCountryById(int country_id) {
		// TODO Auto-generated method stub
		return frontdashboardao.getCountryById(country_id);
	}
	@Override
	public List<RouteLatLngModel> listRouteStudent(int route_id) {
		// TODO Auto-generated method stub
		return frontdashboardao.listRouteStudent(route_id);
	}
	@Override
	public RouteLatLngModel getStudentAddress(int student_id) {
		// TODO Auto-generated method stub
		return frontdashboardao.getStudentAddress(student_id);
	}
	@Override
	public LoginModel getParentById(int parent_id) {
		// TODO Auto-generated method stub
		return frontdashboardao.getParentById(parent_id);
	}
	@Override
	public List<DriverModel> driverByRoute(int route_id) {
		// TODO Auto-generated method stub
		return frontdashboardao.getDriverByRoute(route_id);
	}
	@Override
	public List<HolidayModel> getHolidayByMonth(int month, int year,
			int school_id) {
		// TODO Auto-generated method stub
		return frontdashboardao.getHolidayByMonth(month, year,school_id);
	}

	

}
