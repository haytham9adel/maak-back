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
                         <input type="text" name="first_name" value="${parentBean.first_name}" id="first_name" class="form-control">
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
                       	<div class="input-group-addon zero">${country_details.c_code}</div>
                       	<input type="hidden" name="last_name" value="${country_details.c_code}"   class="form-control">
                         <input type="text" style="padding:6px 55px;" name="mobile_number" value="${parentBean.mobile_number}" id="mobile_number" class="form-control">
                        </div>
                   </div>
                     <%-- <div class="form-group">
                     <label class="col-sm-3 control-label">Last Name :</label>
                       <div class="col-sm-9">
                         <input type="text" name="last_name" value="${parentBean.last_name}" id="last_name" class="form-control">
                        </div>
                   </div> --%>
                   
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Email :</label>
                       <div class="col-sm-9">
                         <input type="text" name="user_email" value="${parentBean.user_email}"  id="user_email" class="form-control">
                        </div>
                   </div>
                     <div class="form-group">
                     <label class="col-sm-3 control-label">Contact Number :</label>
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
          
        <script src="resources/front/js/jquery.validate.min.js"> </script>
  <script>
  $(document).ready(function(){
		$("#student").validate({
          rules: {
        	/*   first_name: "required",
        	  family_name: "required",
        	  middle_name: "required",
        	  mobile_number: "required",
        	  last_name: "required",
        	  user_email:{
						required: true,
						email: true
					}, */
			  mobile_number: "required",
			  
	      },
          messages: {
        	  first_name: "Please enter first name",
        	  family_name: "Please enter family name",
        	  middle_name: "Please enter middle name",
        	  mobile_number: "Please enter mobile number",
        	  last_name: "Please enter last name",
        	  user_email:{
						required: "Please enter email address",
						email: "Please enter valid email address"
					},
		  
					
		  },
          submitHandler: function(form) {
              form.submit();
          }
      });
      

});
  
  </script>

       <c:if test="${!empty success}">
<script>
	swal(
			{   title : "Parent has been added successfully!\n\n",
				text : "Parent username : ${username} \n\nParent password : ${password} "
				, type :"success"}, function() {
		$('#myModal').modal('show');
	});
</script>
</c:if>
<c:if test="${!empty error}">
<script>
	swal("Error!", " ${error} ", "error");
</script>
</c:if>  

<!-- Modal -->
	<div id="myModal_sms" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Send SMS</h4>
				</div>
				<div class="modal-body">
					<form action="admin/sendDriverPassword2" method="post">
						
						<input type="hidden" name="contact_number" value="${driver_contact}">
						<input type="hidden" name="mobile_number" value="${country_details.c_code}">
						<textarea name="first_name" class="form-control" style="height: 152px; resize: none; margin-bottom: 15px;">Your Login details as below&#13;&#10;Username: ${username}&#13;&#10;password: ${password}</textarea>
						<button type="submit" class="btn btn-default">Send</button>
					</form>
				</div>
			</div>

		</div>
	</div>
	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Send SMS</h4>
				</div>
				<div class="modal-body">
					<p>Do you want to send sms now or later</p>
					<p>

						<a href="javascript:void(0);" data-dismiss="modal"
							class="btn btn-success" data-toggle="modal"
							data-target="#myModal_sms">Yes</a> &nbsp; <a
							href="admin/addParent/" class="btn btn-warning">No</a>
					</p>
				</div>
				<!-- 
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div> -->
			</div>

		</div>
	</div>            
    <jsp:include page="footer.jsp" />      