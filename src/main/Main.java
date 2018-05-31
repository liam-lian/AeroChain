package main;

import client.Client;
import main.init.Initial;
import model.block.Block;
import node.GenerateBlock;
import node.Node;
import node.communication.Reciever;
import node.consensus.mainStream.prepared.Prepared;
import node.consensus.signUp.SignUp;
import util.hash.Hash;
import util.simulator.Simulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by DSY on 2018/5/31.
 * 程序入口
 */
public class Main {
    public static void main(String[] args) throws Exception{
        Initial.init();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new Reciever());
        executorService.execute(new Simulator());
        executorService.execute(new GenerateBlock());
        executorService.execute(new Prepared());
        executorService.execute(new Client());
        while (true){
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while ((input = stdin.readLine()) != null){
                if (input.equals("show")){
                    List<Block> result = Node.getBlockChain();
                    for (Block b : result)
                        System.out.println(Hash.hash(b.toString()) + " " + b);
                }
                if (input.equals("signUp"))
                    SignUp.generate();
                if (input.startsWith("f:"))
                    Node.setFaultyNodeNums(input.replace("f:" , ""));
                if (input.equals("exit")){
                    Reciever.clean();
                    executorService.shutdown();
                    System.exit(0);
                }
                if (input.equals("simulator_on"))
                    Simulator.switcher = true;
                if (input.equals("simulator_off"))
                    Simulator.switcher = false;
            }
        }
    }
}
