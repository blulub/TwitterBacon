package utilities;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

import main.Main;
import twitter4j.IDs;
import twitter4j.TwitterException;

public class CursorUtil {

  /**
   * Method to get all the followers of a specified user.
   *
   * Concatenates all the pages into a single linked list.
   *
   * @param id             Long of the userID
   * @return               LinkedList of all the users following userID
   * @throws TwitterException
   */
  public static List<Long> getAllFollowers(long id) throws TwitterException {
    LinkedList<Long> output = Lists.newLinkedList();
    long cursor = -1;
    while (cursor != 0) {
      IDs followers = Main.twitter.getFollowersIDs(id, cursor);
      for (Long follower : followers.getIDs()) {
        output.add(follower);
      }
      cursor = followers.getNextCursor();
    }
    return output;
  }

}
