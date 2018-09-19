package ru.nsu.popova.netsl1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class MulticastReceiver extends Thread {
    private static final int BUFFER_SIZE = 1024;

    private MulticastSocket socket;
    private byte[] buffer = new byte[BUFFER_SIZE];

    public MulticastReceiver(MulticastSocket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, BUFFER_SIZE);
                socket.receive(packet);
                System.out.println("Packet was received from " + packet.getAddress() + " on port: " + packet.getPort());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
