package communication;

import constant.Constant;
import java.io.IOException;
import java.net.*;

public class Sender {

    private void sendData(String data){
        DatagramSocket client = null;
        try {
            client = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        byte[] sendBuf;
        sendBuf = data.getBytes();
        InetAddress addr = null;
        try {
            addr = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        DatagramPacket sendPacket = new DatagramPacket(sendBuf ,sendBuf.length , addr , Constant.POTR);
        try {
            client.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
