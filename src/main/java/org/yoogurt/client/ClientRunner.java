package org.yoogurt.client;

import org.yoogurt.util.CloseUtil;
import org.yoogurt.util.MessageUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * User: yaocj
 * Date: 2019/9/26
 * Description:
 */
public class ClientRunner implements Runnable {
    private Socket socket = null;
    private BufferedReader bufferedReader = null;
    private BufferedWriter bufferedWriter = null;
    public ClientRunner(Socket socket){
        super();
        this.socket = socket;
    }
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    public void run() {
        try {
            while (true) {
                bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                MessageUtil.sendMessage("妖精哥", bufferedReader, bufferedWriter);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            CloseUtil.closeIO(socket, bufferedReader, bufferedWriter);
        }
    }
}
