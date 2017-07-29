 <style>
 	.parent_hidden {
    border: 1px solid #ccc;
    padding: 1px;
    overflow: hidden;
}
.parent_hidden p {
    padding: 7px;
    float: left;
    background-color: #efefef;
    margin: 4px;
    border-radius: 10px;
        cursor: no-drop;
}
 </style>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


               
                       <%-- <div class="parent_hidden">
                          <c:forEach items="${allParent}" var="parent">
                          <p>${parent.first_name} ${parent.last_name}</p>
                           </c:forEach> 
                      </div> --%>
	<c:forEach items="${allParent}" var="parent">
		<div class="parent-child col-sm-12 pad0">
			<label class="col-sm-4 pad0">${parent.first_name} ${parent.last_name}</label>
			<input type="hidden" value="${parent.user_id}" name="parent_list">
			<div class="form-group col-sm-8 pad0">
			 <select name="relationship_details" class="form-control">
				<option value="">Select</option>
				<c:forEach items="${relationship}" var="row_1">
				<option
						<c:choose>
						
						<c:when test="${student_details.r_1==row_1.r_title}">
							<c:if test="${parent.user_id==student_details.p_1}">
							 selected="selected"
						    </c:if>
						</c:when>
						
						
						<c:when test="${student_details.r_2==row_1.r_title}">
							<c:if test="${parent.user_id==student_details.p_2}">
							   selected="selected"
							</c:if>
						  </c:when>
						
						
						
						<c:when test="${student_details.r_3==row_1.r_title}">
							<c:if test="${parent.user_id==student_details.p_3}">
							   selected="selected"
					        </c:if>
						  </c:when>  
				        
						  <c:otherwise>
						    
						  </c:otherwise>
						</c:choose>
				
				
				 value="${row_1.r_title}">${row_1.r_title}</option>
				</c:forEach> 
			 </select>
			</div>
		</div>
		
 </c:forEach> 
   