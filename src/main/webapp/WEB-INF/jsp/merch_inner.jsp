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
<c:set var="merch" value="${entity}" />
</head>
<body>

	<div class="jumbotron" style="background: url(${logo}) no-repeat center;">
	</div>

	<div class="container" align="center" style="margin-top: 30px">
		<div align="left">
			<a href="${prefix}/">Меню</a>
		</div>
		<div class="row align-items-center">
			<!--Add new merchs  -->
			<div class="col-sm-6">
				<div class="form-group">
					<form:form method="post" action="${prefix}/${page}/update/"
						modelAttribute="entity">
						<input type="hidden" name="_csrf" value="${_csrf.token}" />
						<table>
							<tr>
								<td><h4 align="left">Id: ${merch.id}</h4></td>
							</tr>
							<tr>
								<td><form:input path="name" placeholder="${merch.name}" style="margin-top: 5px"/></td>
								<form:input type="hidden" path="id" placeholder="${merch.id}" />
								<td><font color="red"><form:errors path="name" /></font></td>
							</tr>
							<tr>
								<td><form:input path="login" placeholder="${merch.login}" style="margin-top: 5px"/></td>
								<td><font color="red"><form:errors path="login" /></font></td>
							</tr>
							<tr>
								<td><form:input path="pass" placeholder="${merch.pass}" style="margin-top: 5px"/></td>
								<td><font color="red"><form:errors path="pass" /></font></td>
							</tr>
							<tr>
								<td><button type="submit" style="margin-top: 5px">Сохранить</button></td>
							</tr>
						</table>
					</form:form>
				</div>
			</div>
		<!--List of object  -->
		<div class="col-sm-6">
			<div>
				<form action="${prefix}/merch_inner/addObject/${merch.id}"
					method="post">
					<input type="hidden" name="_csrf" value="${_csrf.token}" />
					Select a Category:&nbsp; <select name="object">
						<c:forEach items="${objectsList}" var="object">
							<option value="${object.id}">${object.name}</option>
						</c:forEach>
					</select> <br />
					<br /> <input type="submit" value="Добавить" />
				</form>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Список объектов</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="objectPlace" items="${merch.objectPlace}">
						<tr>
							<td>${objectPlace.name}</td>
							<td>
								<form action="${prefix}/${page}/removeObject/${merch.id}/${objectPlace.id}"
									method="post">
									<input type="hidden" name="_csrf" value="${_csrf.token}" />
									<input type="submit" value="delete"
										onclick="if (confirm('Are you sure you want to delete ${objectPlace.name} from ${merch.name}?')) form.action='${prefix}/${page}/removeObject/${merch.id}/${objectPlace.id}'; else return false;" />
								</form>
							<td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</div>
	</div>
</body>
</html>