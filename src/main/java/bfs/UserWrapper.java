package bfs;

import twitter4j.User;

public class UserWrapper {
  private User user;
  private User previous;

  public UserWrapper(User user) {
    this.user = user;
    this.previous = null;
  }

  public User getUser() {
    return user;
  }

  public User getPrevious() {
    return previous;
  }

  public void setPrevious(User newPrev) {
    previous = newPrev;
  }
}
