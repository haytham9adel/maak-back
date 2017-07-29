package com.trackingbus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.trackingbus.dao.SchoolDao;
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

@Service("schoolService")  
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)  
public class SchoolServiceImpl implements SchoolService {
	@Autowired
	private SchoolDao schooldao;
   
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long addSchool(SchoolModel s) {
		long id=schooldao.addSchool(s);
		return id;
	}


	@Override
	public SchoolModel checkSchoolName(String userName) {
		SchoolModel schoolmodel = schooldao.checkSchoolName(userName);
		return schoolmodel;
	}


	@Override
	public List<CountryModel> listCountry() {
		return schooldao.listCountry();
	}


	@Override
	public List<SchoolModel> listSchools() {
		return schooldao.listSchools();
	}
	
	@Override
	public List<StudentModel> listStudent(int school_id) {
		return schooldao.listStudent(school_id);
	}
	
	@Override
	public List<StudentModel> getStudentsByRouteId(int route_id) {
		return schooldao.getStudentsByRouteId(route_id);
	}
	
	
	@Override
	public List<relationshipModel> listRelationship() {
		return schooldao.listRelationship();
	}
	

	@Override
	public void addParent(LoginModel s) {
		schooldao.addParent(s);
		
	}


	@Override
	public void deleteParent(int parent_id) {
		schooldao.deleteParent(parent_id);
		
	}


	@Override
	public void deleteStudent(int student_id) {
		schooldao.deleteStudent(student_id);
		
	}


	@Override
	public LoginModel getParentById(int parent_id) {
		return schooldao.getParentById(parent_id);
	}
	

	@Override
	public void editParentById(int parent_id, LoginModel login) {
		// TODO Auto-generated method stub
		schooldao.editParentById(parent_id, login);
	}
	
	@Override
	public void saveParentSetting(int parent_id, LoginModel login) {
		// TODO Auto-generated method stub
		schooldao.saveParentSetting(parent_id, login);
	}
	@Override
	public void saveDriverSetting(int driver_id, DriverModel driver_details) {
		// TODO Auto-generated method stub
		schooldao.saveDriverSetting(driver_id, driver_details);
	}

	

	@Override
	public void editStudentById(int student_id, StudentModel student) {
		// TODO Auto-generated method stub
		schooldao.editStudentById(student_id, student);
	}


	@Override
	public SchoolModel getSchoolById(int school_id) {
		// TODO Auto-generated method stub
		return schooldao.getSchoolById(school_id);
	}


	@Override
	public Integer getStudentCount(int school_id) {
		// TODO Auto-generated method stub
		return schooldao.getStudentCount(school_id);
	}


	@Override
	public LoginModel getUserById(int use_id) {
		// TODO Auto-generated method stub
		return schooldao.getUserById(use_id);
	}


	@Override
	public void editSchoolById(int school_id, SchoolModel school) {
		// TODO Auto-generated method stub
		schooldao.editSchoolById(school_id, school);
	}


	@Override
	public Integer getVehicleCount(int school_id) {
		// TODO Auto-generated method stub
		return schooldao.getVehicleCount(school_id);
	}


	@Override
	public DriverModel checkDriverName(String email) {
		// TODO Auto-generated method stub
		return schooldao.checkDriverName(email);
	}


	@Override
	public long addDriver(DriverModel d) {
		// TODO Auto-generated method stub
		return schooldao.addDriver(d);
	}


	@Override
	public List<DriverModel> listDriver(int school_id) {
		// TODO Auto-generated method stub
		return schooldao.listDriver(school_id);
	}
	@Override
	public List<DriverModel> listDriverLatest(int school_id) {
		// TODO Auto-generated method stub
		return schooldao.listDriverLatest(school_id);
	}


	@Override
	public void deleteDriver(int driver_id) {
		// TODO Auto-generated method stub
		schooldao.deleteDriver(driver_id);
	}


	@Override
	public DriverModel getDriverById(int driver_id) {
		// TODO Auto-generated method stub
		return schooldao.getDriverById(driver_id);
	}
	
	@Override
	public DriverModel getDriverByRouteId(int route_id) {
		// TODO Auto-generated method stub
		return schooldao.getDriverByRouteId(route_id);
	}


