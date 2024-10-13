<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Register</title>
<link rel='stylesheet'
	href='https://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>
<link rel="stylesheet" href="css/styleLogin.css">

<!-- icon to the browser tab. -->
<link rel="icon" href="resource/register.png">

</head>
<body>



	<div class="wrapper">
		<p style="color: red; text-align: center; padding: 10px; font-weight: bold">
			<%
				if (request.getAttribute("check") != null) {
					out.println(request.getAttribute("check"));

				}
			%>


		</p>


		<form class="form-signin"
			action="${pageContext.request.contextPath}/RegisterServlet"
			name="myForm" onsubmit="return validateForm()" method="post">
			<h2 class="form-signin-heading">Create a new Account</h2>

			<div id="logoAlign">
				<img src="resource/register.png" width="100" height="100">
			</div>

			<br> <input type="text" class="form-control" name="fullname"
				placeholder="Họ tên" autofocus value="${requestScope.fullname}" /> <br> 
				
				<input
				type="text" class="form-control" name="address"
				placeholder="Địa chỉ" autofocus value="${requestScope.address}" /> <br> 
				
				<input
				type="number" class="form-control" name="phone"
				placeholder="Số điện thoại" autofocus value="${requestScope.phone}" /> <br> 
				
				<input
				type="text" class="form-control" name="email" placeholder="Email"
				autofocus value="${requestScope.email}" /> <br> 
				
				<input type="password"
				class="form-control" name="password" placeholder="Mật khẩu" value="${requestScope.password}"/> 
				
				<input type="password" class="form-control"
				name="rePassword" placeholder="Nhập lại mật khẩu" value="${requestScope.rePassword}" />

			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>



			<br>
			<p style="text-align: center; padding: 10px; color: red">
				<a href="${pageContext.request.contextPath}/" style="color: red"> Trở về trang chủ << </a>
			</p>

		</form>


	</div>




	<!-- Footer -->
	<%@ include file="footer.jsp"%>




	<script>
		function validateForm() {
			let a = document.forms["myForm"]["fullname"].value;
			let b = document.forms["myForm"]["address"].value;
			let c = document.forms["myForm"]["phone"].value;
			let d = document.forms["myForm"]["email"].value;
			let e = document.forms["myForm"]["password"].value;
			let f = document.forms["myForm"]["rePassword"].value;
			if (a == "") {
				alert("Fullname is empty");
				return false;
			}
			if (b == "") {
				alert("Address is empty");
				return false;
			}
			if (c == "") {
				alert("Phone number is empty");
				return false;
			}
			if (d == "") {
				alert("Email is empty");
				return false;
			}
			if (e == "") {
				alert("Password is empty");
				return false;
			}
			if (f == "") {
				alert("Re-enter Password is empty");
				return false;
			}
		}
	</script>




</body>
</html>