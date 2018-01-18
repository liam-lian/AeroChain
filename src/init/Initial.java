package init;

import communication.Reciever;

public class Initial {
    public static void init(){
        Reciever.init();
        while (true) Reciever.receive();
    }
}
