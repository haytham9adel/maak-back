<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css">
<div class="col-md-6 col-sm-6 col-xs-12 m-side">
             <div class="new-student-form"> 
             <%--  <span class="success">${success}</span>
             <span class="error">${error}</span> --%>
                <form class="form-horizontal" id="student" method="post">
              
                  <div class="form-group">
                     <label class="col-sm-3 control-label">First Name :</label>
                       <div class="col-sm-9">
                         <input type="text" name="first_name" value="${parent.first_name}" id="first_name" class="form-control">
                        </div>
                   </div>
                     <div class="form-group">
                     <label class="col-sm-3 control-label">Middle Name   :</label>
                       <div class="col-sm-9">
                         <input type="text" name="middle_name" value="${parent.middle_name}" id="middle_name" class="form-control">
                        </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Family Name   :</label>
                       <div class="col-sm-9">
                         <input type="text" name="family_name" value="${parent.family_name}" id="family_name" class="form-control">
                        </div>
                   </div>
                   <%--  <div class="form-group">
                     <label class="col-sm-3 control-label">Mobile Name <nowiki>*</nowiki>:</label>
                       <div class="col-sm-9">
                         <input type="text" name="mobile_number" value="${parent.mobile_number}" id="mobile_number" class="form-control">
                        </div>
                   </div> --%>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Mobile Number <nowiki>*</nowiki>:</label>
                       <div class="col-sm-9">
                       	<div class="input-group-addon zero">${country_details.c_code}</div>
                      
                         <input type="text" style="padding:5px 55px;" disabled="disabled"  name="mobile_number" value="${parent.mobile_number}" id="mobile_number" class="form-control">
                        </div>
                   </div>
                    <%--  <div class="form-group">
                     <label class="col-sm-3 control-label">Last Name :</label>
                       <div class="col-sm-9">
                         <input type="text" name="last_name" value="${parent.last_name}" id="last_name" class="form-control">
                        </div>
                   </div> --%>
                     <div class="form-group">
                     <label class="col-sm-3 control-label">Username :</label>
                       <div class="col-sm-9">
                         <input type="text"  value="${parent.user_name}" disabled="disabled" class="form-control">
                        </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Email :</label>
                       <div class="col-sm-9">
                         <input type="text" name="user_email"  value="${parent.user_email}"  id="user_email" class="form-control">
                        </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Password :</label>
                       <div class="col-sm-9">
                        <input type="text" name="user_pass" value="${parent.user_pass}" id="user_pass" class="form-control">
                       </div>
                   </div>
                     <div class="form-group">
                    <label class="col-sm-3 control-label">Contact Number  :</label>
                       <div class="col-sm-9">
                        <input type="text" name="contact_number" value="${parent.contact_number}" id="contact_number" class="form-control">
                       </div>
                   </div>
                   <!--  <div class="form-group">
                       <div class="col-sm-9">
                        <input  class="btn btn-primary" value="Update Parent" type="submit">
                       </div>
                   </div> -->
                   <div class="form-group">
                       <div class="col-sm-9">
                        <input  class="btn btn-primary btn-submit" value="Save" id="save" type="submit">&nbsp;&nbsp;
                        <a href="admin/manageParents">
                        <input  class="btn btn-primary btn-submit" value="Cancel" id="cancel" type="button">
                        </a>
                       </div>
                   </div>
                </form>
             </div> 
          </div>
          <div class="col-md-3 col-sm-3 col-xs-12">
              <div class="info-block"> 
                <h4>Send Password</h4>
                 <div class="col-md-12">&nbsp;</div>
                <div class="col-md-12"> 
                <form action="admin/sendParentPassword" method="post">
                <input type="hidden" name="user_id" value="${parent.user_id}">
                <input type="hidden" name="contact_number" value="${parent.mobile_number}">
                <input type="hidden" name="mobile_number" value="${country_details.c_code}">
                
               <% String url=request.getAttribute("javax.servlet.forward.servlet_path").toString();%>
                <input type="hidden" name="user_pass" value="<%=url%>"/>
                <textarea name="school_name" class="form-control" rows="5" cols="5">Dear ${parent.first_name},&#13;&#10;Your Login details as below&#13;&#10;Username: ${parent.user_name}&#13;&#10;password: ${parent.user_pass}</textarea>
                <div class="col-md-12">&nbsp;</div>
                <input type="submit" class="btn btn-submit" value="Send">
                </form>
                </div>
              </div> 
                
        </div> 
        <script src="resources/front/js/jquery.validate.min.js"> </script>
 <script>
  $(document).ready(function(){
		$("#student").validate({
          rules: {
        	  /* first_name: "required",
        	  last_name: "required",
        	  user_email:{
						required: true,
						email: true
					},
			  contact_number:"required" */
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
	swal("Success!", "${success}", "success");
</script>
</c:if>
<c:if test="${!empty error}">
<script>
	swal("Error!", " ${error} ", "error");
</script>
</c:if>

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
					window.location = "admin/manageParents";
			});
		})	 
  });
  
  </script>        
    <jsp:include page="footer.jsp" />      