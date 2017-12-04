package com.dryseed.dryseedapp.test.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            while (true) {
                Socket socket = serverSocket.accept(); //阻塞式接收一个socket连接
                System.out.println("a client is connected");
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                System.out.println(dataInputStream.readUTF()); //阻塞式读取
                dataInputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}