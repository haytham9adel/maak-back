package com.trackingbus.dao;
import java.util.List;

import com.trackingbus.model.AdminSetupModel;
import com.trackingbus.model.AttendanceModel;
import com.trackingbus.model.CategoryContactModel;
import com.trackingbus.model.CityModel;
import com.trackingbus.model.ContactContentModel;
import com.trackingbus.model.ContactDetailModel;
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
import com.trackingbus.model.CountryModel;
import com.trackingbus.model.SliderModel;
import com.trackingbus.model.StudentModel;
import com.trackingbus.model.SubscriberModel;
import com.trackingbus.model.TypingModel;
import com.trackingbus.model.VehicleDocModel;
import com.trackingbus.model.VehicleModel;
import com.trackingbus.model.relationshipModel;
public interface SchoolDao {
	
	public long addSchool(SchoolModel s);
	public void addParent(LoginModel s);
	public long addDriver(DriverModel d);
	public long addRoute(RouteModel route);
	public void addRouteLatLng(RouteLatLngModel route);
	public void addAssignRoute(RouteLatLngModel s);
	public void addHoliday(HolidayModel m);
	public void addSchoolClass(SchoolClassesModel cls);
	public void addCountry(CountryModel c);
	public void addRelationship(relationshipModel r);
	public void addCity(CityModel c);
	public void addNationlity(NationlityModel nationlity);
	public void insertTypingUser(TypingModel type);

	
	public void editParentById(int parent_id, LoginModel login);
	public void saveParentSetting(int parent_id, LoginModel login);
	public void saveDriverSetting(int driver_id, DriverModel driver_details);
	public void editStudentById(int student_id, StudentModel student);
	public void editSchoolById(int school_id, SchoolModel school);
	public void editSchoolByAdmin(int school_id, SchoolModel school);
	public void editRouteById(int route_id, RouteModel routemodel);
	public void saveGeoFancing(int route_id, RouteModel routemodel);
	public void editDriverById(int driver_id, DriverModel driver);
	public void editStudentRoute(int student_id, RouteLatLngModel latlngmodel);
	public void editRouteByStudentId(int student_id, RouteLatLngModel routelatlng);
	public void editschooladmin(int schooadmin, LoginModel login);
	public void editschooladminBySchool(int school_id, LoginModel login);	
	public void updatePageContent(PageContentModel page,int page_id);
	public void updateMainSchoolAdmin(int user_id);
	public void editHolidayById(int h_id, HolidayModel holiday);
	public void editCountryById(int c_id, CountryModel c);
	public void editRelationById(int r_id, relationshipModel r);
	public void editCityById(int c_id, CityModel c);
	public void schoolDeletedHoliday(HolidayDeletedModel hdm);
	public void updateTypingUserStatus(TypingModel type,int type_id,int typing_user);

	
	
	public LoginModel getParentById(int parent_id);
	public SchoolModel getSchoolById(int school_id);
	public SchoolModel checkSchoolName(String email);
	public LoginModel getMainSchoolAdmin(int school_id);
	public LoginModel getUserById(int user_id);
	public DriverModel checkDriverName(String email);
	public DriverModel checkDriverUserName(String username);
	public DriverModel checkDriverContact(String contact);
	public DriverModel getDriverById(int driver_id);
	public DriverModel getDriverByRouteId(int route_id);
	public RouteModel getRouteById(int route_id);
	public RouteLatLngModel checkStudentRouteId(int student_id,int school_id);
	public VehicleModel getVehicleCheck(String vechile_name) ;
	public CountryModel getCountryById(int c_id);
	public HolidayModel getHolidayById(int holiday_id);
	public DriverModel checkAssigedRoute(int route_id);
	public CountryModel countryCheck(String country_name);
	public relationshipModel checkRelationship(String r_title);
	public relationshipModel getRelationshipById(int r_id);
	public CountryModel countryCodeCheck(String country_code);
	public CityModel cityCheck(String city_name,int country_id);
	public CityModel getCityById(int c_id);
	public RouteLatLngModel getStudentRouteLatLng(int student_id);
	public PageContentModel getAboutUs(int page_id);
	public NationlityModel checkNationlity(String name);
	public TypingModel checkTypingUser(int user_id,int reciever_id);

