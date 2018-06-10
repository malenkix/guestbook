package de.nadirhelix.guestbook.pinwall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.util.Pair;

import de.nadirhelix.guestbook.pinwall.exception.UnexpectedUpdateIdException;

/**
 * Strategy to list all updates that makes the update process for the pinwall itself slimmer.
 * The saved updates contain an index and a post id. From that the changes between to updates can be restored.
 * 
 * @author Phil
 */
public class PinwallUpdateStrategy {

	private static List<Pair<Integer, String>> updateLog = Collections.synchronizedList(new ArrayList<>());
	
	private PinwallUpdateStrategy() {
		// no instance required.
	}
	
	public static int getLastUpdateId() {
		return updateLog.size();
	}

	/**
	 * Return a Map of indices and post ids that represent the changes between the last update an now.
	 * 
	 * @param lastUpdate the id of the last update
	 * @return {@link Map} of indices and post ids
	 */
	public static Map<Integer, String> getUpdate(int lastUpdate) {
		Map<Integer, String> result = new HashMap<>();
		switch(Integer.valueOf(updateLog.size()).compareTo(lastUpdate)) {
		case 1 : 
			createUpdateReport(result, lastUpdate);
			break;
		case -1 : 
			throw new UnexpectedUpdateIdException(updateLog.size());
		default: break;	
		}
		return result;
	}

	private static void createUpdateReport(Map<Integer, String> result, int lastUpdate) {
		for (int i = lastUpdate; i < updateLog.size(); i++) {
			Pair<Integer, String> updateElement = updateLog.get(i);
			result.put(updateElement.getFirst(), updateElement.getSecond());
		}
	}

	/**
	 * Adds an update ent
	 * @param index
	 * @param postId
	 */
	public static void update(int index, String postId) {
		updateLog.add(Pair.of(index, postId));
	}

	/**
	 * Removes all updates.
	 * <p><i>Please only use for testing.</i></p>
	 */
	public static void clear() {
		updateLog.clear();
	}

}
