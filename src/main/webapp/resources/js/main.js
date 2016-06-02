function nav(location, id, role){
	var role_type = "developer"
	if(role!=1){
		role_type = "manager"
	}
	window.location.replace("/"+role_type+"/"+location+"?id="+id);
}

function updateTask(tid, e){
	var selectedStatus = document.getElementById("task"+tid).value;
	var selectedUser = document.getElementById("user"+tid).value;
	var obj = {};
	obj.task=tid;
	obj.user=selectedUser;
	obj.status=selectedStatus;
	
 	var msgId = "#msg"+tid;
	$.ajax({
		  type: "PUT",
		  url: "/story/updateTask",
		  data: JSON.stringify(obj),
		  success: success,
		  error: error
		});
	function success(e){
		e = JSON.parse(e);
		$(msgId).html("Saved!<br/>");
	}
	function error(e){
		e = JSON.parse(e);
		$(msgId).html("Failed<br/>");
	}
}



function doneTask(tid, e){
	var selectedStatus = 5;
	var selectedUser = document.getElementById("user"+tid).value;
	var obj = {};
	obj.task=tid;
	obj.user=selectedUser;
	obj.status=selectedStatus;
	
 	var msgId = "#msg"+tid;
	$.ajax({
		  type: "PUT",
		  url: "/story/updateTask",
		  data: JSON.stringify(obj),
		  success: success,
		  error: error
		});
	function success(e){
		e = JSON.parse(e);
		$(msgId).html("Saved!<br/>");
		$("#doneBtn").hide();
	}
	function error(e){
		e = JSON.parse(e);
		$(msgId).html("Failed<br/>");
	}
}

function grab(tid){
	var obj = {};
	obj.task = tid;
	$.ajax({
		  type: "PUT",
		  url: "/story/grabTask",
		  data: JSON.stringify(obj),
		  success: success,
		  error: error
		});
	function success(e){
		e = JSON.parse(e);
		resp = e;
		var grabId = 	"#grab"+tid;
		var statusId = 	"#status"+tid;
		var ownerId = 	"#owner"+tid;
		$(grabId).fadeOut();
		$(ownerId).html("<label>Owner: </label>"+e.user.username);
		
		var newStatus = "<label>Status: TODO</label><br/>";
		newStatus = "<button class='btn btn-default' onclick='reload()'>Update Status</button>";
		
		$(statusId).html(newStatus);
	}
	function error(e){}
}

function reload(){
	location.reload();
}

function taskClear(id){
	var msgId = "#msg"+id;
	$(msgId).html("");
}

$(document).ready(function(){
	$("#lanes").click(function(){
		$.ajax({
			type:"GET",
			url:"/sprint/laneData",
			success: success
		});
		function success(e){
			$("#burndown-modal").modal("hide");
			$("#lanes-modal").modal("show");
			e = JSON.parse(e);
			console.log("lane data:"+e);
			var myLaneData = [];
			for(var i=0; i<e.length; i+=1){
				var myObj = {};
				myObj.name=e[i][0];
				myObj.y=parseInt(e[i][1]);
				myLaneData.push(myObj);
			}
			
		    // Create the chart
		    $('#lane-container').highcharts({
		        chart: {type: 'column'},
		        title: { text: 'Tasks in Each Phase'},
		        xAxis: { type: 'category'},
		        yAxis: {title: {text: 'Percent of Task Volume'}},
		        legend: {enabled: false},
		        plotOptions: {
		            series: {borderWidth: 0,dataLabels: {enabled: true,format: '{point.y:.1f}%'}
		            }
		        },
		        tooltip: {
		            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
		            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
		        },
		        series: [{
		            name: 'Brands',
		            colorByPoint: true,
		            data: myLaneData
		        }]
		    });	
		}
	});

	$("#burndown").click(function(){
		$.ajax({	
			  type: "GET",
			  url: "/sprint/burnData",
			  success: success
			});
		function success(e){
			$("#lanes-modal").modal('hide');
			$("#burndown-modal").modal('show');
			e = JSON.parse(e);
			console.log("burn data:"+e);
			var myData=[];
			var myIdeal=[];
			for(var i=0; i<(e.length-1); i+=1){
				myData.push(e[i]);
			}
			myIdeal[0]=myData[0];
			myIdeal[1]=myIdeal[0];
			for(var i=2; i<(e.length-1); i+=1){
				var newNum = myIdeal[i-1]-(myIdeal[0]/9); 
				myIdeal[i]= parseFloat(newNum.toFixed(2));
			}

    	  $('#burndown-container').highcharts({
    	    title: {text: 'Burndown Chart', x: -20},
    	    colors: ['blue', 'red'],
    	    plotOptions: {
    	      line: { lineWidth: 3 },
    	      tooltip: {hideDelay: 200}
    	    },
    	    xAxis: {categories: ['Start', 'Day 1', 'Day 2', 'Day 3', 'Day 4', 'Day 5', 'Day 6','Day 7', 'Day 8', 'Day 9', 'Day 10']},
    	    yAxis: {
    	      title: {text: 'Points'},
    	      plotLines: [{value: 0, width: 1}]
    	    },
    	    tooltip: {valueSuffix: ' pts',crosshairs: true,shared: true},
    	    legend: {
    	      layout: 'vertical',
    	      align: 'right',
    	      verticalAlign: 'middle',
    	      borderWidth: 0
    	    },
    	    series: [{
    	      name: 'Ideal',
    	      color: 'rgba(255,0,0,0.25)',
    	      lineWidth: 2,
    	      data: myIdeal
    	    }, {
    	      name: 'Actual',
    	      color: 'rgba(0,120,200,0.75)',
    	      marker: {radius: 6},
    	      data: myData
    	    }]
    	  });
		}		
	});

	
});
