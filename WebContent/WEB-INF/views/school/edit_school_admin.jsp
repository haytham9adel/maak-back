<jsp:include page="header.jsp" />
<script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css">

<%@ page import="java.util.*" %> 
<%@ page import="org.apache.commons.codec.binary.Base64" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-6 m-side">
             <div class="new-student-form"> 
          
                <form class="form-horizontal" id="student" method="post">
              		
              		
              		 <div class="form-group">
                     <label class="col-sm-3 control-label">First Name :</label>
                       <div class="col-sm-9">
                       
                         <input type="text" name="first_name" value="${parent.first_name}" id="first_name" class="form-control">
                        </div>
                   </div>
                   
                         <div class="form-group">
                     <label class="col-sm-3 control-label">Last Name :</label>
                       <div class="col-sm-9">
                       
                         <input type="text" name="last_name" value="${parent.last_name}" id="last_name" class="form-control">
                        </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Username :</label>
                       <div class="col-sm-9">
                         <input type="text" disabled="disabled" value="${parent.user_name}" id="user_name" class="form-control">
                        </div>
                   </div>
   					<div class="form-group">
                     <label class="col-sm-3 control-label">Privileges :</label>
                       <div class="col-sm-9">
                       
                        <div class="radio">
						 
	                         <label>
	                            <input type="radio"
	                            	<c:choose>
								<c:when test="${parent.permission==0}">
								checked="checked"
									</c:when>
									</c:choose>
	                            
	                              name="permission" checked="checked" value="0"  id="permission"> Just View
	   
	                         </label>
                             <label>
	                            <input type="radio" <c:choose>
								<c:when test="${parent.permission==1}">
								checked="checked"
									</c:when>
									</c:choose> name="permission"  value="1"  id="permission"> Main School Admin
	   
	                         </label>
                       </div>
                        </div>
                   </div>
              			
              		
                  
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Email :</label>
                       <div class="col-sm-9">
                         <input type="text" name="user_email" value="${parent.user_email}"  id="user_email" class="form-control">
                        </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Password :</label>
                       <div class="col-sm-9">
                        <input type="text" name="user_pass" value="${parent.user_pass}" class="form-control">
                       </div>
                   </div>
                     <div class="form-group">
                     <label class="col-sm-3 control-label">Contact Number :</label>
                       <div class="col-sm-9">
                        <input type="text" name="contact_number"  disabled="disabled" value="${parent.contact_number}" id="contact_number" class="form-control">
                       </div>
                   </div>
                    <div class="form-group">
                       <div class="col-sm-9">
                        <input  class="btn btn-primary btn-submit" value="Save" type="submit">&nbsp;&nbsp;
                        <a href="school/manageSchoolsAdmin">
                        <input  class="btn btn-primary btn-submit" value="Cancel" type="button">
                        </a>
                       </div>
                   </div>
                </form>
             </div> 
          </div>
       		<!--School admin list end here-->
			<div class="clear">&nbsp;</div>
           <div class="col-md-3">
             <div class="info-block"> 
          	<h4>School Admin</h4>
          	</div>
             <c:set var="count" value="1" scope="page" />
				 <c:forEach items="${school_admin}" var="school_admin">
              <div class="info-block"> 
               <div class="info-group">
               <div class="col-a">
               </div>
               <% if((Integer)session.getAttribute("permission")==1) {%>
               <div class="col-b" style="text-align:right;">
               <c:set var="school_admin_id" value="${school_admin.user_id}"/>
	<%
	String str=pageContext.getAttribute("school_admin_id").toString();
	byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
	%>
               		<a href="school/editSchoolAdmin?q=<%=new String(encodedBytes)%>"><i class="fa fa-pencil-square-o fa-2x edit"></i></a>
               </div>
               <% } %>
               </div>
                 <div class="info-group">
                 	 <div class="col-a">Name : </div>
                    <div class="col-b"> ${school_admin.first_name}     ${school_admin.last_name}   </div> 
                 </div>
                 <div class="info-group">
                    <div class="col-a"> Username : </div>
                    <div class="col-b"> ${school_admin.user_name} </div> 
                 </div>
                 <div class="info-group">
                    <div class="col-a">Privileges${school_admin.permission} : </div>
                    <div class="col-b"> <c:choose>
								<c:when test="${school_admin.permission==0}">
									Just View
									</c:when>
									<c:otherwise>
									Main School Admin
									</c:otherwise>
	                            </c:choose> </div> 
                 </div>
                 <div class="info-group">
                    <div class="col-a">Email : </div>
                    <div class="col-b"> ${school_admin.user_email} </div> 
                 </div>
                 <div class="info-group">
                    <div class="col-a"> Contact Number : </div>
                    <div class="col-b"> ${school_admin.contact_number} </div> 
                 </div>
                 
              </div> 
              		<c:set var="count" value="${count + 1}" scope="page"/>
     			 </c:forEach>
        	
                 
          
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
    <jsp:include page="footer.jsp" />      