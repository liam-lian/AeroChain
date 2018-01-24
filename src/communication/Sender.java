package communication;

import buffer.BufferPool;
import constant.Constant;
import java.io.IOException;
import java.net.*;

public class Sender implements Runnable{
    public void run(){
        while (true){
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Sender.sendData(BufferPool.generateBlock());
        }
    }

    public static void sendData(String data){
        DatagramSocket client = null;
        try {
            client = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        byte[] sendBuf;
        sendBuf = data.getBytes();
        for (String ip : IpAddress.address){
            InetAddress addr = null;
            try {
                addr = InetAddress.getByName(ip);
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
}
