<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-9 m-side">
			<form method="post" name="route" id="route">
			<div class="new-student-form">
			<div class="form-group">
			  <label class="col-sm-3 control-label">Route Name :</label>
			    <div class="col-sm-9">
			      <input type="text" name="route_name" value=""  id="route_name" class="form-control">
			     </div>
			</div>
					<div class="clear">&nbsp;</div>
			<div class="form-group">
			  <label class="col-sm-3 control-label">Note:</label>
			    <div class="col-sm-9">
			      <input type="text" name="note" value=""  id="note" class="form-control">
			     </div>
			</div>
			<div class="clear">&nbsp;</div>
			<div class="form-group">
			  <label class="col-sm-3 control-label">Source:</label>
			    <div class="col-sm-9">
			      <input type="text" name="source" value=""  id="source" class="form-control">
			     </div>
			</div>
			<div class="clear">&nbsp;</div>
			<div class="form-group">
			  		<label class="col-sm-3 control-label">Source Note :</label>
			    	<div class="col-sm-9">
			      		<input type="text" name="source_note" value=""  id="source_note" class="form-control">
			     	</div>
			</div>
			<div class="clear">&nbsp;</div>
			<div class="form-group">
			  <label class="col-sm-3 control-label">School Address as Source:</label>
			    <div class="col-sm-9">
			      <input type="checkbox" onchange="myfunction();" style="width: auto;"  name="school_address" value="${school_details.school_address}"  id="school_address" class="form-control">
			     </div>
			</div>
			
			
			
			<%-- <div class="clear">&nbsp;</div>
			<div class="form-group">
			  <label class="col-sm-3 control-label">Select Student :</label>
			    <div class="col-sm-9">
			      <select class="form-control">
			      	<option>Select Student</option>
			      	<c:forEach items="${students}" var="student">
			      	<option value="${student.s_address}">${student.s_fname}&nbsp;${student.father_name}&nbsp;${student.grand_name}&nbsp;${student.family_name}</option>
			      	</c:forEach>
			      </select>
			     </div>
			</div> --%>
			
				<div class="clear">&nbsp;</div>
			   <div class="form-group">
                   	 <div id="geomap" style="width:100%; height:300px;">
            <p>Loading Please Wait...</p>
        </div>
                   </div>
			<div class="clear">&nbsp;</div>
			<div class="form-group">
			  <label class="col-sm-3 control-label">Destination:</label>
			    <div class="col-sm-9">
			      <input type="text" name="destination" value=""  id="destination" class="form-control">
			     </div>
			</div>
			<div class="clear">&nbsp;</div>
				<div class="form-group">
			  		<label class="col-sm-3 control-label">Destination Note :</label>
			    	<div class="col-sm-9">
			      		<input type="text" name="destination_note" value=""  id="destination_note" class="form-control">
			     	</div>
			</div>
			<div class="clear">&nbsp;</div>
			<div class="form-group">
			  <label class="col-sm-3 control-label">School Address as Destination:</label>
			    <div class="col-sm-9">
			      <input type="checkbox" onchange="myfunction1();" style="width: auto;"  name="school_address1" value="${school_details.school_address}"  id="school_address1" class="form-control">
			     </div>
			</div>
			
			<div class="clear">&nbsp;</div>
			   <div class="form-group">
                   	 <div id="geomap1" style="width:100%; height:300px;">
            <p>Loading Please Wait...</p>
        </div>
                   </div>
			<div class="clear">&nbsp;</div>
			<div class="form-group">
	            <div class="col-sm-9">
		             <input  class="btn btn-primary btn-submit" value="Save" type="submit">&nbsp;&nbsp;
		             <a href="school/allRoute">
		             <input  class="btn btn-primary btn-submit" value="Cancel" type="button">
		             </a>
	            </div>
			</div>    


<!-- 			<div class="form-group">
			    <div class="col-sm-9">
			      <input type="submit"  value="Next"  class="btn btn-primary">
			     </div>
			</div>    -->       
			
			</div> 
             
          </div>
         </form>
        <script src="resources/front/js/jquery.validate.min.js"> </script>
  <script>
  $(document).ready(function(){
		$("#route").validate({
          rules: {
        	  route_name: "required",
        	  destination:"required",
        	  source:"required"
          },
          messages: {
        	  route_name: "Please enter route name",
        	  destination:"Please enter destination",
        	  source:"Please enter source"
        	  },
          submitHandler: function(form) {
              form.submit();
          }
      });
      

});
  
  </script>
 <script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyA7QTJeimvMfcLMul6ZsMRZA1qOfjsyHtc&libraries=places&sensor=false"></script>
 
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>   
        <script type="text/javascript" src="resources/front/js/jquery.geocomplete.min.js"></script>             
        <script type="text/javascript">
        var PostCodeid = "#source";
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
            	initialLat = "${school_details.school_lat}";
                initialLong = "${school_details.school_lng}";
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
            
            var marker1 = new google.maps.Marker({
                position: {lat: ${school_details.school_lat} , lng: ${school_details.school_lng}},
                map: map,
                icon:'resources/dashboard/Images/school_icon.png'
              });
            
          /*   var circle = new google.maps.Circle({
                center: latlng,
                map:map,
                radius:1737.3,
                strokeColor: "red",
                strokeOpacity:0.8,
                strokeWeight: 2,
                fillColor: "red",
                editable: true
            });
            circle.bindTo('center',marker,'position');
            
             */
            
        
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
        
           
            
            $('#source').on("change",function (e) {
            	
            	
            	setTimeout(function(){ 
            	
            	//alert($("#address").val());
                var address =$("#source").val();
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

                           
                            $("#source").val(results[0].formatted_address);
                            $(latval).val(marker.getPosition().lat());
                            $(longval).val(marker.getPosition().lng());
                        }
                    }
                });
            });
        
        });
  
    </script>
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

 $("#source").geocomplete({
    
      }); 

 $("#destination").geocomplete({
    
      }); 


