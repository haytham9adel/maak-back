<jsp:include page="header.jsp" />
<%@ page import="resources.Assets"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script>
	$(function() {
		$("#insurance_end_date").datepicker({
			dateFormat : 'yy-mm-dd',
			changeMonth : true,
			changeYear : true,
			minDate: 0,
			yearRange : "-0:+50",
		});
		 $('body').on('click',".d", function(){  
		    	//$(this).datepicker('destroy').datepicker({showOn:'focus'}).focus();
		    	$(this).datepicker('destroy');
		    	$(this).datepicker({ dateFormat: 'yy-mm-dd',
		        	changeMonth: true,
		            changeYear: true,
		            showOn:'focus',
		            yearRange: "-0:+50",}).focus();
		    	   	
		    });
	});
</script>
<script>
	$(document).ready(function() {
		myFunction();

	});
</script>
<script>
	function myFunction() {
		document.getElementById("vechile").reset();
	}
</script>
<div class="col-md-8 col-sm-8 col-xs-12 m-side">

	<div class="new-student-form">
		<span class="success">${success}</span> <span class="error">${error}</span>
		<form class="form-horizontal" id="vechile" method="post"  enctype="multipart/form-data">
			<input type="hidden" name="color" id="insurance_card_copy1" value="${vechileBean.insurance_card_copy}">
			<div class="form-group">
				<label class="col-sm-3 control-label">Vehicle Name :</label>
				<div class="col-sm-9">
					${vechileBean.vehile_name}
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">Vechile Number :</label>
				<div class="col-sm-9">
					${vechileBean.bus_number}
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Model :</label>
				<div class="col-sm-9">
					${vechileBean.model}
				</div>
			</div>
			<%-- <div class="form-group">
				<label class="col-sm-3 control-label">Vehicle insurance End
					date :</label>
				<div class="col-sm-9">
					<input type="text" name="insurance_end_date"
						value="${vechileBean.insurance_end_date}" id="insurance_end_date"
						class="form-control">
				</div>
			</div> --%>
			<!--------------------------DO Not Disturb ---------------------------------->
			<div class="input_fields_wrap">
			<% int j1=0; %>
			  <c:forEach items="${documents}" var="doc">
				<div class="panel section_repeat" id="">
					<div class="form-group">
						<label class="col-sm-4 control-label">Document Name :</label>
						<div class="col-sm-8">
							${doc.insurance_document_name}
						</div>
					</div><!-- form-group -->
					<div class="form-group">
						<label class="col-sm-4 control-label">Expiry Date :</label>
						<div class="col-sm-8">
							${doc.insurance_document_expiry}
						</div>
					</div><!-- form-group -->
					<div class="form-group">
						<label class="col-sm-4 control-label">Remind Date :</label>
						<div class="col-sm-8">
							${doc.insurance_end_date}
						</div>
					</div><!-- form-group -->
					<div class="form-group">
						<label class="col-sm-4 control-label">Remind before days :</label>
						<div class="col-sm-8">
							${doc.remind_day}
						</div>
					</div><!-- form-group -->
					<div class="form-group">
						<label class="col-sm-4 control-label">Document copy :</label>
						<div class="col-sm-8">
							${doc.insurance_card_copy}
							<a download="download" class="btn btn-warning" style="float: right;" href="<%=Assets.INSURANCE_CARD_COPY %>${doc.insurance_card_copy}" />Download</a>	
						</div>
					</div><!-- form-group -->
				</div><!-- panel -->
				<% j1++; %>
				 </c:forEach>
				
			</div><!-- input_fields_wrap -->
			<!--------------------------DO Not Disturb End---------------------------------->
			 <%-- <c:set var="images" value="${vechileBean.insurance_card_copy}"/>
				
				
 				<%
				String str=pageContext.getAttribute("images").toString();
 				String[] abc=str.split(",");
 				List<String> elephantList = Arrays.asList(abc);
			
 				if(str.length()>0)
				{	
				for (int i = 0; i < elephantList.size(); i++) { %>
			<div class="form-group" id="<%=i%>">
				<label class="col-sm-3 control-label">Vechile insurance
					card copy :</label>
					
				<div class="col-sm-9">
					<input type="file" style="float: left;" name="insurance_card_copy"
						value="<%=Assets.INSURANCE_CARD_COPY %>${vechileBean.insurance_card_copy}"
						id="insurance_card_copy" class=""> 
						<c:if test="${vechileBean.insurance_card_copy!=''}">
						<% if(elephantList.get(i)!="" && elephantList.get(i)!=null ){ %>
						<%=elephantList.get(i) %> &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0);" onclick="removeCard('${vechileBean.vechile_id}','<%=elephantList.get(i) %>','<%=i%>')">
						<i style="color:red;" class="fa fa-remove"></i>
 						</a>
 						<a download="download"
						class="btn btn-warning" style="float: right;"
						href="<%=Assets.INSURANCE_CARD_COPY %><%=elephantList.get(i) %>" />Download</a>
				<% } %>
						</c:if> 
				</div>
			</div>
						
						<%		
				}
				}else
				{
				
			%>
				<div class="form-group">
				<label class="col-sm-4 control-label">Veichle inssurance
					card copy :</label>
				<div class="col-sm-8">
					<input type="file" name="insurance_card_copy[0]"
						id="insurance_card_copy" class="">
				</div>

			</div>
			
			<%	} %> --%>
			

			
		</form>
	</div>

