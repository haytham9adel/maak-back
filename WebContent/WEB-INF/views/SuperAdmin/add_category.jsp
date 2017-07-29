<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-6 col-sm-6 col-xs-6 m-side">

<div class="new-student-form"> 

<form class="form-horizontal" id="vechile" method="post">


<div class="form-group">
	<label class="col-sm-12 control-label"><b>Category: </b> </label>
	<div class="col-sm-12">
		<input type="text" name="category_en" id="category_en" value="" class="form-control">
	</div>
</div>

<div class="form-group">
       <div class="col-sm-12">
       <input type="submit" value="Add" class="btn btn-primary btn-submit">
       </div>
</div>     
   </form>
</div> 
             
          </div>
<script>
function setSize(id)
{
	if(id=="0")
	{
		$("#size-image").html("Min. size(1365x365)");	
	}
	if(id=="1")
	{
		$("#size-image").html("Min. size(268x268)");
	}
}

</script>     
<script src="resources/front/js/jquery.validate.min.js"> </script>
  <script>
  $(document).ready(function(){
		$("#vechile").validate({
          rules: {
        	  category_en:"required"
		 },
          messages: {
        	  category_en:"Please enter category"
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