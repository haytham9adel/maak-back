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
 <script>
 $(document).ready(function() {
	  //  $('#example').DataTable();
	} );
 </script>
<style>
table.dataTable thead th, table.dataTable thead td {
    border-bottom: 1px solid #111;
    padding: 10px;
}
</style>

<div class="col-md-9 col-sm-9 col-xs-12 pad0">
	<div class="col-sm-12 pad0">
	<% if((Integer)session.getAttribute("permission")==1) {%>
	<a href="admin/addHoliday/" class="btn btn-primary btn-submit" style="float:right; margin:12px ">Add New</a>
	<% } %>
	    <span class="success">${success}</span>
             <span class="error">${error}</span>
	<table id="example" class="mdl-data-table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Holiday Name</th>
                <th>Date</th>
                <th>EndDate</th>
                <% if((Integer)session.getAttribute("permission")==1) {%>
                <th>Action</th>
                <% } %>
                <th>
                <input type="checkbox" class="checkbox" id="select_all"
						style="margin-top: 1px; float: left;">
						<button type="button" id="delete-all-btn" class="btn-rem_tb"
							style="float: right;">
							<i class="fa fa-trash"></i>
						</button>
                
                
                	</th>
            </tr>
        </thead>
 
         <tbody>
         <c:set var="count" value="1" scope="page" />



        <c:forEach items="${all_holiday}" var="staff">    
    	<tr>
    	
        <td><c:out value="${count}"/></td>       
    	<td>${staff.holiday_name}</td>
    	<td><c:out value="${staff.holiday_date}"/></td>
    	<td><c:out value="${staff.holiday_enddate}"/></td>
    	<% if((Integer)session.getAttribute("permission")==1) {%>
    	<td>
    	<c:if test="${staff.school_id!=0}">
    	<c:set var="h_id" value="${staff.h_id}"/>
<%
String str=pageContext.getAttribute("h_id").toString();
byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
%>
    	<a title="Edit" href="admin/editHoliday?q=<%=new String(encodedBytes)%>"><i class="fa fa-pencil-square-o fa-2x edit"></i></a>
    	<%--  <a 
onClick="confirmBox(this.id)" id="${staff.h_id}" href="javascript:void(0);"><i class="fa fa-times fa-2x delete"></i></a>
		 --%>
		</c:if>
		
    	<%--
		<c:if test="${staff.school_id==0}">
			<a 
onClick="confirmBox1(this.id)" id="${staff.h_id}" href="javascript:void(0)"><i class="fa fa-times fa-2x delete"></i></a>
		
		
		</c:if> --%>
			
    	</td>
    	<% } %>
    	<td>
    		<input type="checkbox" class="checkbox" name="delete" id="${staff.school_id}" value="${staff.h_id}">
			
		</td>
    	</tr>     
    <c:set var="count" value="${count + 1}" scope="page"/>
     	 </c:forEach>
      
          </tbody>  
        
       </table> 	
	
	</div>
    <form action="admin/deleteMultipleHoliday" method="post" id="send-form">
        <input type="hidden" id="h_id_arr" name="holiday_name" value="">
    	<input type="hidden" name="holiday_date" id="school_id" value="">
    </form>        
</div>
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
                    $('#h_id_arr').val(txt);
                    $('#school_id').val(txt_id);
                
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
                    $('#h_id_arr').val(txt);
                    $('#school_id').val(txt_id);
                
                });
        });
    
    
    $('#example').DataTable({
    	"pagingType": "simple_numbers",
    	"oLanguage": {
    		"sUrl": "resources/dashboard/js/english.json"
    	},
   	 aoColumnDefs : [{
	      orderable : false, aTargets : [4,5] //disable sorting for the 1st column 
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
			window.location="admin/deleteHoliday?h_id="+id;
	});
}

</script> 
<Script>
 function confirmBox1(id)
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
			window.location="admin/deleteAdminHoliday?h_id="+id;
	});
}  
 </Script>
 <script>
//select all checkboxes
 /*  $("#select_all").change(function(){  //"select all" change 
     var status = this.checked; // "select all" checked status
     var txt = '';
     var txt_id = '';
     $('.checkbox').each(function(){ //iterate all listed checkbox items
         this.checked = status; //change ".checkbox" checked status
         
         
     });
     
 });
 */
 //uncheck "select all", if one of the listed checkbox item is unchecked
 $('.checkbox').change(function(){ //".checkbox" change 
     if(this.checked == false){ //if this item is unchecked
         $("#select_all")[0].checked = false; //change "select all" checked status to false
        /*  $('#h_id_arr').val("");
         $('#school_id').val(""); */
     }
 });
 
 </script>
<jsp:include page="footer.jsp" />      