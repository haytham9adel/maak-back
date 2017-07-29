<jsp:include page="header.jsp" />
<%@ page import="resources.Assets"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="col-md-6 m-side">
	<link rel="stylesheet"
		href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<link rel="stylesheet" href="resources/dashboard/css/cropper.css">
	<script>
$(function() {
    $( "#dob" ).datepicker({ dateFormat: 'yy-mm-dd',
    	changeMonth: true,
        changeYear: true,
        yearRange: "-100:+0",});
  });
  </script>
	</script>
	<style>
.custom-combobox-input.ui-widget.ui-widget-content.ui-state-default.ui-corner-left.ui-autocomplete-input
	{
	background: #fff none repeat scroll 0 0;
}

.custom-combobox {
	position: relative;
	display: inline-block;
}

.custom-combobox-toggle {
	position: absolute;
	top: 0;
	bottom: 0;
	margin-left: -1px;
	padding: 0;
}

.custom-combobox-input {
	margin: 0;
	padding: 5px 10px;
}
#result img {
    display: block;
    width: 100%;
}
.cropper-bg{margin-bottom:10px;}
</style>
	<script>
  
  $(document).ready(function(){
	  getParent(${studentBean.s_parent_id});
  });
  
  (function( $ ) {
	  
	 
    $.widget( "custom.combobox", {
      _create: function() {
        this.wrapper = $( "<span>" )
          .addClass( "custom-combobox" )
          .insertAfter( this.element );
 
        this.element.hide();
        this._createAutocomplete();
        this._createShowAllButton();
      },
 
      _createAutocomplete: function() {
        var selected = this.element.children( ":selected" ),
          value = selected.val() ? selected.text() : "";
 
        this.input = $( "<input>" )
          .appendTo( this.wrapper )
          .val( value )
          .attr( "title", "" )
          .addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" )
          .autocomplete({
            delay: 0,
            minLength: 0,
            source: $.proxy( this, "_source" )
          })
          .tooltip({
            tooltipClass: "ui-state-highlight"
          });
 
        this._on( this.input, {
          autocompleteselect: function( event, ui ) {
        	  getParent(ui.item.option.attributes[0].nodeValue);
            ui.item.option.selected = true;
            this._trigger( "select", event, {
              item: ui.item.option
            });
          },
 
          autocompletechange: "_removeIfInvalid"
        });
      },
 
      _createShowAllButton: function() {
        var input = this.input,
          wasOpen = false;
 
        $( "<a>" )
          .attr( "tabIndex", -1 )
          .attr( "title", "Show All Parents" )
          .tooltip()
          .appendTo( this.wrapper )
          .button({
            icons: {
              primary: "ui-icon-triangle-1-s"
            },
            text: false
          })
          .removeClass( "ui-corner-all" )
          .addClass( "custom-combobox-toggle ui-corner-right" )
          .mousedown(function() {
            wasOpen = input.autocomplete( "widget" ).is( ":visible" );
          })
          .click(function() {
            input.focus();
 
            // Close if already visible
            if ( wasOpen ) {
              return;
            }
 
            // Pass empty string as value to search for, displaying all results
            input.autocomplete( "search", "" );
          });
      },
 
      _source: function( request, response ) {
        var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
        response( this.element.children( "option" ).map(function() {
          var text = $( this ).text();
          if ( this.value && ( !request.term || matcher.test(text) ) )
            return {
              label: text,
              value: text,
              option: this
            };
        }) );
      },
 
      _removeIfInvalid: function( event, ui ) {
 
        // Selected an item, nothing to do
        if ( ui.item ) {
          return;
        }
 
        // Search for a match (case-insensitive)
        var value = this.input.val(),
          valueLowerCase = value.toLowerCase(),
          valid = false;
        this.element.children( "option" ).each(function() {
          if ( $( this ).text().toLowerCase() === valueLowerCase ) {
            this.selected = valid = true;
            return false;
          }
        });
 
        // Found a match, nothing to do
        if ( valid ) {
          return;
        }
 
        // Remove invalid value
        this.input
          .val( "" )
          .attr( "title", value + " didn't match any parent" )
          .tooltip( "open" );
        this.element.val( "" );
        this._delay(function() {
          this.input.tooltip( "close" ).attr( "title", "" );
        }, 2500 );
        this.input.autocomplete( "instance" ).term = "";
      },
 
      _destroy: function() {
        this.wrapper.remove();
        this.element.show();
      }
    });
  })( jQuery );
 
  $(function() {
    $( "#combobox" ).combobox();
    $( "#toggle" ).click(function() {
      $( "#combobox" ).toggle();
    });
  });
  </script>
	<div class="new-student-form">
		<span class="success">${success}</span> <span class="error">${error}</span>
		<form class="form-horizontal" id="student" method="post">
			<div class="form-group">
				<label class="col-sm-3 control-label">Parent Profile :</label>
				<div class="col-sm-9 ui-widget">
					<select class="form-control" id="combobox" name="s_parent_id"
						onchange="getParent(this.value);">
						<option value="">Select Parent</option>
						<c:if test="${!empty parents}">
							<c:forEach items="${parents}" var="parent">
								<option
									<c:if test="${parent.user_id==studentBean.s_parent_id}">
                         	<c:out value="selected"/>
                         	 </c:if>
									value=<c:out value="${parent.user_id}"/>>${parent.user_name}</option>
							</c:forEach>
						</c:if>
					</select>
					<!--   <div class="col-sm-12 add_parent_btn">
                         <a href="school/addParent"><button type="button" class="btn btn-primary btn-small">Add Parent</button></a>
                        </div> -->

				</div>
			</div>
			<div class="form-group" id="p_status"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">First Name <nowiki>*</nowiki>:
				</label>
				<div class="col-sm-9">
					<input type="text" name="s_fname" value="${studentBean.s_fname}"
						id="s_fname" class="form-control"> <input type="hidden"
						name="s_image_path" id="s_image_path" value=""
						class="form-control"> <input type="hidden"
						name="s_school_id" id="s_school_id"
						value="<%= session.getAttribute("new_school_id") %>"
						class="form-control">

				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Father Name <nowiki>*</nowiki>
					:
				</label>
				<div class="col-sm-9">
					<input type="text" name="father_name"
						value="${studentBean.father_name}" id="father_name"
						class="form-control">
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">Grand Name <nowiki>*</nowiki>
					:
				</label>
				<div class="col-sm-9">
					<input type="text" name="grand_name"
						value="${studentBean.grand_name}" id="grand_name"
						class="form-control">
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">Family Name <nowiki>*</nowiki>
					:
				</label>
				<div class="col-sm-9">
					<input type="text" name="family_name"
						value="${studentBean.family_name}" id="family_name"
						class="form-control">
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">Gender <nowiki>*</nowiki>
					:
				</label>
				<div class="col-sm-9">
					<select class="form-control" name="gender" id="gender">
						<option
							<c:if test="${studentBean.gender=='Male'}">
    selected="selected"
