<!-- Header -->
<jsp:include page="header.jsp" />

<!-- JSTL includes -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<header>
	<div class="container">

<c:choose>
<c:when test="${not empty message }">
	<p class="alert ${messageClass}">${message}</p>
<%
	session.setAttribute("message", null);
	session.setAttribute("messageClass", null);
%>
</c:when>
</c:choose>	
	
	<h1>eZoo <small>Feeding Schedules</small></h1>
	<hr class="paw-primary">
	<table class="table table-striped table-hover table-responsive ezoo-datatable">
		<thead>
			<tr>
				<th class="text-center">Feeding Schedule ID</th>
				<th class="text-center">Feeding Time</th>
				<th class="text-center">Recurrence</th>
				<th class="text-center">Food</th>
				<th class="text-center">Notes</th>	
				<th class="text-center">Animal</th>		
			</tr>
		</thead>
		<tbody>
			<c:forEach var="f" items="${feedingSchedules}">
				<tr>
					<td><button>Delete</button><c:out value="${f.schedule_id}" /></td>
					<td><c:out value="${f.feeding_time}" /></td>
					<td><c:out value="${f.recurrence}" /></td>
					<td><c:out value="${f.food}" /></td>
					<td><c:out value="${f.notes}" /></td>
					<td><c:out value="${f.animalid}" /></td>
										
				</tr>
				
			</c:forEach>
		</tbody>
	</table>
	
	</div>
</header>


<jsp:include page="footer.jsp" />

