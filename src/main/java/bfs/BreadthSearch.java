package bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.google.common.collect.Lists;

import main.Main;
import twitter4j.TwitterException;
import twitter4j.User;
import utilities.CursorUtil;

public class BreadthSearch {

  private User start;
  private User end;

  private Map<Long, UserWrapper> users;
  private Map<Long, Long> previous;


  public BreadthSearch(User startUser, User endUser) throws TwitterException {
    this.start = startUser;
    this.end = endUser;
    this.users = new HashMap<>();
    this.previous = new HashMap<>();
    users.put(start.getId(), new UserWrapper(start));
    users.put(end.getId(), new UserWrapper(end));
    previous.put(start.getId(), null);
    previous.put(end.getId(), null);
    startSearch();
  }

  private Iterable<User> startSearch() throws TwitterException {
    if (start.getId() == end.getId()) {
      return Lists.newArrayList(start);
    }

    int numCalls = 0;

    List<Long> q = new LinkedList<>();
    for (Long id : CursorUtil.getAllFollowers(start.getId())) {
      previous.put(id, start.getId());
      q.add(id);
    }
    q.add(Long.MIN_VALUE);

    while (!q.isEmpty()) {
      Long current = q.remove(0);
      if (current == Long.MIN_VALUE) {
        numCalls++;
        continue;
      }

      if (current == end.getId()) {
        Stack<User> path = new Stack<>();
        while (current != start.getId()) {
          if (current == end.getId()) {
            path.push(end);
          } else {
            path.push(Main.twitter.showUser(current));
          }
          current = previous.get(current);
        }
        path.push(start);
        return path;
      }

      if (numCalls >= 100) {
        return new LinkedList<>();
      }

      for (Long id : CursorUtil.getAllFollowers(current)) {
        if (previous.get(id) != null) {
          continue;
        }
        previous.put(id, current);
        q.add(id);
      }
      q.add(Long.MIN_VALUE);
    }

    return new LinkedList<>();
  }






}
