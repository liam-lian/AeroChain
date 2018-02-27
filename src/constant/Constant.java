package constant;

import java.util.ArrayList;
import java.util.List;

public class Constant {
    public static final int POTR = 7777;

    public static final String VERSION = "version_0";

    public static final int CHECKPOINT = 60;

    public static final int BLOCK_GAP = 60;

    public static List<String> address = new ArrayList<>();
    static {
        address.add("127.0.0.1");
    }
}
