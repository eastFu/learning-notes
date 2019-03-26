package pers.east.learning.redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class RedisUtils {

    private Jedis jedis;

    public void init(){
        jedis = RedisConnectonPool.getJedis();
    }

    public void testBasic(){
        System.out.println(jedis.set("test","2019年3月26日 20:52:39"));
        System.out.println(jedis.get("test"));
    }

    public void testList(){

    }

    public void testMap(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("name", "yc");
        map.put("age", "22");
        map.put("qq", "454645");
        System.out.println(jedis.hmset("user", map));
    }

    public void testSet(){

    }
    public void testSort(){

    }

    public static void main(String[] args) {

    }
}
