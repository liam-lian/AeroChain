package node.consensus.viewChange;

import node.communication.Sender;
import node.consensus.checkpoint.Checkpoint;
import node.consensus.mainStream.prepared.Prepared;
import model.block.Block;
import model.node.Node;
import node.communication.Resolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewChange {
    private static Map<String , Integer> map = new HashMap<>();

    private static List<String> viewChangeProofs = new ArrayList<>();

    private static final int newView = 1;

    private static final int view = 2;

    private static final int checkpointProof = 3;

    private static final int blockProof = 4;

    public static void generate(){
        Node.setSynSwitcher(true);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("view-change").append(",");
        stringBuilder.append(Integer.valueOf(Node.getView()) + 1).append(",");
        stringBuilder.append(Checkpoint.getLatestCheckpoint()).append(",");
        stringBuilder.append(Checkpoint.getCheckpointProofs()).append(",");
        stringBuilder.append(Prepared.getPreparedProofsAfterCheckpoint()).append(",");
        stringBuilder.append(Node.getId());
        Sender.broadcast(stringBuilder.toString());
    }

    public static void process(String data){
    }

    private static void viewChange(){
        synchronize(viewChangeProofs);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<new-view").append(",");
        stringBuilder.append(Node.getView()).append(",");
        stringBuilder.append(viewChangeProofs).append(",");
        stringBuilder.append(Node.getBlockChainHeight()).append(">");
    }

    private static void synchronize(List<String> viewChangeProofs){

    }

    public static void newView(String data){

    }
}
