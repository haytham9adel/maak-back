<jsp:include page="header.jsp" />
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
					<p>Do you want to send sms now or later</p>
					<p>

						<a href="javascript:void(0);" data-dismiss="modal"
							class="btn btn-success" data-toggle="modal"
							data-target="#myModal_sms">Yes</a> &nbsp; <a
							href="admin/addDriver/" class="btn btn-warning">No</a>
					</p>
				</div>
				<!-- 
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div> -->
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
					<form action="admin/sendDriverPassword1" method="post">
						
						<input type="hidden" name="contact_number" value="${driver_contact}">
						<input type="hidden" name="mobile_number" value="${country_details.c_code}">
						<textarea name="first_name" class="form-control" style="height: 152px; resize: none; margin-bottom: 15px;">Your Login details as below&#13;&#10;Username: ${username}&#13;&#10;password: ${password}</textarea>
						<button type="submit" class="btn btn-default">Send</button>
					</form>
				</div>
			</div>

		</div>
	</div>        
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css">
  <c:if test="${!empty success}">
<script>
swal(
		{
			title : "The driver successfully added!",
			text : "Driver User Name  : ${username} \n\nDriver Password : ${password} ",
			type : "success"
		}, function() {
			$('#myModal').modal('show');
		});
	/* swal(
			"The driver successfully added!\n\n",
			"Driver User Name  : ${username} \n\nDriver Password : ${password} ",
			"success");
	
	, function() {
		$('#myModal').modal('show');
	}) */
</script>

</c:if>
<c:if test="${!empty error}">
<script>
	swal("Error!", " ${error} ", "error");
</script>
</c:if>  
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<!-- <script src="http://code.jquery.com/jquery-1.10.2.js"></script> -->
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="resources/dashboard/css/cropper.css">
 <script>
  $(function() {
    $( "#dob" ).datepicker({ dateFormat: 'yy-mm-dd',
    	changeMonth: true,
        changeYear: true,
        yearRange: "-100:+0",});
    $( "#licence_expiry" ).datepicker({ dateFormat: 'yy-mm-dd',
    	changeMonth: true,
        changeYear: true,
        minDate:0,
        yearRange: "-0:+50",});
    
   
    $('body').on('click',".d", function(){  
    	//$(this).datepicker('destroy').datepicker({showOn:'focus'}).focus();
    	$(this).datepicker('destroy');
    	$(this).datepicker({ dateFormat: 'yy-mm-dd',
        	changeMonth: true,
        	 minDate: new Date(),
            changeYear: true,
            showOn:'focus',
            yearRange: "-0:+50",}).focus();
    	   	
    });
    

    
  });
  </script>
<div class="col-md-6 col-sm-6 col-xs-12 m-side">

             <div class="new-student-form"> 
          
           
                <form class="form-horizontal" id="staff" method="post" enctype="multipart/form-data">
                  <c:if test="${!empty success}">
<script>
 		
$(document).ready(function(){
	
	myFunction();
});

