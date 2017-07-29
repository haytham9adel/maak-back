<jsp:include page="header.jsp" />
<%@ page import="resources.Assets"%>
<script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*" %> 
<%@ page import="org.apache.commons.codec.binary.Base64" %> 
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<link rel="stylesheet" href="resources/dashboard/css/cropper.css">
<style>
</style>
<script>
	$(document).ready(function() {
		$("#upload_triger_1").click(function() {
			$("#imgInp").click();
		});
	});
</script>
<form class="form-horizontal" id="school" method="post">
<div class="col-md-9 col-sm-9 col-xs-12 m-side">

	<div class="new-student-form">
			
			<div class="form-group">
				<div class="row">
					<div class="col-sm-6">
						<h3 class="dash_title">School Logo :</h3>
						<div>
						
				<c:choose>
				<c:when test="${schoolModel.school_logo!=''}">
				<img class="img-responsive" id="image" src="resources/dashboard/Images/s_logo_d.png" alt="s_image_path" />
				
				</c:when>
				<c:otherwise>
				<img class="img-responsive" id="image" src="resources/dashboard/Images/s_logo_d.png" alt="s_image_path" />
				 </c:otherwise>
				</c:choose>			
			 			<div class="cp_upload">
				          <label for="imgInp" class="btn"><i class="fa fa-camera"></i> Upload </label>
				          <input onchange="readURL(this)" id="imgInp" name="image" style="visibility:hidden;" type="file">
				        </div>
						</div>
					</div>
			<div class="col-sm-6">
				<h3 class="dash_title">School Logo :</h3>
				<button type="button" class="btn btn-primary btn-submit" id="button">Crop</button>
				<div id="result">
				<img class="img-responsive" id="image" src="<%=Assets.SCHOOL_UPLOAD_PATH %>${schoolModel.school_logo}" alt="s_image_path">
				</div>
				
			</div>
			<input type="hidden" name="school_logo"
							id="school_logo" value="" class="form-control">
				</div>
			</div>

			<%-- <div class="form-group">
				<label class="col-sm-3 control-label">School Logo:</label>
				<div class="col-sm-9">

					<div class="profile-pic-block">
						<div class="profile-thumb">


							<c:choose>
								<c:when test="${schoolModel.school_logo!=''}">
									<td><img id="blah" alt="image_path"
										class="manage-icon-big"
										src="<%=Assets.SCHOOL_UPLOAD_PATH %>${schoolModel.school_logo}" />
								</c:when>
								<c:otherwise>
									<img id="blah" src="resources/dashboard/Images/s_logo_d.png"
										alt="image_path" />
								</c:otherwise>
							</c:choose>
							<!--  <img id="blah" src="resources/dashboard/Images/user_icon.png" alt="s_image_path" /> -->
							<!-- <div id="uploadTrigger" class="">
								<i class="fa fa-camera-retro fa-2x"></i>
							</div>
							<div class="profile_caption" id="upload_triger_1">
								<i class="fa fa-camera-retro fa-2x"></i> <span>Upload
									school logo</span>
							</div> -->
							<div class="tb_upload" id="uploadTrigger">
								<div class="tb_bg" id="upload_triger_1">
									<i class="fa fa-camera"></i><a href="javascript:void(0);"
										class="tb_up_img">Upload School logo</a>
								</div>
							</div>
							
						</div>
						<input type='file' class="hidden" onchange="readURL(this)"
							id="imgInp" /> <input type="hidden" name="school_logo"
							id="school_logo" value="" class="form-control">
					</div>

				</div>
			</div> --%>
			<div class="form-group">&nbsp;</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">School Name:</label>
				<div class="col-sm-9">
				<c:set var="s_id" value="${schoolModel.s_id}"/>
				 <%
    	 			String str=pageContext.getAttribute("s_id").toString();
    				byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
    	  		%>
					<%-- <input type="hidden" class="form-control" id="s_id"
						value="${schoolModel.s_id}" name="s_id">  --%>
						<input type="text" class="form-control" id="school_name"
						value="${schoolModel.school_name}" name="school_name">
				</div>
			</div>
			<%--   	 <div class="form-group">
                     <label class="col-sm-3 control-label">School Admin Username:</label>
                       <div class="col-sm-9">
                        <input type="text" disabled="disabled" value="${schooladmin.user_name}" class="form-control" >
                        </div>
                   </div> --%>

			<div class="form-group">
				<label class="col-sm-3 control-label">School Admin Username:</label>
				<div class="col-sm-9">
					<select class="form-control" id="user_id" name="user_id"
						onchange="getAdminDetails(this.value);">
						<option value="">Select School Admin</option>
						<c:if test="${!empty all_school_admin}">
							<c:forEach items="${all_school_admin}" var="school_admin_all">
								<option <c:if test="${school_admin_all.user_id==schooladmin.user_id}">
                         	<c:out value="selected"/>
                         	 </c:if>   value=<c:out value="${school_admin_all.user_id}"/>>
								 ${school_admin_all.user_name}
								
								</option>
							</c:forEach>
						</c:if>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">School Admin
					Firstname:</label>
				<div class="col-sm-9">
					<input type="text" value="${schooladmin.first_name}"
						class="form-control" id="first_name" name="first_name">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">School Admin Lastname:</label>
				<div class="col-sm-9">
					<input type="text" value="${schooladmin.last_name}"
						class="form-control" id="last_name" name="last_name">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Email :</label>
				<div class="col-sm-9">
					<input type="text" class="form-control"
						value="${schooladmin.user_email}" id="email" name="user_email">
				</div>
			</div>
			<div class="clear">&nbsp;</div>
			<div class="form-group">

				<label class="col-sm-3 control-label"> Privileges :</label>
				<div class="col-sm-9">
					<div class="radio">
						<!-- <label class="col-sm-3 control-label">Privileges:</label>  --><label>
							<input
							<c:choose>
								<c:when test="${schooladmin.permission==0}">
								checked="checked"
									</c:when>
									<c:otherwise>
									
									</c:otherwise>
	                            </c:choose>
							type="radio" name="permission" value="0" id="permission">
							Just View

						</label> <label> <input
							<c:choose>
								<c:when test="${schooladmin.permission==1}">
								checked="checked"
									</c:when>
	                            </c:choose>
							type="radio" name="permission" value="1" id="permission">
							Main School Admin

						</label>
					 
				</div>
				</div>
				
					

			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Total Buses :</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" name="total_bus"
						value="${schoolModel.total_bus}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Total Students :</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" name="total_students"
						value="${schoolModel.total_students}">
				</div>
			</div>
			<div class="form-group">
                     <label class="col-sm-3 control-label">Country :</label>
                       <div class="col-sm-9">
                         <select class="form-control" id="country" name="country" onchange="return getCity();">
                         <option value="">Select Country</option>
                         <c:if test="${!empty countries}">
                         	<c:forEach items="${countries}" var="county">
                         	<option <c:if test="${schoolModel.country==county.c_id}">
                         	<c:out value="selected"/>
                         	 </c:if>  value=<c:out value="${county.c_id}"/>>${county.c_name}</option>
                         	</c:forEach>
                         </c:if>
                         </select>
                        </div>
			</div>
			 <div class="form-group">
                     <label class="col-sm-3 control-label">City :</label>
                       <div class="col-sm-9">
                        <select class="form-control" id="city"  name="city" onchange="setCityMap(this.value);">
                           <option value="">Select City</option>
                         </select>
                       </div>
                   </div>
			 
			<div class="form-group">
				<label class="col-sm-3 control-label">School Location:</label>
				<div class="col-sm-9">
					<input type="text" name="school_address"
						value="${schoolModel.school_address}" id="school_address"
						class="form-control">
				</div>
			</div>
			 
			<div class="form-group">
				<label class="col-sm-3 control-label">School Address:</label>
				<div class="col-sm-9">
					<input type="text" name="school_address_field"
						value="${schoolModel.school_address_field}" id="school_address_field"
						class="form-control">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label">School Admin's Mobile
					Number:</label>
				<div class="col-sm-9">
				<!-- <div id="pre_code_1" class="input-group-addon zero"></div> -->
					<input type="text" class="form-control" id="contact_number"
						value="${schooladmin.contact_number}" disabled="disabled" name="contact_number" >
				</div>
			</div>
			


			
			
			
			

		
	</div>
	</div>
	<div class="col-sm-12 col-xs-12">
	<div class="form-group">
				<div id="geomap" style="width: 100%; height: 500px;">
					<p>Loading Please Wait...</p>
				</div>


				<input id="hidLat" name="school_lat" type="hidden"
					value="${schoolModel.school_lat}"> <input id="hidLong"
					name="school_lng" type="hidden" value="${schoolModel.school_lng}">
			<%-- 	<input id="user_id" name="user_id" type="hidden"
					value="${schooladmin.user_id}"> --%>


				<div class="clear">&nbsp;</div>

				<div class="form-group">
					<div class="col-sm-12 text-center">
						<input class="btn btn-primary btn-submit" id="save"
							onclick="return confirmBox();" value="Save" type="submit">&nbsp;&nbsp;
						<a href="admin/manageSchools"> <input
							class="btn btn-primary btn-submit" id="cancel" value="Cancel"
							type="button">
						</a>
					</div>
				</div>


			</div>
			</div>
	</form>
	<!-- <div class="col-md-3">
              <div class="info-block"> 
                <h4>Bus Information </h4>
                 <div class="info-group">
                    <div class="col-a">Bus no  : </div>
                    <div class="col-b"> 123456 </div> 
                 </div>
                 <div class="info-group">
                    <div class="col-a"> Driver Name : </div>
                    <div class="col-b"> Rakeeb </div> 
                 </div>
                 <div class="info-group">
                    <div class="col-a">Sourse : </div>
                    <div class="col-b"> Street,34 NY 458689 </div> 
                 </div>
                 <div class="info-group">
                    <div class="col-a">Travel-Time : </div>
                    <div class="col-b"> 123456 </div> 
                 </div>
                 <div class="info-group">
                    <div class="col-a"> Bus Stops : </div>
                    <div class="col-b"> 123456 </div> 
                 </div>
              </div> 
              
              <div class="info-block"> 
                <h4>Current Location </h4>
                 <div class="info-group">
                    <div class="col-a">Current Stop: </div>
                    <div class="col-b"> 123456 </div> 
                 </div>
                 <div class="info-group">
                    <div class="col-a">Next Stop :</div>
                    <div class="col-b"> Rakeeb </div> 
                 </div>
                 <div class="info-group">
                    <div class="col-a">Previous Stop </div>
                    <div class="col-b"> Street,34 NY 458689 </div> 
                 </div>
                 
              </div>
              
              <div class="info-block"> 
                <h4>Staff Information</h4>
                 <div class="info-group">
                   <ul class="list-unstyled">
                     <li>Staff Member Name-1 </li> 
                     <li>Staff Member Name-2 </li> 
                   </ul>
                 </div>
                 </div>
                 
          
        </div> -->
	<script src="resources/front/js/jquery.validate.min.js">
		
	</script>
	<script>
		$(document).ready(function() {
			$("#school").validate({
				rules : {
					school_name : "required",
					email : {
						required : true,
						email : true
					},
					contact_number : "required",
					country : "required",
					state : "required",
					city : "required",
					zip_code : "required",
					total_bus : {
						number : true,
						required : true,
					},
					total_students : {
						number : true,
						required : true,
					},
					principal_contact : "required",
					principal_name : "required",
				},
				messages : {
					school_name : "Please enter school name",
					email : {
						required : "Please enter email address",
						email : "Please enter valid email address"
					},
					contact_number : "Please enter contact number",
					country : "Please select country",
					state : "Please select state",
					city : "Please select city",
					zip_code : "required",
					total_bus : {
						number : "This field should be numeric",
						required : "Please enter total number of bus",
					},
					total_students : {
						number : "This field should be numeric",
						required : "Please enter total number of students",
					},
					principal_contact : "Please enter principal contact",
					principal_name : "Please enter principal name",
				},
				submitHandler : function(form) {
					form.submit();
				}
			});

		});
	</script>

	<!-- <script type="text/javascript">
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('#blah').attr('src', e.target.result);
					var src = $('img[alt="image_path"]').attr('src');
					$("#school_logo").val(src);
				}

				reader.readAsDataURL(input.files[0]);
			}
		}
	</script> -->

	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src="http://maps.googleapis.com/maps/api/js?libraries=places&sensor=false&key=AIzaSyA7QTJeimvMfcLMul6ZsMRZA1qOfjsyHtc"></script>
	<script type="text/javascript"
		src="resources/front/js/jquery.geocomplete.min.js"></script>
	<script type="text/javascript">
		var PostCodeid = "#school_address";
		var longval = "#hidLong";
		var latval = "#hidLat";
		var geocoder;
		var map;
		var marker;

		function initialize() {
			//MAP
			var initialLat = $(latval).val();
			var initialLong = $(longval).val();
			if (initialLat == '') {
				initialLat = "${schoolModel.school_lat}";
				initialLong = "${schoolModel.school_lng}";
			}
			var latlng = new google.maps.LatLng(initialLat, initialLong);
			var options = {
				zoom : 14,
				center : latlng,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};

			map = new google.maps.Map(document.getElementById("geomap"),
					options);

			geocoder = new google.maps.Geocoder();

			marker = new google.maps.Marker({
				map : map,
				draggable : true,
				position : latlng
			});

			google.maps.event.addListener(marker, "dragend", function(event) {
				var point = marker.getPosition();
				map.panTo(point);
			});

		};

		$(document)
				.ready(
						function() {

							initialize();

							$(function() {
								$(PostCodeid)
										.autocomplete(
												{
													//This bit uses the geocoder to fetch address values
													source : function(request,
															response) {
														geocoder
																.geocode(
																		{
																			'address' : request.term
																		},
																		function(
																				results,
																				status) {
																			response($
																					.map(
																							results,
																							function(
																									item) {
																								return {
																									label : item.formatted_address,
																									value : item.formatted_address
																								};
																							}));
																		});
													}
												});
							});

							$('#school_address')
									.on(
											"change",
											function(e) {

												setTimeout(
														function() {

															//alert($("#address").val());
															var address = $(
																	"#school_address")
																	.val();
															geocoder
																	.geocode(
																			{
																				'address' : address
																			},
																			function(
																					results,
																					status) {
																				if (status == google.maps.GeocoderStatus.OK) {
																					map
																							.setCenter(results[0].geometry.location);
																					marker
																							.setPosition(results[0].geometry.location);
																					$(
																							latval)
																							.val(
																									marker
																											.getPosition()
																											.lat());
																					$(
																							longval)
																							.val(
																									marker
																											.getPosition()
																											.lng());
																				} else {
																					//alert("Geocode was not successful for the following reason: "
																					//		+ status);
																				}
																			});
															e.preventDefault();
														}, 400);
											});

							//Add listener to marker for reverse geocoding
							google.maps.event
									.addListener(
											marker,
											'drag',
											function() {
												geocoder
														.geocode(
																{
																	'latLng' : marker
																			.getPosition()
																},
																function(
																		results,
																		status) {
																	if (status == google.maps.GeocoderStatus.OK) {
																		if (results[0]) {

																			$(
																					"#school_address")
																					.val(
																							results[0].formatted_address);
																			$(
																					latval)
																					.val(
																							marker
																									.getPosition()
																									.lat());
																			$(
																					longval)
																					.val(
																							marker
																									.getPosition()
																									.lng());
																		}
																	}
																});
											});

						});
	</script>
	<script type='text/javascript'>
		function initialize1() {
			var mapOptions = {
				center : {
					lat : 51.5072,
					lng : 0.1275
				},
				zoom : 12,
				mapTypeControl : false,
				streetViewControl : false,
				panControl : false,
				scrollwheel : false,
				zoomControl : true,
				zoomControlOptions : {
					style : google.maps.ZoomControlStyle.SMALL,
					position : google.maps.ControlPosition.LEFT_TOP
				}
			};
			var map = new google.maps.Map(
					document.getElementById('map_canvas'), mapOptions);
		}
		google.maps.event.addDomListener(window, 'load', initialize1);

		$("#school_address").geocomplete({
		//	details : "form"
		});
	</script>
	<c:if test="${!empty success}">
		<script>
			swal("Success!", "${success}", "success");
		</script>
	</c:if>

	<script>
		/* function confirmBox()
		 {
		 e.preventDefault();
		 swal({
		 title: "Are you sure?", 
		 text: "You will not be able to recover this imaginary file!",
		 type: "warning",
		 showCancelButton: true,
		 confirmButtonColor: "#DD6B55", 
		 confirmButtonText: "Yes, delete it!",
		 cancelButtonText: "No, cancel plx!",
		 closeOnConfirm: false
		 },
		 function(isConfirm){  
		 if (isConfirm) {  
		
		 return false;
		 } 
		 else
		 {  
		
		 //	swal("Cancelled", "Your imaginary file is safe :)", "error");  
		 return false;
		 } 
		
		 });
		 } */
		$('#save').on('click', function(e) {
			e.preventDefault();
			var form = $(this).parents('form');
			swal({
				title : "Are you sure?",
				text : "Do you want to update?",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "Yes, Update!",
				closeOnConfirm : false
			}, function(isConfirm) {
				if (isConfirm)
					form.submit();
			});
		})

		$('#cancel').on('click', function(e) {
			e.preventDefault();
			var form = $(this).parents('form');
			swal({
				title : "Are you sure?",
				text : "Do you want to cancel?",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "Yes, Cancel!",
				closeOnConfirm : false
			}, function(isConfirm) {
				if (isConfirm)
					window.location = "admin/manageSchools";
			});
		})
	</script>
