package ru.nsu.popova.netsl1;

import java.io.IOException;
import java.net.MulticastSocket;

public class MulticastSender extends Thread {
    public void run() {
        try {
            MulticastSocket socket = new MulticastSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
