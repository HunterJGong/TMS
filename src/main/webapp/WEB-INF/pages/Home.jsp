<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
<span class="pageTitle"><h2>Projects</h2></span>
</div>
<c:if test="${user.role.id==1 && not empty myPro}">
	<div class="trendy-box col-xs-2 col-sm-2 col-md-3 col-lg-4 text-center" onclick="nav('project',${myPro.id}, ${user.role.id})">
		<span class="trendyBoxTitle"><h4>PROJECT : ${myPro.name}</h4></span>
		
		<div id="start${myPro.start}">Start date: <fmt:formatDate type="date" dateStyle="medium" value="${myPro.start}"/></div>
		<div id="endPro${myPro.endProjected}">Est. end date: <fmt:formatDate type="date" dateStyle="medium" value="${myPro.endProjected}"/></div>
		<div id="endAct${myPro.endActual}">
			End date: 
			<c:if test="${empty pro.endActual }">In progress</c:if>
			<fmt:formatDate type="date" dateStyle="medium" value="${myPro.endActual}"/>
		</div>
	</div>
</c:if>

<c:if test="${user.role.id==2}">
	<div class="task-box">
		<form action="${pageContext.request.contextPath}/manager/createProject" method="POST">
			<label>Submit a New Project</label>
			<c:forEach items="${errors}" var="error">
				<br/><span class="err-msg">${error}</span>
			</c:forEach>
			<input type="text" name="name" class="form-control" placeholder="Name"/>
			Projected End Date
			<input type="date" name="date" class="form-control" placeholder="End date"/>
			<input type="submit" class="btn btn-default task-submit"/>
		</form>
	</div>
	
	<div class="row">
		<c:forEach items="${projects}" var="pro">
			<div class="trendy-box col-xs-2 col-sm-2 col-md-3 col-lg-4 text-center" onclick="nav('project',${pro.id}, ${user.role.id})">
				<span class="trendyBoxTitle"><h4>PROJECT : ${pro.name}</h4></span>
				<div id="start${pro.start}">Start date: <fmt:formatDate type="date" dateStyle="medium" value="${pro.start}"/></div>
				<div id="endPro${pro.endProjected}">Est. end date: <fmt:formatDate type="date" dateStyle="medium" value="${pro.endProjected}"/></div>
				<div id="endAct${pro.endActual}">
					End date: 
					<c:if test="${empty pro.endActual }">In progress</c:if>
					<fmt:formatDate type="date" dateStyle="medium" value="${pro.endActual}"/>
				</div>
			</div>
		</c:forEach>
	</div>
</c:if>