</c:if>
							value="Male">Male</option>
						<option
							<c:if test="${studentBean.gender=='Female'}">
    selected="selected"
</c:if>
							value="Female">Female</option>
					</select>
				</div>
			</div>


			<%-- <div class="form-group">
				<label class="col-sm-3 control-label">Class :</label>
				<div class="col-sm-9">
					<input type="text" name="student_class"
						value="${studentBean.student_class}" id="student_class"
						class="form-control">
				</div>
			</div>
 --%>
 				<div class="form-group">
				<label class="col-sm-3 control-label">Class:</label>
				<div class="col-sm-9">
					<select class="form-control" id="student_class" name="student_class"
						>
						<option value="">Select Class</option>
						<c:if test="${!empty class_info}">
							<c:forEach items="${class_info}" var="cls">
								<option <c:if test="${cls.class_name==studentBean.student_class}">
    selected="selected"
</c:if> value=<c:out value="${cls.class_name}"/>>${cls.class_name}</option>
							</c:forEach>
						</c:if>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">Date of Birth :</label>
				<div class="col-sm-9">
					<input type="text" name="dob" value="${studentBean.dob}" id="dob"
						class="form-control" autocomplete="off">
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">Nationality :</label>
				<div class="col-sm-9">
					<%-- <input type="text" name="nationality"
						value="${studentBean.nationality}" id="nationality"
						class="form-control"> --%>
						<select class="form-control" id="nationality" name="nationality">
                         <option value="">Select Nationnality</option>
                         <c:if test="${!empty nationality}">
                         	<c:forEach items="${nationality}" var="nation">
                         	<option <c:if test="${studentBean.nationality==nation.name}">
                         	<c:out value="selected"/>
                         	 </c:if>   value=<c:out value="${nation.name}"/>>${nation.name}</option>
                         	</c:forEach>
                         </c:if>
                         </select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Blood Type :</label>
				<div class="col-sm-9">
					<select class="form-control" name="blood_type" id="blood_type">


						<option
							<c:if test="${studentBean.blood_type=='A+'}">
    selected="selected"
