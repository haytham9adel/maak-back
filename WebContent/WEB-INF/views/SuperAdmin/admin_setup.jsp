<jsp:include page="header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-9 col-sm-9 col-xs-12 m-side">

             <div class="new-student-form"> 
             <c:if test="${success}">
             </c:if>
             <h3 class="dash_title">Email Setup</h3>
                <form class="form-horizontal" id="second_login" method="post" action="">
                	
                   <div class="form-group">
                    <label class="col-sm-3 pad0">Email</label>
                       <div class="col-sm-9 pad0">
                       	<input type="text" autocompletetype="none"  autocomplete="off" class="form-control" value="${details.email_id}" name="email_id" id="email_id">
                      </div>
                   </div>
                   <div class="form-group">
                   <label class="col-sm-3 pad0">Password																					</label>
                       <div class="col-sm-9 pad0">
                       	<input type="password"  autocomplete="off" autocompletetype="none" class="form-control" value="${details.password}" name="password" id="password">
                      </div>
                   </div>
                   
                    <div class="form-group">
                       <div class="col-sm-9 pad0">
                        <input  class="btn btn-primary btn-submit" value="Update" type="submit">
                       </div>
                   </div>
                </form>
             </div> 
          </div>
		<script src="resources/front/js/jquery.validate.min.js"> </script>
  <script>
  $(document).ready(function(){
		$("#second_login").validate({
          rules: {
        	  password: "required",
        	  email_id:{
						required: true,
						email: true
					}
		    },
          messages: {
        	 			password: "Please enter password",
					   	email_id:
					   	  {
						 		required: "Please enter email address",
						 		email: "Please enter valid email address"
				          }
						
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
	swal("${success}", "success");
</script>
</c:if>
<c:if test="${!empty error}">
<script>
	swal("Error!", " ${error} ", "error");
</script>
</c:if>
<jsp:include page="footer.jsp" />      