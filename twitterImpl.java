import redis.clients.jedis.Jedis;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class twitterImpl implements twitter {

    DBUtils dbu;

    public twitterImpl() {
        this.dbu = new DBUtils();
    }

    @Override
    public void setupDB(Jedis connection) {
        dbu.fileToDB(connection);
    }

    @Override
    public void postTweet(tweet t) {
        this.dbu.j.hset(String.valueOf(t.twtTID()), "userID", String.valueOf(t.twtUID()));
        this.dbu.j.hset(String.valueOf(t.twtTID()), "timestamp", String.valueOf(t.twtTS()));
        this.dbu.j.hset(String.valueOf(t.twtTID()), "text", t.twtText());
    }

    @Override
    public List<tweet> getTimeline(Integer user_id) {
        List<tweet> allTweetsFromFollowing = new ArrayList<>();
        List<tweet> top10 = new ArrayList<>();
        List<String> following = new ArrayList<>();

        // list of following
        for (int f = 0; f < this.dbu.j.llen("follows:" + user_id); f++) {
            following.add(this.dbu.j.lpop("follows:" + user_id));
        }

        // go through the tweets
        for (int t = 1; t <= 1000000; t++) {
            // each following
            for (int fl = 0; fl < following.size(); fl++) {
            if (this.dbu.j.hget(String.valueOf(t), "userID").equals(following.get(fl))) {
                tweet twt = new tweet(t, Integer.parseInt(this.dbu.j.hget(Integer.toString(t), "userID")), LocalDateTime.parse(this.dbu.j.hget(Integer.toString(t), "timestamp")), this.dbu.j.hget(Integer.toString(t), "text"));
                allTweetsFromFollowing.add(twt);
            }
            }
            }

        for (int i = allTweetsFromFollowing.size() - 1; i > allTweetsFromFollowing.size() - 10; i--) {
            top10.add(allTweetsFromFollowing.get(i));
        }

        Collections.reverse(top10);
        return top10;
    }

    public void printTimeline(List<tweet> listTwt) {
        for (tweet t :listTwt) {
            System.out.print(t.twtToString());
        }
    }

    @Override
    public Jedis openConnection() {
        return dbu.getRedisConnection();
    }

    @Override
    public void closeConnection() {
        dbu.closeConnection();
    }

}
