   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <c:if test="${!empty states}">
   		<option value="">Select State</option>
         <c:forEach items="${states}" var="state">
         
         <option value=<c:out value="${state.state_id}"/>><c:out value="${state.state_name}"/></option>
     	 </c:forEach>
   </c:if>