<script type="text/javascript">
function getAdminDetails(id)
{
	var user_id=id;
	  $.ajax({
        url : 'getSchoolAdminById.html',
        type:'post',
        data:{"user_id":user_id},
        success : function(data) {
        	var obj = jQuery.parseJSON( data );
        	$("#first_name").val('');
        	$("#first_name").val(obj.first_name);
        	$("#last_name").val(obj.last_name);
        	$("#contact_number").val(obj.contact_no);
        	$("#email").val(obj.email);
        }
    });
		  return false;
}

function getCity() {
	
	var c_id=$("#country").val();
		  $.ajax({
        url : 'getCityByState.html',
        type:'post',
        data:{"country_id":c_id},
        success : function(data) {
          $("#city").html(data);
          getCountryDetails()
        }
    });
		  return false;
}
$(document).ready(function(){
	var c_id=${schoolModel.country};
	var city=${schoolModel.city};
		  $.ajax({
      url : 'school/getSchoolCity',
      type:'post',
      data:{"country_id":c_id,'city_id':city},
      success : function(data) {
        $("#city").html(data);
      }
  });
		  return false;
});
function getCountryDetails() {

	var c_id = $("#country").val();

	$.ajax({
		url : 'getCountryById.html',
		type : 'post',
		data : {
			"c_id" : c_id
		},
		success : function(data) {
			var obj = jQuery.parseJSON(data);

			$("#pre_code").html(obj.code);
			//$("#school_address").val(obj.country);
			
			geocoder.geocode({'address' : obj.country},	function(results,status) {

				if (status == google.maps.GeocoderStatus.OK) {
					map.setCenter(results[0].geometry.location);
					marker.setPosition(results[0].geometry.location);
				$(latval).val(marker.getPosition().lat());
				$(longval).val(marker.getPosition().lng());
			 }else {
			 	//alert("Geocode was not successful for the following reason: "+ status);
			 		}
			});
			
			
			
			
			
			
		}
	});
	return false;
}
/*Set Map on city*/
function setCityMap(city)
{
	var city=$("#city option:selected").text();
	//$("#school_address").val(city);
	//var country=$()
	geocoder.geocode({'address' : city},	function(results,status) {

		if (status == google.maps.GeocoderStatus.OK) {
			map.setCenter(results[0].geometry.location);
			marker.setPosition(results[0].geometry.location);
		$(latval).val(marker.getPosition().lat());
		$(longval).val(marker.getPosition().lng());
	 }else {
	 	//alert("Geocode was not successful for the following reason: "+ status);
	 		}
	});
	
}
</script>
<script type="text/javascript">
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('.cropper-canvas img').attr('src', e.target.result);
				$('.cropper-view-box img').attr('src', e.target.result);
				
				var src = $('img[alt="s_image_path"]').attr('src');
				$("#school_logo").val(src);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
