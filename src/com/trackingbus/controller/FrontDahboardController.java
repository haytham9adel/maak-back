package com.trackingbus.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import resources.ApplePushNotification;
import resources.ApplicationMailer;
import resources.Assets;
import resources.Gcm;
import resources.JodaTimeExample;

import com.trackingbus.model.AdminSetupModel;
import com.trackingbus.model.CategoryContactModel;
import com.trackingbus.model.CityModel;
import com.trackingbus.model.ContactContentModel;
import com.trackingbus.model.ContactDetailModel;
import com.trackingbus.model.CountryModel;
import com.trackingbus.model.DriverMessage;
import com.trackingbus.model.DriverModel;
import com.trackingbus.model.DriverTrackModel;
import com.trackingbus.model.FaqModel;
import com.trackingbus.model.FeaturesModel;
import com.trackingbus.model.FrontDashboard;
import com.trackingbus.model.HolidayDeletedModel;
import com.trackingbus.model.HolidayModel;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.NotificationModel;
import com.trackingbus.model.PageContentModel;
import com.trackingbus.model.PageModel;
import com.trackingbus.model.ParentMessage;
import com.trackingbus.model.RouteLatLngModel;
import com.trackingbus.model.RouteModel;
import com.trackingbus.model.SchoolMessage;
import com.trackingbus.model.SchoolModel;
import com.trackingbus.model.SliderModel;
import com.trackingbus.model.StateModel;
import com.trackingbus.model.StudentModel;
import com.trackingbus.model.StudentTrackingModel;
import com.trackingbus.model.SubscriberModel;
import com.trackingbus.model.TypingModel;
import com.trackingbus.model.relationshipModel;
import com.trackingbus.service.FrontDashboardService;
import com.trackingbus.service.SchoolService;
import com.trackingbus.service.StudentService;
import com.trackingbus.bean.CategoryContactBean;
import com.trackingbus.bean.ContactDetailBean;
import com.trackingbus.bean.CountryBean;
import com.trackingbus.bean.DriverBean;
import com.trackingbus.bean.DriverDocBean;
import com.trackingbus.bean.DriverTrack;
import com.trackingbus.bean.FaqBean;
import com.trackingbus.bean.FeaturesBean;
import com.trackingbus.bean.FrontDashboardBean;
import com.trackingbus.bean.ContactBean;
import com.trackingbus.bean.HolidayBean;
import com.trackingbus.bean.HolidayDeletedBean;
import com.trackingbus.bean.LoginBean;
import com.trackingbus.bean.PageBean;
import com.trackingbus.bean.RouteBean;
import com.trackingbus.bean.RouteLatLng;
import com.trackingbus.bean.SchoolBean;
import com.trackingbus.bean.SchoolMessageBean;
import com.trackingbus.bean.SliderBean;
import com.trackingbus.bean.StateBean;
import com.trackingbus.bean.CityBean;
import com.trackingbus.bean.StudentBean;
import com.trackingbus.bean.StudentTracking;
import com.trackingbus.bean.SubscriberBean;
import com.trackingbus.bean.TypingBean;
import com.trackingbus.bean.VechileBean;
import com.trackingbus.bean.VehicleDocBean;
import com.trackingbus.bean.relationshipBean;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Rudiment Webtech solutions
 *
 */
@Controller
public class FrontDahboardController {

	private static final Date FEBRUARY = null;

	private static final Date JANUARY = null;

	private static final Date MARCH = null;

	private static final Date APRIL = null;

	private static final Date MAY = null;

	private static final Date JUNE = null;

	private static final Date JULY = null;

	private static final Date AUGUST = null;

	private static final Date SEPTEMBER = null;

	private static final Date OCTOBER = null;

	private static final Date NOVEMBER = null;

	private static final Date DECEMBER = null;

	@Autowired
	private FrontDashboardService dashboardService;

	@Autowired
	private com.trackingbus.service.WebservicesSer webserviceService;

	@Autowired
	private StudentService studentservice;

	@Autowired
	private SchoolService schoolservice;

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public ModelAndView welcome(
			@ModelAttribute("command") FrontDashboardBean frontdashboardbean,
			BindingResult result,ModelMap model) {
		model.addAttribute("sliders", iterateAllSlider(schoolservice.getAllSlider()));
		model.addAttribute("pages", iterateAllPage(schoolservice.getAllPage(0)));
		model.addAttribute("pages_1", iterateAllPage(schoolservice.getAllPage(1)));
		model.addAttribute("pages_2",schoolservice.getPageById(5));
		model.addAttribute("pages_3", iterateAllPage(schoolservice.getAllPage(3)));
		PageContentModel video=schoolservice.getAboutUs(3);
		model.addAttribute("video", video);
		return new ModelAndView("home/homepage",model);
	}
//	Iterate all pages
	public List<PageBean> iterateAllPage(List<PageModel> page_model)
	{
		List<PageBean> page_bean=null;
		if(page_model!=null &&  !page_model.isEmpty())
		{
			page_bean=new ArrayList<PageBean>();
			for(PageModel p_model : page_model)
			{
				PageBean s_bean=new PageBean();
				s_bean.setPage_id(p_model.getPage_id());
				s_bean.setPage_name(p_model.getPage_name());
				s_bean.setPage_title(p_model.getPage_title());
				s_bean.setPage_desc(p_model.getPage_desc());
				page_bean.add(s_bean);
			}
		}else
		{
			page_bean.add(null);
		}
		return page_bean;
	}
//	 Method for iterate sliders
		public List<SliderBean> iterateAllSlider(List<SliderModel> sliders)
		{
			List<SliderBean> slider_bean=null;
			if(sliders!=null &&  !sliders.isEmpty())
			{
				slider_bean=new ArrayList<SliderBean>();
				for(SliderModel slider : sliders)
				{
					SliderBean s_bean=new SliderBean();
					s_bean.setSlider_id(slider.getSlider_id());
					s_bean.setSlider_type(slider.getSlider_type());
					s_bean.setSlider_image(slider.getSlider_image());
					slider_bean.add(s_bean);
				}
			}else
			{
				slider_bean.add(null);
			}
			return slider_bean;
			
			
		}
	
		@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(	@ModelAttribute("command") FrontDashboardBean frontdashboardbean,
			BindingResult result,ModelMap model , HttpServletRequest request) {
		model.addAttribute("sliders", iterateAllSlider(schoolservice.getAllSlider()));
		model.addAttribute("pages", iterateAllPage(schoolservice.getAllPage(0)));
		model.addAttribute("pages_1", iterateAllPage(schoolservice.getAllPage(1)));
		model.addAttribute("pages_2",schoolservice.getPageById(5));
		model.addAttribute("pages_3", iterateAllPage(schoolservice.getAllPage(3)));
		PageContentModel video=schoolservice.getAboutUs(3);

		
		HttpSession session = request.getSession();
		PageContentModel page_content=schoolservice.getAboutUs(5);
		if(!page_content.getP_name().equals(""))
		{
			session.setAttribute("fb_link", page_content.getP_name());
		}else
		{
			session.setAttribute("fb_link", "");
		}
		if(!page_content.getHeading1().equals(""))
		{
			session.setAttribute("tw_link", page_content.getHeading1());
		}else
		{
			session.setAttribute("tw_link", "");
		}
		if(!page_content.getHeading2().equals(""))
		{
			session.setAttribute("li_link", page_content.getHeading2());
		}else
		{
			session.setAttribute("li_link", "");
		}
//		Get Play store and I-Tunes store link
		PageContentModel page_content1=schoolservice.getAboutUs(6);
		if(!page_content1.getP_name().equals(""))
		{
			session.setAttribute("android_parent_app", page_content1.getP_name());
		}else
		{
			session.setAttribute("android_parent_app", "");
		}
		if(!page_content1.getHeading1().equals(""))
		{
			request.setAttribute("ios_parent_app", page_content1.getHeading1());
		}else
		{
			session.setAttribute("ios_parent_app", "");
		}
		if(!page_content1.getHeading2().equals(""))
		{
			session.setAttribute("android_driver_app", page_content1.getHeading2());
		}else
		{
			request.setAttribute("android_driver_app", "");
		}
		if(!page_content1.getHeading3().equals(""))
		{
			session.setAttribute("android_school_app", page_content1.getHeading3());
		}else
		{
			session.setAttribute("android_school_app", "");
		}
		if(!page_content1.getHeading4().equals(""))
		{
			session.setAttribute("ios_school_app", page_content1.getHeading4());
		}else
		{
			session.setAttribute("ios_school_app", "");
		}

		
		model.addAttribute("video", video);
		return new ModelAndView("home/homepage",model);
	}

	@RequestMapping(value = "/about.html", method = RequestMethod.GET)
	public ModelAndView aboutus(
			@ModelAttribute("command") StudentBean studentbean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		
		PageContentModel page_content=schoolservice.getAboutUs(1);
		model.addAttribute("content", page_content);
		model.addAttribute("heading", "About Us");
		return new ModelAndView("home/about", model);
	}

	
	@RequestMapping(value = "/how_it_works.html", method = RequestMethod.GET)
	public ModelAndView how_it_works() {
		return new ModelAndView("home/how_it_works");
	}

