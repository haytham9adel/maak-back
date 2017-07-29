 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <option value="">Select Parent</option>
 <c:if test="${!empty parents}">
                         	<c:forEach items="${parents}" var="parent">
                         	<option <c:if test="${parent_id==parent.user_id}">
                         	<c:out value="selected"/>
                         	 </c:if>  value=<c:out value="${parent.user_id}"/>><c:out value="${parent.user_name}"/></option>
                         	</c:forEach>
</c:if>