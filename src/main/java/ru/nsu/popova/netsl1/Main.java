package ru.nsu.popova.netsl1;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.lang.System.exit;

public class Main {
    private static boolean isValidIP(InetAddress ip) {
        return ip.isMulticastAddress() && (ip instanceof Inet4Address || ip instanceof Inet6Address);
    }

    public static void main(String[] args) {
        String groupIP = args[0];
        try {
            if (!isValidIP(InetAddress.getByName(groupIP))) {
                System.err.println("Invalid group ip address. Check your IP and try again.");
                exit(1);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