	public Integer getVehicleCount(int school_id);
	public Integer getAllVehicleCount();
	public Integer getStudentCount(int school_id);
	
	
	public List<CountryModel> listCountry();
	public List<SchoolModel> listSchools();
	public List<StudentModel> listStudent(int school_id);
	public List<StudentModel> getStudentsByRouteId(int route_id);
	public List<relationshipModel> listRelationship();
	public List<StudentModel> getAllStudent();
	public List<StudentModel> getAllStudentByRouteId(int route_id);
	public List<LoginModel> getParentBySchool(int school_id);
	public List<LoginModel> viewParentById(int user_id);
	public List<VehicleDocModel> getVehicleDocument(int vehicle_id);
	public List<DriverDocModel> getDriverDocument(int driver_id);
	public List<DriverModel> listDriver(int school_id);
	public List<DriverModel> getDriverList(int school_id);
	public List<DriverModel> listDriverLatest(int school_id);
	public List<DriverModel> allDrivers();
	public List<RouteModel> listRoute(int school_id);
	public List<RouteLatLngModel> listLatLng(int route_id);
	public List<VehicleModel> getVechileByDriverId(int driver_id);
	public List<LoginModel> listSchoolAdmin(int school_id);
	public List<LoginModel> listSchoolAdminLatest(int school_id);
	public List<LoginModel> getParentByPIdList(int p_status);
	public List<RouteLatLngModel> getRouteLatLng(int route_id);
	public List<HolidayModel> getAllHoliday(int school_id);
	public List<HolidayDeletedModel> getAllDeletedHoliday(int school_id);
	public List<HolidayModel> getAllHolidayByMonth(int school_id,String month,String year);
	public List<HolidayModel> getAllHolidayByTwoDate(int school_id,String start_date,String end_date); 
	public List<HolidayDeletedModel> getAllDeletedHolidayByMonth(int school_id,String month,String year);
	public List<LoginModel> listSuperAdmin();
	public List<LoginModel> adminList();
	public List<LoginModel> adminListLatest();
	public List<LoginModel> parentListLatest(int school_id);
	public List<SchoolClassesModel> getAllSchoolClasses(int school_id);
	public List<CityModel> listCities();
	public List<NationlityModel> getAllNationlity();
		
	
	public void deleteParent(int parent_id);
	public void deleteSchoolAdmin(int admin_id);
	public void deleteSuperAdmin(int admin_id);
	public void deleteSchoolAdminNew(int admin_id);
	public void deleteSchool(int school_id);
	public void deleteStudent(int student_id);
	public void deleteDriver(int driver_id);
	public void deleteHoliday(int h_id);
	public void deleteLatLng(int route_id);
	public void deleteRoute(int route_id);
	public void removeInsuranceCardCopy(int vechile_id,String insurance_card_copy);
	public void deleteClass(int class_id);
	public void deleteCountry(int c_id);
	public void deleteRelationship(int r_id);
	public void deleteCity(int c_id);
	public void deleteVehicleDocument(int v_doc_id);
	public void deleteDriverDocument(int v_doc_id);
	public void deleteNationality(int c_id);


