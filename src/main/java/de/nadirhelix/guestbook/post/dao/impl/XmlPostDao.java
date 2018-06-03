package de.nadirhelix.guestbook.post.dao.impl;

import static de.nadirhelix.guestbook.PostConstants.XML_DB_FILE_PATH;

import java.io.File;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import de.nadirhelix.guestbook.post.dao.vo.PostsVO;
import de.nadirhelix.guestbook.post.model.Post;

@Component("postDao")
public class XmlPostDao extends DefaultPostDao {

	private PostsVO storedPosts;

	public XmlPostDao() {
		loadPosts();
	}

	private void loadPosts() {
		readPostsFromFile();
		storedPosts.getPosts().stream().forEach(super::storePost);
	}

	private void readPostsFromFile() {
		try {
			File file = new File(XML_DB_FILE_PATH);
			JAXBContext jaxbContext = JAXBContext.newInstance(PostsVO.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			storedPosts = (PostsVO) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			// TODO log exception
			storedPosts = new PostsVO();
			writePostsToFile();
		}
	}

	@Override
	public void storePost(Post post) {
		super.storePost(post);
		synchronized (storedPosts) {
			storedPosts.addPost(post);
		}
		writePostsToFile();
	}

	@Override
	public void setPinned(String postId, boolean isActive) {
		synchronized (storedPosts) {
			Optional<Post> matchingPost = storedPosts.getPosts().stream()
					.filter(p -> StringUtils.equals(postId, p.getId()))
					.findFirst();
			matchingPost.ifPresent(m -> m.setPinned(isActive));
		}
		writePostsToFile();
	}

	public void writePostsToFile() {
		try {
			File file = new File(XML_DB_FILE_PATH);
			JAXBContext jaxbContext = JAXBContext.newInstance(PostsVO.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");			
			synchronized (storedPosts) {
				jaxbMarshaller.marshal(storedPosts, file);
			}
		} catch (JAXBException e) {
			// TODO log exception
		}
	}

}
