package com.trackingbus.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.trackingbus.bean.AttendanceBean;
import com.trackingbus.bean.CityBean;
import com.trackingbus.bean.CountryBean;
import com.trackingbus.bean.DepartmentBean;
import com.trackingbus.bean.DriverAttendanceBean;
import com.trackingbus.bean.DriverBean;
import com.trackingbus.bean.DriverDocBean;
import com.trackingbus.bean.DriverTrack;
import com.trackingbus.bean.GoogleResponse;
import com.trackingbus.bean.HolidayBean;
import com.trackingbus.bean.HolidayDeletedBean;
import com.trackingbus.bean.NationlityBean;
import com.trackingbus.bean.RouteBean;
import com.trackingbus.bean.RouteLatLng;
import com.trackingbus.bean.SchoolBean;
import com.trackingbus.bean.SchoolClasses;
import com.trackingbus.bean.StaffBean;
import com.trackingbus.bean.StudentBean;
import com.trackingbus.bean.VechileBean;
import com.trackingbus.bean.VehicleDocBean;
import com.trackingbus.model.AttendanceModel;
import com.trackingbus.model.CityModel;
import com.trackingbus.model.CountryModel;
import com.trackingbus.model.DepartmentModel;
import com.trackingbus.model.DriverAttendanceModel;
import com.trackingbus.model.DriverDocModel;
import com.trackingbus.model.DriverModel;
import com.trackingbus.model.DriverTrackModel;
import com.trackingbus.model.FrontDashboard;
import com.trackingbus.model.HolidayDeletedModel;
import com.trackingbus.model.HolidayModel;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.NationlityModel;
import com.trackingbus.model.RouteLatLngModel;
import com.trackingbus.model.RouteModel;
import com.trackingbus.model.SchoolClassesModel;
import com.trackingbus.model.SchoolModel;
import com.trackingbus.model.StaffModel;
import com.trackingbus.model.StudentModel;
import com.trackingbus.model.VehicleDocModel;
import com.trackingbus.service.FrontDashboardService;
import com.trackingbus.service.LoginService;
import com.trackingbus.service.SchoolService;
import com.trackingbus.service.StaffService;
import com.trackingbus.service.StudentService;
import com.trackingbus.bean.LoginBean;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.math.RandomUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.trackingbus.model.VehicleModel;
import com.trackingbus.service.VehicleService;

import resources.Assets;
import resources.RandomNumber;
import resources.Result;
import resources.Sms_api;

@Controller
public class SchoolAdminController {

	@Autowired
	private SchoolService schoolservice;
	@Autowired
	private StudentService studentservice;

	@Autowired
	private LoginService loginservice;

	@Autowired
	private FrontDashboardService frontService;

	@Autowired
	private VehicleService vechileservice;

	@Autowired
	private StaffService staffservice;

	@Autowired
	private FrontDashboardService frontservice;

	@RequestMapping(value = "/schoolDashboard.html", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute("command") SchoolBean schoolbean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");
		SchoolModel schoolinfo = (SchoolModel) schoolservice
				.getSchoolById(school_id);
		model.addAttribute("schoolModel", schoolinfo);
		int total_schools = schoolservice.listSchoolAdmin(school_id).size();
		int total_students = schoolservice.listStudent(school_id).size();
		int total_buses = vechileservice.getVechile(school_id).size();
		model.put("total_student", total_students);
		model.put("total_schools", total_schools);
		model.put("total_buses", total_buses);
		model.put("Heading_box", "Total School Admin");
		model.put("heading", "School Admin Dashboard");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		 return new ModelAndView("redirect:school/schoolDashboard");
		//return new ModelAndView("school/schoolDashboard");
	}
	
