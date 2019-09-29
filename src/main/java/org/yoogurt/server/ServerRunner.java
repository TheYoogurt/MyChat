package org.yoogurt.server;

import org.yoogurt.util.CloseUtil;
import org.yoogurt.util.MessageUtil;

import java.io.*;
import java.net.Socket;

/**
 * User: yaocj
 * Date: 2019/9/25
 * Description:
 */
public class ServerRunner implements Runnable {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public ServerRunner(Socket socket){
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
        try{
            while (true) {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                MessageUtil.receiveMessage(bufferedReader,bufferedWriter);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            CloseUtil.closeIO(socket, bufferedReader, bufferedWriter);
        }
    }
}
