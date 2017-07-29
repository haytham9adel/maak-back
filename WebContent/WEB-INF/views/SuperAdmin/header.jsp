
<% response.setHeader("Cache-Control", "no-cache"); %>
<% response.setHeader("Cache-Control", "must-revalidate"); %>
<% response.setHeader("Pragma","no-cache"); %>
<% response.setDateHeader("Last-Modified",System.currentTimeMillis());%>
<%@ page import="org.apache.commons.codec.binary.Base64"%>
<%@ page import="resources.Assets"%>
<%
/*   if (session.getAttribute("userRole")!= "Admin")
  {
	 
    String address ="/login.html";
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
    dispatcher.forward(request,response);
  } */
%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ page import="resources.Assets"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Bus Tracking</title>
<base href="<%=Assets.BASE_URL %>"></base>
<link rel="shortcut icon" href="resources/front/Images/favicon.png">

<link href="resources/dashboard/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="resources/dashboard/css/mystyle.css" rel="stylesheet"
	type="text/css">
<link href="resources/dashboard/css/responsive.css" rel="stylesheet"
	type="text/css">
<link href="resources/dashboard/css/deshboard.css" rel="stylesheet"
	type="text/css" />

<link href="resources/dashboard/css/font-awesome.css" rel="stylesheet" type="text/css">
<link href="resources/dashboard/css/font-awesome.min.css" rel="stylesheet" type="text/css">


<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="resources/dashboard/js/push.min.js"></script>
<script src="resources/dashboard/js/prism.min.js"></script>

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
<script type="text/javascript">
		jQuery(document).ready(function($){
			getTimezoneName();
			
			var full_path = location.href.split("#")[0];
			$("#cssmenu li a").each(function(){
			var $this = $(this);
			if($this.prop("href").split("#")[0] == full_path) {
				$(this).parents("li").addClass('open').siblings().removeClass('open');
				$(this).addClass('open1').siblings().removeClass('open1');	
				$(this).parents("ul").show();
						}
			});
		});
    </script>
<script>
	$(document).ready(function(){
	 
		  setInterval(function(){
			var message_old_noti=$("#message_noti_old_count").val();  
			
			$.ajax({
				type:"post",
				url:"adminMessageNotification.html",
				data:{},
				success:function(response){
				 if(response) {	
					var obj=$.parseJSON( response);
					if(parseInt(obj.message_count)>parseInt(message_old_noti))
					{
						
						 Push.create(obj.u_name, {
				             body: obj.msg,
				             icon: 'resources/dashboard/Images/noti_img.png',
				             timeout: 4000,
				             onClick: function () {
				                 window.focus();
				                 window.location = "<%=Assets.BASE_URL %>"+"admin/chattingSocket?u_id="+obj.user_id;
			                     this.close();
				             },
				             vibrate: [200, 100, 200, 100, 200, 100, 200]
				         });	
						$("#message_noti_old_count").val(obj.message_count)	
					}
				 }	
				}
			});
		}, 3000);  
 	});
</script>
</head>
<body>
 <a class="lan_switch" href="javascript:void(0);" id="lang_switcher">عربي</a>
	<div id="deshboard" class="desh-wrappper">
		<!-- Hidden value used for notification end here-->
		<input type="hidden" id="message_noti_old_count"
			value="${total_message_notification_count}"></input> <input
			type="hidden" id="document_noti_old_count"
			value="${notification_vehicle_count}"></input>
		<!-- Hidden value used for notificaion end here -->
		<header>
		<div class="head-top">
			<div class="container">

				<div class="pull-right user-blcok">
					<div class="dropdown pull-left notification_drop">
						<button class="dropdown-toggle" type="button"
							data-toggle="dropdown" style="margin: 0 1px;">
							<i class="fa fa-envelope" aria-hidden="true"></i>
							<c:if test="${total_message_notification_count gt 0}">
								<input type="hidden" id="total_count_badge" value="${total_message_notification_count}" />
								<span id="message-badge" class="badge">${total_message_notification_count}</span>
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
										<li><a class="content"
											style="background:<c:if test="${v_n.status eq 0}">#fff</c:if><c:if test="${v_n.status eq 1}">#fff</c:if>!important;;"
											href="admin/chattingSocket?u_id=${v_n.sender_id}">
												<div class="notification-item">
													<h4 class="item-title">
			<script>
		/* 	 var t = v_n.message[i].time.split(/[- :]/);
			 var d = new Date(Date.UTC(t[0], t[1]-1, t[2], t[3], t[4], t[5]));
				var options = {
			    weekday: "long", year: "numeric", month: "short",
			    day: "numeric", hour: "2-digit", minute: "2-digit"
			};
			var item=d.toLocaleDateString("en-us", options);
			$("#note_date_1").html(item); */
			</script>										
													
														<b>${v_n.name}</b>${v_n.msg } <span id="note_date_1" class="note_date">${v_n.time}</span>
													</h4>


													<%-- <p class="item-info"> ${v_n.time}</p> --%>

												</div>

										</a></li>
									</c:forEach>

								</c:when>
								<c:otherwise>
									<li style="padding: 10px 15px;">No Messages</li>
								</c:otherwise>
							</c:choose>


						</ul>
					</div>
					<div class="dropdown pull-left notification_drop">
						<%--  <button class="" type="button" data-toggle="">
						  <i class="fa fa-envelope" aria-hidden="true"></i>
						  <span class="caret"></span>
						  <c:if test="${notification_vehicle_count gt 0}">
						    <span class="badge">${notification_vehicle_count}</span>
						  </c:if>
					   </button> --%>

						<button class="dropdown-toggle" type="button"
							data-toggle="dropdown">
							<i class="fa fa-bell" aria-hidden="true"></i>
							<c:if test="${notification_vehicle_count gt 0}">
								<span class="badge">${notification_vehicle_count}</span>
							</c:if>
						</button>
						<ul class="dropdown-menu">
							<c:choose>
								<c:when test="${not empty notification_vehicle}">



									<c:forEach items="${notification_vehicle}" var="v_n">
										<c:set var="vechile_id" value="${v_n.v_id}" />
										<c:set var="v_doc_id" value="${v_n.v_doc_id}" />
										<%
						String str=pageContext.getAttribute("vechile_id").toString();
						byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
						
						String str1=pageContext.getAttribute("v_doc_id").toString();
						byte[] encodedBytes1 = Base64.encodeBase64(str1.getBytes());
						%>
										<li><c:choose>
												<c:when test="${v_n.noti_type==0}">
													<a class="content"
														style="background:<c:if test="${v_n.status eq 0}">#eeeeee</c:if><c:if test="${v_n.status eq 1}">#fff</c:if>!important;"
														href="admin/editVechile?q=<%=new String(encodedBytes)%>&v=<%=new String(encodedBytes1)%>">
														<div class="notification-item">
