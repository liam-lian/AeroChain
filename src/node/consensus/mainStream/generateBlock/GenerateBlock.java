package node.consensus.mainStream.generateBlock;

import model.node.Node;
import node.consensus.mainStream.prePrepare.PrePrepare;
import model.block.Block;
import node.buffer.BufferPool;
import constant.Constant;
import model.record.Record;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GenerateBlock implements Runnable{
    @Override
    public void run() {
        while (!Node.isViewChangeSwitcher() && !Node.isSynSwitcher() && Node.isPrimary()){
            try {
                TimeUnit.SECONDS.sleep(Constant.BLOCK_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Record> records = BufferPool.generateBlockRecord();
            String merkleRoot = getMerkleTree(records);
            int currentHeight = Node.getBlockChainHeight();
            String prevHash = currentHeight == 0 ? "non" : Node.getLatestHash();
            Block block = new Block(merkleRoot , prevHash , Node.getId() , Node.getView() , new Date() , Constant.VERSION, records , currentHeight + 1);
            PrePrepare.generate(block);
        }
    }

    public String getMerkleTree(List<Record> records){
        String result = "merkle_root";
        if (records.size() == 0) return result;
        int size = records.size();
        int exponential = 0;
        while (Math.pow(2 , exponential) < size){
            exponential++;
        }
        int add = (int)(Math.pow(2 , exponential) - size);
        while (add > 0){
            records.add(new Record());
            add--;
        }
        records.sort(Comparator.comparing(Record::getRecord));

        return result;
    }
}
