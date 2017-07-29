	<jsp:include page="header.jsp" />
	<%@ page import="resources.Assets"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="java.util.*" %> 
	<%@ page import="org.apache.commons.codec.binary.Base64" %> 
	<div class="col-md-6 col-sm-6 col-xs-12 m-side">
		<div class="col-sm-3 col-xs-12  user-icon">
	
			<c:choose>
			<c:when test="${student.s_image_path!=''}">
				<td><img src="<%=Assets.STUDENT_UPLOAD_PATH_DIS %>/${student.s_image_path}" />
	
				</td>
				<br />
			</c:when>
	
			<c:otherwise>
				<td><img src="resources/dashboard/Images/user_icon.png" /></td>
				<br />
			</c:otherwise>
		</c:choose>
		<c:set var="student_id" value="${student.student_id}"/>
		<%
		String str=pageContext.getAttribute("student_id").toString();
		byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
		%>
		<br /> <a href="admin/TrackStudent?q=<%=new String(encodedBytes)%>" style="margin-bottom:0px;display: block;"><button
					class="btn btn-primary btn-submit">Track</button></a>
		<br> <a
				href="admin/viewAttendance?q=<%=new String(encodedBytes)%>"><button
					class="btn btn-primary btn-submit">View Attendance</button></a>
		</div>
	
	<%-- <div class="col-sm-9 col-xs-12 new-student-form view_student_detail">
		<span class="success">${success}</span> <span class="error">${error}</span>
		<form class="form-horizontal" id="student" method="post">
	
	
			<div class="form-group">
				<label class="col-sm-3 control-label">First Name :</label>
				<div class="col-sm-9">
					<label class="col-sm-12 control-label"> ${student.s_fname}</label>
				</div>
	
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Last Name :</label>
				<div class="col-sm-9">
					<label class="col-sm-12 control-label">${student.s_lname}</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Email :</label>
				<div class="col-sm-9">
					<label class="col-sm-12 control-label">${student.s_email}</label>
				</div>
			</div>
			    <div class="form-group">
	                    <label class="col-sm-3 control-label">Address :</label>
	                      <div class="col-sm-9">
	                        <label class="col-sm-12 control-label">${student.s_address}</label>
	                      </div>
	                  </div>
	                     <div class="form-group">
	                    <label class="col-sm-3 control-label">City:</label>
	                      <div class="col-sm-9">
	                        <label class="col-sm-12 control-label">${city.city_name}</label>
	                      </div>
	                  </div>
	                  <div class="form-group">
	                    <label class="col-sm-3 control-label">Country:</label>
	                      <div class="col-sm-9">
	                        <label class="col-sm-12 control-label">${country.c_name}</label>
	                      </div>
	                  </div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Contact Number:</label>
				<div class="col-sm-9">
					<label class="col-sm-12 control-label">${student.s_contact}</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">ID:</label>
				<div class="col-sm-9">
					<label class="col-sm-12 control-label"><%=new String(encodedBytes)%></label>
				</div>
			</div>
			
			    <div class="form-group">
	                    <label class="col-sm-3 control-label">Zip Code:</label>
	                      <div class="col-sm-9">
	                        <label class="col-sm-12 control-label">${student.s_zip}</label>
	                      </div>
	                  </div>
		</form>
	</div> --%>
	
	<div class="col-sm-8 col-xs-12 new-student-form view_student_detail" style="margin-left:41px;">
		<span class="success">${success}</span> <span class="error">${error}</span>
		<form class="form-horizontal" id="student" method="post">


			<div class="form-group">
				<label class="col-sm-4 control-label">First Name :</label>
				<div class="col-sm-8">
					<label class="col-sm-12 control-label"> ${student.s_fname}</label>
				</div>

			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Father Name :</label>
				<div class="col-sm-8">
					<label class="col-sm-12 control-label">${student.father_name}</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Grand Name</label>
				<div class="col-sm-8">
					<label class="col-sm-12 control-label">${student.grand_name}</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Family Name :</label>
				<div class="col-sm-8">
					<label class="col-sm-12 control-label">${student.family_name}</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Email :</label>
				<div class="col-sm-9">
					<label class="col-sm-12 control-label">${student.s_email}</label>
				</div>
			</div>
			<%--     <div class="form-group">
                     <label class="col-sm-3 control-label">Address :</label>
                       <div class="col-sm-9">
                         <label class="col-sm-12 control-label">${student.s_address}</label>
                       </div>
                   </div>
                      <div class="form-group">
                     <label class="col-sm-3 control-label">City:</label>
                       <div class="col-sm-9">
                         <label class="col-sm-12 control-label">${city.city_name}</label>
                       </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Country:</label>
                       <div class="col-sm-9">
                         <label class="col-sm-12 control-label">${country.c_name}</label>
                       </div>
                   </div> --%>
			<div class="form-group">
				<label class="col-sm-3 control-label">Mobile Number:</label>
				<div class="col-sm-9">
					<label class="col-sm-12 control-label">${student.s_contact}</label>
				</div>
			</div>
			<div class="form-group" style="margin-bottom:12px;">
				<label class="col-sm-3 control-label">ID:</label>
				<div class="col-sm-9">
					<label class="col-sm-12 control-label">${student.student_id}<!-- <%=new String(encodedBytes)%> --></label>
				</div>
			</div>
			<%--     <div class="form-group">
                     <label class="col-sm-3 control-label">Zip Code:</label>
                       <div class="col-sm-9">
                         <label class="col-sm-12 control-label">${student.s_zip}</label>
                       </div>
                   </div> --%>
		</form>
	</div>
	
	
	
	<div class="col-md-12" style="margin-bottom: 25px; padding: 21px;">
		
		
		<!-- Start of card -->
		<button style="float: right;" type="button" class="btn btn-primary btn-submit"
