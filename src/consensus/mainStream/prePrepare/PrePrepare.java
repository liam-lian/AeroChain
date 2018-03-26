package consensus.mainStream.prePrepare;

import buffer.BufferPool;
import com.alibaba.fastjson.JSON;
import communication.Sender;
import consensus.mainStream.prepare.Prepare;
import model.block.Block;
import model.record.Record;
import node.Node;
import util.Log;
import util.hash.Hash;

import java.util.Set;

public class PrePrepare {
    private static Block block;

    private static String digest;

    public static void generate(Block block){
        PrePrepareModel model = new PrePrepareModel();
        model.setPrimary(Node.getPrimary());
        model.setView(Node.getView());
        model.setHeight(Node.getBlockChainHeight() + 1);
        model.setBlock(block);
        model.setDigest(Hash.hash(block.toString()));
        Sender.broadcast("<pre-prepare>" + JSON.toJSONString(model));

    }

    public static void process(PrePrepareModel prePrepare){
        if (Node.getView() == prePrepare.view && (Node.getBlockChainHeight() + 1) == prePrepare.getHeight() && isValid(prePrepare.getBlock())){
            setDigest(prePrepare.getDigest());
            setBlock(prePrepare.getBlock());
            Prepare.generate(prePrepare);
            Node.setThreshold(threshold());
            Log.log(prePrepare.toString() , "prePrepareLog" , true);
        }
    }

    private static boolean isValid(Block block){
        for (Record record : block.getData()){
            if (!BufferPool.isContain(record)){
                return false;
            }
        }
        return block.getPrevHash().equals(Node.getLatestHash());
    }

    public synchronized static String getDigest() {
        return digest;
    }

    public synchronized static void setDigest(String digest) {
        PrePrepare.digest = digest;
    }

    public synchronized static Block getBlock() {
        return block;
    }

    public synchronized static void setBlock(Block block) {
        PrePrepare.block = block;
    }

    private static int threshold(){
        int n = Node.getNodeNums();
        int f = Integer.valueOf(Node.getFaultyNodeNums());
        return (int) Math.ceil((n - f) / 2) + f + ((n - f ) % 2 == 0 ? 1 : 0);
    }
}
