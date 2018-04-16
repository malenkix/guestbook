var lastUpdate;

// will create a new image 3 seconds after site was fully loaded.
$(function() {
	var wall = document.getElementById("wall");
	var top = 250;
	var left = 667;
	
	var pic = document.createElement("img");
	pic.setAttribute("id", "1024");
	pic.setAttribute("class", "post");
	pic.setAttribute("src", "/apps/pinnwall/preview.jpg");
	pic.setAttribute("style", "top:" + top + "px; left:" + left + "px;");
	
	
	setTimeout(function(){ wall.appendChild(pic) }, 3000);
});