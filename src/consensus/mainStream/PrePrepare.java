package consensus.mainStream;

import buffer.BufferPool;
import com.alibaba.fastjson.JSON;
import communication.Sender;
import model.block.Block;
import model.record.Record;
import node.Node;
import util.Log;
import util.hash.Hash;

public class PrePrepare {
    private static Block block;

    private static final int view = 2;

    private static final int height = 3;

    private static final int blockData = 4;

    private static final int blockDigest = 5;

    private static String digest;

    public static void generate(String block){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<pre-prepare").append(",");
        stringBuilder.append(Node.getPrimary()).append(",");
        stringBuilder.append(Node.getView()).append(",");
        stringBuilder.append(Node.getBlockChainHeight() + 1).append(",");
        stringBuilder.append(block).append(",");
        stringBuilder.append(Hash.hash(block)).append(">");
        Sender.broadcast(stringBuilder.toString());
        Node.setThreshold(threshold());
    }

    private static int threshold(){
        int n = Integer.valueOf(Node.getNodeNums());
        int f = Integer.valueOf(Node.getFaultyNodeNums());
        return (int) Math.ceil((n - f) / 2) + f + ((n - f ) % 2 == 0 ? 1 : 0);
    }

    public static void process(String prePrepare){
        String[] strings = prePrepare.split(",");
        block = JSON.parseObject(strings[blockData] , Block.class);
        if (Node.getView().equals(strings[view]) && (Node.getBlockChainHeight() + 1) == Integer.valueOf(strings[height]) && isValid(block)){
            Prepare.generate(strings);
            setDigest(strings[blockDigest]);
            Log.log(prePrepare , "prePrepareLog");
        }
    }

    private static boolean isValid(Block block){
        for (Record record : block.getData()){
            if (!BufferPool.isContain(record))
                return false;
        }
        return block.getPrevHash().equals(Node.getLatestHash());
    }

    public static String getDigest() {
        return digest;
    }

    public static void setDigest(String digest) {
        PrePrepare.digest = digest;
    }

    public static Block getBlock() {
        return block;
    }
}
