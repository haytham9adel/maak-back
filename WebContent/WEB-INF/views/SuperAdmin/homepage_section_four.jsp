<jsp:include page="header.jsp" />
<%@ page import="resources.Assets" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-9 col-sm-9 col-xs-12 m-side">

<div class="new-student-form"> 

<form class="form-horizontal" id="vechile" method="post">
<div class="form-group">
     <h3 class="dash_title">Heading </h3>
       <div class="col-sm-12 pad0">
        <input type="text" name="page_name" value="${content.page_name}" id="page_name" placeholder="" class="form-control">
       </div>
</div>   
 
<div class="form-group">
       <div class="col-sm-9 pad0">
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
			 ignore: [],
             debug: false,
          rules: {
        	   page_name:"required",
        	  
        },
          messages: {
        	   
        	  page_name:"Please enter page title",
        	  page_desc: "Please enter page description", 
	      },
          submitHandler: function(form) {
              form.submit();
          }
      });
      

});
  
  </script>
  
  <script src="//cdn.ckeditor.com/4.6.2/standard/ckeditor.js"></script>
<script>   
	CKEDITOR.replace( 'page_desc' );
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
			$('#myModal').modal('show');
		});
	
</script>
</c:if>
<script type="text/javascript">
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				var src = $('img[alt="s_image_path"]').attr('src', e.target.result);
				$("#slider_image").val( e.target.result);
			}

			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
    <jsp:include page="footer.jsp" />      