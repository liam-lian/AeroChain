package node;

import com.alibaba.fastjson.JSON;
import model.block.Block;
import communication.Reciever;
import init.Initial;
import util.simulator.Simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Node {
    public static boolean switcher = true;

    private static String id = "id";

    private static List<Block> blockChain = new ArrayList<>();

    public static void main(String[] args) {
//        JSON.parseObject("{\"data\":[{},{},{},{},{}],\"merkleRoot\":\"root\",\"prevHash\":\"non\",\"primary\":\"id\",\"timeStamp\":1519394068815,\"version\":\"version_0\"}" , Block.class);
        Initial.init();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
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
