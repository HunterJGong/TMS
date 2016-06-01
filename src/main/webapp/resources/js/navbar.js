$(document).ready(function(){
	document.getElementById("navbar-body").innerHTML = new Date().toLocaleString();
	setInterval(function(){
		document.getElementById("navbar-body").innerHTML = new Date().toLocaleString();
	}, 1000);
});

