package node;

import client.Client;
import node.consensus.mainStream.prepared.Prepared;
import node.consensus.signUp.SignUp;
import model.block.Block;
import node.communication.Reciever;
import main.init.Initial;
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
 * 区块链高度编号从1开始
 */

public class Node {
    //判断节点当前的工作状态，是否需要对外发送prepare消息
    private static boolean switcher = true;

    private static List<Block> blockChain = new ArrayList<>();
    //用于存储同步过程中产生的临时区块
    private static List<Block> tmpBlocks = new ArrayList<>();

    private static int id = 1;

    private static int primary = 1;

    private static int view = 1;

    private static int nodeNums = 4;

    private static String faultyNodeNums = "1";

    private volatile static int threshold = Integer.MAX_VALUE;

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

    public static List<Block> getTmpBlocks() {
        return tmpBlocks;
    }

    public static void setTmpBlocks(List<Block> tmpBlocks) {
        Node.tmpBlocks = tmpBlocks;
    }

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

    public synchronized static List<Block> getBlockChain() {
        return blockChain;
    }

    public synchronized static void setBlockChain(List<Block> newBlockChain) {
        blockChain = newBlockChain;
    }
}
