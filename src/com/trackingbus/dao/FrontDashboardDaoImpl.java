package com.trackingbus.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trackingbus.model.CityModel;
import com.trackingbus.model.CountryModel;
import com.trackingbus.model.DriverModel;
import com.trackingbus.model.FrontDashboard;
import com.trackingbus.model.HolidayModel;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.RouteLatLngModel;
import com.trackingbus.model.StateModel;
import com.trackingbus.model.StudentModel;

/**
 * @author Rudiment Webtech Solutions
 *
 */
@Repository("frontdashboarddao")
public class FrontDashboardDaoImpl implements FrontDashboardDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public FrontDashboard getPage(int page_id) {
		return (FrontDashboard) sessionFactory.getCurrentSession().get(
				FrontDashboard.class, page_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StateModel> listState(int country_id) {
		return (List<StateModel>) sessionFactory.getCurrentSession()
				.createCriteria(StateModel.class)
				.add(Restrictions.eq("country_id", country_id)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CityModel> listCity(int country_id) {
		return (List<CityModel>) sessionFactory.getCurrentSession()
				.createCriteria(CityModel.class)
				.add(Restrictions.eq("country_id", country_id))
				.addOrder(Order.asc("city_name")).list();
	}

	@Override
	public CityModel getCityById(int city_id) {
		// TODO Auto-generated method stub
		return (CityModel) sessionFactory.getCurrentSession()
				.createQuery("From CityModel where city_id='" + city_id + "'")
				.uniqueResult();
	}

	@Override
	public CountryModel getCountryById(int country_id) {
		// TODO Auto-generated method stub
		return (CountryModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"From CountryModel where c_id='" + country_id + "'")
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RouteLatLngModel> listRouteStudent(int route_id) {
		// TODO Auto-generated method stub
		return (List<RouteLatLngModel>) sessionFactory.getCurrentSession()
				.createCriteria(RouteLatLngModel.class)
				.add(Restrictions.eq("route_id", route_id)).list();
	}

	@Override
	public RouteLatLngModel getStudentAddress(int student_id) {
		// TODO Auto-generated method stub
		return (RouteLatLngModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"From RouteLatLngModel where student_id='" + student_id
								+ "'").uniqueResult();
	}

	@Override
	public LoginModel getParentById(int parent_id) {
		// TODO Auto-generated method stub
		return (LoginModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from LoginModel where user_id='" + parent_id + "'")
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DriverModel> getDriverByRoute(int route_id) {
		// TODO Auto-generated method stub
		return (List<DriverModel>) sessionFactory.getCurrentSession()
				.createCriteria(DriverModel.class)
				.add(Restrictions.eq("route_id", route_id))
				.addOrder(Order.asc("driver_fname")).list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HolidayModel> getHolidayByMonth(int month, int year,
			int school_id) {
		// TODO Auto-generated method stub
		if(school_id==0)
		{
			return (List<HolidayModel>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from HolidayModel where month(holiday_date) = " + month
									+ " and year(holiday_date) = " + year
									+ " order by holiday_date desc").list();
		}
		else
		{
			return (List<HolidayModel>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from HolidayModel where month(holiday_date) = " + month
									+ " and year(holiday_date) = " + year
									+ " and (school_id=" + school_id
									+ " or school_id=0) order by holiday_date desc").list();
		}
		
	}

}
