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

public class generateBlock implements Runnable{
    @Override
    public void run() {
        while (Node.switcher){
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Record> records = BufferPool.generateBlockRecord();
            String merkleRoot = getMerkleRoot(records);
            String prevHash = Hash.hash(Node.getBlockChain().get(Node.getBlockChain().size() - 1).toString());
            Block newBlock = new Block(merkleRoot , prevHash , Node.getId() , new Date() , Constant.VERSION, records);
            Sender.sendData(JSON.toJSONString(newBlock));
        }
    }

    private String getMerkleRoot(List<Record> records){
        return "root";
    }
}
