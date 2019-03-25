package com.ha.redis;


public class RedisClientTest {

    public static void main(String[] args) {


        String host = "localhost";


        Client client = new Client(host, 6379);
        String res = client.set("shanghai", "china");
        System.out.println("set result:" + res);


        res = client.get("shanghai");
        System.out.println("get result:" + res);

    }
}
