package simulator;

import record.Record;
import communication.Sender;

import java.util.Date;

public class Simulator implements Runnable{
    public static boolean switcher = true;

    public void run(){
        while (Simulator.switcher){
            Record record = new Record(new Date().toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Sender.sendData(record.toString());
        }
    }
}