onclick="PrintElem('#mydiv')">Print</button>
<div id="mydiv">
	<div class="card_visit"
		style="width: 320px; height: 203px; border: 1px solid #ccc; background: #eee; position: relative; overflow: hidden;">
<div class="detail-card col-sm-12"  style="padding: 0;">
	<div class="col-sm-12 pad-0" style="padding: 0;">
<div class="profile_img pull-left"
	style="float: left; padding: 3px; width: 22%;" >
	
	
	
		<c:choose>
		<c:when test="${student.s_image_path!=''}">
		<img style="width: 50px;padding: 5px 0" style="border-radius:50%;" 	src="<%=Assets.BASE_URL %><%=Assets.STUDENT_UPLOAD_PATH_DIS %>/${student.s_image_path}">
		</c:when>
		
		<c:otherwise>
		<img style="width: 50px;padding: 5px 0" style="border-radius:50%;" 	src="<%=Assets.BASE_URL %>resources/dashboard/Images/user_icon.png">
		</c:otherwise>
		</c:choose>
		
		
</div>
<div style="float: left; text-align: center; width: 56%; font-weight: bold; font-size: 13px;">${school_details.school_name}</div>

<div class="profile_img logo" style="float: right; text-align: right; width: 22%; vertical-align: top; position: absolute; right: 0px; top: 0px;">
<c:choose>
<c:when test="${school_details.school_logo!=''}">

<img id=""  alt="image_path"  class="img-responsive" src="<%=Assets.BASE_URL %><%=Assets.SCHOOL_UPLOAD_PATH %>${school_details.school_logo}" style="width: 50px; padding: 5px 0px; float: right;"/>

</c:when>
<c:otherwise>
<img class="img-responsive" id="" src="<%=Assets.BASE_URL %>resources/dashboard/Images/s_logo_d.png" alt="image_path" style="width: 50px; padding: 5px 0px; float: right;"/>
</c:otherwise>
</c:choose>
	 
	</div>
