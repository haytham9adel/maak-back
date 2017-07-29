<jsp:include page="header.jsp" />
<%@ page import="resources.Assets" %>
<link href="//cdn.datatables.net/1.10.7/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
<script src="resources/dashboard/js/jquery.dataTables.min.js"></script>
 <script>
 $(document).ready(function() {
	    $('#example').DataTable();
	} );
 </script>
 <style>
 .dataTables_filter, .dataTables_length {
    display: none;
}
 </style>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-9 col-sm-9 col-xs-12 m-side">
	<div class="new-student-form std_frm" style="margin-top:0 !important;"> 
		<div class="notification_tb">
			<div class="table-responsive">
				<table class="table" id="example">
					<thead>
						<tr>
							<td>Notification</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${notifications}" var="noti">
						<tr>
							<td>${noti.noti_desc} at time ${noti.date} <span><!-- <i class="fa fa-remove"></i> --></span></td>
						</tr>	
						</c:forEach>
						
						
					</tbody>
				</table>
			</div><!-- table-responsive -->
		</div><!-- notification_tb -->
	</div>
</div><!-- col-md-9  -->
    
     <jsp:include page="footer.jsp" />      