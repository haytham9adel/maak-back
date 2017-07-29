<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="resources.Assets" %>
<div class="col-md-6 col-sm-6 col-xs-6 m-side">

<div class="new-student-form"> 

<form class="form-horizontal" id="vechile" method="post">
<c:set var="theString" value="${feature_model.category_type}"/>


<div class="form-group">
     <label class="col-sm-12 control-label"><b>Category Type: </b> (ctrl for multiple)</label>
       <div class="col-sm-12">
       	<select onchange="getValue(this.value);" multiple class="form-control" id="category">
       		<option <c:if test="${fn:contains(theString, '1')}">selected</c:if>  value="1">Parent</option>
       		<option <c:if test="${fn:contains(theString, '2')}">selected</c:if> value="2">School</option>
       		<option <c:if test="${fn:contains(theString, '3')}">selected</c:if> value="3">Student</option>
       	</select> 
       	<input type="hidden" id="category_type" value="${feature_model.category_type}" name="category_type">
       </div>
</div> 
<div class="form-group">
     <label class="col-sm-12 control-label"><b>Title:</label>
       <div class="col-sm-12">
       	<input type="text" class="form-control" value="${feature_model.title}" name="title" id="title">
       </div>
</div>   
<div class="form-group">
	<label class="col-sm-12 control-label"><b>Image: </b> in size(222x222)px</label>
	<div class="col-sm-12">
	<c:choose>
			<c:when test="${feature_model.image_path!=''}">
				<td><img id="blah"  alt="s_image_path" src="<%=Assets.FEATURES_IMAGE %>${feature_model.image_path}"/></td>
			</c:when>
			<c:otherwise>
				<td><img id="blah" src="resources/dashboard/Images/no.jpg" alt="s_image_path" /></td>
			</c:otherwise>
	</c:choose>
	<div class="cp_upload">
      <label for="imgInp" class="btn"><i class="fa fa-camera"></i> Upload </label>
      <input onchange="readURL(this)" id="imgInp" name="image" style="visibility:hidden;" type="file">
      <input type="hidden" name="image_path" id="image_path" value="" class="form-control">
    </div>
	<!-- <input type='file' onchange="readURL(this)" id="imgInp" />
		<input type="hidden" name="image_path" id="image_path" value="" class="form-control"> -->
	</div>
</div>
<div class="form-group">
     <label class="col-sm-12 control-label"><b>Content:</b></label>
       <div class="col-sm-12">
       <textarea class="form-control" name="content" id="content" rows="8">${feature_model.content}</textarea>
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