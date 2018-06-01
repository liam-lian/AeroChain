package node.consensus.mainStream.prepared;

import node.consensus.mainStream.prePrepare.PrePrepare;
import node.consensus.mainStream.prepare.Prepare;
import model.block.Block;
import model.node.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Prepared implements Runnable {
    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Prepare.getValidPrepare() >= Node.getThreshold()) {
                if (Node.isSwitcher()) {
                    Node.addBlock(PrePrepare.getBlock());
                    PrePrepare.setBlock(null);
                    PrePrepare.setDigest(null);
                    Prepare.setValidPrepare(0);
                }
//                else {
//                    //TODO 算法流程待处理
//                    Node.getTmpBlocks().add(PrePrepare.getBlock());
//                }
//                if (Node.getBlockChain().size() % Constant.CHECKPOINT == 0){
//                    Checkpoint.generate();
//                }
//            }else {
//                Block a = new Block("merkle" , "non" , Node.getId() , new Date() , Constant.VERSION, new ArrayList<>() , Node.getBlockChainHeight() + 1);
//                Node.addBlock(a);
//            }
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
