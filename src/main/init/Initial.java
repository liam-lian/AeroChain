package main.init;

import model.node.Node;
import node.communication.Receiver;
import client.Client;
import util.Sign;
import util.simulator.Simulator;

public class Initial {
    public static void init(){
        Receiver.init();
        Simulator.init();
        Client.init();
        Node.threshold();
    }
}
