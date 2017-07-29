<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <script>
/*   $(function() {
    $( "#holiday_date" ).datepicker({ dateFormat: 'yy-mm-dd' });
  }); */
  
  </script>
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
      
    $( "#holiday_date1" ).datepicker({
    	dateFormat: 'yy-mm-dd' ,
        //changeMonth: true,//this option for allowing user to select month
        //changeYear: true //this option for allowing user to select from year range
        minDate: new Date(),
        onSelect : function(selected_date){
          var selectedDate = new Date(selected_date);
          var msecsInADay = 86400000;
          var endDate = new Date(selectedDate.getTime() + msecsInADay);
          
           $("#holiday_enddate1").datepicker( "option", "minDate", endDate );
        }
      });
      
      $('#holiday_enddate').datepicker({dateFormat: 'yy-mm-dd' ,});
      $('#holiday_enddate1').datepicker({dateFormat: 'yy-mm-dd' ,});
    
    
    
    
  });
  </script>
<div class="col-md-6 col-sm-6 col-xs-12 m-side">
             <div class="new-student-form"> 
              <span class="success">${success}</span>
             <span class="error">${error}</span>
             <div class="tabbable" id="tabs-752199">
             <ul class="nav nav-tabs tab-design-ts">
					<li class="active">
						<a href="#panel-378288" data-toggle="tab">Add Holiday</a>
					</li>
					<li>
						<a href="#panel-412380" data-toggle="tab">Multiple Holiday</a>
					</li>
				</ul>
				<br>
				<div class="tab-content">
				<div class="tab-pane active" id="panel-378288">
                <form class="form-horizontal" id="holiday" method="post">
              
                    <div class="form-group">
                     <label class="col-sm-3 control-label">Holiday Name :</label>
                       <div class="col-sm-9">
                         <input type="text" name="holiday_name" value="${holiday.holiday_name}" id="holiday_name" class="form-control">
                        </div>
                   </div>
                    <div class="form-group">
                     <label class="col-sm-3 control-label">Holiday Date:</label>
                       <div class="col-sm-9">
                       	<input type="hidden" name="school_id" value="0"/>
                         <input type="text" name="holiday_date" value="${holiday.holiday_date}" id="holiday_date" class="form-control">
                        </div>
                   </div>
                   
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Holiday EndDate:</label>
                       <div class="col-sm-9">
                       	<input type="hidden" name="school_id" value="0"/>
                         <input type="text" name="holiday_enddate" value="${holiday.holiday_enddate}" id="holiday_enddate" class="form-control">
                        </div>
                   </div>
                   
                 <div class="form-group">
                       <div class="col-sm-9">
                        <input  class="btn btn-primary btn-submit" value="Save" type="submit">&nbsp;&nbsp;
                        <a href="admin/viewAdminCalendar">
                        <input  class="btn btn-primary btn-submit" value="Cancel" type="button">
                        </a>
                       </div>
                   </div>
                </form>
                </div>
                <div class="tab-pane" id="panel-412380">
						<form class="form-horizontal" id="holiday_multi" action="admin/addAdminWeekEndHoliday" method="post">
              
                    <div class="form-group">
                     <label class="col-sm-3 control-label">Holiday Name :</label>
                       <div class="col-sm-9">
                         <input type="text" name="holiday_name" value="${holiday.holiday_name}" id="holiday_name" class="form-control">
                        </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Select Day :</label>
                       <div class="col-sm-9">
                         <select id="day" name="day" class="form-control">
                         	<option value="MONDAY">Monday</option>
                         	<option value="TUESDAY">Tuesday</option>
                         	<option value="WEDNESDAY">Wednesday</option>
                         	<option value="THURSDAY">Thursday</option>
                         	<option value="FRIDAY">Friday</option>
                         	<option value="SATURDAY">Saturday</option>
                         	<option value="SUNDAY">Sunday</option>
                         </select>
                        </div>
                   </div>
                    <div class="form-group">
                     <label class="col-sm-3 control-label">Start Date:</label>
                       <div class="col-sm-9">
                       	<input type="hidden" name="school_id" value="<%= session.getAttribute("new_school_id") %>"/>
                         <input type="text" name="holiday_date" value="${holiday.holiday_date}" id="holiday_date1" class="form-control">
                        </div>
                   </div>
                     <div class="form-group">
                     <label class="col-sm-3 control-label">End Date:</label>
                       <div class="col-sm-9">
                       	<input type="hidden" name="school_id" value="0"/>
                         <input type="text" name="holiday_enddate" value="${holiday.holiday_enddate}" id="holiday_enddate1" class="form-control">
                        </div>
                   </div>
                   
                 <div class="form-group">
                       <div class="col-sm-9">
                        <input  class="btn btn-primary btn-submit" value="Save" type="submit">&nbsp;&nbsp;
                        <a href="admin/viewCalendar">
                        <input  class="btn btn-primary btn-submit" value="Cancel" type="button">
                        </a>
                       </div>
                   </div>
                </form>
					</div>
					</div>
                </div>
             </div> 
          </div>
         
        <script src="resources/front/js/jquery.validate.min.js"> </script>
  <script>
  $(document).ready(function(){
		$("#holiday").validate({
			rules: {
	        	  holiday_date: "required",
	        	  holiday_name: "required",
	        	//  holiday_enddate: "required",
	        	  
		      },
	          messages: {
	        	  holiday_date: "Please select date",
	        	  holiday_name: "Please enter holiday name",
	        	 // holiday_enddate: "Please select end date",
	         },
          submitHandler: function(form) {
              form.submit();
          }
      });
		$("#holiday_multi").validate({
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
  
  </script>

          
    <jsp:include page="footer.jsp" />      