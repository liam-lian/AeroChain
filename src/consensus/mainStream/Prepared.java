package consensus.mainStream;

import consensus.checkpoint.Checkpoint;
import consensus.viewChange.ViewChange;
import constant.Constant;
import model.block.Block;
import node.Node;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Prepared implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(Constant.BLOCK_GAP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Prepare.getValidPrepare() >= Node.getThreshold()){
            if (Node.isSwitcher())
                Node.getBlockChain().add(PrePrepare.getBlock());
            else
                Node.getTmpBlocks().add(PrePrepare.getBlock());
            if (Node.getBlockChain().size() % Constant.CHECKPOINT == 0){
                Checkpoint.generate();
            }
        }else {
            ViewChange.generate();
        }
    }

    public static List<Set<String>> getPreparedProofsAfterCheckpoint(){

    }

    public static boolean isValidBlock(List<Block> blockchain , List<Set<String>> proof){

    }
}
