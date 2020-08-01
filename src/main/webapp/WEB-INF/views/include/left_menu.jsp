<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav id="sidebar" class="">
	<div class="custom-menu">
		<button type="button" id="sidebarCollapse" class="btn btn-primary">
			<i class="fas fa-angle-double-left"></i>
		</button>
	</div>
	<div class="">
		<ul class="list-unstyled components" style="margin-top: 20%;">
			<div class="mt-4 ml-3" style="color: black;">
				<h6>Your role now is:</h6>
			</div>
			<div class="input-group" style="width: 90%; margin-left: 5%;">
				<div class="input-group mb-3">
					<select class="custom-select" id="role" name="j_role">
						<c:forEach items="${roles}" var="role">
							<option id="role" value="">${role}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<script>
				$(function() {
					// bind change event to select
					$('#role').on('change', function() {
						var url = $(this).val(); // get selected value
						if (url) { // require a URL
							window.location = url; // redirect
						}
						return false;
					});

				});
			</script>

			<div class="ml-3">
				<li class="nav-item mt-4">
					<h6>
						<a href="#" id="link_dashboard"> <i
							class="fas fa-circle fa-1x"></i>&emsp;Dashboard
						</a>
					</h6>
				</li>
				<li class="nav-item mt-4">
					<h6>
						<a href="#"> <i class="fas fa-circle fa-1x"></i>&emsp;Class
							Management
						</a>
					</h6>
				</li>
				<li class="nav-item mt-4">
					<h6>
						<a href="#" id="link_candidate_managerment"> <i
							class="fas fa-circle fa-1x"></i>&emsp;Candidate Management
						</a>
					</h6>
				</li>
				<li class="nav-item mt-4">
					<h6>
						<a href="#"> <i class="fas fa-circle fa-1x"></i>&emsp;Trainer
							Management
						</a>
					</h6>
				</li>
				<li class="nav-item mt-4">
					<h6>
						<a href="#"> <i class="fas fa-circle fa-1x"></i>&emsp;Trainee
							Management
						</a>
					</h6>
				</li>
				<li class="nav-item mt-4">
					<h6>
						<a href="#"> <i class="fas fa-circle fa-1x"></i>&emsp;General
							Setting
						</a>
					</h6>
				</li>
			</div>
		</ul>

	</div>
</nav>