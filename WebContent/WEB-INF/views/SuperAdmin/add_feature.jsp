<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-6 col-sm-6 col-xs-6 m-side">

<div class="new-student-form"> 

<form class="form-horizontal" id="vechile" method="post">

<div class="form-group">
     <label class="col-sm-12 control-label"><b>Category Type:</b> (ctrl for multiple)</label>
       <div class="col-sm-12">
       	<select onchange="getValue(this.value);" multiple class="form-control" id="category">
       		<option value="1">Parent</option>
       		<option value="2">School</option>
       		<option value="3">Student</option>
       	</select> 
       	<input type="hidden" id="category_type" name="category_type">
       </div>
</div> 
<div class="form-group">
     <label class="col-sm-12 control-label"><b>Title:</label>
       <div class="col-sm-12">
       	<input type="text" class="form-control" name="title" id="title">
       </div>
</div>   
<div class="form-group">
	<label class="col-sm-12 control-label"><b>Slider Image:</b> Min.size(225x196)</label>
	<div class="col-sm-12 pad0">
		<img id="blah" src="resources/dashboard/Images/no.jpg" alt="s_image_path" />
		<input type='file' onchange="readURL(this)" id="imgInp" />
		<input type="hidden" name="image_path" id="image_path" value="" class="form-control">
	</div>
</div>
<div class="form-group">
     <label class="col-sm-12 control-label"><b>Content:</b></label>
       <div class="col-sm-12">
       <textarea class="form-control" name="content" id="content"></textarea>
       </div>
</div> 
<div class="form-group">
       <div class="col-sm-12 pad0">
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
        	title:"required",
        	content:"required",
       },
          messages: {
        	  title:"Please enter title",
          	content:"Please enter content",
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