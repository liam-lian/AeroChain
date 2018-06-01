package main.init;

import node.communication.Receiver;
import client.Client;
import util.Sign;
import util.simulator.Simulator;

public class Initial {
    public static void init(){
        Receiver.init();
        Simulator.init();
        Sign.init();
        Client.init();
    }
}
