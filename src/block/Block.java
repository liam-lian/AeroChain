package block;

import record.Record;

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

    public Block(String merkleRoot, String prevHash, Integer primary, Date timeStamp, Integer version, List<Record> data) {
        this.merkleRoot = merkleRoot;
        this.prevHash = prevHash;
        this.primary = primary;
        this.timeStamp = timeStamp;
        this.version = version;
        this.data = data;
    }
}
