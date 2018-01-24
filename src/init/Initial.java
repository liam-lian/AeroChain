package init;

import communication.Reciever;
import communication.Sender;

public class Initial {
    public static void init(){
        Reciever.init();
        Thread recieveThread = new Thread(new Reciever());
        recieveThread.start();
        Thread senderThread = new Thread(new Sender());
        senderThread.start();
    }
}
