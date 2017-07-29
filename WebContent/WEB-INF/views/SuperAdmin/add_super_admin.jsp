<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css">
<div class="col-md-6 col-sm-6 col-xs-12 m-side">
             <div class="new-student-form"> 
                <form class="form-horizontal" id="student" method="post">
                
    
         		  <div class="form-group">
                     <label class="col-sm-12 control-label">First Name :</label>
                       <div class="col-sm-12">
                       
                         <input type="text" name="first_name" value="${schooladmin.first_name}" id="first_name" class="form-control">
                        </div>
                   </div>
                   
                         <div class="form-group">
                     <label class="col-sm-12 control-label">Last Name :</label>
                       <div class="col-sm-12">
                       
                         <input type="text" name="last_name" value="${schooladmin.last_name}" id="last_name" class="form-control">
                        </div>
                   </div>
   					<div class="form-group">
                     <label class="col-sm-12 control-label">Privileges :</label>
                       <div class="col-sm-12">
                       
                        <div class="radio">
						 
	                         <label>
	                            <input type="radio" name="permission" checked="checked" value="0"  id="permission"> Just View
	   
	                         </label>
                             <label>
	                            <input type="radio" name="permission"  value="1"  id="permission"> Main Super Admin
	   
	                         </label>
                       </div>
                        </div>
                   </div>

                   <div class="form-group">
                     <label class="col-sm-12 control-label">Email :</label>
                       <div class="col-sm-12">
                         <input type="text" name="user_email" value="${schooladmin.user_email}"  id="user_email" class="form-control">
                        </div>
                   </div>
                     <div class="form-group">
                     <label class="col-sm-12 control-label">Contact Number :</label>
                       <div class="col-sm-12">
                        <input type="text" name="contact_number" value="${schooladmin.contact_number}" id="contact_number" class="form-control">
                       </div>
                   </div>
              <div class="form-group">
                       <div class="col-sm-12">
                        <input  class="btn btn-primary btn-submit" value="Save" type="submit">&nbsp;&nbsp;
                        <a href="admin/manageSuperAdmin">
                        <input  class="btn btn-primary btn-submit" value="Cancel" type="button">
                        </a>
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
               <div class="col-b" style="text-align:right;">
               		<a href="admin/editSuperAdmin?user_id=${school_admin.user_id}"><i class="fa fa-pencil-square-o fa-2x edit"></i></a>
               </div>
               </div>
                 <div class="info-group">
                 	 <div class="col-a">Name : </div>
                    <div class="col-b"> ${school_admin.first_name}     ${school_admin.last_name}   </div> 
                 </div>
                 <div class="info-group">
                    <div class="col-a"> Username : </div>
                    <div class="col-b"> ${school_admin.user_name} </div> 
                 </div>
                 <div class="info-group">
                    <div class="col-a">Privileges : </div>
                    <div class="col-b"> <c:choose>
								<c:when test="${school_admin.permission==0}">
									Just View
									</c:when>
									<c:otherwise>
									Main School Admin
									</c:otherwise>
	                            </c:choose> </div> 
                 </div>
                 <div class="info-group">
                    <div class="col-a">Email : </div>
                    <div class="col-b"> ${school_admin.user_email} </div> 
                 </div>
                 <div class="info-group">
                    <div class="col-a"> Contact Number : </div>
                    <div class="col-b"> ${school_admin.contact_number} </div> 
                 </div>
                 
              </div> 
              		<c:set var="count" value="${count + 1}" scope="page"/>
     			 </c:forEach>
        	
                 
          
        </div>
        <script src="resources/front/js/jquery.validate.min.js"> </script>
  <script>
  $(document).ready(function(){
		$("#student").validate({
			 rules: {
	        	  school_name: "required",
	        	  user_email:{
							required: true,
							email: true
						},
						contact_number: "required",
						school_address: "required",
						first_name: "required",
						last_name: "required",
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
	        	  user_email:{
							required: "Please enter email address",
							email: "Please enter valid email address"
						},
						contact_number: "Please enter contact number",
						school_address: "Please enter school address",
						first_name: "Please enter firstname",
						last_name: "Please enter lastname",
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

   <c:if test="${!empty success}">
<script>
	swal("Super Admin has been added successfully!\n\n *Note", "School Admin username : ${username} \n\nSchool Admin password : ${password} ", "success");
</script>
</c:if>
<c:if test="${!empty error}">
<script>
	swal("Error!", " ${error} ", "error");
</script>
</c:if>       
    <jsp:include page="footer.jsp" />      