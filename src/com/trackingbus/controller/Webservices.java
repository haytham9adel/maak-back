package com.trackingbus.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;
import javax.jws.WebService;
import javax.mail.internet.NewsAddress;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trackingbus.bean.AttendanceBean;
import com.trackingbus.bean.DriverAttendanceBean;
import com.trackingbus.bean.DriverBean;
import com.trackingbus.bean.DriverDocBean;
import com.trackingbus.bean.DriverTrack;
import com.trackingbus.bean.HolidayBean;
import com.trackingbus.bean.HolidayDeletedBean;
import com.trackingbus.bean.LoginBean;
import com.trackingbus.bean.NotificationBean;
import com.trackingbus.bean.RouteBean;
import com.trackingbus.bean.RouteLatLng;
import com.trackingbus.bean.SchoolMessageBean;
import com.trackingbus.bean.StudentAbsentBean;
import com.trackingbus.bean.StudentBean;
import com.trackingbus.model.AttendanceModel;
import com.trackingbus.model.CountryModel;
import com.trackingbus.model.DriverAttendanceModel;
import com.trackingbus.model.DriverDocModel;
import com.trackingbus.model.DriverMessage;
import com.trackingbus.model.DriverModel;
import com.trackingbus.model.DriverTrackModel;
import com.trackingbus.model.HolidayDeletedModel;
import com.trackingbus.model.HolidayModel;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.NotificationModel;
import com.trackingbus.model.ParentMessage;
import com.trackingbus.model.RouteLatLngModel;
import com.trackingbus.model.RouteModel;
import com.trackingbus.model.SchoolModel;
import com.trackingbus.model.StudentAbsentModel;
import com.trackingbus.model.StudentModel;
import com.trackingbus.service.LoginService;
import com.trackingbus.service.SchoolService;
import com.trackingbus.service.StudentService;
import com.trackingbus.service.VehicleService;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONObject;

import resources.ApplePushNotification;
import resources.Assets;
import resources.Gcm;
import resources.JodaTimeExample;
import resources.Sms_api;

@Controller
public class Webservices<K> {

	@Autowired
	private LoginService loginservice;

	@Autowired
	private com.trackingbus.service.WebservicesSer webserviceService;

	@Autowired
	private SchoolService schoolservice;

	@Autowired
	private StudentService studentservice;

