package communication;

import block.Block;
import com.alibaba.fastjson.JSON;
import buffer.BufferPool;

public class Resolver {
    public static void resolve(String data){
        Block block = JSON.parseObject(data , Block.class);
        if (block instanceof Block){
            resolveBlock(block , data);
        }else {
            resolveRecord(data);
        }
    }

    private static void resolveBlock(Block block , String data){
        Sender.sendData(data);
    }

    private static void resolveRecord(String data){
        BufferPool.add(data);
    }
}
