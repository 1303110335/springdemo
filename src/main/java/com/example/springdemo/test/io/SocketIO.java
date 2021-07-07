/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.test.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author xuleyan
 * @version SocketIO.java, v 0.1 2020-04-03 8:30 PM xuleyan
 */
public class SocketIO {

    public static void main(String[] args) {
        Socket socket = null;
        OutputStream outputStream = null;

        try {
            socket = new Socket("localhost", 54563);
            outputStream = socket.getOutputStream();
            int n = 1;
            while (true) {
                TimeUnit.SECONDS.sleep(1);
                String text = "My name is xsg" + (++n);
                outputStream.write(text.getBytes());
                outputStream.flush();
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}