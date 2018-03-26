package node;

import consensus.mainStream.prepared.Prepared;
import consensus.signUp.SignUp;
import model.block.Block;
import communication.Reciever;
import init.Initial;
import util.hash.Hash;
import util.simulator.Simulator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by DSY on 2018/3/15.
 * 区块链节点编号从1开始
 */

public class Node {
    //判断节点当前的工作状态，是否需要对外发送prepare消息
    private static boolean switcher = true;

    private static List<Block> blockChain = new ArrayList<>();

    private static int id = 1;

    private static int primary = 1;

    private static int view = 1;

    private static int nodeNums = 4;

    private static String faultyNodeNums = "1";

    private volatile static int threshold = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        Initial.init();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(new Reciever());
        executorService.execute(new Simulator());
        executorService.execute(new GenerateBlock());
        executorService.execute(new Prepared());
        while (true){
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while ((input = stdin.readLine()) != null){
                if (input.equals("show")){
                    List<Block> result = Node.getBlockChain();
                    for (Block b : result)
                        System.out.println(Hash.hash(b.toString()) + " " + b);
                }
                if (input.equals("signUp"))
                    SignUp.generate();
                if (input.startsWith("f:"))
                    setFaultyNodeNums(input.replace("f:" , ""));
                if (input.equals("exit")){
                    Reciever.clean();
                    executorService.shutdown();
                    System.exit(0);
                }
            }
        }
    }

    public static boolean isPrimary(){
        return (id == view % nodeNums);
    }

    @Override
    public String toString() {
        return "Node{}";
    }

    public synchronized static String getLatestHash(){
        if (blockChain.size() == 0) return "non";
        return Hash.hash(blockChain.get(blockChain.size() - 1).toString());
    }

    public synchronized static int getBlockChainHeight(){
        return blockChain.size();
    }

    public synchronized static void addBlock(Block block){
        blockChain.add(block);
    }

//    public static List<Block> getTmpBlocks() {
//        return tmpBlocks;
//    }
//
//    public static void setTmpBlocks(List<Block> tmpBlocks) {
//        Node.tmpBlocks = tmpBlocks;
//    }

    public static String getFaultyNodeNums() {
        return faultyNodeNums;
    }

    public static void setFaultyNodeNums(String faultyNodeNums) {
        Node.faultyNodeNums = faultyNodeNums;
    }

    public static int getThreshold() {
        return threshold;
    }

    public static void setThreshold(int threshold) {
        Node.threshold = threshold;
    }

    public static boolean isSwitcher() {
        return switcher;
    }

    public static void setSwitcher(boolean switcher) {
        Node.switcher = switcher;
    }

    public static int getPrimary() {
        return primary;
    }

    public static void setPrimary(int primary) {
        Node.primary = primary;
    }

    public static int getView() {
        return view;
    }

    public static void setView(int view) {
        Node.view = view;
    }

    public static int getNodeNums() {
        return nodeNums;
    }

    public static void setNodeNums(int nodeNums) {
        Node.nodeNums = nodeNums;
    }

    public static int getId() {
        return id;
    }

    public static void setId(String id) {
        id = id;
    }

    public synchronized static List<Block> getBlockChain() {
        return blockChain;
    }

    public synchronized static void setBlockChain(List<Block> newBlockChain) {
        blockChain = newBlockChain;
    }
}
