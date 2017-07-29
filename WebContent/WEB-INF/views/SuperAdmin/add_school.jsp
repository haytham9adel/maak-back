<jsp:include page="header.jsp" />
<script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
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
			<%--     <span class="success">${success}</span> 
<span class="error">${error}</span>--%>
			<div class="show-credential"></div>
			
			<div class="form-group">
				<div class="row">
				   <div class="col-sm-6">
				     <h3 class="dash_title">School Logo :</h3>
				     <div>
				       <img class="img-responsive" id="image" src="resources/dashboard/Images/s_logo_d.png" alt="s_image_path">
				       <!-- <input type='file' onchange="readURL(this)" id="imgInp" /> -->
				       <div class="cp_upload">
				          <label for="imgInp" class="btn"><i class="fa fa-camera"></i> Upload </label>
				          <input onchange="readURL(this)" id="imgInp" name="image" style="visibility:hidden;" type="file">
				        </div>
				     </div>
				   </div>
				   <div class="col-sm-6">
				     <h3 class="dash_title">Result</h3>
				     <button type="button" class="btn btn-primary btn-submit" id="button">Crop</button>
				     <div id="result"></div>
				   </div>
				 </div>
				 <input type="hidden" name="school_logo" id="school_logo" value="" class="form-control">
				</div>

			<!-- <div class="form-group">
				<label class="col-sm-3 control-label">School Logo :</label>
				<div class="col-sm-9">
					<div class="profile-pic-block">

						<div class="profile-thumb">
							<img id="blah" src="resources/dashboard/Images/s_logo_d.png"
								alt="image_path" />
							<div id="uploadTrigger" class="">
									<i class="fa fa-camera-retro fa-2x"></i>
								</div>
								<div class="profile_caption" id="upload_triger_1">
									<i class="fa fa-camera-retro fa-2x"></i> <span>Upload
										school logo</span>
								</div>
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
			</div> -->
			<div class="form-group">&nbsp;</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">School Name:<span
					class="after">*</span></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="school_name"
						value="${schoolModel.school_name}" name="school_name">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">School Admin
					Firstname:<span class="after">*</span>
				</label>
				<div class="col-sm-9">
					<input type="text" value="${schooladmin.first_name}"
						class="form-control" id="first_name" name="first_name">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">School Admin Lastname:<span
					class="after">*</span></label>
				<div class="col-sm-9">
					<input type="text" value="${schooladmin.last_name}"
						class="form-control" id="last_name" name="last_name">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Email :<span
					class="after">*</span></label>
				<div class="col-sm-9">
					<input type="text" class="form-control"
						value="${schooladmin.user_email}" id="user_email"
						name="user_email">
				</div>
			</div>

			<div class="clear">&nbsp;</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"> Privileges :</label>
				<div class="col-sm-9">
					<div class="radio">
						<label> <input type="radio" name="permission" value="0"
							id="permission"> Just View

						</label> <label> <input type="radio" name="permission"
							checked="checked" value="1" id="permission"> Main School
							Admin

						</label>
					</div>
				</div>

			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Total Buses :<span
					class="after">*</span></label>
				<div class="col-sm-9">
					<input type="text" class="form-control"
						value="${schoolModel.total_bus}" id="total_bus" name="total_bus">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Total Students :<span
					class="after">*</span></label>
				<div class="col-sm-9">
					<input type="text" class="form-control"
						value="${schoolModel.total_students}" id="total_students"
						name="total_students">
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">Country :</label>
				<div class="col-sm-9">
					<select class="form-control" id="country" name="country"
						onchange="return getCity();">
						<option value="">Select Country</option>
						<c:if test="${!empty countries}">
							<c:forEach items="${countries}" var="county">
								<option value=<c:out value="${county.c_id}"/>>${county.c_name}</option>
							</c:forEach>
						</c:if>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">City :</label>
				<div class="col-sm-9">
					<select class="form-control" id="city" name="city"
						onchange="setCityMap(this.value);">
						<option value="">Select City</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">School Location:<span
					class="after">*</span></label>
				<div class="col-sm-9">
					<input type="text" name="school_address"
						value="${schoolModel.school_address}" id="school_address"
						class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">School Address:<span
					class="after">*</span></label>
				<div class="col-sm-9">
					<input type="text" name="school_address_field"
						value="${schoolModel. school_address_field}"
						id="school_address_field" class="form-control">
				</div>
			</div>




			<div class="form-group">
				<label class="col-sm-3 control-label">School Admin's Mobile
					Number:<span class="after">*</span>
				</label>
				<div class="col-sm-9">

					<div id="pre_code" class="input-group-addon zero"></div>
					<input type="text" class="form-control" id="contact_number"
						value="${schooladmin.contact_number}" name="contact_number"
						style="padding-left: 59px;">
				</div>
			</div>









		</div>
	</div>
	<div class="col-sm-12 col-xs-12">

		<div class="form-group">
			<div id="geomap" style="width: 100%; height: 500px;">
				<p>Loading Please Wait...</p>
			</div>



			<!--    
