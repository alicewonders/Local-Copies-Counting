package ru.nsu.popova.netsl1;

import java.io.IOException;
import java.net.*;

public class MulticastSender extends Thread {
    private int PORT;
    private InetAddress ADDRESS;
    private static final int BUFFER_SIZE = 1024;
    private static final int SEND_PERIOD = 2000;

    private DatagramSocket socket;

    public MulticastSender(InetAddress ip, int port, DatagramSocket socket) {
        this.PORT = port;
        this.ADDRESS = ip;
        this.socket = socket;
    }

    public void run() {
        DatagramPacket packet;
        try {
            byte[] information = new byte[BUFFER_SIZE];
            while (true) {
                packet = new DatagramPacket(information, information.length, new InetSocketAddress(ADDRESS, PORT));
                socket.send(packet);
                Thread.sleep(SEND_PERIOD);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
