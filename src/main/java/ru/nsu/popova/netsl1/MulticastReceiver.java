package ru.nsu.popova.netsl1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class MulticastReceiver extends Thread {
    private static final int BUFFER_SIZE = 1024;

    private MulticastSocket socket;
    private byte[] buffer = new byte[BUFFER_SIZE];

    private HashMap<InetAddress, LocalTime> copiesMap = null;

    public MulticastReceiver(MulticastSocket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            copiesMap = new HashMap<>();
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, BUFFER_SIZE);
                System.out.println("I am waiting......");
                socket.receive(packet);
                System.out.println("I received smth!");
                copiesMap.put(packet.getAddress(), LocalTime.now());

                Map<InetAddress, LocalTime> addressesToDelete = new HashMap<>();
                for (Map.Entry<InetAddress, LocalTime> entry : copiesMap.entrySet()) {
                    if (LocalTime.now().isAfter(entry.getValue().plusSeconds(15))) {
                        addressesToDelete.put(entry.getKey(), entry.getValue());
                    }
                }

                for (Map.Entry<InetAddress, LocalTime> address: addressesToDelete.entrySet()) {
                    copiesMap.remove(address.getKey(), address.getValue());
                }
                for (Map.Entry<InetAddress, LocalTime> entry : copiesMap.entrySet()) {
                    System.out.println(entry.getKey().toString() + " " + entry.getValue().toString() + " ALIVE STATUS");
                }
                System.out.println();
//                System.out.println("Packet was received from " + packet.getAddress() + " on port: " + packet.getPort());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