	@Override
	public void editDriverById(int driver_id, DriverModel driver) {
		// TODO Auto-generated method stub
		schooldao.editDriverById(driver_id, driver);
	}
	@Override
	public List<StudentModel> getAllStudent() {
		return schooldao.getAllStudent();
	}
	@Override
	public List<StudentModel> getAllStudentByRouteId(int route_id) {
		return schooldao.getAllStudentByRouteId(route_id);
	}
	

	@Override
	public List<LoginModel> getParentBySchool(int school_id) {
		// TODO Auto-generated method stub
		return schooldao.getParentBySchool(school_id);
	}


	@Override
	public List<DriverModel> allDrivers() {
		// TODO Auto-generated method stub
		return schooldao.allDrivers();
	}


	@Override
	public void editSchoolByAdmin(int school_id, SchoolModel school) {
		// TODO Auto-generated method stub
		schooldao.editSchoolByAdmin(school_id, school);
	}


	@Override
	public void deleteSchool(int school_id) {
		// TODO Auto-generated method stub
		schooldao.deleteSchool(school_id);
	}


	@Override
	public long addRoute(RouteModel route) {
		// TODO Auto-generated method stub
		long id=schooldao.addRoute(route);
		return id;
	}


	@Override
	public void addRouteLatLng(RouteLatLngModel route) {
		// TODO Auto-generated method stub
		schooldao.addRouteLatLng(route);
	}


	@Override
	public List<RouteModel> listRoute(int school_id) {
		// TODO Auto-generated method stub
		return schooldao.listRoute(school_id);
	}
	
	@Override
	public RouteModel getRouteById(int route_id) {
		// TODO Auto-generated method stub
		return schooldao.getRouteById(route_id);
	}


	@Override
	public List<RouteLatLngModel> listLatLng(int route_id) {
		// TODO Auto-generated method stub
		return schooldao.listLatLng(route_id);
	}
	
	@Override
	public List<VehicleModel> getVechileByDriverId(int driver_id){
		// TODO Auto-generated method stub
		return schooldao.getVechileByDriverId(driver_id);
	}
	
	


	@Override
	public void editRouteById(int route_id, RouteModel routemodel) {
		// TODO Auto-generated method stub
		schooldao.editRouteById(route_id, routemodel);	
	}
	@Override
	public void saveGeoFancing(int route_id, RouteModel routemodel) {
		// TODO Auto-generated method stub
		schooldao.saveGeoFancing(route_id, routemodel);	
	}
	

	@Override
	public void deleteLatLng(int route_id) {
		// TODO Auto-generated method stub
		schooldao.deleteLatLng(route_id);
	}


	@Override
	public RouteLatLngModel checkStudentRouteId(int student_id, int school_id) {
		// TODO Auto-generated method stub
		RouteLatLngModel routelatlngmodel = schooldao.checkStudentRouteId(student_id,school_id);
		return routelatlngmodel;
	}


	@Override
	public void addAssignRoute(RouteLatLngModel s) {
		// TODO Auto-generated method stub
		schooldao.addAssignRoute(s);
	}


	@Override
	public void editStudentRoute(int student_id, RouteLatLngModel latlngmodel) {
		// TODO Auto-generated method stub
		schooldao.editStudentRoute(student_id, latlngmodel);
	}


	@Override
	public void deleteRoute(int route_id) {
		// TODO Auto-generated method stub
		schooldao.deleteRoute(route_id);
	}


	@Override
	public List<LoginModel> listSchoolAdmin(int school_id) {
		// TODO Auto-generated method stub
		return schooldao.listSchoolAdmin(school_id);
	}
	@Override
	public List<LoginModel> listSchoolAdminLatest(int school_id) {
		// TODO Auto-generated method stub
		return schooldao.listSchoolAdminLatest(school_id);
	}
	@Override
	public List<VehicleModel> vehicleNotification(int school_id) {
		// TODO Auto-generated method stub
		return schooldao.vehicleNotification(school_id);
	}

	@Override
	public void deleteSchoolAdmin(int admin_id) {
		// TODO Auto-generated method stub
		schooldao.deleteSchoolAdmin(admin_id);
	}
	@Override
	public void deleteSuperAdmin(int admin_id) {
		// TODO Auto-generated method stub
		schooldao.deleteSuperAdmin(admin_id);
	}
	
	@Override
	public void deleteSchoolAdminNew(int admin_id) {
		// TODO Auto-generated method stub
		schooldao.deleteSchoolAdminNew(admin_id);
	}
	

