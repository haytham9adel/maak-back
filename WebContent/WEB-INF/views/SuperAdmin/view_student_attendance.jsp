<jsp:include page="header.jsp" />
<%@ page import="resources.Assets" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="resources/dashboard/css/pickmeup.css" type="text/css" />
<script type="text/javascript" src="resources/dashboard/js/pickmeup.js"></script>
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

.current_box span{
	background: #F3BE4E none repeat scroll 0 0;
    border: 1px solid #FCF8E3;
   
    padding: 2px 8px;
	
}
.gray{background: #EEEEEE !important;
    color: #000 !important;}
.pmu-days .pmu-button {background:#808080;}
.date-in-past{background:#C6504E !important;}
/* .pmu-sunday{background: #EEEEEE!important;cursor: default!important;color: #000!important;}
.pmu-saturday{background: #EEEEEE!important;cursor: default!important;color: #000!important;} */
.pmu-today {background:#F3BE4E !important;}
.pmu-not-in-month {
    background: #E4E0E1 !important;
    opacity: 0.8;
    color: rgba(40, 40, 40, 0.55) !important;
    cursor: default !important;
}
</style>

<script>
    $(document).ready(function() {
    	$(".k-days span").click(function(){
    		  checkValue();
    	   });
    	$(".k-btn-next-month").click(function(){
    		checkValue();
    	});
    	$(".k-btn-previous-year").click(function(){
  		  checkValue();
  	   });
	  	$(".k-btn-previous-month").click(function(){
	  		checkValue();
	  	});
	  	$(".k-btn-next-year").click(function(){
	  		checkValue();
	  	});
    });
</script>
<!-- This is script start for filter trip by school(e.g. Type) -->




<div class="col-md-6 col-sm-6 col-xs-12 m-side">

				<h2 class="dash_title">${student.s_fname} ${student.s_lname} </h2>
				<div class="report_box">
				&nbsp;
					<div class="holiday_box"><span>&nbsp;</span> Holiday</div>
					<div class="present_box"><span>&nbsp;</span> Present</div>
					<div class="absent_box"><span>&nbsp;</span> Absent</div>
					<div class="current_box"><span>&nbsp;</span> Current Day</div>
				</div>
             <div class="col-sm-12 col-xs-12 new-student-form view_student_detail"> 
              <span class="success">${success}</span>
             <span class="error">${error}</span>
                <div class="row">
		<div class="col-md-12 pad0">
			<div class="tabbable" id="tabs-637212">
				<ul class="nav nav-tabs tab-design-ts">
					<li class="active">
						<a href="#panel-615947" data-toggle="tab">Calendar View</a>
					</li>
					<li>
						<a href="#panel-753290" data-toggle="tab">List View</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel-615947">
					<div class="three-calendars"></div>
					<div class="clearfix"></div>
					<div class="select-date">
						<div class="col-sm-12">
							<div class="col-md-6">
								<label>Start Date</label>
								<div class="form-group">
									<input type="text" class="form-control datepicker" name="start_date" id="start_date">
								</div>
							</div>
							<div class="col-md-6">
								<label>End Date</label>
								<div class="form-group">
									<input type="text" class="form-control datepicker1" name="end_date" id="end_date">
								</div>
							</div>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="list-group bord-no">
                        <a class="list-group-item" href="javascript:void(0);"><span class="hd"></span> Holiday <span class="p_right" id="hd_1">${holiday_days}</span></a>
                        <a class="list-group-item" href="javascript:void(0);"><span class="pt"></span> Present <span class="p_right" id="hd_2">${present_day}</span></a>
                        <a class="list-group-item" href="javascript:void(0);"><span class="ab"></span> Absent <span class="p_right" id="hd_3">${absent_day}</span></a>
                    </div>
					
						<!-- <div id="tb_calendar"></div>-->
					
					
					
						 <!-- <div id='calendar'></div> -->
					</div>
					<div class="tab-pane" id="panel-753290">
					
					<button id="export" class="btn btn-primary btn-submit" style="margin:9px 0 9px 2px;float:right;">EXPORT</button>
					<script>
					$(document).ready(function(){
						
					var start = new Date("${all_present[0].date}"),
				    end = new Date(),
				    year = start.getFullYear(),
				    month = start.getMonth()
				    day = start.getDate(),
				    dates = [start];
					function formatAMPM(date_am) {
						//time="18:00:00";
						 if(date_am!="")
							 {
							 return date_am;
							 var date = new Date(date_am);
								var time_c=date_am.split(" ");
								time_c=time_c[1].split(":");
								var last_c=time_c[2].split(".");
								//var time=date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
								var time=time_c[0]+":"+time_c[1]+":"+last_c[0];
								time = time.toString ().match (/^([01]\d|2[0-3])(:)([0-5]\d)(:[0-5]\d)?$/) || [time];

							    if (time.length > 1) { // If time format correct
							      time = time.slice (1);  // Remove full string match value
							      time[5] = +time[0] < 12 ? 'AM' : 'PM'; // Set AM/PM
							      time[0] = +time[0] % 12 || 12; // Adjust hours
							    }
							    return time.join (''); // return adjusted time or original string
							 }else{
								 return date_am;
							 }
						
						} 
					while(dates[dates.length-1] < end) {
					 	dates.push(new Date(year, month, ++day));
						 }
					 
					function convert(str) {
					    var date = new Date(str),
					        mnth = ("0" + (date.getMonth()+1)).slice(-2),
					        day  = ("0" + date.getDate()).slice(-2);
					    return [ day,mnth,date.getFullYear()].join("-");
					}
					function convert1(str) {
					    var date = new Date(str),
					        mnth = ("0" + (date.getMonth()+1)).slice(-2),
					        day  = ("0" + date.getDate()).slice(-2);
					    return [date.getFullYear(),mnth, day].join("-");
					}
					var htmlData='';
					var htmlData1='';
					for(var j=dates.length-2;j>-1;j--)
						{
						var aphStatus='danger';
						var aphStatus1="#EBCCCC";
						var status="Absent";
						var m_check_in="N/A";
						var m_check_out="N/A";
						var e_check_in="N/A";
						var e_check_out="N/A";
						<c:forEach items="${all_holiday}" var="holiday">
						if(convert1(dates[j])=="${holiday.holiday_date}")
							{
								status="Holiday";
								aphStatus="#008A97";
								aphStatus1="#008A97";
							}
					     
					     </c:forEach> 
					     
					 	<c:forEach items="${all_present}" var="present">
					 	 
						if(convert1(dates[j])=="${present.date}")
						{
							
							
							status="Present";
							aphStatus="success";
							aphStatus1="#D0E9C6";
							m_check_in="${present.login_time}";
							m_check_out="${present.logout_time}";
							e_check_in="${present.login_evening}";
							e_check_out="${present.logout_evening}";
						}
						   </c:forEach>
						
						if(convert1(dates[j])==convert1(new Date()))
							{
								status="N/A";
								aphStatus="warning";
								aphStatus1="#FAF2CC";
							}
				     		htmlData +='<tr class="'+aphStatus+'" style="background:'+aphStatus+';">';
							htmlData +='<td>'+parseInt(j+1)+'</td>';
							htmlData +='<td>'+convert(dates[j])+'</td>';
							htmlData +='<td>'+formatAMPM(m_check_in)+'</td>';
							htmlData +='<td>'+formatAMPM(m_check_out)+'</td>';
							htmlData +='<td>'+formatAMPM(e_check_in)+'</td>';
							htmlData +='<td>'+formatAMPM(e_check_out)+'</td>';
							
							htmlData +='</tr>';
							//console.log(convert(dates[j]));
							
							htmlData1 +='<tr class="'+aphStatus+'"  style="background:'+aphStatus1+';">';
							htmlData1 +='<td style="background:'+aphStatus1+';">'+parseInt(dates.length-j-1)+'</td>';
							htmlData1 +='<td style="background:'+aphStatus1+';">'+convert(dates[j])+'</td>';
							htmlData1 +='<td>'+formatAMPM(m_check_in)+'</td>';
							htmlData1 +='<td>'+formatAMPM(m_check_out)+'</td>';
							htmlData1 +='<td>'+formatAMPM(e_check_in)+'</td>';
							htmlData1 +='<td>'+formatAMPM(e_check_out)+'</td>';	
							htmlData1 +='</tr>';
						}
					$("#list-view-attendance").html(htmlData);	
					$("#list-view-attendance1").html(htmlData1);	
					});
					</script>
						
						<table class="table table-hover" id="example">
				<thead>
					<tr>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th colspan="2" text-align="center" style=" text-align: center;">Morning</th>
						<th colspan="2" text-align="center" style=" text-align: center;">Evening</th>
						
					</tr>
					<tr>
						<th>
							#
						</th>
						<th>
							Date
						</th>
						<th>Check-in</th>
						<th>Check-out</th>
						
						<th>Check-in</th>
						<th>Check-out</th>
						
						
					</tr>
				</thead>
				<tbody id="list-view-attendance">
					
					
				</tbody>
			</table>
			<table class="table table-hover" style="display:none;" id="example1">
				<thead>
					<tr>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th colspan="2" style="text-align:center;" style=" text-align: center;">Morning</th>
						<th colspan="2" text-align="center" style=" text-align: center;">Evening</th>
						
					</tr>
					<tr>
						<th>
							#
						</th>
						<th>
							Date
						</th>
						<th>Check-in</th>
						<th>Check-out</th>
						
						<th>Check-in</th>
						<th>Check-out</th>
						
						
					</tr>
				</thead>
				<tbody id="list-view-attendance1">
					
					
				</tbody>
			</table>
					</div>
				</div>
			</div>
		</div>
	</div>
             </div> 
             
            
            
</div>

<div class="col-md-3 col-sm-3 col-xs-12">
              
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
<link href="//cdn.datatables.net/1.10.7/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
<script src="resources/dashboard/js/jquery.dataTables.min.js"></script>
 <script>
 $(document).ready(function() {
	    $('#example').DataTable();
	} );
 </script>
 <script src="resources/dashboard/js/jquery.table2excel.js"></script>
<script>
$(function() {
	$("#export").on('click',function(){
		  $("#example1").table2excel({
		    name: "Worksheet Name",
		    filename: "Attendance" //do not include extension
		  }); 
});
});

function checkValue()
{
	$( ".k-days span" ).each(function( ) {
		 var new_date= $(this).attr("data-date");
		  d=getDateDay(new_date);
	 	if(parseInt(d)==0 || parseInt(d)==6)
	 		{
	 		$(this).addClass('gray');
	 		}
	});
	$(".k-days span").css('background','#C6504E');
	<c:forEach items="${all_holiday}" var="holiday">
    $('.k-in-month[data-date="${holiday.holiday_date}"]').addClass('holi');
    </c:forEach> 
    
	<c:forEach items="${all_present}" var="present">
   $('.k-in-month[data-date="${present.date}"]').addClass('present');
   </c:forEach>
   
   <c:forEach items="${all_holiday}" var="holiday">
   $('.k-out-of-month[data-date="${holiday.holiday_date}"]').addClass('holi');
   </c:forEach> 
   
	<c:forEach items="${all_present}" var="present">
  $('.k-out-of-month[data-date="${present.date}"]').addClass('present');
  </c:forEach>
  
  

   
   
}
$(document).ready(function(){
	
	/* $(".k-btn-next-month").click(function(){
		alert($(this).attr("class"));
	}); */
		var d;
	
	  $(".k-days span").css('background','#C6504E');
	<c:forEach items="${all_holiday}" var="holiday">
     
     	d=getDateDay("${holiday.holiday_date}");
     	if(parseInt(d)==0 || parseInt(d)==6)
     		{
     		$('.k-in-month[data-date="${holiday.holiday_date}"]').addClass('gray');
     		}else{
     			$('.k-in-month[data-date="${holiday.holiday_date}"]').addClass('holi');
     		}
     	
    </c:forEach> 
    
	<c:forEach items="${all_present}" var="present">
   $('.k-in-month[data-date="${present.date}"]').addClass('present');
   </c:forEach> 
   
	<c:forEach items="${all_holiday}" var="holiday">
   $('.k-out-of-month[data-date="${holiday.holiday_date}"]').addClass('holi');
   </c:forEach> 
   
	<c:forEach items="${all_present}" var="present">
  $('.k-out-of-month[data-date="${present.date}"]').addClass('present');
  </c:forEach>
   
   
  $( ".k-days span" ).each(function( ) {
		 var new_date= $(this).attr("data-date");
		   
			d=getDateDay(new_date);
	 	if(parseInt(d)==0 || parseInt(d)==6)
	 		{
	 		$(this).addClass('gray');
	 		}
	});

   
 	/* $(".k-days span").click(function(){
 		var htmld='';
 		var arr=[];
 		var a=$(this).attr('data-date');
 			if($(this).hasClass('k-selected'))
 				{
 				$(this).addClass('k-selected');
 				}else
 					{
 					$(this).removeClass('k-selected');	
 					}
 		  <c:forEach items="${all_holiday}" var="holiday">
 			if(a=="${holiday.holiday_date}")
 				{
 				htmld+='<div class="list-group-item">';
 				htmld+='<h4>Holiday Details</h4>';
 				htmld+='</div>';
 				htmld+='<div class="list-group-item">';
 				htmld+='<p class="list-group-item-heading">';
 				htmld+='Name: ${holiday.holiday_name}';
 				htmld+='</p>';
 				htmld+='<p class="list-group-item-heading">';
 				htmld+='Start Date: ${holiday.holiday_date}';
 				htmld+='</p>';
 				htmld+='<p class="list-group-item-heading">';
 				htmld+='End Date: ${holiday.holiday_enddate}';
 				htmld+='</p>';
 				htmld+='</div>';
 				}
 		  	
 		  
 		  </c:forEach>
 		if(htmld=='')
 			{
 			htmld+='<div class="list-group-item">';
				htmld+='Holiday Description';
				htmld+='</div>';
				htmld+='<div class="list-group-item">';
				htmld+='<h4 class="list-group-item-heading">';
				htmld+='No Holiday ';
				htmld+='</h4>';
				htmld+='</div>';
				$(".list-group").html(htmld);
 			}else{
 				$(".list-group").html(htmld);
 			}
 		
 	});  */
});
function getDateDay(d_v) {
    var d = new Date(d_v);
    var n = d.getDay()
    return n;
}


var now = new Date;
//var dateArr=["2016-08-10","2016-10-10","2016-11-10","2016-11-05","2016-11-21","2016-11-22"];
var dateArr=[<c:forEach items="${all_present}" var="present">"${present.date}",</c:forEach>];
var holidayArr=[ <c:forEach items="${all_holiday}" var="holiday">
"${holiday.holiday_date}",
</c:forEach>];
pickmeup('.three-calendars', {
	flat      : true,
	date      : dateArr,
	mode      : 'multiple',
	calendars : 3,
	format  : 'Y-m-d',
	current:now,
	first_day:0,
	render: function(date) {
        
      var getdate1=convert(date) ;
           
			if(dateArr.indexOf(getdate1) != -1)
			{  
				return {
                disabled   : false,
                class_name : 'present_date'
           		 }
			 	
			}else if (holidayArr.indexOf(getdate1) != -1) {
			
				return {
                disabled   : false,
                class_name : 'absent_date'
           		 }

			}else
			{
				if (date < now) {
						if(convert(date)>dateArr[0])
						{
			            	return {disabled : true, class_name : 'date-in-past'};
			        	}
				}
			}
       
    }
	

});
function convert(str) {
    var date = new Date(str),
        mnth = ("0" + (date.getMonth()+1)).slice(-2),
        day  = ("0" + date.getDate()).slice(-2);
    return [ date.getFullYear(), mnth, day ].join("-");
}
</script>
<script>
$(document).ready(function(){
	$(".pmu-instance:nth-child(3)").click(function(){
		var current_month=$(".pmu-instance:nth-child(3) .pmu-month").html();
		$.ajax({
		    url : 'getTotalPresentAbsent.html',
		    type:'post',
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    data: JSON.stringify({"current_month":current_month,"student_id":"${student_id}",school_id:"<%=(Integer)session.getAttribute("new_school_id")%>"}),
		    success : function(response) {
		    	$("#hd_1").html(response['holiday_days']);
		    	$("#hd_2").html(response['present_day']);
		    	$("#hd_3").html(response['absent_day']);
		    }
		});
	});
	$(".pmu-instance:nth-child(1)").click(function(){
		var current_month=$(".pmu-instance:nth-child(1) .pmu-month").html();
		$.ajax({
		    url : 'getTotalPresentAbsent.html',
		    type:'post',
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    data: JSON.stringify({"current_month":current_month,"student_id":"${student_id}",school_id:"<%=(Integer)session.getAttribute("new_school_id")%>"}),
		    success : function(response) {
		    	$("#hd_1").html(response['holiday_days']);
		    	$("#hd_2").html(response['present_day']);
		    	$("#hd_3").html(response['absent_day']);
		    }
		});
	});
});
</script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script>
  $(document).ready(function(){
	  $( ".datepicker" ).datepicker({
	    	dateFormat: 'yy-mm-dd' ,
	        //changeMonth: true,//this option for allowing user to select month
	        //changeYear: true //this option for allowing user to select from year range
	        onSelect : function(selected_date){
	          var selectedDate = new Date(selected_date);
	          var msecsInADay = 86400000;
	          var endDate = new Date(selectedDate.getTime() + msecsInADay);
	          
	           $(".datepicker1").datepicker( "option", "minDate", endDate );
	        }
	      });
	  
	  $( ".datepicker1" ).datepicker({
	        dateFormat: 'yy-mm-dd' ,
	        minDate: new Date(),
	        onSelect : function(selected_date){
	          var selectedDate = new Date(selected_date);
	          var msecsInADay = 86400000;
	          var endDate = new Date(selectedDate.getTime() + msecsInADay);
	          
	           $("datepicker1").datepicker( "option", "minDate", endDate );
	           
	           $.ajax({
		   		    url : 'getTotalPresentAbsentByDateRange.html',
		   		    type:'post',
		   		    contentType: "application/json; charset=utf-8",
		   		    dataType: "json",
		   		    data: JSON.stringify({"start_date":$("#start_date").val(),"end_date":selected_date,"student_id":"${student_id}",school_id:"<%=(Integer)session.getAttribute("new_school_id")%>"}),
		   		    success : function(response) {
		   		    	$("#hd_1").html(response['holiday_days']);
		   		    	$("#hd_2").html(response['present_day']);
		   		    	$("#hd_3").html(response['absent_day']);
		   		    }
	   			});
	           
	           
	        }
	      });
	  
	  $('.datepicker').datepicker({dateFormat: 'yy-mm-dd' ,});
      $('.datepicker1').datepicker({dateFormat: 'yy-mm-dd' ,});
});
</script>
  <jsp:include page="footer.jsp" />      