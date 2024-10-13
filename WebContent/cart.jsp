<%@page import="java.util.*"%>
<%@page import="controller.*"%>
<%@page import="model.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>Cart</title>
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

<style>
table, th, td {
	border: 1px solid black;
	border-collapse: separate;
	border-spacing: opx;
}

th, td {
	text-align: center;
}
</style>


<body>
	<!-- Header -->
	<%@ include file="header.jsp"%>

	<div id="pricing" class="container-fluid">
		<div class="text-center">
			<c:if test="${requestScope.totalAmount == 0 || requestScope.totalAmount == null  }">
				<h2> Giỏ hàng trống, vui lòng thêm sản phẩm </h2>
				
				<img src="https://cdni.iconscout.com/illustration/premium/thumb/empty-cart-7359557-6024626.png"
			width="17%"/>
			
			
			</c:if>
			<c:if test="${requestScope.totalAmount > 0 }">
				<h2>Giỏ hàng (${sessionScope.totalItems})</h2>
			
			</c:if>
			
		</div>





		<!-- 		Nếu giỏ hàng không trống, thì sẽ hiện table và form -->
		<c:if test="${requestScope.totalAmount > 0 }">

			<table style="width: 100%">
				<tr>
					<th style="width: 35%">Tên sản phẩm</th>
					<!-- 		    <th style="width:2%" ></th> -->
					<th style="width: 10%" colspan="3">Số lượng</th>
					<!-- 		    <th style="width:2%" ></th> -->
					<th style="width: 20%">Đơn giá</th>
					<th>Tổng</th>
				</tr>

				<c:set var="productMap" value="${sessionScope.cartMap}"></c:set>

				<c:forEach var="items" items="${requestScope.cartProductsArray}">

					<c:set var="number" value="${items.getNumber()}"></c:set>
					<c:set var="price" value="${items.getProduct().getPrice()}"></c:set>



					<tr>
						<td>${items.getProduct().getName()}</td>
						<td><a
							href="${pageContext.request.contextPath}/AddToCartController?idProduct=${items.getProduct().getId()}&action=add&cart=true">
								<button type="button"
									style="background-color: #DC143C; color: white; font-size: 12px; font-weight: bold">
									+</button>
						</a></td>
						<td style="border-left: none; border-right: none;">${number}</td>
						<td><a
							href="${pageContext.request.contextPath}/AddToCartController?idProduct=${items.getProduct().getId()}&action=subtract&cart=true">
								<button type="button"
									style="background-color: #1E90FF; color: white; font-size: 12px; font-weight: bold;">
									-</button>
						</a></td>
						<td>${price}</td>
						<td>${items.getTotal()}</td>
					</tr>



				</c:forEach>
				<tr>
					<td colspan="5">Tổng tiền</td>
					<td style="color: red; font-weight: bold;">$
						${requestScope.totalAmount}</td>
				</tr>


			</table>



			<br>

			<form class="form-signin"
				action="${pageContext.request.contextPath}/PayController"
				name="myForm" onsubmit="return validateForm()" method="post"
				style="width: 80%; display: block; margin-left: auto; margin-right: auto;">

				<label for="fname">Email:</label><br> <input type="text"
					class="form-control" name="username" placeholder="Nhập Email"
					autofocus maxlength="100"
					value="<%if (request.getParameter("username") == null) {
				} else {
					out.print(request.getParameter("username"));
				}%>" />
				<p style="color: red; text-align: center;">
					<%
						if (request.getAttribute("check") != null) {
								out.println(request.getAttribute("check"));
							}
					%>


				</p>
				<label for="fname">Địa chỉ:</label><br> <input type="text"
					class="form-control" name="address"
					placeholder="Nhập địa chỉ nhận hàng" maxlength="255"
					value="<%if (request.getParameter("username") == null) {
				} else {
					out.print(request.getParameter("address"));
				}%>" />
				<label for="fname">Mã giảm giá (nếu có):</label><br> <input
					type="text" class="form-control" name="discount"
					placeholder="Nhập mã giảm giá" maxlength="8" style="width: 20%;"
					value="<%if (request.getParameter("username") == null) {
				} else {
					out.print(request.getParameter("discount"));
				}%>" />

				<br>
				<button
					style="width: 50%; display: block; margin-left: auto; margin-right: auto;"
					class="btn btn-lg btn-primary btn-block" type="submit">Đặt
					hàng</button>

			</form>



		</c:if>






		<script>
			function validateForm() {
				let x = document.forms["myForm"]["username"].value;
				let y = document.forms["myForm"]["address"].value;
				if (x == "") {
					alert("Bạn chưa nhập Email");
					return false;
				}
				if (y == "") {
					alert("Bạn chưa nhập Địa chỉ");
					return false;
				}
			}
		</script>









		<%
			// 			session.setAttribute("totalItems", 0);
			// 			AddToCartController.total = 0;
		%>




	</div>


	<!-- Footer -->
	<%@ include file="footer.jsp"%>



</body>
</html>