	@Override
	public List<LoginModel> getParentByPIdList(int p_status) {
		return schooldao.getParentByPIdList(p_status);
}


	@Override
	public List<RouteLatLngModel> getRouteLatLng(int route_id) {
		// TODO Auto-generated method stub
		return schooldao.getRouteLatLng(route_id);
	}


	@Override
	public void editRouteByStudentId(int student_id,
			RouteLatLngModel routelatlng) {
		// TODO Auto-generated method stub
		schooldao.editRouteByStudentId(student_id, routelatlng);
	}


	@Override
	public List<HolidayModel> getAllHoliday(int school_id) {
		// TODO Auto-generated method stub
		return schooldao.getAllHoliday(school_id);
	}
	@Override
	public List<HolidayModel> getAllHolidayByMonth(int school_id,String month,String year) {
		// TODO Auto-generated method stub
		return schooldao.getAllHolidayByMonth(school_id,month,year);
	}
	
	@Override
	public List<HolidayModel> getAllHolidayByTwoDate(int school_id,String start_date,String end_date) {
		// TODO Auto-generated method stub
		return schooldao.getAllHolidayByTwoDate(school_id,start_date,end_date);
	}


	@Override
	public List<AttendanceModel> getStudentAttendanceList(int student_id) {
		// TODO Auto-generated method stub
		return schooldao.getStudentAttendanceList(student_id);
	}
	@Override
	public List<AttendanceModel> getStudentAttendanceListByMonth(int student_id,String month,String year) {
		// TODO Auto-generated method stub
		return schooldao.getStudentAttendanceListByMonth(student_id,month,year);
	}
	@Override
	public List<DriverAttendanceModel> getDriverAttendanceListByMonth(int student_id,String month,String year) {
		// TODO Auto-generated method stub
		return schooldao.getDriverAttendanceListByMonth(student_id,month,year);
	}
	
	
	@Override
	public List<AttendanceModel> getStudentAttendanceListByTwoDate(int student_id,String start_date,String end_date) {
		// TODO Auto-generated method stub
		return schooldao.getStudentAttendanceListByTwoDate(student_id,start_date,end_date);
	}
	
	public List<DriverAttendanceModel> getDriverAttendanceListByTwoDate(int student_id,String start_date,String end_date)
	{
		return schooldao.getDriverAttendanceListByTwoDate(student_id,start_date,end_date);
	}
	
	
	@Override
	public AttendanceModel getTodayStudentAttendance(int student_id) {
		// TODO Auto-generated method stub
		return schooldao.getTodayStudentAttendance(student_id);
	}
	@Override
	public DriverAttendanceModel getTodayDriverAttendance(int driver_id) {
		// TODO Auto-generated method stub
		return schooldao.getTodayDriverAttendance(driver_id);
	}

	
	@Override
	public void addHoliday(HolidayModel s) {
		// TODO Auto-generated method stub
		schooldao.addHoliday(s);
	}


	@Override
	public Integer getAllVehicleCount() {
		// TODO Auto-generated method stub
		return schooldao.getAllVehicleCount();
	}


	@Override
	public LoginModel getMainSchoolAdmin(int school_id) {
		// TODO Auto-generated method stub
		return schooldao.getMainSchoolAdmin(school_id);
	}


	@Override
	public void editschooladmin(int schooadmin, LoginModel login) {
		// TODO Auto-generated method stub
		schooldao.editschooladmin(schooadmin, login);
	}


	@Override
	public List<LoginModel> listSuperAdmin() {
		// TODO Auto-generated method stub
		return schooldao.listSuperAdmin();
	}


	@Override
	public HolidayModel getHolidayById(int holiday_id) {
		// TODO Auto-generated method stub
		return schooldao.getHolidayById(holiday_id);
	}


	@Override
	public void editHolidayById(int h_id, HolidayModel holiday) {
		// TODO Auto-generated method stub
		schooldao.editHolidayById(h_id, holiday);
	}


	@Override
	public void deleteHoliday(int h_id) {
		// TODO Auto-generated method stub
		schooldao.deleteHoliday(h_id);
	}


	@Override
	public void editschooladminBySchool(int school_id, LoginModel login) {
		// TODO Auto-generated method stub
		schooldao.editschooladminBySchool(school_id, login);
	}
	@Override
	public void updateMainSchoolAdmin(int user_id) {
		// TODO Auto-generated method stub
		schooldao.updateMainSchoolAdmin(user_id);
	}


