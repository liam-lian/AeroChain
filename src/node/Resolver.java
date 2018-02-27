package node;

import consensus.checkpoint.Checkpoint;
import consensus.mainStream.PrePrepare;
import consensus.mainStream.Prepare;
import buffer.BufferPool;
import consensus.synchronize.Synchronize;

public class Resolver {
    public static void resolve(String data){
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
            SignU
        if (data.startsWith("Record")){
            BufferPool.add(data);
        }
    }
}
