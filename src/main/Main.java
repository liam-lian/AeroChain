package main;

import client.Client;
import main.init.Initial;
import model.block.Block;
import node.consensus.mainStream.generateBlock.GenerateBlock;
import model.node.Node;
import node.communication.Receiver;
import node.consensus.mainStream.prepared.Prepared;
import node.consensus.signUp.SignUp;
import util.hash.Hash;
import util.simulator.Simulator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by DSY on 2018/5/31.
 * 区块链应用包括区块链节点和客户端两部分
 * 区块链应用程序入口，启动客户端，区块链节点。
 */
public class Main {
    /** 标志着区块链应用是否启动 */
    public static boolean running = false;

    public static void main(String[] args) throws Exception{
        Initial.init();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new Receiver());
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
                    Receiver.clean();
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
