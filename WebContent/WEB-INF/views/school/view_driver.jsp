<jsp:include page="header.jsp" />
<%@ page import="resources.Assets"%>
<%@ page import="java.util.*" %> 
<%@ page import="org.apache.commons.codec.binary.Base64" %> 
<link href="//cdn.datatables.net/1.10.7/css/jquery.dataTables.css"
	rel="stylesheet" type="text/css">
<script src="resources/dashboard/js/jquery.dataTables.min.js"></script>
<script>
$(document).ready(function() {
	$('#example').DataTable();
});
</script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-md-9 m-side">


	<div class="new-student-form std_frm">
		<form class="form-horizontal" id="student" method="post">
			<div class="form-group">

<label class="col-sm-3 control-label" style="padding:18px 0;">Profile Picture :</label>
<div class="col-sm-9">
<c:choose>
				<c:when test="${driver.image_path!=''}"> 
				<img id="blah" style="width:85px;padding: 5px 0"  alt="image_path" style="border-radius:50%;"  class="img-responsive"  src="<%=Assets.DRIVER_UPLOAD_PATH_DIS %>/${driver.image_path}"/>
				
				</c:when>
				<c:otherwise>
				<img id="blah" style="width: 85px;padding: 5px 0"  style="border-radius:50%;"  class="img-responsive"  src="resources/dashboard/Images/user_icon.png" alt="image_path" />
				</c:otherwise>
				</c:choose>
</div>
</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Name :</label>
				<div class="col-sm-9">${driver.driver_fname}
${driver.middle_name} ${driver.driver_lname}</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label">Date of birth :</label>
	<div class="col-sm-9">${driver.dob}</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label">Email :</label>
	<div class="col-sm-9">${driver.d_email}</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label">Mobile Number :</label>
	<div class="col-sm-9">${driver.contact_number}</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label">UserName :</label>
	<div class="col-sm-9">${driver.username}</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label">Password :</label>
	<div class="col-sm-9">${driver.password}</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label">Nationality :</label>
	<div class="col-sm-9">${driver.nationality}</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label">Blood Group :</label>
	<div class="col-sm-9">${driver.blood_group}</div>
</div>

<div class="form-group">
	<label class="col-sm-3 control-label">License Expiry Date :</label>
	<div class="col-sm-9">${driver.licence_expiry}</div>
		</div>
<div class="form-group">
	<label class="col-sm-3 control-label">Driver Route :</label>
	<div class="col-sm-9">${route.route_name}</div>
		</div>
		<c:set var="driver_id" value="${driver.driver_id}"/>
	<%
	String str=pageContext.getAttribute("driver_id").toString();
	byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
	%>
<div class="form-group">
	<label class="col-sm-3 control-label">Driver ID :</label>
	<div class="col-sm-9">${driver.driver_id}<!-- <%=new String(encodedBytes)%> --></div>
		</div>
	</form>
</div>
<c:set var="count" value="${count + 1}" scope="page" />
<div style="clear: both;">&nbsp;</div>
<br></br>

<div class="form-group clearfix">
	<div class="col-sm-12">
		<div class="col-sm-3">
			<a href="school/trackDriver?q=<%=new String(encodedBytes)%>">
	<button type="button" class="btn btn-primary btn-submit">Track
		the Driver</button>
</a>
<div style="clear: both;">&nbsp;</div>
<a href="school/viewDriverAttendance?q=<%=new String(encodedBytes)%>">
		<button type="button" class="btn btn-primary btn-submit">View
			Attendance</button>
	</a>
</div>
<div class="col-sm-9">
	<button style="float: right;" type="button" class="btn btn-primary btn-submit"
onclick="PrintElem('#mydiv')">Print</button>
<div id="mydiv">
	<div class="card_visit"
		style="width: 320px; height: 203px; border: 1px solid #ccc; background: #eee; position: relative; overflow: hidden;">
<div class="detail-card col-sm-12"  style="padding: 0;">
	<div class="col-sm-12 pad-0" style="padding: 0;">
<div class="profile_img pull-left"
	style="float: left; padding: 3px; width: 22%;">
	
				<c:choose>
				<c:when test="${driver.image_path!=''}"> 
				<img id="blah" style="width: 50px;padding: 5px 0"  alt="image_path" style="border-radius:50%;"  class="img-responsive"  src="<%=Assets.BASE_URL %><%=Assets.DRIVER_UPLOAD_PATH_DIS %>/${driver.image_path}"/>
				
				</c:when>
				<c:otherwise>
				<img id="blah" style="width: 50px;padding: 5px 0"  style="border-radius:50%;"  class="img-responsive"  src="<%=Assets.BASE_URL %>resources/dashboard/Images/user_icon.png" alt="image_path" />
				</c:otherwise>
				</c:choose>
	
