package node.consensus.synchronize;

import node.communication.Sender;
import node.consensus.checkpoint.Checkpoint;
import node.consensus.mainStream.prepared.Prepared;
import model.block.Block;
import model.node.Node;

import java.util.List;

import static node.consensus.checkpoint.Checkpoint.isValidCheckpoint;
import static node.consensus.mainStream.prepared.Prepared.isValidBlock;

public class Synchronize {
    private static final int checkpointProofs = 3;

    private static final int checkpoint = 2;

    private static final int blockProofs = 4;

    private static final int blockchain = 6;

    public static void generate(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<synchronize").append("<");
        stringBuilder.append(Node.getBlockChainHeight()).append(",");
        stringBuilder.append(Node.getId()).append(">");
        Node.setSynSwitcher(true);
        Sender.broadcast(stringBuilder.toString());
    }

    public static void process(String data){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<synchronized").append(",");
        stringBuilder.append(Node.getView()).append(",");
        stringBuilder.append(Checkpoint.getLatestCheckpoint()).append(",");
        stringBuilder.append(Checkpoint.getCheckpointProofs()).append(",");
        stringBuilder.append(Prepared.getPreparedProofsAfterCheckpoint()).append(",");
        stringBuilder.append(Node.getId()).append(",");
        stringBuilder.append(Node.getBlockChain()).append(">");
        Sender.broadcast(stringBuilder.toString());
    }

    public static void synchronize(String data){
        String[] strings = data.split(",");
        int checkpoint = Integer.valueOf(strings[Synchronize.checkpoint]);
        String checkpointProof = strings[checkpointProofs];
        List<Block> blockchain = null;
        List<List<String>> blockProof = null;
        if (isValidCheckpoint(checkpoint , checkpointProof) && isValidBlock(checkpoint , blockchain , blockProof)){
            Node.setBlockChain(blockchain);
//            for (Block block : Node.getTmpBlocks()){
//                if ()
//            }
            Node.setSynSwitcher(false);
        }
    }




}
