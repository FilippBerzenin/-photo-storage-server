<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/dopstyle.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
	<div class="jumbotron" style="background: url(${logo}) no-repeat center;">
	</div>
<a href="${prefix}/">Back</a> <br />
	<h1>Error</h1>
		<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>
		</c:if>
</body>
</html>