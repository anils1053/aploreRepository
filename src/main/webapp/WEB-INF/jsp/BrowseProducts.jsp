<%@ include file="/WEB-INF/jsp/include/tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>aplore</title>
	<%@ include file="/WEB-INF/jsp/include/style.jsp"%>
</head>
<body class="">
    <div class="animsition">  
    
      <!-- start of LOGO CONTAINER -->
      <div id="logo-container">
        <a href="#" id="aplore-logo-container">
          <img id="aplore-logo-dashboard" src="static/css/image/logo.png" class="big-logo" alt="aplore">
          <img src="static/css/image/logo.png" data-no-retina  class="small-logo" alt="aplore">
          <span>aplore</span>
        </a>
      </div>
      <!-- end of LOGO CONTAINER -->

      <!-- - - - - - - - - - - - - -->
      <!-- start of SIDEBAR        -->
      <!-- - - - - - - - - - - - - -->
      <div id="sidebar">
        <div class="sidebar_scroll"> <!-- start of slimScroll -->


           <ul class="nav lg-menu" id="main-nav">
            <li class="sidebar-title"><h5 class="text-center margin-bottom-30 margin-top-15">Navigation</h5></li>
            <li><a href="Aplore.action" > <i class="ti-dashboard"></i> <span>Dashboard</span></a></li>
       		<li><a href="BrowseProducts.action" > <i class="ti-plug"></i> <span>Browse APIs</span></a></li>
            
            <li class="sidebar-title"><h5 class="text-center margin-bottom-30 margin-top-15">Other</h5></li>
            <li><a href="http://devqna" > <i class="ti-layout-cta-left"></i> <span>DevQna</span></a></li>
            <li><a href="http://blogs" > <i class="ti-user"></i> <span>Orion Blog</span></a></li>
          </ul>

        </div> <!-- end of slimScroll -->
      </div>
      <!-- - - - - - - - - - - - - -->
      <!-- end of SIDEBAR          -->
      <!-- - - - - - - - - - - - - -->


      <main id="playground">

            
        <!-- - - - - - - - - - - - - -->
        <!-- start of TOP NAVIGATION -->
        <!-- - - - - - - - - - - - - -->
        <nav class="navbar navbar-top navbar-static-top">
          <div class="container-fluid">

            <!-- sidebar collapse and toggle buttons get grouped for better mobile display -->
            <div class="navbar-header nav">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-top">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand show-hide-sidebar hide-sidebar" href="#"><i class="fa fa-outdent"></i></a>
              <a class="navbar-brand show-hide-sidebar show-sidebar" href="#"><i class="fa fa-indent"></i></a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="navbar-top">

              <!-- start of SEARCH BLOCK -->
              <div class="navbar-form navbar-left navbar-search-block">

                <div class="search-field-container">
                  <input type="text" placeholder="Global Search" class="search-field" id="alpore-product-search-input">
                  <a href="#"><i class="ti-search"></i></a>
                </div>

                <!-- start of CLOSE BUTTON -->
                <a href="#" class="btn btn-danger search-close"><i class="ti-close"></i></a>
                <!-- end of CLOSE BUTTON -->

                <div class="container-fluid search-container">
                  <div class="row">

                    <!-- List Product -->
                    <div class="col-md-12">
                      <h3>Matches Found:</h3>
                      <div id="product-search-results"><ul></ul></div>
                    </div>

                  </div>
                </div>

              </div>
              <!-- end of SEARCH BLOCK -->
			  <!-- nav left side -->
              <ul class="nav navbar-nav">

              </ul>

              <ul class="nav navbar-nav navbar-right">

                <!-- start of USER MENU -->
                <li class="dropdown user-profile">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                    Admin <span class="chat-status success"></span>
                  </a>

                  <ul class="dropdown-menu" role="menu">
                    <li><a href="Aplore.action?logout=" id="aplore-logout">Logout</a></li>
                  </ul>
                </li>
                <!-- end of USER MENU -->

              </ul>
            </div>
            <!-- end of navbar-collapse -->
          </div>
          <!-- end of container-fluid -->
        </nav>

        <!-- - - - - - - - - - - - - -->
        <!-- end of TOP NAVIGATION   -->
        <!-- - - - - - - - - - - - - -->


        <!-- PAGE TITLE -->
        <section id="page-title" class="row">
        	<div class="col-md-8">
	            <h1>Browse Products</h1>
	        </div>
			<div class="col-md-4">
	            <ol class="breadcrumb pull-right no-margin-bottom">
	              <li><a href="Aplore.action">Home</a></li>
	            </ol>
	       </div>
        </section> <!-- / PAGE TITLE -->
		
		<div class="panel-body">
			<h3>Products</h3>
			<div id="list-products" class="list-group">
				<c:forEach var="product" varStatus="status" items="${actionBean.productSummaryDtos}">
					<c:choose>
						<c:when test="${status.index % 2 == 0 }">
							<a class="list-group-item list-group-item-success" href="ProductDetails.action?name=${product.name}"><span><i class="fa fa-cube fa-5"></i>&nbsp;</span><strong>${product.name}</strong></a>
						</c:when>
						<c:otherwise>
							<a class="list-group-item list-group-item-info" href="ProductDetails.action?name=${product.name}"><span><i class="fa fa-cube fa-5"></i>&nbsp;</span><strong>${product.name}</strong></a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
		
      <footer>
        <p>Powered by <a href="#">Aplore</a></p>
      </footer>


    </main>

    <div class="scroll-top">
      <i class="ti-angle-up"></i>
    </div>
  </div> <!-- /animsition -->
	
	
	
	<%@ include file="/WEB-INF/jsp/include/script.jsp"%>
	<script type="text/javascript">
		$(document).ready(function () {
			ProductSummary.init();
		});
	</script>
</body>
</html>