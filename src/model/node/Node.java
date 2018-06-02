package model.node;

import model.block.Block;
import util.hash.Hash;
import java.util.*;

/**
 * Created by DSY on 2018/3/15.
 * 区块链节点模型
 * 第一个区块的高度是1
 * 当前版本不考虑节点动态变化的功能
 */
public class Node {
    /**
     * 判断是否进入同步状态，如果进入同步状态，那么不再发送prepare消息，
     * 但是正常接受prePrepare和prepare消息
     * */
    private static boolean synSwitcher = false;

    /**
     * 判断是否进入视图切换状态，如果进入视图切换状态，则只接受跟视图切换相关的消息
     */
    private static boolean viewChangeSwitcher = false;

    private static List<Block> blockChain = new ArrayList<>();

    /**
     * 用于存储区块同步过程中，生成的临时区块
     */
    private static List<Block> tmpBlocks = new ArrayList<>();

    private static int id = 1;

    private static int primary = 1;

    private static int view = 1;

    private static int nodeNum = 4;

    private static String faultyNodeNum = "1";

    private volatile static int threshold = Integer.MAX_VALUE;

    public static void threshold(){
        int n = Node.getNodeNums();
        int f = Integer.valueOf(Node.getFaultyNodeNums());
        threshold = (int) Math.ceil((n - f) / 2) + f + ((n - f ) % 2 == 0 ? 1 : 0);
    }

    public static boolean isPrimary(){
        return (id == view % nodeNum);
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
        return faultyNodeNum;
    }

    public static void setFaultyNodeNums(String faultyNodeNums) {
        Node.faultyNodeNum = faultyNodeNums;
    }

    public static int getThreshold() {
        return threshold;
    }

    public static void setThreshold(int threshold) {
        Node.threshold = threshold;
    }

    public static boolean isSynSwitcher() {
        return synSwitcher;
    }

    public static void setSynSwitcher(boolean synSwitcher) {
        Node.synSwitcher = synSwitcher;
    }

    public static boolean isViewChangeSwitcher() {
        return viewChangeSwitcher;
    }

    public static void setViewChangeSwitcher(boolean viewChangeSwitcher) {
        Node.viewChangeSwitcher = viewChangeSwitcher;
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
        return nodeNum;
    }

    public static void setNodeNums(int nodeNums) {
        Node.nodeNum = nodeNums;
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
