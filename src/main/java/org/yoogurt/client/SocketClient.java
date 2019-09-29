package org.yoogurt.client;

import java.io.*;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * User: yaocj
 * Date: 2019/9/25
 * Description: 客户端启动类
 */
public class SocketClient {

    /**
     * 服务端ip
     */
    private static final String SERVER_IP = "127.0.0.1";
    /**
     * 服务端端口
     */
    private static final int SERVER_PORT = 1010;

    /**
     * 客户端端口
     */
    private static final int CLIENT_PORT = 2020;

    public static void main(String[] args) {
        Socket socket = null;
        ServerSocket serverSocket = null;
        int i = 0;
        try {
            serverSocket = new ServerSocket(CLIENT_PORT);
            while (true) {
                while (i == 0) {
                    // 客户端与服务端启动往往有一个先后顺序，由于需要发送请求给服务端，这里需要等待服务端启动
                    try {
                        // 这里可能会出现ConnectException
                        socket = new Socket(SERVER_IP, SERVER_PORT);
                        // 一旦连接成功，i加1使当前能跳出循环
                        i ++;
                        // 发送请求给服务端
                        ClientRunner clientRunner = new ClientRunner(socket);
                        new Thread(clientRunner).start();
                    } catch (ConnectException e){
                        System.out.println("正在等待服务端响应。。。");
                    }
                }
                // 接收服务端请求
                ClientServer clientServer = new ClientServer(serverSocket.accept());
                new Thread(clientServer).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (i > 0 && socket != null) {
                    socket.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
