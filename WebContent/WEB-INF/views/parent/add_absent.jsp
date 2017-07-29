<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-6 col-sm-6 col-xs-12 m-side">

             <div class="new-student-form"> 
             <c:if test="${success}">
             </c:if>
                <form class="form-horizontal" id="staff" method="post">
                  <div class="form-group">
                     <label class="col-sm-3 control-label">Select Student:</label>
                       <div class="col-sm-9">
                      	<select class="form-control" name="student_id" id="student_id">
                      		<option value="">Select Student</option>
                      		<c:forEach items="${students}" var="student">
                      		<option value="${student.student_id}">${student.s_fname} ${student.father_name} ${student.grand_name} ${student.family_name}</option>
                      		</c:forEach>
                      	</select>
                     </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Start Date:</label>
                       <div class="col-sm-9">
                     
                         <input type="text" name="start_date" id="start_date" class="form-control">
                            </div>
                   </div>
                    <div class="form-group">
                     <label class="col-sm-3 control-label">End Date:</label>
                       <div class="col-sm-9">
                     
                         <input type="text" name="end_date" id="end_date" class="form-control">
                            </div>
                   </div>
                    <div class="form-group">
                     <label class="col-sm-3 control-label">Reason:</label>
                       <div class="col-sm-9">
                     <textarea class="form-control" name="reason" id="reason"></textarea>
                            </div>
                   </div>
                    
                    <div class="form-group">
                       <div class="col-sm-9">
                        <input  class="btn btn-primary btn-submit" type="submit" value="Submit">
                       </div>
                   </div>
                </form>
             </div> 
             
          </div>
         
        <script src="resources/front/js/jquery.validate.min.js"> </script>
  <script>
  $(document).ready(function(){
		$("#staff").validate({
          rules: {
        	  student_id: "required",
        	  start_date: "required",
        	  reason: "required",
          },
          messages: {
        	  student_id: "Please select student",
        	  start_date: "Please enter start date",
        	  reason: "Please enter reason",
       
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
			type : "success"
		}
	);
	/* swal(
			"The driver successfully added!\n\n",
			"Driver User Name  : ${username} \n\nDriver Password : ${password} ",
			"success");
	
	, function() {
		$('#myModal').modal('show');
	}) */
</script>

</c:if>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script>
  $(document).ready(function(){
	  $( "#start_date" ).datepicker({
	    	dateFormat: 'yy-mm-dd' ,
	    	minDate: new Date(),
	        //changeMonth: true,//this option for allowing user to select month
	        //changeYear: true //this option for allowing user to select from year range
	        onSelect : function(selected_date){
	          var selectedDate = new Date(selected_date);
	          var msecsInADay = 86400000;
	          var endDate = new Date(selectedDate.getTime() + msecsInADay);
	          
	           $(".datepicker1").datepicker( "option", "minDate", endDate );
	        }
	      });
	  
	  $( "#end_date" ).datepicker({
	        dateFormat: 'yy-mm-dd' ,
	        minDate: new Date(),
	        onSelect : function(selected_date){
	          var selectedDate = new Date(selected_date);
	          var msecsInADay = 86400000;
	          var endDate = new Date(selectedDate.getTime() + msecsInADay);
	           $("end_date").datepicker( "option", "minDate", endDate );
	         }
	      });
	  
	  $('#start_date').datepicker({dateFormat: 'yy-mm-dd' ,});
      $('#end_date').datepicker({dateFormat: 'yy-mm-dd' ,});
});
</script>
<script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css">
  <c:if test="${!empty success}">
<script>
swal(
		{
			title : "Request for absent sent to admin!",
			text : "",
			type : "success"
		}, function() {
		//	$('#myModal').modal('show');
		});
	
</script>

</c:if>
    <jsp:include page="footer.jsp" />      