</div>

<script src="resources/front/js/jquery.validate.min.js">
	
</script>
<script>
	$(document).ready(function() {
		$("#vechile").validate({
			rules : {
				school_id : "required",
				vehile_name : "required",
				manufacture : "required",
				model : "required",
				year : "required",
				color : "required",
				configurtion : "required",
				engine : "required",
				bus_number : "required"
			},
			messages : {
				school_id : "Please select school",
				vehile_name : "Please enter vehicle name",
				manufacture : "Please enter manufacture company",
				model : "Please enter vehile model number",
				year : "Please enter vehile year",
				color : "Please enter vechile",
				configurtion : "Please enter vechil configuration",
				engine : "Please enter engine number",
				bus_number : "Please enter vechile number"
			},
			submitHandler : function(form) {
				form.submit();
			}
		});

	});
</script>
<script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css">
<script>
	$(document).ready(function() {

		$('#save').on('click', function(e) {

			e.preventDefault();
			var form = $(this).parents('form');
			swal({
				title : "Are you sure?",
				text : "Do you want to update?",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "Yes, Update!",
				closeOnConfirm : false
			}, function(isConfirm) {
				if (isConfirm)
					form.submit();
			});
		})

		$('#cancel').on('click', function(e) {
			e.preventDefault();
			var form = $(this).parents('form');
			swal({
				title : "Are you sure?",
				text : "Do you want to cancel?",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "Yes, Cancel!",
				closeOnConfirm : false
			}, function(isConfirm) {
				if (isConfirm)
					window.location = "admin/manageVehicle";
			});
		})
	});
	
	function removeCard(id,card_copy,i)
	{
		 $.ajax({
			
			url : 'removeCardCopy.html',
			type : 'post',
			"data":{"color":card_copy,"vechile_id":id},
			success : function(data) {
				$("#"+i).hide();
				var str=$("#insurance_card_copy1").val();
				var str_array = str.split(',');
				arr = $.grep(str_array, function(value) {
					  return value != card_copy;
					});
				var stNew=arr.join(",");
				var str=$("#insurance_card_copy1").val(stNew);
			}
		}); 
	}
