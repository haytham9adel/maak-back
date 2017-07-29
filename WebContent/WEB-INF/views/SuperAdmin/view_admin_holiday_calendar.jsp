<jsp:include page="header.jsp" />

<%@ page import="resources.Assets"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %> 
<%@ page import="org.apache.commons.codec.binary.Base64" %> 
<link rel="stylesheet" href="resources/dashboard/css/pickmeup.css" type="text/css" />
<script type="text/javascript" src="resources/dashboard/js/pickmeup.js"></script>


<%@page pageEncoding="UTF-8"%>
<style>
.pmu-days .pmu-button {background:#808080;}
.holi {
	background: #008A97 !important;
	color: #fff;
}

.present {
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
	float: left;
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

.current_box span {
	background: #F3BE4E none repeat scroll 0 0;
	border: 1px solid #FCF8E3;
	padding: 2px 8px;
}

.info-block.holiday_det>div.info-group {
	padding: 10px;
}

.holiday_det>div.info-group div.col-a {
	text-align: left;
	width: 100%;
	font-size: 17px;
}

.holiday_det>div.info-group div.col-b {
	text-align: left;
	width: 70%;
	float: left;
	font-size: 12px;
}

.edit_holiday>a i {
	font-size: 17px;
	text-align: right;
}

.add-new-holi {
	float: right;
}
.k-in-month,.k-out-of-month{color:#000 !important;}
	.k-today {
	    color: #000000 !important;
	}
.gray{background: #EEEEEE !important;
    color: #000 !important;}


.pmu-not-in-month {
    background: #E4E0E1 !important;
    opacity: 0.8;
    color: rgba(40, 40, 40, 0.55) !important;
    cursor: default !important;
}
</style>

<script>


    $(document).ready(function() {

       
    });

</script>
<!-- This is script start for filter trip by school(e.g. Type) -->

<div class="col-md-6 col-sm-6 col-xs-12 m-side m-side">
<style>
.k-selected{background:#008A97 !important;}
</style>
	<h2>${student.s_fname}${student.s_lname}</h2>
	<div class="report_box">
		&nbsp;
		<div class="holiday_box">
			<span>&nbsp;</span> Holiday
		</div>
		<!-- <div class="present_box"><span>&nbsp;</span> Present</div>
					<div class="absent_box"><span>&nbsp;</span> Absent</div>-->
		<div class="current_box">
			<span>&nbsp;</span> Current Day
		</div>

	</div>
	<div class="add-new-holi">
		<!-- <span>&nbsp;</span><a href="admin/addHoliday" ><button
				class="btn btn-primary btn-submit">Add Holiday</button></a> -->
	</div>
	<div class="col-sm-12 col-xs-12 new-student-form view_student_detail pad0">
		<span class="success">${success}</span> <span class="error">${error}</span>
		 <div class="three-calendars"></div>
		<div class="container-fluid" id="abc">
	<!-- <div class="row">
		<div class="col-md-12">
			<div class="list-group">
				 
				<div class="list-group-item">
					<h4>Holiday Details</h4>
				</div>
				
				 
			</div>
		</div>
	</div> -->
</div>
	</div>



</div>
<div class="col-md-3 col-sm-3 col-xs-12" id="holiday_dis">
	<c:choose>
		<c:when test="${!empty holidays}">

			<div class="info-block">
				<h4>Holiday</h4>
			</div>
			<!-- info-block -->
			<c:forEach items="${holidays}" var="holiday">
				<div class="info-block holiday_det">
					<div class="info-group">
						<div class="col-a">${holiday.holiday_name}</div>
						<div class="col-b">${holiday.holiday_date }</div>


						<div class="edit_holiday">
							<c:set var="h_id" value="${holiday.h_id}"/>
							<%
							String str=pageContext.getAttribute("h_id").toString();
							byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
							%>
<a href="admin/editAdminHoliday?q=<%=new String(encodedBytes)%>"><i class="fa fa-pencil-square-o fa-3x edit"></i></a>
</div>

					</div>
				</div>
			</c:forEach>


		</c:when>
		<c:otherwise>
			<div class="info-block">
				<h4>Holiday</h4>
			</div>
			<!-- info-block -->

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
<script>
$(document).ready(function(){
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
                class_name : 'absent_date',
                 }

			}else
			{
				return {
                disabled   : true,
                 
           		 }
			}
            
       
    }
	

});
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
		
		var current_month =$(".pmu-instance:nth-child(3) .pmu-month").html();
	
		var dateArrMonth= current_month.split(",");
		 
		var c_month=dateArrMonth[0];
		var c_year=dateArrMonth[1];
		
		var current_month=parseInt(c_month)+1;
		var school_id=${school_id};
			$.ajax({
	        url : 'getHolidayByMonthNew.html',
	        type:'post',
	        data:{"day":c_month,"year":c_year,"school_id":school_id},
	        success : function(data) {
	      		
	        	$("#holiday_dis").html(data);
	      }
	    }); 
		
	});
	$(".pmu-instance:nth-child(1)").click(function(){
	
		var current_month=$(".pmu-instance:nth-child(1) .pmu-month").html();
		var dateArrMonth= current_month.split(",");
		 
		var c_month=dateArrMonth[0];
		var c_year=dateArrMonth[1];
		
		var current_month=parseInt(c_month)+1;
		var school_id=${school_id};
		$.ajax({
	        url : 'getHolidayByMonthNew.html',
	        type:'post',
	        data:{"day":c_month,"year":c_year,"school_id":school_id},
	        success : function(data) {
	      		
	        	$("#holiday_dis").html(data);
	      }
	    }); 
	});
	
	 
	/* $(".pmu-instance >.pmu-days>.pmu-button").on('click',function(){
	 
		console.log($(".pmu-days>.pmu-button").html());
	}); */
	
});

function getD(d)
{
//	console.log($(".pmu-days >.pmu-selected").html());
	//console.log($(d).html())
}
</script>
<jsp:include page="footer.jsp" />
