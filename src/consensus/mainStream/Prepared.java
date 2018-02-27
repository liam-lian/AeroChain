package consensus.mainStream;

import constant.Constant;
import model.block.Block;
import node.Node;

public class Prepared implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isMatching()){
            Block newBlock = new Block();
            Node.getBlockChain().add(newBlock);
            if (Node.getBlockChain().size() % Constant.CHECKPOINT == 0){

            }
        }else {

        }
    }

    private boolean isMatching(){

    }
}
