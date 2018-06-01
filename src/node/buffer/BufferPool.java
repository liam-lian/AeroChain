package node.buffer;

import model.record.Record;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by DSY on 2018/3/15.
 * 区块链节点存储未达成共识数据的数据缓冲池。
 * 我们假设不保存数据的输入顺序，因此使用HashSet存储顺序
 */
public class BufferPool {
    private static Set<Record> pool = new HashSet<>();

    public static synchronized List<Record> generateBlockRecord(){
        return new ArrayList<>(pool);
    }

    public static synchronized void add(Record record){
        pool.add(record);
    }

    public static boolean isContain(Record record){
        return pool.contains(record);
    }
}
