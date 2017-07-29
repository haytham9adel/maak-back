   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <c:choose>
   
   <c:when  test="${!empty students}">
         <option value="">Select Student</option>
         <c:forEach items="${students}" var="student">
        
         <option value=<c:out value="${student.student_id}"/>><c:out value="${student.student_name}"/></option>
     	 </c:forEach>
   </c:when >
   <c:otherwise>
 	<option value=""><c:out value="No Student Available"/></option>
   </c:otherwise>
   
   </c:choose>