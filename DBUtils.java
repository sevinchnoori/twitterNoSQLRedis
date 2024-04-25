import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import redis.clients.jedis.Jedis;

public class DBUtils {

    Jedis j;


    public DBUtils() {
        this.j = this.getRedisConnection();
    }

    public Jedis getRedisConnection() {
        this.j = new Jedis("localhost", 6379);
        return this.j;
    }

    public void closeConnection() {
        this.j.close();
    }

    /**
     * Creates key-value pairs for the follows.csv file
     */
    public void fileToDB(Jedis connection) {
        String line = "";
        follows newF = new follows();

        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/sevinchnoori/Downloads/dshw1/src/follows.csv"));
            br.readLine(); // reads 1st line to skip

            while ((line = br.readLine()) != null) {
                String[] row = line.split(","); // creates a String array of the row of the CSV split by ,
                // sets parameters of follows object
                newF.setFllwUID(Integer.parseInt(row[0]));
                newF.setFllwFID(Integer.parseInt(row[1]));

                connection.lpush("follows:" + newF.fllwUID(), String.valueOf(newF.fllwFID()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
