package model.record;

public class Record {
    private String data;

    public Record(String data){
        this.data = data;
    }

    public Record(){}
    
    @Override
    public String toString() {
        return "Record{" +
                "data='" + data + '\'' +
                '}';
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
