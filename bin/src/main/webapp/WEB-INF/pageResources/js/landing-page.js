$(document).ready(
		function() {
			$("#dashboard-for").change(
					function() {
						var dashboardFor = $('#dashboard-for option:selected')
								.val();
						$.ajax({
							type : "GET",
							url : "/landingpage/" + dashboardFor,
							success : function(data) {
								$('#ajax_content').html(data);
								$('#dashboard-dropdown option[value='+ dashboardFor + ']')
									.attr('selected', 'selected');
							}
						});
					});

			$("#status-for").change(
					function() {
						var location = $('#location option:selected').text();
						var status = $('#status-for option:selected').text();
						var dashboardFor = $('#dashboard-for option:selected')
								.text();
						var type = $("#type-of-dashboard option:selected").text();
						if ($('#type-of-dashboard option:selected').text() == "Chart") {
							$.ajax({
					            type: "GET",
					            url: "/landingpage/content-chart/"+dashboardFor
					            	+"?location="+location+"&status="+status,
					            success: function (data) {	 
					            	drawCharts(data);
					            }
					        });
						}
						else{
							$.ajax({
								type : "GET",
								url : "/landingpage/content-table/" + dashboardFor
									+ "?location=" + location + "&status="+ status,
								success : function(data) {
									$('#dashboard-content').html(data);
								}
							});
						}
					});

			$("#location").change(
					function() {
						var location = $('#location option:selected').text();
						var status = $('#status-for option:selected').text();
						var dashboardFor = $('#dashboard-for option:selected')
								.text();
						var type = $("#type-of-dashboard option:selected").text();
						if ($('#type-of-dashboard option:selected').text() == "Chart") {
							$.ajax({
					            type: "GET",
					            url: "/landingpage/content-chart/"+dashboardFor
					            	+"?location="+location+"&status="+status,
					            success: function (data) {	 
					            	drawCharts(data);
					            }
					        });
						}
						else{
							$.ajax({
								type : "GET",
								url : "/landingpage/content-table/" + dashboardFor
								+ "?location=" + location + "&status="
								+ status,
								success : function(data) {
									$('#dashboard-content').html(data);
								}
							});
						}
					});
			$("#type-of-dashboard").change(function() {
				var location = $('#location option:selected').text();
				var dashboardFor = $('#dashboard-for option:selected').val();
				var status = $('#status-for option:selected').text();
				var type = $("#type-of-dashboard option:selected").text();
				if ($('#type-of-dashboard option:selected').text() == "Chart") {
					$.ajax({
			            type: "GET",
			            url: "/landingpage/content-chart/"+dashboardFor
			            	+"?location="+location+"&status="+status,
			            success: function (data) {	 
			            	drawCharts(data);
			            }
			        });
				}
				else{
					$.ajax({
						type : "GET",
						url : "/landingpage/content-table/" + dashboardFor
						+ "?location=" + location + "&status="
						+ status,
						success : function(data) {
							$('#dashboard-content').html(data);
						}
					});
				}
			});
			
			function drawCharts(data){          	
            	var row = "<div class=\"row\" style=\"width: 100%;\">";
            	data.forEach((item,i)=>{
            		if(item[0].skill!= "Empty"){
            		row += '<div class="col-lg-4"><div id="chartContainer'+i+'" style="height: 370px; width: 100%;"></div></div>';
            		}
            	});	            		            	
            	row+="</div>";
            	$("#dashboard-content").html(row);		            
            		var dataPoint=[];
	            	var itemData;
	            	var totalData;
	            	var chart;
	            	data.forEach((item,i)=>{ 
	            		if(item[0].skill!= "Empty"){
	            			dataPoint=[];
		            		totalData=0;
		            		item.forEach((item2,i2)=>{
		            			totalData+=item2.value;
	    					});
		            		item.forEach((item2,i2)=>{
		            			itemData={};
		            			itemData.label = item2.skill;
		            			itemData.y= item2.value/totalData*100;
		            			dataPoint.push(itemData);
	    					});
		            	 chart = new CanvasJS.Chart("chartContainer"+i, {
		            			theme: "light2",
		            			exportEnabled: true,
		            			animationEnabled: true,
		            			title: {
		            				text: item[0].status
		            			},
		            			data: [{
		            				type: "pie",
		            				startAngle: 25,
		            				toolTipContent: "<b>{label}</b>: {y}%",
		            				showInLegend: "true",
		            				legendText: "{label}",
		            				indexLabelFontSize: 16,
		            				indexLabel: "{label} - {y}%",
			                        dataPoints: dataPoint					            				
		            			}]
		            		});
		            		chart.render();
	            		}				            		
	            	});	
			}
});