	@Override
	public List<SchoolMessage> getAllMessage(int user_id,int school_admin) {
		// TODO Auto-generated method stub
		return schooldao.getAllMessage(user_id,school_admin);
	}


	@Override
	public void sendMessageById(SchoolMessage msg) {
		// TODO Auto-generated method stub
		schooldao.sendMessageById(msg);
	}
	
	@Override
	public void sendMessageToParent(ParentMessage msg) {
		// TODO Auto-generated method stub
		schooldao.sendMessageToParent(msg);
	}
	
	@Override
	public void sendMessageToDriver(DriverMessage msg) {
		// TODO Auto-generated method stub
		schooldao.sendMessageToDriver(msg);
	}


	
	
	@Override
	public List<LoginModel> adminList() {
		// TODO Auto-generated method stub
		return schooldao.adminList();
	}
	@Override
	public List<LoginModel> adminListLatest() {
		// TODO Auto-generated method stub
		return schooldao.adminListLatest();
	}
	
	@Override
	public List<LoginModel> parentListLatest(int school_id) {
		// TODO Auto-generated method stub
		return schooldao.parentListLatest(school_id);
	}

	@Override
	public List<SchoolMessage> getAllMessageCount(int user_id, int logged_user) {
		// TODO Auto-generated method stub
		return schooldao.getAllMessageCount(user_id,logged_user);
	}


	@Override
	public void updateNotification(int reciever_id, int sender_id) {
		// TODO Auto-generated method stub
		schooldao.updateNotification(reciever_id,sender_id);
	}
	@Override
	public int updateNotificationCount(int reciever_id, int sender_id) {
		// TODO Auto-generated method stub
		 int i=schooldao.updateNotificationCount(reciever_id,sender_id);
		 return i;
	}
	
	
	
	@Override
	public void updateParentNotification(int reciever_id, int sender_id) {
		// TODO Auto-generated method stub
		schooldao.updateParentNotification(reciever_id,sender_id);
	}
	@Override
	public int updateParentNotificationCount(int reciever_id, int sender_id) {
		// TODO Auto-generated method stub
		return 	schooldao.updateParentNotificationCount(reciever_id,sender_id);
	}
	
	
	@Override
	public void removeInsuranceCardCopy(int vechile_id,String insurance_card_copy) {
		// TODO Auto-generated method stub
		schooldao.removeInsuranceCardCopy(vechile_id,insurance_card_copy);
	}
	
	
	@Override
	public void updatePageContent(PageContentModel page, int page_id) {
		// TODO Auto-generated method stub
		schooldao.updatePageContent(page,page_id);
	}


	@Override
	public List<ParentMessage> getParentAllMessageCount(int user_id,
			int logged_user) {
		// TODO Auto-generated method stub
		return schooldao.getParentAllMessageCount(user_id,logged_user);
	}


	@Override
	public List<ParentMessage> getParentAllMessage(int user_id, int school_id) {
		// TODO Auto-generated method stub
		return schooldao.getParentAllMessage(user_id,school_id);
	}
	
	@Override
	public List<DriverMessage> getDriverAllMessage(int user_id, int school_id) {
		// TODO Auto-generated method stub
		return schooldao.getDriverAllMessage(user_id,school_id);
	}

	@Override
	public DriverModel checkDriverUserName(String username) {
		// TODO Auto-generated method stub
		return schooldao.checkDriverUserName(username);
	}


	@Override
	public DriverModel checkDriverContact(String contact) {
		// TODO Auto-generated method stub
		return schooldao.checkDriverContact(contact);
	}


	@Override
	public List<DriverMessage> getDriverAllMessageCount(int user_id,
			int logged_user) {
		// TODO Auto-generated method stub
		return schooldao.getDriverAllMessageCount(user_id,logged_user);
	}


	@Override
	public void updateDriverNotification(int reciever_id, int sender_id) {
		// TODO Auto-generated method stub
		schooldao.updateDriverNotification(reciever_id,sender_id);
	}

	@Override
	public int updateDriverNotificationCount(int reciever_id, int sender_id) {
		// TODO Auto-generated method stub
		return schooldao.updateDriverNotificationCount(reciever_id,sender_id);
	}
	
	

	@Override
	public CountryModel getCountryById(int c_id) {
		// TODO Auto-generated method stub
		return schooldao.getCountryById(c_id);
	}