	@RequestMapping(value = "/features.html", method = RequestMethod.GET)
	public ModelAndView feature(@ModelAttribute("command") StudentBean studentbean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		
		model.addAttribute("features", iterateAllFeatures(schoolservice.getAllFeatures()));
		return new ModelAndView("home/feature",model);
	}
	//Iterate all pages
	public List<FeaturesBean> iterateAllFeatures(List<FeaturesModel> feature_model)
	{
		List<FeaturesBean> feature_bean=null;
		if(feature_model!=null &&  !feature_model.isEmpty())
		{
			feature_bean=new ArrayList<FeaturesBean>();
			for(FeaturesModel p_model : feature_model)
			{
				FeaturesBean s_bean=new FeaturesBean();
				s_bean.setFeatures_id(p_model.getFeatures_id());
				s_bean.setTitle(p_model.getTitle());
				s_bean.setContent(p_model.getContent());
				s_bean.setImage_path(p_model.getImage_path());
				s_bean.setCategory_type(p_model.getCategory_type());
				feature_bean.add(s_bean);
			}
		}
		return feature_bean;
	}

	@RequestMapping(value = "/faq.html", method = RequestMethod.GET)
	public ModelAndView faq(@ModelAttribute("command") StudentBean studentbean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		model.addAttribute("faq", iterateAllFaq(schoolservice.getAllFaq()));
		model.addAttribute("heading", "FAQ");
		return new ModelAndView("home/faq");
	}
//	Iterate all pages
	public List<FaqBean> iterateAllFaq(List<FaqModel> faq_model)
	{
		List<FaqBean> feature_bean=null;
		if(faq_model!=null &&  !faq_model.isEmpty())
		{
			feature_bean=new ArrayList<FaqBean>();
			for(FaqModel p_model : faq_model)
			{
				FaqBean s_bean=new FaqBean();
				s_bean.setFaq_id(p_model.getFaq_id());
				s_bean.setCategory(p_model.getCategory());
				s_bean.setQuestion(p_model.getQuestion());
				s_bean.setAnswer(p_model.getAnswer());
				feature_bean.add(s_bean);
			}
		}
		return feature_bean;
	}
	
	@RequestMapping(value = "/help.html", method = RequestMethod.GET)
	public ModelAndView help() {
		return new ModelAndView("home/help");
	}

	@RequestMapping(value = "/contact.html", method = RequestMethod.GET)
	public ModelAndView contact(@ModelAttribute("contact") ContactBean contact,
			BindingResult result,ModelMap model) {
		model.addAttribute("categories", iterateAllCategory(schoolservice.getAllContactCategory()));
		ContactContentModel page_content=schoolservice.getContactContent(1);
		model.addAttribute("content", page_content);
		return new ModelAndView("home/contact",model);
	}
	
	//Method for iterate sliders
			public List<CategoryContactBean> iterateAllCategory(List<CategoryContactModel> categoryModel)
			{
				List<CategoryContactBean> category_bean=null;
				if(categoryModel!=null &&  !categoryModel.isEmpty())
				{
					category_bean=new ArrayList<CategoryContactBean>();
					for(CategoryContactModel category_contact : categoryModel)
					{
						CategoryContactBean s_bean=new CategoryContactBean();
						s_bean.setC_cat_id(category_contact.getC_cat_id());
						s_bean.setCategory_en(category_contact.getCategory_en());
						s_bean.setCategory_ar(category_contact.getCategory_ar());
						category_bean.add(s_bean);
					}
				}else
				{
					category_bean.add(null);
				}
				return category_bean;
				
				
			}
	
	
	@RequestMapping(value = "/contact.html", method = RequestMethod.POST)
	public ModelAndView contact(@ModelAttribute("command") ContactDetailBean contact,
			BindingResult result,ModelMap model) {
		try{
			ContactDetailModel c_model=new ContactDetailModel();
			c_model.setContact_id(null);
			c_model.setCategory(contact.getCategory());
			c_model.setEmail(contact.getEmail());
			c_model.setMessage(contact.getMessage());
			c_model.setName(contact.getName());
			c_model.setPhone_number(contact.getPhone_number());
			
			ApplicationMailer send_email=new ApplicationMailer();
			String msg="Dear Site Admin,";
			msg +="<br>You have recieved new contact enquiry from below details";
			msg +="<table>";
			msg +="<tr><td>Name:</td></td>"+contact.getName()+"</td></tr>";
			msg +="<tr><td>Category:</td></td>"+contact.getCategory()+"</td></tr>";
			msg +="<tr><td>Email:</td></td>"+contact.getEmail()+"</td></tr>";
			msg +="<tr><td>Message:</td></td>"+contact.getMessage()+"</td></tr>";
			msg +="</table>";
			
			AdminSetupModel asm=new AdminSetupModel();
			asm=schoolservice.adminSetup();
			send_email.sendEmail(asm.getEmail_id(), msg, "Contact Enquiry",asm.getPassword());
			//schoolservice.addContactDetails(c_model);
			model.put("success", "Thanks for your message , we will contact you soon ");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		model.addAttribute("categories", iterateAllCategory(schoolservice.getAllContactCategory()));
		ContactContentModel page_content=schoolservice.getContactContent(1);
		model.addAttribute("content", page_content);
		return new ModelAndView("home/contact",model);
	}
	
	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return new ModelAndView("redirect:/index.html");
	}