</script>
<script>
function myFunction() {
    document.getElementById("myForm").reset();
}
</script>
 </c:if>
                <input type="hidden" name="driver_school_id" value="<%= session.getAttribute("new_school_id") %>">
             
                  	
                   <div class="form-group">
                     <label class="col-sm-3 control-label">First Name  <nowiki>*</nowiki>:</label>
                       <div class="col-sm-9">
                       <input type="hidden" name="driver_school_id" value="<%=session.getAttribute("schoolId") %>">
                         <input type="text" name="driver_fname" value="${driver.driver_fname}" id="driver_fname" class="form-control">
                          <input type="hidden"  name="image_path" id="image_path" value="" class="form-control">
                        </div>
                   </div>
                      <div class="form-group">
                     <label class="col-sm-3 control-label">Middle Name :</label>
                       <div class="col-sm-9">
                        <input type="text" value="${driver.middle_name}" name="middle_name" id="middle_name" class="form-control">
                       </div>
                   </div>
                     <div class="form-group">
                     <label class="col-sm-3 control-label">Last Name  <nowiki>*</nowiki>:</label>
                       <div class="col-sm-9">
                        <input type="text" value="${driver.driver_lname}" name="driver_lname" id="driver_lname" class="form-control">
                       </div>
                   </div>
                   <!-- <div class="form-group">
                     <label class="col-sm-3 control-label">Profile Picture :</label>
                       <div class="col-sm-9">
                   			<div class="profile-pic-block">
				               <div class="profile-thumb"> <img id="blah" src="resources/dashboard/Images/user_icon.png" alt="image_path" /> </div>
				               <input type='file' onchange="readURL(this)" id="imgInp" />
				              
				             </div>
                       </div>
					</div> -->
					<div class="form-group">
				<div class="row">
				   <div class="col-sm-6">
				   	 <div class="col-sm-12">
				   	  <h3 class="dash_title">Profile Picture :</h3>
				    
				       <img class="img-responsive" id="image" src="resources/dashboard/Images/user_icon.png" alt="s_image_path">
				       <!-- <input type='file' onchange="readURL(this)" id="imgInp" /> -->
				       <div class="cp_upload">
				          <label for="imgInp" class="btn"><i class="fa fa-camera"></i> Upload </label>
				          <input onchange="readURL(this)" id="imgInp" name="image" style="visibility:hidden;" type="file">
				        </div>
				     </div>
				   </div>
				   <div class="col-sm-6">
				   	<div class="col-sm-12">
				     <h3 class="dash_title">Result</h3>
				     <button type="button" class="btn btn-primary btn-submit" id="button">Crop</button>
				     <div id="result"></div>
				     </div>
				   </div>
				 </div>
				</div>
					
			<div class="form-group">
				<label class="col-sm-3 control-label">Date of Birth :</label>
				<div class="col-sm-9">
					<input type="text" name="dob" value="${driver.dob}" id="dob"
						class="form-control" autocomplete="off">
				</div>
			</div>
			     <div class="form-group">
                     <label class="col-sm-3 control-label">Nationality :</label>
                       <div class="col-sm-9">
                     <%--    <input type="text" value="${driver.nationality}" name="nationality" id="nationality" class="form-control"> --%>
                      
                     <select class="form-control" id="nationality" name="nationality">
                         <option value="">Select Nationnality</option>
                         <c:if test="${!empty nationality}">
                         	<c:forEach items="${nationality}" var="nation">
                         	<option  value=<c:out value="${nation.name}"/>>${nation.name}</option>
                         	</c:forEach>
                         </c:if>
                         </select>
                       </div>
                   </div>
					 <div class="form-group">
						<label class="col-sm-3 control-label">Blood Type :</label>
						<div class="col-sm-9">
							<select class="form-control" name="blood_group" id="blood_group">
								<option value="A+">A+</option>
								<option value="A-">A-</option>
								<option value="B+">B+</option>
								<option value="B-">B-</option>
								<option value="AB+">AB+</option>
								<option value="AB-">AB-</option>
								<option value="O+">O+</option>
								<option value="O-">O-</option>
							</select>
						</div>
					</div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">License Expiry Date :</label>
                       <div class="col-sm-9">
                        <input type="text" value="${driver.licence_expiry}" name="licence_expiry" id="licence_expiry" class="form-control">
                       </div>
                   </div>
					
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Email :</label>
                       <div class="col-sm-9">
                        <input type="email" name="d_email" value="${driver.d_email}" id="d_email" class="form-control">
                       </div>
                   </div>
                   
                       <div class="form-group">
                     <label class="col-sm-3 control-label">Mobile Number <nowiki>*</nowiki>:</label>
                       <div class="col-sm-9">
                       	<div class="input-group-addon zero">${country_details.c_code}</div>
                       	<input type="text" style="padding:6px 55px;" value="${driver.contact_number}" name="contact_number" id="contact_number" class="form-control">
                      </div>
                   </div>
                    <div class="form-group">
                     <label class="col-sm-3 control-label">Assign Route  <nowiki>*</nowiki>:</label>
                       <div class="col-sm-9">
                       <select class="form-control" id="route_id" name="route_id"  onchange="return getRouteMap(this.value);" >
                         <option value="0">Select Route</option>
                         <c:if test="${!empty routes}">
                         	<c:forEach items="${routes}" var="route">
                         	<option  value=<c:out value="${route.route_id}"/>>${route.route_name}</option>
                         	</c:forEach>
                         </c:if>
                         </select>
                          <span class="error" id="check_route"></span>
                           <input type="hidden" id="check_route_input"/>
                     </div>
                   </div>
                   
                     <div class="form-group">
                   	<div class="col-sm-12">
                   		<div id="map_canvas" style="width: 100%; height: 500px;">
                   		<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d14929362.906331098!2d36.08272383527203!3d24.000579465714182!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x15e7b33fe7952a41%3A0x5960504bc21ab69b!2sSaudi+Arabia!5e0!3m2!1sen!2s!4v1446016465064" width="100%" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
                   	</div>
                   </div>
                   </div>
          
                <h4 style="text-align:center;">Here you can add document related to driver</h4>   
                   <!--------------------------DO Not Disturb ---------------------------------->
			<div class="input_fields_wrap">
				<div class="panel section_repeat" id="">
					<div class="form-group">
						<label class="col-sm-4 control-label">Document Name :</label>
						<div class="col-sm-8">
							<input type="text" name="insurance_document_name[0]"
								value="${vechileBean.insurance_document_name}" id="insurance_document_name"
								class="form-control">
						</div>
					</div><!-- form-group -->
					<div class="form-group">
						<label class="col-sm-4 control-label">Expiry Date :</label>
						<div class="col-sm-8">
							<input type="text" name="insurance_document_expiry[0]"
								value="${vechileBean.insurance_document_expiry}" id="insurance_end_date0"
								class="form-control d">
						</div>
					</div><!-- form-group -->
					<div class="form-group">
						<label class="col-sm-4 control-label">Remind Date :</label>
						<div class="col-sm-8">
							<input type="text" onchange="getDateDiff(0);" name="insurance_end_date[0]" value="${vechileBean.insurance_end_date}" id="insurance_end_date_e0"
								class="form-control d">
						</div>
					</div><!-- form-group -->
					<div class="form-group">
						<label class="col-sm-4 control-label">Remind before days :</label>
						<div class="col-sm-8">
							<input type="text" name="remind_day[0]"
								value="${vechileBean.remind_day}" id="remind_day0"
								class="form-control">
						</div>
					</div><!-- form-group -->
					<div class="form-group">
						<label class="col-sm-4 control-label">Document Copy:</label>
						<div class="col-sm-8">
							<input type="file" name="insurance_card_copy[0]" id="insurance_card_copy" class="">
						</div>
					</div><!-- form-group -->
				</div><!-- panel -->
			</div><!-- input_fields_wrap -->
			<!--------------------------DO Not Disturb End---------------------------------->
			
			<div class="form-group">
				<div class="col-sm-12">

					<button class="add_field_button btn btn-submit" style="float:right;"><i class="fa fa-plus " aria-hidden="true"></i></button>

				</div>
				<!-- <div class="input_fields_wrap"></div> -->
			</div>
                     <div class="form-group">
                       <div class="col-sm-9">
                        <input  class="btn btn-primary btn-submit" value="Save" type="submit">&nbsp;&nbsp;
                        <a href="admin/manageDrivers">
                        <input  class="btn btn-primary btn-submit" value="Cancel" type="button">
                        </a>
                       </div>
                   </div>
                </form>
             </div> 
             
          </div>
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
        <script src="resources/front/js/jquery.validate.min.js"> </script>
  <script>
  $(document).ready(function(){
		$("#staff").validate({
          rules: {
        	  driver_fname: "required",
        	  driver_lname:"required",
        	  /* route_id:"required", */
        	  dept_id: "required",
        	/*   d_email:{
						required: true,
						email: true
					}, */
					contact_number: "required",
          },
          messages: {
        	  driver_fname: "Please enter first name",
        	  driver_lname: "Please enter last name",
        	  /* route_id:"Please select route", */
        	  d_email:
			   	  {
				 		required: "Please enter email address",
				 		email: "Please enter valid email address"
			          	},
		     contact_number: "Please enter Mobile number",
			 address:"Please enter address",
			  },
          submitHandler: function(form) {
        	  var check_route_input=$("#check_route_input").val();
        	  if(parseInt(check_route_input)==1)
        		  {
        		  	return false;
        		  }
        	  else
        		  {
        		  	form.submit();
        		  }
             
          }
      });
      

});
  
  </script>
