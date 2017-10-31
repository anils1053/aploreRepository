<%@ include file="/WEB-INF/jsp/include/tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Aplore</title>
<%@ include file="/WEB-INF/jsp/include/style.jsp"%>
</head>
<body>
	<div class="col-md-4 col-md-offset-4">
		<div class="panel panel-primary" id="aplore-login-panel">
			<div class="panel-heading">
				<h1><img id="aplore-logo" src="/aplore/static/css/image/logo.png" class="img-rounded" > <font color="white">aplore</font></h1>
			</div>
			<div class="panel-body">
				<stripes:form beanclass="co.nz.aplore.action.IndexActionBean" focus="true">
					<input name="login" type="hidden"/>
					<div class="form-group">
						<input type="text" name="username" class="form-control" placeholder="User Name" autofocus/>
					</div>
					<div class="form-group">
						<input type="password" name="password" class="form-control" placeholder="Password"/>
					</div>
					<div>${actionBean.message}</div>
					<stripes:submit name="login" value="Login" class="btn btn-primary"/>
				</stripes:form>
			</div>
		</div>
	</div>
</body>
</html>