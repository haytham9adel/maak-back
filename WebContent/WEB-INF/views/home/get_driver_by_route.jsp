   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <c:choose>
   <c:when test="${!empty drivers}">

         <option value="">Select Driver</option>
         <c:forEach items="${drivers}" var="driver">
         <option <c:if test="${driver.driver_id==driver_id}"><c:out value="selected"/></c:if> value=<c:out value="${driver.driver_id}"/>><c:out value="${driver.driver_fname} ${driver.driver_lname}"/></option>
     	 </c:forEach>
  
    </c:when> 
     <c:otherwise>
       <option value="">No Driver Available</option>
     	
    </c:otherwise>   
   </c:choose>