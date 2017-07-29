<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<div class="col-md-6 col-sm-6 col-xs-12 m-side">

             <div class="new-student-form"> 
          
           
                <form class="form-horizontal" id="staff" method="post">
                  <c:if test="${!empty success}">
<script>
 		
$(document).ready(function(){
	
	myFunction();
});

</script>
<script>
function myFunction() {
    document.getElementById("myForm").reset();
}
</script>
 </c:if>
                <input type="hidden" name="driver_school_id" value="<%= session.getAttribute("new_school_id") %>">
             
                  	
                      <div class="form-group">
                     <label class="col-sm-3 control-label">Class Name :</label>
                       <div class="col-sm-9">
                        <input type="text" value="" name="class_name" id="class_name" class="form-control">
                       </div>
                   </div>
                 <div class="form-group">
                       <div class="col-sm-9">
                        <input  class="btn btn-primary btn-submit" value="Save" type="submit">&nbsp;&nbsp;
                        <a href="admin/manageClasses">
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
		$("#staff").validate({
          rules: {
        	  class_name: "required",
          },
          messages: {
        	  class_name: "Please enter class name",
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
  <script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css">
  <c:if test="${!empty success}">
<script>
	swal("Success!\n\n", "Class added successfully", "success");
</script>
</c:if>
<c:if test="${!empty error}">
<script>
	swal("Error!", " ${error} ", "error");
</script>
</c:if>            
    <jsp:include page="footer.jsp" />      