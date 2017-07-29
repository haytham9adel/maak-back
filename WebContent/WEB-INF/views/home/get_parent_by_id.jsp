   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

          <script>
$(document).ready(function(){

  	var parent_id=${parent.p_status};
  	  $.ajax({
          url : 'getAllParentByPId.html',
          type:'post',
          data:{"p_status":parent_id,"school_id":${student_id}},
          success : function(data) {
            $("#new").html(data);
          }
      });
 		  return false;

});

$(document).ready(function(){

  	var parent_id=${parent.p_status};
  	  $.ajax({
          url : 'getAllParentByPIdNew.html',
          type:'post',
          data:{"p_status":parent_id},
          success : function(data) {
            $("#parent_right_details").html(data);
          }
      });
 		  return false;

});
 </script>
 <style>
   .parent_pro_new {
    
}
   
   </style>
   <label class="col-sm-3 control-label">Parents :</label>
   					<div class="parent_new">
                       <div class="col-sm-9 parent_pro_new" id="new">
                      	  <%-- <input type="text" id="new" value="${parent.first_name} ${parent.last_name}"  class="form-control">  --%>
    					</div>
    			    </div>
	<input type="hidden" name="p_status_id" value="${parent.p_status}"  id="p_status_id" class="form-control">

   
   