</div>
<div class="profile_img logo" style="float: right; width: 54px;">
<c:choose>
<c:when test="${school_details.school_logo!=''}">

<img id=""  alt="image_path"  class="img-responsive" src="<%=Assets.BASE_URL %><%=Assets.SCHOOL_UPLOAD_PATH %>${school_details.school_logo}" style="width: 50px;padding: 5px 0"/>

</c:when>
<c:otherwise>
<img class="img-responsive" id="" src="<%=Assets.BASE_URL %>resources/dashboard/Images/s_logo_d.png" alt="image_path" style="width: 50px;padding: 5px 0;"/>
</c:otherwise>
</c:choose>
	 
</div>
<div style="float: left; text-align: center; width: 56%; font-weight: bold; font-size: 13px;">${school_details.school_name}</div>
</div>

<div class="pad-0 clearfix col-sm-12"
	style="padding: 0; clear: both;">
<div class="student_card_detail pull-left"
	style="float: left; width: 70%; overflow: hidden; padding: 12px 5px;">
	<p
		style="overflow: hidden; margin: 0px; font-size: 15px; line-height: 15px;">
		<span
			style="margin-bottom: 7px; float: left; margin-right: 10px; font-weight: bold;">Name</span><span
			style="margin-bottom: 7px; float: left; margin-right: 10px;">${driver.driver_fname}
${driver.middle_name} ${driver.driver_lname}</span>
	</p>
	<p
		style="overflow: hidden; margin: 0px; font-size: 15px; line-height: 15px;">
		<span
			style="margin-bottom: 7px; float: left; margin-right: 10px; font-weight: bold;">Nationality</span><span
			style="margin-bottom: 7px; float: left; margin-right: 10px;">${driver.nationality}</span>
	</p>
	<p
		style="overflow: hidden; margin: 0px; font-size: 15px; line-height: 15px;">
		<span
			style="margin-bottom: 7px; float: left; margin-right: 10px; font-weight: bold;">DOB</span><span
			style="margin-bottom: 7px; float: left; margin-right: 10px;">${driver.dob}</span>
	</p>
	<p
		style="overflow: hidden; margin: 0px; font-size: 15px; line-height: 15px;">
		<span
			style="margin-bottom: 7px; float: left; margin-right: 10px; font-weight: bold;">Blood
			group</span><span
			style="margin-bottom: 7px; float: left; margin-right: 10px;">${driver.blood_group}</span>
	</p>
</div>
<div class="student_id_qr pull-right"
	style="margin-top: 14px; position: absolute; right: 0px; width: 100px; height: 100px; bottom: 0px;">
	<img style="width: 100px;"
		src="<%=Assets.BASE_URL %><%=Assets.STUDENT_QR_PATH %>d_${driver.driver_id}.png"
					class="img-responsive" />
			</div>
		</div>
	</div>
</div>
<!-- card_visit -->
</div>
			</div>

		</div>
	</div>

<div class="input_fields_wrap clearfix show_form_bts">
			<% int j1=0; %>
			  <c:forEach items="${documents}" var="doc">
				<div class="panel section_repeat clearfix" id="">
				
					<div class="form-group clearfix">
						<label class="col-sm-4 control-label">Document Name :</label>
						<div class="col-sm-8">
							${doc.insurance_document_name}
							
						</div>
					</div><!-- form-group -->
					<div class="form-group clearfix">
						<label class="col-sm-4 control-label">Expiry Date :</label>
						<div class="col-sm-8">
							
								${doc.insurance_document_expiry}
							
						</div>
					</div><!-- form-group -->
					
					<div class="form-group clearfix" >
						<label class="col-sm-4 control-label">Remind before days :</label>
						<div class="col-sm-8">
							
								${doc.remind_day}
								
						</div>
					</div><!-- form-group -->
					<div class="form-group clearfix">
						<label class="col-sm-4 control-label">Document copy :</label>
						<div class="col-sm-8">
							
								${doc.insurance_card_copy}
								
								
						</div>
					</div><!-- form-group -->
					<div class="col-sm-12" ><a download="download" class="btn btn-warning"  href="<%=Assets.INSURANCE_CARD_COPY %>${doc.insurance_card_copy}" />Download</a></div>
				</div><!-- panel -->
				<% j1++; %>
				 </c:forEach>
				
			</div><!-- input_fields_wrap -->

</div>

