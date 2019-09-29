package org.yoogurt.util;

import java.io.*;
import java.net.Socket;

/**
 * User: yaocj
 * Date: 2019/9/26
 * Description: 消息处理工具类
 */
public class MessageUtil {

    /**
     * 发送消息
     * @param name
     * @param bufferedReader
     * @param bufferedWriter
     * @throws IOException
     */
    public static void sendMessage(String name, BufferedReader bufferedReader, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(name + "\n");
        bufferedWriter.flush();
        String input = bufferedReader.readLine();
        bufferedWriter.write(input + "\n");
        bufferedWriter.flush();
        System.out.println("我:" + input);
    }

    /**
     * 接收消息
     * @param bufferedReader
     * @param bufferedWriter
     * @throws IOException
     */
    public static void receiveMessage(BufferedReader bufferedReader, BufferedWriter bufferedWriter) throws IOException {
        String name = bufferedReader.readLine();
        bufferedWriter.write(name + "\n");
        bufferedWriter.flush();
        String inputString = bufferedReader.readLine();
        bufferedWriter.write(inputString + "\n");
        bufferedWriter.flush();
        System.out.printf(name + ":" + inputString + "\n");
    }

}
