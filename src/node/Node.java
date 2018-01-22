package node;

import block.Block;
import buffer.BufferPool;
import communication.Sender;
import init.Initial;
import java.util.ArrayList;
import java.util.List;

public class Node {
    List<Block> blockList = new ArrayList<>();

    public static void main(String[] args) {
        Initial.init();
        while (true){
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Sender.sendData(BufferPool.generateBlock());
        }
    }
}
