<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/TMS/resources/css/bootstrap.min.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TMS Rest API Doc</title>
</head>
<body>
<div class="container">
	<div class="row">
		<h1>Available APIs</h1>

		<table class="table">
			<thead>
				<tr>
					<td>Type</td>
					<td>URL</td>
				</tr>
				
			</thead>
			<tbody>
				<tr>
					<td>Total Projects</td>
					<td>http://localhost:8085/TMS/rest/tmsStats/totalProjects</td>
				</tr>
				<tr>
					<td>Open Projects</td>
					<td>http://localhost:8085/TMS/rest/tmsStats/openProjects</td>
				</tr>
				<tr>
					<td>Completed Projects</td>
					<td>http://localhost:8085/TMS/rest/tmsStats/completedProjects</td>
				</tr>
			</tbody>
		</table>
	<a href="/TMS/login">Back to Login</a>
	</div>
	

</div>
</body>
</html>