<div class="col-sm-12 col-xs-12 new-student-form view_student_detail">
	<%--   <div class="form-group">
          <label class="col-sm-3 control-label">Route:</label>
            <div class="col-sm-3">
           
             <c:if test="${!empty routes}">
              	<c:forEach items="${routes}" var="route">
              	<c:if test="${route.route_id==latlng.route_id}">
              		${route.route_name}
              	</c:if>
              	${route.route_name}
              	</c:forEach>
              </c:if>
            
           <select class="form-control" disabled="disabled" id="route_id" name="route_id" onchange="return getRouteMap1(this.value);"  >
              <option value="">Select Route</option>
              <c:if test="${!empty routes}">
              	<c:forEach items="${routes}" var="route">
              	<option
              		<c:if test="${route.route_id==latlng.route_id}">
              	<c:out value="selected"/>
              	 </c:if>
              	 value=<c:out value="${route.route_id}"/>><c:out value="${route.route_name}"/></option>
              	</c:forEach>
              </c:if>
              </select> 
          </div>
     </div> --%>
<div style="clear: both;">&nbsp;</div>
<br></br>


</div></div>
<div class="form-group">
	<div class="col-sm-12">
		<div id="map_canvas" style="width: 100%; height: 500px;"></div>
	</div>
</div>
<div class="clearfix"></div>

