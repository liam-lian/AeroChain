package block;

import record.Record;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Block {
    String merkleRoot;

    String prevHash;

    String primary;

    Date timeStamp;

    String version;

    List<Record> data;

    public Block(String merkleRoot, String prevHash, String primary, Date timeStamp, String version, List<Record> data) {
        this.merkleRoot = merkleRoot;
        this.prevHash = prevHash;
        this.primary = primary;
        this.timeStamp = timeStamp;
        this.version = version;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Block{" +
                "merkleRoot='" + merkleRoot + '\'' +
                ", prevHash='" + prevHash + '\'' +
                ", primary=" + primary +
                ", timeStamp=" + timeStamp +
                ", version=" + version +
                ", data=" + data +
                '}';
    }
}
