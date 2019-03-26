package pers.east.learning.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnectonPool {

    //服务器IP地址
    private static final String ADDR= "127.0.0.1";

    //端口
    private static final int PORT = 6379;

    //密码
    private static String PASSWORD = "123456";

    //连接实例的最大连接数
    private static int MAX_ACTIVE = 1024;

    //控制一个pool最多有多少个状态为idle（空闲的）的 jedis实例，默认为8
    private static final int MAX_IDLE = 200;

    //等待可用连接的最大时间，单位毫秒。默认值为-1.表示永不超时，如果超过等待时间，则直接抛出JedisConnectionException
    private static final int MAX_WAIT = 10000;

    //连接超时时间
    private static final int TIMEOUT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true,则得到的jedis实例均是可用的
    private static final boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;

    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config,ADDR,PORT,TIMEOUT,PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取jedis实例
    public static Jedis getJedis(){
        try {
            if(jedisPool!=null){
                return jedisPool.getResource();
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //释放资源
    public static void close(final Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }
    }

}