	public void sendMessageById(SchoolMessage msg);
	public void sendMessageToParent(ParentMessage msg);
	public void sendMessageToDriver(DriverMessage msg);
	public List<VehicleModel> vehicleNotification(int school_id);
	public List<Object[]> getVehicleNotification(int school_id);
	public List<Object[]> getVehicleNotificationCount(int school_id);
	public List<DriverMessage> getMessageNotification(int user_id);
	public List<DriverMessage> getMessageAdminNotification(int user_id);
	public List<DriverMessage> getMessageParentNotification(int user_id);
	public List<SchoolMessage> getAllMessage(int user_id,int school_admin);
	public List<ParentMessage> getParentAllMessageCount(int user_id,int logged_user);
	public List<ParentMessage> getParentAllMessage(int user_id,int school_admin);
	public List<SchoolMessage> getAllMessageCount(int user_id,int logged_user);
	public void updateNotification(int reciever_id,int sender_id);
	public int updateNotificationCount(int reciever_id,int sender_id);
	public void updateParentNotification(int reciever_id,int sender_id);
	public int updateParentNotificationCount(int reciever_id, int sender_id);
	public List<DriverMessage> getDriverAllMessageCount(int user_id,int logged_user);
	public List<DriverMessage> getDriverAllMessage(int user_id,int school_admin);
	public void updateDriverNotification(int reciever_id,int sender_id);
	public int updateDriverNotificationCount(int reciever_id, int sender_id);
	public void updateHeadNotification(int v_id);
	
	public List<AttendanceModel> getStudentAttendanceList(int student_id);
	public List<AttendanceModel> getStudentAttendanceListByMonth(int student_id,String month,String year);
	public List<DriverAttendanceModel> getDriverAttendanceListByMonth(int student_id,String month,String year);
	public List<AttendanceModel> getStudentAttendanceListByTwoDate(int student_id,String start_date,String end_date);
	public List<DriverAttendanceModel> getDriverAttendanceListByTwoDate(int student_id,String start_date,String end_date);
	public AttendanceModel getTodayStudentAttendance(int student_id);
	public DriverAttendanceModel getTodayDriverAttendance(int driver_id);
	public List<DriverAttendanceModel> getDriverAttendanceList(int driver_id);

	

	//	Methods for sliders
	public List<SliderModel> getAllSlider();
	public void addSlider(SliderModel slider_model);
	public SliderModel getSliderById(int slider_id);
	public void updateSliderById(int slider_id,SliderModel s_Model);
	public void deleteSlider(int slider_id);
  
	 //	Methods for homepage's other sections
	public List<PageModel> getAllPage(int p_type);
	public PageModel getPageById(int page_id);
	public void updatePageById(int page_id,PageModel s_Model);
  
	//	Methods for manage features
	public List<FeaturesModel> getAllFeatures();
	public void addFeature(FeaturesModel feature_model);
	public FeaturesModel getFeatureById(int feature_id);
	public void updateFeatureById(int feature_id,FeaturesModel s_model);
	public void deleteFeature(int feature_id);
   
	//	Methods for faq
	public List<FaqModel> getAllFaq();
	public void addFaq(FaqModel faq_model);
	public FaqModel getFaqById(int faq_id);
	public void updateFaqById(int faq_id, FaqModel s_model);
	public void deleteFaq(int faq_id);
   
	 //	Methods for contact details
	public void addContactDetails(ContactDetailModel c_model);
	public List<SubscriberModel> getAllSubscribers();
	public void deleteSubscriber(int faq_id);
	public AdminSetupModel adminSetup();
	public void updateAdminSetup(AdminSetupModel s_model);
	public EmailTemplateModel getEmailTemaplteById(int temp_id);
	public void updateEmailTemplate(EmailTemplateModel email,int temp_id);
	public SubscriberModel getSubscriberById(int s_id) ;

	//	Methods for get all category of contact page
	public List<CategoryContactModel> getAllContactCategory();
	public void addCategory(CategoryContactModel category_model);
	public CategoryContactModel getCategoryById(int c_cat_id);
	public void updateCategoryById(int c_cat_id,CategoryContactModel category_model);
	public void deleteCategory(int c_cat_id);
	public ContactContentModel getContactContent(int content_id);
	public void updateContactContent(ContactContentModel page, int page_id);
}
