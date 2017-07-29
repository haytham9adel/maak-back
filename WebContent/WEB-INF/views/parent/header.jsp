<% response.setHeader("Cache-Control", "no-cache"); %>
<% response.setHeader("Cache-Control", "must-revalidate"); %>
<% response.setHeader("Pragma","no-cache"); %>
<% response.setDateHeader("Last-Modified",System.currentTimeMillis());%>
<%@ page import="resources.Assets"%>
<%
  if (session.getAttribute("userRole")!= "Parent")
  {
    String address ="/login.html";
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
    dispatcher.forward(request,response);
  }
%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="resources.Assets" %>
<head>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Bus Tracking</title>
<base href="<%=Assets.BASE_URL %>"></base>
<link rel="shortcut icon" href="resources/front/Images/favicon.png">
   <link href="resources/dashboard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
     <link href="resources/dashboard/css/mystyle.css" rel="stylesheet" type="text/css">
     <link href="resources/dashboard/css/deshboard.css" rel="stylesheet" type="text/css" />
	
     <link href="resources/dashboard/css/font-awesome.css" rel="stylesheet" type="text/css">
     <link href="resources/dashboard/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <script src="resources/dashboard/js/push.min.js"></script>
<script src="resources/dashboard/js/prism.min.js"></script>   
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="resources/dashboard/js/bootstrap.min.js"> </script>
    
    <script src="resources/dashboard/js/jstz-1.0.4.min.js"> </script>
<script language="javascript">
    function getTimezoneName() {
        timezone = jstz.determine()
       // return timezone.name();
        
        $.ajax({
            url : 'setTimeZoneInCookies.html',
            type:'post',
            data:{"name":timezone.name()},
            success : function(data) {
            }
        });
        
    }
    
</script>
    
    
 <script>
	$(document).ready(function(){
		getTimezoneName();
		  setInterval(function(){
			var message_old_noti=$("#message_noti_old_count").val();  
			
			$.ajax({
				type:"post",
				url:"parentMessageNotification.html",
				data:{},
				success:function(response){
					var obj=$.parseJSON( response);
					 
					if(parseInt(obj.message_count)>parseInt(message_old_noti))
					{
						 Push.create(obj.u_name, {
				             body: obj.msg,
				             icon: 'resources/dashboard/Images/noti_img.png',
				             timeout: 4000,
				             onClick: function () {
				                 window.focus();
				                 window.location = "<%=Assets.BASE_URL %>"+"parent/inboxMessageSocket?u_id="+obj.user_id;
			                     this.close();
				             },
				             vibrate: [200, 100, 200, 100, 200, 100, 200]
				         });	
						$("#message_noti_old_count").val(obj.message_count)	
					}
				}
			});
		}, 4000);  
 	});
