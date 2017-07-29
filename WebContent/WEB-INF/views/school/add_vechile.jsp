<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
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
		            minDate: new Date(),
		            showOn:'focus',
		            yearRange: "-0:+50",}).focus();
		    	   	
		    });
	});
</script>
<div class="col-md-8 m-side">

             <div class="new-student-form"> 
              <%-- <span class="success">${success}</span>
             <span class="error">${error}</span> --%>
                <form class="form-horizontal" id="vechile" method="post" enctype="multipart/form-data">
                   <div class="form-group">
                     <label class="col-sm-4 control-label">Vehicle Name :</label>
                       <div class="col-sm-8">
                       <input type="hidden" name="school_id" value="<%=session.getAttribute("schoolId") %>">
                         <input type="text" name="vehile_name" value="${vechileBean.vehile_name}" id="vehile_name" class="form-control">
                        </div>
                   </div>
               
                   <div class="form-group">
                     <label class="col-sm-4 control-label">Model :</label>
                       <div class="col-sm-8">
                        <input type="text" name="model" value="${vechileBean.model}" id="model" class="form-control">
                       </div>
                   </div>
                      <div class="form-group">
                     <label class="col-sm-4 control-label">Vechile Number :</label>
                       <div class="col-sm-8">
                          <input type="text" name="bus_number" id="bus_number" value="${vechileBean.bus_number}" class="form-control">
                       </div>
                   </div>
					<div class="form-group">
					  <label class="col-sm-4 control-label">Assign Driver :</label>
					    <div class="col-sm-8">
					    <select class="form-control" id="driver_id" name="driver_id" >
					      <option value="0">Select Driver</option>
					      <c:if test="${!empty drivers}">
					      	<c:forEach items="${drivers}" var="driver">
					      	<option  value=<c:out value="${driver.driver_id}"/>>${driver.driver_fname}${driver.driver_lname}</option>
					      	</c:forEach>
					      </c:if>
						</select>
	                       <span class="error" id="check_route"></span>
	                        <input type="hidden" id="check_route_input"/>
						</div>
					</div>
                	<%-- <div class="form-group">
                     <label class="col-sm-3 control-label">Veichle insurance End date :</label>
                       <div class="col-sm-9">
                        <input type="text" name="insurance_end_date" value="${vechileBean.insurance_end_date}" id="insurance_end_date" class="form-control">
                       </div>
                   </div> --%>
               
			<!--------------------------DO Not Disturb ---------------------------------->
			<div class="input_fields_wrap">
				<div class="panel section_repeat" id="">
					<div class="form-group">
						<label class="col-sm-4 control-label">Document Name :</label>
						<div class="col-sm-8">
							<input type="text" name="insurance_document_name[0]"
								value="${vechileBean.insurance_document_name}" id="insurance_document_name"
								class="form-control">
						</div>
					</div><!-- form-group -->
					<div class="form-group">
						<label class="col-sm-4 control-label">Expiry Date :</label>
						<div class="col-sm-8">
							<input type="text" name="insurance_document_expiry[0]"
								value="${vechileBean.insurance_document_expiry}" id="insurance_end_date0"
								class="form-control d">
						</div>
					</div><!-- form-group -->
						<div class="form-group">
						<label class="col-sm-4 control-label">Remind Date :</label>
						<div class="col-sm-8">
							<input type="text" onchange="getDateDiff(0);" name="insurance_end_date_e[0]" value="${vechileBean.insurance_end_date}" id="insurance_end_date_e0"
								class="form-control d">
						</div>
					</div><!-- form-group -->
					<div class="form-group">
						<label class="col-sm-4 control-label">Remind before days :</label>
						<div class="col-sm-8">
							<input type="text" name="remind_day[0]"
								value="${vechileBean.remind_day}" id="remind_day0"
								class="form-control">
						</div>
					</div><!-- form-group -->
					<div class="form-group">
						<label class="col-sm-4 control-label">Document copy :</label>
						<div class="col-sm-8">
							<input type="file" name="insurance_card_copy[0]" id="insurance_card_copy" class="">
						</div>
					</div><!-- form-group -->
				</div><!-- panel -->
			</div><!-- input_fields_wrap -->
			<!--------------------------DO Not Disturb End---------------------------------->
			<div class="form-group">
				<div class="col-sm-12">

					<button class="add_field_button btn btn-submit" style="float:right;"><i class="fa fa-plus " aria-hidden="true"></i></button>

				</div>
				
			</div>
                    <div class="form-group">
                       <div class="col-sm-9">
                        <input  class="btn btn-primary btn-submit" value="Save" type="submit">&nbsp;&nbsp;
                        <a href="school/manageVehicle">
                        <input  class="btn btn-primary btn-submit" value="Cancel" type="button">
                        </a>
                       </div>
                   </div>
                </form>
             </div> 
             
          </div>
         
        <script src="resources/front/js/jquery.validate.min.js"> </script>
  <script>
  $(document).ready(function(){
	  
	  $('.form-horizontal input[type="text"]').val('');
	  
		$("#vechile").validate({
          rules: {
        	  vehile_name: "required",
        	  manufacture: "required",
        	  model: "required",
        	  year: "required",
        	  color: "required",
        	  configurtion: "required",
        	  engine: "required",
        	  bus_number:"required"
		 },
          messages: {
        	  vehile_name: "Please enter vehicle name",
        	  manufacture: "Please enter manufacture company",
        	  model: "Please enter vehicle model number",
        	  year: "Please enter vehicle year",
        	  color: "Please enter vehicle",
        	  configurtion: "Please enter vehicle configuration",
        	  engine: "Please enter engine number",
        	  bus_number:"Please enter vehicle number"
	      },
          submitHandler: function(form) {
              form.submit();
          }
      });
      

});
  
  </script>
  <c:if test="${!empty success}">
