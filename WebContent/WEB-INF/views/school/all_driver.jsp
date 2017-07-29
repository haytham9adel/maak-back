<jsp:include page="header.jsp" />
<%@ page import="resources.Assets" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page import="java.util.*" %> 
<%@ page import="org.apache.commons.codec.binary.Base64" %> 
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
	    	      orderable : false, aTargets : [1,5] //disable sorting for the 1st column
	    	 		  
	    	 }],
	    	 columnDefs: [
	    	              {
	    	                  targets: [ 0, 1, 2 ],
	    	                  className: 'mdl-data-table__cell--non-numeric'
	    	              }
	    	          ],
	    });
	} );
 </script>
<style>
.parent-action-btn{ width: 138px;}
</style>
<div class="col-md-9 pad0">
	<div class="col-sm-12 pad0">
	<% if((Integer)session.getAttribute("permission")==1) {%>
	<a href="school/addDriver/" class="btn btn-primary btn-submit" style="float:right; margin:12px ">Add New</a>
	 <% } %>
	    <span class="success">${success}</span>
             <span class="error">${error}</span>
             <a href="javascript:void(0);" id="export" class="btn btn-primary btn-submit" style="float:right; margin:12px ">Export</a>
		
	<table id="example" class="mdl-data-table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Image</th>
                <th>Name</th>
                <th>Mobile Number</th>
                <th>Route Name</th>
                <th>Action</th>
            </tr>
        </thead>
 
         <tbody>
         <c:set var="count" value="1" scope="page" />



        <c:forEach items="${drivers}" var="driver">    
         <td><c:out value="${count}"/></td>
         <c:choose>
          <c:when test="${driver.image_path!=''}"><td><img class="manage-icon" src="<%=Assets.DRIVER_UPLOAD_PATH_DIS %>/${driver.image_path}"/>
    	
    	</td>
         </c:when>

         <c:otherwise><td><img class="manage-icon" src="resources/dashboard/Images/user_icon.png"/>
    	
    	</td>
         </c:otherwise>
          </c:choose>  
    	<td>${driver.driver_fname} ${driver.middle_name} ${driver.driver_lname}</td>
    	<td><c:out value="${driver.contact_number}"/></td>
    	<td><c:out value="${driver.route_name}"/></td>
    	<%-- <td><c:out value="${driver.address}"/></td> --%>
    	<td >
		<c:set var="driver_id" value="${driver.driver_id}"/>
		<%
		String str=pageContext.getAttribute("driver_id").toString();
		byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
		%>
		<%-- <a href="school/trackDriver?q=<%=new String(encodedBytes)%>"><img alt="Track" title="Track"src="resources/dashboard/Images/track_icon.png" width="25px" style="display: inline-block;
    vertical-align: top;"></i></a>
		<a href="school/viewDriverAttendance?q=<%=new String(encodedBytes)%>"><img alt="view attendance" title="View Attendance" src="resources/dashboard/Images/attancdance.png" width="25px" style="display: inline-block;
    vertical-align: top;"></a>		
		<a title="View" href="school/viewDriver?q=<%=new String(encodedBytes)%>"><i class="fa fa-eye fa-2x edit"></i></a>
		<% if((Integer)session.getAttribute("permission")==1) {%>
		<a title="Edit" href="school/editDriver?q=<%=new String(encodedBytes)%>"><i class="fa fa-pencil-square-o fa-2x edit"></i></a>
		    	<a title="Delete"
		onClick="confirmBox(this.id)" id="${driver.driver_id}" href="javascript:void(0);"><i class="fa fa-times fa-2x delete"></i></a>
		<% } %> --%>
		
		<div class="btn-group parent-action-btn">
	          <a title="Track" class="btn btn-danger" href="school/trackDriver?q=<%=new String(encodedBytes)%>"><i class="fa fa-map-pin"></i></a>
	    	  <a title="View Attandance" class="btn btn-danger" href="school/viewDriverAttendance?q=<%=new String(encodedBytes)%>"><i class="fa fa-user-times"></i></a>
	            <a title="View Driver" class="btn btn-danger" href="school/viewDriver?q=<%=new String(encodedBytes)%>"><i class="fa fa-user-o"></i></a>
				<% if((Integer)session.getAttribute("permission")==1) {%>
				<a title="Edit Driver" class="btn btn-danger"  href="school/editDriver?q=<%=new String(encodedBytes)%>"><i class="fa fa-pencil-square-o edit"></i></a>
				    	
				    	<a title="Delete Driver"  class="btn btn-danger" 
				onClick="confirmBox(this.id)" id="${driver.driver_id}" href="javascript:void(0);"><i class="fa fa-trash-o delete"></i></a>
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
			window.location="school/deleteDriver?driver_id="+id;
	});
}

</script>   
<script src="resources/dashboard/js/jquery.table2excel.js"></script>
<script>
$(function() {
	$("#export").on('click',function(){
		  $("#export_table").table2excel({
		    name: "Worksheet Name",
		    filename: "All Drivers" //do not include extension
		  }); 
});
});
</script>  

<table id="export_table" class="mdl-data-table"  cellspacing="0" width="100%" style="display:none;">
        <thead>
            <tr>
                <th>Driver ID</th>
                <th>Name</th>
                <th>Date of birth</th>
                <th>Email</th>
                <th>Mobile Number</th>
                <th>UserName</th>
                <th>Password</th>
                <th>Nationality</th>
                <th>Blood Group</th>
                <th>Driver Route</th>
                <th>Licence Expriy Date</th>
            </tr>
        </thead>
 
         <tbody>
         <c:set var="count" value="1" scope="page" />



        <c:forEach items="${drivers}" var="driver">    
    	<tr>
       	<td><c:out value="${driver.driver_id}"/></td>
   		<td>${driver.driver_fname} ${driver.middle_name} ${driver.driver_lname}</td>
    	<td>${driver.dob}</td>
    	<td>${driver.d_email}</td>
    	<td>${driver.contact_number}</td>
    	<td>${driver.username}</td>
    	<td>${driver.password}</td>
    	<td>${driver.nationality}</td>
    	<td>${driver.blood_group}</td>
    	<td>${driver.route_name}</td>
    	<td>${driver.licence_expiry}</td>
    	</tr>     
    <c:set var="count" value="${count + 1}" scope="page"/>
     	 </c:forEach>
      
          </tbody>  
        
       </table>             
<jsp:include page="footer.jsp" />      