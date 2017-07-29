<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-6 col-sm-6 col-xs-12 m-side">

             <div class="new-student-form"> 
             <c:if test="${success}">
             </c:if>
             <%--  <span class="success">${success}</span>
             <span class="error">${error}</span> --%>
                <form class="form-horizontal" id="staff" method="post">
                	
                <%-- 	 <div class="form-group">
                     <label class="col-sm-3 control-label">School:</label>
                       <div class="col-sm-9">
                       
                       <select class="form-control" id="school_id" name="school_id" >
                         <option value="">Select School</option>
                         <c:if test="${!empty schools}">
                         	<c:forEach items="${schools}" var="school">
                         	<option  value=<c:out value="${school.s_id}"/>><c:out value="${school.school_name}"/></option>
                         	</c:forEach>
                         </c:if>
                         </select>
                     </div>
                   </div> --%>
                
                
                      <div class="form-group">
                     <label class="col-sm-3 control-label">Country Name:</label>
                       <div class="col-sm-9">
                       <input type="text" class="form-control" name="c_name" value="${country_details.c_name}" id="c_name">
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Country Code :</label>
                       <div class="col-sm-9">
                     
                         <input type="text" name="c_code" value="${country_details.c_code}" id="c_code" class="form-control">
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
		$("#staff").validate({
          rules: {
        	  c_name: "required",
        	  c_code: "required",
          },
          messages: {
         	  c_name: "Please enter country name",
        	  c_code: "Please enter country code",
       
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


    <jsp:include page="footer.jsp" />      