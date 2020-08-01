$(document).ready(function() {

	$("#tranfer").click(function(e) {
		var candidateId = $("#getcandidateID").val();
		var level = document.getElementById("mySelect").value;
		$.ajax({
			type : "post",
			data : {
				candidateId : candidateId,
				level : level
			},
			url : "/tranfer",
			success : function(data) {
				$("#ajax_content").html(data);
			}
		});
	});
	
	$("#btn_update").click(function(e) {
		$.ajax({
			type: "post",
			data:{
				candidateId: $('#getcandidateID').val()
			},
			url : "/viewUpdateCandidate",
			success : function(data) {
				$("#ajax_content").html(data);
			}
		});
	});
	
	$("#delete").click(function() {
		var candidateId = $("#getcandidateID").val();
		var listCandidateID = [];
			listCandidateID.push(candidateId)

		var result = confirm("Are you sure to delete?");
		if (result) {
			$.ajax({
				contentType : "application/JSON",
				type : "POST",
				url : "/deleteCandidate",
				data : JSON.stringify(listCandidateID),
				success : function(data) {
					$("#ajax_content").html(data);
				}
			});
		}
	});
	
	setTimeout(() => {
        $(".tranfer_message").html("");
    }, 5000);
	
	$('#myModal').on('shown.bs.modal', function() {
		$("div").removeClass("modal-backdrop");
	})
});