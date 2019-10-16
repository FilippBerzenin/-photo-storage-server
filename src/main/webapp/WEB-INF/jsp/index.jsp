<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style>
.fakeimg {
	height: 200px;
	background: #aaa;
}
</style>
<c:set var="prefix" value="${pageContext.request.contextPath}" />
<c:set var="page" value="${page}" />
</head>
<body>

	<div class="jumbotron text-center" style="margin-bottom: 0">
		<img src="${logo}" alt="Logo">
	</div>

	<div class="container" align="center" style="margin-top: 30px">
		<div class="row align-items-center">
			<div class="col-sm-4">
				<form action="${prefix}/object_place/show/all" method="GET">
				<input type="hidden" name="_csrf" value="${_csrf.token}" />
					<button type="submit" class="btn btn-outline-primary btn-lg">Добавить объект</button>
				</form>
			</div>
			<div class="col-sm-4">
				<form action="${prefix}/merch/show/all" method="GET">
				<input type="hidden" name="_csrf" value="${_csrf.token}" />
					<button type="submit" class="btn btn-outline-primary btn-lg">Добавить мерча</button>
				</form>
			</div>
			<div class="col-sm-4">
				<form action="${prefix}/reports" method="GET">
				<input type="hidden" name="_csrf" value="${_csrf.token}" />
					<button type="submit" class="btn btn-outline-primary btn-lg">Показать сводку</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>