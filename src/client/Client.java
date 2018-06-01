package client;

import com.alibaba.fastjson.JSON;
import constant.Constant;
import node.communication.Sender;
import model.record.Record;
import java.io.*;

/**
 * Created by DSY on 2018/5/10.
 * 理论上应该把区块链客户端从区块链应用中剥离，单独部署，但是在原型系统中为了简单，把客户端作为
 * 区块链节点的一部分功能实现。
 *
 * 客户端功能：（1）接受用户从控制台的输入，将数据在区块链中存储，同时把用户输入的数据在日志中记录
 */
public class Client implements Runnable{

    private static PrintWriter printWriter;

    public static void init(){
        try {
            printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Constant.CLIENT_LOG_ADDRESS), Constant.UTF_8)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String input;
        try {
            while ((input = stdin.readLine()) != null){
                printWriter.println("客户端输入" + input);
                Record record = new Record(input);
                Sender.broadcast(JSON.toJSONString(record));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
