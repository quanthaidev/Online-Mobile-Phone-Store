<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<link rel='stylesheet'
	href='https://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>
<link rel="stylesheet" href="css/styleLogin.css">

<!-- icon to the browser tab. -->
<link rel="icon"
	href="https://i.pinimg.com/originals/ff/a0/9a/ffa09aec412db3f54deadf1b3781de2a.png">

</head>
<body>



	<div class="wrapper">


		<form class="form-signin" action="${pageContext.request.contextPath}/login"
			name="myForm" onsubmit="return validateForm()" method="post">
			<h2 class="form-signin-heading">Please login</h2>

			<div id="logoAlign">
				<img
					src="https://i.pinimg.com/originals/ff/a0/9a/ffa09aec412db3f54deadf1b3781de2a.png"
					width="100" height="100">
			</div>

			<br>

			<%
				//Chức năng remember me

				Cookie[] cookies = request.getCookies(); //Mảng chứa các coookies đã được add trước đó
				String userName = "", password = "", rememberVal = "";
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("cookuser")) {
							userName = cookie.getValue();
						}
						if (cookie.getName().equals("cookpass")) {
							password = cookie.getValue();
						}
						if (cookie.getName().equals("cookrem")) {
							rememberVal = cookie.getValue();
						}
					}
				}
			%>

			<input type="text" class="form-control" name="username"
				placeholder="Email Address" autofocus value="<%=userName%>" /> <br>
			<input type="password" class="form-control" name="password"
				placeholder="Password" value="<%=password%>" /> <label
				class="checkbox"> <input type="checkbox" name="rememberMe"
				value="1"
				<%="1".equals(rememberVal.trim()) ? "checked=\"checked\"" : ""%>>
				Remember me
			</label>



			<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>


			<p style="text-align: center; padding: 10px">
				<a href="#">Forgot your password?</a>
			</p>


			<p style="text-align: center; padding: 10px; color: red">
				<a href="${pageContext.request.contextPath}" style="color: red"> Trở về trang chủ << </a>
			</p>
			


			<p style="color: red; text-align: center; padding: 10px">
				<%
					if (request.getAttribute("check") != null) {
						out.println(request.getAttribute("check"));

					}
				%>


			</p>
		</form>


	</div>




	<!-- Footer -->
	<%@ include file="footer.jsp"%>




	<script>
		function validateForm() {
			let x = document.forms["myForm"]["username"].value;
			let y = document.forms["myForm"]["password"].value;
			if (x == "") {
				alert("Username is empty");
				return false;
			}
			if (y == "") {
				alert("Password is empty");
				return false;
			}
		}
	</script>




</body>
</html>