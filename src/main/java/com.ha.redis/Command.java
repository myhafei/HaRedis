package com.ha.redis;


public class Command {

    public enum OperationType {
        set(0, "SET"),
        get(1, "GET"),
        ;

        private Integer code;
        private String desc;

        OperationType(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer code() {
            return this.code;
        }

        public String desc() {
            return this.desc;
        }
    }

    private Conntection conntection;

    public Command(Conntection conntection) {
        this.conntection = conntection;
    }

    public String set(String key, String value) {
        Protocol protocol = new Protocol();
        try {
            byte[] targetCmd = protocol.convertSetCommand(OperationType.set.desc, key, value);
            return conntection.execute(targetCmd);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public String get(String key) {
        Protocol protocol = new Protocol();
        try {
            byte[] targetCmd = protocol.convertSetCommand(OperationType.get.desc, key);
            return conntection.execute(targetCmd);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
}
