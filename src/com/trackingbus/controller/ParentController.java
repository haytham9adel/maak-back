package com.trackingbus.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.trackingbus.bean.AttendanceBean;
import com.trackingbus.bean.CountryBean;
import com.trackingbus.bean.DepartmentBean;
import com.trackingbus.bean.DriverBean;
import com.trackingbus.bean.DriverTrack;
import com.trackingbus.bean.GoogleResponse;
import com.trackingbus.bean.HolidayBean;
import com.trackingbus.bean.HolidayDeletedBean;
import com.trackingbus.bean.LoginBean;
import com.trackingbus.bean.NotificationBean;
import com.trackingbus.bean.RouteBean;
import com.trackingbus.bean.RouteLatLng;
import com.trackingbus.bean.SchoolBean;
import com.trackingbus.bean.StaffBean;
import com.trackingbus.bean.StudentAbsentBean;
import com.trackingbus.bean.StudentBean;
import com.trackingbus.bean.StudentTracking;
import com.trackingbus.bean.VechileBean;
import com.trackingbus.model.AttendanceModel;
import com.trackingbus.model.CityModel;
import com.trackingbus.model.CountryModel;
import com.trackingbus.model.DepartmentModel;
import com.trackingbus.model.DriverAttendanceModel;
import com.trackingbus.model.DriverModel;
import com.trackingbus.model.DriverTrackModel;
import com.trackingbus.model.HolidayDeletedModel;
import com.trackingbus.model.HolidayModel;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.NotificationModel;
import com.trackingbus.model.RouteLatLngModel;
import com.trackingbus.model.RouteModel;
import com.trackingbus.model.SchoolModel;
import com.trackingbus.model.StaffModel;
import com.trackingbus.model.StudentAbsentModel;
import com.trackingbus.model.StudentModel;
import com.trackingbus.model.StudentTrackingModel;
import com.trackingbus.model.VehicleModel;
import com.trackingbus.service.FrontDashboardService;
import com.trackingbus.service.LoginService;
import com.trackingbus.service.SchoolService;
import com.trackingbus.service.StaffService;
import com.trackingbus.service.StudentService;
import com.trackingbus.service.VehicleService;

import org.apache.commons.codec.binary.Base64;

@Controller
public class ParentController {

	@Autowired
	private SchoolService schoolservice;

	@Autowired
	private StaffService staffservice;

	@Autowired
	private StudentService studentservice;

	@Autowired
	private LoginService loginservice;

	@Autowired
	private FrontDashboardService frontService;

	@Autowired
	private VehicleService vechileservice;
	
	@Autowired
	private com.trackingbus.service.WebservicesSer webserviceService;

	@RequestMapping(value = "/parentDashboard", method = RequestMethod.GET)
	public ModelAndView parentDashboard(
			@ModelAttribute("command") SchoolBean schoolbean,
			BindingResult result, ModelMap model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (session.getAttribute("userRole") != "Parent") {
			return new ModelAndView("redirect:/login.html");
		}
		int total_schools = schoolservice.listSchools().size();
		int total_students = schoolservice.getAllStudent().size();
		int total_buses = schoolservice.getAllVehicleCount();
		/*
		 * model.put("total_student",total_students);
		 * model.put("total_schools",total_schools);
		 * model.put("total_buses",total_buses); model.put("Heading_box",
		 * "Total Schools");
		 */
		// session.setAttribute("new_school_id", null);
		return new ModelAndView("redirect:parent/studentList");

	}

