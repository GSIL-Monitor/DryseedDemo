package com.dryseed.dryseedapp.test.socket;

import java.net.*;
import java.io.*;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        try {
            // 连接到server端的6666端口，client端的端口随机
            Socket socket = new Socket("127.0.0.1", 6666);
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("hello server");
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}