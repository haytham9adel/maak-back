<jsp:include page="header.jsp" />
<%@page import="java.io.*, java.util.*" %> 
<%@page import="java.text.SimpleDateFormat" %>  
<%@page import="com.trackingbus.bean.*" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %> 
<%@ page import="org.apache.commons.codec.binary.Base64" %> 
<style>
<!--
.current_box span {
    background: #FCF8E3 none repeat scroll 0 0;
    border: 1px solid rgb(252, 248, 227);
    padding: 2px 8px;
}
-->
</style>
<link href="https://cdn.datatables.net/1.10.13/css/dataTables.material.min.css" rel="stylesheet" type="text/css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/material-design-lite/1.1.0/material.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js">
</script>
<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.13/js/dataTables.material.min.js">
</script>
 <script>
 $(document).ready(function() {
	    $('#example').DataTable({
	    	"pagingType": "simple_numbers",
	    	"oLanguage": {
	    		"sUrl": "resources/dashboard/js/english.json"
	    	},
	    	 aoColumnDefs : [{
	    	      orderable : false, aTargets : [5] //disable sorting for the 1st column
	    	 		  
	    	 }],
	    });
	} );
 </script>

<div class="col-md-9 col-sm-9 col-xs-12 pad0">
	<div class="col-sm-12 pad0">
	<% if((Integer)session.getAttribute("permission")==1) {%>
	<a href="admin/addVehicle/" class="btn btn-primary btn-submit" style="float:right; margin:12px ">Add New</a>
	<% } %>
	<a href="javascript:void(0);" id="export" class="btn btn-primary btn-submit" style="float:right; margin:12px ">Export</a>
	    <span class="success">${success}</span>
             <span class="error">${error}</span>
             <div class="current_box clearfix"><span>&nbsp;</span> Will Expire date soon</div>

	
	<table id="example" class="mdl-data-table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Vechile Name</th>
               	<th>School Name</th>
               	<th>Bus Number</th>
                <th>Model</th>
                <th>Action</th>
               
            </tr>
        </thead>
 
         <tbody>
         <c:set var="count" value="1" scope="page" />



                <c:forEach items="${vechiles}" var="vechile">
                <c:set var="insurance_end_date" value="${vechile.insurance_end_date}"/>
    	
    	<%
    	
    	/* VechileBean vbean=new VechileBean();
    	//vbean.setInsurance_end_date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	
    	
    	
    	int number=10;
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 10);
		dt = c.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date cur_date = new Date();
		String current_date=dateFormat.format(cur_date);
		String next_date=dateFormat.format(dt);
		
		Date date1 = sdf.parse(dateFormat.format(cur_date));
    	Date date2 = sdf.parse(dateFormat.format(dt));
    	String abc=pageContext.getAttribute("insurance_end_date").toString();
   		Date date3=sdf.parse(abc); */
   		
    	%>
    
    	  	
    	 
    	
    	<tr>
     
    	
    	<td><c:out value="${count}"/> &nbsp;
    	
    	
    	
    	</td>
    	<td>${vechile.vehile_name}</td>
    	<td>${vechile.school_name}</td>
    	<td>${vechile.bus_number}</td>
    	<td>${vechile.model}</td>
    	<%-- <td><c:out value="${vechile.manufacture}"/></td>
    	
    	<td><c:out value="${vechile.year}"/></td>
    	<td><c:out value="${vechile.color}"/></td>
    	<td><c:out value="${vechile.configurtion}"/></td>
    	<td><c:out value="${vechile.engine}"/></td> --%>
    

    	<td>
		<c:set var="vechile_id" value="${vechile.vechile_id}"/>
		<%
		String str=pageContext.getAttribute("vechile_id").toString();
		byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
		%>
		 <%-- <a title="View" href="admin/viewVechile?q=<%=new String(encodedBytes)%>"><i class="fa fa-eye fa-2x edit"></i></a>
    	<a title="Edit" href="admin/editVechile?q=<%=new String(encodedBytes)%>"><i class="fa fa-pencil-square-o fa-2x edit"></i></a>
    <% if((Integer)session.getAttribute("permission")==1) {%>
    	<a title="Delete"
onClick="confirmBox(this.id)" id="${vechile.vechile_id}" href="javascript:void(0);"><i class="fa fa-times fa-2x delete"></i></a>
<% } %>  --%>
		<div class="btn-group">
	          <a title="View" class="btn btn-danger"  href="admin/viewVechile?q=<%=new String(encodedBytes)%>"><i class="fa fa-eye edit"></i>	</a>
		    	<% if((Integer)session.getAttribute("permission")==1) {%>
		    	<a title="Edit" class="btn btn-danger"  href="admin/editVechile?q=<%=new String(encodedBytes)%>"><i class="fa fa-pencil-square-o edit"></i></a>
		    	<a title="Delete"  class="btn btn-danger" 
		onClick="confirmBox(this.id)" id="${vechile.vechile_id}" href="javascript:void(0);"><i class="fa fa-trash-o delete"></i></a>
		<% } %>
			          
	        </div>
    	


	
    	</td>
    	</tr>     
    <c:set var="count" value="${count + 1}" scope="page"/>
     	 </c:forEach>
      
          </tbody>  
        
       </table> 	
	
	</div>
            
</div>
<script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css">
<script>
function confirmBox(id)
{
	swal({
		title : "Are you sure?",
		text : "Do you want to Delete?",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "Yes, Delete!",
		closeOnConfirm : true
	}, function(isConfirm) {
		if (isConfirm)
			window.location="admin/deleteVechile?vechile_id="+id;
	});
}

</script> 
 <script src="resources/dashboard/js/jquery.table2excel.js"></script>
<script>
$(function() {
	$("#export").on('click',function(){
		  $("#example_export").table2excel({
		    name: "Worksheet Name",
		    filename: "All Vehicles" //do not include extension
		  }); 
});
});
</script>
<table id="example_export" class="mdl-data-table" cellspacing="0" width="100%" style="display:none;">
        <thead>
            <tr>
                <th>ID</th>
                <th>Vechile Name</th>
               	<th>Bus Number</th>
                <th>Model</th>
                <th>Assign Driver</th>
            </tr>
        </thead>
 
         <tbody>
         <c:set var="count" value="1" scope="page" />



                <c:forEach items="${vechiles}" var="vechile">
                <c:set var="insurance_end_date" value="${vechile.insurance_end_date}"/>
    	
    		<tr>
     	<td><c:out value="${count}"/></td>
    	<td>${vechile.vehile_name}</td>
    	<td>${vechile.bus_number}</td>
    	<td>${vechile.model}</td>
    	<td>${vechile.color}</td>
    	</tr>     
    <c:set var="count" value="${count + 1}" scope="page"/>
     	 </c:forEach>
      
          </tbody>  
        
       </table>        
<jsp:include page="footer.jsp" />      