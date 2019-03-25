package com.ha.redis;

import java.net.ServerSocket;
import java.net.Socket;

public class RedisServerTest {

    public static void main(String[] args){

      try {
          ServerSocket serverSocket = new ServerSocket(8088);
          Socket socket = serverSocket.accept();
          byte[] bytes = new byte[1024];
          socket.getInputStream().read(bytes);
          System.out.println(new String(bytes));
      }catch (Exception ex){
          System.out.println(ex.getMessage());
      }
    }
}
