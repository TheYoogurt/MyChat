package org.yoogurt.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

/**
 * User: yaocj
 * Date: 2019/9/26
 * Description: 关闭的工具类（一般在finally主体中调用）
 */
public class CloseUtil {

    /**
     * 关闭IO和Socket
     * @param socket
     * @param bufferedReader
     * @param bufferedWriter
     */
    public static void closeIO(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        try {
            if (bufferedReader != null){
                bufferedReader.close();
            }
            if (bufferedWriter != null){
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
