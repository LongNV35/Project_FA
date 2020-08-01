<!-- content -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<div>
	<h4>Dashboard</h4>
	<div class="mt-4 p-4" style="border: 1px solid darkgray;">
		<div class="row main-content" style="width: 100%; margin-left: -2%;">
			<div class="col-lg-3 list-content">
				<form class="form-inline" id="dashboard-dropdown">
					<label class="col-form-label mr-2">Dashboard for</label> <select
						class="form-control" id="dashboard-for" name="dashboard-for">
						<option value="class">Class</option>
						<option value="candidate">Candidate</option>
						<option value="trainee">Trainee</option>						
					</select>
				</form>
			</div>

			<div class="col-lg-3 list-content">
				<form class="form-inline">
					<label class="col-form-label mr-2">Location</label> <select
						class="form-control" id="location" name="location">
						<option value="all">All</option>
						<c:forEach items="${listLocation}" var="location">
						<option value="all">${location.locationName }</option>
						</c:forEach>
					</select>
				</form>

			</div>

			<div class="col-lg-3 list-content">
				<form class="form-inline">
					<label class="col-form-label mr-2">Type of Dashboard</label> <select
						class="form-control" id="type-of-dashboard" name="type-of-dashboard">
						<option value="table" selected>Table</option>
						<option value="chart">Chart</option>
					</select>
				</form>
			</div>

			<div class="col-lg-3 list-content">
				<form class="form-inline">
					<label class="col-form-label mr-2">Status</label> <select
						class="form-control" id="status-for" name="status-for">
						<option value="all">All</option>
						<c:forEach items="${listNameOfStatus}" var="status">
						<option value="all">${status}</option>
						</c:forEach>
					</select>
				</form>
			</div>
		</div>
		<hr>
		<!-- Tab panes -->
		<div class="row tbl-content" id="dashboard-content">
			<jsp:include page="include/landing-page-table.jsp"></jsp:include>
		</div>
	</div>
	<br />
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/pageResources/js/landing-page.js"></script>