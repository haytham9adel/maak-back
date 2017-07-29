<jsp:include page="header.jsp" />
<%@ page import="resources.Assets" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href='resources/dashboard/calander/fullcalendar.css' rel='stylesheet' />
<link href='resources/dashboard/calander/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src='resources/dashboard/calander/lib/moment.min.js'></script>
<script src='resources/dashboard/calander/lib/jquery.min.js'></script>
<script src='resources/dashboard/calander/fullcalendar.min.js'></script>
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

<style>
.holi {
    background: #008A97 !important;
    color: #fff;
}
.present{
 	background: #00B07D !important;
    color: #fff;
}
.fc-widget-content {
    background-color: #897D7D;
}

.fc-axis {
    background-color: white;
}

.fc-bgevent {
    opacity: 1;
}
 .fc-sun {
    background-color: #EEEEEE !important;
}
.report_box {
    float:left;
}
.report_box div {
    display: inline-block;
}
.holiday_box span {
    background: #008A97 none repeat scroll 0 0;
    border: 1px solid #008A97;
    
    padding: 2px 8px;
}
.present_box span {
    background: #00B07D none repeat scroll 0 0;
    border: 1px solid #00B07D;
    
    padding: 2px 8px;
}
.absent_box span {
    background: #897D7D none repeat scroll 0 0;
    border: 1px solid #897D7D;
   
    padding: 2px 8px;
}

.current_box span{
	 background: #FCF8E3 none repeat scroll 0 0;
    border: 1px solid #FCF8E3;
   
    padding: 2px 8px;
	
}
.info-block.holiday_det > div.info-group {
    padding: 10px;
}
.holiday_det > div.info-group div.col-a {
    text-align: left;
    width: 100%;
    font-size: 17px;
}
.holiday_det > div.info-group div.col-b {
    text-align: left;
    width: 70%;
    float: left;
    font-size: 12px;
}
.edit_holiday > a i {
    font-size: 17px;
    text-align: right;
}
.add-new-holi {
    float: right;
}
</style>

<script>


    $(document).ready(function() {
      /*   $('.fc-day').mouseover(function () {
    var strDate_yyyy_mm_dd = $(this).data('date');
    if (strDate_yyyy_mm_dd != m_strDate_yyyy_mm_dd)
        $('#calTooltip').hide();
  }); */
        $('#calendar').fullCalendar({
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
            },
            defaultDate: "${current_date}",
            editable: true,
            dayClick: function(date, jsEvent, view) {
                
            
             },

eventMouseover: function(calEvent, jsEvent) {
    
   /*  var tooltip = '<div class="tooltipevent" style="width:100px;height:100px;background:#ccc;position:absolute;z-index:10001;">' + calEvent.title + '</div>';
    $("body").append(tooltip);
    $(this).mouseover(function(e) {
        $(this).css('z-index', 10000);
        $('.tooltipevent').fadeIn('500');
        $('.tooltipevent').fadeTo('10', 1.9);
    }).mousemove(function(e) {
        $('.tooltipevent').css('top', e.pageY + 10);
        $('.tooltipevent').css('left', e.pageX + 20);
    }); */
},

eventMouseout: function(calEvent, jsEvent) {
    $(this).css('z-index', 8);
    $('.tooltipevent').remove();
},
eventClick: function(calEvent, jsEvent, view) {
      var a=eval(calEvent);
    }
,
dayRender: function (date, cell) {
	  var today = new Date();
	<c:forEach items="${all_holiday}" var="holiday">
     $('.fc-day[data-date="${holiday.holiday_date}"]').addClass('holi');
     </c:forEach> 
    
    if(date > today) {
        cell.css("background-color", "#897d7d");
    }
    
 		
     
},
eventRender: function (event, element) {
        element.attr('href', 'javascript:void(0);');
        element.click(function() {
        
                        
          
        });
},
    
      
            eventLimit: true, // allow "more" link when too many events
            events: [
                     
	
					

					/* <c:forEach items="${all_holiday}" var="holiday">
                    
					{
					    start: '${holiday.holiday_date}',
					    editable: false,
					    backgroundColor: '#4679BD',
					  
					},
					
					</c:forEach>  */
              
              ],
            eventColor: '#378006'  
        });
        
    });

</script>
<!-- This is script start for filter trip by school(e.g. Type) -->