</script>
 <script type="text/javascript">
 var PostCodeid = "#destination";
 var longval = "#hidLong";
 var latval = "#hidLat";
 var geocoder;
 var map1;
 var marker1;

 function initialize2() {
     //MAP
     var initialLat = $(latval).val();
     var initialLong = $(longval).val();
     if (initialLat == '') {
    	 initialLat = "${school_details.school_lat}";
         initialLong = "${school_details.school_lng}";
     }
     var latlng = new google.maps.LatLng(initialLat, initialLong);
     var options = {
         zoom: 9,
         center: latlng,
         mapTypeId: google.maps.MapTypeId.ROADMAP
     };
 
     map1 = new google.maps.Map(document.getElementById("geomap1"), options);
  
     geocoder = new google.maps.Geocoder();    
 
     marker1 = new google.maps.Marker({
         map: map1,
         draggable: true,
         position: latlng
     });
 	
     var marker2 = new google.maps.Marker({
         position: {lat: ${school_details.school_lat} , lng: ${school_details.school_lng}},
         map: map1,
         icon:'resources/dashboard/Images/school_icon.png'
       });
     google.maps.event.addListener(marker1, "dragend", function (event) {
         var point = marker1.getPosition();
         map1.panTo(point);
     });
     
 };
 
 $(document).ready(function () {
 
     initialize2();
 
     $(function () {
         $(PostCodeid).autocomplete({
             //This bit uses the geocoder to fetch address values
             source: function (request, response) {
                 geocoder.geocode({ 'address': request.term }, function (results, status) {
                     response($.map1(results, function (item) {
                         return {
                             label: item.formatted_address,
                             value: item.formatted_address
                         };
                     }));
                 });
             }
         });
     });
 
    
     
     $('#destination').on("change",function (e) {
     	
     	
     	setTimeout(function(){ 
     	
     	//alert($("#address").val());
         var address =$("#destination").val();
         geocoder.geocode({ 'address': address }, function (results, status) {
             if (status == google.maps.GeocoderStatus.OK) {
                 map1.setCenter(results[0].geometry.location);
                 marker1.setPosition(results[0].geometry.location);
                 $(latval).val(marker1.getPosition().lat());
                 $(longval).val(marker1.getPosition().lng());
             } else {
                 alert("Geocode was not successful for the following reason: " + status);
             }
         });
         e.preventDefault();
     	 }, 400);
     });
 
     //Add listener to marker for reverse geocoding
     google.maps.event.addListener(marker1, 'drag', function () {
         geocoder.geocode({ 'latLng': marker1.getPosition() }, function (results, status) {
             if (status == google.maps.GeocoderStatus.OK) {
                 if (results[0]) {

                    
                     $("#destination").val(results[0].formatted_address);
                     $(latval).val(marker1.getPosition().lat());
                     $(longval).val(marker1.getPosition().lng());
                 }
             }
         });
     });
 
 });
  
    </script>	
	
	
	
        
        <input id="hidLat" name="hidLat" type="hidden" value="">
        <input id="hidLong" name="hidLong" type="hidden" value="">  
  
<script type="text/javascript">

function myfunction()
{
	 var longval = "#hidLong";
     var latval = "#hidLat";
	  if (document.getElementById('school_address').checked) 
	  {
		 $("#source").val($("#school_address").val());
		 setTimeout(function(){ 
         	 var address =$("#source").val();
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
	  } else {
		  $("#source").val('');
		  $(latval).val('');
          $(longval).val("");
	  }
}
</script>

<script type="text/javascript">

function myfunction1()
{
	 var longval = "#hidLong";
     var latval = "#hidLat";
	  if (document.getElementById('school_address1').checked) 
	  {
		 $("#destination").val($("#school_address1").val());
		 setTimeout(function(){ 
		     	
		     	//alert($("#address").val());
		         var address =$("#destination").val();
		         geocoder.geocode({ 'address': address }, function (results, status) {
		             if (status == google.maps.GeocoderStatus.OK) {
		                 map1.setCenter(results[0].geometry.location);
		                 marker1.setPosition(results[0].geometry.location);
		                 $(latval).val(marker1.getPosition().lat());
		                 $(longval).val(marker1.getPosition().lng());
		             } else {
		                 alert("Geocode was not successful for the following reason: " + status);
		             }
		         });
		         e.preventDefault();
		     	 }, 400);
	  } else {
		  $("#source").val('');
		  $(latval).val('');
          $(longval).val("");
	  }
}
</script>
<script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css">
<c:if test="${!empty success}">
	<script>
	swal(
			{
				title : "Route successfully added!",
				text : "",
				type : "success"
			}, function() {
				
			});
	</script>
</c:if> 
<jsp:include page="footer.jsp" />      