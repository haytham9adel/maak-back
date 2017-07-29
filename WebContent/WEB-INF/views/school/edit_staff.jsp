<jsp:include page="header.jsp" />
<%@ page import="resources.Assets" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-6 m-side">

             <div class="new-student-form"> 
             <c:if test="${success}">
             </c:if>
              <span class="success">${success}</span>
             <span class="error">${error}</span>
                <form class="form-horizontal" id="staff" method="post">
                      <div class="form-group">
                     <label class="col-sm-3 control-label">Department:</label>
                       <div class="col-sm-9">
                       <select class="form-control" id="dept_id" name="dept_id" >
                         <option value="">Select Department</option>
                         <c:if test="${!empty departments}">
                         	<c:forEach items="${departments}" var="depart">
                         	<option  <c:if test="${depart.depart_id==staff.dept_id}">
                         	<c:out value="selected"/>
                         	 </c:if> value=<c:out value="${depart.depart_id}"/>><c:out value="${depart.depart_name}"/></option>
                         	</c:forEach>
                         </c:if>
                         </select>
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Name :</label>
                       <div class="col-sm-9">
                       <input type="hidden" name="school_id" value="<%=session.getAttribute("schoolId") %>">
                         <input type="text" name="staff_name" value="${staff.staff_name}" id="staff_name" class="form-control">
                          <input type="hidden"  name="image_path" id="image_path" value="" class="form-control">
                        </div>
                   </div>
                     <div class="form-group">
                     <label class="col-sm-3 control-label">Last Name :</label>
                       <div class="col-sm-9">
                        <input type="text" value="${staff.staff_lname}" name="staff_lname" id="staff_lname" class="form-control">
                       </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Profile Picture :</label>
                       <div class="col-sm-9">
                        	<div class="profile-pic-block">
               <div class="profile-thumb">
               <c:choose>
          <c:when test="${staff.image_path!=''}"><td><img id="blah" alt="image_path"  class="manage-icon-big" src="<%=Assets.STAFF_UPLOAD_PATH_DIS %>/${staff.image_path}"/>
          
           </c:when>
           <c:otherwise>
           <img id="blah" src="resources/dashboard/Images/user_icon.png" alt="image_path" />
            </c:otherwise>
           </c:choose>
                 </div>
               <input type='file' onchange="readURL(this)" id="imgInp" />
             <!--   <button class="upload-pic" type="submit"> 
               Upload your Profile Picture </button> -->
             </div>
                       </div>
</div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Email :</label>
                       <div class="col-sm-9">
                        <input type="email" disabled="disabled" name="staff_email" value="${staff.staff_email}" id="staff_email" class="form-control">
                       </div>
                   </div>
                   
                   
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Contact Number :</label>
                       <div class="col-sm-9">
                        <input type="text" value="${staff.staff_contact}" name="staff_contact" id="staff_contact" class="form-control">
                       </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Address :</label>
                       <div class="col-sm-9">
                       <textarea class="form-control"  name="address" id="address" rows="3">${staff.address}</textarea>
                       </div>
                   </div>
                   
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Qualification :</label>
                       <div class="col-sm-9">
                       <input type="text" name="education" id="education"  value="${staff.education}" class="form-control">
                       </div>
                   </div>
                   
                    <div class="form-group">
                       <div class="col-sm-9">
                        <input  class="btn btn-primary" type="submit">
                       </div>
                   </div>
                </form>
             </div> 
             
          </div>
          <div class="col-md-3">
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
                 
          
        </div>
        <script src="resources/front/js/jquery.validate.min.js"> </script>
  <script>
  $(document).ready(function(){
		$("#staff").validate({
          rules: {
        	  staff_name: "required",
        	  address: "required",
        	  dept_id: "required",
        	  staff_email:{
						required: true,
						email: true
					},
				staff_contact: "required",
			
				education: "required",
			
					
          },
          messages: {
					   	  staff_name: "Please enter  name",
					   	  dept_id: "Please select Department",
					   	staff_email:
					   	  {
						 		required: "Please enter email address",
						 		email: "Please enter valid email address"
				           	},
						staff_contact: "Please enter contact number",
						education: "Please enter qualification",
						address:"Please enter address",
	      },
          submitHandler: function(form) {
              form.submit();
          }
      });
      

});
  
  </script>
<script>
function myFunction() {

    document.getElementById("staff").reset(); 
}
</script>
  <script type="text/javascript">
    function readURL(input) {
    	if (input.files && input.files[0]) {
            var reader = new FileReader();
            
            reader.onload = function (e) {
                $('#blah').attr('src', e.target.result);
                var src = $('img[alt="image_path"]').attr('src');
                $("#image_path").val(src);
            }
            
            reader.readAsDataURL(input.files[0]);
        }
    }
  </script>

          
    <jsp:include page="footer.jsp" />      