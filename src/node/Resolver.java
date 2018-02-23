package node;

import com.alibaba.fastjson.JSON;
import model.block.Block;
import buffer.BufferPool;

import java.util.ArrayList;
import java.util.Date;

public class Resolver {
    public static void resolve(String data){
        if (!data.startsWith("Record")){
            Node.addBlock(resolveBlock(data));
        }
        else {
            resolveRecord(data);
        }
    }

    private static Block resolveBlock(String data){
        return JSON.parseObject(data , Block.class);
    }

    private static void resolveRecord(String data){
        BufferPool.add(data);
    }
}
