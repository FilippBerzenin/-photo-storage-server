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
<link rel="stylesheet" href="css/dopstyle.css">
<c:set var="prefix" value="${pageContext.request.contextPath}" />
<c:set var="page" value="${page}" />
</head>
<title>Object page</title>
</head>
<body>

	<div class="jumbotron" style="background: url(${logo}) no-repeat center;">
	</div>
	
	<div class="container" align="center" style="margin-top: 30px">
		<div align="left">
			<a href="${prefix}/">Меню</a>
		</div>
		<h1>Создание и отображение объекта</h1>
		<div class="row align-items-center">
			<div class="col-sm-6">
				<h2>Список объектов:</h2>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>#</th>
							<!-- <th>ID</th> -->
							<th>Object name</th>
							<!-- <th>Objects photo</th> -->
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="object_place" items="${listOfEntites}"
							varStatus="counter">
							<tr>
								<td>${counter.count}</td>
								<%-- <td>${object_place.id}</td> --%>
								<td>${object_place.name}</td>
								<%-- <td>${object_place.photos}</td> --%>
								<td>
									<form action="${prefix}/object_place/delete/${object_place.id}"
										method="post">
										<input type="hidden" name="_csrf" value="${_csrf.token}" />
										<input type="submit" value="delete"
											onclick="if (confirm('Are you sure you want to delete ${object_place.name}?')) form.action='${prefix}/object_place/delete/${object_place.id}'; else return false;" />
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-sm-6">
				<div>
					<div class="form-group">
						<form:form method="post" action="${prefix}/object_place/create/"
							modelAttribute="entityFor">
							<input type="hidden" name="_csrf" value="${_csrf.token}" />
							<table>
								<tr>
									<td><font color="red"><form:errors path="name" /></font></td>
									<td><form:input path="name" placeholder="object(place)" /></td>
									<td><button type="submit">Добавить</button></td>
								</tr>
							</table>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>