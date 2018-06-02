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

/**
 * Created by DSY on 2018/5/31.
 * 每隔一个区块生成间隔，主节点会生成一个区块，并广播给其他区块链节点。
 */
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
            Block block = new Block(merkleRoot, prevHash, Node.getId(), Node.getView(), new Date(), records, currentHeight + 1);
            PrePrepare.generate(block);
        }
    }

    /**
     * 在有SPV节点的区块链应用中，通过使用merkle树可以使轻量级节点高效的证明一笔交易
     * 但是在AeroChain中，目前全部节点均为全节点，因此提供merkle树的意义不大
     * 如果有需要，日后可再实现梅克尔树
     * @param records 用于构造merkle树的数据
     * 将输入数据按字典序排序
     * @return 梅克尔树根
     */
    private String getMerkleTree(List<Record> records){
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
