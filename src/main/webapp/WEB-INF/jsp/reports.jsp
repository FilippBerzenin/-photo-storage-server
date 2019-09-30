<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<c:set var="prefix" value="${pageContext.request.contextPath}" />
<c:set var="page" value="${page}" />
</head>
<body>

	<div class="jumbotron text-center" style="margin-bottom: 0">
		<h1>Logo</h1>
	</div>

	<div class="container" align="center" style="margin-top: 30px">
		<div align="left">
			<a href="${prefix}/">Меню</a>
		</div>
		<div class="align-items-center">
			<h3>Формирование сводки</h3>
			<c:if test="${not empty message}">
				<br>
				<div class="alert alert-success">${message}</div>
			</c:if>
			<br>
			<div>
				<form:form action="${prefix}/reports/create_request_week"
					modelAttribute="requestFor" method="post">
					<input type="hidden" name="_csrf" value="${_csrf.token}" />
					<div class="row col-sm-6">
						<div class="col-sm-8">
							<form:input class="form-control" type="date"
								path="dateStartSearch" />
						</div>
						<div class="col-sm-4">
							<form:select path="typeReqest">
								<form:options items="${objectTypes}" itemValue="value" />
							</form:select>
						</div>
					</div>
					<div class="row align-items-center" style="margin-top: 10px">
						<div class="col-sm">
							<input type="submit" value="Сформировать сводку" />
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div>
			<c:if test="${not empty listOfMerchsPhoto}">
				<h1>Сводка по мерчам</h1>
				<div class="row align-items-left">
				<div class="col-sm-2">
 					<form:form action="${prefix}/reports/create_request_week__change_day"
					 method="get" modelAttribute="changedaystartreport">
						<input type="hidden" name="_csrf" value="${_csrf.token}" />
						<form:hidden path="date" value="${listOfDates[0]}"/>
						<form:hidden path="typeReqest" value="${type_request}"/>
						<form:hidden path="changeDay" value="minus"/>
						<input type="submit" value="На день назад" />
					</form:form>
				</div>
				<div class="col-sm-2">
 					<form:form action="${prefix}/reports/create_request_week__change_day"
					 method="get" modelAttribute="changedaystartreport">
						<input type="hidden" name="_csrf" value="${_csrf.token}" />
						<form:hidden path="date" value="${listOfDates[0]}"/>
						<form:hidden path="typeReqest" value="${type_request}"/>
						<form:hidden path="changeDay" value="plus"/>
						<input type="submit" value="На день вперед" />
					</form:form>
				</div>
				</div>
				<div class="row">
					<!--List of merchs  -->
					<div class="col-sm-12">
						<table class="table table-striped">
							<thead class="table-info" >
								<tr >
									<th align="center">ФИО</th>
									<c:forEach var="data" items="${listOfDates}">
										<th nowrap="nowrap" align="center">"${data}"</th>
									</c:forEach>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="merchWithPhoto" items="${listOfMerchsPhoto}">
									<tr>
										<td  align="center">${merchWithPhoto.name}</td>
										<c:forEach var="photosByObjects" items="${merchWithPhoto.photos}">
											<td>
												<c:forEach var="photos" items="${photosByObjects.value}">
												<p class="my-1">ID: 
												${photosByObjects.key}/${merchWithPhoto.merch_id+photos.key.id+fn:length(photos.value)},
												 time:${photos.value[0].time} </p>
												<p class="my-1">${photos.key.name}</p>
												<div>
													<form action="${prefix}/reports/merch_report/"
														method="post">
														<input type="hidden" name="_csrf" value="${_csrf.token}" />
														<input type="hidden" name="photos_list" value="<c:out value="${photos.value}"/>" /> 
														<input class="img-fluid" type="image" alt="Photo" src="<c:url value="${photos.value[0].pathFoPhoto}"/>"
															value="Кол-во фото: ${fn:length(photos.value)}">
													</form>
												</div>
												</c:forEach>
											</td>
										</c:forEach>
								</c:forEach>
								</tbody>
						</table>
					</div>
				</div>
			</c:if>
		</div>
		
				<div>
			<c:if test="${not empty listOfShopsPhoto}">
				<h1>Сводка по магазинам</h1>
				<div class="row align-items-left">
				<div class="col-sm-2">
 					<form:form action="${prefix}/reports/create_request_week__change_day"
					 method="get" modelAttribute="changedaystartreport">
						<input type="hidden" name="_csrf" value="${_csrf.token}" />
						<form:hidden path="date" value="${listOfDates[0]}"/>
						<form:hidden path="typeReqest" value="${type_request}"/>
						<form:hidden path="changeDay" value="minus"/>
						<input type="submit" value="На день назад" />
					</form:form>
				</div>
				<div class="col-sm-2">
 					<form:form action="${prefix}/reports/create_request_week__change_day"
					 method="get" modelAttribute="changedaystartreport">
						<input type="hidden" name="_csrf" value="${_csrf.token}" />
						<form:hidden path="date" value="${listOfDates[0]}"/>
						<form:hidden path="typeReqest" value="${type_request}"/>
						<form:hidden path="changeDay" value="plus"/>
						<input type="submit" value="На день вперед" />
					</form:form>
				</div>
				<div class="col-sm-2">
				</div>
				</div>
				<div class="row">
					<!--List of merchs  -->
					<div class="col-sm-6">
						<table class="table table-hover table-sm">
							<thead class="table-info">
								<tr>
									<th align="center">Название объекта</th>
									<c:forEach var="data" items="${listOfDates}">
										<th align="center">"${data}"</th>
									</c:forEach>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="shopsWithPhoto" items="${listOfShopsPhoto}">
									<tr>
										<td  align="center">${shopsWithPhoto.name}</td>
										<c:forEach var="photosByMerch" items="${shopsWithPhoto.photos}">
											<td>
												<c:forEach var="photos" items="${photosByMerch.value}">
												<p class="my-1">ID: 
												${shopsWithPhoto.shop_id+photos.key.id+fn:length(photos.value)},
												 time:${photos.value[0].time} </p>
												<p class="my-1">${photos.key.name}</p>
												<div>
													<form action="${prefix}/reports/shop_report/"
														method="post">
														<input type="hidden" name="_csrf" value="${_csrf.token}" />
														<input type="hidden" name="photos_list" value="<c:out value="${photos.value}"/>" /> 
														<input class="img-fluid" type="image" alt="Photo" src="<c:url value="${photos.value[0].pathFoPhoto}"/>"
															value="Кол-во фото: ${fn:length(photos.value)}">
													</form>
												</div>
												</c:forEach>
											</td>
										</c:forEach>
								</c:forEach>
								</tbody>
						</table>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>