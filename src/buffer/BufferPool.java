package buffer;

import java.util.ArrayList;
import java.util.List;

public class BufferPool {
    private static List<String> bufferPool = new ArrayList<>();

    public static synchronized String generateBlockRecord(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < bufferPool.size() ; i++){
            stringBuilder.append(bufferPool.get(i)).append("\n");
        }
        bufferPool = new ArrayList<>();
        return stringBuilder.toString();
    }

    public static synchronized void add(String record){
        bufferPool.add(record);
    }
}