	@RequestMapping(value = "/getStateByCountry.html", method = RequestMethod.POST)
	public ModelAndView getStateByCountry(
			@ModelAttribute("command") StateBean state, BindingResult result) {
	//	System.out.println(state.getCountry_id());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("states",
				getStates(dashboardService.listStates(state.getCountry_id())));
		return new ModelAndView("home/get_state", model);
	}

	/**
	 * Method for get City by state id
	 * **/
	@RequestMapping(value = "/getCityByState.html", method = RequestMethod.POST)
	public ModelAndView getCityByState(
			@ModelAttribute("command") CityBean city, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("cities",
				getCities(dashboardService.listCity(city.getCountry_id())));
		return new ModelAndView("home/get_city", model);
	}

	/* Method for get state list by country */
	private List<StateBean> getStates(List<StateModel> states) {

		List<StateBean> beans = null;
		if (states != null && !states.isEmpty()) {
			beans = new ArrayList<StateBean>();
			StateBean bean = null;
			for (StateModel state : states) {
				bean = new StateBean();
				bean.setCountry_id(state.getCountry_id());
				bean.setState_id(state.getState_id());
				bean.setState_name(state.getState_name());
				beans.add(bean);
			}
		} else {
	//		System.out.println("empty");
		}
		return beans;
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
		//	System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Method for get Parent by School id
	 * **/
	@RequestMapping(value = "/getParentBySchoolId.html", method = RequestMethod.POST)
	public ModelAndView getParentBySchoolId(
			@ModelAttribute("command") SchoolBean school, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("parents",
				getParents(schoolservice.getParentBySchool(school.getS_id())));
		// model.put("parent",
		// getParents(schoolservice.getParentById(school.getS_id())));
		return new ModelAndView("home/get_parents", model);
	}

	/* Method for get city list by state */
	private List<LoginBean> getParents(List<LoginModel> users) {

		List<LoginBean> beans = null;
		if (users != null && !users.isEmpty()) {
			beans = new ArrayList<LoginBean>();
			LoginBean bean = null;
			for (LoginModel user : users) {
				bean = new LoginBean();
				bean.setUser_id(user.getUser_id());
				bean.setUser_name(user.getUser_name());
				beans.add(bean);
			}
		} else {
		//	System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Method for get City by state id
	 * **/
	@RequestMapping(value = "/studentByRoute.html", method = RequestMethod.POST)
	public ModelAndView studentByRoute(
			@ModelAttribute("command") RouteLatLng routelatlng,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("students", getStudent(dashboardService
				.listRouteStudent(routelatlng.getRoute_id())));
		return new ModelAndView("home/get_route_student", model);
	}

	/* Method for get city list by state */
	private List<RouteLatLng> getStudent(List<RouteLatLngModel> students) {

		List<RouteLatLng> beans = null;
		if (students != null && !students.isEmpty()) {
			beans = new ArrayList<RouteLatLng>();
			RouteLatLng bean = null;
			for (RouteLatLngModel student : students) {
				bean = new RouteLatLng();
				bean.setStudent_id(student.getStudent_id());
				bean.setStudent_name(student.getStudent_name());
				beans.add(bean);
			}
		} else {
			//System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Method for get City by state id
	 * **/
	@RequestMapping(value = "/addressByStudent.html", method = RequestMethod.POST)
	public ModelAndView addressByStudent(
			@ModelAttribute("command") RouteLatLng routelatlng,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		RouteLatLngModel l = dashboardService.getStudentAddress(routelatlng
				.getStudent_id());
		model.put("address",
				dashboardService.getStudentAddress(routelatlng.getStudent_id()));
		//System.out.println(l.getAddress());
		return new ModelAndView("home/get_student_address", model);
	}

	/**
	 * Method for get City by state id
	 * **/
	@RequestMapping(value = "/getStudentParent.html", method = RequestMethod.POST)
	public ModelAndView getStudentParent(
			@ModelAttribute("command") StudentBean studentbean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		StudentModel stud = studentservice.getStudentById(studentbean
				.getStudent_id());
		model.put("students", stud);
		return new ModelAndView("home/get_student_parent", model);
	}

	/**
	 * Method for get parent by parent id
	 * **/
	@RequestMapping(value = "/getParentById.html", method = RequestMethod.POST)
	public ModelAndView getParentById(
			@ModelAttribute("command") LoginBean user, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		LoginModel parent = dashboardService.getParentById(user.getUser_id());
		model.put("parent", parent);
		model.put("student_id", user.getSchool_id());
		return new ModelAndView("home/get_parent_by_id", model);
	}

	/**
	 * Method for get parent by parent id
	 * **/
	@RequestMapping(value = "/getAllParentByPId.html", method = RequestMethod.POST)
	public ModelAndView getAllParentByPId(
			@ModelAttribute("command") LoginBean user, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("allParent", getParentList(schoolservice
				.getParentByPIdList(user.getP_status())));
		model.put("relationship",getRelationshipList(schoolservice.listRelationship()));
		model.put("student_details", studentservice.getStudentById(user.getSchool_id()));
		return new ModelAndView("home/get_allparent_by_id", model);
	}
	
	/**
	 * Method for get parent by parent id
	 * **/
	@RequestMapping(value = "/getAllParentByPIdNew.html", method = RequestMethod.POST)
	public ModelAndView getAllParentByPIdNew(
			@ModelAttribute("command") LoginBean user, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("allParent", getParentList(schoolservice
				.getParentByPIdList(user.getP_status())));
		model.put("relationship",getRelationshipList(schoolservice.listRelationship()));
		return new ModelAndView("home/get_allparent_by_id_new", model);
	}
	/* Method for get Student list */
	private List<relationshipBean> getRelationshipList(List<relationshipModel> relationships) {

		List<relationshipBean> beans = null;
		if (relationships != null && !relationships.isEmpty()) {
			beans = new ArrayList<relationshipBean>();
			relationshipBean bean = null;
			for (relationshipModel rel : relationships) {
				bean = new relationshipBean();
				bean.setR_id(rel.getR_id());
				bean.setR_title(rel.getR_title());
				beans.add(bean);
			}
		} else {
			//System.out.println("empty");
		}
		return beans;
	}
	/* Method for get country list */
	private List<LoginBean> getParentList(List<LoginModel> users) {

		List<LoginBean> beans = null;
		if (users != null && !users.isEmpty()) {
			beans = new ArrayList<LoginBean>();
			LoginBean bean = null;
			for (LoginModel user : users) {
				bean = new LoginBean();
				bean.setUser_id(user.getUser_id());
				bean.setP_status(user.getP_status());
				bean.setFirst_name(user.getFirst_name());
				bean.setLast_name(user.getLast_name());
				bean.setFamily_name(user.getFamily_name());
				bean.setMiddle_name(user.getMiddle_name());
				bean.setUser_name(user.getUser_name());
				bean.setUser_email(user.getUser_email());
				bean.setContact_number(user.getContact_number());
				beans.add(bean);
			}
		} else {
		//	System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Method for get route latlng by route id
	 * **/
	@RequestMapping(value = "/getRouteLatLng.html", headers = "Accept=*/*", method = RequestMethod.POST)
	@ResponseBody
	public String getRouteLatLng(@ModelAttribute("command") RouteLatLng latlng,
			BindingResult result) {
		String jsonResult = "";
		try {
			JSONObject jsonArray = new JSONObject();
		//	System.out.println("Route=" + latlng.getRoute_id());
			jsonArray.put("latlng", getLatLngtList(schoolservice.getRouteLatLng(latlng.getRoute_id())));
			jsonResult = jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;
	}

	/* Method for get country list */

	private List<RouteLatLng> getLatLngtList(List<RouteLatLngModel> latlng) {

		List<RouteLatLng> beans = null;
		if (latlng != null && !latlng.isEmpty()) {
			beans = new ArrayList<RouteLatLng>();
			RouteLatLng bean = null;
			for (RouteLatLngModel user : latlng) {
				bean = new RouteLatLng();
				/*
				 * bean.setId(user.getId()); bean.setAddress(user.getAddress());
				 */
				bean.setLat(user.getLat());
				bean.setLng(user.getLng());
				beans.add(bean);
			}
		} else {
	//		System.out.println("empty");
		}
		return beans;
	}

	/*
	 * private JSONArray getLatLngtList(List<RouteLatLngModel> latlng){
	 * JSONArray jsonarr=new JSONArray(); List<RouteLatLng> beans = null;
	 * if(latlng != null && !latlng.isEmpty()){ beans = new
	 * ArrayList<RouteLatLng>(); RouteLatLng bean = null;
	 * 
	 * try{ for(RouteLatLngModel user : latlng){ JSONStringer jsonStr=new
	 * JSONStringer(); jsonStr.object().key("lat").value(ltlng.getLat())
	 * .key("lng").value(ltlng.getLng()).endObject(); jsonarr.put(jsonStr);
	 * bean.setId(user.getId()); bean.setAddress(user.getAddress());
	 * bean.setLat(user.getLat()); bean.setLng(user.getLng()); beans.add(bean);
	 * } }catch(JSONException e) { e.printStackTrace(); //System.out.println(e);
	 * } } else { System.out.println("empty"); } return jsonarr; }
	 */
	/**
	 * Method for get route by route id
	 * 
	 **/
	@RequestMapping(value = "/getRouteSourceDestination.html", headers = "Accept=*/*", method = RequestMethod.POST)
	@ResponseBody
	public String getRouteSourceDestination(
			@ModelAttribute("command") RouteLatLng latlng, BindingResult result) {
		String jsonResult = "";
		try {
			JSONObject jsonArray = new JSONObject();
			RouteModel r = new RouteModel();
			r = schoolservice.getRouteById(latlng.getRoute_id());
			jsonArray.put("slat", r.getSource_lat());
			jsonArray.put("slng", r.getSource_lng());
			jsonArray.put("dlat", r.getDestination_lat());
			jsonArray.put("dlng", r.getDestination_lng());
			jsonArray.put("source", r.getSource());
			jsonArray.put("destination", r.getDestination());
			jsonResult = jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;
	}

	/**
	 * Method for get City by country id for student
	 * **/
	@RequestMapping(value = "/getStudentCity.html", method = RequestMethod.POST)
	public ModelAndView getStudentCity(
			@ModelAttribute("command") CityBean city, BindingResult result) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("city_id", city.getCity_id());
		model.put("cities",
				getCities(dashboardService.listCity(city.getCountry_id())));
		return new ModelAndView("home/get_student_city", model);
	}

	/**
	 * Method for get driver by route
	 * **/
	@RequestMapping(value = "/getDriverByRoute.html", method = RequestMethod.POST)
	public ModelAndView getDriverByRoute(
			@ModelAttribute("command") RouteBean route, DriverBean driver,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("route_id", driver.getRoute_id());
		model.put("driver_id", driver.getDriver_id());
		model.put(
				"drivers",
				getDrivers(dashboardService.driverByRoute(driver.getRoute_id())));
		return new ModelAndView("home/get_driver_by_route", model);
	}

	/* Method for get city list by state */
	@SuppressWarnings("unused")
	private List<DriverBean> getDrivers(List<DriverModel> drivers) {

		List<DriverBean> beans = null;
		if (drivers != null && !drivers.isEmpty()) {
			beans = new ArrayList<DriverBean>();
			DriverBean bean = null;
			for (DriverModel driver : drivers) {
				bean = new DriverBean();
				bean.setD_email(driver.getD_email());
				bean.setDriver_id(driver.getDriver_id());
				;
				bean.setDriver_fname(driver.getDriver_fname());
				bean.setDriver_lname(driver.getDriver_lname());
				beans.add(bean);
			}
		} else {
		//	System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Method for get student tracking latlng by student id
	 * **/
	@RequestMapping(value = "/getStudentTrackLatLng.html", headers = "Accept=*/*", method = RequestMethod.POST)
	@ResponseBody
	public String getStudentTrackLatLng(
			@ModelAttribute("command") StudentTracking tracking,
			BindingResult result) {
		String jsonResult = "";
		try {
			JSONObject jsonArray = new JSONObject();

			jsonArray.put("details", getTrackList(studentservice
					.getStudentTracking(tracking.getStudent_id())));
			jsonArray.put(
					"size_l",
					getTrackList(
							studentservice.getStudentTracking(tracking
									.getStudent_id())).size() - 1);
			jsonResult = jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;
	}

	/**
	 * Method for get student tracking latlng by student id
	 * **/
	@RequestMapping(value = "/trackSingleTripCount.html", headers = "Accept=*/*", method = RequestMethod.POST)
	@ResponseBody
	public String trackSingleTripCount(
			@ModelAttribute("command") StudentTracking tracking,
			BindingResult result) {
		String jsonResult = "";
		try {
			JSONObject jsonArray = new JSONObject();

			// jsonArray.put("details",getTrackList(studentservice.getStudentTracking(tracking.getStudent_id())));
			jsonArray.put(
					"size_l",
					getTrackList(
							studentservice.getStudentTracking(tracking
									.getStudent_id())).size());
			jsonResult = jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;
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
		//	System.out.println("No Data Available");
		}
		return beans;
	}

	/**
	 * Method for get holiday by month and school
	 * **/
	@RequestMapping(value = "/getHolidayByMonth.html", method = RequestMethod.POST)
	public ModelAndView getHolidayByMonth(
			@ModelAttribute("command") HolidayBean holiday, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();

		model.put(
				"holidays",
				getHolidays(dashboardService.getHolidayByMonth(
						holiday.getMonth(), holiday.getYear(),
						holiday.getSchool_id())));
		return new ModelAndView("home/get_holiday_ajax", model);
	}
	
	@RequestMapping(value = "/getHolidayByMonthNew.html", method = RequestMethod.POST)
	public ModelAndView getHolidayByMonthNew(
			@ModelAttribute("command") HolidayBean holiday, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		
		String month="";
		System.out.println("Month ="+holiday.getDay());
		if(holiday.getDay().equals("January"))
		{
			month="01";
			
		}else if (holiday.getDay().equals("February")) {
			month="02";
			
		}else if (holiday.getDay().equals("March")) {
			month="03";
			
		}else if (holiday.getDay().equals("April")) {
			month="04";
		
		}else if (holiday.getDay().equals("May")) {
			month="05";
			
		}else if (holiday.getDay().equals("June")) {
			month="06";
		}else if (holiday.getDay().equals("July")) {
			month="07";
		
		}else if (holiday.getDay().equals("August")) {
			month="08";
			
		}else if (holiday.getDay().equals("September")) {
			month="09";
		
		}else if (holiday.getDay().equals("October")) {
			month="10";
			
		}else if (holiday.getDay().equals("November")) {
			month="11";
			
		}else {
			month="12";
			
		}
		model.put(
				"holidays",
				getHolidays(dashboardService.getHolidayByMonth(
						Integer.parseInt(month), holiday.getYear(),
						holiday.getSchool_id())));
		return new ModelAndView("home/get_holiday_ajax", model);
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
		//	System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Method for get parent by parent id
	 * **/
	@RequestMapping(value = "/getSchoolAdminById.html", headers = "Accept=*/*", method = RequestMethod.POST)
	@ResponseBody
	public String getSchoolAdminById(@ModelAttribute("command") LoginBean user,
			BindingResult result) {
		String jsonResult = "";
		try {
			JSONObject jsonArray = new JSONObject();
			LoginModel u = dashboardService.getParentById(user.getUser_id());
			LoginModel u_d = new LoginModel();
			jsonArray.put("first_name", u.getFirst_name());
			jsonArray.put("last_name", u.getLast_name());
			jsonArray.put("contact_no", u.getContact_number());
			jsonArray.put("email", u.getUser_email());

			jsonResult = jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;

	}

	/**
	 * Method for get Driver tracking latlng by student id
	 * **/
	@RequestMapping(value = "/getDriverTrackLatLng.html", headers = "Accept=*/*", method = RequestMethod.POST)
	@ResponseBody
	public String getDriverTrackLatLng(
			@ModelAttribute("command") StudentBean studentbean,
			BindingResult result) {
		String jsonResult = "";
		try {
			JSONObject jsonArray = new JSONObject();
			// model.put("students",getDriverTrackList(studentservice.getDriverTrack(studentModel.getS_route_id()))
			// );
			// model.put("size_l",
			// getDriverTrackList(studentservice.getDriverTrack(studentModel.getS_route_id())).size()-1);

			jsonArray.put("details", getDriverTrackList(studentservice
					.getDriverTrack(studentbean.getS_route_id())));
			jsonArray.put("lat_lng", getDriverTrackList(studentservice
					.getDriverTrackLimit(studentbean.getS_route_id())));
			jsonArray.put("size_l",
					studentservice.getDriverTrack(studentbean.getS_route_id())
							.size() - 1);
			jsonResult = jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;
	}

	/* Method for route latlng */
	private List<DriverTrack> getDriverTrackList(
			List<DriverTrackModel> tracklist) {
		List<DriverTrack> beans = null;
		if (tracklist != null && !tracklist.isEmpty()) {
			beans = new ArrayList<DriverTrack>();
			DriverTrack bean = null;
			for (DriverTrackModel t_list : tracklist) {
			//	System.out.println("Test LATLNG="+t_list.getLat());
				bean = new DriverTrack();
				bean.setRoute_id(t_list.getRoute_id());
				bean.setLng(t_list.getLng());
				bean.setLat(t_list.getLat());
				bean.setTrack_date(t_list.getTrack_date());
				bean.setTrack_id(t_list.getTrack_id());
				bean.setSpeed(t_list.getSpeed());
				beans.add(bean);
			}
		} else {
		//	System.out.println("No Data Available");
		}
		return beans;
	}

	/**
	 * Method for get student tracking latlng by student id
	 * **/
	@RequestMapping(value = "/trackSingleRouteCount.html", headers = "Accept=*/*", method = RequestMethod.POST)
	@ResponseBody
	public String trackSingleRouteCount(
			@ModelAttribute("command") StudentBean studentbean,
			BindingResult result) {
		String jsonResult = "";
		try {
			JSONObject jsonArray = new JSONObject();
		//	System.out.println(studentbean.getS_route_id());
			// jsonArray.put("details",getTrackList(studentservice.getStudentTracking(tracking.getStudent_id())));
			jsonArray.put("size_l",
					studentservice.getDriverTrack(studentbean.getS_route_id())
							.size());
			jsonResult = jsonArray.toString();
		} catch (Exception e) {
		//	System.out.println("Not Available");
		}

		return jsonResult;
	}

	/**
	 * Method for get driver by route
	 * **/
	@RequestMapping(value = "/getSchoolAdminBySchoolId.html", method = RequestMethod.POST)
	@ResponseBody
	public String getSchoolAdminBySchoolId(
			@ModelAttribute("command") RouteBean route, LoginBean login,
			BindingResult result, HttpServletRequest request) {
		String jsonResult = "";
		try {
			HttpSession session = request.getSession();
			int user_id = (Integer) session.getAttribute("user_id");
			JSONObject jsonArray = new JSONObject();

			jsonArray
					.put("school_admin",
							getSchoolAdmin(schoolservice.listSchoolAdmin(login
									.getSchool_id()), user_id));
			jsonResult = jsonArray.toString();
		} catch (Exception e) {
		//	System.out.println("Not Available");
		}

		return jsonResult;
	}

	/**
	 * Method for get message by id of School Admin
	 **/
	@RequestMapping(value = "/getMessageByUserId.html", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getMessageByUserId(
			@ModelAttribute("command") LoginBean login, BindingResult result,
			HttpServletRequest request) {
		String jsonResult = "";
		try {
			HttpSession session = request.getSession();
			JSONObject jsonArray = new JSONObject();
			int user_id = (Integer) session.getAttribute("user_id");
			jsonArray.put("message", getUserMessageList(schoolservice
					.getAllMessage(user_id, login.getUser_id()),request));
			jsonArray.put("count",
					schoolservice.getAllMessage(user_id, login.getUser_id())
							.size());
			LoginModel user_details = schoolservice.getParentById(login
					.getUser_id());
			SchoolModel schoolinfo = (SchoolModel) schoolservice
					.getSchoolById(user_details.getSchool_id());
			jsonArray.put("first_name", user_details.getFirst_name());
			//jsonArray.put("first_name", "التركيز");
			jsonArray.put("last_name", user_details.getLast_name());
			jsonArray.put("logo", schoolinfo.getSchool_logo());
			jsonArray.put("logged_f_name", session.getAttribute("f_name"));
			jsonArray.put("logged_l_name", session.getAttribute("l_name"));
			int reciever_id = user_id;
			int sender_id = login.getUser_id();
			//schoolservice.updateNotification(reciever_id, sender_id);
			int update_count=schoolservice.updateNotificationCount(reciever_id, sender_id);
			jsonArray.put("update_count", update_count);
			jsonResult = jsonArray.toString();
		} catch (Exception e) {
		//	System.out.println("Not Available");
		}

		return jsonResult;
	}
	
	/**
	 * Method for get message by id of School Admin
	 **/
	@RequestMapping(value = "/getMessageByAdminId.html",  method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getMessageByAdminId(
			@ModelAttribute("command") LoginBean login, BindingResult result,
			HttpServletRequest request) {
		String jsonResult = "";
		try {
			HttpSession session = request.getSession();
			JSONObject jsonArray = new JSONObject();
			int user_id = (Integer) session.getAttribute("user_id");
			jsonArray.put("message", getUserMessageList(schoolservice
					.getAllMessage(user_id, login.getUser_id()),request));
			jsonArray.put("count",
					schoolservice.getAllMessage(user_id, login.getUser_id())
							.size());
			LoginModel user_details = schoolservice.getParentById(login
					.getUser_id());
			LoginModel user_details1 = schoolservice.getParentById(user_id);
			SchoolModel schoolinfo = (SchoolModel) schoolservice
					.getSchoolById(user_details1.getSchool_id());
			jsonArray.put("first_name", user_details.getFirst_name());
			jsonArray.put("last_name", user_details.getLast_name());
			jsonArray.put("logo", schoolinfo.getSchool_logo());
			jsonArray.put("logged_f_name", session.getAttribute("f_name"));
			jsonArray.put("logged_l_name", session.getAttribute("l_name"));

			int reciever_id = user_id;
			int sender_id = login.getUser_id();
			//schoolservice.updateNotificationCount(reciever_id, sender_id);
			int i=schoolservice.updateNotificationCount(reciever_id, sender_id);
			jsonArray.put("update_count", i);
			

			jsonResult = jsonArray.toString();
			//jsonResult=new String(jsonResult.getBytes("UTF-8"), "UTF-8");
		} catch (Exception e) {
			// System.out.println("No Data Available");
		}

		return jsonResult;
	}

	/* Method for get country list */
	private List<SchoolMessageBean> getUserMessageList(
			List<SchoolMessage> messages,HttpServletRequest request) throws UnsupportedEncodingException {

		List<SchoolMessageBean> beans = null;
		if (messages != null && !messages.isEmpty()) {
			beans = new ArrayList<SchoolMessageBean>();
			SchoolMessageBean bean = null;
			for (SchoolMessage message : messages) {
				bean = new SchoolMessageBean();
				bean.setMsg_id(message.getMsg_id());
				bean.setReciever_id(message.getReciever_id());
				bean.setSender_id(message.getSender_id());
				bean.setSender(message.getSender());
				bean.setMsg(message.getMsg());
				//bean.setMsg(new String(message.getMsg().getBytes("UTF-8")));
				//bean.setMsg(message.getMsg().getBytes("UTF-8").toString());
				//jsonResult=new String(jsonResult.getBytes("UTF-8"), "UTF-8");
				//System.out.println("Messages is here="+new String(message.getMsg().getBytes("UTF-8")));
				String new_time="";
				try{
					
				JodaTimeExample jtx=new JodaTimeExample();
				
				Cookie[] cookies = request.getCookies();
				String time_zone="Asia/Riyadh";
				if (cookies != null) {
				 for (Cookie cookie : cookies) {
				   if (cookie.getName().equals("time_zone")) {
					   time_zone=cookie.getValue();
				    }
				  }
				}
				
				
				
				
		    	new_time=jtx.getTime(message.getTime(), time_zone);
		    	}catch(Exception e)
				{
					System.out.println(e);
				}
				//bean.setTime(message.getTime());
				bean.setTime(new_time);
				bean.setStatus(message.getStatus());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}
	
	
	
	@RequestMapping(value = "/setTimeZoneInCookies.html", method = RequestMethod.POST)
	@ResponseBody
	public void setTimeZoneInCookies(
			@ModelAttribute("command") SchoolMessageBean msg,
			BindingResult result, HttpServletRequest request,HttpServletResponse response) {
			
		Cookie myCookie = new Cookie("time_zone", msg.getName());
		response.addCookie(myCookie);
		
	}
		
	
	
	
	
	/**
	 * Method for get message by id of School Admin
	 **/
	@RequestMapping(value = "/sendMessageByUserId.html", method = RequestMethod.POST)
	@ResponseBody
	public String sendMessageByUserId(
			@ModelAttribute("command") SchoolMessageBean msg,
			BindingResult result, HttpServletRequest request) {
		String jsonResult = "";
		try {
			SchoolMessage msgModel = new SchoolMessage();
			msgModel.setMsg(msg.getMsg());
			msgModel.setReciever_id(msg.getReciever_id());
			msgModel.setSender(msg.getSender());
			msgModel.setStatus(msg.getStatus());
			msgModel.setSender_id(msg.getSender_id());
			msgModel.setSchool_id(0);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			msgModel.setTime(dateFormat.format(date));
			schoolservice.sendMessageById(msgModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;
	}

	/* Method for get country list */
	private List<LoginBean> getSchoolAdmin(List<LoginModel> users, int user_id) {

		List<LoginBean> beans = null;
		if (users != null && !users.isEmpty()) {
			beans = new ArrayList<LoginBean>();
			LoginBean bean = null;
			for (LoginModel user : users) {
				bean = new LoginBean();
				bean.setUser_id(user.getUser_id());
				int count = schoolservice.getAllMessageCount(user_id,
						user.getUser_id()).size();
				bean.setP_status(count);
				bean.setFirst_name(user.getFirst_name());
				bean.setLast_name(user.getLast_name());
				bean.setUser_name(user.getUser_name());
				bean.setUser_email(user.getUser_email());
				bean.setContact_number(user.getContact_number());
				beans.add(bean);
			}
		} else {
		//	System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Method for get message by id of School Admin
	 **/
	@RequestMapping(value = "/getMessageByParentId.html", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getMessageByParentId(
			@ModelAttribute("command") LoginBean login, BindingResult result,
			HttpServletRequest request) {
		String jsonResult = "";
		try {
			HttpSession session = request.getSession();
			JSONObject jsonArray = new JSONObject();
			int user_id = (Integer) session.getAttribute("user_id");
			jsonArray.put("message", getParentMessageList(schoolservice
					.getParentAllMessage(user_id, login.getUser_id()),request));
			jsonArray.put(
					"count",
					schoolservice.getParentAllMessage(user_id,
							login.getUser_id()).size());
			LoginModel user_details = schoolservice.getParentById(login
					.getUser_id());
			LoginModel user_details1 = schoolservice.getParentById(user_id);
			SchoolModel schoolinfo = (SchoolModel) schoolservice
					.getSchoolById(user_details1.getSchool_id());
			jsonArray.put("first_name", user_details.getFirst_name());
			jsonArray.put("last_name", user_details.getLast_name());
			jsonArray.put("logo", schoolinfo.getSchool_logo());
			jsonArray.put("logged_f_name", session.getAttribute("f_name"));
			jsonArray.put("logged_l_name", session.getAttribute("l_name"));

			int reciever_id = user_id;
			int sender_id = login.getUser_id();
			//schoolservice.updateParentNotification(reciever_id, sender_id);
			int update_count=schoolservice.updateParentNotificationCount(reciever_id, sender_id);
			jsonArray.put("update_count",update_count);
			
			jsonResult = jsonArray.toString();
		} catch (Exception e) {
			// System.out.println("No Data Available");
		}

		return jsonResult;
	}

	/* Method for get country list */
	private List<SchoolMessageBean> getParentMessageList(
			List<ParentMessage> messages,HttpServletRequest request) {

		List<SchoolMessageBean> beans = null;
		if (messages != null && !messages.isEmpty()) {
			beans = new ArrayList<SchoolMessageBean>();
			SchoolMessageBean bean = null;
			for (ParentMessage message : messages) {
				bean = new SchoolMessageBean();
				bean.setMsg_id(message.getMsg_id());
				bean.setReciever_id(message.getReciever_id());
				bean.setSender_id(message.getSender_id());
				bean.setSender(message.getSender());
				bean.setMsg(message.getMsg());
				
				String new_time="";
				try{
				JodaTimeExample jtx=new JodaTimeExample();
				Cookie[] cookies = request.getCookies();
				String time_zone="Asia/Riyadh";
				if (cookies != null) {
				 for (Cookie cookie : cookies) {
				   if (cookie.getName().equals("time_zone")) {
					   time_zone=cookie.getValue();
				    }
				  }
				}
				new_time=jtx.getTime(message.getTime(), time_zone);
				}catch(Exception e)
				{
					System.out.println(e);
				}
				bean.setTime(new_time);
				//bean.setTime(message.getTime());
				bean.setStatus(message.getStatus());
				beans.add(bean);
			}
		} else {
		//	System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Method for get message by id of School Admin
	 **/
	@RequestMapping(value = "/sendMessageToParent.html", method = RequestMethod.POST)
	@ResponseBody
	public String sendMessageToParent(
			@ModelAttribute("command") SchoolMessageBean msg,
			BindingResult result, HttpServletRequest request) {
		String jsonResult = "";
		try {
			JodaTimeExample jtx=new JodaTimeExample();
			ParentMessage msgModel = new ParentMessage();
			msgModel.setMsg(msg.getMsg());
			msgModel.setReciever_id(msg.getReciever_id());
			msgModel.setSender(msg.getSender());
			msgModel.setStatus(msg.getStatus());
			msgModel.setSender_id(msg.getSender_id());
			msgModel.setSchool_id(0);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			msgModel.setTime(dateFormat.format(date));
			schoolservice.sendMessageToParent(msgModel);
			// Send push notification end here
			LoginModel parent = schoolservice.getParentById(msg
					.getReciever_id());
			String device_token = parent.getDevice_token();
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2 = new Date();
			String date3 = dateFormat1.format(date2);
			String desc = msg.getMsg();
			NotificationModel noti = new NotificationModel();
			noti.setParent_id(msg.getReciever_id());

			noti.setDate(date3);
			noti.setNoti_desc(desc);
			noti.setRoute_id(0);
			noti.setStudent_id(0);
			noti.setNoti_id(null);
			noti.setNoti_type("Message");

			// int noti_id = webserviceService.insertNotification(noti);
			int noti_id = 0;
			String msg1 = desc;

			JSONObject jsonObjectnew = new JSONObject();
			jsonObjectnew.put("msg", msg1);
			jsonObjectnew.put("noti_id", Integer.toString(noti_id));
			jsonObjectnew.put("date", date3);
			jsonObjectnew.put("noti_type", "chat");
			Gcm g = new Gcm();
			
			
			if(device_token.length()==64)
			{
				
				ApplePushNotification ap=new ApplePushNotification();
				ap.pushMessage(device_token,jsonObjectnew.toString(),msg1,parent.getChat_sound());
			}else
			{
				g.pushNotificationToGCM(parent.getDevice_token(),
						jsonObjectnew.toString());
			}
			
			// Send push notification end here

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;
	}

	/**
	 * Method for get message by id of School Driver
	 **/
	@RequestMapping(value = "/getMessageByDriverId.html", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getMessageByDriverId(
			@ModelAttribute("command") DriverBean login, BindingResult result,
			HttpServletRequest request) {
		String jsonResult = "";
		try {
			HttpSession session = request.getSession();
			JSONObject jsonArray = new JSONObject();
			int user_id = (Integer) session.getAttribute("user_id");
			jsonArray.put("message", getDriverMessageList(schoolservice
					.getDriverAllMessage(user_id, login.getDriver_id()),request));
			jsonArray.put(
					"count",
					schoolservice.getDriverAllMessage(user_id,
							login.getDriver_id()).size());
			/*
			 * LoginModel user_details = schoolservice.getParentById(login
			 * .getDriver_id());
			 */
			DriverModel user_details = schoolservice.getDriverById(login
					.getDriver_id());
			LoginModel user_details1 = schoolservice.getParentById(user_id);
			SchoolModel schoolinfo = (SchoolModel) schoolservice
					.getSchoolById(user_details1.getSchool_id());
			jsonArray.put("first_name", user_details.getDriver_fname());
			jsonArray.put("last_name", user_details.getDriver_lname());
			jsonArray.put("logo", schoolinfo.getSchool_logo());
			jsonArray.put("logged_f_name", session.getAttribute("f_name"));
			jsonArray.put("logged_l_name", session.getAttribute("l_name"));

			int reciever_id = user_id;
			int sender_id = login.getDriver_id();
			//schoolservice.updateDriverNotification(reciever_id, sender_id);
			int i=schoolservice.updateDriverNotificationCount(reciever_id, sender_id);
			jsonArray.put("update_count", i);
			jsonResult = jsonArray.toString();
		} catch (Exception e) {
			// System.out.println("No Data Available");
		}

		return jsonResult;
	}

	/* Method for get country list */
	private List<SchoolMessageBean> getDriverMessageList(
			List<DriverMessage> messages,HttpServletRequest request) {

		List<SchoolMessageBean> beans = null;
		if (messages != null && !messages.isEmpty()) {
			beans = new ArrayList<SchoolMessageBean>();
			SchoolMessageBean bean = null;
			for (DriverMessage message : messages) {
				bean = new SchoolMessageBean();
				bean.setMsg_id(message.getMsg_id());
				bean.setReciever_id(message.getReciever_id());
				bean.setSender_id(message.getSender_id());
				bean.setSender(message.getSender());
				bean.setMsg(message.getMsg());
				String new_time="";
				try{
				JodaTimeExample jtx=new JodaTimeExample();
				Cookie[] cookies = request.getCookies();
				String time_zone="Asia/Riyadh";
				if (cookies != null) {
				 for (Cookie cookie : cookies) {
				   if (cookie.getName().equals("time_zone")) {
					   time_zone=cookie.getValue();
				    }
				  }
				}
				new_time=jtx.getTime(message.getTime(), time_zone);
				}catch(Exception e)
				{
					System.out.println(e);
				}
				//bean.setTime(message.getTime());
				bean.setTime(new_time);
				bean.setStatus(message.getStatus());
				beans.add(bean);
			}
		} else {
		//	System.out.println("empty");
		}
		return beans;
	}

	/**
	 * Method for get message by id of School Admin
	 **/
	@RequestMapping(value = "/sendMessageToDriver.html", method = RequestMethod.POST)
	@ResponseBody
	public String sendMessageToDriver(
			@ModelAttribute("command") SchoolMessageBean msg,
			BindingResult result, HttpServletRequest request) {
		String jsonResult = "";
		try {
			DriverMessage msgModel = new DriverMessage();
			msgModel.setMsg(msg.getMsg());
			msgModel.setReciever_id(msg.getReciever_id());
			msgModel.setSender(msg.getSender());
			msgModel.setStatus(msg.getStatus());
			msgModel.setSender_id(msg.getSender_id());
			msgModel.setSchool_id(0);
			msgModel.setU_check("");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			msgModel.setTime(dateFormat.format(date));
			schoolservice.sendMessageToDriver(msgModel);

			JSONObject jsonObjectnew = new JSONObject();
			jsonObjectnew.put("msg", msg.getMsg());
			jsonObjectnew.put("noti_id", "");
			jsonObjectnew.put("date", dateFormat.format(date));
			jsonObjectnew.put("noti_type", "chat");
			Gcm g = new Gcm();

			DriverModel driver = schoolservice.getDriverById(msg
					.getReciever_id());
			if(driver.getDevice_token().length()==64)
			{
				
				ApplePushNotification ap=new ApplePushNotification();
				ap.pushMessage(driver.getDevice_token(),jsonObjectnew.toString(), msg.getMsg(),1);
			}else
			{
				g.pushNotificationToGCM(driver.getDevice_token(),jsonObjectnew.toString());
			}
			//g.pushNotificationToGCM(driver.getDevice_token(),jsonObjectnew.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;
	}

	/**
	 * Method for remove insurance card copy
	 **/
	@RequestMapping(value = "/removeCardCopy.html", method = RequestMethod.POST)
	@ResponseBody
	public String removeCardCopy(@ModelAttribute("command") VechileBean vBean,
			BindingResult result, HttpServletRequest request) {
		String jsonResult = "";
		try {
			int vechile_id = vBean.getVechile_id();
			String insurance_card_copy = vBean.getColor();
			schoolservice.removeInsuranceCardCopy(vechile_id,
					insurance_card_copy);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;
	}

	/**
	 * Method for send to School Admin by Parent
	 **/
	@RequestMapping(value = "/sendMessageToSchoolAdminByParent.html", method = RequestMethod.POST)
	@ResponseBody
	public String sendMessageToSchoolAdminByParent(
			@ModelAttribute("command") SchoolMessageBean msg,
			BindingResult result, HttpServletRequest request) {
		String jsonResult = "";
		try {
			ParentMessage msgModel = new ParentMessage();
			msgModel.setMsg(msg.getMsg());
	//		System.out.println(msg.getMsg());
			msgModel.setReciever_id(msg.getReciever_id());
			msgModel.setSender(msg.getSender());
			msgModel.setStatus(msg.getStatus());
			msgModel.setSender_id(msg.getSender_id());
			msgModel.setSchool_id(0);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			msgModel.setTime(dateFormat.format(date));
			schoolservice.sendMessageToParent(msgModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;
	}

	/**
	 * Method for send to School Admin by Parent
	 **/
	@RequestMapping(value = "/saveGeoFancing.html", method = RequestMethod.POST)
	@ResponseBody
	public String saveGeoFancing(@ModelAttribute("command") RouteBean route,
			BindingResult result, HttpServletRequest request) {
		String jsonResult = "";
		try {
			RouteModel routemodel = new RouteModel();
			routemodel.setRadius(route.getRadius());
			schoolservice.saveGeoFancing(route.getRoute_id(), routemodel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;
	}

	/**
	 * @access public Method for check route is assigned or not
	 **/
	@RequestMapping(value = "/checkAssigedRoute.html", headers = "Accept=*/*", method = RequestMethod.POST)
	@ResponseBody
	public String checkAssigedRoute(
			@ModelAttribute("command") RouteLatLng latlng, BindingResult result) {
		String jsonResult = "";
		try {
			JSONObject jsonArray = new JSONObject();
			DriverModel r = new DriverModel();
			r = schoolservice.checkAssigedRoute(latlng.getRoute_id());
			if (r != null) {
				jsonArray.put("status", 1);
			} else {
				jsonArray.put("status", 0);
			}

			jsonResult = jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;
	}

	/**
	 * @access public Method for remove vehicle document
	 **/
	@RequestMapping(value = "/deleteVehicleDocument.html", method = RequestMethod.POST)
	@ResponseBody
	public void deleteVehicleDocument(
			@ModelAttribute("command") VehicleDocBean vBean,
			BindingResult result) {
		int v_doc_id = vBean.getV_doc_id();
		schoolservice.deleteVehicleDocument(v_doc_id);
	}

	/**
	 * @access public Method for remove driver document
	 **/
	@RequestMapping(value = "/deleteDriverDocument.html", method = RequestMethod.POST)
	@ResponseBody
	public void deleteDriverDocument(
			@ModelAttribute("command") DriverDocBean vBean, BindingResult result) {
		try {
			int v_doc_id = vBean.getV_doc_id();
			schoolservice.deleteDriverDocument(v_doc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @access public Method for remove driver document
	 **/
	@RequestMapping(value = "/getCountryById.html", method = RequestMethod.POST)
	@ResponseBody
	public String getCountryById(@ModelAttribute("command") CountryBean vBean,
			BindingResult result) {
		String jsonResult = "";
		try {
			int v_doc_id = vBean.getC_id();
			CountryModel c = schoolservice.getCountryById(v_doc_id);

			JSONObject jsonArray = new JSONObject();
			jsonArray.put("code", c.getC_code());
			jsonArray.put("country", c.getC_name());
			jsonResult = jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonResult;
	}

	/**
	 * @access public Method for remove driver document
	 **/
	@RequestMapping(value = "/parentToSchoolAdminTyping.html", method = RequestMethod.POST)
	@ResponseBody
	public void parentToSchoolAdminTyping(
			@ModelAttribute("command") TypingBean tBean, BindingResult result,
			HttpServletRequest request) {
		String jsonResult = "";
		try {
			int type_id = tBean.getTyping_reciever();
			HttpSession session = request.getSession();
			int typing_user = (Integer) session.getAttribute("user_id");
			TypingModel c = schoolservice.checkTypingUser(typing_user, type_id);
			if (c == null) {
				TypingModel type_model = new TypingModel();
				type_model.setStatus(1);
				type_model.setTyping_reciever(type_id);
				type_model.setTyping_user(typing_user);
				type_model.setType_id(null);
				schoolservice.insertTypingUser(type_model);
			} else {
				TypingModel type_model = new TypingModel();
				type_model.setStatus(1);
				schoolservice.updateTypingUserStatus(type_model, type_id,
						typing_user);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @access public Method for remove driver document
	 **/
	@RequestMapping(value = "/checkUserTypingStatus.html", method = RequestMethod.POST)
	@ResponseBody
	public String checkUserTypingStatus(
			@ModelAttribute("command") TypingBean tBean, BindingResult result,
			HttpServletRequest request) {
		String jsonResult = "";
		try {
			int type_id = tBean.getTyping_user();
			HttpSession session = request.getSession();
			int typing_user = (Integer) session.getAttribute("user_id");
			TypingModel c = schoolservice.checkTypingUser(type_id, typing_user);
			JSONObject jsonArray = new JSONObject();
			if (c == null) {
				jsonArray.put("status", 0);
			} else {
				jsonArray.put("status", c.getStatus());
			}
			jsonResult = jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;

	}

	/**
	 * @access public Method for remove driver document
	 **/
	@RequestMapping(value = "/parentToSchoolAdminTypingUpdate.html", method = RequestMethod.POST)
	@ResponseBody
	public void parentToSchoolAdminTypingUpdate(
			@ModelAttribute("command") TypingBean tBean, BindingResult result,
			HttpServletRequest request) {
		String jsonResult = "";
		try {
			int type_id = tBean.getTyping_reciever();
			HttpSession session = request.getSession();
			int typing_user = (Integer) session.getAttribute("user_id");
			TypingModel c = schoolservice.checkTypingUser(typing_user, type_id);
			TypingModel type_model = new TypingModel();
			type_model.setStatus(0);
			schoolservice.updateTypingUserStatus(type_model, type_id,
					typing_user);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author Rudiment Webtech Solutions Method for get school admin message
	 *         notification
	 **/
	@RequestMapping(value = "/schoolAdminMessageNotification.html", method = RequestMethod.POST)
	@ResponseBody
	public String schoolAdminMessageNotification(
			@ModelAttribute("command") TypingBean tBean, BindingResult result,
			HttpServletRequest request) {
		String jsonResult = "";
		try {
			HttpSession session = request.getSession();
			int user_id = (Integer) session.getAttribute("user_id");
			JSONObject json = new JSONObject();
			json.put("message_count",
					schoolservice.getMessageNotification(user_id).size());
			DriverMessage dm = (DriverMessage) schoolservice.getMessageNotification(user_id)
					.get(0);
			json.put("msg", dm.getMsg());
			LoginModel loginmodel = new LoginModel();
			loginmodel = schoolservice.getParentById(dm.getSender_id());
			json.put("user_id", dm.getSender_id());
			json.put("role", dm.getU_check());
			json.put("u_name", loginmodel.getUser_name());
			jsonResult = json.toString();
		} catch (Exception e) {
			System.out.println(e);
		}
		return jsonResult;
	}

	/**
	 * @author Rudiment Webtech Solutions Method for get school admin message
	 *         notification
	 **/
	@RequestMapping(value = "/parentMessageNotification.html", method = RequestMethod.POST)
	@ResponseBody
	public String parentMessageNotification(
			@ModelAttribute("command") TypingBean tBean, BindingResult result,
			HttpServletRequest request) {
		String jsonResult = "";
		try {
			HttpSession session = request.getSession();
			int user_id = (Integer) session.getAttribute("user_id");
			JSONObject json = new JSONObject();
			json.put("message_count", schoolservice
					.getMessageParentNotification(user_id).size());
			DriverMessage dm = (DriverMessage) schoolservice.getMessageParentNotification(
					user_id).get(0);
			json.put("msg", dm.getMsg());
			LoginModel loginmodel = new LoginModel();
			loginmodel = schoolservice.getParentById(dm.getSender_id());
			json.put("user_id", dm.getSender_id());
			json.put("role", dm.getU_check());
			json.put("u_name", loginmodel.getUser_name());
			jsonResult = json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonResult;
	}

	/**
	 * @author Rudiment Webtech Solutions Method for get school admin message
	 *         notification
	 **/
	@RequestMapping(value = "/adminMessageNotification.html", method = RequestMethod.POST)
	@ResponseBody
	public String adminMessageNotification(
			@ModelAttribute("command") TypingBean tBean, BindingResult result,
			HttpServletRequest request) {
		String jsonResult = "";
		try {
			HttpSession session = request.getSession();
			int user_id = (Integer) session.getAttribute("user_id");
			JSONObject json = new JSONObject();
			if (schoolservice.getMessageAdminNotification(user_id).size() > 0) {
				json.put("message_count", schoolservice
						.getMessageAdminNotification(user_id).size());
				DriverMessage dm = (DriverMessage) schoolservice.getMessageAdminNotification(
						user_id).get(0);
				json.put("msg", dm.getMsg());
				LoginModel loginmodel = new LoginModel();
				loginmodel = schoolservice.getParentById(dm.getSender_id());
				json.put("user_id", dm.getSender_id());
				json.put("role", dm.getU_check());
				json.put("u_name", loginmodel.getUser_name());
				jsonResult = json.toString();
			} else {
				jsonResult = json.toString();
			}

		} catch (Exception e) {
			//e.printStackTrace();
		}
		return jsonResult;
	}

	/**
	 * Method for get student blink notification
	 **/
	@RequestMapping(value = "/getStudentBlinkStatus.html", method = RequestMethod.POST)
	@ResponseBody
	public String getStudentBlinkStatus(
			@ModelAttribute("command") StudentBean studentbean,
			BindingResult result, HttpServletRequest request) {
		String jsonResult = "";
		try {
			HttpSession session = request.getSession();
			int user_id = studentbean.getStudent_id();
			StudentModel student_details = new StudentModel();
			student_details = studentservice.getStudentById(user_id);
			JSONObject json = new JSONObject();
			json.put("blink_status", student_details.getBlink_status());
			jsonResult = json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	/**
	 * Method for get Driver tracking latlng by student id
	 * **/
	@RequestMapping(value = "/getAllDriversLatLng.html", headers = "Accept=*/*", method = RequestMethod.POST)
	@ResponseBody
	public String getAllDriversLatLng(
			@ModelAttribute("command") StudentBean studentbean,
			BindingResult result) {
		String jsonResult = "";
		try {
			JSONObject jsonArray = new JSONObject();
			int school_id=studentbean.getS_school_id();
			List<RouteModel> routes=new ArrayList<RouteModel>();
			routes= schoolservice.listRoute(school_id);
			Map<Integer, List<DriverTrack>> map = new HashMap<Integer, List<DriverTrack>>();
			int i=0;
			for (RouteModel st : routes) {
				List<DriverTrack> driver_lat_lng=new ArrayList<DriverTrack>();	
				driver_lat_lng=getDriverTrackList(studentservice.getDriverTrackLimitOne(st.getRoute_id()));
				if(driver_lat_lng!=null)
				{
					map.put(i,driver_lat_lng);
					i++;
				}
			
			}
			jsonArray.put("lat_lng", map);
			/*jsonArray.put("details", getDriverTrackList(studentservice
					.getDriverTrack(studentbean.getS_route_id())));
			jsonArray.put("lat_lng", getDriverTrackList(studentservice
					.getDriverTrackLimit(studentbean.getS_route_id())));
			jsonArray.put("size_l",
					studentservice.getDriverTrack(studentbean.getS_route_id())
							.size() - 1);*/
			jsonResult = jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;
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
		//	System.out.println("empty");
		}
		return beans;
	}
	
	@RequestMapping(value = "getTotalPresentAbsent.html", method = RequestMethod.POST)
	@ResponseBody
	public String getTotalPresentAbsent(@RequestBody String jsonNew) {
		String jsonResult = "";
		try {
			JSONObject jsonArray = new JSONObject(jsonNew);
			String current_month = (String) jsonArray.get("current_month");
			String student_id_str = (String) jsonArray.get("student_id");
			String school_id=(String) jsonArray.get("school_id");
			int student_id=Integer.parseInt(student_id_str);
			String date_arr[]= current_month.split(",");
			String month="";
			int iMonth = 0;
			if(date_arr[0].equals("January"))
			{
				month="01";
				iMonth =Calendar.JANUARY;
			}else if (date_arr[0].equals("February")) {
				month="02";
				iMonth =Calendar.FEBRUARY;
			}else if (date_arr[0].equals("March")) {
				month="03";
				iMonth =Calendar.MARCH;
			}else if (date_arr[0].equals("April")) {
				month="04";
				iMonth =Calendar.APRIL;
			}else if (date_arr[0].equals("May")) {
				month="05";
				iMonth =Calendar.MAY;
			}else if (date_arr[0].equals("June")) {
				month="06";
				iMonth =Calendar.JUNE;
			}else if (date_arr[0].equals("July")) {
				month="07";
				iMonth =Calendar.JULY;
			}else if (date_arr[0].equals("August")) {
				month="08";
				iMonth =Calendar.AUGUST;
			}else if (date_arr[0].equals("September")) {
				month="09";
				iMonth =Calendar.SEPTEMBER;
			}else if (date_arr[0].equals("October")) {
				month="10";
				iMonth =Calendar.OCTOBER;
			}else if (date_arr[0].equals("November")) {
				month="11";
				iMonth =Calendar.NOVEMBER;
			}else {
				month="12";
				iMonth =Calendar.DECEMBER;
			}
			
			String year=date_arr[1];
			int total_present=0;
			int absent_day=0;
			total_present=schoolservice.getStudentAttendanceListByMonth(student_id,month,year).size();
			int iYear = Integer.parseInt(year.trim());
			int iDay = 1;
			Calendar mycal = new GregorianCalendar(iYear, iMonth, iDay);
			int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			List<HolidayBean> a=getHolidayList(schoolservice.getAllHolidayByMonth(Integer.parseInt(school_id),month,year));
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
			
			
			
			
		 
			
			int holiday_days=schoolservice.getAllHoliday(Integer.parseInt(school_id)).size();
			absent_day=daysInMonth-total_present;
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
						jsonResult = jsonRetun.toString();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return jsonResult;
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
				bean.setHoliday_start_date(holi.getHoliday_start_date());
				beans.add(bean);
			}
		} else {
			//System.out.println("empty");
		}
		return beans;
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
			//System.out.println("empty");
		}
		return beans;
	}
	
	

	@RequestMapping(value = "getTotalPresentAbsentByDateRange.html", method = RequestMethod.POST)
	@ResponseBody
	public String getTotalPresentAbsentByDateRange(@RequestBody String jsonNew) {
		String jsonResult = "";
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
						jsonResult = jsonRetun.toString();
			
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return jsonResult;
	}
	/**
	 * Method for get student tracking latlng by student id
	 * **/
	@RequestMapping(value = "/trackAllRouteCount.html", headers = "Accept=*/*", method = RequestMethod.POST)
	@ResponseBody
	public String trackAllRouteCount(
			@ModelAttribute("command") StudentBean studentbean,
			BindingResult result) {
		String jsonResult = "";
		try {
			JSONObject jsonArray = new JSONObject();
			int school_id=studentbean.getS_school_id();
	//		System.out.println(studentbean.getS_school_id());
			//jsonArray.put("size_l",studentservice.getDriverTrack(studentbean.getS_route_id()).size());
			List<RouteModel> routes=new ArrayList<RouteModel>();
			routes= schoolservice.listRoute(school_id);
			Map<Integer, List<DriverTrack>> map = new HashMap<Integer, List<DriverTrack>>();
			int i=0;
			int total=0;
			for (RouteModel st : routes) {
				List<DriverTrack> driver_lat_lng=new ArrayList<DriverTrack>();	
				driver_lat_lng=getDriverTrackList(studentservice.getDriverTrackLimitOne(st.getRoute_id()));
				total=total+studentservice.getDriverTrack(st.getRoute_id()).size();
				if(driver_lat_lng!=null)
				{
					map.put(i,driver_lat_lng);
				}
			i++;
			}
			jsonArray.put("size_l",total);
			jsonResult = jsonArray.toString();
		} catch (Exception e) {
		//	System.out.println("Not Available");
		}

		return jsonResult;
	}
	
	/**
	 * Method for get student tracking latlng by student id
	 * **/
	@RequestMapping(value = "/getStudentBlinkUpdatedStatus.html", headers = "Accept=*/*", method = RequestMethod.POST)
	@ResponseBody
	public String getStudentBlinkUpdatedStatus(
			@ModelAttribute("command") RouteBean r_bean, BindingResult result) {
		String jsonResult="";
		try {
		JSONObject jsonObject = new JSONObject();
		RouteModel routemodel =new RouteModel();
		routemodel = schoolservice.getRouteById(r_bean.getRoute_id());
		if (routemodel != null) {
			jsonObject.put("latlong", getAllLatLng(schoolservice.listLatLng(r_bean.getRoute_id())));
			jsonObject.put("route",routemodel);
			jsonResult=jsonObject.toString();
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return jsonResult;
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
					bean.setStudent_name(studentModel.getS_fname());
					bean.setAddress(studentModel.getS_address());
					bean.setId(st.getId());
					bean.setLat(st.getLat());
					bean.setLng(st.getLng());
					bean.setRoute_id(st.getRoute_id());
					bean.setBlink_s(studentModel.getBlink_status());
					beans.add(bean);
				}else
				{
					bean.setS_image("");
					bean.setS_class("");
					bean.setStudent_name("");
					bean.setAddress("");
					bean.setId(null);
					bean.setLat(null);
					bean.setLng(null);
					bean.setRoute_id(null);
					bean.setBlink_s(0);
					beans.add(bean);
				}
				
			}
		} else {
			//System.out.println("empty");
		}
		return beans;
	}
	@RequestMapping(value = "/page.html", method = RequestMethod.GET)
	public ModelAndView page(
			@ModelAttribute("command") StudentBean studentbean,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		
		String q=request.getParameter("q");
		byte[] decodedBytes = Base64.decodeBase64(""+q+"");
		int page_id=Integer.parseInt(new String(decodedBytes));
		PageModel page_model= schoolservice.getPageById(page_id);
		model.put("content", page_model);
		model.addAttribute("heading", page_model.getPage_name());
		return new ModelAndView("home/page", model);
	}
	
	
	/**
	 * Method for get subscribers
	 **/
	@RequestMapping(value = "/subscribe.html", method = RequestMethod.POST)
	@ResponseBody
	public String subscribe(
			@ModelAttribute("command") SubscriberBean s_bean,
			BindingResult result, HttpServletRequest request) {
		String jsonResult = "";
		try {
			HttpSession session = request.getSession();
			String email = s_bean.getEmail();
			SubscriberModel sub_model = new SubscriberModel();
			JSONObject json = new JSONObject();
			if(!email.equals(""))
			{
				sub_model  = studentservice.getSubEmailById(email);
				SubscriberModel sub_model1 = new SubscriberModel();
				if(sub_model==null)
				{
					sub_model1.setSub_id(null);
					sub_model1.setEmail(email);
					studentservice.addSubscribers(sub_model1);
					json.put("msg","Thanks for subscribing");
				}else
				{
					json.put("msg","You are already subscribed");
				}
				
			}else
			{
				json.put("msg","Please enter email");
			}
			
			
			
			jsonResult = json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	//	Method for get driver present and absent between two dates
	@RequestMapping(value = "getTotalDriverPresentAbsentByDateRange.html", method = RequestMethod.POST)
	@ResponseBody
	public String getTotalDriverPresentAbsentByDateRange(@RequestBody String jsonNew) {
		String jsonResult = "";
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
			total_present=schoolservice.getDriverAttendanceListByTwoDate(student_id,start_date,end_date).size();
			
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
						jsonResult = jsonRetun.toString();
			
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return jsonResult;
	}
	
	@RequestMapping(value = "getTotalDriverPresentAbsent.html", method = RequestMethod.POST)
	@ResponseBody
	public String getTotalDriverPresentAbsent(@RequestBody String jsonNew) {
		String jsonResult = "";
		try {
			JSONObject jsonArray = new JSONObject(jsonNew);
			String current_month = (String) jsonArray.get("current_month");
			String student_id_str = (String) jsonArray.get("student_id");
			String school_id=(String) jsonArray.get("school_id");
			int student_id=Integer.parseInt(student_id_str);
			String date_arr[]= current_month.split(",");
			String month="";
			int iMonth = 0;
			if(date_arr[0].equals("January"))
			{
				month="01";
				iMonth =Calendar.JANUARY;
			}else if (date_arr[0].equals("February")) {
				month="02";
				iMonth =Calendar.FEBRUARY;
			}else if (date_arr[0].equals("March")) {
				month="03";
				iMonth =Calendar.MARCH;
			}else if (date_arr[0].equals("April")) {
				month="04";
				iMonth =Calendar.APRIL;
			}else if (date_arr[0].equals("May")) {
				month="05";
				iMonth =Calendar.MAY;
			}else if (date_arr[0].equals("June")) {
				month="06";
				iMonth =Calendar.JUNE;
			}else if (date_arr[0].equals("July")) {
				month="07";
				iMonth =Calendar.JULY;
			}else if (date_arr[0].equals("August")) {
				month="08";
				iMonth =Calendar.AUGUST;
			}else if (date_arr[0].equals("September")) {
				month="09";
				iMonth =Calendar.SEPTEMBER;
			}else if (date_arr[0].equals("October")) {
				month="10";
				iMonth =Calendar.OCTOBER;
			}else if (date_arr[0].equals("November")) {
				month="11";
				iMonth =Calendar.NOVEMBER;
			}else {
				month="12";
				iMonth =Calendar.DECEMBER;
			}
			
			String year=date_arr[1];
			int total_present=0;
			int absent_day=0;
			total_present=schoolservice.getDriverAttendanceListByMonth(student_id,month,year).size();
			System.out.println("Total Present"+total_present);
			int iYear = Integer.parseInt(year.trim());
			int iDay = 1;
			Calendar mycal = new GregorianCalendar(iYear, iMonth, iDay);
			int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			List<HolidayBean> a=getHolidayList(schoolservice.getAllHolidayByMonth(Integer.parseInt(school_id),month,year));
			List<HolidayDeletedBean> b=getDeletedHolidayList(schoolservice.getAllDeletedHoliday(Integer.parseInt(school_id)));
			try{
				if(a!=null)
				{
			for(int i=0;i<a.size();i++)
			{
				for(int j=0;j<b.size();j++)
				{
					if(a.get(i).getH_id().equals(b.get(j).getHoliday_id()))
					{
						a.remove(i);
					}
				}
			}}
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println(e);
			}
			
			
			
			
		 
			
			int holiday_days=schoolservice.getAllHoliday(Integer.parseInt(school_id)).size();
			absent_day=daysInMonth-total_present;
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
						jsonResult = jsonRetun.toString();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	/**
	 * Method for get all students by route id
	 * **/
	@RequestMapping(value = "/getNewStudentTableStatus.html", method = RequestMethod.POST)
	public ModelAndView getNewStudentTableStatus(
			@ModelAttribute("command") RouteBean route_bean, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("all_students", getStudentList(schoolservice.getStudentsByRouteId(route_bean.getRoute_id())));
		return new ModelAndView("home/get_student_blink_table", model);
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
				bean.setDob(student.getDob());
				bean.setGender(student.getGender());
				bean.setGrand_name(student.getGrand_name());
				bean.setNationality(student.getNationality());
				bean.setStudent_class(student.getStudent_class());
				bean.setGender(student.getGender());
				bean.setBlink_status(student.getBlink_status());
				beans.add(bean);
			}
		} else {
			System.out.println("empty");
		}
		return beans;
	}

	
}
