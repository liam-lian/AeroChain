package node;

import block.Block;
import init.Initial;
import java.util.ArrayList;
import java.util.List;

public class Node {
    List<Block> blockList = new ArrayList<>();

    public static void main(String[] args) {
        Initial.init();
    }
}
