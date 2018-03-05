package node;

import consensus.checkpoint.Checkpoint;
import consensus.mainStream.PrePrepare;
import consensus.mainStream.Prepare;
import buffer.BufferPool;
import consensus.signUp.SignUp;
import consensus.synchronize.Synchronize;
import consensus.viewChange.ViewChange;

public class Resolver {
    private static boolean switcher = true;

    public static void resolve(String data){
        if (switcher){
            if (data.startsWith("<pre-prepare"))
                PrePrepare.process(data);
            if (data.startsWith("<prepare"))
                Prepare.process(data);
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
            if (data.startsWith("Record")){
                BufferPool.add(data);
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
