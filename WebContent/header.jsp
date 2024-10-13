<style>
    .badge:after{
        content:attr(value);
        font-size:12px;
        color: #fff;
        background: red;
        border-radius:50%;
        padding: 0 5px;
        position:relative;
        left:-8px;
        top:-10px;
        opacity:0.9;
    }
    
    .badge{
    	background-color: transparent;
    }

</style>


<nav class="navbar navbar-default navbar-fixed-top">
	

    <div class="container">

      <div class="row">

        <div class="col-sm-3">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>

            <!-- <a class="navbar-brand" href="#myPage">FX05474</a> -->

            <a href="home.jsp">
              <img src="resource/logo.png" width="130px" height="45px">
            </a>

          </div>
        </div>



        <div class="col-sm-9">
          <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav navbar-right">
              <li><a href="${pageContext.request.contextPath}">HOME</a></li>
              <li><a href="${pageContext.request.contextPath}">PRODUCTS</a></li>
              <li><a href="${pageContext.request.contextPath}/about">ABOUT US</a></li>
              
              <li>
	              <a href="${pageContext.request.contextPath}/login" >
	              	<%
	              		if(session.getAttribute("user") == null){
	              			out.println("LOGIN");
	              		}else{
	              			out.print(session.getAttribute("user"));
	              		}
	              	
	              	%>
	             
	              
	              <span class="glyphicon glyphicon-user"></span>
	              </a>
              </li>
              
              <li><a href="${pageContext.request.contextPath}/register">Register</a></li>
              <li><a href="${pageContext.request.contextPath}/cart">Cart <i class="fa badge fa-lg" 
              value = "${sessionScope.totalItems}" >&#xf07a;</i></a></li>
              

            </ul>
          </div>
        </div>

      </div>



    </div>

  </nav>
  
  <div class="jumbotron text-center">
<!--     <h1>Smartphones Online Store</h1> -->
    <p>Smartphones - Accessory - Wearables</p>

    <form action="${pageContext.request.contextPath}/search" method="get">
      <div class="input-group container">
        <div class="row">

<!--           <select class="col-sm-3 col-xs-3 input-select"> -->
<!--             <option value="0">Categories</option> -->
<!--             <option value="1">Category 01</option> -->
<!--             <option value="1">Category 02</option> -->
<!--           </select> -->

          <input class="col-sm-10 col-xs-10" type="text" id="form-control" placeholder="Search here" 
          name ="searchKey" value = "${param.searchKey}"required>


          <div class="col-sm-2 col-xs-2   input-group-btn ">
            <button type="submit" class="btn btn-danger">Search</button>
          </div>

        </div>


      </div>



    </form>
  </div>