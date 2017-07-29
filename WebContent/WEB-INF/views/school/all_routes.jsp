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
	    	      orderable : false, aTargets : [7] //disable sorting for the 1st column
	    	 		  
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
.parent-action-btn{width:87px;}
</style>
<div class="col-md-9 pad0">
	<div class="col-sm-12 pad0">
	<% if((Integer)session.getAttribute("permission")==1) {%>
	<a href="school/addRoute" class="btn btn-primary btn-submit" style="float:right; margin:12px ">Add New</a>
	 <% } %>
	    <span class="success">${success}</span>
             <span class="error">${error}</span>
	<table id="example" class="mdl-data-table sep_row" cellspacing="0" width="100%">
        <thead>
            <tr>
                 <th>ID</th>
                <th>Name</th>
                <th>Note</th>
                <th>Source</th>
               	<th>Note</th>
                <th>Destination</th>
                <th>Note</th>
                <th>Action</th>
            </tr>
        </thead>
 
         <tbody>
         <c:set var="count" value="1" scope="page" />



        <c:forEach items="${staffs}" var="staff">    
    	<tr>
    	<td><c:out value="${count}"/></td>
    	<td>${staff.route_name}</td>
    	<td>${staff.note}</td>
    	<td>${staff.source}</td>
    	<td>${staff.source_note}</td>
    	<td>${staff.destination}</td>
    	<td>${staff.destination_note}</td>
    	<td style="min-width:93px !important;">
		<c:set var="route_id" value="${staff.route_id}"/>
		<%
		String str=pageContext.getAttribute("route_id").toString();
		byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
		%>
    	<div class="btn-group parent-action-btn">
	          <a title="View" class="btn btn-danger"  href="school/viewRoute?q=<%=new String(encodedBytes)%>"><i class="fa fa-eye edit"></i>	</a>
		    	<% if((Integer)session.getAttribute("permission")==1) {%>
		    	<a title="Edit" class="btn btn-danger"  href="school/editRoute?q=<%=new String(encodedBytes)%>"><i class="fa fa-pencil-square-o edit"></i></a>
		    	<a title="Delete"  class="btn btn-danger" 
		onClick="confirmBox(this.id)" id="${staff.route_id}"  href="javascript:void(0);"><i class="fa fa-trash-o delete"></i></a>
		<% } %>     
	        </div>
    	<%-- <a title="View" href="school/viewRoute?q=<%=new String(encodedBytes)%>"><i class="fa fa-eye fa-2x edit"></i></a>
    	 <% if((Integer)session.getAttribute("permission")==1) {%>
    	<a title="Edit" href="school/editRoute?q=<%=new String(encodedBytes)%>"><i class="fa fa-pencil-square-o fa-2x edit"></i></a>
    
    	<a title="Delete"
onClick="confirmBox(this.id)" id="${staff.route_id}" href="javascript:void(0);"><i class="fa fa-times fa-2x delete"></i></a>
	<% } %> --%>
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
		
			window.location="school/deleteRoute?route_id="+id;
	});
}

</script>          
<jsp:include page="footer.jsp" />      