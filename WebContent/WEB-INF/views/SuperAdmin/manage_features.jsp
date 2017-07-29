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
<style>
table.dataTable thead th, table.dataTable thead td {
    border-bottom: 1px solid #111;
    padding: 10px;
}
</style>

<div class="col-md-9 col-sm-9 col-xs-12 pad0">
	<div class="col-sm-12 pad0">
	<% if((Integer)session.getAttribute("permission")==1) {%>
	<a href="admin/addFeature/" class="btn btn-primary btn-submit" style="float:right; margin:12px ">Add New</a>
	<% } %>
	    <span class="success">${success}</span>
             <span class="error">${error}</span>
	<table id="example" class="mdl-data-table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Image</th>
                 <% if((Integer)session.getAttribute("permission")==1) {%>
                <th>Action</th>
                <% } %>
            </tr>
        </thead>
 
         <tbody>
         <c:set var="count" value="1" scope="page" />



        <c:forEach items="${features}" var="features">
        <c:if test="${features.title!=''}">    
    	<tr>
    	
        <td><c:out value="${count}"/></td>       
		<td>${features.title}</td>
			<c:choose>
	          <c:when test="${features.image_path!=''}">
	          <td><img class="manage-icon" src="<%=Assets.FEATURES_IMAGE %>${features.image_path}"/></td>
	         </c:when>
	         <c:otherwise>
	         	<td><img class="manage-icon" src="resources/dashboard/Images/no.jpg"/></td>
	         </c:otherwise>
	          </c:choose>
		<% if((Integer)session.getAttribute("permission")==1) {%>
    	<td>
     
    	<c:set var="h_id" value="${features.features_id}"/>
<%
String str=pageContext.getAttribute("h_id").toString();
byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
%>
    	<div class="btn-group">
          <a title="Edit" class="btn btn-danger" href="admin/editFeature?q=<%=new String(encodedBytes)%>"><i class="fa fa-pencil-square-o edit"></i></a>
          <a title="Delete" class="btn btn-danger" onClick="confirmBox(this.id)" id="${features.features_id}"  href="javascript:void(0);"><i class="fa fa-trash-o delete"></i></a>
        </div>
    </td>
    	<% } %>
    	
    	</tr>     
    <c:set var="count" value="${count + 1}" scope="page"/>
    </c:if>
     	 </c:forEach>
      
          </tbody>  
        
       </table> 	
	
	</div>
       
</div>
<script>
$(document).ready(function() {
	$('#example').DataTable({
		"pagingType": "simple_numbers",
    	"oLanguage": {
    		"sUrl": "resources/dashboard/js/english.json"
    	},
   	 aoColumnDefs : [{
	      orderable : false, aTargets : [2] //disable sorting for the 1st column
	 }],
	 columnDefs: [
   	              {
   	                  targets: [ 0, 1 ],
   	                  className: 'mdl-data-table__cell--non-numeric'
   	              }
   	          ],
   	       
});
});

</script>
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
			window.location="admin/deleteFeature?features_id="+id;
	});
}

</script> 
<jsp:include page="footer.jsp" />      