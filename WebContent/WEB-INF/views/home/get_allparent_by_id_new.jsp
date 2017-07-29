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

<% int i=1; %>
               
	<c:forEach items="${allParent}" var="parent">
	
		<div class="info-block">
			<h4>
				<b>Parent - <%=i %></b>
			</h4>
			<div class="info-group">
				<div class="col-a">Name :</div>
				<div class="col-b">${parent.first_name} ${parent.middle_name} ${parent.family_name} </div>
			</div>
		 <div class="info-group">
				<div class="col-a">Email :</div>
				<div class="col-b">${parent.user_email}</div>
		 </div>
		 <div class="info-group">
				<div class="col-a">Contact No :</div>
				<div class="col-b">${parent.contact_number}</div>
		 </div>
				<%--<div class="info-group">
				<div class="col-a">Contact Number :</div>
				<div class="col-b">${parent.contact_number}</div>
			</div> --%>
		</div>
	<%-- 
		<div class="parent-child col-sm-12 pad0">
			<label class="col-sm-4 pad0">${parent.first_name} ${parent.last_name}</label>
			<input type="hidden" value="${parent.user_id}" name="parent_list">
			<div class="form-group col-sm-8 pad0">
			 <select name="relationship_details" class="form-control">
				<option value="">Select</option>
				<c:forEach items="${relationship}" var="row_1">
				<option value="${row_1.r_title}">${row_1.r_title}</option>
				</c:forEach> 
			 </select>
			</div>
		</div> --%>
	<% i++; %>	
 </c:forEach> 
   