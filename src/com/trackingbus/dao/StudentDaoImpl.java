package com.trackingbus.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trackingbus.model.CityModel;
import com.trackingbus.model.DriverTrackModel;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.NationlityModel;
import com.trackingbus.model.RouteLatLngModel;
import com.trackingbus.model.SchoolModel;
import com.trackingbus.model.StudentAbsentModel;
import com.trackingbus.model.StudentModel;
import com.trackingbus.model.StudentTrackingModel;
import com.trackingbus.model.SubscriberModel;

@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addSchool(StudentModel s) {
		sessionFactory.getCurrentSession().saveOrUpdate(s);

	}

	@Override
	public StudentModel checkSchoolName(String email) {
		return (StudentModel) sessionFactory.getCurrentSession()
				.createQuery("from StudentModel where s_email='" + email + "'")
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginModel> listParent(int student_id) {
		return (List<LoginModel>) sessionFactory.getCurrentSession()
				.createCriteria(LoginModel.class)
				.add(Restrictions.eq("school_id", student_id))
				.add(Restrictions.eq("user_role", 3))
				.add(Restrictions.ne("p_status", 0)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentModel> getStudentByParent(int parent_id) {
		// TODO Auto-generated method stub
		return (List<StudentModel>) sessionFactory.getCurrentSession()
				.createCriteria(StudentModel.class)
				.add(Restrictions.eq("s_parent_id", parent_id)).list();

	}

	@Override
	public StudentModel getStudentById(int student_id) {
		// TODO Auto-generated method stub
		
		return (StudentModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from StudentModel where student_id='" + student_id
								+ "'").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginModel> getAllParents() {
		// TODO Auto-generated method stub
		return (List<LoginModel>) sessionFactory.getCurrentSession()
				.createCriteria(LoginModel.class)
				.add(Restrictions.eq("user_role", 3)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentModel> getStudentByParentStatus(int p_status_id) {
		// TODO Auto-generated method stub
		return (List<StudentModel>) sessionFactory.getCurrentSession()
				.createCriteria(StudentModel.class)
				.add(Restrictions.eq("p_status_id", p_status_id)).list();
	}

	@Override
	public long addNewStudent(StudentModel s) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(s);
		return s.getStudent_id();
	}

	@Override
	public RouteLatLngModel getLatLngBySId(int student_id) {
		// TODO Auto-generated method stub
		return (RouteLatLngModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from RouteLatLngModel where student_id='" + student_id
								+ "'").uniqueResult();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentTrackingModel> getStudentTracking(int student_id) {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		// return (List<StudentTrackingModel>)
		// sessionFactory.getCurrentSession().createQuery("from StudentTrackingModel where student_id='"+student_id+"'").list();
		return (List<StudentTrackingModel>) sessionFactory.getCurrentSession()
				.createCriteria(StudentTrackingModel.class)
				.add(Restrictions.eq("student_id", student_id))
				.add(Restrictions.eq("track_date", dateFormat.format(date)))
				.list();
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<DriverTrackModel> getDriverTrack(int route_id) {
		// TODO Auto-generated method stub
		System.out.println(route_id);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		// return (List<StudentTrackingModel>)
		// sessionFactory.getCurrentSession().createQuery("from StudentTrackingModel where student_id='"+student_id+"'").list();
		return (List<DriverTrackModel>) sessionFactory.getCurrentSession()
				.createCriteria(DriverTrackModel.class)
				.add(Restrictions.eq("route_id", route_id))
				.add(Restrictions.eq("track_date", dateFormat.format(date)))
				.list();
	}*/
	@SuppressWarnings("unchecked")
	@Override
	public List<DriverTrackModel> getDriverTrack(int route_id) {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		return (List<DriverTrackModel>) sessionFactory.getCurrentSession()
				.createCriteria(DriverTrackModel.class)
				.add(Restrictions.eq("route_id", route_id))
				.add(Restrictions.eq("track_date", dateFormat.format(date)))
			    .list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<DriverTrackModel> getDriverTrackLimit(int route_id) {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
		Date date = new Date();
		
		return (List<DriverTrackModel>) sessionFactory.getCurrentSession()
				.createCriteria(DriverTrackModel.class)
				.add(Restrictions.eq("route_id", route_id))
				.add(Restrictions.eq("track_date", dateFormat.format(date)))
				.addOrder(Order.desc("track_id"))
			    .setMaxResults(2)
			    .list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<DriverTrackModel> getDriverTrackLimitOne(int route_id)
	{
		// TODO Auto-generated method stub
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
				Date date = new Date();
				
				return (List<DriverTrackModel>) sessionFactory.getCurrentSession()
						.createCriteria(DriverTrackModel.class)
						.add(Restrictions.eq("route_id", route_id))
						.add(Restrictions.eq("track_date", dateFormat.format(date)))
						.addOrder(Order.desc("track_id"))
					    .setMaxResults(1)
					    .list();
	}

	@Override
	public NationlityModel getNationalityById(int national_id) {
		// TODO Auto-generated method stub
		return (NationlityModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from NationlityModel where national_id='" + national_id
								+ "'").uniqueResult();
	}

	@Override
	public void updateNationalityById(NationlityModel nation, int national_id) {
		// TODO Auto-generated method stub
		 sessionFactory
				.getCurrentSession()
				.createQuery(
						"Update NationlityModel set name='"+nation.getName()+"' where national_id='" + national_id
								+ "'").executeUpdate();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<DriverTrackModel> getAllDriverTrack(Object[] drivers) {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		String[] employeeNames = { "test", "test2" };
		return (List<DriverTrackModel>) sessionFactory.getCurrentSession()
				.createCriteria(DriverTrackModel.class)
				.add(Restrictions.in("driver_id",employeeNames))
				.add(Restrictions.eq("track_date", dateFormat.format(date)))
			    .list();
	}

	@Override
	public void addStudentAbsent(StudentAbsentModel s) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(s);
		 
	}

	@Override
	public SubscriberModel getSubEmailById(String email) {
		// TODO Auto-generated method stub
		return (SubscriberModel) sessionFactory.getCurrentSession()
				.createQuery("from SubscriberModel where email='" + email + "'")
				.uniqueResult();
	}

	@Override
	public void addSubscribers(SubscriberModel s) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(s);
	}
	
	
	
	
}