	/**
	 * Function for view parent
	 **/
	@RequestMapping(value = "parent/studentList", method = RequestMethod.GET)
	public ModelAndView studentList(
			@ModelAttribute("command") LoginBean parents, BindingResult result,
			StudentBean student, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole") != "Parent") {
			return new ModelAndView("redirect:/login.html");
		}
		LoginModel parent = schoolservice.getParentById((Integer) session
				.getAttribute("user_id"));
		Map<String, Object> model = new HashMap<String, Object>();

		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		if (parent != null) {
			model.put("allParent", getParentList(schoolservice
					.getParentByPIdList(parent.getP_status())));
			model.put("heading", "Student List");
			model.put("parent", parent);
			// model.put("students",
			// getStudentList(studentservice.getStudentByParent(parents.getUser_id())));
			model.put("students", getStudentList(studentservice
					.getStudentByParentStatus(parent.getP_status())));

			return new ModelAndView("parent/student_list", model);
		} else {
			return new ModelAndView("redirect:studentList");
		}

	}

	/* Method for get country list */
	private List<LoginBean> getParentList(List<LoginModel> users) {

		List<LoginBean> beans = null;
		if (users != null && !users.isEmpty()) {
			beans = new ArrayList<LoginBean>();
			LoginBean bean = null;
			for (LoginModel user : users) {
				bean = new LoginBean();
				SchoolModel stud = new SchoolModel();
				stud = schoolservice.getSchoolById(user.getSchool_id());
				bean.setUser_id(user.getUser_id());
				bean.setSchool_name(stud.getSchool_name());
				bean.setP_status(user.getP_status());
				bean.setFirst_name(user.getFirst_name());
				bean.setLast_name(user.getLast_name());
				bean.setUser_name(user.getUser_name());
				bean.setUser_email(user.getUser_email());
				bean.setContact_number(user.getContact_number());
				bean.setPermission(user.getPermission());
				bean.setUser_pass(user.getUser_pass());
				bean.setMobile_number(user.getMobile_number());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
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
				bean.setS_fname(student.getS_fname());
				bean.setS_lname(student.getS_lname());
				bean.setS_email(student.getS_email());
				bean.setS_address(student.getS_address());
				bean.setS_contact(student.getS_contact());
				bean.setS_image_path(student.getS_image_path());
				bean.setFamily_name(student.getFamily_name());
				bean.setFather_name(student.getFather_name());
				bean.setGrand_name(student.getGrand_name());
				bean.setS_route_id(student.getS_route_id());
				bean.setBlink_status(student.getBlink_status());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Function for view Student
	 **/
	@RequestMapping(value = "parent/viewStudent", method = RequestMethod.GET)
	public ModelAndView viewStudent(
			@ModelAttribute("command") StudentBean students,
			BindingResult result, HttpServletRequest request) {
		try {
			String q = request.getParameter("q");
			byte[] decodedBytes = Base64.decodeBase64("" + q + "");
			int student_id = Integer.parseInt(new String(decodedBytes));
			StudentModel studentModel = studentservice
					.getStudentById(student_id);
			Map<String, Object> model = new HashMap<String, Object>();
			if (studentModel != null) {
				HttpSession session = request.getSession();
				if (session.getAttribute("userRole") != "Parent") {
					return new ModelAndView("redirect:/login.html");
				}
				
				
				
				
				
				
				
				
				if(studentModel.getS_route_id()!=null)
				{
					RouteModel r= schoolservice.getRouteById(studentModel.getS_route_id());
					System.out.println("route_id"+r.getRoute_id());
					model.put("driver_details", schoolservice.getDriverByRouteId(r.getRoute_id()));
				}
				model.put("heading", "View Student");
				model.put("student", studentModel);
				model.put("routes", getAllRoute(schoolservice
						.listRoute(student_id)));
				RouteLatLngModel latlngmodel = studentservice
						.getLatLngBySId(student_id);
				model.put("allParent", getParentList(schoolservice
						.getParentByPIdList(studentModel.getP_status_id())));
				model.put("latlng", latlngmodel);
				int school_id = (Integer) session.getAttribute("schoolId");
				model.put("school_details",
						schoolservice.getSchoolById(school_id));
				return new ModelAndView("parent/view_student", model);
			} else {
				return new ModelAndView("redirect:studentList");
			}
		} catch (Exception e) {
			return new ModelAndView("redirect:studentList");
		}

	}

	/* Method for get Student list */
	private List<RouteBean> getAllRoute(List<RouteModel> route) {

		List<RouteBean> beans = null;
		if (route != null && !route.isEmpty()) {
			beans = new ArrayList<RouteBean>();
			RouteBean bean = null;
			for (RouteModel st : route) {
				bean = new RouteBean();
				bean.setRoute_id(st.getRoute_id());
				bean.setRoute_name(st.getRoute_name());
				bean.setSource(st.getSource());
				bean.setDestination(st.getDestination());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Method for Track Student
	 **/

	@RequestMapping(value = "parent/TrackStudent", method = RequestMethod.GET)
	public ModelAndView TrackStudent(
			@ModelAttribute("command") StudentTracking tracking,
			BindingResult result, HttpServletRequest request) {
		try{
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int student_id=Integer.parseInt(new String(decodedBytes));		
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole") != "Parent") {
			return new ModelAndView("redirect:/login.html");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			int school_id = (Integer) session.getAttribute("schoolId");
			StudentModel studentModel = studentservice.getStudentById(student_id);
			model.put("school_details", schoolservice.getSchoolById(school_id));
			model.put("students", getDriverTrackList(studentservice
					.getDriverTrack(studentModel.getS_route_id())));
			model.put("students", getDriverTrackList(studentservice
					.getDriverTrack(studentModel.getS_route_id())));
			AttendanceModel att=new AttendanceModel();
			att=schoolservice.getTodayStudentAttendance(student_id);
			
			//model.put("check_in_check_out", schoolservice.getTodayStudentAttendance(student_id));
			
			
			
		  
			model.put("route_id", studentModel.getS_route_id());
			RouteModel routemodel =new RouteModel();
			routemodel = schoolservice.getRouteById(studentModel.getS_route_id());
			if (routemodel != null) {
				model.put("latlong",getAllLatLng(schoolservice.listLatLng(studentModel.getS_route_id())));
				model.put("route", routemodel);
			}
			model.put(
					"size_l",
					getDriverTrackList(
							studentservice.getDriverTrack(studentModel
									.getS_route_id())).size() - 1);
			model.put("first_lat_lng", getDriverTrackList(studentservice.getDriverTrackLimit(studentModel.getS_route_id())));
			
			String morning_login = att.getLogin_time();
			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat outputFormat = new SimpleDateFormat("KK:mm a");
			model.put("morning_time", outputFormat.format(inputFormat.parse(morning_login)));
			
			String morning_logout = att.getLogout_time();
			model.put("morning_logout", outputFormat.format(inputFormat.parse(morning_logout)));
			
			String evening_login = att.getLogin_evening();
			model.put("evening_login", outputFormat.format(inputFormat.parse(evening_login)));
			
			String evening_logout = att.getLogout_evening();
			model.put("evening_logout", outputFormat.format(inputFormat.parse(evening_logout)));
			
			DriverModel DM=new DriverModel();
			DM=schoolservice.getDriverByRouteId(studentModel.getS_route_id());
			
		    DriverAttendanceModel dam=new DriverAttendanceModel();
		    dam=schoolservice.getTodayDriverAttendance(DM.getDriver_id());
		    model.put("driver_login_date",dam.getLogin_date());
			
		} catch (Exception e) {
			int school_id = (Integer) session.getAttribute("schoolId");
			model.put("school_details", schoolservice.getSchoolById(school_id));
			model.put("error", "No data available1");
			System.out.println(e);
		}

		return new ModelAndView("parent/track_student", model);
		}catch(Exception e)
		{
			return new ModelAndView("redirect:studentList");
		}
	}

	/* Method for get Student list */
	private List<StudentTracking> getTrackList(
			List<StudentTrackingModel> tracklist) {

		List<StudentTracking> beans = null;
		if (tracklist != null && !tracklist.isEmpty()) {
			beans = new ArrayList<StudentTracking>();
			StudentTracking bean = null;
			for (StudentTrackingModel t_list : tracklist) {
				bean = new StudentTracking();
				bean.setStudent_id(t_list.getStudent_id());
				bean.setLng(t_list.getLng());
				bean.setLat(t_list.getLat());
				bean.setTrack_date(t_list.getTrack_date());
				bean.setTrack_id(t_list.getTrack_id());
				beans.add(bean);
			}
		} else {
			System.out.println("No Data Available");
		}
		return beans;
	}

	/* Method for get Student list */
	private List<DriverTrack> getDriverTrackList(
			List<DriverTrackModel> tracklist) {

		List<DriverTrack> beans = null;
		if (tracklist != null && !tracklist.isEmpty()) {
			beans = new ArrayList<DriverTrack>();
			DriverTrack bean = null;
			for (DriverTrackModel t_list : tracklist) {
				bean = new DriverTrack();
				bean.setRoute_id(t_list.getRoute_id());
				bean.setLng(t_list.getLng());
				bean.setLat(t_list.getLat());
				bean.setTrack_date(t_list.getTrack_date());
				bean.setTrack_id(t_list.getTrack_id());
				beans.add(bean);
			}
		} else {
			System.out.println("No Data Available");
		}
		return beans;
	}

	/**
	 * Method for view School Details
	 **/
	@RequestMapping(value = "parent/schoolDetails", method = RequestMethod.GET)
	public ModelAndView schoolDetails(
			@ModelAttribute("command") StudentBean students,
			BindingResult result, HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();

		HttpSession session = request.getSession();
		if (session.getAttribute("userRole") != "Parent") {
			return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");
		SchoolModel s = new SchoolModel();
		s = schoolservice.getSchoolById(school_id);
		model.put("school", schoolservice.getSchoolById(school_id));
		LoginModel school_admin = schoolservice.getMainSchoolAdmin(s.getS_id());

		model.put("school_details", schoolservice.getSchoolById(school_id));
		model.put("country_details",schoolservice.getCountryById(s.getCountry()));
		model.put("school_admin",getParentList(schoolservice.listSchoolAdmin(school_id)));
		model.put("schooladmin", school_admin);
		model.put("heading", "School Details");
		return new ModelAndView("parent/school_details", model);

	}

	/**
	 * Function for view parent profile
	 **/
	@RequestMapping(value = "parent/parentProfile", method = RequestMethod.GET)
	public ModelAndView parentProfile(
			@ModelAttribute("command") LoginBean parents, BindingResult result,
			StudentBean student, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole") != "Parent") {
			return new ModelAndView("redirect:/login.html");
		}
		LoginModel parent = schoolservice.getParentById((Integer) session
				.getAttribute("user_id"));
		Map<String, Object> model = new HashMap<String, Object>();
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_details", schoolservice.getSchoolById(school_id));

		model.put("allParent", getParentList(schoolservice
				.getParentByPIdList(parent.getP_status())));
		model.put("heading", "Parent Profile");
		model.put("parent", parent);
		model.put("students", getStudentList(studentservice
				.getStudentByParentStatus(parent.getP_status())));

		return new ModelAndView("parent/parent_profile", model);

	}

	/**
	 * Function for Messaging system in parent section
	 **/
	@RequestMapping(value = "parent/inboxMessage", method = RequestMethod.GET)
	public ModelAndView inboxMessage(
			@ModelAttribute("command") LoginBean parents, BindingResult result,
			StudentBean student, ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole") != "Parent") {
			return new ModelAndView("redirect:/login.html");
		}
		int user_id = (Integer) session.getAttribute("user_id");
		int school_id = (Integer) session.getAttribute("schoolId");
		LoginModel school_admin = schoolservice.getMainSchoolAdmin(school_id);
		model.put("school_details", schoolservice.getSchoolById(school_id));
		model.put("school_admin", school_admin);
		return new ModelAndView("parent/chattingParent", model);

	}
	/**
	 * Function for Messaging system in parent section
	 **/
	@RequestMapping(value = "parent/inboxMessageSocket", method = RequestMethod.GET)
	public ModelAndView inboxMessageSocket(
			@ModelAttribute("command") LoginBean parents, BindingResult result,
			StudentBean student, ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole") != "Parent") {
			return new ModelAndView("redirect:/login.html");
		}
		int user_id = (Integer) session.getAttribute("user_id");
		int school_id = (Integer) session.getAttribute("schoolId");
		LoginModel school_admin = schoolservice.getMainSchoolAdmin(school_id);
		model.put("User_id", user_id);
		model.put("User_name", session.getAttribute("f_name"));
		model.put("school_details", schoolservice.getSchoolById(school_id));
		model.put("school_admin", school_admin);
		return new ModelAndView("parent/chattingParent_socket", model);

	}
	/**
	 * Function for view Student
	 **/
	@RequestMapping(value = "parent/viewAttendance", method = RequestMethod.GET)
	public ModelAndView viewAttendance(
			@ModelAttribute("command") StudentBean students,
			BindingResult result, HttpServletRequest request) {
		StudentModel studentModel = new StudentModel();
		try {
		
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");	
		int student_id=Integer.parseInt(new String(decodedBytes));
		studentModel = studentservice.getStudentById(student_id);

		Map<String, Object> model = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Parent")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");
		
		if (studentModel != null) {
			try {

				/*
				 * CityModel city=new CityModel(); CountryModel country=new
				 * CountryModel();
				 * city=frontService.getCityById(studentModel.getS_city());
				 * country
				 * =frontService.getCountryById(studentModel.getS_country());
				 */

			model.put("heading", "View Attendance");
			model.put("student", studentModel);
			/*
			 * model.put("city",city); model.put("country", country);
			 */
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			model.put("current_date", dateFormat.format(date));
			model.put("routes", getAllRoute(schoolservice
					.listRoute(student_id)));
			RouteLatLngModel latlngmodel = studentservice
					.getLatLngBySId(student_id);
					model.put("allParent", getParentList(schoolservice
					.getParentByPIdList(studentModel.getP_status_id())));
			model.put("latlng", latlngmodel);
			model.put("all_holiday",
					getHolidayList(schoolservice.getAllHoliday(school_id)));
			model.put("all_present", getPresentList(schoolservice
			.getStudentAttendanceList(student_id)));
			model.put("student_id", student_id);
			
			//Get holiday by school id 
			List<HolidayBean> a=getHolidayList(schoolservice.getAllHoliday(school_id));
			List<HolidayDeletedBean> b=getDeletedHolidayList(schoolservice.getAllDeletedHoliday(school_id));
			if(a!=null)
			{
				for(int i=0;i<a.size();i++)
				{
					if(b!=null)
					{
				
					for(int j=0;j<b.size();j++)
					{
						if(a.get(i).getH_id().equals(b.get(j).getHoliday_id()))
						{
							a.remove(i);
						}
					}
					
					}
				}
				model.put("all_holiday",a); // all holiday of school
			}else{
				model.put("all_holiday","");
			}
			
			
			//model.put("all_holiday",getHolidayList(schoolservice.getAllHoliday(school_id)));
			model.put("all_present", getPresentList(schoolservice
			.getStudentAttendanceList(student_id)));
			
			model.put("student_id", student_id);
			model.put("school_details", schoolservice.getSchoolById(school_id));
			Calendar now = Calendar.getInstance();
			
			int iMonth=0;
			int year_num=now.get(Calendar.YEAR);
			
			String month=""+(now.get(Calendar.MONTH) + 1);
			
			System.out.println("--month"+month);
			if((now.get(Calendar.MONTH) + 1)==01)
			{
				month="01";
				iMonth =Calendar.JANUARY;
			}else if ((now.get(Calendar.MONTH) + 1)==02) {
				month="02";
				iMonth =Calendar.FEBRUARY;
			}else if ((now.get(Calendar.MONTH) + 1)==03) {
				month="03";
				iMonth =Calendar.MARCH;
			}else if ((now.get(Calendar.MONTH) + 1)==04) {
				month="04";
				iMonth =Calendar.APRIL;
			}else if ((now.get(Calendar.MONTH) + 1)==05) {
				month="05";
				iMonth =Calendar.MAY;
			}else if ((now.get(Calendar.MONTH) + 1)==06) {
				month="06";
				iMonth =Calendar.JUNE;
			}else if ((now.get(Calendar.MONTH) + 1)==07) {
				month="07";
				iMonth =Calendar.JULY;
			}else if ((now.get(Calendar.MONTH) + 1)==8) {
				month="08";
				iMonth =Calendar.AUGUST;
			}else if ((now.get(Calendar.MONTH) + 1)==9) {
				month="09";
				iMonth =Calendar.SEPTEMBER;
			}else if ((now.get(Calendar.MONTH) + 1)==10) {
				month="10";
				iMonth =Calendar.OCTOBER;
			}else if ((now.get(Calendar.MONTH) + 1)==11) {
				month="11";
				iMonth =Calendar.NOVEMBER;
			}else {
				month="12";
				iMonth =Calendar.DECEMBER;
			}
			
			String year=""+year_num;
			int total_present=0;
			int absent_day=0;
			total_present=schoolservice.getStudentAttendanceListByMonth(student_id,month,year).size();
			int iYear = Integer.parseInt(year.trim());
			int iDay = 1;
			Calendar mycal = new GregorianCalendar(iYear, iMonth, iDay);
			int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
			System.out.println("Day in month="+daysInMonth);
			List<HolidayBean> b1=getHolidayList(schoolservice.getAllHolidayByMonth(school_id,month,year));
			List<HolidayDeletedBean> c=getDeletedHolidayList(schoolservice.getAllDeletedHoliday(school_id));
			try{
			for(int i=0;i<b1.size();i++)
			{
				for(int j=0;j<c.size();j++)
				{
					if(b1.get(i).getH_id().equals(c.get(j).getHoliday_id()))
					{
						b1.remove(i);
					}
				}
			}
			}catch(Exception e)
			{
				System.out.println(e);
			}
			
			if(b1!=null)
			{
				absent_day=absent_day- b1.size();
				model.put("holiday_days", b1.size());
			}else
			{
				model.put("holiday_days",0);
			}
			absent_day=daysInMonth-total_present;
			model.put("present_day", total_present);
			model.put("absent_day", absent_day);
			
			
			
			} catch (Exception e) {
				e.printStackTrace();
				return new ModelAndView("redirect:manageStudents");
			}

			model.put("school_details", schoolservice.getSchoolById(school_id));
			return new ModelAndView("parent/view_student_attendance", model);
		} else {
			return new ModelAndView("redirect:studentList");
		}
		} catch (Exception e) {
			return new ModelAndView("redirect:studentList");
		}

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
	/**
	 * Method for get all hodiday list
	 * **/
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
				bean.setHoliday_enddate(holi.getHoliday_enddate());
				bean.setSchool_id(holi.getSchool_id());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}
	/**
	 * Method for get all hodiday list
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
				bean.setLogin_date(present.getLogin_date());
				bean.setLogin_time(present.getLogin_time());
				bean.setLogout_time(present.getLogout_time());
				bean.setLogin_evening(present.getLogin_evening());
				bean.setLogout_evening(present.getLogout_evening());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}
	/**
	 * Method for edit parent profile 
	 **/
	@RequestMapping(value = "parent/editProfile", method = RequestMethod.GET)
	public ModelAndView editProfile(
			@ModelAttribute("command") LoginBean parents, BindingResult result,
			StudentBean student, ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole") != "Parent") {
			return new ModelAndView("redirect:/login.html");
		}
		int user_id = (Integer) session.getAttribute("user_id");
		int school_id = (Integer) session.getAttribute("schoolId");
		LoginModel parent = schoolservice.getParentById(user_id);
		LoginModel school_admin = schoolservice.getMainSchoolAdmin(school_id);
		model.put("school_details", schoolservice.getSchoolById(school_id));
		model.put("school_admin", school_admin);
		model.put("parent", parent);
		return new ModelAndView("parent/edit_profile", model);

	}
	/**
	 * Method for edit parent profile 
	 **/
	@RequestMapping(value = "parent/editProfile", method = RequestMethod.POST)
	public ModelAndView editProfile(
			@ModelAttribute("command") LoginBean parents, BindingResult result,
			StudentBean student, HttpServletRequest request,ModelMap model) {
		HttpSession session = request.getSession();
	
		try{
			if (session.getAttribute("userRole") != "Parent") {
				return new ModelAndView("redirect:/login.html");
			}
			LoginModel parentModel = new LoginModel();
			String parent_str=request.getParameter("parent");
			byte[] decodedBytes = Base64.decodeBase64(""+parent_str+"");
			int parent_id=(Integer) session.getAttribute("user_id");	
			int school_id = (Integer) session.getAttribute("schoolId");
			parentModel.setContact_number(parents.getContact_number());
			parentModel.setUser_pass(parents.getUser_pass());
			parentModel.setFirst_name(parents.getFirst_name());
			parentModel.setLast_name("");
			parentModel.setFamily_name(parents.getFamily_name());
			parentModel.setMiddle_name(parents.getMiddle_name());
		/*	parentModel.setMobile_number(parents.getMobile_number());*/
			parentModel.setUser_email(parents.getUser_email());
			// parentModel.setUser_name(parents.getUser_name());
			parentModel.setSchool_id(school_id);
			schoolservice.editParentById(parent_id, parentModel);
			
			}catch(Exception e)
			{
				e.printStackTrace();
//				System.out.println(e);
			}
	//	return new ModelAndView("redirect:editProfile");
		int user_id = (Integer) session.getAttribute("user_id");
		int school_id = (Integer) session.getAttribute("schoolId");
		LoginModel parent = schoolservice.getParentById(user_id);
		LoginModel school_admin = schoolservice.getMainSchoolAdmin(school_id);
		model.put("school_details", schoolservice.getSchoolById(school_id));
		model.put("school_admin", school_admin);
		model.put("parent", parent);
		model.put("success", "Profile updated successfully");
		return new ModelAndView("parent/edit_profile", model);
	 

	}
	/**
	 * Method for edit parent profile 
	 **/
	@RequestMapping(value = "parent/notification", method = RequestMethod.GET)
	public ModelAndView notification(
			@ModelAttribute("command") LoginBean parents, BindingResult result,
			StudentBean student, ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole") != "Parent") {
			return new ModelAndView("redirect:/login.html");
		}
		int user_id = (Integer) session.getAttribute("user_id");
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		LoginModel parent = schoolservice.getParentById(user_id);
		System.out.println(parent.getP_status());
		List<StudentBean>  stud= getStudentList(studentservice.getStudentByParentStatus(parent.getP_status()));
		int route_id=0;
		if(stud!=null)
		{
			for(StudentBean s:stud)
			{
				if(s.getS_route_id()!=null)
				{ 
					
					route_id=s.getS_route_id();
					break;
					
				}
			}
		}
	
		List<NotificationBean> noti_model = iterateNoti(webserviceService.getNotificationList(user_id, route_id));
		model.put("notifications", noti_model);
		model.put("heading", "Notification");
		return new ModelAndView("parent/notification", model);

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
				bean.setNoti_desc(msgIt.getNoti_desc());
				/* bean.set */
				beans.add(bean);
			}

		} else {
			System.out.println("empty");
		}
		return beans;
	}
	/* Method for get latlng of route */
	private List<RouteLatLng> getAllLatLng(List<RouteLatLngModel> latlng) {

		List<RouteLatLng> beans = null;
		if (latlng != null && !latlng.isEmpty()) {
			beans = new ArrayList<RouteLatLng>();
			RouteLatLng bean = null;
			for (RouteLatLngModel st : latlng) {
				bean = new RouteLatLng();
				
				
				StudentModel studentModel = studentservice.getStudentById(st.getStudent_id());
				bean.setS_image(studentModel.getS_image_path());
				bean.setS_class(studentModel.getStudent_class());
				bean.setStudent_name(studentModel.getS_fname()+" "+studentModel.getFather_name()+" "+studentModel.getGrand_name()+" "+studentModel.getFamily_name());
				bean.setAddress(studentModel.getS_address());
				bean.setId(st.getId());
				bean.setLat(st.getLat());
				bean.setLng(st.getLng());
				bean.setRoute_id(st.getRoute_id());
				bean.setBlink_s(studentModel.getBlink_status());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}
	/**
	 * Method for edit parent profile 
	 **/
	@RequestMapping(value = "parent/reportAbsence", method = RequestMethod.GET)
	public ModelAndView reportAbsence(
			@ModelAttribute("command") StudentAbsentBean absent_bean, BindingResult result,
			StudentBean student, ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole") != "Parent") {
			return new ModelAndView("redirect:/login.html");
		}
		LoginModel parent = schoolservice.getParentById((Integer) session
				.getAttribute("user_id"));
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		if (parent != null) {
			model.put("allParent", getParentList(schoolservice
					.getParentByPIdList(parent.getP_status())));
			model.put("heading", "Student List");
			model.put("parent", parent);
			// model.put("students",
			// getStudentList(studentservice.getStudentByParent(parents.getUser_id())));
			model.put("students", getStudentList(studentservice
					.getStudentByParentStatus(parent.getP_status())));
		
		}
		model.put("school_details",schoolservice.getSchoolById(school_id));
		model.put("heading", "Report Absence");
		return new ModelAndView("parent/add_absent", model);

	}
	
	/**
	 * Method for edit parent profile 
	 **/
	@RequestMapping(value = "parent/reportAbsence", method = RequestMethod.POST)
	public ModelAndView addReportAbsence(
			@ModelAttribute("command") StudentAbsentBean absent_bean, BindingResult result,
			StudentBean student, ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("userRole") != "Parent") {
			return new ModelAndView("redirect:/login.html");
		}
		try{
		LoginModel parent = schoolservice.getParentById((Integer) session
				.getAttribute("user_id"));
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		if (parent != null) {
			model.put("allParent", getParentList(schoolservice
					.getParentByPIdList(parent.getP_status())));
			model.put("heading", "Student List");
			model.put("parent", parent);
			// model.put("students",
			// getStudentList(studentservice.getStudentByParent(parents.getUser_id())));
			model.put("students", getStudentList(studentservice
					.getStudentByParentStatus(parent.getP_status())));
		
		}
		
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-mm-dd");
		String inputString1 = absent_bean.getStart_date();
		String inputString2 =absent_bean.getEnd_date();
		Date date1 = myFormat.parse(inputString1);
		Date date2=new Date();
		if(!inputString2.equals(""))
		{
			date2 = myFormat.parse(inputString2);
			
		}else
		{
			date2 = myFormat.parse(inputString1);
		}
		
		long diff = date2.getTime() - date1.getTime();
		diff=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		Calendar cal = Calendar.getInstance();
		//adding one day to current date 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		for(int i=0;i<=diff;i++)
		{
			Date date = formatter.parse(absent_bean.getStart_date());
			cal.setTime(formatter.parse(absent_bean.getStart_date()));

			cal.add(Calendar.DATE, i);
			Date newDate = cal.getTime();
			
			StudentAbsentModel sam= new StudentAbsentModel();
			   sam.setA_id(null);
			   sam.setAbsent_date(formatter.format(newDate));
			   sam.setReason(absent_bean.getReason());
			   sam.setStudent_id(absent_bean.getStudent_id());
			   studentservice.addStudentAbsent(sam);
			
		}	
		
		
		model.put("school_details",schoolservice.getSchoolById(school_id));
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		model.addAttribute("success", "Absent request sent successfully");
		model.put("heading", "Report Absence");
		return new ModelAndView("parent/add_absent", model);

	}
	/**
	 *@access public
	 *Method for set and redirect for language
	 **/
	@RequestMapping(value = "parent/langSwitcher", method = RequestMethod.GET)
	public ModelAndView langSwitcher(@ModelAttribute("command") StudentBean students,
			BindingResult result, HttpServletRequest request) {
			try{
				String q=request.getParameter("q");
				int u=Integer.parseInt(request.getParameter("u"));
				LoginModel found = schoolservice.getUserById(u);
				if(found!=null){
					HttpSession session=request.getSession();
					session.setAttribute("f_name", found.getFirst_name());
					session.setAttribute("l_name", found.getLast_name());
					session.setAttribute("family_name", found.getFamily_name());
					session.setAttribute("userName", found.getUser_name());
					session.setAttribute("userEmail", found.getUser_email());
					session.setAttribute("userRole", "Parent");
					session.setAttribute("schoolId", found.getSchool_id());
					session.setAttribute("p_status", found.getP_status());
					session.setAttribute("user_id", found.getUser_id());
					return new ModelAndView("redirect:/"+q);
				}else
				{
					return new ModelAndView("redirect:login.html");
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			//return new ModelAndView("redirect:manageFaq");
			return null;
	}
}