	@RequestMapping(value = "school/schoolDashboard", method = RequestMethod.GET)
	public ModelAndView schoolDashboard(@ModelAttribute("command") SchoolBean schoolbean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");
		SchoolModel schoolinfo = (SchoolModel) schoolservice
				.getSchoolById(school_id);
		model.addAttribute("schoolModel", schoolinfo);
		int total_schools = schoolservice.listSchoolAdmin(school_id).size();
		int total_students = schoolservice.listStudent(school_id).size();
		int total_buses = vechileservice.getVechile(school_id).size();
		model.put("total_student", total_students);
		model.put("total_schools", total_schools);
		model.put("total_buses", total_buses);
		model.put("Heading_box", "Total School Admin");
		model.put("heading", "School Admin Dashboard");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		return new ModelAndView("school/schoolDashboard");
	}
	@RequestMapping(value = "school/addStudent", method = RequestMethod.GET)
	public ModelAndView addStudent(
			@ModelAttribute("command") SchoolBean schoolbean,
			BindingResult result, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("countries", prepareListofBean(schoolservice.listCountry()));
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("heading", "Add Student");
		model.put("parents",
				getParentList(studentservice.listParent(school_id)));
		model.put("routes", getAllRoute(schoolservice.listRoute(school_id)));
		model.put("school_details", schoolservice.getSchoolById(school_id));
		model.put("class_info", getAllClassList(schoolservice.getAllSchoolClasses(school_id)));
		model.put("nationality", prepareNationalityList(schoolservice.getAllNationlity()));
		return new ModelAndView("school/add_student", model);
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
				/*bean.setUser_id(user.getUser_id());
				bean.setP_status(user.getP_status());
				bean.setFirst_name(user.getFirst_name());
				bean.setLast_name(user.getLast_name());
				bean.setUser_name(user.getUser_name());
				bean.setUser_email(user.getUser_email());
				bean.setContact_number(user.getContact_number());
				bean.setUser_pass(user.getUser_pass());*/
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
				bean.setFamily_name(user.getFamily_name());
				bean.setMiddle_name(user.getMiddle_name());
				bean.setMobile_number(user.getMobile_number());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/* Method for get country list */
	private List<CountryBean> prepareListofBean(List<CountryModel> country) {

		List<CountryBean> beans = null;
		if (country != null && !country.isEmpty()) {
			beans = new ArrayList<CountryBean>();
			CountryBean bean = null;
			for (CountryModel countries : country) {
				bean = new CountryBean();
				bean.setC_id(countries.getC_id());
				bean.setC_name(countries.getC_name());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	@RequestMapping(value = "school/addStudent", method = RequestMethod.POST)
	public ModelAndView addStudent(
			@ModelAttribute("command") StudentBean studentbean,
			RouteLatLng latlng, RouteBean route, BindingResult result,
			ModelMap model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}

			SchoolModel schoolinfo = (SchoolModel) schoolservice
					.getSchoolById(studentbean.getS_school_id());

			int totalStudent = (Integer) schoolservice
					.getStudentCount(studentbean.getS_school_id());

		/*	StudentModel found = studentservice.checkSchoolName(studentbean
					.getS_email());*/
			int school_id = (Integer) session.getAttribute("schoolId");
			if (totalStudent > schoolinfo.getTotal_students()) {
				model.addAttribute("studentBean", studentbean);
				model.addAttribute("error", "You can not add more student");
			} else {
				/*if (found == null) {*/
					String imageName = "";
					if (studentbean.getS_image_path() != "") {
						String filePath = studentbean.getS_image_path();
						String[] parts = filePath.split("base64,");
						String part2 = parts[1]; // 034556
						int rand = RandomUtils.nextInt();
						imageName = studentbean.getS_contact() + rand + ".png";
						String directory = request.getServletContext()
								.getRealPath(
										Assets.STUDENT_UPLOAD_PATH + imageName);
						byte[] imageByteArray = Base64.decodeBase64(part2);
						// Write a image byte array into file system
						FileOutputStream imageOutFile = new FileOutputStream(
								directory);
						imageOutFile.write(imageByteArray);
						imageOutFile.close();
					} else {
						imageName = "";
					}

					StudentModel student = new StudentModel();
					student.setStudent_id(studentbean.getStudent_id());
					student.setS_fname(studentbean.getS_fname());
					student.setS_lname(" ");
					student.setS_email(studentbean.getS_email());
					student.setS_pass(studentbean.getS_pass());
					student.setS_address(studentbean.getS_address());
					student.setS_city(studentbean.getS_city());
					student.setS_driver(studentbean.getS_driver());
					student.setS_country(studentbean.getS_country());
					student.setS_zip(studentbean.getS_zip());
					student.setS_image_path(imageName);
					student.setS_parent_id(studentbean.getS_parent_id());
					student.setS_school_id(studentbean.getS_school_id());
					student.setS_contact(studentbean.getS_contact());
					student.setP_status_id(studentbean.getP_status_id());
					student.setS_route_id(route.getRoute_id());
					student.setFamily_name(studentbean.getFamily_name());
					student.setFather_name(studentbean.getFather_name());
					student.setGrand_name(studentbean.getGrand_name());
					student.setBlood_type(studentbean.getBlood_type());
					if(studentbean.getDob() != null && studentbean.getDob().length() > 0 ) student.setDob(studentbean.getDob());
					student.setNationality(studentbean.getNationality());
					student.setGender(studentbean.getGender());
					student.setStudent_class(studentbean.getStudent_class());
					student.setStudent_id(null);
					student.setBlink_status(0);
					List<String> relationship_details=studentbean.getRelationship_details();
					List<String> parent_list=studentbean.getParent_list();
					int relation_size=relationship_details.size();
					if(relation_size==2)
					{
						relationship_details.add(2, "");
						parent_list.add(2, "0");
						
						
					}else if(relation_size==1)
					{
						//relationship_details.add("");
						relationship_details.add(1, "");
						parent_list.add(1, "0");
						relationship_details.add(2, "");
						parent_list.add(2, "0");
					}
					
					if(relationship_details.get(0)!=null && !relationship_details.get(0).equals("") )
					{
						student.setP_1(Integer.parseInt(parent_list.get(0)));
						student.setR_1(relationship_details.get(0));
					}else{
						student.setP_1(0);
						student.setR_1("");
					}
					if(relationship_details.get(1)!=null && !relationship_details.get(1).equals("") )
					{
						student.setP_2(Integer.parseInt(parent_list.get(1)));
						student.setR_2(relationship_details.get(1));
					}else{
						student.setP_2(0);
						student.setR_2("");
					}
					if(relationship_details.get(2)!=null && !relationship_details.get(2).equals("") )
					{
						student.setP_3(Integer.parseInt(parent_list.get(2)));
						student.setR_3(relationship_details.get(2));
					}else{
						student.setP_3(0);
						student.setR_3("");
					}
					
					
					
					long student_id = studentservice.addNewStudent(student);
					LoginModel parent = schoolservice.getParentById(studentbean
							.getS_parent_id());
					String qr_name = "Student Id: Stud" + student_id;
					String qr_text = "Student Id: Stud" + student_id;
					qr_text += "\nStudent Name:" + studentbean.getS_fname()
							+ " " + studentbean.getS_lname();
					qr_text += "\nSchool Name:" + schoolinfo.getSchool_name();
					qr_text += "\nParent Contact:" + parent.getContact_number();
					qr_text=""+student_id;
					ByteArrayOutputStream out = QRCode.from(qr_text)
							.to(ImageType.PNG).stream();
					String qr_directory = request.getServletContext()
							.getRealPath(
									Assets.STUDENT_QR_PATH + "/s_" + student_id
											+ ".png");

					FileOutputStream fout = new FileOutputStream(new File(
							qr_directory));
					fout.write(out.toByteArray());
					fout.flush();
					fout.close();

					RouteLatLngModel routemodel = new RouteLatLngModel();
					String lat = latlng.getLat();
					System.out.println(latlng.getLat());
					String[] latArr = lat.split(",", -1);
					String lng = latlng.getLng();
					String[] lngArr = lng.split(",", -1);

					String s_address = "";
					System.out.println(latArr[latArr.length - 1]);

					GoogleResponse res1 = convertFromLatLong(latArr[latArr.length - 1]
							+ "," + lngArr[lngArr.length - 1]);
					if (res1.getStatus().equals("OK")) {
						for (Result result2 : res1.getResults()) {
							s_address = result2.getFormatted_address();
							System.out.println("address is :"
									+ result2.getFormatted_address());
						}
					} else {
						System.out.println(res1.getStatus());
					}

					routemodel.setId(null);
					routemodel.setSchool_id(school_id);
					routemodel.setRoute_id(route.getRoute_id());
					routemodel.setLat(latArr[latArr.length - 1]);
					routemodel.setLng(lngArr[lngArr.length - 1]);
					routemodel.setStudent_id((int) student_id);
					routemodel.setStudent_name(studentbean.getS_fname());
					routemodel.setAddress(s_address);
					routemodel.setParent_id(studentbean.getP_status_id());

					schoolservice.addRouteLatLng(routemodel);

					model.addAttribute("success", "Student Added Successfully");

			/*	} else {
					model.addAttribute("studentBean", studentbean);
					model.addAttribute("error", "This student already exist");
				}*/
				model.put("school_details",
						schoolservice.getSchoolById(school_id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> modelM = new HashMap<String, Object>();
		modelM.put("countries", prepareListofBean(schoolservice.listCountry()));
		HttpSession session = request.getSession();
		int school_id = (Integer) session.getAttribute("schoolId");
		modelM.put("heading", "Add Student");
		model.put("routes", getAllRoute(schoolservice.listRoute(school_id)));
		model.put("nationality", prepareNationalityList(schoolservice.getAllNationlity()));
		model.put("parents",
				getParentList(studentservice.listParent(school_id)));
		model.put("class_info", getAllClassList(schoolservice.getAllSchoolClasses(school_id)));
		return new ModelAndView("school/add_student", modelM);

	}

	@RequestMapping(value = "school/addParent", method = RequestMethod.GET)
	public ModelAndView addParent(
			@ModelAttribute("command") SchoolBean schoolbean,
			BindingResult result, HttpServletRequest request, ModelMap model) {
		model.addAttribute("heading", "Add Parent");
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		SchoolModel schol= schoolservice.getSchoolById(school_id);
		model.put("country_details", schoolservice.getCountryById(schol.getCountry()));
		return new ModelAndView("school/add_parent");
	}

	@RequestMapping(value = "school/addParent", method = RequestMethod.POST)
	public ModelAndView addParent(@ModelAttribute("command") LoginBean parent,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			RandomNumber random = new RandomNumber(9999);
			String f_name = parent.getFirst_name().substring(0, 1);
			String password = random.nextSessionId();
			String username = parent.getFirst_name()+ "." +parent.getMiddle_name();
			String username1 = parent.getFirst_name()+ "." +parent.getMiddle_name();
			username = username.toLowerCase();
			username1 = username1.toLowerCase();
			username = username.replaceAll(" ", "_").toLowerCase();
			username1 = username1.replaceAll(" ", "_").toLowerCase();	
			int y = 1;
			int x = 1;
			while (y < 2) {
				LoginModel check = loginservice.checkUsername(username);
				if (check == null) {
					username = username;
					break;
				} else {
					y = 1;
					username = username1 + x;
				}
				x++;
			}

			// LoginModel
			// check=loginservice.checkUsername(parent.getUser_name());
			/*
			 * if(check==null) {
			 */
			// LoginModel found = loginservice.login(parent.getUser_email(),"");
			LoginModel found = loginservice.checkMobile(parent.getMobile_number());

			if (found == null) {
				LoginModel parentModel = new LoginModel();
				parentModel.setUser_email(parent.getUser_email());
				parentModel.setUser_name(username);
				parentModel.setUser_pass(password);
				parentModel.setSchool_id(parent.getSchool_id());
				parentModel.setContact_number(parent.getContact_number());
				parentModel.setUser_role(3);
				parentModel.setP_status(random.generateNewRandom(5000));
				parentModel.setFirst_name(parent.getFirst_name());
				//parentModel.setLast_name(parent.getLast_name());
				parentModel.setLast_name("");
				parentModel.setDevice_token("");
				parentModel.setFamily_name(parent.getFamily_name());
				parentModel.setMiddle_name(parent.getMiddle_name());
				parentModel.setMobile_number(parent.getMobile_number());
			/*	parentModel.setChat_on(0);
				parentModel.setLang(0);
				parentModel.setNoti_on(0);
				parentModel.setChecked_in_on(0);
				parentModel.setChecked_out_on(0);
				parentModel.setSpeed_on(0);
				parentModel.setMax_speed(40);
				parentModel.setWrong_route_on(0);
				*/
				parentModel.setChat_on(0);
				parentModel.setLang(0);
				parentModel.setNoti_on(0);
				parentModel.setChecked_in_on(0);
				parentModel.setChecked_out_on(0);
				parentModel.setSpeed_on(1);
				parentModel.setMax_speed(100);
				parentModel.setWrong_route_on(1);
				parentModel.setSms_checked_in_on(1);
				parentModel.setSms_checked_out_on(1);
				parentModel.setSms_speed_on(1);
				parentModel.setSms_max_speed(100);
				parentModel.setSms_wrong_route_on(1);
				parentModel.setInstant_message(0);
				parentModel.setMorning_before(0);
				parentModel.setEvening_before(0);
				parentModel.setSms_instant_message(1);
				parentModel.setSms_morning_before(1);
				parentModel.setSms_evening_before(1);
				parentModel.setDevice_id("");
				parentModel.setSound_setting(0);
				parentModel.setChat_sound(0);
				
				schoolservice.addParent(parentModel);
				Sms_api sms = new Sms_api();
				sms.sendhttp("Your login details are username=" + username
						+ " and password=" + password, 0,
						parent.getLast_name()+parent.getMobile_number());
				model.addAttribute("success", "Parent Added Successfully");
				model.addAttribute("username", username);
				model.addAttribute("password", password);
				model.addAttribute("heading", "Add Parent");
			} else {
				model.addAttribute("heading", "Add Parent");
				model.addAttribute("parentBean", parent);
				model.addAttribute("error", "This parent already exist");
			}

			/*
			 * RandomNumber random=new RandomNumber(9999); String f_name =
			 * parent.getFirst_name().substring(0, 3); String l_name=
			 * parent.getLast_name().substring(0, 3); String con_number=
			 * parent.getContact_number().substring(0, 3);
			 * 
			 * String username=f_name+random.generateNewRandom(50)+l_name;
			 * LoginModel
			 * check=loginservice.checkUsername(parent.getUser_name());
			 * if(check==null) {
			 * 
			 * LoginModel found = loginservice.login(parent.getUser_email(),"");
			 * if(found==null) {
			 * 
			 * LoginModel parentModel = new LoginModel();
			 * 
			 * 
			 * 
			 * System.out.println(username);
			 * parentModel.setP_status(random.generateNewRandom(5000));
			 * parentModel.setFirst_name(parent.getFirst_name());
			 * parentModel.setLast_name(parent.getLast_name());
			 * parentModel.setUser_email(parent.getUser_email());
			 * parentModel.setUser_name(username);
			 * parentModel.setUser_pass(random.nextSessionId());
			 * parentModel.setSchool_id(parent.getSchool_id());
			 * parentModel.setContact_number(parent.getContact_number());
			 * parentModel.setUser_role(3);
			 * schoolservice.addParent(parentModel);
			 * model.addAttribute("success", "Parent Added Successfully");
			 * model.addAttribute("heading", "Add Parent"); }else {
			 * model.addAttribute("heading", "Add Parent");
			 * model.addAttribute("parentBean", parent);
			 * model.addAttribute("error", "This parent already exist"); } }else
			 * { model.addAttribute("heading", "Add Parent");
			 * model.addAttribute("parentBean", parent);
			 * model.addAttribute("error", "This user already exist"); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		int school_id = (Integer) session.getAttribute("schoolId");
		SchoolModel schol= schoolservice.getSchoolById(school_id);
		 model.put("country_details", schoolservice.getCountryById(schol.getCountry()));
		model.put("school_details", schoolservice.getSchoolById(school_id));
		return new ModelAndView("school/add_parent");

	}

	@RequestMapping(value = "school/manageStudents", method = RequestMethod.GET)
	public ModelAndView manageStudents(
			@ModelAttribute("command") StudentBean studentbean,
			BindingResult result, ModelMap modelH, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		modelH.addAttribute("heading", "Manage Student");
		Map<String, Object> model = new HashMap<String, Object>();
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		model.put("students",
				getStudentList(schoolservice.listStudent(school_id)));
		return new ModelAndView("school/all_student", model);
	}

	/* Method for get Student list */
	private List<StudentBean> getStudentList(List<StudentModel> students) {

		List<StudentBean> beans = null;
		if (students != null && !students.isEmpty()) {
			beans = new ArrayList<StudentBean>();
			StudentBean bean = null;
			for (StudentModel student : students) {
				bean = new StudentBean();
				bean.setStudent_id(student.getStudent_id());
				bean.setS_fname(student.getS_fname());
				RouteModel rm=schoolservice.getRouteById(student.getS_route_id());
				if(rm!=null)
				{
					bean.setS_lname(rm.getRoute_name());
				}else
				{
					bean.setS_lname("N/A");
				}
				bean.setS_email(student.getS_email());
				
				bean.setS_address(student.getS_address());
				bean.setS_contact(student.getS_contact());
				bean.setS_image_path(student.getS_image_path());
				bean.setFamily_name(student.getFamily_name());
				bean.setFather_name(student.getFather_name());
				bean.setDob(student.getDob());
				bean.setGender(student.getGender());
				bean.setGrand_name(student.getGrand_name());
				bean.setNationality(student.getNationality());
				bean.setStudent_class(student.getStudent_class());
				bean.setGender(student.getGender());
				bean.setBlink_status(student.getBlink_status());
				bean.setBlood_type(student.getBlood_type());
				if(student.getP_1()!=null && student.getP_1()!=0)
				{
					LoginModel p1=schoolservice.getParentById(student.getP_1());
					
					bean.setPp_1(p1.getFirst_name()+" "+p1.getLast_name());
					bean.setR_1(student.getR_1());
				}else
				{
					bean.setPp_1("N/A");
					bean.setR_1("N/A");
				}
				
				if(student.getP_2()!=null && student.getP_2()!=0)
				{
					LoginModel p2=schoolservice.getParentById(student.getP_2());
					if(p2!=null)
					{
						bean.setPp_2(p2.getFirst_name()+" "+p2.getLast_name());
						bean.setR_2(student.getR_2());
					}else{
						bean.setPp_2("N/A");
						bean.setR_2("N/A");
					}
					
				}else
				{
					bean.setPp_2("N/A");
					bean.setR_2("N/A");
				}
				
				if(student.getP_3()!=null && student.getP_3()!=0)
				{
					LoginModel p3=schoolservice.getParentById(student.getP_3());
					
					bean.setPp_3(p3.getFirst_name()+" "+p3.getLast_name());
					bean.setR_3(student.getR_2());
				}else
				{
					bean.setPp_3("N/A");
					bean.setR_3("N/A");
				}
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	@RequestMapping(value = "school/deleteStudent", method = RequestMethod.GET)
	public ModelAndView editEmployee(
			@ModelAttribute("command") StudentBean student,
			BindingResult result, ModelMap modelH) {

		schoolservice.deleteStudent(student.getStudent_id());
		return new ModelAndView("redirect:manageStudents");
		// return new ModelAndView("school/all_student",model);
	}

	@RequestMapping(value = "school/deleteParent", method = RequestMethod.GET)
	public ModelAndView editEmployee(
			@ModelAttribute("command") LoginBean parents, BindingResult result) {
		schoolservice.deleteParent(parents.getUser_id());
		return new ModelAndView("redirect:manageParents");
	}

	/**
	 * Function for manage all parents
	 **/
	@RequestMapping(value = "school/manageParents", method = RequestMethod.GET)
	public ModelAndView manageParents(
			@ModelAttribute("command") LoginBean parents, BindingResult result,
			ModelMap modelH, HttpServletRequest request) {
		try{
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		modelH.addAttribute("heading", "Manage Parents");
		Map<String, Object> model = new HashMap<String, Object>();
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("parents",
				getParentList(studentservice.listParent(school_id)));
		model.put("school_details", schoolservice.getSchoolById(school_id));
		return new ModelAndView("school/all_parents", model);
		}catch(Exception e)
		{
			return new ModelAndView("redirect:manageParents");
		}
	}

	/**
	 * Function for edit parent
	 **/
	@RequestMapping(value = "school/editParent", method = RequestMethod.GET)
	public ModelAndView editParent(
			@ModelAttribute("command") LoginBean parents, BindingResult result,
			HttpServletRequest request) {
		try{
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int parent_id=Integer.parseInt(new String(decodedBytes));	
		LoginModel parent = schoolservice.getParentById(parent_id);
		String loadView = null;
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("school_details", schoolservice.getSchoolById(school_id));
		if (parent != null) {
			model.put("heading", "Edit Parent");
			model.put("parent", parent);
			SchoolModel schol= schoolservice.getSchoolById(school_id);
		    model.put("country_details", schoolservice.getCountryById(schol.getCountry()));
			return new ModelAndView("school/edit_parent", model);
		} else {
			return new ModelAndView("redirect:manageParents");
		}
	}catch(Exception e)
		{
			return new ModelAndView("redirect:manageParents");
		}

	}

	/**
	 * Function for edit parent
	 **/
	@RequestMapping(value = "school/editParent", method = RequestMethod.POST)
	public ModelAndView editParent(
			@ModelAttribute("command") LoginBean parents, BindingResult result,
			ModelMap model, HttpServletRequest request) {
		try{
		
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int parent_id=Integer.parseInt(new String(decodedBytes));
		int school_id = (Integer) session.getAttribute("schoolId");
		LoginModel parentModel = new LoginModel();
		parentModel.setFirst_name(parents.getFirst_name());
		parentModel.setLast_name("");
		parentModel.setFamily_name(parents.getFamily_name());
		parentModel.setMiddle_name(parents.getMiddle_name());
		parentModel.setMobile_number(parents.getMobile_number());
		parentModel.setContact_number(parents.getContact_number());
		parentModel.setUser_email(parents.getUser_email());
		parentModel.setUser_pass(parents.getUser_pass());
		parentModel.setUser_name(parents.getUser_name());
		parentModel.setSchool_id(parents.getSchool_id());
		schoolservice.editParentById(parent_id, parentModel);
		LoginModel parent = schoolservice.getParentById(parent_id);
		model.put("school_details", schoolservice.getSchoolById(school_id));
		SchoolModel schol= schoolservice.getSchoolById(school_id);
	    model.put("country_details", schoolservice.getCountryById(schol.getCountry()));
		model.put("success", "Parent Updated successfully");
		model.put("heading", "Edit Parent");
		model.put("parent", parent);
		return new ModelAndView("school/edit_parent", model);
		}catch(Exception e)
		{
			return new ModelAndView("redirect:manageParents");
		}
	}

	/**
	 * Function for view parent
	 **/
	@RequestMapping(value = "school/viewParent", method = RequestMethod.GET)
	public ModelAndView viewParent(
			@ModelAttribute("command") LoginBean parents, BindingResult result,
			StudentBean student, HttpServletRequest request) {
		try{
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int parent_id=Integer.parseInt(new String(decodedBytes));
		LoginModel parent = schoolservice.getParentById(parent_id);
		Map<String, Object> model = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");
		if (parent != null) {
			model.put("allParent", getParentList(schoolservice
					.getParentByPIdList(parent.getP_status())));
			model.put("heading", "View Parent");
			model.put("parent", parent);
			model.put("school_details", schoolservice.getSchoolById(school_id));
			model.put("students", getStudentList(studentservice
					.getStudentByParentStatus(parent.getP_status())));
			return new ModelAndView("school/view_parent", model);
		} else {
			return new ModelAndView("redirect:manageParents");
		}
		}catch(Exception e)
		{
			return new ModelAndView("redirect:manageParents");
		}

	}

	/**
	 * Function for view Student
	 **/
	@RequestMapping(value = "school/viewStudent", method = RequestMethod.GET)
	public ModelAndView viewStudent(
			@ModelAttribute("command") StudentBean students,
			BindingResult result, HttpServletRequest request) {
		try{

	    String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int student_id=Integer.parseInt(new String(decodedBytes));
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		StudentModel studentModel = studentservice.getStudentById(student_id);
		Map<String, Object> model = new HashMap<String, Object>();
		if (studentModel != null) {
			/*
			 * CityModel city=new CityModel(); CountryModel country=new
			 * CountryModel();
			 * city=frontService.getCityById(studentModel.getS_city());
			 * country=frontService.getCountryById(studentModel.getS_country());
			 */
			model.put("heading", "View Student");
			model.put("student", studentModel);
			/*
			 * model.put("city",city); model.put("country", country);
			 */
			model.put("routes", getAllRoute(schoolservice.listRoute(student_id)));
			RouteLatLngModel latlngmodel = studentservice
					.getLatLngBySId(student_id);
			model.put("allParent", getParentList(schoolservice
					.getParentByPIdList(studentModel.getP_status_id())));
			model.put("latlng", latlngmodel);
			int school_id = (Integer) session.getAttribute("schoolId");
			model.put("school_details", schoolservice.getSchoolById(school_id));
			return new ModelAndView("school/view_student", model);
		} else {
			return new ModelAndView("redirect:manageStudents");
		}
		}catch(Exception e)
		{
			return new ModelAndView("redirect:manageStudents");
		}

	}

	/**
	 * Function for edit student
	 **/
	@RequestMapping(value = "school/editStudent", method = RequestMethod.GET)
	public ModelAndView editStudent(
			@ModelAttribute("command") StudentBean students,
			RouteLatLng latlng, RouteBean route, BindingResult result,
			HttpServletRequest request) {
		try{
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int student_id=Integer.parseInt(new String(decodedBytes));
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		StudentModel studentModel = studentservice.getStudentById(student_id);

		Map<String, Object> model = new HashMap<String, Object>();
		if (studentModel != null) {
			int school_id = (Integer) session.getAttribute("schoolId");
			model.put("parents",
					getParentList(studentservice.listParent(school_id)));
			model.put("heading", "Edit Student");
			model.put("countries",
					prepareListofBean(schoolservice.listCountry()));
			model.put("routes", getAllRoute(schoolservice.listRoute(school_id)));
			model.put("studentBean", studentModel);
			RouteLatLngModel latlngmodel = studentservice
					.getLatLngBySId(student_id);
			model.put("latlng", latlngmodel);
			model.put("nationality", prepareNationalityList(schoolservice.getAllNationlity()));
			model.put("school_details", schoolservice.getSchoolById(school_id));
			model.put("class_info", getAllClassList(schoolservice.getAllSchoolClasses(school_id)));
			return new ModelAndView("school/edit_student", model);
		} else {
			return new ModelAndView("redirect:manageStudents");
		}
		}
		catch (Exception e) {
			return new ModelAndView("redirect:manageStudents");
		}

	}

	/**
	 * Function for edit Student
	 **/
	@RequestMapping(value = "school/editStudent", method = RequestMethod.POST)
	public ModelAndView editStudent(
			@ModelAttribute("command") StudentBean studentbean,
			RouteLatLng latlng, RouteBean route, BindingResult result,
			ModelMap model, HttpServletRequest request) {
		try {
			String q=request.getParameter("q");
			byte[] decodedBytes = Base64.decodeBase64(""+q+"");
			int student_id=Integer.parseInt(new String(decodedBytes));
			try {
			
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			String imageName = "";
			if (studentbean.getS_image_path() != "") {

				String filePath = studentbean.getS_image_path();
				String[] parts = filePath.split("base64,");
				String part2 = parts[1]; // 034556
				int rand = RandomUtils.nextInt();
				imageName =  studentbean.getS_contact()+"_"+rand + ".png";
				String directory = request.getServletContext().getRealPath(
						Assets.STUDENT_UPLOAD_PATH + imageName);
				byte[] imageByteArray = Base64.decodeBase64(part2);
				FileOutputStream imageOutFile = new FileOutputStream(directory);
				imageOutFile.write(imageByteArray);
				imageOutFile.close();
			} else {
				imageName = "";
			}
			SchoolModel schoolinfo = (SchoolModel) schoolservice
					.getSchoolById(studentbean.getS_school_id());
			LoginModel parent = schoolservice.getParentById(studentbean
					.getS_parent_id());
			String qr_name = "Student Id: Stud" + student_id;
			String qr_text = "Student Id: Stud" + student_id;
			qr_text += "\nStudent Name:" + studentbean.getS_fname() + " "
					+ studentbean.getS_lname();
			qr_text += "\nSchool Name:" + schoolinfo.getSchool_name();
			qr_text += "\nParent Contact:" + parent.getContact_number();
			qr_text=""+student_id;
			ByteArrayOutputStream out = QRCode.from(qr_text).to(ImageType.PNG)
					.stream();
			String qr_directory = request.getServletContext().getRealPath(
					Assets.STUDENT_QR_PATH + "/s_" + student_id + ".png");

			FileOutputStream fout = new FileOutputStream(new File(qr_directory));
			fout.write(out.toByteArray());
			fout.flush();
			fout.close();

			StudentModel student = new StudentModel();
			student.setS_fname(studentbean.getS_fname());
			student.setS_lname(studentbean.getS_lname());
			student.setS_email(studentbean.getS_email());
			student.setS_pass(studentbean.getS_pass());
			student.setS_image_path(imageName);
			student.setS_address(studentbean.getS_address());
			student.setS_city(studentbean.getS_city());
			student.setS_driver(studentbean.getS_driver());
			student.setS_route_id(route.getRoute_id());
			student.setS_country(studentbean.getS_country());
			student.setS_zip(studentbean.getS_zip());
			student.setS_parent_id(studentbean.getS_parent_id());
			student.setS_contact(studentbean.getS_contact());
			student.setP_status_id(studentbean.getP_status_id());
			student.setS_school_id(studentbean.getS_school_id());
			student.setFamily_name(studentbean.getFamily_name());
			student.setBlood_type(studentbean.getBlood_type());
			student.setDob(studentbean.getDob());
			student.setFather_name(studentbean.getFather_name());
			student.setGrand_name(studentbean.getGrand_name());
			student.setNationality(studentbean.getNationality());
			student.setGender(studentbean.getGender());
			student.setStudent_class(studentbean.getStudent_class());
			student.setBlink_status(0);
			
			
			List<String> relationship_details=studentbean.getRelationship_details();
			List<String> parent_list=studentbean.getParent_list();
			int relation_size=relationship_details.size();
			
			
			if(relation_size==2)
			{
				relationship_details.add(2, "");
				parent_list.add(2, "0");
				
				
			}else if(relation_size==1)
			{
				//relationship_details.add("");
				relationship_details.add(1, "");
				parent_list.add(1, "0");
				relationship_details.add(2, "");
				parent_list.add(2, "0");
			}
			
			if(relationship_details.get(0)!=null && !relationship_details.get(0).equals("") )
			{
				student.setP_1(Integer.parseInt(parent_list.get(0)));
				student.setR_1(relationship_details.get(0));
			}else{
				student.setP_1(0);
				student.setR_1("");
			}
			if(relationship_details.get(1)!=null && !relationship_details.get(1).equals("") )
			{
				student.setP_2(Integer.parseInt(parent_list.get(1)));
				student.setR_2(relationship_details.get(1));
			}else{
				student.setP_2(0);
				student.setR_2("");
			}
			if(relationship_details.get(2)!=null && !relationship_details.get(2).equals("") )
			{
				student.setP_3(Integer.parseInt(parent_list.get(2)));
				student.setR_3(relationship_details.get(2));
			}else{
				student.setP_3(0);
				student.setR_3("");
			}
			
			
			
			schoolservice.editStudentById(student_id, student);

			RouteLatLngModel routemodel = new RouteLatLngModel();
			String lat = latlng.getLat();
			String[] latArr = lat.split(",", -1);
			String lng = latlng.getLng();
			String[] lngArr = lng.split(",", -1);
			if (latlng.getCheckStatus() == 1) {
				routemodel.setLat(latArr[2]);
				routemodel.setLng(lngArr[2]);
				routemodel.setRoute_id(route.getRoute_id());
				System.out.println("New=" + latArr[2]);
			} else {
				routemodel.setLat(latArr[1]);
				routemodel.setLng(lngArr[1]);
				routemodel.setRoute_id(0);
				System.out.println("Update=" + latArr[1]);
			}

			
			
			RouteLatLngModel routemodelCheck = new RouteLatLngModel();
			routemodelCheck= schoolservice.getStudentRouteLatLng(student_id);
			
			if(routemodelCheck!=null)
			{
				schoolservice.editRouteByStudentId(student_id,routemodel);
			}else
			{
				/*routemodel.setStudent_id(student_id);
				routemodel.setStudent_name(studentbean.getS_fname());
				routemodel.setAddress(s_address);
				routemodel.setParent_id(studentbean.getP_status_id());
				routemodel.setRoute_id(route_id);*/
				routemodel.setId(null);
				routemodel.setSchool_id(studentbean.getS_school_id());
				routemodel.setRoute_id(route.getRoute_id());
				routemodel.setLat(latArr[latArr.length - 1]);
				routemodel.setLng(lngArr[lngArr.length - 1]);
				routemodel.setStudent_id((int) student_id);
				routemodel.setStudent_name(studentbean.getS_fname());
				routemodel.setAddress(studentbean.getS_address());
				routemodel.setParent_id(studentbean.getP_status_id());
				schoolservice.addRouteLatLng(routemodel);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:manageStudents");
		}catch(Exception e)
		{
			return new ModelAndView("redirect:manageStudents");
		}
	}

	/**
	 * Function for add vehicle get method
	 **/
	@RequestMapping(value = "school/addVechile", method = RequestMethod.GET)
	public ModelAndView addVechile(
			@ModelAttribute("command") VechileBean vechilebean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		model.put("drivers",getDriversList(schoolservice.listDriver(school_id)));
		model.addAttribute("heading", "Add Vehicle");
		return new ModelAndView("school/add_vechile");
	}

	/**
	 * Function for add vehicle post data
	 **/
	@RequestMapping(value = "school/addVechile", method = RequestMethod.POST)
	public ModelAndView addVechile(
			@ModelAttribute("command") VechileBean vechileBean,
			BindingResult result, HttpServletRequest request, ModelMap model) {
		try {
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			VehicleModel vechile_check = new VehicleModel();
			vechile_check=schoolservice.getVehicleCheck(vechileBean.getBus_number());
			VehicleModel vechile = new VehicleModel();
			if (vechile_check != null)
			{
				model.addAttribute("vechileBean", vechile);
				model.addAttribute("error", "Vechile already exist");
			}else
			{
			int user_id = (Integer) session.getAttribute("user_id");
			LoginModel found = schoolservice.getUserById(user_id);
			SchoolModel schoolinfo = (SchoolModel) schoolservice
					.getSchoolById(found.getSchool_id());
			int totalVehicle = (Integer) schoolservice.getVehicleCount(found
					.getSchool_id());
			if (totalVehicle >= schoolinfo.getTotal_bus()) {
			 
				vechile.setBus_number(vechileBean.getBus_number());
				vechile.setColor(vechileBean.getColor());
				vechile.setConfigurtion(vechileBean.getConfigurtion());
				vechile.setEngine(vechileBean.getEngine());
				vechile.setManufacture(vechileBean.getManufacture());
				vechile.setModel(vechileBean.getModel());
				vechile.setSchool_id(vechileBean.getSchool_id());
				vechile.setVehile_name(vechileBean.getVehile_name());
				vechile.setVechile_id(null);
				vechile.setYear(vechileBean.getYear());
				model.addAttribute("vechileBean", vechile);
				model.addAttribute("error", "You can not add more busses");
			} else {
				 
				vechile.setBus_number(vechileBean.getBus_number());
				vechile.setColor(vechileBean.getColor());
				vechile.setConfigurtion(vechileBean.getConfigurtion());
				vechile.setEngine(vechileBean.getEngine());
				vechile.setManufacture(vechileBean.getManufacture());
				vechile.setModel(vechileBean.getModel());
				vechile.setSchool_id(vechileBean.getSchool_id());
				vechile.setVehile_name(vechileBean.getVehile_name());
				vechile.setVechile_id(null);
				vechile.setYear(vechileBean.getYear());
				/*vechile.setInsurance_end_date(vechileBean
						.getInsurance_end_date());*/
				vechile.setInsurance_end_date("0000-00-00");
				
				
				List<String> fileNames = new ArrayList<String>();
				List<String> insurance_document_name=vechileBean.getInsurance_document_name();
				List<String> insurance_document_expiry=vechileBean.getInsurance_document_expiry();
				List<String> insurance_remind=vechileBean.getRemind_day();
				List<String> insurance_end_date_e=vechileBean.getInsurance_end_date_e();
				String in_end_date="";
				for(String a: insurance_end_date_e)
				{
					in_end_date +=a+",";
				}
				String in_doc_name="";
				for(String a : insurance_document_name)
				{
					 
					in_doc_name +=a+",";
					 
				 
				}
				
				
				String in_doc_date="";
				for(String a : insurance_document_expiry)
				{
					in_doc_date +=a+",";
				 
				}
				
				
				String in_doc_remind="";
				for(String a : insurance_remind)
				{
					in_doc_remind +=a+",";
				 
				}
				 
				List<MultipartFile> files = vechileBean.getInsurance_card_copy();
				String new_file_name = "";
				if (null != files && files.size() > 0) {
					for (MultipartFile multipartFile : files) {
						System.out.println(multipartFile.getSize());
						
						String fileName = multipartFile.getOriginalFilename();
						// fileNames.add(fileName);
						if (!fileName.isEmpty()) {
							byte[] bytes = fileName.getBytes();
							System.out.println(fileName.toString());
							String[] fn = fileName.toString().split("\\.");
							int rand = RandomUtils.nextInt();
							String directory = request.getServletContext()
									.getRealPath(
											Assets.INSURANCE_CARD_COPY
													+ rand
													+ vechileBean
															.getVehile_name()
													+ "." + fn[1]);
							new_file_name += rand
									+ vechileBean.getVehile_name() + "."
									+ fn[1] + ",";
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(directory));
							stream.write(multipartFile.getBytes());
							stream.close();
						} else {
							vechile.setInsurance_card_copy("");
						}

					}
				}

				/*String new_file_name = "";
				if (null != files && files.size() > 0) {
					for (MultipartFile multipartFile : files) {
						System.out.println(multipartFile.getSize());
						String fileName = multipartFile.getOriginalFilename();
						// fileNames.add(fileName);
						if (!fileName.isEmpty()) {
							byte[] bytes = fileName.getBytes();
							System.out.println(fileName.toString());
							String[] fn = fileName.toString().split("\\.");
							int rand = RandomUtils.nextInt();
							String directory = request.getServletContext()
									.getRealPath(
											Assets.INSURANCE_CARD_COPY
													+ rand
													+ vechileBean
															.getVehile_name()
													+ "." + fn[1]);
							new_file_name += rand
									+ vechileBean.getVehile_name() + "."
									+ fn[1] + ",";
							BufferedOutputStream stream = new BufferedOutputStream(
									new FileOutputStream(directory));
							stream.write(bytes);
							stream.close();
						} else {
							vechile.setInsurance_card_copy("");
						}

					}
				}*/
				vechile.setInsurance_card_copy(new_file_name);
				vechile.setInsurance_document_name(in_doc_name);
				vechile.setInsurance_document_expiry(in_doc_date);
				vechile.setRemind_day(in_doc_remind);
				vechile.setDriver_id(vechileBean.getDriver_id());
				long vechile_id=vechileservice.addVechile(vechile);
				
				int j=0;
				List<String> items = Arrays.asList(new_file_name.split("\\s*,\\s*"));
				for(String a : insurance_document_name)
				{
					VehicleDocModel doc=new VehicleDocModel();
					doc.setInsurance_document_name(a);
					doc.setInsurance_document_expiry(insurance_document_expiry.get(j));
					doc.setRemind_day(insurance_remind.get(j));
					doc.setInsurance_end_date(insurance_end_date_e.get(j));
					doc.setV_id((int) vechile_id);
					doc.setSchool_id((Integer) session.getAttribute("schoolId"));
					doc.setInsurance_card_copy(items.get(j));
					doc.setStatus(0);
					doc.setNoti_type(0);
					if(insurance_document_expiry.get(j)!="" && !insurance_document_expiry.get(j).equals(""))
					{
						vechileservice.addVechileDoc(doc);
					}
					j++;
				}
				//vechileservice.addVechile(vechile);
				vechile = null;
				model.addAttribute("heading", "Add Vehicle");
				model.addAttribute("vechileBean", vechile);
				model.put("drivers",getDriversList(schoolservice.listDriver((Integer) session.getAttribute("schoolId"))));
				model.put("school_details", schoolservice.getSchoolById((Integer) session.getAttribute("schoolId")));
				model.addAttribute("success", "Vechile Added Successfully");
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("heading", "Add Vehicle");
		return new ModelAndView("school/add_vechile");

	}

	@RequestMapping(value = "school/manageVehicle", method = RequestMethod.GET)
	public ModelAndView manageVechile(
			@ModelAttribute("command") VechileBean vechilebean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		model.addAttribute("heading", "Manage Vehicle");
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("vechiles",
				getAllVechiles(vechileservice.getVechile(school_id)));
		model.put("school_details", schoolservice.getSchoolById(school_id));
		return new ModelAndView("school/all_vechiles");
	}

	/* Method for get country list */
	private List<VechileBean> getAllVechiles(List<VehicleModel> vechiles) {

		List<VechileBean> beans = null;
		if (vechiles != null && !vechiles.isEmpty()) {
			beans = new ArrayList<VechileBean>();
			VechileBean bean = null;
			for (VehicleModel vechile : vechiles) {
				bean = new VechileBean();
				bean.setBus_number(vechile.getBus_number());
				if(vechile.getDriver_id()!=null && vechile.getDriver_id()!=0)
				{
					DriverModel d=schoolservice.getDriverById(vechile.getDriver_id());
					if(d!=null)
					{
						bean.setColor(d.getDriver_fname()+" "+d.getDriver_lname());
					}else
					{
						bean.setColor("N/A");
					}
					
				}else
				{
					bean.setColor("N/A");
				}
				
				bean.setConfigurtion(vechile.getConfigurtion());
				bean.setEngine(vechile.getEngine());
				bean.setManufacture(vechile.getManufacture());
				bean.setModel(vechile.getModel());
				bean.setVechile_id(vechile.getVechile_id());
				bean.setVehile_name(vechile.getVehile_name());
				bean.setYear(vechile.getYear());
				bean.setInsurance_end_date(vechile.getInsurance_end_date());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/*
	 * Function for delete
	 */
	@RequestMapping(value = "school/deleteVechile", method = RequestMethod.GET)
	public ModelAndView deleteVechile(
			@ModelAttribute("command") VechileBean vechiles,
			BindingResult result) {

		vechileservice.deleteVechile(vechiles.getVechile_id());
		return new ModelAndView("redirect:manageVehicle");
	}

	/**
	 * Function for edit student
	 **/
	@RequestMapping(value = "school/editVechile", method = RequestMethod.GET)
	public ModelAndView editVechile(
			@ModelAttribute("command") VechileBean vechile,
			BindingResult result, HttpServletRequest request) {
		try{
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int vehicle_id=Integer.parseInt(new String(decodedBytes));
		VehicleModel vehiclemodel = vechileservice.getVehicleById(vehicle_id);
		String ed=request.getParameter("v");
		if(ed != null)
		{
			byte[] decodedBytes1 = Base64.decodeBase64(""+ed+"");
			schoolservice.updateHeadNotification(Integer.parseInt(new String(decodedBytes1)));
			
		}
		Map<String, Object> model = new HashMap<String, Object>();
		if (vehiclemodel != null) {
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			int school_id = (Integer) session.getAttribute("schoolId");
			model.put("school_details", schoolservice.getSchoolById(school_id));
			model.put("heading", "Edit Vehicle");
			model.put("vechileBean", vehiclemodel);
			model.put("documents", prepareListOfVehicleDoc(schoolservice.getVehicleDocument(vehiclemodel.getVechile_id())));
			model.put("drivers",getDriversList(schoolservice.listDriver((Integer) session.getAttribute("schoolId"))));
			return new ModelAndView("school/edit_vehicle", model);
		} else {
			return new ModelAndView("redirect:manageVehicle");
		}
	  }catch(Exception e)
		{
		    return new ModelAndView("redirect:manageVehicle");
		}

	}

	/**
	 * Function for edit Vehicle
	 **/
	@RequestMapping(value = "school/editVechile", method = RequestMethod.POST)
	public ModelAndView editVechile(
			@ModelAttribute("command") VechileBean vechile,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		
		try{
			String q=request.getParameter("q");
			byte[] decodedBytes = Base64.decodeBase64(""+q+"");
			int vehicle_id=Integer.parseInt(new String(decodedBytes));
		try {
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			VehicleModel vehiclemodel = new VehicleModel();
			vehiclemodel.setBus_number(vechile.getBus_number());
			vehiclemodel.setColor(vechile.getColor());
			vehiclemodel.setConfigurtion(vechile.getConfigurtion());
			vehiclemodel.setEngine(vechile.getEngine());
			vehiclemodel.setManufacture(vechile.getManufacture());
			vehiclemodel.setModel(vechile.getModel());
			vehiclemodel.setVechile_id(vehicle_id);
			vehiclemodel.setVehile_name(vechile.getVehile_name());
			vehiclemodel.setYear(vechile.getYear());
			
			/*vehiclemodel.setInsurance_end_date(vechile
					.getInsurance_end_date());*/
			vehiclemodel.setInsurance_end_date("0000-00-00");
			
			List<String> insurance_document_name=vechile.getInsurance_document_name();
			List<String> insurance_document_expiry=vechile.getInsurance_document_expiry();
			List<String> insurance_remind=vechile.getRemind_day();
			List<String> insurance_end_date_e=vechile.getInsurance_end_date_e();
			
	        String in_end_date="";
	        if(insurance_end_date_e!=null){
			for(String a: insurance_end_date_e){
				in_end_date+=a+",";
						
			}
	        }
			String in_doc_name="";
			 if(insurance_document_name!=null){
			for(String a : insurance_document_name)
			{
				 
				in_doc_name +=a+",";
				 
			 
			}
			 }
			
			String in_doc_date="";
			 if(insurance_document_expiry!=null){
			for(String a : insurance_document_expiry)
			{
				in_doc_date +=a+",";
			 
			}
			 }
			
			String in_doc_remind="";
			 if(insurance_remind!=null){
			for(String a : insurance_remind)
			{
				in_doc_remind +=a+",";
			 
			}
			 }
			// MultipartFile file = vechileBean.getInsurance_card_copy();
			List<MultipartFile> files = vechile.getInsurance_card_copy();
			List<String> fileNames = new ArrayList<String>();
			MultipartFile abc=null;
			String new_file_name = "";
		
			if (null != files && files.size() > 0) {
				for (MultipartFile multipartFile : files) {
					if (multipartFile.getOriginalFilename() != null
							&& multipartFile.getOriginalFilename() != "" || !multipartFile.getOriginalFilename().equals("") ) {
						String fileName = multipartFile
								.getOriginalFilename();

						if (!fileName.isEmpty()) {
							byte[] bytes = fileName.getBytes();
							String[] fn = fileName.toString().split("\\.");
							int rand = RandomUtils.nextInt();
							String directory = request
									.getServletContext()
									.getRealPath(
											Assets.INSURANCE_CARD_COPY+rand + fn[0] + "." + fn[1]);
							new_file_name += rand + fn[0] + "." + fn[1]
									+ ",";
							
							BufferedOutputStream stream = new BufferedOutputStream(
									new FileOutputStream(directory));
							System.out.println(directory);
							stream.write(multipartFile.getBytes());
							stream.close();
						} else {
							vehiclemodel.setInsurance_card_copy("");
							new_file_name +=",";
						}
						
					}else
					{
						new_file_name +=",";
					}


				}
			}
			else
			{
				new_file_name +=",";
			}
			vehiclemodel.setInsurance_card_copy(vechile.getColor()
					+ new_file_name);

			vehiclemodel.setInsurance_document_name(in_doc_name);
			vehiclemodel.setInsurance_document_expiry(in_doc_date);
			vehiclemodel.setRemind_day(in_doc_remind);
			int j=0;
			List<String> v_doc_id=vechile.getV_doc_id();
			List<String> items = Arrays.asList(new_file_name.split("\\s*,\\s*"));
			if(insurance_document_name!=null)
			{
			for(String a : insurance_document_name)
			{
				if(!v_doc_id.get(j).equals("0"))
				{
					VehicleDocModel doc=new VehicleDocModel();
					doc.setInsurance_document_name(a);
					doc.setInsurance_document_expiry(insurance_document_expiry.get(j));
					doc.setInsurance_end_date(insurance_end_date_e.get(j));
					doc.setRemind_day(insurance_remind.get(j));
					doc.setInsurance_card_copy(items.get(j));
					vechileservice.editVechileDocById(Integer.parseInt(v_doc_id.get(j)), doc);
				}
				else
				{
					VehicleDocModel doc=new VehicleDocModel();
					doc.setInsurance_document_name(a);
					doc.setInsurance_document_expiry(insurance_document_expiry.get(j));
					doc.setInsurance_end_date(insurance_end_date_e.get(j));
					doc.setRemind_day(insurance_remind.get(j));
					doc.setV_id((int) vehicle_id);
					doc.setSchool_id((Integer) session.getAttribute("schoolId"));
					doc.setInsurance_card_copy(items.get(j));
					doc.setStatus(0);
					doc.setNoti_type(0);
					if(insurance_document_expiry.get(j)!="" && !insurance_document_expiry.get(j).equals(""))
					{
						vechileservice.addVechileDoc(doc);
					}
					
				}
				j++;
			}
			}
			vehiclemodel.setSchool_id(vechile.getSchool_id());
			vehiclemodel.setDriver_id(vechile.getDriver_id());
			vechileservice.editVechileById(vehicle_id,
					vehiclemodel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:manageVehicle");
		}catch(Exception e)
		{
			return new ModelAndView("redirect:manageVehicle");
		}
	}

	/**
	 * Function for add staff member
	 * */
	@RequestMapping(value = "school/addStaff", method = RequestMethod.GET)
	public ModelAndView addStaff(
			@ModelAttribute("command") StaffBean staffbean,
			BindingResult result, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("departments", getDepartments(staffservice.listDepartment()));
		model.put("staff", staffbean);
		model.put("heading", "Add Staff Memeber");
		return new ModelAndView("school/add_staff", model);
	}

	/* Method for get country list */
	private List<DepartmentBean> getDepartments(
			List<DepartmentModel> departments) {

		List<DepartmentBean> beans = null;
		if (departments != null && !departments.isEmpty()) {
			beans = new ArrayList<DepartmentBean>();
			DepartmentBean bean = null;
			for (DepartmentModel depart : departments) {
				bean = new DepartmentBean();
				bean.setDepart_id(depart.getDepart_id());
				bean.setDepart_name(depart.getDepart_name());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Function for add staff post method
	 **/
	@RequestMapping(value = "school/addStaff", method = RequestMethod.POST)
	public ModelAndView addStaff(
			@ModelAttribute("command") StaffBean staffbean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			StaffModel found = staffservice.checkSchoolName(staffbean
					.getStaff_email());
			if (found == null) {
				String imageName = "";
				if (staffbean.getImage_path() != "") {
					String filePath = staffbean.getImage_path();
					String[] parts = filePath.split("base64,");
					String part2 = parts[1]; // 034556
					int rand = RandomUtils.nextInt();
					System.out.println(rand);
					imageName = staffbean.getStaff_name() + rand + ".png";
					String directory = request.getServletContext().getRealPath(
							Assets.STAFF_UPLOAD_PATH + imageName);
					byte[] imageByteArray = Base64.decodeBase64(part2);
					// Write a image byte array into file system
					FileOutputStream imageOutFile = new FileOutputStream(
							directory);
					imageOutFile.write(imageByteArray);
					imageOutFile.close();
				} else {
					imageName = "";
				}

				StaffModel staffmodel = new StaffModel();
				staffmodel.setAddress(staffbean.getAddress());
				staffmodel.setDept_id(staffbean.getDept_id());
				staffmodel.setEducation(staffbean.getEducation());
				staffmodel.setImage_path(imageName);
				staffmodel.setSchool_id(staffbean.getSchool_id());
				staffmodel.setStaff_contact(staffbean.getStaff_contact());
				staffmodel.setStaff_email(staffbean.getStaff_email());
				staffmodel.setStaff_id(null);
				staffmodel.setStaff_name(staffbean.getStaff_name());
				staffmodel.setStaff_lname(staffbean.getStaff_lname());
				staffservice.addStaff(staffmodel);
				model.addAttribute("success", "Staff Member Added Successfully");
			} else {
				model.addAttribute("staff", staffbean);
				model.addAttribute("error", "This Staff member already exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.put("departments", getDepartments(staffservice.listDepartment()));
		model.put("staff", staffbean);
		model.put("heading", "Add Staff Memeber");
		return new ModelAndView("school/add_staff", model);

	}

	/**
	 * Function for manage staff
	 **/
	@RequestMapping(value = "school/manageStaff", method = RequestMethod.GET)
	public ModelAndView manageStaff(
			@ModelAttribute("command") StaffBean staffbean,
			BindingResult result, ModelMap modelH, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		modelH.addAttribute("heading", "Manage Staff");
		Map<String, Object> model = new HashMap<String, Object>();
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("staffs", getStaffList(staffservice.listStaff(school_id)));
		return new ModelAndView("school/all_staff", model);
	}

	/* Method for get Student list */
	private List<StaffBean> getStaffList(List<StaffModel> staff) {

		List<StaffBean> beans = null;
		if (staff != null && !staff.isEmpty()) {
			beans = new ArrayList<StaffBean>();
			StaffBean bean = null;
			for (StaffModel st : staff) {
				bean = new StaffBean();
				bean.setAddress(st.getAddress());
				bean.setDept_id(st.getDept_id());
				bean.setEducation(st.getEducation());
				bean.setImage_path(st.getImage_path());
				bean.setSchool_id(st.getSchool_id());
				bean.setStaff_contact(st.getStaff_contact());
				bean.setStaff_email(st.getStaff_email());
				bean.setStaff_id(st.getStaff_id());
				bean.setStaff_name(st.getStaff_name());
				bean.setStaff_lname(st.getStaff_lname());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
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
				bean.setNote(st.getNote());
				bean.setSource_note(st.getSource_note());
				bean.setDestination_note(st.getDestination_note());
				bean.setDestination(st.getDestination());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Function for edit Staff
	 **/
	@RequestMapping(value = "school/editStaff", method = RequestMethod.GET)
	public ModelAndView editStudent(
			@ModelAttribute("command") StaffBean staffbean,
			BindingResult result, HttpServletRequest request) {
		StaffModel staffmodel = staffservice.getStaffById(staffbean
				.getStaff_id());
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		if (staffmodel != null) {
			model.put("departments",
					getDepartments(staffservice.listDepartment()));
			model.put("heading", "Edit Staff");
			model.put("staff", staffmodel);

			return new ModelAndView("school/edit_staff", model);
		} else {
			return new ModelAndView("redirect:manageStaff");
		}

	}

	/**
	 * Function for edit parent
	 **/
	@RequestMapping(value = "school/editStaff", method = RequestMethod.POST)
	public ModelAndView editStaff(
			@ModelAttribute("command") StaffBean staffbean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			String imageName = "";
			if (staffbean.getImage_path() != "") {

				String filePath = staffbean.getImage_path();
				String[] parts = filePath.split("base64,");
				String part2 = parts[1]; // 034556
				int rand = RandomUtils.nextInt();
				imageName = staffbean.getStaff_name() + rand + ".png";
				String directory = request.getServletContext().getRealPath(
						Assets.STAFF_UPLOAD_PATH + imageName);
				byte[] imageByteArray = Base64.decodeBase64(part2);
				FileOutputStream imageOutFile = new FileOutputStream(directory);
				imageOutFile.write(imageByteArray);
				imageOutFile.close();
			} else {
				imageName = "";
			}

			StaffModel staffmodel = new StaffModel();
			staffmodel.setAddress(staffbean.getAddress());
			staffmodel.setDept_id(staffbean.getDept_id());
			staffmodel.setEducation(staffbean.getEducation());
			staffmodel.setImage_path(imageName);
			staffmodel.setStaff_contact(staffbean.getStaff_contact());
			staffmodel.setStaff_email(staffbean.getStaff_email());
			staffmodel.setStaff_id(staffbean.getStaff_id());
			staffmodel.setStaff_name(staffbean.getStaff_name());
			staffmodel.setStaff_lname(staffbean.getStaff_lname());
			staffservice.editStaffById(staffbean.getStaff_id(), staffmodel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:manageStaff");
	}

	@RequestMapping(value = "school/deleteStaff", method = RequestMethod.GET)
	public ModelAndView editEmployee(
			@ModelAttribute("command") StaffBean staffbean,
			BindingResult result, ModelMap modelH) {

		staffservice.deleteStaff(staffbean.getStaff_id());
		return new ModelAndView("redirect:manageStaff");

	}

	@RequestMapping(value = "school/schoolInfomation", method = RequestMethod.GET)
	public ModelAndView schoolInfomation(
			@ModelAttribute("command") SchoolBean schoolbean,
			BindingResult result, HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int user_id = (Integer) session.getAttribute("user_id");
		LoginModel found = schoolservice.getUserById(user_id);
		model.put("countries", prepareListofBean(schoolservice.listCountry()));
		SchoolModel schoolinfo = (SchoolModel) schoolservice
				.getSchoolById(found.getSchool_id());
		model.addAttribute("schoolModel", schoolinfo);

		model.addAttribute("heading", "School Information");

		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		return new ModelAndView("school/school_infomation");
	}

	/**
	 * Method for get City by state id
	 * **/
	@RequestMapping(value = "school/getSchoolCity", method = RequestMethod.POST)
	public ModelAndView getCityByState(
			@ModelAttribute("command") CityBean city, BindingResult result,
			ModelMap modal) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		modal.addAttribute("city_id", city.getCity_id());
		model.put("cities",
				getCities(frontservice.listCity(city.getCountry_id())));
		return new ModelAndView("school/school_city", model);
	}

	/* Method for get city list by state */
	private List<CityBean> getCities(List<CityModel> cities) {

		List<CityBean> beans = null;
		if (cities != null && !cities.isEmpty()) {
			beans = new ArrayList<CityBean>();
			CityBean bean = null;
			for (CityModel city : cities) {
				bean = new CityBean();
				bean.setCity_id(city.getCity_id());
				bean.setCity_name(city.getCity_name());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Function for Update School Information
	 **/
	@RequestMapping(value = "school/schoolInfomation", method = RequestMethod.POST)
	public ModelAndView schoolInfomation(
			@ModelAttribute("command") SchoolBean school, BindingResult result,
			ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		SchoolModel schoolmodel = new SchoolModel();
		schoolmodel.setCity(school.getCity());
		schoolmodel.setCountry(school.getCountry());
		schoolmodel.setPrincipal_contact(school.getPrincipal_contact());
		schoolmodel.setPrincipal_name(school.getPrincipal_name());
		schoolmodel.setSchool_name(school.getSchool_name());
		schoolmodel.setZip_code(school.getZip_code());
		int user_id = (Integer) session.getAttribute("user_id");
		LoginModel found = schoolservice.getUserById(user_id);
		schoolservice.editSchoolById(found.getSchool_id(), schoolmodel);
		model.addAttribute("success", "School information updated successfully");

		model.put("countries", prepareListofBean(schoolservice.listCountry()));
		SchoolModel schoolinfo = (SchoolModel) schoolservice
				.getSchoolById(found.getSchool_id());
		model.addAttribute("schoolModel", schoolinfo);

		model.addAttribute("heading", "School Information");
		return new ModelAndView("school/school_infomation");
	}

	/**
	 * Function for add Driver
	 * */
	@RequestMapping(value = "school/addDriver", method = RequestMethod.GET)
	public ModelAndView addDriver(
			@ModelAttribute("command") DriverBean driverbean,
			BindingResult result, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("school_details", schoolservice.getSchoolById(school_id));
		model.put("heading", "Add Driver");
		model.put("routes", getAllRoute(schoolservice.listRoute(school_id)));
		model.put("nationality", prepareNationalityList(schoolservice.getAllNationlity()));
		SchoolModel schol= schoolservice.getSchoolById(school_id);
		model.put("country_details", schoolservice.getCountryById(schol.getCountry()));
		
		return new ModelAndView("school/add_driver", model);
	}

	/**
	 * Function for add staff post method
	 **/
	@RequestMapping(value = "school/addDriver", method = RequestMethod.POST)
	public ModelAndView addStaff(
			@ModelAttribute("command") DriverBean driverbean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");

		try {
			// DriverModel
			// found=schoolservice.checkDriverName(driverbean.getD_email());
			DriverModel found = schoolservice.checkDriverContact(driverbean
					.getContact_number());
			RandomNumber random = new RandomNumber(9999);
			String f_name = driverbean.getDriver_fname().substring(0, 1);
			int password = random.generateNewRandom(5000);// random.nextSessionId();

			String username = f_name + "." + driverbean.getDriver_lname();
			String username1 = f_name + "." + driverbean.getDriver_lname();
			username = username.toLowerCase();
			username1 = username1.toLowerCase();
			username = username.replaceAll(" ", "_").toLowerCase();
			username1 = username1.replaceAll(" ", "_").toLowerCase();	
			int y = 1;
			int x = 1;
			while (y < 2) {
				DriverModel check = schoolservice.checkDriverUserName(username);
				if (check == null) {
					username = username;
					break;
				} else {
					y = 1;
					username = username1 + x;
				}
				x++;
			}
			if (found == null) {
				String imageName = "";
				if (driverbean.getImage_path() != "" && !driverbean.getImage_path().equals("")) {
					String filePath = driverbean.getImage_path();
					String[] parts = filePath.split("base64,");
					String part2 = parts[1]; // 034556
					int rand = RandomUtils.nextInt();
					imageName = driverbean.getContact_number() + rand + ".png";
					String directory = request.getServletContext().getRealPath(
							Assets.DRIVER_UPLOAD_PATH + imageName);
					byte[] imageByteArray = Base64.decodeBase64(part2);
					// Write a image byte array into file system
					FileOutputStream imageOutFile = new FileOutputStream(
							directory);
					imageOutFile.write(imageByteArray);
					imageOutFile.close();
				} else {
					imageName = "";
				}

				DriverModel drivermodel = new DriverModel();
				// drivermodel.setAddress(driverbean.getAddress());
				drivermodel.setContact_number(driverbean.getContact_number());
				drivermodel.setD_email(driverbean.getD_email());
				drivermodel.setDriver_fname(driverbean.getDriver_fname());
				drivermodel.setDriver_id(null);
				drivermodel.setPassword("" + password);
				drivermodel.setDriver_lname(driverbean.getDriver_lname());
				drivermodel.setDriver_school_id(school_id);
				drivermodel.setImage_path(imageName);
				drivermodel.setStatus(1);
				drivermodel.setUsername(username);
				drivermodel.setMiddle_name(driverbean.getMiddle_name());
				drivermodel.setDevice_id("");
				drivermodel.setLang(0);
				drivermodel.setLogged_status(0);
				if(driverbean.getDob()!="" && !driverbean.getDob().equals(""))
				{
					drivermodel.setDob(driverbean.getDob());
				}else
				{
					drivermodel.setDob("0000-00-00");
				}
				
				drivermodel.setNationality(driverbean.getNationality());
				if(driverbean.getLicence_expiry()!="" && !driverbean.getLicence_expiry().equals(""))
				{
					drivermodel.setLicence_expiry(driverbean.getLicence_expiry());
				}
				else
				{
					drivermodel.setLicence_expiry("0000-00-00");
				}
				
				drivermodel.setBlood_group(driverbean.getBlood_group());
				drivermodel.setInsurance_card_copy("");
				drivermodel.setRemind_day("");
				drivermodel.setInsurance_document_expiry("");
				drivermodel.setInsurance_document_name("");
				System.out.println("here"+driverbean.getRoute_id());
				if (driverbean.getRoute_id() != 0) {
					drivermodel.setRoute_id(driverbean.getRoute_id());
				} else {
					drivermodel.setRoute_id(0);
				}
				long insert_id =schoolservice.addDriver(drivermodel);
				
				String qr_text = ""+insert_id+"";
				ByteArrayOutputStream out = QRCode.from(qr_text)
						.to(ImageType.PNG).stream();
				String qr_directory = request.getServletContext()
						.getRealPath(
								Assets.STUDENT_QR_PATH + "d_" + insert_id
										+ ".png");
				
				FileOutputStream fout = new FileOutputStream(new File(
						qr_directory));
				fout.write(out.toByteArray());
				fout.flush();
				fout.close();
				
				
				
				
				/*Sms_api sms = new Sms_api();
				sms.sendhttp("Your login details are username=" + username
						+ " and password=" + password, 0,
						driverbean.getContact_number());*/
				List<String> fileNames = new ArrayList<String>();
				List<String> insurance_document_name=driverbean.getInsurance_document_name();
				List<String> insurance_document_expiry=driverbean.getInsurance_document_expiry();
				List<String> insurance_remind=driverbean.getRemind_day();
				List<String> insurance_end_date=driverbean.getInsurance_end_date();
				String in_end_date="";
				for(String a : insurance_end_date)
				{
					in_end_date +=a+",";
				}
				
				String in_doc_name="";
				for(String a : insurance_document_name)
				{
					in_doc_name +=a+",";
				}
				String in_doc_date="";
				for(String a : insurance_document_expiry)
				{
					in_doc_date +=a+",";
				}
				String in_doc_remind="";
				for(String a : insurance_remind)
				{
					in_doc_remind +=a+",";
				}
				List<MultipartFile> files = driverbean.getInsurance_card_copy();
				String new_file_name = "";
				if (null != files && files.size() > 0) {
					for (MultipartFile multipartFile : files) {
						System.out.println(multipartFile.getSize());
						
						String fileName = multipartFile.getOriginalFilename();
						// fileNames.add(fileName);
						if (!fileName.isEmpty()) {
							byte[] bytes = fileName.getBytes();
							System.out.println(fileName.toString());
							String[] fn = fileName.toString().split("\\.");
							int rand = RandomUtils.nextInt();
							String directory = request.getServletContext()
									.getRealPath(
											Assets.INSURANCE_CARD_COPY
													+ rand
													+ driverbean
															.getDriver_fname()
													+ "." + fn[1]);
							new_file_name += rand
									+ driverbean.getDriver_fname() + "."
									+ fn[1] + ",";
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(directory));
							stream.write(multipartFile.getBytes());
							stream.close();
						} else {
							drivermodel.setInsurance_card_copy("");
						}

					}
				}
				
				drivermodel.setInsurance_card_copy(new_file_name);
				drivermodel.setInsurance_document_name(in_doc_name);
				drivermodel.setInsurance_document_expiry(in_doc_date);
				drivermodel.setRemind_day(in_doc_remind);
				long vechile_id=insert_id;
				
				int j=0;
				List<String> items = Arrays.asList(new_file_name.split("\\s*,\\s*"));
				for(String a : insurance_document_name)
				{
					DriverDocModel doc=new DriverDocModel();
					doc.setInsurance_document_name(a);
					doc.setInsurance_document_expiry(insurance_document_expiry.get(j));
					doc.setInsurance_end_date(insurance_end_date.get(j));
					doc.setRemind_day(insurance_remind.get(j));
					doc.setV_id((int) vechile_id);
					doc.setSchool_id((Integer) session.getAttribute("schoolId"));
					doc.setInsurance_card_copy(items.get(j));
					doc.setStatus(0);
					doc.setNoti_type(1);
					if(insurance_document_expiry.get(j)!="" && !insurance_document_expiry.get(j).equals(""))
					{
						vechileservice.addDriverDoc(doc);	
					}
					j++;
				}
				
				
				model.addAttribute("driver_contact",driverbean.getContact_number());
				driverbean = null;
				model.addAttribute("username", username);
				model.addAttribute("password", password);
				model.addAttribute("heading", "Add Driver");
				model.addAttribute("success", "Driver Added Successfully");
				model.put("school_details",
						schoolservice.getSchoolById(school_id));

			} else {
				model.addAttribute("driver", driverbean);
				model.addAttribute("error", "This Driver already exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		SchoolModel schol= schoolservice.getSchoolById(school_id);
		model.put("country_details", schoolservice.getCountryById(schol.getCountry()));
		model.put("driver", driverbean);
		model.put("routes", getAllRoute(schoolservice.listRoute(school_id)));
		model.put("heading", "Add Driver");
		model.put("success", "Driver Added Successfully");
		model.put("nationality", prepareNationalityList(schoolservice.getAllNationlity()));
		return new ModelAndView("school/add_driver", model);

	}

	/**
	 * Function for manage Driver
	 **/
	@RequestMapping(value = "school/manageDrivers", method = RequestMethod.GET)
	public ModelAndView manageStaff(
			@ModelAttribute("command") DriverBean driverbean,
			BindingResult result, ModelMap modelH, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		modelH.addAttribute("heading", "Manage Driver");
		Map<String, Object> model = new HashMap<String, Object>();
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("drivers",
				getDriversList(schoolservice.listDriver(school_id)));
		model.put("school_details", schoolservice.getSchoolById(school_id));
		return new ModelAndView("school/all_driver", model);
	}

	/* Method for get Student list */
	private List<DriverBean> getDriversList(List<DriverModel> driver) {

		List<DriverBean> beans = null;
		if (driver != null && !driver.isEmpty()) {
			beans = new ArrayList<DriverBean>();
			DriverBean bean = null;
			for (DriverModel st : driver) {
				bean = new DriverBean();
				bean.setAddress(st.getAddress());
				bean.setContact_number(st.getContact_number());
				bean.setD_email(st.getD_email());
				bean.setDriver_fname(st.getDriver_fname());
				bean.setDriver_id(st.getDriver_id());
				bean.setDriver_lname(st.getDriver_lname());
				bean.setDriver_school_id(st.getDriver_school_id());
				bean.setImage_path(st.getImage_path());
				bean.setPassword(st.getPassword());
				bean.setUsername(st.getUsername());
				bean.setBlood_group(st.getBlood_group());
				bean.setNationality(st.getNationality());
				bean.setDob(st.getDob());
				bean.setStatus(1);
				if(st.getRoute_id()!=null && st.getRoute_id()!=0)
				{
					RouteModel route_details=schoolservice.getRouteById(st.getRoute_id());
					if(route_details!=null)
					{
						bean.setRoute_name(route_details.getRoute_name());
					}else
					{
						bean.setRoute_name("N/A");
					}
				}else
				{
					bean.setRoute_name("N/A");
				}
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/*
	 * Function for delete
	 */
	@RequestMapping(value = "school/deleteDriver", method = RequestMethod.GET)
	public ModelAndView deleteDriver(
			@ModelAttribute("command") DriverBean driverbean,
			BindingResult result) {

		schoolservice.deleteDriver(driverbean.getDriver_id());
		return new ModelAndView("redirect:manageDrivers");
	}

	/**
	 * Function for edit student
	 **/
	@RequestMapping(value = "school/editDriver", method = RequestMethod.GET)
	public ModelAndView editVechile(
			@ModelAttribute("command") DriverBean driverbean,
			BindingResult result, HttpServletRequest request) {
		try{
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int driver_id=Integer.parseInt(new String(decodedBytes));
		DriverModel drivermodel = schoolservice.getDriverById(driver_id);
		Map<String, Object> model = new HashMap<String, Object>();
		if (drivermodel != null) {
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			int school_id = (Integer) session.getAttribute("schoolId");
			model.put("routes", getAllRoute(schoolservice.listRoute(school_id)));
			model.put("school_details", schoolservice.getSchoolById(school_id));
			model.put("heading", "Edit Driver");
			model.put("driver", drivermodel);
			model.put("nationality", prepareNationalityList(schoolservice.getAllNationlity()));
			SchoolModel schol= schoolservice.getSchoolById(school_id);
		    model.put("country_details", schoolservice.getCountryById(schol.getCountry()));
		    model.put("documents", prepareListOfDriverDoc(schoolservice.getDriverDocument(driver_id)));
		    
		    
		    
			return new ModelAndView("school/edit_driver", model);
		} else {
			return new ModelAndView("redirect:manageDrivers");
		}
		}catch(Exception e)
		{
			return new ModelAndView("redirect:manageDrivers");
		}

	}

	/**
	 * Function for edit parent
	 **/
	@RequestMapping(value = "school/editDriver", method = RequestMethod.POST)
	public ModelAndView editDriver(
			@ModelAttribute("command") DriverBean driverbean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		try{
			String q=request.getParameter("q");
			byte[] decodedBytes = Base64.decodeBase64(""+q+"");
			int driver_id=Integer.parseInt(new String(decodedBytes));
		
		try {
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			String imageName = "";
			if (driverbean.getImage_path() != "" && !driverbean.getImage_path().equals("")) {
				System.out.println(driverbean.getImage_path());

				String filePath = driverbean.getImage_path();
				String[] parts = filePath.split("base64,");
				String part2 = parts[1]; // 034556
				int rand = RandomUtils.nextInt();
				imageName = driverbean.getContact_number() + rand + ".png";
				String directory = request.getServletContext().getRealPath(
						Assets.DRIVER_UPLOAD_PATH + imageName);
				byte[] imageByteArray = Base64.decodeBase64(part2);
				System.out.println(directory);
				FileOutputStream imageOutFile = new FileOutputStream(directory);
				imageOutFile.write(imageByteArray);
				imageOutFile.close();
			} else {
				imageName = "";

				System.out.println(driverbean.getImage_path());
			}
			int school_id = (Integer) session.getAttribute("schoolId");
			model.put("school_details", schoolservice.getSchoolById(school_id));
			DriverModel driver = new DriverModel();
			// driver.setAddress();
			driver.setDriver_fname(driverbean.getDriver_fname());
			driver.setDriver_lname(driverbean.getDriver_lname());
			// driver.setContact_number(driverbean.getContact_number());
			driver.setD_email(driverbean.getD_email());
			driver.setImage_path(imageName);
			driver.setDriver_school_id(driverbean.getDriver_school_id());
			driver.setMiddle_name(driverbean.getMiddle_name());
			driver.setDob(driverbean.getDob());
			driver.setNationality(driverbean.getNationality());
			//driver.setLicence_expiry(driverbean.getLicence_expiry());
			if(driverbean.getLicence_expiry()!="" && !driverbean.getLicence_expiry().equals(""))
			{
				driver.setLicence_expiry(driverbean.getLicence_expiry());
			}
			else
			{
				driver.setLicence_expiry("0000-00-00");
			}
			driver.setBlood_group(driverbean.getBlood_group());
			// driver.setRoute_id(driverbean.getRoute_id());

			if (driverbean.getRoute_id() != 0) {
				driver.setRoute_id(driverbean.getRoute_id());
			} else {
				driver.setRoute_id(0);
			}
			schoolservice.editDriverById(driver_id, driver);
			List<String> insurance_document_name=driverbean.getInsurance_document_name();
			List<String> insurance_document_expiry=driverbean.getInsurance_document_expiry();
			List<String> insurance_remind=driverbean.getRemind_day();
			List<String> insurance_end_date=driverbean.getInsurance_end_date();
			String in_end_date="";
			if(insurance_end_date!=null)
			{
			for(String a: insurance_end_date)
			{
				in_end_date +=a+",";
			}
			}
			String in_doc_name="";
			if(insurance_document_name!=null)
			{
			for(String a : insurance_document_name)
			{
				 
				in_doc_name +=a+",";
				 
			 
			}
			}
			
			String in_doc_date="";
			if(insurance_document_expiry!=null)
			{
			for(String a : insurance_document_expiry)
			{
				in_doc_date +=a+",";
			 
			}
			}
			
			
			String in_doc_remind="";
			if(insurance_remind!=null)
			{
			for(String a : insurance_remind)
			{
				in_doc_remind +=a+",";
			 
			}
			}
			List<MultipartFile> files = driverbean.getInsurance_card_copy();
			List<String> fileNames = new ArrayList<String>();
			MultipartFile abc = null;
			String new_file_name = "";

			if (null != files && files.size() > 0) {
				for (MultipartFile multipartFile : files) {
					 System.out.println("File name="+multipartFile.getOriginalFilename());

					
					if (multipartFile.getOriginalFilename() != null && multipartFile.getOriginalFilename() != "") {
						String fileName = multipartFile
								.getOriginalFilename();

						if (!fileName.isEmpty()) {
							byte[] bytes = fileName.getBytes();
							String[] fn = fileName.toString().split("\\.");
							int rand = RandomUtils.nextInt();
							String directory = request
									.getServletContext()
									.getRealPath(
											Assets.INSURANCE_CARD_COPY+rand + fn[0] + "." + fn[1]);
							new_file_name += rand + fn[0] + "." + fn[1]
									+ ",";
							System.out.println(directory);
							BufferedOutputStream stream = new BufferedOutputStream(
									new FileOutputStream(directory));
							stream.write(multipartFile.getBytes());
							stream.close();
						} else {
							driver.setInsurance_card_copy("");
							new_file_name +=",";
						}
					}else
					{
						new_file_name +=",";
					}

				}
			}else
			{
				new_file_name +=",";
			}
			
			
			int j=0;
			List<String> v_doc_id=driverbean.getV_doc_id();
			List<String> items = Arrays.asList(new_file_name.split("\\s*,\\s*"));
			if(insurance_document_name!=null)
			{
			for(String a : insurance_document_name)
			{
				if(!v_doc_id.get(j).equals("0"))
				{
					DriverDocModel doc=new DriverDocModel();
					doc.setInsurance_document_name(a);
					doc.setInsurance_document_expiry(insurance_document_expiry.get(j));
					doc.setInsurance_end_date(insurance_end_date.get(j));
					doc.setRemind_day(insurance_remind.get(j));
					/*if(!items.get(j).equals(""))
					{*/
					if(items.get(j).length()>0)
					{
						doc.setInsurance_card_copy(items.get(j));
					}
					else
					{
						doc.setInsurance_card_copy("");
					}
					
					vechileservice.editDriverDocById(Integer.parseInt(v_doc_id.get(j)), doc);
					System.out.println("E#");
				}else
				{
					DriverDocModel doc=new DriverDocModel();
					doc.setInsurance_document_name(a);
					doc.setInsurance_document_expiry(insurance_document_expiry.get(j));
					doc.setInsurance_end_date(insurance_end_date.get(j));
					doc.setRemind_day(insurance_remind.get(j));
					doc.setV_id((int) driver_id);
					doc.setSchool_id((Integer) session.getAttribute("schoolId"));
					doc.setInsurance_card_copy(items.get(j));
					doc.setStatus(0);
					doc.setNoti_type(1);
					if(insurance_document_expiry.get(j)!="" && !insurance_document_expiry.get(j).equals(""))
					{
						vechileservice.addDriverDoc(doc);
					}
					
				 
					
				}
				j++;
			}	
		}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("redirect:manageDrivers");
		}
		try{
			DriverModel drivermodel = schoolservice.getDriverById(driver_id);
			
			if (drivermodel != null) {
				HttpSession session = request.getSession();
				if (session.getAttribute("userRole")!= "Manager")
				{
				 return new ModelAndView("redirect:/login.html");
				}
				int school_id = (Integer) session.getAttribute("schoolId");
				model.put("routes", getAllRoute(schoolservice.listRoute(school_id)));
				model.put("school_details", schoolservice.getSchoolById(school_id));
				model.put("heading", "Edit Driver");
				model.put("driver", drivermodel);
				model.put("nationality", prepareNationalityList(schoolservice.getAllNationlity()));
				SchoolModel schol= schoolservice.getSchoolById(school_id);
			    model.put("country_details", schoolservice.getCountryById(schol.getCountry()));
			    model.put("documents", prepareListOfDriverDoc(schoolservice.getDriverDocument(driver_id)));
			    
			    
			    model.put("success", "Driver updated successfully");
				return new ModelAndView("school/edit_driver", model);
			} else {
				return new ModelAndView("redirect:manageDrivers");
			}
			}catch(Exception e)
			{
				return new ModelAndView("redirect:manageDrivers");
			}
		
		}
		catch(Exception e)
		{
			return new ModelAndView("redirect:manageDrivers");
		}
		
	}

	/**
	 * Function for add route
	 **/
	/*
	 * @RequestMapping(value = "school/addRoute", method = RequestMethod.GET)
	 * public ModelAndView addRoute(@ModelAttribute("command") DriverBean
	 * driverbean, BindingResult result) {
	 * 
	 * System.out.println("here"); Map<String, Object> model = new
	 * HashMap<String, Object>(); model.put("heading","Bus Route"); return new
	 * ModelAndView("school/add_route",model); }
	 */
	@RequestMapping(value = "school/addRoute", method = RequestMethod.GET)
	public ModelAndView addRoute(
			@ModelAttribute("command") DriverBean driverbean,
			BindingResult result, HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("heading", "Bus Route");
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		model.put("students",
				getStudentList(schoolservice.listStudent(school_id)));
		return new ModelAndView("school/add_route_details", model);
	}

	/**
	 * Function for add new route
	 **/
	@RequestMapping(value = "school/addRoute", method = RequestMethod.POST)
	public ModelAndView addRoute(@ModelAttribute("command") RouteBean route,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		try {
			String url = Assets.URL;
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			int school_id = (Integer) session.getAttribute("schoolId");
			model.put("school_details", schoolservice.getSchoolById(school_id));
			RouteModel routemodel = new RouteModel();
			GoogleResponse res = convertToLatLong(route.getSource());
			if (res.getStatus().equals("OK")) {
				for (Result result1 : res.getResults()) {
					System.out.println("Location is "
							+ result1.getGeometry().getLocation_type());
					routemodel.setSource_lat(result1.getGeometry()
							.getLocation().getLat());
					routemodel.setSource_lng(result1.getGeometry()
							.getLocation().getLng());
					model.addAttribute("source_lat", result1.getGeometry()
							.getLocation().getLat());
					model.addAttribute("source_lng", result1.getGeometry()
							.getLocation().getLng());
				}
			}
			GoogleResponse res1 = convertToLatLong(route.getDestination());
			if (res.getStatus().equals("OK")) {
				for (Result result2 : res1.getResults()) {
					System.out.println("Location is "
							+ result2.getGeometry().getLocation_type());
					routemodel.setDestination_lat(result2.getGeometry()
							.getLocation().getLat());
					routemodel.setDestination_lng(result2.getGeometry()
							.getLocation().getLng());
					model.addAttribute("destination_lat", result2.getGeometry()
							.getLocation().getLat());
					model.addAttribute("destination_lng", result2.getGeometry()
							.getLocation().getLng());
				}
			}

			routemodel.setSource(route.getSource());
			routemodel.setDestination(route.getDestination());
			routemodel.setRoute_id(null);
			routemodel.setRoute_name(route.getRoute_name());
			routemodel.setSchool_id(school_id);
			routemodel.setNote(route.getNote());
			routemodel.setRadius("");
			routemodel.setSource_note(route.getSource_note());
			routemodel.setDestination_note(route.getDestination_note());
			long route_id = schoolservice.addRoute(routemodel);
			model.addAttribute("source", route.getSource());
			model.addAttribute("destination", route.getDestination());
			model.addAttribute("route_id", route_id);
			model.addAttribute("success", "success");

		} catch (Exception e) {
			e.printStackTrace();
		}
		// return new ModelAndView("school/add_route");
		return new ModelAndView("school/add_route_details",model);
		//return new ModelAndView("redirect:allRoute");
	}

	public GoogleResponse convertToLatLong(String fullAddress)
			throws IOException {

		/*
		 * Create an java.net.URL object by passing the request URL in
		 * constructor. Here you can see I am converting the fullAddress String
		 * in UTF-8 format. You will get Exception if you don't convert your
		 * address in UTF-8 format. Perhaps google loves UTF-8 format. :) In
		 * parameter we also need to pass "sensor" parameter. sensor (required
		 * parameter) � Indicates whether or not the geocoding request comes
		 * from a device with a location sensor. This value must be either true
		 * or false.
		 */

		URL url = new URL(Assets.URL + "?address="
				+ URLEncoder.encode(fullAddress, "UTF-8") + "&sensor=false");
		// Open the Connection
		URLConnection conn = url.openConnection();

		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		GoogleResponse response = (GoogleResponse) mapper.readValue(in,
				GoogleResponse.class);
		in.close();
		return response;

	}

	public GoogleResponse convertFromLatLong(String latlongString)
			throws IOException {

		/*
		 * Create an java.net.URL object by passing the request URL in
		 * constructor. Here you can see I am converting the fullAddress String
		 * in UTF-8 format. You will get Exception if you don't convert your
		 * address in UTF-8 format. Perhaps google loves UTF-8 format. :) In
		 * parameter we also need to pass "sensor" parameter. sensor (required
		 * parameter) � Indicates whether or not the geocoding request comes
		 * from a device with a location sensor. This value must be either true
		 * or false.
		 */
		URL url = new URL(Assets.URL + "?latlng="
				+ URLEncoder.encode(latlongString, "UTF-8") + "&sensor=false");
		// Open the Connection
		URLConnection conn = url.openConnection();

		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		GoogleResponse response = (GoogleResponse) mapper.readValue(in,
				GoogleResponse.class);
		in.close();
		return response;

	}

	/**
	 * Function for add lat lng for route
	 **/
	@RequestMapping(value = "school/addLatLng", method = RequestMethod.POST)
	public ModelAndView addLatLng(@ModelAttribute("command") RouteLatLng route,
			BindingResult result, ModelMap modelH, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			int school_id = (Integer) session.getAttribute("schoolId");
			RouteLatLngModel routemodel = new RouteLatLngModel();
			String lat = route.getLat();
			String[] latArr = lat.split(",", -1);
			String lng = route.getLng();
			String[] lngArr = lng.split(",", -1);
			for (int i = 0; i < latArr.length; i++) {
				routemodel.setId(null);
				routemodel.setSchool_id(school_id);
				routemodel.setRoute_id(route.getRoute_id());
				routemodel.setLat(latArr[i]);
				routemodel.setLng(lngArr[i]);
				schoolservice.addRouteLatLng(routemodel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("heading", "Bus Route");
		return new ModelAndView("school/add_route_details", model);
	}

	@RequestMapping(value = "school/allRoute", method = RequestMethod.GET)
	public ModelAndView allRoute(@ModelAttribute("command") RouteBean route,
			BindingResult result, HttpServletRequest request, ModelMap modelH) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		modelH.addAttribute("heading", "Manage Route");
		Map<String, Object> model = new HashMap<String, Object>();
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("staffs", getAllRoute(schoolservice.listRoute(school_id)));
		model.put("school_details", schoolservice.getSchoolById(school_id));
		return new ModelAndView("school/all_routes", model);
	}

	/**
	 * Function for edit Route
	 **/
	@RequestMapping(value = "school/editRoute", method = RequestMethod.GET)
	public ModelAndView editRoute(@ModelAttribute("command") RouteBean route,
			BindingResult result, HttpServletRequest request) {
		try{
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int route_id=Integer.parseInt(new String(decodedBytes));	
		RouteModel routemodel = schoolservice.getRouteById(route_id);
		Map<String, Object> model = new HashMap<String, Object>();
		if (routemodel != null) {
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			model.put("latlong",
					getAllLatLng(schoolservice.listLatLng(route_id)));
			model.put("heading", "Edit Route");
			model.put("route", routemodel);
			int school_id = (Integer) session.getAttribute("schoolId");
			model.put("school_details", schoolservice.getSchoolById(school_id));
			return new ModelAndView("school/edit_route", model);
		} else {
			return new ModelAndView("redirect:allRoute");
		}
		}catch(Exception e)
		{
			return new ModelAndView("redirect:allRoute");
		}

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
				if(studentModel!=null)
				{
					bean.setS_image(studentModel.getS_image_path());
					bean.setS_class(studentModel.getStudent_class());
					bean.setAddress(studentModel.getS_address());
					bean.setId(st.getId());
					bean.setLat(st.getLat());
					bean.setLng(st.getLng());
					bean.setRoute_id(st.getRoute_id());
					bean.setStudent_name(studentModel.getS_fname()+" "+studentModel.getFather_name()+" "+studentModel.getGrand_name()+" "+studentModel.getFamily_name());
					bean.setBlink_s(studentModel.getBlink_status());
					bean.setStudent_id(studentModel.getStudent_id());
				}else
				{

					bean.setS_image("");
					bean.setS_class("");
					bean.setAddress("");
					bean.setId(null);
					bean.setLat("");
					bean.setLng("");
					bean.setRoute_id(null);
					bean.setStudent_name("");
					bean.setBlink_s(0);
					bean.setStudent_id(0);
				}
				
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Function for edit Route
	 **/
	@RequestMapping(value = "school/editRoute", method = RequestMethod.POST)
	public ModelAndView editRoutepost(
			@ModelAttribute("command") RouteBean route, RouteLatLng latlng,
			BindingResult result, HttpServletRequest request, ModelMap model) {
		try{
			String q=request.getParameter("q");
			byte[] decodedBytes = Base64.decodeBase64(""+q+"");
			int route_id=Integer.parseInt(new String(decodedBytes));	
		
		try {
			
			String url = Assets.URL;
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			int school_id = (Integer) session.getAttribute("schoolId");
			RouteModel routemodel = new RouteModel();
			GoogleResponse res = convertToLatLong(route.getSource());
			if (res.getStatus().equals("OK")) {
				for (Result result1 : res.getResults()) {
					System.out.println("Location is "
							+ result1.getGeometry().getLocation_type());
					routemodel.setSource_lat(result1.getGeometry()
							.getLocation().getLat());
					routemodel.setSource_lng(result1.getGeometry()
							.getLocation().getLng());
					model.addAttribute("source_lat", result1.getGeometry()
							.getLocation().getLat());
					model.addAttribute("source_lng", result1.getGeometry()
							.getLocation().getLng());
				}
			}
			GoogleResponse res1 = convertToLatLong(route.getDestination());
			if (res.getStatus().equals("OK")) {
				for (Result result2 : res1.getResults()) {
					System.out.println("Location is "
							+ result2.getGeometry().getLocation_type());
					routemodel.setDestination_lat(result2.getGeometry()
							.getLocation().getLat());
					routemodel.setDestination_lng(result2.getGeometry()
							.getLocation().getLng());
					model.addAttribute("destination_lat", result2.getGeometry()
							.getLocation().getLat());
					model.addAttribute("destination_lng", result2.getGeometry()
							.getLocation().getLng());
				}
			}

			routemodel.setSource(route.getSource());
			routemodel.setDestination(route.getDestination());
			routemodel.setRoute_name(route.getRoute_name());
			routemodel.setSchool_id(school_id);
			routemodel.setNote(route.getNote());
			routemodel.setSource_note(route.getSource_note());
			routemodel.setDestination_note(route.getDestination_note());
			schoolservice.editRouteById(route_id, routemodel);
			
			model.addAttribute("source", route.getSource());
			model.addAttribute("destination", route.getDestination());
			model.addAttribute("route_id", route.getRoute_id());

			RouteModel routemodel1 = schoolservice.getRouteById(route_id);

			model.put("latlong",
					getAllLatLng(schoolservice.listLatLng(route_id)));
			model.put("heading", "Edit Route");
			model.put("route", routemodel1);
			model.put("success", "success");

			/*
			 * schoolservice.deleteLatLng(route.getRoute_id());
			 * 
			 * RouteLatLngModel routeLatLngmodel=new RouteLatLngModel(); String
			 * lat=latlng.getLat(); String[] latArr=lat.split(",", -1); String
			 * lng=latlng.getLng(); String[] lngArr=lng.split(",", -1); for (int
			 * i = 0; i < latArr.length; i++) { routeLatLngmodel.setId(null);
			 * routemodel.setSchool_id(school_id);
			 * routemodel.setRoute_id(route.getRoute_id());
			 * routeLatLngmodel.setLat(latArr[i]);
			 * routeLatLngmodel.setLng(lngArr[i]);
			 * schoolservice.addRouteLatLng(routeLatLngmodel); }
			 */

			model.put("school_details", schoolservice.getSchoolById(school_id));
			ModelMap model_success=new ModelMap();
		} catch (Exception e) {

			System.out.println(e);
		}

		//return new ModelAndView("redirect:allRoute");
		RouteModel routemodel = schoolservice.getRouteById(route_id);
		if (routemodel != null) {
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			model.put("latlong",
					getAllLatLng(schoolservice.listLatLng(route_id)));
			model.put("heading", "Edit Route");
			model.put("route", routemodel);
			int school_id = (Integer) session.getAttribute("schoolId");
			model.put("school_details", schoolservice.getSchoolById(school_id));
			return new ModelAndView("school/edit_route", model);
		} else {
			return new ModelAndView("redirect:allRoute");
		}
		} catch (Exception e) {

			return new ModelAndView("redirect:allRoute");
		}

	}

	/**
	 * Function for edit Route
	 **/
	@RequestMapping(value = "school/editlatlng", method = RequestMethod.POST)
	public ModelAndView editlatlng(@ModelAttribute("command") RouteBean route,
			RouteLatLng latlng, BindingResult result,
			HttpServletRequest request, ModelMap model) {

		try {
			String url = Assets.URL;
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			int school_id = (Integer) session.getAttribute("schoolId");
			RouteModel routemodel = new RouteModel();
			System.out.println(route.getRoute_id());
			schoolservice.deleteLatLng(route.getRoute_id());

			RouteLatLngModel routeLatLngmodel = new RouteLatLngModel();
			String lat = latlng.getLat();
			String[] latArr = lat.split(",", -1);
			String lng = latlng.getLng();
			String[] lngArr = lng.split(",", -1);
			for (int i = 0; i < latArr.length; i++) {
				routeLatLngmodel.setId(null);
				routeLatLngmodel.setSchool_id(school_id);
				routeLatLngmodel.setRoute_id(route.getRoute_id());
				routeLatLngmodel.setLat(latArr[i]);
				routeLatLngmodel.setLng(lngArr[i]);
				schoolservice.addRouteLatLng(routeLatLngmodel);
			}

		} catch (Exception e) {

			System.out.println(e);
		}
		return new ModelAndView("redirect:allRoute");

	}

	@RequestMapping(value = "school/studentRoute", method = RequestMethod.GET)
	public ModelAndView studentRoute(
			@ModelAttribute("command") RouteBean route, BindingResult result,
			HttpServletRequest request, ModelMap modelH) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		modelH.addAttribute("heading", "Assign Route");
		Map<String, Object> model = new HashMap<String, Object>();
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("routes", getAllRoute(schoolservice.listRoute(school_id)));
		model.put("students",
				getStudentList(schoolservice.listStudent(school_id)));
		return new ModelAndView("school/assign_student_route", model);
	}

	@RequestMapping(value = "school/studentRoute", method = RequestMethod.POST)
	public ModelAndView studentRoutePost(
			@ModelAttribute("command") RouteLatLng route, BindingResult result,
			HttpServletRequest request, ModelMap modelH) {

		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		System.out.println("here");
		modelH.addAttribute("heading", "Assign Route");
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			int school_id = (Integer) session.getAttribute("schoolId");
			RouteLatLngModel routelatlng = schoolservice.checkStudentRouteId(
					route.getStudent_id(), school_id);
			if (routelatlng == null) {
				RouteLatLngModel routeNew = new RouteLatLngModel();
				routeNew.setAddress(route.getAddress());
				GoogleResponse res = convertToLatLong(route.getAddress());
				if (res.getStatus().equals("OK")) {
					for (Result result1 : res.getResults()) {
						routeNew.setLat(result1.getGeometry().getLocation()
								.getLat());
						routeNew.setLng(result1.getGeometry().getLocation()
								.getLng());
					}
				}
				routeNew.setSchool_id(school_id);
				routeNew.setStudent_id(route.getStudent_id());
				routeNew.setRoute_id(route.getRoute_id());
				routeNew.setId(null);
				routeNew.setParent_id(route.getParent_id());
				routeNew.setStudent_name(route.getStudent_name());
				schoolservice.addAssignRoute(routeNew);
				model.put("success", "Route Assigned successfully");

			} else {

				model.put("error", "You already assigned this student");
			}
			model.put("routes", getAllRoute(schoolservice.listRoute(school_id)));
			model.put("students",
					getStudentList(schoolservice.listStudent(school_id)));

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

		return new ModelAndView("school/assign_student_route", model);
	}

	/**
	 * Function for View Route
	 **/
	@RequestMapping(value = "school/viewRoute", method = RequestMethod.GET)
	public ModelAndView viewRoute(@ModelAttribute("command") RouteBean route,
			BindingResult result, HttpServletRequest request) {
		
		try{
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int route_id=Integer.parseInt(new String(decodedBytes));	
		RouteModel routemodel = schoolservice.getRouteById(route_id);
		Map<String, Object> model = new HashMap<String, Object>();
		if (routemodel != null) {
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			int school_id = (Integer) session.getAttribute("schoolId");
			model.put("school_details", schoolservice.getSchoolById(school_id));
			model.put("latlong",
					getAllLatLng(schoolservice.listLatLng(route_id)));
			model.put("heading", "View Route");
			model.put("route", routemodel);
			model.put("all_students", getStudentList(schoolservice.getStudentsByRouteId(route_id)));
			return new ModelAndView("school/view_route_map", model);
		} else {
			return new ModelAndView("redirect:allRoute");
		}
		}catch(Exception e)
		{
			return new ModelAndView("redirect:allRoute");
		}

	}

	/**
	 * Function for edit Student route
	 **/
	@RequestMapping(value = "school/editStudentRoute", method = RequestMethod.GET)
	public ModelAndView editStudentRoute(
			@ModelAttribute("command") RouteLatLng routelatlng,
			BindingResult result, HttpServletRequest request, ModelMap modelH) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		modelH.addAttribute("heading", "Assign Route");
		Map<String, Object> model = new HashMap<String, Object>();
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("routes", getAllRoute(schoolservice.listRoute(school_id)));
		return new ModelAndView("school/edit_assign_student_route", model);
	}

	/**
	 * Function for Update Student Route
	 * 
	 * @throws IOException
	 **/
	@RequestMapping(value = "school/editStudentRoute", method = RequestMethod.POST)
	public ModelAndView editStudentRoute(
			@ModelAttribute("command") RouteLatLng route, BindingResult result,
			ModelMap modelH, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		RouteLatLngModel latlngmodel = new RouteLatLngModel();
		latlngmodel.setAddress(route.getAddress());
		GoogleResponse res = convertToLatLong(route.getAddress());
		if (res.getStatus().equals("OK")) {
			for (Result result1 : res.getResults()) {
				latlngmodel
						.setLat(result1.getGeometry().getLocation().getLat());
				latlngmodel
						.setLng(result1.getGeometry().getLocation().getLng());
			}
		}

		schoolservice.editStudentRoute(route.getStudent_id(), latlngmodel);
		modelH.addAttribute("heading", "Assign Route");
		Map<String, Object> model = new HashMap<String, Object>();
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("routes", getAllRoute(schoolservice.listRoute(school_id)));
		model.put("success", "Student Route Updated successfully");

		model.put("heading", "Edit Student Route");
		return new ModelAndView("school/edit_assign_student_route", model);
	}

	@RequestMapping(value = "school/deleteRoute", method = RequestMethod.GET)
	public ModelAndView deleteRoute(@ModelAttribute("command") RouteBean route,
			BindingResult result, ModelMap modelH) {
		schoolservice.deleteRoute(route.getRoute_id());
		return new ModelAndView("redirect:allRoute");
	}

	@RequestMapping(value = "school/AddNewParent", method = RequestMethod.GET)
	public ModelAndView AddNewParent(
			@ModelAttribute("command") LoginBean loginbean,
			BindingResult result, HttpServletRequest request, ModelMap model) {
		try{
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int p_status_new=Integer.parseInt(new String(decodedBytes));
		int p_status = p_status_new;
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		model.addAttribute("p_status", p_status);
		int school_id = (Integer) session.getAttribute("schoolId");
		SchoolModel schol= schoolservice.getSchoolById(school_id);
		model.put("country_details", schoolservice.getCountryById(schol.getCountry()));
		model.put("school_details", schoolservice.getSchoolById(school_id));
		model.addAttribute("heading", "Add Parent");
		return new ModelAndView("school/add_new_parent");
		}catch(Exception e)
		{
			return new ModelAndView("redirect:manageParents");
		}
	}

	@RequestMapping(value = "school/AddNewParent", method = RequestMethod.POST)
	public ModelAndView AddNewParent(
			@ModelAttribute("command") LoginBean parent, BindingResult result,
			ModelMap model, HttpServletRequest request) {
		try {
			String q=request.getParameter("q");
			byte[] decodedBytes = Base64.decodeBase64(""+q+"");
			int p_status_new=Integer.parseInt(new String(decodedBytes));
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			RandomNumber random = new RandomNumber(9999);
			String f_name = parent.getFirst_name().substring(0, 1);
			String password = random.nextSessionId();
			String username = f_name + "." + parent.getMiddle_name();
			String username1 = f_name + "." + parent.getMiddle_name();
			username = username.toLowerCase();
			username1 = username1.toLowerCase();
			username = username.replaceAll(" ", "_").toLowerCase();
			username1 = username1.replaceAll(" ", "_").toLowerCase();	
			int y = 1;
			int x = 1;
			while (y < 2) {
				LoginModel check = loginservice.checkUsername(username);
				if (check == null) {
					username = username;
					break;
				} else {
					y = 1;
					username = username1 + x;
				}
				x++;
			}

			// LoginModel
			// check=loginservice.checkUsername(parent.getUser_name());
			/*
			 * if(check==null) {
			 */
			// LoginModel found = loginservice.login(parent.getUser_email(),"");
			LoginModel found = loginservice.checkMobile(parent
					.getContact_number());
			if (found == null) {
				LoginModel parentModel = new LoginModel();
				parentModel.setUser_email(parent.getUser_email());
				parentModel.setUser_name(username);
				parentModel.setUser_pass(password);
				parentModel.setSchool_id(parent.getSchool_id());
				parentModel.setContact_number(parent.getContact_number());
				parentModel.setUser_role(3);
				parentModel.setP_status(p_status_new);
				parentModel.setFirst_name(parent.getFirst_name());
				parentModel.setLast_name("");
				parentModel.setFamily_name(parent.getFamily_name());
				parentModel.setMiddle_name(parent.getMiddle_name());
				parentModel.setMobile_number(parent.getMobile_number());
				parentModel.setDevice_token("");
				/*parentModel.setChat_on(0);
				parentModel.setLang(0);
				parentModel.setNoti_on(0);
				parentModel.setChecked_in_on(0);
				parentModel.setChecked_out_on(0);
				parentModel.setSpeed_on(0);
				parentModel.setMax_speed(40);
				parentModel.setWrong_route_on(0);*/
				parentModel.setChat_on(0);
				parentModel.setLang(0);
				parentModel.setNoti_on(0);
				parentModel.setChecked_in_on(0);
				parentModel.setChecked_out_on(0);
				parentModel.setSpeed_on(1);
				parentModel.setMax_speed(100);
				parentModel.setWrong_route_on(1);
				parentModel.setSms_checked_in_on(1);
				parentModel.setSms_checked_out_on(1);
				parentModel.setSms_speed_on(1);
				parentModel.setSms_max_speed(100);
				parentModel.setSms_wrong_route_on(1);
				parentModel.setInstant_message(0);
				parentModel.setMorning_before(0);
				parentModel.setEvening_before(0);
				parentModel.setSms_instant_message(1);
				parentModel.setSms_morning_before(1);
				parentModel.setSms_evening_before(1);
				parentModel.setDevice_id("");
				parentModel.setSound_setting(0);
				parentModel.setChat_sound(0);
				schoolservice.addParent(parentModel);
				Sms_api sms = new Sms_api();
				/*sms.sendhttp("Your login details are username=" + username
						+ " and password=" + password, 0,
						parent.getContact_number());*/
				model.addAttribute("success", "Parent Added Successfully");
				model.addAttribute("username", username);
				model.addAttribute("password", password);
				model.addAttribute("heading", "Add Parent");
			} else {
				model.addAttribute("heading", "Add Parent");
				model.addAttribute("parentBean", parent);
				model.addAttribute("error", "This parent already exist");
			}

			/*
			 * RandomNumber random=new RandomNumber(9999); String f_name =
			 * parent.getFirst_name().substring(0, 3); String l_name=
			 * parent.getLast_name().substring(0, 3); String con_number=
			 * parent.getContact_number().substring(0, 3);
			 * 
			 * String username=f_name+random.generateNewRandom(50)+l_name;
			 * LoginModel
			 * check=loginservice.checkUsername(parent.getUser_name());
			 * if(check==null) {
			 * 
			 * LoginModel found = loginservice.login(parent.getUser_email(),"");
			 * if(found==null) {
			 * 
			 * LoginModel parentModel = new LoginModel();
			 * 
			 * 
			 * 
			 * System.out.println(username);
			 * parentModel.setP_status(parent.getP_status());
			 * parentModel.setFirst_name(parent.getFirst_name());
			 * parentModel.setLast_name(parent.getLast_name());
			 * parentModel.setUser_email(parent.getUser_email());
			 * parentModel.setUser_name(username);
			 * parentModel.setUser_pass(random.nextSessionId());
			 * parentModel.setSchool_id(parent.getSchool_id());
			 * parentModel.setContact_number(parent.getContact_number());
			 * parentModel.setUser_role(3);
			 * schoolservice.addParent(parentModel);
			 * model.addAttribute("success", "Parent Added Successfully");
			 * model.addAttribute("heading", "Add Parent"); }else {
			 * model.addAttribute("heading", "Add Parent");
			 * model.addAttribute("parentBean", parent);
			 * model.addAttribute("error", "This parent already exist"); } }else
			 * { model.addAttribute("heading", "Add Parent");
			 * model.addAttribute("parentBean", parent);
			 * model.addAttribute("error", "This user already exist"); }
			 */
			int school_id = (Integer) session.getAttribute("schoolId");
			model.put("school_details", schoolservice.getSchoolById(school_id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("school/add_parent",model);

	}

	/**
	 * Function for view Student
	 **/
	@RequestMapping(value = "school/viewAttendance", method = RequestMethod.GET)
	public ModelAndView viewAttendance(
			@ModelAttribute("command") StudentBean students,
			BindingResult result, HttpServletRequest request) {
		try{
			
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int student_id=Integer.parseInt(new String(decodedBytes));
		StudentModel studentModel = studentservice.getStudentById(student_id);
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");
		Map<String, Object> model = new HashMap<String, Object>();

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
				//model.put("all_holiday",getHolidayList(schoolservice.getAllHoliday(school_id)));
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
			}

			model.put("school_details", schoolservice.getSchoolById(school_id));
			return new ModelAndView("school/view_student_attendance", model);
		} else {
			return new ModelAndView("redirect:manageParents");
		}
		}catch(Exception e)
		{
			return new ModelAndView("redirect:manageParents");
		}

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
				bean.setHoliday_name(holi.getHoliday_name());
				bean.setHoliday_date(holi.getHoliday_date());
				bean.setHoliday_enddate(holi.getHoliday_enddate());
				bean.setSchool_id(holi.getSchool_id());
				bean.setHoliday_start_date(holi.getHoliday_start_date());
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
	/* Method for get country list */
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

	@RequestMapping(value = "school/addHoliday", method = RequestMethod.GET)
	public ModelAndView addHoliday(
			@ModelAttribute("command") HolidayBean holidaybean,
			BindingResult result, HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		model.addAttribute("heading", "Add Holiday");
		model.put("school_details", schoolservice
				.getSchoolById((Integer) session.getAttribute("schoolId")));
		return new ModelAndView("school/add_holiday");
	}

	@RequestMapping(value = "school/addHoliday", method = RequestMethod.POST)
	public ModelAndView addHoliday(
			@ModelAttribute("command") HolidayBean holidaybean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			
			
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-mm-dd");
			String inputString1 = holidaybean.getHoliday_date();
			String inputString2 =holidaybean.getHoliday_enddate();
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
				Date date = formatter.parse(holidaybean.getHoliday_date());
				cal.setTime(formatter.parse(holidaybean.getHoliday_date()));

				cal.add(Calendar.DATE, i);
				Date newDate = cal.getTime();
				HolidayModel holidaymodel = new HolidayModel();
				holidaymodel.setH_id(null);
				holidaymodel.setHoliday_date(formatter.format(newDate));
				holidaymodel.setHoliday_name(holidaybean.getHoliday_name());
				if(!holidaybean.getHoliday_enddate().equals(""))
				{
					holidaymodel.setHoliday_enddate(holidaybean.getHoliday_enddate());
				}else
				{
					holidaymodel.setHoliday_enddate(holidaybean.getHoliday_date());
				}
				
				holidaymodel.setSchool_id(holidaybean.getSchool_id());
				holidaymodel.setHoliday_start_date(holidaybean.getHoliday_date());
				schoolservice.addHoliday(holidaymodel);
			}	
			
			
			
			
			
			
			model.put("school_details", schoolservice
					.getSchoolById((Integer) session.getAttribute("schoolId")));
			model.addAttribute("success", "Holiday Added Successfully");
			model.addAttribute("heading", "Add Holiday");

		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		int school_id = (Integer) session.getAttribute("schoolId");
		model.addAttribute("heading", "View Holiday Calendar");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy");
		DateFormat dateFormat2 = new SimpleDateFormat("MM");

		Date date = new Date();
		int year = Integer.parseInt(dateFormat1.format(date));
		int month = Integer.parseInt(dateFormat2.format(date));
		model.put("school_id", school_id);
		model.put("current_date", dateFormat.format(date));
		model.put("all_holiday",
				getHolidayList(schoolservice.getAllHoliday(school_id)));
		model.put("holidays", getHolidays(frontService.getHolidayByMonth(month,
				year, school_id)));
		model.put("school_details", schoolservice.getSchoolById(school_id));
		return new ModelAndView("school/view_holiday_calendar", model);
	}

	/**
	 * Function for view driver
	 **/
	@RequestMapping(value = "school/viewDriver", method = RequestMethod.GET)
	public ModelAndView viewDriver(
			@ModelAttribute("command") DriverBean driverbean,
			BindingResult result, HttpServletRequest request) {
		try{
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int driver_id=Integer.parseInt(new String(decodedBytes));
		String ed=request.getParameter("v");
		if(ed != null)
		{
			byte[] decodedBytes1 = Base64.decodeBase64(""+ed+"");
			schoolservice.updateHeadNotification(Integer.parseInt(new String(decodedBytes1)));
			
		}
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		DriverModel drivermodel = schoolservice.getDriverById(driver_id);
		RouteModel route = schoolservice.getRouteById(drivermodel.getRoute_id());
		Map<String, Object> model = new HashMap<String, Object>();
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		if (drivermodel != null) {
			model.put("heading", "View Driver");
			model.put("driver", drivermodel);
			model.put("route", route);
			model.put("routes", getAllRoute(schoolservice
					.listRoute((Integer) session.getAttribute("schoolId"))));
			model.put("documents", prepareListOfDriverDoc(schoolservice.getDriverDocument(driver_id)));
			return new ModelAndView("school/view_driver", model);
		}else {
			return new ModelAndView("redirect:manageDrivers");
		}
		}catch(Exception e)
		{
			return new ModelAndView("redirect:manageDrivers");
		}

	}

	/**
	 * Function for get all manager of current school
	 **/
	@RequestMapping(value = "school/manageSchoolsAdmin", method = RequestMethod.GET)
	public ModelAndView manageSchoolsAdmin(
			@ModelAttribute("command") LoginBean parents, BindingResult result,
			ModelMap modelH, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");
		modelH.put("school_details", schoolservice.getSchoolById(school_id));
		modelH.addAttribute("heading", "School Admin");
		Map<String, Object> model = new HashMap<String, Object>();
		/* model.put("parents", getParentList(studentservice.getAllParents())); */
		model.put("parents",
				getParentList(schoolservice.listSchoolAdmin(school_id)));

		return new ModelAndView("school/all_school_admin", model);
	}

	/**
	 * Function for add School Admin
	 **/
	@RequestMapping(value = "school/addSchoolAdmin", method = RequestMethod.GET)
	public ModelAndView addSchoolAdmin(
			@ModelAttribute("command") SchoolBean schoolbean,
			BindingResult result, HttpServletRequest request, ModelMap model) {
		model.addAttribute("heading", "Add School Admin");
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		model.put("school_admin",
				getParentList(schoolservice.listSchoolAdmin(school_id)));
		return new ModelAndView("school/add_school_admin");
	}

	/**
	 * Function for add School Admin Post Data
	 **/
	@RequestMapping(value = "school/addSchoolAdmin", method = RequestMethod.POST)
	public ModelAndView addSchoolAdmin(
			@ModelAttribute("command") LoginBean schooladmin,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		try {
			model.addAttribute("heading", "Add School Admin");
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			int school_id = (Integer) session.getAttribute("schoolId");
			// LoginModel found =
			// loginservice.login(schooladmin.getUser_email(),"");
			LoginModel found = loginservice.checkMobile(schooladmin
					.getContact_number());
			if (found == null) {
				RandomNumber random = new RandomNumber(9999);
				String f_name = schooladmin.getFirst_name().substring(0, 1);
				String password = random.nextSessionId();
				String username = f_name + "_" + schooladmin.getLast_name();
				String username1 = f_name + "_" + schooladmin.getLast_name();
				username = username.toLowerCase();
				username1 = username1.toLowerCase();
				username = username.replaceAll(" ", "_").toLowerCase();
				username1 = username1.replaceAll(" ", "_").toLowerCase();	
				int y = 1;
				int x = 1;
				while (y < 2) {
					LoginModel check = loginservice.checkUsername(username);
					if (check == null) {
						username = username;
						break;
					} else {
						y = 1;
						username = username1 + x;
					}
					x++;
				}
				LoginModel schooladminmodel = new LoginModel();
				schooladminmodel.setUser_email(schooladmin.getUser_email());
				schooladminmodel.setUser_name(username);
				schooladminmodel.setUser_pass(password);
				schooladminmodel.setSchool_id((int) school_id);
				schooladminmodel.setContact_number(schooladmin
						.getContact_number());
				schooladminmodel.setUser_role(2);
				schooladminmodel.setP_status(0);
				schooladminmodel.setFirst_name(schooladmin.getFirst_name());
				schooladminmodel.setLast_name(schooladmin.getLast_name());
				schooladminmodel.setPermission(schooladmin.getPermission());
				schooladminmodel.setMain_school_admin(0);
				schooladminmodel.setDevice_token("");
				schooladminmodel.setDevice_id("");
				schooladminmodel.setMorning_before(0);
				schooladminmodel.setEvening_before(0);
				schooladminmodel.setSms_instant_message(1);
				schooladminmodel.setSms_morning_before(1);
				schooladminmodel.setSms_evening_before(1);
				schooladminmodel.setInstant_message(0);
				schooladminmodel.setSms_wrong_route_on(1);
				schooladminmodel.setSound_setting(0);
				schooladminmodel.setChat_sound(0);
				schoolservice.addParent(schooladminmodel);
				System.out.println("Here");
				Sms_api sms = new Sms_api();
				sms.sendhttp("Your login details are username=" + username
						+ " and password=" + password, 0,
						schooladmin.getContact_number());
				model.addAttribute("username", username);
				model.addAttribute("password", password);
				model.addAttribute("success", "School Admin added successfully");

			} else {
				model.addAttribute("schooladmin", schooladmin);
				model.addAttribute("error", "school admin already exist");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_admin",
				getParentList(schoolservice.listSchoolAdmin(school_id)));
		model.put("school_details", schoolservice.getSchoolById(school_id));
		return new ModelAndView("school/add_school_admin");

	}

	/**
	 * Function for Edit School Admin
	 **/
	@RequestMapping(value = "school/editSchoolAdmin", method = RequestMethod.GET)
	public ModelAndView editSchoolAdmin(
			@ModelAttribute("command") LoginBean parents, BindingResult result,
			HttpServletRequest request) {
		try{
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int school_admin_id=Integer.parseInt(new String(decodedBytes));
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		LoginModel parent = schoolservice.getParentById(school_admin_id);
		Map<String, Object> model = new HashMap<String, Object>();
		if (parent != null) {
			
			model.put("heading", "Edit School Admin");
			model.put("parent", parent);
			int school_id = (Integer) session.getAttribute("schoolId");
			model.put("school_details", schoolservice.getSchoolById(school_id));
			model.put("school_admin",
					getParentList(schoolservice.listSchoolAdmin(school_id)));
			return new ModelAndView("school/edit_school_admin", model);
		} else {
			return new ModelAndView("redirect:manageSchoolsAdmin");
		}
      }catch(Exception e)
		{
    	  return new ModelAndView("redirect:manageSchoolsAdmin");
		}

	}

	/**
	 * Function for Edit School Admin
	 **/
	@RequestMapping(value = "school/editSchoolAdmin", method = RequestMethod.POST)
	public ModelAndView editSchoolAdmin(
			@ModelAttribute("command") LoginBean parents, BindingResult result,
			ModelMap model, HttpServletRequest request) {
		
		try {
			String q=request.getParameter("q");
			byte[] decodedBytes = Base64.decodeBase64(""+q+"");
			int school_admin_id=Integer.parseInt(new String(decodedBytes));
		try {
			LoginModel parentModel = new LoginModel();
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			int school_id = (Integer) session.getAttribute("schoolId");
			// parentModel.setContact_number(parents.getContact_number());
			parentModel.setUser_email(parents.getUser_email());
			parentModel.setUser_pass(parents.getUser_pass());
			parentModel.setFirst_name(parents.getFirst_name());
			parentModel.setLast_name(parents.getLast_name());
			parentModel.setPermission(parents.getPermission());
			schoolservice.editschooladmin(school_admin_id, parentModel);
			/* return new ModelAndView("redirect:manageSchoolsAdmin"); */
			model.put("school_details", schoolservice.getSchoolById(school_id));
			model.put("success", "School admin updated successfully");
		} catch (Exception e) {
			System.out.println(e);
		}
		LoginModel parent = schoolservice.getParentById(school_admin_id);
		model.put("parent", parent);
		HttpSession session = request.getSession();
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_admin",
				getParentList(schoolservice.listSchoolAdmin(school_id)));
		return new ModelAndView("school/edit_school_admin", model);
		}catch(Exception e)
		{
			return new ModelAndView("redirect:manageSchoolsAdmin");
		}
	}

	/**
	 * Function for view School Admin
	 **/
	@RequestMapping(value = "school/viewSchoolAdmin", method = RequestMethod.GET)
	public ModelAndView viewSchoolAdmin(
			@ModelAttribute("command") LoginBean parents, BindingResult result,
			HttpServletRequest request) {
		try{ 
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int school_admin_id=Integer.parseInt(new String(decodedBytes));
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		LoginModel parent = schoolservice.getParentById(school_admin_id);
		Map<String, Object> model = new HashMap<String, Object>();
		if (parent != null) {

			model.put("heading", "View School Admin");
			model.put("parent", parent);
			int school_id = (Integer) session.getAttribute("schoolId");
			model.put("school_details", schoolservice.getSchoolById(school_id));
			model.put("school_admin", parent);
			return new ModelAndView("school/view_school_admin", model);
		} else {
			return new ModelAndView("redirect:manageSchoolsAdmin");
		}
		}catch(Exception e)
		{
			return new ModelAndView("redirect:manageSchoolsAdmin");
		}

	}

	/**
	 * Function for delete school admin
	 **/
	@RequestMapping(value = "school/deleteSchoolAdmin", method = RequestMethod.GET)
	public ModelAndView deleteSchoolAdmin(
			@ModelAttribute("command") LoginBean parents, BindingResult result,
			HttpServletRequest request) {
		schoolservice.deleteSchoolAdmin(parents.getUser_id());
		return new ModelAndView("redirect:manageSchoolsAdmin");
	}

	/**
	 * Function for get all holiday of current school
	 **/
	@RequestMapping(value = "school/manageHoliday", method = RequestMethod.GET)
	public ModelAndView manageHoliday(
			@ModelAttribute("command") LoginBean parents, BindingResult result,
			ModelMap modelH, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");
		modelH.addAttribute("heading", "Holiday");
		Map<String, Object> model = new HashMap<String, Object>();
		
		
		List<HolidayBean> a=getHolidayList(schoolservice.getAllHoliday(school_id));
		List<HolidayDeletedBean> b=getDeletedHolidayList(schoolservice.getAllDeletedHoliday(school_id));
		
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
		
		
		
		
		model.put("all_holiday",a);
		model.put("deleted_holiday", getDeletedHolidayList(schoolservice.getAllDeletedHoliday(school_id)));
		//schoolservice.getAllDeletedHoliday(school_id).removeAll(getDeletedHolidayList(getHolidayList(schoolservice.getAllHoliday(school_id))));
		//model.put("all_holiday",getHolidayList(schoolservice.getAllHoliday(school_id)).removeAll(getDeletedHolidayList(schoolservice.getAllDeletedHoliday(school_id))));
		
		
		
		model.put("school_details", schoolservice.getSchoolById(school_id));
		return new ModelAndView("school/all_holiday", model);
	}

	/**
	 * Function for edit parent
	 **/
	@RequestMapping(value = "school/editHoliday", method = RequestMethod.GET)
	public ModelAndView editHoliday(
			@ModelAttribute("command") HolidayBean holiday,
			BindingResult result, HttpServletRequest request) {
		try{
			
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int h_id=Integer.parseInt(new String(decodedBytes));	
		HolidayModel holidaymodel = schoolservice.getHolidayById(h_id);
		String loadView = null;
		Map<String, Object> model = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		if (holidaymodel != null) {

			model.put("heading", "Edit Holiday");
			model.put("holiday", holidaymodel);
			model.put("parent", holidaymodel);
			return new ModelAndView("school/edit_holiday", model);
		} else {
			return new ModelAndView("redirect:manageHoliday");
		}
		}catch(Exception e)
		{
			return new ModelAndView("redirect:manageHoliday");
		}
	}

	/**
	 * Function for edit parent
	 **/
	@RequestMapping(value = "school/editHoliday", method = RequestMethod.POST)
	public ModelAndView editHoliday(
			@ModelAttribute("command") HolidayBean holiday,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		try{
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int h_id=Integer.parseInt(new String(decodedBytes));	
		HolidayModel holidaymodel = new HolidayModel();
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");
		holidaymodel.setHoliday_date(holiday.getHoliday_date());
		holidaymodel.setHoliday_name(holiday.getHoliday_name());
		holidaymodel.setHoliday_enddate(holiday.getHoliday_enddate());
		schoolservice.editHolidayById(h_id, holidaymodel);
		model.put("school_details", schoolservice.getSchoolById(school_id));
		// return new ModelAndView("redirect:manageParents");
		HolidayModel holidaymodel1 = schoolservice.getHolidayById(h_id);
		model.put("heading", "Edit Holiday");
		model.put("holiday", holidaymodel1);
		model.put("success", "Holiday updated successfully");
		return new ModelAndView("school/edit_holiday", model);
		}catch(Exception e)
		{
			return new ModelAndView("redirect:manageHoliday");
		}
	}

	@RequestMapping(value = "school/deleteHoliday", method = RequestMethod.GET)
	public ModelAndView deleteHoliday(
			@ModelAttribute("command") HolidayBean holiday,
			BindingResult result, ModelMap modelH) {

		schoolservice.deleteHoliday(holiday.getH_id());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("staffs", getStaffList(staffservice.getAllStaff()));
		model.put("success", "Holiday deleted successfully");
		return new ModelAndView("redirect:manageHoliday", model);

	}

	/**
	 * Function for get all holiday of current school
	 **/
	@RequestMapping(value = "school/viewCalendar", method = RequestMethod.GET)
	public ModelAndView viewCalendar(
			@ModelAttribute("command") LoginBean parents, BindingResult result,
			ModelMap modelH, HttpServletRequest request) {
		try{
			
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");
		modelH.addAttribute("heading", "View Holiday Calendar");
		Map<String, Object> model = new HashMap<String, Object>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy");
		DateFormat dateFormat2 = new SimpleDateFormat("MM");

		Date date = new Date();
		int year = Integer.parseInt(dateFormat1.format(date));
		int month = Integer.parseInt(dateFormat2.format(date));
		model.put("school_id", school_id);
		model.put("current_date", dateFormat.format(date));
		model.put("all_holiday",
				getHolidayList(schoolservice.getAllHoliday(school_id)));
		model.put("holidays", getHolidays(frontService.getHolidayByMonth(month,
				year, school_id)));
		model.put("school_details", schoolservice.getSchoolById(school_id));
		return new ModelAndView("school/view_holiday_calendar", model);
		}catch(Exception e)
		{
			return new ModelAndView("redirect:viewCalendar");
		}
	}

	/* Method for get holiday list by current month */
	private List<HolidayBean> getHolidays(List<HolidayModel> holidays) {

		List<HolidayBean> beans = null;
		if (holidays != null && !holidays.isEmpty()) {
			beans = new ArrayList<HolidayBean>();
			HolidayBean bean = null;
			for (HolidayModel holiday : holidays) {
				bean = new HolidayBean();
				bean.setHoliday_date(holiday.getHoliday_date());
				bean.setHoliday_name(holiday.getHoliday_name());
				bean.setH_id(holiday.getH_id());
				bean.setSchool_id(holiday.getSchool_id());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Function for send password to parent
	 **/
	@RequestMapping(value = "school/sendParentPassword", method = RequestMethod.POST)
	public ModelAndView sendParentPassword(
			@ModelAttribute("command") LoginBean parent, BindingResult result,
			ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int user_id = parent.getUser_id();
		String str=""+user_id+"";
		byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
		String contact_number = parent.getMobile_number()+parent.getContact_number();
		String current_url = parent.getUser_pass() + "?q=" +new String(encodedBytes);;
		String message = parent.getSchool_name();
		Sms_api sms = new Sms_api();
		sms.sendhttp(message, 0, contact_number);

		return new ModelAndView("redirect:" + current_url);
	}

	/**
	 * Function for track student by route id
	 **/
	@RequestMapping(value = "school/TrackStudent", method = RequestMethod.GET)
	public ModelAndView TrackStudent(
			@ModelAttribute("command") StudentBean studentbean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		try {
		 
			String q=request.getParameter("q");
			byte[] decodedBytes = Base64.decodeBase64(""+q+"");
			int student_id=Integer.parseInt(new String(decodedBytes));

			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			int school_id = (Integer) session.getAttribute("schoolId");
			model.put("school_details", schoolservice.getSchoolById(school_id));
			StudentModel studentModel = studentservice.getStudentById(student_id);
			System.out.println("Student Route id="+studentModel.getS_route_id());
			model.put("students", getDriverTrackList(studentservice.getDriverTrack(studentModel.getS_route_id())));
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
			
			DriverModel DM=new DriverModel();
			DM=schoolservice.getDriverByRouteId(studentModel.getS_route_id());
			
		    DriverAttendanceModel dam=new DriverAttendanceModel();
		    dam=schoolservice.getTodayDriverAttendance(DM.getDriver_id());
			
		   // model.put("driver_login_date",dam.getLogin_date());
		    model.put("all_students", getStudentList(schoolservice.getStudentsByRouteId(studentModel.getS_route_id())));
		    if(dam!=null)
			   {
				   model.put("driver_login_date",dam.getLogin_time());
			   }else
			   {
				   model.put("driver_login_date","");
			   }
		    List<VechileBean> v=new ArrayList<VechileBean>();
			v=getDriverVehicleList(schoolservice.getVechileByDriverId(DM.getDriver_id()));
			if(v!=null)
			{
				  model.put("vehicle_details",getDriverVehicleList(schoolservice.getVechileByDriverId(DM.getDriver_id())));
			}else
			{
				  model.put("vehicle_details","");
			}
			DriverModel drivermodel = schoolservice.getDriverById(DM.getDriver_id());
			model.put("driver_details", drivermodel);
			
		 

		return new ModelAndView("school/track_student", model);
		}catch(Exception e)
		{
			String q=request.getParameter("q");
			byte[] decodedBytes = Base64.decodeBase64(""+q+"");
			int student_id=Integer.parseInt(new String(decodedBytes));

			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			int school_id = (Integer) session.getAttribute("schoolId");
			model.put("school_details", schoolservice.getSchoolById(school_id));
			StudentModel studentModel = studentservice.getStudentById(student_id);
			System.out.println("Student Route id="+studentModel.getS_route_id());
			model.put("students", getDriverTrackList(studentservice.getDriverTrack(studentModel.getS_route_id())));
			model.put("route_id", studentModel.getS_route_id());
			RouteModel routemodel =new RouteModel();
			routemodel = schoolservice.getRouteById(studentModel.getS_route_id());
			if (routemodel != null) {
				model.put("latlong",getAllLatLng(schoolservice.listLatLng(studentModel.getS_route_id())));
				model.put("route", routemodel);
			}
			
			model.put("first_lat_lng", getDriverTrackList(studentservice.getDriverTrackLimit(studentModel.getS_route_id())));
			
			DriverModel DM=new DriverModel();
			DM=schoolservice.getDriverByRouteId(studentModel.getS_route_id());
			
		    DriverAttendanceModel dam=new DriverAttendanceModel();
		    dam=schoolservice.getTodayDriverAttendance(DM.getDriver_id());
			
		   // model.put("driver_login_date",dam.getLogin_date());
		    model.put("all_students", getStudentList(schoolservice.getStudentsByRouteId(studentModel.getS_route_id())));
		    if(dam!=null)
			   {
				   model.put("driver_login_date",dam.getLogin_time());
			   }else
			   {
				   model.put("driver_login_date","");
			   }
		    List<VechileBean> v=new ArrayList<VechileBean>();
			v=getDriverVehicleList(schoolservice.getVechileByDriverId(DM.getDriver_id()));
			if(v!=null)
			{
				  model.put("vehicle_details",getDriverVehicleList(schoolservice.getVechileByDriverId(DM.getDriver_id())));
			}else
			{
				  model.put("vehicle_details","");
			}
			DriverModel drivermodel = schoolservice.getDriverById(DM.getDriver_id());
			model.put("driver_details", drivermodel);
			
			return new ModelAndView("school/track_student", model);
		}
	}

	/* Method for route latlng */
	private List<DriverTrack> getDriverTrackList(
			List<DriverTrackModel> tracklist) {
		List<DriverTrack> beans = null;
		if (tracklist != null && !tracklist.isEmpty()) {
			beans = new ArrayList<DriverTrack>();
			DriverTrack bean = null;
			for (DriverTrackModel t_list : tracklist) {
				bean = new DriverTrack();
				bean.setRoute_id(t_list.getRoute_id());
				RouteModel r=schoolservice.getRouteById(t_list.getRoute_id());
				DriverModel driver_details=schoolservice.getDriverById(t_list.getDriver_id());

				bean.setLng(t_list.getLng());
				bean.setLat(t_list.getLat());
				bean.setTrack_date(t_list.getTrack_date());
				bean.setTrack_id(t_list.getTrack_id());
				bean.setSpeed(t_list.getSpeed());
				bean.setRoute_name(r.getRoute_name());
				bean.setDriver_name(driver_details.getDriver_fname());
				
				beans.add(bean);
			}
		} else {
			System.out.println("No Data Available");
		}
		return beans;
	}

	/**
	 * Function for chatting in super admin with school admin
	 **/
	@RequestMapping(value = "school/chatting", method = RequestMethod.GET)
	public ModelAndView chatting(
			@ModelAttribute("command") StudentBean studentbean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int user_id = (Integer) session.getAttribute("user_id");
		//model.put("school_admins",getAdminList(schoolservice.adminList(), user_id));
		model.put("school_admins",getAdminListLatest(schoolservice.adminListLatest(), user_id));
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		return new ModelAndView("school/chatting", model);
	}
	
	private List<LoginBean> getAdminListLatest(List<LoginModel> vehicles,int user_id) {

		List<LoginBean> beans = null;
		if (vehicles != null && !vehicles.isEmpty()) {
			beans = new ArrayList<LoginBean>();
			LoginBean bean = null;
			for (LoginModel vehicle : vehicles) {
				bean = new LoginBean();
				System.out.println("User ID="+vehicle.getUser_id());
				int count = schoolservice.getAllMessageCount(user_id,vehicle.getUser_id()).size();
				bean.setP_status(count);
				bean.setFirst_name(vehicle.getFirst_name());
				bean.setLast_name(vehicle.getLast_name());
				bean.setUser_name(vehicle.getUser_name());
				bean.setUser_email(vehicle.getUser_email());
				bean.setContact_number(vehicle.getContact_number());
				bean.setPermission(vehicle.getPermission());
				bean.setUser_pass(vehicle.getUser_pass());
				bean.setUser_id(vehicle.getUser_id());
				bean.setMiddle_name(vehicle.getMiddle_name());
				/*int count = schoolservice.getAllMessageCount(user_id,Integer.parseInt(vehicle[1].toString())).size();
				bean.setP_status(count);
				bean.setFirst_name(vehicle[8].toString());
				bean.setLast_name(vehicle[9].toString());
				bean.setUser_name(vehicle[2].toString());
				bean.setUser_email(vehicle[3].toString());
				bean.setContact_number(vehicle[7].toString());
				bean.setPermission(Integer.parseInt(vehicle[11].toString()));
				bean.setUser_pass(vehicle[4].toString());
				bean.setUser_id(Integer.parseInt(vehicle[1].toString()));*/
				beans.add(bean);
			}
		} else {
			System.out.println("empty Result");
		}
		return beans;

	}
	/* Method for get country list */
	private List<LoginBean> getAdminList(List<LoginModel> users, int user_id) {

		List<LoginBean> beans = null;
		if (users != null && !users.isEmpty()) {
			beans = new ArrayList<LoginBean>();
			LoginBean bean = null;
			for (LoginModel user : users) {

				bean = new LoginBean();
				bean.setUser_id(user.getUser_id());

				int count = schoolservice.getAllMessageCount(user_id,user.getUser_id()).size();
				bean.setP_status(count);
				bean.setFirst_name(user.getFirst_name());
				bean.setLast_name(user.getLast_name());
				bean.setUser_name(user.getUser_name());
				bean.setUser_email(user.getUser_email());
				bean.setContact_number(user.getContact_number());
				bean.setPermission(user.getPermission());
				bean.setUser_pass(user.getUser_pass());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Function for chatting between parent and school admin
	 **/
	@RequestMapping(value = "school/chattingToParent", method = RequestMethod.GET)
	public ModelAndView chattingToParent(
			@ModelAttribute("command") StudentBean studentbean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int user_id = (Integer) session.getAttribute("user_id");
		int school_id = (Integer) session.getAttribute("schoolId");
		//model.put("school_admins", getParentListNew(studentservice.listParent(school_id), user_id));
		model.put("school_admins",getAdminListLatest(schoolservice.parentListLatest(school_id), user_id));
		model.put("school_details", schoolservice.getSchoolById(school_id));
		return new ModelAndView("school/chattingParent", model);
	}
	
	/**
	 * Function for chatting between parent and school admin
	 **/
	@RequestMapping(value = "school/chattingToParentSocket", method = RequestMethod.GET)
	public ModelAndView chattingToParentSocket(
			@ModelAttribute("command") StudentBean studentbean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int user_id = (Integer) session.getAttribute("user_id");
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("User_id", user_id);
		model.put("User_name", session.getAttribute("f_name"));
		//model.put("school_admins", getParentListNew(studentservice.listParent(school_id), user_id));
		model.put("school_admins",getAdminListLatest(schoolservice.parentListLatest(school_id), user_id));
		model.put("school_details", schoolservice.getSchoolById(school_id));
		return new ModelAndView("school/chattingParent_socket", model);
	}
	/* Method for get country list */
	private List<LoginBean> getParentListNew(List<LoginModel> users, int user_id) {

		List<LoginBean> beans = null;
		if (users != null && !users.isEmpty()) {
			beans = new ArrayList<LoginBean>();
			LoginBean bean = null;
			for (LoginModel user : users) {

				bean = new LoginBean();
				bean.setUser_id(user.getUser_id());

				int count = schoolservice.getParentAllMessageCount(user_id,
						user.getUser_id()).size();
				bean.setP_status(count);
				bean.setFirst_name(user.getFirst_name());
				bean.setLast_name(user.getLast_name());
				bean.setUser_name(user.getUser_name());
				bean.setUser_email(user.getUser_email());
				bean.setContact_number(user.getContact_number());
				bean.setPermission(user.getPermission());
				bean.setUser_pass(user.getUser_pass());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Function for chatting between parent and school admin
	 **/
	@RequestMapping(value = "school/chattingToDriver", method = RequestMethod.GET)
	public ModelAndView chattingToDriver(
			@ModelAttribute("command") StudentBean studentbean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int user_id = (Integer) session.getAttribute("user_id");
		int school_id = (Integer) session.getAttribute("schoolId");
		// model.put("school_admins",
		// getDriversList(schoolservice.listDriver(school_id)));
		model.put("school_details", schoolservice.getSchoolById(school_id));
	    //model.put("school_admins",getDriversListNew(schoolservice.listDriver(school_id), user_id));
		model.put("school_admins",getDriversListNew(schoolservice.listDriverLatest(school_id), user_id));
		
		//model.put("school_admins",getAdminListLatest(schoolservice.parentListLatest(school_id), user_id));
		return new ModelAndView("school/chatting_driver", model);
	}
	
	/**
	 * Function for chatting between parent and school admin
	 **/
	@RequestMapping(value = "school/chattingToDriverSocket", method = RequestMethod.GET)
	public ModelAndView chattingToDriverSocket(
			@ModelAttribute("command") StudentBean studentbean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int user_id = (Integer) session.getAttribute("user_id");
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("User_id", user_id);
		model.put("User_name", session.getAttribute("f_name"));
		// model.put("school_admins",
		// getDriversList(schoolservice.listDriver(school_id)));
		model.put("school_details", schoolservice.getSchoolById(school_id));
	    //model.put("school_admins",getDriversListNew(schoolservice.listDriver(school_id), user_id));
		model.put("school_admins",getDriversListNew(schoolservice.listDriverLatest(school_id), user_id));
		
		//model.put("school_admins",getAdminListLatest(schoolservice.parentListLatest(school_id), user_id));
		return new ModelAndView("school/chatting_driver_socket", model);
	}

	/**
	 * Method for get driver message for school admin
	 **/
	/* Method for get country list */
	private List<DriverBean> getDriversListNew(List<DriverModel> users,
			int user_id) {

		List<DriverBean> beans = null;
		if (users != null && !users.isEmpty()) {
			beans = new ArrayList<DriverBean>();
			DriverBean bean = null;
			for (DriverModel user : users) {

				bean = new DriverBean();
				bean.setDriver_id(user.getDriver_id());
				int count = schoolservice.getDriverAllMessageCount(user_id,user.getDriver_id()).size();
				bean.setStatus(count);
				bean.setDriver_fname(user.getDriver_fname());
				bean.setDriver_lname(user.getDriver_lname());
				bean.setUsername(user.getUsername());
				bean.setD_email(user.getD_email());
				bean.setContact_number(user.getContact_number());
				bean.setDevice_token(user.getDevice_token());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}
	/**
	 * Method for manage all school classes 
	 **/
	@RequestMapping(value = "school/manageClasses", method = RequestMethod.GET)
	public ModelAndView manageClasses(
			@ModelAttribute("command") SchoolClasses sClass,
			BindingResult result, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		model.put("class_info", getAllClassList(schoolservice.getAllSchoolClasses(school_id)));
		model.put("heading", "Manage Classes");
		return new ModelAndView("school/all_classes", model);
	}
	/* Method for get country list */
	private List<SchoolClasses> getAllClassList(List<SchoolClassesModel> classes) {

		List<SchoolClasses> beans = null;
		if (classes != null && !classes.isEmpty()) {
			beans = new ArrayList<SchoolClasses>();
			SchoolClasses bean = null;
			for (SchoolClassesModel s_class : classes) {

				bean = new SchoolClasses();
				bean.setClass_id(s_class.getClass_id());
				bean.setClass_name(s_class.getClass_name());
				bean.setSchool_id(s_class.getSchool_id());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Method for manage Add new class
	 **/
	@RequestMapping(value = "school/addClass", method = RequestMethod.GET)
	public ModelAndView addClass(
			@ModelAttribute("command") SchoolClasses sClass,
			BindingResult result, HttpServletRequest request, ModelMap model1) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		model.put("heading", "Add Class");
		return new ModelAndView("school/add_school_class", model);
	
	}
	/**
	 * Method for manage all school classes 
	 **/
	@RequestMapping(value = "school/addClass", method = RequestMethod.POST)
	public ModelAndView addClass(
			@ModelAttribute("command") SchoolClasses sClass,
			BindingResult result, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		SchoolClassesModel cls=new SchoolClassesModel();
		cls.setClass_name(sClass.getClass_name());
		cls.setSchool_id(school_id);
		schoolservice.addSchoolClass(cls);
		model.put("heading", "Add Class");
		model.put("success", "Class added successfully");	
		return new ModelAndView("school/add_school_class", model);
	
	}
	@RequestMapping(value = "school/deleteClass", method = RequestMethod.GET)
	public ModelAndView deleteClass(
			@ModelAttribute("command") SchoolClasses sClass,
			BindingResult result, ModelMap modelH) {
		schoolservice.deleteClass(sClass.getClass_id());
		return new ModelAndView("redirect:manageClasses");
	}
	/**
	 * Function for send password to parent
	 **/
	@RequestMapping(value = "school/sendDriverPassword", method = RequestMethod.POST)
	public ModelAndView sendDriverPassword(
			@ModelAttribute("command") LoginBean parent, BindingResult result,
			ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int user_id = parent.getUser_id();
		String str=""+user_id+"";
		byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
		String contact_number = parent.getMobile_number()
				+ parent.getContact_number();
		String current_url = parent.getUser_pass() + "?q=" + new String(encodedBytes);
		String message = parent.getSchool_name();
		System.out.println(message);
		Sms_api sms = new Sms_api();
		sms.sendhttp(message, 0, contact_number);

		return new ModelAndView("redirect:" + current_url);
	}
	/**
	 * Function for send password to driver
	 **/
	@RequestMapping(value = "school/sendDriverPassword1", method = RequestMethod.POST)
	public ModelAndView sendDriverPassword1(
			@ModelAttribute("command") LoginBean parent, BindingResult result,
			ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
	
		String contact_number = parent.getMobile_number()+ parent.getContact_number();
		String current_url = "/school/addDriver/";
		String message = parent.getFirst_name();
		Sms_api sms = new Sms_api();
		sms.sendhttp(message, 0, contact_number);

		return new ModelAndView("redirect:" + current_url);
	}
	
	/**
	 * Function for send password to driver
	 **/
	@RequestMapping(value = "school/sendDriverPassword2", method = RequestMethod.POST)
	public ModelAndView sendDriverPassword2(
			@ModelAttribute("command") LoginBean parent, BindingResult result,
			ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
	
		String contact_number = parent.getMobile_number()+ parent.getContact_number();
		String current_url = "/school/addParent/";
		String message = parent.getFirst_name();
		Sms_api sms = new Sms_api();
		sms.sendhttp(message, 0, contact_number);

		return new ModelAndView("redirect:" + current_url);
	}
	
	/**
	 * Function for track student by route id
	 **/
	@RequestMapping(value = "school/trackDriver", method = RequestMethod.GET)
	public ModelAndView trackDriver(
			@ModelAttribute("command") StudentBean studentbean,DriverBean driverbean
			,BindingResult result, ModelMap model, HttpServletRequest request) {
		try {
			String q=request.getParameter("q");
			byte[] decodedBytes = Base64.decodeBase64(""+q+"");
			int driver_id=Integer.parseInt(new String(decodedBytes));
			HttpSession session = request.getSession();
		try {
			
			
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			DriverModel drivermodel = schoolservice.getDriverById(driver_id);
			System.out.println("Route_id="+drivermodel.getRoute_id());
			int school_id = (Integer) session.getAttribute("schoolId");
			model.put("school_details", schoolservice.getSchoolById(school_id));
		/*	StudentModel studentModel = studentservice
					.getStudentById(studentbean.getStudent_id());*/
			model.put("students", getDriverTrackList(studentservice
					.getDriverTrack(drivermodel.getRoute_id())));
			model.put("students", getDriverTrackList(studentservice
					.getDriverTrack(drivermodel.getRoute_id())));
			model.put("all_students", getStudentList(schoolservice.getStudentsByRouteId(drivermodel.getRoute_id())));
				
			model.put("first_lat_lng", getDriverTrackList(studentservice.getDriverTrackLimit(drivermodel.getRoute_id())));
			model.put("size_l",getDriverTrackList(studentservice.getDriverTrack(drivermodel.getRoute_id())).size() - 1);
			
			RouteModel routemodel =new RouteModel();
			routemodel = schoolservice.getRouteById(drivermodel.getRoute_id());
			if (routemodel != null) {
				model.put("latlong",getAllLatLng(schoolservice.listLatLng(drivermodel.getRoute_id())));
				model.put("route", routemodel);
			}
			model.put("route_id", drivermodel.getRoute_id());		
			DriverAttendanceModel dam=new DriverAttendanceModel();
		    dam=schoolservice.getTodayDriverAttendance(driver_id);
		    //model.put("driver_login_date",dam.getLogin_date());
		    if(dam!=null)
			   {
				   model.put("driver_login_date",dam.getLogin_time());
			   }else
			   {
				   model.put("driver_login_date","");
			   }
		    model.put("driver_details", drivermodel);
		    
		    List<VechileBean> v=new ArrayList<VechileBean>();
			v=getDriverVehicleList(schoolservice.getVechileByDriverId(driver_id));
			if(v!=null)
			{
				  model.put("vehicle_details",getDriverVehicleList(schoolservice.getVechileByDriverId(driver_id)));
			}else
			{
				  model.put("vehicle_details","");
			}
		    
		} catch (Exception e) {
			q=request.getParameter("q");
			decodedBytes = Base64.decodeBase64(""+q+"");
			driver_id=Integer.parseInt(new String(decodedBytes));	
			DriverModel drivermodel = schoolservice.getDriverById(driver_id);
			RouteModel routemodel =new RouteModel();
			routemodel = schoolservice.getRouteById(drivermodel.getRoute_id());
			if (routemodel != null) {
				model.put("latlong",getAllLatLng(schoolservice.listLatLng(drivermodel.getRoute_id())));
				model.put("route", routemodel);
			}
			model.put("route_id", drivermodel.getRoute_id());
			 model.put("driver_details", drivermodel);
			
			
		    DriverAttendanceModel dam=new DriverAttendanceModel();
		    
		    dam=schoolservice.getTodayDriverAttendance(driver_id);
		/*	if(dam!=null)
			{
				model.put("driver_login_date",dam.getLogin_date());
			}else
			{
				model.put("driver_login_date","");
			}*/
		    if(dam!=null)
			   {
				   model.put("driver_login_date",dam.getLogin_time());
			   }else
			   {
				   model.put("driver_login_date","");
			   }
			model.put("all_students", getStudentList(schoolservice.getStudentsByRouteId(drivermodel.getRoute_id())));
			model.put("error", "No data available1");
			model.put("driver_details", drivermodel);
			
			List<VechileBean> v=new ArrayList<VechileBean>();
			v=getDriverVehicleList(schoolservice.getVechileByDriverId(driver_id));
			if(v!=null)
			{
				  model.put("vehicle_details",getDriverVehicleList(schoolservice.getVechileByDriverId(driver_id)));
			}else
			{
				  model.put("vehicle_details","");
			}
			
		}
	
		return new ModelAndView("school/track_driver", model);
		}catch(NumberFormatException e)
		{
			return new ModelAndView("redirect:manageDrivers");
		}
	}
	/**
	 * Function for view Student
	 **/
	@RequestMapping(value = "school/viewDriverAttendance", method = RequestMethod.GET)
	public ModelAndView viewDriverAttendance(
			@ModelAttribute("command") StudentBean students,DriverBean driverbean,
			BindingResult result, HttpServletRequest request) {
		try {
			String q=request.getParameter("q");
			byte[] decodedBytes = Base64.decodeBase64(""+q+"");
			int driver_id=Integer.parseInt(new String(decodedBytes));
		DriverModel drivermodel = schoolservice.getDriverById(driver_id);
		Map<String, Object> model = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");

		if (drivermodel != null) {
			try {
			
				model.put("heading", "View Driver Attendance");
				model.put("student", drivermodel);
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				model.put("current_date", dateFormat.format(date));
				model.put("all_holiday",
						getHolidayList(schoolservice.getAllHoliday(school_id)));
				model.put("all_present", getDriverPresentList(schoolservice
						.getDriverAttendanceList(driver_id)));

			} catch (Exception e) {
				e.printStackTrace();
			}
			
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
			total_present=schoolservice.getDriverAttendanceListByMonth(driver_id,month,year).size();
			int iYear = Integer.parseInt(year.trim());
			int iDay = 1;
			Calendar mycal = new GregorianCalendar(iYear, iMonth, iDay);
			int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
			System.out.println("Day in month="+daysInMonth);
			List<HolidayBean> b=getHolidayList(schoolservice.getAllHolidayByMonth(school_id,month,year));
			List<HolidayDeletedBean> c=getDeletedHolidayList(schoolservice.getAllDeletedHoliday(school_id));
			try{
			for(int i=0;i<b.size();i++)
			{
				for(int j=0;j<c.size();j++)
				{
					if(b.get(i).getH_id().equals(c.get(j).getHoliday_id()))
					{
						b.remove(i);
					}
				}
			}
			}catch(Exception e)
			{
				System.out.println(e);
			}
			
			if(b!=null)
			{
				absent_day=absent_day- b.size();
				model.put("holiday_days", b.size());
			}else
			{
				model.put("holiday_days",0);
			}
			absent_day=daysInMonth-total_present;
			model.put("present_day", total_present);
			model.put("absent_day", absent_day);
			model.put("school_details", schoolservice.getSchoolById(school_id));
			return new ModelAndView("school/view_driver_attendance", model);
		} else {
			return new ModelAndView("redirect:manageDrivers");
		}
		}catch(Exception e)
		{
			return new ModelAndView("redirect:manageDrivers");
		}

	}
	/**
	 * Method for get all iterate attendace list
	 * **/
	private List<DriverAttendanceBean> getDriverPresentList(List<DriverAttendanceModel> presents) {

		List<DriverAttendanceBean> beans = null;
		if (presents != null && !presents.isEmpty()) {
			beans = new ArrayList<DriverAttendanceBean>();
			DriverAttendanceBean bean = null;
			for (DriverAttendanceModel present : presents) {
				bean = new DriverAttendanceBean();
				bean.setA_id(present.getA_id());
				bean.setDriver_id(present.getDriver_id());
				bean.setLogin_date(present.getLogin_date());
				bean.setLogin_time(present.getLogin_time());
				bean.setLogout_time(present.getLogout_time());
				bean.setStatus(present.getStatus());
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
	 * Method For get all schools
	 **/
	private List<VehicleDocBean> prepareListOfVehicleDoc(List<VehicleDocModel> vehicles) {

		List<VehicleDocBean> beans = null;
		if (vehicles != null && !vehicles.isEmpty()) {
			beans = new ArrayList<VehicleDocBean>();
			VehicleDocBean bean = null;
			for (VehicleDocModel vehicle : vehicles) {
				bean = new VehicleDocBean();
				bean.setInsurance_card_copy(vehicle.getInsurance_card_copy());
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
	private List<DriverDocBean> prepareListOfDriverDoc(List<DriverDocModel> drivers) {

		List<DriverDocBean> beans = null;
		if (drivers != null && !drivers.isEmpty()) {
			beans = new ArrayList<DriverDocBean>();
			DriverDocBean bean = null;
			for (DriverDocModel vehicle : drivers) {
				bean = new DriverDocBean();
				bean.setInsurance_card_copy(vehicle.getInsurance_card_copy());
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
//	Method for iterate nationlity list
	public List<NationlityBean> prepareNationalityList(List<NationlityModel> nationModel)
	{
		List<NationlityBean> beans = null;
		if (nationModel != null && !nationModel.isEmpty()) {
			beans = new ArrayList<NationlityBean>();
			NationlityBean bean = null;
			for (NationlityModel nation : nationModel) {
				bean = new NationlityBean();
				bean.setName(nation.getName());
				bean.setNational_id(nation.getNational_id());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}
	@RequestMapping(value = "school/addWeekEndHoliday", method = RequestMethod.POST)
	public ModelAndView addWeekEndHoliday(
			@ModelAttribute("command") HolidayBean holidaybean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			String holiday1 = holidaybean.getHoliday_date();
			String[] parts = holiday1.split("-");
			int year1 =Integer.parseInt(parts[0]); // 004
			int month1 = Integer.parseInt(parts[1]); // 034556
			int day1 = Integer.parseInt(parts[2]); // 034556
			
			
			
			String holiday2 = holidaybean.getHoliday_enddate();
			String[] parts2 = holiday2.split("-");
			int year2 = Integer.parseInt(parts2[0]); // 004
			int month2 = Integer.parseInt(parts2[1]); // 034556
			int day2 = Integer.parseInt(parts2[2]); // 034556
			
			System.out.println(holidaybean.getDay());
			List<LocalDate> dates = getWeekendDates(new LocalDate(year1,month1,day1), new LocalDate(year2,month2,day2),holidaybean.getDay());
	        for (LocalDate date : dates)
	        {
				HolidayModel holidaymodel = new HolidayModel();
				holidaymodel.setH_id(null);
				holidaymodel.setHoliday_date(date.toString());
				holidaymodel.setHoliday_name(holidaybean.getHoliday_name());
				holidaymodel.setHoliday_enddate(date.toString());
				holidaymodel.setSchool_id((Integer) session.getAttribute("schoolId"));
				schoolservice.addHoliday(holidaymodel);
				model.put("school_details", schoolservice
						.getSchoolById((Integer) session.getAttribute("schoolId")));
				model.addAttribute("success", "Holiday Added Successfully");
				model.addAttribute("heading", "Add Holiday");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}

	
		return new ModelAndView("redirect:viewCalendar");
	}
	@SuppressWarnings("unused")
	private static List<LocalDate> getWeekendDates
	    (LocalDate start, LocalDate end,String dayName)
	{
	    List<LocalDate> result = new ArrayList<LocalDate>();
	    for (LocalDate date = start;
	         date.isBefore(end);
	         date = date.plusDays(1))
	    {
	        int day = (Integer)date.getDayOfWeek();
	        // These could be passed in...
	        if(dayName.equals("SATURDAY"))
	        {
	        	if (day == DateTimeConstants.SATURDAY)
		        {
		            result.add(date);
		        }
	        }
	        if(dayName.equals("SUNDAY"))
	        {
	        	if (day == DateTimeConstants.SUNDAY)
		        {
		            result.add(date);
		        }
	        }
	        if(dayName.equals("MONDAY"))
	        {
	        	if (day == DateTimeConstants.MONDAY)
		        {
		            result.add(date);
		        }
	        }
	        if(dayName.equals("TUESDAY"))
	        {
	        	if (day == DateTimeConstants.TUESDAY)
		        {
		            result.add(date);
		        }
	        }
	        if(dayName.equals("WEDNESDAY"))
	        {
	        	if (day == DateTimeConstants.WEDNESDAY)
		        {
		            result.add(date);
		        }
	        }
	        if(dayName.equals("THURSDAY"))
	        {
	        	if (day == DateTimeConstants.THURSDAY)
		        {
		            result.add(date);
		        }
	        }
	        if(dayName.equals("FRIDAY"))
	        {
	        	if (day == DateTimeConstants.FRIDAY)
		        {
		            result.add(date);
		        }
	        }
	        
	        
	    }
	    return result;
	}
	/**
	 * Function for edit parent
	 **/
	@RequestMapping(value = "school/deleteAdminHoliday", method = RequestMethod.GET)
	public ModelAndView deleteAdminHoliday(
			@ModelAttribute("command") HolidayBean holiday,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		try{
		int h_id=holiday.getH_id();	
		HolidayModel holidaymodel = new HolidayModel();
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int school_id = (Integer) session.getAttribute("schoolId");
		holidaymodel = schoolservice.getHolidayById(h_id);
		
		HolidayDeletedModel hdm=new HolidayDeletedModel();
		hdm.setHoliday_id(holidaymodel.getH_id());
		hdm.setSchool_id(school_id);
		schoolservice.schoolDeletedHoliday(hdm);
		model.put("success", "Holiday deleted successfully");
		return new ModelAndView("redirect:manageHoliday", model);
		}catch(Exception e)
		{
			return new ModelAndView("redirect:manageHoliday");
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
	 * Function for edit parent
	 **/
	@RequestMapping(value = "school/deleteMultipleHoliday", method = RequestMethod.POST)
	public ModelAndView deleteMultipleHoliday(
			@ModelAttribute("command") HolidayBean holiday,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		try{
	
		HolidayModel holidaymodel = new HolidayModel();
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int j=0;
		List<String> items = Arrays.asList(holiday.getHoliday_name().split("\\s*,\\s*"));
		List<String> items_school = Arrays.asList(holiday.getHoliday_date().split("\\s*,\\s*"));
		System.out.println("abc");
		for(String a : items)
		{	
			if(items_school.get(j).equals("0"))
			{
				int school_id = (Integer) session.getAttribute("schoolId");
				holidaymodel = schoolservice.getHolidayById(Integer.parseInt(a));
				HolidayDeletedModel hdm=new HolidayDeletedModel();
				hdm.setHoliday_id(holidaymodel.getH_id());
				hdm.setSchool_id(school_id);
				schoolservice.schoolDeletedHoliday(hdm);
				
			}else{
				schoolservice.deleteHoliday(Integer.parseInt(a));
			}
		
			j++;
		}
		
		return new ModelAndView("redirect:manageHoliday", model);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new ModelAndView("redirect:manageHoliday");
		}
		
	}
	
	/**
	 * Function for chatting in super admin with school admin
	 **/
	@RequestMapping(value = "school/chattingSocket", method = RequestMethod.GET)
	public ModelAndView chattingSocket(
			@ModelAttribute("command") StudentBean studentbean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userRole")!= "Manager")
		{
		 return new ModelAndView("redirect:/login.html");
		}
		int user_id = (Integer) session.getAttribute("user_id");
		model.put("User_id", user_id);
		model.put("User_name", session.getAttribute("f_name"));
		//model.put("school_admins",getAdminList(schoolservice.adminList(), user_id));
		model.put("school_admins",getAdminListLatest(schoolservice.adminListLatest(), user_id));
		int school_id = (Integer) session.getAttribute("schoolId");
		model.put("school_details", schoolservice.getSchoolById(school_id));
		return new ModelAndView("school/chatting_socket", model);
	}
	
	/**
	 * Function for edit student
	 **/
	@RequestMapping(value = "school/viewVechile", method = RequestMethod.GET)
	public ModelAndView viewVechile(
			@ModelAttribute("command") VechileBean vechile,
			BindingResult result, HttpServletRequest request) {
		try{
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int vehicle_id=Integer.parseInt(new String(decodedBytes));
		VehicleModel vehiclemodel = vechileservice.getVehicleById(vehicle_id);
		String ed=request.getParameter("v");
		if(ed != null)
		{
			byte[] decodedBytes1 = Base64.decodeBase64(""+ed+"");
			schoolservice.updateHeadNotification(Integer.parseInt(new String(decodedBytes1)));
			
		}
		Map<String, Object> model = new HashMap<String, Object>();
		if (vehiclemodel != null) {
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			int school_id = (Integer) session.getAttribute("schoolId");
			model.put("school_details", schoolservice.getSchoolById(school_id));
			model.put("heading", "View Vehicle");
			model.put("vechileBean", vehiclemodel);
			model.put("documents", prepareListOfVehicleDoc(schoolservice.getVehicleDocument(vehiclemodel.getVechile_id())));

			return new ModelAndView("school/view_vehicle", model);
		} else {
			return new ModelAndView("redirect:manageVehicle");
		}
	  }catch(Exception e)
		{
		    return new ModelAndView("redirect:manageVehicle");
		}

	}
	
	/**
	 * Function for track all drivers
	 **/
	@RequestMapping(value = "school/trackDrivers", method = RequestMethod.GET)
	public ModelAndView trackDrivers(
			@ModelAttribute("command") StudentBean studentbean,DriverBean driverbean
			,BindingResult result, ModelMap model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			if (session.getAttribute("userRole")!= "Manager")
			{
			 return new ModelAndView("redirect:/login.html");
			}
			
			DriverModel drivermodel = null;
			int school_id = (Integer) session.getAttribute("schoolId");
			
			List<RouteModel> routes=new ArrayList<RouteModel>();
			routes= schoolservice.listRoute(school_id);
			Map<Integer, List<DriverTrack>> map = new HashMap<Integer, List<DriverTrack>>();
			
			Map<Integer,  RouteModel> map_route = new HashMap<Integer, RouteModel>();
			int i=0;
			int total=0;
			for (RouteModel st : routes) {
				List<DriverTrack> driver_lat_lng=new ArrayList<DriverTrack>();	
				driver_lat_lng=getDriverTrackList(studentservice.getDriverTrackLimitOne(st.getRoute_id()));
				if(driver_lat_lng!=null)
				{
					map.put(i,driver_lat_lng);
					map_route.put(i, schoolservice.getRouteById(st.getRoute_id()));
					total=total+studentservice.getDriverTrack(st.getRoute_id()).size();
				}
			i++;
			}
			model.put("drivers",getDriversList(schoolservice.listDriver(school_id)));
			model.put("school_id",school_id);
			model.put("school_details", schoolservice.getSchoolById(school_id));
		    model.put("students", getDriverTrackList(studentservice.getDriverTrack(0)));
			model.put("first_lat_lng", getDriverTrackList(studentservice.getDriverTrackLimit(0)));
			if(map.isEmpty())
			{
				SchoolModel sc_details=new SchoolModel();
				sc_details=schoolservice.getSchoolById(school_id);
				List<DriverTrack> dtBean=new ArrayList<DriverTrack>();	
				DriverTrack	Dbean = new DriverTrack();	
				//Dbean.setLat(sc_details.getSchool_lat());
				//Dbean.setLng(sc_details.getSchool_lng());
				//	dtBean.add(Dbean);
				//	map.put(i,dtBean);
				model.put("lat_lng", map);
				model.put("route_details", "");
			}else
			{
				model.put("lat_lng", map);
				model.put("route_details",map_route);
			}
			model.put("size_l",total);
			//model.put("size_l",getDriverTrackList(studentservice.getDriverTrack(0)).size() - 1);
			RouteModel routemodel =new RouteModel();
			routemodel = schoolservice.getRouteById(0);
			if (routemodel != null) {
				model.put("latlong",getAllLatLng(schoolservice.listLatLng(0)));
				model.put("route", routemodel);
			}
			//model.put("route_id", drivermodel.getRoute_id());
		} catch (Exception e) {
			
			
			DriverModel drivermodel =null;
			RouteModel routemodel =new RouteModel();
			HttpSession session = request.getSession();
			int school_id = (Integer) session.getAttribute("new_school_id");
			model.put("drivers",getDriversList(schoolservice.listDriver(school_id)));
			routemodel = schoolservice.getRouteById(0);
			if (routemodel != null) {
				model.put("latlong",getAllLatLng(schoolservice.listLatLng(0)));
				model.put("route", routemodel);
			}
			model.put("route_id", 0);
			model.put("error", "No data available1");
			e.printStackTrace();
		}
		
		return new ModelAndView("school/track_all_drivers", model);
	}
	
	/**
	 *@access public
	 *Method for set and redirect for language
	 **/
	@RequestMapping(value = "school/langSwitcher", method = RequestMethod.GET)
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
					session.setAttribute("schoolId", found.getSchool_id());
					session.setAttribute("userRole", "Manager");
					session.setAttribute("user_id", found.getUser_id());
					session.setAttribute("permission", found.getPermission());
					session.setAttribute("main_admin",found.getMain_school_admin());
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
	/* Method for route latlng */
	private List<VechileBean> getDriverVehicleList(
			List<VehicleModel> tracklist) {
		List<VechileBean> beans = null;
		if (tracklist != null && !tracklist.isEmpty()) {
			beans = new ArrayList<VechileBean>();
			VechileBean bean = null;
			for (VehicleModel t_list : tracklist) {
				bean = new VechileBean();
				bean.setBus_number(t_list.getBus_number());
				bean.setVechile_id(t_list.getVechile_id());
				bean.setVehile_name(t_list.getVehile_name());
				beans.add(bean);
			}
		} else {
			System.out.println("No Data Available");
		}
		return beans;
	}
}
