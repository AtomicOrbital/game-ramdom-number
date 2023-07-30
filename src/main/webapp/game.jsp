<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Game đoán số</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1 class="text-center">Game Đoán số</h1>
		<div class="row">
			<div class="col-md-6">
				<form action="${pageContext.request.contextPath}/game" method="post">
					<div class="row">
						<div class="col-md-12 mx-auto">
							Đoán số: <input class="form-control" type="number" name="guess"
								required="required"> 
								<label class="mt-2">Nhập họ và tên: </label>
							<c:choose>
								<c:when test="${sessionScope.username != null}">
							${sessionScope.username}
						</c:when>
								<c:otherwise>
									<input class="form-control mt-2" type="text" name="username" />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<button class="btn btn-success mt-3 mb-4" type="submit">Đoán</button>
				</form>
				${sessionScope.message}
			</div>
			<div class="col-md-6">
				<h2>Điểm số cao nhất</h2>
				<table class="table">
					<thead>
						<tr>
							<th>Tên người chơi</th>
							<th>Số lần đoán</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="player" items="${sessionScope.players}">
							<tr>
								<td>${player.name}</td>
								<td>${player.guesses}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>
