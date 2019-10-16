<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<body>

	<div class="jumbotron" style="background: url(${logo}) no-repeat center;">
	</div>

	<div class="container" align="center" style="margin-top: 30px">
		<div align="left">
			<a href="${prefix}/">Меню</a>
		</div>
		<h1>Сводка по мерчам</h1>
		<div class="row align-items-center">
			<!--List of merchs  -->
			<div class="col-sm-6">
				<table class="table table-hover table-sm">
					<thead class="table-info">
						<tr>
							<th align="center">ФИО</th>
							<c:forEach var="data" items="${listOfDates}">
								<th align="center">"${data}"</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="merchWithPhoto" items="${listOfMerchsPhoto}">
							<tr>
								<td align="center">${merchWithPhoto.name}</td>
								<c:forEach var="photo" items="${merchWithPhoto.photos}">
									<c:if test="${fn:length(photo.value) > 0}">
										<td class="font-weight-bold" align="center">
											<form action="${prefix}/reports/merch_report/" method="post">
												<input type="hidden" name="_csrf" value="${_csrf.token}" />
												<input type="hidden" name="photos" value="<c:out value="${photo.value}"/>"/>
												<input class="img-fluid" type="image" alt="Photo"
													src="<c:url value="${photo.value[0].pathFoPhoto}"/>"
													value="Кол-во фото: ${fn:length(photo.value)}">
											</form>
										</td>
									</c:if>
									<c:if test="${fn:length(photo.value) == 0}">
										<td align="center">Нет фото</td>
									</c:if>
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>