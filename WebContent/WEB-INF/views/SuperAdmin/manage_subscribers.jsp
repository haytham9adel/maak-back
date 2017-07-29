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
	<h4>Subscribers</h4>
	    <% if((Integer)session.getAttribute("permission")==1) {%>
	<a href="admin/emailTemplate" class="btn btn-primary btn-submit" style="float:right; margin:12px ">Email Template</a>
	<% } %> 
	<table id="example" class="mdl-data-table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Email</th>
                 <% if((Integer)session.getAttribute("permission")==1) {%>
                <th>Action</th>
                 <th>
                <input type="checkbox" class="checkbox" id="select_all"
						style="margin-top: 13px; float: left;    margin-left: 8px;">
						<button type="button" id="delete-all-btn" class="btn-rem_tb"
							style="float: left;font-size:20px;  margin-left: 9px;">
							<i class="fa fa-send"></i>
						</button>
                
                
                	</th>
                <% } %>
            </tr>
        </thead>
 
         <tbody>
         <c:set var="count" value="1" scope="page" />



        <c:forEach items="${faq}" var="faq">    
    	<tr>
    	
        <td><c:out value="${count}"/></td>       
		<td>${faq.email}</td>
		<% if((Integer)session.getAttribute("permission")==1) {%>
    	<td>
     
    	<c:set var="h_id" value="${faq.sub_id}"/>
<%
String str=pageContext.getAttribute("h_id").toString();
byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
%>
    	<div class="btn-group">
               <a title="Delete" class="btn btn-danger" onClick="confirmBox(this.id)" id="${faq.sub_id}" href="javascript:void(0);"><i class="fa fa-trash-o delete"></i></a>
        </div>
    </td>
    <td>
    		<input type="checkbox" class="checkbox" name="delete" id="${faq.sub_id}" value="${faq.sub_id}">
			
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
	   	       "columns": [
	          	            null,
	          	            null,
	          	            null,
	          	            { "width": "20%" }
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
			window.location="admin/deleteSubscriber?sub_id="+id;
	});
}

</script> 
<script>
$(document).ready(function() {
	
	$("#delete-all-btn").click(function(){
		
		$("#send-form").submit();
	});
	
    $.each($('input[name*=delete'), function () {
    	$(this).on('click',function (){
                	
                    var txt = '';
                    var txt_id = '';
                    $.each($('input[name*=delete]:checked'),
                        function () {
                    	 
                          txt = txt + $(this).val() + ',';
                          txt_id = txt_id + $(this).attr('id') + ',';
                        });
                    if (txt.length > 0)
                        txt = txt.substring(0, txt.length - 1);
                    	txt_id = txt_id.substring(0, txt_id.length - 1);
                    $('#description').val(txt);
                 
                });
        });
    
    $("#select_all").change(function(){  //"select all" change 
        var status = this.checked; // "select all" checked status
        var txt = '';
        var txt_id = '';
        $('.checkbox').each(function(){ //iterate all listed checkbox items
            this.checked = status; //change ".checkbox" checked status
        });
        
    });
    $.each($('input[name*=delete'), function () {
    	$("#select_all").on('change',function (){
    		
    		        var txt = '';
                    var txt_id = '';
                    $.each($('input[name*=delete]:checked'),
                        function () {
                    	  txt = txt + $(this).val() + ',';
                          txt_id = txt_id + $(this).attr('id') + ',';
                        });
                    if (txt.length > 0)
                        txt = txt.substring(0, txt.length - 1);
                    	txt_id = txt_id.substring(0, txt_id.length - 1);
                    $('#description').val(txt);
                    
                
                });
        });
    
    

});

</script>
<c:if test="${!empty success}">
<script>
swal(
		{
			title : "${success}",
			text : "",
			type : "success"
		}, function() {
			 
		});
	
</script>
</c:if>
 <form action="admin/sendEmailTemplates" method="post" id="send-form">
        <input type="hidden" id="description" name="desc" value="">
 </form> 
<jsp:include page="footer.jsp" />      