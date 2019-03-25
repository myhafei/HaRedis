package com.ha.redis;

import java.io.UnsupportedEncodingException;

public class Protocol {

//    In RESP, the type of some data depends on the first byte:
//
//    For Simple Strings the first byte of the reply is "+"
//    For Errors the first byte of the reply is "-"
//    For Integers the first byte of the reply is ":"
//    For Bulk Strings the first byte of the reply is "$"
//    For Arrays the first byte of the reply is "*"

    private final static String contast_doller = "$";
    private final static String contast_asterisk = "*";
    private final static String contast_add = "+";
    private final static String contast_split = "\r\n";

    public byte[] convertSetCommand(String operation, String key, String... params) throws UnsupportedEncodingException {
//       *3
//       $3
//       SET
//       $4
//       name
//       $2
//       china

        StringBuffer command = new StringBuffer();
        command.append(contast_asterisk).append(params.length + 2).append(contast_split);
        command.append(contast_doller).append(operation.length()).append(contast_split);
        command.append(operation).append(contast_split);
        command.append(contast_doller).append(key.length()).append(contast_split);
        command.append(key).append(contast_split);
        for (String param : params) {
            command.append(contast_doller).append(param.length()).append(contast_split);
            command.append(param).append(contast_split);
        }
        return command.toString().getBytes("utf-8");
    }
}
