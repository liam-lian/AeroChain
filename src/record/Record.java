package record;

public class Record {
    private String data;

    public Record(String data){
        this.data = data;
    }

    @Override
    public String toString() {
        return "Record{" +
                "data='" + data + '\'' +
                '}';
    }
}
