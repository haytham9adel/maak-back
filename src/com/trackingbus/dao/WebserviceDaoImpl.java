package com.trackingbus.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.trackingbus.model.StudentModel;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("webservicedao")
public class WebserviceDaoImpl implements WebserviceDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public DriverModel driverLogin(String driver_email, String driver_pass) {
		// TODO Auto-generated method stub
		DriverModel m = (DriverModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from DriverModel where username='" + driver_email + "'")
				.uniqueResult();
		return m;
	}

	@Override
	public DriverModel driverLoginByNFC(int nfc_code) {
		// TODO Auto-generated method stub
		DriverModel m = (DriverModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from DriverModel where driver_id='" + nfc_code + "'")
				.uniqueResult();
		return m;
	}
	
	@Override
	public DriverModel getDriverByRouteId(int route_id) {
		// TODO Auto-generated method stub
		DriverModel m = (DriverModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from DriverModel where route_id='" + route_id + "'")
				.uniqueResult();
		return m;
	}
	
	
	@Override
	public void driverTrack(DriverTrackModel dtm) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(dtm);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RouteLatLngModel> getRouteLatLngDetails(int route_id) {
		// TODO Auto-generated method stub
		return (List<RouteLatLngModel>) sessionFactory.getCurrentSession()
				.createCriteria(RouteLatLngModel.class)
				.add(Restrictions.eq("route_id", route_id))
				.addOrder(Order.asc("lat")).list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public RouteModel getRouteDetailsByRouteId(int route_id) {
		// TODO Auto-generated method stub

		return (RouteModel) sessionFactory.getCurrentSession()
				.createCriteria(RouteModel.class)
				.add(Restrictions.eq("route_id", route_id)).uniqueResult();
	}

	@Override
	public AttendanceModel getAttendanceByStudentId(int student_id) {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		return (AttendanceModel) sessionFactory.getCurrentSession()
				.createCriteria(AttendanceModel.class)
				.add(Restrictions.eq("student_id", student_id))
				.add(Restrictions.eq("date", dateFormat.format(date)))
				.uniqueResult();
	}
	@Override
	public StudentAbsentModel getAbsentByStudentId(int student_id) {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		return (StudentAbsentModel) sessionFactory.getCurrentSession()
				.createCriteria(StudentAbsentModel.class)
				.add(Restrictions.eq("student_id", student_id))
				.add(Restrictions.eq("absent_date", dateFormat.format(date)))
				.uniqueResult();
	}

	@Override
	public RouteLatLngModel getStudentLatLng(int student_id) {
		// TODO Auto-generated method stub
		return (RouteLatLngModel) sessionFactory.getCurrentSession()
				.createCriteria(RouteLatLngModel.class)
				.add(Restrictions.eq("student_id", student_id)).uniqueResult();
	}

	@Override
	public DriverTrackModel getDriverCurrentLocation(int route_id) {
		// TODO Auto-generated method stub
		return (DriverTrackModel) sessionFactory.getCurrentSession()
				.createCriteria(DriverTrackModel.class)
				.add(Restrictions.eq("route_id", route_id))
				.addOrder(Order.desc("track_id")).setMaxResults(1)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RouteLatLngModel> getLatLngByParentId(int parent_id) {
		// TODO Auto-generated method stub
		return (List<RouteLatLngModel>) sessionFactory.getCurrentSession()
				.createCriteria(RouteLatLngModel.class)
				.add(Restrictions.eq("parent_id", parent_id)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RouteModel> getRouteDetailsBySchoolId(int school_id) {
		// TODO Auto-generated method stub
		return (List<RouteModel>) sessionFactory.getCurrentSession()
				.createCriteria(RouteModel.class)
				.add(Restrictions.eq("school_id", school_id)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RouteLatLngModel> getRouteDetailsBySchoolId(int school_id,
			int route_id) {
		// TODO Auto-generated method stub
		return (List<RouteLatLngModel>) sessionFactory.getCurrentSession()
				.createCriteria(RouteLatLngModel.class)
				.add(Restrictions.eq("school_id", school_id))
				.add(Restrictions.eq("route_id", route_id)).list();
	}

	@Override
	public void sendMessage(ParentMessage pm) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(pm);
	}
	
	@Override
	public void sendMessageDriver(DriverMessage pm) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(pm);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParentMessage> getMessageByParentId(int reciever_id) {
		// TODO Auto-generated method stub
		return (List<ParentMessage>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from ParentMessage where reciever_id='" + reciever_id
								+ "' or sender_id='" + reciever_id + "'")
				.list();
	}

	@Override
	public void updateStudentAttandance(int student_id, String date, String field,String field_value) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"Update AttendanceModel set status=2, "+field+"='"+field_value+"' where student_id='"
								+ student_id + "' and date='" + date + "'")
				.executeUpdate();
	}
	@Override
	public void updateStudentAttandanceSecond(int student_id, String date, String field,String field_value) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"Update AttendanceModel set status=1, "+field+"='"+field_value+"' where student_id='"
								+ student_id + "' and date='" + date + "'")
				.executeUpdate();
	}
	
	@Override
	public void updateDriverAttandance(int driver_id, String date, String field,String field_value) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"Update DriverAttendanceModel set status=2, "+field+"='"+field_value+"' where driver_id='"
								+ driver_id + "' and login_date='" + date + "'")
				.executeUpdate();
	}

	@Override
	public int insertNotification(NotificationModel nm) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(nm);
		return nm.getNoti_id();
	}

	@Override
	public void insertStudentAttandance(AttendanceModel am) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(am);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationModel> getNotificationList(int parent_id,
			int route_id) {
		// TODO Auto-generated method stub
		return (List<NotificationModel>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from NotificationModel where parent_id='" + parent_id
								+ "' or( route_id='" + route_id + "'  AND  parent_id =0) order by noti_id desc")
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DriverMessage> getMessageByDriverId(int reciever_id) {
		// TODO Auto-generated method stub
		return (List<DriverMessage>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from DriverMessage where reciever_id='" + reciever_id
								+ "' or sender_id='" + reciever_id + "'")
				.list();
	}

	@Override
	public DriverAttendanceModel driverLoggedCheck(int driver_id) {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date();
		String date = dateFormat.format(date1);
		
		DriverAttendanceModel m = (DriverAttendanceModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from DriverAttendanceModel where driver_id='" + driver_id + "' and login_date='"+date+"' ")
				.uniqueResult();
		return m;
	}

	@Override
	public void insertDriverAttendace(DriverAttendanceModel dab) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(dab);
	}
	/**
	 * Function for view parent with childs
	 **/
	@Override
	public void editParentById(int parent_id, LoginModel login) {
		// TODO Auto-generated method stub
					sessionFactory
					.getCurrentSession()
					.createQuery( "UPDATE LoginModel set  user_pass='" + login.getUser_pass()
									+ "' where user_id='" + parent_id + "'")
					.executeUpdate();
		}

	@Override
	public void updateStudentBlinkStatus(int student_id, int blink_status) {
		// TODO Auto-generated method stub
		sessionFactory
		.getCurrentSession()
		.createQuery(
				"Update StudentModel set blink_status="+blink_status+" where student_id="+student_id)
		.executeUpdate();
	}

}