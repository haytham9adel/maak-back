<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css">
<div class="col-md-6 col-sm-6 col-xs-12 m-side">
             <div class="new-student-form"> 
                <form class="form-horizontal" id="student" method="post">
                    <div class="form-group">
                     <label class="col-sm-3 control-label">First Name :</label>
                       <div class="col-sm-9">
                         <input type="text" name="first_name" value="${parentBean.first_name}" id="first_name" class="form-control">
                           <input type="hidden" name="p_status" value="${p_status}" id="p_status" class="form-control">
                         <input type="hidden" name="school_id" value="<%= session.getAttribute("new_school_id") %>">
                        </div>
                   </div>
                    <div class="form-group">
                     <label class="col-sm-3 control-label">Middle Name   :</label>
                       <div class="col-sm-9">
                         <input type="text" name="middle_name" value="${parentBean.middle_name}" id="middle_name" class="form-control">
                        </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Family Name   :</label>
                       <div class="col-sm-9">
                         <input type="text" name="family_name" value="${parentBean.family_name}" id="family_name" class="form-control">
                        </div>
                   </div>
                    <div class="form-group">
                     <label class="col-sm-3 control-label">Mobile Number <nowiki>*</nowiki>:</label>
                       <div class="col-sm-9">
                         <input type="text" name="mobile_number" value="${parentBean.mobile_number}" id="mobile_number" class="form-control">
                        </div>
                   </div>
                   <%-- 
                     <div class="form-group">
                     <label class="col-sm-3 control-label">Last Name :</label>
                       <div class="col-sm-9">
                         <input type="text" name="last_name" value="${parentBean.last_name}" id="last_name" class="form-control">
                        </div>
                   </div> --%>
                 <%--  <div class="form-group">
                     <label class="col-sm-3 control-label">Username :</label>
                       <div class="col-sm-9">
                       
                         <input type="text" name="user_name" value="${parentBean.user_name}" id="user_name" class="form-control">
                        </div>
                   </div> --%>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Email :</label>
                       <div class="col-sm-9">
                         <input type="text" name="user_email" value="${parentBean.user_email}"  id="user_email" class="form-control">
                        </div>
                   </div>
              <%--      <div class="form-group">
                     <label class="col-sm-3 control-label">Password :</label>
                       <div class="col-sm-9">
                        <input type="password" name="user_pass" value="${parentBean.user_pass}" id="user_pass" class="form-control">
                       </div>
                   </div> --%>
                     <div class="form-group">
                     <label class="col-sm-3 control-label">Contact Number <nowiki>*</nowiki>:</label>
                       <div class="col-sm-9">
                        <input type="text" name="contact_number" value="${parentBean.contact_number}" id="contact_number" class="form-control">
                       </div>
                   </div>
                    <div class="form-group">
                       <div class="col-sm-9">
                        <input  class="btn btn-primary btn-submit" value="Save" type="submit">&nbsp;&nbsp;
                        <a href="admin/manageParents">
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
		$("#student").validate({
		    rules: {
		    	  mobile_number: "required",
				  contact_number:"required"
		      },
	          messages: {
	        	  first_name: "Please enter first name",
	        	  last_name: "Please enter last name",
	        	  user_email:{
							required: "Please enter email address",
							email: "Please enter valid email address"
						},
			   contact_number:"Please enter contact number"
						
			  },
	          submitHandler: function(form) {
	              form.submit();
	          }
      });
      

});
  
  </script>
 <c:if test="${!empty success}">
<script>
	swal("Parent has been added successfully!\n\n *Note", "Parent username : ${username} \n\nParent password : ${password} ", "success");
</script>
</c:if>
<c:if test="${!empty error}">
<script>
	swal("Error!", " ${error} ", "error");
</script>
</c:if>  
          
    <jsp:include page="footer.jsp" />      