package consensus.checkpoint;

/**
 * Created by DSY on 2018/3/15.
 */
public class CheckpointModel {
    int height;

    int id;

    String digest;

    int view;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public CheckpointModel() {
    }
}
