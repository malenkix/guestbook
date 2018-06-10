package de.nadirhelix.guestbook.pinwall;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This strategy contains and provides hard coded position values for pinned posts.
 * 
 * @author Phil
 */
public class PinwallPositioningStrategy {

	private static Stack<PinwallPosition> availablePositions = new Stack<>();

	private static Map<String, PinwallPosition> occupiedPostitions = new ConcurrentHashMap<>();
	
	static {
		availablePositions.push(new PinwallPosition(0, 100, 100, 10));
		availablePositions.push(new PinwallPosition(1, 350, 100, -5));
		availablePositions.push(new PinwallPosition(2, 600, 100, 3));
		availablePositions.push(new PinwallPosition(3, 850, 100, -6));
		availablePositions.push(new PinwallPosition(4, 100, 100, 0.5f));
		availablePositions.push(new PinwallPosition(5, 350, 350, 3.2f));
		availablePositions.push(new PinwallPosition(6, 600, 350, -1));
		availablePositions.push(new PinwallPosition(7, 850, 350, 22));
	}
	
	private PinwallPositioningStrategy() {
		// no instance required.
	}

	/**
	 * Returns the current {@link PinwallPosition} of the Post represented by its id.
	 * @param postId the id of the post
	 * @return the current position at the pinwall.
	 */
	public static PinwallPosition getPosition(String postId) {
		return occupiedPostitions.get(postId);
	}

	/**
	 * Sets the Post represented the given post id on the next available position and returns its index.
	 * If now position is available the oldest post will be unpinned.
	 * 
	 * @param postId the id of the post
	 * @return the index of the position
	 */
	public static int pin(String postId) {
		if (availablePositions.isEmpty()) {
			unpinOldestPost();
		}
		synchronized (availablePositions) {
			PinwallPosition position = availablePositions.pop();
			occupiedPostitions.put(postId, position);
			return position.getIndex();
		}
	}
	
	private static void unpinOldestPost() {
		String oldestPostId = occupiedPostitions.keySet().stream().sorted().findFirst().orElse("");
		unpin(oldestPostId);
	}

	public static int unpin(String postId) {
		PinwallPosition position = occupiedPostitions.remove(postId);
		synchronized (availablePositions) {
			availablePositions.push(position);
		}
		return position.getIndex();
	}

	/**
	 * Returns the amount posts that are currently pinned.
	 * 
	 * @return amount of pinned posts
	 */
	public static int pinnedPosts() {
		return occupiedPostitions.size();
	}

	/**
	 * Returns all posts that are currently pinned.
	 * 
	 * @return pinned posts
	 */
	public static Collection<String> getPinnedPosts() {
		return new HashSet<>(occupiedPostitions.keySet());
	}

	/**
	 * Returns the amount of available positions.
	 * 
	 * @return available positions amount
	 */
	public static int availablePositionsAmount() {
		return availablePositions.size();
	}

	/**
	 * Removes all pinned posts.
	 * <p><i>Please only use for testing.</i></p>
	 */
	public static void clear() {
		occupiedPostitions.entrySet().stream().forEach(e -> availablePositions.push(e.getValue()));
		occupiedPostitions.clear();
		
	}

}
