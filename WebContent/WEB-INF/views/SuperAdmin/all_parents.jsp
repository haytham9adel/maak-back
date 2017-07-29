<jsp:include page="header.jsp" />

<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	    	      orderable : false, aTargets : [5] //disable sorting for the 1st column
	    	 		  
	    	 }],
	    	 columnDefs: [
		   	              {
		   	                  targets: [ 0, 1 ],
		   	                  className: 'mdl-data-table__cell--non-numeric'
		   	              }
		   	          ],
		   	       "columns": [
			    	             null,
			    	             null,
			    	             null,
			    	             null,
			    	             null,
			    	             { "width": "35%" }
			    	           ]
	    });
	} );
 </script>
<style>
.parent-action-btn {width: 87px;}
</style>
<div class="col-md-9 col-sm-9 col-xs-12 pad0">
	<div class="col-sm-12 pad0">
	<% if((Integer)session.getAttribute("permission")==1) {%>
	<a href="admin/addParent/" class="btn btn-primary btn-submit" style="float:right; margin:12px ">Add New</a>
	<% } %>
	    <span class="success">${success}</span>
             <span class="error">${error}</span>
             <a href="javascript:void(0);" id="export" class="btn btn-primary btn-submit" style="float:right; margin:12px ">Export</a>
		
	<table id="example" class="mdl-data-table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>User Name</th>
              <!--   <th>Email</th> -->
                <th>Mobile No.</th>
                <th style="width: 110px !important;">Action</th>
            </tr>
        </thead>
 
         <tbody>
         <c:set var="count" value="1" scope="page" />



                <c:forEach items="${parents}" var="parent">
    	<tr>
    	<td><c:out value="${count}"/></td>
    	<!-- <td>${fn:toUpperCase(parent.first_name)} ${fn:toUpperCase(parent.middle_name)}  ${fn:toUpperCase(parent.family_name)}</td> -->
    	<td style="text-transform: capitalize;">${parent.first_name} ${parent.middle_name}  ${parent.family_name}</td>
    	<td>${parent.user_name}</td>
    	<%-- <td>${parent.user_email}</td> --%>
    	<td><c:out value="${parent.mobile_number}"/></td>
    	<td>
    	
    		<c:set var="user_id" value="${parent.user_id}"/>
	<%
	String str=pageContext.getAttribute("user_id").toString();
	byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
	%>
    	
    	  <%-- 	<a title="View" href="admin/viewParent?parent=<%=new String(encodedBytes)%>"><i class="fa fa-eye fa-2x edit"></i></a>
    	  	<% if((Integer)session.getAttribute("permission")==1) {%>
    	<a title="Edit" href="admin/editParent?parent=<%=new String(encodedBytes)%>"><i class="fa fa-pencil-square-o fa-2x edit"></i></a>
 
    	<a title="Delete" 
onClick="confirmBox(this.id)" id="${parent.user_id}" href="javascript:void(0);"><i class="fa fa-times fa-2x delete"></i></a>
<% } %> --%>
	   <div class="btn-group parent-action-btn">
	   	 <a title="View" class="btn btn-danger" href="admin/viewParent?q=<%=new String(encodedBytes)%>"><i class="fa fa-eye view"></i></a>
         <% if((Integer)session.getAttribute("permission")==1) {%>
         <a title="Edit" class="btn btn-danger" href="admin/editParent?parent=<%=new String(encodedBytes)%>"><i class="fa fa-pencil-square-o edit"></i></a>
         <a title="Delete" class="btn btn-danger" onClick="confirmBox(this.id)" id="${parent.user_id}" href="javascript:void(0);"><i class="fa fa-trash-o delete"></i></a>
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
			window.location="admin/deleteParent?user_id="+id;
	});
}

</script> 
<script src="resources/dashboard/js/jquery.table2excel.js"></script>
<script>
$(function() {
	$("#export").on('click',function(){
		  $("#example_export").table2excel({
		    name: "Worksheet Name",
		    filename: "All Parents" //do not include extension
		  }); 
});
});
</script>          
  <table id="example_export" class="mdl-data-table" cellspacing="0" width="100%" style="display:none;">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>User Name</th>
                <th>Password</th>
                <th>Email</th>
                <th>Contact Number</th>
                <th>Mobile Number</th>
            </tr>
        </thead>
 
         <tbody>
         <c:set var="count" value="1" scope="page" />
      <c:forEach items="${parents}" var="parent">
    	<tr>
    	<td><c:out value="${parent.user_id}"/></td>
    	<td>${fn:toUpperCase(parent.first_name)} ${fn:toUpperCase(parent.middle_name)}  ${fn:toUpperCase(parent.family_name)}</td>
    	<td>${parent.user_name}</td>
    	<td>${parent.user_pass}</td>
    	<td>${parent.user_email}</td>
    	<td><c:out value="${parent.contact_number}"/></td>
    	<td><c:out value="${parent.mobile_number}"/></td>
    	</tr>     
    <c:set var="count" value="${count + 1}" scope="page"/>
     	 </c:forEach>
          </tbody>  
       </table>         
<jsp:include page="footer.jsp" />      