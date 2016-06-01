<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<tiles:insertAttribute name="header" ignore="true" />
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>

<body>
	<tiles:insertAttribute name="sidebar" ignore="true" />
	<tiles:insertAttribute name="navbar" ignore="true" />
	
	<div class="container-fluid">
		<tiles:insertAttribute name="body" ignore="true" />
	</div>

	<tiles:insertAttribute name="footer" ignore="true" />
</body>
</html>