<div class="col-md-6 col-sm-6 col-xs-12 m-side">

				<h2>${student.s_fname} ${student.s_lname} </h2>
				<div class="report_box">
				&nbsp;
					<div class="holiday_box"><span>&nbsp;</span> Holiday</div>
					<!-- <div class="present_box"><span>&nbsp;</span> Present</div>
					<div class="absent_box"><span>&nbsp;</span> Absent</div>-->
					<div class="current_box"><span>&nbsp;</span> Current Day</div> 
					
				</div>
				<div class="add-new-holi"><span>&nbsp;</span><a href="#myModal" data-toggle="modal"><button class="btn btn-primary btn-submit">Add Holiday</button></a></div>
             <div class="col-sm-12 col-xs-12 new-student-form view_student_detail"> 
              <span class="success">${success}</span>
             <span class="error">${error}</span>
                <div id='calendar'></div>
             </div> 
             
            
            
</div>
<div class="col-md-3 col-sm-3 col-xs-12" id="holiday_dis">
              <c:choose>
	 <c:when test="${!empty holidays}">
	        
	   		 <div class="info-block">
			              		<h4>Holiday</h4>
			 </div><!-- info-block -->
				<c:forEach items="${holidays}" var="holiday"> 
		            <div class="info-block holiday_det"> 
		               <div class="info-group">
		                  <div class="col-a">${holiday.holiday_name}</div>
		                  <div class="col-b">${holiday.holiday_date }</div> 
		                  
		                  <c:if test="${holiday.school_id!=0}">
		                   <div class="edit_holiday">
		               		<a href="admin/editHoliday?h_id=${holiday.h_id}"><i class="fa fa-pencil-square-o fa-3x edit"></i></a>
		                   </div>
		                 </c:if>
		               </div>
		           	</div> 
			   </c:forEach>
	     	
	
	  </c:when>
	 <c:otherwise>
	   	 <div class="info-block">
			              		<h4>Holiday</h4>
			 </div><!-- info-block -->
				
		            <div class="info-block holiday_det"> 
		               <div class="info-group">
		                  <div class="col-a">No Holiday Available</div>
		               </div>
		           	</div> 
			 
	   </c:otherwise>
    </c:choose>
   

</div>
<script>
$(document).ready(function(){
	$(".fc-next-button").on('click',function(){
		
		var moment = $('#calendar').fullCalendar('getDate');
		var c_month=moment.month();
		var c_year=moment.year();
		var current_month=parseInt(c_month)+1;
		var school_id=${school_id};
		$.ajax({
	        url : 'getHolidayByMonth.html',
	        type:'post',
	        data:{"month":current_month,"year":c_year,"school_id":school_id},
	        success : function(data) {
	      		
	        	$("#holiday_dis").html(data);
	      }
	    }); 
		
		
		});
	$(".fc-prev-button").on('click',function(){
		var moment = $('#calendar').fullCalendar('getDate');
		var c_month=moment.month();
		var c_year=moment.year();
		var current_month=parseInt(c_month)+1;
		var school_id=${school_id};
		$.ajax({
	        url : 'getHolidayByMonth.html',
	        type:'post',
	        data:{"month":current_month,"year":c_year,"school_id":school_id},
	        success : function(data) {
	      		
	        	$("#holiday_dis").html(data);
	      }
	    }); 
	});
	$(".fc-today-button").on('click',function(){
		var moment = $('#calendar').fullCalendar('getDate');
		var c_month=moment.month();
		var c_year=moment.year();
		var current_month=parseInt(c_month)+1;
		var school_id=${school_id};
		$.ajax({
	        url : 'getHolidayByMonth.html',
	        type:'post',
	        data:{"month":current_month,"year":c_year,"school_id":school_id},
	        success : function(data) {
	      		
	        	$("#holiday_dis").html(data);
	      }
	    }); 
	});
	
});
</script>
<div class="modal fade" id="myModal" style="display: none;" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                    <h3>Add Holiday<small><p id="msg"></p></small></h3>
                    <span id="order-msg"></span>
                </div>
                
                <div class="modal-body">
                  <form id="add-note" method="post" novalidate="novalidate" action="admin/addHoliday">
         			<table width="100%" cellpadding="2" border="0" class="table">
         			
            <tbody>
            <tr><th>Holiday Name</th><td><input type="text" id="holiday_name" name="holiday_name" class="form-control"></td></tr>
       		<tr>  <th>Date</th><td valign="top"><input autocomplete="off" type="text" id="holiday_date" name="holiday_date" class="form-control"></td>
          </tr>
          <tr>
            <td
           
            ><button class="btn btn-primary" type="submit" id="add_not_btn">Add</button></td>
          </tr>
        
          </tbody></table>
        
         	 <input type="hidden" name="school_id" value="<%= session.getAttribute("new_school_id") %>">
          <div>&nbsp;</div>
               </form></div>
              </div>
          </div>
</div>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <script>
  $(function() {
    $( "#holiday_date" ).datepicker({ dateFormat: 'yy-mm-dd' });
  });
  </script>
  
    <script src="resources/front/js/jquery.validate.min.js"> </script>
  <script>
  $(document).ready(function(){
		$("#add-note").validate({
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