<h4 class="item-title">${v_n.insurance_document_name} of <b>${v_n.insurance_card_copy}</b> will be expiry on</h4>
															<p class="item-info">
																Date <b>${v_n.insurance_document_expiry}</b>
															</p>




														</div>

													</a>


												</c:when>
												<c:otherwise>
													<a class="content"
														style="background:<c:if test="${v_n.status eq 0}">#fff</c:if><c:if test="${v_n.status eq 1}">#fff</c:if>!important;"
														href="admin/viewDriver?q=<%=new String(encodedBytes)%>&v=<%=new String(encodedBytes1)%>">
														<div class="notification-item">
															<h4 class="item-title">
																<strong>${v_n.insurance_document_name} of ${v_n.insurance_card_copy}</strong>
																will be expiry on ${v_n.insurance_document_expiry}
															</h4>

															<%-- <h4 class="item-title">${v_n.insurance_document_name}
																of <b>${v_n.insurance_card_copy}</b> will be expiry on
															</h4>
															<p class="item-info">
																Date <b>${v_n.insurance_document_expiry}</b>
															</p> --%>
														</div>

													</a>
												</c:otherwise>
											</c:choose></li>
									</c:forEach>

								</c:when>
								<c:otherwise>
									<li style="padding: 10px 15px;">No Notification</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
					<div class="dropdown pull-right">
						<button class="loged-user dropdown-toggle" type="button"
							data-toggle="dropdown" aria-expanded="true">
							Hello,
							<%=session.getAttribute("f_name") %> 
							<span class="caret"></span>
						</button>
						<div class="dropdown-menu">
							<a href="logout.html">Logout</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="head-bottom">
			<div class="container">
				<div class="col-sm-3">
					<img src="resources/dashboard/Images/desh-logo.jpg" />
				</div>
				<div class="col-sm-4">
					<h2 class="desh-page-header">${heading}</h2>

				</div>



				<%  if (session.getAttribute("new_school_id")!=null)
				{ %>
				<div class="col-sm-5 pad0">


					<div class="col-sm-9 school_name">
						<h4>${school_details.school_name}</h4>
					</div>
					<div class="schol-logo pull-right col-sm-3 pad0">
						<c:choose>
							<c:when test="${school_details.school_logo!=''}">

								<img id="" alt="image_path" class="img-responsive"
									src="<%=Assets.SCHOOL_UPLOAD_PATH %>${school_details.school_logo}"
									style="width: 200px; height: auto;" />

							</c:when>
							<c:otherwise>
								<img class="img-responsive" id=""
									src="resources/dashboard/Images/s_logo_d.png" alt="image_path"
									style="width: 200px; height: auto;" />
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<% } %>
				<!--  <nav class="desh-nav navbar-right" role="navigation">
                <div class="navbar-header">
                 <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-collapse">
                   <span class="sr-only">Toggle navigation</span>
                   <span class="icon-bar"></span>
                   <span class="icon-bar"></span>
                   <span class="icon-bar"></span>
                   </button><!--//nav-toggle
                   </div><!--//navbar-header-->
				<!--     <div id="navbar-collapse" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                    <li class="active nav-item"><a href="#">Menu-1</a></li>
                    <li class="nav-item "> <a href="#">Menu-2 </a></li>
                    <li class="nav-item"><a href="#">Menu-3</a></li>
                    <li class="nav-item"><a href="#">Menu-4</a></li>
                    <li class="nav-item"><a href="#">Menu-5</a> </li>
                    </ul>//nav
                </div> -->
				<!--//navabr-collapse
            </nav> -->
			</div>
		</div>
		</header>
		<section class="desh-content">
		<div class="container">

			<%  if (session.getAttribute("new_school_id")==null)
{ %>
			<jsp:include page="sidebar_new.jsp" />
			<% }else{ %>


			<jsp:include page="sidebar.jsp" />
			<% }%>
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
				
				<%  if (session.getAttribute("new_school_id")==null)
				{ %>
				window.location = "<%=Assets.BASE_URL_AR%>admin/langSwitcher?q="+new_url+"&u=<%=session.getAttribute("user_id")%>";
				<% }else{ %>
				window.location = "<%=Assets.BASE_URL_AR%>admin/langSwitcher?q="+new_url+"&u=<%=session.getAttribute("user_id")%>&ns=<%=session.getAttribute("new_school_id")%>";
				<% }%>
				
				
			});
    	});
    
    
</script>			
			