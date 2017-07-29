package com.trackingbus.ws;

import javax.annotation.security.PermitAll;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.trackingbus.model.LoginModel;
import com.trackingbus.service.LoginService;


@Path("/webservice")
public class Webservices {
	

	@Autowired
	private WebDao loginservice;
	
//    @GET
//    @Path("/hello")
//    @Produces("text/plain")
//    public String hello(@QueryParam("abc") String a){
//    	return a;    
//    }
//   
	
	
	
    /*For login*/
    @GET
    @PermitAll
    @Path("/login")
    @Produces("text/plain")
    public String login(@QueryParam("user_email") String user_email,@QueryParam("user_pass") String user_pass){
    	String jsonResult="";
		
		try{
			System.out.println(user_email);
			LoginModel found=new LoginModel();
			JSONObject jsonArray=new JSONObject();
			found=loginservice.login(user_email, user_pass);
		
			System.out.println("Here"+found.getUser_pass());
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
    
    
    
    
//    @POST
//  	@Path("/post")
//    public Response createProductInJSON(@FormParam("name") String name,
//  			@FormParam("age") int age) {
//
//    	return Response.status(200)
//    			.entity("addUser is called, name : " + name + ", age : " + age)
//    			.build();
//  		
//  	}
}
