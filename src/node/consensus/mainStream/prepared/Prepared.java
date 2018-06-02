package node.consensus.mainStream.prepared;

import constant.Constant;
import node.consensus.mainStream.prePrepare.PrePrepare;
import node.consensus.mainStream.prepare.Prepare;
import model.block.Block;
import model.node.Node;
import node.consensus.viewChange.ViewChange;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by DSY on 2018/5/31.
 * 每隔一个区块生成间隔，各个节点都会检查是否生成了新的区块，如果有那么将其添加至区块链
 * 如果没有，那么认为主节点出现错误，通过调用viewChange协议使系统回归正常状态
 *
 * viewChange和同步协议中涉及为区块生成证据的功能目前考虑在该类中实现，不确定
 */
public class Prepared implements Runnable {
    @Override
    public void run() {
        while (!Node.isViewChangeSwitcher()){
            try {
                TimeUnit.SECONDS.sleep(Constant.BLOCK_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Prepare.getValidPrepare() >= Node.getThreshold()) {
                Node.addBlock(PrePrepare.getBlock());
                PrePrepare.setBlock(null);
                PrePrepare.setDigest(null);
                Prepare.setValidPrepare(0);
            }else {
                PrePrepare.setBlock(null);
                PrePrepare.setDigest(null);
                Prepare.setValidPrepare(0);
                ViewChange.generate();
            }
        }
    }

    @Deprecated
    public static List<List<String>> getPreparedProofsAfterCheckpoint(){
        List<List<String>> result = new ArrayList<>();
        return result;
    }

    @Deprecated
    public static boolean isValidBlock(int checkpoint , List<Block> blockchain , List<List<String>> proof){
        return true;
    }
}