</c:if>
							value="A+">A+</option>
						<option
							<c:if test="${studentBean.blood_type=='A-'}">
    selected="selected"
</c:if>
							value="A-">A-</option>
						<option
							<c:if test="${studentBean.blood_type=='B+'}">
    selected="selected"
</c:if>
							value="B+">B+</option>
						<option
							<c:if test="${studentBean.blood_type=='B-'}">
    selected="selected"
</c:if>
							value="B-">B-</option>
						<option
							<c:if test="${studentBean.blood_type=='AB+'}">
    selected="selected"
</c:if>
							value="AB+">AB+</option>
						<option
							<c:if test="${studentBean.blood_type=='AB-'}">
    selected="selected"
</c:if>
							value="AB-">AB-</option>
						<option
							<c:if test="${studentBean.blood_type=='O+'}">
    selected="selected"
</c:if>
							value="O+">O+</option>
						<option
							<c:if test="${studentBean.blood_type=='O-'}">
    selected="selected"
</c:if>
							value="O-">O-</option>

					</select>
				</div>
			</div>

			<%--    <div class="form-group">
                     <label class="col-sm-3 control-label">Last Name :</label>
                       <div class="col-sm-9">
                         <input type="text" name="s_lname" value="${studentBean.s_lname}"  id="s_lname" class="form-control">
                        </div>
                   </div> --%>
			<div class="form-group">
				<div class="row">
					<div class="col-sm-6">
						<h3 class="dash_title">Profile Picture :</h3>
						<div>
				<c:choose>
				<c:when test="${studentBean.s_image_path!=''}">
				<img class="img-responsive" id="image" src="resources/dashboard/Images/user_icon.png" alt="s_image_path" />
				</c:when>
				<c:otherwise>
				<img class="img-responsive" id="image" src="resources/dashboard/Images/user_icon.png" alt="s_image_path" />
				 </c:otherwise>
				</c:choose>			
			<!-- <input type='file' onchange="readURL(this)" id="imgInp" /> -->
			<div class="cp_upload">
	          <label for="imgInp" class="btn"><i class="fa fa-camera"></i> Upload </label>
	          <input onchange="readURL(this)" id="imgInp" name="image" style="visibility:hidden;" type="file">
	        </div>
				
						</div>
					</div>
			<div class="col-sm-6">
				<h3 class="dash_title">Result</h3>
				<button type="button" class="btn btn-submit" id="button">Crop</button>
				<div id="result">
					<img class="img-responsive" id="image" src="<%=Assets.STUDENT_UPLOAD_PATH_DIS %>/${studentBean.s_image_path}" alt="s_image_path">
				
				</div>
			</div>
				</div>
			</div>
			<%-- <div class="form-group">
                     <label class="col-sm-3 control-label">Profile Picture :</label>
                       <div class="col-sm-9">
                		   <div class="profile-pic-block">
				               <div class="profile-thumb">
				               
				               
				                 <c:choose>
				          <c:when test="${studentBean.s_image_path!=''}"><td><img id="blah" alt="s_image_path"  class="manage-icon-big" src="<%=Assets.STUDENT_UPLOAD_PATH_DIS %>/${studentBean.s_image_path}"/>
				          
				           </c:when>
				           <c:otherwise>
				           <img id="blah" src="resources/dashboard/Images/user_icon.png" alt="s_image_path" />
				            </c:otherwise>
				           </c:choose>
				            <!--  <img id="blah" src="resources/dashboard/Images/user_icon.png" alt="s_image_path" /> --> </div>
				               <input type='file' onchange="readURL(this)" id="imgInp" />
				             <!--   <button class="upload-pic" type="submit"> 
				               Upload your Profile Picture </button> -->
				             </div>
                       </div>
					</div> --%>
			<div class="form-group">
				<label class="col-sm-3 control-label">Email :</label>
				<div class="col-sm-9">
					<input type="email" name="s_email" disabled="disabled"
						value="${studentBean.s_email}" id="s_email" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Mobile Number</label>
				<div class="col-sm-9">
					<input type="text" id="s_contact" value="${studentBean.s_contact}"
						name="s_contact" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Assign Route:</label>
				<div class="col-sm-9">
					<select class="form-control" id="route_id" name="route_id"
						onchange="return getRouteMap1(this.value);">
						<option value="">Select Route</option>
						<c:if test="${!empty routes}">
							<c:forEach items="${routes}" var="route">
								<option
									<c:if test="${route.route_id==latlng.route_id}">
                         	<c:out value="selected"/>
                         	 </c:if>
									value=<c:out value="${route.route_id}"/>>${route.route_name}</option>
							</c:forEach>
						</c:if>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-12">
					<div id="map_canvas" style="width: 544px; height: 500px;"></div>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-12 text-center">
					<input type="hidden" name="lat" id="lat" value=""> <input
						type="hidden" name="lng" id="lng" value=""> <input
						type="hidden" name="checkStatus" id="checkStatus" value="0">
					<input class="btn btn-primary btn-submit" value="Save" id="save"
						onclick="add();" type="button">&nbsp;&nbsp; <a
						href="admin/manageStudents"> <input
						class="btn btn-primary btn-submit" value="Cancel" id="cancel"
						type="button">
					</a>
				</div>
			</div>
			<!--        <div class="form-group">
                       <div class="col-sm-9">
							<input type="hidden" name="lat" id="lat" value="">
							<input type="hidden" name="lng" id="lng" value="">
							<input type="hidden" name="checkStatus" id="checkStatus" value="0">
							<input  class="btn btn-primary" value="update Student" type="button" onclick="add();">
                       </div>
                   </div> -->
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
	 /*  alert("${latlng.route_id}");
	  if("${latlng.route_id}"!="")
		  { */
		  	getRouteMap("${latlng.route_id}");
		/*   } */
	 
		
	/*   getCity("${studentBean.s_country}"); */
	  
		$("#student").validate({
          rules: {
        	  s_fname: "required",
        	  s_lname: "required",
        	  s_parent_id:"required",
        	/*   s_email:{
						required: true,
						email: true
					},
					s_contact: "required", */
					s_country: "required",
					s_state: "required",
					s_city: "required",
					s_zip: "required",
					s_address:"required",
					
          },
          messages: {
        	  s_fname: "Please enter Student first name",
        	  s_lname: "Please enter Student last name",
        	  s_parent_id:"Please Select parent",
        	/*   s_email:{
						required: "Please enter email address",
						email: "Please enter valid email address"
					},
					s_contact: "Please enter contact number", */
					s_country: "Please select country",
					s_state: "Please select state",
					s_city: "Please select city",
					s_zip: "Please enter zip code" ,
					s_address:"Please enter address",
	      },
          submitHandler: function(form) {
              form.submit();
          }
      });
      

});
  
  </script>
