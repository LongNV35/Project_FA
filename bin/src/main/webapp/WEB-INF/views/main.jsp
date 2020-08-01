<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include/import_top.jsp"></jsp:include>
</head>

<body>
	<div class="container-fluid">
		<nav class="navbar navbar-expand-lg"
			style="background-color: midnightblue;">
			<img src="../pageResources/img/logo_white.svg" width="150px" />
			<h4 style="color: white; margin-left: 5%;">FSOFT HR UNTILTIES</h4>

			<p style="color: white; margin-left: 500px;">Welcome: <strong>${principal}</strong>
				(FWA.FA)</p>
			<p>
				<a href="<c:url value="/logout"/>" style="color:white"> Logout</a>
			</p>
		</nav>
		<div class="wrapper d-flex align-items-stretch">
			<jsp:include page="include/left_menu.jsp"></jsp:include>
			<div class="content" style="margin-left: 3%; margin-right: 3%"
				id="ajax_content"></div>
		</div>
	</div>

	<jsp:include page="include/footer.jsp"></jsp:include>
	<jsp:include page="include/import_bottom.jsp"></jsp:include>
</body>
</html>