</div>
<div class="pad-0 clearfix col-sm-12"
	style="padding: 0; clear: both;">
<div class="student_card_detail pull-left"
	style="float: left; width: 70%; overflow: hidden; padding: 12px 5px;">
	<%-- <p
		style="font-size: 13px; line-height: 12px; overflow: hidden; margin: 0;">
		<span
			style="margin-bottom: 7px; float: left; margin-right: 10px; font-weight: bold;">School</span><span
			style="margin-bottom: 7px; float: left; margin-right: 10px;">${school_details.school_name} </span>
	</p> --%>
	 
	<p
		style="overflow: hidden; margin: 0px; font-size: 15px; line-height: 15px;">
		<span
			style="margin-bottom: 7px; float: left; margin-right: 10px; font-weight: bold;">Name</span><span
			style="margin-bottom: 7px; float: left; margin-right: 10px;">${student.s_fname} </span>
	</p>
	<p
		style="overflow: hidden; margin: 0px; font-size: 15px; line-height: 15px;">
		<span
			style="margin-bottom: 7px; float: left; margin-right: 10px; font-weight: bold;">Id Number</span><span
			style="margin-bottom: 7px; float: left; margin-right: 10px;">STUD-${student.student_id}</span>
	</p>
	<p
		style="overflow: hidden; margin: 0px; font-size: 15px; line-height: 15px;">
		<span
			style="margin-bottom: 7px; float: left; margin-right: 10px; font-weight: bold;">DOB</span><span
			style="margin-bottom: 7px; float: left; margin-right: 10px;">${student.dob}</span>
	</p>
	<p
		style="overflow: hidden; margin: 0px; font-size: 15px; line-height: 15px;">
		<span
			style="margin-bottom: 7px; float: left; margin-right: 10px; font-weight: bold;">Blood
			group</span><span
			style="margin-bottom: 7px; float: left; margin-right: 10px;">${student.blood_type}</span>
	</p>
</div>
<div class="student_id_qr pull-right"
	style="margin-top: 14px; position: absolute; right: 0px; width: 100px; height: 100px; bottom: 0px;">
	<img style="width: 100px;"
		src="<%=Assets.BASE_URL %><%=Assets.STUDENT_QR_PATH %>s_${student.student_id}.png""
					class="img-responsive" />
			</div>
		</div>
	</div>
