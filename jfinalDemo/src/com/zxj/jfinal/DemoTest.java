package com.zxj.jfinal;

import com.jfinal.plugin.redis.Redis;
import com.jfinal.plugin.redis.RedisPlugin;

/**
 * Created by xiaojun.zhang on 2015/11/22.
 */
public class DemoTest {

    public static void main(String[] args){
        RedisPlugin redis = new RedisPlugin("redis","127.0.0.1");
        redis.start();

        Redis.use().set("key", "value");
        String value = Redis.use().get("key");
        System.out.print(value);
    }
}
