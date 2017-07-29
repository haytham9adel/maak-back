<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-9 col-sm-9 col-xs-12 m-side">

             <div class="new-student-form"> 
              <span class="success">${success}</span>
             <span class="error">${error}</span>
                <form class="form-horizontal" id="student" method="post">
                      <div class="form-group">
                     <label class="col-sm-3 control-label">Assign Route:</label>
                       <div class="col-sm-9">
                       <select class="form-control" id="route_id" name="route_id"  >
                         <option value="">Select Route</option>
                         <c:if test="${!empty routes}">
                         	<c:forEach items="${routes}" var="route">
                         	<option  value=<c:out value="${route.route_id}"/>><c:out value="${route.route_name}"/></option>
                         	</c:forEach>
                         </c:if>
                         </select>
                     </div>
                   </div>
                  
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Student :</label>
                       <div class="col-sm-9">
                         <select class="form-control" id="student_id" name="student_id" onchange="getParent(this.value);" >
                         <option value="">Select Student</option>
                         <c:if test="${!empty students}">
                         	<c:forEach items="${students}" var="student">
                         	<option  value=<c:out value="${student.student_id}"/>><c:out value="${student.s_fname} ${student.s_lname}"/></option>
                         	</c:forEach>
                         </c:if>
                         </select>
                        </div>
                   </div>
                  
                  <div class="clear">&nbsp;</div>
			<div class="form-group">
			  <label class="col-sm-3 control-label">Stop Address:</label>
			    <div class="col-sm-9">
			      <input type="text" name="address" value=""  id="address" class="form-control">
			     </div>
			</div>
					<div id="p">
				  <input type="hidden" name="parent_id" value="${student.s_parent_id}" id="parent_id">
				  </div>
                  <input type="hidden" id="student_name" name="student_name">
                  
                    <div class="form-group">
                       <div class="col-sm-9">
                        <input  class="btn btn-primary" type="submit">
                       </div>
                   </div>
                      <div class="form-group">
                   	 <div id="geomap" style="width:100%; height:400px;">
            <p>Loading Please Wait...</p>
        </div>
                   </div>
                </form>
             </div> 
             
          </div>
         
        <script src="resources/front/js/jquery.validate.min.js"> </script>
  <script>
  
  function getParent(student_id) {
	  
	  	var student_id=student_id;
	  	  $.ajax({
	          url :'getStudentParent.html',
	          type:'post',
	          data:{"student_id":student_id},
	          success : function(data) {
	            $("#p").html(data);
	           
	          }
	      });
	 		  return false;
	  }


  $(document).ready(function(){
	  	
	  $("#student_id").on("change",function(){
		 $("#student_name").val($(this).find("option:selected").text());
	  });
	  
		$("#student").validate({
          rules: {
        	  route_id: "required",
        	  student_id: "required",
        	  address: "required",
         },
          messages: {
        	  school_id: "Please select route",
        	  student_id: "Please select student",
        	  address: "Please enter address",
	      },
          submitHandler: function(form) {
              form.submit();
          }
      });
      

});
  
  </script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>

        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>   
        <script type="text/javascript" src="resources/front/js/jquery.geocomplete.min.js"></script>             
        <script type="text/javascript">
        var PostCodeid = "#address";
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
                zoom: 9,
                center: latlng,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
        
            map = new google.maps.Map(document.getElementById("geomap"), options);
         
            geocoder = new google.maps.Geocoder();    
        
            marker = new google.maps.Marker({
                map: map,
                draggable: true,
                position: latlng
            });
        
            google.maps.event.addListener(marker, "dragend", function (event) {
                var point = marker.getPosition();
                map.panTo(point);
            });
            
        };
        
        $(document).ready(function () {
        
            initialize();
        
            $(function () {
                $(PostCodeid).autocomplete({
                    //This bit uses the geocoder to fetch address values
                    source: function (request, response) {
                        geocoder.geocode({ 'address': request.term }, function (results, status) {
                            response($.map(results, function (item) {
                                return {
                                    label: item.formatted_address,
                                    value: item.formatted_address
                                };
                            }));
                        });
                    }
                });
            });
        
           
            
            $('#address').on("change",function (e) {
            	
            	
            	setTimeout(function(){ 
            	
            	//alert($("#address").val());
                var address =$("#address").val();
                geocoder.geocode({ 'address': address }, function (results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                        map.setCenter(results[0].geometry.location);
                        marker.setPosition(results[0].geometry.location);
                        $(latval).val(marker.getPosition().lat());
                        $(longval).val(marker.getPosition().lng());
                    } else {
                        alert("Geocode was not successful for the following reason: " + status);
                    }
                });
                e.preventDefault();
            	 }, 400);
            });
        
            //Add listener to marker for reverse geocoding
            google.maps.event.addListener(marker, 'drag', function () {
                geocoder.geocode({ 'latLng': marker.getPosition() }, function (results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                        if (results[0]) {

                           
                            $("#address").val(results[0].formatted_address);
                            $(latval).val(marker.getPosition().lat());
                            $(longval).val(marker.getPosition().lng());
                        }
                    }
                });
            });
        
        });
  
    </script>
    <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?libraries=places&sensor=false"></script>
<script type='text/javascript'>
  function initialize1() {
  var mapOptions = {
    center: { lat: 51.5072, lng: 0.1275},
    zoom: 12,
    mapTypeControl: false,
    streetViewControl: false,
    panControl: false,
    scrollwheel: false,
    zoomControl: true,
    zoomControlOptions: {
    style: google.maps.ZoomControlStyle.SMALL,
    position: google.maps.ControlPosition.LEFT_TOP}
  };
  var map = new google.maps.Map(document.getElementById('map_canvas'),
    mapOptions);
  }
  google.maps.event.addDomListener(window, 'load', initialize1);

 $("#address").geocomplete({
    
      }); 



</script>
       
        
        <input id="hidLat" name="hidLat" type="hidden" value="">
        <input id="hidLong" name="hidLong" type="hidden" value="">    
          
    <jsp:include page="footer.jsp" />      