package node.communication;

import main.Main;

import java.io.IOException;
import java.net.*;

/**
 * Created by DSY on 2018/3/15.
 * 区块链节点使用UDP协议与彼此和客户端通信
 */
public class Receiver implements Runnable {
    private volatile static DatagramSocket server;

    public void run(){
        while (Main.running){
            byte[] recvBuf = new byte[8192];
            DatagramPacket recvPacket = new DatagramPacket(recvBuf , recvBuf.length);
            try {
                server.receive(recvPacket);
            }catch (IOException e){
                e.printStackTrace();
            }
            String recvStr = new String(recvPacket.getData() , 0 , recvPacket.getLength());
            Resolver.resolve(recvStr);
        }
    }

    public static void init(){
        if (server == null){
            synchronized (Receiver.class){
                if (server == null){
                    try {
                        server = new DatagramSocket(7777);
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void clean(){
        server.close();
    }
}