	@Override
	public VehicleModel getVehicleCheck(String vechile_name) {
		// TODO Auto-generated method stub
		return schooldao.getVehicleCheck(vechile_name);
	}


	@Override
	public List<SchoolClassesModel> getAllSchoolClasses(int school_id) {
		// TODO Auto-generated method stub
		return schooldao.getAllSchoolClasses(school_id);
	}


	@Override
	public void addSchoolClass(SchoolClassesModel cls) {
		// TODO Auto-generated method stub
		schooldao.addSchoolClass(cls);
	}


	@Override
	public void deleteClass(int class_id) {
		// TODO Auto-generated method stub
		schooldao.deleteClass(class_id);
	}


	@Override
	public List<DriverAttendanceModel> getDriverAttendanceList(int driver_id) {
		// TODO Auto-generated method stub
		return schooldao.getDriverAttendanceList(driver_id);
	}


	@Override
	public DriverModel checkAssigedRoute(int route_id) {
		// TODO Auto-generated method stub
		DriverModel drivermodel = schooldao.checkAssigedRoute(route_id);
		return drivermodel;
	}


	@Override
	public CountryModel countryCheck(String country_name) {
		// TODO Auto-generated method stub
		return schooldao.countryCheck(country_name);
	}
	
	@Override
	public relationshipModel checkRelationship(String r_title){
		// TODO Auto-generated method stub
		return schooldao.checkRelationship(r_title);
	}

	@Override
	public relationshipModel getRelationshipById(int r_id){
		// TODO Auto-generated method stub
		return schooldao.getRelationshipById(r_id);
	}
	
	
	
	
	
	@Override
	public CountryModel countryCodeCheck(String country_code) {
		// TODO Auto-generated method stub
		return schooldao.countryCodeCheck(country_code);
	}


	@Override
	public void addCountry(CountryModel c) {
		// TODO Auto-generated method stub
		schooldao.addCountry(c);
	}
	
	@Override
	public void addRelationship(relationshipModel r) {
		// TODO Auto-generated method stub
		schooldao.addRelationship(r);
	}
	
	


	@Override
	public void deleteCountry(int c_id) {
		// TODO Delete Country  
		schooldao.deleteCountry(c_id);
	}
	@Override
	public void deleteRelationship(int r_id) {
		// TODO Delete Country  
		schooldao.deleteRelationship(r_id);
	}
	
	

	@Override
	public void editCountryById(int c_id, CountryModel c) {
		// TODO Auto-generated method stub
		schooldao.editCountryById(c_id, c);
	}
	@Override
	public void editRelationById(int r_id, relationshipModel r) {
		// TODO Auto-generated method stub
		schooldao.editRelationById(r_id, r);
	}
	

	@Override
	public List<CityModel> listCities() {
		// TODO Auto-generated method stub
		return schooldao.listCities();
	}


	@Override
	public CityModel cityCheck(String city_name, int country_id) {
		// TODO Auto-generated method stub
		return schooldao.cityCheck(city_name,country_id);
	}


	@Override
	public void addCity(CityModel c) {
		// TODO Auto-generated method stub
			schooldao.addCity(c);	
	}


	@Override
	public CityModel getCityById(int c_id) {
		// TODO Auto-generated method stub
		return schooldao.getCityById(c_id);
	}


	@Override
	public void editCityById(int c_id, CityModel c) {
		// TODO Auto-generated method stub
		schooldao.editCityById(c_id,c);
	}


	@Override
	public void deleteCity(int c_id) {
		// TODO Auto-generated method stub
		schooldao.deleteCity(c_id);
	}


	@Override
	public List<VehicleDocModel> getVehicleDocument(int vehicle_id) {
		// TODO Auto-generated method stub
		return schooldao.getVehicleDocument(vehicle_id);
	}


	@Override
	public void deleteVehicleDocument(int v_doc_id) {
		// TODO Auto-generated method stub
		schooldao.deleteVehicleDocument(v_doc_id);
	}


	@Override
	public List<DriverDocModel> getDriverDocument(int driver_id) {
		// TODO Auto-generated method stub
		return schooldao.getDriverDocument(driver_id);
	}


	@Override
	public void deleteDriverDocument(int v_doc_id) {
		// TODO Auto-generated method stub
		schooldao.deleteDriverDocument(v_doc_id);
	}