<script>
	swal("Success!\n\n", "${success}", "success");
</script>
</c:if>
<c:if test="${!empty error}">
<script>
	swal("Error!", " ${error} ", "error");
</script>
</c:if>
<script>
	$(document)
			.ready(
					function() {
						var max_fields = 10; //maximum input boxes allowed
						var wrapper = $(".input_fields_wrap"); //Fields wrapper
						var add_button = $(".add_field_button"); //Add button ID
						var y=0;
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
																'<div class="panel section_repeat" id=""><span class="col-sm-12"><a href="#" style="float:right;padding:0 14px 8px 0;color:red;" class="remove_field ">X</a></span><div class="form-group"><label class="col-sm-4 control-label">Document Name :</label><div class="col-sm-8"><input type="text" name="insurance_document_name['+y+']"	value="${vechileBean.insurance_document_name}" id="insurance_end_date" class="form-control"></div></div><div class="form-group"><label class="col-sm-4 control-label">Expiry Date :</label><div class="col-sm-8"><input type="text" name="insurance_document_expiry['+y+']" value="${vechileBean.insurance_document_expiry}" id="insurance_end_date'+y+'"	class="form-control d"></div></div><div class="form-group"><label class="col-sm-4 control-label">Remind Date :</label><div class="col-sm-8"><input type="text" name="insurance_end_date_e['+y+']" value="${vechileBean.insurance_end_date_e}" id="insurance_end_date_e'+y+'" onchange="getDateDiff('+y+')"	class="form-control d"></div></div><div class="form-group"><label class="col-sm-4 control-label">Remind before days :</label><div class="col-sm-8"><input type="text" name="remind_day['+y+']"	value="${vechileBean.remind_day}" id="remind_day'+y+'"	class="form-control"></div></div><div class="form-group"><label class="col-sm-4 control-label">Vechile Document copy :</label><div class="col-sm-8"><input type="file" name="insurance_card_copy['+y+']" value=""	id="insurance_card_copy" class=""></div></div></div><script>'+$("#insurance_end_date"+y).datepicker()+$("#insurance_end_date_e"+y).datepicker()); //add input box
											}
										});

						$(wrapper).on("click", ".remove_field", function(e) { //user click on remove text
							e.preventDefault();
							$(this).parent().parent('div').remove();
							x--;
						})
					});
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