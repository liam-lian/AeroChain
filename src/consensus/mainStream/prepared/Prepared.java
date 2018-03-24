package consensus.mainStream.prepared;

import consensus.checkpoint.Checkpoint;
import consensus.mainStream.prePrepare.PrePrepare;
import consensus.mainStream.prepare.Prepare;
import consensus.viewChange.ViewChange;
import constant.Constant;
import model.block.Block;
import node.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Prepared implements Runnable {
    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.SECONDS.sleep(Constant.BLOCK_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Prepare.getValidPrepare() <= Node.getThreshold()){
                if (Node.isSwitcher()) {
                    Node.addBlock(PrePrepare.getBlock());
                    PrePrepare.setBlock(null);
                    PrePrepare.setDigest(null);
                    Prepare.setValidPrepare(0);
                }
                else {
                    //TODO 算法流程待处理
                    Node.getTmpBlocks().add(PrePrepare.getBlock());
                }
                if (Node.getBlockChain().size() % Constant.CHECKPOINT == 0){
                    Checkpoint.generate();
                }
            }else {
                ViewChange.generate();
            }
        }
    }

    public static List<List<String>> getPreparedProofsAfterCheckpoint(){
        List<List<String>> result = new ArrayList<>();

        return result;
    }

    public static boolean isValidBlock(int checkpoint , List<Block> blockchain , List<List<String>> proof){
        return true;
    }
}
