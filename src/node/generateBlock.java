package node;

import block.Block;
import block.Hash;
import buffer.BufferPool;
import com.alibaba.fastjson.JSON;
import communication.Sender;
import constant.Constant;
import record.Record;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class generateBlock implements Runnable{
    @Override
    public void run() {
        while (Node.switcher){
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Record> records = BufferPool.generateBlockRecord();
            String merkleRoot = getMerkleRoot(records);
            List<Block> list = Node.getBlockChain();
            int size = list.size();
            String prevHash = size == 0 ? "non" : Hash.hash(list.get(size - 1).toString());
            Block newBlock = new Block(merkleRoot , prevHash , Node.getId() , new Date() , Constant.VERSION, records);
            String json = JSON.toJSONString(newBlock);
            Sender.sendData(json);
        }
    }

    private String getMerkleRoot(List<Record> records){
        return "root";
    }
}
