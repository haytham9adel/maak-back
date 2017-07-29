<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-9 col-sm-9 col-xs-12 m-side">

<div class="new-student-form"> 

<form class="form-horizontal" id="vechile" method="post">
<div class="form-group">
     <label class="col-sm-12 control-label"><b>Android Parents App:</b> </label>
       <div class="col-sm-12">
        <input type="text" name="p_name" value="${content.p_name}" id="p_name" placeholder="" class="form-control">
       </div>
</div>   
<div class="form-group">
     <label class="col-sm-12 control-label"><b>IOS Parents App</b> </label>
       <div class="col-sm-12">
        <input type="text" name="heading1" value="${content.heading1}" id="heading1" placeholder="" class="form-control">
       </div>
</div>   
<div class="form-group">
     <label class="col-sm-12 control-label"><b>Android Driver App:</b> </label>
       <div class="col-sm-12">
        <input type="text" name="heading2" value="${content.heading2}" id="heading2" placeholder="" class="form-control">
       </div>
</div> 

<div class="form-group">
     <label class="col-sm-12 control-label"><b>Android School Admin App:</b> </label>
       <div class="col-sm-12">
        <input type="text" name="heading3" value="${content.heading3}" id="heading3" placeholder="" class="form-control">
       </div>
</div> 
<div class="form-group">
     <label class="col-sm-12 control-label"><b>IOS School Admin App:</b> </label>
       <div class="col-sm-12">
        <input type="text" name="heading4" value="${content.heading4}" id="heading4" placeholder="" class="form-control">
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