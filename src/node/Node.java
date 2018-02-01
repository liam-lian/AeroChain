package node;

import block.Block;
import communication.Reciever;
import communication.Sender;
import init.Initial;
import simulator.Simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Node {
    public static boolean switcher = true;

    private static String id;

    private static List<Block> blockChain = new ArrayList<>();

    public static void main(String[] args) {
        Initial.init();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Reciever());
        executorService.execute(new Simulator());
        executorService.execute(new generateBlock());
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        id = id;
    }

    public static List<Block> getBlockChain() {
        return blockChain;
    }

    public static void setBlockChain(List<Block> newBlockChain) {
        blockChain = newBlockChain;
    }

    public static void addBlock(Block block){
        blockChain.add(block);
    }
}
