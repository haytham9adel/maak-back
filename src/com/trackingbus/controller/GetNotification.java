package com.trackingbus.controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.trackingbus.bean.DriverDocBean;
import com.trackingbus.bean.SchoolMessageBean;
import com.trackingbus.bean.VehicleDocBean;
import com.trackingbus.model.DriverDocModel;
import com.trackingbus.model.DriverMessage;
import com.trackingbus.model.DriverModel;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.PageContentModel;
import com.trackingbus.model.VehicleDocModel;
import com.trackingbus.model.VehicleModel;
import com.trackingbus.service.SchoolService;
import com.trackingbus.service.VehicleService;

@Component
public class GetNotification extends HandlerInterceptorAdapter {
	@Autowired
	private SchoolService schoolservice;
	
	@Autowired
	private VehicleService vechileservice;
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/*System.out.println("NotificationInterceptor: REQUEST Intercepted for URI: "
				+ request.getRequestURI());*/
		try{
			HttpSession session = request.getSession();
		//	System.out.println(session.getAttribute("userRole"));
			if(session.getAttribute("new_school_id")!=null)
			{
				
				int user_id=(Integer) session.getAttribute("user_id");
				int school_id = (Integer) session.getAttribute("new_school_id");
				int total_notification = schoolservice.vehicleNotification(school_id).size();
				request.setAttribute("notification", total_notification);
				request.setAttribute("notification_vehicle",prepareListOfVehicleDoc(schoolservice.getVehicleNotification(school_id)));
				request.setAttribute("notification_vehicle_count",schoolservice.getVehicleNotificationCount(school_id).size());
				request.setAttribute("total_message_notification_count", schoolservice.getMessageAdminNotification(user_id).size());
				request.setAttribute("total_message_notification", prepareListOfMessage(schoolservice.getMessageAdminNotification(user_id)));
		
				
			}else if(session.getAttribute("schoolId")!=null)
			{
				
				int user_id=(Integer) session.getAttribute("user_id");
				int school_id = (Integer) session.getAttribute("schoolId");
				int total_notification = schoolservice.vehicleNotification(school_id).size();
				System.out.println("Total Size="+schoolservice.getVehicleNotification(school_id).size());
				request.setAttribute("notification_vehicle",prepareListOfVehicleDoc(schoolservice.getVehicleNotification(school_id)));
				request.setAttribute("notification", total_notification);
				request.setAttribute("notification_vehicle_count",schoolservice.getVehicleNotificationCount(school_id).size());
				request.setAttribute("total_message_notification_count", schoolservice.getMessageNotification(user_id).size());
				request.setAttribute("total_message_notification", prepareListOfMessage(schoolservice.getMessageNotification(user_id)));
				/*request.setAttribute("all_notifications", prepareAllNotification(schoolservice.getAllNotification(school_id)));*/
			
			}
			else if(session.getAttribute("userRole")=="Admin") 
			{
				
				int user_id=(Integer) session.getAttribute("user_id");
				request.setAttribute("total_message_notification_count", schoolservice.getMessageAdminNotification(user_id).size());
				request.setAttribute("total_message_notification", prepareListOfMessage(schoolservice.getMessageAdminNotification(user_id)));
				
				request.setAttribute("notification", 0);
			}else if(session.getAttribute("userRole")=="Parent") 
			{
				 
				int user_id=(Integer) session.getAttribute("user_id");
				request.setAttribute("total_message_notification_count", schoolservice.getMessageParentNotification(user_id).size());
				request.setAttribute("total_message_notification", prepareListOfMessage(schoolservice.getMessageParentNotification(user_id)));
				
				request.setAttribute("notification", 0);
			}
			
			
			
		}catch(Exception e)
		{
			//e.printStackTrace();
			//System.out.println(e);
		}
		
		
		return true;
	}
	public boolean postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/*System.out.println("NotificationInterceptor: REQUEST Intercepted for URI: "
				+ request.getRequestURI());*/
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("new_school_id")!=null)
			{
				int school_id = (Integer) session.getAttribute("new_school_id");
				int total_notification = schoolservice.vehicleNotification(school_id).size();
				request.setAttribute("notification", total_notification);
				request.setAttribute("notification_vehicle",prepareListOfVehicleDoc(schoolservice.getVehicleNotification(school_id)));
				request.setAttribute("notification_vehicle_count",schoolservice.getVehicleNotificationCount(school_id).size());
				System.out.println("Come Here");
			}else if(session.getAttribute("schoolId")!=null)
			{
				int school_id = (Integer) session.getAttribute("schoolId");
				int user_id=(Integer) session.getAttribute("user_id");
				int total_notification = schoolservice.vehicleNotification(school_id).size();
				System.out.println(schoolservice.getVehicleNotification(school_id).size());
				request.setAttribute("notification_vehicle",prepareListOfVehicleDoc(schoolservice.getVehicleNotification(school_id)));
				request.setAttribute("notification", total_notification);
				request.setAttribute("notification_vehicle_count",schoolservice.getVehicleNotificationCount(school_id).size());
				request.setAttribute("total_message_notification_count", schoolservice.getMessageNotification(user_id).size());
				request.setAttribute("total_message_notification", prepareListOfMessage(schoolservice.getMessageNotification(user_id)));
				
				 
			}
			else
			{
				request.setAttribute("notification", 0);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
		
		
		return true;
	}
	/**
	 * Method For get all schools
	 **/
	private List<VehicleDocBean> prepareListOfVehicleDoc(List<Object[]> vehicles) {

		List<VehicleDocBean> beans = null;
		if (vehicles != null && !vehicles.isEmpty()) {
			beans = new ArrayList<VehicleDocBean>();
			VehicleDocBean bean = null;
			for (Object[] vehicle : vehicles) {
    	   	    bean = new VehicleDocBean();
//				bean.setInsurance_document_expiry(vehicle[2].toString());
//				bean.setInsurance_document_name(vehicle[1].toString());
//				bean.setRemind_day(vehicle[3].toString());
//				bean.setSchool_id(Integer.parseInt(vehicle[4].toString()));
				bean.setV_doc_id(Integer.parseInt(vehicle[0].toString()));
				bean.setV_id(Integer.parseInt(vehicle[5].toString()));
//				bean.setStatus(Integer.parseInt(vehicle[7].toString()));
//				bean.setNoti_type(Integer.parseInt(vehicle[8].toString()));
//				VehicleModel vehiclemodel =new 	VehicleModel();
//				if(Integer.parseInt(vehicle[8].toString())==0)
//				{
//					
//						vehiclemodel = vechileservice.getVehicleById(Integer.parseInt(vehicle[5].toString()));
//						
//						if(vehiclemodel!=null)
//						{
//							bean.setInsurance_card_copy(vehiclemodel.getBus_number());
//						}else
//						{
							bean.setInsurance_card_copy("");
//						}
//					
//				}else
//				{
//					DriverModel drivermodel = schoolservice.getDriverById(Integer.parseInt(vehicle[5].toString()));
//					bean.setInsurance_card_copy(drivermodel.getDriver_fname());
//				}
				
				
				beans.add(bean);
			}
		} else {
			//System.out.println("empty Result");
		}
		return beans;

	}
	/**
	 * Method For get all schools
	 **/
	private List<SchoolMessageBean> prepareListOfMessage(List<DriverMessage> messages) {

		List<SchoolMessageBean> beans = null;
		if (messages != null && !messages.isEmpty()) {
			beans = new ArrayList<SchoolMessageBean>();
			SchoolMessageBean bean = null;
			for (DriverMessage vehicle : messages) {
				bean = new SchoolMessageBean();
				bean.setMsg(vehicle.getMsg());
				bean.setSender_id(vehicle.getSender_id());
				bean.setTime(vehicle.getTime());
				bean.setU_check(vehicle.getU_check());
				if(vehicle.getU_check()=="driver" || vehicle.getU_check().equals(""))
				{
					DriverModel drivermodel = schoolservice.getDriverById(vehicle.getSender_id());
					bean.setName(drivermodel.getDriver_fname());
				}else
				{
					LoginModel loginmodel =new LoginModel();
					loginmodel = schoolservice.getParentById(vehicle.getSender_id());
					bean.setName(loginmodel.getUser_name());
				}
				
				bean.setStatus(vehicle.getStatus());
				beans.add(bean);
			}
		} else {
			//System.out.println("empty Result");
		}
		return beans;

	}

}
