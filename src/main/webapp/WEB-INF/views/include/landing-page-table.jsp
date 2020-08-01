<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row" style="width: 100%;">
	<c:forEach items="${listDashboardVos}" var="listDashboardVo">
		<div class="col-lg-4">
			<label>${listDashboardVo[0].status} </label>
			<div class="table-wrapper-scroll-y my-custom-scrollbar">
				<table class="table table-bordered table-striped mb-0">
					<thead class="table-secondary">
						<tr>
							<th scope="col">Skill</th>
							<th scope="col">Plan of enrolment</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="total" value="${0}" />
						<c:forEach items="${listDashboardVo}" var="dashboardVo">
							<tr>
								<td>${dashboardVo.skill}</td>
								<td>${dashboardVo.value}</td>
							</tr>
							<c:set var="total" value="${total +dashboardVo.value}" />
						</c:forEach>
						<tr>
							<td>Total</td>
							<td>${total}</td>
						</tr>
					</tbody>
				</table>

			</div>
		</div>
	</c:forEach>
</div>
