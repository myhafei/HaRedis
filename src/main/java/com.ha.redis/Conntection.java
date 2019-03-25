package com.ha.redis;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;

//    RESP Simple Strings
//    Simple Strings are encoded in the following way: a plus character, followed by a string that cannot contain a CR or LF character (no newlines are allowed), terminated by CRLF (that is "\r\n").
//
//    Simple Strings are used to transmit non binary safe strings with minimal overhead. For example many Redis commands reply with just "OK" on success, that as a RESP Simple String is encoded with the following 5 bytes:
//
//    "+OK\r\n"
public class Conntection {

    private String host;
    private Integer port;

    private OutputStream outputStream;
    private InputStream inputStream;

    public enum ResltType{
        Add(0,'+'),
        Doller(1,'$'),;

        private Integer code;
        private char desc;

        ResltType(Integer code, char desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer code() {
            return this.code;
        }

        public char desc() {
            return this.desc;
        }
    }


    public String execute(byte[] tagertSrc){
        Socket socket = new Socket();
        try{
            socket.setReuseAddress(true);
            socket.setKeepAlive(true);
            socket.setTcpNoDelay(true);
            socket.setSoLinger(true, 0);
            socket.connect(new InetSocketAddress(host, port), 3000);
            socket.setSoTimeout(3000);

            socket.getOutputStream().write(tagertSrc);
            socket.getOutputStream().flush();

            //获取返回值
            String result = convertResultStream(socket.getInputStream());
            System.out.println("redis command execut result:" + result);

            return result;
        }catch (Exception ex){
            System.out.println(ex);
        }finally {
            try {
                socket.getOutputStream().close();
                socket.getInputStream().close();
                socket.close();
            }catch (Exception ex){}
        }
        return null;
    }

    private static String readCRLF(InputStream is) throws Exception{
        byte b = (byte)is.read();
        StringBuilder sb = new StringBuilder();
        while(b != -1){
            if(b != '\r'){
                sb.append((char)b);
            }else{
                byte oneMore = (byte)is.read();
                if(oneMore != '\n'){
                    throw new RuntimeException("CRLF error!");
                }else{
                    break;
                }
            }
            b = (byte)is.read();
        }
        return sb.toString();
    }

    private static String convertResultStream(InputStream is) throws Exception{
        byte type = (byte)is.read();

        if(type == ResltType.Add.desc()){
            return readCRLF(is);
        }else if(type == ResltType.Doller.desc()){
            int len = initIntStream(is);
            return initByte(is, len);
        }
        return null;
    }

    private static String initByte(InputStream is, int len) throws Exception{
        byte[] bytes = new byte[len];
        for(int i = 0; i < len; i++){
            bytes[i] = (byte)is.read();
        }
        //CR
        is.read();
        //LF
        is.read();
        return new String(bytes, "UTF-8");
    }

    private static int initIntStream(InputStream is) throws Exception{
        return Integer.parseInt(readCRLF(is));
    }

    public Conntection(String host, Integer port){
        this.host = host;
        this.port = port;
    }

    public String getHots() {
        return host;
    }

    public void setHots(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

}
