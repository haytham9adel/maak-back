package com.trackingbus.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trackingbus.bean.LoginBean;
import com.trackingbus.model.AdminSetupModel;
import com.trackingbus.model.AttendanceModel;
import com.trackingbus.model.CategoryContactModel;
import com.trackingbus.model.CityModel;
import com.trackingbus.model.ContactContentModel;
import com.trackingbus.model.ContactDetailModel;
import com.trackingbus.model.CountryModel;
import com.trackingbus.model.DriverAttendanceModel;
import com.trackingbus.model.DriverDocModel;
import com.trackingbus.model.DriverMessage;
import com.trackingbus.model.DriverModel;
import com.trackingbus.model.EmailTemplateModel;
import com.trackingbus.model.FaqModel;
import com.trackingbus.model.FeaturesModel;
import com.trackingbus.model.HolidayDeletedModel;
import com.trackingbus.model.HolidayModel;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.NationlityModel;
import com.trackingbus.model.PageContentModel;
import com.trackingbus.model.PageModel;
import com.trackingbus.model.ParentMessage;
import com.trackingbus.model.RouteLatLngModel;
import com.trackingbus.model.RouteModel;
import com.trackingbus.model.SchoolClassesModel;
import com.trackingbus.model.SchoolMessage;
import com.trackingbus.model.SchoolModel;
import com.trackingbus.model.SliderModel;
import com.trackingbus.model.StudentModel;
import com.trackingbus.model.SubscriberModel;
import com.trackingbus.model.TypingModel;
import com.trackingbus.model.VehicleDocModel;
import com.trackingbus.model.VehicleModel;
import com.trackingbus.model.relationshipModel;

