package de.nadirhelix.guestbook.post.dao.impl;

import static de.nadirhelix.guestbook.PostConstants.XML_DB_FILE_PATH;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import de.nadirhelix.guestbook.pinwall.PinwallPositioningStrategy;
import de.nadirhelix.guestbook.pinwall.PinwallUpdateStrategy;
import de.nadirhelix.guestbook.post.dao.vo.PostsVO;
import de.nadirhelix.guestbook.post.model.Post;
import de.nadirhelix.guestbook.post.util.PostMarshallingUtil;

@Component("postDao")
public class XmlPostDao extends DefaultPostDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(XmlPostDao.class);

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
			storedPosts =  PostMarshallingUtil.unmarshall(PostsVO.class, file);
			storedPosts.getPosts().forEach(this::addToPinwall);
		} catch (JAXBException e) {
			LOG.info("Haven't found file {}. Will create new file. ErrorMessage: {}", XML_DB_FILE_PATH, e.getMessage());
			LOG.debug("Stacktrace:", e);
			storedPosts = new PostsVO();
			writePostsToFile();
		}
	}
	
	private void addToPinwall(Post post) {
		int index = PinwallPositioningStrategy.pin(post.getId());
		PinwallUpdateStrategy.update(index, post.getId());
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
		super.setPinned(postId, isActive);
		writePostsToFile();
	}

	public void writePostsToFile() {
		try {
			File file = new File(XML_DB_FILE_PATH);
			FileUtils.touch(file);
			synchronized (storedPosts) {
				PostMarshallingUtil.marshal(PostsVO.class, storedPosts, file);
			}
		} catch (JAXBException | IOException e) {
			LOG.warn("Could not store post informations to disk", e);
		}
	}

}
