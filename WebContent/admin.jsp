<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Admin</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<!-- icon to the browser tab. -->
<link rel="icon"
	href="https://i.pinimg.com/originals/ff/a0/9a/ffa09aec412db3f54deadf1b3781de2a.png">


<link rel="stylesheet" href="css/styleAdmin.css">



</head>

<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

				<!-- logo -->
				<a class="navbar-brand" href="home.jsp"> <img
					src="resource/logo.png" alt="LOGO" width="130px" height="45px">
				</a>


			</div>
			<!-- Top Menu Items -->
			<ul class="nav navbar-right top-nav">
				<li><a href="#" data-placement="bottom" data-toggle="tooltip"
					href="#" data-original-title="Stats"><i
						class="fa fa-paper-plane-o"></i> </a></li>
				<li class="dropdown">
					<!--      Tên user sau khi đăng nhập thành công --> <a href="#"
					class="dropdown-toggle" data-toggle="dropdown"><%=session.getAttribute("user")%>
						<b class="fa fa-angle-down"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#"><i class="fa fa-fw fa-user"></i> Edit
								Profile</a></li>
						<li><a href="#"><i class="fa fa-fw fa-cog"></i> Change
								Password</a></li>
					</ul>
				</li>



				<!--Thoát đăng nhập, bấm vào đây thì nó sẽ close session.
				Lúc này trở về LoginServlet.java để xử lý hủy session -->
				<li><a href="${pageContext.request.contextPath}/LogOutServlet">
						<i class="fa fa-fw fa-power-off"></i> Log out


				</a></li>




			</ul>
			<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav side-nav">
					<li><a href="#" data-toggle="collapse"
						data-target="#submenu-1"><i class="fa fa-fw fa-star"></i>
							DASHBOARD <i class="fa fa-fw fa-angle-down pull-right"></i></a>
						<ul id="submenu-1" class="collapse">
							<li><a href="#"><i class="fa fa-angle-double-right"></i>
									Default</a></li>
							<li><a href="#"><i class="fa fa-angle-double-right"></i>
									Socials</a></li>
							<li><a href="#"><i class="fa fa-angle-double-right"></i>
									Pages</a></li>
						</ul></li>
					<li><a href="#" data-toggle="collapse"
						data-target="#submenu-2"><i class="fa fa-fw fa-bar-chart-o"></i>
							SELLING <i class="fa fa-fw fa-angle-down pull-right"></i></a>
						<ul id="submenu-2" class="collapse">
							<li><a href="#"><i class="fa fa-angle-double-right"></i>
									Revenue</a></li>
							<li><a href="#"><i class="fa fa-angle-double-right"></i>
									Cost</a></li>
							<li><a href="#"><i class="fa fa-angle-double-right"></i>
									Tracking</a></li>
						</ul></li>
					<li><a href="#"><i class="fa fa-fw fa-user-plus"></i> ADD
							USERS</a></li>
					<li><a href="#"><i class="fa-cog"></i> LANDING</a></li>
					<li><a href="#"><i class="fa fa-fw fa fa-question-circle"></i>
							HELP</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>

		<div id="page-wrapper">
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row" id="main">
					<div class="col-sm-12 col-md-12 well" id="content">
						<h1>
							Welcome
							<%=session.getAttribute("user")%>
							!
						</h1>
					</div>
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>








		<div id="page-wrapper">
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row" id="main">
					<div class="col-sm-12 col-md-12 well" id="content">




						<c:if test="${requestScope.idOrder == 'portal' }">

							<p>${requestScope.idOrder}</p>
							<h2 style="margin: 10px 10px;">Danh sách đơn hàng (click vào
								"ID đơn hàng" để xem chi tiết)</h2>

							<table>

								<tr>
									<th style="width: 10%">ID đơn hàng</th>
									<th style="width: 10%">Ngày đặt</th>
									<th style="width: 5%">Status</th>
									<th style="width: 10%">Mã giảm giá</th>
									<th style="width: 10%">Tổng tiền $</th>
									<th style="width: 20%">Email</th>
									<th>Địa chỉ nhận hàng</th>
								</tr>

								<c:forEach var="list" items="${requestScope.arrayOrders}">
									<tr>
										<td><a
											href="${pageContext.request.contextPath}/AdminServlet?idOrder=${list.getId()}">${list.getId()}</a>                      
										</td>
										<td>${list.getDate()}</td>
										<td>${list.getStatus()}</td>
										<td>${list.getDiscountCode()}</td>
										<td>${list.getTotalPrice()}</td>
										<td>${list.getEmail()}</td>
										<td>${list.getAddress()}</td>
									</tr>

								</c:forEach>



							</table>

						</c:if>

						<c:if test="${param.idOrder != null}">
							<a href="${pageContext.request.contextPath}/AdminServlet"> Back << </a>
							<h2>Đơn hàng: ${param.idOrder}</h2>
							<table>

								<tr>
									<th style="width: 10%">ID đơn hàng</th>
									<th style="width: 10%">ID sản phẩm</th>
									<th style="width: 25%">Tên sản phẩm</th>
									<th style="width: 10%">Đơn giá</th>
									<th style="width: 10%">Số lượng</th>
									<th>Tổng tiền $</th>

								</tr>



								<c:forEach var="list" items="${requestScope.arrayProductOrders}">
									<tr>
										<td>${list.idO}</td>
										<td>${list.idP}</td>
										<td>${list.nameP}</td>
										<td>${list.price}</td>
										<td>${list.amountP}</td>
										<td>${list.totalP}</td>

									</tr>


								</c:forEach>
								
								






							</table>

						</c:if>














					</div>
				</div>

			</div>

		</div>









	</div>


</body>



</html>