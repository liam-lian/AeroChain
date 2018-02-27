package consensus.mainStream;

import consensus.checkpoint.Checkpoint;
import consensus.viewChange.ViewChange;
import constant.Constant;
import model.block.Block;
import node.Node;

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
            Node.getBlockChain().add(PrePrepare.getBlock());
            if (Node.getBlockChain().size() % Constant.CHECKPOINT == 0){
                Checkpoint.generate();
            }
        }else {
            ViewChange.generate();
        }
    }
}
