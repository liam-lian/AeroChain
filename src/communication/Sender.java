package communication;

import constant.Constant;
import constant.IpAddress;
import java.io.IOException;
import java.net.*;

public class Sender{
    public static void sendData(String data){
        DatagramSocket client = null;
        try {
            client = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        byte[] sendBuf = data.getBytes();
        for (String ip : IpAddress.address){
            InetAddress addr = null;
            try {
                addr = InetAddress.getByName(ip);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            DatagramPacket sendPacket = new DatagramPacket(sendBuf ,sendBuf.length , addr , Constant.POTR);
            try {
                assert client != null;
                client.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
