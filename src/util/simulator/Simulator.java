package util.simulator;

import com.alibaba.fastjson.JSON;
import model.record.Record;
import node.communication.Sender;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class Simulator implements Runnable{
    public static boolean switcher = true;
    private static BufferedReader bufferedReader;
    private static PrintWriter printWriter;

    public static void init(){
        try {
            printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\DSY\\blockchain\\log\\simulator"), "UTF-8")));
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("simulate.txt"), "UTF-8"));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while (Simulator.switcher){
            Record record = null;
            String line;
            try {
                if ((line = bufferedReader.readLine()) != null){
                    record = new Record(line);
                }
                TimeUnit.SECONDS.sleep(5);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            printWriter.println(record);
            Sender.broadcast(JSON.toJSONString(record));
        }
    }
}