	@Override
	public List<Object[]> getVehicleNotification(int school_id) {
		// TODO Auto-generated method stub
		return schooldao.getVehicleNotification(school_id);
	}
	@Override
	public List<Object[]> getVehicleNotificationCount(int school_id) {
		// TODO Auto-generated method stub
		return schooldao.getVehicleNotificationCount(school_id);
	}


	@Override
	public void updateHeadNotification(int v_id) {
		// TODO Auto-generated method stub
		schooldao.updateHeadNotification(v_id);
	}


	@Override
	public NationlityModel checkNationlity(String name) {
		// TODO Auto-generated method stub
		return schooldao.checkNationlity(name);
	}


	@Override
	public void addNationlity(NationlityModel nationlity) {
		// TODO Auto-generated method stub
		 schooldao.addNationlity(nationlity);
	}


	@Override
	public List<NationlityModel> getAllNationlity() {
		// TODO Auto-generated method stub
		return schooldao.getAllNationlity();
	}


	@Override
	public void deleteNationlity(int national_id) {
		// TODO Auto-generated method stub
		schooldao.deleteNationality(national_id);
	}


	@Override
	public List<DriverMessage> getMessageNotification(int user_id) {
		// TODO Auto-generated method stub
		return schooldao.getMessageNotification(user_id);
	}


	@Override
	public List<DriverMessage> getMessageAdminNotification(int user_id) {
		// TODO Auto-generated method stub
		return schooldao.getMessageAdminNotification(user_id);
	}


	@Override
	public List<DriverMessage> getMessageParentNotification(int user_id) {
		// TODO Auto-generated method stub
		return schooldao.getMessageParentNotification(user_id);
	}


	@Override
	public void schoolDeletedHoliday(HolidayDeletedModel hdm) {
		// TODO Auto-generated method stub
		schooldao.schoolDeletedHoliday(hdm);
	}


	@Override
	public List<HolidayDeletedModel> getAllDeletedHoliday(int school_id) {
		// TODO Auto-generated method stub
		return schooldao.getAllDeletedHoliday(school_id);
	}
	@Override
	public List<HolidayDeletedModel> getAllDeletedHolidayByMonth(int school_id,String month,String year) {
		// TODO Auto-generated method stub
		return schooldao.getAllDeletedHolidayByMonth(school_id,month,year);
	}
	


	@Override
	public TypingModel checkTypingUser(int user_id, int reciever_id) {
		// TODO Auto-generated method stub
		return schooldao.checkTypingUser(user_id,reciever_id);
	}


	@Override
	public void insertTypingUser(TypingModel type) {
		// TODO Auto-generated method stub
		schooldao.insertTypingUser(type);
	}


	@Override
	public void updateTypingUserStatus(TypingModel type, int type_id,
			int typing_user) {
		// TODO Auto-generated method stub
	    schooldao.updateTypingUserStatus(type,type_id,typing_user);
	}


	@Override
	public RouteLatLngModel getStudentRouteLatLng(int student_id) {
		// TODO Auto-generated method stub
		return schooldao.getStudentRouteLatLng(student_id);
	}


	@Override
	public PageContentModel getAboutUs(int page_id) {
		// TODO Auto-generated method stub
		return 	schooldao.getAboutUs(page_id);
	}


	@Override
	public List<SliderModel> getAllSlider() {
		// TODO Auto-generated method stub
		return schooldao.getAllSlider();
	}
	
	@Override
	public List<CategoryContactModel> getAllContactCategory() {
		// TODO Auto-generated method stub
		return schooldao.getAllContactCategory();
	}
 
	
	
	@Override
	public List<FeaturesModel> getAllFeatures() {
		// TODO Auto-generated method stub
		return schooldao.getAllFeatures();
	}
	

	
	@Override
	public void addSlider(SliderModel slider_model) {
		// TODO Auto-generated method stub
		schooldao.addSlider(slider_model);
	}
	
	@Override
	public void addCategory(CategoryContactModel category_model) {
		// TODO Auto-generated method stub
		schooldao.addCategory(category_model);
	}
	
	
	@Override
	public void addFeature(FeaturesModel feature_model) {
		// TODO Auto-generated method stub
		schooldao.addFeature(feature_model);
	}
	
	

