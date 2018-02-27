package consensus.synchronize;

import communication.Sender;
import node.Node;

public class Synchronize {

    public static boolean synchronizing = false;

    public static void generate(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<synchronize").append("<");
        stringBuilder.append(Node.getBlockChain().size()).append(",");
        stringBuilder.append(Node.getId()).append(">");
        synchronizing = true;
        Sender.broadcast(stringBuilder.toString());
    }

    public static void process(String data){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<synchronized").append(",");
        stringBuilder.append(Node.getView()).append(",");
    }

    public static void synchronize(String data){

    }
}
