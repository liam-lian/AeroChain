package block;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Block {
    String merkleRoot;

    String prevHash;

    Integer primary;

    Date timeStamp;

    Integer version;

    List<Record> data;

    HashMap<Record, Integer> location;

}
