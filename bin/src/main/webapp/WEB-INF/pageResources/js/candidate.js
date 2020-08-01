$(document).ready(function () {
	
	// common setting for datepicker
    $('.datepicker').datepicker({
        todayHighlight: true,
        autoclose: true,
        dateFormat: 'dd-mm-yy'
    }).on('change', function() {
        $(this).valid();  
    });
    
    // set value and set readonly for application date
    $('#applicationDate').datepicker("setDate", new Date());
    $("#applicationDate").prop('disabled', true);
    $("#applicationDate").datepicker( "option", "dateFormat", "dd-mm-yy" );
    $('#dateofbirth').datepicker('option', 'maxDate', new Date());
    $("#dateofbirth").datepicker( "option", "dateFormat", "dd-mm-yy" );
    // set graduration year not future and format
    $('#gradurationYear').datepicker('option', 'maxDate', new Date());
    $('#gradurationYear').datepicker( "option", "dateFormat", "dd-mm-yy" );
    $('.datepicker_result').datepicker('option', 'maxDate', new Date());

    // this function will be call when click add or delete button in result tab
	// page
    function setThemeDatepicker(){
        $('.datepicker').datepicker({
            todayHighlight: true,
            autoclose: true,
            dateFormat: 'dd-mm-yy'
        }).on('change', function() {
            $(this).valid();  
        });
        // set value and set readonly for application date
        $('#applicationDate').datepicker("setDate", new Date());
        $("#applicationDate").prop('disabled', true);
        $("#applicationDate").datepicker( "option", "dateFormat", "dd-mm-yy" );
        $('#dateofbirth').datepicker('option', 'maxDate', new Date());
        $("#dateofbirth").datepicker( "option", "dateFormat", "dd-mm-yy" );
        // set graduration year not future and format
        $('#gradurationYear').datepicker('option', 'maxDate', new Date());
        $('#gradurationYear').datepicker( "option", "dateFormat", "dd-mm-yy" );
        $('.datepicker_result').datepicker('option', 'maxDate', new Date());
    }

    
    // add new entry row for input data
    $('#btn_add_entry').click(function () {
    	var entryTestTime=parseInt($('.entryTestTime').last().text()) + 1;
    	if(isNaN(entryTestTime)==true) entryTestTime=1;
        var content =
        	"<tr> " +
        		"<td scope='row' class='w_3pc'>" +
        			"<button type='button' class='btn btn-danger btn_delete_entry'>" +
        				" <i class='far fa-trash-alt'></i>" +
    				" </button>" +
				"</td>" +
				" <td class='w_3pc'>" +
					"<p class='entryTestTime' value='"+entryTestTime+"'>"+entryTestTime+"</p>" +
				"</td>" +
				" <td class='w_17pc'>" +
					" <div class='input-group mb-3'>" +
						" <div class='input-group-prepend'>" +
							" <span class='input-group-text'>" +
								" <i class='far fa-calendar-alt'></i>" +
							" </span>" +
						" </div>" +
						" <input type='text' name='entry_test_datepicker"+entryTestTime+"' class='form-control datepicker" +
						" datepicker_result' data-provide='datepicker' required>" +
					" </div>" +
				" </td>" +
				" <td>" +
					" <input class='form-control' type='text' name='languageValuator"+entryTestTime+"' required>" +
				" </td>" +
				" <td>" +
					" <input class='form-control' type='number' min='0' max='100' name='languagePoint"+entryTestTime+"' required>" +
				" </td>" +
				" <td>" +
					"<input class='form-control' type='text' name='technicalValuator"+entryTestTime+"' required>" +
				"</td>" +
				" <td>" +
					"<input class='form-control' type='number' min='0' max='100' name='technicalPoint"+entryTestTime+"' required>" +
				"</td>" +
				" <td class='w_10pc'>" +
					" <div class='form-group'>" +
						" <select class='form-control'>" +
							" <option value='1'>Pass</option>" +
							" <option value='0'>Fail</option>" +
						" </select>" +
					" </div>" +
				" </td>" +
			" </tr>";
        
        
        $('#table_entry tbody').append(content);
        setThemeDatepicker();
    });

    // add new interview row for input data
    $('#btn_add_interview').click(function () {
    	var interviewTime=parseInt($('.interviewTime').last().text()) + 1;
    	if(isNaN(interviewTime)==true) interviewTime=1;
        var content =
        	"<tr>" +
        		" <td scope='row' class='w_3pc'>" +
        			" <button type='button' class='btn btn-danger btn_delete_interview'>" +
        				" <i class='far fa-trash-alt'></i>" +
    				" </button>" +
				" </td>" +
				" <td>" +
					"<p class='interviewTime' value='"+interviewTime+"'>"+interviewTime+"</p>" +
				" </td>" +
				" <td class='w_17pc'>" +
					" <div class='input-group mb-3'>" +
						" <div class='input-group-prepend'>" +
							" <span class='input-group-text'>" +
								" <i class='far fa-calendar-alt'></i>" +
							" </span>" +
						" </div>" +
						" <input type='text' name='interview_datepicker"+interviewTime+"'" +
								" class='form-control datepicker datepicker_result'data-provide='datepicker' required>" +
					" </div>" +
				" </td>" +
				" <td>" +
					" <input class='form-control' type='text' name='interviewer"+interviewTime+"' required>" +
				" </td>" +
				" <td colspan='3'>" +
					" <input class='form-control' type='text' name='comment"+interviewTime+"' required>" +
				" </td>" +
				" <td class='w_10pc'>" +
					" <div class='form-group'>" +
						" <select class='form-control'>" +
							" <option value='1'>Pass</option>" +
							" <option value='0'>Fail</option>" +
						" </select>" +
					" </div>" +
				" </td>" +
			" </tr>";
        
        $('#table_interview tbody').append(content);
        setThemeDatepicker();
    });

    // delete a entry row when click button delete entry test
    $("#table_entry").on('click', '.btn_delete_entry', function () {
        $(this).closest('tr').remove();
        var entryTestLength=$('.entryTestTime').length;
        $('.entryTestTime').each(function(index, value) {
        	  $(this).text(index+1);
    	});
        setThemeDatepicker();
    });

    // delete a interview row when click button delete interview
    $("#table_interview").on('click', '.btn_delete_interview', function () {
        $(this).closest('tr').remove();
        var interviewLength=$('.interviewTime').length;
        $('.interviewTime').each(function(index, value) {
        	  $(this).text(index+1);
    	});
        setThemeDatepicker();
    });
    
    // auto create account after input full name
    // example Nguyên Minh Thanh Thai
    $( "#name" ).change(function() {
    	var name = $('#name').val();
    	var names=name.split(" ");
    	
    	// T
    	var lastName=names[names.length-1].charAt(0).toUpperCase();
    	
    	// N
    	var surName=names[0].charAt(0).toUpperCase();
    	
    	// MT
    	var middleName="";
    	for (i = 1; i < names.length -1; i++) {
    		  middleName += names[i].charAt(0).toUpperCase();
		}
    	
    	var userNameTemp= lastName + surName + middleName;
    	var userNameFinal="";	
    	
    	// TNMT1 after call ajax to server
    	$.ajax({
            type: "post",
            data: {
            	userNameTemp
            },
            url: "/getUserNameCount",
            success: function(data) {
            	userNameFinal = userNameTemp + data;
            	$('#account').text(userNameFinal);
            }
        });
    });
    
    // university check only one
    $('.university').click(function() {
        $('.university').not(this).prop('checked', false);
        if(this.id !='other_u'){
        	$('#div_other_u').removeClass("show");
        }
        
    });
    
    // faculty check only one
    $('.faculty').click(function() {
        $('.faculty').not(this).prop('checked', false);
        if(this.id !='other_f'){
        	$('#div_other_f').removeClass("show");
        }
    });
    
    // set university name to input after checked ckeckbox
    $("#unversity_drop").on('hidden.bs.collapse', function(){
    	$('#input_university').val("");
    	$("input:checkbox[name=university]:checked").each(function(){
    		if(this.id =='other_u'){
    			var universityName= $('#input_other_u').val();
    			$('#input_university').val(universityName);
    			$('#input_university').attr('universityId', '-1');
            }
    		else{
    			var universityName= $(this).attr('universityName');
    			$('#input_university').val(universityName);
    			var universityId=$(this).attr('universityId');
    			$('#input_university').attr('universityId', universityId);
    		}
    	});
    });
    
    // set faculty name to input after checked ckeckbox
    $("#faculty_drop").on('hidden.bs.collapse', function(){
    	$('#input_faculty').val("");
    	$("input:checkbox[name=faculty]:checked").each(function(){
    		if(this.id =='other_f'){
    			var facultyName= $('#input_other_f').val();
    			$('#input_faculty').val(facultyName);
    			$('#input_faculty').attr('facultyId', '-1');
            }
    		else{
    			var facultyName= $(this).attr('facultyName');
    			$('#input_faculty').val(facultyName);
    			var facultyId=$(this).attr('facultyId');
    			$('#input_faculty').attr('facultyId', facultyId);
    		}
    	});
    });
    
    // validate VietNamese phone
    $.validator.addMethod("validPhone", function(value, element) {
    	// VietNamese phone regex
    	var pattern = /((09|03|07|08|05)+([0-9]{8})\b)/g;
    	return pattern.test(value);
    }, "Please input VietNamese correct phone number( 0x xxxx xxxx ).");
    
    // validate email
    $.validator.addMethod("validEmail", function(value, element) {
        var pattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
        return pattern.test(value);
    }, "Please input your correct email address( x@x.x ).");
    
    // validate form and submit formdata to server by ajax in case create new
	// candidate
    $("#create_candidate_form").validate({
        errorClass: "error_validate",
        errorElement: "em",
        rules: {
            channel_dropdown: {
                required: true
            },
            site_dropdown: {
                required: true
            },
            name: {
                required: true
            },
            gender: {
                required: true
            },
            dateOfBirth: {
                required: true,
            },
            input_university: {
                required: true,
            },
            input_faculty: {
                required: true
            },
            phone: {
                required: true,
                number: true,
                validPhone: true,
                minlength: 10,
                maxlength: 14
            },
            email: {
                required: true,
                validEmail: true
            },
            skill: {
                required: true
            },
            gradurationYear: {
                required: true
            }
        },
        messages: {
            channel_dropdown: {
                required: "Please select a channel"
            },
            site_dropdown: {
                required: "Please select a site location"
            },
            name: {
                required: "Please input your name"
            },
            gender: {
                required: "Please select your gender"
            },
            dateOfBirth: {
                required: "Please select your date of birth",
            },
            input_university: {
                required: "Please select your university",
            },
            input_faculty: {
                required: "Please select your faculty"
            },
            phone: {
                required: "Please input your phone number",
                number: "Please only input number type",
                minlength: "Min length of phone number is 10",
                maxlength: "Min length of phone number is 14"
            },
            email: {
                required: "Please input your email",
                email: "Please input your correct email"
            },
            skill: {
                required: "Please input your skill"
            },
            gradurationYear: {
                required: "Please select your graduration year"
            }
        },
        submitHandler: function (form, event) {
            event.preventDefault();
            if (confirm('Are you sure to submit ?')) {
	            var form = $('#create_candidate_form')[0];
	            var data = new FormData(form);
	            
	            var employeeId=$('#employeeId').text();
	            data.append("employeeId", employeeId);
	            
	            var type=$('#type').text();
	            data.append("type", type);
	            
	            var status=$('#status').text();
	            data.append("status", status);
	            
	            var account=$('#account').text();
	            data.append("account", account);
	            
	            var universityId= $('#input_university').attr('universityId');
	            data.append("universityId", universityId);
	            
	            if(universityId == "-1"){
	            	var universityOtherName= $('#input_other_u').val();
	            	data.append("universityOtherName", universityOtherName);
	            }
	            
	            var facultyId= $('#input_faculty').attr('facultyId');
	            data.append("facultyId", facultyId);
	            
	            if(facultyId == "-1"){
	            	var facultyOtherName= $('#input_other_f').val();
	            	data.append("facultyOtherName", facultyOtherName);
	            }
	            
	            var datepicker_application_date=$('#applicationDate').val();
	            data.append("applicationDate", datepicker_application_date);
	            
	            var listEntryTest = [];
	            $("#table_entry tr").slice(1).each(function () {
	            	var rowEntryTest= $(this).find("td:eq(2)").find("input").val()
	            													  		 + "&and&" + $(this).find("td:eq(3)").find("input").val()
	            													  		 + "&and&" + $(this).find("td:eq(4)").find("input").val()
	            													  		 + "&and&" + $(this).find("td:eq(5)").find("input").val()
	            													  		 + "&and&" + $(this).find("td:eq(6)").find("input").val()
	            													  		 + "&and&" + $(this).find("td:eq(7)").find("select").val()
	            	listEntryTest.push(rowEntryTest);
	            });
	            data.append("listEntryTest", listEntryTest);
	            
	            var listInterview = [];
	            $("#table_interview tr").slice(1).each(function () {
	            	var rowEntryTest= $(this).find("td:eq(2)").find("input").val()
            													  			 + "&and&" + $(this).find("td:eq(3)").find("input").val()
            													  			 + "&and&" + $(this).find("td:eq(4)").find("input").val()
            													  			 + "&and&" + $(this).find("td:eq(5)").find("select").val()
	            	listInterview.push(rowEntryTest);
	            });
	            data.append("listInterview", listInterview);
	            
	            for(var pair of data.entries()) {
	        	   console.log(pair[0]+ ', '+ pair[1]); 
	        	}
	
	            $.ajax({
	                type: "post",
	                data: data,
	                processData: false,
	                contentType: false,
	                enctype: "multipart/form-data",
	                url: "/createCandidate",
	                success: function (data) {
	                    $("#ajax_content").html(data);
	                }
	            });
            }
        }
    });
    
  // validate form and submit formdata to server by ajax in case update a
	// candidate
    $("#update_candidate_form").validate({
        errorClass: "error_validate",
        errorElement: "em",
        rules: {
            channel_dropdown: {
                required: true
            },
            site_dropdown: {
                required: true
            },
            name: {
                required: true
            },
            gender: {
                required: true
            },
            dob: {
                required: true,
            },
            input_university: {
                required: true,
            },
            input_faculty: {
                required: true
            },
            phone: {
                required: true,
                number: true,
                validPhone: true,
                minlength: 10,
                maxlength: 14
            },
            email: {
                required: true,
                validEmail: true
            },
            skill: {
                required: true
            },
            graduration_year: {
                required: true
            }
        },
        messages: {
            channel_dropdown: {
                required: "Please select a channel"
            },
            site_dropdown: {
                required: "Please select a site location"
            },
            name: {
                required: "Please input your name"
            },
            gender: {
                required: "Please select your gender"
            },
            dob: {
                required: "Please select your date of birth",
            },
            input_university: {
                required: "Please select your university",
            },
            input_faculty: {
                required: "Please select your faculty"
            },
            phone: {
                required: "Please input your phone number",
                number: "Please only input number type",
                minlength: "Min length of phone number is 10",
                maxlength: "Min length of phone number is 14"
            },
            email: {
                required: "Please input your email",
                email: "Please input your correct email"
            },
            skill: {
                required: "Please input your skill"
            },
            graduration_year: {
                required: "Please select your graduration year"
            }
        },
        submitHandler: function (form, event) {
            event.preventDefault();
            if (confirm('Are you sure to submit ?')) {
	            var form = $('#update_candidate_form')[0];
	            var data = new FormData(form);
	            
	            var employeeId=$('#employeeId').text();
	            data.append("employeeId", employeeId);
	            
	            var type=$('#type').text();
	            data.append("type", type);
	            
	            var status=$('#status').text();
	            data.append("status", status);
	            
	            var account=$('#account').text();
	            data.append("account", account);
	            
	            var universityId= $('#input_university').attr('universityId');
	            data.append("universityId", universityId);
	            
	            if(universityId == "-1"){
	            	var universityOtherName= $('#input_other_u').val();
	            	data.append("universityOtherName", universityOtherName);
	            }
	            
	            var facultyId= $('#input_faculty').attr('facultyId');
	            data.append("facultyId", facultyId);
	            
	            if(facultyId == "-1"){
	            	var facultyOtherName= $('#input_other_f').val();
	            	data.append("facultyOtherName", facultyOtherName);
	            }
	            
	            var datepicker_application_date=$('#applicationDate').val();
	            data.append("applicationDate", datepicker_application_date);
	            
	            var listEntryTest = [];
	            $("#table_entry tr").slice(1).each(function () {
	            	var rowEntryTest= $(this).find("td:eq(2)").find("input").val()
	            													  		 + "&and&" + $(this).find("td:eq(3)").find("input").val()
	            													  		 + "&and&" + $(this).find("td:eq(4)").find("input").val()
	            													  		 + "&and&" + $(this).find("td:eq(5)").find("input").val()
	            													  		 + "&and&" + $(this).find("td:eq(6)").find("input").val()
	            													  		 + "&and&" + $(this).find("td:eq(7)").find("select").val()
	            													  		 + "&and&" + $(this).find("td:eq(0)").text().trim();
	            	listEntryTest.push(rowEntryTest);
	            });
	            data.append("listEntryTest", listEntryTest);
	            
	            var listInterview = [];
	            $("#table_interview tr").slice(1).each(function () {
	            	var rowEntryTest= $(this).find("td:eq(2)").find("input").val()
            													  			 + "&and&" + $(this).find("td:eq(3)").find("input").val()
            													  			 + "&and&" + $(this).find("td:eq(4)").find("input").val()
            													  			 + "&and&" + $(this).find("td:eq(5)").find("select").val()
            													  			 + "&and&" + $(this).find("td:eq(0)").text().trim();
	            	listInterview.push(rowEntryTest);
	            });
	            data.append("listInterview", listInterview);
	            
	            for(var pair of data.entries()) {
	        	   console.log(pair[0]+ ', '+ pair[1]); 
	        	}
	
	            $.ajax({
	                type: "post",
	                data: data,
	                processData: false,
	                contentType: false,
	                enctype: "multipart/form-data",
	                url: "/updateCandidate",
	                success: function (data) {
	                    $("#ajax_content").html(data);
	                }
	            });
            }
        }
    });
    
    // call ajax when click close button
    $("#btn_close").click(function(e) {
		$.ajax({
			type : "get",
			url : "/listcandidate",
			success : function(data) {
				$("#ajax_content").html(data);
			}
		});
	});
    setTimeout(function(){$('#message_content').hide();}, 4000);
}) 