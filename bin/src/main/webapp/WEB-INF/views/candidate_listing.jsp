<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:if test="${sessionScope.message !=null}">
	<div class="p-2" style="text-align: center;" id="message_content">
		<b> <c:if
				test="${sessionScope.message.getMessageOfCandidate() !=null }">
			Candidate: <spring:message
					code="${sessionScope.message.getMessageOfCandidate() }" />
			</c:if> <br /> <c:if
				test="${sessionScope.message.getMessageOfEntryTest() !=null }">
			 Result Entry Tests: <spring:message
					code="${sessionScope.message.getMessageOfEntryTest() }" />
			</c:if> <br /> <c:if
				test="${sessionScope.message.getMessageOfInterview() !=null }">
			Result Interviews: <spring:message
					code="${sessionScope.message.getMessageOfInterview() }" />
			</c:if>
		</b>
	</div>
</c:if>
<c:if test="${sessionScope.message !=null }">
	<c:remove var="message" scope="session" />
</c:if>

<div class="p-2 view_candidate_message" id=view_candidate_message
	style="text-align: center; color: red;">
	<i> <c:if test="${view_candidate_message==true }">
			<spring:message code="MSG31" />
		</c:if>
	</i> <i> <c:if test="${view_candidate_message==false }">
			<spring:message code="MSG33" />
		</c:if>
	</i>
</div>
<c:if test="${sessionScope.view_candidate_message !=null }">
	<c:remove var="view_candidate_message" scope="session" />
</c:if>
<div class="p-2 view_candidate_message" id="deleteCandidateMSG"
	style="text-align: center; color: red;">
	<i> <c:if test="${deleteCandidateMSG==true }">
			<spring:message code="MSG34" />
		</c:if>
	</i>
</div>

<h4>Candidate List</h4>
<div class="mt-4 p-4" style="border: 1px solid darkgray;">
	<c:if test="${deleted}">
		<spring:message code="myDeletedMessageCode" />
	</c:if>
	<form action="${pageContext.request.contextPath}/deleteCandidate">
		<table id="table_candidate" class="display" style="width: 100%;"
			class="table table-striped table-bordered">
			<thead>
				<tr>
					<th><input type="checkbox" name="checkBoxAll" id="checkBoxAll"></th>
					<th>#</th>
					<th>Empl ID</th>
					<th>Account</th>
					<th>Name</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="candidate" items="${candidates}">
					<tr>
						<td><input type="checkbox" class="chkCheckBoxId"
							name="candidateId" value="${candidate.candidateID}"></td>
						<td></td>
						<td><a href="#" class="link_a_candidate"
							id="${candidate.candidateID}">${candidate.candidateID}</a></td>
						<td>${candidate.candidateProfile.account}</td>
						<td>${candidate.candidateProfile.fullName}</td>
						<td>${candidate.status}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="div-button"
			style="margin-bottom: 1%; margin-left: 1%; bottom: 0; width: 100%;">
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<button type="button" class="btn btn-primary"
					id="btn_create_candidate">
					<i class="fas fa-plus-circle"></i>&nbsp;New Candidate
				</button>

				<button type="button" class="btn btn-primary"
					id="btn_update_candidate">
					<i class="fas fa-arrow-up"></i>&nbsp;Update Candidate
				</button>

				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#myModal" id="delete" name="delete"
					data-contextPath="${pageContext.request.contextPath}">
					<i class="fas fa-backspace"></i>&nbsp;Delete Candidate
				</button>
			</security:authorize>
		</div>
	</form>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/pageResources/js/candidate_listing.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/pageResources/js/update_candidate.js"></script>