</div>
<!-- card_visit -->
</div>
		
		
		
		
		
		
		
		
		<!-- End of Card -->
			<%--<div class="print_btn" style="margin-bottom: 25px; padding: 21px;">
			<input style="float: right;" type="button"
				class="btn btn-primary btn-small" value="Print"
				onclick="PrintElem('#print')" />
		</div>
	
	
	 <table width="243" height="153" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr>
				<td align="left" valign="top"><table width="100%" border="0"
						cellspacing="0" cellpadding="0">
						<tr class="header">
							<td height="25" align="left" valign="top" bgcolor="#F3BE4E"><table
									width="100%" border="0" cellspacing="0" cellpadding="3"
									style="font-size: 14px; color: #fff;">
									<tr>
										<td width="5" align="left" valign="top">&nbsp;</td>
										<td width="40" align="left" valign="middle"><c:choose>
												<c:when test="${school_details.school_logo!=''}">
													<img
														src="<%=Assets.SCHOOL_UPLOAD_PATH %>${school_details.school_logo}"
														style="width: 45px; height: auto;" />
												</c:when>
												<c:otherwise>
													<img class="img-responsive" id=""
														src="resources/dashboard/Images/s_logo_d.png"
														alt="image_path" style="width: 37px; height: auto;" />
												</c:otherwise>
											</c:choose></td>
										<td align="center" valign="middle" style="font-size: 15px;">${school_details.school_name}</td>
										<td width="40" align="left" valign="middle">&nbsp;</td>
										<td width="5" align="left" valign="top">&nbsp;</td>
									</tr>
								</table></td>
						</tr>
						<tr class="middle">
							<td height="" align="left" valign="top"><table width="100%"
									border="0" cellspacing="0" cellpadding="2">
									<tr>
										<td width="5" align="left" valign="top">&nbsp;</td>
										<td align="left" valign="top"><table width="100%"
												border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="85" align="left" valign="top"><c:choose>
															<c:when test="${student.s_image_path!=''}">
																<img width="100" height="99"
																	src="<%=Assets.STUDENT_UPLOAD_PATH_DIS %>/${student.s_image_path}">
															</c:when>
	
															<c:otherwise>
																<img width="100" height="99"
																	src="resources/dashboard/Images/user_icon.png">
															</c:otherwise>
														</c:choose></td>
													<td width="10" align="left" valign="top">&nbsp;</td>
													<td align="left" valign="top"><table width="100%"
															border="0" cellspacing="0" cellpadding="0"
															style="font-size: 10px;">
															<tr>
																<td align="left" valign="top" style="color: #F3BE4E;">STUDENT</td>
															</tr>
															<tr>
																<td align="left" valign="top">${student.s_fname}
																	${student.s_lname}</td>
															</tr>
															<tr>
																<td align="left" valign="top">&nbsp;</td>
															</tr>
															<tr>
																<td align="left" valign="top" style="color: #F3BE4E;">PARENT</td>
															</tr>
															<tr>
																<td align="left" valign="top">1234567892</td>
															</tr>
														</table></td>
													<td align="left" valign="top"><table width="100%"
															border="0" cellspacing="0" cellpadding="0"
															style="font-size: 10px;">
															<tr>
																<td align="left" valign="top" style="color: #F3BE4E;">&nbsp;</td>
																<td align="left" valign="top" style="color: #F3BE4E;">ID
																	NO.</td>
															</tr>
															<tr>
																<td width="10" align="left" valign="top">&nbsp;</td>
																<td align="left" valign="top">STUD-${student.student_id}</td>
															</tr>
															<tr>
																<td align="left" valign="top">&nbsp;</td>
																<td align="left" valign="top">&nbsp;</td>
															</tr>
															<tr>
																<td align="left" valign="top">&nbsp;</td>
																<td align="left" valign="top">&nbsp;</td>
															</tr>
															<tr>
																<td align="left" valign="top">&nbsp;</td>
																<td align="left" valign="top"><img
																	src="<%=Assets.STUDENT_QR_PATH %>s_${student.student_id}.png"
																	width="150" height="150" /></td>
															</tr>
														</table></td>
												</tr>
											</table></td>
										<td width="5" align="left" valign="top">&nbsp;</td>
									</tr>
								</table></td>
						</tr>
						<tr class="content">
							<td height="25" align="left" valign="top" bgcolor="#F3BE4E"><table
									width="100%" border="0" align="center" cellpadding="2"
									cellspacing="0" style="font-size: 12px; color: #fff;">
									<tr>
										<td width="5" align="left" valign="top">&nbsp;</td>
										<td align="center" valign="middle" style="font-size: 15px;">&nbsp;School Contact-${school_details.school_address}</td>
											<td width="5" align="left" valign="top">&nbsp;</td>
										</tr>
									</table></td>
							</tr>
						</table></td>
				</tr>
			</table> --%>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		</div>
	
	</div>
	<div class="col-md-3 col-sm-3 col-xs-12">
	
		<c:forEach items="${allParent}" var="parent">
		<div class="info-block">
			<h4>
				<b>Parent - ${count + 1}</b>
			</h4>
			<div class="info-group">
				<div class="col-a">Name :</div>
				<div class="col-b">${parent.first_name} ${parent.last_name}</div>
			</div>
			<div class="info-group">
				<div class="col-a">Relationship :</div>
				<div class="col-b">
				
				
				<c:choose>
				<c:when test="${parent.user_id==student.p_1}">
					${student.r_1}
				</c:when>
				<c:when test="${parent.user_id==student.p_2}">
					${student.r_2}
				</c:when>
				<c:otherwise>
					${student.r_3}
				</c:otherwise>
				</c:choose>
				
				</div>
			</div>
			<div class="info-group">
				<div class="col-a">Email :</div>
				<div class="col-b">${parent.user_email}</div>
			</div>
			<div class="info-group">
				<div class="col-a">Contact Number :</div>
				<div class="col-b">${parent.contact_number}</div>
			</div>
		</div>
		<c:set var="count" value="${count + 1}" scope="page" />
	</c:forEach>
	
	
	
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
	<div class="form-group">
		<div class="col-sm-12">
			<div id="map_canvas" style="width: 100%; height: 500px;"></div>
			</div>
		</div>
	
	</div>
	<script
		src="http://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&key=AIzaSyA7QTJeimvMfcLMul6ZsMRZA1qOfjsyHtc"></script>
	<script type="text/javascript">
	+$(document).ready(function() {
		getRouteMap("${latlng.route_id}");
	});
	var latiArr = [];
	var longiArr = [];
	function init(a, b, latiLongi, sourceDesti) {
		var infowindow = new google.maps.InfoWindow();
	
		latiArr = [];
		longiArr = [];
		latiArr.pop();
		longiArr.pop();
		var latStr = '';
		var lngStr = '';
		longiArr.push(sourceDesti.slng);
		latiArr.push(sourceDesti.slat);
		latiArr.push("${latlng.lat}");
		longiArr.push("${latlng.lng}");
		latiArr.push(sourceDesti.dlat);
		longiArr.push(sourceDesti.dlng);
	
		var sourceLat;
		var sourceLng;
		var destLat;
		var destLng;
		var markers = [], segments = [], myOptions = {
			zoom : 13,
			center : new google.maps.LatLng(sourceDesti.slat, sourceDesti.slng),
			mapTypeId : google.maps.MapTypeId.ROADMAP,
			disableDoubleClickZoom : true,
			draggableCursor : "crosshair"
		}, alphas = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split(''),
		//   alphas = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split(''),
		alphaIdx = 0, map = new google.maps.Map(document
				.getElementById("map_canvas"), myOptions), service = new google.maps.DirectionsService(), poly = new google.maps.Polyline(
				{
					map : map,
					strokeColor : '#00FFFF',
					strokeOpacity : 0.6,
					strokeWeight : 5
				});
	
		window.resetMarkers = function() {
			for (var i = 0; i < markers.length; i++) {
				markers[i].setMap(null);
			}
			alphaIdx = 0;
			segments = [];
			markers = [];
			poly.setPath([]);
		};
		function getSegmentsPath() {
			var a, i, len = segments.length, arr = [];
			for (i = 0; i < len; i++) {
				a = segments[i];
				if (a && a.routes) {
					arr = arr.concat(a.routes[0].overview_path);
				}
			}
			return arr;
		}
	
		function addSegment(start, end, segIdx) {
	
			service.route({
				origin : start,
				destination : end,
				travelMode : google.maps.DirectionsTravelMode.DRIVING
			}, function(result, status) {
				if (status == google.maps.DirectionsStatus.OK) {
					//store the entire result, as we may at some time want
					//other data from it, such as the actual directions
					segments[segIdx] = result;
	
					poly.setPath(getSegmentsPath());
	
				}
	
			});
	
			//addLatLong(start);
		}
	
		for (var p = 0; p < latiArr.length; p++) {
			var ic = ''
			var dr = '';
			if (p == 1) {
				ic = "resources/dashboard/Images/markerS.png";
				dr = false;
			}
			if (p == 0) {
				ic = "resources/dashboard/Images/source_icon.png";
				dr = false;
			}
			if (p == 2) {
				ic = "resources/dashboard/Images/desti.png";
				dr = false;
			}
	
			if (alphaIdx > latiArr.length + 1) {
				return;
			}
			
			 marker = new google.maps.Marker({
		          map: map,
		          position: new google.maps.LatLng("${school_details.school_lat}","${school_details.school_lng}"),
		          draggable: dr,
		          icon: "resources/dashboard/Images/school_icon.png"
		  });
			var evtPos = new google.maps.LatLng(latiArr[p], longiArr[p]), c = alphas[alphaIdx++],
	
			marker = new google.maps.Marker({
				map : map,
				position : evtPos,
				draggable : dr,
				icon : ic
			});
			marker.segmentIndex = markers.length - 1;
			marker.iconChar = c;//just storing this for good measure, may want at some time
	
			function updateSegments() {
				var start, end, inserts, i, idx = this.segmentIndex, segLen = segments.length, //segLen will always be 1 shorter than markers.length
				myPos = this.getPosition();
				if (segLen === 0) { //nothing to do, this is the only marker
					return;
				}
				if (idx == -1) { //this is the first marker
					start = [ myPos ];
					end = [ markers[1].getPosition() ];
					inserts = [ 0 ];
	
				} else if (idx == segLen - 1) { //this is the last marker
					start = [ markers[markers.length - 2].getPosition() ];
					end = [ myPos ];
					inserts = [ idx ];
				} else {
					//there are markers both behind and ahead of this one in the 'markers' array
					start = [ markers[idx].getPosition(), myPos ];
					end = [ myPos, markers[idx + 2].getPosition() ];
					inserts = [ idx, idx + 1 ];
				}
	
				latiArr.splice(idx + 1, 1);
				latiArr
						.splice(idx + 1, 0, markers[idx + 1].getPosition()
								.lat());
				longiArr.splice(idx + 1, 1);
				longiArr.splice(idx + 1, 0, markers[idx + 1].getPosition()
						.lng());
				console.log(start.length);
				for (i = 0; i < start.length; i++) {
					addSegment(start[i], end[i], inserts[i]);
				}
			}
	
			//google.maps.event.addListener(marker, 'drag', updateSegments);
	
			google.maps.event.addListener(marker, 'dragend', updateSegments);
			markers.push(marker);
	
			if (markers.length > 1) {
	
				var infowindow = new google.maps.InfoWindow({
					content : ""
				});
	
				addSegment(markers[markers.length - 2].getPosition(), evtPos,
						marker.segmentIndex);
				// infowindow.open(map,markers[markers.length - 2]);
			}
		}
	
	}
	
	function addLatLong(start) {
	
	}
	function add() {
	
		$("#lat").val(latiArr);
		$("#lng").val(longiArr);
	
		var lat = $("#lat").val();
		var lng = $("#lng").val();
	
		document.getElementById("student").submit();
	
	}
	
	function getRouteMap(id) {
	
		$.ajax({
			url : 'getRouteSourceDestination.html',
			type : 'post',
			data : {
				"route_id" : id
			},
			success : function(data) {
				var sourceDesti = $.parseJSON(data);
	
				$.ajax({
					url : 'getRouteLatLng.html',
					type : 'post',
					data : {
						"route_id" : id
					},
					success : function(data) {
	
						var latiLongi = $.parseJSON(data);
						//init(json);
						/*   	if(latiLongi.latlng.length===0)
						 		{
						 			$("#map_canvas").html("No Route Defined");
						 		}else
								{ */
						var latStr = '';
						var lngStr = '';
						for (var i = 0; i < latiLongi.latlng.length; i++) {
							if (i == parseInt(latiLongi.length) - 1) {
								latStr += latiLongi.latlng[i].lat;
								lngStr += latiLongi.latlng[i].lng;
							} else {
								latStr += latiLongi.latlng[i].lat + ",";
								lngStr += latiLongi.latlng[i].lng + ",";
							}
	
						}
	
						//}
						init(latStr, lngStr, latiLongi, sourceDesti);
	
					}
				});
	
			}
		});
	
		return false;
	
	}
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
