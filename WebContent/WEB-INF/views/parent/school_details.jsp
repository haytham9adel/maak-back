<jsp:include page="header.jsp" />
<%@ page import="resources.Assets" %>
<link href="//cdn.datatables.net/1.10.7/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
<script src="resources/dashboard/js/jquery.dataTables.min.js"></script>
 <script>
 $(document).ready(function() {
	    $('#example').DataTable();
	} );
 </script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-6 col-sm-6 col-xs-12 m-side">
            
   
             <div class="new-student-form std_frm"> 
              <span class="success">${success}</span>
             <span class="error">${error}</span>
                <form class="form-horizontal" id="student" method="post">
                	
                	      <div class="form-group">
                     <label class="col-sm-3 control-label">School Logo:</label>
                       <div class="col-sm-9">
                       
                       <c:choose>
			<c:when test="${school.school_logo!=''}">
			
			<img id="" alt="image_path" style="width: 95px !important;height: auto;" class="manage-icon-small" src="<%=Assets.SCHOOL_UPLOAD_PATH %>${school.school_logo}"/>
			
			</c:when>
			<c:otherwise>
			<img class="manage-icon-small" style="width: 95px !important;height: auto;" id="" src="resources/dashboard/Images/s_logo_d.png" alt="image_path" />
			 </c:otherwise>
			 </c:choose>
                        </div>
                   </div>
                	
                  <div class="form-group">
                     <label class="col-sm-3 control-label">School Name :</label>
                       <div class="col-sm-9">
                       
                         ${school.school_name}
                        </div>
                   </div>
                  
                    <div class="form-group">
                     <label class="col-sm-3 control-label">School Admin :</label>
                       <div class="col-sm-9">
                       
                       ${schooladmin.first_name} ${schooladmin.last_name}
                        </div>
                   </div>
                     <div class="form-group">
                     <label class="col-sm-3 control-label">School Admin's Mobile
					Number:</label>
                       <div class="col-sm-9">
                      ${country_details.c_code} ${schooladmin.contact_number}
                       </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Email:</label>
                       <div class="col-sm-9">
                       ${schooladmin.user_email}
                       </div>
                   </div>
                   
                   <div class="form-group">
                     <label class="col-sm-3 control-label">School Address:</label>
                       <div class="col-sm-9">
                       ${school.school_address}
                       </div>
                   </div>
                   
                   
                </form>
             </div> 
              

    
       </div>
     <!--School admin list end here-->
             	<div class="clear">&nbsp;</div>
          <div class="col-md-3 col-sm-3 col-xs-12">
            <div class="info-block"> 
          	<h4>School Admin</h4>
          	</div>
          </div>
			<!--School admin list end here-->
           <div class="col-md-3">
             <c:set var="count" value="1" scope="page" />
				 <c:forEach items="${school_admin}" var="school_admin">
              <div class="info-block"> 
               <div class="info-group">
               <div class="col-a">
               </div>
              
               </div>
                 <div class="info-group">
                 	 <div class="col-a">Name : </div>
                    <div class="col-b"> ${school_admin.first_name}     ${school_admin.last_name}   </div> 
                 </div>
                 
             
                 <div class="info-group">
                    <div class="col-a">Email : </div>
                    <div class="col-b"> ${school_admin.user_email} </div> 
                 </div>
                 <div class="info-group">
                    <div class="col-a"> Contact Number : </div>
                    <div class="col-b"> ${country_details.c_code} ${school_admin.contact_number} </div> 
                 </div>
                 
              </div> 
              		<c:set var="count" value="${count + 1}" scope="page"/>
     			 </c:forEach>
        	
                 
          
        </div>
   <div class="col-md-12 col-sm-3 col-xs-12 m-side">
   <style>
   	#map {
        height: 300px;
        width:100%;
      }
   </style>
   		<div id="geomap" style="width: 100%; height: 300px;">
					<p>Loading Please Wait...</p>
		</div>
   <!-- <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA7QTJeimvMfcLMul6ZsMRZA1qOfjsyHtc&callback=initialize">
    </script> -->
    <script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>
   <script type="text/javascript"
		src="http://maps.googleapis.com/maps/api/js?key=AIzaSyA7QTJeimvMfcLMul6ZsMRZA1qOfjsyHtc&libraries=places&sensor=false"></script>
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
				initialLat = "${school.school_lat}";
				initialLong = "${school.school_lng}";
			}
			   var myLatlng = new google.maps.LatLng("${school.school_lat}","${school.school_lng}");
			     var myOptions = {
			         zoom: 14,
			         center: myLatlng,
			         mapTypeId: google.maps.MapTypeId.ROADMAP
			         }
			      map = new google.maps.Map(document.getElementById("geomap"), myOptions);
			      var marker = new google.maps.Marker({
			          position: myLatlng, 
			          map: map,
			          icon:"resources/dashboard/Images/school_icon.png",
			      title:" ${school.school_address}"
			     });

		};

$(document)
		.ready(
				function() {

					initialize();

				

				});
	</script>
    
  	</div>  
     <jsp:include page="footer.jsp" />      