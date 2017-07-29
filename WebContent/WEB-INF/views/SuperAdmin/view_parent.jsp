<jsp:include page="header.jsp" />
<%@ page import="java.util.*" %> 
<%@ page import="org.apache.commons.codec.binary.Base64" %> 
<link href="//cdn.datatables.net/1.10.7/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
<script src="resources/dashboard/js/jquery.dataTables.min.js"></script>
 <script>
 $(document).ready(function() {
	    $('#example').DataTable();
	} );
 </script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-9 col-sm-9 col-xs-12 m-side">
            
   <c:forEach items="${allParent}" var="parent">
             <div class="new-student-form std_frm"> 
              <span class="success">${success}</span>
             <span class="error">${error}</span>
                <form class="form-horizontal" id="student" method="post">
                	<div class="form-group">
                     <div class="p_name">Parent - ${count + 1}</div>
                   </div>
                	
                	
                	      <div class="form-group">
                     <label class="col-sm-3 control-label">Name :</label>
                       <div class="col-sm-9">
                       
                         ${parent.first_name} ${parent.middle_name} ${parent.family_name}
                        </div>
                   </div>
                	
                  <div class="form-group">
                     <label class="col-sm-3 control-label">UserName :</label>
                       <div class="col-sm-9">
                       
                         ${parent.user_name}
                        </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Email :</label>
                       <div class="col-sm-9">
                         ${parent.user_email}
                        </div>
                   </div>
                    <div class="form-group">
                     <label class="col-sm-3 control-label">Password :</label>
                       <div class="col-sm-9">
                       
                         ${parent.user_pass}
                        </div>
                   </div>
                     
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Mobile Number :</label>
                       <div class="col-sm-9">
                       ${parent.mobile_number}
                       </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Contact Number :</label>
                       <div class="col-sm-9">
                       ${parent.contact_number}
                       </div>
                   </div>
                </form>
             </div> 
              <c:set var="count" value="${count + 1}" scope="page"/>
    </c:forEach> 
    <c:if test="${count < 3}">
    
    	<c:set var="p_status" value="${parent.p_status}"/>
	<%
	String str=pageContext.getAttribute("p_status").toString();
	byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
	%>
	<div class="col-sm-12"> 
      <a href="admin/AddNewParent?q=<%=new String(encodedBytes)%>"><button class="btn btn-primary btn-submit">Additional Parent</button></a>   
    </div> 
  </c:if> 
<div class="col-sm-12"> 
<h2>Student Details</h2>
</div>
<div class="col-md-12">
	<div class="col-sm-12">
	    <span class="success">${success}</span>
             <span class="error">${error}</span>
	<table id="example" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Route Name</th>
                <th>Mobile Number</th>
                <th>Action</th>
            </tr>
        </thead>
 
         <tbody>
         <c:set var="count" value="1" scope="page" />



                <c:forEach items="${students}" var="student">
    	<tr>
    	<td><c:out value="${count}"/></td>
    	<td><c:out value="${student.s_fname} ${student.father_name} ${student.grand_name} ${student.family_name}"/></td>
    	<td><c:out value="${student.s_lname}"/></td>
    	<td><c:out value="${student.s_contact}"/></td>
    	<td>
	<c:set var="student_id" value="${student.student_id}"/>
	<%
	String str=pageContext.getAttribute("student_id").toString();
	byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
	%>
    	<a href="admin/viewStudent?q=<%=new String(encodedBytes)%>"><i class="fa fa-eye fa-2x edit"></i></a>
    	<a href="admin/editStudent?q=<%=new String(encodedBytes)%>"><i class="fa fa-pencil-square-o fa-2x edit"></i></a>
    
    	<a 
onClick="javascript:return confirm('Are you sure to delete it?')" href="admin/deleteStudent?student_id=${student.student_id}"><i class="fa fa-times fa-2x delete"></i></a>

    	
    	</td>
    	</tr>     
    <c:set var="count" value="${count + 1}" scope="page"/>
     	 </c:forEach>
      
          </tbody>  
        
       </table> 	
	
	</div>
            
</div>
          </div>
        
        <script src="resources/front/js/jquery.validate.min.js"> </script>
  <script>
  $(document).ready(function(){
		$("#student").validate({
          rules: {
        	  user_name: "required",
        	  user_email:{
						required: true,
						email: true
					},
	      },
          messages: {
        	  user_name: "Please enter name",
        	  user_email:{
						required: "Please enter email address",
						email: "Please enter valid email address"
					},
		  },
          submitHandler: function(form) {
              form.submit();
          }
      });
      

});
  
  </script>

          
    <jsp:include page="footer.jsp" />      