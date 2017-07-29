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
                     <label class="col-sm-3 control-label">Name :</label>
                       <div class="col-sm-9">
                       
                         ${parent.first_name}  ${parent.last_name}
                        </div>
                   </div>
                	
                  <div class="form-group">
                     <label class="col-sm-3 control-label">UserName :</label>
                       <div class="col-sm-9">
                       
                         ${parent.user_name}
                        </div>
                   </div>
                            <div class="form-group">
                  <label class="col-sm-3 control-label"> Privileges : </label>
                    <div class="col-sm-9">
                     <c:choose>
								<c:when test="${parent.permission==0}">
									Just View
									</c:when>
									<c:otherwise>
									Main School Admin
									</c:otherwise>
	                            </c:choose> </div> 
                 </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Email :</label>
                       <div class="col-sm-9">
                         ${parent.user_email}
                        </div>
                   </div>
                     <div class="form-group">
                     <label class="col-sm-3 control-label">Mobile Number :</label>
                       <div class="col-sm-9">
                       ${parent.contact_number}
                       </div>
                   </div>
                  
                </form>
             </div> 
              <c:set var="count" value="${count + 1}" scope="page"/>
 
   


          </div>
        
       
          
    <jsp:include page="footer.jsp" />      