<script type="text/javascript">
/*     function getState() {
    	var c_id=$("#s_country").val();
   		  $.ajax({
            url : 'getStateByCountry.html',
            type:'post',
            data:{"Country_id":c_id},
            success : function(data) {
              $("#s_state").html(data);
            }
        });
   		 return false;
    }
    function getCity() {
    	var c_id=$("#s_country").val();
    	  $.ajax({
            url : 'getCityByState.html',
            type:'post',
            data:{"country_id":c_id,"city_id":"${studentBean.s_city}"},
            success : function(data) {
              $("#s_city").html(data);
            }
        });
   		  return false;
    } */
    $(document).ready(function(){
    	
    /* 	var c_id=${studentBean.s_country};
    	var city=${studentBean.s_city}
 		  $.ajax({
          url : 'school/getSchoolCity',
          type:'post',
          data:{"country_id":c_id,'city_id':city},
          success : function(data) {
            $("#s_city").html(data);
          }
      }); */
 		  
 		  
	var school_id=${studentBean.s_school_id};
	var s_parent_id=${studentBean.s_parent_id};
     $.ajax({
         url : 'admin/getParentBySchoolAjax',
         type:'post',
         data:{"s_school_id":school_id,"s_parent_id":s_parent_id},
         success : function(data) {
           $("#s_parent_id").html(data);
         }
     });
	return false;
    }); 

    
