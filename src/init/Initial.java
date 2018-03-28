package init;

import communication.Reciever;
import communication.Sender;
import util.Sign;
import util.simulator.Simulator;

public class Initial {
    public static void init(){
        Reciever.init();
        Simulator.init();
        Sign.init();
    }
}
