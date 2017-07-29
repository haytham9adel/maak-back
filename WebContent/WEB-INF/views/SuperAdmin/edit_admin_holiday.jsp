<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <!-- <script>
  $(function() {
    $( "#holiday_date" ).datepicker({ dateFormat: 'yy-mm-dd' });
  });
  </script> -->
   <script>
  $(function() {
 //   $( "#holiday_date" ).datepicker({ dateFormat: 'yy-mm-dd' });
    
    $( "#holiday_date" ).datepicker({
    	dateFormat: 'yy-mm-dd' ,
        //changeMonth: true,//this option for allowing user to select month
        //changeYear: true //this option for allowing user to select from year range
        minDate: new Date(),
        onSelect : function(selected_date){
          var selectedDate = new Date(selected_date);
          var msecsInADay = 86400000;
          var endDate = new Date(selectedDate.getTime() + msecsInADay);
          
           $("#holiday_enddate").datepicker( "option", "minDate", endDate );
        }
      });
      
      $('#holiday_enddate').datepicker({dateFormat: 'yy-mm-dd' ,});
    
    
    
    
  });
  </script>
<div class="col-md-9 col-sm-9 col-xs-12 m-side">
             <div class="new-student-form"> 
              <span class="success">${success}</span>
             <span class="error">${error}</span>
                <form class="form-horizontal" id="holiday" method="post">
              
                    <div class="form-group">
                     <label class="col-sm-3 control-label">Holiday Name :</label>
                       <div class="col-sm-9">
                         <input type="text" name="holiday_name" value="${holiday.holiday_name}" id="holiday_name" class="form-control">
                          <input type="hidden" name="school_id" value="${holiday.school_id}" id="school_id" class="form-control">
                        </div>
                   </div>
                    <div class="form-group">
                     <label class="col-sm-3 control-label">Holiday Date:</label>
                       <div class="col-sm-9">
                         <input type="text" name="holiday_date" value="${holiday.holiday_date}" id="holiday_date" class="form-control">
                        </div>
                   </div>
                   
                     <div class="form-group">
                     <label class="col-sm-3 control-label">Holiday EndDate:</label>
                       <div class="col-sm-9">
                         <input type="text" name="holiday_enddate" value="${holiday.holiday_enddate}" id="holiday_enddate" class="form-control">
                        </div>
                   </div>
                   
                    <div class="form-group">
                       <div class="col-sm-9">
                        <input id="save"  class="btn btn-primary btn-submit" value="Save" type="submit">&nbsp;&nbsp;
                        <a href="admin/viewAdminCalendar">
                        <input  class="btn btn-primary btn-submit" id="cancel" value="Cancel" type="button">
                        </a>
                       </div>
                   </div>
                </form>
             </div> 
          </div>
         
        <script src="resources/front/js/jquery.validate.min.js"> </script>
        <script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css">
  <script>
  $(document).ready(function(){
		$("#holiday").validate({
			rules: {
	        	  holiday_date: "required",
	        	  holiday_name: "required",
	        	  holiday_enddate: "required",
	        	  
		      },
	          messages: {
	        	  holiday_date: "Please select date",
	        	  holiday_name: "Please enter holiday name",
	        	  holiday_enddate: "Please select end date",
	         },
          submitHandler: function(form) {
              form.submit();
          }
      });
      

});
  
  
  $('#save').on('click', function(e) {
		e.preventDefault();
		var form = $(this).parents('form');
		swal({
			title : "Are you sure?",
			text : "Do you want to update?",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Yes, Update!",
			closeOnConfirm : false
		}, function(isConfirm) {
			if (isConfirm)
				form.submit();
		});
	})

	$('#cancel').on('click', function(e) {
		e.preventDefault();
		var form = $(this).parents('form');
		swal({
			title : "Are you sure?",
			text : "Do you want to cancel?",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Yes, Cancel!",
			closeOnConfirm : false
		}, function(isConfirm) {
			if (isConfirm)
				window.location = "admin/viewAdminCalendar";
		});
	})
  
  
  
  
  </script>

          
    <jsp:include page="footer.jsp" />      