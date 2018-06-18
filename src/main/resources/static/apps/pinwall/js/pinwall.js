var lastUpdate = 0;
var failureCounter = 0;

(function startup(){
	update();
})();

function update() {
	setTimeout(function(){
		$.ajax({
		    url: "/pinwall/update",
		    type: 'POST',
		    data: { lastUpdate: lastUpdate },
		    success: function(data){
			   	append(data);
				update();
			},
		    error: function (err) {
		    	if (err.status == "400" || failureCounter >= 10) {
		    		location.reload();
		    	}
		    	failureCounter++;
		    	setTimeout(update(), 60000);
		    }
		})
		
	}, 5000);
}
	   
function append(data) {
	lastUpdate = data.updateId;
	if (data.pinnedPosts) {
		data.pinnedPosts.forEach(function(element){
			pin(element);
		});
	}
};
		
function pin(pinnedPost) {
	if (!pinnedPost) {
		return;
	}
	var post = document.getElementById(pinnedPost.position.index);
	var wall = document.getElementById("wall");
	var isNew = false;
	if (!post) {
		post = document.createElement("img");
		isNew = true;
	}	
	if (!pinnedPost.postId) {
		wall.remove(post);
		return;
	}
	
	populatePost(pinnedPost, post);
	
	if (isNew) {
		wall.appendChild(post);
	}
}

function populatePost(pinnedPost, post) {
	var left = pinnedPost.position.posX;
	var top = pinnedPost.position.posY;
	var rot = pinnedPost.position.rotation;
		
	post.setAttribute("id", pinnedPost.position.index);
	post.setAttribute("class", "post");
	post.setAttribute("src", "../posts/" + pinnedPost.postId);
	post.setAttribute("style", "top:" + top + "px; left:" + left + "px; transform:rotate(" + rot + "deg)");
}