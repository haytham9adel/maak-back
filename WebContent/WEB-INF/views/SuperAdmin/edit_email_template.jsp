<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-8 col-sm-8 col-xs-8 m-side">

<div class="new-student-form"> 

<form class="form-horizontal" id="vechile" method="post">

<div class="form-group">
     <label class="col-sm-12 control-label"><b>Template Text:</label>
       <div class="col-sm-12">
       	  <textarea class="form-control" name="desc" id="desc" rows="6">${templates.description}</textarea>
       </div>
</div>   
 
<div class="form-group">
       <div class="col-sm-12">
       <input type="submit" value="Update" class="btn btn-primary btn-submit">
       </div>
</div>     
   </form>
</div> 
             
          </div>
  
<script src="resources/front/js/jquery.validate.min.js"> </script>
  <script>
  $(document).ready(function(){
		$("#vechile").validate({
          rules: {
			question:"required",
        	answer:"required",
       },
          messages: {
			question:"Please enter question",
			answer:"Please enter answer",
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
			text : "",
			type : "success"
		}, function() {
			
		});
	
</script>
</c:if>
  <script src="//cdn.ckeditor.com/4.6.2/standard/ckeditor.js"></script>
<script>   
	CKEDITOR.replace( 'desc' );
</script>
    <jsp:include page="footer.jsp" />      