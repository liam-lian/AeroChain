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

    String id;

    List<Block> blockChain = new ArrayList<>();

    public static void main(String[] args) {
        Initial.init();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Reciever());
        executorService.execute(new Sender());
        executorService.execute(new Simulator());
    }
}