<script src="http://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&key=AIzaSyA7QTJeimvMfcLMul6ZsMRZA1qOfjsyHtc"></script>
 
            <script type="text/javascript">
	var directionsDisplay = [];
	var directionsService = [];
	var map = null;

	function calcRoute() {
		
		
		var msg = '${route.source_lat},${route.source_lng}:${route.source_lat},${route.source_lng}:<c:forEach items="${latlong}" var="latlng"> ${latlng.lat},${latlng.lng}:</c:forEach>${route.destination_lat},${route.destination_lng}:${route.destination_lat},${route.destination_lng}';
		
		var input_msg = msg.split(":");
		var locations = new Array();
		//alert(input_msg.length);
		var bounds = new google.maps.LatLngBounds();
		for (var i = 0; i < input_msg.length; i++) {
			var tmp_lat_lng = input_msg[i].split(",");
			locations.push(new google.maps.LatLng(tmp_lat_lng[0],
					tmp_lat_lng[1]));
			bounds.extend(locations[locations.length - 1]);
		}
		var contentString='';
		var locations1 = [ 
		                  
['${route.source}','${route.source_lat}','${route.source_lng}','resources/dashboard/Images/source_icon.png'],		                  

['${route.destination}','${route.destination_lat}','${route.destination_lng}','resources/dashboard/Images/desti.png'],		                   
		      
		        
		        
		        ];

		var mapOptions = {
			// center: locations[0],
			zoom : 12,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
		map.fitBounds(bounds);
		google.maps.event.addDomListener(window, 'resize', function() {
			google.maps.event.trigger(map, 'resize');
			map.fitBounds(bounds);
		});

		var infowindow = new google.maps.InfoWindow();

		var marker, i;
		 var index=0;
		for (i = 0; i < locations1.length; i++) {
			marker = new google.maps.Marker({
				position : new google.maps.LatLng(locations1[i][1],
						locations1[i][2]),
				map : map,
				icon :{ url:locations1[i][3],
					scaledSize: new google.maps.Size(30, 30),
                    anchor: new google.maps.Point(10, 25)
			}
				
				
			});
			
			 marker = new google.maps.Marker({
		          map: map,
		          position: new google.maps.LatLng("${school_details.school_lat}","${school_details.school_lng}"),
		          draggable: false,
		          icon: "resources/dashboard/Images/school_icon.png"
		  });
			google.maps.event.addListener(marker, 'click',
					(function(marker, i) {
						return function() {
							infowindow.setContent(locations1[i][0]);
							infowindow.open(map, marker);
						}
					})(marker, i));
		}

		var oldRadius="${route.radius}";
		var resArr = oldRadius.split(":");
		var resStr='';
		var pos;
		var triangleCoords=[];
		
if(resArr.length>1)
{
	
	
}else{
	triangleCoords.push(new google.maps.LatLng(${route.source_lat},${route.source_lng}));
}
		                      
		
		
	
		
		
		if(resArr.length>1)
			{
			
			
		for(var m=0;m<resArr.length;m++)
		{
			if(m==resArr.length-1)
			{
				resStr+=resArr[m];
			}else{
					resStr+=resArr[m]+",";	
			}
				
		}	
		resArr1 = resStr.split(",");
		//console.log(resArr1.length);
		for(var n=0;n<resArr1.length;n++)
		{
			//console.log("H-"+resArr1[n]);
			//console.log("P-"+resArr1[n+1]);
		  pos=new google.maps.LatLng(resArr1[n],resArr1[n+1]);
		  triangleCoords.push(pos);
		  n=n+1;
		} 
		
			}
		
		if(resArr.length>1)
		{
			
			
		}else{
			triangleCoords.push(new google.maps.LatLng(${route.destination_lat},${route.destination_lng}));
		}
			
		
		
		//console.log("Test"+triangleCoords);
		// Styling & Controls
		myPolygon = new google.maps.Polygon({
			paths : triangleCoords,
			draggable : false, // turn off if it gets annoying
			editable : false,
			strokeColor : '#FF0000',
			strokeOpacity : 0.8,
			strokeWeight : 2,
			fillColor : '#FF0000',
			fillOpacity : 0.35
		});

		myPolygon.setMap(map);
		//google.maps.event.addListener(myPolygon, "dragend", getPolygonCoords);
		//google.maps.event.addListener(myPolygon.getPath(), "insert_at",
		//		getPolygonCoords);
		//google.maps.event.addListener(myPolygon.getPath(), "remove_at", getPolygonCoords);
		//google.maps.event.addListener(myPolygon.getPath(), "set_at",
		//		getPolygonCoords);

		var i = locations.length;
		var index = 0;

		while (i != 0) {

			if (i < 3) {
				var tmp_locations = new Array();
				for (var j = index; j < locations.length; j++) {
					tmp_locations.push(locations[index]);
				}
				drawRouteMap(tmp_locations);
				i = 0;
				index = locations.length;
			}

			if (i >= 3 && i <= 10) {
			//	console.log("before :fun < 10: i value " + i + " index value"
			//			+ index);
				var tmp_locations = new Array();
				for (var j = index; j < locations.length; j++) {
					tmp_locations.push(locations[j]);
				}
				drawRouteMap(tmp_locations);
				i = 0;
				index = locations.length;
			//	console.log("after fun < 10: i value " + i + " index value"
			//			+ index);
			}

			if (i >= 10) {
			//	console.log("before :fun > 10: i value " + i + " index value"
			//			+ index);
				var tmp_locations = new Array();
				for (var j = index; j < index + 10; j++) {
					tmp_locations.push(locations[j]);
				}
				drawRouteMap(tmp_locations);
				i = i - 9;
				index = index + 9;
			//	console.log("after fun > 10: i value " + i + " index value"
			//			+ index);
			}
		}
	}

	function drawRouteMap(locations) {
	console.log(locations);
		var start, end;
		var waypts = [];

		for (var k = 0; k < locations.length; k++) {
			if (k >= 1 && k <= locations.length - 2) {
				waypts.push({
					location : locations[k],
					stopover : true
				});
			}
			if (k == 0)
				start = locations[k];

			if (k == locations.length - 1)
				end = locations[k];

		}
		var request = {
			origin : start,
			destination : end,
			waypoints : waypts,
			travelMode : google.maps.TravelMode.DRIVING
		};
		// console.log(request);

		directionsService.push(new google.maps.DirectionsService());
		var instance = directionsService.length - 1;
		directionsDisplay.push(new google.maps.DirectionsRenderer({
			preserveViewport : true,
			suppressMarkers : true
		}));
		directionsDisplay[instance].setMap(map);
		directionsService[instance].route(request, function(response, status) {
			if (status == google.maps.DirectionsStatus.OK) {
			//	console.log(status);
				directionsDisplay[instance].setDirections(response);
			}
		});
	}

	google.maps.event.addDomListener(window, 'load', calcRoute);
</script>
<script type="text/javascript">

function PrintElem(elem)
{
    Popup($(elem).html());
}

function Popup(data) 
{ 
  
	 var docType = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
     var disp_setting = "toolbar=yes,location=no,directories=yes,menubar=yes,";
     disp_setting += "scrollbars=yes,width=600, height=400, left=50, top=25,_blank";
     if (navigator.appName != "Microsoft Internet Explorer")
         disp_setting = "";

     var content_vlue = document.getElementById("mydiv").innerHTML;
     var docprint = window.open("", "", disp_setting);
     docprint.document.open();

     docprint.document.write(docType);
     docprint.document.write('<head><title></title>');
     //document.write('<link rel="stylesheet" media="print" href="resources/dashboard/css/print.css" type="text/css" />');
      docprint.document.write('</head>');
   //  docprint.document.write('</head><body style="padding:0;margin-top:0 !important;margin-bottom:0!important;"   onLoad="self.print();self.close();">');

     docprint.document.write(content_vlue);
     docprint.document.write('</body></html>');
     //document.write(doct + divContent.innerHTML);
     docprint.document.close();
     docprint.focus();
}

</script>
<jsp:include page="footer.jsp" />
