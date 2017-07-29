<jsp:include page="header.jsp" />
<style>
.no_space br {
    display: none;
}
.parent-action-btn{
    width: 87px;
}
</style>
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
	    	          "columns": [
	    		    	             null,
	    		    	             null,
	    		    	             null,
	    		    	             null,
	    		    	             null,
	    		    	             { "width": "25%" }
	    		    	           ]
	    });
	} );
 </script>

<div class="col-md-9 col-sm-9 col-xs-12 pad0">
	<div class="col-sm-12 no_space pad0">
	<% if((Integer)session.getAttribute("permission")==1) {%>
	<a href="admin/addStudent/" class="btn btn-primary btn-submit" style="float:right; margin:12px ">Add New</a>
	<% } %>
	<a href="javascript:void(0);" id="export" class="btn btn-primary btn-submit" style="float:right; margin:12px ">Export</a>
	
	<table id="example" class="mdl-data-table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Image</th>
                <th>Name</th>
                <th>Mobile number</th>
                <th>Route Name</th>
                <!-- <th>Address</th> -->
                <th>Action</th>
            </tr>
        </thead>
 
         <tbody>
         <c:set var="count" value="1" scope="page" />
	
	    	<c:forEach items="${students}" var="student">
	    		<tr>
	                <td><c:out value="${count}"/></td>
	    	<c:choose>
	          <c:when test="${student.s_image_path!=''}"><td><img class="manage-icon" src="<%=Assets.STUDENT_UPLOAD_PATH_DIS %>/${student.s_image_path}"/>
	    	
	    	</td>
	          <br />
	         </c:when>
	
	         <c:otherwise><td><img class="manage-icon" src="resources/dashboard/Images/user_icon.png"/>
	    	
	    	</td>
	         <br />
	         </c:otherwise>
	          </c:choose>
	    	
	    	
	    	
	    	<td>${student.s_fname}&nbsp;${student.father_name}&nbsp;${student.grand_name}&nbsp;${student.family_name}</td>
	    	<td><c:out value="${student.s_contact}"/></td>
	    	<td><c:out value="${student.s_lname}"/></td>
	    	<%-- <td><c:out value="${student.s_address}"/></td> --%>
	    	<td style="min-width:93px !important;">
	    	
			<c:set var="student_id" value="${student.student_id}"/>
			<%
			String str=pageContext.getAttribute("student_id").toString();
			byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
			%>
	    	<%-- <a title="View" href="admin/viewStudent?q=<%=new String(encodedBytes)%>"><i class="fa fa-eye fa-2x edit"></i></a>
	    	<a href="admin/viewStudent?student_id=${student.student_id}"><i class="fa fa-eye fa-2x edit"></i></a>
	    	<% if((Integer)session.getAttribute("permission")==1) {%>
	    	<a title="Edit" href="admin/editStudent?q=<%=new String(encodedBytes)%>"><i class="fa fa-pencil-square-o fa-2x edit"></i></a>
	    	<a title="Delete"
	onClick="confirmBox(this.id)" id="${student.student_id}" href="javascript:void(0);"><i class="fa fa-times fa-2x delete"></i></a>
	<% } %> --%>
				<div class="btn-group parent-action-btn">
		          <a title="View" class="btn btn-danger"  href="admin/viewStudent?q=<%=new String(encodedBytes)%>"><i class="fa fa-eye edit"></i>	</a>
			    	<% if((Integer)session.getAttribute("permission")==1) {%>
			    	<a title="Edit" class="btn btn-danger"  href="admin/editStudent?q=<%=new String(encodedBytes)%>"><i class="fa fa-pencil-square-o edit"></i></a>
			    	<a title="Delete"  class="btn btn-danger" 
			onClick="confirmBox(this.id)" id="${student.student_id}" href="javascript:void(0);"><i class="fa fa-trash-o delete"></i></a>
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
 <c:if test="${!empty success}">
<script>
	swal("Success!", "${success}", "success");
</script>
</c:if>
<c:if test="${!empty error}">
<script>
	swal("Error!", " ${error} ", "error");
</script>
</c:if>       
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
			window.location="admin/deleteStudent?student_id="+id;
	});
}
</script>
<script src="resources/dashboard/js/jquery.table2excel.js"></script>
<script>
$(function() {
	$("#export").on('click',function(){
		  $("#example_export").table2excel({
		    name: "Worksheet Name",
		    filename: "All Students" //do not include extension
		  }); 
});
});
</script>
<table id="example_export" class="mdl-data-table" cellspacing="0" width="100%" style="display:none;">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Mobile Number</th>
                <th>Date of Birth</th>
                <th>Blood group</th>
                <th>Parent-1</th>
                <th>Relation </th>
                <th>Parent-2</th>
                <th>Relation </th>
                <th>Parent-3</th>
                <th>Relation </th>
                <th>Route Name</th>
            </tr>
        </thead>
 
         <tbody>
         <c:set var="count" value="1" scope="page" />
			<c:forEach items="${students}" var="student">
    		<tr>
				<td><c:out value="${count}"/></td>
		    	<td>${student.s_fname}&nbsp;${student.father_name}&nbsp;${student.grand_name}&nbsp;${student.family_name}</td>
		    	<td>${student.s_email}</td>
		    	<td><c:out value="${student.s_contact}"/></td>
		    	<td><c:out value="${student.dob}"/></td>
		    	<td>${student.blood_type}</td>
		    	<td>${student.pp_1}</td>
		    	<td>${student.r_1}</td>
		    	<td>${student.pp_2}</td>
		    	<td>${student.r_2}</td>
		    	<td>${student.pp_3}</td>
		    	<td>${student.r_3}</td>
		    	<td>${student.s_lname}</td>
	    	</tr>     
   		 	<c:set var="count" value="${count + 1}" scope="page"/>
     	 	</c:forEach>
          </tbody>  
       </table>            
<jsp:include page="footer.jsp" />      