package de.nadirhelix.guestbook.post.dao.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.nadirhelix.guestbook.post.model.Post;

@XmlRootElement
public class PostsVO {

	private List<Post> posts;
	
	public PostsVO() {
		posts = new ArrayList<>();
	}

	public List<Post> getPosts() {
		return posts;
	}
	
	@XmlElement
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public void addPost(Post post) {
		posts.add(post);
	}
	
}
