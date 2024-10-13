<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Product Info</title>
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
			<h2>${requestScope.productSelected.getName()}</h2>
		</div>

		<div class="row slideanim" style="visibility: visible;">
			<div class='col-sm-6 col-xs-6'>
				<img style="width: 60%; height: auto; float:right;"
					src="${requestScope.productSelected.getSrc()}">
			</div>
			<div class='col-sm-6 col-xs-6'>
				<h1 style="color: red;">${requestScope.productSelected.getPrice()}</h1>

				<p style="width: 60%;"><strong>Product Description: </strong> 
					${requestScope.productSelected.getDescription()}
				</p>

				<a href="${pageContext.request.contextPath}/AddToCartController?idProduct=${requestScope.productSelected.getId()}&action=info">
					<button class="btn btn-lg" onclick="myFunction()"
					style="background-color: #f4511e; color: white;">Add To Cart</button>
				</a>
					<script>
						function myFunction() {
						  alert("Đã thêm vào giỏ hàng!");
						}
					</script>
			</div>

		</div>



	</div>


	<!-- Footer -->
	<%@ include file="footer.jsp"%>


</body>
</html>