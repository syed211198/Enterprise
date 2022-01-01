<!DOCTYPE html>
<html>
<head>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<meta charset="UTF-8">
<title>Film Detail</title>
</head>
<body>
<p><a href = "http://localhost:8080/Film_Getter/FilmForm.html">Back to Search Bar</a></p>
<c:forEach items="${films}" var="film">
	<h3>Film ID: ${film.getId()}</h3>
	<h3>Title: ${film.getTitle()}</h3>
	<h3>Year Released: ${film.getYear()}</h3>
	<h3>Director: ${film.getDirector()}</h3>
	<h3>Stars: ${film.getStars()}</h3>
	<p>Review: ${film.getReview()}</p>
</c:forEach>

</body>
</html>