<script>
function myFunction() {
    document.getElementById("staff").reset(); 
}
</script>
 <!--  <script type="text/javascript">
    function readURL(input) {
    	if (input.files && input.files[0]) {
            var reader = new FileReader();
            
            reader.onload = function (e) {
                $('#blah').attr('src', e.target.result);
                var src = $('img[alt="image_path"]').attr('src');
                $("#image_path").val(e.target.result);
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
				$("#image_path").val(src);
			}

			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
<script src="http://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&key=AIzaSyA7QTJeimvMfcLMul6ZsMRZA1qOfjsyHtc"></script>
<script type="text/javascript">
var latiArr= [];
var longiArr= [];	
function init(a,b,latiLongi,sourceDesti,source_name,destination_name) {
	
	
	 latiArr= [];
	 longiArr= [];
	   latiArr.pop();
		longiArr.pop();
   var latStr='';
	   var lngStr='';
	   longiArr.push(sourceDesti.slng);
	   latiArr.push(sourceDesti.slat);
	   latiArr.push(sourceDesti.dlat);
	   longiArr.push(sourceDesti.dlng);
	
		
		
	 
	     
		 
	  var sourceLat;
	  var sourceLng;
	  var destLat;
	  var destLng;
      var markers = [],
        segments = [],
        myOptions = {
            zoom: 10,
            center: new google.maps.LatLng(sourceDesti.slat, sourceDesti.slng),
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            disableDoubleClickZoom: true,
            draggableCursor: "crosshair"
        },
        alphas = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split(''),
     //   alphas = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split(''),
        alphaIdx = 0,
        map = new google.maps.Map(document.getElementById("map_canvas"), myOptions),
        service = new google.maps.DirectionsService(),
        poly = new google.maps.Polyline({
            map: map,
            strokeColor: '#00FFFF',
            strokeOpacity: 0.6,
            strokeWeight: 5
    });




    window.resetMarkers = function () {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
        alphaIdx = 0;
        segments = [];
        markers = [];
        poly.setPath([]);
    };
    function getSegmentsPath() {
        var a, i,
            len = segments.length,
            arr = [];
        for (i = 0; i < len; i++) {
            a = segments[i];
            if (a && a.routes) {
                arr = arr.concat(a.routes[0].overview_path);
            }
        }
        return arr;
    }

    function addSegment(start, end, segIdx) {

        service.route(
            {
                origin: start,
                destination: end,
                travelMode: google.maps.DirectionsTravelMode.DRIVING
            },
            function (result, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                    //store the entire result, as we may at some time want
                    //other data from it, such as the actual directions
                    segments[segIdx] = result;

                    poly.setPath(getSegmentsPath());
                }
   
            }
        );
       
         //addLatLong(start);
    }


    for(var p=0;p<latiArr.length;p++)
     { 
    	
    	var ic=''
        	var dr='';
        	
        		ic="resources/dashboard/Images/school_icon.png";
        		dr=false;
          	
        	if(p==0)
          	{
        		ic="resources/dashboard/Images/source_icon.png";
        		dr=false;
          	}
        	if(p==parseInt(latiArr.length-1))
          	{
        		ic="resources/dashboard/Images/desti.png";
        		dr=false;
          	}
    	
      if (alphaIdx > latiArr.length+1) {
            return;
        }
      marker = new google.maps.Marker({
          map: map,
          position: new google.maps.LatLng("${school_details.school_lat}","${school_details.school_lng}"),
          draggable: dr,
          icon: "resources/dashboard/Images/school_icon.png"
  });
       /* console.log("lati="+latiArr[p]);
        console.log("logi="+longiArr[p]);*/
        var evtPos =new google.maps.LatLng(latiArr[p],longiArr[p]) ,
            c = alphas[alphaIdx++],
            marker = new google.maps.Marker({
                map: map,
                position: evtPos,
                draggable: dr,
                icon: ic
        });
        
        
       /*  latiArr.push(latlongi.lat());
         longiArr.push(latlongi.lng());*/
         // console.log(latiArr);
         marker.segmentIndex = markers.length - 1;
         marker.iconChar = c;//just storing this for good measure, may want at some time
        function updateSegments() {
            var start, end, inserts, i,
                idx = this.segmentIndex,
                segLen = segments.length, //segLen will always be 1 shorter than markers.length
                myPos = this.getPosition();
            if (segLen === 0) { //nothing to do, this is the only marker
                return;
            }
            if (idx == -1) { //this is the first marker
                start = [myPos];
                end = [markers[1].getPosition()];
                inserts = [0];
               
            } else if (idx == segLen - 1) { //this is the last marker
                start = [markers[markers.length - 2].getPosition()];
                end = [myPos];
                inserts = [idx];
            } else {
            //there are markers both behind and ahead of this one in the 'markers' array
                start = [markers[idx].getPosition(), myPos];
                end = [myPos, markers[idx + 2].getPosition()];
                inserts = [idx, idx + 1];
            }
          
          
           latiArr.splice(idx+1,1);
           latiArr.splice(idx+1, 0,markers[idx+1].getPosition().lat());
           longiArr.splice(idx+1,1);
           longiArr.splice(idx+1, 0,markers[idx+1].getPosition().lng());
           console.log(start.length);
            for (i = 0; i < start.length; i++) {
                //addSegment(start[i], end[i], inserts[i]);
            }
        }

    
        //google.maps.event.addListener(marker, 'drag', updateSegments);
       

        google.maps.event.addListener(marker, 'dragend', updateSegments);
        markers.push(marker);

        if (markers.length > 1) {

             var infowindow = new google.maps.InfoWindow({
             //content: "latlong"+e.latLng
  });

            addSegment(markers[markers.length - 2].getPosition(), evtPos, marker.segmentIndex);
          //  infowindow.open(map,markers[markers.length - 2]);
        }
   }

    google.maps.event.addListener(map, "click", function (e) {
            google.maps.event.addListener(marker, 'dragend', updateSegments);
      //  markers.push(marker);

         $.ajax({
            url: "http://maps.googleapis.com/maps/api/geocode/json?latlng=44.4647452,7.3553838&sensor=true",
            type: "GET",

            contentType: 'application/json; charset=utf-8',
            success: function(resultData) {
                  alert(resultData.formatted_address);
            },
            error : function(jqXHR, textStatus, errorThrown) {
            },

            timeout: 120000,
        });
     

        if (markers.length > 1) {
         
             //var infowindow = new google.maps.InfoWindow({
           //  content: evtPos
              /*  infowindow.setContent('');
                infowindow.open(map, marker);*/

  //});

            addSegment(markers[markers.length - 2].getPosition(), evtPos, marker.segmentIndex);
         // infowindow.open(map,markers[markers.length - 1]);
        }
    });
    infowindows0 = new google.maps.InfoWindow({
  	  content: '<div style="width:140px;"><b>Source</b><br>'+source_name+"</div>"
    });
  google.maps.event.addListener(markers[0], 'click', function() {
      infowindows0.open(map, markers[0]);
    }); 
  
  infowindows1 = new google.maps.InfoWindow({
  	 content: '<div style="width:140px;"><b>Destination</b><br>'+destination_name+"</div>"
  });
google.maps.event.addListener(markers[1], 'click', function() {
    infowindows1.open(map, markers[1]);
  });
}
 function getRouteMap(id)
 {
	 	
	 	if(parseInt(id)==0){
			$("#check_route").html("");
			$("#check_route_input").val(0);
		}
	 	$.ajax({
	         url : 'checkAssigedRoute.html',
	         type:'post',
	         data:{"route_id":id},
	         success : function(response) {
	        	 var objNew = $.parseJSON(response);
	        	if(parseInt(objNew.status)==1)
	        		{
	        			$("#check_route").html("Already this route assigned to another driver");
	        			$("#check_route_input").val(1);
	        			return false;
	        		}else{
	        			$("#check_route").html("");
	        			$("#check_route_input").val(0);
	        			$.ajax({
	        		         url : 'getRouteSourceDestination.html',
	        		         type:'post',
	        		         data:{"route_id":id},
	        		         success : function(data) {
	        		         	var sourceDesti = $.parseJSON(data);
	        		         	
	        		         	$.ajax({
	        		                 url : 'getRouteLatLng.html',
	        		                 type:'post',
	        		                 data:{"route_id":id},
	        		                 success : function(data) {
	        		               	  
	        		               	var latiLongi = $.parseJSON(data);
	        		           
	        		   	               var latStr='';
	        		   	           	   var lngStr='';
	        		   	           		for(var i=0;i<latiLongi.latlng.length;i++)
	        		   	           			{
	        		   	           			   if(i==parseInt(latiLongi.length)-1)
	        		   	           			   {
	        		   	           				   latStr +=latiLongi.latlng[i].lat;
	        		   	           				   lngStr +=latiLongi.latlng[i].lng;
	        		   	           			   }
	        		   	           			   else
	        		   	           			   {
	        		   	           				   latStr +=latiLongi.latlng[i].lat+",";
	        		   	           				   lngStr +=latiLongi.latlng[i].lng+",";
	        		   	           			   }
	        		   	           			   
	        		   	           			}
	        		   	           			
	        		   	  			//}
	        		   	           	init(latStr,lngStr,latiLongi,sourceDesti,sourceDesti.source,sourceDesti.destination);
	        		              
	        		                 }
	        		             });
	        		         	
	        		         	
	        		         	
	        		         }
	        		     });
	        		}
	         }
	 	});
 	
 		 return false; 
 	
 	   
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
        $("#image_path").val(roundedCanvas.toDataURL());
        $result.html('<img src="' + roundedCanvas.toDataURL() + '">');
      });
    });
  </script>
 <script>
	$(document)
			.ready(
					function() {
						var max_fields = 10; //maximum input boxes allowed
						var wrapper = $(".input_fields_wrap"); //Fields wrapper
						var add_button = $(".add_field_button"); //Add button ID
						var y=0;
						var x = 1; //initlal text box count
						$(add_button)
								.click(
										function(e) { //on add input button click
											e.preventDefault();
											if (x < max_fields) { //max input box allowed
												x++; //text box increment
												y++;
												$(wrapper)
														.append(
																'<div class="panel section_repeat" id=""><span class="col-sm-12"><a href="#" style="float:right;padding:0 14px 8px 0;color:red;" class="remove_field ">X</a></span><div class="form-group"><label class="col-sm-4 control-label">Document Name :</label><div class="col-sm-8"><input type="text" name="insurance_document_name['+y+']"	value="${vechileBean.insurance_document_name}" id="insurance_end_date" class="form-control"></div></div><div class="form-group"><label class="col-sm-4 control-label">Expiry Date :</label><div class="col-sm-8"><input type="text" name="insurance_document_expiry['+y+']" value="${vechileBean.insurance_document_expiry}" id="insurance_end_date'+y+'"	class="form-control d"></div></div><div class="form-group"><label class="col-sm-4 control-label">Remind Date :</label><div class="col-sm-8"><input type="text" onchange=getDateDiff('+y+') name="insurance_end_date['+y+']" value="${vechileBean.insurance_end_date}" id="insurance_end_date_e'+y+'"	class="form-control d"></div></div><div class="form-group"><label class="col-sm-4 control-label">Remind before days :</label><div class="col-sm-8"><input type="text" name="remind_day['+y+']"	value="${vechileBean.remind_day}" id="remind_day'+y+'"	class="form-control"></div></div><div class="form-group"><label class="col-sm-4 control-label">Document copy :</label><div class="col-sm-8"><input type="file" name="insurance_card_copy['+y+']" value="" id="insurance_card_copy" class=""></div></div></div><script>'+$("#insurance_end_date"+y).datepicker()+$("#insurance_end_date_e"+y).datepicker()); //add input box
											
												//$(".d").find('input').datepicker();
												//$("").datepicker().appendTo(".d"); 

											}
										});

						$(wrapper).on("click", ".remove_field", function(e) { //user click on remove text
							e.preventDefault();
							$(this).parent().parent('div').remove();
							x--;
						})
					});
</script>
<script>
function getDateDiff(id)
{
 
	var exp_date=$("#insurance_end_date"+id).val();
	var remind_date=$("#insurance_end_date_e"+id).val();
	 
	var f_date=parseDate(exp_date);
	var s_date=parseDate(remind_date);
	$("#remind_day"+id).val(Math.abs(daydiff(s_date,f_date)));
}
function parseDate(str) {
	var mdy = str.split('-');
    return new Date(mdy[0], mdy[1], mdy[2]);
}

function daydiff(first, second) {
    return Math.round((second-first)/(1000*60*60*24));
}

</script>
    <jsp:include page="footer.jsp" />      