	@Override
	public SliderModel getSliderById(int slider_id) {
		// TODO Auto-generated method stub
		return schooldao.getSliderById(slider_id);
	}
	
	
	@Override
	public CategoryContactModel getCategoryById(int c_cat_id) {
		// TODO Auto-generated method stub
		return schooldao.getCategoryById(c_cat_id);
	}
	
	
	@Override
	public void updateSliderById(int slider_id, SliderModel s_model) {
		// TODO Auto-generated method stub
		schooldao.updateSliderById(slider_id,s_model);
	}
	
	@Override
	public void updateCategoryById(int c_cat_id,CategoryContactModel category_model) {
		// TODO Auto-generated method stub
		schooldao.updateCategoryById(c_cat_id,category_model);
	}
	
	

	@Override
	public void deleteSlider(int slider_id) {
		// TODO Auto-generated method stub
		schooldao.deleteSlider(slider_id);
	}
	
	@Override
	public void deleteCategory(int c_cat_id) {
		// TODO Auto-generated method stub
		schooldao.deleteCategory(c_cat_id);
	}
	
	
	public void deleteFeature(int feature_id)
	{
		schooldao.deleteFeature(feature_id);
	}


	@Override
	public List<PageModel> getAllPage(int p_type) {
		// TODO Auto-generated method stub
		return schooldao.getAllPage(p_type);
	}
	
	@Override
	public PageModel getPageById(int page_id) {
		// TODO Auto-generated method stub
		return schooldao.getPageById(page_id);
	}
	
	@Override
	public void updatePageById(int page_id, PageModel s_model) {
		// TODO Auto-generated method stub
		schooldao.updatePageById(page_id,s_model);
	}
	
	@Override
	public FeaturesModel getFeatureById(int feature_id) {
		// TODO Auto-generated method stub
		return schooldao.getFeatureById(feature_id);
	}


	@Override
	public void updateFeatureById(int feature_id, FeaturesModel s_model) {
		// TODO Auto-generated method stub
		schooldao.updateFeatureById(feature_id,s_model);
	}
	
	@Override
	public List<FaqModel> getAllFaq()
	{
		return schooldao.getAllFaq();
	}


	@Override
	public void addFaq(FaqModel faq_model) {
		// TODO Auto-generated method stub
		schooldao.addFaq(faq_model);
	}


	@Override
	public FaqModel getFaqById(int faq_id) {
		// TODO Auto-generated method stub
		return schooldao.getFaqById(faq_id);
	}


	@Override
	public void updateFaqById(int faq_id, FaqModel s_model) {
		// TODO Auto-generated method stub
		schooldao.updateFaqById(faq_id,s_model);
	}


	@Override
	public void deleteFaq(int faq_id) {
		// TODO Auto-generated method stub
		schooldao.deleteFaq(faq_id);
	}
	@Override
	public void deleteSubscriber(int faq_id) {
		// TODO Auto-generated method stub
		schooldao.deleteSubscriber(faq_id);
	}
	

	@Override
	public void addContactDetails(ContactDetailModel c_model) {
		// TODO Auto-generated method stub
		schooldao.addContactDetails(c_model);
	}


	@Override
	public List<SubscriberModel> getAllSubscribers() {
		// TODO Auto-generated method stub
		return schooldao.getAllSubscribers();
	}


	@Override
	public AdminSetupModel adminSetup() {
		// TODO Auto-generated method stub
		return schooldao.adminSetup();
	}
	
	@Override
	public void updateAdminSetup(AdminSetupModel s_model)
	{
		schooldao.updateAdminSetup(s_model);
	}


	@Override
	public EmailTemplateModel getEmailTemaplteById(int temp_id) {
		// TODO Auto-generated method stub
		return schooldao.getEmailTemaplteById(temp_id);
	}


	@Override
	public void updateEmailTemplate(EmailTemplateModel email, int temp_id) {
		// TODO Auto-generated method stub
		schooldao.updateEmailTemplate(email,temp_id);
	}


	@Override
	public SubscriberModel getSubscriberById(int s_id) {
		// TODO Auto-generated method stub
		return schooldao.getSubscriberById(s_id);
	}


	@Override
	public ContactContentModel getContactContent(int content_id) {
		// TODO Auto-generated method stub
		return schooldao.getContactContent(content_id);
	}


	@Override
	public void updateContactContent(ContactContentModel page, int page_id) {
		// TODO Auto-generated method stub
		 schooldao.updateContactContent(page,page_id);
	}
	
	
	
}




	

	

