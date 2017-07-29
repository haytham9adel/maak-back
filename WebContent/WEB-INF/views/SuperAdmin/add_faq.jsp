<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-6 col-sm-6 col-xs-6 m-side">

<div class="new-student-form"> 

<form class="form-horizontal" id="vechile" method="post">

<div class="form-group">
     <label class="col-sm-12 control-label"><b>Category :</b></label>
       <div class="col-sm-12">
       	<select  class="form-control" id="category" name="category">
       		<option value="General">General</option>
       		<option value="Functionality">Functionality</option>
       		<option value="Technology">Technology</option>
       		<option value="Other questions">Other questions</option>
       	</select> 
       	 
       </div>
</div> 
<div class="form-group">
     <label class="col-sm-12 control-label"><b>Question:</label>
       <div class="col-sm-12">
       	<input type="text" class="form-control" name="question" id="question">
       </div>
</div>   

<div class="form-group">
     <label class="col-sm-12 control-label"><b>Content:</b></label>
       <div class="col-sm-12">
       <textarea class="form-control" name="answer" id="answer"></textarea>
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
function getValue(id)
{
 var foo = []; 
	$('#category :selected').each(function(i, selected){ 
	  foo[i] = $(selected).val(); 
	});
	$("#category_type").val(foo.toString());
     
}

</script>     
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
				$("#image_path").val( e.target.result);
			}

			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
    <jsp:include page="footer.jsp" />      