<script src="resources/dashboard/js/cropper.js"></script>
<script>
    function getRoundedCanvas(sourceCanvas) {
      var canvas = document.createElement('canvas');
      var context = canvas.getContext('2d');
      var width = sourceCanvas.width;
      var height = sourceCanvas.height;

      canvas.width = width;
      canvas.height = height;
      context.beginPath();
      context.arc(width / 2, height / 2, Math.min(width, height) / 2, 0, 2 * Math.PI);
      context.strokeStyle = 'rgba(0,0,0,0)';
      context.stroke();
      context.clip();
      context.drawImage(sourceCanvas, 0, 0, width, height);

      return canvas;
    }

    $(function () {
      var $image = $('#image');
      var $button = $('#button');
      var $result = $('#result');
      var croppable = false;

      $image.cropper({
        aspectRatio: 1,
        viewMode: 1,
        built: function () {
          croppable = true;
        }
      });

      $button.on('click', function () {
        var croppedCanvas;
        var roundedCanvas;

        if (!croppable) {
          return;
        }

        // Crop
        croppedCanvas = $image.cropper('getCroppedCanvas');

        // Round
        roundedCanvas = getRoundedCanvas(croppedCanvas);

        // Show
        $("#school_logo").val(roundedCanvas.toDataURL());
        $result.html('<img src="' + roundedCanvas.toDataURL() + '">');
      });
    });
  </script>
	<jsp:include page="footer.jsp" />