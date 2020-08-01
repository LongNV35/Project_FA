$(document).ready(function() {
	$("#btn_create_candidate").click(function(e) {
		$.ajax({
			type : "get",
			url : "/viewCreateCandidate",
			success : function(data) {
				$("#ajax_content").html(data);
			}
		});
	});

	$("#btn_update_candidate").click(function(e) {
		var ids = $('input:checkbox:checked.chkCheckBoxId').map(function() {
			return this.value;
		}).get();

		if (ids.length == 0) {
			alert("Please select at least one record");
			return false;
		}
		var candidateId = $("input[type=checkbox]:checked:first").val();
		
		
		$.ajax({
			type: "post",
			data:{
				candidateId: candidateId
			},
			url : "/viewUpdateCandidate",
			success : function(data) {
				$("#ajax_content").html(data);
			}
		});
	});

	$(".link_a_candidate").click(function(e) {
		var candidateId = $(this).attr("id");
		$.ajax({
			type : "get",
			data : {
				candidateId : candidateId
			},
			url : "/viewcandidate",
			success : function(data) {
				$("#ajax_content").html(data);
			}
		});
	});
	
	setTimeout(() => {
        $(".view_candidate_message").html("");
    }, 5000);

	$("#delete").click(function() {
		var ids = $('input:checkbox:checked.chkCheckBoxId').map(function() {
			return this.value;
		}).get();

		if (ids.length == 0) {
			alert("Please select at least one record");
			return false;
		}

		var contextPath = $(this).data("contextPath");
		var listCandidateID = [];
		$("input[type=checkbox]:checked").each(function() {
			var candidateID = $(this).val();
			listCandidateID.push(candidateID)
		});

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
});

//    
// var table = document.getElementsByTagName('table')[0],
// rows = table.getElementsByTagName('tr'),
// text = 'textContent' in document ? 'textContent' : 'innerText';
//
// for (var i = 0, len = rows.length; i < len; i++){
// rows[i].children[0][text] = i + rows[i].children[0][text];
// }

$(document).ready(function() {
	$('#table_candidate').DataTable({
		"pagingType" : "first_last_numbers",
		"searching" : false,
		"scrollY" : "450px",
		"scrollCollapse" : true,
		// "info" : false,
		"language": {
		    "lengthMenu": 'Display <select>'+
		      '<option value="10">10</option>'+
		      '<option value="20">20</option>'+
		      '<option value="30">30</option>'+
		      '<option value="40">40</option>'+
		      '<option value="50">50</option>'+
		      '<option value="-1">All</option>'+
		      '</select> records'
		  },
		// "dom": '<"top"i>rt<"bottom"flp><"clear">'
		/* "paging": false */
		// "ordering": false
		"columnDefs" : [ {
			"orderable" : false,
			"targets" : 0
		}, {
			"orderable" : false,
			"targets" : 1
		} ]
	});
});

$(document).ready(function() {

	$('#checkBoxAll').click(function() {
		if ($(this).is(":checked")) {
			$('.chkCheckBoxId').prop('checked', true);
		} else {
			$('.chkCheckBoxId').prop('checked', false);
		}
	});

});
