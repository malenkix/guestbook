package de.nadirhelix.guestbook.pinwall;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * This strategy contains and provides hard coded position values for pinned posts.
 * 
 * @author Phil
 */
public class PinwallPositioningStrategy {
	
	private static final int COLUMN_1 = 30;
	private static final int COLUMN_2 = 250;
	private static final int COLUMN_3 = 470;
	private static final int COLUMN_4 = 690;
	private static final int COLUMN_5 = 910;
	private static final int COLUMN_6 = 1130;
	private static final int COLUMN_7 = 1350;
	private static final int COLUMN_8 = 1570;
	
	private static final int ROW_1 = 20;
	private static final int ROW_2 = 240;
	private static final int ROW_3 = 460;
	private static final int ROW_4 = 680;

	private static Stack<PinwallPosition> availablePositions = new Stack<>();

	private static Map<String, PinwallPosition> occupiedPostitions = new ConcurrentHashMap<>();
	
	static {
		availablePositions.push(new PinwallPosition(COLUMN_1, ROW_1));
		availablePositions.push(new PinwallPosition(COLUMN_2, ROW_1));
		availablePositions.push(new PinwallPosition(COLUMN_3, ROW_1));
		availablePositions.push(new PinwallPosition(COLUMN_4, ROW_1));
		availablePositions.push(new PinwallPosition(COLUMN_5, ROW_1));
		availablePositions.push(new PinwallPosition(COLUMN_6, ROW_1));
		availablePositions.push(new PinwallPosition(COLUMN_7, ROW_1));
		availablePositions.push(new PinwallPosition(COLUMN_8, ROW_1));
		availablePositions.push(new PinwallPosition(COLUMN_1, ROW_2));
		availablePositions.push(new PinwallPosition(COLUMN_2, ROW_2));
		availablePositions.push(new PinwallPosition(COLUMN_3, ROW_2));
		availablePositions.push(new PinwallPosition(COLUMN_4, ROW_2));
		availablePositions.push(new PinwallPosition(COLUMN_5, ROW_2));
		availablePositions.push(new PinwallPosition(COLUMN_6, ROW_2));
		availablePositions.push(new PinwallPosition(COLUMN_7, ROW_2));
		availablePositions.push(new PinwallPosition(COLUMN_8, ROW_2));
		availablePositions.push(new PinwallPosition(COLUMN_1, ROW_3));
		availablePositions.push(new PinwallPosition(COLUMN_2, ROW_3));
		availablePositions.push(new PinwallPosition(COLUMN_3, ROW_3));
		availablePositions.push(new PinwallPosition(COLUMN_4, ROW_3));
		availablePositions.push(new PinwallPosition(COLUMN_5, ROW_3));
		availablePositions.push(new PinwallPosition(COLUMN_6, ROW_3));
		availablePositions.push(new PinwallPosition(COLUMN_7, ROW_3));
		availablePositions.push(new PinwallPosition(COLUMN_8, ROW_3));
		availablePositions.push(new PinwallPosition(COLUMN_1, ROW_4));
		availablePositions.push(new PinwallPosition(COLUMN_2, ROW_4));
		availablePositions.push(new PinwallPosition(COLUMN_3, ROW_4));
		availablePositions.push(new PinwallPosition(COLUMN_4, ROW_4));
		availablePositions.push(new PinwallPosition(COLUMN_5, ROW_4));
		availablePositions.push(new PinwallPosition(COLUMN_6, ROW_4));
		availablePositions.push(new PinwallPosition(COLUMN_7, ROW_4));
		availablePositions.push(new PinwallPosition(COLUMN_8, ROW_4));
		
		Collections.shuffle(availablePositions);
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
		String oldestPostId = occupiedPostitions.keySet().stream()
				.map(NumberUtils::toLong).sorted()
				.map(Object::toString).findFirst().orElse(StringUtils.EMPTY);
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
