var lastUpdate = 0;
var failureCounter = 0;

(function update(){
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
		    	setTimeout(update(), 10000);
		    }
		})
		
	}, 1000);
})();
	   
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
	var post = document.getElementById(pinnedPost.index);
	var wall = document.getElementById("wall");
	var isNew = false;
	if (!post) {
		post = document.createElement("img");
		isNew = true;
	}	
	if (!pinnedPost.postId) {
		wall.remove(pic);
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
		
	post.setAttribute("id", pinnedPost.index);
	post.setAttribute("class", "post");
	post.setAttribute("src", "../images/posts/" + pinnedPost.postId);
	post.setAttribute("style", "top:" + top + "px; left:" + left + "px; transform:rotate(" + rot + "deg)");
}
