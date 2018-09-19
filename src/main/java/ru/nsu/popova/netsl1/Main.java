package ru.nsu.popova.netsl1;

import java.io.IOException;
import java.net.*;

public class Main {
    private static boolean isValidIP(InetAddress ip) {
        return ip.isMulticastAddress() && (ip instanceof Inet4Address || ip instanceof Inet6Address);
    }

    public static void main(String[] args) {
        String groupIP = args[0];
        try {
            if (!isValidIP(InetAddress.getByName(groupIP))) {
                System.err.println("Invalid group ip address. Check your IP and try again.");
                System.exit(1);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            MulticastSocket multicastSocket = new MulticastSocket(24242);
            multicastSocket.joinGroup(InetAddress.getByName(groupIP));

            DatagramSocket datagramSocket = new DatagramSocket(50505, InetAddress.getByName(args[1]));

            MulticastSender sender = new MulticastSender(InetAddress.getByName(groupIP), 24242, datagramSocket);
            MulticastReceiver receiver = new MulticastReceiver(multicastSocket);

            sender.start();
            receiver.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
