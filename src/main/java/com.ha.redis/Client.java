package com.ha.redis;

public class Client {

    private String host;
    private Integer port;

    private Conntection conntection;
    private Command command;

    public Client(String host, Integer port) {
        this.conntection = new Conntection(host, port);
        this.command = new Command(conntection);
    }

    public String set(String key, String value) {
        return command.set(key, value);

    }

    public String get(String key) {
        return command.get(key);
    }
}