@Repository("schoolDao")
public class SchoolDaoImpl implements SchoolDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public long addSchool(SchoolModel s) {
		sessionFactory.getCurrentSession().saveOrUpdate(s);
		return s.getS_id();

	}

	@Override
	public SchoolModel checkSchoolName(String email) {
		return (SchoolModel) sessionFactory.getCurrentSession()
				.createQuery("from SchoolModel where email='" + email + "'")
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<CountryModel> listCountry() {
		return (List<CountryModel>) sessionFactory.getCurrentSession()
				.createCriteria(CountryModel.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<SchoolModel> listSchools() {
		// TODO Auto-generated method stub
		return (List<SchoolModel>) sessionFactory.getCurrentSession()
				.createCriteria(SchoolModel.class).list();
	}

	@Override
	public void addParent(LoginModel s) {
		sessionFactory.getCurrentSession().saveOrUpdate(s);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentModel> listStudent(int school_id) {
		return (List<StudentModel>) sessionFactory.getCurrentSession()
				.createCriteria(StudentModel.class)
				.add(Restrictions.eq("s_school_id", school_id)).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StudentModel> getStudentsByRouteId(int route_id) {
		System.out.println("get here");
		return (List<StudentModel>) sessionFactory.getCurrentSession()
				.createCriteria(StudentModel.class)
				.add(Restrictions.eq("s_route_id", route_id)).list();
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<relationshipModel> listRelationship(){
		return (List<relationshipModel>) sessionFactory.getCurrentSession()
				.createCriteria(relationshipModel.class)
				.list();
	}
	
	
	
	
	@Override
	public void deleteParent(int parent_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM  LoginModel where user_id='" + parent_id
								+ "' and user_role='" + 3 + "'")
				.executeUpdate();

	}

	@Override
	public void deleteStudent(int student_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM StudentModel where student_id='"
								+ student_id + "'").executeUpdate();
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

	/**
	 * Function for save parent details
	 **/
	@Override
	public void editParentById(int parent_id, LoginModel login) {
		// TODO Auto-generated method stub

		if (login.getUser_pass() == "") {
			System.out.println("Password is not");
			sessionFactory
					.getCurrentSession()
					.createQuery(
							"UPDATE LoginModel set user_email='"
									+ login.getUser_email() + "',school_id='"
									+ login.getSchool_id() + "',first_name='"
									+ login.getFirst_name() + "',middle_name='"
									+ login.getMiddle_name()
									+ "',family_name='"
									+ login.getFamily_name()
									+ "',contact_number='"
									+ login.getContact_number()
									+ "' where user_id='" + parent_id + "'")
					.executeUpdate();
		} else {

			sessionFactory
					.getCurrentSession()
					.createQuery(
							"UPDATE LoginModel set user_email='"
									+ login.getUser_email() + "' ,school_id='"
									+ login.getSchool_id() + "',first_name='"
									+ login.getFirst_name() + "',middle_name='"
									+ login.getMiddle_name()
									+ "',family_name='"
									+ login.getFamily_name()
									+ "',contact_number='"
									+ login.getContact_number()
									+ "', user_pass='" + login.getUser_pass()
									+ "' where user_id='" + parent_id + "'")
					.executeUpdate();
		}

	}

	@Override
	public void saveParentSetting(int parent_id, LoginModel login) {
		
	/*	"UPDATE LoginModel set user_email='"
				+ login.getUser_email() + "' ,school_id='"
				+ login.getSchool_id() + "',first_name='"
				+ login.getFirst_name() + "',middle_name='"
				+ login.getMiddle_name()
				+ "',family_name='"
				+ login.getFamily_name()
				+ "',contact_number='"
				+ login.getContact_number()
				+ "', user_pass='" + login.getUser_pass()
				+ "' where user_id='" + parent_id + "'")*/
		
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE LoginModel set lang='" + login.getLang()
								+ "', noti_on='" + login.getNoti_on()
								+ "',chat_on='" + login.getChat_on()
								+ "', checked_in_on='"
								+ login.getChecked_in_on()
								+ "', checked_out_on='"
								+ login.getChecked_out_on() + "',speed_on='"
								+ login.getSpeed_on() + "',max_speed='"
								+ login.getMax_speed() 
								+ "',wrong_route_on='"+ login.getWrong_route_on()
								+"',sms_checked_in_on='"+login.getSms_checked_in_on()+"'"
								+",sms_checked_out_on='"+login.getSms_checked_out_on()+"'"
								+",sms_speed_on='"+login.getSms_speed_on()+"'"
								+",sms_max_speed='"+login.getSms_max_speed()+"'"
								+",sms_wrong_route_on='"+login.getSms_wrong_route_on()+"'"
								+",instant_message='"+login.getInstant_message()+"'"
								+",morning_before='"+login.getMorning_before()+"'"
								+",evening_before='"+login.getEvening_before()+"'"
								+",sms_instant_message='"+login.getSms_instant_message()+"'"
								+",sound_setting='"+login.getSound_setting()+"'"
								+",chat_sound='"+login.getChat_sound()+"'"
								+",sms_morning_before='"+login.getSms_morning_before()+"'"
								+",sms_evening_before='"+login.getSms_evening_before()+"'"
								+ " where user_id='" + parent_id + "'")
				.executeUpdate();
		
		
	}
	@Override
	public void saveDriverSetting(int driver_id, DriverModel driver_details) {
		
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE DriverModel set lang='"+driver_details.getLang()+"' where driver_id='" + driver_id + "'")
				.executeUpdate();
	}
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<LoginModel> viewParentById(int parent_id) {
		return (List<LoginModel>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from LoginModel where user_id='" + parent_id + "'")
				.uniqueResult();
	}

	/**
	 * Function Update student by student id
	 **/
	@Override
	public void editStudentById(int student_id, StudentModel student) {
		// TODO Auto-generated method stub
		if (student.getS_pass() == "") {
			if (student.getS_image_path() != "") {
				sessionFactory
						.getCurrentSession()
						.createQuery(
								"UPDATE StudentModel set s_fname='"
										+ student.getS_fname() + "',s_lname='"
										+ student.getS_lname()
										+ "',s_address='"
										+ student.getS_address()
										+ "',s_contact='"
										+ student.getS_contact()
										+ "',s_image_path='"
										+ student.getS_image_path()
										+ "',s_parent_id='"
										+ student.getS_parent_id()
										+ "',p_status_id='"
										+ student.getP_status_id()
										+ "', s_school_id='"
										+ student.getS_school_id()
										+ "', s_route_id='"
										+ student.getS_route_id()
										+ "', father_name='"
										+ student.getFather_name()
										+ "', grand_name='"
										+ student.getGrand_name()
										+ "', family_name='"
										+ student.getFamily_name()
										+ "', gender='" + student.getGender()
										+ "', student_class='"
										+ student.getStudent_class()
										+ "', dob='" + student.getDob()
										+ "', nationality='"
										+ student.getNationality()
										+ "', blood_type='"
										+ student.getBlood_type()
										+ "', r_1='"
										+ student.getR_1()
										+ "', r_2='"
										+ student.getR_2()
										+ "', r_3='"
										+ student.getR_3()
										+ "', p_1='"
										+ student.getP_1()
										+ "', p_2='"
										+ student.getP_2()
										+ "', p_3='"
										+ student.getP_3()
										+ "' where student_id='" + student_id
										+ "'").executeUpdate();
			} else {
				sessionFactory
						.getCurrentSession()
						.createQuery(
								"UPDATE StudentModel set s_fname='"
										+ student.getS_fname() + "',s_lname='"
										+ student.getS_lname()
										+ "',s_address='"
										+ student.getS_address()
										+ "',s_contact='"
										+ student.getS_contact()
										+ "',s_parent_id='"
										+ student.getS_parent_id()
										+ "',s_school_id='"
										+ student.getS_school_id()
										+ "',p_status_id='"
										+ student.getP_status_id()
										+ "', s_route_id='"
										+ student.getS_route_id()
										+ "', father_name='"
										+ student.getFather_name()
										+ "', grand_name='"
										+ student.getGrand_name()
										+ "', family_name='"
										+ student.getFamily_name()
										+ "', gender='" + student.getGender()
										+ "', student_class='"
										+ student.getStudent_class()
										+ "', dob='" + student.getDob()
										+ "', nationality='"
										+ student.getNationality()
										+ "', blood_type='"
										+ student.getBlood_type()
										+ "', r_1='"
										+ student.getR_1()
										+ "', r_2='"
										+ student.getR_2()
										+ "', r_3='"
										+ student.getR_3()
										+ "', p_1='"
										+ student.getP_1()
										+ "', p_2='"
										+ student.getP_2()
										+ "', p_3='"
										+ student.getP_3()
										+ "' where student_id='" + student_id
										+ "'").executeUpdate();
			}

		} else {
			if (student.getS_image_path() != "") {
				sessionFactory
						.getCurrentSession()
						.createQuery(
								"UPDATE StudentModel set s_fname='"
										+ student.getS_fname() + "',s_lname='"
										+ student.getS_lname()
										+ "',s_address='"
										+ student.getS_address()
										+ "',s_contact='"
										+ student.getS_contact()
										+ "',s_image_path='"
										+ student.getS_image_path()
										+ "',s_parent_id='"
										+ student.getS_parent_id()
										+ "',s_school_id='"
										+ student.getS_school_id()
										+ "',p_status_id='"
										+ student.getP_status_id()
										+ "', s_route_id='"
										+ student.getS_route_id()
										+ "', father_name='"
										+ student.getFather_name()
										+ "', grand_name='"
										+ student.getGrand_name()
										+ "', family_name='"
										+ student.getFamily_name()
										+ "', gender='" + student.getGender()
										+ "', student_class='"
										+ student.getStudent_class()
										+ "', dob='" + student.getDob()
										+ "', nationality='"
										+ student.getNationality()
										+ "', blood_type='"
										+ student.getBlood_type()
										+ "', r_1='"
										+ student.getR_1()
										+ "', r_2='"
										+ student.getR_2()
										+ "', r_3='"
										+ student.getR_3()
										+ "', p_1='"
										+ student.getP_1()
										+ "', p_2='"
										+ student.getP_2()
										+ "', p_3='"
										+ student.getP_3()
										+ "' where student_id='" + student_id
										+ "'").executeUpdate();
			} else {
				sessionFactory
						.getCurrentSession()
						.createQuery(
								"UPDATE StudentModel set s_fname='"
										+ student.getS_fname() + "',s_lname='"
										+ student.getS_lname()
										+ "',s_address='"
										+ student.getS_address()
										+ "',s_contact='"
										+ student.getS_contact()
										+ "',s_school_id='"
										+ student.getS_school_id()
										+ "',s_parent_id='"
										+ student.getS_parent_id()
										+ "' ,p_status_id='"
										+ student.getP_status_id()
										+ "', s_route_id='"
										+ student.getS_route_id()
										+ "', father_name='"
										+ student.getFather_name()
										+ "', grand_name='"
										+ student.getGrand_name()
										+ "', family_name='"
										+ student.getFamily_name()
										+ "', gender='" + student.getGender()
										+ "', student_class='"
										+ student.getStudent_class()
										+ "', dob='" + student.getDob()
										+ "', nationality='"
										+ student.getNationality()
										+ "', blood_type='"
										+ student.getBlood_type()
										+ "', r_1='"
										+ student.getR_1()
										+ "', r_2='"
										+ student.getR_2()
										+ "', r_3='"
										+ student.getR_3()
										+ "', p_1='"
										+ student.getP_1()
										+ "', p_2='"
										+ student.getP_2()
										+ "', p_3='"
										+ student.getP_3()
										+ "' where student_id='" + student_id
										+ "'").executeUpdate();
			}
		}

	}

	@Override
	public SchoolModel getSchoolById(int school_id) {
		// TODO Auto-generated method stub
		return (SchoolModel) sessionFactory.getCurrentSession()
				.createQuery("from SchoolModel where s_id='" + school_id + "'")
				.uniqueResult();
	}

	@Override
	public Integer getStudentCount(int school_id) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				StudentModel.class);
		crit.add(Restrictions.eq("s_school_id", school_id));
		crit.setProjection(Projections.rowCount());
		return (Integer) crit.uniqueResult();

	}

	@Override
	public LoginModel getUserById(int user_id) {
		// TODO Auto-generated method stub
		return (LoginModel) sessionFactory.getCurrentSession()
				.createQuery("from LoginModel where user_id='" + user_id + "'")
				.uniqueResult();
	}

	@Override
	public void editSchoolById(int school_id, SchoolModel school) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE SchoolModel set school_name='"
								+ school.getSchool_name()
								+ "',principal_name='"
								+ school.getPrincipal_name()
								+ "',principal_contact='"
								+ school.getPrincipal_contact() + "',country='"
								+ school.getCountry() + "',city='"
								+ school.getCity() + "',zip_code='"
								+ school.getZip_code() + "' WHERE  s_id='"
								+ school_id + "'").executeUpdate();
	}

	@Override
	public Integer getVehicleCount(int school_id) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				VehicleModel.class);
		crit.add(Restrictions.eq("school_id", school_id));
		crit.setProjection(Projections.rowCount());
		return (Integer) crit.uniqueResult();
	}

	@Override
	public DriverModel checkDriverName(String email) {
		// TODO Auto-generated method stub
		return (DriverModel) sessionFactory.getCurrentSession()
				.createQuery("from DriverModel where d_email='" + email + "'")
				.uniqueResult();
	}

	@Override
	public long addDriver(DriverModel d) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(d);
		return d.getDriver_id();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DriverModel> listDriver(int school_id) {
		// TODO Auto-generated method stub
		return (List<DriverModel>) sessionFactory.getCurrentSession()
				.createCriteria(DriverModel.class)
				.add(Restrictions.eq("driver_school_id", school_id)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DriverModel> listDriverLatest(int school_id) {
		// TODO Auto-generated method stub
		return (List<DriverModel>) sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT DISTINCT(u.`driver_id`),u.`insurance_card_copy`,u.`insurance_document_name`,u.`insurance_document_expiry`,u.`remind_day`,u.`insurance_end_date`,u.`licence_expiry`,u.`middle_name`,u.`nationality`,u.`status`,u.`route_id`,u.`address`,u.`dob`,u.`image_path`,u.`blood_group`,u.`driver_fname`,u.`driver_lname`,u.`d_email`,u.`password`,u.`contact_number`,u.`address`,u.`driver_school_id`,u.`username`,u.`device_token`,u.`logged_status`,u.`device_id`,u.`lang` FROM tbl_driver as u LEFT JOIN ( SELECT * FROM tbl_driver_msg  ORDER BY time desc ) m ON u.driver_id = m.sender_id WHERE u.driver_school_id='"
								+ school_id + "' ORDER by m.time DESC")
				.addEntity(DriverModel.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DriverModel> getDriverList(int school_id) {
		// TODO Auto-generated method stub
		return (List<DriverModel>) sessionFactory.getCurrentSession()
				.createCriteria(DriverModel.class)
				.add(Restrictions.eq("s_school_id", school_id)).list();
	}

	@Override
	public void deleteDriver(int driver_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM DriverModel where driver_id='" + driver_id
								+ "'").executeUpdate();
	}

	@Override
	public DriverModel getDriverById(int driver_id) {
		// TODO Auto-generated method stub
		return (DriverModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM DriverModel where driver_id='" + driver_id + "'")
				.uniqueResult();
	}

	@Override
	public void editDriverById(int driver_id, DriverModel driver) {
		// TODO Auto-generated method stub
		if (driver.getImage_path() != "") {
			System.out.println("aa");
			sessionFactory
					.getCurrentSession()
					.createQuery(
							"UPDATE DriverModel set driver_fname='"
									+ driver.getDriver_fname() + "',d_email='"
									+ driver.getD_email() + "', driver_lname='"
									+ driver.getDriver_lname()
									+ "', image_path='"
									+ driver.getImage_path()
									+ "',driver_school_id='"
									+ driver.getDriver_school_id()
									+ "',route_id='" + driver.getRoute_id()
									+ "',middle_name='"
									+ driver.getMiddle_name() + "',dob='"
									+ driver.getDob() + "',nationality='"
									+ driver.getNationality()
									+ "',blood_group='"
									+ driver.getBlood_group()
									+ "',licence_expiry='"
									+ driver.getLicence_expiry()
									+ "' where driver_id='" + driver_id + "'")
					.executeUpdate();
		} else {
			System.out.println("nnn");
			sessionFactory
					.getCurrentSession()
					.createQuery(
							"UPDATE DriverModel set driver_fname='"
									+ driver.getDriver_fname() + "',d_email='"
									+ driver.getD_email() + "', driver_lname='"
									+ driver.getDriver_lname()
									+ "',driver_school_id='"
									+ driver.getDriver_school_id()
									+ "' ,route_id='" + driver.getRoute_id()
									+ "',middle_name='"
									+ driver.getMiddle_name() + "',dob='"
									+ driver.getDob() + "',nationality='"
									+ driver.getNationality()
									+ "',blood_group='"
									+ driver.getBlood_group()
									+ "',licence_expiry='"
									+ driver.getLicence_expiry()
									+ "' where driver_id='" + driver_id + "'")
					.executeUpdate();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentModel> getAllStudent() {
		// TODO Auto-generated method stub
		return (List<StudentModel>) sessionFactory.getCurrentSession()
				.createCriteria(StudentModel.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentModel> getAllStudentByRouteId(int route_id) {
		// TODO Auto-generated method stub
		return (List<StudentModel>) sessionFactory.getCurrentSession()
				.createCriteria(StudentModel.class)
				.add(Restrictions.eq("s_route_id", route_id)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginModel> getParentBySchool(int school_id) {
		// TODO Auto-generated method stub
		return (List<LoginModel>) sessionFactory.getCurrentSession()
				.createCriteria(LoginModel.class)
				.add(Restrictions.eq("school_id", school_id))
				.add(Restrictions.eq("user_role", 3)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DriverModel> allDrivers() {
		// TODO Auto-generated method stub
		return (List<DriverModel>) sessionFactory.getCurrentSession()
				.createCriteria(DriverModel.class).list();
	}

	@Override
	public void editSchoolByAdmin(int school_id, SchoolModel school) {
		// TODO Auto-generated method stub
		if (school.getSchool_logo() == "") {
			sessionFactory
					.getCurrentSession()
					.createQuery(
							"UPDATE SchoolModel set school_name='"
									+ school.getSchool_name()
									+ "',school_address='"
									+ school.getSchool_address()
									+ "',school_lat='" + school.getSchool_lat()
									+ "',school_lng='" + school.getSchool_lng()
									+ "',total_bus='" + school.getTotal_bus()
									+ "',total_students='"
									+ school.getTotal_students()
									+ "',country='" + school.getCountry()
									+ "',city='" + school.getCity()
									+ "',school_address_field='"
									+ school.getSchool_address_field()
									+ "' WHERE  s_id='" + school_id + "'")
					.executeUpdate();
		} else {
			sessionFactory
					.getCurrentSession()
					.createQuery(
							"UPDATE SchoolModel set school_name='"
									+ school.getSchool_name()
									+ "',school_address='"
									+ school.getSchool_address()
									+ "',school_lat='" + school.getSchool_lat()
									+ "',school_lng='" + school.getZip_code()
									+ "',total_bus='" + school.getTotal_bus()
									+ "',total_students='"
									+ school.getTotal_students()
									+ "',school_logo='"
									+ school.getSchool_logo()
									+ "',school_address_field='"
									+ school.getSchool_address_field()
									+ "' WHERE  s_id='" + school_id + "'")
					.executeUpdate();
		}

	}

	@Override
	public void deleteSchool(int school_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE from  SchoolModel where s_id='" + school_id
								+ "'").executeUpdate();

		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE from DriverModel where driver_school_id='"
								+ school_id + "'").executeUpdate();

		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE from LoginModel where school_id='" + school_id
								+ "'").executeUpdate();
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE from StudentModel where s_school_id='"
								+ school_id + "'").executeUpdate();

		/*
		 * sessionFactory .getCurrentSession() .createQuery(
		 * "DELETE from StaffModel where school_id='" + school_id +
		 * "'").executeUpdate();
		 */
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE from VehicleModel where school_id='"
								+ school_id + "'").executeUpdate();

	}

	@Override
	public long addRoute(RouteModel route) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(route);
		return route.getRoute_id();
	}

	@Override
	public void addRouteLatLng(RouteLatLngModel route) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(route);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RouteModel> listRoute(int school_id) {
		// TODO Auto-generated method stub
		return (List<RouteModel>) sessionFactory.getCurrentSession()
				.createCriteria(RouteModel.class)
				.add(Restrictions.eq("school_id", school_id)).list();
	}

	@Override
	public RouteModel getRouteById(int route_id) {
		// TODO Auto-generated method stub
		return (RouteModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from RouteModel where route_id='" + route_id + "'")
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RouteLatLngModel> listLatLng(int route_id) {
		// TODO Auto-generated method stub
		return (List<RouteLatLngModel>) sessionFactory.getCurrentSession()
				.createCriteria(RouteLatLngModel.class)
				.add(Restrictions.eq("route_id", route_id))
				.addOrder(Order.asc("lat")).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VehicleModel> getVechileByDriverId(int driver_id) {
		// TODO Auto-generated method stub
		return (List<VehicleModel>) sessionFactory.getCurrentSession()
				.createCriteria(VehicleModel.class)
				.add(Restrictions.eq("driver_id", driver_id))
				.addOrder(Order.asc("vechile_id")).list();
	}
	
	
	
	@Override
	public void editRouteById(int route_id, RouteModel routemodel) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE RouteModel set route_name='"
								+ routemodel.getRoute_name() + "',source='"
								+ routemodel.getSource() + "', destination='"
								+ routemodel.getDestination()
								+ "', source_lat='"
								+ routemodel.getSource_lat()
								+ "', source_lng='"
								+ routemodel.getSource_lng()
								+ "',destination_lat='"
								+ routemodel.getDestination_lat()
								+ "',source_note='"
								+ routemodel.getSource_note()
								+ "',destination_note='"
								+ routemodel.getDestination_note()
								+ "',destination_lng='"
								+ routemodel.getDestination_lng() + "',note='"
								+ routemodel.getNote() + "' where route_id='"
								+ route_id + "'").executeUpdate();
	}

	@Override
	public void saveGeoFancing(int route_id, RouteModel routemodel) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE RouteModel set radius='"
								+ routemodel.getRadius() + "' where route_id='"
								+ route_id + "'").executeUpdate();
	}

	@Override
	public void deleteLatLng(int route_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM  RouteLatLngModel where route_id='"
								+ route_id + "'").executeUpdate();

	}

	@Override
	public RouteLatLngModel checkStudentRouteId(int student_id, int school_id) {
		// TODO Auto-generated method stub
		return (RouteLatLngModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from RouteLatLngModel where student_id='" + student_id
								+ "' and school_id='" + school_id + "'")
				.uniqueResult();
	}

	@Override
	public void addAssignRoute(RouteLatLngModel s) {
		sessionFactory.getCurrentSession().saveOrUpdate(s);
	}

	@Override
	public void editStudentRoute(int student_id, RouteLatLngModel latlngmodel) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE RouteLatLngModel set address='"
								+ latlngmodel.getAddress() + "',lat='"
								+ latlngmodel.getLat() + "' , lng='"
								+ latlngmodel.getLng()
								+ "' WHERE  student_id='" + student_id + "'")
				.executeUpdate();
	}

	@Override
	public void deleteRoute(int route_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE from  RouteModel where route_id='" + route_id
								+ "'").executeUpdate();
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE from RouteLatLngModel where route_id='"
								+ route_id + "'").executeUpdate();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginModel> listSchoolAdmin(int school_id) {
		// TODO Auto-generated method stub
		if (school_id == 0) {
			return (List<LoginModel>) sessionFactory.getCurrentSession()
					.createCriteria(LoginModel.class)
					.add(Restrictions.eq("user_role", 2)).list();
		} else {
			return (List<LoginModel>) sessionFactory.getCurrentSession()
					.createCriteria(LoginModel.class)
					.add(Restrictions.eq("school_id", school_id))
					.add(Restrictions.eq("user_role", 2)).list();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginModel> listSchoolAdminLatest(int school_id) {
		// TODO Auto-generated method stub
		return (List<LoginModel>) sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT DISTINCT(u.`user_id`),u.`user_name`,u.`user_email`,u.`user_pass`,u.`user_role`,u.`school_id`,u.`contact_number`,u.`first_name`,u.`last_name`,u.`p_status`,u.`permission`,u.`main_school_admin`,u.`device_token`,u.`middle_name`,u.`family_name`,u.`mobile_number`,u.`radius`,u.`lang`,u.`noti_on`,u.`chat_on`,u.`checked_in_on`,u.`checked_out_on`,u.`speed_on`,u.`max_speed`,u.`wrong_route_on`,u.`sms_checked_in_on`,u.`sms_checked_out_on`,u.`sms_speed_on`,u.`sms_max_speed`,u.`sms_wrong_route_on`,u.`instant_message`,u.`morning_before`,u.`evening_before`,u.`sms_instant_message`,u.`sms_morning_before`,u.`sms_evening_before`,u.`device_id`,u.`sound_setting`,u.`chat_sound` FROM tbl_users as u LEFT JOIN ( SELECT * FROM tbl_school_msg   ORDER BY time desc ) m ON u.user_id = m.sender_id WHERE u.user_role=2 ORDER by m.time DESC")
				.addEntity(LoginModel.class).list();
	}

	@Override
	public void deleteSchoolAdmin(int admin_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM  LoginModel where user_id='" + admin_id
								+ "' and user_role='" + 2 + "'")
				.executeUpdate();
		/*
		 * sessionFactory .getCurrentSession()
		 * .createQuery("DELETE FROM  LoginModel where user_id='" + admin_id)
		 * .executeUpdate();
		 */
	}
	@Override
	public void deleteSuperAdmin(int admin_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM  LoginModel where user_id='" + admin_id
								+ "' and user_role='" + 1 + "'")
				.executeUpdate();
		
	}
	
	

	@Override
	public void deleteSchoolAdminNew(int admin_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM  LoginModel where user_id='" + admin_id
								+ "' and user_role='" + 2 + "'")
				.executeUpdate();
		/*
		 * sessionFactory .getCurrentSession()
		 * .createQuery("DELETE FROM  LoginModel where user_id='" + admin_id)
		 * .executeUpdate();
		 */
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginModel> getParentByPIdList(int p_status) {
		// TODO Auto-generated method stub
		return (List<LoginModel>) sessionFactory.getCurrentSession()
				.createCriteria(LoginModel.class)
				.add(Restrictions.eq("p_status", p_status)).list();
	}