<div class="form-group">
     <div class="col-sm-9">
      <input  class="btn btn-primary" value="Add School" type="submit">
     </div>
 </div> -->


		</div>
		<input id="hidLat" name="school_lat" type="hidden" value=""> <input
			id="hidLong" name="school_lng" type="hidden" value="">
		<div class="clear">&nbsp;</div>
		<div class="form-group">
			<div class="col-sm-12 text-center">
				<input class="btn btn-primary btn-submit" value="Save" type="submit">&nbsp;&nbsp;
				<a href="admin/manageSchools"> <input
					class="btn btn-primary btn-submit" id="cancel" value="Cancel"
					type="button">
				</a>
			</div>
		</div>
	</div>
</form>
<script src="resources/front/js/jquery.validate.min.js">
	
</script>
<script>
	$(document).ready(function() {
		$("#school").validate({
			rules : {
				school_name : "required",
				user_email : {
					required : true,
					email : true
				},
				contact_number : "required",
				school_address_field : "required",
				school_address : "required",
				first_name : "required",
				last_name : "required",
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
				user_email : {
					required : "Please enter email address",
					email : "Please enter valid email address"
				},
				contact_number : "Please enter contact number",
				school_address_field : "Please enter school address",
				school_address : "Please enter school location",
				first_name : "Please enter firstname",
				last_name : "Please enter lastname",
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
<script type="text/javascript">
	function getState() {
		var c_id = $("#country").val();
		$.ajax({
			url : 'getStateByCountry.html',
			type : 'post',
			data : {
				"Country_id" : c_id
			},
			success : function(data) {
				$("#state").html(data);
			}
		});
		return false;
	}
	function getCity() {

		var c_id = $("#country").val();
		$.ajax({
			url : 'getCityByState.html',
			type : 'post',
			data : {
				"country_id" : c_id
			},
			success : function(data) {
				$("#city").html(data);
				getCountryDetails();
			}
		});
		return false;
	}

	function getCountryDetails() {

		var c_id = $("#country").val();

		$.ajax({
			url : 'getCountryById.html',
			type : 'post',
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			data : {
				"c_id" : c_id
			},
			success : function(data) {
				var obj = jQuery.parseJSON(data);
				$("#pre_code").html(obj.code);
				//$("#school_address").val(obj.country);

				geocoder.geocode({
					'address' : obj.country
				}, function(results, status) {

					if (status == google.maps.GeocoderStatus.OK) {
						map.setCenter(results[0].geometry.location);
						marker.setPosition(results[0].geometry.location);
						$(latval).val(marker.getPosition().lat());
						$(longval).val(marker.getPosition().lng());
					} else {
						/* 	alert("Geocode was not successful for the following reason: "+ status); */
					}
				});

			}
		});
		return false;
	}
	/*Set Map on city*/
	function setCityMap(city) {
		var city = $("#city option:selected").text();
		//$("#school_address").val(city);
		//var country=$()
		geocoder.geocode({
			'address' : city
		}, function(results, status) {

			if (status == google.maps.GeocoderStatus.OK) {
				map.setCenter(results[0].geometry.location);
				marker.setPosition(results[0].geometry.location);
				$(latval).val(marker.getPosition().lat());
				$(longval).val(marker.getPosition().lng());
			} else {
				//alert("Geocode was not successful for the following reason: "+ status);
			}
		});

	}
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
<!--    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script> -->

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
			initialLat = "24.7532142";
			initialLong = "46.6497862";
		}
		var latlng = new google.maps.LatLng(initialLat, initialLong);
		var options = {
			zoom : 14,
			center : latlng,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};

		map = new google.maps.Map(document.getElementById("geomap"), options);

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

	$(document).ready(function() {

		initialize();

		$(function() {
			$(PostCodeid).autocomplete({

				//This bit uses the geocoder to fetch address values
				source : function(request, response) {
					geocoder.geocode({
						'address' : request.term
					}, function(results, status) {
						console.log(status);
						response($.map(results, function(item) {
							return {
								label : item.formatted_address,
								value : item.formatted_address
							};
						}));
					});
				}
			});
		});

		$('#school_address').on("change", function(e) {
			setTimeout(function() {

				var address = $("#school_address").val();
				geocoder.geocode({
					'address' : address
				}, function(results, status) {
					if (status == google.maps.GeocoderStatus.OK) {
						map.setCenter(results[0].geometry.location);
						marker.setPosition(results[0].geometry.location);
						$(latval).val(marker.getPosition().lat());
						$(longval).val(marker.getPosition().lng());
					} else {
						/* 	alert("Geocode was not successful for the following reason: "
									+ status); */
					}
				});
				e.preventDefault();
			}, 400);
		});

		//Add listener to marker for reverse geocoding
		google.maps.event.addListener(marker, 'drag', function() {
			geocoder.geocode({
				'latLng' : marker.getPosition()
			}, function(results, status) {
				if (status == google.maps.GeocoderStatus.OK) {
					if (results[0]) {

						$("#school_address").val(results[0].formatted_address);
						$(latval).val(marker.getPosition().lat());
						$(longval).val(marker.getPosition().lng());
					}
				}
			});
		});
		/* $('#school_address').on("change",function(e) {
			setTimeout(function() {
				var address = $("#school_address").val();
				geocoder.geocode({'address' : address},function(results,status) {
					if (status == google.maps.GeocoderStatus.OK) {
							map.setCenter(results[0].geometry.location);
							marker.setPosition(results[0].geometry.location);
							$(latval).val(marker.getPosition().lat());
							$(longval).val(marker.getPosition().lng());
					}else{ alert("Geocode was not successful for the following reason: "+ status);
					}
		});
		e.preventDefault();
		}, 400);
		}); */

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
		var map = new google.maps.Map(document.getElementById('map_canvas'),
				mapOptions);
	}
	google.maps.event.addDomListener(window, 'load', initialize1);

	$("#school_address").geocomplete({
	//details : "form"
	});
</script>
<c:if test="${!empty success}">
	<script>
		swal(
				{
					title : "Registration successfully!",
					text : " School Admin username is ${username} \n\nSchool Admin password is ${password} ",
					type : "success"
				}, function() {
					$('#myModal').modal('show');
				});
	</script>
</c:if>
<c:if test="${!empty error}">
	<script>
		swal("Error!", " ${error} ", "error");
	</script>
</c:if>
<script>
	$('#cancel').on('click', function(e) {
		e.preventDefault();
		var form = $(this).parents('form');
		swal({
			title : "Are you sure?",
			text : "Do you want to cancel?",
			type : "danger",
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
<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Send SMS</h4>
			</div>
			<div class="modal-body">
				<p>Do you want to send sms to the school admin with his login
					details now?</p>
				<p>

					<a href="javascript:void(0);" data-dismiss="modal"
						class="btn btn-success" data-toggle="modal"
						data-target="#myModal_sms">Yes</a> &nbsp; <a
						href="admin/addSchool/" class="btn btn-warning">No</a>
				</p>
			</div>

		</div>

	</div>
</div>

<!-- Modal -->
<div id="myModal_sms" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Send SMS</h4>
			</div>
			<div class="modal-body">
				<form action="admin/sendSchoolAdminSms" method="post">
					<input type="hidden" name="contact_number"
						value="${school_admin_number}">
					<textarea name="first_name" class="form-control"
						style="height: 152px; resize: none; margin-bottom: 15px;">Your school has been added successfully,&#13;&#10;Your Login details as below&#13;&#10;Username: ${username}&#13;&#10;password: ${password}</textarea>
					<button type="submit" class="btn btn-default">Send</button>
				</form>
			</div>

		</div>

	</div>
</div>
<script>
	
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