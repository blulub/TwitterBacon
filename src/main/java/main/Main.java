package main;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class Main {
  private static String consumerKey = "Pfhdrz4ER5eVvApnIS9y75Md3";
  private static String consumerSecret = "23H7SQFBxLYYgHTLKY8FsCGYXNvbQSSCHrT7AGxZkM8NjFdNnK";
  private static String accessToken = "215051822-ihLSGPSJLtErg8lA8cI9ZFQsSqz2ucuBmIDjdQrx";
  private static String accessTokenSecret = "uqRm8vimYBptY1tfaenabKfS20Uqcgvr1ezBNcUTH4qaN";

  private String[] args;
  public static Twitter twitter;

  private Main(String[] args) {
    this.args = args;
  }


  public static void main(String[] args) {
    new Main(args).run();
  }

  private void run() {
    try {
      Twitter twitterInst = new TwitterFactory().getInstance();

      twitterInst.setOAuthConsumer(consumerKey, consumerSecret);
      AccessToken access = new AccessToken(accessToken,
          accessTokenSecret);

      twitterInst.setOAuthAccessToken(access);

      twitter = twitterInst;


      for (Status stat : twitter.getFavorites()) {
        System.out.println(stat.getText());
      }

      for (Long id : twitter.getFollowersIDs(-1).getIDs()) {
        System.out.println(id);
      }

      System.out.println("getting other");

      for (Long id : twitter.getFollowersIDs(495160060, -1).getIDs()) {
        System.out.println(id);
      }

      System.out.println(twitter.showUser(495160060).getName());
      System.out.println(twitter.showUser(495160060).getScreenName());


    } catch (TwitterException te) {
      te.printStackTrace();
    }
  }
}
