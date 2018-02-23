package buffer;

import model.record.Record;

import java.util.ArrayList;
import java.util.List;

public class BufferPool {
    private static List<String> bufferPool = new ArrayList<>();

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
    }
}