</script>
<%-- <script>
	$(document)
			.ready(
					function() {
						var max_fields = 10; //maximum input boxes allowed
						var wrapper = $(".input_fields_wrap"); //Fields wrapper
						var add_button = $(".add_field_button"); //Add button ID
						<%
						if(str.length()>0)
						{	
						%>
						
						var y=-1;
						
						<% }else{ %>
						
						var y=0;
						<% } %>
					
						var x = 1; //initlal text box count
						$(add_button)
								.click(
										function(e) { //on add input button click
											e.preventDefault();
											if (x < max_fields) { //max input box allowed
												x++; //text box increment
												y++;
												$(wrapper)
														.append(
																'<div class="a"><div class="form-group"><div class="col-sm-8 col-sm-offset-4" style="margin-top:15px;"><div><input style="float:left;" type="file" name="insurance_card_copy['+y+']"/><a href="#" class="remove_field">Remove</a></div></div></div></div>'); //add input box
											}
										});

						$(wrapper).on("click", ".remove_field", function(e) { //user click on remove text
							e.preventDefault();
							$(this).parent().parent().parent().parent('div').remove();
							x--;
							y--;
						})
					});
</script>
 --%>
<script>
	$(document)
			.ready(
					function() {
						 
						var max_fields = 10; //maximum input boxes allowed
						var wrapper = $(".input_fields_wrap"); //Fields wrapper
						var add_button = $(".add_field_button"); //Add button ID
						var y=<%=j1-1%>;
						var x = 1; //initlal text box count
						$(add_button)
								.click(
										function(e) { //on add input button click
											e.preventDefault();
											if (x < max_fields) { //max input box allowed
												x++; //text box increment
												y++;
												$(wrapper)
														.append('<div class="panel section_repeat" id=""><span class="col-sm-12"><a href="#" style="float:right;padding:0 14px 8px 0;color:red;" class="remove_field ">X</a></span><div class="form-group"><label class="col-sm-4 control-label">Document Name :</label><div class="col-sm-8"><input type="text" name="insurance_document_name['+y+']"	value="" id="insurance_end_date" class="form-control"><input type="hidden" name="v_doc_id['+y+']" value="0"></div></div><div class="form-group"><label class="col-sm-4 control-label">Expiry Date :</label><div class="col-sm-8"><input type="text" name="insurance_document_expiry['+y+']" value="" id="insurance_end_date'+y+'"	class="form-control d"></div></div><div class="form-group"><label class="col-sm-4 control-label">Expiry Date :</label><div class="col-sm-8"><input type="text" name="insurance_end_date_e['+y+']" value="" id="insurance_end_date_e'+y+'" onchange=getDateDiff('+y+')	class="form-control d"></div></div><div class="form-group"><label class="col-sm-4 control-label">Remind before days :</label><div class="col-sm-8"><input type="text" name="remind_day['+y+']"	value="" id="remind_day'+y+'"	class="form-control"></div></div><div class="form-group"><label class="col-sm-4 control-label">Vechile Document copy :</label><div class="col-sm-8"><input type="file" name="insurance_card_copy['+y+']" value=""	id="insurance_card_copy" class=""></div></div></div><script>'+$("#insurance_end_date"+y).datepicker()); //add input box
											}
										});

						$(wrapper).on("click", ".remove_field", function(e) { //user click on remove text
							e.preventDefault();
							$(this).parent().parent('div').remove();
							x--;
						})
					});
</script>
<script type="text/javascript">
	function deleteDocument(v_doc_id)
	{
		$.ajax({
			type:"post",
			url:"deleteVehicleDocument.html",
			data:{v_doc_id:v_doc_id},
			success:function()
			{
				
			}
		});
	}
</script>
<script>
function getDateDiff(id)
{
 
	var exp_date=$("#insurance_end_date"+id).val();
	var remind_date=$("#insurance_end_date_e"+id).val();
	var f_date=parseDate(exp_date);
	var s_date=parseDate(remind_date);
	$("#remind_day"+id).val(Math.abs(daydiff(s_date,f_date)));
}
function parseDate(str) {
	var mdy = str.split('-');
    return new Date(mdy[0], mdy[1], mdy[2]);
}

function daydiff(first, second) {
    return Math.round((second-first)/(1000*60*60*24));
}

</script>
<jsp:include page="footer.jsp" />
