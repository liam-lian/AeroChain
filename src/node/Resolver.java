package node;

import com.alibaba.fastjson.JSON;
import consensus.checkpoint.Checkpoint;
import consensus.mainStream.prePrepare.PrePrepare;
import consensus.mainStream.prePrepare.PrePrepareModel;
import consensus.mainStream.prepare.Prepare;
import buffer.BufferPool;
import consensus.mainStream.prepare.PrepareModel;
import consensus.signUp.SignUp;
import consensus.synchronize.Synchronize;
import consensus.viewChange.ViewChange;
import constant.Constant;
import model.record.Record;

public class Resolver {
    private static boolean switcher = true;

    public static void resolve(String data){
        if (switcher){
            if (data.startsWith(Constant.PRE_PREPARE_TAG)) {
                String tmp = data.replace(Constant.PRE_PREPARE_TAG , "");
                PrePrepareModel model = JSON.parseObject(tmp , PrePrepareModel.class);
                PrePrepare.process(model);
            }
            if (data.startsWith(Constant.PREPARE_TAG)){
                String tmp = data.replace(Constant.PREPARE_TAG , "");
                PrepareModel model = JSON.parseObject(tmp , PrepareModel.class);
                Prepare.process(model);
            }
            if (data.startsWith("<checkpoint"))
                Checkpoint.process(data);
            if (data.startsWith("<synchrony"))
                Synchronize.process(data);
            if (data.startsWith("<synchronized"))
                Synchronize.synchronize(data);
            if (data.startsWith("<join"))
                SignUp.process(data);
            if (data.startsWith("<approve"))
                SignUp.approve(data);
            if (data.startsWith("{\"record\"")){
                BufferPool.add(JSON.parseObject(data , Record.class));
            }
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
