scoreboard<%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board game</title>
</head>
<body>
	<table>
		<tr>
			<th>Name</th>
			<th>Guesses</th>
		</tr>
		<c:forEach var="player" items="${players}">
			<tr>
				<td>${player.name}</td>
				<td>${player.guesses}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>