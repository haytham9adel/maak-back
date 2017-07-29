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
                     <label class="col-sm-3 control-label">Relationship:</label>
                       <div class="col-sm-9">
                       <input type="text" class="form-control" name="r_title" value="${relationship.r_title}" id="r_title">
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
        	  r_title: "required",
          },
          messages: {
        	  r_title: "Please enter Relationship",
        	 
       
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
			type : "error"
		}
	);
	
</script>

</c:if>

    <jsp:include page="footer.jsp" />      