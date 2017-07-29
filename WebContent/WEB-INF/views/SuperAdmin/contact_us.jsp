<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-9 col-sm-9 col-xs-12 m-side">

<div class="new-student-form"> 

<form class="form-horizontal" id="vechile" method="post">
<div class="form-group">
     <label class="col-sm-12 control-label"><b>Address:</b> </label>
       <div class="col-sm-12">
        <input type="text" name="address" value="${content.address}" id="address" placeholder="" class="form-control">
       </div>
</div>   
<div class="form-group">
     <label class="col-sm-12 control-label"><b>Email:</b> </label>
       <div class="col-sm-12">
        <input type="text" name="email" value="${content.email}" id="email" placeholder="" class="form-control">
       </div>
</div>
<div class="form-group">
     <label class="col-sm-12 control-label"><b>Phone Number:</b> </label>
       <div class="col-sm-12">
        <input type="text" name="phone_number" id="phone_number" class="form-control" value="${content.phone_number}">
       </div>
</div>

<div class="form-group">
     <label class="col-sm-12 control-label"><b>Website:</b> </label>
       <div class="col-sm-12">
        <input type="text" name="website" id="website" class="form-control" value="${content.website}">
       </div>
</div>
<div class="form-group">
     <label class="col-sm-12 control-label"><b>Map Link:</b> </label>
       <div class="col-sm-12">
        <textarea name="location" id="location" class="form-control">${content.location}</textarea>
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
        	  school_id:"required",
        	  vehile_name: "required",
        	  manufacture: "required",
        	  model: "required",
        	  year: "required",
        	  color: "required",
        	  configurtion: "required",
        	  engine: "required",
        	  bus_number:"required"
		 },
          messages: {
        	  school_id:"Please select school",
        	  vehile_name: "Please enter vehicle name",
        	  manufacture: "Please enter manufacture company",
        	  model: "Please enter vehicle model number",
        	  year: "Please enter vehicle year",
        	  color: "Please enter vehicle",
        	  configurtion: "Please enter vechil configuration",
        	  engine: "Please enter engine number",
        	  bus_number:"Please enter vehicle number"
	      },
          submitHandler: function(form) {
              form.submit();
          }
      });
      

});
  
  </script>
  
  <script src="//cdn.ckeditor.com/4.6.2/standard/ckeditor.js"></script>
<script>   
	CKEDITOR.replace( 'description1' );
	CKEDITOR.replace( 'description2' );
	CKEDITOR.replace( 'description3' );
	CKEDITOR.replace( 'description4' );
	CKEDITOR.replace( 'description5' );
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