</script>
<script type="text/javascript">
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('.cropper-canvas img').attr('src', e.target.result);
				$('.cropper-view-box img').attr('src', e.target.result);
				
				var src = $('img[alt="s_image_path"]').attr('src');
				$("#s_image_path").val(src);
			}

			reader.readAsDataURL(input.files[0]);
		}
	}
</script>

<script>
  function getParent(parent_id) {
	 
  	var parent_id=parent_id;
  	  $.ajax({
          url : 'getParentById.html',
          type:'post',
          data:{"user_id":parent_id,"school_id":${studentBean.student_id}},
          success : function(data) {
            $("#p_status").html(data);
          }
      });
 		  return false;
  }
 </script>
<script
	src="http://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&key=AIzaSyA7QTJeimvMfcLMul6ZsMRZA1qOfjsyHtc"></script>
<script type="text/javascript">
var latiArr= [];
var longiArr= [];	
function init(a,b,latiLongi,sourceDesti) {
	 var infowindow = new google.maps.InfoWindow();
	
		latiArr= [];
		longiArr= [];
		latiArr.pop();
		longiArr.pop();
		var latStr='';	
		var lngStr='';
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
      var markers = [],
        segments = [],
        myOptions = {
            zoom: 12,
            center: new google.maps.LatLng(sourceDesti.slat, sourceDesti.slng),
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            disableDoubleClickZoom: true,
            draggableCursor: "crosshair"
        },
        alphas = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split(''),
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
    	if(p==1)
      	{
    		ic="resources/dashboard/Images/markerS.png";
    		dr=true;
      	}
    	if(p==0)
      	{
    		ic="resources/dashboard/Images/source_icon.png";
    		dr=false;
      	}
    	if(p==2)
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
        var evtPos =new google.maps.LatLng(latiArr[p],longiArr[p]) ,
            c = alphas[alphaIdx++],
           
            marker = new google.maps.Marker({
                map: map,
                position: evtPos,
                draggable: dr,
                icon: ic
        });
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
                addSegment(start[i], end[i], inserts[i]);
            }
        }

    
        //google.maps.event.addListener(marker, 'drag', updateSegments);
       

        google.maps.event.addListener(marker, 'dragend', updateSegments);
        markers.push(marker);

        if (markers.length > 1) {

             var infowindow = new google.maps.InfoWindow({
             content: ""
  });

            addSegment(markers[markers.length - 2].getPosition(), evtPos, marker.segmentIndex);
          // infowindow.open(map,markers[markers.length - 2]);
        }
   }

  
}

