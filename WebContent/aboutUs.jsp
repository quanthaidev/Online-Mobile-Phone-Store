<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>About</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<link href="https://use.fontawesome.com/releases/v5.0.1/css/all.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>



<link rel="stylesheet" href="css/styleHome.css">

<link rel="icon" href="resource/logo.png">
</head>
<body>
	<!-- Header -->
	<%@ include file="header.jsp"%>

	<div id="pricing" class="container-fluid">
		<div class="text-center">
			<h2>Welcome to FX05474 Shopping Online</h2>
			<p>Please select your fav producrs in 
			<a href="${pageContext.request.contextPath}">Homepage</a> 
			and then Add To Cart</p> <br>
			
			<img src="resource/hello.png" width="10%"/> <br>
			<img src="resource/logo.png" width="30%"/>
		</div>

		
	</div>
	
	<br>


	<!-- Footer -->
	<%@ include file="footer.jsp"%>


</body>
</html>