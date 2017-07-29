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
	    <span class="success">${success}</span>
             <span class="error">${error}</span>
	<table id="example" class="mdl-data-table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Page Title</th>
                <th>Image</th>
                <% if((Integer)session.getAttribute("permission")==1) {%>
                <th>Action</th>
                <% } %>
            </tr>
        </thead>
 
         <tbody>
         <c:set var="count" value="1" scope="page" />



        <c:forEach items="${pages}" var="slider">    
    	<tr>
    	
        <td><c:out value="${count}"/></td>       
    	<td>
			${slider.page_name}
    	</td>
    	<c:choose>
	          <c:when test="${slider.page_title!=''}">
	          <td><img class="manage-icon" src="<%=Assets.SLIDER_IMAGES %>${slider.page_title}"/>
	    	
	    		</td>
	          <br />
	         </c:when>
	
	         <c:otherwise><td><img class="manage-icon" src="resources/dashboard/Images/no.jpg"/>
	    	
	    	</td>
	         <br />
	         </c:otherwise>
	          </c:choose>
    	<% if((Integer)session.getAttribute("permission")==1) {%>
    	<td>
     
    	<c:set var="h_id" value="${slider.page_id}"/>
<%
String str=pageContext.getAttribute("h_id").toString();
byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
%>
		<div class="btn-group">
          <a title="Edit" class="btn btn-danger" href="admin/editPageSectionThree?q=<%=new String(encodedBytes)%>"><i class="fa fa-pencil-square-o edit"></i></a>
           </div>
    	</td>
    	<% } %>
    	
    	</tr>     
    <c:set var="count" value="${count + 1}" scope="page"/>
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

<jsp:include page="footer.jsp" />      