package org.yoogurt.server;

import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * User: yaocj
 * Date: 2019/9/25
 * Description: 服务端启动类
 */
public class SocketServer {
    /**
     * 服务端端口
     */
    private static final int SERVER_PORT = 1010;

    /**
     * 客户端ip
     */
    private static final String CLIENT_IP = "127.0.0.1";

    /**
     * 客户端端口
     */
    private static final int CLIENT_PORT = 2020;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        int i = 0;
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            while (true){
                // 接收客户端请求
                ServerRunner serverRunner = new ServerRunner(serverSocket.accept());
                new Thread(serverRunner).start();
                while (i == 0) {
                    // 客户端与服务端启动往往有一个先后顺序，由于需要发送请求给客户端，这里需要等待客户端启动
                    try {
                        // 这里可能会出现ConnectException
                        socket = new Socket(CLIENT_IP, CLIENT_PORT);
                        // 一旦连接成功，i加1使当前能跳出循环
                        i ++;
                        //发送请求给客户端
                        ServerClient serverClient = new ServerClient(socket);
                        new Thread(serverClient).start();
                    } catch (ConnectException e) {
                        System.out.println("正在等待客户端响应。。。。。");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (i > 0 && socket != null){
                    socket.close();
                }
                if (serverSocket != null){
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void runSocket(){

    }

}
