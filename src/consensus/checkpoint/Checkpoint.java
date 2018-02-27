package consensus.checkpoint;

import communication.Sender;
import node.Node;
import util.Log;
import util.hash.Hash;

public class Checkpoint {
    public static void generate(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<checkpoint").append(",");
        stringBuilder.append(Node.getBlockChain().size()).append(",");
        stringBuilder.append(Hash.hash(Node.getBlockChain().get(Node.getBlockChain().size() - 1).toString())).append(",");
        stringBuilder.append(Node.getId()).append(",");
        stringBuilder.append(Node.getView());
        Sender.sendData(stringBuilder.toString());
        Log.log(stringBuilder.toString() , "checkpoint");
    }

    public static void process(String data){
        if (isMatching(data)){
            Log.log(data , "checkpoint");

        }
    }

    private static boolean isMatching(String data){

    }
}
