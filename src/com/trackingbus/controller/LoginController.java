package com.trackingbus.controller;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import com.trackingbus.model.LoginModel;
import com.trackingbus.service.FrontDashboardService;
import com.trackingbus.service.LoginService;
import com.trackingbus.bean.LoginBean; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Controller
public class LoginController {

	@Autowired
	private LoginService loginservice;
	
	@RequestMapping(value="/login.html",method=RequestMethod.GET)
	public ModelAndView login()
	{
		return new ModelAndView("home/login");
	}
	@RequestMapping(value="/login.html",method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("command") LoginModel loginmodel, BindingResult result,ModelMap model,HttpServletRequest request)
	{
		try{
		if (result.hasErrors()) {
			System.out.println("Some error occrrued");
		} else {
			//LoginModel found = loginservice.login(loginmodel.getUser_email(), loginmodel.getUser_pass());
			LoginModel found = loginservice.checkUsername(loginmodel.getUser_name());
			if(found!=null){
			if(found!=null && found.getUser_pass().equals(loginmodel.getUser_pass()))
			{
				HttpSession session=request.getSession();
				session.setAttribute("f_name", found.getFirst_name());
				session.setAttribute("l_name", found.getLast_name());
				session.setAttribute("family_name", found.getFamily_name());
				session.setAttribute("userName", found.getUser_name());
				session.setAttribute("userEmail", found.getUser_email());
				if (found.getUser_role()==1) {
					session.setAttribute("userRole", "Admin");
					session.setAttribute("permission", found.getPermission());
					session.setAttribute("user_id", found.getUser_id());
					 return new ModelAndView("redirect:/adminDashboard");
				} else if(found.getUser_role()==2) {
					session.setAttribute("schoolId", found.getSchool_id());
					session.setAttribute("userRole", "Manager");
					session.setAttribute("user_id", found.getUser_id());
					session.setAttribute("permission", found.getPermission());
					session.setAttribute("main_admin",found.getMain_school_admin());
					return new ModelAndView("redirect:/schoolDashboard.html");
				} else if(found.getUser_role()==3){
					session.setAttribute("userRole", "Parent");
					session.setAttribute("schoolId", found.getSchool_id());
					session.setAttribute("p_status", found.getP_status());
					session.setAttribute("user_id", found.getUser_id());
					return new ModelAndView("redirect:/parentDashboard");
				} 
			}
			else{
				model.addAttribute("error", "Please enter correct password");
			}
			
			}else
			{
				model.addAttribute("error", "Please enter correct username");
			}
			
			
			
		}
		
		}catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return new ModelAndView("home/login");
	}
	
	/*@RequestMapping("/person.html")
	
	public String getPersonDetail(@RequestParam(value = "user_email",required = false,
	                                                    defaultValue = "0") String user_email,@RequestParam(value = "user_pass",required = false,
	                                                    defaultValue = "0") String user_pass,BindingResult result,ModelMap model) {
		String jsonResult="";
		System.out.println("New One"+user_email);
		try{
			LoginModel found=new LoginModel();
			
			JSONObject jsonArray=new JSONObject();
			found = loginservice.login(user_email, user_pass);
			System.out.println("Here"+found.getSchool_id());
			if(found!=null && found.getUser_pass().equals(user_pass))
			{
				
				if (found.getUser_role()==1) {
					jsonArray.put("role", "Admin");
				} else if(found.getUser_role()==2) {
					jsonArray.put("role", "Manager");
				} else if(found.getUser_role()==3){
					jsonArray.put("role", "Parent");
				}
				jsonArray.put("user_email", found.getUser_email());
				jsonArray.put("user_name", found.getUser_name());
				jsonArray.put("school_id", found.getSchool_id());
				jsonArray.put("contact_number", found.getClass());
			}
			else{
				jsonArray.put("error", "Username or password does not match");
				jsonArray.put("status", "404");
			}
			jsonResult=jsonArray.toString();
			
	
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonResult;
	}
	*/
	@RequestMapping(value="/loginUser.html",method=RequestMethod.GET)
	@ResponseBody
	public String login1(@ModelAttribute("command") LoginModel loginmodel, BindingResult result,ModelMap model,HttpServletRequest request) throws JSONException
	{
		String jsonResult="";
		JSONObject jsonArray=new JSONObject();
		try{
			LoginModel found = loginservice.login(loginmodel.getUser_email(), loginmodel.getUser_pass());
			
			if(found!=null  && found.getUser_pass().equals(loginmodel.getUser_pass()))
			{
				if (found.getUser_role()==1) {
					jsonArray.put("role", "Admin");
				} else if(found.getUser_role()==2) {
					jsonArray.put("role", "Manager");
				} else if(found.getUser_role()==3){
					jsonArray.put("role", "Parent");
				}
				jsonArray.put("result", "success");
				jsonArray.put("user_id", found.getUser_id());
				jsonArray.put("user_email", found.getUser_email());
				jsonArray.put("user_name", found.getUser_name());
				jsonArray.put("school_id", found.getSchool_id());
				jsonArray.put("contact_number", found.getContact_number());
			}
			else{
				jsonArray.put("result", "failed");
				jsonArray.put("error", "Username or password does not match");
				jsonArray.put("status", "404");
			}
			jsonResult=jsonArray.toString();
			
	
		
		}catch (Exception e) {
				jsonArray.put("result", "failed");
				jsonArray.put("error", "Username or password does not match");
				jsonArray.put("status", "404");
				jsonResult=jsonArray.toString();
		}
		return jsonResult;
	}
	
	
	
	
}
