<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-6 col-sm-6 col-xs-12 m-side">

             <div class="new-student-form"> 
             <c:if test="${success}">
             </c:if>
              <span class="success">${success}</span>
             <span class="error">${error}</span>
             <h4 class="page-heading" style="margin-left:85px;">Please enter your email and password !</h4>
                <form class="form-horizontal" id="second_login" method="post">
                	
                	  <div class="form-group">
                     <label class="col-sm-3 control-label">Email:</label>
                       <div class="col-sm-9">
                        <input type="text" value="" name="user_email" id="user_email" class="form-control">
                       </div>
                   </div>
	
					<div class="form-group">
					  <label class="col-sm-3 control-label">Password:</label>
					    <div class="col-sm-9">
					     <input type="password" value="" name="user_pass" id="user_pass" class="form-control">
					    </div>
					</div>
                   
                   
                    <div class="form-group">
                       <div class="col-sm-offset-3 col-sm-10">
                                    <button type="submit" class="btn btn-submit">Login</button>
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
        	  user_pass: "required",
        	  user_email:{
						required: true,
						email: true
					}
		    },
          messages: {
        	 			user_pass: "Please enter password",
					   	user_email:
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