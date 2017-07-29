<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-9 col-sm-9 col-xs-12 m-side">

<div class="new-student-form"> 

<form class="form-horizontal" id="vechile" method="post">
<div class="form-group">
     <label class="col-sm-12 control-label"><b>Page Title:</b> </label>
       <div class="col-sm-12">
        <input type="text" name="page_name" value="${content.page_name}" id="page_name" placeholder="" class="form-control">
       </div>
</div>   
 
<div class="form-group">
     <label class="col-sm-12 control-label"><b>Description:</b> </label>
       <div class="col-sm-12">
        <textarea name="page_desc" rows="5" id="page_desc" class="form-control">${content.page_desc}</textarea>
       </div>
</div>



<div class="form-group">
       <div class="col-sm-9">
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
    <jsp:include page="footer.jsp" />      