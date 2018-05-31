package node.consensus.mainStream.prepare;

import com.alibaba.fastjson.JSON;
import node.communication.Sender;
import node.consensus.mainStream.prePrepare.PrePrepare;
import node.consensus.mainStream.prePrepare.PrePrepareModel;
import constant.Constant;
import node.Node;
import util.Log;
import util.Sign;

public class Prepare {
    private static volatile int validPrepare;

    public static void generate(PrePrepareModel prePrepare){
        if (Node.isSwitcher()){
            PrepareModel model = new PrepareModel();
            model.setId(Node.getId());
            model.setView(prePrepare.getView());
            model.setHeight(prePrepare.getHeight());
            model.setDigest(prePrepare.getDigest());
            Sender.broadcast("<prepare>" + JSON.toJSONString(model));
        }
    }

    public synchronized static void process(PrepareModel prepare){
        if (prepare.getView() == Node.getView() && prepare.getHeight() == (Node.getBlockChainHeight() + 1) && prepare.getDigest().equals(PrePrepare.getDigest())) {
            Sign.jdkRSA();
            if (validPrepare == 0)
                Log.log(Constant.SEPARTOR, "prepareLog" ,true);
            setValidPrepare(validPrepare + 1);
            Log.log(prepare.toString(), "prepareLog" , true);
        }
    }

    public synchronized static int getValidPrepare() {
        return validPrepare;
    }

    public synchronized static void setValidPrepare(int validPrepare) {
        Prepare.validPrepare = validPrepare;
    }
}
