package util.simulator;

import com.alibaba.fastjson.JSON;
import model.block.Block;
import model.record.Record;
import communication.Sender;
import node.Node;
import util.Log;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Simulator implements Runnable{
    public static boolean switcher = true;

    static BufferedReader bufferedReader;
    static PrintWriter printWriter;

    public static void init(){
        try {
            printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\DSY\\blockchain\\log\\sss"), "UTF-8")));
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
                    System.out.println(line);
                }
                else{
                    List<Block> result = Node.getBlockChain();
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Block b : result){
                        stringBuilder.append(b).append("\n");
                    }
                    Log.log(stringBuilder.toString() , "blocks" , false);
                    System.exit(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printWriter.println(record);
//            Sender.broadcast(JSON.toJSONString(record));
        }
    }
}
