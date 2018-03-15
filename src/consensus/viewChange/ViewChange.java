package consensus.viewChange;

import communication.Sender;
import consensus.checkpoint.Checkpoint;
import consensus.mainStream.prepared.Prepared;
import model.block.Block;
import node.Node;
import node.Resolver;

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
        Resolver.setSwitcher(false);
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
        viewChangeProofs.add(data);
        String[] strings = data.split(",");
        if (strings[newView].equals(Node.getId())){
            int checkpoint = Integer.valueOf(strings[2]);
            List<Block> blockchain = null;
            List<List<String>> proof = null;
            if (Checkpoint.isValidCheckpoint(checkpoint , strings[3]) && Prepared.isValidBlock(checkpoint , blockchain , proof)){
                String signature = strings[checkpointProof] + strings[blockProof];
                map.putIfAbsent(signature , 0);
                map.put(signature , map.get(signature) + 1);
                if (map.get(signature) >= Node.getThreshold()){
                    Resolver.setSwitcher(false);
                    viewChange();
                    Resolver.setSwitcher(true);
                }
            }
        }
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
