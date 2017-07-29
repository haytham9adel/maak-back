
<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-6 m-side">

             <div class="new-student-form"> 
             <span class="success">${success}</span>
             <span class="error">${error}</span>
    <form class="form-horizontal" id="school" method="post">
                  <div class="form-group">
                     <label class="col-sm-3 control-label">School Name:</label>
                       <div class="col-sm-9">
                         <input type="text" class="form-control" id="school_name" value="${schoolModel.school_name}" name="school_name">
                        </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Responsible Person :</label>
                       <div class="col-sm-9">
                        <input type="text" value="${schoolModel.principal_name}" class="form-control" id="principal_name" name="principal_name">
                        </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Responsible Person's Contact Number :</label>
                       <div class="col-sm-9">
                        <input type="text" class="form-control" id="principal_contact" value="${schoolModel.principal_contact}" name="principal_contact">
                        </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Email :</label>
                       <div class="col-sm-9">
                        <input type="text" class="form-control" value="${schoolModel.email}" disabled="disabled">
                       </div>
                   </div>
                   
                 <%--   <div class="form-group">
                     <label class="col-sm-3 control-label">Contact Number :</label>
                       <div class="col-sm-9">
                      <input type="text" class="form-control" value="${schoolModel.contact_number}" id="contact_number" name="contact_number">
                       </div>
                   </div> --%>
                     <div class="form-group">
                     <label class="col-sm-3 control-label">Country :</label>
                       <div class="col-sm-9">
                         <select class="form-control" id="country" name="country" onchange="return getCity();">
                         <option value="">Select Country</option>
                         <c:if test="${!empty countries}">
                         	<c:forEach items="${countries}" var="county">
                         	<option <c:if test="${schoolModel.country==county.c_id}">
                         	<c:out value="selected"/>
                         	 </c:if>  value=<c:out value="${county.c_id}"/>><c:out value="${county.c_name}"/></option>
                         	</c:forEach>
                         </c:if>
                         </select>
                        </div>
                   </div>
                <!--     <div class="form-group">
                     <label class="col-sm-3 control-label">State :</label>
                       <div class="col-sm-9">
                        <select class="form-control" id="state" onchange="return getCity();" name="state">
                          <option value="">Select State</option>
                         </select>
                       </div>
                   </div> -->
                    <div class="form-group">
                     <label class="col-sm-3 control-label">City :</label>
                       <div class="col-sm-9">
                        <select class="form-control" id="city"  name="city">
                           <option value="">Select City</option>
                         </select>
                       </div>
                   </div>
              <div class="form-group">
                     <label class="col-sm-3 control-label">Zip code :</label>
                       <div class="col-sm-9">
                       <input type="text"  value="${schoolModel.zip_code}" name="zip_code" id="zip_code" class="form-control">
                       </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Total Buses :</label>
                       <div class="col-sm-9">
                      <input type="text" class="form-control" disabled="disabled" value="${schoolModel.total_bus}" >
                       </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Total Students :</label>
                       <div class="col-sm-9">
                        <input type="text" class="form-control" disabled="disabled" value="${schoolModel.total_students}" >
                       </div>
                   </div>
                   
                  <div class="form-group">
                       <div class="col-sm-9">
                        <input  class="btn btn-primary btn-submit" value="Update" type="submit">
                       </div>
                   </div>
                 
                
             </div> 
             
             </form>
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
		$("#school").validate({
          rules: {
        	  school_name: "required",
        	  email:{
						required: true,
						email: true
					},
					contact_number: "required",
					country: "required",
					state: "required",
					city: "required",
					zip_code: "required",
					total_bus: {
						number:true,
						required:true,
					},
					total_students:  {
						number:true,
						required:true,
					},
					principal_contact: "required",
					principal_name: "required",
          },
          messages: {
        	  school_name: "Please enter school name",
        	  email:{
						required: "Please enter email address",
						email: "Please enter valid email address"
					},
					contact_number: "Please enter contact number",
					country: "Please select country",
					state: "Please select state",
					city: "Please select city",
					zip_code: "required",
					total_bus: {
						number:"This field should be numeric",
						required:"Please enter total number of bus",
					},
					total_students:  {
						number:"This field should be numeric",
						required:"Please enter total number of students",
					},
					principal_contact: "Please enter principal contact",
					principal_name: "Please enter principal name",
	      },
          submitHandler: function(form) {
              form.submit();
          }
      });
      

});
  
  </script>
<script type="text/javascript">
    function getState() {
    	var c_id=$("#country").val();
   		  $.ajax({
            url : 'getStateByCountry.html',
            type:'post',
            data:{"Country_id":c_id},
            success : function(data) {
              $("#state").html(data);
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
            }
        });
   		  return false;
    }
    $(document).ready(function(){
    	var c_id=${schoolModel.country};
    	var city=${schoolModel.city}
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
</script>
          
          
    <jsp:include page="footer.jsp" />      