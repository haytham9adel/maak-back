<jsp:include page="header.jsp" />
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="resources.Assets"%>
<style>
.map{position: relative;}
</style>
<script src="https://maps.googleapis.com/maps/api/js?sensor=false&libraries=drawing&key=AIzaSyA7QTJeimvMfcLMul6ZsMRZA1qOfjsyHtc"></script>
<style>
#markerLayer img {
    border-radius: 50% !important;background-image: url('resources/dashboard/Images/marker-wimg.png') !important;padding: 10px !important;background-position: 3px 6px !important;background-repeat: no-repeat;}
</style>
<script type="text/javascript">
	function getPolygonCoords() {
		var len = myPolygon.getPath().getLength();
		var htmlStr = "";
		for (var i = 0; i < len; i++) {
			if(i<len-1)
				{
				htmlStr += myPolygon.getPath().getAt(i).toUrlValue(5) + ":";
				}
			else
				{
				htmlStr += myPolygon.getPath().getAt(i).toUrlValue(5)	;
				}
			
			
		}
		$("#radius").val(htmlStr);
	}
	function copyToClipboard(text) {
		window.prompt("Copy to clipboard: Ctrl+C, Enter", text);
	}
</script>

<%@ page import="org.apache.commons.codec.binary.Base64" %>
<style type="text/css">

</style>
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

['${school_details.school_address_field}','${school_details.school_lat}','${school_details.school_lng}','resources/dashboard/Images/school_icon.png'],
['${school_details.school_name}','${route.source_lat}','${route.source_lng}','resources/dashboard/Images/blue_marker.png'],		                  
<c:forEach items="${latlong}" var="latlng">

<c:set var="student_id" value="${latlng.student_id}"/>
<%
String str=pageContext.getAttribute("student_id").toString();
byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
%>

[<c:choose><c:when test="${latlng.s_image!=''}">'<div class="map_info-win"><img  src="<%=Assets.STUDENT_UPLOAD_PATH_DIS %>/${latlng.s_image}" /></c:when><c:otherwise>'<div class="map_info-win"><img src="resources/dashboard/Images/user_icon.png" /></c:otherwise></c:choose><div><p>Name : <a class="map-link-info" href="admin/viewStudent?q=<%=new String(encodedBytes)%>">${latlng.student_name}</a></p><p>Class : ${latlng.s_class}</p></div></div>',"${latlng.lat}","${latlng.lng}",'<c:choose><c:when test="${latlng.s_image!=''}"><%=Assets.STUDENT_UPLOAD_PATH_DIS %>/${latlng.s_image}	</c:when><c:otherwise>resources/dashboard/Images/user_icon.png</c:otherwise></c:choose>'],


</c:forEach>        
['${route.destination}','${route.destination_lat}','${route.destination_lng}','resources/dashboard/Images/green_marker.png'],		                   
		      
		        
		        
		        ];

		var mapOptions = {
			// center: locations[0],
			zoom : 12,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById('dvMap'), mapOptions);
		
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
					scaledSize: new google.maps.Size(45, 45),
                    anchor: new google.maps.Point(10, 25)
				},
				optimized:false
				
			 
				
				
			});
			 var myoverlay = new google.maps.OverlayView();
		     myoverlay.draw = function () {
		    	
		         this.getPanes().markerLayer.id='markerLayer';
		     };
		     myoverlay.setMap(map);
			 
			  
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
			draggable : true, // turn off if it gets annoying
			editable : true,
			strokeColor : '#FF0000',
			strokeOpacity : 0.8,
			strokeWeight : 2,
			fillColor : '#FF0000',
			fillOpacity : 0.35
		});

		myPolygon.setMap(map);
		//google.maps.event.addListener(myPolygon, "dragend", getPolygonCoords);
		google.maps.event.addListener(myPolygon.getPath(), "insert_at",
				getPolygonCoords);
		//google.maps.event.addListener(myPolygon.getPath(), "remove_at", getPolygonCoords);
		google.maps.event.addListener(myPolygon.getPath(), "set_at",
				getPolygonCoords);

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
<body>
	<div class="col-sm-9 m-side">


		<div class="map">
			<div style=" position: absolute;right: 0;top: 0;z-index: 999;padding:15px;">
			
			<span id="succ_msg" style="color:green;font-weight: bold;font-size:14px;"></span>
			<button id="delete-button" type="submit" class="btn btn-primary btn-submit" onclick="saveGeoFancing(${route.route_id});">Save</button>
			
			</div>
			<div id="dvMap" style="width: 100%; height: 600px;"></div>
			<div>
				
				<input type="hidden" id="radius" value="${route.radius}">
			</div>

		</div>
		<div class="col-sm-12 pad0" style="margin-top: 23px;">
		<a href="javascript:void(0);" id="export" class="btn btn-primary btn-submit" style="float:right; margin:12px ">Export</a>
		<table id="st-tb" class="mdl-data-table" cellspacing="0" width="100%" id="example1">
			<thead>
				<tr>
					<th>S.No.</th>
					<th>Stundent Name</th>
					<th>Mobile Number</th>
					<th>Route Name</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody id="tbl-stude-body-status">
			   <c:set var="count" value="1" scope="page" />
			  <c:forEach items="${all_students}" var="student">    
				<tr>
					<td><c:out value="${count}"/></td>
					<td>${student.s_fname}&nbsp;${student.father_name}&nbsp;${student.grand_name}&nbsp;${student.family_name}</td>
					<td>${student.s_contact}</td>
					<td>${route.route_name}</td>
					<td><c:if test="${student.blink_status==0}">
						Checked-out
					</c:if>
					<c:if test="${student.blink_status==1}">
						Checked-in
					</c:if>
					 </td>
				</tr>
				<c:set var="count" value="${count + 1}" scope="page"/>
			</c:forEach>	
			</tbody>
		</table>
	 
	</div> 
	</div>
	</div>

</body>

<script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css">
<script>
function saveGeoFancing(route_id)
{
	var radius=$("#radius").val();
	$.ajax({
		url : 'saveGeoFancing.html',
		type : 'post',
		data : {
			"route_id" : route_id,
			"radius":radius
		},
		success : function(data) {
			swal(
					{
						title : "Saved Successfully",
						text : "",
						type : "success"
					}, function() {
						$('#myModal').modal('show');
					});
		//	$("#succ_msg").html("Saved Successfully");
		//	setTimeout(function(){ $("#succ_msg").html(""); }, 3000);
		}
	});
}
</script>
<link href="https://cdn.datatables.net/1.10.13/css/dataTables.material.min.css" rel="stylesheet" type="text/css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/material-design-lite/1.1.0/material.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js">
</script>
<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.13/js/dataTables.material.min.js">
</script>
<script>
 $(document).ready(function() {

	    $('#st-tb').DataTable({
	    	"pagingType": "simple_numbers",
	    	    "oLanguage": {
	    	    	  "sUrl": "resources/dashboard/js/english.json"
	    	    	},
	   	
		 columnDefs: [
		              {
		                  targets: [ 0, 1 ],
		                  className: 'mdl-data-table__cell--non-numeric'
		              }
		          ],
			});
	});
 
 </script>
<script src="resources/dashboard/js/jquery.table2excel.js"></script>
<script>
$(function() {
	$("#export").on('click',function(){
		  $("#st-tb").table2excel({
		    name: "Worksheet Name",
		    filename: "Route Details" //do not include extension
		  }); 
});
});
</script>
  
<jsp:include page="footer.jsp" />
