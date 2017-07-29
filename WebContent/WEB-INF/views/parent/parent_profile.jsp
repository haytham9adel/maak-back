<jsp:include page="header.jsp" />
<link href="//cdn.datatables.net/1.10.7/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
<script src="resources/dashboard/js/jquery.dataTables.min.js"></script>
 <script>
 $(document).ready(function() {
	    $('#example').DataTable();
	} );
 </script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-9 col-sm-9 col-xs-12 m-side">
        <div class="col-sm-12" style="margin-bottom:15px;"><a href="parent/editProfile" class="btn btn-primary btn-submit">Edit Profile</a></div>     
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
                     <label class="col-sm-3 control-label">Contact Number :</label>
                       <div class="col-sm-9">
                       ${parent.contact_number}
                       </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Mobile Number :</label>
                       <div class="col-sm-9">
                       ${parent.mobile_number}
                       </div>
                   </div>
                </form>
             </div> 
             
	 </c:forEach>

   </div>
        
      
          
    <jsp:include page="footer.jsp" />      