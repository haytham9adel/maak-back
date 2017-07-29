   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <c:if test="${!empty cities}">
         <option value="">Select City</option>
         <c:forEach items="${cities}" var="city">
         <option <c:if test="${city.city_id==city_id}"><c:out value="selected"/></c:if> value=<c:out value="${city.city_id}"/>><c:out value="${city.city_name}"/></option>
     	 </c:forEach>
   </c:if>