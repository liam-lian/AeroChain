package consensus.checkpoint;

import communication.Sender;
import node.Node;
import util.Log;
import util.hash.Hash;

import java.io.StringReader;
import java.util.List;

public class Checkpoint {
    private static String signature;

    private static int count;

    private static int currentHeight;

    private static final int height = 1;

    private static final int digest = 2;

    private static final int view = 4;

    public synchronized static void generate(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<checkpoint").append(",");
        stringBuilder.append(Node.getBlockChain().size()).append(",");
        stringBuilder.append(Hash.hash(Node.getBlockChain().get(Node.getBlockChain().size() - 1).toString())).append(",");
        stringBuilder.append(Node.getId()).append(",");
        stringBuilder.append(Node.getView());
        String result = stringBuilder.toString();
        Sender.broadcast(result);
        signature = Node.getBlockChain().size() + Hash.hash(Node.getBlockChain().get(Node.getBlockChain().size() - 1).toString()) + Node.getView();
        Log.log(result , "checkpoint");
    }

    public synchronized static void process(String data){
        String[] strings = data.split(",");
        if ((strings[height] + strings[digest] + strings[view]).equals(signature)){
            Log.log(data , "checkpoint");
            if (++count == Node.getThreshold()){
                currentHeight = Integer.valueOf(strings[height]);
                deleteOldData();
            }
        }
    }

    private static void deleteOldData(){}

    public static int getLatestCheckpoint(){
        return currentHeight;
    }

    public static List<String> getCheckpointProofs(){
        return Log.getLogs("checkpoint");
    }

    public static boolean isValidCheckpoint(int checkpoint , String proof){
        String[] strings = proof.split("\n");
        for (int i = 0 ; i < strings.length - 1 ; i++)
            if (!strings[i].equals(strings[i + 1]))
                return false;
        return strings[height].equals(String.valueOf(checkpoint));
    }
}
