<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="navbar">
	<div class="nav-left">
		<c:if test="${breadCrumbs.size() >0 }">
		history:
			<c:forEach items="${ breadCrumbs }" var="crumb">
				<a href="${crumb }">
				<c:if test="${crumb.contains('/app/')}">
					<c:set var= "urlTrimed" value="${ crumb.replace('/app/','')}"></c:set>
				</c:if>
				<c:if test="${crumb.contains('/app/developer/')}">
					<c:set var= "urlTrimed" value="${ crumb.replace('/app/developer/','')}"></c:set>
				</c:if>
				<c:if test="${crumb.contains('/app/manager/')}">
					<c:set var= "urlTrimed" value="${ crumb.replace('/app/manager/','')}"></c:set>
				</c:if>
				
					<c:out value="${urlTrimed}/"></c:out></a>
			</c:forEach>
		</c:if>
	</div>
	<div class="nav-right" id="navbar-body" data-uid="${user.id}">	</div>
</div>
