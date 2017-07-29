<jsp:include page="header.jsp" />
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?libraries=places&sensor=false&key=AIzaSyA7QTJeimvMfcLMul6ZsMRZA1qOfjsyHtc"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-6 col-sm-6 col-xs-12 m-side">

             <div class="new-student-form"> 
             <c:if test="${success}">
             </c:if>
             <%--  <span class="success">${success}</span>
             <span class="error">${error}</span> --%>
                <form class="form-horizontal" id="staff" method="post">
                	
                <div class="form-group">
                     <label class="col-sm-3 control-label">Country:</label>
                       <div class="col-sm-9">
                       
                       <select class="form-control" id="country_id" name="country_id" onchange="getCode(this.value)"; >
                         <option value="">Select Country</option>
                         <c:if test="${!empty countries}">
                         	<c:forEach items="${countries}" var="country">
                         	<option
                         		
                         		<c:if test="${city_details.country_id==country.c_id}">
selected="selected"
</c:if>
                         	
                         	  value=<c:out value="${country.c_id}"/>>${country.c_name}</option>
                         	</c:forEach>
                         </c:if>
                         </select>
                     </div>
                   </div> 
                
                
                      <div class="form-group">
                     <label class="col-sm-3 control-label">Town Name:</label>
                       <div class="col-sm-9">
                    <!--    <input type="text" id="country_id1"> -->
                       <input type="text" class="form-control" name="city_name" value="${city_details.city_name}" id="city_name">
                     </div>
                   </div>
                 
                    
                    <div class="form-group">
                       <div class="col-sm-9">
                        <input  class="btn btn-primary btn-submit" type="submit" value="Submit">
                       </div>
                   </div>
                </form>
             </div> 
             
          </div>
         
        <script src="resources/front/js/jquery.validate.min.js"> </script>
  <script>
  $(document).ready(function(){
	  intiGoogleLocation();
		$("#staff").validate({
          rules: {
        	  city_name: "required",
        	  country_id: "required",
          },
          messages: {
        	  city_name: "Please enter Town name",
        	  country_id: "Please select country",
       
          },
          submitHandler: function(form) {
              form.submit();
          }
      });
      

});
  
  </script>
<script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css"> 
<c:if test="${!empty success}">
<script>
swal(
		{
			title : "${success}",
			type : "success"
		}
	);
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
swal(
		{
			title : "${error}",
			type : "success"
		}
	);
	/* swal(
			"The driver successfully added!\n\n",
			"Driver User Name  : ${username} \n\nDriver Password : ${password} ",
			"success");
	
	, function() {
		$('#myModal').modal('show');
	}) */
</script>

</c:if>
<script>
//This example displays an address form, using the autocomplete feature
//of the Google Places API to help users fill in the information.

function intiGoogleLocation() {
	fillInAddress();
 

 
}
function fillInAddress() {
	 /*  alert($("#country_id").attr('rel'));
	  var list          = document.getElementById('country_id'),
    input         = document.getElementById('city_name'),
    autocomplete  = new google.maps.places.Autocomplete(input, {
              types: ['(cities)'],
              componentRestrictions: $("#country_id").attr('rel')
              
    });
	
	  google.maps.event.addDomListener(list,'change', function(e){
		    
		    var componentRestrictions={};
		    
		    if(this.value!==''){
		      componentRestrictions.country=this.value
		    }
		    
		    autocomplete.setComponentRestrictions(componentRestrictions);
		    input.value='';
		    if(e){
		      autocomplete.set('place',{name:''});
		    }
		          
		        
		  });
	   */

}
</script>
  <script type="text/javascript">

	
function initAutocomplete1(abc) {
	  
	  var list      = document.getElementById('country_id'),
	  input         = document.getElementById('city_name');
	/*   autocomplete  = new google.maps.places.Autocomplete(input, {
	            types: ['(cities)'],
	           
	  }); */
	  
	  google.maps.event.trigger(list,'change',null);
	 /*  google.maps.event.addDomListener(list,'change', function(e){
		 
	  }); */
}  

  
  function initAutocomplete(abc) {
	  
	  var list      = document.getElementById('country_id'),
	  input         = document.getElementById('city_name'),
	  autocomplete  = new google.maps.places.Autocomplete(input, {
	            types: ['(cities)'],
	            componentRestrictions: {country: abc}
	  });
	  
	  google.maps.event.trigger(list,'change',null);
	  google.maps.event.addDomListener(list,'change', function(e){
		 
	  });
	  /* alert(abc);
	  
	  autocomplete = new google.maps.places.Autocomplete(
	    
	    (document.getElementById('city_name')), {
	      types: ['(cities)'],
	      componentRestrictions: {country: abc}
	    });

	 
	  google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    var place = autocomplete.getPlace();
	    document.getElementById('lat').value = place.geometry.location.lat();
	    document.getElementById('lng').value = place.geometry.location.lng();
	    fillInAddress();
	  }); */
	}
	//google.maps.event.addDomListener(window, 'load', initAutocomplete("us"));
     </script>       
 
 <script>
 function getCode(id)
 {
	 var txt = $("#country_id option:selected").text();
 
	 jQuery.get( "https://maps.googleapis.com/maps/api/geocode/json?address="+txt+"&key=AIzaSyAU7ae7rJFSWvJvLCnuEBjJtx0kXcwFmsU", function( response ) { 
		  
	 		//$("#country_id1").val(response.results[0].address_components[0].short_name);
		 	$("#country_id").attr("rel",response.results[0].address_components[0].short_name);
	 		console.log(response.results[0].address_components[0].short_name);
	 		initAutocomplete1("");
	 		initAutocomplete(response.results[0].address_components[0].short_name);
		} );
	// https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyAU7ae7rJFSWvJvLCnuEBjJtx0kXcwFmsU
 }
 
 </script>
 
    <jsp:include page="footer.jsp" />      