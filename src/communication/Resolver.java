package communication;

import block.Block;
import com.alibaba.fastjson.JSON;
import buffer.BufferPool;
import node.Node;

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
        return new Block("root" , "non" , "null" , new Date() , "0" , new ArrayList<>());
    }

    private static void resolveRecord(String data){
        BufferPool.add(data);
    }
}
