package node;

import consensus.mainStream.PrePrepare;
import model.block.Block;
import util.hash.Hash;
import buffer.BufferPool;
import com.alibaba.fastjson.JSON;
import communication.Sender;
import constant.Constant;
import model.record.Record;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GenerateBlock implements Runnable{
    @Override
    public void run() {
        while (Node.isSwitcher()){
            try {
                TimeUnit.SECONDS.sleep(Constant.BLOCK_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Record> records = BufferPool.generateBlockRecord();
            String merkleRoot = getMerkleRoot(records);
            int currentHeight = Node.getBlockChainHeight();
            String prevHash = currentHeight == 0 ? "non" : Node.getLatestHash();
            Block newBlock = new Block(merkleRoot , prevHash , Node.getId() , new Date() , Constant.VERSION, records , currentHeight + 1);
            String block = JSON.toJSONString(newBlock);
            PrePrepare.generate(block);
        }
    }

    private String getMerkleRoot(List<Record> records){
        return "merkle_root";
    }
}
