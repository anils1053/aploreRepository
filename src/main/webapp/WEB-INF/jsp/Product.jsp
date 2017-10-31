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
            <h1>${actionBean.productDto.productDetails.prouctDisplayName}</h1>
          </div>
          <div class="col-md-4">
            <ol class="breadcrumb pull-right no-margin-bottom">
              <li><a href="Aplore.action">Home</a></li>
              <li><a href="ProductDetails.action?name=${actionBean.productDto.productDetails.productName}">${actionBean.productDto.productDetails.productName}</a></li>
            </ol>
          </div>
        </section> <!-- / PAGE TITLE -->

        <div class="container-fluid">
          <div class="row">
             <div id="product-tab-container">
			  <!-- Nav tabs -->
			  <ul class="nav nav-tabs" role="tablist">
			    <li role="presentation" class="active"><a href="#restapi" aria-controls="restapi" role="tab" data-toggle="tab">REST</a></li>
			    <li role="presentation"><a href="#soapapi" aria-controls="soapapi" role="tab" data-toggle="tab">SOAP</a></li>
			    <li role="presentation"><a href="#clinicalevent" aria-controls="clinicalevent" role="tab" data-toggle="tab">CLINICAL EVENTS</a></li>
			    <li role="presentation"><a href="#osgiservice" aria-controls="osgiservice" role="tab" data-toggle="tab">OSGI SERVICE</a></li>
			  </ul>
			
			  <!-- Tab panes -->
			  <div class="tab-content">
			    <div role="tabpanel" class="tab-pane fade in active" id="restapi">
			    	<section class="panel panel-primary">
						<header class="panel-heading">
							<h3 class="panel-title">Rest</h3>
						</header>
						<div class="panel-body">
							<table id="rest-api-table" class="table table-hover">
							<thead>
								<tr>
 									<th>Class Name</th>
									<th>Method Name</th>
									<th>URI</th>
									<th>Method</th>
									<th>Request Parameter</th>
									<th>Path Parameter</th>
									<th>Doc</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="rest" varStatus="status" items="${actionBean.productDto.restEndPoints}">
									<tr>
 										<td>${rest.javaClass}</td>
										<td>${rest.javaMethodName}</td>
										<td>
											<span class=rest-uri>${rest.uri}</span>
										</td>
										<td>
										<c:choose>
											<c:when test="${rest.method == 'POST'}">
												<span class="label label-info">${rest.method}</span>
											</c:when>
											<c:when test="${rest.method == 'PUT'}">
												<span class="label label-success">${rest.method}</span>
											</c:when>
											<c:when test="${rest.method == 'DELETE'}">
												<span class="label label-danger">${rest.method}</span>
											</c:when>
											<c:otherwise>
												<span class="label label-default">${rest.method}</span>
											</c:otherwise>
										</c:choose>
										</td>
										<td>
											<c:forEach var="queryParameter" items="${rest.queryParameters}">
												<span class="label label-primary">${queryParameter.name }</span>
												<br/>
											</c:forEach>
										</td>
										<td>
											<c:forEach var="pathParameter" items="${rest.pathParameters}">
												<span class="label label-primary">${pathParameter.name }</span>
												<br/>
											</c:forEach>
										</td>
										<td><a id="java-doc-link" class="btn btn-info" href="${rest.javaDocUrl}">Java Doc</a></td>
									</tr>
								</c:forEach>
							</tbody>
							</table>
						</div>
					</section>
			    </div>
			    <div role="tabpanel" class="tab-pane fade" id="soapapi">
					<section class="panel panel-primary">
						<header class="panel-heading">
							<h3 class="panel-title">SOAP</h3>
						</header>
						<div class="panel-body">
							<table id="soap-api-table" class="table table-hover">
							<thead>
								<tr>
									<th>Class Name</th>
									<th>Method Name</th>
									<th>Java Doc</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="soap" varStatus="status" items="${actionBean.productDto.soapEndPoints}">
									<tr>
										<td>${soap.javaClass}</td>
										<td class="soap-method">${soap.javaMethodName}</td>
										<td><a id="java-doc-link" class="btn btn-info" href="${soap.javaDocUrl}">Java Doc</a></td>
									</tr>
								</c:forEach>
							</tbody>
							</table>
						</div>
					</section>
			    </div>
			    <div role="tabpanel" class="tab-pane fade" id="clinicalevent">
			   		<section class="panel panel-primary">
						<header class="panel-heading">
							<h3 class="panel-title">Clinical Events Classes List</h3>
						</header>
						<div class="panel-body">
							<table id="clinical-event-table" class="table table-hover">
							<thead>
								<tr>
									<th>Class Name</th>
									<th>Package</th>
									<th>Java Doc</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="clinicalEvent" varStatus="status" items="${actionBean.productDto.clinicalEventEndPoints}">
									<tr>
										<td class="clinical-event-class">${clinicalEvent.javaClass}</td>
										<td>${clinicalEvent.packageName}</td>
										<td><a id="java-doc-link" class="btn btn-info" href="${clinicalEvent.javadocLink}">Java Doc</a></td>
									</tr>
								</c:forEach>
							</tbody>
							</table>
						</div>
					</section>
			    </div>
			    <div role="tabpanel" class="tab-pane fade" id="osgiservice">
			    	<section class="panel panel-primary">
						<header class="panel-heading">
							<h3 class="panel-title">OSGI SERVICES List</h3>
						</header>
						<div class="panel-body">
							<p>Not supported yet. Please contribute.</p>
						</div>
					</section>
			    </div>
			  </div>
			
			</div>
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
			Product.init();
		});
	</script>
</body>
</html>