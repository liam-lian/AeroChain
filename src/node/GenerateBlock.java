package node;

import consensus.mainStream.prePrepare.PrePrepare;
import model.block.Block;
import buffer.BufferPool;
import com.alibaba.fastjson.JSON;
import constant.Constant;
import model.record.Record;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GenerateBlock implements Runnable{
    @Override
    public void run() {
        while (Node.isSwitcher() && Node.isPrimary()){
            try {
                TimeUnit.SECONDS.sleep(Constant.BLOCK_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Node.isPrimary()){
                List<Record> records = BufferPool.generateBlockRecord();
                String merkleRoot = getMerkleRoot(records);
                int currentHeight = Node.getBlockChainHeight();
                String prevHash = currentHeight == 0 ? "non" : Node.getLatestHash();
                Block block = new Block(merkleRoot , prevHash , Node.getId() , Node.getView() , new Date() , Constant.VERSION, records , currentHeight + 1);
                PrePrepare.generate(block);
            }
        }
    }

    private String getMerkleRoot(List<Record> records){
        return "merkle_root";
    }
}
