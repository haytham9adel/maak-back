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
       
      <div class="new-student-form std_frm"> 
             
            
                <form class="form-horizontal" id="student" method="post">
                	<div class="form-group">
                     <div class="p_name">My Profile</div>
                   </div>
                	
                	
					<div class="form-group">
						<label class="col-sm-3 control-label">Name :</label>
						<div class="col-sm-9">
						  <input type="text" value="${parent.first_name}" class="form-control" name="first_name">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label">Middle Name :</label>
						<div class="col-sm-9">
						  <input type="text" value="${parent.middle_name}" class="form-control" name="middle_name">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label">Family Name :</label>
						<div class="col-sm-9">
						  <input type="text" value="${parent.family_name}" class="form-control" name="family_name">
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
                        <input type="text" value="${parent.user_email}" class="form-control" name="user_email">
                         		
                        </div>
                   </div>
                    <div class="form-group">
                     <label class="col-sm-3 control-label">Password :</label>
                       <div class="col-sm-9">
                       <input type="text" value="${parent.user_pass}" class="form-control" name="user_pass">
                        
                        
                        </div>
                   </div>
                     <div class="form-group">
                     <label class="col-sm-3 control-label">Contact Number :</label>
                       <div class="col-sm-9">
                        <input type="text" value="${parent.contact_number}" class="form-control" name="contact_number">
                        
                       
                       </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Mobile Number :</label>
                       <div class="col-sm-9">
                       ${parent.mobile_number}
                       </div>
                   </div>
                   
                    <div class="form-group">
                       <div class="col-sm-12">
                        <input type="submit" value="Update" class="btn btn-primary btn-submit" >
					</div>
                   </div>
                </form>
             </div> 
             
 

   </div>
<script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css">        
<c:if test="${!empty success}">
<script>
	swal(" ${success} ", "success");
</script>
</c:if>
<c:if test="${!empty error}">
<script>
	swal("Error!", " ${error} ", "error");
</script>
</c:if>          
          
    <jsp:include page="footer.jsp" />      