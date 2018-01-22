package buffer;

import java.util.ArrayList;
import java.util.List;

public class BufferPool {
    private static List<String> bufferPool = new ArrayList<>();

    public static String generateBlock(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < bufferPool.size() ; i++){
            stringBuilder.append(bufferPool.get(i));
        }
        return stringBuilder.toString();
    }

    public static void add(String record){
        bufferPool.add(record);
    }
}
