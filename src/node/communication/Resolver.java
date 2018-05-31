package node.communication;

import com.alibaba.fastjson.JSON;
import node.consensus.checkpoint.Checkpoint;
import node.consensus.mainStream.prePrepare.PrePrepare;
import node.consensus.mainStream.prePrepare.PrePrepareModel;
import node.consensus.mainStream.prepare.Prepare;
import node.buffer.BufferPool;
import node.consensus.mainStream.prepare.PrepareModel;
import node.consensus.signUp.SignUp;
import node.consensus.synchronize.Synchronize;
import node.consensus.viewChange.ViewChange;
import constant.Constant;
import model.record.Record;

public class Resolver {
    private static boolean switcher = true;

    public static void resolve(String data){
        if (switcher){
            if (data.startsWith("{\"record\"")){
                BufferPool.add(JSON.parseObject(data , Record.class));
            }
            else if (data.startsWith(Constant.PRE_PREPARE_TAG)) {
                String tmp = data.replace(Constant.PRE_PREPARE_TAG , "");
                PrePrepareModel model = JSON.parseObject(tmp , PrePrepareModel.class);
                PrePrepare.process(model);
            }
            else if (data.startsWith(Constant.PREPARE_TAG)){
                String tmp = data.replace(Constant.PREPARE_TAG , "");
                PrepareModel model = JSON.parseObject(tmp , PrepareModel.class);
                Prepare.process(model);
            }
            else if (data.startsWith("<checkpoint"))
                Checkpoint.process(data);
            else if (data.startsWith("<synchrony"))
                Synchronize.process(data);
            else if (data.startsWith("<synchronized"))
                Synchronize.synchronize(data);
            else if (data.startsWith("<join"))
                SignUp.process(data);
            else if (data.startsWith("<approve"))
                SignUp.approve(data);
        }
        if (data.startsWith("<view-change")){
            ViewChange.process(data);
        }
        if (data.startsWith("<new-view")){
            ViewChange.newView(data);
        }
    }

    public static boolean isSwitcher() {
        return switcher;
    }

    public static void setSwitcher(boolean switcher) {
        Resolver.switcher = switcher;
    }
}
