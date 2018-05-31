package client;

import com.alibaba.fastjson.JSON;
import node.communication.Sender;
import model.record.Record;
import java.io.*;

/**
 * Created by DSY on 2018/5/10.
 */
public class Client implements Runnable{

    private static PrintWriter printWriter;

    public static void init(){
        try {
            printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\DSY\\blockchain\\log\\client"),"UTF-8")));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String input;
        try {
            while ((input = stdin.readLine()) != null){
                Record record = new Record(input);
                printWriter.println(record);
                Sender.broadcast(JSON.toJSONString(record));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
