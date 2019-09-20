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
<c:set var="merch" value="${merch}" />
</head>
<body>

	<div class="jumbotron text-center" style="margin-bottom: 0">
		<h1>Logo</h1>
	</div>

	<div class="container" align="center" style="margin-top: 30px">
		<div class="row align-items-center">
			<!--Add new merchs  -->
			<div class="col-sm-6">
				<div class="form-group"">
					<form:form method="post" action="${prefix}/${page}/update/"
						modelAttribute="new_merch">
						<table>
							<tr>
								<td><h4 align="left">Id: ${merch.id}</h4></td>
							</tr>
							<tr>
								<td><form:input path="name" placeholder="${merch.name}" /></td>
								<td><font color="red"><form:errors path="name" /></font></td>
							</tr>
							<tr>
								<td><form:input path="login" placeholder="${merch.login}" /></td>
								<td><font color="red"><form:errors path="login" /></font></td>
							</tr>
							<tr>
								<td><form:input path="pass" placeholder="${merch.pass}" /></td>
								<td><font color="red"><form:errors path="pass" /></font></td>
							</tr>
							<tr>
								<td><button type="submit">Сохранить</button></td>
							</tr>
						</table>
					</form:form>
				</div>
			</div>
		</div>
		<!--List of object  -->
		<div class="col-sm-6">
		<div>
			<form action="${prefix}/merch/addObject/${merch.id}" method="post">
				Select a Category:&nbsp; 
				<select name="object">
					<c:forEach items="${objectsList}" var="object">
						<option value="${object.id}"
							<c:if test="${object.id eq selectedCatId}">selected="selected"</c:if>>
							${object.name}</option>
					</c:forEach>
				</select> <br />
				<br /> <input type="submit" value="Submit" />
			</form>		
		</div>
			<table class="table  table-sm">
				<thead class="table-info">
					<tr>
						<th>Список объектов</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="objectPlace" items="${merch.objectPlace}">
						<tr>
							<td>${objectPlace.name}</td>
							<%-- 						<td>
							<form action="${prefix}/${page}/update/${merch.id}"
								method="post">
								<button type="submit" name="update" value="update">Update</button>
							</form>	
						</td> --%>
							<td>
								<form action="${prefix}/${page}/delete/${merch.id}"
									method="post">
									<input type="submit" value="delete"
										onclick="if (confirm('Are you sure you want to delete ${merch.name}?')) form.action='${prefix}/${page}/delete/${merch.id}'; else return false;" />
									<!-- <button type="submit" name="delete" value="Delete">Delete</button> -->
								</form>
							<td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>