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
	<a href="admin/addSlider/" class="btn btn-primary btn-submit" style="float:right; margin:12px ">Add New</a>
	<% } %>
	    <span class="success">${success}</span>
        <span class="error">${error}</span>
	<table id="example" class="mdl-data-table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Slider Type</th>
                <th>Image</th>
                <% if((Integer)session.getAttribute("permission")==1) {%>
                <th>Action</th>
                <% } %>
            </tr>
        </thead>
 
         <tbody>
         <c:set var="count" value="1" scope="page" />



        <c:forEach items="${sliders}" var="slider">    
    	<tr>
    	
        <td><c:out value="${count}"/></td>       
    	<td>
			<c:choose>
			<c:when test="${slider.slider_type==0}">
			Main Slider
			  
			</c:when>    
			<c:otherwise>
				Second Slider
			</c:otherwise>
			</c:choose>
    	</td>
    	<c:choose>
	          <c:when test="${slider.slider_image!=''}">
	          <td><img class="manage-icon" src="<%=Assets.SLIDER_IMAGES %>/${slider.slider_image}"/>
	    	
	    		</td>
	         </c:when>
	
	         <c:otherwise><td><img class="manage-icon" src="resources/dashboard/Images/no.jpg"/>
	    	
	    	</td>
	         </c:otherwise>
	          </c:choose>
    	
    	<% if((Integer)session.getAttribute("permission")==1) {%>
    	<td>
     
    	<c:set var="h_id" value="${slider.slider_id}"/>
<%
String str=pageContext.getAttribute("h_id").toString();
byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
%>
    	<div class="btn-group">
	          <a title="Edit" class="btn btn-danger" href="admin/editSlider?q=<%=new String(encodedBytes)%>"><i class="fa fa-pencil-square-o edit"></i></a>
	          <a title="Delete" class="btn btn-danger" onClick="confirmBox(this.id)" id="${slider.slider_id}"  href="javascript:void(0);"><i class="fa fa-trash-o delete"></i></a>
	        </div>
		</td>
    	<% } %>
    	
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
	      orderable : false, aTargets : [2,3] //disable sorting for the 1st column
	 		  
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
			window.location="admin/deleteSlider?slider_id="+id;
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