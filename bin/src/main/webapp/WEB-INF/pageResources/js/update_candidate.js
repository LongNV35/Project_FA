$(document).ready(function(){
	var dOB = $('#dateofbirth').attr('updateValue');
	$('#dateofbirth').datepicker("setDate", new Date(dOB));
	
	var graduYear = $('#gradurationYear').attr('updateValue');
	$('#gradurationYear').datepicker("setDate", new Date(graduYear));
	
	var dateResult = $('.datepicker_result').attr('updateValue');
	$('.datepicker_result').datepicker("setDate", new Date(dateResult));
	
    setTimeout(function(){$('#message_content').hide();}, 4000);
})