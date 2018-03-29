package model.block;

import model.record.Record;

import java.util.Date;
import java.util.List;

public class Block {
    private String merkleRoot;

    private String prevHash;

    private int primary;

    private int view;

    private Date timeStamp;

    private String version;

    private int index;

    private List<Record> data;

    public Block(String merkleRoot, String prevHash, int primary, int view, Date timeStamp, String version, List<Record> data , int index) {
        this.merkleRoot = merkleRoot;
        this.prevHash = prevHash;
        this.primary = primary;
        this.view = view;
        this.timeStamp = timeStamp;
        this.version = version;
        this.data = data;
        this.index = index;
    }

    public Block(){}

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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    public int getPrimary() {
        return primary;
    }

    public void setPrimary(int primary) {
        this.primary = primary;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Record> getData() {
        return data;
    }

    public void setData(List<Record> data) {
        this.data = data;
    }
}