/*
	@SuppressWarnings("unchecked")
	@Override
	public List<RouteLatLngModel> getRouteLatLng(int route_id) {
		// TODO Auto-generated method stub
		return (List<RouteLatLngModel>) sessionFactory.getCurrentSession()
				.createCriteria(RouteLatLngModel.class)
				.add(Restrictions.eq("route_id", route_id)).list();
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RouteLatLngModel> getRouteLatLng(int route_id) {
		// TODO Auto-generated method stub
		return (List<RouteLatLngModel>) sessionFactory.getCurrentSession()
				.createCriteria(RouteLatLngModel.class)
				.add(Restrictions.eq("route_id", route_id)).list();
	}


	@Override
	public void editRouteByStudentId(int student_id,
			RouteLatLngModel routelatlng) {
		// TODO Auto-generated method stub
		if (routelatlng.getRoute_id() == 0) {
			sessionFactory
					.getCurrentSession()
					.createQuery(
							"UPDATE RouteLatLngModel set lat='"
									+ routelatlng.getLat() + "',lng='"
									+ routelatlng.getLng() + "',address='"
									+ routelatlng.getAddress()
									+ "' where student_id='" + student_id + "'")
					.executeUpdate();
		} else {
			sessionFactory
					.getCurrentSession()
					.createQuery(
							"UPDATE RouteLatLngModel set lat='"
									+ routelatlng.getLat() + "',lng='"
									+ routelatlng.getLng() + "',route_id='"
									+ routelatlng.getRoute_id()
									+ "' where student_id='" + student_id + "'")
					.executeUpdate();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HolidayModel> getAllHoliday(int school_id) {
		// TODO Auto-generated method stub
		/*
		 * return (List<HolidayModel>) sessionFactory.getCurrentSession()
		 * .createCriteria(HolidayModel.class) .add(Restrictions.eq("school_id",
		 * school_id)).list();
		 */
		if (school_id == 0) {
			return (List<HolidayModel>) sessionFactory.getCurrentSession()
					.createQuery("from HolidayModel").list();

		} else {
			return (List<HolidayModel>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from HolidayModel where school_id='" + school_id
									+ "' or school_id=0").list();
		}
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<HolidayModel>getAllHolidayByMonth(int school_id,String month,String year) {
		// TODO Auto-generated method stub
		
			return (List<HolidayModel>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from HolidayModel where (school_id='" + school_id 
									+ "' or school_id=0) and MONTH(holiday_date) = '"+month+"' AND YEAR(holiday_date) = '"+year+"' ").list();
	
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<HolidayModel>getAllHolidayByTwoDate(int school_id,String start_date,String end_date) {
		// TODO Auto-generated method stub
		
			return (List<HolidayModel>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from HolidayModel where (school_id='" + school_id 
									+ "' or school_id=0) and holiday_date BETWEEN '"+start_date+"' and '"+end_date+"' ").list();
	}
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<AttendanceModel> getStudentAttendanceList(int student_id) {
		// TODO Auto-generated method stub
		return (List<AttendanceModel>) sessionFactory.getCurrentSession()
				.createCriteria(AttendanceModel.class)
				.add(Restrictions.eq("student_id", student_id))
				.addOrder(Order.desc("student_id")).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DriverAttendanceModel> getDriverAttendanceListByMonth(int student_id,String month,String year) {
		// TODO Auto-generated method stub
		return (List<DriverAttendanceModel>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from DriverAttendanceModel where driver_id='" + student_id+ "' and MONTH(login_date) = '"+month+"' AND YEAR(login_date) = '"+year+"'").list();

	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AttendanceModel> getStudentAttendanceListByMonth(int student_id,String month,String year) {
		// TODO Auto-generated method stub
		return (List<AttendanceModel>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from AttendanceModel where student_id='" + student_id+ "' and MONTH(login_date) = '"+month+"' AND YEAR(login_date) = '"+year+"'").list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<AttendanceModel> getStudentAttendanceListByTwoDate(int student_id,String start_date,String end_date) {
		// TODO Auto-generated method stub
		return (List<AttendanceModel>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from AttendanceModel where student_id='" + student_id+ "' and login_date BETWEEN '"+start_date+"' AND '"+end_date+"' ").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DriverAttendanceModel> getDriverAttendanceListByTwoDate(int student_id,String start_date,String end_date){
		// TODO Auto-generated method stub
		return (List<DriverAttendanceModel>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from DriverAttendanceModel where driver_id='" + student_id+ "' and login_date BETWEEN '"+start_date+"' AND '"+end_date+"' ").list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public AttendanceModel getTodayStudentAttendance(int student_id) {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		 
		return (AttendanceModel) sessionFactory.getCurrentSession()
				.createCriteria(AttendanceModel.class)
				.add(Restrictions.eq("student_id", student_id))
				.add(Restrictions.eq("login_date", dateFormat.format(date)))
				.addOrder(Order.desc("student_id")).uniqueResult();
	}
	@SuppressWarnings("unchecked")
	@Override
	public DriverAttendanceModel getTodayDriverAttendance(int driver_id) {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return (DriverAttendanceModel) sessionFactory.getCurrentSession()
				.createCriteria(DriverAttendanceModel.class)
				.add(Restrictions.eq("driver_id", driver_id))
				.add(Restrictions.eq("login_date", dateFormat.format(date)))
				.addOrder(Order.desc("driver_id")).uniqueResult();
	}
	

	@Override
	public void addHoliday(HolidayModel m) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(m);
	}

	@Override
	public Integer getAllVehicleCount() {
		// TODO Auto-generated method stub
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				VehicleModel.class);
		crit.setProjection(Projections.rowCount());
		return (Integer) crit.uniqueResult();
	}

	@Override
	public LoginModel getMainSchoolAdmin(int school_id) {
		// TODO Auto-generated method stub
		return (LoginModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from LoginModel where school_id='" + school_id
								+ "' and main_school_admin=1").uniqueResult();
	}

	@Override
	public void editschooladmin(int schooadmin, LoginModel login) {
		// TODO Auto-generated method stub

		if (login.getUser_pass() == "") {
			System.out.println("Password is not");
			sessionFactory
					.getCurrentSession()
					.createQuery(
							"UPDATE LoginModel set user_email='"
									+ login.getUser_email() + "',first_name='"
									+ login.getFirst_name() + "',last_name='"
									+ login.getLast_name() + "',permission='"
									+ login.getPermission()
									+ "' where user_id='" + schooadmin + "'")
					.executeUpdate();
		} else {

			sessionFactory
					.getCurrentSession()
					.createQuery(
							"UPDATE LoginModel set user_email='"
									+ login.getUser_email() + "',first_name='"
									+ login.getFirst_name() + "',last_name='"
									+ login.getLast_name() + "',permission='"
									+ login.getPermission() + "',user_pass='"
									+ login.getUser_pass()
									+ "' where user_id='" + schooadmin + "'")
					.executeUpdate();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginModel> listSuperAdmin() {
		// TODO Auto-generated method stub
		return (List<LoginModel>) sessionFactory.getCurrentSession()
				.createCriteria(LoginModel.class)
				.add(Restrictions.eq("user_role", 1))
				.add(Restrictions.eq("main_school_admin", 0)).list();

	}

	@Override
	public HolidayModel getHolidayById(int holiday_id) {
		// TODO Auto-generated method stub
		return (HolidayModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from HolidayModel where h_id='" + holiday_id + "'")
				.uniqueResult();
	}

	@Override
	public void editHolidayById(int h_id, HolidayModel holiday) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE HolidayModel set holiday_name='"
								+ holiday.getHoliday_name()
								+ "',holiday_date='"
								+ holiday.getHoliday_date()
								+ "',holiday_enddate='"
								+ holiday.getHoliday_enddate()
								+ "' where h_id='" + h_id + "'")
				.executeUpdate();
	}

	@Override
	public void deleteHoliday(int h_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM  HolidayModel where h_id='" + h_id + "'")
				.executeUpdate();
	}

	@Override
	public void editschooladminBySchool(int school_id, LoginModel login) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE LoginModel set main_school_admin=0 where school_id='"
								+ school_id + "'").executeUpdate();
	}

	@Override
	public void updateMainSchoolAdmin(int user_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE LoginModel set main_school_admin=1 where user_id='"
								+ user_id + "'").executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchoolMessage> getAllMessage(int user_id, int school_admin) {
		// TODO Auto-generated method stub
		return (List<SchoolMessage>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from SchoolMessage where (reciever_id='" + user_id
								+ "' and sender_id='" + school_admin
								+ "') or (reciever_id='" + school_admin
								+ "' and sender_id='" + user_id + "')").list();

	}

	@Override
	public void sendMessageById(SchoolMessage msg) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(msg);
	}

	@Override
	public void sendMessageToParent(ParentMessage msg) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(msg);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginModel> adminList() {
		// TODO Auto-generated method stub
		return (List<LoginModel>) sessionFactory.getCurrentSession()
				.createCriteria(LoginModel.class)
				.add(Restrictions.eq("user_role", 1)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginModel> adminListLatest() {
		// TODO Auto-generated method stub
		return (List<LoginModel>) sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT DISTINCT(u.`user_id`),u.`user_name`,u.`user_email`,u.`user_pass`,u.`user_role`,u.`school_id`,u.`contact_number`,u.`first_name`,u.`last_name`,u.`p_status`,u.`permission`,u.`main_school_admin`,u.`device_token`,u.`middle_name`,u.`family_name`,u.`mobile_number`,u.`radius`,u.`lang`,u.`noti_on`,u.`chat_on`,u.`checked_in_on`,u.`checked_out_on`,u.`speed_on`,u.`max_speed`,u.`wrong_route_on`,u.`sms_checked_in_on`,u.`sms_checked_out_on`,u.`sms_speed_on`,u.`sms_max_speed`,u.`sms_wrong_route_on`,u.`instant_message`,u.`morning_before`,u.`evening_before`,u.`sms_instant_message`,u.`sms_morning_before`,u.`sms_evening_before`,u.`device_id`,u.`sound_setting`,u.`chat_sound` FROM tbl_users as u LEFT JOIN ( SELECT * FROM tbl_school_msg   ORDER BY time desc ) m ON u.user_id = m.sender_id WHERE u.user_role=1 ORDER by m.time DESC")
				.addEntity(LoginModel.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginModel> parentListLatest(int school_id) {
		// TODO Auto-generated method stub
		return (List<LoginModel>) sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT DISTINCT(u.`user_id`),u.`user_name`,u.`user_email`,u.`user_pass`,u.`user_role`,u.`school_id`,u.`contact_number`,u.`first_name`,u.`last_name`,u.`p_status`,u.`permission`,u.`main_school_admin`,u.`device_token`,u.`middle_name`,u.`family_name`,u.`mobile_number`,u.`radius` ,u.`lang`,u.`noti_on`,u.`chat_on`,u.`checked_in_on`,u.`checked_out_on`,u.`speed_on`,u.`max_speed`,u.`wrong_route_on`,u.`sms_checked_in_on`,u.`sms_checked_out_on`,u.`sms_speed_on`,u.`sms_max_speed`,u.`sms_wrong_route_on`,u.`instant_message`,u.`morning_before`,u.`evening_before`,u.`sms_instant_message`,u.`sms_morning_before`,u.`sms_evening_before`,u.`device_id`,u.`sound_setting`,u.`chat_sound` FROM tbl_users as u LEFT JOIN ( SELECT * FROM tbl_chatting   ORDER BY time desc ) m ON u.user_id = m.sender_id WHERE u.user_role=3 and u.school_id='"
								+ school_id + "' ORDER by m.time DESC")
				.addEntity(LoginModel.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchoolMessage> getAllMessageCount(int user_id, int logged_user) {
		// TODO Auto-generated method stub
		return (List<SchoolMessage>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from SchoolMessage where reciever_id='" + user_id
								+ "' and sender_id='" + logged_user
								+ "' and status=0").list();
	}

	@Override
	public void updateNotification(int reciever_id, int sender_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE SchoolMessage set status=1 where reciever_id='"
								+ reciever_id + "' and sender_id='" + sender_id
								+ "'").executeUpdate();
	}
	@Override
	public int updateNotificationCount(int reciever_id, int sender_id) {
		// TODO Auto-generated method stub
	int i= 	sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE SchoolMessage set status=1 where reciever_id='"
								+ reciever_id + "' and sender_id='" + sender_id
								+ "' and status=0").executeUpdate();
	return i;
	}
	
	@Override
	public void updateParentNotification(int reciever_id, int sender_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE ParentMessage set status=1 where reciever_id='"
								+ reciever_id + "' and sender_id='" + sender_id
								+ "'").executeUpdate();
	}
	@Override
	public int updateParentNotificationCount(int reciever_id, int sender_id) {
		// TODO Auto-generated method stub
		int i=sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE ParentMessage set status=1 where reciever_id='"
								+ reciever_id + "' and sender_id='" + sender_id
								+ "' and status=0").executeUpdate();
		
		return i;
	}
	
	

	@Override
	public void updatePageContent(PageContentModel page, int page_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE PageContentModel set p_name='"
								+ page.getP_name() + "',heading1='"
								+ page.getHeading1() + "', heading2='"
								+ page.getHeading2() + "',heading3='"
								+ page.getHeading3() + "',description1='"
								+ page.getDescription1() + "',description2='"
								+ page.getDescription2() + "',heading4='"
								+ page.getHeading4() + "',heading5='"
								+ page.getHeading5() + "',description4='"
								+ page.getDescription4() +"',description5='"
								+ page.getDescription5()+ "',description3='"
										+ page.getDescription3()+"' where p_id='"+page_id+"'").executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParentMessage> getParentAllMessageCount(int user_id,
			int logged_user) {
		// TODO Auto-generated method stub
		return (List<ParentMessage>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from SchoolMessage where reciever_id='" + user_id
								+ "' and sender_id='" + logged_user
								+ "' and status=0").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParentMessage> getParentAllMessage(int user_id, int school_admin) {
		// TODO Auto-generated method stub
		return (List<ParentMessage>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from ParentMessage where (reciever_id='" + user_id
								+ "' and sender_id='" + school_admin
								+ "') or (reciever_id='" + school_admin
								+ "' and sender_id='" + user_id + "')").list();
	}

	@Override
	public DriverModel checkDriverUserName(String username) {
		// TODO Auto-generated method stub
		return (DriverModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from DriverModel where username='" + username + "'")
				.uniqueResult();
	}

	@Override
	public DriverModel checkDriverContact(String contact) {
		// TODO Auto-generated method stub
		return (DriverModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from DriverModel where contact_number='" + contact
								+ "'").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DriverMessage> getDriverAllMessageCount(int user_id,
			int logged_user) {
		// TODO Auto-generated method stub
		return (List<DriverMessage>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from DriverMessage where reciever_id='" + user_id
								+ "' and sender_id='" + logged_user
								+ "' and status=0").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DriverMessage> getDriverAllMessage(int user_id, int school_admin) {
		// TODO Auto-generated method stub
		return (List<DriverMessage>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from DriverMessage where (reciever_id='" + user_id
								+ "' and sender_id='" + school_admin
								+ "') or (reciever_id='" + school_admin
								+ "' and sender_id='" + user_id + "')").list();
	}

	@Override
	public void updateDriverNotification(int reciever_id, int sender_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE DriverMessage set status=1 where reciever_id='"
								+ reciever_id + "' and sender_id='" + sender_id
								+ "'").executeUpdate();
	}
	@Override
	public int updateDriverNotificationCount(int reciever_id, int sender_id) {
		// TODO Auto-generated method stub
		int i=sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE DriverMessage set status=1 where reciever_id='"
								+ reciever_id + "' and sender_id='" + sender_id
								+ "' and status=0").executeUpdate();
		return i;
	}
	

		
	@Override
	public void sendMessageToDriver(DriverMessage msg) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(msg);
	}

	@Override
	public CountryModel getCountryById(int c_id) {
		// TODO Auto-generated method stub
		return (CountryModel) sessionFactory.getCurrentSession()
				.createQuery("from CountryModel where c_id='" + c_id + "'")
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VehicleModel> vehicleNotification(int school_id) {
		// TODO Auto-generated method stub
		int number = 10;
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 10);
		dt = c.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date cur_date = new Date();
		String current_date = dateFormat.format(cur_date);
		String next_date = dateFormat.format(dt);

		return (List<VehicleModel>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM VehicleModel where insurance_end_date BETWEEN '"
								+ current_date + "' and '" + next_date + "' ")
				.list();

	}

	@Override
	public void removeInsuranceCardCopy(int vechile_id,
			String insurance_card_copy) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"update VehicleModel SET insurance_card_copy = REPLACE(insurance_card_copy,'"
								+ insurance_card_copy + ",','')")
				.executeUpdate();
	}

	@Override
	public VehicleModel getVehicleCheck(String vechile_name) {
		// TODO Auto-generated method stub
		return (VehicleModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from VehicleModel where bus_number='" + vechile_name
								+ "'").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchoolClassesModel> getAllSchoolClasses(int school_id) {
		// TODO Auto-generated method stub
		return (List<SchoolClassesModel>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM SchoolClassesModel where school_id='" + school_id
								+ "' ").list();
	}

	@Override
	public void addSchoolClass(SchoolClassesModel cls) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(cls);

	}

	@Override
	public void deleteClass(int class_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM  SchoolClassesModel where class_id='"
								+ class_id + "'").executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DriverAttendanceModel> getDriverAttendanceList(int driver_id) {
		// TODO Auto-generated method stub
		return (List<DriverAttendanceModel>) sessionFactory.getCurrentSession()
				.createCriteria(DriverAttendanceModel.class)
				.add(Restrictions.eq("driver_id", driver_id))
				.addOrder(Order.desc("driver_id")).list();
	}

	@Override
	public DriverModel checkAssigedRoute(int route_id) {
		// TODO Auto-generated method stub
		DriverModel m = (DriverModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from DriverModel where route_id='" + route_id + "'")
				.uniqueResult();
		return m;
	}

	@Override
	public CountryModel countryCheck(String country_name) {
		// TODO Auto-generated method stub
		return (CountryModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from CountryModel where c_name='" + country_name + "'")
				.uniqueResult();
	}
	@Override
	public relationshipModel checkRelationship(String r_title) {
		// TODO Auto-generated method stub
		return (relationshipModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from relationshipModel where r_title='" + r_title + "'")
				.uniqueResult();
	}
	@Override
	public relationshipModel getRelationshipById(int r_id){
		// TODO Auto-generated method stub
		return (relationshipModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from relationshipModel where r_id='" + r_id + "'")
				.uniqueResult();
	}
	
	
	
	@Override
	public CountryModel countryCodeCheck(String country_code) {
		// TODO Auto-generated method stub
		return (CountryModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from CountryModel where c_code='" + country_code + "'")
				.uniqueResult();
	}

	@Override
	public void addCountry(CountryModel c) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(c);
	}
	@Override
	public void addRelationship(relationshipModel r){
		sessionFactory.getCurrentSession().saveOrUpdate(r);
	}

	@Override
	public void deleteCountry(int c_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM  CountryModel where c_id='" + c_id + "'")
				.executeUpdate();
	}
	@Override
	public void deleteRelationship(int r_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM  relationshipModel where r_id='" + r_id + "'")
				.executeUpdate();
	}
	
	@Override
	public void editCountryById(int c_id, CountryModel c) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE CountryModel set c_name='" + c.getC_name()
								+ "',c_code='" + c.getC_code()
								+ "' where c_id='" + c_id + "'")
				.executeUpdate();
	}
	
	public void editRelationById(int r_id, relationshipModel r){
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE relationshipModel set r_title='" + r.getR_title()+ "' where r_id='" + r_id + "'")
				.executeUpdate();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<CityModel> listCities() {
		// TODO Auto-generated method stub
		return (List<CityModel>) sessionFactory.getCurrentSession()
				.createCriteria(CityModel.class).list();
	}

	@Override
	public CityModel cityCheck(String city_name, int country_id) {
		// TODO Auto-generated method stub
		return (CityModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from CityModel where city_name='" + city_name
								+ "' and country_id='" + country_id + "'")
				.uniqueResult();
	}

	@Override
	public void addCity(CityModel c) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(c);
	}

	@Override
	public CityModel getCityById(int c_id) {
		// TODO Auto-generated method stub
		return (CityModel) sessionFactory.getCurrentSession()
				.createQuery("from CityModel where city_id='" + c_id + "'")
				.uniqueResult();
	}

	@Override
	public void editCityById(int c_id, CityModel c) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE CityModel set city_name='" + c.getCity_name()
								+ "',country_id='" + c.getCountry_id()
								+ "' where city_id='" + c_id + "'")
				.executeUpdate();
	}

	@Override
	public void deleteCity(int c_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM  CityModel where city_id='" + c_id + "'")
				.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VehicleDocModel> getVehicleDocument(int vehicle_id) {
		// TODO Auto-generated method stub
		return (List<VehicleDocModel>) sessionFactory.getCurrentSession()
				.createCriteria(VehicleDocModel.class)
				.add(Restrictions.eq("noti_type",0))
				.add(Restrictions.eq("v_id", vehicle_id)).list();
	}

	@Override
	public void deleteVehicleDocument(int v_doc_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM  VehicleDocModel where v_doc_id='"
								+ v_doc_id + "'").executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DriverDocModel> getDriverDocument(int driver_id) {
		// TODO Auto-generated method stub
		return (List<DriverDocModel>) sessionFactory.getCurrentSession()
				.createCriteria(DriverDocModel.class)
				.add(Restrictions.eq("noti_type",1))
				.add(Restrictions.eq("v_id", driver_id)).list();
	}

	@Override
	public void deleteDriverDocument(int v_doc_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM  DriverDocModel where v_doc_id='"
								+ v_doc_id + "'").executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getVehicleNotification(int school_id) {
		// TODO Auto-generated method stub

		return (List<Object[]>) sessionFactory
				.getCurrentSession()
				// .createQuery(
				// "SELECT FROM vehicle_doc i where insurance_document_expiry BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL i.remind_day DAY)")
				.createSQLQuery(
						"SELECT * FROM driver_doc i where school_id='"
								+ school_id
								+ "' and insurance_document_expiry BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL i.remind_day DAY) order by insurance_document_expiry asc")
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getVehicleNotificationCount(int school_id) {
		// TODO Auto-generated method stub

		return (List<Object[]>) sessionFactory
				.getCurrentSession()
				// .createQuery(
				// "SELECT FROM vehicle_doc i where insurance_document_expiry BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL i.remind_day DAY)")
				.createSQLQuery(
						"SELECT * FROM driver_doc i where school_id='"
								+ school_id
								+ "' and status=0 and insurance_document_expiry BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL i.remind_day DAY) order by insurance_document_expiry asc")
				.list();
	}

	@Override
	public void updateHeadNotification(int v_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE DriverDocModel set status=1 where v_doc_id='"
								+ v_id + "'").executeUpdate();
	}

	@Override
	public NationlityModel checkNationlity(String name) {
		// TODO Auto-generated method stub
		return (NationlityModel) sessionFactory.getCurrentSession()
				.createQuery("from NationlityModel where name='" + name + "'")
				.uniqueResult();

	}

	@Override
	public void addNationlity(NationlityModel nationlity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(nationlity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NationlityModel> getAllNationlity() {
		// TODO Auto-generated method stub
		return (List<NationlityModel>) sessionFactory.getCurrentSession()
				.createCriteria(NationlityModel.class)
				.addOrder(Order.asc("name")).list();

	}

	@Override
	public void deleteNationality(int c_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM  NationlityModel where national_id='"
								+ c_id + "'").executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DriverMessage> getMessageNotification(int user_id) {
		// TODO Auto-generated method stub
		return (List<DriverMessage>) sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"(SELECT * FROM `tbl_school_msg` WHERE `reciever_id` ='"
								+ user_id
								+ "' and status=0) UNION (SELECT * FROM `tbl_chatting` WHERE `reciever_id` ='"
								+ user_id
								+ "' and status=0) UNION (SELECT * FROM `tbl_driver_msg` WHERE `reciever_id` ='"
								+ user_id
								+ "' and status=0) ORDER BY `time` DESC")
				.addEntity(DriverMessage.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DriverMessage> getMessageAdminNotification(int user_id) {
		// TODO Auto-generated method stub
		return (List<DriverMessage>) sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"(SELECT * FROM `tbl_school_msg` WHERE `reciever_id` ='"
								+ user_id
								+ "' and status=0) ORDER BY `time` DESC")
				.addEntity(DriverMessage.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DriverMessage> getMessageParentNotification(int user_id) {
		// TODO Auto-generated method stub
		return (List<DriverMessage>) sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"(SELECT * FROM `tbl_chatting` WHERE `reciever_id` ='"
								+ user_id
								+ "' and status=0) ORDER BY `time` DESC")
				.addEntity(DriverMessage.class).list();
	}

	@Override
	public void schoolDeletedHoliday(HolidayDeletedModel hdm) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(hdm);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HolidayDeletedModel> getAllDeletedHoliday(int school_id) {
		// TODO Auto-generated method stub
		return (List<HolidayDeletedModel>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from HolidayDeletedModel where school_id='"
								+ school_id + "'").list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<HolidayDeletedModel> getAllDeletedHolidayByMonth(int school_id,String month,String year) {
		// TODO Auto-generated method stub
		return (List<HolidayDeletedModel>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from HolidayDeletedModel where school_id='"
								+ school_id + "'").list();
	}
	
	

	@Override
	public DriverModel getDriverByRouteId(int route_id) {
		// TODO Auto-generated method stub
		return (DriverModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM DriverModel where route_id='" + route_id + "'")
				.uniqueResult();
	}

	@Override
	public TypingModel checkTypingUser(int user_id, int reciever_id) {
		// TODO Auto-generated method stub
		return (TypingModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM TypingModel where typing_user='" + user_id
								+ "' and typing_reciever='" + reciever_id + "'")
				.uniqueResult();
	}

	@Override
	public void insertTypingUser(TypingModel type) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(type);
	}

	@Override
	public void updateTypingUserStatus(TypingModel type, int type_id,
			int typing_user) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE TypingModel set status='" + type.getStatus()
								+ "' where typing_reciever='" + type_id
								+ "' and typing_user='" + typing_user + "'")
				.executeUpdate();
	}

	@Override
	public RouteLatLngModel getStudentRouteLatLng(int student_id) {
		// TODO Auto-generated method stub
		return (RouteLatLngModel) sessionFactory.getCurrentSession()
				.createQuery("from RouteLatLngModel where student_id='" + student_id + "'")
				.uniqueResult();
	}

	@Override
	public PageContentModel getAboutUs(int page_id) {
		// TODO Auto-generated method stub
		return (PageContentModel) sessionFactory.getCurrentSession()
		.createQuery("from PageContentModel where p_id='" + page_id + "'")
		.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SliderModel> getAllSlider() {
		// TODO Auto-generated method stub
		return (List<SliderModel>) sessionFactory.getCurrentSession().createQuery("From SliderModel").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryContactModel> getAllContactCategory() {
		// TODO Auto-generated method stub
		return (List<CategoryContactModel>) sessionFactory.getCurrentSession().createQuery("From CategoryContactModel").list();
	}
 	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FeaturesModel> getAllFeatures() {
		// TODO Auto-generated method stub
		return (List<FeaturesModel>) sessionFactory.getCurrentSession().createQuery("From FeaturesModel").list();
	}
	

	@Override
	public void addSlider(SliderModel slider_model) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(slider_model);
	}
	@Override
	public void addCategory(CategoryContactModel category_model) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(category_model);
	}
	
	
	@Override
	public void addFeature(FeaturesModel feature_model) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(feature_model);
	}
	@Override
	public SliderModel getSliderById(int slider_id) {
		// TODO Auto-generated method stub
		return (SliderModel)sessionFactory.getCurrentSession().createQuery("From SliderModel where slider_id='"+slider_id+"'").uniqueResult();
	}
	
	@Override
	public CategoryContactModel getCategoryById(int c_cat_id) {
		// TODO Auto-generated method stub
		return (CategoryContactModel)sessionFactory.getCurrentSession().createQuery("From CategoryContactModel where c_cat_id='"+c_cat_id+"'").uniqueResult();
	}
	
	
	
	@Override
	public void updateSliderById(int slider_id, SliderModel s_Model) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().createQuery("update SliderModel set slider_type="+s_Model.getSlider_type()+",slider_image='"+s_Model.getSlider_image()+"' where slider_id="+slider_id+"").executeUpdate();
	}
	
	@Override
	public void updateCategoryById(int c_cat_id,CategoryContactModel category_model) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().createQuery("update CategoryContactModel set category_en='"+category_model.getCategory_en()+"' where c_cat_id="+c_cat_id+"").executeUpdate();
	}
	
	
	
	
	@Override
	public void deleteSlider(int slider_id) {
		// TODO Auto-generated method stub
		sessionFactory
		.getCurrentSession()
		.createQuery(
				"DELETE FROM  SliderModel where slider_id='" + slider_id
						+ "'")
		.executeUpdate();
	}
	
	@Override
	public void deleteCategory(int c_cat_id) {
		// TODO Auto-generated method stub
		sessionFactory
		.getCurrentSession()
		.createQuery(
				"DELETE FROM  CategoryContactModel where c_cat_id='" + c_cat_id
						+ "'")
		.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PageModel> getAllPage(int p_type) {
		// TODO Auto-generated method stub
		return (List<PageModel>) sessionFactory.getCurrentSession()
				.createQuery("From PageModel where p_type="+p_type)
				.list();
	}

	@Override
	public PageModel getPageById(int page_id) {
		// TODO Auto-generated method stub
		return (PageModel)sessionFactory.getCurrentSession().createQuery("From PageModel where page_id='"+page_id+"'").uniqueResult();
	}

	@Override
	public void updatePageById(int page_id, PageModel s_Model) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().createQuery("update PageModel set page_name='"+s_Model.getPage_name()+"',page_title='"+s_Model.getPage_title()+"',page_desc='"+s_Model.getPage_desc()+"' where page_id="+page_id+"").executeUpdate();
		 
	}
	
	@Override
	public FeaturesModel getFeatureById(int feature_id) {
		// TODO Auto-generated method stub
		return (FeaturesModel)sessionFactory.getCurrentSession()
				.createQuery("From FeaturesModel where features_id='"+feature_id+"'")
				.uniqueResult();
	}

	@Override
	public void updateFeatureById(int feature_id,FeaturesModel s_Model) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession()
		.createQuery("update FeaturesModel set "
				+ "title='"+s_Model.getTitle()
				+"',content='"+s_Model.getContent()
				+"',image_path='"+s_Model.getImage_path()
				+"',category_type='"+s_Model.getCategory_type()
				+"' where  features_id="+feature_id+"").executeUpdate();

	}

	@Override
	public void deleteFeature(int feature_id) {
		// TODO Auto-generated method stub
		sessionFactory
		.getCurrentSession()
		.createQuery(
				"DELETE FROM  FeaturesModel where features_id='" + feature_id
						+ "'")
		.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaqModel> getAllFaq() {
		// TODO Auto-generated method stub
		return (List<FaqModel>) sessionFactory.getCurrentSession().createQuery("From FaqModel").list();

	}

	@Override
	public void addFaq(FaqModel faq_model) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(faq_model);
	}

	@Override
	public FaqModel getFaqById(int faq_id) {
		// TODO Auto-generated method stub
		return (FaqModel)sessionFactory.getCurrentSession()
				.createQuery("From FaqModel where faq_id='"+faq_id+"'")
				.uniqueResult();
	}

	@Override
	public void updateFaqById(int faq_id, FaqModel s_model) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession()
		.createQuery("update FaqModel set "
				+ "category='"+s_model.getCategory()
				+"',question='"+s_model.getQuestion()
				+"',answer='"+s_model.getAnswer()
				+"' where  faq_id="+faq_id+"").executeUpdate();

	}

	@Override
	public void deleteFaq(int faq_id) {
		// TODO Auto-generated method stub
		sessionFactory
		.getCurrentSession()
		.createQuery(
				"DELETE FROM  FaqModel where faq_id='" + faq_id
						+ "'")
		.executeUpdate();
	}

	@Override
	public void addContactDetails(ContactDetailModel c_model) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(c_model);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubscriberModel> getAllSubscribers() {
		// TODO Auto-generated method stub
		return (List<SubscriberModel>) sessionFactory.getCurrentSession().createQuery("From SubscriberModel").list();
	}

	@Override
	public void deleteSubscriber(int faq_id) {
		// TODO Auto-generated method stub
		sessionFactory
		.getCurrentSession()
		.createQuery(
				"DELETE FROM  SubscriberModel where sub_id='" + faq_id
						+ "'")
		.executeUpdate();
	}

	@Override
	public AdminSetupModel adminSetup() {
		// TODO Auto-generated method stub
		return (AdminSetupModel)sessionFactory.getCurrentSession()
				.createQuery("From AdminSetupModel where setup_id=1")
				.uniqueResult();
	}
	
	@Override
	public void updateAdminSetup(AdminSetupModel s_model) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession()
		.createQuery("update AdminSetupModel set "
				+ "email_id='"+s_model.getEmail_id()
				+"',password='"+s_model.getPassword()
				+"' where  setup_id=1").executeUpdate();

	}

	@Override
	public EmailTemplateModel getEmailTemaplteById(int temp_id) {
		// TODO Auto-generated method stub
		return (EmailTemplateModel)sessionFactory.getCurrentSession()
				.createQuery("From EmailTemplateModel where temp_id='"+temp_id+"'")
				.uniqueResult();
	}
	
	@Override
	public void updateEmailTemplate(EmailTemplateModel s_model,int temp_id) {
		// TODO Auto-generated method stub
		sessionFactory
		.getCurrentSession()
		.createQuery(
				"UPDATE EmailTemplateModel set description='"
						+ s_model.getDescription()+ "' where temp_id='" + temp_id + "'")
		.executeUpdate();
	}

	@Override
	public SubscriberModel getSubscriberById(int s_id) {
		// TODO Auto-generated method stub
		return (SubscriberModel)sessionFactory.getCurrentSession()
				.createQuery("From SubscriberModel where sub_id='"+s_id+"'")
				.uniqueResult();
	}

	@Override
	public ContactContentModel getContactContent(int content_id) {
		// TODO Auto-generated method stub
		return (ContactContentModel)sessionFactory.getCurrentSession().createQuery("From ContactContentModel where c_id="+content_id+"").uniqueResult();
	}

	@Override
	public void updateContactContent(ContactContentModel page, int page_id) {
		// TODO Auto-generated method stub
		sessionFactory
		.getCurrentSession()
		.createQuery(
				"UPDATE ContactContentModel set address='"
						+ page.getAddress()+ "',email='"+page.getEmail()+"',phone_number='"+page.getPhone_number()+"',website='"+page.getWebsite()+"',location='"+page.getLocation()+"' where c_id='" + page_id + "'")
		.executeUpdate();
	}
	
}
