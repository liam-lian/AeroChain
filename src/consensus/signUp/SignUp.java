package consensus.signUp;

import communication.Sender;
import node.Node;
import util.Log;

public class SignUp {

    private static final int selfIntroduction = 1;
    public static void generate(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<join").append(",");
        stringBuilder.append(selfIntroduction()).append(">");
        Sender.broadcast(stringBuilder.toString());
    }

    public static void process(String data){
        data = data.replaceAll(">" , "");
        String[] strings = data.split(",");
        if (isMatching(strings[selfIntroduction])){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<approve").append(",");
            stringBuilder.append(Node.getId()).append(",");
            stringBuilder.append(strings[selfIntroduction]).append(",");
            stringBuilder.append(Node.getNodeNums() + 1).append(">");
            Node.setNodeNums(Node.getNodeNums() + 1);
            Sender.broadcast(stringBuilder.toString());
        }
    }

    private static boolean isMatching(String selfIndroduction){
        return true;
    }

    public static void approve(String data){
        Log.log(data , "signUp" , true);
    }

    private static String selfIntroduction(){
        return "self_introduction";
    }
}
