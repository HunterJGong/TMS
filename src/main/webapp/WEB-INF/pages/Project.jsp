<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<span class="pageTitle"><h2>Project Details: ${project.name}</h2></span>
<h3>Start: <fmt:formatDate type="date" dateStyle="medium" value="${project.start}"/></h3>
<h3>Projected end: <fmt:formatDate type="date" dateStyle="medium" value="${project.endProjected}"/></h3>
<h3>Actual end date: <fmt:formatDate type="date" dateStyle="medium" value="${project.endActual}"/></h3>

<c:if test="${user.role.id==1}">
	<h3>Teammates</h3>
	<h3>
	<c:forEach items="${users}" var="bob">
		<c:if test="${bob.id != user.id}">${bob.username}/</c:if>
	</c:forEach>
	</h3>
</c:if>

<c:if test="${user.role.id==2}">
	<span class="trendyBoxTitle"><h3>Developers</h3></span>
	<h3>
		<c:forEach items="${users}" var="bob">
			${bob.username}/ 
		</c:forEach>
	</h3>
	<div class="task-box">
		<form action="/TMS/manager/project/createSprint" method="POST">
			<label>Create a New Sprint</label>
			<input type="submit" class="btn btn-default task-submit"/>
		</form>
	</div>
	<div class="task-box">
		<form action="/TMS/manager/project/assignDeveloper" method="POST">
			<label>Assign a Developer</label>
			<select name="dev">
				<c:forEach items="${devs}" var="bob">
					<option value="${bob.id}">${bob.username}</option>
				</c:forEach>
			</select>		
			<input type="submit" class="btn btn-default task-submit"/>
		</form>
	</div>
</c:if>

<div class="row">
	<c:forEach items="${sprints}" var="spr">
		<div class="trendy-box col-xs-2 col-sm-2 col-md-3 col-lg-4 text-center" onclick="nav('sprint',${spr.id},${user.role.id})">
			<span class="trendyBoxTitle"><h4>SPRINT : ${spr.id}</h4></span>
			
			<div id="start${spr.start}">Start date: <fmt:formatDate type="date" dateStyle="medium" value="${spr.start}"/></div>
			<div id="end${spr.end}">
				End date: 
				<c:if test="${empty spr.end }">In progress</c:if>
				<fmt:formatDate type="date" dateStyle="medium" value="${spr.end}"/>
			</div>
		</div>
	</c:forEach>
</div>
