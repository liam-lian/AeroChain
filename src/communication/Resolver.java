package communication;

import block.Block;
import com.alibaba.fastjson.JSON;
import buffer.BufferPool;
import node.Node;

public class Resolver {
    public static void resolve(String data){
        if (!data.startsWith("Record")){
            Block block = JSON.parseObject(data , Block.class);
            resolveBlock(block);
        }
        else {
            resolveRecord(data);
        }
    }

    private static void resolveBlock(Block block){
        Node.addBlock(block);
    }

    private static void resolveRecord(String data){
        BufferPool.add(data);
    }
}
