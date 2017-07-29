<jsp:include page="header.jsp" />

<%@ page import="resources.Assets" %>


<%@ page import="java.util.*" %> 
<%@ page import="org.apache.commons.codec.binary.Base64" %> 
<link href="//cdn.datatables.net/1.10.7/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
<script src="resources/dashboard/js/jquery.dataTables.min.js"></script>
<script src="resources/dashboard/js/jquery.blink.js"></script>
<style>
.blink_class{background: #fff !important;color:#000 !important;}
</style>
 <script>
 $(document).ready(function() {
	    $('#example').DataTable();
	} );
 </script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-9 m-side">

<!--  New Dashboard  -->
<div class="ps_dashboard">
<c:set var="count" value="1" scope="page" />
 <c:forEach items="${students}" var="student">
<div class="col-sm-4">
   	<div class="ps-grid">
   		<c:choose>
	          <c:when test="${student.s_image_path!=''}"><td><img class="manage-icon" src="<%=Assets.STUDENT_UPLOAD_PATH_DIS %>/${student.s_image_path}"/>
	    	
	    	</td>
	          <br />
	         </c:when>
	
	         <c:otherwise><td><img class="manage-icon" src="resources/dashboard/Images/user_icon.png"/>
	    	
	    	</td>
	         <br />
	         </c:otherwise>
	          </c:choose>
   	
       	<c:set var="student_id" value="${student.student_id}"/>
			<%
			String str=pageContext.getAttribute("student_id").toString();
			byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
			%>
           <a class="ps-md track" id="${student.student_id}" rel="${student.blink_status}" href="parent/TrackStudent?q=<%=new String(encodedBytes)%>">Track</a>
           <h3>${student.s_fname} ${student.father_name} ${student.grand_name} ${student.family_name}</h3>
           <div class="ps-btn-cls">
           	<a class="ps-md" href="parent/viewStudent?q=<%=new String(encodedBytes)%>">More Detail</a>
            <a class="ps-md" href="parent/viewAttendance?q=<%=new String(encodedBytes)%>">Attendance</a>
           </div>
       </div>
   </div><!-- col-sm-4 -->
   <script>
   		$(document).ready(function(){
   			setInterval(function(){ changeBlink("${student.student_id}");}, 6000);
   			setInterval(function () {
				var relAttr=$("#${student.student_id}").attr("rel");
				if(parseInt(relAttr)==1)
					{
					 $("#${student.student_id}").toggleClass("track");
					//$("#${student.student_id}").toggle()
					}
				else{
					$("#${student.student_id}").addClass("track");
					//$("#${student.student_id}").show()
				}
			}, 1000);
   		});
   </script>
 </c:forEach>  
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
 function changeBlink(id)
 {
	$.ajax({
			type:"post",
			url:"getStudentBlinkStatus.html",
			data:{'student_id':id},
			success:function(response)
			{
				var obj=$.parseJSON(response)
		 		if(obj.blink_status==1)
				{
		 			$("#"+id).attr("rel",1);
				}else
				{		
					$("#"+id).attr("rel",0);
				}
				
			}
	});
 }
  </script>

          
    <jsp:include page="footer.jsp" />      