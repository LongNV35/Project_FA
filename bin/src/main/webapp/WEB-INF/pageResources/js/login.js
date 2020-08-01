$(document).ready(function() {
	$("#event-nav").click(function() {

		$(this).hide(1000);
		$("#nav-event").show(1000);
		$("#content-left").attr("class", "content-left col-lg-1");
		$("#content-right").attr("class", "content-right col-lg-11");
	});
});

$(document).ready(function() {
	$("#nav-event").click(function() {
		$(this).hide(1000);
		$("#event-nav").show(2000);
		$("#content-left").attr("class", "content-left col-lg-2");
		$("#content-right").attr("class", "content-right col-lg-10");
	});
});

$(document).ready(function(){
		$("#loginForm").validate({
            rules: {
                j_username: {
                    required: true
                },
                j_password: {
                    required: true
                }
            },
            messages: {
                j_username: {
                    required: "Username must be not empty!"
                },
                j_password: {
                    required: "Password must be not empty!"
                }
            },
            errorElement : 'div',
            errorLabelContainer: '.errorTxt'
        });
});

