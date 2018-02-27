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

    private static final int view = 2;

    private static final int height = 3;

    private static final int blockData = 4;

    private static final int digest = 5;

    public static void generate(String block){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<pre-prepare").append(",");
        stringBuilder.append(Node.getPrimary()).append(",");
        stringBuilder.append(Node.getView()).append(",");
        stringBuilder.append(Node.getBlockChainHeight() + 1).append(",");
        stringBuilder.append(block).append(",");
        stringBuilder.append(Hash.hash(block)).append(">");
        Sender.broadcast(stringBuilder.toString());
    }

    public static void process(String prePrepare){
        String[] strings = prePrepare.split(",");
        Block block = JSON.parseObject(strings[blockData] , Block.class);
        if (Node.getView().equals(strings[view]) && Node.getBlockChain().size() == Integer.valueOf(strings[height])
                && strings[digest].equals(Hash.hash(block.toString())) && isValid(block)){
            Sender.sendData(Prepare.generate(strings));
            Log.log(prePrepare , "C:\\Users\\DSY\\blockchain\\log\\prePrepareLog");
        }
    }

    private static boolean isValid(Block block){
        for (Record record : block.getData()){
            if (!BufferPool.isContain(record))
                return false;
        }
        return true;
    }

}
