$(document).ready(function(){
	var event = {};
	
	// New-User Button Event
	$("#newUser").click(function(){
		populateFields({});
		$("h2#modal-title").html("New User");
		$("#modal-submit").html("Create User");
		$("#modal-messages").html("");
		$("#nav-modal").modal("show");
		event.url="profile/new";
		event.creation=true;
	});
	
	// Edit-User Button Event
	$("#editUser").click(function(){
		var uid = document.getElementById("sidebar").getAttribute("data-uid");
		$.ajax({// Get user data to populate forms
			  type: "GET",
			  url: "/TMS/ajax/profile?id="+uid,
			  success: success
			});
		function success(response){
			populateFields(JSON.parse(response));
			$("h2#modal-title").html("Edit Profile");
			$("#modal-submit").html("Update Profile");
			$("#modal-messages").html("");
			$("#nav-modal").modal("show");
			event.creation=false;
			event.url="profile/update";
		}
	});
	
	// Event for SUBMIT from either perspective
	$("#modal-submit").click(function(){
		var request = generateUserJson();
		var data = JSON.stringify(request);
		$.ajax({
			  type: "PUT",
			  url: "/TMS/ajax/"+event.url,
			  data: data,
			  success: success,
			  error: error
			});
		function success(response){
			responseParsed = JSON.parse(response);
			//console.log(responseParsed);
			displayMessages(responseParsed);
		}
		function error(response){
			responseParsed = JSON.parse(response);
			//console.log(responseParsed);
			displayMessages(responseParsed.messages);
		}
	});
	
	
	// Helper Functions
	
	// Display messages that came with the response 
	function displayMessages(obj){
		var arr = obj.messages;
		var output = "";
		if(arr){
			for(var i=0; i<arr.length; i+=1){
				output+=arr[i]+"<br/>";
			}
			if(arr.length==0){
				if(event.creation===true){
					populateFields({});
					output+="User Created";
				}else{
					output+="User Updated";
				}
				
			}
		}else{
			output+="Username already exists";
		}
		$("#modal-messages").html(output);
	}
	
	function populateFields(user){
		$("#username").val(user.username);
		$("#password1").val(user.password);
		$("#password2").val(user.password);
		$("#email").val(user.email);
		$("#phone").val(user.phone);
		$("#bio").val(user.bio);
	}
	
	function generateUserJson(){
		var request= {};
		request.username = $("#username").val();
		request.password = $("#password1").val();
		request.password2 = $("#password2").val();
		request.email = $("#email").val();
		request.phone = $("#phone").val();
		request.bio = $("#bio").val();
		if(event.creation===false){
			request.id = document.getElementById("sidebar").getAttribute("data-uid");
		}
		
		var email_formatted = request.email.toLowerCase().trim();
		request.image = "http://www.gravatar.com/avatar/"+md5(email_formatted);
		if(event.creation===false){
			document.getElementById("editUser").setAttribute("src", request.image);
		}
		return request;
		
	}
});