<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="org.apache.commons.codec.binary.Base64"%>

<c:choose>
	<c:when test="${!empty holidays}">

		<div class="info-block">
			<h4>Holiday</h4>
		</div>
		<!-- info-block -->
		<c:forEach items="${holidays}" var="holiday">
			<div class="info-block holiday_det">
				<div class="info-group">
					<div class="col-a">${holiday.holiday_name}  </div>
					<div class="col-b">${holiday.holiday_date }</div>
				<!-- 	<c:if test="${holiday.school_id!=0}">  -->
						<div class="edit_holiday"> 
						<c:set var="h_id" value="${holiday.h_id}"/>
<%
String str=pageContext.getAttribute("h_id").toString();
byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
%>
		<%  if (session.getAttribute("userRole")=="manager") { %>
							<a href="school/editHoliday?q=<%=new String(encodedBytes)%>"><i
								class="fa fa-pencil-square-o fa-3x edit"></i></a>
							<% }else { %>

							<%  if (session.getAttribute("new_school_id")==null)
			{ %>

							<a href="admin/editAdminHoliday?q=<%=new String(encodedBytes)%>"><i
								class="fa fa-pencil-square-o fa-3x edit"></i></a>
							<% }else{%>

							<a href="admin/editHoliday?q=<%=new String(encodedBytes)%>"><i
								class="fa fa-pencil-square-o fa-3x edit"></i></a>
							<% }%>

							<% }%>

						</div>
				<!-- 	</c:if> -->
				</div>
			</div>
		</c:forEach>


	</c:when>
	<c:otherwise>
		<div class="info-block">
			<h4>Holiday</h4>
		</div>
		<!-- info-block -->

		<div class="info-block holiday_det">
			<div class="info-group">
				<div class="col-a">No Holiday Available</div>
			</div>
		</div>

	</c:otherwise>
</c:choose>
