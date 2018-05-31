package node.consensus.mainStream.prepare;

public class PrepareModel {
    int id;

    int view;

    int height;

    String digest;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public PrepareModel() {}

    @Override
    public String toString() {
        return "PrepareModel{" +
                "id=" + id +
                ", view=" + view +
                ", height=" + height +
                ", digest='" + digest + '\'' +
                '}';
    }
}
