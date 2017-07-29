<jsp:include page="header.jsp" />
<%@ page import="resources.Assets" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="//cdn.datatables.net/1.10.7/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
<script src="resources/dashboard/js/jquery.dataTables.min.js"></script>
 <script>
 $(document).ready(function() {
	    $('#example').DataTable();
	} );
 </script>

<div class="col-md-9 col-sm-9 col-xs-12 pad0">
	<div class="col-sm-12 pad0">
	    <span class="success">${success}</span>
             <span class="error">${error}</span>
	<table id="example" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Image</th>
                <th>Name</th>
                <th>School</th>
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
    	<td><c:out value="${staff.school_name}"></c:out></td>
    	<td><c:out value="${staff.staff_email}"/></td>
    	<td><c:out value="${staff.staff_contact}"/></td>
    	<td><c:out value="${staff.address}"/></td>
    	<td>
    	<a href="admin/editStaff?staff_id=${staff.staff_id}">Edit</a>
    	|
    	<a 
onClick="javascript:return confirm('Are you sure to delete it?')" href="admin/deleteStaff?staff_id=${staff.staff_id}">Delete</a>
    	</td>
    	</tr>     
    <c:set var="count" value="${count + 1}" scope="page"/>
     	 </c:forEach>
      
          </tbody>  
        
       </table> 	
	
	</div>
            
</div>

          
<jsp:include page="footer.jsp" />      