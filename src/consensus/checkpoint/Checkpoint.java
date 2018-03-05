package consensus.checkpoint;

import communication.Sender;
import node.Node;
import util.Log;
import util.hash.Hash;

import java.util.List;

public class Checkpoint {
    public static void generate(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<checkpoint").append(",");
        stringBuilder.append(Node.getBlockChain().size()).append(",");
        stringBuilder.append(Hash.hash(Node.getBlockChain().get(Node.getBlockChain().size() - 1).toString())).append(",");
        stringBuilder.append(Node.getId()).append(",");
        stringBuilder.append(Node.getView());
        Sender.broadcast(stringBuilder.toString());
        Log.log(stringBuilder.toString() , "checkpoint");
    }

    public static void process(String data){
        if (isMatching(data)){
            Log.log(data , "checkpoint");

        }
    }

    private static boolean isMatching(String data){

    }

    public static int getLatestCheckpoint(){

    }

    public static List<String> getCheckpointProofs(){

    }

    public static boolean isValidCheckpoint(int checkpoint , String proof){

    }
}
