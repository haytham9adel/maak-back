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
    background-color: #C6504E;
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
    background: #C6504E none repeat scroll 0 0;
    border: 1px solid #C6504E;
   
    padding: 2px 8px;
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
     
 	<c:forEach items="${all_present}" var="present">
    $('.fc-day[data-date="${present.date}"]').addClass('present');
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

<div class="col-md-6 m-side">

				<h2>${student.s_fname} ${student.s_lname} </h2>
		     <div class="report_box">
				&nbsp;
					<div class="holiday_box"><span>&nbsp;</span> Holiday</div>
					<div class="present_box"><span>&nbsp;</span> Present</div>
					<div class="absent_box"><span>&nbsp;</span> Absent</div>
				</div>
             <div class="col-sm-12 col-xs-12 new-student-form view_student_detail"> 
              <span class="success">${success}</span>
             <span class="error">${error}</span>
                <div id='calendar'></div>
             </div> 
             
            
             
             
</div>
<div class="col-md-3">
              
               <c:forEach items="${allParent}" var="parent">
              <div class="info-block"> 
                <h4><b>Parent - ${count + 1}</b> </h4>
                 <div class="info-group">
                    <div class="col-a"> Name : </div>
                    <div class="col-b">   ${parent.first_name} ${parent.last_name} </div> 
                 </div>
             
                 <div class="info-group">
                    <div class="col-a">Email  : </div>
                    <div class="col-b"> ${parent.user_email} </div> 
                 </div>
                 <div class="info-group">
                    <div class="col-a"> Contact Number : </div>
                    <div class="col-b">  ${parent.contact_number} </div> 
                 </div>
              </div> 
              <c:set var="count" value="${count + 1}" scope="page"/>
               </c:forEach> 
             
                 
          
        </div>

  <jsp:include page="footer.jsp" />      