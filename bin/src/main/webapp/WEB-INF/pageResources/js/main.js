$(document).ready(function() {

	$.ajax({
		type : "get",
		url : "/landingpage/Class",
		success : function(data) {
			$("#ajax_content").html(data);
		}
	});

	$("#link_dashboard").click(function(e) {
		$.ajax({
			type : "get",
			url : "/landingpage/Class",
			success : function(data) {
				console.log(data);
				$("#ajax_content").html(data);
			}
		});
	});

	$("#link_candidate_managerment").click(function(e) {
		$.ajax({
			url : "/listcandidate",
			success : function(data) {
				$("#ajax_content").html(data);
			}
		});
	});
    
    $('#myModal').on('shown.bs.modal', function() {
        $("div").removeClass("modal-backdrop");
    })
});