<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h4>Update Candidate Profile</h4>
<div class="mt-4 p-4 mb-4" style="border: 1px solid darkgray;">
	<form id="update_candidate_form">
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link active"
				data-toggle="tab" href="#candidate_infomation">Candidate
					Infomation</a></li>
			<li class="nav-item"><a class="nav-link " data-toggle="tab"
				href="#result">Result</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content mt-4">
			<!-- candidate -->
			<div class="tab-pane container active ml-0" style="min-width: 100%;"
				id="candidate_infomation">
				<table class="table table-bordered">
					<div class="p-2" style="background-color: #3399ff;">
						<b>Personal Infomation</b>
					</div>

					<div class="row mb-1 mt-2" style="border: 1px solid #E0E0E0;">
						<input name="candidateId" value="${candidate.getCandidateID() }"
							hidden="true">
						<div class="col-2 bg_title p-2">
							<b>Empl ID</b>
						</div>
						<div class="col-2">
							<p id="employeeId">${sessionScope.employeeId }</p>
						</div>
						<div class="col-2 bg_title p-2">
							<b>Type</b>
						</div>
						<div class="col-2">
							<p id="type">${candidateProfile.getType() }</p>
						</div>
						<div class="col-2 bg_title p-2">
							<b>Status</b>
						</div>
						<div class="col-2">
							<p id="status">${candidate.getStatus() }</p>
						</div>
					</div>
					<tbody>
						<tr>
							<td class="bg_title w_15pc"><b>Application Date</b></td>
							<td class="w_35pc in">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1"> <i
											class="far fa-calendar-alt"></i>
										</span>
									</div>
									<input type="text" class="form-control datepicker"
										id="applicationDate" data-provide="datepicker" name="applicationDate"
										readonly="readonly">
								</div>
							</td>
							<td class="bg_title w_15pc"><b>Channel *</b></td>
							<td class="w_35pc">
								<div class="input-group">
									<select class="w_100pc custom-select" id="channel_dropdown"
										name="channel_dropdown">
										<option value="" selected></option>
										<c:forEach items="${channels }" var="channel">
											<option
												${channel.getChannelId()==candidate.getChannel().getChannelId()?"selected":"" }
												value="${channel.getChannelId() }">${channel.getChannelName() }</option>
										</c:forEach>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td class="bg_title w_15pc"><b>Site</b></td>
							<td class="w_35pc">
								<div class="input-group mb-3">
									<select class="w_100pc custom-select" id="site_dropdown"
										name="site_dropdown">
										<option selected></option>
										<c:forEach items="${locations }" var="location">
											<option
												${location.getLocationId()==candidate.getLocation().getLocationId()?"selected":"" }
												value="${location.getLocationId() }">${location.getLocationName() }</option>
										</c:forEach>
									</select>
								</div>
							</td>
							<td class="bg_title w_15pc"><b>Account</b></td>
							<td class="w_35pc" style="background-color: #E8E8E8;">
								<p id="account" name="account">${candidateProfile.getAccount() }</p>
							</td>
						</tr>
						<tr>
							<td class="bg_title w_15pc"><b>Name *</b></td>
							<td class="w_35pc"><input type="text" class="form-control"
								required id="name" name="name"
								value="${candidateProfile.getFullName() }" /></td>
							<td class="bg_title w_15pc"><b>Gender *</b></td>
							<td class="w_35pc">
								<div
									class="custom-control custom-radio custom-control-inline ml_25pc">
									<input ${candidateProfile.getGender()==true? "checked": "" }
										type="radio" class="custom-control-input" id="customRadio"
										value="1" name="gender"> <label
										class="custom-control-label" for="customRadio">Male</label>
								</div>
								<div
									class="custom-control custom-radio custom-control-inline ml_25pc">
									<input ${candidateProfile.getGender()==false? "checked": "" }
										type="radio" class="custom-control-input" id="customRadio2"
										value="0" name="gender"> <label
										class="custom-control-label" for="customRadio2">Female</label>
								</div>
							</td>
						</tr>
						<tr>
							<td class="bg_title w_15pc"><b>DOB *</b></td>
							<td class="w_35pc ">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1"> <i
											class="far fa-calendar-alt"></i>
										</span>
									</div>
									<input updateValue="${candidateProfile.getDateOfBirth() }"
										type="text" class="form-control datepicker w_80pc"
										id="dateofbirth" name="dateOfBirth">
								</div>
							</td>
							<td class="bg_title w_15pc"><b>CV</b></td>
							<td class="w_35pc">

								<div class="custom-file">
									<input type="file" accept=".doc, .docx, .pdf"
										class="custom-file-input" id="cv" name="cv"> <label
										id="lable_file_name"
										updatedValue="${candidateProfile.getCv() }"
										class="custom-file-label" for="customFile"
										style="overflow: hidden;"></label>
								</div> <script>
									// Add the following code if you want the name of the file appear on select
									$(".custom-file-input").on(
											"change",
											function() {
												var fileName = $(this).val()
														.split("\\").pop();
												$(this).siblings(
														".custom-file-label")
														.addClass("selected")
														.html(fileName);
											});
								</script>
							</td>
						</tr>
						<tr>
							<td class="bg_title w_15pc"><b>University</b></td>
							<td class="w_35pc ">
								<div class="input-group mb-3">
									<div class="input-group">
										<div class="input-group-append card">
											<a class="btn btn-light" data-toggle="collapse"
												data-target="#unversity_drop"> <i
												class="fas fa-caret-down"></i>
											</a>
										</div>
										<input
											value="${candidateProfile.getUniversity().getUniversityName() }"
											universityId="${candidateProfile.getUniversity().getUniversityId() }"
											data-toggle="collapse" data-target="#unversity_drop"
											type="text" class="form-control w_80pc" id="input_university"
											name="input_university" readonly="readonly">
									</div>
									<div id="unversity_drop" class="collapse card w_100pc p-2">
										<c:forEach items="${universities }" var="university">
											<div class="custom-control custom-checkbox">
												<input type="checkbox"
													class="custom-control-input university"
													${candidateProfile.getUniversity().getUniversityName()==university.getUniversityName() ? "checked": "" }
													universityName="${university.getUniversityName()}"
													id="u_${university.getUniversityId() }" name="university">
												<label class="custom-control-label"
													for="u_${university.getUniversityId() }">
													${university.getUniversityName()} </label>
											</div>
										</c:forEach>
										<!-- other dropdown -->
										<div class="custom-control custom-checkbox">
											<input type="checkbox"
												class="custom-control-input university" id="other_u"
												name="university" data-toggle='collapse'
												data-target='#div_other_u'> <label
												class="custom-control-label" for="other_u"> other </label>
										</div>
										<div id='div_other_u' class='collapse p-1'>
											<input type="text" class="form-control" id="input_other_u">
										</div>
									</div>
								</div>
							</td>
							<td class="bg_title w_15pc"><b>Faculty</b></td>
							<td class="w_35pc">
								<div class="input-group mb-3">
									<div class="input-group">
										<div class="input-group-append card">
											<a class="btn btn-light" data-target="#faculty_drop"
												data-toggle="collapse"> <i class="fas fa-caret-down"></i>
											</a>
										</div>
										<input
											value="${candidateProfile.getFaculty().getFacultyName() }"
											facultyId="${candidateProfile.getFaculty().getFacultyId() }"
											data-target="#faculty_drop" data-toggle="collapse"
											type="text" class="form-control w_80pc" id="input_faculty"
											name="input_faculty" readonly="readonly">
									</div>
									<div id="faculty_drop" class="collapse card w_100pc p-2">
										<c:forEach items="${faculties }" var="faculty">
											<div class="custom-control custom-checkbox">
												<input type="checkbox" class="custom-control-input faculty"
													${candidateProfile.getFaculty().getFacultyName()==faculty.getFacultyName() ? "checked": "" }
													facultyName="${faculty.getFacultyName()}"
													id="f_${faculty.getFacultyId() }" name="faculty"> <label
													class="custom-control-label"
													for="f_${faculty.getFacultyId() }">
													${faculty.getFacultyName()} </label>
											</div>
										</c:forEach>
										<!-- other dropdown -->
										<div class="custom-control custom-checkbox">
											<input type="checkbox" class="custom-control-input faculty"
												id="other_f" name="faculty" data-toggle='collapse'
												data-target='#div_other_f'> <label
												class="custom-control-label" for="other_f"> other </label>
										</div>
										<div id='div_other_f' class='collapse p-1'>
											<input type="text" class="form-control" id="input_other_f">
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="bg_title w_15pc"><b>Phone *</b></td>
							<td class="w_35pc "><input
								value="${candidateProfile.getPhone() }" type="text"
								class="form-control" id="phone" name="phone" /></td>
							<td class="bg_title w_15pc"><b>Email *</b></td>
							<td class="w_35pc"><input
								value="${candidateProfile.getEmail() }" type="text"
								class="form-control" id="email" name="email" /></td>
						</tr>
						<tr>
							<td class="bg_title w_15pc"><b>Skill</b></td>
							<td class="w_35pc "><input
								value="${candidateProfile.getSkill() }" type="text"
								class="form-control" id="skill" name="skill" /></td>
							<td class="bg_title w_15pc"><b>Graduration Year</b></td>
							<td class="w_35pc">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1"> <i
											class="far fa-calendar-alt"></i>
										</span>
									</div>
									<input updateValue="${candidateProfile.getGraduationYear() }"
										type="text" class="form-control datepicker w_80pc"
										id="gradurationYear" data-provide="datepicker"
										name="gradurationYear">
								</div>
							</td>
						</tr>
						<tr>
							<td class="bg_title w_15pc"><b>Foreign Language</b></td>
							<td class="w_35pc "><input
								value="${candidateProfile.getForeignLanguage() }" type="text"
								class="form-control" id="foreignLanguage" name="foreignLanguage" /></td>
							<td class="bg_title w_15pc"><b>Level</b></td>
							<td class="w_35pc"><input
								value="${candidateProfile.getLevel() }" type="text"
								class="form-control" id="level" name="level" /></td>
						</tr>
						<tr>
							<td class="bg_title w_15pc"><b>Note</b></td>
							<td colspan="3" class="w_35pc "><input
								value="${candidateProfile.getRemarks() }" type="text"
								class="form-control" id="note" name="note" /></td>
						</tr>
						<tr>
							<td class="bg_title w_15pc"><b>History</b></td>
							<td colspan="3" class="w_35pc bg_title">
								<div>
									<p id="history" name="history" id="history" style="float: left">
										${candidate.getHistory() }</p>
								</div>
								<div style="float: right;">
									<i class="far fa-clock fa-2x" style="text-align: right;"></i>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<!-- result -->
			<div class="tab-pane container " style="min-width: 100%;" id="result">
				<div>
					<!-- collapse entry test -->
					<div class="bg_title m-0 p-2">
						<button type="button" class="btn btn-primary collapsible ml-2"
							data-toggle="collapse" data-target="#entry_test_table"
							aria-expanded="true" aria-controls="entry_test_table">
							<i class="fas fa-caret-down"></i>
						</button>
						&emsp; <b>Entry Test</b>
					</div>
					<!-- entry test table -->
					<div class="collapse show" id="entry_test_table">
						<table class="table table-bordered" id="table_entry">
							<thead class="bg_title">
								<tr>
									<th>
										<button type="button" class="btn btn-success"
											id="btn_add_entry">
											<i class="fas fa-plus-circle"></i>
										</button>
									</th>
									<th>Time</th>
									<th>Date</th>
									<th>Language Valuator</th>
									<th>Language Point</th>
									<th>Technical Valuator</th>
									<th>Technical Point</th>
									<th>Result</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${candidate.getListEntryTest() }"
									var="entryTest">
									<tr>
										<td scope='row' class='w_3pc'>
											<p hidden="true">${entryTest.getTestId() }</p>
										</td>
										<td class='w_3pc'>
											<p class='entryTestTime' value="${entryTest.getTime() }">
												${entryTest.getTime() }</p>
										</td>
										<td class='w_17pc'>
											<div class='input-group mb-3'>
												<div class='input-group-prepend'>
													<span class='input-group-text'> <i
														class='far fa-calendar-alt'></i>
													</span>
												</div>
												<input
													updateValue="${entryTest.getDate().toString().substring(8,10)}/${entryTest.getDate().toString().substring(5,7)}/${entryTest.getDate().toString().substring(0,4)}"
													type='text'
													name="entry_test_datepicker${entryTest.getTime() }"
													class='form-control datepicker  datepicker_result'
													data-provide='datepicker' required>
											</div>
										</td>
										<td><input value="${entryTest.getLanguageValuator() }"
											class='form-control' type='text'
											name="languageValuator${entryTest.getTime() }" required>
										</td>
										<td><input value="${entryTest.getLanguageResult() }"
											class='form-control' type='number' min="0" max="100"
											name="languagePoint${entryTest.getTime() }" required>
										</td>
										<td><input value="${entryTest.getTechnicalValuator() }"
											class='form-control' type='text'
											name="technicalValuator${entryTest.getTime() }" required>
										</td>
										<td><input value="${entryTest.getTechnicalResult() }"
											class='form-control' type='number' min="0" max="100"
											name="technicalPoint${entryTest.getTime() }" required>
										</td>
										<td class='w_10pc'>
											<div class='form-group'>
												<select class='form-control'>
													<option value='1'
														${entryTest.getResult().equals("1")?"selected":"" }>Pass</option>
													<option value='0'
														${entryTest.getResult().equals("0")?"selected":"" }>Fail</option>
												</select>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div>
					<!-- collapse interview -->
					<div class="bg_title m-0 p-2 mt-4 mb-0">
						<button type="button" class="btn btn-primary collapsible ml-2"
							data-toggle="collapse" data-target="#interview_table"
							aria-expanded="true" aria-controls="interview_table">
							<i class="fas fa-caret-down"></i>
						</button>
						&emsp; <b>Interview</b>
					</div>

					<!-- interview table -->
					<div class=" collapse show" id="interview_table">
						<table class="table table-bordered" id="table_interview">
							<thead class="bg_title">
								<tr>
									<th>
										<button type="button" class="btn btn-success"
											id="btn_add_interview">
											<i class="fas fa-plus-circle"></i>
										</button>
									</th>
									<th class="w_3pc">Time</th>
									<th>Date</th>
									<th>Interviewer</th>
									<th colspan="3">Comment</th>
									<th>Result</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${candidate.getListInterview() }"
									var="interview">
									<tr>
										<td scope='row' class='w_3pc'>
											<p hidden="true">${interview.getInterviewId() }</p>
										</td>
										<td>
											<p class='interviewTime'
												value='${interview.getInterviewTime() }'>
												${interview.getInterviewTime() }</p>
										</td>
										<td class='w_17pc'>
											<div class='input-group mb-3'>
												<div class='input-group-prepend'>
													<span class='input-group-text'> <i
														class='far fa-calendar-alt'></i>
													</span>
												</div>
												<input
													updateValue="${interview.getInterviewDate().toString().substring(8,10)}/${interview.getInterviewDate().toString().substring(5,7)}/${interview.getInterviewDate().toString().substring(0,4)}"
													type='text'
													name='interview_datepicker${interview.getInterviewTime() }'
													class='form-control datepicker datepicker_result'
													data-provide='datepicker' required>
											</div>
										</td>
										<td><input value="${interview.getInterviewer() }"
											class='form-control' type='text'
											name='interviewer${interview.getInterviewTime() }' required>
										</td>
										<td colspan='3'><input
											value="${interview.getComments() }" class='form-control'
											type='text' name='comment${interview.getInterviewTime() }'
											required></td>
										<td class='w_10pc'>
											<div class='form-group'>
												<select class='form-control'>
													<option value="1"
														${interview.getResult().equals("1")?"selected":"" }>Pass</option>
													<option value="0"
														${interview.getResult().equals("0")?"selected":"" }>Fail</option>
												</select>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="mt-4">
			<button type="submmit" class="btn btn-primary" id="btn_submmit">
				Submit</button>
			<button type="button" class="btn btn-outline-dark" id="btn_close">
				<i class="fas fa-undo-alt"></i>&nbsp;Close
			</button>
		</div>
	</form>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/pageResources/js/candidate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/pageResources/js/update_candidate.js"></script>