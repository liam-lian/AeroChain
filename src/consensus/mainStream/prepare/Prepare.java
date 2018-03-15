package consensus.mainStream.prepare;

import com.alibaba.fastjson.JSON;
import communication.Sender;
import consensus.mainStream.prePrepare.PrePrepare;
import consensus.mainStream.prePrepare.PrePrepareModel;
import constant.Constant;
import node.Node;
import util.Log;

public class Prepare {
    private static final int view = 2;

    private static final int height = 3;

    private static final int prePrepareDigest = 5;

    private static final int prepareDigest = 4;

    private static int validPrepare;

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
            if (validPrepare == 0)
                Log.log(Constant.SEPARTOR, "prepareLog");
            validPrepare++;
            Log.log(prepare.toString(), "prepareLog");
        }
    }

    public static int getValidPrepare() {
        return validPrepare;
    }

    public synchronized static void setValidPrepare(int validPrepare) {
        Prepare.validPrepare = validPrepare;
    }
}
