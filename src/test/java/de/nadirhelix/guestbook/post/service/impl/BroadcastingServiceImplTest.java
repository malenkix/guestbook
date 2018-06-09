package de.nadirhelix.guestbook.post.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import de.nadirhelix.guestbook.pinwall.PinwallPosition;
import de.nadirhelix.guestbook.pinwall.PinwallPositioningStrategy;
import de.nadirhelix.guestbook.pinwall.PinwallUpdateStrategy;
import de.nadirhelix.guestbook.pinwall.exception.UnexpectedUpdateIdException;
import de.nadirhelix.guestbook.post.dao.PostDao;
import de.nadirhelix.guestbook.post.model.Post;
import de.nadirhelix.guestbook.post.util.PinnedPost;
import de.nadirhelix.guestbook.post.util.PinnedPosts;

@RunWith(MockitoJUnitRunner.class)
public class BroadcastingServiceImplTest {

	private static final String POST_ID = "1234";

	private static final String WISHES = "wishes";

	private static final String NAME = "name";

	@InjectMocks
	private BroadcastingServiceImpl broadcastingService;

	@Mock
	private PostDao postDao;

	private List<Post> posts;

	@Before
	public void setup() {
		posts = new ArrayList<>();
		when(postDao.getAllActivePosts()).thenAnswer(new ActivePostsAnswer());
		doAnswer(new PinPostAnswer()).when(postDao).setPinned(anyString(), anyBoolean());

	}

	@Test
	public void testAddPosts() {
		Post post = createPost(POST_ID);
		broadcastingService.addPost(post);

		assertIdsInUpdate(POST_ID);
		verify(postDao).storePost(post);
	}

	@Test
	public void testGetPinnedPosts() {
		int postCount = 5;
		List<String> postIds = createPostIdsAndUpdates(postCount);

		PinnedPosts result = broadcastingService.getPinnedPosts(0);

		assertEquals(postCount, result.getPinnedPosts().size());
		List<String> pinnedPostIds = result.getPinnedPosts().stream()
				.map(PinnedPost::getPostId).collect(Collectors.toList());
		assertTrue(pinnedPostIds.containsAll(postIds));
		assertEquals(postCount, result.getUpdateId());
		result.getPinnedPosts().stream().forEach(p -> assertNotNull(p.getPosition()));
	}

	@Test
	public void testGetPinnedPostsEmptyPosition() {
		PinwallUpdateStrategy.update(0, "");

		PinnedPosts result = broadcastingService.getPinnedPosts(0);

		assertEquals(1, result.getPinnedPosts().size());
		assertNull(result.getPinnedPosts().get(0).getPostId());
		PinwallPosition pos = result.getPinnedPosts().get(0).getPosition();
		assertEquals(0, pos.getPosX());
		assertEquals(0, pos.getPosY());
		assertEquals(0.0f, pos.getRotation(), 0);
	}
	
	@Test
	public void testGetPinnedPostsNoResult() {

		PinnedPosts resultEqualUpdateId = broadcastingService.getPinnedPosts(0);
		
		assertTrue(resultEqualUpdateId.getPinnedPosts().isEmpty());
	}
	
	@Test(expected = UnexpectedUpdateIdException.class)
	public void testGetPinnedPostsWithException() {
		broadcastingService.getPinnedPosts(1);
	}

	@Test
	public void testSetPinnedTrue() {

		broadcastingService.setPinned(POST_ID, true);

		assertIdsInUpdate(POST_ID);
		verify(postDao).setPinned(POST_ID, true);
	}

	@Test
	public void testSetPinnedFalseWithRepin() {
		int amount = PinwallPositioningStrategy.availablePositionsAmount();
		List<String> postIds = createPostIdsAndUpdates(amount + 1);
		String unpinnedPostId = postIds.remove(amount);

		broadcastingService.setPinned(unpinnedPostId, false);

		// delta is 2 because one more element was added and one was repinned.
		assertIdsInUpdate(postIds, 2);
		verify(postDao).setPinned(unpinnedPostId, false);
	}

	@Test
	public void testSetPinnedFalseWithOutRepin() {
		int amount = PinwallPositioningStrategy.availablePositionsAmount();
		List<String> postIds = createPostIdsAndUpdates(amount);
		String unpinnedPostId = postIds.remove(amount - 1);

		broadcastingService.setPinned(unpinnedPostId, false);

		// delta is 2 because one element was removed from the list and was unpinned.
		assertIdsInUpdate(postIds, 2);
		verify(postDao).setPinned(unpinnedPostId, false);
		assertEmptyPostIdInUpdate();
	}

	private List<String> createPostIdsAndUpdates(int amount) {
		List<String> result = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			String postId = POST_ID + i;
			result.add(postId);
			posts.add(createPost(postId));
			int index = PinwallPositioningStrategy.pin(postId);
			PinwallUpdateStrategy.update(index, postId);
		}
		return result;
	}

	private void assertIdsInUpdate(String postIds) {
		assertIdsInUpdate(Arrays.asList(postIds), 0);
	}

	private void assertIdsInUpdate(List<String> postIds, int delta) {
		int lastUpdateId = PinwallUpdateStrategy.getLastUpdateId();
		assertEquals(postIds.size() +  delta, lastUpdateId);

		Map<Integer, String> update = PinwallUpdateStrategy.getUpdate(0);
		postIds.stream().forEach(id -> assertIdInUpdate(id, update));
	}
	
	private void assertIdInUpdate(String postId, Map<Integer, String> update) {
		PinwallPosition pinId = PinwallPositioningStrategy
				.getPosition(postId);
		assertEquals(postId, update.get(pinId.getIndex()));
	}

	private void assertEmptyPostIdInUpdate() {
		Map<Integer, String> update = PinwallUpdateStrategy.getUpdate(PinwallUpdateStrategy.getLastUpdateId() - 1);
		assertEquals("", update.entrySet().iterator().next().getValue());
	}

	@After
	public void clear() {
		PinwallUpdateStrategy.clear();
		PinwallPositioningStrategy.clear();
	}

	private Post createPost(String postId) {
		return new Post(postId, new Date(), postId + ".png", WISHES, NAME);
	}
	
	private class PinPostAnswer implements Answer<Object> {

		@Override
		public Object answer(InvocationOnMock invocation) throws Throwable {
			String postId = (String) invocation.getArguments()[0];
			Optional<Post> post = posts.stream().filter(p -> p.getId().equals(postId)).findFirst();
			post.ifPresent(p -> p.setPinned((Boolean) invocation.getArguments()[1]));
			return null;
		}
	}
	
	private class ActivePostsAnswer implements Answer<List<Post>> {

		@Override
		public List<Post> answer(InvocationOnMock invocation) throws Throwable {
			return posts.stream().filter(Post::isPinned).collect(Collectors.toList());
		}
	}
}
