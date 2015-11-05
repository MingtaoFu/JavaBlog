package DA;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class JedisDA {
    private static Jedis jedis;

    public static void initialize() {
        jedis = new Jedis("localhost", 6379);
        jedis.select(3);
    }

    public static String find(String key) {
        return jedis.get(key);
    }

    public static void set(String key, String value) {
        jedis.set(key, value);
    }
}
