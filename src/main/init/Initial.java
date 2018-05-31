package main.init;

import node.communication.Reciever;
import client.Client;
import util.Sign;
import util.simulator.Simulator;

public class Initial {
    public static void init(){
        Reciever.init();
        Simulator.init();
        Sign.init();
        Client.init();
    }
}
