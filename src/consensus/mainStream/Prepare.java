package consensus.mainStream;

import node.Node;
import util.Log;

public class Prepare {

    private static final int view = 2;

    private static final int height = 3;

    private static final int digest = 5;

    public static String generate(String[] prePrepare){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<prepare").append(",");
            stringBuilder.append(Node.getId()).append(",");
            stringBuilder.append(prePrepare[view]).append(",");
            stringBuilder.append(prePrepare[height]).append(",");
            stringBuilder.append(prePrepare[digest]).append(">");
            return stringBuilder.toString();
    }

    public static void process(String prepare){
        String[] strings = prepare.split(",");
        if (strings[view].equals(Node.getView()) && Integer.valueOf(strings[height]) == Node.getBlockChain().size()) {
            Log.log(prepare, "C:\\Users\\DSY\\blockchain\\log\\prepareLog");
        }
    }
}
