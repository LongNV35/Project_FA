<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<div class="p-2 tranfer_message" id="tranfer_message"
	style="text-align: center; color: red;">
	<i> <c:if test="${tranfer_message==true }">
			<spring:message code="MSG32" />
		</c:if>
	</i>
</div>
<c:if test="${sessionScope.tranfer_message !=null }">
	<c:remove var="tranfer_message" scope="session" />
</c:if>

<h4>Candidate Profile</h4>
<div class="mt-4 p-4" style="border: 1px solid darkgray;">
	<ul class="nav nav-tabs">
		<li class="nav-item"><a class="nav-link active" data-toggle="tab"
			href="#candidate_infomation">Candidate Infomation</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab"
			href="#result">Result</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content mt-4">
		<div class="tab-pane container active ml-0" style="min-width: 100%;"
			id="candidate_infomation">
			<table class="table table-bordered" item="candidate"
				id="CandidateProfile" value="candidate">
				<div class="p-2" style="background-color: #3399ff;">
					<b>Personal Infomation</b>
				</div>
				<thead>
					<form id="hidden" class="hidden">
						<input type="hidden" id="getcandidateID" class="getcandidateID"
							name="getcandidateID" value="${candidate.candidateID }">
						<input type="hidden" id="getGender" class="getGender"
							name="getGender"
							value="${candidate.getCandidateProfile().gender }">
					</form>
					<div class="row m-0">
						<div class="col-2 bg_title p-2">
							<b>Empl ID</b>
						</div>

						<div class="col-2 p-2 candidateid" id="candidateid"
							name="candidateID" txt="${candidate.candidateID }">${candidate.candidateID }</div>
						<div class="col-2 bg_title p-2">
							<b>Type</b>
						</div>
						<div class="col-2 p-2 type" id="type">${candidate.getCandidateProfile().type }</div>
						<div class="col-2 bg_title p-2">
							<b>Status</b>
						</div>
						<div class="col-2 p-2 status" id="status">${candidate.status }</div>
					</div>
				</thead>
				<tbody>
					<tr>
						<td class="bg_title">Application Date</td>
						<td class="applicationDate" id="applicationDate"
							data-provide="applicationDate">${applicationDate }</td>
						<td class="bg_title">Channel</td>
						<td class="channelName" id="channelName">${candidate.getChannel().channelName }</td>
					</tr>
					<tr>
						<td class="bg_title">Site</td>
						<td class="locationName" id="locationName">${candidate.getLocation().locationName }</td>
						<td class="bg_title">Account</td>
						<td class="account" id="account">${candidate.getCandidateProfile().account }</td>
					</tr>
					<tr>
						<td class="bg_title">Name</td>
						<td class="fullName" id="fullName">${candidate.getCandidateProfile().fullName }</td>
						<td class="bg_title">Gender</td>
						<td id="gender" class="gender"></td>
					</tr>
					<tr>
						<td class="bg_title">DOB</td>
						<td class="dateOfBirth" id="dateOfBirth">${dateOfBirth }</td>
						<td class="bg_title">CV</td>
						<td class="cv" id="cv"><u>${candidate.getCandidateProfile().cv }</u></td>
					</tr>
					<tr>
						<td class="bg_title">University</td>
						<td class="universityName" id="universityName">${candidate.getCandidateProfile().getUniversity().universityName }</td>
						<td class="bg_title">Faculty</td>
						<td class="facultyName" id="facultyName">${candidate.getCandidateProfile().getFaculty().facultyName }</td>
					</tr>
					<tr>
						<td class="bg_title">Phone</td>
						<td class="facultyName" id="facultyName">${candidate.getCandidateProfile().phone }</td>
						<td class="bg_title">Email</td>
						<td class="email" id="email">${candidate.getCandidateProfile().email }</td>
					</tr>
					<tr>
						<td class="bg_title">Skill</td>
						<td class="skill" id="skill">${candidate.getCandidateProfile().skill }</td>
						<td class="bg_title">Graduation Year</td>
						<td class="graduationYear" id="graduationYear">${graduationYear }</td>
					</tr>
					<tr>
						<td class="bg_title">Foreign Language</td>
						<td class="foreignLanguage" id="foreignLanguage">${candidate.getCandidateProfile().foreignLanguage }</td>
						<td class="bg_title">Level</td>
						<td class="level" id=level>${candidate.getCandidateProfile().level }</td>
					</tr>
					<tr>
						<td class="bg_title">Note</td>
						<td colspan="3" class="note" id="note">${candidate.remarks }</td>
					</tr>
					<tr>
						<td class="bg_title">History</td>
						<td colspan="3" class="history" id="history">${candidate.history }</td>
					</tr>
				</tbody>
			</table>

		</div>
		<div class="tab-pane container fade" style="min-width: 100%;"
			id="result">
			<div class="bg_title m-0 p-2">
				<button type="button" class="btn btn-primary collapsible ml-2"
					id="#" data-toggle="collapse" data-target="#entry_test_table"
					aria-expanded="true" aria-controls="entry_test_table">
					<i class="fas fa-caret-down"></i>
				</button>
				&emsp; <b>Entry Test</b>
			</div>
			<table class="table" id="entry_test_table">
				<thead class="thead-light">
					<tr>
						<th scope="col">Time</th>
						<th scope="col">Date</th>
						<th scope="col">Language Valuator</th>
						<th scope="col">Language Point</th>
						<th scope="col">Technical Valuator</th>
						<th scope="col">Technical Point</th>
						<th scope="col">Result</th>
					</tr>
				</thead>

				<tbody class="content" id="milestoneCollapse">
					<c:forEach items="${candidate.getListEntryTest() }" var="entrytest">
						<tr>
							<th scope="row">${entrytest.time }</th>
							<td>${entrytest.date }</td>
							<td>${entrytest.languageValuator }</td>
							<td>${entrytest.languageResult }<span> / 100</span></td>
							<td>${entrytest.technicalValuator }</td>
							<td>${entrytest.technicalResult }<span> / 100</span></td>
							<td>${entrytest.result }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>
				<div class="bg_title m-0 p-2 mt-4 mb-0">
					<button type="button" class="btn btn-primary collapsible ml-2"
						id="#" data-toggle="collapse" data-target="#interview_table"
						aria-expanded="true" aria-controls="interview_table">
						<i class="fas fa-caret-down"></i>
					</button>
					&emsp; <b>Interview</b>
				</div>
				<table class="table" id="interview_table">
					<thead class="thead-light">
						<tr>
							<th scope="col">Time</th>
							<th scope="col">Date</th>
							<th scope="col">Interviewer</th>
							<th scope="col">Comment</th>
							<th scope="col">Result</th>
						</tr>
					</thead>
					<tbody class="content1" id="milestoneCollapse1">
						<c:forEach items="${candidate.getListInterview() }"
							var="interview">
							<tr>
								<th scope="row">${interview.interviewTime }</th>
								<td>${interview.interviewDate }</td>
								<td>${interview.interviewer }</td>
								<td>${interview.comments }</td>
								<td>${interview.result }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<br />
	<security:authorize access="hasRole('ROLE_ADMIN')">
	<button type="button" class="btn btn-primary" id="btn_update">
		<i class="fas fa-pen-fancy"></i>&nbsp;Update Candidate
	</button>
	<button type="button" class="btn btn-primary" id="delete">
		<i class="far fa-trash-alt"></i>&nbsp;Delete Candidate
	</button>
	<button type="button" class="btn btn-primary" data-toggle="modal"
		data-target="#myModal">
		<i class="fas fa-exchange-alt"></i>&nbsp;Tranfer Candidate
	</button>
	</security:authorize>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog" aria-hidden="true"
	style="overflow-y: hidden;">
	<div class="modal-dialog modal-dialog-centered">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h4>Tranfer</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<table class="table">
					<tr class="col-2 bg_title">Tranfer to:
					</tr>
					<tr class="col-2 bg_title col-6">
						<select id="mySelect" style="float: right; width: 30%;">
							<option value="FA">FA</option>
							<option value="Intership">Intership</option>
							<option value="Campus_Link">Campus Link</option>
						</select>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default tranfer" id="tranfer"
					name="tranfer" data-dismiss="modal">OK</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancle</button>
			</div>
		</div>
	</div>
</div>
<script>
	var gender = "";
	gender = document.getElementById("getGender").value;
	var n = gender.localeCompare("true");
	if (n == 0) {
		document.getElementById("gender").innerHTML = "Male";
	} else {
		document.getElementById("gender").innerHTML = "Female";
	}
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/pageResources/js/view_candidate_profile.js"></script>