	@Autowired
	private VehicleService vechileservice;
	/*
	 * @RequestMapping(value="/login.html",method=RequestMethod.GET) public
	 * ModelAndView login() { return new ModelAndView("home/login"); }
	 */
	@RequestMapping(value = "webservices/weblogin", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String webserviceLogin(@ModelAttribute("command") LoginBean login) {
		String jsonResult = "";

		try {
			JSONObject jsonArray = new JSONObject();
			/*
			 * JSONObject jsonArray = new JSONObject(jsonNew); String
			 * email=(String) jsonArray.get("user_email"); String
			 * password=(String) jsonArray.get("user_pass");
			 */
			String email = login.getUser_email();
			String password = login.getUser_pass();
			LoginModel found = loginservice.login(email, password);
			if(found != null)
			{
				if (found != null && found.getUser_pass().equals(password)) {
					
				if(found.getDevice_id().equals(login.getDevice_id()) || found.getDevice_id().equals("") || found.getDevice_id()==null)
				{
						
					loginservice.updateParentDeviceId(found.getUser_id(),login.getDevice_id());
					if (found.getUser_role() == 1) {
						jsonArray.put("role", "Admin");
					} else if (found.getUser_role() == 2) {
						jsonArray.put("role", "Manager");
					} else if (found.getUser_role() == 3) {
						jsonArray.put("role", "Parent");
						loginservice.editParentById(found.getUser_id(),
								login.getDevice_token());
					}
					LoginModel school_admin = schoolservice
							.getMainSchoolAdmin(found.getSchool_id());
	
					jsonArray.put("user_email", found.getUser_email());
					jsonArray.put("user_name", found.getUser_name());
					jsonArray.put("user_id", found.getUser_id());
					jsonArray.put("school_id", found.getSchool_id());
					jsonArray.put("first_name", found.getFirst_name());
					jsonArray.put("middle_name", found.getMiddle_name());
					jsonArray.put("family_name", found.getFamily_name());
					jsonArray.put("mobile_number",found.getMobile_number());
					jsonArray.put("contact_number", found.getContact_number());
					jsonArray.put("lang", found.getLang());
					jsonArray.put("noti_on", found.getNoti_on());
					jsonArray.put("chat_on", found.getChat_on());
					jsonArray.put("checked_in_on", found.getChecked_in_on());
					jsonArray.put("checked_out_on",found.getChecked_out_on());
					jsonArray.put("speed_on", found.getSpeed_on());
					jsonArray.put("max_speed", found.getMax_speed());
					jsonArray.put("wrong_route_on", found.getWrong_route_on());
					jsonArray.put("sms_checked_in_on", found.getSms_checked_in_on());
					jsonArray.put("sms_checked_out_on", found.getSms_checked_out_on());
					jsonArray.put("sms_speed_on", found.getSms_speed_on());
					jsonArray.put("sms_max_speed", found.getSms_max_speed());
					jsonArray.put("sms_wrong_route_on", found.getSms_wrong_route_on());
					jsonArray.put("instant_message",found.getInstant_message());
					jsonArray.put("morning_before",found.getMorning_before());
					jsonArray.put("evening_before",found.getEvening_before());
					jsonArray.put("sms_instant_message",found.getSms_instant_message());
					jsonArray.put("sms_morning_before",found.getSms_morning_before());
					jsonArray.put("sms_evening_before",found.getSms_evening_before());
					jsonArray.put("device_id",found.getDevice_id());
					
					SchoolModel school_details=new SchoolModel();
					school_details=schoolservice.getSchoolById(found.getSchool_id());
					jsonArray.put("school_name", school_details.getSchool_name());
					jsonArray.put("school_logo", school_details.getSchool_logo());
					if (school_admin != null) {
						jsonArray.put("school_admin", school_admin.getUser_id()
								.toString());
						jsonArray.put("school_admin_name", school_admin.getFirst_name()+" "+school_admin.getLast_name()+" "+school_admin.getFamily_name());
						jsonArray.put("school_admin_number",school_admin.getContact_number());
					}
	
					jsonArray.put("result", "success");
					jsonArray.put("responseMessage", "Login successfull");
				}else
				{
					JSONObject n=new JSONObject();
					n.put("noti_type", "stop_service");
					//n.put("blink_status", "0");
					Gcm g1= new Gcm();
					
					if(!found.getDevice_token().equals("")){
						
						if(found.getDevice_token().length()==64)
						{	
							n.put("title", "Someone logged in with this user details");
							
							ApplePushNotification ap=new ApplePushNotification();
							ap.pushMessage(found.getDevice_token(), n.toString(), "Someone logged in with this user details",found.getSound_setting());
						}else
						{
							g1.pushNotificationToGCM(found.getDevice_token(), n.toString());
						}
						
						//g1.pushNotificationToGCM(found.getDevice_token(), n.toString());
					}
					loginservice.updateParentDeviceId(found.getUser_id(),login.getDevice_id());
					if (found.getUser_role() == 1) {
						jsonArray.put("role", "Admin");
					} else if (found.getUser_role() == 2) {
						jsonArray.put("role", "Manager");
					} else if (found.getUser_role() == 3) {
						jsonArray.put("role", "Parent");
						loginservice.editParentById(found.getUser_id(),
								login.getDevice_token());
					}
					LoginModel school_admin = schoolservice
							.getMainSchoolAdmin(found.getSchool_id());
	
					jsonArray.put("user_email", found.getUser_email());
					jsonArray.put("user_name", found.getUser_name());
					jsonArray.put("user_id", found.getUser_id());
					jsonArray.put("school_id", found.getSchool_id());
					jsonArray.put("first_name", found.getFirst_name());
					jsonArray.put("middle_name", found.getMiddle_name());
					jsonArray.put("family_name", found.getFamily_name());
					jsonArray.put("mobile_number",found.getMobile_number());
					jsonArray.put("contact_number", found.getContact_number());
					jsonArray.put("lang", found.getLang());
					jsonArray.put("noti_on", found.getNoti_on());
					jsonArray.put("chat_on", found.getChat_on());
					jsonArray.put("checked_in_on", found.getChecked_in_on());
					jsonArray.put("checked_out_on",found.getChecked_out_on());
					jsonArray.put("speed_on", found.getSpeed_on());
					jsonArray.put("max_speed", found.getMax_speed());
					jsonArray.put("wrong_route_on", found.getWrong_route_on());
					jsonArray.put("sms_checked_in_on", found.getSms_checked_in_on());
					jsonArray.put("sms_checked_out_on", found.getSms_checked_out_on());
					jsonArray.put("sms_speed_on", found.getSms_speed_on());
					jsonArray.put("sms_max_speed", found.getSms_max_speed());
					jsonArray.put("sms_wrong_route_on", found.getSms_wrong_route_on());
					jsonArray.put("instant_message",found.getInstant_message());
					jsonArray.put("morning_before",found.getMorning_before());
					jsonArray.put("evening_before",found.getEvening_before());
					jsonArray.put("sms_instant_message",found.getSms_instant_message());
					jsonArray.put("sms_morning_before",found.getSms_morning_before());
					jsonArray.put("sms_evening_before",found.getSms_evening_before());
					jsonArray.put("device_id",found.getDevice_id());
					
					SchoolModel school_details=new SchoolModel();
					school_details=schoolservice.getSchoolById(found.getSchool_id());
					jsonArray.put("school_name", school_details.getSchool_name());
					jsonArray.put("school_logo", school_details.getSchool_logo());
					if (school_admin != null) {
						jsonArray.put("school_admin", school_admin.getUser_id()
								.toString());
						jsonArray.put("school_admin_name", school_admin.getFirst_name()+" "+school_admin.getLast_name()+" "+school_admin.getFamily_name());
						jsonArray.put("school_admin_number",school_admin.getContact_number());
					}
	
					jsonArray.put("result", "success");
					jsonArray.put("responseMessage", "Login successfull");
					
//					jsonArray.put("result", "failed");
//					jsonArray.put("responseMessage",
//							"User already logged in");
//					jsonArray.put("status", "404");
				}
			} else {
				jsonArray.put("result", "failed");
				jsonArray.put("responseMessage",
						"Password does not match");
				jsonArray.put("status", "404");
			}
			}else
			{
				jsonArray.put("result", "failed");
				jsonArray.put("responseMessage",
						"Username does not match");
				jsonArray.put("status", "404");
			}
			jsonResult = jsonArray.toString();

		} catch (Exception e) {
			System.out.println(e);
		}

		return jsonResult;
	}
	
	/**
	 * Webservice for parent logout
	 **/
	@RequestMapping(value = "webservices/parentLogout", method = RequestMethod.POST)
	@ResponseBody
	public String parentLogout(@RequestBody String jsonNew) {
		String jsonResult = "";

		try {
			JSONObject jsonArray1 = new JSONObject(jsonNew);
			JSONObject jsonArray = new JSONObject();
			
			String parent_id_str =(String)jsonArray1.get("parent_id");
			int parent_id = Integer.parseInt(parent_id_str );
			loginservice.updateParentDeviceId(parent_id,"");
			jsonArray.put("responseMessage", "Parent logged out successfully");
			jsonArray.put("result", "success");
			jsonResult = jsonArray.toString();
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return jsonResult;
		
	}	
	/**
	 * Webservice for driver login
	 **/
	@RequestMapping(value = "webservices/driverLogout", method = RequestMethod.POST)
	@ResponseBody
	public String driverLogout(@RequestBody String jsonNew) {
		String jsonResult = "";

		try {
			JSONObject jsonArray1 = new JSONObject(jsonNew);
			JSONObject jsonArray = new JSONObject();
			
			String driver_id_str =(String)jsonArray1.get("driver_id");
			int driver_id = Integer.parseInt(driver_id_str );
			loginservice.updateDriverLoggedIn(driver_id,0);
			loginservice.updateDriverDeviceId(driver_id,"");
			jsonArray.put("responseMessage", "Driver logged out successfully");
			jsonArray.put("result", "success");
			jsonResult = jsonArray.toString();
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return jsonResult;
		
	}	
	@SuppressWarnings("unused")
	@RequestMapping(value = "webservices/driverLogin", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String driverLogin(@RequestBody String jsonNew) {
		String jsonResult = "";

		try {
			
			JodaTimeExample jtx=new JodaTimeExample();
			
			JSONObject jsonArray1 = new JSONObject(jsonNew);
			JSONObject jsonArray = new JSONObject();
			String nfc_string = (String) jsonArray1.get("nfc");
			String time_zone= (String) jsonArray1.get("time_zone");
			String device_token=(String) jsonArray1.get("device_token");
			DriverModel dr_check=new DriverModel();
			int nfc = Integer.parseInt(nfc_string);
			if (nfc == 0) {
				String user_email = (String) jsonArray1.get("user_email");
				String user_pass = (String) jsonArray1.get("user_pass");
				String device_id=(String) jsonArray1.get("device_id");
				DriverModel found = webserviceService.driverLogin(user_email,
						user_pass);
				
				if (found != null){
					
					
					if(found.getDevice_id().equals(device_id) || found.getDevice_id().equals("") || found.getDevice_id().equals(null))
					{
						
				if (found != null && found.getPassword().equals(user_pass)) {
					
					
					JSONObject n=new JSONObject();
					n.put("noti_type", "stop_service");
					//n.put("blink_status", "0");
					Gcm g1= new Gcm();
					
					if(!found.getDevice_token().equals("")){
						g1.pushNotificationToGCM(found.getDevice_token(), n.toString());
					}
					
					
					
					loginservice.updateDriverLoggedIn(found.getDriver_id(),1);
					loginservice.updateDriverDeviceToken(found.getDriver_id(), device_token);
					loginservice.updateDriverDeviceId(found.getDriver_id(), device_id);
					
					jsonArray.put("result", "success");
					jsonArray.put("user_email", found.getD_email());
					jsonArray.put("first_name", found.getDriver_fname());
					jsonArray.put("last_name", found.getDriver_lname());
					jsonArray.put("role", "driver");
					jsonArray.put("user_id", found.getDriver_id());
					jsonArray.put("school_id", found.getDriver_school_id());
					jsonArray.put("contact_number", found.getContact_number());
					jsonArray.put("route_id", found.getRoute_id());
					jsonArray.put("image_path",found.getImage_path());
					jsonArray.put("user_name",found.getUsername());
					jsonArray.put("dob", found.getDob());
					jsonArray.put("blood_group", found.getBlood_group());
					jsonArray.put("nationlity", found.getNationality());
					
					RouteModel routemodel = schoolservice.getRouteById(found.getRoute_id());
					
					jsonArray.put("route_name", routemodel.getRoute_name());
					jsonArray.put("device_token",device_token);
					jsonArray.put("qr_code", "d_"+found.getDriver_id()+".png");
					
					SchoolModel school_details=new SchoolModel();
					school_details=schoolservice.getSchoolById(found.getDriver_school_id());
					jsonArray.put("school_logo", school_details.getSchool_logo());
					jsonArray.put("school_name", school_details.getSchool_name());
					jsonArray.put("lang", found.getLang());
					
					
					
					LoginModel school_admin = schoolservice
							.getMainSchoolAdmin(found.getDriver_school_id());
					if (school_admin != null) {
						jsonArray.put("school_admin", school_admin.getUser_id()
								.toString());
						jsonArray.put("school_admin_name", school_admin.getFirst_name()+" "+school_admin.getLast_name()+" "+school_admin.getFamily_name());
						
						jsonArray.put("school_admin_number", school_admin.getContact_number());
						CountryModel country_details=new CountryModel();
						country_details=schoolservice.getCountryById(school_details.getCountry());
						jsonArray.put("country_code", country_details.getC_code());
					}
					DriverAttendanceModel dabModel = webserviceService.driverLoggedCheck(found.getDriver_id());
					DriverAttendanceModel dabBean= new DriverAttendanceModel();
					if (dabModel!=null) {
						
						if(!dabModel.getLogin_time().equals("") && (dabModel.getLogout_time()==null||dabModel.getLogout_time().equals("")))
						{
							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							Date date1 = new Date();
							String date = dateFormat.format(date1);
							
							DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Date date2 = new Date();
							
							
							String date3 = dateFormat1.format(date2);
							date3=jtx.SaveTime(date3, time_zone);
							dabBean.setDriver_id(found.getDriver_id());
							dabBean.setLogin_date(date);
							dabBean.setLogin_time(date3);
							dabBean.setStatus(1);
							webserviceService.updateDriverAttandance(found.getDriver_id(), date, "logout_time", date3);
						}else if(!dabModel.getLogout_time().equals("") && (dabModel.getLogin_evening()==null||dabModel.getLogin_evening().equals("")))
						{
							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							Date date1 = new Date();
							String date = dateFormat.format(date1);
							
							DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Date date2 = new Date();
							String date3 = dateFormat1.format(date2);
							date3=jtx.SaveTime(date3, time_zone);
							dabBean.setDriver_id(found.getDriver_id());
							dabBean.setLogin_date(date);
							dabBean.setLogin_time(date3);
							dabBean.setStatus(1);
							webserviceService.updateDriverAttandance(found.getDriver_id(), date, "login_evening", date3);
						}
						else if(!dabModel.getLogin_evening().equals("") && (dabModel.getLogout_evening()==null||dabModel.getLogout_evening().equals("")))
						{
							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							Date date1 = new Date();
							String date = dateFormat.format(date1);
							
							DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Date date2 = new Date();
							String date3 = dateFormat1.format(date2);
							date3=jtx.SaveTime(date3, time_zone);
							dabBean.setDriver_id(found.getDriver_id());
							dabBean.setLogin_date(date);
							dabBean.setLogin_time(date3);
							dabBean.setStatus(1);
							webserviceService.updateDriverAttandance(found.getDriver_id(), date, "logout_evening", date3);
						}else
						{
							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							Date date1 = new Date();
							String date = dateFormat.format(date1);
							
							DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Date date2 = new Date();
							String date3 = dateFormat1.format(date2);
							date3=jtx.SaveTime(date3, time_zone);
							dabBean.setDriver_id(found.getDriver_id());
							dabBean.setLogin_date(date);
							dabBean.setLogin_time(date3);
							dabBean.setStatus(1);
							webserviceService.updateDriverAttandance(found.getDriver_id(), date, "logout_evening", date3);
						}
					}else
					{
						
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Date date1 = new Date();
						String date = dateFormat.format(date1);
						
						DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date2 = new Date();
						String date3 = dateFormat1.format(date2);
						date3=jtx.SaveTime(date3, time_zone);
						dabBean.setDriver_id(found.getDriver_id());
						dabBean.setLogin_date(date);
						dabBean.setLogin_time(date3);
						dabBean.setStatus(0);
						webserviceService.insertDriverAttendace(dabBean);
					}
					

					} else {
						jsonArray.put("responseMessage",
								"Password does not match");
						jsonArray.put("result", "failed");
					}
					}else{
						jsonArray.put("responseMessage",
								"Driver already logged in");
						jsonArray.put("result", "failed");
					}	
				
				}else{
					jsonArray.put("responseMessage",
							"Username does not match");
					jsonArray.put("result", "failed");
				}
				

			} else if (nfc == 1) {
				String nfc_code_string = (String) jsonArray1.get("nfc_code");
				String device_id = (String) jsonArray1.get("device_id");
				int nfc_code = Integer.parseInt(nfc_code_string);
				DriverModel found = webserviceService.driverLoginByNFC(nfc_code);
				if (found != null) {
					if(found.getDevice_id().equals(device_id) || found.getDevice_id().equals("") || found.getDevice_id().equals(null))
					{
						loginservice.updateDriverDeviceId(found.getDriver_id(), device_id);
						loginservice.updateDriverLoggedIn(found.getDriver_id(),1);
					/*loginservice.updateDriverDeviceToken(found.getDriver_id(), device_token);
					jsonArray.put("result", "success");
					jsonArray.put("user_email", found.getD_email());
					jsonArray.put("first_name", found.getDriver_fname());
					jsonArray.put("last_name", found.getDriver_lname());
					jsonArray.put("role", "driver");
					jsonArray.put("user_id", found.getDriver_id());
					jsonArray.put("school_id", found.getDriver_school_id());
					jsonArray.put("contact_number", found.getContact_number());
					jsonArray.put("route_id", found.getRoute_id());
					jsonArray.put("dob", found.getDob());
					jsonArray.put("blood_group", found.getBlood_group());
					jsonArray.put("nationlity", found.getNationality());
					jsonArray.put("device_token",device_token);
					jsonArray.put("qr_code", "d_"+found.getDriver_id()+".png");
					jsonArray.put("image_path",found.getImage_path());
					
					SchoolModel school_details=new SchoolModel();
					school_details=schoolservice.getSchoolById(found.getDriver_school_id());
					jsonArray.put("school_logo", school_details.getSchool_logo());
					jsonArray.put("school_name",school_details.getSchool_name());
					
					 
					RouteModel routemodel = schoolservice.getRouteById(found.getRoute_id());
					jsonArray.put("route_name", routemodel.getRoute_name());
					jsonArray.put("device_token",device_token);
					jsonArray.put("qr_code", "d_"+found.getDriver_id()+".png");
					
				
					
					
					
					LoginModel school_admin = schoolservice
							.getMainSchoolAdmin(found.getDriver_school_id());
					if (school_admin != null) {
						jsonArray.put("school_admin", school_admin.getUser_id()
								.toString());
					}*/
					
					jsonArray.put("result", "success");
					jsonArray.put("user_email", found.getD_email());
					jsonArray.put("first_name", found.getDriver_fname());
					jsonArray.put("last_name", found.getDriver_lname());
					jsonArray.put("role", "driver");
					jsonArray.put("user_id", found.getDriver_id());
					jsonArray.put("school_id", found.getDriver_school_id());
					jsonArray.put("contact_number", found.getContact_number());
					jsonArray.put("route_id", found.getRoute_id());
					jsonArray.put("image_path",found.getImage_path());
					jsonArray.put("user_name",found.getUsername());
					jsonArray.put("dob", found.getDob());
					jsonArray.put("blood_group", found.getBlood_group());
					jsonArray.put("nationlity", found.getNationality());
					RouteModel routemodel = schoolservice.getRouteById(found.getRoute_id());
					jsonArray.put("route_name", routemodel.getRoute_name());
					jsonArray.put("device_token",device_token);
					jsonArray.put("qr_code", "d_"+found.getDriver_id()+".png");
					
					SchoolModel school_details=new SchoolModel();
					school_details=schoolservice.getSchoolById(found.getDriver_school_id());
					jsonArray.put("school_logo", school_details.getSchool_logo());
					jsonArray.put("school_name", school_details.getSchool_name());
					
					LoginModel school_admin = schoolservice
							.getMainSchoolAdmin(found.getDriver_school_id());
					if (school_admin != null) {
						jsonArray.put("school_admin", school_admin.getUser_id()
								.toString());
						jsonArray.put("school_admin_name", school_admin.getFirst_name()+" "+school_admin.getLast_name()+" "+school_admin.getFamily_name());
						
						jsonArray.put("school_admin_number", school_admin.getContact_number());
						CountryModel country_details=new CountryModel();
						country_details=schoolservice.getCountryById(school_details.getCountry());
						jsonArray.put("country_code", country_details.getC_code());
					}
					
					DriverAttendanceModel dabModel = webserviceService.driverLoggedCheck(found.getDriver_id());
					DriverAttendanceModel dabBean= new DriverAttendanceModel();
					if (dabModel!=null) {
						
						if(!dabModel.getLogin_time().equals("") && (dabModel.getLogout_time()==null||dabModel.getLogout_time().equals("")))
						{
							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							Date date1 = new Date();
							String date = dateFormat.format(date1);
							
							DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							Date date2 = new Date();
							String date3 = dateFormat1.format(date2);
							
							dabBean.setDriver_id(found.getDriver_id());
							dabBean.setLogin_date(date);
							dabBean.setLogin_time(date3);
							dabBean.setStatus(1);
							webserviceService.updateDriverAttandance(found.getDriver_id(), date, "logout_time", date3);
						}else if(!dabModel.getLogout_time().equals("") && (dabModel.getLogin_evening()==null||dabModel.getLogin_evening().equals("")))
						{
							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							Date date1 = new Date();
							String date = dateFormat.format(date1);
							
							DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							Date date2 = new Date();
							String date3 = dateFormat1.format(date2);
							
							dabBean.setDriver_id(found.getDriver_id());
							dabBean.setLogin_date(date);
							dabBean.setLogin_time(date3);
							dabBean.setStatus(1);
							webserviceService.updateDriverAttandance(found.getDriver_id(), date, "login_evening", date3);
						}
						else if(!dabModel.getLogin_evening().equals("") && (dabModel.getLogout_evening()==null||dabModel.getLogout_evening().equals("")))
						{
							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							Date date1 = new Date();
							String date = dateFormat.format(date1);
							
							DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							Date date2 = new Date();
							String date3 = dateFormat1.format(date2);
							
							dabBean.setDriver_id(found.getDriver_id());
							dabBean.setLogin_date(date);
							dabBean.setLogin_time(date3);
							dabBean.setStatus(1);
							webserviceService.updateDriverAttandance(found.getDriver_id(), date, "logout_evening", date3);
						}else
						{
							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							Date date1 = new Date();
							String date = dateFormat.format(date1);
							
							DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							Date date2 = new Date();
							String date3 = dateFormat1.format(date2);
							
							dabBean.setDriver_id(found.getDriver_id());
							dabBean.setLogin_date(date);
							dabBean.setLogin_time(date3);
							dabBean.setStatus(1);
							webserviceService.updateDriverAttandance(found.getDriver_id(), date, "logout_evening", date3);
						}
					}else
					{
						 
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Date date1 = new Date();
						String date = dateFormat.format(date1);
						
						DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						Date date2 = new Date();
						String date3 = dateFormat1.format(date2);
						
						dabBean.setDriver_id(found.getDriver_id());
						dabBean.setLogin_date(date);
						dabBean.setLogin_time(date3);
						dabBean.setLogout_time("0000-00-00 00:00:00");
						dabBean.setStatus(0);
						webserviceService.insertDriverAttendace(dabBean);
					}
					
					}else
					{
						jsonArray.put("responseMessage",
								"Driver already logged  in");
						jsonArray.put("result", "failed");
					}
					
					
					
				} else {
					jsonArray.put("responseMessage",
							"Incorrect Email or Password");
					jsonArray.put("result", "failed");
				}
			}

			jsonResult = jsonArray.toString();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			 
		}

		return jsonResult;
	}

	/**
	 * Webservice for get student by parent id
	 **/
	@RequestMapping(value = "webservices/getStudentByParent", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getStudentByParent(
			@ModelAttribute("command") StudentBean student) {
		String jsonResult = "";

		try {
			JSONObject jsonArray = new JSONObject();
			int s_parent_id = student.getS_parent_id();
			LoginModel parent = schoolservice.getParentById(s_parent_id);
			if (parent != null) {
				jsonArray.put("result", "success");
				jsonArray.put("child", getStudentList(studentservice.getStudentByParentStatus(parent.getP_status())));

			} else {
				jsonArray.put("result", "failed");
				jsonArray.put("responseMessage", "No data available");
			}

			jsonResult = jsonArray.toString();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}

		return jsonResult;
	}

	/* Method for get Student list */
	private List<StudentBean> getStudentList(List<StudentModel> students) {

		List<StudentBean> beans = null;
		if (students != null && !students.isEmpty()) {
			beans = new ArrayList<StudentBean>();
			StudentBean bean = null;
			for (StudentModel student : students) {
				bean = new StudentBean();
				SchoolModel stud = new SchoolModel();
				stud = schoolservice.getSchoolById(student.getS_school_id());
				bean.setStudent_id(student.getStudent_id());
				bean.setSchool_name(stud.getSchool_name());
				if(student.getS_fname()!=null)
				{
					bean.setS_fname(student.getS_fname());
				}else
				{
					bean.setS_fname("");
				}
				//bean.setS_fname(student.getS_fname());
				//bean.setS_lname(student.getS_lname());
				
				if(student.getS_lname()!=null)
				{
					bean.setS_lname(student.getS_lname());
				}else
				{
					bean.setS_lname("");
				}
				
				
				if(student.getS_email()!=null)
				{
					bean.setS_email(student.getS_email());
				}else
				{
					bean.setS_email("");
				}
				//bean.setS_email(student.getS_email());
				//bean.setS_contact(student.getS_contact());
				if(student.getS_contact()!=null)
				{
					bean.setS_contact(student.getS_contact());
				}else
				{
					bean.setS_contact("");
				}
				
				if(student.getS_image_path()!=null)
				{
					bean.setS_image_path(student.getS_image_path());
				}else
				{
					bean.setS_image_path("");
				}
				//bean.setS_image_path(student.getS_image_path());
				
				bean.setS_parent_id(student.getS_parent_id());
				bean.setS_route_id(student.getS_route_id());
				
				bean.setP_status_id(student.getP_status_id());
				bean.setS_school_id(student.getS_school_id());
				
				if(student.getBlood_type()!=null)
				{
					bean.setBlood_type(student.getBlood_type());
				}else
				{
					bean.setBlood_type("");
				}
				
				//bean.setBlood_type(student.getBlood_type());
				//bean.setFamily_name(student.getFamily_name());
				
				if(student.getFamily_name()!=null)
				{
					bean.setFamily_name(student.getFamily_name());
				}else
				{
					bean.setFamily_name("");
				}
				//bean.setFather_name(student.getFather_name());
				if(student.getFather_name()!=null)
				{
					bean.setFather_name(student.getFather_name());
				}else
				{
					bean.setFather_name("");
				}
				//bean.setNationality(student.getNationality());
				
				if(student.getNationality()!=null)
				{
					bean.setNationality(student.getNationality());
				}else
				{
					bean.setNationality("");
				}
				
				
				//bean.setGrand_name(student.getGrand_name());
				
				if(student.getGrand_name()!=null)
				{
					bean.setGrand_name(student.getGrand_name());
				}else
				{
					bean.setGrand_name("");
				}
				
				

				if(student.getFamily_name()!=null)
				{
					bean.setFamily_name(student.getFamily_name());
				}else
				{
					bean.setFamily_name("");
				}
				
				if(student.getDob()!=null)
				{
					bean.setDob(student.getDob());
				}else
				{
					bean.setDob("");
				}
				//bean.setDob(student.getDob());
				//bean.setGender(student.getGender());
				
				if(student.getGender()!=null)
				{
					bean.setGender(student.getGender());
				}else
				{
					bean.setGender("");
				}
				
				if(student.getStudent_class()!=null)
				{
					bean.setStudent_class(student.getStudent_class());
				}else
				{
					bean.setStudent_class("");
				}
			//	bean.setStudent_class(student.getStudent_class());
				if(student.getBlink_status()!=null)
				{
					bean.setBlink_status(student.getBlink_status());
				}else
				{
					bean.setBlink_status(0);
				}
			//	bean.setBlink_status(student.getBlink_status());
				
				RouteLatLngModel routelatlngmodel = new RouteLatLngModel();
				routelatlngmodel = webserviceService.getStudentLatLng(student.getStudent_id());
				if(routelatlngmodel!=null)
				{
					bean.setStudent_lat(routelatlngmodel.getLat());
					bean.setStudent_lng(routelatlngmodel.getLng());
					 
				}
				else
				{
					bean.setStudent_lat("");
					bean.setStudent_lng(""); 
				}
				if(student.getS_address()!=null)
				{
					
					bean.setS_address(student.getS_address());
				}else
				{
					bean.setS_address("");
				}
				DriverModel driver_details= new DriverModel();
				driver_details = webserviceService.getDriverByRouteId(student.getS_route_id());
				if(driver_details!=null)
				{
					//bean.setS_state(driver_details.getDriver_id());

					SchoolModel school_details=new SchoolModel();
					school_details=schoolservice.getSchoolById(student.getS_school_id());
					CountryModel country_details=new CountryModel();
					country_details=schoolservice.getCountryById(school_details.getCountry());
					bean.setS_zip(driver_details.getDriver_fname()+" "+driver_details.getDriver_lname());
					bean.setS_pass(country_details.getC_code()+driver_details.getContact_number());
					
				}else{
					bean.setS_zip("");
					bean.setS_pass("");
				}
			
				
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Function for driver lat lng inserted
	 **/
	@RequestMapping(value = "webservices/driver_lat_lng", method = RequestMethod.GET)
	@ResponseBody
	public String driver_lat_lng(
			@ModelAttribute("command") DriverTrack driverTrack) {
		String jsonResult = "";
		JSONObject jsonArray = new JSONObject();
		try {

			DriverTrackModel drivertrackmodel = new DriverTrackModel();
			drivertrackmodel.setDriver_id(driverTrack.getDriver_id());
			drivertrackmodel.setLat(driverTrack.getLat());
			drivertrackmodel.setLng(driverTrack.getLng());
			drivertrackmodel.setRoute_id(driverTrack.getRoute_id());
			drivertrackmodel.setTrack_date(null);
			drivertrackmodel.setSpeed(driverTrack.getSpeed());
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			drivertrackmodel.setTrack_date(dateFormat.format(date));
			;
			webserviceService.driverTrack(drivertrackmodel);
			jsonArray.put("result", "success");
			jsonArray.put("responseMessage", "updated successfully");
		} catch (Exception e) {
			System.out.println(e);
		}
		jsonResult = jsonArray.toString();
		return jsonResult;
	}

	/**
	 * Function for get student attendance
	 **/
	@RequestMapping(value = "webservices/student_attendance", method = RequestMethod.GET)
	@ResponseBody
	public String student_attendance(
			@ModelAttribute("command") StudentBean student) {
		String jsonResult = "";
		JSONObject jsonArray = new JSONObject();
		try {
			jsonArray.put("holiday",
					getHolidayList(schoolservice.getAllHoliday(0)));
			jsonArray.put("present", getPresentList(schoolservice
					.getStudentAttendanceList(student.getStudent_id())));
			jsonArray.put("result", "success");
		} catch (Exception e) {
			System.out.println(e);
		}

		jsonResult = jsonArray.toString();
		return jsonResult;
	}

	/**
	 * Method for get all hodiday list
	 **/
	private List<HolidayBean> getHolidayList(List<HolidayModel> holiday) {

		List<HolidayBean> beans = null;
		if (holiday != null && !holiday.isEmpty()) {
			beans = new ArrayList<HolidayBean>();
			HolidayBean bean = null;
			for (HolidayModel holi : holiday) {
				bean = new HolidayBean();
				bean.setH_id(holi.getH_id());
				bean.setHoliday_date(holi.getHoliday_date());
				bean.setHoliday_name(holi.getHoliday_name());
				bean.setSchool_id(holi.getSchool_id());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Method for get Student Present list
	 * **/
	private List<AttendanceBean> getPresentList(List<AttendanceModel> presents) {

		List<AttendanceBean> beans = null;
		if (presents != null && !presents.isEmpty()) {
			beans = new ArrayList<AttendanceBean>();
			AttendanceBean bean = null;
			for (AttendanceModel present : presents) {
				bean = new AttendanceBean();
				bean.setA_id(present.getA_id());
				bean.setDate(present.getDate());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Function get driver route lat lng with student details
	 * 
	 * @param <V>
	 **/
	@RequestMapping(value = "webservices/driver_route", method = RequestMethod.GET)
	@ResponseBody
	public <V> String driver_route(
			@ModelAttribute("command") RouteLatLng routebean) {
		String jsonResult = "";
		JSONObject jsonArray = new JSONObject();
		try {
			// RouteLatLngModel r=new RouteLatLngModel();
			int route_id = routebean.getRoute_id();
			int r = webserviceService.getRouteLatLngDetails(route_id).size();
			if (r > 0) {
				// Get route source and destination lat lng
				RouteModel routemodel = webserviceService
						.getRouteDetailsByRouteId(route_id);

				jsonArray.put("source_lat", routemodel.getSource_lat());
				jsonArray.put("source_lng", routemodel.getSource_lng());
				jsonArray.put("radius", routemodel.getRadius());
				jsonArray.put("destination_lat",
						routemodel.getDestination_lat());
				jsonArray.put("destination_lng",
						routemodel.getDestination_lng());
				// Get route lat lng with student lat lng
				List<RouteLatLngModel> routeLntLng = webserviceService.getRouteLatLngDetails(route_id);
				
				JSONArray list1 = new JSONArray();
				JSONArray list2 = new JSONArray();
				JSONObject list4 = new JSONObject();
				AttendanceModel at = new AttendanceModel();
				StudentModel studentModel = new StudentModel();
				List<HashMap<String, String>> msg = new ArrayList<HashMap<String, String>>();
				JSONArray studentArr=new JSONArray();
				for (RouteLatLngModel user : routeLntLng) {
					list1.put(user.getLat());
					list2.put(user.getLng());
					studentModel = studentservice.getStudentById(user.getStudent_id());
					if(studentModel!=null)
					{
					
					HashMap<String, String> jt = new HashMap<String, String>();
					HashMap<String, String> jtParent = new HashMap<String, String>();
					
					JSONObject studentObj=new JSONObject();
					if (studentModel.getS_fname() != "") {
						studentObj.put("s_fname", studentModel.getS_fname());
					}else
					{
						studentObj.put("s_fname", "");
					}
					if (studentModel.getStudent_id() != null) {
						jt.put("student_id", studentModel.getStudent_id()
								.toString());
						studentObj.put("student_id", studentModel.getStudent_id()
								.toString());
					}
					if (studentModel.getS_fname() != null) {
						jt.put("s_fname", studentModel.getS_fname());
					}else
					{
						studentObj.put("s_fname", "");
					}
					if (studentModel.getS_lname() != null) {
						
						jt.put("s_lname", studentModel.getS_lname());
					
					}else
					{
						studentObj.put("s_lname", "");
					}
					
					if (studentModel.getS_lname() != null) {
						
					studentObj.put("s_lname",  studentModel.getS_lname());
					}else
					{
						studentObj.put("s_lname", "");
					}
					
					if (studentModel.getFamily_name() != null) {
					jt.put("family_name", studentModel.getFamily_name());
					}else
					{
						studentObj.put("family_name", "");
					}
					if (studentModel.getFamily_name() != null) {
						studentObj.put("family_name",  studentModel.getFamily_name());
					}else
					{
						studentObj.put("family_name", "");
					}
					if (studentModel.getS_email() != null) {
						
					jt.put("s_email", studentModel.getS_email());
					}else
					{
						studentObj.put("s_email", "");
					}
					if (studentModel.getS_email() != null) {
					
						studentObj.put("s_email",  studentModel.getS_email());
					}else
					{
						studentObj.put("s_email", "");
					}
					
					//jt.put("s_pass", studentModel.getS_pass());
					///studentObj.put("s_pass",  studentModel.getS_pass());
					
					if (studentModel.getS_address() != null) {
					jt.put("s_address", studentModel.getS_address());
					}else
					{
						studentObj.put("s_address", "");
					}
					
					if (studentModel.getS_address() != null) {
					studentObj.put("s_address",  studentModel.getS_address());
					}else
					{
						studentObj.put("s_address", "");
					}
					
					if (studentModel.getS_city() != null) {
						jt.put("s_city", studentModel.getS_city().toString());
						studentObj.put("s_city",  studentModel.getS_city().toString());
					}
					if (studentModel.getS_country() != null) {
						jt.put("s_country", studentModel.getS_country()
								.toString());
						studentObj.put("s_country", studentModel.getS_country()
								.toString());
					}

					jt.put("s_contact", studentModel.getS_contact());
					studentObj.put("s_contact", studentModel.getS_contact());
					jt.put("s_zip", studentModel.getS_zip());
					studentObj.put("s_zip",  studentModel.getS_zip());
					jt.put("s_image_path", studentModel.getS_image_path());
					studentObj.put("s_image_path",  studentModel.getS_image_path());
					if (studentModel.getS_parent_id() != null) {
						jt.put("s_parent_id", studentModel.getS_parent_id()
								.toString());
						studentObj.put("s_parent_id",   studentModel.getS_parent_id()
								.toString());
					}
					if (studentModel.getS_school_id() != null) {
						jt.put("s_school_id", studentModel.getS_school_id()
								.toString());
						studentObj.put("s_school_id",   studentModel.getS_school_id()
								.toString());
					}

					if (studentModel.getP_status_id() != null) {
						jt.put("p_status_id", studentModel.getP_status_id()
								.toString());
						studentObj.put("p_status_id",   studentModel.getP_status_id()
								.toString());
					}
					if (studentModel.getS_driver() != null) {
						jt.put("s_driver", studentModel.getS_driver()
								.toString());
						studentObj.put("s_driver",   studentModel.getS_driver()
								.toString());
					}
					if (studentModel.getS_route_id() != null) {
						jt.put("s_route_id", studentModel.getS_route_id()
								.toString());
						studentObj.put("s_route_id",studentModel.getS_route_id()
								.toString());
					}

					at = webserviceService.getAttendanceByStudentId(user
							.getStudent_id());
					StudentAbsentModel absent=webserviceService.getAbsentByStudentId(user.getStudent_id());
					if(absent!=null)
					{
						jt.put("absent_status", "0");
						studentObj.put("absent_status",0);
					}else
					{
						jt.put("absent_status", "1");
						studentObj.put("absent_status",1);
					}
					if (at != null) {
						jt.put("status", at.getStatus().toString());
						studentObj.put("status",at.getStatus().toString());
					} else {
						jt.put("status", "0");
						studentObj.put("status","0");
					}
					JSONArray jsonParent = new JSONArray();
					List<LoginModel> parent_details=schoolservice.getParentByPIdList(studentModel.getP_status_id());
					HashMap<String, String> pt = new HashMap<String, String>();
					List<HashMap<String, String>> parents = new ArrayList<HashMap<String, String>>();
					for (LoginModel parent_detail : parent_details) {
						if(parent_detail!=null)
						{
						    JSONObject parentObject=new JSONObject();
						    System.out.println(parent_detail.getUser_id()+"=="+studentModel.getP_1());
							if(Integer.valueOf(parent_detail.getUser_id())==Integer.valueOf(studentModel.getP_1()))
							{
								parentObject.put("relationship", studentModel.getR_1());
							}else if(Integer.valueOf(parent_detail.getUser_id())==Integer.valueOf(studentModel.getP_2()))
							{
								parentObject.put("relationship", studentModel.getR_2());
							}else if(Integer.valueOf(parent_detail.getUser_id())==Integer.valueOf(studentModel.getP_2()))
							{
								System.out.println("Here2");
								parentObject.put("relationship", studentModel.getR_3());
							}else{
								System.out.println("Here3");
								parentObject.put("relationship", "");
							}
						    parentObject.put("parent_id", parent_detail.getUser_id());
							parentObject.put("parent_fname", parent_detail.getFirst_name());
							parentObject.put("parent_family_name", parent_detail.getFamily_name());
							parentObject.put("parent_number", parent_detail.getMobile_number());
							
							
							
							SchoolModel school_details=new SchoolModel();
							school_details=schoolservice.getSchoolById(parent_detail.getSchool_id());
							CountryModel country_details=new CountryModel();
							country_details=schoolservice.getCountryById(school_details.getCountry());
							parentObject.put("country_code", country_details.getC_code());
							
							
							
							
							parentObject.put("speed", parent_detail.getMax_speed());
							jsonParent.put(parentObject);
						}
					}
					studentObj.put("parent", jsonParent);
					studentArr.put(studentObj);
					msg.add(jt);
				//	msg.addAll(parents);
				}
			}
				jsonArray.put("lat", list1);
				jsonArray.put("lng", list2);
				//jsonArray.put("student", msg);
				jsonArray.put("student", studentArr);
				
				// Success message
				jsonArray.put("result", "success");
			} else {
				jsonArray.put("result", "failed");
				jsonArray.put("responseMessage", "No Data available");

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		jsonResult = jsonArray.toString();
		return jsonResult;
	}

	/* Method for get country list */
	private List<RouteLatLng> getStudentLatLngList(List<RouteLatLngModel> users) {

		List<RouteLatLng> beans = null;
		if (users != null && !users.isEmpty()) {
			beans = new ArrayList<RouteLatLng>();
			RouteLatLng bean = null;
			for (RouteLatLngModel user : users) {

				bean = new RouteLatLng();
				/*
				 * SchoolModel stud = new SchoolModel(); stud =
				 * schoolservice.getSchoolById(user.getSchool_id());
				 */
				bean.setLat(user.getLat());

				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Webservice for get real time location of student as well as stop lat lng
	 * by route id and student id
	 **/
	@RequestMapping(value = "webservices/get_realtime_location", method = RequestMethod.GET)
	@ResponseBody
	public String get_realtime_location(
			@ModelAttribute("command") RouteLatLng routelatlng) {
		String jsonResult = "";
		JSONObject jsonArray = new JSONObject();
		try {
			int route_id = routelatlng.getRoute_id();
			int student_id = routelatlng.getStudent_id();
			// Get student stop lat lng
			RouteLatLngModel routelatlngmodel = new RouteLatLngModel();
			routelatlngmodel = webserviceService.getStudentLatLng(student_id);
			//jsonArray.put("student_lat", routelatlngmodel.getLat());
			//jsonArray.put("student_lng", routelatlngmodel.getLng());

			// Get bus current location i.e. get last lat lng of driver from
			// driver track
			DriverTrackModel drivertrack = new DriverTrackModel();
			drivertrack = webserviceService.getDriverCurrentLocation(route_id);
			List<HashMap<String, String>> ltln = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> hm = new HashMap<String, String>();
			if(drivertrack!=null)
			{
				hm.put("track_id", drivertrack.getTrack_id().toString());
				hm.put("driver_id", drivertrack.getDriver_id().toString());
				hm.put("lat", drivertrack.getLat());
				hm.put("lng", drivertrack.getLng());
				hm.put("track_date", drivertrack.getTrack_date());
				hm.put("route_id", drivertrack.getRoute_id().toString());
				ltln.add(hm);
				jsonArray.put("route_lat_lng", ltln);
			}
			else
			{
				hm.put("track_id", "");
				hm.put("driver_id","");
				hm.put("lat", "");
				hm.put("lng", "");
				hm.put("track_date", "");
				hm.put("route_id","");
				ltln.add(hm);
				jsonArray.put("route_lat_lng", ltln);
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e);
		}

		jsonResult = jsonArray.toString();
		return jsonResult;
	}

	@RequestMapping(value = "webservices/parent_map", method = RequestMethod.GET)
	@ResponseBody
	public String parent_map(@ModelAttribute("command") RouteLatLng routelatlng) {
		String jsonResult = "";
		JSONObject jsonArray = new JSONObject();
		try {
			int parent_id = routelatlng.getParent_id();
			int count = webserviceService.getLatLngByParentId(parent_id).size();
			if (count > 0) {
				List<RouteLatLngModel> parentDetails = webserviceService
						.getLatLngByParentId(parent_id);

				for (RouteLatLngModel user : parentDetails) {
					RouteLatLngModel stud_details = webserviceService
							.getStudentLatLng(user.getStudent_id());
					// select * from tbl_route where
					// `school_id`='".$row['school_id']."'"
					List<RouteModel> route_details = webserviceService
							.getRouteDetailsBySchoolId(stud_details
									.getSchool_id());
					for (RouteModel route : route_details) {

					}

				}

			} else {
				jsonArray.put("responseMessage", "No Data available");
				jsonArray.put("result", "failed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		jsonResult = jsonArray.toString();
		return jsonResult;
	}

	/* Student Lat lng */
	@RequestMapping(value = "webservices/student_lat_lng", method = RequestMethod.GET)
	@ResponseBody
	public String student_lat_lng(
			@ModelAttribute("command") RouteLatLng routelatlng) {
		String jsonResult = "";
		JSONObject jsonArray = new JSONObject();
		try {
			int student_id = routelatlng.getStudent_id();
			RouteLatLngModel routelatlngmodel = new RouteLatLngModel();
			routelatlngmodel = webserviceService.getStudentLatLng(student_id);
			jsonArray.put("student_lat", routelatlngmodel.getLat());
			jsonArray.put("student_lng", routelatlngmodel.getLng());

			// Get route lat lng
			RouteModel routemodel = webserviceService
					.getRouteDetailsByRouteId(routelatlngmodel.getRoute_id());

			jsonArray.put("source_lat", routemodel.getSource_lat());
			jsonArray.put("source_lng", routemodel.getSource_lng());
			jsonArray.put("destination_lat", routemodel.getDestination_lat());
			jsonArray.put("destination_lng", routemodel.getDestination_lng());

			List<RouteLatLngModel> r = webserviceService
					.getRouteDetailsBySchoolId(routelatlngmodel.getSchool_id(),
							routelatlngmodel.getRoute_id());
			List<HashMap<String, String>> ltln = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> hm = new HashMap<String, String>();
			List<String> a = new ArrayList<String>();
			List<String> b = new ArrayList<String>();
			for (RouteLatLngModel user : r) {
				a.add(user.getLat());
				b.add(user.getLng());
				/*
				 * hm.put("lat", user.getLat()); hm.put("lng", user.getLng());
				 * ltln.add(hm);
				 */

			}
			jsonArray.put("lat", a);
			jsonArray.put("lng", b);
			// jsonArray.put("route_lat_lng", ltln);
			jsonArray.put("result", "success");
		} catch (Exception e) {
			System.out.println(e);
		}

		jsonResult = jsonArray.toString();

		return jsonResult;
	}

	/**
	 * Webservice for send message using webservice to school admin
	 **/
	@RequestMapping(value = "webservices/send_message", method = RequestMethod.GET)
	@ResponseBody
	public String send_message(@ModelAttribute("command") SchoolMessageBean msg) {
		String jsonResult = "";
		JSONObject jsonObject = new JSONObject();
		try {
			ParentMessage pm = new ParentMessage();
			pm.setMsg(msg.getMsg());
			pm.setMsg_id(null);
			pm.setReciever_id(msg.getReciever_id());
			pm.setSender(1);
			pm.setSender_id(msg.getSender_id());
			pm.setStatus(0);
			pm.setSchool_id(0);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			pm.setTime(dateFormat.format(date));
			webserviceService.sendMessage(pm);
			jsonObject.put("result", "success");
			jsonObject.put("responseMessage", "message sent successfully");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		jsonResult = jsonObject.toString();
		return jsonResult;
	}

	/**
	 * Method for get message from school admin
	 **/
	@RequestMapping(value = "webservices/chatting", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String chatting(@ModelAttribute("command") SchoolMessageBean msg) {
		String jsonResult = "";
		JSONObject jsonObject = new JSONObject();
		try {
			List<SchoolMessageBean> msg_model = iterateMsg(
					webserviceService
							.getMessageByParentId(msg.getReciever_id()),
					msg.getReciever_id());
			jsonObject.put("details", msg_model);
			jsonObject.put("result", "success");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		jsonResult = jsonObject.toString();
		return jsonResult;
	}

	/**
	 * Method for get parent message
	 **/
	private List<SchoolMessageBean> iterateMsg(List<ParentMessage> parentmsg,
			int user_id) {

		List<SchoolMessageBean> beans = null;
		if (parentmsg != null && !parentmsg.isEmpty()) {
			beans = new ArrayList<SchoolMessageBean>();
			SchoolMessageBean bean = null;
			for (ParentMessage msgIt : parentmsg) {
				/*
				 * LoginModel parent =
				 * schoolservice.getParentById(msgIt.getSender_id());
				 */bean = new SchoolMessageBean();
				bean.setMsg(msgIt.getMsg());
				bean.setSender(msgIt.getSender());
				bean.setSender_id(msgIt.getSender_id());
				bean.setStatus(msgIt.getStatus());
				bean.setReciever_id(msgIt.getReciever_id());
				bean.setTime(msgIt.getTime());
				/* bean.set */
				beans.add(bean);
			}

		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Function for update check-in and check-out
	 **/
	@RequestMapping(value = "webservices/update_check_in_checkout", method = RequestMethod.GET)
	@ResponseBody
	public String update_check_in_checkout(
			@ModelAttribute("command") StudentBean studentbean) {
		int student_id = studentbean.getStudent_id();
		String time_zone=studentbean.getS_address();
		int parent_id;
		String device_token;
		String student_name;
		String desc;
		int student_route;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date();
		String date = dateFormat.format(date1);
		String jsonResult;
		JSONObject jsonObject = new JSONObject();
		JodaTimeExample jtx=new JodaTimeExample();
		try {
			StudentModel studentModel = studentservice
					.getStudentById(student_id);
			LoginModel parent = schoolservice.getParentById(studentModel
					.getS_parent_id());
			device_token = parent.getDevice_token();
			parent_id = parent.getUser_id();
			student_name = studentModel.getS_fname();
			student_route = studentModel.getS_route_id();
			AttendanceModel attendancemodel = webserviceService
					.getAttendanceByStudentId(student_id);
			if (attendancemodel != null) {
					
				if(!attendancemodel.getLogin_time().equals("") && (attendancemodel.getLogout_time()==null||attendancemodel.getLogout_time().equals("")))
				{
					 
					DateFormat dateFormat1 = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Date date2 = new Date();
					String date3 = dateFormat1.format(date2);
					desc = "Student " + student_name
							+ " successfully checked out at school on " + date +" from bus";
					
					date3=jtx.SaveTime(date3, time_zone);
					
					webserviceService.updateStudentAttandance(student_id,dateFormat.format(date1),"logout_time",date3);
					NotificationModel noti = new NotificationModel();
					noti.setParent_id(parent_id);

					
					noti.setDate(date3);
					noti.setNoti_desc(desc);
					noti.setRoute_id(studentModel.getS_route_id());
					noti.setStudent_id(student_id);
					noti.setNoti_id(null);
					noti.setNoti_type("Check out");

					int noti_id = webserviceService.insertNotification(noti);
					String msg = desc;
				//	Map<String, String> m = new HashMap<String, String>();
					JSONObject m=new JSONObject();
					m.put("msg", msg);
					m.put("noti_id", Integer.toString(noti_id));
					m.put("date", date3);
					m.put("noti_type", "checked_out");
					Gcm g = new Gcm();
					if(parent.getChecked_out_on()==0)
					{
						if(device_token.length()==64)
						{
							
							ApplePushNotification ap=new ApplePushNotification();
							ap.pushMessage(device_token,m.toString(),msg,parent.getSound_setting());
						}else
						{
							
							g.pushNotificationToGCM(device_token, m.toString());
						}
						
					}
					if(parent.getSms_checked_out_on()==0)
					{
						String contact_number = parent.getMobile_number();
						Sms_api sms = new Sms_api();
						sms.sendhttp(msg, 0, contact_number);
					}
					
					
					

				//	Map<String, String> n=new HashMap<String, String>();
					JSONObject n=new JSONObject();
					n.put("noti_type", "blink");
					n.put("student_id", Integer.toString(student_id));
					n.put("blink_status", "0");
					Gcm g1= new Gcm();
					if(device_token.length()==64)
					{
						
						ApplePushNotification ap=new ApplePushNotification();
						ap.pushMessage(device_token,m.toString(),"",parent.getSound_setting());
					}else
					{
						g1.pushNotificationToGCM(device_token, n.toString());
					}
					
					
					webserviceService.updateStudentBlinkStatus(student_id, 0);
				}else if(!attendancemodel.getLogout_time().equals("") && (attendancemodel.getLogin_evening()==null || attendancemodel.getLogin_evening().equals("")))
				{
					DateFormat dateFormat1 = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Date date2 = new Date();
					String date3 = dateFormat1.format(date2);
					desc = "Student " + student_name
							+ " successfully checked-in at " + date +" from school";
					date3=jtx.SaveTime(date3, time_zone);
					//webserviceService.updateStudentAttandance(student_id,dateFormat.format(date1),"login_evening",date3);
					webserviceService.updateStudentAttandanceSecond(student_id,dateFormat.format(date1),"login_evening",date3);
					
					NotificationModel noti = new NotificationModel();
					noti.setParent_id(parent_id);
					noti.setDate(date3);
					noti.setNoti_desc(desc);
					noti.setRoute_id(studentModel.getS_route_id());
					noti.setStudent_id(student_id);
					noti.setNoti_id(null);
					noti.setNoti_type("Check In");

					int noti_id = webserviceService.insertNotification(noti);
					String msg = desc;
				//	Map<String, String> m = new HashMap<String, String>();
					JSONObject m=new JSONObject();
					m.put("msg", msg);
					m.put("noti_id", Integer.toString(noti_id));
					m.put("date", date3);
					m.put("noti_type", "checked_in");
					Gcm g = new Gcm();
					if(parent.getChecked_in_on()==0)
					{
						if(device_token.length()==64)
						{
							
							ApplePushNotification ap=new ApplePushNotification();
							ap.pushMessage(device_token,m.toString(),msg,parent.getSound_setting());
						}else
						{
							g.pushNotificationToGCM(device_token, m.toString());
						}
						
					}
					if(parent.getSms_checked_in_on()==0)
					{
						String contact_number = parent.getMobile_number();
						Sms_api sms = new Sms_api();
						sms.sendhttp(msg, 0, contact_number);
					}
					//Map<String, String> n=new HashMap<String, String>();
					JSONObject n=new JSONObject();
					n.put("noti_type", "blink");
					n.put("student_id", Integer.toString(student_id));
					n.put("blink_status", "1");
					
					Gcm g1= new Gcm();
					if(device_token.length()==64)
					{
						
						ApplePushNotification ap=new ApplePushNotification();
						ap.pushMessage(device_token,m.toString(),"",parent.getSound_setting());
					}else
					{
						g1.pushNotificationToGCM(device_token, n.toString());
					}
				//	
					
					webserviceService.updateStudentBlinkStatus(student_id, 1);
				}else if(attendancemodel.getLogout_evening()==null)
				{
					System.out.println();
					DateFormat dateFormat1 = new SimpleDateFormat(
							"yyyy-MM-dd hh:mm:ss");
					Date date2 = new Date();
					String date3 = dateFormat1.format(date2);
					desc = "Student " + student_name
							+ " successfully checked-out at" + date +" from bus";
					date3=jtx.SaveTime(date3, time_zone);
					webserviceService.updateStudentAttandance(student_id,dateFormat.format(date1),"logout_evening",date3);
					NotificationModel noti = new NotificationModel();
					noti.setParent_id(parent_id);
					noti.setDate(date3);
					noti.setNoti_desc(desc);
					noti.setRoute_id(studentModel.getS_route_id());
					noti.setStudent_id(student_id);
					noti.setNoti_id(null);
					noti.setNoti_type("Check out");

					int noti_id = webserviceService.insertNotification(noti);
					String msg = desc;
					//Map<String, String> m = new HashMap<String, String>();
					JSONObject m=new JSONObject();
					m.put("msg", msg);
					m.put("noti_id", Integer.toString(noti_id));
					m.put("noti_type", "checked_out");
					m.put("date", date3);
					Gcm g = new Gcm();
					if(parent.getChecked_out_on()==0)
					{
						if(device_token.length()==64)
						{
							
							ApplePushNotification ap=new ApplePushNotification();
							ap.pushMessage(device_token,m.toString(),msg,parent.getSound_setting());
						}else
						{
							g.pushNotificationToGCM(device_token, m.toString());
						}
						//
					}
					if(parent.getSms_checked_out_on()==0)
					{
						String contact_number = parent.getMobile_number();
						Sms_api sms = new Sms_api();
						sms.sendhttp(msg, 0, contact_number);
					}
					Map<String, String> n=new HashMap<String, String>();
					JSONObject jsonObjectnew=new JSONObject();
					
					
					jsonObjectnew.put("noti_type", "blink");
					jsonObjectnew.put("student_id", Integer.toString(student_id));
					jsonObjectnew.put("blink_status", "1");
					Gcm g1= new Gcm();
					if(device_token.length()==64)
					{
						
						ApplePushNotification ap=new ApplePushNotification();
						ap.pushMessage(device_token,jsonObjectnew.toString(),"",parent.getSound_setting());
					}else
					{
						g1.pushNotificationToGCM(device_token, jsonObjectnew.toString());
					}
					//g1.pushNotificationToGCM(device_token, jsonObjectnew.toString());
					
					webserviceService.updateStudentBlinkStatus(student_id, 0);
				}else if(!attendancemodel.getLogout_evening().equals(""))
				{
					
					jsonObject.put("responseMessage", "You can not checkIn more then 2 times.");
				}

			} else {
				desc = "Student " + student_name
						+ "  successfully checked in at " + date +" from bus stop";
				AttendanceModel am = new AttendanceModel();
				DateFormat dateFormat1 = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date2 = new Date();
				
				am.setA_id(null);
				am.setDate(date);
				am.setStatus(1);
				am.setStudent_id(student_id);
				am.setLogin_date(date);
				String latestDate=jtx.SaveTime(dateFormat1.format(date2), time_zone);
				am.setLogin_time(latestDate);
				webserviceService.insertStudentAttandance(am);
				NotificationModel noti = new NotificationModel();
				noti.setParent_id(parent_id);
				
				
				String date3 = dateFormat1.format(date2);
				noti.setDate(date3);
				noti.setNoti_desc(desc);
				noti.setRoute_id(studentModel.getS_route_id());
				noti.setStudent_id(student_id);
				noti.setNoti_id(null);
				noti.setNoti_type("Check in");

				int noti_id = webserviceService.insertNotification(noti);
				String msg = desc;
				Map<String, String> m = new HashMap<String, String>();
				
				JSONObject jsonObjectnew1=new JSONObject();
				jsonObjectnew1.put("msg", msg);
				jsonObjectnew1.put("noti_id", Integer.toString(noti_id));
				jsonObjectnew1.put("date", date3);
				jsonObjectnew1.put("noti_type", "checked_in");
				Gcm g = new Gcm();
				if(parent.getChecked_in_on()==0)
				{
					if(device_token.length()==64)
					{
						
						ApplePushNotification ap=new ApplePushNotification();
						ap.pushMessage(device_token,jsonObjectnew1.toString(),msg,parent.getSound_setting());
					}else
					{
						g.pushNotificationToGCM(device_token, jsonObjectnew1.toString());
					}
					//g.pushNotificationToGCM(device_token, jsonObjectnew1.toString());
				}
				if(parent.getSms_checked_in_on()==0)
				{
					String contact_number = parent.getMobile_number();
					Sms_api sms = new Sms_api();
					sms.sendhttp(msg, 0, contact_number);
				}
				
//				Notification for blinking 
				
				JSONObject jsonObjectnew=new JSONObject();
				
				//Map<String, String> jsonObjectnew=new HashMap<String, String>();
				jsonObjectnew.put("noti_type", "blink");
				jsonObjectnew.put("student_id", Integer.toString(student_id));
				jsonObjectnew.put("blink_status", "1");
				Gcm g1= new Gcm();
				if(device_token.length()==64)
				{
					
					ApplePushNotification ap=new ApplePushNotification();
					ap.pushMessage(device_token,jsonObjectnew.toString(),"",parent.getSound_setting());
				}else
				{
					g1.pushNotificationToGCM(device_token, jsonObjectnew.toString());
					
				}
				//g1.pushNotificationToGCM(device_token, jsonObjectnew.toString());
				
				
				webserviceService.updateStudentBlinkStatus(student_id, 1);
				
			}
			AttendanceModel attendancemodel1 = webserviceService
					.getAttendanceByStudentId(student_id);
			if (attendancemodel1 != null) {
				jsonObject.put("result", "success");

			} else {
				jsonObject.put("result", "failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		jsonResult = jsonObject.toString();
		return jsonResult;
	}

	/**
	 * Method for update Lat Lng
	 **/
	/**
	 * Webservice for get real time location of student as well as stop lat lng
	 * by route id and student id
	 **/
	@RequestMapping(value = "webservices/notification", method = RequestMethod.GET)
	@ResponseBody
	public String notification(
			@ModelAttribute("command") NotificationBean noti_bean) {
		String jsonResult = "";
		JSONObject jsonObject = new JSONObject();
		try {

			String method = noti_bean.getMethod();
			String route_name = "";
			int student_id = 0;
			if (method.equals("noti")) {
				if(noti_bean.getStudent_id()!=null && !noti_bean.getStudent_id().equals(""))
				{
					student_id = noti_bean.getStudent_id();
				}
				
				int route_id = noti_bean.getRoute_id();
				String noti_type = noti_bean.getNoti_type();

				RouteModel routemodel = schoolservice.getRouteById(route_id);
				route_name = routemodel.getRoute_name();
				if (noti_type.equals("1")) {

					String desc = "Bus of " + route_name
							+ " is moving on wrong path";
					NotificationModel noti = new NotificationModel();
					noti.setParent_id(0);
					DateFormat dateFormat1 = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Date date2 = new Date();
					String date3 = dateFormat1.format(date2);
					noti.setDate(date3);
					noti.setNoti_desc(desc);
					noti.setRoute_id(route_id);
					noti.setStudent_id(0);
					noti.setNoti_id(null);
					noti.setNoti_type("Wrong Route");
					int noti_id = webserviceService.insertNotification(noti);
					// StudentBean studentbean=new StudentBean();
					getStudentListNotification(schoolservice.getAllStudentByRouteId(route_id),desc, noti_id, date3,"wrong_route");
					jsonObject.put("result", "success");

				} else if (noti_type.equals("2")) {

					String desc = "Bus of " + route_name
							+ " is moving with over speed";
					NotificationModel noti = new NotificationModel();
					noti.setParent_id(0);
					DateFormat dateFormat1 = new SimpleDateFormat(
							"yyyy-MM-dd hh:mm:ss");
					Date date2 = new Date();
					String date3 = dateFormat1.format(date2);
					noti.setDate(date3);
					noti.setNoti_desc(desc);
					noti.setRoute_id(route_id);
					noti.setStudent_id(0);
					noti.setNoti_id(null);
					noti.setNoti_type("Over Speed");
					int noti_id = webserviceService.insertNotification(noti);
					// StudentBean studentbean=new StudentBean();
					getStudentListNotification(
							schoolservice.getAllStudentByRouteId(route_id),
							desc, noti_id, date3,"over_speed");
					jsonObject.put("result", "success");

				} else if (noti_type.equals("3")) {

					NotificationModel noti = new NotificationModel();
					DateFormat dateFormat1 = new SimpleDateFormat(
							"yyyy-MM-dd hh:mm:ss");
					Date date2 = new Date();
					String date3 = dateFormat1.format(date2);
					StudentModel studentModel = studentservice
							.getStudentById(student_id);
					String desc = "Student " + studentModel.getS_fname()
							+ " will come at destination within 15 minutes";
					noti.setDate(date3);
					noti.setNoti_desc(desc);
					noti.setRoute_id(route_id);
					noti.setStudent_id(student_id);
					noti.setNoti_id(null);
					noti.setNoti_type("Pickup your child");
					noti.setParent_id(studentModel.getS_parent_id());
					int noti_id = webserviceService.insertNotification(noti);
					// StudentBean studentbean=new StudentBean();

					LoginModel parent = schoolservice
							.getParentById(studentModel.getS_parent_id());
					String msg = desc;
					Map<String, String> m = new HashMap<String, String>();
					m.put("noti_type", "within");
					m.put("msg", msg);
					m.put("noti_id", Integer.toString(noti_id));
					m.put("date", date3);
					Gcm g = new Gcm();
				//	g.pushNotificationToGCM(parent.getDevice_token(),m.toString());
					if(parent.getDevice_token().length()==64)
					{
						
						ApplePushNotification ap=new ApplePushNotification();
						ap.pushMessage(parent.getDevice_token(),m.toString(),msg,parent.getSound_setting());
					}else
					{
						g.pushNotificationToGCM(parent.getDevice_token(),m.toString());
						
					}
					jsonObject.put("result", "success");

				}
			} else if (method.equals("all_notification")) {

				int parent_id = noti_bean.getParent_id();
				int route_id = noti_bean.getRoute_id();
				List<NotificationBean> noti_model = iterateNoti(webserviceService.getNotificationList(parent_id, route_id));

				jsonObject.put("notifications", noti_model);
				jsonObject.put("result", "success");

			} else if (method.equals("sms")) {
				student_id = noti_bean.getStudent_id();
				String parent_ids=noti_bean.getParent_ids();
				int ins_status=noti_bean.getIns_status();
				
				//String parent_ids= "item1 , item2 , item3";
				String msg = noti_bean.getMsg();
				List<String> items = Arrays.asList(parent_ids.split("\\s*,\\s*"));
				DateFormat dateFormat1 = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				Date date2 = new Date();
				String date3 = dateFormat1.format(date2);
				if(items!=null)
				{
					for(String item: items)
					{
					 
						LoginModel parent = schoolservice.getParentById(Integer.parseInt(item));
						String contact_number = parent.getMobile_number();
						Sms_api sms = new Sms_api();
						if(ins_status==1)
						{
							if(parent.getSms_instant_message()==0)
							{
								sms.sendhttp(msg, 0, contact_number);
							}
						}else if (ins_status==2) {
							if(parent.getSms_morning_before()==0)
							{
								sms.sendhttp(msg, 0, contact_number);
							}
						}else if (ins_status==3) {
							if(parent.getSms_evening_before()==0)
							{
								sms.sendhttp(msg, 0, contact_number);
							}
						}
						String noti_type_str="";
						//	Map<String, String> m = new HashMap<String, String>();
						JSONObject m=new JSONObject();
						m.put("noti_type", "msg");
						m.put("msg", msg);
						m.put("noti_id", "");
						m.put("date", date3);
						Gcm g = new Gcm();
						if(ins_status==1)
						{ 
							noti_type_str="Instant Message";
							if(parent.getInstant_message()==0)
							{
								if(parent.getDevice_token().length()==64)
								{
									
									ApplePushNotification ap=new ApplePushNotification();
									ap.pushMessage(parent.getDevice_token(),m.toString(),msg,parent.getSound_setting());
								}else
								{
									g.pushNotificationToGCM(parent.getDevice_token(),
											m.toString());		
								}
								
								
							
							}
						}else if (ins_status==2) {
							noti_type_str="Morning Before";
							if(parent.getMorning_before()==0)
							{
								if(parent.getDevice_token().length()==64)
								{
									
									ApplePushNotification ap=new ApplePushNotification();
									ap.pushMessage(parent.getDevice_token(),m.toString(),msg,parent.getSound_setting());
								}else
								{
									g.pushNotificationToGCM(parent.getDevice_token(),
											m.toString());
								}
								
							}
						}else if (ins_status==3) {
							noti_type_str="Evening Message";
							if(parent.getEvening_before()==0)
							{
								if(parent.getDevice_token().length()==64)
								{
									
									ApplePushNotification ap=new ApplePushNotification();
									ap.pushMessage(parent.getDevice_token(),m.toString(),msg,parent.getSound_setting());
								}else
								{
									g.pushNotificationToGCM(parent.getDevice_token(),
											m.toString());
								}
								
							}
						}
						
						NotificationModel noti = new NotificationModel();
						DateFormat dateFormat2 = new SimpleDateFormat(
								"yyyy-MM-dd hh:mm:ss");
						Date date4 = new Date();
						String date5 = dateFormat2.format(date2);
						StudentModel studentModel = studentservice.getStudentById(student_id);
						String desc = msg;
						noti.setDate(date3);
						noti.setNoti_desc(desc);
						noti.setRoute_id(studentModel.getS_route_id());
						noti.setStudent_id(student_id);
						noti.setNoti_id(null);
						noti.setNoti_type(noti_type_str);
						noti.setParent_id(parent.getUser_id());
						int noti_id = webserviceService.insertNotification(noti);
						
					}
				}else
				{
					parent_ids=noti_bean.getParent_ids();
				}
				StudentModel studentModel = studentservice
						.getStudentById(student_id);
				//String msg = noti_bean.getMsg();

				LoginModel parent = schoolservice.getParentById(studentModel
						.getS_parent_id());
				String contact_number = parent.getContact_number();
				//Sms_api sms = new Sms_api();
				//sms.sendhttp(msg, 0, contact_number);
				
				
				
				jsonObject.put("result", "success");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		jsonResult = jsonObject.toString();
		return jsonResult;
	}

	private List<StudentBean> getStudentListNotification(
			List<StudentModel> students, String desc, int noti_id, String date3,String type) {

		List<StudentBean> beans = null;
		if (students != null && !students.isEmpty()) {
			beans = new ArrayList<StudentBean>();
			StudentBean bean = null;
			for (StudentModel student : students) {
				bean = new StudentBean();
				System.out.println(student.getP_status_id());
				SchoolModel stud = new SchoolModel();
				stud = schoolservice.getSchoolById(student.getS_school_id());
				LoginModel parent = schoolservice.getParentById(student
						.getS_parent_id());
				String msg = desc;
				/*Map<String, String> m = new HashMap<String, String>();
				m.put("msg", msg);
				m.put("noti_id", Integer.toString(noti_id));
				m.put("date", date3);*/
				try{
				JSONObject jsonObjectnew=new JSONObject();
				jsonObjectnew.put("noti_type", type);
				jsonObjectnew.put("msg", msg);
				jsonObjectnew.put("noti_id", Integer.toString(noti_id));
				jsonObjectnew.put("date", date3);
				Gcm g = new Gcm();
				if(type.equals("wrong_route"))
				{
					if(parent.getWrong_route_on()==0)
					{
						if(parent.getDevice_token().length()==64)
						{
							
							ApplePushNotification ap=new ApplePushNotification();
							ap.pushMessage(parent.getDevice_token(),jsonObjectnew.toString(),msg,parent.getSound_setting());
						}else
						{
							g.pushNotificationToGCM(parent.getDevice_token(), jsonObjectnew.toString());
						}
				 
					}
					
				}
				if(type.equals("over_speed"))
				{
					if(parent.getDevice_token().length()==64)
					{
						
						ApplePushNotification ap=new ApplePushNotification();
						ap.pushMessage(parent.getDevice_token(),jsonObjectnew.toString(),msg,parent.getSound_setting());
					}else
					{
						g.pushNotificationToGCM(parent.getDevice_token(), jsonObjectnew.toString());
					}
					//g.pushNotificationToGCM(parent.getDevice_token(), jsonObjectnew.toString());
				}
				
				beans.add(bean);
				}catch(Exception e)
				{
					System.out.println(e);
				}
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Method for get parent message
	 **/
	private List<NotificationBean> iterateNoti(List<NotificationModel> notilist) {

		List<NotificationBean> beans = null;
		if (notilist != null && !notilist.isEmpty()) {
			beans = new ArrayList<NotificationBean>();
			NotificationBean bean = null;
			for (NotificationModel msgIt : notilist) {
				/*
				 * LoginModel parent =
				 * schoolservice.getParentById(msgIt.getSender_id());
				 */
				bean = new NotificationBean();
				bean.setDate(msgIt.getDate());
				bean.setMsg(msgIt.getNoti_desc());
				bean.setNoti_id(msgIt.getNoti_id());
				bean.setParent_id(msgIt.getParent_id());
				bean.setNoti_type(msgIt.getNoti_type());
				bean.setStudent_id(msgIt.getStudent_id());
				bean.setRoute_id(msgIt.getRoute_id());
				/* bean.set */
				beans.add(bean);
			}

		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Function for forgot password
	 **/
	@RequestMapping(value = "webservices/forgot_password", method = RequestMethod.GET)
	@ResponseBody
	public String forgot_password(@ModelAttribute("command") LoginBean login) {
		String jsonResult = "";
		JSONObject jsonObject = new JSONObject();
		try {
			String name = login.getFirst_name();
			String mobile_number = login.getContact_number();
			String email = login.getUser_email();
			LoginModel found = loginservice.login(email, "");
			if (found != null) {
				Sms_api sms = new Sms_api();
				String msg = "Dear "
						+ name
						+ " ,&#13;&#10;Your Login details as below&#13;&#10;Email: "
						+ found.getUser_email() + "&#13;&#10;Username: "
						+ found.getUser_name() + "&#13;&#10;password: "
						+ found.getUser_pass() + "";
				sms.sendhttp(msg, 0, found.getMobile_number());
				jsonObject.put("result", "success");
				jsonObject.put("responseMessage", "Successfully request sent");
			} else {
				jsonObject.put("result", "failed");
				jsonObject.put("responseMessage",
						"Please enter registered email");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		jsonResult = jsonObject.toString();
		return jsonResult;
	}
	/**
	 * Webservice for send message using webservice to school admin from driver
	 **/
	@RequestMapping(value = "webservices/driver_send_message", method = RequestMethod.GET)
	@ResponseBody
	public String driver_send_message(@ModelAttribute("command") SchoolMessageBean msg) {
		String jsonResult = "";
		JSONObject jsonObject = new JSONObject();
		try {
			DriverMessage pm = new DriverMessage();
			pm.setMsg(msg.getMsg());
			pm.setMsg_id(null);
			pm.setReciever_id(msg.getReciever_id());
			pm.setSender(1);
			pm.setSender_id(msg.getSender_id());
			pm.setStatus(0);
			pm.setSchool_id(0);
			pm.setU_check("");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			pm.setTime(dateFormat.format(date));
			webserviceService.sendMessageDriver(pm);
			jsonObject.put("result", "success");
			jsonObject.put("responseMessage", "message sent successfully");

		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			System.out.println(e);
		}

		jsonResult = jsonObject.toString();
		return jsonResult;
	}
	/**
	 * Method for get message from school admin by driver 
	 **/
	@RequestMapping(value = "webservices/driver_chatting", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String driver_chatting(@ModelAttribute("command") SchoolMessageBean msg) {
		String jsonResult = "";
		JSONObject jsonObject = new JSONObject();
		try {
			List<SchoolMessageBean> msg_model = iterateMsgDriver(
					webserviceService
							.getMessageByDriverId(msg.getReciever_id()),
					msg.getReciever_id());
		
			jsonObject.put("details", msg_model);
			jsonObject.put("result", "success");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		jsonResult = jsonObject.toString();
		return jsonResult;
	}
	
	/**
	 * Method for get Driver message
	 **/
	private List<SchoolMessageBean> iterateMsgDriver(List<DriverMessage> driverMsg,
			int user_id) {

		List<SchoolMessageBean> beans = null;
		if (driverMsg != null && !driverMsg.isEmpty()) {
			beans = new ArrayList<SchoolMessageBean>();
			SchoolMessageBean bean = null;
			for (DriverMessage msgIt : driverMsg) {
				/*
				 * LoginModel parent =
				 * schoolservice.getParentById(msgIt.getSender_id());
				 */bean = new SchoolMessageBean();
				bean.setMsg(msgIt.getMsg());
				bean.setSender(msgIt.getSender());
				bean.setSender_id(msgIt.getSender_id());
				bean.setStatus(msgIt.getStatus());
				bean.setReciever_id(msgIt.getReciever_id());
				bean.setTime(msgIt.getTime());
				/* bean.set */
				beans.add(bean);
			}

		} else {
			System.out.println("empty");
		}
		return beans;
	}
	/*
	 * @RequestMapping(value="/login.html",method=RequestMethod.GET) public
	 * ModelAndView login() { return new ModelAndView("home/login"); }
	 */
	@RequestMapping(value = "webservices/changePassword", method = RequestMethod.GET)
	@ResponseBody
	public String changePassword(@ModelAttribute("command") LoginBean login) {
		String jsonResult = "";

		try {
			JSONObject jsonArray = new JSONObject();
			String username = login.getUser_email();
			String password = login.getUser_pass();
			String new_password=login.getFirst_name();
			LoginModel found = loginservice.login(username, password);
			if (found != null && found.getUser_pass().equals(password)) {
 
				LoginModel loginmodel=new LoginModel();
				loginmodel.setUser_pass(new_password);
				webserviceService.editParentById(found.getUser_id(), loginmodel);
				jsonArray.put("result", "success");
				jsonArray.put("responseMessage", "Password has been changed successfully");
			} else {
				jsonArray.put("result", "failed");
				jsonArray.put("responseMessage","Username or password does not match");
				jsonArray.put("status", "404");
			}
			jsonResult = jsonArray.toString();

		} catch (Exception e) {
			System.out.println(e);
		}

		return jsonResult;
	}
	
	@RequestMapping(value = "webservices/get_student_blink", method = RequestMethod.GET)
	@ResponseBody
	public String get_student_blink(@ModelAttribute("command") StudentBean student) {
		String jsonResult = "";
		JSONObject jsonObject = new JSONObject();
		try {
			StudentModel studentModel=new StudentModel();
			studentModel = studentservice.getStudentById(student.getStudent_id());
			jsonObject.put("blink_status", studentModel.getBlink_status());
			jsonObject.put("result", "success");
			jsonResult=jsonObject.toString();
		}catch(Exception e)
		{
			System.out.println(e);
		}
	
		return jsonResult;
	}	
	
	@RequestMapping(value="webservices/save_parent_setting",method=RequestMethod.GET)
	@ResponseBody
	public String save_parent_setting(@ModelAttribute("command") LoginBean userbean){
		String jsonResult="";
		JSONObject jsonObject = new JSONObject();
		try{
			int parent_id=userbean.getUser_id();
			LoginModel found=new LoginModel();
			found=schoolservice.getParentById(parent_id);
			if(found!=null)
			{
				LoginModel usermodel=new LoginModel();
				usermodel.setLang(userbean.getLang());
				usermodel.setChat_on(userbean.getChat_on());
				usermodel.setNoti_on(userbean.getNoti_on());
				usermodel.setChecked_in_on(userbean.getChecked_in_on());
				usermodel.setChecked_out_on(userbean.getChecked_out_on());
				usermodel.setSpeed_on(userbean.getSpeed_on());
				usermodel.setMax_speed(userbean.getMax_speed());
				usermodel.setWrong_route_on(userbean.getWrong_route_on());
				usermodel.setSms_checked_in_on(userbean.getSms_checked_in_on());
				usermodel.setSms_checked_out_on(userbean.getSms_checked_out_on());
				usermodel.setSms_max_speed(userbean.getSms_max_speed());
				usermodel.setSms_speed_on(userbean.getSms_speed_on());
				usermodel.setSms_wrong_route_on(userbean.getSms_wrong_route_on());
				usermodel.setInstant_message(userbean.getInstant_message());
				usermodel.setMorning_before(userbean.getMorning_before());
				usermodel.setEvening_before(userbean.getEvening_before());
				usermodel.setSms_instant_message(userbean.getSms_instant_message());
				usermodel.setSms_morning_before(userbean.getSms_morning_before());
				usermodel.setSms_evening_before(userbean.getSms_evening_before());
				usermodel.setSound_setting(userbean.getSound_setting());
				usermodel.setChat_sound(userbean.getChat_sound());
				schoolservice.saveParentSetting(parent_id, usermodel);
				
				jsonObject.put("result", "success");
				jsonResult=jsonObject.toString();
			}else{
				jsonObject.put("result", "failed");
				jsonResult=jsonObject.toString();
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
		return jsonResult;
	}
	@RequestMapping(value="webservices/sos",method=RequestMethod.POST)
	@ResponseBody
	public String sos(@RequestBody String jsonNew){
		String jsonResult="";
		try{
			JSONObject jsonArray = new JSONObject(jsonNew);
			System.out.println("Hello"+jsonArray.get("driver_id"));
			int driver_id= Integer.parseInt((String)jsonArray.get("driver_id"));	
			int school_id=Integer.parseInt((String) jsonArray.get("school_id"));
			String message=(String) jsonArray.get("message");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, 1);
			String date_new=new SimpleDateFormat("yyyy/MM/dd").format(c.getTime());
			DriverDocModel doc=new DriverDocModel();
			doc.setInsurance_document_name(message);
			doc.setInsurance_document_expiry(date_new);
			doc.setInsurance_end_date(date_new);
			doc.setRemind_day("10");
			doc.setV_id(driver_id);
			doc.setSchool_id(school_id);
			doc.setInsurance_card_copy("");
			doc.setStatus(0);
			doc.setNoti_type(2);
			vechileservice.addDriverDoc(doc);	
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "success");
			jsonResult=jsonObject.toString();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return jsonResult;
	}
	/**
	 * Function for driver lat lng inserted
	 **/
	
	@RequestMapping(value = "webservices/add_student_absent", method = RequestMethod.POST)
	@ResponseBody
	public String add_student_absent(@RequestBody String jsonNew) {
		String jsonResult = "";
		
		JSONObject jsonArray = new JSONObject();
		try {
			JSONObject jsonArray1 = new JSONObject(jsonNew);
			String student_id = (String) jsonArray1.get("student_id");
			String absent_date=(String) jsonArray1.get("absent_date");
			String reason=(String) jsonArray1.get("reason");
			String[] myData = absent_date.split(",");
			for (String s: myData) {
			   StudentAbsentModel sam= new StudentAbsentModel();
			   sam.setA_id(null);
			   sam.setAbsent_date(s);
			   sam.setReason(reason);
			   sam.setStudent_id(Integer.parseInt(student_id));
			   studentservice.addStudentAbsent(sam);
			}
			jsonArray.put("student_id", student_id);
			jsonArray.put("result", "success");
			jsonArray.put("responseMessage", "absent has been added successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonResult = jsonArray.toString();
		return jsonResult;
	}
	/**
	 * Function for driver lat lng inserted
	 **/
	@RequestMapping(value = "webservices/add_student_absent_by_driver", method = RequestMethod.POST)
	@ResponseBody
	public String add_student_absent_by_driver(@RequestBody String jsonNew) {
		String jsonResult = "";
		
		JSONObject jsonArray = new JSONObject();
		try {
			JSONObject jsonArray1 = new JSONObject(jsonNew);
			String student_id = (String) jsonArray1.get("student_id");
			String absent_date=(String) jsonArray1.get("absent_date");
			String reason="";
		    StudentAbsentModel sam= new StudentAbsentModel();
		    sam.setA_id(null);
		    sam.setAbsent_date(absent_date);
		    sam.setReason(reason);
		    sam.setStudent_id(Integer.parseInt(student_id));
		    studentservice.addStudentAbsent(sam);
			jsonArray.put("student_id", student_id);
			jsonArray.put("result", "success");
			jsonArray.put("responseMessage", "absent has been added successfully");
			StudentModel studentModel = studentservice.getStudentById(Integer.parseInt(student_id));
			LoginModel parent = schoolservice.getParentById(studentModel.getS_parent_id());
			
			
			
			DateFormat dateFormat1 = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date date2 = new Date();
			String date3 = dateFormat1.format(date2);
			NotificationModel noti = new NotificationModel();
			noti.setParent_id(studentModel.getS_parent_id());
			noti.setDate(date3);
			noti.setNoti_desc("Your child "+studentModel.getS_fname()+" is not available on stop.");
			noti.setRoute_id(studentModel.getS_route_id());
			noti.setStudent_id(Integer.parseInt(student_id));
			noti.setNoti_id(null);
			noti.setNoti_type("absent_by_driver");
			//int noti_id = webserviceService.insertNotification(noti);
			JSONObject jsonObjectnew=new JSONObject();
			jsonObjectnew.put("noti_type", "absent_by_driver");
			jsonObjectnew.put("msg","Your child "+studentModel.getS_fname()+" is not available on stop.");
			//jsonObjectnew.put("noti_id", Integer.toString(noti_id));
			jsonObjectnew.put("date", date3);
			Gcm g = new Gcm();
			if(parent.getDevice_token().length()==64)
			{
				ApplePushNotification ap=new ApplePushNotification();
				ap.pushMessage(parent.getDevice_token(),jsonObjectnew.toString(),"Your child "+studentModel.getS_fname()+" is not available on stop.",parent.getSound_setting());
			}else
			{
				g.pushNotificationToGCM(parent.getDevice_token(), jsonObjectnew.toString());
			}
			//g.pushNotificationToGCM(parent.getDevice_token(), jsonObjectnew.toString());
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonResult = jsonArray.toString();
		return jsonResult;
	}
	/**
	 * Function for driver lat lng inserted
	 **/
	@RequestMapping(value = "webservices/get_student_present_absent", method = RequestMethod.POST)
	@ResponseBody
	public String get_student_present_absent(@RequestBody String jsonNew) {
		String jsonResult = "";
		
		JSONObject jsonResponse= new JSONObject();
		try {
			JSONObject jsonArray = new JSONObject(jsonNew);
			String start_date= (String) jsonArray.get("start_date");
			String end_date= (String) jsonArray.get("end_date");
			String student_id_str = (String) jsonArray.get("student_id");
			String school_id=(String) jsonArray.get("school_id");
			int student_id=Integer.parseInt(student_id_str);
			
			int total_present=0;
			int absent_day=0;
			int daysInMonth=0;
			total_present=schoolservice.getStudentAttendanceListByTwoDate(student_id,start_date,end_date).size();
			
			List<HolidayBean> a=getHolidayList(schoolservice.getAllHolidayByTwoDate(Integer.parseInt(school_id),start_date,end_date));
			List<HolidayDeletedBean> b=getDeletedHolidayList(schoolservice.getAllDeletedHoliday(Integer.parseInt(school_id)));
			try{
			for(int i=0;i<a.size();i++)
			{
				for(int j=0;j<b.size();j++)
				{
					if(a.get(i).getH_id().equals(b.get(j).getHoliday_id()))
					{
						a.remove(i);
					}
				}
			}
			}catch(Exception e)
			{
				System.out.println(e);
			}
			LocalDate date = LocalDate.parse(start_date);
			LocalDate date1 = LocalDate.parse(end_date);
			int days = Days.daysBetween(date, date1).getDays();
			absent_day=days-total_present;
			JSONObject jsonRetun= new JSONObject();
			if(a!=null)
			{
				absent_day=absent_day- a.size();
				jsonRetun.put("holiday_days", a.size());
			}else
			{
				jsonRetun.put("holiday_days",0);
			}
			
			
		jsonRetun.put("present_day", total_present);
		jsonRetun.put("absent_day", absent_day);
		jsonRetun.put("result", "success");
		jsonResult = jsonRetun.toString();

			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return jsonResult;
	}
	/**
	 * Method for get all hodiday list
	 * **/
	private List<HolidayDeletedBean> getDeletedHolidayList(List<HolidayDeletedModel> holiday) {

		List<HolidayDeletedBean> beans = null;
		if (holiday != null && !holiday.isEmpty()) {
			beans = new ArrayList<HolidayDeletedBean>();
			HolidayDeletedBean bean = null;
			for (HolidayDeletedModel holi : holiday) {
				bean = new HolidayDeletedBean();
				bean.setDel_id(holi.getDel_id());
				bean.setHoliday_id(holi.getHoliday_id());
				bean.setSchool_id(holi.getSchool_id());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "webservices/driverDetails", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String driverDetails(@RequestBody String jsonNew) {
		String jsonResult = "";

		try {
			JSONObject jsonArray1 = new JSONObject(jsonNew);
			JSONObject jsonArray = new JSONObject();
			String driver_id_str = (String)jsonArray1.get("driver_id");
			int driver_id=Integer.parseInt(driver_id_str);
			DriverModel drivermodel = schoolservice.getDriverById(driver_id);
			jsonArray.put("result", "success");
			jsonArray.put("f_name",drivermodel.getDriver_fname());
			jsonArray.put("l_name", drivermodel.getDriver_lname());
			jsonArray.put("middle_name", drivermodel.getMiddle_name());
			jsonArray.put("email", drivermodel.getD_email());
			jsonArray.put("dob", drivermodel.getDob());
			jsonArray.put("mobile_number", drivermodel.getContact_number());
			jsonArray.put("username", drivermodel.getUsername());
			jsonArray.put("password", drivermodel.getPassword());
			jsonArray.put("nationlity", drivermodel.getNationality());
			jsonArray.put("blood_group", drivermodel.getBlood_group());
			jsonArray.put("licence_expiry_date", drivermodel.getLicence_expiry());
		 	jsonArray.put("image_path",Assets.BASE_URL+Assets.DRIVER_UPLOAD_PATH_DIS+drivermodel.getImage_path());
			jsonArray.put("documents", prepareListOfDriverDoc(schoolservice.getDriverDocument(driver_id)));
			jsonResult=jsonArray.toString();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return jsonResult;
	}	
	/**
	 * Method For get all schools
	 **/
	private List<DriverDocBean> prepareListOfDriverDoc(List<DriverDocModel> drivers) {

		List<DriverDocBean> beans = null;
		if (drivers != null && !drivers.isEmpty()) {
			beans = new ArrayList<DriverDocBean>();
			DriverDocBean bean = null;
			for (DriverDocModel vehicle : drivers) {
				bean = new DriverDocBean();
				bean.setInsurance_card_copy(Assets.BASE_URL+Assets.INSURANCE_CARD_COPY+vehicle.getInsurance_card_copy());
				bean.setInsurance_document_expiry(vehicle.getInsurance_document_expiry());
				bean.setInsurance_document_name(vehicle.getInsurance_document_name());
				bean.setRemind_day(vehicle.getRemind_day());
				bean.setSchool_id(vehicle.getSchool_id());
				bean.setV_doc_id(vehicle.getV_doc_id());
				bean.setV_id(vehicle.getV_id());
				bean.setStatus(vehicle.getStatus());
				bean.setInsurance_end_date(vehicle.getInsurance_end_date());
				beans.add(bean);
			}
		} else {
			System.out.println("empty Result");
		}
		return beans;

	}
	/**
	 * Method For get all schools
	 **/
	private DriverBean prepareListOfDriver(DriverModel drivers) {

		 
		DriverBean bean = null;
		if (drivers != null) {
			bean = new DriverBean();
			
			 
			bean.setBlood_group(drivers.getBlood_group());
			bean.setContact_number(drivers.getContact_number());
			bean.setD_email(drivers.getD_email());
			bean.setDevice_token(drivers.getDevice_token());
			bean.setDob(drivers.getDob());
			bean.setDriver_fname(drivers.getDriver_fname());
			bean.setDriver_id(drivers.getDriver_id());
			bean.setDriver_lname(drivers.getDriver_lname());
			bean.setDriver_school_id(drivers.getDriver_school_id());
			bean.setLicence_expiry(drivers.getLicence_expiry());
			bean.setUsername(drivers.getUsername());
			bean.setPassword(drivers.getPassword());
			bean.setNationality(drivers.getNationality());
			bean.setImage_path(drivers.getImage_path());
			
			
		} else {
			System.out.println("empty Result");
		}
		return bean;

	}
	@SuppressWarnings("unused")
	@RequestMapping(value = "webservices/parentSpeedNotification", method = RequestMethod.POST)
	@ResponseBody
	public String parentSpeedNotification(@RequestBody String jsonNew) {
		String jsonResult = "";

		try {
			JSONObject jsonArray1 = new JSONObject(jsonNew);
			JSONObject jsonArray = new JSONObject();
			
			String parent_ids =(String)jsonArray1.get("parent_ids");
			String speed_str=(String) jsonArray1.get("max_speed");
			String route_id_str=(String)jsonArray1.get("route_id");
			int route_id=Integer.parseInt(route_id_str);
			int speed=Integer.parseInt(speed_str);
			//String parent_ids= "item1 , item2 , item3";
			List<String> items = Arrays.asList(parent_ids.split("\\s*,\\s*"));
			DateFormat dateFormat1 = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			Date date2 = new Date();
			String msg="Your child's school bus is running with speed "+speed;
			String date3 = dateFormat1.format(date2);
			if(items!=null)
			{
				for(String item: items)
				{
				 
					LoginModel parent = schoolservice.getParentById(Integer.parseInt(item));
					if(parent.getMobile_number()!=null)
					{
						String contact_number = parent.getMobile_number();
						int sms_max_speed=parent.getSms_max_speed();
						int sms_speed_status=parent.getSms_speed_on();
						if(sms_speed_status==0)
						{
							if(speed>sms_max_speed)
							{
								Sms_api sms = new Sms_api();
								sms.sendhttp(msg, 0, contact_number);
							}
						}
					}
					
					
					int noti_max_speed=parent.getMax_speed();
					int noti_speed_status=parent.getSpeed_on();	
					if(noti_speed_status==0)
					{
						String desc =msg;
						NotificationModel noti = new NotificationModel();
						noti.setParent_id(0);
						
						noti.setDate(date3);
						noti.setNoti_desc(desc);
						noti.setRoute_id(route_id);
						noti.setStudent_id(0);
						noti.setNoti_id(null);
						noti.setNoti_type("Over Speed");
						int noti_id = webserviceService.insertNotification(noti);
						
				//	Map<String, String> m = new HashMap<String, String>();
						JSONObject m=new JSONObject();
						m.put("noti_type", "over_speed");
						m.put("msg", msg);
						m.put("noti_id", "");
						m.put("date", date3);
						System.out.println("JsonArray="+m.toString());
						Gcm g = new Gcm();
						g.pushNotificationToGCM(parent.getDevice_token(),
								m.toString());
					}
					
				
					
					
				}
			}else{
				LoginModel parent = schoolservice.getParentById(Integer.parseInt(parent_ids));
				String contact_number = parent.getMobile_number();
				int sms_max_speed=parent.getSms_max_speed();
				int sms_speed_status=parent.getSms_speed_on();
				if(sms_speed_status==0)
				{
					if(speed>sms_max_speed)
					{
						Sms_api sms = new Sms_api();
						sms.sendhttp(msg, 0, contact_number);
					}
				}
				
				int noti_max_speed=parent.getMax_speed();
				int noti_speed_status=parent.getSpeed_on();	
				if(noti_speed_status==0)
				{
					String desc =msg;
					NotificationModel noti = new NotificationModel();
					noti.setParent_id(0);
					
					noti.setDate(date3);
					noti.setNoti_desc(desc);
					noti.setRoute_id(route_id);
					noti.setStudent_id(0);
					noti.setNoti_id(null);
					noti.setNoti_type("Over Speed");
					int noti_id = webserviceService.insertNotification(noti);
					
					//Map<String, String> m = new HashMap<String, String>();
					JSONObject m=new JSONObject();
					m.put("noti_type", "over_speed");
					m.put("msg", msg);
					m.put("noti_id", "");
					m.put("date", date3);
					System.out.println("JsonArray="+m.toString());
					Gcm g = new Gcm();

					if(parent.getDevice_token().length()==64)
					{
						
						ApplePushNotification ap=new ApplePushNotification();
						ap.pushMessage(parent.getDevice_token(),m.toString(),msg,parent.getSound_setting());
					}else
					{
						g.pushNotificationToGCM(parent.getDevice_token(),
								m.toString());
					}
					
				}
				
			}
			jsonArray.put("responseMessage", "Notification sent successfully");
			jsonArray.put("result", "success");
			jsonResult=jsonArray.toString();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	@RequestMapping(value="webservices/save_driver_setting",method=RequestMethod.GET)
	@ResponseBody
	public String save_driver_setting(@ModelAttribute("command") DriverBean userbean){
		String jsonResult="";
		JSONObject jsonObject = new JSONObject();
		try{
			int driver_id=userbean.getDriver_id();
			DriverModel found=new DriverModel();
			found=schoolservice.getDriverById(driver_id);
			if(found!=null)
			{
				DriverModel usermodel=new DriverModel();
				usermodel.setLang(userbean.getLang());
				schoolservice.saveDriverSetting(driver_id, usermodel);
				jsonObject.put("result", "success");
				jsonResult=jsonObject.toString();
			}else{
				jsonObject.put("result", "failed");
				jsonResult=jsonObject.toString();
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
		return jsonResult;
	}
	
	
	/**
	 *Method for get site admin phone number 
	 ***/
	@RequestMapping(value="webservices/getSiteNumber",method=RequestMethod.GET)
	@ResponseBody
	public String getSiteNumber(){
		String jsonResult="";
		JSONObject jsonObject = new JSONObject();
		try{
			
			LoginModel found=new LoginModel();
			found=schoolservice.getParentById(1);
			if(found!=null)
			{
				jsonObject.put("mobile_number",found.getMobile_number());
				jsonObject.put("phone_number",found.getContact_number());
				jsonObject.put("result", "success");
				jsonResult=jsonObject.toString();
			}else{
				jsonObject.put("result", "failed");
				jsonResult=jsonObject.toString();
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
		return jsonResult;
	}
	/**
	 * Method for update Lat Lng
	 **/
	/**
	 * Webservice for get real time location of student as well as stop lat lng
	 * by route id and student id
	 **/
	@RequestMapping(value = "webservices/trackNotification", method = RequestMethod.GET)
	@ResponseBody
	public String trackNotification(
			@ModelAttribute("command") NotificationBean noti_bean) {
		String jsonResult = "";
		JSONObject jsonObject = new JSONObject();
		try {

			String route_name = "";
			int student_id = 0;	
			String parent_ids=noti_bean.getParent_ids();
				
				//String parent_ids= "item1 , item2 , item3";
				String msg = noti_bean.getMsg();
				List<String> items = Arrays.asList(parent_ids.split("\\s*,\\s*"));
				DateFormat dateFormat1 = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				Date date2 = new Date();
				String date3 = dateFormat1.format(date2);
				if(items!=null)
				{
					for(String item: items)
					{
					 
						LoginModel parent = schoolservice.getParentById(Integer.parseInt(item));
						String noti_type_str="";
						//	Map<String, String> m = new HashMap<String, String>();
						JSONObject m=new JSONObject();
						m.put("noti_type", "msg");
						m.put("msg", msg);
						m.put("noti_id", "");
						m.put("date", date3);
						Gcm g = new Gcm();
						
						if(parent.getDevice_token().length()==64)
						{
							
							ApplePushNotification ap=new ApplePushNotification();
							ap.pushMessage(parent.getDevice_token(),m.toString(),msg,parent.getSound_setting());
						}else
						{
							g.pushNotificationToGCM(parent.getDevice_token(),
									m.toString());		
						}
						
					}
				}else
				{
					parent_ids=noti_bean.getParent_ids();
					LoginModel parent = schoolservice.getParentById(Integer.parseInt(parent_ids));
					String noti_type_str="";
					//	Map<String, String> m = new HashMap<String, String>();
					JSONObject m=new JSONObject();
					m.put("noti_type", "track_noti");
					m.put("msg", msg);
					m.put("noti_id", "");
					m.put("date", date3);
					Gcm g = new Gcm();
					
					if(parent.getDevice_token().length()==64)
					{
						
						ApplePushNotification ap=new ApplePushNotification();
						ap.pushMessage(parent.getDevice_token(),m.toString(),msg,parent.getSound_setting());
					}else
					{
						g.pushNotificationToGCM(parent.getDevice_token(),
								m.toString());		
					}
					
					
				}
				jsonObject.put("result", "success");
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		jsonResult = jsonObject.toString();
		return jsonResult;
	}
}