function addLatLong(start)
{
  
}
function add()
{
	
    $("#lat").val(latiArr);
    $("#lng").val(longiArr);
    var lat= $("#lat").val();
    var lng=  $("#lng").val();
   
    
    
  //document.getElementById("student").submit();
 
}   



function getRouteMap(id)
{
	
	
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
  	          	
  	           	init(latStr,lngStr,latiLongi,sourceDesti);
             
                }
            });
        	
        	
        	
        }
    });
	$.ajax({
        url : 'getDriverByRoute.html',
        type:'post',
        data:{"route_id":id,"driver_id":"${studentBean.s_driver}"},
        success : function(data) {
      		
        	$("#s_driver").html(data);
     
        }
    });
	
	 
		 return false; 
	
	   
}

function getRouteMap1(id)
{
	
	
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
  	           		
  	           		
  	           		
  	           		
  	  			
  	           	  $("#checkStatus").val(1);
  	           	init1(latStr,lngStr,latiLongi,sourceDesti);
             
                }
            });
        
        	
        	
        	
        }
    });
	
	$.ajax({
        url : 'getDriverByRoute.html',
        type:'post',
        data:{"route_id":id},
        success : function(data) {
      		
        	$("#s_driver").html(data);
     
        }
    });
	 
		 return false; 
	
	   
}
function init1(a,b,latiLongi,sourceDesti) {

	
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
        zoom: 12,
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
	var ic='';
	var dr='';
    	if(p==3)
      	{
    		ic="resources/dashboard/Images/markerS.png";
    		dr=true;
      	}
    	if(p==0)
      	{
    		ic="resources/dashboard/Images/source_icon.png";
    		dr=false;
      	}
    	if(p==1)
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
            addSegment(start[i], end[i], inserts[i]);
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
	
    var latlongi=e.latLng;
    if (alphaIdx >2){
        return;
    }
    var evtPos = e.latLng,
        c = alphas[alphaIdx++],
        marker = new google.maps.Marker({
            map: map,
            position: evtPos,
            draggable: true,
            icon: 'resources/dashboard/Images/markerS.png'
    });
        
     latiArr.push(latlongi.lat());
     longiArr.push(latlongi.lng());
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
        for (i = 0; i < start.length; i++) {
            addSegment(start[i], end[i], inserts[i]);
        }
    }
   
    //google.maps.event.addListener(marker, 'drag', updateSegments);
   

    google.maps.event.addListener(marker, 'dragend', updateSegments);
    markers.push(marker);

     $.ajax({
        url: "http://maps.googleapis.com/maps/api/geocode/json?latlng=44.4647452,7.3553838&sensor=true&key=AIzaSyA7QTJeimvMfcLMul6ZsMRZA1qOfjsyHtc",
        type: "GET",

        contentType: 'application/json; charset=utf-8',
        success: function(resultData) {
            //  alert(resultData.formatted_address);
        },
        error : function(jqXHR, textStatus, errorThrown) {
        },

        timeout: 120000,
    });
 

    if (markers.length > 1) {
     
         var infowindow = new google.maps.InfoWindow({
        	 
         content: evtPos,
          /*  infowindow.setContent('');
            infowindow.open(map, marker);*/

});

        addSegment(markers[markers.length - 2].getPosition(), evtPos, marker.segmentIndex);
    //  infowindow.open(map,markers[markers.length - 1]);
    }
});
}
     
            
            
         
            </script>
<script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css">
<script>
    $(document).ready(function(){

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
  					window.location = "admin/manageStudents";
  			});
  		})	 
    });
    
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
        $("#s_image_path").val(roundedCanvas.toDataURL());
        $result.html('<img src="' + roundedCanvas.toDataURL() + '">');
      });
    });
  </script>

<jsp:include page="footer.jsp" />
