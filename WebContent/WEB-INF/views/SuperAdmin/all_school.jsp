
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<%@ page import="java.util.*" %> 
<%@ page import="org.apache.commons.codec.binary.Base64" %> 
<%@ page import="resources.Assets" %>
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
	    	      orderable : false, aTargets : [1,6], //disable sorting for the 1st column
             	 // className: 'mdl-data-table__cell--non-numeric',
	    	 }],
	    	 columnDefs: [
	    	              {
	    	                  targets: [ 0, 1, 2 ],
	    	                  className: 'mdl-data-table__cell--non-numeric'
	    	              }
	    	          ],
	    	 
	    	 "columns": [
	    	             { "width": "5%" },
	    	             { "width": "10%" },
	    	             { "width": "10%" },
	    	             { "width": "15%" },
	    	             { "width": "20%" },
	    	             { "width": "20%" },
	    	             { "width": "20%" }
	    	           ]
	    });
	  } );
 </script>
	
<div class="col-md-9 col-sm-9 col-xs-12 pad0">
	<div class="col-sm-12 pad0">
	<% if((Integer)session.getAttribute("permission")==1) {%>
	<a href="admin/addSchool/" class="btn btn-primary btn-submit" style="float:right; margin:12px ">Add New</a>
    <% } %>
	
	<table id="example"  class="mdl-data-table"  cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th name="logo">School Logo</th>
                <th>School Name</th>
                <th>Address</th>
                <th>No. of Buses</th>
                <th>No. of Student</th>
                <th>Action</th>
            </tr>
        </thead>
         <tbody>
         <c:set var="count" value="1" scope="page" />



                <c:forEach items="${schools}" var="school">
    	<tr>
    	<td><c:out value="${count}"/></td>
  		<td>
			<c:choose>
			<c:when test="${school.school_logo!=''}">
			
			<img id="" alt="image_path"  class="manage-icon-small" src="<%=Assets.SCHOOL_UPLOAD_PATH %>${school.school_logo}"/>
			
			</c:when>
			<c:otherwise>
			<img class="manage-icon-small" id="" src="resources/dashboard/Images/s_logo_d.png" alt="image_path" />
			 </c:otherwise>
			 </c:choose>
    	</td>
     <c:set var="s_id" value="${school.s_id}"/>
    	 <%
    	 		String str=pageContext.getAttribute("s_id").toString();
    			byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
    	//String ab=new String(encodedBytes);
    	%>
    	<td><a href="admin/editSchool?sc_id=<%=new String(encodedBytes)%>">${school.school_name}</a></td>
    	<td>${school.school_address_field}</td>
    	<td><c:out value="${school.total_bus}"/></td>
    	<td><c:out value="${school.total_students}"/></td>
    	<td>
    	<div class="btn-group">
          <a title="Edit" class="btn btn-danger" href="admin/editSchool?sc_id=<%=new String(encodedBytes)%>"><i class="fa fa-pencil-square-o edit"></i></a>
          <a title="Delete" class="btn btn-danger" onClick="confirmBox(this.id)" id="${school.s_id}" href="javascript:void(0);"><i class="fa fa-trash-o delete"></i></a>
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
			window.location="admin/deleteSchool?s_id="+id;
	});
}

</script>           
    <jsp:include page="footer.jsp" />      