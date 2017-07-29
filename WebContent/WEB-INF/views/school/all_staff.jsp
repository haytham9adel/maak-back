<jsp:include page="header.jsp" />
<%@ page import="resources.Assets" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.datatables.net/1.10.13/css/dataTables.material.min.css" rel="stylesheet" type="text/css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/material-design-lite/1.1.0/material.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js">
</script>
<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.13/js/dataTables.material.min.js">
</script>
 <script>
 $(document).ready(function() {
	    $('#example').DataTable();
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
	} );
 </script>

<div class="col-md-9">
	<div class="col-sm-12">
	    <span class="success">${success}</span>
             <span class="error">${error}</span>
	<table id="example" class="mdl-data-table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Image</th>
                <th>Name</th>
                <th>Email</th>
                <th>Contact Number</th>
                <th>Address</th>
                <th>Action</th>
            </tr>
        </thead>
 
         <tbody>
         <c:set var="count" value="1" scope="page" />



        <c:forEach items="${staffs}" var="staff">    
    	<tr>
    	
                <td><c:out value="${count}"/></td>
    	<c:choose>
          <c:when test="${staff.image_path!=''}"><td><img class="manage-icon" src="<%=Assets.STAFF_UPLOAD_PATH_DIS %>/${staff.image_path}"/>
    	
    	</td>
          <br />
         </c:when>

         <c:otherwise><td><img class="manage-icon" src="resources/dashboard/Images/user_icon.png"/>
    	
    	</td>
         <br />
         </c:otherwise>
          </c:choose>
    	
    	
    	
    	<td><c:out value="${staff.staff_name}"/>&nbsp;<c:out value="${staff.staff_lname}"/></td>
    	<td><c:out value="${staff.staff_email}"/></td>
    	<td><c:out value="${staff.staff_contact}"/></td>
    	<td><c:out value="${staff.address}"/></td>
    	<td>
    	<a href="school/editStaff?staff_id=${staff.staff_id}">Edit</a>
    	|
    	<a 
onClick="javascript:return confirm('Are you sure to delete it?')" href="school/deleteStaff?staff_id=${staff.staff_id}">Delete</a>
    	</td>
    	</tr>     
    <c:set var="count" value="${count + 1}" scope="page"/>
     	 </c:forEach>
      
          </tbody>  
        
       </table> 	
	
	</div>
            
</div>

          
<jsp:include page="footer.jsp" />      