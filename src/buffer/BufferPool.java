package buffer;

import model.record.Record;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BufferPool {
    private static List<String> bufferPool = new ArrayList<>();

    private static Set<String> set = new HashSet<>();

    public static synchronized List<Record> generateBlockRecord(){
        List<Record> result = new ArrayList<>();
        for (int i = 0 ; i < bufferPool.size() ; i++){
            result.add(new Record(bufferPool.get(i)));
        }
        bufferPool = new ArrayList<>();
        return result;
    }

    public static synchronized void add(String record){
        bufferPool.add(record);
        set.add(record);
    }

    public static boolean isContain(Record record){
        return set.contains(record);
    }
}
