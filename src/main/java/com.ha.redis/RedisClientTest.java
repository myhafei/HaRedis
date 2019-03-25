package com.ha.redis;

import redis.clients.jedis.Jedis;

public class RedisClientTest {

    public static void main(String[] args) {

//        Conntection conntection = new Conntection("94.191.95.57", 6379);
//        Command command = new Command(conntection);
//        String res = command.set("huhu", "fast");
//        System.out.println(res);
//
//        String vals = command.get("huhu");
//        System.out.println("redis get key-valus:" + vals);

        String host = "94.191.95.57";


        Client client = new Client(host, 6379);
        String res = client.set("shanghai", "china");
        System.out.println("set result:" + res);


        res = client.get("shanghai");
        System.out.println("get result:" + res);

    }
}
