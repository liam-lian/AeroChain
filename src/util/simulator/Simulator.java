package util.simulator;

import com.alibaba.fastjson.JSON;
import model.record.Record;
import communication.Sender;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Simulator implements Runnable{
    public static boolean switcher = true;
    public void run(){
        while (Simulator.switcher){
            Record record = new Record(new Date().toString());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Sender.broadcast(JSON.toJSONString(record));
        }
    }
}
