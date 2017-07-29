<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-9 m-side">
			<form method="post" name="route" id="route">
				<div class="new-student-form">
			<div class="form-group">
			  <label class="col-sm-3 control-label">Route Name :</label>
			    <div class="col-sm-9">
			      <input type="text" name="route_name" value="${route.route_name}"  id="route_name" class="form-control">
			     </div>
			</div>
			<div class="clear">&nbsp;</div>
				<div class="form-group">
			  <label class="col-sm-3 control-label">Note :</label>
			    <div class="col-sm-9">
			      <input type="text" name="note" value="${route.note}"  id="note" class="form-control">
			     </div>
			</div>
			
			<div class="clear">&nbsp;</div>
			<div class="form-group">
			  <label class="col-sm-3 control-label">Source:</label>
			    <div class="col-sm-9">
			      <input type="text" name="source" value="${route.source}"  id="source" class="form-control">
			     </div>
			</div>
			<div class="clear">&nbsp;</div>
				<div class="form-group">
			  		<label class="col-sm-3 control-label">Source Note :</label>
			    	<div class="col-sm-9">
			      		<input type="text" name="source_note" value="${route.source_note}"  id="source_note" class="form-control">
			     	</div>
			</div>
			<div class="clear">&nbsp;</div>
			<div class="form-group">
			  <label class="col-sm-3 control-label">School Address as Source:</label>
			    <div class="col-sm-9">
			      <input type="checkbox" onchange="myfunction();" style="width: auto;"  name="school_address" value="${school_details.school_address}"  id="school_address" class="form-control">
			     </div>
			</div>
				<div class="clear">&nbsp;</div>
			   <div class="form-group">
                   	 <div id="geomap" style="width:100%; height:300px;">
            <p>Loading Please Wait...</p>
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
			  <label class="col-sm-3 control-label">Destination:</label>
			    <div class="col-sm-9">
			      <input type="text" name="destination" value="${route.destination}"  id="destination" class="form-control">
			     </div>
			</div>
			<div class="clear">&nbsp;</div>
				<div class="form-group">
			  		<label class="col-sm-3 control-label">Destination Note :</label>
			    	<div class="col-sm-9">
			      		<input type="text" name="destination_note" value="${route.destination_note}"  id="destination_note" class="form-control">
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
		             <input  class="btn btn-primary btn-submit" value="Save" id="save" type="submit">&nbsp;&nbsp;
		             <a href="school/allRoute">
		             <input  class="btn btn-primary btn-submit" value="Cancel" id="cancel" type="button">
		             </a>
	            </div>
			</div>       
			
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
 <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&key=AIzaSyA7QTJeimvMfcLMul6ZsMRZA1qOfjsyHtc"></script>

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
                initialLat = "${route.source_lat}";
                initialLong = "${route.source_lng}";
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
    	  initialLat = "${route.destination_lat}";
          initialLong = "${route.destination_lng}";
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
					window.location = "school/allRoute";
			});
		})	 
  });
  
  </script> 
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
		 // $("#source").val('');
		//  $(latval).val('');
         // $(longval).val("");
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
		//  $("#source").val('');
		//  $(latval).val('');
        //  $(longval).val("");
	  }
}
</script>
<c:if test="${!empty success}">
<script>

swal(
		{
			title : "The route has been updated successfully",
			text : "",
			type : "success"
		}, function() {
			
		});
</script>

</c:if>  
<jsp:include page="footer.jsp" />      