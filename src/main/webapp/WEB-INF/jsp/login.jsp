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
		<h1>Logo</h1>
	</div>
	
	<div class="container login-container" align="center">
		<div class="col-md-6 login-form-1">
			<h3>Login</h3>
			<form action="/login" method="post">
				<c:if test="${param.error != null}">
					<div class="alert alert-warning">Пароль или логин неправильные</div>
				</c:if>
				<div class="form-group">
					<input type="text" class="form-control" name="username" value="" />
				</div>
				<div class="form-group">
					<input type="password" class="form-control" name="password"	value="" />
				</div>
				<div>
					<input type="hidden" name="_csrf" value="${_csrf.token}" />
				</div>
			<div class="form-group">
				<input type="submit" class="btnSubmit" value="Войти" />
			</div>
			</form>
		</div>
	</div>
</body>
</html>