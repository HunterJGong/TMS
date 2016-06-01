<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<link href='https://fonts.googleapis.com/css?family=Alegreya+Sans+SC:400,300,100' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="/app/resources/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="/app/resources/css/public.css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Scrum TMS Login</title>
</head>
<body>
	<div class="video-wrapper">
		<video autoplay loop id="bgvid">
		    <source src="/app/resources/img/bg.mp4" type="video/mp4">
		</video>
	</div>
	
	<div class="body-text">	  
		<c:if test="${errorMessage != null}">
		    <div class="alert alert-danger">${errorMessage}</div>
		</c:if>
		  
		<h1>TMS</h1>
		<br/>
		<form:form action="login" method="POST" commandName="user">
			<form:errors path="username" cssClass="alert alert-danger" element="div"/>
		    <form:input class="form-control" path="username" placeholder="Username" />
		    <br/>
		    <form:password class="form-control" path="password" placeholder="Password"/>
		    <form:errors path="password" cssClass="alert alert-danger" element="div"/>
		    <br/>
		    <input type="submit" class="btn btn-default" value="Login"/>
		</form:form>
		<h2><a href="/app/rest/apis">REST API DOCUMENTATION</a></h2>
	</div>
  
  
</body>
</html>