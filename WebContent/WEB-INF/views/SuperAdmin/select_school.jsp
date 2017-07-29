<jsp:include page="header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-9 col-sm-9 col-xs-12 m-side">

             <div class="new-student-form"> 
             <c:if test="${success}">
             </c:if>
              <span class="success">${success}</span>
             <span class="error">${error}</span>
             <h3 class="dash_title">Select School</h3>
                <form class="form-horizontal" id="second_login" method="post" action="selectSchool">
                	
                	   <div class="form-group">
                       <div class="col-sm-12 pad0">
                       <select class="form-control" id="s_id" name="s_id">
                         <option value="">Select School</option>
                         <c:if test="${!empty schools}">
                         	<c:forEach items="${schools}" var="school">
                         	<option  value=<c:out value="${school.s_id}"/>>
                         	
                         	${school.school_name}
                         	</option>
                         	</c:forEach>
                         </c:if>
                         </select>
                      </div>
                   </div>
                   
                    <div class="form-group">
                       <div class="col-sm-9 pad0">
                        <input  class="btn btn-primary btn-submit" value="Proceed" type="submit">
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