</script>    
</head>  
<body>
<a class="lan_switch" href="javascript:void(0);" id="lang_switcher">عربي</a>
   <div id="deshboard" class="desh-wrappper">
   <!-- Hidden value used for notification end here-->
   <input type="hidden" id="message_noti_old_count" value="${total_message_notification_count}"></input>
   <input type="hidden" id="document_noti_old_count" value="${notification_vehicle_count}"></input>
   <!-- Hidden value used for notificaion end here -->
      <header>
        <div class="head-top"> 
           <div class="container"> 
                <div class="pull-right user-blcok">
                <div class="dropdown pull-left notification_drop" >
					  <button class="dropdown-toggle" type="button" data-toggle="dropdown">
					  <i class="fa fa-envelope" aria-hidden="true"></i>
					  <span class="caret"></span>
						<c:if test="${total_message_notification_count gt 0}">
						<input type="hidden" id="total_count_badge" value="${total_message_notification_count}" />
						 <span  id="message-badge" class="badge">${total_message_notification_count}</span>
						</c:if>
					  </button>
					  <ul class="dropdown-menu">
					
					 
						<c:choose>
						<c:when test="${not empty total_message_notification}">
						
					 
					 
					 <c:forEach items="${total_message_notification}" var="v_n">
					<%-- 	<c:set var="vechile_id" value="${v_n.v_id}"/>
						<c:set var="v_doc_id" value="${v_n.v_doc_id}"/>
						<%
						String str=pageContext.getAttribute("vechile_id").toString();
						byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
						
						String str1=pageContext.getAttribute("v_doc_id").toString();
						byte[] encodedBytes1 = Base64.encodeBase64(str1.getBytes());
						%>
						 --%>
					     <li>
					       
					     	
					     	
					     	
					     
					 	<a class="content" style="background:<c:if test="${v_n.status eq 0}">#fff</c:if><c:if test="${v_n.status eq 1}">#fff</c:if>!important;;" href="parent/inboxMessageSocket?u_id=${v_n.sender_id}">
					    <div class="notification-item">
					        <h4 class="item-title"><%-- <b>Message:</b>${v_n.msg } --%>
					        
					        <b>${v_n.name}</b>${v_n.msg } <span id="note_date_1" class="note_date">${v_n.time}</span>
					        
					        </h4>
					        <%-- <p class="item-info"><b>From:</b> ${v_n.name}</p>
					        <p class="item-info"><b>Date:</b> ${v_n.time}</p> --%>
					        
					         
					      </div>
					       
					    	</a>
					    </li>
					   </c:forEach>
					   
						</c:when>
						<c:otherwise>
						<li>No Notification</li>
						</c:otherwise>
						</c:choose>
					   
					   
					  </ul>
					  </div>
					   <div class="dropdown pull-right">
                      <button class="loged-user dropdown-toggle" type="button" 
                      data-toggle="dropdown" aria-expanded="true">
                        Hi <%=session.getAttribute("f_name") %> <span class="caret"></span>
                      </button>
                       <div class="dropdown-menu">
                        <a href="logout.html">Logout</a>
                       </div>
                     </div>
                   <%-- <div class="dropdown">
                      <button class="loged-user dropdown-toggle" type="button" 
                      data-toggle="dropdown" aria-expanded="true">
                        Hi <%=session.getAttribute("userName") %>  <span class="caret"></span>
                      </button>
                       <div class="dropdown-menu">
                        <a href="logout.html">Logout</a>
                       </div>
                     </div> --%>
                </div>
            </div>
        </div>
         <div class="head-bottom">
           <div class="container">
             <div class="col-sm-3">
               <img src="resources/dashboard/Images/desh-logo.jpg" />
             </div>
             <div class="col-sm-4 ">
               <h2 class="desh-page-header"> ${heading} </h2>
             </div>
             <%  if (session.getAttribute("schoolId")!=null)
				{ %>
				 
				      
				<div class="col-sm-5 pad0"> 
				 <div class="col-sm-9 school_name pad0"><h4>${school_details.school_name}</h4> </div> 
				  <div class="schol-logo pull-right col-sm-3"> 
						<c:choose>
						<c:when test="${school_details.school_logo!=''}">
						
						<img id="" alt="image_path"   class="img-responsive" src="<%=Assets.SCHOOL_UPLOAD_PATH %>${school_details.school_logo}"/>
						
						</c:when>
						<c:otherwise>
						<img class="img-responsive" id="" src="resources/dashboard/Images/s_logo_d.png" alt="image_path" />
						 </c:otherwise>
						</c:choose>
				</div>
				</div>
				<% } %>
             <nav class="desh-nav navbar-right" role="navigation">
                <div class="navbar-header">
                 <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-collapse">
                   <span class="sr-only">Toggle navigation</span>
                   <span class="icon-bar"></span>
                   <span class="icon-bar"></span>
                   <span class="icon-bar"></span>
                   </button><!--//nav-toggle-->
                   </div><!--//navbar-header-->
                   
            </nav> 
           </div> 
         </div>
      </header> 
           <section class="desh-content">
        <div class="container">
        <jsp:include page="sidebar.jsp" />
<script>
    	<%-- $(document).ready(function(){
    		$("#lang_switcher").click(function(){
    			var pathname = window.location.pathname; // Returns path only
        		var url      = window.location.href;     // Returns full URL
        	 	var base_url = "<%=Assets.BASE_URL%>";
				var new_url = url.replace(base_url,'');
				window.location = "<%=Assets.BASE_URL_AR%>"+new_url;
			});
    	}); --%>
    	$(document).ready(function(){
    		$("#lang_switcher").click(function(){
    			var pathname = window.location.pathname; // Returns path only
        		var url      = window.location.href;     // Returns full URL
        	 	var base_url = "<%=Assets.BASE_URL%>";
				var new_url = url.replace(base_url,'');
				window.location = "<%=Assets.BASE_URL_AR%>parent/langSwitcher?q="+new_url+"&u=<%=session.getAttribute("user_id")%>";
			});
    	});
    	
</script>