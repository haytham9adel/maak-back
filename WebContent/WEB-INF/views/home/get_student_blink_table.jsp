<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="resources.Assets" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="count" value="1" scope="page" />
			  <c:forEach items="${all_students}" var="student">    
	<tr>
		<td><c:out value="${count}"/></td>
	<td>${student.s_fname}&nbsp;${student.father_name}&nbsp;${student.grand_name}&nbsp;${student.family_name}</td>
	<td>${student.s_contact}</td>
	<td>${route.route_name}</td>
	<td><c:if test="${student.blink_status==0}">
		Checked-out
	</c:if>
	<c:if test="${student.blink_status==1}">
				Checked-in
			</c:if>
			 </td>
		</tr>
		<c:set var="count" value="${count + 1}" scope="page"/>
	</c:forEach>