import redis.clients.jedis.Jedis;

import java.sql.SQLException;
import java.util.List;

public interface twitter {

    /**
     * Sets up the foll table by inserting data from follows.csv
     */
    void setupDB(Jedis connection);


    /**
     * Posts a tweet
     * @param t The tweet
     */
    void postTweet(tweet t);

    /**
     * Retrieves a user's timeline
     * @param user_id The user whose timeline is to be returned
     * @return a list of the 10 most recent tweets on a user's timeline
     */
    List<tweet> getTimeline(Integer user_id) throws SQLException;

    /**
     * Closes connection to Jedis
     */
    void closeConnection();

    /**
     * Prints the timeline
     * @param listTwt list of tweets that are some user's 10 most recent tweets
     */
    void printTimeline(List<tweet> listTwt);

    /**
     * Creates Jedis conenction
     * @return Jedis
     */
    Jedis openConnection();
}
